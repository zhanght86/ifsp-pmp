<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!-- ===============================���ͼƬ����==================================== -->
<html>
<head>
<script>
    function checkValue(){
    	var lg=document.getElementById('lgPic').value;	
    	//����ļ���ʽ
    	if(lg==''){
    		alert("������ͼƬ��Ϣ��");
    		return false;
    	}
    	
    	var arr=new Array(['.jpg','.bmp','.jpeg','.gif']);
    	var valueStr=arr.toString();

    		if(valueStr.indexOf(lg.substring(lg.indexOf('.')))==-1){
    			alert("ϵͳ����ͼƬ��ʽ����");
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
				<td style="color: #4c8fbd">ͼƬ��Ϣ:</td>
				<td><input type="file" name="lgPic" id="lgPic" /></td>
			</tr>
		</table>
		<input id="submitButton" type="button" value="�ύ�ϴ�"
			onClick="checkValue();" />
	</form>
</body>
</html>
