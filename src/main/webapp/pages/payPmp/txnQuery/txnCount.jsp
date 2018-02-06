<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<snow:page title="交易统计">

	<!-- 交易统计 -->
    <snow:dataset id="dtstTxnCount" path="com.ruimin.ifs.pmp.txnQuery.dataset.txnCount" init="true" submitMode="current"></snow:dataset>
	
	<!-- 查询条件 -->
	<snow:query label="查询条件" id="queryCondition"  dataset="dtstTxnCount" 
	fieldstr="qTxnDateStart,qTxnDateEnd,qAccessType"></snow:query>
	<!-- 交易列表表格 -->
	<snow:grid dataset="dtstTxnCount"  id="gridTxnList"  label="交易统计结果"
	fieldstr="txnDate,txnTemlType,totalItems,successItems,failItems,successRate,failRate" 
	paginationbar="" fitcolumns="true" height="99%"></snow:grid>

	<script type="text/javascript">
	/*
		return 0 :date1 = date2
		return 1 :date1 < date2
		return -1:date1 > date2
	*/
	function compareDate(date1,date2){
		var flag = (date2 - date1);
		if(flag > 0){
			return 1;
		}else if(flag < 0){
			return -1;
		}else{
			return 0
		}		
	}
	
	// 当用户点击查询按钮时检查输入框的数据
	function dtstTxnCount_interface_dataset_queryCondition_onClickCheck(button,commit){
		//交易日期（起始）
		var TxnDateStart=document.getElementById("editor_query_qTxnDateStart").value.replace("-","").replace("-","");
		//交易日期（截止）
		var TxnDateEnd = document.getElementById("editor_query_qTxnDateEnd").value.replace("-","").replace("-","");
		//获取当前日期
		var currentTime="<%= new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())%>";
		if((TxnDateStart != null && TxnDateStart != "" ) && (TxnDateEnd != null && TxnDateEnd != "" )){
			// 开始日期compareTo当天日期
			if((compareDate(TxnDateStart,currentTime) == -1) || (compareDate(TxnDateStart,currentTime) == 0)){
				$.warn("交易日期(起始)应小于当前日期!");
				return false;
			}
			// 截至日期compareTo当天日期
			if((compareDate(TxnDateEnd,currentTime) == -1) || (compareDate(TxnDateEnd,currentTime) == 0)){
				$.warn("交易日期(截止)应小于当前日期!");
				return false;
			}
			// 开始日期compareTo截至日期
			if((compareDate(TxnDateStart,TxnDateEnd) == -1)){
				$.warn("交易日期(起始)不能大于交易日期(截止)!");
				return false;
			}
		}else if(TxnDateStart != null && TxnDateStart != "" ){
			// 开始日期compareTo当天日期
			if((compareDate(TxnDateStart,currentTime) == -1) || (compareDate(TxnDateStart,currentTime) == 0)){
				$.warn("交易日期(起始)应小于当前日期!");
				return false;
			}
		}else if(TxnDateEnd != null && TxnDateEnd != ""){
			// 截至日期compareTo当天日期
			if((compareDate(TxnDateEnd,currentTime) == -1) || (compareDate(TxnDateEnd,currentTime) == 0)){
				$.warn("交易日期(截止)应小于当前日期!");
				return false;
			}
		}
		commit();
	}
	
	
	</script>
</snow:page>