<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="银联差错处理">
	<snow:dataset id="unionErr"
		path="com.ruimin.ifs.pmp.err.dataset.unionPayErr"
		init="true" submitMode="current"></snow:dataset>
	<snow:dataset id="tempSec"
		path="com.ruimin.ifs.pmp.err.dataset.tempErr"
		init="false" submitMode="current"></snow:dataset>
		
	<snow:dataset id="errinf"
		path="com.ruimin.ifs.pmp.err.dataset.errinf"
		init="false" submitMode="current"></snow:dataset>
		
		
	<snow:query label="请输入查询条件" id="queryId" dataset="unionErr"
		fieldstr="qstlmDate,qtxnSsn"></snow:query>
		
	<snow:grid fitcolumns="true" dataset="unionErr" id="gridId"
		fieldstr="pan,stlmDate,transDateTime,orgTransAmt,amtTrans,errFlag2,errCode2,misc"
		paginationbar="detail,deal,mod,btImport" toolbar="toolbar"
	exporter="exporterId"></snow:grid>
		
	<snow:exporter dataset="unionErr" id="exporterId" type="XLS|CSV"
	fieldstr="pan,stlmDate,transDateTime,orgTransAmt,amtTrans,errFlag2,errCode2,misc"></snow:exporter>
	
	<snow:window id="detailInfo" title="差错详细信息" modal="true" closable="true" width="900">
		<snow:form id="detail" dataset="unionErr" border="true" label="原始交易信息" collapsible="true" colnum="4"
  				fieldstr="stlmDate,transDateTime,pan,orgTransAmt,amtTrans" >   
		</snow:form>
		<snow:grid id="oprlist" dataset="tempSec" fieldstr="instDate[100],instTime[100],updUser[100],sourceFlag[100],txnNo[100],errorCode[100],errAmtTrans[100],status[100]" fitcolumns="true" height="300" border="true" label="差错交易记录" pagination="false">
		</snow:grid> 
	</snow:window>
	
	<snow:window id="deal" title="处理完成" modal="true" closable="true" width="900" buttons="btCmt">
	
	
	<snow:form id="complete" dataset="errinf" label="差错处理" fieldstr="dateSettlmt,instDate,instTime,status,misc1" colnum="2"></snow:form>
	
		<snow:button id="btCmt" dataset="errinf" hidden="true"></snow:button>
	</snow:window>
	
	
	<snow:window id="mod" title="差错调整" modal="true" closable="true" width="900" buttons="btMod">
		<snow:form id="deal2" dataset="unionErr" border="true" label="差错调整" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="stlmDate2,transDateTime2,orgDateTime2,pan2,txnSsn2,mccCode,termId2,orgTransAmt2,amtTrans2,createTime,lstUpdTime,errFlag,errCode,errAtm,remark" >   
		</snow:form>
		<snow:button id="btMod" dataset="unionErr" hidden="true"></snow:button>
	</snow:window>	
	
	
	<script type="text/javascript">
	
	var iscallback = false;
	function btImport_onClick(){
		iscallback = false;
		$.open("importFile", '文件导入', "/pages/error/fileUpload.jsp", "750", "280", false, true, null, false,"确定,关闭");
	}
	
	function window_btImport_beforeClose(wmf) {
		unionErr_dataset.cancelRecord();
	}
	
	function importFile_onButtonClick(i,win,framewin){
		if(i==1){//取消按钮
			win.close();
			unionErr_dataset.flushData(unionErr_dataset.pageIndex);
		}else if(i==0){
			if(iscallback){
				win.close();
				unionErr_dataset.flushData(unionErr_dataset.pageIndex);
			}else{
				framewin.excuteImport();
			}
		}
	}
	
	function importFileCallBack(ret){
		iscallback = true;
		if(ret){
			unionErr_dataset.flushData(unionErr_dataset.pageIndex);
		}
	}
	
	function detail_onClick(){
		
		var ssn = unionErr_dataset.getValue("txnSsn");
		tempSec_dataset.setParameter("txnSsn",ssn);
		tempSec_dataset.flushData(tempSec_dataset.pageIndex);	
		window_detailInfo.open();
		
	}
	
	function deal_onClick(){
		
		var ssn = unionErr_dataset.getValue("txnSsn");
		errinf_dataset.setParameter("txnSsn",ssn);
		errinf_dataset.flushData(errinf_dataset.pageIndex);	
		window_deal.open();
		
	}
	
	function btCmt_onClickCheck(btCmt, commit) {
		$.confirm("确认提交？", function() {
			commit();
		}, function() {
		})
	}
	
	
	function btCmt_postSubmit() {
		$.success("处理完成!", function() {
			window_deal.close();
			unionErr_dataset.flushData(unionErr_dataset.pageIndex);
		});
	}
	
	function mod_onClick(){
// 		unionErr_dataset.flushData(unionErr_dataset.pageIndex);
		unionErr_dataset.setValue("errAtm","");
		unionErr_dataset.setValue("remark","");
		window_mod.open();
	}
	
	
	
	function btMod_onClickCheck(btMod, commit) {
		$.confirm("确认提交？", function() {
			var atm = unionErr_dataset.getValue("errAtm");
			var remark = unionErr_dataset.getValue("remark");
			unionErr_dataset.setParameter("errAtm",atm);
			unionErr_dataset.setParameter("remark",remark);
			commit();
		}, function() {
		})
	}
	
	function btMod_postSubmit() {
		$.success("提交成功!", function() {
			window_mod.close();
			unionErr_dataset.flushData(unionErr_dataset.pageIndex);
		});
	}
</script>
</snow:page>
