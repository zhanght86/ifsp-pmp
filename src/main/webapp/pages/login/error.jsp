<%@page import="com.ruimin.ifs.util.CommonConstants2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!DOCTYPE html>
<html>    
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
<title>用户信息错误</title>    
<style>    
body{font-family:Helvetica,Arial,sans-serif;margin:0;background-color:#CCC;background:-webkit-linear-gradient(#CCC,#AAA);background-attachment:fixed;}#cell{padding:40px;}#box{background-color:white;color:black;font-size:10pt;line-height:18px;margin:auto;max-width:800px;border-radius:5px;-webkit-box-shadow:2px 5px 12px#555;padding:20px;}    
ul{margin:0;padding-bottom:0;}    
li{padding-top:2px;}    
h1{font-size:18pt;line-height:30px;margin:0;}    
.right{color:#B5B5B5;text-align: right;}    
a{color:#B5B5B5;}    
</style>    
</head>    
<body id="t">    
<div id="cell">    
<div id="box">    
<h1>用户信息出现错误</h1>    
<p>    
<ul>    
<li>用户信息不存在</li>    
<li>用户没有角色和权限</li> 
<span id="totalSecond">3</span>秒后自动返回
</ul>    
</p>    
<p class="right">&copy; 2017 @<a href="<%=CommonConstants2.getProperty("casLogonOut")%>">重新登陆</a></p>    
</div>    
</div>    
</body>  
<script language="javascript" type="text/javascript">

    var second = document.getElementById('totalSecond').innerText;   
    setInterval("redirect()", 1000); //æ¯1ç§éè°ç¨redirect()æ¹æ³ä¸æ¬¡
   function redirect()
    {
        if (second < 0)
       {
            location.href ="<%=CommonConstants2.getProperty("casLogonOut")%>";
        } else
        {
              document.getElementById('totalSecond').innerText = second--;
        }
    }

</script>
</html>