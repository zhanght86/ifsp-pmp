<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="商户网点费用报表">
	<snow:dataset id="mchtNetExpenseDaily"
		path="com.ruimin.ifs.pmp.report.dataset.mchtNetExpenseDaily"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="mchtNetExpenseDaily"
		fieldstr="qTxnDtStart,qTxnDtEnd,qInAcctMerId,qInAcctMerName"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="mchtNetExpenseDaily" id="gridId" height="99%" fieldstr="txnDt,inAcctDate,inAcctMerId,inAcctMerName,pointId,prodName,txnNum,txnAmt,setlFeeAmt"
		label="商户网点费用信息" paginationbar="btnDownload"></snow:grid>

	
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function mchtNetExpenseDaily_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_qTxnDtStart").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_qTxnDtEnd").value;
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
		if(mchtNetExpenseDaily_dataset.length==0){
             $.warn("没有可下载的信息!");
             return false;
         }else{
        	 /*****查询条件*****/

        	 var qTxnDtStart = mchtNetExpenseDaily_interface_dataset.getValue("qTxnDtStart");//交易日期(起始)
        	 var qTxnDtEnd = mchtNetExpenseDaily_interface_dataset.getValue("qTxnDtEnd");//交易日(截止)
        	 var qInAcctMerId = mchtNetExpenseDaily_interface_dataset.getValue("qInAcctMerId");//商户编号
        	 var qInAcctMerName = mchtNetExpenseDaily_interface_dataset.getValue("qInAcctMerName");//商户名称
        	 if(qTxnDtStart){
        		 qTxnDtStart = qTxnDtStart.format("yyyyMMdd");
 				
 			}
 			if(qTxnDtEnd){
 				qTxnDtEnd = qTxnDtEnd.format("yyyyMMdd");
 				
 			}
 			//如果起始日期不为空
 			if(qTxnDtStart != null && qTxnDtStart != ""&&qTxnDtEnd != null && qTxnDtEnd != ""){
 				//与当前日期比较，不能大于当前日期
 				if(qTxnDtStart>qTxnDtEnd){
 					$.warn("交易日期(起始),不能大于截止日期！");
 				    return false; 
 				}
 			}
        	 /*****进入后台*****/
        	 var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.MchtNetExpenseDailyAction:doutAgent" />' + "&qTxnDtStart="+qTxnDtStart + "&qTxnDtEnd="+qTxnDtEnd + "&qInAcctMerId="+qInAcctMerId + "&qInAcctMerName="+qInAcctMerName;
        	 url = encodeURI(url);
        	 url = encodeURI(url);
        	 window.location.href = url;
        	 return true;
         }			
	}
	</script>
</snow:page>