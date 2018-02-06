<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="支付产品">
	<!-- 1.产品信息数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.payProdMng.dataset.pbsProdInfo"
		id="pbsProdInfo" submitMode="current" init="true"></snow:dataset>
	<!-- 2.配置产品数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.payProdMng.dataset.confProdInfo"
		id="confProdInfo" submitMode="all" init="false"></snow:dataset> 
	<!-- 3.签约商户数据集 -->
	<snow:dataset id="mchtContract"
		path="com.ruimin.ifs.pmp.payProdMng.dataset.mchtContractNumber"
		init="false" parameters="apprFlag=1"></snow:dataset>


	<!-- 查询条件 -->
	<snow:query label="请输入查询条件" id="queryProd" dataset="pbsProdInfo"
		fieldstr="qprodId,qprodName,qprodState" border="false"></snow:query>
	<snow:grid dataset="pbsProdInfo" id="gridProdList" label="支付产品信息"
		fieldstr="prodId,prodName,openDate,prodState,oper" fitcolumns="true"
		height="99%"
		paginationbar="btnAdd,btnUpd,disableEnable,btnConfProdInfo"></snow:grid>

	<!-- 产品基本信息新增窗体 -->
	<snow:window id="windowAddId" title="产品新增" modal="true" closable="true"
		buttons="btnSave" width="900" height="650">
		<snow:form id="formAddProd" dataset="pbsProdInfo" border="true"
			label="产品基本信息" collapsible="true"
			fieldstr="prodName,prodState,prodDesc,accessTypeCode"></snow:form>
		<br/>
		<p style="">产品功能</p>
<%-- 		<snow:grid id="gridconfProdInfo" dataset="confProdInfo" --%>
<%-- 			fieldstr="txnTypeCode[220],acctTypeCode[550]" height="375" --%>
<%-- 			border="true" label="产品功能配置" toolbar="toolbarconfProdInfo" --%>
<%-- 			editable="true" fitcolumns="false" pagination="false" --%>
<%-- 			collapsible="true"> --%>
<%-- 		</snow:grid> --%>
		<snow:grid id ="gridconfProdInfo" dataset="confProdInfo" fieldstr="txnTypeCode[220],acctTypeCode[550]"
		height = "420" border = "true" label = "产品功能配置"  toolbar="toolbarconfProdInfo"
		editable="true" fitcolumns="false" pagination="false" collapsible="true"></snow:grid>
		<snow:toolbar id="toolbarconfProdInfo">
			<snow:button id="btnAddConfig" dataset="confProdInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="confProdInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="pbsProdInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 启用/停用按钮 -->
	<snow:button id="btnDisableEnable" dataset="pbsProdInfo" hidden="true"></snow:button>

	<!-- 产品修改窗体 -->
	<snow:window id="windowUptId" title="产品修改" modal="true" closable="true"
		buttons="btnUpdate" width="900" height="650">
		<snow:form id="formUptProd" dataset="pbsProdInfo" border="true"
			label="产品基本信息" collapsible="true"
			fieldstr="prodId,prodName,prodDesc,accessTypeCode"></snow:form>
		<br />
		<p style="">产品功能</p>
<%-- 		<snow:grid id="griduptProdInfo" dataset="confProdInfo" --%>
<%-- 			fieldstr="txnTypeCode[220],acctTypeCode[550]" height="375" --%>
<%-- 			border="true" label="产品功能修改" editable="true" fitcolumns="false" --%>
<%-- 			pagination="true" toolbar="uptConfProdInfo" collapsible="true"> --%>
<%-- 		</snow:grid> --%>
			<snow:grid id ="griduptProdInfo" dataset="confProdInfo" 
				fieldstr="txnTypeCode[220],acctTypeCode[550]" height = "420" 
				border = "true" label = "产品功能修改"  toolbar="uptConfProdInfo"
				editable="true" fitcolumns="false" pagination="false" collapsible="true"></snow:grid>
			<snow:toolbar id="uptConfProdInfo">
			<snow:button id="btnAddConfig" dataset="confProdInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="confProdInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		<!-- 按钮: 修改 -->
		<snow:button id="btnUpdate" dataset="pbsProdInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 产品详情窗口 -->
	<snow:window id="winPbsProdInfo" title="产品信息" modal="true"
		closable="true" buttons="" width="900" height="650">
		<snow:form id="formPbsProdInfo" dataset="pbsProdInfo" label="产品详情"
			border="false" collapsible="true"
			fieldstr="prodId,prodName,prodState,accessTypeCode,prodDesc">
		</snow:form>
		<!-- 签约商户数 -->
		<snow:form id="account" dataset="pbsProdInfo" label="签约产品商户数量"
			border="false" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="signedNum[200]">
		</snow:form>
		<br />
		<p style="">产品功能详情</p>
<%-- 		<snow:grid id="txnTypeInfoQueryByProdId" dataset="confProdInfo" --%>
<%-- 			fitcolumns="true" fieldstr="txnTypeCode[220],acctTypeCode[550]" --%>
<%-- 			height="400"> --%>
<%-- 		</snow:grid> --%>
			<snow:grid id="txnTypeInfoQueryByProdId" dataset="confProdInfo" 
						fieldstr="txn[220],acct[550]"
						height = "420" border = "true" label = "产品功能详情" 
						fitcolumns="false" pagination="false" editable="true"></snow:grid>
	</snow:window>

	<!-- 签约商户信息展示 -->
	<snow:window id="windowMchtInfo" title="签约信息" modal="true"
		closable="true" buttons="" width="900" height="400">
		<p style="">产品信息</p>
		<snow:form id="pbsProdInfo" dataset="pbsProdInfo" label="产品详情"
			border="false" collapsible="true" colnum="4" labelwidth="100"
			fieldstr="prodId,prodName">
		</snow:form>
		<br />
		<p style="">商户列表</p>
		<snow:grid id="mchtContract" dataset="mchtContract" fitcolumns="true"
			fieldstr="mchtId[400],mchtName[400]" height="210">
		</snow:grid>
	</snow:window>

	<script type="text/javascript">
		//产品信息操作
		function gridProdList_oper_onRefresh(record, fieldId, fieldValue) {
			return "<span style='width:100%;text-align:center' class='fa fa-eye'><a href=\"JavaScript:onClickPbsProdInfo()\">详情</a></span>";
		}
		/* =======================================STP1   产品信息新增=============================================== */
		//单击新增按钮
		function btnAdd_onClick() {
			//仅仅用于刷新数据使用。
			confProdInfo_dataset.setParameter("qprodId", "ABC");
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
			//打开新增窗体
			window_windowAddId.open();

		}
		//接入方式从新选择的时候，清空配置信息
		function pbsProdInfo_dataset_accessTypeCode_beforeOpen(dropDown,
				dropDownDataset) {
			//仅仅用于刷新数据使用
			confProdInfo_dataset.setParameter("qprodId", "ABC");
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
			//confProdInfo_dataset.clearData();
		}
		//当交易类型下拉选择框展开时,根据接入方式加载dataset
		function confProdInfo_dataset_txnTypeCode_beforeOpen(dropDown,
				dropDownDataset) {
			//获取接入方式
			var accessTypeCode = pbsProdInfo_dataset.getValue("accessTypeCode");
			//判断是否选择接入方式
			if (accessTypeCode == null || accessTypeCode == "") {
				$.warn("请先选择接入方式!");
				return false;
			}
			//传递查询条件显示结果
			dropDownDataset.setParameter("qaccessTypeCode", accessTypeCode);
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}
		
		//当选择交易类型时
		function confProdInfo_dataset_afterChange(dataset,field){
			if(field.fieldName == 'txnTypeCode'){
				dataset.setValue("acctTypeCode","");
				dataset.setValue("acctTypeCodeName","");
			}
		}

		//当商户类型下拉选择框展开时,根据交易类型加载dataset
		function confProdInfo_dataset_acctTypeCode_beforeOpen(dropDown,
				dropDownDataset) {
			//获取接入方式
			var txnTypeCode = confProdInfo_dataset.getValue("txnTypeCode");
			//判断是否选择接入方式
			if (txnTypeCode == null || txnTypeCode == "") {
				$.warn("请先选择交易类型!");
				return false;
			}
			//传递查询条件显示结果
			dropDownDataset.setParameter("qtxnTypeCode", txnTypeCode);
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}
		//点击保存按钮对数据进行校验
		function btnSave_onClickCheck(button, commit) {
			//获取产品名称
			var prodName = pbsProdInfo_dataset.getValue("prodName");
			//获取接入方式
			var accessTypeCode = pbsProdInfo_dataset.getValue("accessTypeCode");
			//获取产品描述
			var prodDesc = pbsProdInfo_dataset.getValue("prodDesc");
			//获取产品状态
			var prodState = pbsProdInfo_dataset.getValue("prodState");
			if (prodName == null || prodName == '') {
				$.warn("请输入产品名称！");
				return false;
			}
			if (prodState == null || prodState == '') {
				$.warn("请选择产品状态！");
				return false;
			}
			if (prodDesc == null || prodDesc == '') {
				$.warn("请输入产品描述！");
				return false;
			}
			if (accessTypeCode == null || accessTypeCode == '') {
				$.warn("请选择接入方式！");
				return false;
			}
			if (prodName.length > 21) {
				$.warn("产品名称超过最大长度【21】！");
				return false;
			}
			if (prodDesc.length > 42) {
				$.warn("产品描述超过最大长度【42】！");
				return false;
			}
			//对产品功能配置进行验证
			//获取交易类型
			var txnTypeCode = confProdInfo_dataset.getValue("txnTypeCode");
			if (txnTypeCode == null || txnTypeCode == '') {
				$.warn("请完善产品功能配置,至少有一条产品功能配置！");
				return false;
			}
			if (txnTypeCode != null || txnTypeCode != '') {
				//对配置进行重复验证      
				var firstRecord = confProdInfo_dataset.getFirstRecord();//获取产品配置第一行数据
				var exists = [];//存在的交易编号和 账户编号
				// 遍历
				while (firstRecord) {
					var txnTypeCode = firstRecord.getValue("txnTypeCode");
					for (var i = 0; i < exists.length; i++) {
						//console.info(exists[i]);
						if (txnTypeCode == exists[i].txnTypeCode) {
							$.warn("您配置的交易[ "
									+ firstRecord.getValue("txnTypeCodeName")
									+ "] 出现重复!");
							return false;
						}
					}
					var exist = {};
					exist["txnTypeCode"] = txnTypeCode;
					exists.push(exist);
					firstRecord = firstRecord.getNextRecord();
				}
			}
			return true;
		}
		//成功回调函数
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				//添加结束关闭新增窗口刷新数据
				window_windowAddId.close();
				pbsProdInfo_dataset.flushData(pbsProdInfo_dataset.pageIndex);
			});
		}
		//新增关闭窗口刷新
		function window_windowAddId_afterClose() {
			pbsProdInfo_dataset.flushData(pbsProdInfo_dataset.pageIndex);
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
		}

		/* =======================================STP2 停用启用功能=============================================== */
		//启用、停用产品信息单击事件
		function disableEnable_onClick() {
			var prodId = pbsProdInfo_dataset.getValue("prodId");
			var prodState = pbsProdInfo_dataset.getValue("prodState");
			if ('00' == prodState) {
				dwr.engine.setAsync(false);
				var data = MchtAccessRemove.remove(prodId,prodState);
				dwr.engine.setAsync(true);
					$.confirm("确定要停用吗?此产品下有"+data+"个商户", function() {
						btnDisableEnable.click();
					}, function() {
						return false;
					});
			}
			if ('02' == prodState) {
				$.confirm("确定要启用吗?", function() {
					btnDisableEnable.click();
				}, function() {
					return false;
				});
			}
			if ('99' == prodState) {
				$.confirm("确定要启用吗?", function() {
					btnDisableEnable.click();
				}, function() {
					return false;
				});
			}
		}
		function btnDisableEnable_postSubmit() {
			$.success("操作成功！");
			pbsProdInfo_dataset.flushData(pbsProdInfo_dataset.pageIndex);
		}
		/* =======================================STP3 商品修改功能=============================================== */
		//单击修改按钮
		function btnUpd_onClick() {
			var prodId = pbsProdInfo_dataset.getValue("prodId");
			if (prodId == '') {
				$.warn("请先选择要修改的产品");
				return;
			}
			//产品编号设置为只读，不予许修改
			pbsProdInfo_dataset.setFieldReadOnly("prodId", true);
			confProdInfo_dataset.setParameter("qprodId", prodId);
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
			//打开修改窗体
			window_windowUptId.open();
		}
		//修改产品保存按钮点击时，校验内容并提交请求
		function btnUpdate_onClickCheck() {
			//获取产品名称
			var prodName = pbsProdInfo_dataset.getValue("prodName");
			//获取接入方式
			var accessTypeCode = pbsProdInfo_dataset.getValue("accessTypeCode");
			//获取产品描述
			var prodDesc = pbsProdInfo_dataset.getValue("prodDesc");
			if (prodName == null || prodName == '') {
				$.warn("请输入产品名称！");
				return false;
			}
			if (prodDesc == null || prodDesc == '') {
				$.warn("请输入产品描述！");
				return false;
			}
			if (accessTypeCode == null || accessTypeCode == '') {
				$.warn("请选择接入方式！");
				return false;
			}
			if (prodName.length > 21) {
				$.warn("产品名称超过最大长度【21】！");
				return false;
			}
			if (prodDesc.length > 42) {
				$.warn("产品描述超过最大长度【42】！");
				return false;
			}
			//对产品功能配置进行验证
			var txnTypeCode = confProdInfo_dataset.getValue("txnTypeCode");
			if(txnTypeCode==null||txnTypeCode==''){
				$.warn("请配置产品功能！");
				return false;
			}
			if (txnTypeCode != null || txnTypeCode != '') {
				//对配置进行重复验证      
				var firstRecord = confProdInfo_dataset.getFirstRecord();//获取产品配置第一行数据
				var exists = [];//存在的交易编号和 账户编号
				// 遍历
				while (firstRecord) {
					var txnTypeCode = firstRecord.getValue("txnTypeCode");
					for (var i = 0; i < exists.length; i++) {
						//console.info(exists[i]);
						if (txnTypeCode == exists[i].txnTypeCode) {
							$.warn("您配置的交易[ "
									+ firstRecord.getValue("txnTypeCodeName")
									+ "] 出现重复!");
							return false;
						}
					}
					var exist = {};
					exist["txnTypeCode"] = txnTypeCode;
					exists.push(exist);
					firstRecord = firstRecord.getNextRecord();
				}
			}
			return true;
		}

		//修改产品 提交成功后
		function btnUpdate_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUptId.close();
				pbsProdInfo_dataset.flushData(pbsProdInfo_dataset.pageIndex);
			});
		}
		//修改关闭窗口刷新
		function window_windowUptId_afterClose() {
			pbsProdInfo_dataset.flushData(pbsProdInfo_dataset.pageIndex);
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
		}

		/* =======================================STP4 商品预览功能=============================================== */
		//详情相关操作
		function onClickPbsProdInfo() {
			pbsProdInfo_dataset.setFieldReadOnly("prodId", true);
			pbsProdInfo_dataset.setFieldReadOnly("prodName", true);
			pbsProdInfo_dataset.setFieldReadOnly("openDate", true);
			pbsProdInfo_dataset.setFieldReadOnly("prodState", true);
			pbsProdInfo_dataset.setFieldReadOnly("prodDesc", true);
			pbsProdInfo_dataset.setFieldReadOnly("accessTypeCode", true);

			//获取产品编号，查询对应下的交易类型和账户类型
			var prodId = pbsProdInfo_dataset.getValue("prodId");
			confProdInfo_dataset.setParameter("qprodId", prodId);
			confProdInfo_dataset.flushData(confProdInfo_dataset.pageIndex);
			confProdInfo_dataset.setFieldReadOnly("acct", true);
			confProdInfo_dataset.setFieldReadOnly("txn", true);

			window_winPbsProdInfo.open();
		}
		//签约商户数量超链接
		function editor_signedNum_onRefresh(element, v) {
			var prodId = pbsProdInfo_dataset.getValue("prodId");
			if (v != '' && v > 0) {
				return "<a href=\"JavaScript:onClickModify('" + prodId
						+ "')\"> " + v + " </a>";
			}
			return v;
		}
		//点击签约数量，显示签约的商户信息 
		function onClickModify(prodId) {
			mchtContract_dataset.setParameter("qprodId", prodId);
			mchtContract_dataset.flushData(mchtContract_dataset.pageIndex);
			window_windowMchtInfo.open();
		}
		//预览关闭窗口刷新
		function window_winPbsProdInfo_afterClose() {
			pbsProdInfo_dataset.setFieldReadOnly("prodId", false);
			pbsProdInfo_dataset.setFieldReadOnly("prodName", false);
			pbsProdInfo_dataset.setFieldReadOnly("prodState", false);
			pbsProdInfo_dataset.setFieldReadOnly("prodDesc", false);
			pbsProdInfo_dataset.setFieldReadOnly("openDate", false);
			pbsProdInfo_dataset.setFieldReadOnly("accessTypeCode", false);
			confProdInfo_dataset.setFieldReadOnly("acct", false);
			confProdInfo_dataset.setFieldReadOnly("txn", false);
			window_windowMchtInfo.close();
		}
	</script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/MchtAccessRemove.js"></script>

</snow:page>