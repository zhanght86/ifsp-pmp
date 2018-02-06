<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<div>
		<div style="color: #4c8fbd; text-align: center">已上传</div>
	</div>
	<div style="text-align: center">
		<img src="<%=request.getAttribute("displayUrl")%><%=request.getAttribute("fileCode")%>" name="lgPic" style="height:280px;width:750px;" />
	</div>
	<input type="hidden" value="<%=request.getAttribute("fileCode")%>" id="lgPic" />
	<input type="hidden" value="true" id="result" />
	
	<script type="text/javascript">
		var result="<%=request.getAttribute("result")%>";
		if (result == "true") {
			parent.importFileCallBack(result);
		}
	</script>
</body>
</html>