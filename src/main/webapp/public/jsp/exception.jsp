<%@page import="com.ruimin.ifs.core.server.ServerUtil"%>
<%@page import="com.ruimin.ifs.core.exception.SnowExceptionUtil"%>
<%@page import="com.ruimin.ifs.core.global.GlobalUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>异常页面</title>
<style>
	.suc{
		background: url("<%=request.getContextPath()%>/public/jsp/imgs/500.jpg") no-repeat center center;
		width:600px;
		height:400px;
		font-size: 16px;
	}
</style>
</head>
<body>
<%if(GlobalUtil.isProductMode()){ %>
	<div class="suc">
	<div style="padding: 20px;">
		<div ><font color="red">系统异常，请联系管理员。</font></div> 
		<div style="padding-top: 20px;"><a href="<%=request.getContextPath()%>/">重新登录</a></div>
	</div>
	
	</div>
<%}else{%>
<div class="suc">
	<div style="padding: 20px;">
		<div ><font color="red">系统异常，请联系管理员。</font></div> 
		<div style="padding-top: 20px;"><a href="<%=request.getContextPath()%>/">重新登录</a></div>
	</div>
	<%
	SnowExceptionUtil.snowException2Log(SnowExceptionUtil.wrapException(new Exception(exception)), ServerUtil.getVirtualServer().getLogger()); 
	%>
</div>
<%} %>
</body>
</html>