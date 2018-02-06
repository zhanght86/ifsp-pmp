<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>



<html>
<head>
<style type="text/css">
#s1 {
	color: red;
	text-align: center;
}
</style>

<title>全量文件导入</title>
</head>
<body style="background-color: #f7fbfc; border: 1px solid #eee; font-size: 12px; background-color: rgb(224, 243, 250); background: #f7fbfc; scrollbar-arrow-color: #fff; scrollbar-face-color: #dce2e4; scrollbar-3dlight-color: #dce2e4; scrollbar-highlight-color: #dce2e4; scrollbar-shadow-color: #dce2e4; scrollbar-darkshadow-color: #b3bcbe; scrollbar-track-color: #ebf0f2; scrollbar-base-color: #ebf0f2;">
 <table align="center" width="100%" style="">
	<tr>
		<td>					    
		   <form method="POST" enctype="multipart/form-data" id="postForm"
				action="${pageContext.request.contextPath}/UploadXlsServlet?type=<%=StringUtil.filtrateSpecialCharater( request.getParameter("type")) %>"target="_self">
				<span id="nameMsg" align="center" style="font-size: 22px; padding-left: 3px; border-bottom: 1px dotted #e5e5e5;">请选择要上传的文件</span>
				<br><br> 
				信息导入：<input id="file" type="file" name="upload" /><br /> 
			    <input type="button" onclick="btn_file();" value="提交">
			    <br><br>
			    <% if(request.getParameter("type").equals("city")){%>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/cityTemplate.xls" onclick>地区文件模版下载 </a>
			    <% }else if(request.getParameter("type").equals("org")){%>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/orgTemplate.xls">机构文件模版下载 </a>
			    <% }else if(request.getParameter("type").equals("openOrg")){ %>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/openMchrTemplate.xls">开户机构文件模版下载 </a>
			    <% }else if(request.getParameter("type").equals("bathAdd")){ %>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/termadd.xls">批量入库文件模版下载 </a>	
			    <% } else if(request.getParameter("type").equals("blacklist")){%>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/userBlacklist.xls">用户黑名单信息模版下载 </a>
			    <%}else if(request.getParameter("type").equals("serviceMchtPolling")){ %>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/serviceMchtPolling.xls">服务机构商户巡检信息模版下载 </a>
			    <%}else if(request.getParameter("type").equals("txnWhiteList")){  %>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/spclMchtTxnWhiteListTemplate.xls">特殊商户交易白名单模版下载 </a>
			    <%}else if(request.getParameter("type").equals("merchantMessage")){  %>
			    	<a href="${pageContext.request.contextPath}/pages/ImportTemplate/merchantMessage.xls">商户信息导入模版下载 </a>
			    <%}%>
			    <div style="text-align: center;color: red;  margin-top:50px;">
			    ${message} 
				</div>
			</form>				
		</td>
   </tr>
 </table>
<script type="text/javascript">

	function btn_file() {
		var file = document.getElementById("file").value;
		var arr=new Array(['.xls']);
		var valueStr=arr.toString();
		var index1=file.lastIndexOf("."); 
		var index2=file.length;
		var postf=file.substring(index1,index2);
		if(postf=='.XLS'){
			file=file.substring(0,index1)+".xls";
		}
		//上传文件控制
		if (file==null||file=="" ) {
			alert("文件不能为空！请选择导入文件！");
			return false;
		}else if(valueStr.indexOf(file.substring(file.indexOf('.')))==-1){
			alert("文件类型错误！必须为xls类型！");
			return false;
		}else{
			document.getElementById("postForm").submit();
		}	
    	var regex=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
    	var me=regex.test(file);
    	if(!me){
    		$.warn("文件名只能为数字字母和下划线！"); 
    		return false;
    	}
	}
		
	var result="<%= request.getAttribute("result") %>";
	if(result == "true"){
		parent.importFileCallBack(result);
	}
	
	
</script>
</body>
</html>