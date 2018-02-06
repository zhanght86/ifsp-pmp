<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<br/><br/>
	<input id="submit" type="button" name="Submit" value="重新选择" onclick="location.href='/ifsp-pmp/pages/payPmp/pubTool/uploadImage.jsp?picNum=<%= request.getAttribute("picNum") %>'">  
		<div style="text-align:center;display: block;">
			<% 
				String fileCode = String.valueOf(request.getAttribute("fileCode"));
				if(fileCode == null || fileCode.equals("")){
				String message = String.valueOf(request.getAttribute("message"));	
			%>
			<div style="text-align: center; color: red; margin-top: 50px;">
				<%=message %>
			</div>
			<%
				}else{
			%>
			<div style="text-align:center;display: none;">
			<img
				src="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>"
				name="lgPic" style="height: 550px; width: 820px;" id="showDiv"/>
				</div>
			<%
				}
			%>
			
		</div>
			
	<input type="hidden" value="<%=request.getAttribute("fileCode")%>"
		id="lgPic" />		
	<input type="hidden" value="true" id="result" />
	<input type="hidden" value="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>" id="picSrc" />	
		
	<script type="text/javascript">
	var result="<%= request.getAttribute("result") %>";
	var picSrc="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>";
	var picNum="<%= request.getAttribute("picNum") %>";
	if(result == "true"){
		parent.importFileCallBack(result,picSrc,picNum);		
	}
	
	
</script>
</body>
</html>