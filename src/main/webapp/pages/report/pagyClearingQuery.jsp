<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
<snow:page title="通道清算查询">
	<snow:dataset id="pagyClearingRslt"
		path="com.ruimin.ifs.pmp.report.dataset.pagyClearingRslt"
		submitMode="current" init="true"></snow:dataset>
	<snow:dataset id="pagyClearingDtl"
		path="com.ruimin.ifs.pmp.report.dataset.pagyClearingDtl"
		submitMode="current" init="false"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="pagyClearingRslt"
		fieldstr="qStlmDateStart,qStlmDateEnd,qPagyNo"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="pagyClearingRslt" id="gridId" height="99%" fieldstr="stlmDate[95],pagyNo,pagyName,setlAmt,txnCount,txnAmt,refundTxnCount,refundTxnAmt,tramFeeAmt,retTpamFeeAmt,operate"
		label="通道清算信息" paginationbar="btnDownload"></snow:grid>
	
	<!-- 详情窗体 -->
	<snow:window id="windDtl" title="通道清算详情 " modal="true"
		closable="true" buttons="btnDownLoadDtl,btnClose" height="400" width="1000">
		<snow:form id="frmDtl" dataset="pagyClearingRslt"
			border="false" label="通道清算详情" fieldstr="stlmDate,pagyNo,pagyName,setlAmt,txnCount,txnAmt,refundTxnCount,refundTxnAmt,tramFeeAmt,retTpamFeeAmt" collapsible="false"
			colnum="4">
		</snow:form>
		<snow:grid dataset="pagyClearingDtl" id="grdDtl"  fieldstr="stlmDate,pagyMainMchtNo[240],txnType,txnTime[90],txnAmt,setlAmt,tramFeeAmt[90],retTpamFeeAmt[90],thirdSsn[240]"
		label="交易明细"  border="true" fitcolumns="false" paginationbar=""></snow:grid>
	</snow:window>
	<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->
	<script type="text/javascript">
	//**********对输入时间进行验证
	function pagyClearingRslt_interface_dataset_queryId_onClickCheck(button, commit) {
		//交易日期（起始）
		var startDate=document.getElementById("editor_query_qStlmDateStart").value;
		//交易日期（截止）
		var endDate=document.getElementById("editor_query_qStlmDateEnd").value;
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
		function gridId_operate_onRefresh(record, fieldId, fieldValue){
			if(record){
				return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:showDetail()\">详情</a></span>";
			}
		}
		function showDetail(){
			pagyClearingDtl_dataset.setParameter("qSettleDate",pagyClearingRslt_dataset.getValue("stlmDate"));
			pagyClearingDtl_dataset.setParameter("qPagyNo",pagyClearingRslt_dataset.getValue("pagyNo"));
			//pagyClearingDtl_dataset.setParameter("qPagyMainMchtNo",pagyClearingRslt_dataset.getValue("pagyMainMchtNo"));
			pagyClearingDtl_dataset.flushData(pagyClearingDtl_dataset.pageIndex);
			window_windDtl.open();
		}
		function btnDownload_onClickCheck(btnDownload, commit) {
			var qStlmDateStart = pagyClearingRslt_interface_dataset.getValue("qStlmDateStart");
			var qStlmDateEnd = pagyClearingRslt_interface_dataset.getValue("qStlmDateEnd");
			var qPagyNo = pagyClearingRslt_interface_dataset.getValue("qPagyNo");
			var qPagyName = pagyClearingRslt_interface_dataset.getValue("qPagyNoName");
			
			if(qStlmDateStart){
				qStlmDateStart = qStlmDateStart.format("yyyyMMdd");
				
			}
			if(qStlmDateEnd){
				qStlmDateEnd = qStlmDateEnd.format("yyyyMMdd");
				
			}
			//如果起始日期不为空
			if(qStlmDateStart != null && qStlmDateStart != ""&&qStlmDateEnd != null && qStlmDateEnd != ""){
				//与当前日期比较，不能大于当前日期
				if(qStlmDateStart>qStlmDateEnd){
					$.warn("交易日期(起始),不能大于截止日期！");
				    return false; 
				}
			}
			var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.PagyClearingRsltAction:downloadReport" />';
			url = url+"&qStlmDateStart="+qStlmDateStart+"&qStlmDateEnd="+qStlmDateEnd+"&qPagyNo="+qPagyNo+"&qPagyName="+qPagyName;
			url = encodeURI(url);
			url = encodeURI(url);
       	 	window.location.href = url;
		    return true;
		}
		
		function window_windDtl_afterClose(){
			pagyClearingDtl_dataset.clearData();	
			pagyClearingDtl_dataset.flushData(1);			
		}
	</script>
</snow:page>