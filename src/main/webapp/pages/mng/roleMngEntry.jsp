<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="角色管理">
	<!--  -->
	<snow:dataset id="RoleMngEntry"
		path="com.ruimin.ifs.mng.dataset.RoleMngEntry" init="true"
		submitMode="current"></snow:dataset>
	<snow:dataset id="FunctionMng"
		path="com.ruimin.ifs.mng.dataset.FunctionMng" init="true"></snow:dataset>
	<snow:dataset id="StaffRoleRef"
		path="com.ruimin.ifs.mng.dataset.StaffRoleRef" init="false"></snow:dataset>

	<!-- 1.查询 -->
	<snow:query id="querybtn" label="查询条件" dataset="RoleMngEntry"
		collapsible="false" fieldstr="qRoleId,qRoleName,qBrclass,qStatus"
		border="false">
	</snow:query>

	<!-- 2.主表单 -->
	<snow:grid dataset="RoleMngEntry" label="角色信息" fitcolumns="true"
		id="gridId" height="99%"
		fieldstr="roleId[120],roleName,brclass,status,opr[120]"
		paginationbar="btnAdd,btUpt,btnStatus,btnRoleAuthorityManagement,btnViewOper">
	</snow:grid>

	<!-- 3.新增角色信息&设置角色权限 -->
	<snow:window id="windowCheckId" title="角色信息" modal="true"
		closable="true" width="850" height="550"
		buttons="btnOpen,btnSelectAll,btnUnSelectAll,btnSave">
		<snow:form dataset="RoleMngEntry" fieldstr="roleName,brclass"
			id="rnform" label="角色信息" border="false">
		</snow:form>
		<snow:tree dataset="FunctionMng" id="funcs" checkboxs="true">
		</snow:tree>
		<snow:button id="btnOpen" desc="展开/合并" hidden="true"></snow:button>
		<snow:button id="btnSelectAll" desc="全选" hidden="true"></snow:button>
		<snow:button id="btnUnSelectAll" desc="全不选" hidden="true"></snow:button>
		<snow:button id="btnSave" dataset="RoleMngEntry" hidden="true"></snow:button>
	</snow:window>

	<!-- 4.角色信息修改 -->
	<snow:window id="windowUpdateId" title="角色信息" modal="true"
		closable="true" buttons="btUpdate">
		<snow:form id="formAddId" dataset="RoleMngEntry" border="false"
			label="详细信息" fieldstr="roleId,roleName,brclass,status"
			collapsible="false" colnum="4">
		</snow:form>
		<br />
		<snow:button id="btUpdate" dataset="RoleMngEntry" hidden="true">
		</snow:button>
	</snow:window>

	<!-- 5.角色人员查看 -->
	<snow:window id="windowOwnId" title="角色人员信息" modal="true"
		closable="true" width="700">
		<snow:form dataset="RoleMngEntry" fieldstr="roleName" id="rnform2"
			label="角色信息" border="false">
		</snow:form>
		<snow:grid dataset="StaffRoleRef" id="gridStaffId" fitcolumns="true" label="人员信息列表"
			fieldstr="tlrno[120],tlrName[140],brName[243],flag[100]"
			height="400px" >
		</snow:grid>
	</snow:window>

	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="RoleMngEntry"></snow:button>
	</div>

	<script>
		var isRoleName30 = /^\S{1,30}$/; // 最大长度30位字符
	
		/************************************** 详情 ******************************************/
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
			} else {
				return "&nbsp;";
			}
		}

		var expandAllFlag = false;
		// 展开/合并
		function btnOpen_onClick() {
			if (!expandAllFlag) {
				tree_funcs.expandAll();
			} else {
				tree_funcs.collapseAll();
			}
			expandAllFlag = !expandAllFlag;
		}
		
		// 全选
		function btnSelectAll_onClick() {
			tree_funcs.checkAll();
		}
		
		// 全不选
		function btnUnSelectAll_onClick() {
			tree_funcs.unCheckAll();
		}
		
		/************************************** 1.角色信息新增 ******************************************/
		function btnAdd_onClick() {
			RoleMngEntry_dataset.setFieldReadOnly("roleName", false);
			RoleMngEntry_dataset.setFieldReadOnly("brclass", false);
			tree_funcs.unCheckAll();
			window_windowCheckId.open();
		}

		function window_windowCheckId_beforeClose(wmf) {
			RoleMngEntry_dataset.cancelRecord();
		}

		/* 角色信息新增保存检查 */
		function btnSave_onClickCheck(btn) {
			var funcStr = "";
			var flag = 0;
			var checkedArr = tree_funcs.getChecked();
			for (var i = 0; i < checkedArr.length; i++) {
				if (flag++ > 0) {
					funcStr += ",";
				}
				funcStr += checkedArr[i].getValue("funcid");
			}
			var indeterminateArr = tree_funcs.getIndeterminate();
			for (var i = 0; i < indeterminateArr.length; i++) {
				funcStr += ",";
				funcStr += indeterminateArr[i].getValue("funcid");
			}
			RoleMngEntry_dataset.setParameter("funcStr", funcStr);
			var roleName = RoleMngEntry_dataset.getValue('roleName');
			if (roleName == '') {
				$.warn("角色名称必须输入");
				return false;
			}			
			if(roleName.length>10){
				$.warn("角色名称最大长度为10位！");
				return false;
			}
			
			var brclass = RoleMngEntry_dataset.getValue('brclass');
			if (brclass == '') {
				$.warn("角色级别必须输入");
				return false;
			}
			return true;
		}

		function btnSave_needCheck() {
			return false;
		}
		
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowCheckId.close();
				RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
			});
		}
		
		/************************************** 2.角色信息修改 ******************************************/
		function btUpt_onClick() {
			RoleMngEntry_dataset.setFieldReadOnly("roleId", true);
			RoleMngEntry_dataset.setFieldReadOnly("status", true);
			if (RoleMngEntry_dataset.getValue("status") == '00') {
				RoleMngEntry_dataset.setReadOnly(false);
			} else {
				RoleMngEntry_dataset.setReadOnly(true);
			}
			btUpdate.setHidden(false);
			window_windowUpdateId.open();
		}

		function openDetail() {
			RoleMngEntry_dataset.setReadOnly(true);
			btUpdate.setHidden(true);
			window_windowUpdateId.open();
		}
		function window_windowUpdateId_beforeClose(wmf){
			RoleMngEntry_dataset.cancelRecord();
		}
		function btUpdate_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUpdateId.close();
				RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
			});
		}
		
		/************************************** 3.角色状态停用启用 ******************************************/
		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
			});
		}
		
		function btnStatusSub_needCheck() {
			return false;
		}
		
		function btnStatus_onClick() {
			var foo = RoleMngEntry_dataset.getValue("status");
			var roleId = RoleMngEntry_dataset.getValue("roleId");
			var msg = ""
			if (foo == "00") {
				msg = "是否要将该角色设置为【无效】?";
				foo = "99";
			} else {
				msg = "是否要将该角色设置为【有效】?";
				foo = "00";
			}
			$.confirm(msg, function() {
				RoleMngEntry_dataset.setParameter("foo", foo);
				RoleMngEntry_dataset.setParameter("roleId", roleId);
				btnStatusSub.click();
			}, function() {
				return false;
			});
		}
		

		/************************************** 4.设置角色权限 ******************************************/		
		function btnRoleAuthorityManagement_onClickCheck(btn) {
			var roleId = RoleMngEntry_dataset.getValue("roleId");
			RoleMngEntry_dataset.setReadOnly(true);
			RoleMngEntry_dataset.setParameter("roleId", roleId);
			return true;
		}
		
		function btnRoleAuthorityManagement_needCheck() {
			return false;
		}
		
		function btnRoleAuthorityManagement_postSubmit(btn, param) {
			tree_funcs.unCheckAll();
			tree_funcs.collapseAll();
			var arr = param.funcs.split(","); // 根据后台返回的数据,进行相应的勾选
			tree_funcs.check(arr);
			window_windowCheckId.open();
		}
		
		
		/************************************** 5.人员查看 ******************************************/
		function btnViewOper_onClickCheck(btn) {
			var roleId = RoleMngEntry_dataset.getValue("roleId");
			StaffRoleRef_dataset.setParameter("roleId", roleId);
			StaffRoleRef_dataset.flushData(StaffRoleRef_dataset.pageIndex);
			
			window_windowOwnId.open();
			RoleMngEntry_dataset.setFieldReadOnly("roleName", true);
			return true;
		}
		
		function btnViewOper_needCheck() {
			return false;
		}
	
	</script>
</snow:page>
