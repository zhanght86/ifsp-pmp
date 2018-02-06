<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="demo">
	<snow:dataset id="Fields" path="com.ruimin.ifs.example.dataset.Fields" init="true"></snow:dataset>
	<snow:form label="表单" dataset="Fields" fieldstr="d1,d2" id="f1" colnum="2" border="true" collapsible="true"></snow:form>
	<script>
		
	</script>

</snow:page>
