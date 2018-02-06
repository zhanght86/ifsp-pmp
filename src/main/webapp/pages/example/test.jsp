<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="Demo">
	<snow:dataset id="aaa" path="com.ruimin.ifs.example.dataset.SampleFields" init="true"></snow:dataset>
	<snow:form id="f1" dataset="aaa" label="Hello" fieldstr="n1,n2,n3,f1,f2,f3"></snow:form>
	<snow:button id="btSave" dataset="aaa"></snow:button>
	<snow:grid dataset="aaa" id="grid1" fieldstr="r1,r2,select,select2,f1,f2,f3,n1,n2,n3,s1,s2,d1,d2"></snow:grid>
	<script>
		
	</script>
</snow:page>