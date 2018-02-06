<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="风控管理">
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
	<!-- 风控主要信息数据集 -->
	<snow:dataset id="riskInfo"
		path="com.ruimin.ifs.pmp.risk.dataset.riskMng" init="true"
		submitMode="current"></snow:dataset>
	<!-- 风控阈值操作数据集 -->
	<snow:dataset id="riskValueOperate"
		path="com.ruimin.ifs.pmp.risk.dataset.riskValueOperate"
		submitMode="all"></snow:dataset>
	<!-- 风控操作数据集 -->
	<snow:dataset id="riskOperate"
		path="com.ruimin.ifs.pmp.risk.dataset.riskOperate"
		submitMode="current"></snow:dataset>

	<!-- 查询栏 -->
	<snow:query dataset="riskInfo" label="查询条件"
		fieldstr="qriskId,qriskName,qriskStauts,qprodId"></snow:query>
	<!-- 查询展示表单 -->
	<snow:grid id="gridMain" dataset="riskInfo" label="风控管理"
		fieldstr="riskId,riskName,riskStauts,openDate,endDate,opr"
		height="99%" paginationbar="btnAdd,btnMod,btnStatus"></snow:grid>

	<!-- 新增风控窗口 -->
	<snow:window id="windowAddARisk" title="风控新增" modal="true"
		closable="true" buttons="btnAddSave">
		<p style="">基本信息</p>
		<snow:form id="formAddRisk" dataset="riskInfo" border="false"
			label="基本信息"
			fieldstr="riskId,riskName,riskStauts,openDate,endDate,riskModelId,actionBitmap,valueId,prodId"
			collapsible="false" colnum="4"></snow:form>
		<br />
		<p>风控配置</p>
		<snow:grid id="gridAddOperate" dataset="riskValueOperate"
			fieldstr="valueId[100],param1[100],param2[100],param3[100],value[600]"
			height="99%" border="true" editable="true" fitcolumns="true"
			pagination="false" collapsible="true">
		</snow:grid>
		<snow:button id="btnAddSave" dataset="riskInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 查看详情窗口 -->
	<snow:window id="windowDetailRisk" title="风控详情" modal="true"
		closable="true" >
		<p style="">基本信息</p>
		<snow:form id="formDetailRisk" dataset="riskInfo" border="false"
			label="基本信息"
			fieldstr="riskId,riskName,riskStauts,openDate,endDate,riskModelId,actionBitmap,valueId,prodId"
			collapsible="false" colnum="4"></snow:form>
		<p>风控配置</p>
		<snow:grid id="gridDetailOperate" dataset="riskValueOperate"
			fieldstr="valueId[100],param1[100],param2[100],param3[100],value[600]"
			height="99%" border="true" editable="true" fitcolumns="true"
			pagination="false" collapsible="true">
		</snow:grid>
	</snow:window>

	<!-- 修改窗口 -->
	<snow:window id="windowModifyRisk" title="风控修改" modal="true"
		closable="true" buttons="btnModSave">
		<p style="">基本信息</p>
		<snow:form id="formAddRisk" dataset="riskInfo" border="false"
			label="基本信息"
			fieldstr="riskId,riskName,riskStauts,openDate,endDate,riskModelId,actionBitmap,valueId,prodId"
			collapsible="false" colnum="4"></snow:form>
		<br />
		<p>风控配置</p>
		<snow:grid id="gridModifyOperate" dataset="riskValueOperate"
			fieldstr="valueId[100],param1[100],param2[100],param3[100],value[600]"
			height="99%" border="true" editable="true" fitcolumns="true"
			pagination="false" collapsible="true">
		</snow:grid>
		<snow:button id="btnModSave" dataset="riskInfo" hidden="true"></snow:button>
	</snow:window>
	<div style="display: none;">
		<!-- 确认启用/停用按钮 -->
		<snow:button id="btnStatusSave" dataset="riskInfo"></snow:button>
	</div>

	<script type="text/javascript">
		//---------转换结束日期格式显示--------------------
		function gridMain_endDate_onRefresh(record, fieldId, fieldValue) {
			var endDate = record.getValue("endDate");
			if (endDate == null || endDate == "") {
				return "——";
			} else {
				return fieldValue.substr(0, 4) + "-" + fieldValue.substr(4, 2)
						+ "-" + fieldValue.substr(6);
			}
		}
		//------------------------新增------------------------------------
		//新增按钮点击时，打开新增窗口
		function btnAdd_onClick() {
			riskInfo_dataset.setFieldReadOnly("actionBitmap", true);
			riskInfo_dataset.setFieldReadOnly("valueId", true);
			riskInfo_dataset.setFieldReadOnly("riskStauts", false);
			 //$("#gridAddOperategrid").css("display", "none");
			 $("#gridAddOperate").css("display", "none");
			window_windowAddARisk.open();
		}
		//当模型选择下拉框打开之前
		function riskInfo_dataset_riskModelId_beforeOpen(dropDown, dropDownDataset){
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("qstatus","01");//只查询状态不是未使用的模型信息
			//dropDownDataset.setParameter("qstatus","00,01");//只查询状态不是停用的模型信息
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}
		//当模型选择发生变化时
		function riskInfo_dataset_riskModelId_onSelect(v, record) {
			riskInfo_dataset.setValue("valueId", "");//每次模型改变时清空模型阈值中的数据
			riskInfo_dataset.setValue("valueIdName", "");//每次模型改变时清空模型阈值中的数据
			riskInfo_dataset.setValue("actionBitmap", "");//每次模型改变时清空风控动作中的数据
			riskInfo_dataset.setFieldReadOnly("valueId", true);
			 //$("#gridAddOperategrid").css("display", "block");
			 $("#gridAddOperate").css("display", "block");
			if (record != null) {
				riskInfo_dataset.setFieldReadOnly("valueId", false);
				riskInfo_dataset.setValue("actionBitmap", record
						.getValue("actionBitmap"));
				return record;//反回选中的值，否则框体中无值
			}
		}
		//当模型阈值选择的下拉选择框展开时,根据所选模型加载dataset
		function riskInfo_dataset_valueId_beforeOpen(dropDown, dropDownDataset) {
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("riskModelId", riskInfo_dataset
					.getValue("riskModelId"));
			dropDownDataset.flushData(dropDownDataset.pageIndex);	
			
		}
		//当数据集发生变化时
		function riskInfo_dataset_afterChange(dataset, field) {
			if (field.fieldName == "valueId") {//判断是不是模型阈值变化了

				riskValueOperate_dataset.setFieldReadOnly("valueId", true);//风控配置阈值编号无法编辑
				riskValueOperate_dataset.setParameter("valueId",
						riskInfo_dataset.getValue("valueId"));
				riskValueOperate_dataset.setParameter("riskId",
						riskInfo_dataset.getValue("riskId"));
				riskValueOperate_dataset.setParameter("riskModelId", riskInfo_dataset
						.getValue("riskModelId"));
				riskValueOperate_dataset.setParameter("sign", "addSign");//标识符，代表新增
				riskValueOperate_dataset
						.flushData(riskValueOperate_dataset.pageIndex);
			}
		}

		function riskValueOperate_dataset_param1_beforeOpen(dropDown,
				dropDownDataset) {
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("actionBitmap", riskInfo_dataset
					.getValue("actionBitmap"));
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}
		function riskValueOperate_dataset_param2_beforeOpen(dropDown,
				dropDownDataset) {
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("actionBitmap", riskInfo_dataset
					.getValue("actionBitmap"));
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}
		function riskValueOperate_dataset_param3_beforeOpen(dropDown,
				dropDownDataset) {
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("actionBitmap", riskInfo_dataset
					.getValue("actionBitmap"));
			dropDownDataset.flushData(dropDownDataset.pageIndex);
		}

		//新增提交按钮点击时，校验提交内容
		function btnAddSave_onClickCheck() {
			var result = checkInput();
			if (!result) {
				return;
			}
			var resultValue = checkInputValue();
			if(!resultValue){
				return;	
			}
			return true;
		}

		//当新增或修改提交时，校验输入信息
		function checkInput() {
			if (riskInfo_dataset.validate() == false) {
				return false;
			}
			var riskName = riskInfo_dataset.getValue("riskName");
			if (riskName.length > 60) {
				$.warn("风控名称最大长度只能是60位");
				return false;
			}
			var openDate = riskInfo_dataset.getValue("openDate");
			var endDate = riskInfo_dataset.getValue("endDate");
			if (openDate != "" && endDate != "") {
				if (openDate > endDate) {
					$.warn("开始日期不能早于结束日期");
					return false;
				}
				if (Date.parse(endDate) < Date.parse(new Date())) {
					$.warn("结束日期必须大于当天");
					return false;
				}
			}
			return true;
		}
		//新增修改时判断模型下面的阈值条数,每个等级最多只能有一个警告/拒绝
		function checkInputValue(){
			var actionBitmap = riskInfo_dataset.getValue("actionBitmap");//风控动作
			//一条模型下面只能配置最多3条阈值
			if(riskInfo_dataset.getValue("valueId").split(",").length > 3){
				$.warn("一个模型下面最多选择3条阈值,请修改数据");
				return false;
			}
			var firstRecord = riskValueOperate_dataset.getFirstRecord();
			if(!riskValueOperate_dataset.validate()){
				return false;
			}
			var param1Array = new Array();
			var param2Array = new Array();
			var param3Array = new Array();
			var errMes = "同一等级下只能允许存在一个拒绝动作,请修改数据";
			var nameString = "01";//01代表事中风控的拒绝操作
			if(actionBitmap == '01'){//事后风控
				errMes = "同一等级下只能允许存在一个警告动作,请修改数据";
				nameString = "03";//03代表事后风控中的警告
			}
			while(firstRecord){
				var param1 = firstRecord.getValue("param1");
				var param2 = firstRecord.getValue("param2");
				var param3 = firstRecord.getValue("param3");
				//事后风控/事中风控相同等级下的阈值动作只能有一个警告/拒绝
				if(param1 == nameString ){//等级1
					if($.inArray(param1,param1Array) != -1){
						$.warn(errMes);
	 					return false;
	 				}else{
	 					param1Array.push(param1); 
	 				}
				}
				if(param2 == nameString ){//等级2
					if($.inArray(param2,param2Array) != -1){
						$.warn(errMes);
	 					return false;
	 				}else{
	 					param2Array.push(param2); 
	 				}
				}
				if(param3 == nameString ){//等级3
					if($.inArray(param3,param3Array) != -1){
						$.warn(errMes);
	 					return false;
	 				}else{
	 					param3Array.push(param3); 
	 				}
				}
 				firstRecord = firstRecord.getNextRecord();
			}
			return true;
		}
		
		//数据提交成功后进入
		function btnAddSave_postSubmit() {
			$.success("新增成功!", function() {
				window_windowAddARisk.close();
				riskInfo_dataset.flushData(riskInfo_dataset.pageIndex);
			});
		}

		//当新增窗口关闭时，取消记录
		function window_windowAddARisk_beforeClose(wmf) {
			riskInfo_dataset.cancelRecord();
			riskValueOperate_dataset.cancelRecord();
		}

		//------------------------详情---------------------------
		function gridMain_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<i class='fa fa-info'></i>&nbsp;<a href=\"JavaScript:showDetails()\">详情</a>";
			}
		}
		//详情链接点击时
		function showDetails() {
			riskValueOperate_dataset.setParameter("riskId", riskInfo_dataset
					.getValue("riskId"));
			riskValueOperate_dataset.setParameter("riskModelId", riskInfo_dataset
					.getValue("riskModelId"));
			riskValueOperate_dataset.setParameter("sign", "selectSign");//查询标识
			riskInfo_dataset.setReadOnly(true);//全部设置只读
			riskValueOperate_dataset.setFieldReadOnly("valueId", true);
			riskValueOperate_dataset.setFieldReadOnly("param1", true);
			riskValueOperate_dataset.setFieldReadOnly("param2", true);
			riskValueOperate_dataset.setFieldReadOnly("param3", true);

			riskValueOperate_dataset
					.flushData(riskValueOperate_dataset.pageIndex);
			window_windowDetailRisk.open();
		}

		//详情框体关闭后
		function window_windowDetailRisk_beforeClose(win) {
			riskInfo_dataset.setReadOnly(false);//全部设置只读
			riskValueOperate_dataset.setFieldReadOnly("valueId", false);
			riskValueOperate_dataset.setFieldReadOnly("param1", false);
			riskValueOperate_dataset.setFieldReadOnly("param2", false);
			riskValueOperate_dataset.setFieldReadOnly("param3", false);
			riskValueOperate_dataset.cancelRecord();
			riskInfo_dataset.cancelRecord();
		}

		//--------------------------------修改 ------------------------------
		//点击修改按钮，打开修改窗口
		function btnMod_onClick() {
			var riskId = riskInfo_dataset.getValue("riskId");
			if (riskId == '') {
				$.warn("请先选择要修改的风控信息");
				return false;
			}
			var riskStauts = riskInfo_dataset.getValue("riskStauts");
			if(riskStauts == "00"){
				 $.warn("无法修改使用中的风控");
				return;
			}
			riskValueOperate_dataset.setParameter("riskId", riskInfo_dataset
					.getValue("riskId"));
			riskValueOperate_dataset.setParameter("riskModelId", riskInfo_dataset
					.getValue("riskModelId"));
			riskValueOperate_dataset.setParameter("sign", "selectSign");//查询标识addSign

			riskInfo_dataset.setFieldReadOnly("riskId", true);
			riskInfo_dataset.setFieldReadOnly("riskStauts", true);
			riskInfo_dataset.setFieldReadOnly("actionBitmap", true);

			riskValueOperate_dataset.setFieldReadOnly("valueId", false);
			riskValueOperate_dataset
					.flushData(riskValueOperate_dataset.pageIndex);
			window_windowModifyRisk.open();
		}
		//修改提交按钮点击时，校验提交内容
		function btnModSave_onClickCheck() {
//			var actionBitmap = riskInfo_dataset.getValue("actionBitmap");//风控动作
// 			//一条模型下面只能配置最多3条阈值
// 			if(riskInfo_dataset.getValue("valueId").split(",").length > 3){
// 				$.warn("一个模型下面最多选择3条阈值,请修改数据");
// 				return;
// 			}
// 			var firstRecord = riskValueOperate_dataset.getFirstRecord();
// 			if(!riskValueOperate_dataset.validate()){
// 				return;
// 			}
// 			var param1Array = new Array();
// 			var param2Array = new Array();
// 			var param3Array = new Array();
// 			var errMes = "同一等级下只能允许存在一个拒绝动作,请修改数据";
// 			var nameString = "01";//01代表事中风控的拒绝操作
// 			if(actionBitmap == '01'){//事后风控
// 				errMes = "同一等级下只能允许存在一个警告动作,请修改数据";
// 				nameString = "03";//03代表事后风控中的警告
// 			}
// 			while(firstRecord){
// 				var param1 = firstRecord.getValue("param1");
// 				var param2 = firstRecord.getValue("param2");
// 				var param3 = firstRecord.getValue("param3");
// 				//事后风控/事中风控相同等级下的阈值动作只能有一个警告/拒绝
// 				if(param1 == nameString ){//等级1
// 					if($.inArray(param1,param1Array) != -1){
// 						$.warn(errMes);
// 	 					return;
// 	 				}else{
// 	 					param1Array.push(param1); 
// 	 				}
// 				}
// 				if(param2 == nameString ){//等级2
// 					if($.inArray(param2,param2Array) != -1){
// 						$.warn(errMes);
// 	 					return;
// 	 				}else{
// 	 					param2Array.push(param2); 
// 	 				}
// 				}
// 				if(param3 == nameString ){//等级3
// 					if($.inArray(param3,param3Array) != -1){
// 						$.warn(errMes);
// 	 					return;
// 	 				}else{
// 	 					param3Array.push(param3); 
// 	 				}
// 				}
//  				firstRecord = firstRecord.getNextRecord();
// 			}
			var resultValue = checkInputValue();
			if(!resultValue){
				return;	
			}
			var result = checkInput();
			if (!result) {
				return;
			}
			return true;
		}
		//修改成功之后
		function btnModSave_postSubmit() {
			$.success("修改成功!", function() {
				window_windowModifyRisk.close();
				riskInfo_dataset.flushData(riskInfo_dataset.pageIndex);
			});
		}
		//当修改框体关闭时，取消记录
		function window_windowModifyRisk_beforeClose(wmf) {
			riskInfo_dataset.cancelRecord();
			riskValueOperate_dataset.cancelRecord();
		}

		//---------------------启用停用-----------------------------
		function btnStatus_onClick() {
			var valueId = riskInfo_dataset.getValue("valueId");
			if (valueId == '') {
				$.warn("请先选择要修改状态的风控信息记录");
				return false;
			}
			var riskStauts = riskInfo_dataset.getValue("riskStauts");
			var str = "是否要暂停使用当前选中的风控信息？";
			if (riskStauts == '99') {//00使用中，99暂停使用
				str = "是否要启用当前选中的风控信息？";
			}
			$.confirm(str, function() {
				riskInfo_dataset.setParameter("riskId", riskInfo_dataset
						.getValue("riskId"));
				riskInfo_dataset.setParameter("riskStauts", riskStauts);
				btnStatusSave.click();
			}, function() {
				return false;
			});
		}
		//修改状态成功后
		function btnStatusSave_postSubmit(btn) {
			$.success("操作成功!", function() {
				riskInfo_dataset.flushData(riskInfo_dataset.pageIndex);
			});
		}
		
//---------------优化(当打开多个下拉框时，将之前的下拉框显示的box自动收起)------------------
//给input所有 属性 extra 是 combogrid类型的 元素添加单击事件，每次点击时，将所有div中 class 为l-box-select-lookup
//的隐藏(分析：框架自己的下拉框的单击事件应该是绑在了父类的div中，从而没有覆盖 我们给下拉框添加的单击事件，然后根据事件冒泡机制   事件之间有先后顺序 ，得以实现优化
		
		//jQurey bind("click",function(){})  给一个元素添加单击事件
		$("input[extra=combogrid]").bind("click",function(e){
			//jQuery each()  遍历
			$("div.l-box-select-lookup").each(function(e){
				$(this).css("display","none");
			});
		});
	</script>
	
</snow:page>