<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户用户组信息管理">
	<snow:dataset path="com.ruimin.ifs.pmp.mchtAppMng.dataset.mchtUserGroupMng" id="mchtUserGroupMng" submitMode="current" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="mchtUserGroupMng" dataset="mchtUserGroupMng" fieldstr="qgroupId,qmchtId,qdeviceType" border="false"></snow:query>
	<snow:grid dataset="mchtUserGroupMng" label="查询列表" id="gridMain"
		fieldstr="groupId,mchtId,deviceType,userCount" fitcolumns="true"
		height="99%" paginationbar="btnAdd"></snow:grid>
			
	<!-- 新增 -->
	<snow:window id="winAdd" title="商户用户组信息新增" modal="true" closable="true"
		buttons="btnSave" width="850" height="400">
		<snow:form id="groupAdd" dataset="mchtUserGroupMng"
			border="false" label="保存" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="mchtId,deviceType">
		</snow:form>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="mchtUserGroupMng" hidden="true"></snow:button>
	</snow:window>
		
<style type="text/css">

</style>

	<script type="text/javascript">
	
	/* =======================================新增=============================================== */
	//新增单击按钮
	function btnAdd_onClick() {
		mchtUserGroupMng_dataset.setReadOnly(false);
		mchtUserGroupMng_dataset.setFieldRequired("mchtId", true);
		mchtUserGroupMng_dataset.setFieldRequired("deviceType", true);
		window_winAdd.open();
	}
	
	//确认保存之前验证操作
	function btnSave_onClickCheck() {
		var mchtId = mchtUserGroupMng_dataset.getValue("mchtId");
		mchtId = $.trim(mchtId);
		mchtUserGroupMng_dataset.setValue("mchtId",mchtId);
		return true;			
	}
	//保存成功
	function btnSave_postSubmit() {
		$.success("操作成功!", function() {
			window_winAdd.close();
			mchtUserGroupMng_dataset.flushData(mchtUserGroupMng_dataset.pageIndex);
		});
	}
	//新增关闭窗口刷新
	function window_winAdd_afterClose(){
		mchtUserGroupMng_dataset.flushData(mchtUserGroupMng_dataset.pageIndex);
		window.location.reload(true);
	}
	
	</script>
</snow:page>
