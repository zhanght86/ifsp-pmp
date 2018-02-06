<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="商户交易统计日报表">
	<snow:dataset id="mchtTxnCountDaily"
		path="com.ruimin.ifs.pmp.report.dataset.mchtTxnCountDaily"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="mchtTxnCountDaily"
		fieldstr="qDateStlmStart,qDateStlmEnd,qMchtInfo,qMchtOrg"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="mchtTxnCountDaily" id="gridId" height="99%" fieldstr="dateStlm[95],brname,chlMerId,chlMerName,mchtType,txnCount,txnAmt,refundTxnCount,refundTxnAmt,setlFeeAmt,txnAmtComparative"
		label="交易统计信息" paginationbar="btnDownload"></snow:grid>

	
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function mchtTxnCountDaily_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_qDateStlmStart").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_qDateStlmEnd").value;
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
	function btnDownload_onClickCheck(){
		if(mchtTxnCountDaily_dataset.length==0){
             $.warn("没有可下载的信息!");
             return false;
         }else{
        	 /*****查询条件*****/

             var qDateStlmStart = mchtTxnCountDaily_interface_dataset.getValue("qDateStlmStart");//统计月份(起始)
        	 var qDateStlmEnd = mchtTxnCountDaily_interface_dataset.getValue("qDateStlmEnd");//统计月份(截止)
        	 var qMchtInfo = mchtTxnCountDaily_interface_dataset.getValue("qMchtInfo");//代理商编号
        	 var qMchtOrg = mchtTxnCountDaily_interface_dataset.getValue("qMchtOrg");//代理商名称
     		if(qDateStlmStart){
     			qDateStlmStart = qDateStlmStart.format("yyyyMMdd");
				
			}
			if(qDateStlmEnd){
				qDateStlmEnd = qDateStlmEnd.format("yyyyMMdd");
				
			}
			//如果起始日期不为空
 			if(qDateStlmStart != null && qDateStlmStart != ""&&qDateStlmEnd != null && qDateStlmEnd != ""){
 				//与当前日期比较，不能大于当前日期
 				if(qDateStlmStart>qDateStlmEnd){
 					$.warn("交易日期(起始),不能大于截止日期！");
 				    return false; 
 				}
 			}
        	 /*****进入后台*****/
        	 var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.MchtTxnCountDailyAction:doutAgent" />' + "&qDateStlmStart="+qDateStlmStart + "&qDateStlmEnd="+qDateStlmEnd + "&qMchtInfo="+qMchtInfo + "&qMchtOrg="+qMchtOrg;
        	 url = encodeURI(url);
        	 url = encodeURI(url);
        	 window.location.href = url;
        	 return true;
         }			
	}
	</script>
</snow:page>