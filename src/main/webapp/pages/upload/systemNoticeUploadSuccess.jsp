<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=GBK" pageEncoding="UTF-8"%>
<html>
<head>
<script>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
    %>

    </script>
</head>
<body>
	<table>
		<tr>
			<td style="color: #4c8fbd" width="200">系统公告:</td>
		</tr>
		<tr>
			<td><img
				src="/PAY-PMP/SystemNoticePrintImage?fileName=<%=request.getAttribute("lgPic")%>"
				name="lgPic" style="height: 550px; width: 820px;" /></td>
		</tr>
	</table>
	<input type="hidden" value="<%=request.getAttribute("lgPicId")%>"
		id="lgPic" />
</body>
</html>