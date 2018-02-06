<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道路由策略管理">
      <!-- 路由策略的dataset -->
      <snow:dataset id="galRoutTactMng" path="com.ruimin.ifs.pmp.chnlMng.dataset.galRoutTactMng" init="true" submitMode="current"></snow:dataset>
      <!-- 通道信息的dataset -->
      <snow:dataset id="galBasicInfo" path="com.ruimin.ifs.pmp.chnlMng.dataset.galBasicInfo" init="false" submitMode="all"></snow:dataset>
      <!-- 策略关联表的dataset -->
      <snow:dataset id="tactAndBasic" path="com.ruimin.ifs.pmp.chnlMng.dataset.tactAndBasic" init="false" submitMode="current"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="galRoutTactMng" collapsible="false" fieldstr="qttsCode,qttsResp,qttsType,qttsStat"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="galRoutTactMng" height="99%" label="通道路由策略管理" fitcolumns="true" fieldstr="ttsCode,ttsResp,ttsType,ttsStat,opr" paginationbar="btAdd,btUpt,btStatus"></snow:grid>
      <!-- 新增页面 -->
    <snow:window id="addWindow" closable="true" width="900" title="路由策略新增" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="galRoutTactMng" border="false" fieldstr="ttsResp,ttsType,ttsStat"></snow:form>
            <br/>
            <p>通道列表</p>
         <snow:grid dataset="galBasicInfo" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridAddId" fitcolumns="true" height="300" editable="true" toolbar="toolbarExt01"></snow:grid>
            <snow:toolbar id="toolbarExt01" >
                <snow:button id="btnAddSection01" dataset="galBasicInfo" hidden="false"></snow:button>   
                <snow:button id="btnDeleteSection01" dataset="galBasicInfo" hidden="false"></snow:button>
            </snow:toolbar> 
            <snow:button id="btSave" dataset="galRoutTactMng" hidden="true"></snow:button>
      </snow:window> 
      <!-- 修改页面 -->
       <snow:window id="uptWindow" closable="true" width="900" title="路由策略修改" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="galRoutTactMng" border="false" fieldstr="ttsCode,ttsResp,ttsType,ttsStat"></snow:form>
            <br/>
            <p>通道列表</p>
            <snow:grid dataset="galBasicInfo" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridUpdId" fitcolumns="true" height="300" editable="true" toolbar="toolbarExt02"></snow:grid>
            <snow:toolbar id="toolbarExt02" >
                <snow:button id="btnAddSection02" dataset="galBasicInfo" hidden="false"></snow:button>   
                <snow:button id="btnDeleteSection02" dataset="galBasicInfo" hidden="false"></snow:button>
            </snow:toolbar>
            <snow:button id="btUpdate" dataset="galRoutTactMng" hidden="true"></snow:button>
      </snow:window> 
      <!-- 详情页面 -->
       <snow:window id="detailWindow" closable="true" width="900" title="路由策略详情" modal="true" buttons="btnDetail">
            <snow:form id="detailId" dataset="galRoutTactMng" border="false" fieldstr="ttsCode,ttsResp,ttsType,ttsStat"></snow:form>
            <br/>
            <p>通道列表</p>
            <snow:grid dataset="galBasicInfo" fieldstr="pagyNo[300],priorityVul[300]" 
            id="gridDetailId" fitcolumns="true" height="300" ></snow:grid>
            
            <snow:button id="btnDetail" dataset="galRoutTactMng" hidden="true"></snow:button>
      </snow:window> 
      <!-- 启用停用按钮 -->
      <snow:button id="btnStatusSub" dataset="galRoutTactMng" hidden="true"> </snow:button>
      <script>
 /*************************************新增窗口*****************************************/
           function btAdd_onClick(){
        	   galRoutTactMng_dataset.setReadOnly(false);
        	   galRoutTactMng_dataset.setFieldReadOnly("ttsCode",false);
        	   galRoutTactMng_dataset.setFieldReadOnly("ttsStat",false);
        	   galBasicInfo_dataset.setParameter("param","add");
        	 //仅仅用于刷新数据使用
        	   galBasicInfo_dataset.setParameter("ttsCode","ABC");
        	   galBasicInfo_dataset.flushData(galBasicInfo_dataset.pageIndex);
        	   window_addWindow.open();
           }
          function gridAddId_dataset_pagyNo_beforeOpen(dropDown,
       			dropDownDataset) {
        	  
        	   galBasicInfo_dataset.flushData(galBasicInfo_dataset.pageIndex);
       	}
          function btSave_onClickCheck(){
        	  var result = galBasicInfo_dataset.getFirstRecord();
        	  var ttsResp = galRoutTactMng_dataset.getValue("ttsResp");
        	  var pagyNo = galBasicInfo_dataset.getValue("pagyNo");
        	  if(pagyNo.length==0){
   			    $.warn("新增时通道列表的数量最少为1条");
   			     return false;
   		      }
        	  if(ttsResp.length>42){
        		  $.warn("路由策略长度不能超过42位");
        		  return false;
        	  }
        	  while(result){
            	  var priorityVul = result.getValue("priorityVul"); 
            	  var temp=/^[1-9][\d]{0,1}$/;   
            	  if(!temp.test(priorityVul) && priorityVul!=null){
         			    $.warn("优先级只能输入最大为2位的数字，并且1为优先级最高,而且第一位为1-9");
         			     return false;
         		  }
            	  result = result.getNextRecord();
        	  }
        	  return true;
          }
           function btSave_postSubmit(btn){
        	   $.success("操作成功!",function(){
        		   var pagyNo = galBasicInfo_dataset.getParameter("pagyNo");
        		   galBasicInfo_dataset.setParameter("pagyNo",pagyNo);
        		   window_addWindow.close();
        		   galRoutTactMng_dataset.flushData(galRoutTactMng_dataset.pageIndex);
        	   });
           }
           
          function window_addWindow_beforeClose(wmf) {
        	  //如果没有添加成功则清除页面的冗余数据
        	  galRoutTactMng_dataset.cancelRecord(); 
        	  galBasicInfo_dataset.cancelRecord();
          } 
 /**************************** 修改窗口******************************************************/
           function btUpt_onClick(){
        	    galRoutTactMng_dataset.setReadOnly(false);
        	    galBasicInfo_dataset.setReadOnly(false);
        	    galRoutTactMng_dataset.setFieldReadOnly("ttsCode",true);
        	    galRoutTactMng_dataset.setFieldReadOnly("ttsStat",true);
        	    var foo = galRoutTactMng_dataset.getValue("ttsCode");
        	    galBasicInfo_dataset.setParameter("ttsCode",foo);
        	    galBasicInfo_dataset.flushData(galBasicInfo_dataset.pageIndex);
            	
            	window_uptWindow.open();
           }
           function window_uptWindow_beforeOpen(){
        	   var ttsStat = galRoutTactMng_dataset.getValue("ttsStat");
        	        if(ttsStat=="02" || ttsStat=="99"){
        		   $.warn("该条数据的状态为未启用或是停用,不可以进行修改！！");
        		   return false;
        	   }
        	   return true;
           }
           function btUpdate_onClickCheck(){
        	   var result = galBasicInfo_dataset.getFirstRecord();
         	  var ttsResp = galRoutTactMng_dataset.getValue("ttsResp");
         	  var pagyNo = galBasicInfo_dataset.getValue("pagyNo");
         	  if(pagyNo.length==0){
    			    $.warn("新增时通道列表的数量最少为1条");
    			     return false;
    		      }
         	  if(ttsResp.length>42){
         		  $.warn("路由策略长度不能超过42位");
         		  return false;
         	  }
         	  while(result){
             	  var priorityVul = result.getValue("priorityVul"); 
             	  var temp=/^[1-9][\d]{0,1}$/;
             	  if(!temp.test(priorityVul) && priorityVul!=null){
          			    $.warn("优先级只能输入最大为2位的数字，并且1为优先级最高,而且第一位为1-9");
          			     return false;
          		  }
             	  result = result.getNextRecord();
         	  }
         	  return true;
           }
           
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_uptWindow.close();
        		   galRoutTactMng_dataset.flushData(galRoutTactMng_dataset.pageIndex);
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	   galRoutTactMng_dataset.cancelRecord();
        	   galRoutTactMng_dataset.flushData(galRoutTactMng_dataset.pageIndex);
        	   galBasicInfo_dataset.cancelRecord();
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
        	  galRoutTactMng_dataset.setReadOnly(true);
        	  galBasicInfo_dataset.setReadOnly(true);
        	  var foo = galRoutTactMng_dataset.getValue("ttsCode");
      	      galBasicInfo_dataset.setParameter("ttsCode",foo); 
    	    galBasicInfo_dataset.flushData(galBasicInfo_dataset.pageIndex);
        	  window_detailWindow.open();
          }
 /*********************************************启用停用页面*******************************/
          function btStatus_onClick(){
        	  var dataState = galRoutTactMng_dataset.getValue("ttsStat");
        	  var msg = "";
        	  if(dataState == "00"){
        		  msg = "是否要将该路由策略状态修改为【停用】?";
        		  dataState = "99";
        	  }else if(dataState == "02"){
        		  msg = "是否要将该路由策略状态修改为【启用】?";
        		  dataState = "00";
        	  }else if(dataState == "99"){
        		  msg = "是否要将该路由策略状态修改为【启用】?";
        		  dataState = "00";
        	  }
        	  $.confirm(msg, function() {
        		  galRoutTactMng_dataset.setParameter("ttsStat",dataState);
        		  btnStatusSub.click();
        	  }, function() {
        		  return false;
        	  });
          }
          var btnStatusSub_postSubmit= function(){
        	  $.success("操作成功!", function(){
        		  galRoutTactMng_dataset.flushData(galRoutTactMng_dataset.pageIndex);
        	  });
          }
      </script>
</snow:page>