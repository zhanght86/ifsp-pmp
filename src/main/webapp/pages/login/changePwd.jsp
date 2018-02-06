<%@page import="com.ruimin.ifs.framework.utils.SysParamUtil"%>
<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%
	String contextPath = request.getContextPath();
%>
<snow:page title="操作员密码修改">
<%
	SessionUserInfo si = SessionUserInfo.getSessionUserInfo();
	boolean forceChange =false;
	if(null != si){
		forceChange = si.getPassInfo().isPswdForcedToChange();
	}
	String pswdStrength = SysParamUtil.getInstance().getString("PSWD.PSWD_STRENGTH", "2");
	String pswdLimit = SysParamUtil.getInstance().getString("PSWD.LIMIT", "6");
	String pswdComplexity = SysParamUtil.getInstance().getString("PSWD.COMPLEXITY", "1111");
	String type = request.getParameter("type") == null ? "" : request.getParameter("type");
	String logout = request.getContextPath()+"/logout.ifs";
%>
<style>
.password-strength span {
	height: 15px;
	width: 30px;
	background-color: #eeeeee;
	display: inline-block;
}
</style>
	<snow:dataset id="changePwd" path="com.ruimin.ifs.login.dataset.ChangePwd"></snow:dataset>
	<snow:form id="changePwdForm" dataset="changePwd" label="操作员密码修改" fieldstr="oldPassWord,newPassWord,againNewPassWord" colnum="2"></snow:form>
	<div style="padding: 5px;padding-left: 10px">
	<snow:button id="btSave" dataset="changePwd" desc="确定"></snow:button>
	</div>
	<span style="padding-left: 10px;" id="strengthtshow" class="password-strength">
		<span id="strength_L"></span>
		<span id="strength_M"></span>
		<span id="strength_H"></span>
		<b id="strength_text"></b>
	</span>
<script type="text/javascript" src="<%=contextPath%>/pages/login/js/md5.js"></script>
<script type="text/javascript">
// 	function initCallGetter_post(){
<%-- 		<%if (forceChange) {%> --%>
// 		        if (window.self == window.top) {
// 		        	$.warn("您的初始密码未更改或已超过间隔期,请重新更改密码!");
// 		        }
<%-- 		<%}%> --%>
// 		$("#editor_newPassWord").parent().append($("#strengthtshow")); 
// 	}
	
	$("#editor_newPassWord").parent().append($("#strengthtshow")); 
	function btSave_postSubmit(){
		$.success("操作成功!", function() {
			// logout
			top.location.href = '<snow:url flowId="com.ruimin.ifs.login.comp.LoginAction:snowLogOut" action="<%=logout%>"/>';
        });
	}
	
	//测试某个字符是属于哪一类. 
	function charMode(ch){
		if (ch>=48 && ch <=57) {//数字 
			return 1; 
		} else if (ch>=65 && ch <=90) {//大写字母 
			return 2; 
		} else if (ch>=97 && ch <=122) {//小写字母 
			return 4; 
		} else {//特殊字符 
			return 8; 
		}
	}
	
	//计算出当前密码当中一共有多少种模式 
	function bitTotal(num){
		modes=0; 
		for (var i=0;i<4;i++){ 
			if (num & 1) modes++; 
			num>>>=1; 
		} 
		return modes; 
	}
	
	//返回密码的强度级别
	function checkStrong(pwd){
		if (pwd.length< <%= pswdLimit %>) {//密码太短 
			return 0; 
		}
		modes=0; 
		for (var i=0;i<pwd.length;i++){ 
			modes|=charMode(pwd.charCodeAt(i)); 
		} 
		return bitTotal(modes); 
	}
	
	function getComplexity(pwd) {
		var modes=0; 
		for (var i=0;i<pwd.length;i++){ 
			modes|=charMode(pwd.charCodeAt(i)); 
		}
		return modes;
	}
	
	function checkComplexity(pwd) {
		var pswdComplexity = parseInt("<%=pswdComplexity%>",2);
		var complex = getComplexity(pwd);
		if(pswdComplexity&complex!=pswdComplexity) {
			var msg = "必须包含";
			var num = 1;
			do {
				switch(pswdComplexity & num){
					case 1: msg += "[数字]";break;
					case 2: msg += "[大写字母]";break;
					case 4: msg += "[小写字母]";break;
					case 8: msg += "[特殊字符]";break;
					default:break;
				}
				num <<= 1;
			} while(num < 9);
			$.error(msg);
			return false;
		}
		return true;
	}
	
	function pwdStrength(pwd){
		O_color="#eeeeee"; 
		L_color="#FF0000"; 
		M_color="#FF9900"; 
		H_color="#33CC00"; 
		if (pwd==null||pwd==""){ 
			Lcolor=Mcolor=Hcolor=O_color; 
		} else{ 
			S_level=checkStrong(pwd); 
			switch(S_level) { 
				case 0: 
					Lcolor=Mcolor=Hcolor=O_color;
					$("#strength_text").text("");
					break;
				case 1: 
					Lcolor=L_color; 
					Mcolor=Hcolor=O_color; 
					$("#strength_L").css("background-color",Lcolor);
					$("#strength_text").css("color",Lcolor);
					$("#strength_text").text("弱");
					break; 
				case 2: 
					Lcolor=Mcolor=M_color; 
					Hcolor=O_color; 
					$("#strength_M").css("background-color",Mcolor);
					$("#strength_text").css("color",Mcolor);
					$("#strength_text").text("中");
					break; 
				default: 
					Lcolor=Mcolor=Hcolor=H_color; 
					$("#strength_H").css("background-color",Hcolor);
					$("#strength_text").css("color",Hcolor);
					$("#strength_text").text("强");
			} 
		} 
		$("#strength_L").css("background-color",Lcolor);
		$("#strength_M").css("background-color",Mcolor);
		$("#strength_H").css("background-color",Hcolor);
		return; 
	}
	
<%-- 	<%if (forceChange) {%> --%>
// 		function btSave_postSubmit(button) {
// 	        if (window.self == window.top) {
//                 changePwd_dataset.setReadOnly(true);
//                 btSave.disable(true);
//                 alert("密码更改成功,请重新登陆!");
//                 button.url="/custlogout.do?relogin=true";
// 	        }
// 		}
<%-- 	<%}%> --%>
	
	function btSave_onClickCheck(button) {
		var newPasswd = changePwd_dataset.getValue("newPassWord");
		if (newPasswd != "" && checkStrong(newPasswd) < parseInt("<%=pswdStrength%>")) {
			var msg = "";
			if ("<%=pswdStrength%>" == "1") 
				msg = "至少输入<%=pswdLimit%>位字符";
			else if ("<%=pswdStrength%>" == "2") 
				msg = "至少输入<%=pswdLimit%>位字符,且至少包含两种类型的字符";
			else if ("<%=pswdStrength%>" == "3") 
				msg = "至少输入<%=pswdLimit%>位字符,且至少包含三种类型的字符";
			$.error("您输入的密码强度太弱，请重新输入！("+msg+")");
			return false;
		}
		if(!checkComplexity(newPasswd)) {
			return false;
		}
		var newPasswdSure = changePwd_dataset.getValue("againNewPassWord");
		if(newPasswd!=newPasswdSure){
			$.error("两次输入的新密码不相同");
			return false;
		}
	
		changePwd_dataset.setValue("oldPassWord",hex_md5(changePwd_dataset.getValue("oldPassWord")));
		changePwd_dataset.setValue("newPassWord",hex_md5(newPasswd));
		changePwd_dataset.setValue("againNewPassWord",hex_md5(newPasswdSure));
		return true;
	}
	
	function changePwd_dataset_afterChange(dataset) {
		var foo = $("#editor_newPassWord").val();
		pwdStrength(foo);
	}
</script>
</snow:page>