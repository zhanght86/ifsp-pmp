<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="商户入账结果查询">
	<snow:dataset id="mchtInAcctRslt"
		path="com.ruimin.ifs.pmp.report.dataset.mchtInAcctRslt"
		submitMode="current" init="true"></snow:dataset>
	<snow:dataset id="mchtInAcctDtl"
		path="com.ruimin.ifs.pmp.report.dataset.mchtInAcctDtl"
		submitMode="current" init="false"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="mchtInAcctRslt"
		fieldstr="qStlmDateStart,qStlmDateEnd,qMchtInfo,qMchtOrg,qInAcctNo,qInAcctStat"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="mchtInAcctRslt" id="gridId" height="99%" fieldstr="stlmDate[95],chlMerId,chlMerName,amtFlg,txnCount,txnAmt,feeAmt,inAcctNo,inAcctAmt,planInAcctDate,inAcctStat[90],inAcctTime,statMark,operate"
		label="入账结果信息" paginationbar="btnDownload"></snow:grid>
	
	<!-- 详情窗体 -->
	<snow:window id="windDtl" title="商户入账结果-->详情 " modal="true"
		closable="true" buttons="btnDownLoadDtl,btnClose" height="750" width="900">
		<snow:form id="frmDtl" dataset="mchtInAcctRslt"
			border="false" label="入账详情" fieldstr="stlmDate,amtFlg,chlMerId,chlMerName,txnCount,txnAmt,feeAmt,inAcctNo,inAcctAmt,planInAcctDate,inAcctStat,inAcctTime,statMark" collapsible="false"
			colnum="4">
		</snow:form>
		<snow:grid dataset="mchtInAcctDtl" id="grdDtl"  fieldstr="inAcctDate[95],txnType[95],txnAmt[115],tnxTime[95],setlFeeAmt[115],txnSeqId[290]"
		label="交易明细"  border = "true" fitcolumns="true" paginationbar="btnDownloadDtl"></snow:grid>
		<snow:button id="btnDownloadDtl" dataset="mchtInAcctDtl" hidden="true"></snow:button>
		<snow:button id="btnClose" dataset="mchtInAcctDtl" hidden="true"></snow:button>
	</snow:window>
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function mchtInAcctRslt_interface_dataset_queryId_onClickCheck(button, commit) {
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
		function gridId_operate_onRefresh(record, fieldId, fieldValue){
			if(record){
				return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:showDetail()\">详情</a></span>";
			}
		}
		function showDetail(){
			mchtInAcctDtl_dataset.setParameter("qSettleDate",mchtInAcctRslt_dataset.getValue("stlmDate"));
			mchtInAcctDtl_dataset.setParameter("qMchtId",mchtInAcctRslt_dataset.getValue("chlMerId"));
			mchtInAcctDtl_dataset.setParameter("qAmtFlg",mchtInAcctRslt_dataset.getValue("amtFlg"));
			mchtInAcctDtl_dataset.flushData(mchtInAcctDtl_dataset.pageIndex);
			window_windDtl.open();
		}
		function btnClose_onClick(){
			window_windDtl.close();
		}
		function btnDownload_onClickCheck(btnDownload, commit) {
			var qStlmDateStart = mchtInAcctRslt_interface_dataset.getValue("qStlmDateStart");
			var qStlmDateEnd = mchtInAcctRslt_interface_dataset.getValue("qStlmDateEnd");
			var qMchtInfo = mchtInAcctRslt_interface_dataset.getValue("qMchtInfo");
			var qMchtOrgName = mchtInAcctRslt_interface_dataset.getValue("qMchtOrgName");
			var qMchtOrg = mchtInAcctRslt_interface_dataset.getValue("qMchtOrg");
			var qInAcctNo = mchtInAcctRslt_interface_dataset.getValue("qInAcctNo");
			var qInAcctStat = mchtInAcctRslt_interface_dataset.getValue("qInAcctStat");
			
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
			var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.MchtInAcctRsltAction:downloadReport" />';
			url = url+"&qStlmDateStart="+qStlmDateStart+"&qStlmDateEnd="+qStlmDateEnd+"&qMchtInfo="+qMchtInfo+"&qMchtOrg="+qMchtOrg+"&qMchtOrgName="+qMchtOrgName+"&qInAcctNo="+qInAcctNo+"&qInAcctStat="+qInAcctStat;
			url = encodeURI(url);
			url = encodeURI(url);
       	 	window.location.href = url;
		    return true;
		}
		function btnDownloadDtl_onClickCheck(btnDownload, commit) {
			var qStlmDate = mchtInAcctRslt_dataset.getValue("stlmDate");
			var qChlMerId = mchtInAcctRslt_dataset.getValue("chlMerId");
			var qChlMerName = mchtInAcctRslt_dataset.getValue("chlMerName");
			var qTxnCount = mchtInAcctRslt_dataset.getValue("txnCount");
			var qTxnAmt = mchtInAcctRslt_dataset.getValue("txnAmt");
			var qFeeAmt = mchtInAcctRslt_dataset.getValue("feeAmt");
			var qInAcctNo = mchtInAcctRslt_dataset.getValue("inAcctNo");
			var qInAcctAmt = mchtInAcctRslt_dataset.getValue("inAcctAmt");
			var qPlanInAcctDate = mchtInAcctRslt_dataset.getValue("planInAcctDate");
			var qInAcctStat = mchtInAcctRslt_dataset.getValue("inAcctStat");
			var qInAcctTime = mchtInAcctRslt_dataset.getValue("inAcctTime");
			var qStatMark = mchtInAcctRslt_dataset.getValue("statMark");
			var qAmtFlg = mchtInAcctRslt_dataset.getValue("amtFlg");
			
			var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.MchtInAcctDtlAction:downloadReport" />';
			url = url+"&qStlmDate="+qStlmDate+"&qChlMerId="+qChlMerId+"&qChlMerName="+qChlMerName+"&qTxnCount="+qTxnCount+"&qTxnAmt="+qTxnAmt+"&qFeeAmt="+qFeeAmt+"&qInAcctNo="+qInAcctNo+"&qInAcctAmt="+qInAcctAmt+"&qPlanInAcctDate="+qPlanInAcctDate+"&qInAcctStat="+qInAcctStat+"&qInAcctTime="+qInAcctTime+"&qStatMark="+qStatMark+"&qAmtFlg="+qAmtFlg;
			url = encodeURI(url);
			url = encodeURI(url);
       	 	window.location.href = url;
		    return true;
		}
		function window_windDtl_afterClose(){
			mchtInAcctDtl_dataset.clearData();	
			mchtInAcctDtl_dataset.flushData(1);			
		}
	</script>
</snow:page>