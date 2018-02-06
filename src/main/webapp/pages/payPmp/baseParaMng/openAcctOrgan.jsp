<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="开户机构">
      <!-- 开户机构的dataset -->
      <snow:dataset id="openAcctOrgan" path="com.ruimin.ifs.pmp.baseParaMng.dataset.openAcctOrgan" init="true" submitMode="current"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="openAcctOrgan" collapsible="false" fieldstr="qacctInstNo,qacctInstName"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="openAcctOrgan" height="99%" label="开户信息" fitcolumns="true" fieldstr="acctInstNo,acctInstName" paginationbar="btAdd,btUpt,btDelete,btImport"></snow:grid>
      <!-- 新增页面 -->
      <snow:window id="addWindow" closable="true" width="900" title="开户新增" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="openAcctOrgan" border="false" fieldstr="acctInstNo,acctInstName"></snow:form>
            <br/>
            <snow:button id="btSave" dataset="openAcctOrgan" hidden="true"></snow:button>
      </snow:window>
      <snow:window id="uptWindow" closable="true" width="900" title="开户修改" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="openAcctOrgan" border="false" fieldstr="acctInstNo,acctInstName"></snow:form>
            <br/>
            <snow:button id="btUpdate" dataset="openAcctOrgan" hidden="true"></snow:button>
      </snow:window>
      <!-- 删除按钮 -->
      <snow:button id="btnDelete" dataset="openAcctOrgan" hidden="true"> </snow:button>
     
      <script>
           function btAdd_onClick(){
        	   openAcctOrgan_dataset.setReadOnly(false);
           	   openAcctOrgan_dataset.setFieldReadOnly("acctInstNo",false);
           	   window_addWindow.open();
           }
          function btSave_onClickCheck(){
        	  var acctInstNo = openAcctOrgan_dataset.getValue("acctInstNo");
        	  var acctInstName= openAcctOrgan_dataset.getValue("acctInstName");
             	var temp=/^\d+(\.\d+)?$/;
    	       if(!temp.test(acctInstNo)){
   			    $.warn("开户机构编码请输入数字");
   			     return false;
   		       }
    	       if(acctInstNo.length>12){
    	    	   $.warn("开户机构编码不能大于12位");
     			     return false;
    	       }
    	       if(acctInstName.length>42){
    	    	   $.warn("开户机构编码不能大于42位的汉字");
     			     return false;
    	       }
    	       return true;
          }
           function btSave_postSubmit(){
        	   $.success("操作成功!",function(){
        		   
        		   window_addWindow.close();
        		   openAcctOrgan_dataset.flushData(openAcctOrgan_dataset.pageIndex);
        	   });
           }
            //防止没有增加成功数据显示在页面
           function window_addWindow_beforeClose(wmf){
        	   openAcctOrgan_dataset.cancelRecord();
           }
           function btUpt_onClick(){
            	openAcctOrgan_dataset.setReadOnly(false);
            	openAcctOrgan_dataset.setFieldReadOnly("acctInstNo",true);
            	window_uptWindow.open();
           }
           function btUpdate_onClickCheck(){
        	   var acctInstName= openAcctOrgan_dataset.getValue("acctInstName");
        	   if(acctInstName.length>42){
    	    	   $.warn("开户机构编码不能大于42位的汉字");
     			     return false;
    	       }
    	       return true;
           }
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_uptWindow.close();
        		   openAcctOrgan_dataset.flushData(openAcctOrgan_dataset.pageIndex);
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	   openAcctOrgan_dataset.cancelRecord();
           }
           function btDelete_onClick(){
        	   var foo = openAcctOrgan_dataset.getValue("acctInstNo");
        	   $.confirm("是否确认删除该机构?注:一经删除无法恢复!!!", function() {
        		   openAcctOrgan_dataset.setParameter("foo",foo);
        		   btnDelete.click();
        	   },function(){
        		   return false;
        	   });
           }
           function btnDelete_postSubmit(){
        	   $.success("操作成功!",function(){
        		   openAcctOrgan_dataset.flushData(openAcctOrgan_dataset.pageIndex);
        	   });
           }
           var iscallback = false;
           function btImport_onClick(){
        	   iscallback = false;
        	   $.open("importFile", '文件导入', "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_OPENORG_LST%>", "750", "280", false, true, null, false,"关闭");
           }
           function importFile_onButtonClick(i,win,framewin){
        	   if(i==0){
        		   win.close();
        		   openAcctOrgan_dataset.flushData(openAcctOrgan_dataset.pageIndex);
        	   }else{
        		   framewin.excuteImport();
        		   importFileCallBack();
        	   }
           }
           function importFileCallBack(){
   			iscallback = true;
   			openAcctOrgan_dataset.flushData(openAcctOrgan_dataset.pageIndex);			
   		}
      </script>
</snow:page>