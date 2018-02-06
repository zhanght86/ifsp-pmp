<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户证书管理">
	<script src="../../common/upload/js/ajaxfileupload.js"></script>

	<!-- 1.商户证书配置数据集 -->
	<snow:dataset id="MchtCert"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.MchtCert"
		submitMode="current" init="true"></snow:dataset>
	<!-- 2.查询 -->
	<snow:query label="查询条件" id="queryId" collapsible="false"
		border="false" dataset="MchtCert"
		fieldstr="qMchtId,qmchtSimpleName,qcertifiStatus"></snow:query>

	<!-- 3.主表单 -->
	<snow:grid dataset="MchtCert" height="99%" label="商户证书配置信息" fitcolumns="true" id="gridId"
		fieldstr="certifiBl,mchtSimpleName,certifiDate,certifiEndDate,certifiStatus,opr"
		paginationbar="btnAdd,btnUpt,btnStatus"></snow:grid>

	<!-- 3.新增 -->
	<snow:window id="windowAddId" title="新增" width="850" modal="true"
		closable="true" buttons="btnAddSave">
		<snow:form id="baseInfo1" dataset="MchtCert" border="false" label=""
			fieldstr="mchtId,certifiStatus,certifiDate,certifiEndDate">
			
		</snow:form>
		
		<snow:button id="btnImportAdd" dataset="MchtCert" hidden="false"></snow:button>
		
		<iframe id="uploadFileForm1" name="uploadFileForm"			
			src="" width="800" height="220">
		</iframe>
		<snow:button id="btnAddSave" dataset="MchtCert" hidden="true"></snow:button>
	</snow:window>

	<!-- 4.修改 -->
	<snow:window id="windowUpdateId" title="修改" width="810" modal="true"
		closable="true" buttons="btnUpdSave">
		<snow:form id="baseInfo2" dataset="MchtCert" border="false" label=""
			fieldstr="mchtId,certifiDate,certifiEndDate">
		</snow:form>
		
		<snow:button id="btnImportUpd" dataset="MchtCert" hidden="false"></snow:button>
		
		<iframe id="uploadFileForm2" name="uploadFileForm"			
			src="" width="800" height="220">
		</iframe>
		<snow:button id="btnUpdSave" dataset="MchtCert" hidden="true"></snow:button>
	</snow:window>

	<!-----5.状态修改配置框体 ----->
	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="MchtCert" hidden="true"></snow:button>
	</div>

	<!-- 6.详情窗口 -->
	<snow:window id="winDetail" title="详情信息" modal="true" closable="true">
		<!-- 基本信息模块 -->
		<snow:form id="mchtCertDetail" label="基本信息" dataset="MchtCert"
			fieldstr="mchtId,certName,certifiDate,certifiEndDate,certifiType">
		</snow:form>
	</snow:window>

	<script>
		/************************************* 上传证书 ***************************************/		
		// 接受证书文件路径
		function callBackCertPath(certPath) {
			if (certPath) {
				MchtCert_dataset.setValue("certifiPath",certPath);	
			}
		}
		
		// 接受返回的证书编号
		function callBackCertId(certifiId){
			if(certifiId == null || certifiId== ''){
				MchtCert_dataset.setValue("certifiId","");
			}else{
				MchtCert_dataset.setValue("certifiId",certifiId);
			}			
		}
		
		// 接受返回的证书文件编号
		function callBackCertCode(ret) {
			if (ret) {
				MchtCert_dataset.setValue("certifiCode",ret);
			}
		}
		
		
		// 保存前字段检查
		function checkField() {	
			var mchtId = MchtCert_dataset.getValue("mchtId");
			var certifiDate = MchtCert_dataset.getValue("certifiDate");
			var certifiEndDate = MchtCert_dataset.getValue("certifiEndDate");
			var certifiId = MchtCert_dataset.getValue("certifiId");
			
			if(certifiId == '' || certifiId == undefined || certifiId == null){
				$.error("证书未上传！");
				return false;
			}			
			if (mchtId == '' || mchtId == null) {
				$.warn("提示：商户编号不能为空!");
				return false;
			}
			
			if(certifiDate == null || certifiDate == ''){
				$.warn("提示：生效日期不能为空!");
				return false;
			}
					
			if(certifiEndDate == null || certifiEndDate == ''){
				$.warn("提示：失效日期不能为空!");
				return false;
			}
			
			// 生效日期不能大于失效日期
			if (certifiDate > certifiEndDate) {
				$.warn("提示：生效日期不能大于失效日期!");
				return false;
			}
			return true;
		}

		/************************************* 1.商户证书配置:新增 ***************************************/
		function btnAdd_onClick() {
			MchtCert_dataset.setFieldReadOnly("mchtId", false);
			MchtCert_dataset.setFieldReadOnly("certifiPasswd", false);
			MchtCert_dataset.setFieldReadOnly("certifiDate", false);
			MchtCert_dataset.setFieldReadOnly("certifiEndDate", false);
			MchtCert_dataset.setFieldReadOnly("certifiStatus", false);
			MchtCert_dataset.setFieldReadOnly("certifiPath", false);
			MchtCert_dataset.setFieldRequired("certifiPath", true);
			MchtCert_dataset.setFieldReadOnly("certName", false);
			window_windowAddId.open();
		}

		function window_windowAddId_beforeClose(wmf) {
			MchtCert_dataset.cancelRecord();
			$("#uploadFileForm1").attr("src","");
		}	

		/* 上传商户证书文件  */
		function btnAddSave_onClickCheck() {
			if (!checkField()) {
				return false;
			}
			return true;
		}

		// 上传文件按钮事件add
		function btnImportAdd_onClickCheck(){
			var mchtId = MchtCert_dataset.getValue("mchtId");
			if (mchtId == '' || mchtId == undefined || mchtId == null) {
				$.warn("上传证书前,请先选择商户号！");
				return false;
			}
			$("#uploadFileForm1").attr("src","../../payPmp/pubTool/importCer.jsp?certType=02");
		}
		
		function btnAddSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				MchtCert_dataset.flushData(MchtCert_dataset.pageIndex);
				$("#uploadFileForm1").attr("src","");
			});
		}

		/************************************* 2.商户证书配置:修改 ***************************************/
		// 在窗口打开前检查数据状态
		function window_windowUpdateId_beforeOpen() {
			var certifiStatus = MchtCert_dataset.getValue("certifiStatus");
			if (certifiStatus == "99") {
				$.warn("该条数据为停用状态，不能修改！");
				return false;
			}
			
			var certifiType = MchtCert_dataset.getValue("certifiType");
			if(certifiType != "01"){
				$.warn("该条数据的证书类型不是公钥证书,不能进行修改操作！");
				return false;
			}
			return true;
		}

		function btnUpt_onClick() {
			MchtCert_dataset.setFieldReadOnly("mchtId", true);
			MchtCert_dataset.setFieldReadOnly("certifiPasswd", false);
			MchtCert_dataset.setFieldReadOnly("certifiDate", false);
			MchtCert_dataset.setFieldReadOnly("certifiEndDate", false);
			MchtCert_dataset.setFieldReadOnly("certifiStatus", false);
			MchtCert_dataset.setFieldReadOnly("certifiPath", false);
			MchtCert_dataset.setFieldRequired("certifiPath", true);
			MchtCert_dataset.setFieldReadOnly("certName", false);
			window_windowUpdateId.open();
		}
		
		// 上传文件按钮事件upd
		function btnImportUpd_onClickCheck(){
			var mchtId = MchtCert_dataset.getValue("mchtId");
			if (mchtId == '' || mchtId == undefined || mchtId == null) {
				$.warn("上传证书前,请先选择商户号！");
				return false;
			}
			$("#uploadFileForm2").attr("src","../../payPmp/pubTool/importCer.jsp?certType=02");
		}
		
		// 窗口关闭清除上传证书JSP
		function window_windowUpdateId_beforeClose(wmf) {
			MchtCert_dataset.cancelRecord();
			$("#uploadFileForm2").attr("src","");
		}
		
		// 修改保存前进行检查
		function btnUpdSave_onClickCheck() {
			if (!checkField()) {
				return false;
			}
			return true;
		}
		function btnUpdSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUpdateId.close();
				MchtCert_dataset.flushData(MchtCert_dataset.pageIndex);
				$("#uploadFileForm2").attr("src","");
			});
		}

		/*********************************** 3.商户证书配置:启用/停用 ************************************/
		function btnStatusSub_needCheck() {
			return false;
		}
		function btnStatus_onClick() {
			var dataState = MchtCert_dataset.getValue("certifiStatus");
			var msg = "";
			if (dataState == "01") {
				msg = "是否要将该商户证书状态修改为【启用】?";
				dataState = "00";
			} else {
				msg = "是否要将该商户证书状态修改为【停用】?";
				dataState = "01";
			}
			$.confirm(msg, function() {
				MchtCert_dataset.setParameter("certifiStatus", dataState);
				btnStatusSub.click();
			}, function() {
				return false;
			});
		}
		
		// 修改保存前进行检查
		function btnUpdSave_onClickCheck() {
			if (!checkField()) {
				return false;
			}
			return true;
		}
		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				MchtCert_dataset.flushData(MchtCert_dataset.pageIndex);
			});
		}

		/************************************** 4.详情  ******************************************/
		/**详情显示*/
		function gridId_opr_onRefresh(record) {
			if (record) {
				return "<i class='fa fa-search'></i>&nbsp;<a href=\"JavaScript:onClickDetail()\">详情</a>";
			}
		}

		/**打开详情窗口*/
		function onClickDetail() {
			MchtCert_dataset.setReadOnly(true);//全部字段设置为-只读	
			//打开窗口
			window_winDetail.open();
		}
	</script>
</snow:page>