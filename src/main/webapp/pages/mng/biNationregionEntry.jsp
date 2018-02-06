<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="国家/地区代码维护">
	<snow:dataset id="BiNationregionEntry" path="com.ruimin.ifs.mng.dataset.BiNationregionEntry" init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="BiNationregionEntry" fieldstr="qid,qnationregionNumber,cnEnFullName,cnEnShortName"></snow:query>
	<snow:grid dataset="BiNationregionEntry" id="gridId" fieldstr="nationregionCode,chinaName,nationregionNumber,chinaShortName,engShortName,engName,opr" paginationbar="btnAdd"></snow:grid>
	<snow:window id="windowModifyId" title="国家地区代码编辑" modal="true" closable="true" buttons="btnSave">
		<snow:form id="formModifyId" dataset="BiNationregionEntry" border="false"  label="国家地区代码 --> 编辑 --> 参数设置" fieldstr="nationregionCode,chinaName,nationregionNumber,chinaShortName,engShortName,engName" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave" dataset="BiNationregionEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="windowAddId" title="国家地区代码新增" modal="true" closable="true" buttons="btnSave1">
		<snow:form id="formAddId" dataset="BiNationregionEntry"  border="false" label="国家地区代码 --> 新增 --> 数据新增" fieldstr="nationregionCode,chinaName,nationregionNumber,chinaShortName,engShortName,engName" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave1" dataset="BiNationregionEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="windowDetailId" title="国家地区代码详情" modal="true" closable="true">
		<snow:form id="formDetailId" dataset="BiNationregionEntry" border="false" label="国家地区代码 --> 详情 --> 参数详情" fieldstr="nationregionCode,chinaName,nationregionNumber,chinaShortName,engShortName,engName" collapsible="false" colnum="4"></snow:form>
	</snow:window>
	<snow:button id="btnDelete" dataset="BiNationregionEntry" hidden="true"></snow:button>
	<script>
		function gridId_nationregionCode_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("nationregionCode");
				return "<a href=\"JavaScript:onClickDetail()\">" + id + "</a>";
			}
		}
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("nationregionCode");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + id + "')\">删除</a>";
			}
		}
		function onClickModify() {
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionCode", true);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionNumber", false);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaShortName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("engShortName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("engName", false);
			window_windowModifyId.open();
		}
		function btnAdd_onClick() {
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionCode", false);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionNumber", false);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaShortName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("engShortName", false);
			BiNationregionEntry_dataset.setFieldReadOnly("engName", false);
			window_windowAddId.open();
		}
		function btnDelete_needCheck(btn) {
			return false;
		}
		function onClickDelete(id) {
			$.confirm("是否确认删除?", function() {
				btnDelete.click();
			}, function() {
				return false;
			});
		}
		function onClickDetail(id) {
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionCode", true);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaName", true);
			BiNationregionEntry_dataset.setFieldReadOnly("nationregionNumber", true);
			BiNationregionEntry_dataset.setFieldReadOnly("chinaShortName", true);
			BiNationregionEntry_dataset.setFieldReadOnly("engShortName", true);
			BiNationregionEntry_dataset.setFieldReadOnly("engName", true);
			window_windowDetailId.open();
		}
		function window_windowModifyId_beforeClose(wmf) {
			BiNationregionEntry_dataset.cancelRecord();
		}
		function window_windowAddId_beforeClose(wmf) {
			BiNationregionEntry_dataset.cancelRecord();
		}
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				BiNationregionEntry_dataset.flushData(BiNationregionEntry_dataset.pageIndex);
			});
		}
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowModifyId.close();
				BiNationregionEntry_dataset.flushData(BiNationregionEntry_dataset.pageIndex);
			});
		}
		function btnSave1_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				BiNationregionEntry_dataset.flushData(BiNationregionEntry_dataset.pageIndex);
			});
		}
	</script>
</snow:page>
