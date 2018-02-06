<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="特殊商户交易白名单管理">
	<!-- 券商app信息 dataset -->
	<snow:dataset id="whiteList"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.txnWhiteList"
		init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query id="qryQuery" label="查询条件" dataset="whiteList"
		collapsible="false" fieldstr="qMchtId,qMchtSimpleName,qFiltrateValue"></snow:query>
	<!-- 数据列表 -->
	<snow:grid id="grdQueryList" dataset="whiteList" height="99%"
		label="白名单列表" fitcolumns="true"
		fieldstr="mchtId,mchtSimpleName,filtrateValue"
		paginationbar="btnAdd,btnUpd,btnDlt,btnImport"></snow:grid>
	<!-- 新增窗口 -->
	<snow:window id="windAdd" closable="true" width="900" title="新增特殊商户交易白名单" modal="true" buttons="btnAddSubmit">
		<snow:form id="frmAdd" dataset="whiteList" border="false" fieldstr="mchtId,filtrateValue"></snow:form>
		<snow:button id="btnAddSubmit" dataset="whiteList" hidden="true"></snow:button>
	</snow:window>
	<!-- 修改窗口 -->
	<snow:window id="windUpd" closable="true" width="900" title="修改特殊商户交易白名单" modal="true" buttons="btnUpdSubmit">
		<snow:form id="frmUpd" dataset="whiteList" border="false" fieldstr="mchtId,filtrateValue"></snow:form>
		<snow:button id="btnUpdSubmit" dataset="whiteList" hidden="true"></snow:button>
	</snow:window>
	<!-- 删除按钮 -->
      <snow:button id="btnDltSubmit" dataset="whiteList" hidden="true"> </snow:button>
	<script>
	/**************************新增**************************/
	//点击新增按钮
	function btnAdd_onClick(){
		window_windAdd.open();
	}
	function btnAddSubmit_postSubmit(){
		$.success("操作成功!",function(){
 		   window_windAdd.close();
 		   whiteList_dataset.flushData(whiteList_dataset.pageIndex);
 	   });
	}
	function window_windAdd_beforeClose(){
		whiteList_dataset.cancelRecord();
	}
	/**************************修改**************************/
	//点击修改按钮
	function btnUpd_onClick(){
		var cfgId = whiteList_dataset.getValue("cfgId");
		if(!cfgId){
			$.warn("请先选择一条记录");
			return false;
		}
		 window_windUpd.open();
	}
	function btnUpdSubmit_postSubmit(){
		$.success("操作成功!",function(){
 		   window_windUpd.close();
 		   whiteList_dataset.flushData(whiteList_dataset.pageIndex);
 	   });
	}
	function window_windUpd_beforeClose(){
		whiteList_dataset.cancelRecord();
	}
	/**************************删除**************************/
	//点击删除按钮
	function btnDlt_onClick(){
		var cfgId = whiteList_dataset.getValue("cfgId");
		if(!cfgId){
			$.warn("请先选择一条记录");
			return false;
		}
		$.confirm("是否确认删除？一经删除后该信息无法恢复！", function() {
			btnDltSubmit.click();
		}, function() {
			return false;
		});
	}
	//删除按钮回调
	function btnDltSubmit_postSubmit(){
		$.success("操作成功!",function(){
			whiteList_dataset.flushData(whiteList_dataset.pageIndex);
 	   });
	}
	/**************************批量导入**************************/
	var iscallback = false;
	function btnImport_onClick(){
		iscallback = false;
		$.open("importFile", '文件导入', "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_TXN_WHITE_LST%>", "750", "280", false, true, null, false,"关闭");
	}
	 function importFile_onButtonClick(i,win,framewin){
   	  if(i==0){
   		  win.close();
   			whiteList_dataset.flushData(whiteList_dataset.pageIndex);
   	  }else{
   		  framewin.excuteImport();
   		  importFileCallBack();
   	  }
     }
    function importFileCallBack(){
		 	iscallback = true;
		 	whiteList_dataset.flushData(whiteList_dataset.pageIndex);			
	}
	</script>
	
</snow:page>