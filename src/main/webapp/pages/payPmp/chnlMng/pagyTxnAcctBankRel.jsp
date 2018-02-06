<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%
Boolean initData = true;
String qpagyTxnCode = StringUtil.filtrateSpecialCharater( request.getParameter("pagyTxnCode"));
%>
<snow:page title="账户列表">
	<!-- 渠道信息数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnAcctBankRel"
		id="pagyTxnAcctBankRel" init="false" submitMode="current" ></snow:dataset>
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnAcctBankList"
		id="pagyTxnAcctBankList" init="false" submitMode="current" ></snow:dataset>
	<!-- 显示表格 -->
	<snow:grid dataset="pagyTxnAcctBankRel" height="99%" border="false" id="gridIdBank"
		fitcolumns="true"
		fieldstr="acctTypeNoRelName[350],bankCount[370]"></snow:grid>	
		
  <!-- 详情窗口 -->
	<snow:window id="windowBankList" closable="true" width="600"
		title="银行列表" modal="true" buttons="btnSave">
		<snow:grid dataset="pagyTxnAcctBankList"  border="false" id="gridIdBankList"
		fitcolumns="true"
		fieldstr="bankName[520]"></snow:grid>
	</snow:window>
<script>
	function initCallGetter_post() {
		pagyTxnAcctBankRel_dataset.setParameter("qpagyTxnCode",<%=qpagyTxnCode%>);
		pagyTxnAcctBankRel_dataset.flushData();
	}
	
	function gridIdBank_bankCount_onRefresh(record, fieldId, fieldValue){		
		if(record){
			var acctTypeNo= record.getValue("acctTypeNo");
			return "<span style='width:100%;text-align:center'><a href=\"JavaScript:openBankList('"+acctTypeNo+"')\">"+fieldValue+"</a></span>"; 
		}else{
			return "&nbsp;";
		}
	}
	
	function openBankList(acctTypeNo){
		pagyTxnAcctBankList_dataset.setParameter("qpagyTxnCode",<%=qpagyTxnCode%>);
		pagyTxnAcctBankList_dataset.setParameter("qacctTypeNo",acctTypeNo);
		pagyTxnAcctBankList_dataset.flushData();
		window_windowBankList.open();
	}
	
	
</script>


</snow:page>