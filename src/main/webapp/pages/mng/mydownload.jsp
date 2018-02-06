<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量导出下载任务">

	<snow:dataset id="mydownload" path="com.ruimin.ifs.mng.dataset.BatchExpTask" init="true"></snow:dataset>
	<snow:query id="query" label="请输入查询条件" dataset="mydownload" fieldstr="tskNm,tskStartTms,tskStat"></snow:query>
	<snow:grid dataset="mydownload" id="grid" fitcolumns="true" fieldstr="fileNm,tskStartTms[130],tskEndTms[130],expRcdSumNum,expFileSize,tskStat[60],op[100]"></snow:grid>
	<iframe name="dfrm" id="dfrm" height="0" width="0" src="" style="display: none"></iframe>
	<script>
		function grid_op_onRefresh(record, fid, value) {
			if (record) {//当存在记录时
				if (record.getValue("tskStat") == "3") {
					return "<a href='JavaScript:download(\"" + value + "\")' title='下载'><i class='fa fa-download'></i></a> ";
				} else {
					return "<a href='JavaScript:void(0)' title='无法下载'><i class='fa fa-ban'></i></a> ";
				}
			}
		}
		function download(id) {
			var url = "<snow:url flowId="com.ruimin.ifs.mng.comp.BatchExpTaskAction:download" />" + "&oid=" + id;
			document.getElementById('dfrm').src = url;
		}
	</script>
</snow:page>
