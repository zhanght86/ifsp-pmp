<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="交易管理">
    <!-- 交易类型的dataset -->
    <snow:dataset id="tradeType" path="com.ruimin.ifs.pmp.sysConf.dataset.tradeType" init="true" submitMode="current"></snow:dataset>
    <!-- 交易类型与账户类型关联的dataset -->
    <snow:dataset  id="acctAndTrade" path="com.ruimin.ifs.pmp.sysConf.dataset.acctAndTrade" init="false" submitMode="all"></snow:dataset>
    <!-- 账户类型的dataset -->
    <snow:dataset  id="acctType" path="com.ruimin.ifs.pmp.sysConf.dataset.acctType" init="false" parameters="apprFlag=1"></snow:dataset>
    <!-- 查询条件 -->
    <snow:query id="queryId" label="查询条件" dataset="tradeType" collapsible="false" fieldstr="qtxnTypeCode,qtxnTypeName"></snow:query>
    <!-- 显示表格 -->
    <snow:grid dataset="tradeType" height="99%" label="交易类型" id="gridId" fitcolumns="true" fieldstr="txnTypeCode,txnTypeName,dataState,txnTypeDesc,opr" paginationbar="btAdd,btUpt,btStatus"></snow:grid>
    
    <!-- 新增页面 -->
    <snow:window id="win" closable="true" width="800" title="新增交易类型" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="tradeType" border="false" 
            fieldstr="txnTypeName,txnTypeDesc"></snow:form>
            <br/>
            <snow:grid id="gridAcctId" dataset="acctType"  fitcolumns="true" fieldstr="select,acctTypeNo[300],acctTypeName[400]" height="300" ></snow:grid>
            <snow:button id="btSave" dataset="tradeType" hidden="true"></snow:button>
    </snow:window>
    
    <!-- 修改页面 -->
    <snow:window id="winupdate" closable="true" width="800" title="修改交易类型" modal="true" buttons="btUpdate">
          <snow:form id="updId" dataset="tradeType" border="false" 
          fieldstr="txnTypeName,txnTypeDesc"></snow:form>
          <br/>
          <snow:grid id="gridAcctAndId" dataset="acctAndTrade"  fitcolumns="true" height="300" editable="true" toolbar="toolbarExt01"
          fieldstr="acctTypeNo[700]" ></snow:grid>
          <snow:toolbar id="toolbarExt01" >
                <snow:button id="btnAddSection01" dataset="acctAndTrade" hidden="false"></snow:button>   
                <snow:button id="btnDeleteSection01" dataset="acctAndTrade" hidden="false"></snow:button>
            </snow:toolbar> 
          <snow:button id="btUpdate" dataset="tradeType" hidden="true"></snow:button>
    </snow:window>
    
    <!-- 详情页面 -->
    <snow:window id="windowIdDetail" title="交易类型详情" modal="true" closable="true" width="800">
         <snow:form id="formDetailId" dataset="tradeType" label="*" border="false" fieldstr="txnTypeCode,txnTypeName,txnTypeDesc" collapsible="false" colnum="4"></snow:form>
         <br/>
         <snow:grid id="gridRoleDetailId" dataset="acctType" height="300" fieldstr="acctTypeNo[300],acctTypeName[427]"></snow:grid>
    </snow:window>
    <!-- 删除页面 -->
    <snow:button id="btnStatusSub" dataset="tradeType" hidden="true"></snow:button>
    <snow:button id="btnShowDetail" dataset="tradeType" hidden="true"></snow:button>
    
    <script language="javascript">
         function btAdd_onClick(){
        	 tradeType_dataset.setReadOnly(false);
        	 tradeType_dataset.setFieldReadOnly("txnTypeCode",false);
        	 var foo = tradeType_dataset.getValue("txnTypeCode");
        	 acctType_dataset.setParameter("txnTypeCode",foo);
        	 acctType_dataset.setParameter("param","add");
        	 acctType_dataset.flushData(acctType_dataset.pageIndex);
        	 window_win.open();
         }
        
         /* function gridAcctId_select_onRefresh(record, fieldId, fieldValue){
   			if(record){
   				var acctTypeNo = record.getValue("acctTypeNo");
   				var select = record.getValue("select");
   				var returnSelect = returnPara.split(",");
   				for (var i = 0; i < returnSelect.length; i++) {
  					if(returnSelect[i] == acctTypeNo){
  						return "<>"
  					}
  				}
  				return ""; 
   			} 
   		} */
         
         function btUpdate_onClickCheck(){
 			var hasRoleSelected = 0;
 			var roleRecord = acctAndTrade_dataset.getFirstRecord();
 			var num = 0;
 			while(roleRecord){
 				roleRecord=roleRecord.getNextRecord();
 				num++;
 			}
 			if(num<1){
 				 $.warn("每个交易类型下最少有一个账户类型");
 	      		 return false;
 			}
 			var txnTypeCodeList = [];
 			var txnTypeCode= tradeType_dataset.getValue("txnTypeCode");
 			var txnTypeName = tradeType_dataset.getValue("txnTypeName");
 			
  			 var txnTypeDesc = tradeType_dataset.getValue("txnTypeDesc");
 			tradeType_dataset.setParameter("txnTypeCode",txnTypeCode);
 			
 			/* while(roleRecord){
 				var v_selected = roleRecord.getValue("select");
 				if( v_selected == true ){
 					txnTypeCodeList.push(roleRecord.getValue("acctTypeNo"));
 					hasRoleSelected ++ ;
 				}
 				roleRecord=roleRecord.getNextRecord();
 		   	}
 		   	if (hasRoleSelected<1) {
 		   		$.warn("至少选择一个账户类型");
 		   		return false;
 		   	} */
 		   if(txnTypeName.length>21){
      		 $.warn("交易类型名称最多为21位数字");
      		 return false;
      	 }
      	
      	 if(txnTypeDesc.length>42){
      		 $.warn("交易描述名称最多为42位数字");
      		 return false;
      	 }
 			
 			tradeType_dataset.setParameter("txnTypeCode", txnTypeCode);
 			tradeType_dataset.setParameter("s", txnTypeCodeList.join(","));
 			return true;
 		}
         function btSave_onClickCheck(){
  			var hasRoleSelected = 0;
  			var roleRecord = acctType_dataset.getFirstRecord();
  			var txnTypeCodeList = [];
  			var txnTypeCode= tradeType_dataset.getValue("txnTypeCode");
  			var txnTypeName = tradeType_dataset.getValue("txnTypeName");
  			var txnTypeDesc = tradeType_dataset.getValue("txnTypeDesc");
  			tradeType_dataset.setParameter("txnTypeName",txnTypeName);
  			
  			while(roleRecord){
  				var v_selected = roleRecord.getValue("select");
  				if( v_selected == true ){
  					txnTypeCodeList.push(roleRecord.getValue("acctTypeNo"));
  					hasRoleSelected ++ ;
  				}
  				roleRecord=roleRecord.getNextRecord();
  		   	}
  		   	if (hasRoleSelected<1) {
  		   		$.warn("至少选择一个账户类型");
  		   		return false;
  		   	}
  		  if(txnTypeName.length>21){
     		 $.warn("交易类型名称最多为21位数字");
     		 return false;
     	 }
     	
     	 if(txnTypeDesc.length>42){
     		 $.warn("交易描述名称最多为42位数字");
     		 return false;
     	 }
  			tradeType_dataset.setParameter("txnTypeCode", txnTypeCode);
  			tradeType_dataset.setParameter("s", txnTypeCodeList.join(","));
  			return true;
  		}
         
         function btSave_postSubmit(btn){
        	 $.success("操作成功!",function(){
        		 window_win.close();
        		 tradeType_dataset.flushData(tradeType_dataset.pageIndex);
        	 });
         }
         
         function window_win_beforeClose(wmf) {
         	/* 数据集的操作， 一般DataSetName_dataset.cancelRecord()和beforeClose()搭配使用 */
     			tradeType_dataset.cancelRecord();
     		}

         
         function gridId_opr_onRefresh(record,fieldId,fieldValue){
        	 if(record){
        		 return "<span style='width:100%;text-align:center' class='fa fa_list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
        	 }else{
        		 return "&nbsp;";
        	 }
         }
         
         
         
         function openDetail(){
        	 btnShowDetail.click();
    	 }
         function btnShowDetail_onClick(){
        	 tradeType_dataset.setReadOnly(true);
        	 var foo = tradeType_dataset.getValue("txnTypeCode");
        	 acctType_dataset.setParameter("txnTypeCode",foo);
        	 acctType_dataset.setParameter("param","detail");
        	 acctType_dataset.flushData(acctType_dataset.pageIndex);
        	 window_windowIdDetail.open();
         }
         function btUpt_onClick(btn,param){
        	 tradeType_dataset.setReadOnly(false);
        	 //获取交易类型编号
        	 var foo = tradeType_dataset.getValue("txnTypeCode");
        	 acctAndTrade_dataset.setParameter("txnTypeCode",foo);
        	 tradeType_dataset.setParameter("txnTypeCode",foo);
        	 acctType_dataset.setParameter("txnTypeCode",foo);
     //   	 acctType_dataset.setParameter("param","update");
        	 var fee = acctType_dataset.getValue("acctTypeNo");
        	 tradeType_dataset.setParameter("acctTypeNo",fee);
        	 acctAndTrade_dataset.flushData(acctAndTrade_dataset.pageIndex);
        	 window_winupdate.open();
        	 
         }
         function window_winupdate_beforeOpen(){
        	 var dataState = tradeType_dataset.getValue("dataState");
        	 var foo = tradeType_dataset.getValue("txnTypeCode");
        	 acctAndTrade_dataset.setParameter("txnTypeCode",foo);
        	// acctAndTrade_dataset.setParameter("param","update");
        	 if(dataState=='99'){
        		 $.warn("该条数据状态被设置为停用,不可以修改");
        		 return false;
        	 }
        	 acctAndTrade_dataset.flushData(acctType_dataset.pageIndex);
        	 return true;
         }
        
         function btUpdate_postSubmit(btn){
        	 $.success("操作成功!",function(){
        		 window_winupdate.close();
        		 tradeType_dataset.flushData(tradeType_dataset.pageIndex);
        	 });
         }
         function window_winupdate_beforeClose(wmf){
        	 tradeType_dataset.cancelRecord();
         }
     
         function btStatus_onClick(){
				var dataState = tradeType_dataset.getValue("dataState");
				var msg = "";
				if(dataState == "99"){
					msg = "是否要将该交易类型状态修改为【启用】?";
					dataState = "00";
				}else{
					msg = "是否要将该交易类型状态修改为【停用】?";
					dataState = "99";
				}
				$.confirm(msg, function() {
					tradeType_dataset.setParameter("dataState",dataState);
					btnStatusSub.click();
		        }, function() {
		        	return false;
		        });
			
		  }
         var btnStatusSub_postSubmit= function(){
        		$.success("操作成功！", function(){
        			tradeType_dataset.flushData(tradeType_dataset.pageIndex);
        		});
        	}; 



    </script>
    
    
    
</snow:page>