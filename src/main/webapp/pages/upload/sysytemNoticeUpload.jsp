<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!-- ===============================添加图片窗体==================================== -->
<html>
<head>
<script>
    function checkValue(){
    	var lg=document.getElementById('lgPic').value;	
    	//检查文件格式
    	if(lg==''){
    		alert("请完善图片信息！");
    		return false;
    	}
    	
    	var arr=new Array(['.jpg','.bmp','.jpeg','.gif']);
    	var valueStr=arr.toString();

    		if(valueStr.indexOf(lg.substring(lg.indexOf('.')))==-1){
    			alert("系统公告图片格式错误！");
        		return false;
    		}
    		
        document.getElementById('imgForm').submit();
        document.getElementById('submitButton').disabled=true;
    	return true;
    }
    </script>
</head>

<body>
	<form action="/PAY-PMP/SystemNoticeUploadServlet" method="POST"
		enctype="multipart/form-data" id="imgForm">
		<table>
			<tr>
				<td style="color: #4c8fbd">图片信息:</td>
				<td><input type="file" name="lgPic" id="lgPic" /></td>
			</tr>
		</table>
		<input id="submitButton" type="button" value="提交上传"
			onClick="checkValue();" />
	</form>
</body>
</html>
