<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="银联卡表维护">
      <!-- 银联卡表维护的dataset -->
      <snow:dataset id="bankBin" path="com.ruimin.ifs.pmp.baseParaMng.dataset.bankBin" init="true" submitMode="current"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="bankBin" collapsible="false" fieldstr="qissueOrgId,qcardBinNo,qcardType"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="bankBin" height="99%" label="银联卡表信息" fitcolumns="true" fieldstr="seqNo,issueOrgId,issueName,cardType,cardNoLen,cardBinNo,internalAcctType,opr" paginationbar="btUpt,btDelete,btImport"></snow:grid>
      <!-- 修改页面 -->
      <snow:window id="uptWindow" closable="true" width="900" title="银联卡表修改" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="bankBin" border="false" fieldstr="seqNo,issueOrgId,issueName,cardType,cardNoLen,cardBinNo,cupFlag,dataSource,internalAcctType"></snow:form>
            <br/>
            <snow:button id="btUpdate" dataset="bankBin" hidden="true"></snow:button>
      </snow:window>
       <!-- 详情页面 -->
      <snow:window id="uptWindow1" closable="true" width="900" title="银联卡表详情" modal="true" buttons="btnDetail">
            <snow:form id="uptId" dataset="bankBin" border="false" fieldstr="seqNo,issueOrgId,issueName,cardType,cardNoLen,cardBinNo,cupFlag,dataSource,internalAcctType"></snow:form>
            <br/>
            <snow:button id="btnDetail" dataset="bankBin" hidden="true"></snow:button>
      </snow:window>
      <!-- 删除按钮 -->
      <snow:button id="btnDelete" dataset="bankBin" hidden="true"> </snow:button>
    
      <script>
           
           function btUpt_onClick(){
            	bankBin_dataset.setReadOnly(false);
            	bankBin_dataset.setFieldReadOnly("seqNo",true);
            	btUpdate.setHidden(false);
            	if(bankBin_dataset.getValue("cardType") == '03'){
            		bankBin_dataset.setFieldReadOnly("internalAcctType",true);
            		bankBin_dataset.setFieldRequired("internalAcctType",false);
            	}else{
            		bankBin_dataset.setFieldReadOnly("internalAcctType",false);
            		bankBin_dataset.setFieldRequired("internalAcctType",true);
            	}
            	window_uptWindow.open();
           }
           
           function btUpdate_onClickCheck(){
        	   var issueOrgId = bankBin_dataset.getValue("issueOrgId");
        	   var cardNoLen = bankBin_dataset.getValue("cardNoLen");
        	   var cardBinNo = bankBin_dataset.getValue("cardBinNo");
        	   var temp=/^\d+(\.\d+)?$/;
        	   if(!temp.test(issueOrgId)){
       			    $.warn("发卡行机构编码请输入数字");
       			     return false;
       		   }
        	   
        	   if(!temp.test(cardNoLen)){
          			$.warn("卡号长度请输入数字");
          			return false;
          	   }
        	   if(!temp.test(cardBinNo)){
          			$.warn("卡BIN号请输入数字");
          			return false;
          		}
        	   return true;
           }
           
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_uptWindow.close();
        		   bankBin_dataset.flushData(bankBin_dataset.pageIndex);
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	   bankBin_dataset.cancelRecord();
           }
           function btDelete_onClick(){
        	   bankBin_dataset.setFieldRequired("internalAcctType",false);
        	   var foo = bankBin_dataset.getValue("seqNo");
        	   $.confirm("是否确认删除该记录?注:一经删除无法恢复!!!", function() {
        		   bankBin_dataset.setParameter("foo",foo);
        		   btnDelete.click();
        	   },function(){
        		   return false;
        	   });
           }
           function btnDelete_postSubmit(){
        	   $.success("操作成功!",function(){
        		   bankBin_dataset.flushData(bankBin_dataset.pageIndex);
        	   });
           }
           var iscallback = false;
           function btImport_onClick(){
        	   iscallback = false;
        	   $.open("importFile", '文件导入', "/pages/payPmp/pubTool/fileUpload.jsp?type=<%=SnowConstant.FILE_BANK_BIN2_LST%>", "750", "280", false, true, null, false,"确定,关闭");
           }
           function importFile_onButtonClick(i,win,framewin){
        	   if(i==1){
        		   win.close();
        		   bankBin_dataset.flushData(bankBin_dataset.pageIndex);
        	   }else if(i==0){
        		   if(iscallback){
        			   win.close();
        			   bankBin_dataset.flushData(bankBin_dataset.pageIndex);
        		   }else{
            		   framewin.excuteImport();
            		   importFileCallBack();
            	   }
        	   }
           }
          function importFileCallBack(){
   			iscallback = true;
   			bankBin_dataset.flushData(bankBin_dataset.pageIndex);			
   		  }
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
        	  bankBin_dataset.setReadOnly(true);
        	  btnDetail.setHidden(true);
        	  window_uptWindow1.open();
          }
      </script>
</snow:page>