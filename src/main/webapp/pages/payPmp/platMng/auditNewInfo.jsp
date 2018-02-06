<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="审核消息列表">
     <!-- 审核消息表的dataset -->
     <snow:dataset id="auditNewInfo" path="com.ruimin.ifs.pmp.platMng.dataset.auditNewInfo" init="true" submitMode="current" ></snow:dataset>
     <!-- 审核消息记录表的dataset -->
     <snow:dataset id="auditNewRecordInfo" path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" init="false" submitMode="current" ></snow:dataset> 
     <!-- 查询条件 -->
     <snow:query id="queryId" label="查询条件" dataset="auditNewInfo" fieldstr="qapplyDateTime,qapplyTlrNo,qauditType,qauditState"></snow:query>
     <!-- 列表信息 -->
     <snow:grid id="gridId"  label="列表信息" dataset="auditNewInfo" height="99%" fitcolumns="true"
     fieldstr="auditState,applyDateTime,auditType,applyTlrNo,auditDesc,opr"></snow:grid>
     <!-- 详情页面 -->
     <snow:window id="windowIdDetail"  title="审核消息详情" modal="true" closable="true" width="auto" >
           <snow:form id="formDetailId" dataset="auditNewInfo" label="审核信息" border="false"  
           fieldstr="auditId,auditState,applyDateTime,auditType,applyTlrNo,auditDesc,auditProcId,applyBrNo" collapsible="false" colnum="4"></snow:form>
           <p></p>
           <snow:grid id="gridRoleDetailId" label="审核信息步骤" dataset="auditNewRecordInfo" height="300" fitcolumns="true" border="true"  
           fieldstr="stepNo[100],auditState[150],roleName[150],auditOprNo[100],auditDatetIme[150],auditView[373]"></snow:grid>
           <snow:button id="btnShowDetail" dataset="auditNewInfo" hidden="true"></snow:button>
     </snow:window>
     <script>
  /*********************************************详情页面*****************************************************************/
            function gridId_auditState_onRefresh(record,fieldId,fieldValue){
	              if(record){
	            	  if(fieldValue == "00"){
		            	  return "<span style='color:red'>未审核</span>";
		              }else if(fieldValue == "01"){
		            	  return "<span style='color:blue'>审核通过</span>";
		              }else if(fieldValue == "02"){
		            	  return "<span style='color:gray'>审核拒绝</span>";
		              }
	              }
            }
           
            function gridId_opr_onRefresh(record,fieldId,fieldValue){
            	var flag = record.getValue("flag");
                if(fieldValue=="01"){
             	     return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>" ;
      	        }else if(fieldValue=="02"){
      	        	 return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>" ;
      	        }else if(fieldValue=="00"){
        	    	if(flag=="1"){
        	    		return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openCheck()\">审核</a></span>" ;
        	    	}else{
        	    		return "&nbsp;";
        	    	}
                }else{
      		         return "&nbsp;";
      	        }    
          }
          
        
          //审核超链接
         function openCheck(){
        	 var auditUrl =  auditNewInfo_dataset.getValue("auditUrl");
        	 var auditId =  auditNewInfo_dataset.getValue("auditId");
        	 var flag = auditUrl.substring(4,8);
        	 
        	/*  if(auditUrl=='balAcctErrorsQueryAudit.jsp'){
        		 $.open("submitWin", "审核信息", "/pages/report/" + auditUrl + "?auditId=" + auditId,
             			   "1060", "1000", false, true, null, false, "返回");
        	 }else if(flag == 'Term'){
        		  window.location.href='../../term/paypTerm/'+auditUrl+'?auditId='+ auditId;
        	 }else{
            	 $.open("submitWin", "审核信息", "/pages/payPmp/mchtMng/" + auditUrl + "?auditId=" + auditId,
           			   "1060", "1000", false, true, null, false, "返回");
        	 } */
        	 
        	 switch(auditUrl)
        	 {
        	 case 'balAcctErrorsQueryAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/report/" + auditUrl + "?auditId=" + auditId,
             			   "1060", "1000", false, true, null, false, "返回");
        	   break;
        	 case 'mchtInfoAudit.jsp':case 'mchtContractAudit.jsp':case 'mchtTotalAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mchtMng/" + auditUrl + "?auditId=" + auditId,
             			   "1060", "1000", false, true, null, false, "返回");
        	   break;
        	 case 'actvMethodMngAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
           			   "1060", "700", false, true, null, false, "返回");
        		 break;
        	 case 'actvMngAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
           			   "1060", "700", false, true, null, false, "返回");
        		 break;
        	 case 'actvMngConfAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
          			   "1060", "700", false, true, null, false, "返回");
        		 break;
        	 case 'actvMngMchtAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
          			   "1060", "700", false, true, null, false, "返回");
        		 break;
        	 case 'actvMngSRAudit.jsp':
        		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
          			   "1060", "700", false, true, null, false, "返回");
        		 break;
        	 }
        	 
        	 
         }
         function submitWin_onButtonClick(){
         	submitWin.close();
      		auditNewInfo_dataset.flushData(auditNewInfo_dataset.pageIndex);
          }
         //详情超链接
         function openDetail(){
        	 btnShowDetail.click();
    	 }
         //详情窗体打开
    	 function btnShowDetail_onClick(){
    		 auditNewInfo_dataset.setReadOnly(true);
    		 auditNewRecordInfo_dataset.setReadOnly(true);
    		 //获取审核信息编号
    		 var auditId = auditNewInfo_dataset.getValue("auditId");
    		 auditNewRecordInfo_dataset.setParameter("auditId",auditId);
    		 auditNewRecordInfo_dataset.flushData(auditNewRecordInfo_dataset.pageIndex);
        	 window_windowIdDetail.open();
         }
     </script>
</snow:page>