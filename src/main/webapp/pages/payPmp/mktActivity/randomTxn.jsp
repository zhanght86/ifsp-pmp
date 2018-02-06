<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="立减营销活动查询">
	<!-- 数据集 -->	
	<snow:dataset id="randomTxn" path="com.ruimin.ifs.pmp.mktActivity.dataset.RandomTxn" init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryId" dataset="randomTxn"
		fieldstr="qActiveNm,qActiveNo,qMchtId,qSDate,qEDate"></snow:query>
	<!-- 主表单 -->
	<snow:grid dataset="randomTxn" id="gridId" height="99%" fieldstr="activeNm,activeNo,sDate,eDate,sRedbagCount,sUseBag,sRedbagConsume,sUseAmt"
		label="立减营销活动"></snow:grid>
	<script type ="text/javascript">	
		//分转元
		 function gridId_sRedbagConsume_onRefresh(record, fieldId, fieldValue) {
	        	if(fieldValue.length!=0){
		        	var str = (fieldValue/100).toFixed(2) + '';
		        	return str;
	        	}
	       
	        return fieldValue;
	  	}
		//分转元
		 function gridId_sUseAmt_onRefresh(record, fieldId, fieldValue) {
	        	if(fieldValue.length!=0){
		        	var str = (fieldValue/100).toFixed(2) + '';
		        	return str;
	        	}
	        return fieldValue;
	  	}
	</script>

</snow:page>