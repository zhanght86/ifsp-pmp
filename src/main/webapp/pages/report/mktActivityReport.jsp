<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="营销活动报表">
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->

	<snow:dataset id="mktActivity"
		path="com.ruimin.ifs.pmp.report.dataset.mktActivityReport"
		submitMode="current" init="true"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="mktActivity"
		fieldstr="qTxnDt,qStlmDate,qActiveNo,qCardNo"></snow:query>
	<!-- 查询结果集分页显示 -->
	<snow:grid dataset="mktActivity" id="gridId" height="99%" fieldstr="txnDt,stlmDate,activeNo,activeNm,cardNo,pointId,merId,orgIdRecv,acctNo,payerName,acctName,activeAmt,realPayAmt,txnOrderAmt"
		label="营销信息" paginationbar="btnDownload"></snow:grid>
<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->


	<script type="text/javascript">
	/***************下载报表***************/
	function btnDownload_onClickCheck(){
		if(mktActivity_dataset.length==0){
             $.warn("没有可下载的信息!");
             return false;
         }else{
        	 /*****查询条件*****/

        	 var qTxnDt = mktActivity_interface_dataset.getValue("qTxnDt");//交易日期
        	 var qStlmDate = mktActivity_interface_dataset.getValue("qStlmDate");//清算日期
        	 var qActiveNo = mktActivity_interface_dataset.getValue("qActiveNo");//活动编号
        	 var qCardNo = mktActivity_interface_dataset.getValue("qCardNo");//用户编号
        	 if(qTxnDt){
        		 qTxnDt = qTxnDt.format("yyyyMMdd");
 				
 			}
 			if(qStlmDate){
 				qStlmDate = qStlmDate.format("yyyyMMdd");
 				
 			}
 			
        	 /*****进入后台*****/
        	 var url = '<snow:url flowId="com.ruimin.ifs.pmp.report.comp.MktActivityAction:doutAgent" />' + "&qTxnDt="+qTxnDt + "&qStlmDate="+qStlmDate + "&qActiveNo="+qActiveNo+ "&qCardNo=" +qCardNo;
        	 url = encodeURI(url);
        	 url = encodeURI(url);
        	 window.location.href = url;
        	 return true;
         }			
	}
	
	</script>
</snow:page>