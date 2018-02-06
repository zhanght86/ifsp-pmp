<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="汇率信息维护">
	<snow:dataset id="CurrrateMng" path="com.ruimin.ifs.mng.dataset.CurrrateMng" init="true" submitMode="current"></snow:dataset>
	<snow:query id="queryId" dataset="CurrrateMng" fieldstr="curcd,toCurcd,currrateDate"></snow:query>
	<snow:grid dataset="CurrrateMng" id="gridId" fieldstr="currrateDate,curcdName,toCurcdName,buyRate,exchgRate,sellRate,opr" paginationbar="btnAdd"></snow:grid>
	<snow:window id="windowId" title="汇率信息维护" modal="true" closable="true" buttons="btnSave">
		<snow:form id="formId" dataset="CurrrateMng" label="*" border="false" fieldstr="currrateDate,curcd,toCurcd,buyRate,exchgRate,sellRate" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave" dataset="CurrrateMng" hidden="true"></snow:button>
	</snow:window>
	<snow:button id="btnDelete" dataset="CurrrateMng" hidden="true"></snow:button>
	<script>
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("id");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + id + "')\">删除</a>";
			}
		}
		function btnAdd_onClick() {
			window_windowId.open();
		}
		function window_windowId_beforeClose(wmf) {
			CurrrateMng_dataset.cancelRecord();
		}
		function onClickModify() {
			window_windowId.open();
		}
		function btnDelete_needCheck(btn) {
			return false;
		}
		function onClickDelete(id) {
			$.confirm("是否确认删除?", function() {
				CurrrateMng_dataset.setParameter("id", id);
				btnDelete.click();
			}, function() {
				return false;
			});
		}
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowId.close();
				CurrrateMng_dataset.flushData(CurrrateMng_dataset.pageIndex);
			});
		}
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				CurrrateMng_dataset.flushData(CurrrateMng_dataset.pageIndex);
			});
		}
	</script>
</snow:page>


