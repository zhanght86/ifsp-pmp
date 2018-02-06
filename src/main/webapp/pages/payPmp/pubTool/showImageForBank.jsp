<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<br/><br/>
	<input id="submit" type="button" name="Submit" value="重新选择" onclick="location.href='/ifsp-pmp/pages/payPmp/pubTool/uploadImage.jsp'">
		<!-- <div>
			<div style="color: #4c8fbd ;text-align: left">已上传</div>
		</div> -->
		
		<div style="text-align:center;display: none;"><img
				src="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>"
				name="lgPic" style="height: 66px; width: 276px;" /></div>
		
	
	<input type="hidden" value="<%=request.getAttribute("fileCode")%>" id="lgPic" />
	<input type="hidden" value="true" id="result" />
	<input type="hidden" value="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>" id="picSrc" />	
		
	<script type="text/javascript">
	var result="<%= request.getAttribute("result") %>";
	var picSrc = "<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>";
	if(result == "true"){
		parent.importFileCallBack(result,picSrc);
	}
	
	
</script>
</body>
</html>