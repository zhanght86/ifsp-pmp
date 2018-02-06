<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户风险等级">
	<script src="../common/upload/js/ajaxfileupload.js"></script>
	
	<!-------------- 商户风险等级数据集 ---------------->
	<snow:dataset id="MchtRiskGrade" path="com.ruimin.ifs.pmp.risk.dataset.MchtRiskGrade" submitMode="current" init="true"></snow:dataset>
	
	<snow:query label="查询条件" id="mchtRiskGradeQuery" collapsible="false" border="false" dataset="MchtRiskGrade" fieldstr="qMchtNo,qMchtName,qRiskLevel" ></snow:query>
	<snow:grid dataset="MchtRiskGrade" height="99%" label="商户风险等级列表" fitcolumns="true" id="mchtRiskGradeGrid" fieldstr="mchtNo,mchtName,riskLevel" 
		paginationbar="btnGra"  ></snow:grid>
	
	<!-------------- 手工评级窗口 ---------------->
 	<snow:window id="windowHandWorkGrade" title="手工评级" width="800" modal="true" closable="true" buttons="btnGraSave"> 
		<snow:form id="handWorkGradeForm" dataset="MchtRiskGrade" border="false" label="" 
 				fieldstr="mchtNo,mchtName,riskLevel" > 
		</snow:form>
		<snow:button id="btnGraSave" dataset="MchtRiskGrade" hidden="true"></snow:button>
	</snow:window>
	
 	<script> 

		//--------------------------------修改 ------------------------------
		//点击手工修改按钮，打开修改窗口
 		function btnGra_onClick() { 
			var mchtNo = MchtRiskGrade_dataset.getValue("mchtNo");
			if(mchtNo == ''){
				$.warn("请先选择要修改的商户");
				return false;
			}
			MchtRiskGrade_dataset.setFieldReadOnly("mchtNo", true);
			MchtRiskGrade_dataset.setFieldReadOnly("mchtName", true);
 			window_windowHandWorkGrade.open(); 
 		} 
		
		//修改成功之后
 		function btnGraSave_postSubmit() { 
 			$.success("修改成功!", function() { 
 				window_windowHandWorkGrade.close(); 
 				MchtRiskGrade_dataset.flushData(MchtRiskGrade_dataset.pageIndex); 
 			}); 
 		} 
 		//当手工评级框体关闭时，取消记录
 		function window_windowHandWorkGrade_beforeClose(wmf){ 
 			MchtRiskGrade_dataset.cancelRecord(); 
		} 
		
	</script>
</snow:page>