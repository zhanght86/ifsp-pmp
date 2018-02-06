<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道银行配置">
	<!-----通道银行配置: 通道银行数据集 ----->
	<snow:dataset id="passbank"
		path="com.ruimin.ifs.pmp.sysConf.dataset.PassBank" init="true"
		submitMode="current">
	</snow:dataset>

	<!-----通道银行配置: 通道信息数据集 ----->
	<snow:dataset id="passinfo"
		path="com.ruimin.ifs.pmp.sysConf.dataset.PassInfo" init="false"
		parameters="showvir=true">
	</snow:dataset>

	<!-----配置查询框体 ----->
	<snow:query id="querybtn" label="查询条件" dataset="passbank"
		collapsible="true" fieldstr="qPassInfo,qBankCode,qBankName"
		border="false"></snow:query>

	<!-----配置列表框体 ----->
	<snow:grid id="passbanklist" label="通道银行配置信息" dataset="passbank"
		height="99%" fitcolumns="true" fieldstr="passNo,passName,bankNo,bankName,dataState"
		paginationbar="btAdd,btUpt,btnStatus"></snow:grid>

	<!-----新增配置框体 ----->
	<snow:window id="winAddInfo" closable="true" title="通道银行配置新增"
		modal="true" buttons="btAddSave">
		<snow:form id="formAddInfo" dataset="passbank" label="" border="false"
			fieldstr="passNoList,bankNo,bankName"></snow:form>
		<br />
		<snow:button id="btAddSave" dataset="passbank" hidden="true"></snow:button>
	</snow:window>

	<!-----修改配置框体 ----->
	<snow:window id="winUpdInfo" closable="true" title="通道银行配置修改"
		modal="true" buttons="btUpdSave">
		<snow:form id="formUpdInfo" dataset="passbank" label="" border="false"
			fieldstr="passNoList,bankNo,bankName"></snow:form>
		<br />
		<snow:button id="btUpdSave" dataset="passbank" hidden="true"></snow:button>
	</snow:window>

	<!-----状态修改配置框体 ----->
	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="passbank"></snow:button>
	</div>
	<script language="javascript">
		// 新增通道银行配置
		function btAdd_onClick() {
			passbank_dataset.setFieldReadOnly("passNoList",false);
			passbank_dataset.setFieldReadOnly("bankNo",false);
			window_winAddInfo.open();
		}
		function window_winAddInfo_beforeClose(wmf) {
			passbank_dataset.cancelRecord();
		}
		function btAddSave_postSubmit(btn) {
			$.success("操作成功!", function() {
				window_winAddInfo.close();
				passbank_dataset.flushData(passbank_dataset.pageIndex);
			});
		}

		// 修改通道银行配置
		function btUpt_onClick() {
			passbank_dataset.setFieldReadOnly("passNoList",true);
			passbank_dataset.setValue("passNoList",passbank_dataset.getValue("passNo"));
			passbank_dataset.setFieldReadOnly("bankNo",true);
			window_winUpdInfo.open();
		}
		function window_winUpdInfo_beforeClose(wmf) {
			passbank_dataset.cancelRecord();
		}
		function btUpdSave_postSubmit(btn) {
			$.success("操作成功!", function() {
				window_winUpdInfo.close();
				passbank_dataset.flushData(passbank_dataset.pageIndex);
			});
		}

		// 启用/停用通道银行配置
		function btnStatusSub_needCheck() {
			return false;
		}

		function btnStatus_onClick() {
			var dataState = passbank_dataset.getValue("dataState");
			var msg = "";
			if (dataState == "99") {
				msg = "是否要将该交易类型状态修改为【启用】?";
				dataState = "00";
			} else {
				msg = "是否要将该交易类型状态修改为【停用】?";
				dataState = "99";
			}
			$.confirm(msg, function() {
				passbank_dataset.setParameter("dataState", dataState);
				btnStatusSub.click();
			}, function() {
				return false;
			});
		} 

		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				passbank_dataset.flushData(passbank_dataset.pageIndex);
			});
		}
	</script>
</snow:page>
