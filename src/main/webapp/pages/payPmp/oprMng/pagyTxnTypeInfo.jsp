<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="接入支付通道交易">
      <!-- 接入支付通道交易的dataset -->
      <snow:dataset id="pagyTxnTypeInfo" path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnTypeInfo" init="true" submitMode="current"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="pagyTxnTypeInfo" collapsible="false" fieldstr="qpayTxnTypeId,qpayTxnTypeName,qpayTxnTypeDesc"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="pagyTxnTypeInfo" height="99%" label="接入支付通道交易" fitcolumns="true" fieldstr="payTxnTypeId,payTxnTypeName,payTxnTypeDesc" paginationbar="btAdd,btUpt"></snow:grid>
      <!-- 新增页面 -->
      <snow:window id="addWindow" closable="true" width="900" title="接入支付通道交易新增" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="pagyTxnTypeInfo" border="false" fieldstr="payTxnTypeId,payTxnTypeName,payTxnTypeDesc"></snow:form>
            <br/>
            <snow:button id="btSave" dataset="pagyTxnTypeInfo" hidden="true"></snow:button>
      </snow:window>
      <snow:window id="uptWindow" closable="true" width="900" title="接入支付通道交易修改" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="pagyTxnTypeInfo" border="false" fieldstr="payTxnTypeId,payTxnTypeName,payTxnTypeDesc"></snow:form>
            <br/>
            <snow:button id="btUpdate" dataset="pagyTxnTypeInfo" hidden="true"></snow:button>
      </snow:window>
     
      <script>
           function btAdd_onClick(){
        	   pagyTxnTypeInfo_dataset.setReadOnly(false);
        	   pagyTxnTypeInfo_dataset.setFieldRequired("payTxnTypeId",true);
        	   pagyTxnTypeInfo_dataset.setFieldReadOnly("payTxnTypeId",false);
           	   window_addWindow.open();
           }
          function btSave_onClickCheck(){
        	  var payTxnTypeId = pagyTxnTypeInfo_dataset.getValue("payTxnTypeId");
        	  var payTxnTypeName = pagyTxnTypeInfo_dataset.getValue("payTxnTypeName");
        	  var payTxnTypeDesc= pagyTxnTypeInfo_dataset.getValue("payTxnTypeDesc");
        	  pagyTxnTypeInfo_dataset.setParameter("payTxnTypeId",payTxnTypeId);
        	  var reg=/^\d{3}$/;
        	  if(!reg.test(payTxnTypeId)){
      			   $.warn("通道交易编号为3位的数字!!");
      			   return false;
      		  }
    	       if(payTxnTypeName.length>21){
   			       $.warn("交易类型名称最长为21为的中文汉字!!");
   			       return false;
   		       }
    	       if(payTxnTypeDesc.length>42){
    	    	   $.warn("交易类型名称最长为42为的中文汉字!!");
     			     return false;
    	       }
    	       return true;
          }
           function btSave_postSubmit(){
        	   $.success("操作成功!",function(){
        		   
        		   window_addWindow.close();
        		   pagyTxnTypeInfo_dataset.flushData(pagyTxnTypeInfo_dataset.pageIndex);
        	   });
           }
            //防止没有增加成功数据显示在页面
           function window_addWindow_beforeClose(wmf){
            	//刷新dataset防止数据没添加影响页面
        	   pagyTxnTypeInfo_dataset.flushData(pagyTxnTypeInfo_dataset.pageIndex);
        	   pagyTxnTypeInfo_dataset.cancelRecord();
           }
           function btUpt_onClick(){
        	   pagyTxnTypeInfo_dataset.setReadOnly(false);
        	   pagyTxnTypeInfo_dataset.setFieldRequired("payTxnTypeId",false);
        	   pagyTxnTypeInfo_dataset.setFieldReadOnly("payTxnTypeId",true);
            	window_uptWindow.open();
           }
           function btUpdate_onClickCheck(){
        	   var payTxnTypeId = pagyTxnTypeInfo_dataset.getValue("payTxnTypeId");
        	   var payTxnTypeName = pagyTxnTypeInfo_dataset.getValue("payTxnTypeName");
         	   var payTxnTypeDesc= pagyTxnTypeInfo_dataset.getValue("payTxnTypeDesc");
         	   pagyTxnTypeInfo_dataset.setParameter("payTxnTypeId",payTxnTypeId);
         	   pagyTxnTypeInfo_dataset.setParameter("payTxnTypeName",payTxnTypeName);
         	   pagyTxnTypeInfo_dataset.setParameter("payTxnTypeDesc",payTxnTypeDesc);
     	       if(payTxnTypeName.length>21){
    			       $.warn("交易类型名称最长为21为的中文汉字!!");
    			       return false;
    		       }
     	       if(payTxnTypeDesc.length>42){
     	    	   $.warn("交易类型名称最长为42为的中文汉字!!");
      			     return false;
     	       }
    	       return true;
           }
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_uptWindow.close();
        		   pagyTxnTypeInfo_dataset.flushData(pagyTxnTypeInfo_dataset.pageIndex);
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	  //刷新dataset防止数据没添加影响页面
        	   pagyTxnTypeInfo_dataset.flushData(pagyTxnTypeInfo_dataset.pageIndex);
        	   pagyTxnTypeInfo_dataset.cancelRecord();
           }
      </script>
</snow:page>