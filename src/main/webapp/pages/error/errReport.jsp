<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="银联差错处理">
	<snow:dataset id="pesSec"
		path="com.ruimin.ifs.pmp.err.dataset.errReport"
		init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="pesSec"
		fieldstr="startDate,endDate,qerrFlag"></snow:query>
		 
		
	<snow:grid dataset="pesSec" id="gridId"
		fieldstr="pan,transDate,transTime2,dateSettlmt,amtTrans,errorCode,txnNo"
		paginationbar="" toolbar="toolbar"
		exporter="exporterId"></snow:grid>
		
	<snow:exporter dataset="pesSec" id="exporterId" type="XLS|CSV"
		fieldstr="pan,transDate,transTime2,dateSettlmt,amtTrans,errorCode,txnNo"></snow:exporter>

	
	
	<script type="text/javascript">
	
	
	function detail_onClick(){
		
		pesSec_dataset.flushData(pesSec_dataset.pageIndex);
		window_detailInfo.open();
		
	}
	
	function deal_onClick(){
		
		pesSec_dataset.flushData(pesSec_dataset.pageIndex);
		window_deal.open();
		
	}
	
	function mod_onClick(){
		pesSec_dataset.flushData(pesSec_dataset.pageIndex);
		window_mod.open();
	}
</script>
</snow:page>
