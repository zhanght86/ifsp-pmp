<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="费率模板管理">
	<!-- 费率基本信息的dataset -->
	<snow:dataset id="rateBaseInfo"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.rateBaseInfo" init="true"
		submitMode="current"></snow:dataset>
	<!-- 费率规则表的dataset -->
	<snow:dataset id="rateRuleInfo"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.rateRuleInfo" init="true"
		parameters="apprFlag=1"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query id="queryId" label="查询条件" dataset="rateBaseInfo"
		collapsible="false" fieldstr="qrateId,qrateName,qsectionType"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="rateBaseInfo" height="99%" label="费率信息" id="gridId"
		fitcolumns="true" fieldstr="rateId,rateName,sectionType,opr"
		paginationbar="btnAdd,btnMod,btnDelete"></snow:grid>

	<!-- 新增页面 -->
	<snow:window id="windowAddId" closable="true" width="800"
		title="费率信息新增" modal="true" buttons="btnSave">
		<p>费率基本信息</p>
		<br>
		<snow:form id="formAddId" dataset="rateBaseInfo" border="false"
			fieldstr="rateName,sectionType1" collapsible="false" colnum="4"
			label="费率基本信息"></snow:form>
		<!-- 分段信息列表 -->
		<snow:grid id="gridAddId" dataset="rateRuleInfo"
			fieldstr="sectionMin[121],sectionMax[121],chargeType[121],chargeValue[121],feeMin[121],feeMax[121]"
			height="99%" border="true" label="费率分段信息" editable="true"
			fitcolumns="true" pagination="false" toolbar="toolbarExt01"
			collapsible="true"></snow:grid>
		<snow:toolbar id="toolbarExt01">
			<snow:button id="btnAddSection01" dataset="rateRuleInfo"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="rateRuleInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnSave" dataset="rateBaseInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 修改页面 -->
	<snow:window id="windowModId" closable="true" width="800"
		title="费率信息修改" modal="true" buttons="btnSave1">
		<p>费率基本信息</p>
		<br>
		<snow:form id="formAddId" dataset="rateBaseInfo" border="true"
			fieldstr="rateId,rateName,sectionType" collapsible="true" colnum="4"></snow:form>
		<!-- 分段信息列表 -->
		<snow:grid id="gridModId" dataset="rateRuleInfo"
			fieldstr="sectionMin[121],sectionMax[121],chargeType[121],chargeValue[121],feeMin[121],feeMax[121]"
			height="99%" border="true" label="费率分段信息" editable="true"
			fitcolumns="true" pagination="false" toolbar="toolbarExt02"
			collapsible="true"></snow:grid>
		<snow:toolbar id="toolbarExt02">
			<snow:button id="btnAddSection02" dataset="rateRuleInfo"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteSection02" dataset="rateRuleInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnSave1" dataset="rateBaseInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 详情页面 -->
	<snow:window id="windowDetailId" closable="true" width="760"
		height="400" title="费率信息详情" modal="true">
		<p>费率基本信息</p>
		<br>
		<snow:form id="formAddId" dataset="rateBaseInfo" border="true"
			fieldstr="rateId,rateName,sectionType" collapsible="true" colnum="4"></snow:form>
		<!-- 分段信息列表 -->
		<snow:grid id="gridDetailId" dataset="rateRuleInfo"
			fieldstr="sectionMin[111],sectionMax[111],chargeType[111],chargeValue[111],feeMin[111],feeMax[111]"
			height="99%" border="false" label="费率分段信息" editable="false"
			fitcolumns="true" pagination="false" collapsible="true"></snow:grid>
	</snow:window>


	<div style="display: none;">
		<snow:button id="btnShowDetail" dataset="rateBaseInfo" hidden="true"></snow:button>
		<snow:button id="btnDeleteSub" dataset="rateBaseInfo" hidden="true"></snow:button>
	</div>

	<script>
		//公共变量声明
		var isAmt = /^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;
		var isPercentNum = /^([1-9][\d]{0,1}|0|100)(\.[\d]{1,2})?$/;
		
		//点击添加按钮时
		function btnAdd_onClick() {
			rateBaseInfo_dataset.setReadOnly(false);
			$("#gridAddIdgrid").css("display", "none");
			$("#btnAddSection01").css("display", "none");
			$("#btnDeleteSection01").css("display", "none");
			rateRuleInfo_dataset.setParameter("rateId", null);
			rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
			window_windowAddId.open();
		}

		//当添加费率模板框体关闭时，取消记录
		function window_windowAddId_beforeClose(wmf) {
			rateBaseInfo_dataset.cancelRecord();
			rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
		}

		
		//新增时，分段标志下拉列表改变时
		function rateBaseInfo_dataset_sectionType1_onSelect(v, record) {
			//当分段标志选择为“00-默认”
			if ('00' == v) {
				showSection(false);
				rateBaseInfo_dataset.setReadOnly(false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMin", true);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMax", true);
				var lastRecord= rateRuleInfo_dataset.getLastRecord();
					while(lastRecord){
						if(lastRecord!=null){
							rateRuleInfo_dataset.deleteRecord();
						}
						lastRecord=lastRecord.prevUnit;
					}
					rateRuleInfo_dataset.insertRecord("end");
			} else {
				showSection(true);
				rateBaseInfo_dataset.setReadOnly(false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMin", false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMax", false);
				rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
			}
		}
		
		
		//修改时，分段标志下拉列表改变时
		function rateBaseInfo_dataset_sectionType_onSelect(v, record) {
			//当分段标志选择为“00-默认”
			if ('00' == v) {
				showSection1(false);
				rateBaseInfo_dataset.setReadOnly(false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMin", true);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMax", true);
		 	    var firstRecord = rateRuleInfo_dataset.getFirstRecord();
				while(firstRecord){
					if(firstRecord!=null){
						rateRuleInfo_dataset.deleteRecord();
					}
					firstRecord=firstRecord.getNextRecord();
				}
				rateRuleInfo_dataset.insertRecord("end");
			} else {
				showSection1(true);
				rateBaseInfo_dataset.setReadOnly(false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMin", false);
				rateRuleInfo_dataset.setFieldReadOnly("sectionMax", false);
				var firstRecord = rateRuleInfo_dataset.getFirstRecord();
				while(firstRecord){
					if(firstRecord!=null){
						rateRuleInfo_dataset.deleteRecord();
					}
					firstRecord=firstRecord.getNextRecord();
				}
			}
		}

		
		//新增加控制是否显示分段信息列表和工具栏
		function showSection(showFlag) {
			if (showFlag == true) {
				$("#gridAddIdgrid").css("display", "block");
				$("#btnAddSection01").css("display", "");
				$("#btnDeleteSection01").css("display", "");
			} else if (showFlag == false) {
				$("#gridAddIdgrid").css("display", "block");
				$("#btnAddSection01").css("display", "none");
				$("#btnDeleteSection01").css("display", "none");
			}
		}
		//修改控制是否显示分段信息列表和工具栏
		function showSection1(showFlag) {
			if (showFlag == true) {
				$("#btnAddSection02").css("display", "");
				$("#btnDeleteSection02").css("display", "");
			} else if (showFlag == false) {
				$("#btnAddSection02").css("display", "none");
				$("#btnDeleteSection02").css("display", "none");
			}
		}

		//收费类型下拉列表改变时
		function rateRuleInfo_dataset_chargeType_onSelect(v, record) {
			//当选择00-固定金额时
			if ('00' == v) {
				rateRuleInfo_dataset.setFieldReadOnly("chargeValue", false);
				rateRuleInfo_dataset.setValue("feeMin", "");
				rateRuleInfo_dataset.setValue("feeMax", "");
				rateRuleInfo_dataset.setFieldReadOnly("feeMin", true);
				rateRuleInfo_dataset.setFieldReadOnly("feeMax", true);
			} else if('01' == v){
				rateRuleInfo_dataset.setFieldReadOnly("chargeValue", false);
				rateRuleInfo_dataset.setValue("feeMin", "");
				rateRuleInfo_dataset.setValue("feeMax", "");
				rateRuleInfo_dataset.setFieldReadOnly("feeMin", false);
				rateRuleInfo_dataset.setFieldReadOnly("feeMax", false);
			}else{
				rateRuleInfo_dataset.setValue("feeMin", "");
				rateRuleInfo_dataset.setValue("feeMax", "");
			}

		}


		//新增校验
		function btnSave_onClickCheck() {		
			var sectionType1=rateBaseInfo_dataset.getValue("sectionType1");
			rateBaseInfo_dataset.setValue("sectionType", sectionType1);
			var rateName=rateBaseInfo_dataset.getValue("rateName");
			if(rateName.length>21){
				$.warn("费率名称过长，最大不超过21位！");
				return false;
			}
			var checkResult = checkInputByType();
			if (!checkResult) {
				return;
			}
			return true;
		}
		
		
		
		//新增或修改时，输入内容格式校验
		function checkInputByType() {
			//根据分段标志判断值
			var sectionType = rateBaseInfo_dataset.getValue("sectionType");
			if ("00" == sectionType) {
				var firstRecord = rateRuleInfo_dataset.getFirstRecord();
				if (firstRecord == null || firstRecord == '') {
					$.warn("当前费率模板必须输入信息");
					return false;
				}
				if ("00" == firstRecord.getValue("chargeType")) {
					if (!isAmt.test(firstRecord
							.getValue("chargeValue"))) {
						$.warn("收费值金额格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
				} else if ("01" == firstRecord.getValue("chargeType")) {
					if (!isPercentNum.test(firstRecord.getValue("chargeValue"))) {
						$.warn("收费值格式错误（最大长度4位数字,最多包含两位小数）");
						return false;
					}
					if (!isAmt.test(firstRecord.getValue("feeMin"))&&firstRecord.getValue("feeMin")!="") {
						$.warn("最小手续费格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					if (!isAmt.test(firstRecord.getValue("feeMax"))&&firstRecord.getValue("feeMax")!="") {
						$.warn("最大手续费格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					if (parseFloat(firstRecord.getValue("feeMin")) > parseFloat(firstRecord
							.getValue("feeMax"))) {
						$.warn("最小手续费不能大于最大手续费！");
						return false;
					}
				}
				firstRecord=firstRecord.getNextRecord();
				if(firstRecord!=null){
					$.warn("分段标志为默认时，只能有一条记录！");
					return false;
				}
			} else if ("01" == sectionType) {
				var currentRecord = rateRuleInfo_dataset.getFirstRecord();
				var LastRecord = rateRuleInfo_dataset.getLastRecord();
				var strmax=LastRecord.getValue("sectionMax");
				if(strmax==null||strmax==''){
				}else{
					$.warn("最后一个分段的最大值应该为空，视作无穷");
					return false;					
				}
					if (currentRecord == null || currentRecord == '') {
						$.warn("当前费率模板必须输入分段信息");
						return false;
					}
					if(currentRecord.getValue("sectionMin")!='0'){
						$.warn("费率的起始值必须为0");
						return false;
					}
					var strMin='1';
				while (currentRecord) {

					if(strMin=='99'){
						if(currentRecord.getValue("sectionMin")=='0'){
							$.warn("费率只有起始值才可以为0");
							return false;
						}
					}
					
					if(strMin=='1'){
						if(currentRecord.getValue("sectionMin")=='0'){
							strMin='99';
						}	
					}
					if (!isAmt.test(currentRecord.getValue("sectionMin"))&&currentRecord.getValue("sectionMin")!="") {
						$.warn("分段最小值格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					if (!isAmt.test(currentRecord.getValue("sectionMax"))&&currentRecord.getValue("sectionMax")!="") {
						$.warn("分段最大值格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					if (parseFloat(currentRecord.getValue("sectionMin")) >= parseFloat(currentRecord
							.getValue("sectionMax"))) {
						$.warn("分段最小值必须小于分段最大值");
						return false;
					}
					if ("00" == currentRecord.getValue("chargeType")) {
						if (!isAmt.test(currentRecord.getValue("chargeValue"))) {
							$.warn("收费值金额格式错误（最大长度10位数字,最多包含两位小数）");
							return false;
						}
					} else if ("01" == currentRecord.getValue("chargeType")) {
						if (!isPercentNum.test(currentRecord
								.getValue("chargeValue"))) {
							$.warn("收费值格式错误（最大长度4位数字,最多包含两位小数）");
							return false;
						}
						if (!isAmt.test(currentRecord.getValue("feeMin"))&&currentRecord.getValue("feeMin")!="") {
							$.warn("最小手续费格式错误（最大长度10位数字,最多包含两位小数）");
							return false;
						}
						if (!isAmt.test(currentRecord.getValue("feeMax"))&&currentRecord.getValue("feeMax")!="") {
							$.warn("最大手续费格式错误（最大长度10位数字,最多包含两位小数）");
							return false;
						}
						if (parseFloat(currentRecord.getValue("feeMin")) > parseFloat(currentRecord.getValue("feeMax"))) {
							$.warn("最小手续费不能大于最大手续费！");
							return false;
						}
					}
					var sectionMax = parseFloat(currentRecord.getValue("sectionMax"));
					//获取下一行dataset数据集
					currentRecord = currentRecord.getNextRecord();
					if(currentRecord==null){
						continue;
					}else{
						var sectionMin = parseFloat(currentRecord.getValue("sectionMin"));
						if (sectionMin != '' && sectionMin != sectionMax) {
							$.warn("分段区间数值错误（前一条数据的最大值应等于后一条数据的最小值）");
							return false;
						}
					}
				}
			}
			return true;
		}

		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				window.location.reload(true);
				//rateBaseInfo_dataset.flushData(rateBaseInfo_dataset.pageIndex);
			});
		}

		//点击修改按钮之后的检测 
		function btnMod_onClickCheck(){
			var rateId =rateBaseInfo_dataset.getValue("rateId");
			rateBaseInfo_dataset.setParameter("rateId", rateId);
			return true;
		}
		 function btnMod_postSubmit(btn,param){
			rateBaseInfo_dataset.setFieldReadOnly("rateId", true);
			rateBaseInfo_dataset.setFieldReadOnly("sectionType", false);
			rateBaseInfo_dataset.setFieldReadOnly("rateName", false);
			var rateId = rateBaseInfo_dataset.getValue("rateId");
			var chargeType = rateRuleInfo_dataset.getValue("chargeType");
			rateRuleInfo_dataset.setParameter("rateId", rateId);
			rateRuleInfo_dataset.setParameter("chargeType", chargeType);
			rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
			window_windowModId.open();
			
		} 
			//修改窗口关闭时
			function window_windowModId_beforeOpen(wmf) {
				var openclick =  rateBaseInfo_dataset.getValue("sectionType");
				//当分段标志选择为“00-默认”
				if ('00' == openclick ) {
					rateRuleInfo_dataset.setFieldReadOnly("sectionMin", true);
					rateRuleInfo_dataset.setFieldReadOnly("sectionMax", true);
					$("#btnAddSection02").css("display", "none");
					$("#btnDeleteSection02").css("display", "none");
				} else {
					rateRuleInfo_dataset.setFieldReadOnly("sectionMin", false);
					rateRuleInfo_dataset.setFieldReadOnly("sectionMax", false);
					$("#btnAddSection02").css("display", "");
					$("#btnDeleteSection02").css("display", "");
				}
			}
		
		//修改窗口关闭时
		function window_windowModId_beforeClose(wmf) {
			rateBaseInfo_dataset.flushData(rateBaseInfo_dataset.pageIndex);
			rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
		}
		
		
		//当数据集的当前记录改变时，执行如下方法
	    function rateRuleInfo_dataset_afterScroll(dataset){
			var chargeType = dataset.getValue("chargeType");
			if(chargeType =='00'){
				rateRuleInfo_dataset.setFieldReadOnly("chargeValue", false);
				rateRuleInfo_dataset.setFieldReadOnly("feeMin", true);
				rateRuleInfo_dataset.setValue("feeMin", "");
				rateRuleInfo_dataset.setFieldReadOnly("feeMax", true);
				rateRuleInfo_dataset.setValue("feeMax", "");
			}else if(chargeType =='01'){
				rateRuleInfo_dataset.setFieldReadOnly("chargeValue", false);
				rateRuleInfo_dataset.setFieldReadOnly("feeMin", false);
				rateRuleInfo_dataset.setFieldReadOnly("feeMax", false);
			}
		} 
		
		
		//修改校验
		function btnSave1_onClickCheck() {
			var rateName=rateBaseInfo_dataset.getValue("rateName");
			if(rateName.length>21){
				$.warn("费率名称过长，最大不超过21位！");
				return false;
			}
			rateRuleInfo_dataset_afterScroll(rateRuleInfo_dataset);
			var checkResult = checkInputByType();
			if (!checkResult) {
				return;
			}
			return true;
		}

		function btnSave1_postSubmit() {
			$.success("操作成功!", function() {
				window_windowModId.close();
				rateBaseInfo_dataset.flushData(rateBaseInfo_dataset.pageIndex);
			});
		}

		//删除按钮点击时
		function btnDelete_onClick() {
			var rateId = rateBaseInfo_dataset.getValue("rateId");
			$.confirm("删除后数据不可恢复,是否确认删除？", function() {
				rateBaseInfo_dataset.setParameter("rateId", rateId);
				btnDeleteSub.click();
			}, function() {
				return false;
			});
		}

		var btnDeleteSub_postSubmit = function() {
			$.success("操作成功!", function() {
				rateBaseInfo_dataset.flushData(rateBaseInfo_dataset.pageIndex);
			});
		};

		//向显示列表中添加详情链接
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
			} else {
				return "&nbsp;";
			}
		}

		function openDetail() {
			btnShowDetail.click();
		}

		//点击详情连接时，查看费率模板详细信息
		function btnShowDetail_onClick() {
			rateBaseInfo_dataset.setReadOnly(true);
			var rateId = rateBaseInfo_dataset.getValue("rateId");
			var chargeType = rateRuleInfo_dataset.getValue("chargeType");
			rateRuleInfo_dataset.setParameter("rateId", rateId);
			rateRuleInfo_dataset.setParameter("chargeType", chargeType);
			rateRuleInfo_dataset.flushData(rateRuleInfo_dataset.pageIndex);
			window_windowDetailId.open();
		}


		//当详情框体关闭时，取消字段的只读
		function window_windowDetailId_beforeClose(wmf) {
			rateBaseInfo_dataset.setReadOnly(false);
			rateBaseInfo_dataset.flushData(rateBaseInfo_dataset.pageIndex);
		}
		
	</script>
</snow:page>