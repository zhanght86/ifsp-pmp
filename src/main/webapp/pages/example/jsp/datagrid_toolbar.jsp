<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="普通表格">
	<snow:dataset id="GridFields" path="com.ruimin.ifs.example.dataset.GridFields" init="false"></snow:dataset>
	<snow:grid dataset="GridFields" id="grid1" label="表格-顶部工具栏" toolbar="toolbar" height="200" fieldstr="f1,f2,f3,n1,n2,n3">
	</snow:grid>
	<snow:toolbar id="toolbar">
		<snow:query id="q" dataset="GridFields" fieldstr="f2"></snow:query>
	</snow:toolbar>
	<hr>
	<snow:grid dataset="GridFields" id="grid2" label="表格-底部工具栏" height="200" paginationbar="btSave,-,btAdd" fieldstr="f1,f2,f3,n1,n2,n3">
	</snow:grid>
	<script>
		function btn1_onClick() {
			$.alert("add")
		} 
	</script>

</snow:page>
