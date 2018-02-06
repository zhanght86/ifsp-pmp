<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="数据字典配置">
	<snow:dataset id="DataDicEntry" path="com.ruimin.ifs.mng.dataset.DataDicEntry" submitMode="current" init="true"></snow:dataset>
	<snow:query label="查询条件" id="queryId" dataset="DataDicEntry" collapsible="false"  fieldstr="qDataTypeNo,qDataTypeName,qDataNo,qDataName"></snow:query>
	<snow:grid dataset="DataDicEntry" id="gridId" label="数据字典" height="99%" fitcolumns="true" fieldstr="dataTypeNo[160],dataTypeName,dataNo[160],dataName,opr[160]" paginationbar="btnAdd"></snow:grid>
	<!-- 可使用一个窗体 -->
	<snow:window id="windowModifyId" title="数据字典 --> 编辑" modal="true" closable="true" buttons="btnSave">
		<snow:form id="formModifyId" dataset="DataDicEntry" border="false" label="数据字典 --> 编辑 --> 参数设置" fieldstr="dataTypeNo,dataTypeName,dataNo,dataName,dataNoLen" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave" dataset="DataDicEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="windowAddId" title="数据字典新增" modal="true" closable="true" buttons="btnSave1">
		<snow:form id="formAddId" dataset="DataDicEntry" border="false" label="数据字典 --> 新增 --> 数据新增" fieldstr="dataTypeNo,dataTypeName,dataNo,dataName,dataNoLen" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave1" dataset="DataDicEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:button id="btnDelete" dataset="DataDicEntry" hidden="true"></snow:button>
	<script>
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("id");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + id + "')\">删除</a>";
			}
		}
		function onClickModify() {
			DataDicEntry_dataset.setFieldReadOnly("dataTypeNo", true);
			window_windowModifyId.open();
		}
		function btnAdd_onClick() {
			DataDicEntry_dataset.setFieldReadOnly("dataTypeNo", false);
			window_windowAddId.open();
		}
		function btnDelete_needCheck(btn) {
			return false;
		}
		function onClickDelete(id) {
			$.confirm("是否确认删除?", function() {
				// 				locate(id);
				btnDelete.click();
			}, function() {
				return false;
			});
		}
		function window_windowModifyId_beforeClose(wmf) {
			DataDicEntry_dataset.cancelRecord();
		}
		function window_windowAddId_beforeClose(wmf) {
			DataDicEntry_dataset.cancelRecord();
		}
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				DataDicEntry_dataset.flushData(DataDicEntry_dataset.pageIndex);
			});
		}
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowModifyId.close();
				DataDicEntry_dataset.flushData(DataDicEntry_dataset.pageIndex);
			});
		}
		function btnSave1_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				DataDicEntry_dataset.flushData(DataDicEntry_dataset.pageIndex);
			});
		}
		//定位一条记录
		// 		function locate(id) {
		// 			var record = DataDicEntry_dataset.find(["id"],[id]);
		// 			if (record) {
		// 				DataDicEntry_dataset.setRecord(record); 
		// 			}
		// 		}
	</script>
</snow:page>