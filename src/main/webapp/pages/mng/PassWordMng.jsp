<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="密码规则设置">
	<snow:dataset id="PassWordMng" path="com.ruimin.ifs.mng.dataset.PassWordMng" init="true"></snow:dataset>
	<snow:query id="queryId" dataset="PassWordMng" fieldstr="queryParamId,queryOprcode1" label="请输入查询条件"></snow:query>
	<snow:grid dataset="PassWordMng" id="gridId" fieldstr="paramId[100],magicId[140],paramValueTx[100],desc0,opr[140]" paginationbar="btAdd,btUpt,btnDelete" exporter="exporterId"></snow:grid>
	<snow:exporter dataset="PassWordMng" id="exporterId" type="XLS|CSV" fieldstr="paramId,magicId,paramValueTx,desc0"></snow:exporter>
	<snow:window id="windowModifyId" title="密码规则参数编辑" modal="true" closable="true" buttons="btUpdate">
		<snow:form id="formModifyId" dataset="PassWordMng" border="false" label="密码规则参数 --> 编辑 --> 参数设置" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>
		<snow:button id="btUpdate" dataset="PassWordMng" hidden="true" ></snow:button>
	</snow:window>
	<snow:window id="windowDetailId" title="密码规则参数详情" modal="true" closable="true" buttons="btnCancel">
		<snow:form id="formDetailId" dataset="PassWordMng" border="false" label="密码规则参数--> 详情 --> 参数详情" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>

	</snow:window>
	/*add by ttt 20151104*/
	<snow:button id="btnDeleteSub" dataset="PassWordMng"  hidden="true"></snow:button>	
	<snow:window id="win" title="参数新增" modal="true" closable="true" buttons="btnSave">
		<snow:form id="sysparamswin" dataset="PassWordMng" border="false" label="密码规则参数 --> 详情 --> 参数新增" fieldstr="paramId,magicId,paramValueTx,paramStartTm,paramEndTm,desc0" collapsible="false" colnum="2"></snow:form>
		<snow:button id="btnSave" dataset="PassWordMng" hidden="true"></snow:button>
	</snow:window>

	/*end add*/
	<script>
 	
	function gridId_opr_onRefresh(record, fieldId, fieldValue){ 
 			if(record){
 				var paramId = record.getValue("paramId"); 
 				var magicId = record.getValue("magicId"); 
				return "<i class='fa fa-search-plus'></i>&nbsp;<a href=\"JavaScript:onClickDetail('"+paramId+"','"+magicId+"')\">查看详情</a>"; 
 			} 
 		}
 	
 		function onClickDetail(paramId,magicId){
 		//	PassWordMng_dataset.setFieldReadOnly("paramValueTx",true);
			PassWordMng_dataset.setFieldReadOnly("paramId",true);
			PassWordMng_dataset.setFieldReadOnly("magicId",true);
			PassWordMng_dataset.setFieldReadOnly("paramValueTx",true);
			PassWordMng_dataset.setFieldReadOnly("paramStartTm",true);
			PassWordMng_dataset.setFieldReadOnly("paramEndTm",true);
			PassWordMng_dataset.setFieldReadOnly("desc0",true);
			window_windowDetailId.open();
 		}
		function onClickModify(paramId,magicId){
			PassWordMng_dataset.setFieldReadOnly("paramValueTx",false);
			PassWordMng_dataset.setFieldReadOnly("paramStartTm",false);
			PassWordMng_dataset.setFieldReadOnly("paramEndTm",false);
			PassWordMng_dataset.setFieldReadOnly("desc0",false);
			window_windowModifyId.open();
 		}
		function btnCancel_onClick(){
			window_windowDetailId.close();
		}
		function window_windowModifyId_beforeClose(wmf){
			PassWordMng_dataset.cancelRecord();
		}
				
		
		function btnSave_postSubmit(){
			$.success("操作成功!", function() {
				window_win.close();
				PassWordMng_dataset.flushData(PassWordMng_dataset.pageIndex);
	        });
		}
		
		/*add by ttt 20151104*/
		function btnDelete_onClick(){

			var paramId = PassWordMng_dataset.getValue("paramId");
			var magicId = PassWordMng_dataset.getValue("magicId");
			$.confirm("是否确认删除该卡bin? 注:一经删除无法恢复!", function() {
				PassWordMng_dataset.deleteRecord();
				PassWordMng_dataset.setParameter("paramId",paramId);
				PassWordMng_dataset.setParameter("magicId",magicId);
				btnDeleteSub.click();
    		}, function() {
    			return false;
    		});
		}
		function btnDeleteSub_postSubmit(btn){
			$.success("操作成功!", function() {
				PassWordMng_dataset.flushData(PassWordMng_dataset.pageIndex);
    		});
		}
		
		function btAdd_onClick(){
			PassWordMng_dataset.setReadOnly(false);
			PassWordMng_dataset.setFieldReadOnly("paramId",false);
			PassWordMng_dataset.setFieldReadOnly("magicId",false);
			PassWordMng_dataset.setFieldReadOnly("paramValueTx",false);
			PassWordMng_dataset.setFieldReadOnly("paramStartTm",false);
			PassWordMng_dataset.setFieldReadOnly("paramEndTm",false);
			PassWordMng_dataset.setFieldReadOnly("desc0",false);
			window_win.open();
		}
		
		function window_win_beforeClose(wmf){
			PassWordMng_dataset.cancelRecord();
		}
		
		
		function btUpt_onClick(){
			//PassWordMng_dataset.setReadOnly(false);
			PassWordMng_dataset.setFieldReadOnly("paramId",true);
			PassWordMng_dataset.setFieldReadOnly("magicId",true);
			PassWordMng_dataset.setFieldReadOnly("paramValueTx",false);
			PassWordMng_dataset.setFieldReadOnly("paramStartTm",false);
			PassWordMng_dataset.setFieldReadOnly("paramEndTm",false);
			PassWordMng_dataset.setFieldReadOnly("desc0",false);
			window_windowModifyId.open();
		}
		
		function openupdate(){
			PassWordMng_dataset.setReadOnly(false);
			btUpdate.setHidden(false);
			window_windowModifyId.open();
		}
		function window_winupdate_beforeClose(wmf){
			PassWordMng_dataset.cancelRecord();
		}
		function btUpdate_postSubmit(btn){
			$.success("操作成功!", function() {
				window_windowModifyId.close();
				PassWordMng_dataset.flushData(PassWordMng_dataset.pageIndex);
	        });
		}
		
		/*end add */
	</script>
</snow:page>
