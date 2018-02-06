<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户巡检管理">
	<!-- 商户巡检基本信息数据集 -->
	<snow:dataset id="serviceMchtPolling" path="com.ruimin.ifs.pmp.service.dataset.serviceMchtPolling" 
		init="true" submitMode="current">
	</snow:dataset>
	
	<!-- 查询条件 -->
	<snow:query id="queryId" label="查询条件" dataset="serviceMchtPolling"
		collapsible="false"
		fieldstr="qmchtName,qserviceName,qtermId,qpollingDate"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="serviceMchtPolling" height="99%" label="商户巡检信息"
		id="gridMain" fitcolumns="true"					
		fieldstr="brname[200],mchtId[200],mchtName[200],pollingDate,serviceName[200],opr"
		paginationbar="btnImport"></snow:grid>
	
	<!-- 详细信息窗口 -->
	<snow:window id="winDetail" title="商户巡检详情" modal="true" closable="true">
		<!-- 基本信息 -->
		<snow:form id="mchtPollingInfo" label="基本信息" dataset="serviceMchtPolling"
			fieldstr="mchtId,mchtName,mchtPersonName,mchtPhone,serviceName,mchtAmrNo"></snow:form>
		<br/>
		<snow:form id="mchtPollingMoreInfo" label="巡检信息" dataset="serviceMchtPolling"
			fieldstr="termId,manageName,manageAddr,pollingDate,equipmentId,configurationCode,keyboardCode,versionsCode,pollingResult,remark"></snow:form>
	</snow:window>


	<script type="text/javascript">
		//------------***********************详情**************************------------------------
		function gridMain_opr_onRefresh(record) {//给详情超链接绑定事件
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:openDetail()\"> 详情</a></span>";
			}
		}
		function openDetail() {//点击详情超链接触发事件
			window_winDetail.open();
 		}
		
		function window_winDetail_afterClose() {//窗口关闭后清除数据
			serviceMchtPolling_dataset.cancelRecord();
			serviceMchtPolling_dataset.flushData(serviceMchtPolling_dataset.pageIndex);
		}
		//----------------------------导入-----------------------------------
		 var iscallback = false;
          function btnImport_onClick(){
        	iscallback = false;
          	$.open("importFile","文件导入","/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_POLLING_LIST%>","750", "280", false, true, null, false,"关闭");
          }
          function importFile_onButtonClick(i,win,framewin){
        	  if(i==0){
        		  win.close();
        		  serviceMchtPolling_dataset.flushData(serviceMchtPolling_dataset.pageIndex);
        	  }else{
        		  framewin.excuteImport();
        		  importFileCallBack();
        	  }
          }
         function importFileCallBack(){
   		 	iscallback = true;
   		 serviceMchtPolling_dataset.flushData(serviceMchtPolling_dataset.pageIndex);			
   		}
	</script>
</snow:page>