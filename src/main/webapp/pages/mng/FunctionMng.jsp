<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="权限管理">
	<snow:dataset id="FunctionMng" path="com.ruimin.ifs.mng.dataset.FunctionMng" init="true" submitMode="current"></snow:dataset>
	<snow:layout id="layout">
		<snow:Layoutleft id="left" width="200">
			<snow:tree dataset="FunctionMng" id="funcs" contextmenu="menu"></snow:tree>
			<snow:menu id="menu">
				<snow:menuitem desc="添加" id="add" icon="fa fa-plus"></snow:menuitem>
				<snow:menuitem desc="添加子菜单" id="addSub1" icon="fa fa-indent"></snow:menuitem>
				<snow:menuitem desc="添加子功能" id="addSub2" icon="fa fa-indent"></snow:menuitem>
				<snow:menuitem desc="删除" id="del" icon="fa fa-trash"></snow:menuitem>
			</snow:menu>
		</snow:Layoutleft> 
		<snow:Layoutcenter id="main">
			<snow:form id="form" dataset="FunctionMng" label="详细" collapsible="false" fieldstr="_text,_icon,funcType,showseq,pagepath,funcDesc"></snow:form>
			<div style="text-align: center">
				<snow:button id="btnSave" dataset="FunctionMng"></snow:button>
				<snow:button id="btnCancel" desc="取消" icon=""></snow:button>
			</div>
			<div style="display: none">
				<snow:button id="btnDel" dataset="FunctionMng"></snow:button>
			</div>
		</snow:Layoutcenter>
	</snow:layout>
	<script>
		function initCallGetter_post() {

		}
		function btnSave_postSubmit(btn, param) {
			FunctionMng_dataset.setValue("funcid", param.funcid);
			FunctionMng_dataset.setFieldReadOnly("funcType", true);
			$.success("保存成功");
		}
		function btnCancel_onClick() {
			FunctionMng_dataset.cancelRecord();
		}
		function btnDel_postSubmit(btn, param) {
			$.success("删除成功");
			FunctionMng_dataset.deleteRecord();
		}
		function FunctionMng_dataset_afterScroll(dataset, record) {
			if (record.getValue("funcid")) {
				dataset.setFieldReadOnly("funcType", true);
			} else {
				dataset.setFieldReadOnly("funcType", false);
			}
		}
		function menu_menu_onClick(element, item) {
			menu_menu.srcRecord;
			switch (item.id) {
			case "add":
				var r = FunctionMng_dataset.insertRecord();
				r && FunctionMng_dataset.setFieldReadOnly("funcType", false);
				break;
			case "addSub1":
				var pid = FunctionMng_dataset.getValue("funcid");
				var success = FunctionMng_dataset.insertRecord("append");
				if (success) {
					FunctionMng_dataset.setValue("lastdirectory", pid);
					FunctionMng_dataset.setValue("funcType", 0);
					FunctionMng_dataset.setFieldReadOnly("funcType", true);
				}
				break;
			case "addSub2":
				var pid = FunctionMng_dataset.getValue("funcid");
				var success = FunctionMng_dataset.insertRecord("append");
				if (success) {
					FunctionMng_dataset.setValue("lastdirectory", pid);
					FunctionMng_dataset.setValue("funcType", 1);
					FunctionMng_dataset.setFieldReadOnly("funcType", true);
				}
				break
			case "del":
				if (FunctionMng_dataset.getValue("funcid")) {
					$.confirm("确定删除？", function() {
						btnDel.click();
					});
				} else {
					FunctionMng_dataset.deleteRecord();
				}
				break;
			}

		}
		function tree_funcs_onContextmenu(menu, record) {
			var functype = record.getValue("funcType");
			var saved = !!record.getValue("funcid");
			if (saved) {
				if (functype == "2") {//目录
					menu.showItem("addSub1");
					menu.hideItem("addSub2");
				} else if (functype == "0") {//菜单 
					menu.hideItem("addSub1");
					menu.showItem("addSub2");
				} else {//功能
					menu.hideItem("addSub1");
					menu.hideItem("addSub2");
				}
			} else {
				menu.hideItem("addSub1");
				menu.hideItem("addSub2");
			}

		}
	</script>
</snow:page>
