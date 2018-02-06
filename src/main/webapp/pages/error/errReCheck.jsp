<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="差错提交复核">
	<snow:dataset id="errReCheck"
		path="com.ruimin.ifs.pmp.err.dataset.errReCheck"
		init="true" submitMode="current"></snow:dataset>
		
	<snow:grid dataset="errReCheck" id="gridId"
		fieldstr="txnNum[100],stlmDate[100],transDateTime[100],pan[200],orgTransAmt[100],amtTrans[100]"
		paginationbar="mod"></snow:grid>

	<script type="text/javascript">
	
	
	function mod_postSubmit() {
		$.success("审核成功!", function() {
		});
	}
</script>
</snow:page>
