<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="对账差错交易查询">
	<snow:dataset id="balAcctErrors"
		path="com.ruimin.ifs.pmp.report.dataset.balAcctErrorsQuery"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="balAcctErrors"
		fieldstr="qStlmDateStart,qStlmDateEnd,qMchtInfo,qMchtOrg,qTxnType,qErrStat,qPagyNo,qThirdSsn,qChlTxnSsn,qCorrStat,qtpamOutTransactionId,qRefundStat"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="balAcctErrors" id="gridId" height="99%" fieldstr="stlmDate[95],chlMerId,chlMerName,txnType,txnDate,txnAmt,pagyName,thirdSsn,chlTxnSsn,errStat[100],errRemark,corrStat[90],refundStat,lastUpdTlr,lastUpdDateTime"
		label="对账差错信息" paginationbar="btnReturns,btnQuery,btnAdjust,btnDownload"></snow:grid>

	<!-- 手工调账窗口 -->
	<snow:window id="windAdjustment" title="对账差错-->手工调账" modal="true" closable="true" buttons="btnAdjustSubmit">
		<snow:form id="frmAdjustment" dataset="balAcctErrors" border="false"  fieldstr="stlmDate,txnAmt,chlMerId,chlMerName,pagyName,thirdSsn,chlTxnSsn,txnDate,txnType,errStat,corrStat" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnAdjustSubmit" dataset="balAcctErrors" hidden="true"/>
	</snow:window>
		<!-- 退款窗口 -->
	<snow:window id="windReturns" title="对账差错-->退款" modal="true" closable="true" buttons="btnReturnsSubmit">
		<snow:form id="frmReturns" dataset="balAcctErrors" border="false"  fieldstr="stlmDate,txnAmt,chlMerId,chlMerName,pagyName,thirdSsn,chlTxnSsn,txnDate,txnType,errStat" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnReturnsSubmit" dataset="balAcctErrors" hidden="true"/>
	</snow:window>
	<!-- 查询窗口 -->
	<snow:window id="windQuery" title="对账差错-->查询" modal="true" closable="true" buttons="btnQuerySubmit">
		<snow:form id="frmReturns" dataset="balAcctErrors" border="false"  fieldstr="stlmDate,txnAmt,chlMerId,chlMerName,pagyName,thirdSsn,chlTxnSsn,txnDate,txnType,errStat,pagySeqId,tpamTransactionId,tpamOutTransactionId" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnQuerySubmit" dataset="balAcctErrors" hidden="true"/>
	</snow:window>
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function balAcctErrors_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_qStlmDateStart").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_qStlmDateEnd").value;
		//转换日期
		var TxnDateStart=startDate.replace("-","").replace("-","");
		var TxnDateEnd=endDate.replace("-","").replace("-","");
		//如果起始日期不为空
		if(TxnDateStart != null && TxnDateStart != ""&&TxnDateEnd != null && TxnDateEnd != ""){
			//与当前日期比较，不能大于当前日期
			if(TxnDateStart>TxnDateEnd){
				$.warn("交易日期(起始),不能大于截止日期！");
			    return false; 
			}
		}
		return true;
	}
		function btnAdjust_onClick(){			
			var txnSsn = balAcctErrors_dataset.getValue("txnSsn");
			var corrStat = balAcctErrors_dataset.getValue("corrStat");
			var refundStat = balAcctErrors_dataset.getValue("refundStat");
			if(!txnSsn){
				$.warn("请先选择一条差错信息！");
				return false;
			}
			if(corrStat=='00'){
				$.warn("此交易以调账！");
				return false;
			}
			if(refundStat=='04'||refundStat=='05'){
				$.warn("退款审核以及退款查询审核不允许调账!");
				return false;
			}
			window_windAdjustment.open();
		}
		/**提交前检验数据格式*/
		 function btnAdjustSubmit_onClickCheck(button,commit){	
				var corrStat = balAcctErrors_dataset.getValue("corrStat");				
				if(corrStat==null||corrStat==""){
					$.warn("请输入调账状态！");
					return false;
				}
				if(corrStat!='00'){
					$.warn("请选择将调账状态设置为已调账！");
					return false;
				}
				return true;
		}
		function btnAdjustSubmit_postSubmit(){
			$.success("操作成功!", function() {
				window_windAdjustment.close();
			});
		}
		function window_windAdjustment_afterClose(){
			balAcctErrors_dataset.clearData();
			balAcctErrors_dataset.flushData(balAcctErrors_dataset.pageIndex);
		}
		
		//退款
		function btnReturns_onClick(){
			var corrStat = balAcctErrors_dataset.getValue("corrStat");
			var txnSsn = balAcctErrors_dataset.getValue("txnSsn");
			var errStat = balAcctErrors_dataset.getValue("errStat");
			var pagyNo = balAcctErrors_dataset.getValue("pagyNo");
			var refundStat = balAcctErrors_dataset.getValue("refundStat");
			var txnType = balAcctErrors_dataset.getValue("txnType");
			if(txnType!='01'){
				$.warn("请先选择一条支付交易！");
				return false;
			}
			if(corrStat=='02'){
				$.warn("调账待审核的交易不允许退款！");
				return false;
			}
			if(!txnSsn){
				$.warn("请先选择一条差错信息！");
				return false;
			}
			if(errStat!='03'){
				$.warn("请先选择选择通道单边帐退款！");
				return false;
			}
			// modify by fengwei 20180129 开放中信通道中信通道的差错退款  jira-1962
			if(pagyNo!='311'&&pagyNo!='304'&&pagyNo!='312'&&pagyNo!='313'){
			//modify eng jira-1962
				$.warn("请先选择威福通通道或支付宝直连或平安通道退款或中信通道！");
				return false;
			}
			if(refundStat=='00'||refundStat=='02'){
				$.warn("退款成功或受理成功的交易不允许再次发起退款!");
				return false;
			}
			if(refundStat=='04'||refundStat=='05'){
				$.warn("退款审核以及退款查询审核不允许再次发起退款!");
				return false;
			}
			window_windReturns.open();
		}
		function btnReturnsSubmit_postSubmit(){
			$.success("操作成功!", function() {
				window_windReturns.close();
				balAcctErrors_dataset.flushData(balAcctErrors_dataset.pageIndex);
			});
		}
		
		function btnDownload_onClickCheck(btnDownload, commit) {
			var qStlmDateStart = balAcctErrors_interface_dataset.getValue("qStlmDateStart");
			var qStlmDateEnd = balAcctErrors_interface_dataset.getValue("qStlmDateEnd");
			var qMchtInfo = balAcctErrors_interface_dataset.getValue("qMchtInfo");
			var qMchtOrgName = balAcctErrors_interface_dataset.getValue("qMchtOrgName");
			var qMchtOrg = balAcctErrors_interface_dataset.getValue("qMchtOrg");
			var qTxnType = balAcctErrors_interface_dataset.getValue("qTxnType");
			var qErrStat = balAcctErrors_interface_dataset.getValue("qErrStat");
			var qPagyNo = balAcctErrors_interface_dataset.getValue("qPagyNoName");
			var qThirdSsn = balAcctErrors_interface_dataset.getValue("qThirdSsn");
			var qChlTxnSsn = balAcctErrors_interface_dataset.getValue("qChlTxnSsn");
			var qCorrStat = balAcctErrors_interface_dataset.getValue("qCorrStat");
			
			
			if(qStlmDateStart){
				qStlmDateStart = qStlmDateStart.format("yyyyMMdd");
				
			}
			if(qStlmDateEnd){
				qStlmDateEnd = qStlmDateEnd.format("yyyyMMdd");
				
			}
			//如果起始日期不为空
			if(qStlmDateStart != null && qStlmDateStart != ""&&qStlmDateEnd != null && qStlmDateEnd != ""){
				//与当前日期比较，不能大于当前日期
				if(qStlmDateStart>qStlmDateEnd){
					$.warn("交易日期(起始),不能大于截止日期！");
				    return false; 
				}
			}
			var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.BalAcctErrorsAction:downloadReport" />';
			url = url+"&qStlmDateStart="+qStlmDateStart+"&qStlmDateEnd="+qStlmDateEnd+"&qMchtInfo="+qMchtInfo+"&qMchtOrg="+qMchtOrg+"&qMchtOrgName="+qMchtOrgName+"&qTxnType="+qTxnType+"&qErrStat="+qErrStat+"&qPagyNo="+qPagyNo+"&qThirdSsn="+qThirdSsn+"&qChlTxnSsn="+qChlTxnSsn+"&qCorrStat"+qCorrStat;
			url = encodeURI(url);
			url = encodeURI(url);
       	 	window.location.href = url;
		    return true;
		}
		//查询
		function btnQuery_onClick(){
			var corrStat = balAcctErrors_dataset.getValue("corrStat");
			var txnSsn = balAcctErrors_dataset.getValue("txnSsn");
			var errStat = balAcctErrors_dataset.getValue("errStat");
			var pagyNo = balAcctErrors_dataset.getValue("pagyNo");
			var refundStat = balAcctErrors_dataset.getValue("refundStat");
			var txnType = balAcctErrors_dataset.getValue("txnType");
			if(txnType!='01'){
				$.warn("请先选择一条支付交易！");
				return false;
			}
			if(!txnSsn){
				$.warn("请先选择一条差错信息！");
				return false;
			}
			if(corrStat=='02'){
				$.warn("调账待审核的交易不允许查询！");
				return false;
			}
			if(errStat!='03'){
				$.warn("请先选择选择通道单边帐退款！");
				return false;
			}
			// modify by fengwei 20180129 开放中信通道中信通道的差错退款  jira-1962
			if(pagyNo!='311'&&pagyNo!='312'&&pagyNo!='313'){
			//	modify end jira-1962
				$.warn("请先选择威福通通道或平安通道退款或中信通道！");
				return false;
			}
			window_windQuery.open();
		}
		function btnQuerySubmit_onClickCheck(button,commit){
			var refundStat = balAcctErrors_dataset.getValue("refundStat");
			if(refundStat!='02'){
				$.warn("只有退货受理成功的交易才可以去查询状态!");
				return false;
			}
			return true;
		}
		function btnQuerySubmit_postSubmit(){
			$.success("操作成功!", function() {
				window_windQuery.close();
				balAcctErrors_dataset.flushData(balAcctErrors_dataset.pageIndex);
			});
		}
	</script>
</snow:page>