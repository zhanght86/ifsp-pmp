<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="审核任务管理">

	<snow:dataset id="AuditInfo" path="com.ruimin.ifs.mng.dataset.AuditInfo" submitMode="current" init="true"></snow:dataset>
	<snow:dataset id="AuditPro" path="com.ruimin.ifs.mng.dataset.AuditPro" submitMode="all" init="false"></snow:dataset>
	
	<snow:grid dataset="AuditInfo" label="任务维护" id="gridId" height="99%" fieldstr="auditId[150],auditType,auditDesc,auditStat[150]" paginationbar="btnUpd,btnStat"></snow:grid>
	
	<!-- 修改 -->
	<snow:window id="windowUpdateId" title="审核任务修改"  modal="true" closable="true" buttons="btnUpdate">
		<snow:form id="baseInfo1" dataset="AuditInfo" border="true" label="" collapsible="false" colnum="4" 
  				fieldstr="auditId,auditType,auditDesc,auditStat" >
		</snow:form>
		<snow:grid id="gridIdForPro" dataset="AuditPro" height="300" border="true" label="审核流程设置" editable="true" pagination="false" toolbar=""
			fieldstr="auditName[200],auditRole[200]" >
		</snow:grid> 
		<snow:button id="btnUpdate" dataset="AuditInfo" hidden="true"></snow:button>
	</snow:window>
	<snow:button id="btnUseChg" dataset="AuditInfo" hidden="true"></snow:button>
	
	<script>

		function btnUpd_onClick() {
			AuditPro_dataset.setParameter("auditId", AuditInfo_dataset.getValue("auditId"));
			AuditPro_dataset.flushData(AuditPro_dataset.pageIndex);
			AuditPro_dataset.setFieldReadOnly("auditRole", false);
			AuditPro_dataset.setFieldRequired("auditRole", true);
			window_windowUpdateId.open();
		}
		function btnUpdate_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUpdateId.close();
				AuditInfo_dataset.flushData(AuditInfo_dataset.pageIndex);
			});
		}
		function window_windowUpdateId_beforeClose(wmf){
			AuditInfo_dataset.cancelRecord();
		}
		
		
		function btnStat_onClick(){
			AuditPro_dataset.setFieldRequired("auditRole", false);
			var foo = AuditInfo_dataset.getValue("auditStat");
			var msg = ""
			if(foo == "1"){
				msg = "是否要将该用户状态修改为【停用】?";
				foo = "0";
			}else{
				msg = "是否要将该用户状态修改为【启用】?";
				foo = "1";
			}
			$.confirm(msg, function() {
				AuditInfo_dataset.setParameter("foo",foo);
				btnUseChg.click();
	        }, function() {
	        	return false;
	        });
		}
		function btnUseChg_needCheck(btn) {
			return false;
		}
		function btnUseChg_postSubmit() {
			AuditInfo_dataset.flushData(AuditInfo_dataset.pageIndex);
		}
		
	
	</script>
</snow:page>