<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="交易详情">
		<!-- modify by lengjingyu 20180205 交易查询界面变更需求 jira-1865 -->
		<snow:dataset id="txnQueryDetil" path="com.ruimin.ifs.pmp.txnQuery.dataset.txnQueryDetil" init="true" submitMode="current" ></snow:dataset>
		<!-- 商户订单信息 -->
		<snow:form id="formDtlMchtOrder" dataset="txnQueryDetil" label="商户订单信息"
			border="true" collapsible="true" colnum="4" labelwidth="100" 
			fieldstr="mchtId,mchtName,txnOrderId,txnOrderAmt"/>
		<!-- 平台交易信息 -->
		<snow:form id="formDtlOrder" dataset="txnQueryDetil" label="平台交易信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="txnSeqId,accessType,txnDate,txnTime,txnType,txnAmt,txnState,txnAccTypeName,payProduct,txnRespCode,txnRespMsg,pointId"></snow:form>
		<!-- 退款信息 -->
		<snow:form id="formDtlOrderRefund" dataset="txnQueryDetil" label="交易退款信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="origTxnSeqId,srAmtSum"></snow:form>
		<!-- 通道交易信息 -->
		<snow:form id="formDtlPagy" dataset="txnQueryDetil" label="通道交易信息"
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="pagySeqId,tpamOutId,pagyTxnTm,pagyRespCode,pagyRespMsg,tpamErrCode,tpamErrMsg"></snow:form>
		<!-- 营销活动信息 -->
		<snow:form id="formActiveNo" dataset="txnQueryDetil" label="营销活动信息 "
			border="true" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="activeNo,activeNm,favourableAmt"></snow:form>		
<script type="text/javascript">
	function initCallGetter_post() {
		
		
	}
</script>
</snow:page>