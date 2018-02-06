<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="demo_field">

	<snow:dataset id="DemoFields" path="com.ruimin.ifs.example.dataset.DemoFields" init="true"></snow:dataset>
	<snow:query id="queryId" dataset="DemoFields" fieldstr="id,name" label="范例1查询条件"></snow:query>
	
	<snow:grid exporter="exporterId" dataset="DemoFields" id="gridId" fieldstr="id,pid,managerId,name,sex,age,birthday,salary,workStartTime,workEndTime" paginationbar="btn2,-,btn3,-,btn1" label="范例1表格" ></snow:grid>
	<snow:exporter dataset="DemoFields" id="exporterId" type="XLS|CSV" ></snow:exporter>
	
	<!-- 双击后弹出的窗体 -->
	<snow:window id="windowId" title="编辑" modal="true">
		<snow:form id="formId" dataset="DemoFields" label="编辑  --> 员工明细" fieldstr="id,pid,managerId,name,sex,age,birthday,salary,workStartTime,workEndTime"></snow:form>
		<snow:button id="btn1" desc="保存" icon="fa fa-save" dataset="DemoFields" ></snow:button>	
	</snow:window>
	
	<script>
		// 双击表格行事件
		function gridId_onDblClick() {
            window_windowId.open();
        }
		// 单击新增弹出新窗体
		function btn2_onClick(){
			window_windowId.open();
		}
		// 删除一行
	 	function btn3_onClick(){ 
	 		DemoFields_dataset.deleteRecord();
        }

	</script>
</snow:page>
