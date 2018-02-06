<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="手工录入差错">
	<snow:dataset id="handInputErr"
		path="com.ruimin.ifs.pmp.err.dataset.handInputErr"
		init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="handInputErr"
		fieldstr="qdateSettlm,qcupSsn"></snow:query>
		 
		
	<snow:grid dataset="handInputErr" id="gridId"
		fieldstr="cupSsn[100],dateSettlmt[100],transDate[100],pan[200],amtTrans[100],seqNo[100],errAmtTrans[100],status[100]"
		paginationbar="mod" toolbar="toolbar"
		exporter="exporterId"></snow:grid>
		

	<snow:window id="mod" title="手工录入差错" modal="true" closable="true" width="900" buttons="btDeal">
		<snow:form id="deal" dataset="handInputErr" border="true" label="手工录入差错" collapsible="true" colnum="4" labelwidth="100"
 				fieldstr="cupSsn,dateSettlmt,transDate,pan,amtTrans,errAmtTrans,txnNo,errorCode,misc1" >  
 		</snow:form> 
 		<snow:button id="btDeal" dataset="handInputErr" hidden="true"></snow:button> 
 	</snow:window> 
	
	<script type="text/javascript">
	function mod_onClick(){
		//handInputErr_dataset.flushData(handInputErr_dataset.pageIndex);
		window_mod.open();
	}
	
	function btDeal_postSubmit() {
		$.success("新增成功!", function() {
			window_mod.close();
			handInputErr_dataset.flushData(handInputErr_dataset.pageIndex);
		});
	}
	
	
	function window_mod_beforeClose(wmf) {
		handInputErr_dataset.cancelRecord();
	}
</script>
</snow:page>
