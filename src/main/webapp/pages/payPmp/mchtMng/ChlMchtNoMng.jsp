<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="渠道商户信息维护">
	<snow:dataset id="PbsChlMchtInfo" path="com.ruimin.ifs.pmp.mchtMng.dataset.PbsChlMchtInfo" submitMode="current" init="true"></snow:dataset>
	<snow:query label="查询条件" id="queryId" dataset="PbsChlMchtInfo" collapsible="false"  fieldstr="qChlMchtNo,qChlMchtName"></snow:query>
	<snow:grid dataset="PbsChlMchtInfo" id="gridId" label="渠道商户信息" height="99%" fitcolumns="true" fieldstr="chlMchtNo[160],chlMchtName,chlMchtStat[160],crtDateTime,opr[160]" paginationbar="btnAdd,btnStatus"></snow:grid>
	
	 <!-- 修改 -->
	<snow:window id="windowModifyId" title="渠道商户信息 --> 编辑" modal="true" closable="true" buttons="btnUpdSave">
		<snow:form id="formModifyId" dataset="PbsChlMchtInfo" border="false" label="渠道商户信息 --> 编辑 " fieldstr="chlMchtNo,chlMchtName,chlMchtStat" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnUpdSave" dataset="PbsChlMchtInfo" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 新增 -->
	<snow:window id="windowAddId" title="渠道商户信息新增" modal="true" closable="true" buttons="btnAddSave">
		<snow:form id="formAddId" dataset="PbsChlMchtInfo" border="false" label="渠道商户信息 --> 新增" fieldstr="chlMchtNo,chlMchtName,chlMchtStat" collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnAddSave" dataset="PbsChlMchtInfo" hidden="true"></snow:button>
	</snow:window>
	
	<snow:button id="btnDelete" dataset="PbsChlMchtInfo" hidden="true"/>
	<snow:button id="btnStatusSub" dataset="PbsChlMchtInfo" hidden="true"/>
	<script>
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var id = record.getValue("chlMchtNo");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + id + "')\">删除</a>";
			}
		}
		
		/**=============修改==============*/
		 function onClickModify() {
			PbsChlMchtInfo_dataset.setFieldReadOnly("chlMchtNo", true);
			window_windowModifyId.open();
		}
		 
		 function window_windowModifyId_beforeClose(wmf) {
			 PbsChlMchtInfo_dataset.cancelRecord();
		 } 
		 
		 function btnUpdSave_postSubmit() {
				$.success("操作成功!", function() {
					window_windowModifyId.close();
					PbsChlMchtInfo_dataset.flushData(PbsChlMchtInfo_dataset.pageIndex);
				});
		 }
		 
		/**=============新增==============*/
		function btnAdd_onClick() {
			window_windowAddId.open();
		}
		
		function window_windowAddId_beforeClose(wmf) {
			PbsChlMchtInfo_dataset.cancelRecord();
		}
		
		function btnAddSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				PbsChlMchtInfo_dataset.flushData(PbsChlMchtInfo_dataset.pageIndex);
			});
		}
		
		/**=============删除==============*/
		function onClickDelete(id) {
			$.confirm("是否确认删除?", function() {
				btnDelete.click();
			}, function() {
				return false;
			});
		}
		
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				PbsChlMchtInfo_dataset.flushData(PbsChlMchtInfo_dataset.pageIndex);
			});
		}
		
		/**=============启用/停用==============*/
		function btnStatus_onClick() {
			var dataState = PbsChlMchtInfo_dataset.getValue("chlMchtStat");
			var msg = "";
			if (dataState == "00") {
				msg = "是否要将该渠道商户状态修改为【停用】?";
			} else {
				msg = "是否要将该渠道商户状态修改为【启用】?";
			}
			$.confirm(msg, function() {
				btnStatusSub.click();
			}, function() {
				return false;
			});
		}
		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				PbsChlMchtInfo_dataset.flushData(PbsChlMchtInfo_dataset.pageIndex);
			});
		}
		
	</script>
</snow:page>