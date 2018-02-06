<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="交易查询">
	<!-- modify by lengjingyu 20180205 交易查询界面变更需求 jira-1865 -->
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascrpt"  src="../../public/lib/jquery/jquery.form.js"></script>
	<!-- 交易查询dataset -->
    <snow:dataset id="dtstTxnQuery" path="com.ruimin.ifs.pmp.txnQuery.dataset.txnQuery" init="false" submitMode="current"></snow:dataset>
	
	<!-- 查询条件 -->
	<snow:query label="查询条件" id="queryCondition"  dataset="dtstTxnQuery" fieldstr="qTxnDateStart,qTxnDateEnd,qMchtId,qMchtOrderId,qTxnSeqId,qtpamOutId,qPayProduct,qTxnState,qPagySeqId"></snow:query>
	<!-- 交易列表表格 -->
	<snow:grid dataset="dtstTxnQuery" label="交易信息"  id="gridTxnList" fieldstr="txnSeqId,pointId,txnAccTypeName,txnDate,txnTime,mchtId,mchtName,txnOrderId,payProduct,txnAmt,txnState,opr" exporter="exp" fitcolumns="true" height="99%"></snow:grid>
	<snow:exporter dataset="dtstTxnQuery" type="csv" fieldstr="txnSeqId,pointId,txnAccTypeName,txnDate,txnTime,mchtId,mchtName,txnOrderId,payProduct,txnAmt,txnState" id="exp"></snow:exporter>
	<script type="text/javascript">
	
	function initCallGetter_post() {
		dtstTxnQuery_interface_dataset.setValue("qTxnDateStart",getCurrentDate());
		dtstTxnQuery_interface_dataset.setValue("qTxnDateEnd",getCurrentDate());
		dtstTxnQuery_dataset.setParameter("qTxnDateStart",getCurrentDate2());
		dtstTxnQuery_dataset.setParameter("qTxnDateEnd",getCurrentDate2());
		dtstTxnQuery_dataset.flushData(dtstTxnQuery_dataset.pageIndex); 
	}
	
	
	//获取当前日期
	function getCurrentDate(){
		var currDate = new Date();
		var str = currDate.getFullYear()+"-";
        if((currDate.getMonth()+1).toString.length<2) {
              str+="0"+(currDate.getMonth()+1)+"-";
        }else{
              str+=(currDate.getMonth()+1)+"-";
        } 
        if((currDate.getDate()+"").length<2){
              str+="0"+currDate.getDate();

        }else{
              str+=currDate.getDate();
        }
		return str;
	}
	
	//获取当前日期
	function getCurrentDate2(){
		var currDate = new Date();
		var str = currDate.getFullYear()+"";
        if((currDate.getMonth()+1).toString.length<2) {
              str+="0"+(currDate.getMonth()+1)+"";
        }else{
              str+=(currDate.getMonth()+1)+"";
        } 
        if((currDate.getDate()+"").length<2){
              str+="0"+currDate.getDate();

        }else{
              str+=currDate.getDate();
        }
		return str;
	}
	
	/***************下载报表***************/
	function btnDownload_onClickCheck(){
		if(dtstTxnQuery_dataset.length==0){
             $.warn("没有可下载的信息!");
             return false;
         }else{
        	 /*****查询条件*****/

        	 var qTxnDateStart = dtstTxnQuery_interface_dataset.getValue("qTxnDateStart");//
        	 var qTxnDateEnd = dtstTxnQuery_interface_dataset.getValue("qTxnDateEnd");//
        	 var qMchtId = dtstTxnQuery_interface_dataset.getValue("qMchtId");
        	 var qMchtOrderId = dtstTxnQuery_interface_dataset.getValue("qMchtOrderId");//
        	 var qTxnSeqId = dtstTxnQuery_interface_dataset.getValue("qTxnSeqId");//
        	 var qtpamOutId = dtstTxnQuery_interface_dataset.getValue("qtpamOutId");//
        	 var qPayProduct = dtstTxnQuery_interface_dataset.getValue("qPayProduct");//
        	 var qTxnState = dtstTxnQuery_interface_dataset.getValue("qTxnState");//
        	 var qPagySeqId = dtstTxnQuery_interface_dataset.getValue("qPagySeqId");//
        	 
        	 if(qTxnDateStart){
        		 qTxnDateStart = qTxnDateStart.format("yyyyMMdd");
 				
 			}
 			if(qTxnDateEnd){
 				qTxnDateEnd = qTxnDateEnd.format("yyyyMMdd");
 				
 			}
 			//如果起始日期不为空
 			if(qTxnDateStart != null && qTxnDateStart != ""&&qTxnDateStart != null && qTxnDateStart != ""){
 				//与当前日期比较，不能大于当前日期
 				if(qTxnDateStart>qTxnDateStart){
 					$.warn("交易日期(起始),不能大于截止日期！");
 				    return false; 
 				}
 			}
        	 /*****进入后台*****/
        	 var url = '<snow:url flowId="com.ruimin.ifs.pmp.txnQuery.comp.TxnQueryAction:doutAgent" />' + "&qTxnDateStart="+qTxnDateStart + "&qTxnDateEnd="+qTxnDateEnd + "&qMchtId="+qMchtId + "&qMchtOrderId="+qMchtOrderId + "&qTxnSeqId="+qTxnSeqId+ "&qtpamOutId="+qtpamOutId+"&qPayProduct"+qPayProduct+ "&qTxnState="+qTxnState + "&qPagySeqId="+qPagySeqId;

        	 url = encodeURI(url);
        	 url = encodeURI(url);
        	 window.location.href = url;
        	 return true;
         }			
	}
	
	
	    //打开详情窗口
	    /**详情显示*/
		function gridTxnList_opr_onRefresh(record) {
		
	 		if (record) {
	 			var txnSeqId=record.getValue("txnSeqId");
	 			
	 				
	 				
	 			return "<span style='width:100%;text-align:center' class='fa fa-eye'><a href=\"JavaScript:onClickDetail('"+txnSeqId+"')\">详情</a></span>";
	 		}
		}
		
		/**打开详情窗口*/
		function onClickDetail(txnSeqId){
			$.open("winDetail", '交易详情', "/pages/payPmp/txnQuery/txnQueryDetil.jsp?qTxnSeqId="+txnSeqId, "1060", "1000", false, true, null, false,"关闭");
		}
		
		function winDetail_onButtonClick(){
			winDetail.close();
	    }
	
		
		//当查询条件中交易类型下拉框展开前事件
		function dtstTxnQuery_interface_dataset_qTxnType_beforeOpen(dropDown,dropDownDataset) {
			var accessTypeCode =dtstTxnQuery_interface_dataset.getValue("qAccessType");
			if(accessTypeCode == null || accessTypeCode == ''){
				$.warn("请先选择接入方式");
				return false;
			}
			dropDownDataset.setParameter("qAccessTypeCode", accessTypeCode);
			
			dropDownDataset.flushData(dropDownDataset.pageIndex);
			return true;
		}
		//返回按钮点击事件
		function btnReturn_onClick(){
			window_windowDetail.close();
		}
		//当查询条件中接入方式选择时
		function dtstTxnQuery_interface_dataset_qAccessType_onSelect(v,record) {
			dtstTxnQuery_interface_dataset.setValue("qTxnType","");
			dtstTxnQuery_interface_dataset.setValue("qTxnTypeName","");
			return true;
		}
		  //**********对输入时间进行验证
		function dtstTxnQuery_interface_dataset_queryCondition_onClickCheck(button, commit) {

			//交易日期（起始）
			var startDate=document.getElementById("editor_query_qTxnDateStart").value;
			//交易日期（截止）
			var endDate=document.getElementById("editor_query_qTxnDateEnd").value;
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
	</script>
	
	
	
</snow:page>