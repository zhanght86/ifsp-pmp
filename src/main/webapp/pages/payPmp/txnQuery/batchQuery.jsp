<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量系统任务列表">
     <!-- 批量系统任务列表的dataset -->
     <snow:dataset id="batchJobRecord" path="com.ruimin.ifs.pmp.txnQuery.dataset.batchJobRecord" init="true" submitMode="current" ></snow:dataset>
     <snow:dataset id="batchJobReRun" path="com.ruimin.ifs.pmp.txnQuery.dataset.batchJobReRun" init="false" submitMode="current" ></snow:dataset>
     <!-- 查询条件 -->
     <snow:query id="queryId" label="查询条件" dataset="batchJobRecord" fieldstr="qjobId,qsettleDate,qjobStat"></snow:query>
     <!-- 列表信息 -->
     <snow:grid id="gridId"  label="批量信息" dataset="batchJobRecord" height="99%" fitcolumns="true"
     fieldstr="jobId[90],settleDate[100],jobDesc[180],jobStat[100],jobResult,logTraceId[140],lastUpdDateTime[140],opr[90]"></snow:grid>
     
   	<snow:button id="doReRun" dataset="batchJobReRun" hidden="true"></snow:button>
     <script>
  /*********************************************详情页面*****************************************************************/

           
     function gridId_opr_onRefresh(record,fieldId,fieldValue){
	  	if(record) {
	  		var stat = record.$data.jobStat;
	  		if(stat == '02'){
	  			return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:doRenRun()\">重跑</a></span>" ; 
	  		}else{
	  			return "<a style=\"color:gray;text-align:center\">/</a>";
	  		}
	  	}
     	  
     }
     
     function onClickModify(){
    	 batchJobRecord_dataset.flushData(batchJobRecord_dataset.pageIndex);
     }
        
     //审核超链接
     function doRenRun(){
        var jobId = batchJobRecord_dataset.getValue("jobId");
        var settleDate = batchJobRecord_dataset.getValue("settleDate");
        
        batchJobReRun_dataset.setParameter("jobId",jobId);
        batchJobReRun_dataset.setParameter("settleDate",settleDate);
        
        $.confirm("启动重新跑批，原批量任务信息会被全部重置，是否确认启动", function() {
			doReRun.click();
		}, function() {
			return false;
		});
        
     }
     
     
     function doReRun_postSubmit(){
    	 $.success(" 重新执行请求已受理，界面刷新后显示处理结果",function(){
    		 batchJobRecord_dataset.flushData(batchJobRecord_dataset.pageIndex);				
 		 });
    	 
    	 return;
     }
 
     </script>
</snow:page>