<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道交易查询">
	<!-- 交易查询dataset -->
    <snow:dataset id="dtstTxnRecord" path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnQuery" init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="查询条件" id="queryCondition"  dataset="dtstTxnRecord" fieldstr="qTxnDateStart,qTxnDateEnd,qChnlNo,qChnlTxnSsn,qPagyNo,qThirdPagyTxnSsn,qTxnState,qPagyCoreTxnSsn"></snow:query>
	<!-- 交易列表表格 -->
	<snow:grid dataset="dtstTxnRecord" label="通道交易信息" id="gridTxnList" fieldstr="coreTxnSsn,coreTxnTime[150],chlName,chlTxnSsn,pagyName,pagyTxnSsn,txnAmt,status,opr[80]" paginationbar="" fitcolumns="true" height="99%"></snow:grid>
	<!-- 详情框体 -->
	<snow:window id="windowDetail" title="通道交易详情" modal="true"
		closable="true" buttons="btnReturn" width="900" height="880">
		<!-- 渠道交易信息 -->
		<snow:form id="formDtlChnl" dataset="dtstTxnRecord" label="渠道交易信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="chlNo,chlName,chlTxnSsn,chlTxnTime"></snow:form>
		<!-- 通道核心信息 -->
		<snow:form id="formDtlPagyCore" dataset="dtstTxnRecord" label="通道核心交易信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="coreTxnSsn,coreTxnTime,status,acctTypeName,acctNo,txnAmt,origPagyTxnSsn,origPagyTxnTime,respCode,respMsg"></snow:form>
		<!-- 支付通道信息 -->
		<snow:form id="formDtlThirdPagy" dataset="dtstTxnRecord" label="支付通道交易信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="pagyNo,pagyName,pagyType,pagyMerId,pagySubMerId,pagySubMerName,pagyTxnSsn,pagyTxnTime,pagyTxnCode,pagyTxnName"></snow:form>
		<snow:button id="btnReturn" dataset="dtstTxnRecord" hidden="true"></snow:button>
	</snow:window>
	



<script type="text/javascript">
//**********对输入时间进行验证
function dtstTxnRecord_interface_dataset_queryCondition_onClickCheck(button, commit) {
	//交易日期（起始）
	var startDate=document.getElementById("editor_query_qTxnDateStart").value;
	//交易日期（截止）
	var endDate=document.getElementById("editor_query_qTxnDateEnd").value;

	//转换日期
	var TxnDateStart=startDate.replace("-","").replace("-","");
	var TxnDateEnd=endDate.replace("-","").replace("-","");
	if(TxnDateStart != null && TxnDateStart != ""&&TxnDateEnd != null && TxnDateEnd != ""){
		//与当前日期比较，不能大于当前日期
		if(TxnDateStart>TxnDateEnd){
			$.warn("交易日期(起始),不能大于截止日期！");
		    return false; 
		}
	}
	return true;
}
	//交易列表刷新时，给操作列返回详情链接
	function gridTxnList_opr_onRefresh(record, fieldId, fieldValue){
		if(record){
			return "<span style='width:100%;text-align:center' class='fa fa-eye'><a href=\"JavaScript:showDetail()\">详情</a></span>";
		}
	}
	
	//详情链接点击事件
	function showDetail(){
		
		window_windowDetail.open();
	}
	//返回按钮点击事件
	function btnReturn_onClick(){
		window_windowDetail.close();
	}
</script>
</snow:page>