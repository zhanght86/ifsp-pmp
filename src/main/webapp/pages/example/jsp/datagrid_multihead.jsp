<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="多表头表格">
	<snow:dataset id="GridFields" path="com.ruimin.ifs.example.dataset.GridFields" init="true"></snow:dataset>
	<snow:grid dataset="GridFields" id="grid11" label="多表头表格" height="400" fieldstr="组合列1{f1;组合列2{f2|f3}},n1,n2,n3">
	</snow:grid>
	<script>
		
	</script>

</snow:page>
