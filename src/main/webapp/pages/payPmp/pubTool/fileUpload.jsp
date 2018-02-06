<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%
String type = request.getParameter("type");
%>
<snow:page title="文件导入">
	
	<%
	Object result = request.getAttribute("result");
	
	if(result==null){
	%>
	<snow:groupbox id="group" label="请选择导入文件">
	<%
	String fId = "com.ruimin.ifs.pmp.pubTool.comp.FileProcessAction:processUploadFile?type="+type;
	%>
		<snow:sform name="uploadFileForm" flowId="<%=fId %>" isSubFile="true">
			<div style="padding: 10px;">
			<input type="file" name="upFile" width="100px" style="border: opx" size="100"/>
			</div>
		</snow:sform>
	</snow:groupbox>
	<script type="text/javascript">
		function excuteImport(){
			var fi = document.uploadFileForm.upFile.value;
			if(fi==""){
				$.warn("请选择要处理的文件!");
			}else{
				document.uploadFileForm.submit();
			}
		}
	</script>
	<%}else{ //导入结果
		boolean isSuccess = Boolean.valueOf(result.toString());
	%>
	<%if(request.getAttribute("impFile")!=null){ %>
	<snow:groupbox id="group0" label="导入文件">
		<div style="padding: 10px">
		导入文件：<%=request.getAttribute("impFile").toString() %>
		</div>
	</snow:groupbox>
	<br/>
	<%} %>
	<snow:groupbox id="group" label="文件导入结果">
		<%
		Object resMsg = request.getAttribute("resultMsg");
		List resList = null;
		if(resMsg!=null){
			resList = (List)resMsg;
		}
		if(resList==null){
		%>
		<div style="padding: 10px;color: red">
			没有执行返回结果，请联系管理员!
		</div>
		<%}
		for(int i=0;i<resList.size();i++){
			if(i==0){
			%>
			<div style="padding: 10px;">
				<%=resList.get(i).toString() %>
			</div>
			<ul style="padding-left: 10px;">
		<%}else{ %>
				<li style="line-height: 24px"><%=resList.get(i).toString() %></li>
		<%} %>
			<%if(i==resList.size()-1){ %>
			</ul>
			<%}} %>
	</snow:groupbox>
	<script type="text/javascript">
		parent.importFileCallBack();
	</script>
	<%} %>
</snow:page>
