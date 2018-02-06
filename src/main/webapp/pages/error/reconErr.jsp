<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="对账差错">
	<snow:dataset id="reconErr"
		path="com.ruimin.ifs.pmp.err.dataset.reconErr"
		init="true" submitMode="current"></snow:dataset>
		
		
	<snow:query label="请输入查询条件" id="queryId" dataset="reconErr"
		fieldstr="startDate,endDate"></snow:query>  
		
	<snow:grid dataset="reconErr" id="gridId"
		fieldstr="stlmDate,tranDateTime,cardNo,txnAmt,mchtId,termId,settFlag"
		paginationbar="errStart"
		></snow:grid>
		
		
		<snow:window id="errStart" title="差错调整" modal="true" closable="true" width="900" buttons="btCmt">
		<snow:form id="deal2" dataset="reconErr" border="true" label="差错调整" collapsible="true" colnum="4" labelwidth="100"
  				fieldstr="stlmDate,tranDateTime,hostSsn,tpamSsn,txnAmt,tpamStlmAmt,mchtId,termId,errFlag1,errCode1,errAtm,remark" >   
		</snow:form>
		<snow:button id="btCmt" dataset="reconErr" hidden="true"></snow:button>
	</snow:window>	
		
	
	<script type="text/javascript">

	function errStart_onClick(){
		reconErr_dataset.setValue("errAtm","");
		reconErr_dataset.setValue("remark","");
		reconErr_dataset.setValue("errFlag1","");
		reconErr_dataset.setValue("errCode1","");
		window_errStart.open();
	}
	function btCmt_onClickCheck(btCmt, commit) {
			var atm = reconErr_dataset.getValue("errAtm");
			var remark = reconErr_dataset.getValue("remark");
			var errCode1 = reconErr_dataset.getValue("errCode");
			var errFlag1 = reconErr_dataset.getValue("errFlag2");
			reconErr_dataset.setParameter("errAtm",atm);
			reconErr_dataset.setParameter("remark",remark);
			reconErr_dataset.setParameter("errCode",errCode1);
			reconErr_dataset.setParameter("errFlag",errFlag1);
			commit();
	}
	
	function btCmt_postSubmit() {
		$.success("提交成功!", function() {
			window_errStart.close();
			reconErr_dataset.flushData(reconErr_dataset.pageIndex);
		});
	}
	
</script>
</snow:page>
