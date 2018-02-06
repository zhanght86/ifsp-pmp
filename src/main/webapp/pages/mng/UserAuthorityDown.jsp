<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="人员权限查询">
	<snow:dataset id="UserAuthorityDown" path="com.ruimin.ifs.mng.dataset.UserAuthorityDown" init="true"></snow:dataset>
	<snow:query label="查询条件" id="queryId" dataset="UserAuthorityDown" collapsible="false" fieldstr="ptrlno,ptrlname"></snow:query>
	<snow:grid dataset="UserAuthorityDown" label="操作员信息" height="99%" fitcolumns="true" id="gridId" fieldstr="tlrno,tlrName,roleName" paginationbar="btnExport"></snow:grid>	
	<script>
		var param = "";
		function gridId_tlrno_onRefresh(record, fieldId, fieldValue){ 
			if(record){
				var tlrno = record.getValue("tlrno");
				param += tlrno + ";";
				return tlrno;
			} 
		}
		function btnExport_needCheck(btn){
			return false;
		}
		function btnExport_onClickCheck(btn){
			var url = '<snow:url flowId="com.ruimin.ifs.mng.comp.UserAuthorityDownAction:exportExcel" />';
			url += "&param=" + param;
			window.location.href = url;
		}
	</script>
</snow:page>
