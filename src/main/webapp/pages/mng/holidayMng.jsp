<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="工作日期维护">
<snow:dataset id="holidayDs" path="com.ruimin.ifs.mng.dataset.Holiday" init="true" submitMode="current"></snow:dataset>
<snow:query id="query" dataset="holidayDs" collapsible="false" fieldstr="year" border="false"  label="查询条件" ></snow:query>
<snow:grid dataset="holidayDs" label="日期信息" id="daylist" fieldstr="year,sunWorkDay,sunHoliDay" paginationbar="btnAdd,btUpdate,btImport,btnDetail" height="99%" ></snow:grid>
<snow:window id="holidayAdd" closable="true" title="工作日期维护" modal="true" buttons="btAddOk">
	<snow:form id="holidayAddForm" dataset="holidayDs" label="" border="false" fieldstr="year"></snow:form>
	<snow:button id="btAddOk" dataset="holidayDs" hidden="true" ></snow:button>
</snow:window>
<script type="text/javascript">
	function btAddOk_onClickCheck(){
		var yr = holidayDs_dataset.getValue("year");
		if(!yr){
			$.warn("年份不能为空!");
			return false;
		}
		return true;
	}

	function btAddOk_postSubmit(btn,retmap){
		if(retmap){
			var retid = retmap.id;
			var yr = retmap.year;
			window_holidayAdd.close();
			if(retid.length>0){
				//editOpen(retid,yr);
				alert(yr+"年节假日信息已存在！");
			}else{
				$.open("holidayadd", "工作日期维护", "/pages/mng/holidayMngDetail.jsp?year="+yr, "750", "500", false, true, null, false,"确定,取消");
			}
		}
	}

	function btnAdd_onClick(){
		window_holidayAdd.open();
	}
	
	function window_holidayAdd_beforeClose(wmf){
		holidayDs_dataset.cancelRecord();
	}
	function window_holidayAdd_onButtonClick(index, win) {
		btAddOk.click();
	}
	
	function btUpdate_postSubmit(btn,retmap){
		if(retmap){
			var retid = retmap.id;
			var yr = retmap.year;
			window_holidayAdd.close();
			if(retid.length>0){
				editOpen(retid,yr);
				
			}else{
				alert(yr+"年节假日信息不存在！请核查！");
			}
		}
	}
	
	function btnDetail_onClick(){
		var id = holidayDs_dataset.getValue("id");
		var yr = holidayDs_dataset.getValue("year");
		editOpen(id,yr);
	}
	
	function editOpen(id,yr){
		$.open("holidayadd", yr+'年', "/pages/mng/holidayMngDetail.jsp?id="+id, "750", "500", false, true, null, false,"确定,取消");
	}
	
	function holidayadd_onButtonClick (i,win,framewin){
		if(i==1){//取消按钮
			win.close();
		}else if(i==0){
			framewin.saveHoliday();
		}
	}
	
	function editPost(){
		holidayadd.close();
		holidayDs_dataset.flushData(holidayDs_dataset.pageIndex);
	}
	
	var iscallback = false;
	function btImport_onClick(){
		iscallback = false;
		$.open("importFile", '文件导入', "/pages/mng/fileUpload.jsp?type=<%=SnowConstant.FILE_HOLIDAY_LST%>", "750", "280", false, true, null, false,"确定,关闭");
	}
	function importFile_onButtonClick(i,win,framewin){
		if(i==1){//取消按钮
			win.close();
			holidayDs_dataset.flushData(holidayDs_dataset.pageIndex);
		}else if(i==0){
			if(iscallback){
				win.close();
				holidayDs_dataset.flushData(holidayDs_dataset.pageIndex);
			}else{
				framewin.excuteImport();
				importFileCallBack();
			}
		}
	}
		function importFileCallBack(){
			iscallback = true;
			holidayDs_dataset.flushData(holidayDs_dataset.pageIndex);			
		}
</script>
</snow:page>
