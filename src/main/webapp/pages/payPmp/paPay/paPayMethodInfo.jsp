<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="平安支付方式查询">
	<snow:dataset id="PagyMixpayPayMethodInfo" path="com.ruimin.ifs.pmp.paPay.dataset.PagyMixpayPayMethodInfo" submitMode="current" init="true"></snow:dataset>
	<snow:query label="查询条件" id="queryId" dataset="PagyMixpayPayMethodInfo" collapsible="false"  fieldstr="qPmtId"></snow:query>
	
	<snow:grid dataset="PagyMixpayPayMethodInfo" id="gridId" label="支付方式" height="99%" fitcolumns="true" 
	fieldstr="pmtId[120],pmtTag[120],pmtType[140],pmtName[140],pmtInternalName[160],pmtIcon[200],usedTag[100],opr[120]" paginationbar="btnQuy"/>
	
	<!-- 启用/停用-->
	<!-- button  一般都会使用hidden属性 -->
	<snow:button id="btnUpdState" dataset="PagyMixpayPayMethodInfo" hidden="true"/>
	<snow:button id="btnQuySub" dataset="PagyMixpayPayMethodInfo" hidden="true"/>
	
	<script>
	function gridId_opr_onRefresh(record, fieldId, fieldValue) {
		if (record.getValue("pmtId")) {
			var pmtId = record.getValue("pmtId");
			var pmtTag = record.getValue("pmtTag");
			return "<a href=\"JavaScript:onClickDelete('" + pmtId + ","+ pmtTag +"')\">启用/停用</a>";
		}
	}	
	
	/* 消息框提示  $.confirm(message[,okCallback,cancelCallback])	确认框 */
	function onClickDelete(pmtId,pmtTag){
    	var state=PagyMixpayPayMethodInfo_dataset.getValue("usedTag");
    	/* 1,启用;2,停用 */
    	if('1'==state){
    		$.confirm("确定要停用该支付方式吗?", function() {
    			btnUpdState.click();
    		}, function() {
    			return false;
    		});	
    	}
    	if('2'==state){
    		$.confirm("确定要启用该支付方式吗?", function() {
    			btnUpdState.click();
    		}, function() {
    			return false;
    		});	
    	}
	}
	
	function btnUpdState_postSubmit() {
		$.success("操作成功!", function() {
			//定义到当前页
			PagyMixpayPayMethodInfo_dataset.flushData(PagyMixpayPayMethodInfo_dataset.pageIndex);
		});
	}
	
	function btnQuy_onClick(){
		if(""==PagyMixpayPayMethodInfo_dataset.getValue("pmtId")){
			PagyMixpayPayMethodInfo_dataset.setValue("pmtName", "");
		} 
		btnQuySub.click(); 
	}
	
	function btnQuySub_postSubmit() {
		$.success("操作成功!", function() {
			//定义到当前页
			PagyMixpayPayMethodInfo_dataset.flushData(PagyMixpayPayMethodInfo_dataset.pageIndex);
		});
	} 
	
   </script>
</snow:page>