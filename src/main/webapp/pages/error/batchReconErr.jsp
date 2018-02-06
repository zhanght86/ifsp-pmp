<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量对账差错">
	<snow:dataset id="batchReconErr"
		path="com.ruimin.ifs.pmp.err.dataset.batchRecon"
		init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="batchReconErr"
		fieldstr="stlmDate,pan,termId"></snow:query>
		 
		
	<snow:grid dataset="batchReconErr" id="gridId"
		fieldstr="txnNum[100],stlmDate[100],transDateTime[100],pan[200],orgTransAmt[100],amtTrans[100]"
		paginationbar="mod" toolbar="toolbar"
		exporter="exporterId"></snow:grid>
		
	<snow:exporter dataset="batchReconErr" id="exporterId" type="XLS|CSV"
		fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans"></snow:exporter>

	
	<snow:window id="mod" title="差错调整" modal="true" closable="true" width="900" buttons="btMod">
		<snow:form id="deal" dataset="batchReconErr" border="true" label="差错调整" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:button id="btMod" dataset="batchReconErr" hidden="true"></snow:button>
	</snow:window>
	
	<script type="text/javascript">
	
	function mod_onClick(){
		batchReconErr_dataset.flushData(batchReconErr_dataset.pageIndex);
		window_mod.open();
	}
	
	function btMod_postSubmit() {
		$.success("新增成功!", function() {
			window_mod.close();
			batchReconErr_dataset.flushData(batchReconErr_dataset.pageIndex);
		});
	}
</script>
</snow:page>
