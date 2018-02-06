<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="代理商分润统计月报表">
	<snow:dataset id="profitCountMonthly"
		path="com.ruimin.ifs.pmp.report.dataset.agentShareProfitCountMonthly"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="profitCountMonthly"
		fieldstr="qStlmMonthStart,qStlmMonthEnd,qAgentId,qAgentName"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="profitCountMonthly" id="gridId" height="99%" fieldstr="stlmMonth[105],agentId,agentName,sigContMchtCount,shaBenfAmt,stlmDateTime"
		label="分润统计信息" paginationbar="btnDownload"></snow:grid>

	
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function profitCountMonthly_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_qStlmMonthStart").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_qStlmMonthEnd").value;
		//转换日期
		var TxnDateStart=startDate.replace("-","").replace("-","");
		var TxnDateEnd=endDate.replace("-","").replace("-","");
		//如果起始日期不为空
		if(TxnDateStart != null && TxnDateStart != ""&&TxnDateEnd != null && TxnDateEnd != ""){
			//与当前日期比较，不能大于当前日期
			if(TxnDateStart>TxnDateEnd){
				$.warn("交易日期(起始),不能大于截止日期！");
			    return false; 
			}
		}
		return true;
	}
	/***************下载报表***************/
	function btnDownload_onClickCheck(){
		if(profitCountMonthly_dataset.length==0){
             $.warn("没有可下载的信息!");
             return false;
         }else{
        	 /*****查询条件*****/

        	 var qStlmMonthStart = profitCountMonthly_interface_dataset.getValue("qStlmMonthStart");//统计月份(起始)
        	 var qStlmMonthEnd = profitCountMonthly_interface_dataset.getValue("qStlmMonthEnd");//统计月份(截止)
        	 var qAgentId = profitCountMonthly_interface_dataset.getValue("qAgentId");//代理商编号
        	 var qAgentName = profitCountMonthly_interface_dataset.getValue("qAgentName");//代理商名称
        	 if(qStlmMonthStart){
        		 qStlmMonthStart = qStlmMonthStart.format("yyyyMMdd");
 				
 			}
 			if(qStlmMonthEnd){
 				qStlmMonthEnd = qStlmMonthEnd.format("yyyyMMdd");
 				
 			}
 			//如果起始日期不为空
 			if(qStlmMonthStart != null && qStlmMonthStart != ""&&qStlmMonthEnd != null && qStlmMonthEnd != ""){
 				//与当前日期比较，不能大于当前日期
 				if(qStlmMonthStart>qStlmMonthEnd){
 					$.warn("交易日期(起始),不能大于截止日期！");
 				    return false; 
 				}
 			}
        	 /*****进入后台*****/
        	 var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.AgentShareProfitCountMonthlyAction:doutAgent" />' + "&qStlmMonthStart="+qStlmMonthStart + "&qStlmMonthEnd="+qStlmMonthEnd + "&qAgentId="+qAgentId + "&qAgentName="+qAgentName;
        	 url = encodeURI(url);
        	 url = encodeURI(url);
        	 window.location.href = url;
        	 return true;
         }			
	}
	</script>
</snow:page>