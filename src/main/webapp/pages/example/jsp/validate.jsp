<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="demo_field_1">
	<snow:dataset id="GroupValidateFields" path="com.ruimin.ifs.example.dataset.GroupValidateFields" init="true"></snow:dataset>
	<snow:form label="前台验证" dataset="GroupValidateFields" fieldstr="requireField,mail,number,maxNumber,url,chinese,password,numOrWord,multiValidate,currency,textarea" id=""></snow:form>
	<snow:form label="后台验证" dataset="GroupValidateFields" fieldstr="mail,bnumber,burl,bmail" id=""></snow:form>
	<div class="" style="text-align:center">
		<snow:button id="btSave" dataset="GroupValidateFields"></snow:button>
	</div>

	<script>
		$(function(){
			
		});
	</script>

</snow:page>
