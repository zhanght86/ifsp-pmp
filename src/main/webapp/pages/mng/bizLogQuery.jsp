<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="日志查询">
	<snow:dataset id="bizLogQuery" path="com.ruimin.ifs.mng.dataset.BizLogQuery" init="true"></snow:dataset>
	<snow:query id="queryId" dataset="bizLogQuery" fieldstr="startDate,endDate,tlrno,oprcode1" label="请输入查询条件"></snow:query>
	<snow:grid dataset="bizLogQuery" id="gridId" fieldstr="txnDate[120],txnStartTime[120],oprcode[120],tlrName[120],ipAdr[120],txnBizLog1" exporter="exporterId" label="操作员日志" height="99%" fitcolumns="true"></snow:grid>
	<snow:exporter dataset="bizLogQuery" id="exporterId" type="XLS" fieldstr="txnDate,oprcode,tlrName,txnStartTime,txnEndTime,ipAdr,txnBizLog1"></snow:exporter>

<script type="text/javascript">
    //**********对输入时间进行验证
	function bizLogQuery_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_startDate").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_endDate").value;
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
</script>
</snow:page>
