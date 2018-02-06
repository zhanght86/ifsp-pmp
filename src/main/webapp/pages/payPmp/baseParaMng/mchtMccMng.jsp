<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户Mcc管理">
	<!-- 数据集加载 -->
	<!-- 主界面数据集  -->
	<snow:dataset id="mchtMcc"
		path="com.ruimin.ifs.pmp.baseParaMng.dataset.mchtMccMng"
		init="true" submitMode="current"></snow:dataset>

	<!-- 页面元素 -->
	<!-- 查询栏 -->
	<snow:query dataset="mchtMcc" label="查询条件" collapsible="false" border="false" 
	fieldstr="qmccId,qmccDesc,qmchtGrpId"></snow:query>
	
	<!-- 主表单 -->
	<snow:grid id="gridMain" dataset="mchtMcc" label="商户MCC信息"
		fieldstr="mccId[200],grpDesc,mccDesc" height="99%"
		paginationbar="btnAdd,btnUpd,btnDel"></snow:grid>

	<!-- 弹出窗口 -->
	<!-- 新增窗口 -->
	<snow:window id="winAdd" title="商户MCC信息--新增" modal="true" closable="true"
		buttons="btnAddSub">
		<snow:form label="基本信息" dataset="mchtMcc"
			fieldstr="mchtGrpIdGrid,mccId,mccDesc"></snow:form>
		<snow:button id="btnAddSub" dataset="mchtMcc" hidden="true"></snow:button>
	</snow:window>
	<!-- 修改窗口 -->
	<snow:window id="winUpd" title="商户MCC信息--修改" modal="true" closable="true"
		buttons="btnUpdSub">
		<snow:form label="基本信息" dataset="mchtMcc"
			fieldstr="mccId,mccDesc,mchtGrpIdGrid"></snow:form>
		<snow:button id="btnUpdSub" dataset="mchtMcc" hidden="true"></snow:button>
	</snow:window>
	<!-- 删除 -->
	<div style="display: none;">
		<snow:button id="btnDelSub" dataset="mchtMcc"></snow:button>
	</div>

	<script type="text/javascript">
		var isDesc4 = /^\d{1,4}$/;//最大长度4位 
		var isDesc42 = /^\S{1,42}$/;//最大长度42位 

		/****************************************新增******************************************/
		/**打开新增窗口*/
		function btnAdd_onClick() {
			mchtMcc_dataset.setFieldReadOnly("mccId", false);
			window_winAdd.open();
		}
		/**提交前检验数据格式*/
		function btnAddSub_onClickCheck() {
			//获取需校验字段
			var mccId = mchtMcc_dataset.getValue("mccId"); //商户组别编号
			var mccDesc = mchtMcc_dataset.getValue("mccDesc"); //商户组别描述

			//校验过程
			if (!isDesc4.test(mccId)) {
				$.warn("提示：MCC编号由最大长度为4位的数字组成");
				return false;
			}
			if (!isDesc42.test(mccDesc)) {
				$.warn("提示：商户Mcc描述长度不能超过42位字符");
				return false;
			}
			return true;
		}
		/**提交*/
		function btnAddSub_postSubmit() {
			$.success("添加成功！", function() {
				window_winAdd.close();

			});
		}
		/**窗口关闭后清除数据*/
		function window_winAdd_afterClose() {
			mchtMcc_dataset.clearData();
			mchtMcc_dataset.flushData(mchtMcc_dataset.pageIndex);
		}
		/****************************************修改******************************************/
		/**打开修改窗口*/
		function btnUpd_onClick() {
			mchtMcc_dataset.setFieldReadOnly("mccId", true);
			window_winUpd.open();
		}
		/**提交前检验数据格式*/
		function btnUpdSub_onClickCheck() {
			var mccDesc = mchtMcc_dataset.getValue("mccDesc"); //商户组别描述

			//校验过程
			if (!isDesc42.test(mccDesc)) {
				$.warn("提示：商户Mcc描述长度不能超过42位字符");
				return false;
			}
			return true;
		}
		/**提交*/
		function btnUpdSub_postSubmit() {
			$.success("修改成功！", function() {
				window_winUpd.close();

			});
		}
		/**窗口关闭后清除数据*/
		function window_winUpd_afterClose() {
			mchtMcc_dataset.clearData();
			mchtMcc_dataset.flushData(mchtMcc_dataset.pageIndex);
		}

		/****************************************删除******************************************/
		function btnDel_onClick() {
			mchtMcc_dataset.setFieldRequired("mchtGrpIdGrid",false)
			var mchtGrpId = mchtMcc_dataset.getValue("mchtGrpId"); //商户组别编号
			$.confirm("是否确认删除该商户MCC?  注:一经删除无法恢复!", function() {
				mchtMcc_dataset.setParameter("mchtGrpId", mchtGrpId);
				btnDelSub.click();
			}, function() {
				return false;
			});
		}
		function btnDelSub_postSubmit(btn) {
			$.success("操作成功!", function() {
				mchtMcc_dataset.flushData(mchtMcc_dataset.pageIndex);
			});
		}
	</script>
</snow:page>