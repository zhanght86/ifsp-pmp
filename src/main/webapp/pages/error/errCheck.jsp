<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="差错提交审核">
	<snow:dataset id="errCheck"
		path="com.ruimin.ifs.pmp.err.dataset.errCheck"
		init="true" submitMode="current"></snow:dataset>
		
	<snow:grid dataset="errCheck" id="gridId"
		fieldstr="seqNo[100],dateSettlmt[100],instDate[100],pan[200],amtTrans[100],errAmtTrans[100],status[100]"
		paginationbar="check,uncheck"></snow:grid>

	<script type="text/javascript">
	
	function check_onClickCheck(btn){
		errCheck_dataset.setParameter("seqNo",errCheck_dataset.getValue("seqNo"));
		return true;
// 		errCheck_dataset.flushData(errCheck_dataset.pageIndex);
	}
	
	function uncheck_onClickCheck(){
		errCheck_dataset.setParameter("seqNo",errCheck_dataset.getValue("seqNo"));
		return true;
// 		errCheck_dataset.flushData(errCheck_dataset.pageIndex);
	}
	function check_postSubmit() {
		$.success("审核通过!", function() {
			errCheck_dataset.flushData(errCheck_dataset.pageIndex);
		});
	}
	function uncheck_postSubmit() {
		$.success("审核不通过!", function() {
			errCheck_dataset.flushData(errCheck_dataset.pageIndex);
		});
	}
</script>
</snow:page>
