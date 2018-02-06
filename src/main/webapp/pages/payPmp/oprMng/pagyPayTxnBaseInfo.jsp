<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="接入支付交易基础信息">
      <!-- 接入支付通道交易的dataset -->
      <snow:dataset id="pagyPayTxnBaseInfo"  path="com.ruimin.ifs.pmp.oprMng.dataset.pagyPayTxnBaseInfo" init="true" submitMode="current"></snow:dataset>
      <!-- 支付与通道交易关联的dataset -->
      <snow:dataset id="pagyPayTxnRel"  path="com.ruimin.ifs.pmp.oprMng.dataset.pagyPayTxnRel" init="false" submitMode="all"></snow:dataset>
      <!-- 支付通道交易基础信息的dataset -->
      <snow:dataset id="pagyTxnBaseInfo"  path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnBaseInfo" init="false" submitMode="current"></snow:dataset>
      <!-- 查询条件 -->
      <snow:query id="queryId" label="查询条件" dataset="pagyPayTxnBaseInfo" collapsible="false" 
      fieldstr="qpayTxnCode,qpayTxnResp,qpayTxnTypeId,qpayTxnStat"></snow:query>
      <!-- 显示表格 -->
      <snow:grid id="gridId" dataset="pagyPayTxnBaseInfo" height="99%" label="接入支付交易基础信息" fitcolumns="true" 
      fieldstr="payTxnCode,payTxnResp,payTxnTypeId,payTxnStat,opr" paginationbar="btAdd,btCfg,btStatus,btUpt,btnPagyCore"></snow:grid>
      <!-- 新增页面 -->
      <snow:window id="addWindow" closable="true" width="900" title="接入支付交易基础信息新增" modal="true" buttons="btSave">
            <snow:form id="addId" dataset="pagyPayTxnBaseInfo" border="false" fieldstr="txnNo,payTxnResp,payTxnTypeId,payTxnStat"></snow:form>
            <br/>
            <snow:button id="btSave" dataset="pagyPayTxnBaseInfo" hidden="true"></snow:button>
      </snow:window>
      <!-- 修改页面 -->
      <snow:window id="cfgWindow" closable="true" width="800" title="接入支付交易基础信息修改" modal="true" buttons="btCofig">
            <snow:form id="cfgId" dataset="pagyPayTxnBaseInfo" border="false" fieldstr="payTxnCode,payTxnResp,payTxnStat"></snow:form>
            <br/>
            <snow:button id="btCofig" dataset="pagyPayTxnBaseInfo" hidden="true"></snow:button>
      </snow:window> 
       <!-- 详情页面 -->
    <snow:window id="windowIdDetail" title="接入支付交易与通道交易和通道的配置详情" modal="true" closable="true" width="800">
         <snow:form id="formDetailId" dataset="pagyPayTxnBaseInfo" label="*" border="false" fieldstr="payTxnCode,payTxnResp,payTxnStat" collapsible="false" colnum="4"></snow:form>
         <br/>
         <snow:grid id="gridRoleDetailId" dataset="pagyPayTxnRel" height="300" fieldstr="pagyTxnCode[700]"></snow:grid>
    </snow:window>
    <!-- 配置页面 -->
      <snow:window id="uptWindow" closable="true" width="800" title="接入支付交易与通道交易和通道的配置" modal="true" buttons="btUpdate">
            <snow:form id="uptId" dataset="pagyPayTxnBaseInfo" border="false" fieldstr="payTxnCode,payTxnResp,payTxnStat"></snow:form>
            <br/>
            <snow:grid id="gridUpdId" dataset="pagyPayTxnRel" height="99%" label="支付与通道交易关系表" fitcolumns="true" 
            fieldstr="pagyTxnCode[700]" editable="true" toolbar="toolbarExt01"></snow:grid>
            <snow:toolbar id="toolbarExt01" >
                <snow:button id="btnAddSection01" dataset="pagyPayTxnRel" hidden="false"></snow:button>   
                <snow:button id="btnDeleteSection01" dataset="pagyPayTxnRel" hidden="false"></snow:button>
            </snow:toolbar> 
            <br/>
            <snow:button id="btUpdate" dataset="pagyPayTxnBaseInfo" hidden="true"></snow:button>
      </snow:window>
     <!-- 启用停用按钮 -->
      <snow:button id="btnStatusSub" dataset="pagyPayTxnBaseInfo" hidden="true"> </snow:button> 
      <snow:button id="btnShowDetail" dataset="pagyPayTxnBaseInfo" hidden="true"></snow:button>
      <script>
      /*********************************新增页面******************************************************/
           function btAdd_onClick(){
        	   pagyPayTxnBaseInfo_dataset.setReadOnly(false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnTypeId",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnTypeId",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnResp",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("txnNo",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("pagyTxnCode",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnStat",false);
           	   window_addWindow.open();
           }
          function btSave_onClickCheck(){
        	  var txnNo = pagyPayTxnBaseInfo_dataset.getValue("txnNo");
        	  var payTxnResp = pagyPayTxnBaseInfo_dataset.getValue("payTxnResp");
        	  pagyPayTxnBaseInfo_dataset.setParameter("txnNo",txnNo);
        	  pagyPayTxnBaseInfo_dataset.setParameter("payTxnResp",payTxnResp);
        	  var reg=/^\d{3}$/;
        	  if(!reg.test(txnNo)){
      			$.warn("交易序号只能为3位数字");
      			return false;
      		}
    	       if(payTxnResp.length>42){
    	    	   $.warn("交易名称最长为42为的中文汉字!!");
     			     return false;
    	       }
    	       return true;
          }
           function btSave_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_addWindow.close();
        	   });
           }
            //防止没有增加成功数据显示在页面
           function window_addWindow_beforeClose(wmf){
        	   //刷新dataset防止数据没添加影响页面
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("txnNo",false);
        	   pagyPayTxnBaseInfo_dataset.flushData(pagyPayTxnBaseInfo_dataset.pageIndex);
        	   pagyPayTxnBaseInfo_dataset.cancelRecord();
           }
/**************************************2个下拉框选择事件****************************************/
         //当交易类型编号下拉框刷新时,把数据传到后台
           function pagyPayTxnBaseInfo_dataset_payTxnTypeId_onSelect(dropdown,record){
	           if(record != null){
	        	   var payTxnTypeId = record.getValue("payTxnTypeId");
	           }
	           pagyPayTxnBaseInfo_dataset.setParameter("payTxnTypeId",payTxnTypeId);
	           return record;
           }
         //当通道交易编号下拉框刷新时,把数据传到后台
           function pagyPayTxnRel_dataset_pagyTxnCode_beforeOpen(dropdown,dropDownDataset){
	        	   dropDownDataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
	           return true;
           }
/*************************************修改页面****************************************************************/
           function btCfg_onClick(){
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("pagyTxnCode",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnTypeId",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnCode",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("txnNo",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnResp",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnResp",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnCode",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnStat",true);
           	   window_cfgWindow.open();
           }
            function window_cfgWindow_beforeOpen(){
        	   var payTxnCode = pagyPayTxnBaseInfo_dataset.getValue("payTxnCode");
          	   var payTxnResp = pagyPayTxnBaseInfo_dataset.getValue("payTxnResp");
          	   var payTxnStat = pagyPayTxnBaseInfo_dataset.getValue("payTxnStat");
          	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnCode",payTxnCode);
          	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnResp",payTxnResp);
          	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnStat",payTxnStat);
           	   return true; 
           }
           function btCofig_onClickCheck(){
         	   var payTxnResp = pagyPayTxnBaseInfo_dataset.getValue("payTxnResp");
         	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnResp",payTxnResp);
     	       if(payTxnResp.length>42){
     	    	   $.warn("交易名称最长为42为的中文汉字!!");
      			     return false;
     	       }
     	       return true;
           }
           function btCofig_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_cfgWindow.close();
        	   });
           }
            //防止没有修改成功数据显示在页面
           function window_cfgWindow_beforeClose(wmf){
        	   //刷新dataset防止数据没添加影响页面
        	   pagyPayTxnBaseInfo_dataset.flushData(pagyPayTxnBaseInfo_dataset.pageIndex);
        	   pagyPayTxnBaseInfo_dataset.cancelRecord();
           }
/*********************************配置页面******************************************************/
           function btUpt_onClick(){
        	   var payTxnCode = pagyPayTxnBaseInfo_dataset.getValue("payTxnCode");
        	   pagyPayTxnBaseInfo_dataset.setReadOnly(false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnTypeId",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("pagyTxnCode",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnResp",false);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnResp",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnCode",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnTypeId",true);
        	   pagyPayTxnBaseInfo_dataset.setFieldReadOnly("payTxnStat",true);
               window_uptWindow.open();
           }
           function window_uptWindow_beforeOpen(dropDown,
   				dropDownDataset){
        	   var payTxnCode = pagyPayTxnBaseInfo_dataset.getValue("payTxnCode"); 
        	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnCode",payTxnCode);
        	   pagyPayTxnRel_dataset.setParameter("payTxnCode",payTxnCode);
        	   pagyPayTxnRel_dataset.flushData(pagyPayTxnRel_dataset.pageIndex);
        	   return true;
           }
           function btUpdate_onClickCheck(){
        	   var hasRoleSelected = 0;
    			var roleRecord = pagyPayTxnRel_dataset.getFirstRecord();
    			var num = 0;
    			while(roleRecord){
    				roleRecord=roleRecord.getNextRecord();
    				num++;
    			}
    			if(num<1){
    				 $.warn("每个接入支付通道交易下最少有一条通道名称和通道交易名称");
    	      		 return false;
    			}
        	   var payTxnResp = pagyPayTxnBaseInfo_dataset.getValue("payTxnResp");
        	   pagyPayTxnBaseInfo_dataset.setParameter("payTxnResp",payTxnResp);
        	   
     	       if(payTxnResp.length>42){
     	    	   $.warn("交易类型最长为42为的中文汉字!!");
      			     return false;
     	       }
    	       return true;
           }
           function btUpdate_postSubmit(){
        	   $.success("操作成功!",function(){
        		   window_uptWindow.close();
        	   });
           }
           function window_uptWindow_beforeClose(wmf){
        	   //刷新dataset防止数据没添加影响页面
        	   pagyPayTxnBaseInfo_dataset.flushData(pagyPayTxnBaseInfo_dataset.pageIndex);
        	   pagyPayTxnBaseInfo_dataset.cancelRecord();
           }
/********************************************详情页面*****************************************************************/    
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
        	 pagyPayTxnBaseInfo_dataset.setReadOnly(true);
        	 var payTxnCode = pagyPayTxnBaseInfo_dataset.getValue("payTxnCode");
        	 pagyPayTxnRel_dataset.setParameter("payTxnCode",payTxnCode);
        	 pagyPayTxnRel_dataset.flushData(pagyPayTxnRel_dataset.pageIndex);
        	 window_windowIdDetail.open();
         }

/*********************************************启用停用页面*******************************/
           function btStatus_onClick(){
        	   pagyPayTxnBaseInfo_dataset.setFieldRequired("payTxnTypeId",false);
         	  var payTxnCode = pagyPayTxnBaseInfo_dataset.getValue("payTxnCode");
         	  pagyPayTxnBaseInfo_dataset.setParameter("payTxnCode",payTxnCode);
         	  var payTxnStat = pagyPayTxnBaseInfo_dataset.getValue("payTxnStat");
         	  var msg = "";
         	  if(payTxnStat == "00"){
         		  msg = "是否要将该交易状态修改为【停用】?";
         		  payTxnStat = "99";
         	  }else if(payTxnStat == "02"){
         		  msg = "是否要将该交易状态修改为【启用】?";
         		  payTxnStat = "00";
         	  }else if(payTxnStat == "99"){
         		  msg = "是否要将该交易状态修改为【启用】?";
         		  payTxnStat = "00";
         	  }
         	  $.confirm(msg, function() {
         		 pagyPayTxnBaseInfo_dataset.setParameter("payTxnStat",payTxnStat);
         		  btnStatusSub.click();
         	  }, function() {
         		  return false;
         	  });
           }
           var btnStatusSub_postSubmit= function(){
         	  $.success("操作成功!", function(){
         		 pagyPayTxnBaseInfo_dataset.flushData(pagyPayTxnBaseInfo_dataset.pageIndex);
         	  });
           }
           
           
           /****************************************（如需要）返回通道核心配置页面,且查询需配置记录******************************************/
        	function initCallGetter_post() {
        		var pagyCoreFlag = <%=StringUtil.filtrateSpecialCharater(request.getParameter("pagyCoreFlag")) %>;
        		var param = <%= StringUtil.filtrateSpecialCharater(request.getParameter("param")) %>;
        		var updFlag = <%=StringUtil.filtrateSpecialCharater(request.getParameter("updFlag")) %>;
        		if(pagyCoreFlag == null){
        			btnPagyCore.setHidden(true);
        		}
        		if(param){
        			if(updFlag){
        				pagyPayTxnBaseInfo_dataset.setParameter("qpayTxnCode",param);
        			}			
        			pagyPayTxnBaseInfo_dataset.flushData(pagyPayTxnBaseInfo_dataset.pageIndex);
        		}
       		}
           
        	function btnPagyCore_onClick(){
        		window.location.href='../oprMng/pagyCoreMng.jsp';
        	}
      </script>
</snow:page>