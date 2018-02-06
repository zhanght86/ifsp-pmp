<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道路由管理">
      <!-- 通道路由的dataset -->
      <snow:dataset id="galRoutMng" path="com.ruimin.ifs.pmp.chnlMng.dataset.galRoutMng" init="true" submitMode="all"></snow:dataset>
      <!-- 页面表格的dataset -->
      <snow:dataset id="showGridData" path="com.ruimin.ifs.pmp.chnlMng.dataset.showGridData" init="false" submitMode="all"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="galRoutMng" collapsible="false" fieldstr="qrouteId,qpayTxnTypeId,qacctTypeNo,qrouteStat,qchnlId"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="galRoutMng" height="99%" label="通道路由管理" fitcolumns="true" fieldstr="routeId,chnlId,payTxnTypeId,acctTypeNo,ttsCode,routeStat,openDate,opr" paginationbar="btAdd,btUpt,btStatus"></snow:grid>
      <!-- 新增页面 -->
      <snow:window id="addWindow" closable="true" width="900" title="通道路由新增" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="galRoutMng" border="false" fieldstr="routeStat,payTxnTypeId,acctTypeNo,ttsCode,chnlId"></snow:form>
            <br/>
            <p>通道列表</p>
         <snow:grid dataset="showGridData" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridAddId" fitcolumns="true" height="300" editable="" toolbar=""></snow:grid>
            
            <snow:button id="btSave" dataset="galRoutMng" hidden="true"></snow:button>
      </snow:window> 
      <!-- 修改页面 -->
       <snow:window id="uptWindow" closable="true" width="900" title="通道路由修改" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="galRoutMng" border="false" fieldstr="routeId,routeStat,payTxnTypeId,acctTypeNo,ttsCode,chnlId"></snow:form>
            <br/>
            <p>通道列表</p>
            <snow:grid dataset="showGridData" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridUpdId" fitcolumns="true" height="300" editable="" toolbar=""></snow:grid>
            
           <snow:button id="btUpdate" dataset="galRoutMng" hidden="true"></snow:button> 
      </snow:window>  
      <!-- 详情页面 -->
      <snow:window id="detailWindow" closable="true" width="900" title="通道路由详情" modal="true" buttons="btnDetail">
            <snow:form id="detailId" dataset="galRoutMng" border="false" fieldstr="routeId,routeStat,payTxnTypeId,acctTypeNo,ttsCode,chnlId"></snow:form>
            <br/>
            <p>通道列表</p>
            <snow:grid dataset="showGridData" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridDetailId" fitcolumns="true" height="300" ></snow:grid>
            
            <snow:button id="btnDetail" dataset="galRoutMng" hidden="true"></snow:button>
      </snow:window> 
      <!-- 启用停用按钮 -->
      <snow:button id="btnStatusSub" dataset="galRoutMng" hidden="true"> </snow:button> 
      <script>
 /***********************************下拉框体选中时刷新表格里的数据**********************************************************/
           /*当交易类型的下拉框选中时 把3个下拉框中指取到传入到后台*/
           function galRoutMng_dataset_payTxnTypeId_onSelect(dropdown,record){
        	   if(record!=null){
       	    	   var payTxnTypeId=record.getValue("payTxnTypeId");
        	   } 
	            var acctTypeNo=galRoutMng_dataset.getValue("acctTypeNo");
	            var ttsCode=galRoutMng_dataset.getValue("ttsCode"); 
	            showGridData_dataset.setParameter("qpayTxnTypeId",payTxnTypeId);
	            showGridData_dataset.setParameter("qacctTypeNo",acctTypeNo);
	            showGridData_dataset.setParameter("qttsCode",ttsCode); 
	            showGridData_dataset.flushData(showGridData_dataset.pageIndex);
	            return record;
           } 
           /*当账户类型的下拉框选中时 把3个下拉框中指取到传入到后台*/
           function galRoutMng_dataset_acctTypeNo_onSelect(dropdown,record){
        	    var payTxnTypeId=galRoutMng_dataset.getValue("payTxnTypeId"); 
        	    if(record!=null){
        	    	var acctTypeNo=record.getValue("acctTypeNo");
        	    }
	            
	            var ttsCode=galRoutMng_dataset.getValue("ttsCode"); 
	            showGridData_dataset.setParameter("qpayTxnTypeId",payTxnTypeId);
	            showGridData_dataset.setParameter("qacctTypeNo",acctTypeNo);
	            showGridData_dataset.setParameter("qttsCode",ttsCode); 
	            showGridData_dataset.flushData(showGridData_dataset.pageIndex);
	            return record;
          } 
           /*当策略类型的下拉框选中时 把3个下拉框中指取到传入到后台*/
           function galRoutMng_dataset_ttsCode_onSelect(dropdown,record){
        	   
        	    var payTxnTypeId=galRoutMng_dataset.getValue("payTxnTypeId"); 
	            var acctTypeNo=galRoutMng_dataset.getValue("acctTypeNo");
	            if(record!=null){
	            	var ttsCode=record.getValue("ttsCode");
	            }
	            showGridData_dataset.setParameter("qpayTxnTypeId",payTxnTypeId);
	            showGridData_dataset.setParameter("qacctTypeNo",acctTypeNo);
	            showGridData_dataset.setParameter("qttsCode",ttsCode); 
	            showGridData_dataset.flushData(showGridData_dataset.pageIndex);
        	   return record;
          } 
 /*************************************新增窗口*****************************************/
           
         function btAdd_onClick(){
        	 showGridData_dataset.setParameter("qpayTxnTypeId","ABC");
        	   galRoutMng_dataset.setReadOnly(false);
        	   galRoutMng_dataset.setFieldReadOnly("routeId",false);
        	   galRoutMng_dataset.setFieldReadOnly("routeStat",false);
        	   showGridData_dataset.flushData(showGridData_dataset.pageIndex);
        	   window_addWindow.open();
           }
         /*  function window_addWindow_beforeOpen(dropDown,
       			dropDownDataset) {
        	 
        	} */
          //新增页面保存按钮选定时验证页面的数据格式
          function btSave_onClickCheck(){
        	  var routeStat = galRoutMng_dataset.getValue("routeStat");
        	  var payTxnTypeId = galRoutMng_dataset.getValue("payTxnTypeId");
        	  var acctTypeNo = galRoutMng_dataset.getValue("acctTypeNo");
        	  var ttsCode = galRoutMng_dataset.getValue("ttsCode");
        	  var chnlId = galRoutMng_dataset.getValue("chnlId");
        	  var pagyNo=showGridData_dataset.getValue("pagyNo");
        	  if(pagyNo==null||pagyNo==""){
        		  $.warn("此数据为无效数据，请检查配置！！");
        		  return false;
        	  }
        	  //设置交易类型编号
        	 galRoutMng_dataset.setParameter("payTxnTypeId",payTxnTypeId);
        	  //设置账户类型编号
        	 galRoutMng_dataset.setParameter("acctTypeNo",acctTypeNo);
        	  //设置策略类型编号
        	  galRoutMng_dataset.setParameter("ttsCode",ttsCode);
        	  //设置通道状态
        	  galRoutMng_dataset.setParameter("routeStat",routeStat);
        	  //设置渠道编号
        	  galRoutMng_dataset.setParameter("chnlId",chnlId);
        	  return true;
          }
           function btSave_postSubmit(btn){
        	   $.success("操作成功!",function(){
        		   window_addWindow.close();
        	   });
           }
          function window_addWindow_beforeClose(wmf) {
        	  var num = showGridData_dataset.getValue("pagyNo");
        	  
        	  galRoutMng_dataset.setParameter("payTxnTypeId","");
        	  //设置账户类型编号
        	  galRoutMng_dataset.setParameter("acctTypeNo","");
        	  showGridData_dataset.flushData(showGridData_dataset.pageIndex);
   		      galRoutMng_dataset.flushData(galRoutMng_dataset.pageIndex);
        	  //如果没有添加成功则清除页面的冗余数据
        	  galRoutMng_dataset.cancelRecord(); 
          }  
 /**************************** 修改窗口******************************************************/
         function btUpt_onClick(){
        	    btUpdate.setHidden(false);
        	    var payTxnTypeId=galRoutMng_dataset.getValue("payTxnTypeId"); 
	            var acctTypeNo=galRoutMng_dataset.getValue("acctTypeNo");
	            var ttsCode=galRoutMng_dataset.getValue("ttsCode");
	            showGridData_dataset.setParameter("qpayTxnTypeId",payTxnTypeId);
	            showGridData_dataset.setParameter("qacctTypeNo",acctTypeNo);
	            showGridData_dataset.setParameter("qttsCode",ttsCode); 
	            
        	    galRoutMng_dataset.setReadOnly(false);
        	    showGridData_dataset.setReadOnly(false);
        	    
        	    galRoutMng_dataset.setFieldReadOnly("routeId",true);
        	    galRoutMng_dataset.setFieldReadOnly("routeStat",true);
        	    var foo = galRoutMng_dataset.getValue("routeId");
        	    galRoutMng_dataset.setParameter("routeId",foo);
        	    showGridData_dataset.flushData(showGridData_dataset.pageIndex);
            	window_uptWindow.open();
           }
 
           function btUpdate_onClickCheck(){
         	  var payTxnTypeId = galRoutMng_dataset.getValue("payTxnTypeId");
         	  var acctTypeNo = galRoutMng_dataset.getValue("acctTypeNo");
         	  var ttsCode = galRoutMng_dataset.getValue("ttsCode");
         	  var chnlId = galRoutMng_dataset.getValue("chnlId");
         	  //设置交易类型编号
         	 galRoutMng_dataset.setParameter("payTxnTypeId",payTxnTypeId);
         	  //设置账户类型编号
         	 galRoutMng_dataset.setParameter("acctTypeNo",acctTypeNo);
         	  //设置策略类型编号
         	  galRoutMng_dataset.setParameter("ttsCode",ttsCode);
         	  //设置渠道编号
        	  galRoutMng_dataset.setParameter("chnlId",chnlId);
         	  return true;
           }
           
           function window_uptWindow_beforeOpen(){
        	   var routeStat = galRoutMng_dataset.getValue("routeStat");
        	   if(routeStat=="02" || routeStat=="99"){
        		   $.warn("该条数据的状态为未启用或是停用,不可以进行修改！！");
        		   return false;
        	   }
        	   showGridData_dataset.flushData(showGridData_dataset.pageIndex);
        	   return true;
           }
           
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        	//	   galRoutMng_dataset.setParameter("qpayTxnTypeId",record.getValue("payTxnTypeId"));
        		   window_uptWindow.close();
        		   galRoutMng_dataset.flushData(galRoutMng_dataset.pageIndex);
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	   galRoutMng_dataset.setParameter("payTxnTypeId","");
         	   //设置账户类型编号
         	   galRoutMng_dataset.setParameter("acctTypeNo","");
        	   galRoutMng_dataset.cancelRecord();
        	   showGridData_dataset.cancelRecord();
           } 
/************************************详情页面******************************************/
        function gridId_opr_onRefresh(record, fieldId, fieldValue){
      		if(record){
      			return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
      		}else{
      			return "&nbsp;";
      		}
      	 }
          function openDetail(){
        	  btnDetail.click();
        	  
          }
          function btnDetail_onClick(){
        	    btnDetail.setHidden(true);
        	    var payTxnTypeId=galRoutMng_dataset.getValue("payTxnTypeId"); 
	            var acctTypeNo=galRoutMng_dataset.getValue("acctTypeNo");
	            var ttsCode=galRoutMng_dataset.getValue("ttsCode");
	            showGridData_dataset.setParameter("qpayTxnTypeId",payTxnTypeId);
	            showGridData_dataset.setParameter("qacctTypeNo",acctTypeNo);
	            showGridData_dataset.setParameter("qttsCode",ttsCode); 
        	  galRoutMng_dataset.setReadOnly(true);
        	  showGridData_dataset.setReadOnly(true);
        	  var foo = galRoutMng_dataset.getValue("routeId");
        	  showGridData_dataset.setParameter("routeId",foo); 
        	  showGridData_dataset.flushData(showGridData_dataset.pageIndex);
        	  window_detailWindow.open();
          } 
          function window_detailWindow_beforeClose(wmf){
       	   galRoutMng_dataset.setParameter("payTxnTypeId","");
        	   //设置账户类型编号
        	   galRoutMng_dataset.setParameter("acctTypeNo","");
       	   galRoutMng_dataset.cancelRecord();
       	   showGridData_dataset.cancelRecord();
       	showGridData_dataset.flushData(showGridData_dataset.pageIndex);
          } 
 /*********************************************启用停用页面*******************************/
          function btStatus_onClick(){
        	  var routeId = galRoutMng_dataset.getValue("routeId");
        	  galRoutMng_dataset.setParameter("routeId",routeId);
        	  var dataState = galRoutMng_dataset.getValue("routeStat");
        	  var openDate = galRoutMng_dataset.getValue("openDate");
        	  galRoutMng_dataset.setParameter("openDate",openDate);
        	  var msg = "";
        	  if(dataState == "00"){
        		  msg = "是否要将该路由策略状态修改为【暂停启用】?";
        	  }else if(dataState == "02"){
        		  msg = "是否要将该路由策略状态修改为【启用】?";

        	  }else if(dataState == "99"){
        		  msg = "是否要将该路由策略状态修改为【启用】?";
        	  }
        	  $.confirm(msg, function() {
        		  galRoutMng_dataset.setParameter("routeStat",dataState);
        		  btnStatusSub.click();
        	  }, function() {
        		  return false;
        	  });
          }
          var btnStatusSub_postSubmit= function(){
        	  $.success("操作成功!", function(){
        		  galRoutMng_dataset.flushData(galRoutMng_dataset.pageIndex);
        	  });
          }
      </script>
</snow:page>