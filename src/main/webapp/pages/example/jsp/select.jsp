<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="demo">
	<snow:dataset id="Fields" path="com.ruimin.ifs.example.dataset.Fields" init="true"></snow:dataset>
	<snow:form label="表单" dataset="Fields" fieldstr="s1,s2,s4,s5,c1,c2,c3,c4,c5" id="f1" border="true" collapsible="true"></snow:form>
	<script>
	</script>

</snow:page>
