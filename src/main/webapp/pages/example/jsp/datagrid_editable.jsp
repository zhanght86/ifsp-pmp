<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="表格">
	<snow:dataset id="GridFields" path="GridFields" init="true"></snow:dataset>
	<snow:form id="f" dataset="GridFields" label="" fieldstr=""></snow:form>
	<snow:grid dataset="GridFields" id="grid" label="可编辑表格" editable="true"  fitcolumns="false" fieldstr="r1,r2,select,select2,f1,f2,f3,n1,n2,n3,s1,s2,s4,s5,c1,c2,c3,c4,c5,d1,d2" exporter="xx">
	</snow:grid>
	<script>
		function grid_col1_onRefresh(record, fid) {
			return "<a href='#'>No." + record.getValue(fid) + "</a>";
		}
	</script>

</snow:page>
