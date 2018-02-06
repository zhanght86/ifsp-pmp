<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="">
	<snow:tabs id="demo">
		<snow:tab title="预览" id="preview" closable="false" url="/pages/example/jsp/${param.jsp}.jsp">&nbsp;</snow:tab>
		<snow:tab title="代码" id="code" closable="false" url="/pages/example/code/codetag.jsp?jsp=${param.jsp}&dataset=${param.dataset}">&nbsp;</snow:tab>
	</snow:tabs>
	<script>
	</script>
</snow:page>
