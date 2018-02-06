<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="对账结果交易查询">
	<snow:dataset id="balAcctResult"
		path="com.ruimin.ifs.pmp.report.dataset.balAcctResultQuery"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="balAcctResult"
		fieldstr="qStlmDateStart,qStlmDateEnd,qMchtInfo,qMchtOrg,qTxnType,qChkStat,qPagyNo,qThirdSsn,qChlTxnSsn,qChlMchtNo"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="balAcctResult" id="gridId" height="99%" fieldstr="stlmDate[95],chlMerId,chlMerName,txnType,txnDate,txnAmt,pagyName,txnSsn,chlTxnSsn,chkStat[90],chlMchtNo"
		label="对账结果信息" paginationbar="btnDownload"></snow:grid>


	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function balAcctResult_interface_dataset_queryId_onClickCheck(button, commit) {
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
	function btnDownload_onClickCheck(btnDownload, commit) {
		var qStlmDateStart = balAcctResult_interface_dataset.getValue("qStlmDateStart");
		var qStlmDateEnd = balAcctResult_interface_dataset.getValue("qStlmDateEnd");
		var qMchtInfo = balAcctResult_interface_dataset.getValue("qMchtInfo");
		var qMchtOrgName = balAcctResult_interface_dataset.getValue("qMchtOrgName");
		var qMchtOrg = balAcctResult_interface_dataset.getValue("qMchtOrg");
		var qTxnType = balAcctResult_interface_dataset.getValue("qTxnType");
		var qChkStat = balAcctResult_interface_dataset.getValue("qChkStat");
		var qPagyNo = balAcctResult_interface_dataset.getValue("qPagyNoName");
		var qThirdSsn = balAcctResult_interface_dataset.getValue("qThirdSsn");
		var qChlTxnSsn = balAcctResult_interface_dataset.getValue("qChlTxnSsn");
		var qChlMchtNo = balAcctResult_interface_dataset.getValue("qChlMchtNo");
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
		var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.BalAcctResultAction:downloadReport" />';
		url = url+"&qStlmDateStart="+qStlmDateStart+"&qStlmDateEnd="+qStlmDateEnd+"&qMchtInfo="+qMchtInfo+"&qMchtOrg="+qMchtOrg+"&qMchtOrgName="+qMchtOrgName+"&qTxnType="+qTxnType+"&qChkStat="+qChkStat+"&qPagyNo="+qPagyNo+"&qThirdSsn="+qThirdSsn+"&qChlTxnSsn="+qChlTxnSsn+"&qChlMchtNo="+qChlMchtNo;
		url = encodeURI(url);
		url = encodeURI(url);
   	 	window.location.href = url;
	    return true;
	}
	</script>
</snow:page>