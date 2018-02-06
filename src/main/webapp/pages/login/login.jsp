<%@page import="com.ruimin.ifs.framework.utils.ValueUtil"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import="com.ruimin.ifs.framework.session.SessionManager"%>
<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo"%>
<%@page import="com.ytec.yuap.uacs.authentication.AttributePrincipal"%>
<%@page import="com.ytec.yuap.uacs.util.AssertionHolder"%>
<%@page import="com.ytec.yuap.uacs.validation.Assertion"%>
<%@page import="com.ruimin.ifs.login.comp.DESEncrypt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	String contextPath = request.getContextPath();
%>
<link href="<%=contextPath%>/pages/login/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/pages/login/js/b.js"></script>
<script type="text/javascript" src="<%=contextPath%>/pages/login/js/md5.js"></script>
<title>证通二维码支付管理平台</title>
</head>
<body onload="func_check()">
	<div class="header">
		<div class="container">
<!-- 			<div class="link"> -->
<!-- 				<a href="">用户协议</a> <span>/</span> <a href="">隐私</a> <span>/</span> <a href="">登录说明</a> -->
<!-- 			</div> -->
		</div>
	</div>

	<div class="center">
		<div class="container">
			<div class="khao" id="khao"></div>
			<div class="form-div" id="form-div">
				<%
					String action = request.getContextPath() + "/login.ifs";
				%>
				<snow:sform name="loginForm" flowId="com.ruimin.ifs.login.comp.LoginAction:snowLogin" action="<%=action%>" className="form">
					<h3>用户登录</h3>
					<div>
						<input type="text"  id="userName" name="userName" placeholder="用户名"  > 					</div>
					<div>
						<input type="password" id="passWord" name="passWord" placeholder="密码" onkeypress="nextEvent('submit');" onfocus="inputfocus(this)" onblur="inputblur(this)"> <i class="icon-pwd"></i>
					</div>
					<div class="btn-div">
						<a onclick="submitForm()">登 录</a>
					</div>
					<div id="messageinfo" style="font-family: Microsoft Yahei; font-size: 15px; font-weight: 200; color: orangered;">
					</div>
				</snow:sform>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<p>&copy;&nbsp;2017&nbsp;证通股份有限公司<font size="0.5">&nbsp;&nbsp;&nbsp;&nbsp;由上海睿民互联网科技提供技术服务</font></p>
		</div>
	</div>

	<script type="text/javascript">
	window.onload = function submitForm(){ 
		var btn= document.getElementsByName("loginForm");
		btn[0].submit();
	}
	</script>
</body>
