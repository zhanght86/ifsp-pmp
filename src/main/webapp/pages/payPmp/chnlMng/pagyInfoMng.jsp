<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道信息查询">
	<!-- 渠道信息数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyInfo"
		id="pagyInfo" init="true" submitMode="current"></snow:dataset>
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnBaseInfo"
		id="pagyTxnBaseInfo" init="false" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query dataset="pagyInfo" label="查询条件" collapsible="false"
		fieldstr="qpagyNo,qpagyName"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="pagyInfo" height="99%" label="通道信息" id="gridId"
		fitcolumns="true"
		fieldstr="pagyNo,pagyName,opr"></snow:grid>
	
	<!-- 详情窗口 -->
	<snow:window id="windowDetailId" closable="true" width="800"
		title="通道信息" modal="true">
		<snow:form id="formDetailId" dataset="pagyInfo" border="true"
			fieldstr="pagyNo,pagyName"
			collapsible="false" colnum="4" label="基本信息"></snow:form>
		<snow:form id="pagyTxnDetail" dataset="pagyTxnBaseInfo" border="true"
			fieldstr="" collapsible="false" colnum="4" label="功能清单"></snow:form>
		<snow:tabs id="pagyTabsDetail" showswitch="true" border="true" height="300">									
		</snow:tabs>
	</snow:window>
<script>
function gridId_opr_onRefresh(record, fieldId, fieldValue){
	if(record){
		return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
	}else{
		return "&nbsp;";
	}
}

function openDetail(){
	$("#pagyTxnDetail").css("display","none");
	$("#tabs_pagyTabsDetail").css("display","none");
	pagyInfo_dataset.setReadOnly(true);
	pagyTxnBaseInfo_dataset.setParameter("qpagyNo", pagyInfo_dataset.getValue("pagyNo"));
	pagyTxnBaseInfo_dataset.flushData();	
}
function pagyTxnBaseInfo_dataset_flushDataPost(dataset) {
	//加载完数据后动态加载tab页
	var len= pagyTxnBaseInfo_dataset.length;
	if(len>0){
		$("#tabs_pagyTabsDetail").css("display","block");
	}
	while(len>0){
		tabs_pagyTabsDetail.add(len,pagyTxnBaseInfo_dataset.getValue("pagyTxnName"),"/pages/payPmp/chnlMng/pagyTxnAcctBankRel.jsp?pagyTxnCode="+pagyTxnBaseInfo_dataset.getValue("pagyTxnCode"));
		tabs_pagyTabsDetail.setClosable(len, false);
		pagyTxnBaseInfo_dataset.deleteRecord();
		len--;
	}
	window_windowDetailId.open();
}

function tabs_pagyTabsDetail_beforeTabChange(tabid) {
	tabs_pagyTabsDetail.refresh(tabid,"");
} 

function window_windowDetailId_afterClose(){
	//关闭所有的tab
	var len= pagyTxnBaseInfo_dataset.length;
	while(len>0){
		tabs_pagyTabsDetail.setClosable(len, true);
		len--
	}
	tabs_pagyTabsDetail.closeAll();
	pagyInfo_dataset.flushData(pagyInfo_dataset.pageIndex);
}
</script>


</snow:page>
