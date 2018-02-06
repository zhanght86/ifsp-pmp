<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量参数设置">
	<snow:dataset id="procStepDs" path="com.ruimin.ifs.mng.dataset.batch.BhProcStep" init="true" submitMode="current"></snow:dataset>
	<snow:grid dataset="procStepDs" id="stepgrid" fieldstr="id,jobno,step,subStep,processFunction,runtime,maxproc,opr[100]" paginationbar="btNew"></snow:grid>
	<snow:window id="procAdd" closable="true" title="批量信息" modal="true" width="850"  buttons="btSave">
		<snow:form id="procform" dataset="procStepDs" label="" border="false" fieldstr="jobno,step,subStep,processFunction,processParam,processTlrno,runtime,subFlag,reportFlag,maxproc,tempFlag,suspend,repeatCnt,desc0,desc1,desc2"></snow:form>
		<snow:button id="btSave" dataset="procStepDs" hidden="true"></snow:button>
	</snow:window>

	<snow:button id="btDelete" dataset="procStepDs" hidden="true"></snow:button>
	<script type="text/javascript">
		function btNew_onClick() {
			btSave.setHidden(false);
			procStepDs_dataset.setReadOnly(false);
			window_procAdd.open();
		}

		function window_procAdd_beforeClose(wmf) {
			procStepDs_dataset.cancelRecord();
		}
		function btSave_postSubmit() {
			$.success("操作成功!", function() {
				window_procAdd.close();
				procStepDs_dataset.flushData(procStepDs_dataset.pageIndex);
			});
		}
		//当系统刷新单元格的内容时被触发
		function stepgrid_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {//当存在记录时
				return "<a href='JavaScript:showEdit(" + fieldValue + ")'>编辑</a> &nbsp; <a href='JavaScript:void(0);' onclick='doDelete(" + fieldValue + ");'>删除</a>";
			} else {//当不存在记录时
				return "&nbsp;";
			}
		}

		function showEdit(id) {
			btSave.setHidden(false);
			procStepDs_dataset.setReadOnly(false);
			window_procAdd.open();
		}

		function stepgrid_onDblClick() {
			procStepDs_dataset.setReadOnly(true);
			//btSave.style.display = "none";
			btSave.setHidden(true);
			window_procAdd.open();
		}

		//删除
		function doDelete(id) {
			$.confirm("确认将该机构设置为有效?", function() {
				btDelete.click();
			});
		}
		function btDelete_needCheck(button) {
			return false;
		}
		function btDelete_postSubmit() {
			$.success("操作成功!", function() {
				procStepDs_dataset.flushData(procStepDs_dataset.pageIndex);
			});
		}
	</script>
</snow:page>
