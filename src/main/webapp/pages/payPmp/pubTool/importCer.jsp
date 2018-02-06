<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=GB2312" pageEncoding="UTF-8"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!-- ===============================上传证书窗体==================================== -->
<html>
<head>
<script>
    function checkValue(){
		var file = document.getElementById("file").value;
		var arr=new Array(['.cer','.p12','.pem','.pfx']);
		var valueStr=arr.toString();
		
		//上传内容文件控制
		if (file==null||file=="" ) {
			alert("证书文件不能为空！请选择要上传的证书!");
			return false;		
		}else if(valueStr.indexOf(file.substring(file.indexOf('.')))==-1){
			alert("有效证书的后缀名必须为.cer .p12 .pem .pfx! 请选择要上传的证书!");
			document.getElementById("file").value="";
			return false;
		}else{
			document.getElementById("postForm").submit();
			document.getElementById('submitButton').disabled=true;
		}
    	var regex=/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
    	var me=regex.test(file);
    	if(!me){
    		$.warn("文件名只能为数字字母和下划线！"); 
    		return false;
    	}
    }
 	// 返回参数
	var flag="<%=request.getAttribute("flag")%>";		
	var message="<%=request.getAttribute("message")%>";
	var certPath="<%=request.getAttribute("certPath")%>";
	var certCode="<%=request.getAttribute("certCode")%>";
	var certName="<%=request.getAttribute("certName")%>";
	var certType="<%=request.getAttribute("certType")%>";
	var certifiId="<%=request.getAttribute("certifiId")%>";
	if (flag == 1) {
		parent.callBackCertPath(certPath);		
		parent.callBackCertCode(certCode);
		if(certType == 2){			
			parent.callBackCertId(certifiId);
		}else if(certType == 1){
			parent.callBackCertName(certName);	
		}		
		
	}else {
		certPath = null;
		parent.callBackCertPath(certPath);
		if(certType == 2){			
			parent.callBackCertId(certifiId);
		}
	}

</script>
</head>

<body>
	<form
		action="${pageContext.request.contextPath}/UploadCertServlet?certType=<%=StringUtil.filtrateSpecialCharater(request.getParameter("certType"))%>"
		method="POST" enctype="multipart/form-data" id="postForm">
		<table>
			<tr>
				<td style="color: #4c8fbd">商户证书文件:</td>
				<td><input type="file" name="upload" id="file" /></td>
			</tr>
		</table>
		<input id="submitButton" type="button" value="提交上传"
			onClick="checkValue();" />
		<div 
			style="text-align: center; color: red; margin-top: 50px;">
			${message}
		</div>
	</form>
</body>
</html>