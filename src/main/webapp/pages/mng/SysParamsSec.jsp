<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="系统参数设置">
	<snow:dataset id="SysParamsSec" path="com.ruimin.ifs.mng.dataset.SysParamsSec" init="true"></snow:dataset>
	<snow:query id="queryId" dataset="SysParamsSec" collapsible="false" fieldstr="queryParamId,queryOprcode1" label="查询条件"></snow:query>
	<snow:grid dataset="SysParamsSec" id="gridId" label="参数信息" height="99%" fieldstr="paramId[100],magicId[140],paramValueTx[100],desc0,opr[140]" paginationbar="btAdd,btUpt,btnDelete" exporter="exporterId"></snow:grid>
	<snow:exporter dataset="SysParamsSec" id="exporterId" type="XLS|CSV" fieldstr="paramId,magicId,paramValueTx,desc0"></snow:exporter>
	<snow:window id="windowModifyId" title="系统参数编辑" modal="true" closable="true" buttons="btUpdate">
		<snow:form id="formModifyId" dataset="SysParamsSec" border="false" label="系统参数 --> 编辑 --> 参数设置" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>
		<snow:button id="btUpdate" dataset="SysParamsSec" hidden="true" ></snow:button>
	</snow:window>
	<snow:window id="windowDetailId" title="系统参数详情" modal="true" closable="true" buttons="btnCancel">
		<snow:form id="formDetailId" dataset="SysParamsSec" border="false" label="系统参数 --> 详情 --> 参数详情" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>
	</snow:window>
	<snow:button id="btnDeleteSub" dataset="SysParamsSec"  hidden="true"></snow:button>	
	<snow:window id="win" title="参数新增" modal="true" closable="true" buttons="btnSave">
		<snow:form id="sysparamswin" dataset="SysParamsSec" border="false" label="系统参数 --> 详情 --> 参数新增" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>
		<snow:button id="btnSave" dataset="SysParamsSec" hidden="true"></snow:button>
	</snow:window>
	<script>
 	
	function gridId_opr_onRefresh(record, fieldId, fieldValue){ 
 			if(record){
 				var paramId = record.getValue("paramId"); 
 				var magicId = record.getValue("magicId"); 
				return "<i class='fa fa-list'></i>&nbsp;<a href=\"JavaScript:onClickDetail('"+paramId+"','"+magicId+"')\">详情</a>";
 			} 
 		}
 	
 		function onClickDetail(paramId,magicId){
 		//	SysParamsSec_dataset.setFieldReadOnly("paramValueTx",true);
			SysParamsSec_dataset.setFieldReadOnly("paramId",true);
			SysParamsSec_dataset.setFieldReadOnly("magicId",true);
			SysParamsSec_dataset.setFieldReadOnly("paramValueTx",true);
			SysParamsSec_dataset.setFieldReadOnly("paramStartTm",true);
			SysParamsSec_dataset.setFieldReadOnly("paramEndTm",true);
			SysParamsSec_dataset.setFieldReadOnly("desc0",true);
			window_windowDetailId.open();
 		}
		function onClickModify(paramId,magicId){
			SysParamsSec_dataset.setFieldReadOnly("paramValueTx",false);
			SysParamsSec_dataset.setFieldReadOnly("paramStartTm",false);
			SysParamsSec_dataset.setFieldReadOnly("paramEndTm",false);
			SysParamsSec_dataset.setFieldReadOnly("desc0",false);
			window_windowModifyId.open();
 		}
		function btnCancel_onClick(){
			window_windowDetailId.close();
		}
		function window_windowModifyId_beforeClose(wmf){
			SysParamsSec_dataset.cancelRecord();
		}
				
		
		function btnSave_postSubmit(){
			$.success("操作成功!", function() {
				window_win.close();
				SysParamsSec_dataset.flushData(SysParamsSec_dataset.pageIndex);
	        });
		}
		
	
		function btnDelete_onClick(){

			var paramId = SysParamsSec_dataset.getValue("paramId");
			var magicId = SysParamsSec_dataset.getValue("magicId");
			$.confirm("是否确认删除该卡bin? 注:一经删除无法恢复!", function() {
				SysParamsSec_dataset.deleteRecord();
				SysParamsSec_dataset.setParameter("paramId",paramId);
				SysParamsSec_dataset.setParameter("magicId",magicId);
				btnDeleteSub.click();
    		}, function() {
    			return false;
    		});
		}
		function btnDeleteSub_postSubmit(btn){
			$.success("操作成功!", function() {
				SysParamsSec_dataset.flushData(SysParamsSec_dataset.pageIndex);
    		});
		}
		
		function btAdd_onClick(){
			SysParamsSec_dataset.setReadOnly(false);
			SysParamsSec_dataset.setFieldReadOnly("paramId",false);
			SysParamsSec_dataset.setFieldReadOnly("magicId",false);
			SysParamsSec_dataset.setFieldReadOnly("paramValueTx",false);
			SysParamsSec_dataset.setFieldReadOnly("paramStartTm",false);
			SysParamsSec_dataset.setFieldReadOnly("paramEndTm",false);
			SysParamsSec_dataset.setFieldReadOnly("desc0",false);
			window_win.open();
		}
		
		function window_win_beforeClose(wmf){
			SysParamsSec_dataset.cancelRecord();
		}
		
		
		function btUpt_onClick(){
			//SysParamsSec_dataset.setReadOnly(false);
			SysParamsSec_dataset.setFieldReadOnly("paramId",true);
			SysParamsSec_dataset.setFieldReadOnly("magicId",true);
			SysParamsSec_dataset.setFieldReadOnly("paramValueTx",false);
			SysParamsSec_dataset.setFieldReadOnly("paramStartTm",false);
			SysParamsSec_dataset.setFieldReadOnly("paramEndTm",false);
			SysParamsSec_dataset.setFieldReadOnly("desc0",false);
			window_windowModifyId.open();
		}
		
		function openupdate(){
			SysParamsSec_dataset.setReadOnly(false);
			btUpdate.setHidden(false);
			window_windowModifyId.open();
		}
		function window_winupdate_beforeClose(wmf){
			SysParamsSec_dataset.cancelRecord();
		}
		function btUpdate_postSubmit(btn){
			$.success("操作成功!", function() {
				window_windowModifyId.close();
				SysParamsSec_dataset.flushData(SysParamsSec_dataset.pageIndex);
	        });
		}

	</script>
</snow:page>
