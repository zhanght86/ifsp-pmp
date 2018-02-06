<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="币种信息维护">
	<snow:dataset id="CurrencyManEntry" path="com.ruimin.ifs.mng.dataset.CurrencyManEntry" init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="CurrencyManEntry" fieldstr="qcurcd,qcnname"></snow:query>
	<snow:grid dataset="CurrencyManEntry" id="gridId" fieldstr="curcd,curno,cnname,enname,cursymbol,dratedays,opr" paginationbar="btnAdd"></snow:grid>
	<snow:window id="windowModifyId" title="币种信息维护编辑" modal="true" closable="true" buttons="btnSave">
		<snow:form id="formModifyId" dataset="CurrencyManEntry" border="false" label="币种信息维护 --> 编辑 --> 参数设置" fieldstr="curcd,curno,cnname,enname,cursymbol,dratedays,baseUnit,minUnit,curEdicd,curEdiname,showseq" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave" dataset="CurrencyManEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="windowAddId" title="币种信息维护新增" modal="true" closable="true" buttons="btnSave1">
		<snow:form id="formAddId" dataset="CurrencyManEntry" border="false" label="币种信息维护 --> 新增 --> 数据新增" fieldstr="curcd,curno,cnname,enname,cursymbol,dratedays,baseUnit,minUnit,curEdicd,curEdiname,showseq" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnSave1" dataset="CurrencyManEntry" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="windowDetailId" title="币种信息维护详情" modal="true" closable="true">
		<snow:form id="formDetailId" dataset="CurrencyManEntry" border="false" label="币种信息维护--> 详情 --> 参数详情" fieldstr="curcd,curno,cnname,enname,cursymbol,dratedays,baseUnit,minUnit,curEdicd,curEdiname,showseq" collapsible="false" colnum="4"></snow:form>
	</snow:window>
	<snow:button id="btnDelete" dataset="CurrencyManEntry" hidden="true"></snow:button>
	<script>
		function gridId_curcd_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("curcd");
				return "<a href=\"JavaScript:onClickDetail()\">" + id + "</a>";
			}
		}
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("curcd");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + id + "')\">删除</a>";
			}
		}
		function onClickModify() {
			CurrencyManEntry_dataset.setFieldReadOnly("curcd", true);
			CurrencyManEntry_dataset.setFieldReadOnly("curno", false);
			CurrencyManEntry_dataset.setFieldReadOnly("cnname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("enname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("cursymbol", false);
			CurrencyManEntry_dataset.setFieldReadOnly("dratedays", false);
			CurrencyManEntry_dataset.setFieldReadOnly("baseUnit", false);
			CurrencyManEntry_dataset.setFieldReadOnly("minUnit", false);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdicd", false);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdiname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("showseq", false);
			window_windowModifyId.open();
		}
		function btnAdd_onClick() {
			CurrencyManEntry_dataset.setFieldReadOnly("curcd", false);
			CurrencyManEntry_dataset.setFieldReadOnly("curno", false);
			CurrencyManEntry_dataset.setFieldReadOnly("cnname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("enname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("cursymbol", false);
			CurrencyManEntry_dataset.setFieldReadOnly("dratedays", false);
			CurrencyManEntry_dataset.setFieldReadOnly("baseUnit", false);
			CurrencyManEntry_dataset.setFieldReadOnly("minUnit", false);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdicd", false);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdiname", false);
			CurrencyManEntry_dataset.setFieldReadOnly("showseq", false);
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
			CurrencyManEntry_dataset.setFieldReadOnly("curcd", true);
			CurrencyManEntry_dataset.setFieldReadOnly("curno", true);
			CurrencyManEntry_dataset.setFieldReadOnly("cnname", true);
			CurrencyManEntry_dataset.setFieldReadOnly("enname", true);
			CurrencyManEntry_dataset.setFieldReadOnly("cursymbol", true);
			CurrencyManEntry_dataset.setFieldReadOnly("dratedays", true);
			CurrencyManEntry_dataset.setFieldReadOnly("baseUnit", true);
			CurrencyManEntry_dataset.setFieldReadOnly("minUnit", true);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdicd", true);
			CurrencyManEntry_dataset.setFieldReadOnly("curEdiname", true);
			CurrencyManEntry_dataset.setFieldReadOnly("showseq", true);
			window_windowDetailId.open();
		}
		function window_windowModifyId_beforeClose(wmf) {
			CurrencyManEntry_dataset.cancelRecord();
		}
		function window_windowAddId_beforeClose(wmf) {
			CurrencyManEntry_dataset.cancelRecord();
		}
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				CurrencyManEntry_dataset.flushData(CurrencyManEntry_dataset.pageIndex);
			});
		}
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowModifyId.close();
				CurrencyManEntry_dataset.flushData(CurrencyManEntry_dataset.pageIndex);
			});
		}
		function btnSave1_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				CurrencyManEntry_dataset.flushData(CurrencyManEntry_dataset.pageIndex);
			});
		}
	</script>
</snow:page>
