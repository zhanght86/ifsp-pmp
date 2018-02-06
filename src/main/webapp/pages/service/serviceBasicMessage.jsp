<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="服务机构管理">
	<!-- 服务机构信息数据集 -->
	<snow:dataset id="serviceBasicMessage"
		path="com.ruimin.ifs.pmp.service.dataset.serviceBasicMessage" init="true"
		submitMode="current"></snow:dataset>
	<!-- 内部机构服务机构关联信息数据集 -->
	<snow:dataset id="serviceInnerBrcodeService"
		path="com.ruimin.ifs.pmp.service.dataset.serviceInnerBrcodeService" init="true"
		submitMode="current"></snow:dataset>

	<!-- 查询栏 -->
	<snow:query dataset="serviceBasicMessage" label="查询条件"
		fieldstr="qserviceCode,qserviceName"></snow:query>
	<!-- 查询展示表单 -->
	<snow:grid id="gridMain" dataset="serviceBasicMessage" label="服务机构信息管理"
		fieldstr="serviceCode,serviceName,opr"
		height="99%" paginationbar="btnAdd,btnMod,btnDel,btnConnec"></snow:grid>

	<!-- 新增窗口 -->
	<snow:window id="windowAddService" title="服务机构新增" modal="true"
		closable="true" buttons="btnAddSave">
		<p style="">基本信息</p>
		<snow:form id="formAddService" dataset="serviceBasicMessage" border="false"
			label="基本信息"
			fieldstr="serviceCode,serviceName,splitType,splitValue,contactName,contactTel,contactAdd,zipCode"
			collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnAddSave" dataset="serviceBasicMessage" hidden="true"></snow:button>
	</snow:window>


	<!-- 修改窗口 -->
	<snow:window id="windowModifyService" title="服务机构修改" modal="true"
		closable="true" buttons="btnModSave">
		<p style="">基本信息</p>
		<snow:form id="formAddService" dataset="serviceBasicMessage" border="false"
			label="基本信息"
			fieldstr="serviceCode,serviceName,splitType,splitValue,contactName,contactTel,contactAdd,zipCode"
			collapsible="false" colnum="4"></snow:form>
		<snow:button id="btnModSave" dataset="serviceBasicMessage" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 查看详情窗口 -->
	<snow:window id="windowDetailService" title="服务机构详细信息" modal="true"
		closable="true" >
		<p style="">基本信息</p>
		<snow:form id="formDetailService" dataset="serviceBasicMessage" border="false"
			label="基本信息"
			fieldstr="serviceCode,serviceName,splitType,splitValue,contactName,contactTel,contactAdd,zipCode"
			 collapsible="false" colnum="4"></snow:form>
		<br />
		<p>代理商户</p>
		<snow:grid id="gridDetailMcht" dataset="serviceInnerBrcodeService"
			fieldstr="mchtId[300],mchtName[370]"
			height="99%" border="true" editable="true" fitcolumns="true"
			pagination="false" collapsible="true">
		</snow:grid>
	</snow:window>
	
		<!-- 关联窗口 -->
	<snow:window id="windowConnecService" title="关联配置" modal="true"
		closable="true" buttons="btnConnecSave">
		<p style="">基本信息</p>
 		<snow:form id="formConnectService" dataset="serviceBasicMessage" border="false" 
 			label="基本信息" 
			fieldstr="serviceCode,serviceName,splitType,splitValue,contactName,contactTel,contactAdd,zipCode"
 			collapsible="false" colnum="4"></snow:form> 
		<snow:button id="btnConnecSave" dataset="serviceBasicMessage" hidden="true"></snow:button>
	</snow:window>
	
	<div style="display: none;">
		<!-- 确认删除按钮 -->
		<snow:button id="btnDelSave" dataset="serviceBasicMessage"></snow:button>
		<snow:button id="btnConnecSave" dataset="serviceBasicMessage"></snow:button>
	</div>

	<script type="text/javascript">
	//------------------------------共用代码---------------------------
		//每次分润类型发生变化时清空分润值框体中的内容
		function serviceBasicMessage_dataset_splitType_onSelect(v, record) {
			serviceBasicMessage_dataset.setValue("splitValue", "");//每次模型改变时清空模型阈值中的数据	
			if (record != null) {
				return record;//反回选中的值，否则框体中无值
			}
		}
		
		/**0到100整数*/
		var isPercentNum = /^(\d{0,2}|100)$/;
		/**验证金额*/
		var tempMoney = /^([1-9][\d]{0,9}|[1-9][\d]{0,2}(\,[\d]{3})*|0)(\.[\d]{1,2})?$/;
		/**验证座机电话 */
		var isPhone = /^(\d{3,4}-)?\d{6,8}$/;
		/**验证手机电话 */
		var isCellPhone = /[1]+\d{10}$/;
		//当新增或修改提交时，校验输入信息
		function checkInput() {
			
			if (serviceBasicMessage_dataset.validate() == false) {
				return false;
			}
			var splitType = serviceBasicMessage_dataset.getValue("splitType");
			var splitValue = serviceBasicMessage_dataset.getValue("splitValue");
			var contactTel = serviceBasicMessage_dataset.getValue("contactTel");
			if (splitType == "01") {//比例分润
				if(!isPercentNum.test(splitValue)){
					$.warn("选择比例分润时,分润值只能填入0到100之间的整数");
					return false;
				}
			}
			if(splitType == "02"){//固定分润
				if(!tempMoney.test(splitValue)){
					$.warn("选择固定分润时,分润值只能填入小数点前最大为10位的数字，小数点后最大2位");
					return false;
				}
			}
			if(!(isPhone.test(contactTel) || (isCellPhone.test(contactTel)))){
				$.warn("【联系电话】格式错误，格式为【区号】（3或4位数字 + '-'）+ 6到8位数字，【区号】可以不输入，同时也支持11位手机号");
				return false;
			}
			return true;
		}

		//------------------------新增------------------------------------
		//新增按钮点击时，打开新增窗口
		function btnAdd_onClick() {
			window_windowAddService.open();
		}
		//新增提交按钮点击时，校验提交内容
		function btnAddSave_onClickCheck() {
			var result = checkInput();
			if (!result) {
				return;
			}
			return true;
		}
		//数据提交成功后进入
		function btnAddSave_postSubmit() {
			$.success("新增成功!", function() {
				window_windowAddService.close();
				serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
			});
		}

		//当新增窗口关闭时，取消记录
		function window_windowAddService_beforeClose(wmf) {
			serviceBasicMessage_dataset.cancelRecord();
		}

		//------------------------详情---------------------------
		function gridMain_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<i class='fa fa-info'></i>&nbsp;<a href=\"JavaScript:showDetails()\">详情</a>";
			}
		}
		//详情链接点击时
		function showDetails() {
			serviceInnerBrcodeService_dataset.setParameter("serviceCode",
					serviceBasicMessage_dataset.getValue("serviceCode"));
			serviceInnerBrcodeService_dataset
					.flushData(serviceInnerBrcodeService_dataset.pageIndex);
			//设置服务机构基本信息所有的字段只读
			serviceBasicMessage_dataset.setFieldReadOnly("serviceName", true);
			serviceBasicMessage_dataset.setFieldReadOnly("splitType", true);
			serviceBasicMessage_dataset.setFieldReadOnly("splitValue", true);
			serviceBasicMessage_dataset.setFieldReadOnly("contactName", true);
			serviceBasicMessage_dataset.setFieldReadOnly("contactTel", true);
			serviceBasicMessage_dataset.setFieldReadOnly("contactAdd", true);
			serviceBasicMessage_dataset.setFieldReadOnly("zipCode", true);
			window_windowDetailService.open();
		}

		//详情框体关闭后
		function window_windowDetailService_beforeClose(win) {
			serviceBasicMessage_dataset.setFieldReadOnly("serviceName", false);
			serviceBasicMessage_dataset.setFieldReadOnly("splitType", false);
			serviceBasicMessage_dataset.setFieldReadOnly("splitValue", false);
			serviceBasicMessage_dataset.setFieldReadOnly("contactName", false);
			serviceBasicMessage_dataset.setFieldReadOnly("contactTel", false);
			serviceBasicMessage_dataset.setFieldReadOnly("contactAdd", false);
			serviceBasicMessage_dataset.setFieldReadOnly("zipCode", false);
			serviceBasicMessage_dataset.cancelRecord();
			serviceInnerBrcodeService_dataset.cancelRecord();
		}

		//--------------------------------修改 ------------------------------
		//点击修改按钮，打开修改窗口
		function btnMod_onClick() {
			var serviceCode = serviceBasicMessage_dataset.getValue("serviceCode");
			if (serviceCode == '') {
				$.warn("请先选择要修改的服务机构信息");
				return false;
			}
			window_windowModifyService.open();
		}
		//修改提交按钮点击时，校验提交内容
		function btnModSave_onClickCheck() {
			var result = checkInput();
			if (!result) {
				return;
			}
			return true;
		}
		//修改成功之后
		function btnModSave_postSubmit() {
			$.success("修改成功!", function() {
				window_windowModifyService.close();
				serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
			});
		}
		//当修改框体关闭时，取消记录
		function window_windowModifyService_beforeClose(wmf) {
			serviceBasicMessage_dataset.cancelRecord();
		}
		
		//---------------------删除-----------------------------
		function btnDel_onClick(){ 
			var serviceCode = serviceBasicMessage_dataset.getValue("serviceCode");
			if(serviceCode == ''){
				$.warn("请先选择要删除的服务机构");
				return false;
			}
 			$.confirm("是否确认删除", function() { 
 				serviceBasicMessage_dataset.setParameter("serviceCode",serviceCode);
 				btnDelSave.click(); 
 			},function(){
 				return false;
 			}); 
 		} 
		//删除成功后
	    function btnDelSave_postSubmit(btn){
	    	$.success("操作成功!", function() {
	    		serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
			});
	    }
		//--------------------关联----------------------------
		function btnConnec_onClick(){
			var serviceCode = serviceBasicMessage_dataset.getValue("serviceCode");
			if(serviceCode == null || serviceCode == ""){
				$.warn("请选择一条需要关联的服务机构信息");
			}
			$.open("serviceConnect", "关联内部机构号", "/pages/service/serviceConnect.jsp?qserviceCode="+serviceCode,
					"1100", "800", false, true, null,false, "提交,返回");
		}
		 function serviceConnect_onButtonClick (i,win,framewin){
				if(i==1){//取消按钮
					win.close();
					serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
				}else if(i==0){
					var checkResult = framewin.submitCheck();
				}			
			}

		 function btnConnecSave_postSubmit() {
	 		$.success("操作成功!", function() {
	 			window_windowServiceConnect.close();
	 			serviceBasicMessage_dataset.flushData(serviceBasicMessage_dataset.pageIndex);
	 		});
		 }
	</script>
</snow:page>