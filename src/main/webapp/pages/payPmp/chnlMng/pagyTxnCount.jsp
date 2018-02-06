<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<snow:page title="通道交易统计">
	<!-- 交易查询dataset -->
    <snow:dataset id="pagyTxnCount" path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnCount" init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="查询条件" id="queryCondition"  dataset="pagyTxnCount" fieldstr="qTxnDateStart,qTxnDateEnd,qPagyNo"></snow:query>
	<!-- 交易列表表格 -->
	<snow:grid dataset="pagyTxnCount" label="通道交易统计" id="gridTxnList" fieldstr="pagyName,coreTxnTime,totalItems,successItems,failItems,successRate,failRate" paginationbar="" fitcolumns="true" height="99%"></snow:grid>

<script type="text/javascript">
    //**********对输入时间进行验证
	function pagyTxnCount_interface_dataset_queryCondition_onClickCheck(button, commit) {
		//交易日期（起始）
		var qTxnDateStart=document.getElementById("editor_query_qTxnDateStart").value;
		//交易日期（截止）
		var qTxnDateEnd=document.getElementById("editor_query_qTxnDateEnd").value;
		//获取当前日期
		var now="<%= new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())%>";
		//转换日期
		var TxnDateStart=qTxnDateStart.replace("-","").replace("-","");
		var TxnDateEnd=qTxnDateEnd.replace("-","").replace("-","");
		//如果起始日期不为空
		if(TxnDateStart != null && TxnDateStart != ""){
			//与当前日期比较，不能大于当前日期
			if(TxnDateStart>now || TxnDateStart == now){
				$.warn("交易日期(起始),不能大于等于当前日期！");
			    return false; 
			}
			//如果截止日期也不为空，则不能大于截止日期
			if(TxnDateEnd != null && TxnDateEnd != ""){
				if(TxnDateStart>TxnDateEnd){
					$.warn("交易日期(开始),不能大于交易日期(截止)！");
				    return false; 
				}
			}
		}
		//如果截止日期不为空
		if(TxnDateEnd != null && TxnDateEnd != ""){
			//与当前日期比较，不能大于当前日期
			if(TxnDateEnd>=now || TxnDateStart == now){
				$.warn("交易日期(截止),不能大于等于当前日期！");
			    return false; 
			}
			//如果开始日期也不为空，则不能大于截止日期
			if(TxnDateStart != null && TxnDateEnd != ""){
				if(TxnDateStart>TxnDateEnd){
					$.warn("交易日期(开始),不能大于交易日期(截止)！");
				    return false; 
				}
			}
		}
		return true;
	}
</script>
</snow:page>