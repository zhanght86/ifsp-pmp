<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户用户组信息管理">
	<snow:dataset path="com.ruimin.ifs.pmp.mchtAppMng.dataset.userGroupDtlMng" id="userGroupDtlMng" submitMode="current" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="userGroupDtlMng" dataset="userGroupDtlMng" fieldstr="qgroupId,qmchtId,qdeviceType,quserId,quserName" border="false"></snow:query>
	<snow:grid dataset="userGroupDtlMng" label="查询列表" id="gridMain"
		fieldstr="groupId,mchtId,deviceType,userId,userName,channelId" fitcolumns="true"
		height="99%" paginationbar="btnAdd"></snow:grid>
			
	<!-- 新增 -->
	<snow:window id="winAdd" title="商户用户组信息新增" modal="true" closable="true"
		buttons="btnSave" width="850" height="400">
		<snow:form id="groupAdd" dataset="userGroupDtlMng"
			border="false" label="保存" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="groupId,userId">
		</snow:form>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="userGroupDtlMng" hidden="true"></snow:button>
	</snow:window>
		
<style type="text/css">

</style>

	<script type="text/javascript">
	
	/* =======================================新增=============================================== */
	//新增单击按钮
	function btnAdd_onClick() {
		userGroupDtlMng_dataset.setReadOnly(false);
		userGroupDtlMng_dataset.setFieldRequired("groupId", true);
		userGroupDtlMng_dataset.setFieldRequired("userId", true);
		window_winAdd.open();
	}
	
	//确认保存之前验证操作
	function btnSave_onClickCheck() {
		var groupId = userGroupDtlMng_dataset.getValue("groupId");
		groupId = $.trim(groupId);
		var userId = userGroupDtlMng_dataset.getValue("userId");
		userId = $.trim(userId);
		userGroupDtlMng_dataset.setValue("groupId",groupId);
		userGroupDtlMng_dataset.setValue("userId",userId);
		return true;			
	}
	//保存成功
	function btnSave_postSubmit() {
		$.success("操作成功!", function() {
			window_winAdd.close();
			userGroupDtlMng_dataset.flushData(userGroupDtlMng_dataset.pageIndex);
		});
	}
	//新增关闭窗口刷新
	function window_winAdd_afterClose(){
		userGroupDtlMng_dataset.flushData(userGroupDtlMng_dataset.pageIndex);
		window.location.reload(true);
	}
	
	</script>
</snow:page>
