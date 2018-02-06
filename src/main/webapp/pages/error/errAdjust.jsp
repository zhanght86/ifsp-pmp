<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="差错流程调整">
	<snow:dataset id="errFlow"
		path="com.ruimin.ifs.pmp.err.dataset.errAdjust"
		init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="errFlow"
		fieldstr="stlmDate,pan,termId"></snow:query>
		 
		
	<snow:grid dataset="errFlow" id="gridId"
		fieldstr="txnNum[100],stlmDate[100],transDateTime[100],pan[200],orgTransAmt[100],amtTrans[100]"
		paginationbar="deal" toolbar="toolbar"
		exporter="exporterId"></snow:grid>
		
	<snow:exporter dataset="errFlow" id="exporterId" type="XLS|CSV"
		fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans"></snow:exporter>

	
	<snow:window id="mod" title="您可以调整差错的处理流程" modal="true" closable="true" width="900" buttons="btDeal">
		<snow:form id="deal" dataset="errFlow" border="true" label="您可以调整差错的处理流程" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:button id="btDeal" dataset="errFlow" hidden="true"></snow:button>
	</snow:window>
	
	<script type="text/javascript">
	
	function deal_onClick(){
		
		errFlow_dataset.flushData(errFlow_dataset.pageIndex);
		window_mod.open();
		
	}
	
	function btDeal_postSubmit() {
		$.success("调整成功!", function() {
			window_mod.close();
			errFlow_dataset.flushData(errFlow_dataset.pageIndex);
		});
	}
</script>
</snow:page>
