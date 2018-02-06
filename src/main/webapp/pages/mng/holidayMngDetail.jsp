<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="工作日期维护">
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/mng/js/holiday.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/mng/css/holiday.css" />
<snow:dataset id="holidayDetail" path="com.ruimin.ifs.mng.dataset.HolidayDetail" init="true" submitMode="current" readOnly="true"></snow:dataset>
<%
String reqId = request.getParameter("id");
String field = null;
if(StringUtils.isNotBlank(reqId)){
	field = "year,sunWorkDay,sunHoliDay";
}else{
	field = "year";
}
%>
<snow:form id="detailform" dataset="holidayDetail" label="年份信息" fieldstr="<%=field %>" colnum="4" ></snow:form>
<div id="month"></div>
<div style="display: none">
	<snow:button id="btnSave" dataset="holidayDetail"></snow:button>
</div>
<script type="text/javascript">
	var holidayArray = null;
	function initCallGetter_post(){
		var yr = holidayDetail_dataset.getValue("year");
		var strHoliday = holidayDetail_dataset.getValue("def");
		holidayArray = genCalendar(yr,document.getElementById("month"),strHoliday);
	}

	function saveHoliday(){
		var yr = holidayDetail_dataset.getValue("year");
		var oldDef = holidayDetail_dataset.getValue("def");
		var newDef = holidayArrayToStr(yr,holidayArray);
		if(oldDef!=newDef){
			holidayDetail_dataset.setValue("def",newDef);
			btnSave.click();
		}else{
			$.warn("请修改后保存!");
		}
	}
	
	function btnSave_postSubmit(btn,retmap){
		$.success("操作成功!", function() {
			parent.editPost();
		});
	}
	
</script>
</snow:page>
