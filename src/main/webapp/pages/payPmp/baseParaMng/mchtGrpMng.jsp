<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户组别管理">
	<!-- 数据集加载 -->
	<!-- 主界面数据集  -->
	<snow:dataset id="mchtGrp"
		path="com.ruimin.ifs.pmp.baseParaMng.dataset.mchtGrpMng"
		init="true" submitMode="current"></snow:dataset>

	<!-- 页面元素 -->
	<!-- 查询栏 -->
	<snow:query dataset="mchtGrp" label="查询条件" collapsible="false" border="false" 
	fieldstr="qmchtGrpNo,qgrpDesc"></snow:query>
	<!-- 主表单 -->
	<snow:grid id="gridMain" dataset="mchtGrp" label="商户组别信息"
		fieldstr="mchtGrpNo[400],grpDesc" height="99%"
		paginationbar="btnAdd,btnUpd,btnDel"></snow:grid>

	<!-- 弹出窗口 -->
	<!-- 新增窗口 -->
	<snow:window id="winAdd" title="商户组别信息--新增" modal="true" closable="true"
		buttons="btnAddSub">
		<snow:form label="基本信息" dataset="mchtGrp" fieldstr="mchtGrpNo,grpDesc"></snow:form>
		<snow:button id="btnAddSub" dataset="mchtGrp" hidden="true"></snow:button>
	</snow:window>
	<!-- 修改窗口 -->
	<snow:window id="winUpd" title="商户组别信息--修改" modal="true" closable="true"
		buttons="btnUpdSub">
		<snow:form label="基本信息" dataset="mchtGrp" fieldstr="mchtGrpNo,grpDesc"></snow:form>
		<snow:button id="btnUpdSub" dataset="mchtGrp" hidden="true"></snow:button>
	</snow:window>
	<!-- 删除 -->
	<div style="display: none;">
		<snow:button id="btnDelSub" dataset="mchtGrp"></snow:button>
	</div>

	<script type="text/javascript">
		/**验证字符串长度*/
		var isDesc2 = /^\d{1,2}$/;//最大长度2位 
		var isDesc42 = /^\S{1,42}$/;//最大长度42位 
		
		function checkField(){
			//获取需校验字段
			var mchtGrpNo = mchtGrp_dataset.getValue("mchtGrpNo"); //商户组别编号
			var grpDesc = mchtGrp_dataset.getValue("grpDesc"); //商户组别描述

			//校验过程
			if (!isDesc2.test(mchtGrpNo)) {
				$.warn("商户编号长度错误（最大长度2位）");
				return false;
			}
			if (!isDesc42.test(grpDesc)) {
				//alert(grpDesc);
				$.warn("商户全名长度错误（最大长度42位）");
				return false;
			}
			return true;
		}

		/****************************************新增******************************************/
		/**打开新增窗口*/
		function btnAdd_onClick() {
			mchtGrp_dataset.setFieldReadOnly("mchtGrpNo", false);
			window_winAdd.open();
		}

		/**提交前检验数据格式*/
		function btnAddSub_onClickCheck() {
			if(!checkField()){
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
			mchtGrp_dataset.clearData();
			mchtGrp_dataset.flushData(mchtGrp_dataset.pageIndex);
		}

		/****************************************修改******************************************/
		/**打开修改窗口*/
		function btnUpd_onClick() {
			mchtGrp_dataset.setFieldReadOnly("mchtGrpNo", true);
			window_winUpd.open();
		}
		/**提交前检验数据格式*/
		function btnUpdSub_onClickCheck() {
			if(!checkField()){
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
			mchtGrp_dataset.clearData();
			mchtGrp_dataset.flushData(mchtGrp_dataset.pageIndex);
		}

		/****************************************删除******************************************/
		function btnDel_onClick() {
			var mchtGrpNo = mchtGrp_dataset.getValue("mchtGrpNo"); //商户组别编号
			$.confirm("是否确认删除该商户组别?  注:一经删除无法恢复!", function() {
				mchtGrp_dataset.setParameter("mchtGrpNo", mchtGrpNo);
				btnDelSub.click();
			}, function() {
				return false;
			});
		}
		function btnDelSub_postSubmit(btn) {
			$.success("操作成功!", function() {
				mchtGrp_dataset.flushData(mchtGrp_dataset.pageIndex);
			});
		}
		
	</script>
</snow:page>