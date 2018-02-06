<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" import="java.util.*"
contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!-- ===============================添加图片窗体==================================== -->
<snow:page title="">
	<form action="${pageContext.request.contextPath}/UploadImageServlet?picType=<%=StringUtil.filtrateSpecialCharater(request.getParameter("picType"))%>&picNum=<%=StringUtil.filtrateSpecialCharater(request.getParameter("picNum"))%>" 
	method="POST" enctype="multipart/form-data" id="imgForm" style="height:150px">		
		<div style="padding-left: 30px;">
			<br/><br/>
			<table>
				<tr>
					<td style="color: #4c8fbd">图片信息:&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" name="lgPic" id="lgPic" /></td>
				</tr>
			</table>		
			<br/>
			<input id="submitButton" type="button" value="上传图片" onClick="checkValue();" />
		</div>
		<input type="hidden" value="false" id="result" />
	</form>
	<script>	
    function checkValue(){    	
    	var lg=document.getElementById('lgPic').value; 
    	var strFileName=lg.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取
    	if(lg==''){
    		$.warn("请先选择图片！"); 
    		return false;
    	}
    	var regex=/^[\u4E00-\u9FA5A-Za-z0-9_-]+$/;
    	var me=regex.test(strFileName);
    	if(!me){
    		$.warn("图片文件名只能为数字字母和下划线和汉字！"); 
    		return false;
    	}
    	//检查文件格式
    	if(lg==''){
    		$.warn("请完善图片信息！"); 
    		return false;
    	}
    	
    	var arr=new Array(['.jpg','.bmp','.jpeg','.gif','.png','.JPG','.BMP','.JPEG','.GIF','.PNG']);
    	var valueStr=arr.toString();

    		if(valueStr.indexOf(lg.substring(lg.indexOf('.')))==-1){
    			$.warn("图片格式错误！仅支持全大写或全小写的[jpg、jpeg、bmp、gif、png]格式图片！");
        		return false;
    		}
    		
        document.getElementById('imgForm').submit();
        document.getElementById('submitButton').disabled=true;
    	return true;
    }
    
    </script>
</snow:page>
