<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="服务机构关联内部机构">
	<%
		String reqActiveNo = request.getParameter("activeNo");
	%>
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
    <!-- 服务机构信息数据集 -->
	<snow:dataset id="serviceBasicMessage"
		path="com.ruimin.ifs.pmp.service.dataset.serviceBasicMessage" init="true"
		submitMode="current">
	</snow:dataset>
		
	<!-- 内部机构服务机构关联信息数据集 -->
	<snow:dataset id="serviceInnerBrcode"
		path="com.ruimin.ifs.pmp.service.dataset.serviceInnerBrcode" init="true"
		submitMode="all">
	</snow:dataset>
	
	
	<!-- 服务机构信息显示 -->
	<snow:form label="服务机构信息" id="formServiceInfo" dataset="serviceBasicMessage" 
		fieldstr="serviceCode,serviceName" collapsible="false" colnum="4" border="false">
	</snow:form>
	<br/>
	<!-- 查询 -->
	<snow:query label="请选择查询条件" id="queryConnect" dataset="serviceInnerBrcode" 
		fieldstr="qbrcode,qbrname,qconnectState" collapsible="false" colnum="4" 
		border="false">
	</snow:query>
	<!-- 页面显示 -->
	<snow:grid id="gridConnect"   dataset="serviceInnerBrcode" 
		fieldstr="select,brno,brcode,brname,inFlag" 
			height="99%" border="true" label="关联内部机构" editable="false"
			 fitcolumns="true" pagination="true" collapsible="true">
	</snow:grid>
	<div style="display: none;">
		<snow:button id="btnConnecSave" dataset="serviceBasicMessage"></snow:button>
	</div>
	<script type ="text/javascript">
		function initCallGetter_post(){
			serviceBasicMessage_dataset.setFieldReadOnly("serviceName", true);
			serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
			serviceInnerBrcode_dataset.flushData(serviceInnerBrcode_dataset.pageIndex);
		}
		function submitCheck(){
			var firstRecord = serviceInnerBrcode_dataset.getFirstRecord();
			if(firstRecord == null){
				$.warn("未关联内部机构号，无法提交保存");
				return;
			}
			var str = "是否确认修改所有选中联状态和取消已有的关联状态";
			$.confirm(str, function() { 
				btnConnecSave.click(); 
			},function(){
				return false;
			}); 
			
		}

		function btnConnecSave_postSubmit() {
	 		$.success("操作成功!", function() {
	 			serviceInnerBrcode_dataset.flushData(serviceInnerBrcode_dataset.pageIndex);
	 		});
		 }

	</script>
</snow:page>