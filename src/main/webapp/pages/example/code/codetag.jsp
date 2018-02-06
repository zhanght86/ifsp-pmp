
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Map<String,String> map = new HashMap<String,String>();
	String path = request.getSession().getServletContext().getRealPath("");
	String[] jspArr = StringUtils.split(request.getParameter("jsp"),";");
	String[] queryArr = StringUtils.split(request.getParameter("dataset"),";");
	if(jspArr == null){
		jspArr = new String[]{};
	}
	if(queryArr == null){
		queryArr = new String[]{};
	}
	for(String jspStr:jspArr){
		File jspFile = new File(path + "/pages/example/jsp/" + StringUtils.trim(jspStr) + ".jsp");
		InputStream input = null;
		try{
			if(jspFile.exists()){
				input = new BufferedInputStream(new FileInputStream(jspFile));
				String foo = IOUtils.toString(input, "UTF-8").replaceAll("<","&lt;");
				map.put(jspStr+".jsp", foo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input!=null){
				try{
					input.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	for(String commqueryStr:queryArr){
		File commqueryFile = new File(path + "/WEB-INF/classes/com/ruimin/ifs/example/dataset/" + commqueryStr + ".dtst");
		InputStream input2 = null;
		try{
			if(commqueryFile.exists()){
				input2 = new BufferedInputStream(new FileInputStream(commqueryFile));
				String bar = IOUtils.toString(input2, "UTF-8").replaceAll("<","&lt;");
				map.put(commqueryStr+".dtst", bar);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input2!=null){
				try{
					input2.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码</title>
<script type="text/javascript" src="syntaxhighlighter/scripts/shCore.js"></script>
<script type="text/javascript"
	src="syntaxhighlighter/scripts/shBrushPlain.js"></script>
<script type="text/javascript"
	src="syntaxhighlighter/scripts/shBrushXml.js"></script>
<link type="text/css" rel="stylesheet"
	href="syntaxhighlighter/styles/shCoreEclipse.css" />
<script type="text/javascript">
	SyntaxHighlighter.all();
</script>
</head>
<body>
	<%
	for(String foo:jspArr){
		if(map.containsKey(foo+".jsp")){
%>
	<h2><%= "/example/jsp/"+foo %>.jsp</h2>
	<pre class="brush: text"><%= map.get(foo+".jsp") %></pre>
	<%
		}
	}
%>
	<%
	for(String bar:queryArr){
		if(map.containsKey(bar+".dtst")){
%>
	<h2><%= "/example/dataset/"+bar %>.dtst</h2>
	<pre class="brush: xml"><%= map.get(bar+".dtst") %></pre>
	<%
		}
	}
%>
</body>
</html>