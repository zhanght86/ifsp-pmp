<%@page import="com.ruimin.ifs.core.util.DateUtil"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量监控">

	<snow:dataset id="BatchStepList"
		path="com.ruimin.ifs.mng.dataset.batch.BatchStepList" init="true" readOnly="true"></snow:dataset>

	<snow:query id="query" dataset="BatchStepList" fieldstr="bhdate"
		width="95%"></snow:query>
		
	<snow:grid label="批量运行信息" dataset="BatchStepList" id="datatable2"
		fieldstr="batchstatus,batchcurrentstep,batchsubstepcount,successstepcount,failstepcount"
		width="95%"></snow:grid>
	<br />
	<snow:grid dataset="BatchStepList" id="datatable1"
		fieldstr="stepdispname,substepname,starttime,endtime,status"
		width="95%"></snow:grid>
<%
	SessionUserInfo userInfo = SessionUtil.getSessionUserInfo(request);
	String bhDate = DateUtil.dateToNumber(userInfo.getBhDate());
%>
	<script type="text/javascript">
		var finding = false;
		var bhDate="<%=bhDate%>" ;
		function initCallGetter_post() {
			getRunningStep();
			BatchStepList_interface_dataset.setValue("bhdate", bhDate);
		}
		function getRunningStep() {
			finding = true;
			for ( var index = 1; index <= BatchStepList_dataset.pageCount; index++) {
				BatchStepList_dataset.flushData(index);
				var findRunning = false;
				for ( var rowIndex = 1; rowIndex <= BatchStepList_dataset.pageSize; rowIndex++) {
					if (BatchStepList_dataset.getValue("status") == '运行') {
						findRunning = true;
						break;
					}
					BatchStepList_dataset.moveNext();
				}
				if (findRunning)
					break;
			}
			finding = false;
		}

		function setRowColor() {
			for ( var i = 1; i < datatable1.rows.length; i++) {
				datatable1.rows[i].bgColor = 'red';
			}
		}

		function BatchStepList_dataset_flushDataPost(dataset) {
		}

		function datatable2_successstepcount_onRefresh(record, fieldId, fieldValue) {
			if(record){
				var bhDate = BatchStepList_interface_dataset.getString("bhdate");
				return "<a href=\"Javascript:openBatchDetails('F','"
						+ bhDate + "')\">" + fieldValue + "</a>";
			}
		}

		function datatable2_failstepcount_onRefresh(record, fieldId, fieldValue) {
			if(record){
				var bhDate = BatchStepList_interface_dataset.getString("bhdate");
				return "<a href=\"Javascript:openBatchDetails('E','"
						+ bhDate + "')\">" + fieldValue + "</a>";
			}
			
		}

		function datatable1_status_onRefresh(record, fieldId, fieldValue) {
			var color = null;
			if (fieldValue == '异常中断') {
				color = "red";
			} else if (fieldValue == '运行') {
				color = 'orange';
			} else if (fieldValue == '结束') {
				color = 'green';
			} else {
				color = 'blue';
			}
			if(color==null){
				return fieldValue;
			}else{
				return "<font color='"+color+"'>"+fieldValue+"</font>";
			}
		}

		function openBatchDetails(statuscode, bhDate) {
			var path = "/pages/mng/batch/BatchStepMonitor.jsp?statuscode="+ statuscode + "&bhdate=" + bhDate;
			$.open("batchDetail", "批量步骤运行明细", path, "850", "500", false, true, null, false);
		}
	</script>
</snow:page>
