<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="平安支付费率查询">
	<snow:dataset id="PagyMixpayPayMethodFeeCfg" path="com.ruimin.ifs.pmp.paPay.dataset.PagyMixpayPayMethodFeeCfg" submitMode="current" init="true"></snow:dataset>
	<snow:query label="查询条件" id="queryId" dataset="PagyMixpayPayMethodFeeCfg" collapsible="false"  fieldstr="qPmtId,qPmfId"></snow:query>
	
	<snow:grid dataset="PagyMixpayPayMethodFeeCfg" id="gridId" label="支付费率" height="99%" fitcolumns="true" 
	fieldstr="pmtId[120],pmtTag[120],pmfId[120],pmtOptHide[160],pmtDiscountFee[120],pmfName[180],usedTag[110],opr[160]" paginationbar="btnQuy"></snow:grid>
	
	<!-- 费率详情 -->
	<snow:window id="winDetails" closable="true" width="800" title="支付费率详情" 
		modal="true">
		<snow:form id="winBase" dataset="PagyMixpayPayMethodFeeCfg"  border="true"
			fieldstr="pmtId,pmfId,pmfMinFee,pmfMaxFee,pmfLimit,opmFee,opmFeeType"></snow:form>	
		<br />
	</snow:window>
	
	<!-- 启用/停用-->
	<!-- button  一般都会使用hidden属性 -->
	<snow:button id="btnUpdState" dataset="PagyMixpayPayMethodFeeCfg" hidden="true"></snow:button>
	<snow:button id="btnQuySub" dataset="PagyMixpayPayMethodFeeCfg" hidden="true"/>
	<script>
	function gridId_opr_onRefresh(record, fieldId, fieldValue){
		if(record.getValue("pmtId")){
			var pmtId = record.getValue("pmtId");
			var pmtTag = record.getValue("pmtTag");
			var pmfId = record.getValue("pmfId");
			return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:openDetail()\">详情</a></span><i class='fa fa-edit'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + pmtId + ","+ pmtTag +","+pmfId+"')\">启用/停用</a>"; 
		}else{
			return "&nbsp;";
		}
	}
	
	function openDetail(){
		PagyMixpayPayMethodFeeCfg_dataset.setReadOnly(true); 
		window_winDetails.open();
	}
	
	function window_winDetails_beforeOpen(wmf) {
		var pmfLimit = PagyMixpayPayMethodFeeCfg_dataset.getValue("pmfLimit");
		if(0==pmfLimit){
			PagyMixpayPayMethodFeeCfg_dataset.setValue("pmfLimit","不限封顶");
		}else{
			PagyMixpayPayMethodFeeCfg_dataset.setValue("pmfLimit",pmfLimit);
		}
	} 
	
	/* 消息框提示  $.confirm(message[,okCallback,cancelCallback])	确认框 */
	function onClickDelete(pmtId,pmtTag,pmfId){
    	var state=PagyMixpayPayMethodFeeCfg_dataset.getValue("usedTag");
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
			PagyMixpayPayMethodFeeCfg_dataset.flushData(PagyMixpayPayMethodFeeCfg_dataset.pageIndex);
		});
	}
	
	function btnQuy_onClick(){	
		if(""==PagyMixpayPayMethodFeeCfg_dataset.getValue("pmtId")){
			PagyMixpayPayMethodFeeCfg_dataset.setValue("pmfMaxFee", "");
		}
		btnQuySub.click(); 
	}
	
	function btnQuySub_postSubmit() {
		$.success("操作成功!", function() {
			//定义到当前页
			PagyMixpayPayMethodFeeCfg_dataset.flushData(PagyMixpayPayMethodFeeCfg_dataset.pageIndex);
		});
	}
	</script>
</snow:page>