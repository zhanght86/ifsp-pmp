<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="银联差错处理">
	<snow:dataset id="pesSec"
		path="com.ruimin.ifs.pmp.err.dataset.errSetting"
		init="true" submitMode="current"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="pesSec"
		fieldstr="stlmDate,pan,termId"></snow:query>
		 
		
	<snow:grid dataset="pesSec" id="gridId"
		fieldstr="txnNum[100],stlmDate[100],transDateTime[100],pan[200],orgTransAmt[100],amtTrans[100]"
		paginationbar="detail,deal,mod" toolbar="toolbar"
		exporter="exporterId"></snow:grid>
		
	<snow:exporter dataset="pesSec" id="exporterId" type="XLS|CSV"
		fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans"></snow:exporter>

	<snow:window id="detailInfo" title="差错详细信息" modal="true" closable="true" width="900">
		<snow:form id="detail" dataset="pesSec" border="true" label="原始交易信息" collapsible="true" colnum="4"
  				fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:grid id="oprlist" dataset="pesSec" fieldstr="txnNum[100],stlmDate[100],transDateTime[100],pan[200],orgTransAmt[100],amtTrans[100]" height="300" border="true" label="差错交易记录">
		</snow:grid> 
	</snow:window>
	
	
	<snow:window id="deal" title="差错处理" modal="true" closable="true" width="900" buttons="btDeal">
		<snow:form id="deal" dataset="pesSec" border="true" label="差错处理" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:button id="btDeal" dataset="pesSec" hidden="true"></snow:button>
	</snow:window>
	
	
	<snow:window id="mod" title="差错调整" modal="true" closable="true" width="900" buttons="btMod">
		<snow:form id="deal" dataset="pesSec" border="true" label="差错调整" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="txnNum,stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:button id="btMod" dataset="pesSec" hidden="true"></snow:button>
	</snow:window>
	
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
