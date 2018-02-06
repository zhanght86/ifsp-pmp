<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="定时任务设置">
	<snow:dataset id="BopTimedSchedulerManage" path="com.ruimin.ifs.mng.dataset.BopTimedSchedulerManage" init="true"></snow:dataset>
	<snow:grid dataset="BopTimedSchedulerManage" id="gridId" fitcolumns="true" height="99%" fieldstr="jobno[60],desc0,isRunning,runtime,daysOfMonth[60],startTime,endTime,repeatCnt[60],repeatTime,processFunction" paginationbar="btnAdd,btnMod,btnDel"></snow:grid>
	<snow:window id="windowId" title="定时任务" modal="true" closable="true" buttons="btnSave">
		<snow:form id="formAddId" dataset="BopTimedSchedulerManage" label="*" border="false" fieldstr="jobno,desc0,runtime,daysOfMonth,startTime,endTime,repeatCnt,repeatTime,processFunction" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave" dataset="BopTimedSchedulerManage" hidden="true"></snow:button>
	</snow:window>
	<snow:button id="btnDelete" dataset="BopTimedSchedulerManage" hidden="true"></snow:button>
	<script>
		function btnAdd_onClick(btn){
			BopTimedSchedulerManage_dataset.setFieldReadOnly("jobno",false);
			window_windowId.open();
		}
		function btnMod_onClick(btn){
			BopTimedSchedulerManage_dataset.setFieldReadOnly("jobno",true);
			window_windowId.open();
		}
		function window_windowId_beforeClose(wmf){
			BopTimedSchedulerManage_dataset.cancelRecord();
		}
		function btnSave_onClickCheck(btn){
			var runtime = BopTimedSchedulerManage_dataset.getValue("runtime");
			if((null != runtime || "" != runtime) && "97" == runtime){
				var daysOfMonth = BopTimedSchedulerManage_dataset.getValue("daysOfMonth");
				if(null == daysOfMonth || "" == daysOfMonth){
					$.error("当执行方式为【每月某日】时,必须指定对应的执行时间!");
					return false;
				}
			}
			return true;
		}
		function btnSave_postSubmit(){
			$.success("操作成功!", function() {
				window_windowId.close();
				BopTimedSchedulerManage_dataset.flushData(BopTimedSchedulerManage_dataset.pageIndex);
	        });
		}
		function btnDel_onClick(btn){
			$.confirm("是否确认删除?", function() {
				BopTimedSchedulerManage_dataset.setParameter("id", BopTimedSchedulerManage_dataset.record.getValue("id"));
				btnDelete.click();
	        }, function() {
	        	return false;
	        });
		}
		function btnDelete_needCheck(btn){
			return false;
		}
		function btnDelete_postSubmit(){
			$.success("操作成功!", function() {
				BopTimedSchedulerManage_dataset.flushData(BopTimedSchedulerManage_dataset.pageIndex);
	        });
		}
	</script>
</snow:page>

