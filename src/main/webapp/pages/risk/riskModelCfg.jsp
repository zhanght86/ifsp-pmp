<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="模型管理">
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
	<!-- 模型基本信息的dataset -->
	<snow:dataset id="riskModelCfg"
		path="com.ruimin.ifs.pmp.risk.dataset.riskModelCfg" init="true"
		submitMode="current">
	</snow:dataset>
	<!-- 模型对应阈值数据集 -->
	<snow:dataset id="modelDtst1"
		path="com.ruimin.ifs.pmp.risk.dataset.riskModelCfgModel" init="true"
		submitMode="all">
	</snow:dataset>
	
	
	<!-- 查询条件 -->
	<snow:query id="queryId" label="查询条件" dataset="riskModelCfg"
		collapsible="false"
		fieldstr="qriskModelId,qriskModelName,qriskModelType,qstatus,qactionBitmap"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="riskModelCfg" height="99%" label="模型信息"
		id="gridMain" fitcolumns="true"
		fieldstr="riskModelId[60],riskModelType,riskModelName[300],actionBitmap,status,opr"
		paginationbar="btnUpd,btnCfg,btnEnableOrDisable"></snow:grid>
	<!-- 修改窗口 -->
	<snow:window id="winUpd" title="修改信息" modal="true" closable="true"
		buttons="btnUpdSub">
		<snow:form id="riskModelInfoUpd" label="模型信息" dataset="riskModelCfg"
			fieldstr="riskModelTypeSel,riskModelName,riskModelDesc,actionBitmapSel"></snow:form>
		<snow:button id="btnUpdSub" dataset="riskModelCfg" hidden="true"></snow:button>
	</snow:window>
	<!-- 详细信息窗口 -->
	<snow:window id="winDetail" title="详情信息" modal="true" closable="true">
		<!-- 基本信息 -->
		<snow:form id="riskModelInfoDetail" label="基本信息" dataset="riskModelCfg"
			fieldstr="riskModelId,riskModelType,riskModelName,riskModelDesc,actionBitmap"></snow:form>
		<br/>
		<snow:grid id="riskModelInfoDetailGrid" dataset="modelDtst1" fieldstr="valueId[100],param1[300],valueExplain[500]"
		height = "300" border = "true" label = "阈值详情" fitcolumns="false" pagination="false" editable="true"></snow:grid>
	</snow:window>
	
	<!-- 参数配置窗口 -->
	<snow:window id="winModify" title="模型修改" modal="true" closable="true">
		<!-- 基本信息 -->
		<snow:form id="riskModelInfoModify" label="基本信息" dataset="riskModelCfg"
			fieldstr="riskModelId,riskModelType,riskModelName,riskModelDesc,actionBitmap"></snow:form>
		<br/>
		<snow:grid id ="riskModelInfoModifyGrid" dataset="modelDtst1" fieldstr="valueId[100],param1[300],valueExplain[500]"
		height = "300" border = "true" label = "阈值详情" fitcolumns="false" pagination="false" editable="true"></snow:grid>
		<snow:toolbar id="toolbarExt1">
			<snow:button id="btnAddSection" dataset="modelDtst1" hidden="false"></snow:button>
			<snow:button id="btnDeleteSection" dataset="modelDtst1" hidden="false"></snow:button>
			<snow:button id="btUpdate" dataset="modelDtst1" hidden="false"></snow:button>
		</snow:toolbar>
	</snow:window>
	
	<div style="display:none;">
		<snow:button id="btnEnableOrDisableSubMit" dataset="riskModelCfg"></snow:button>
	</div>

	<script type="text/javascript">
		//------------***********************详情**************************------------------------
		function gridMain_opr_onRefresh(record) {//给详情超链接绑定事件
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openDetail()\">详情</a></span>";
			}
		}
		function openDetail() {//点击详情超链接触发事件
			var riskModelId = riskModelCfg_dataset.getValue("riskModelId");
			//全部字段设置为-只读
			riskModelCfg_dataset.setFieldReadOnly("riskModelId", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelType", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelName", true);
			riskModelCfg_dataset.setFieldReadOnly("actionBitmap", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelDesc", true);
			modelDtst1_dataset.setParameter("riskModelId",riskModelId);
			modelDtst1_dataset.flushData(modelDtst1_dataset.pageIndex);
			//打开窗口
			window_winDetail.open();
 		}
		
		function window_winDetail_beforeOpen(win) {//窗口打开前初始化form显示
			var valueModel = riskModelCfg_dataset.getValue("changeValueModel");
			var modelArray = valueModel.split(",");
			modifyParamLabel(modelArray);
			modelDtst1_dataset.setFieldReadOnly("valueId",true);
			modelDtst1_dataset.setFieldReadOnly("param1",true);
			modelDtst1_dataset.setFieldReadOnly("param2",true);
			modelDtst1_dataset.setFieldReadOnly("param3",true);
			modelDtst1_dataset.setFieldReadOnly("valueExplain",true);
		}
		function window_winDetail_afterClose() {//窗口关闭后清除数据
			riskModelCfg_dataset.setFieldReadOnly("riskModelId", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelType", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelName", false);
			riskModelCfg_dataset.setFieldReadOnly("actionBitmap", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelDesc", false);
			riskModelCfg_dataset.cancelRecord();
			riskModelCfg_dataset.flushData(riskModelCfg_dataset.pageIndex);
			modelDtst1_dataset.setFieldReadOnly("param1",false);
			modelDtst1_dataset.setFieldReadOnly("param2",false);
			modelDtst1_dataset.setFieldReadOnly("param3",false);
			modelDtst1_dataset.cancelRecord();
			modelDtst1_dataset.flushData(modelDtst1_dataset.pageIndex);
		}

		//------------***********************参数配置**************************------------------------
		function btnCfg_onClick() {//点击参数配置按钮
			var status = riskModelCfg_dataset.getValue("status");
			var riskModelId = riskModelCfg_dataset.getValue("riskModelId");
			//打开窗口
			window_winModify.open();
			modelDtst1_dataset.setParameter("riskModelId",riskModelId);
			modelDtst1_dataset.flushData(modelDtst1_dataset.pageIndex,function(){
				var valueModel = riskModelCfg_dataset.getValue("changeValueModel");
				var modelArray = valueModel.split(",");
				modifyParamLabel(modelArray);
				 modelDtst1_dataset_afterScroll(modelDtst1_dataset);
			});
		}
		function window_winModify_beforeOpen(win){
			riskModelCfg_dataset.setFieldReadOnly("riskModelId", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelType", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelName", true);
			riskModelCfg_dataset.setFieldReadOnly("actionBitmap", true);
			riskModelCfg_dataset.setFieldReadOnly("riskModelDesc", true);
		}
		function window_winModify_afterClose(){
			riskModelCfg_dataset.setFieldReadOnly("riskModelId", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelType", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelName", false);
			riskModelCfg_dataset.setFieldReadOnly("actionBitmap", false);
			riskModelCfg_dataset.setFieldReadOnly("riskModelDesc", false);
			riskModelCfg_dataset.cancelRecord();
			riskModelCfg_dataset.flushData(riskModelCfg_dataset.pageIndex);
			modelDtst1_dataset.cancelRecord();
			modelDtst1_dataset.flushData(modelDtst1_dataset.pageIndex);
		}
		function modelDtst1_dataset_afterChange(dataset, field) {//档数据集发生变化时
			var valueExplain = riskModelCfg_dataset.getValue("valueExplain");//阈值说明
			var valueModel = riskModelCfg_dataset.getValue("changeValueModel");
			var modelArray = valueModel.split(",");
			if (field.fieldName != "valueExplain") {//判断只要不是valueExplain发生变化
				for(var i = 0;i<modelArray.length;i++){
					var param = modelDtst1_dataset.getValue("param"+(i+1));
					valueExplain = valueExplain.replace("{param"+(i+1)+"}",param);
				}
				modelDtst1_dataset.setValue("valueExplain",valueExplain);
			
			}
		}
		function btUpdate_onClickCheck() {//提交按钮点击时检查
			var valueModel = riskModelCfg_dataset.getValue("changeValueModel");
			var modelArray = valueModel.split(",");
			var result = modelDtst1_dataset.getFirstRecord();
			var tempMoney = /^([1-9][\d]{0,9}|[1-9][\d]{0,2}(\,[\d]{3})*|0)(\.[\d]{1,2})?$/;
			var tempNumber = /^[\d]{0,12}$/;
			var isPercentNum = /^(\d{0,2}|100)$/;//0-100整数
			while (result) {
				for(var i = 0;i<modelArray.length;i++){
					var type = modelArray[i].split("-")[2];//获取该列阈值的类型(金额，笔数，占比  三种类型)
					var param = modelDtst1_dataset.getValue("param"+(i+1));
 					if(type == "金额"){
 						if(param == null || param ==""){
 							$.warn("金额不能为空");
 							return false;
 						}else if(!tempMoney.test(param)){
 							$.warn("金额只能输入小数点前最大为10位的数字，小数点后最大2位");
 							return false;
 						}
 					}
 					if(type == "笔数"){
 						if(param == null || param ==""){
 							$.warn("笔数不能为空");
 							return false;
 						}else if (!tempNumber.test(param)){
 							$.warn("笔数只能输入最大为12位的数字,不能输入小数");
 							return false;
 						}
 					}
 					if(type == "占比"){
 						if(param == null || param ==""){
 							$.warn("占比不能为空");
 							return false;
 						}else if (!isPercentNum.test(param)){
 							$.warn("占比只能输入0到100的整数");
 							return false;
 						}
 					}
				}
				result = result.getNextRecord();
			}
			return true;
		}
		function btUpdate_postSubmit() {
			$.success("操作成功!", function() {
				window_winModify.close();
				riskModelCfg_dataset.flushData(riskModelCfg_dataset.pageIndex);
			});
		}
		//------------***********************修改**************************------------------------
		function btnUpd_onClickCheck() {
			riskModelCfg_dataset.setValue("riskModelTypeSel",
					riskModelCfg_dataset.getValue("riskModelType"));
			riskModelCfg_dataset.setValue("actionBitmapSel",
					riskModelCfg_dataset.getValue("actionBitmap"));
			return true;
		}
		function btnUpd_onClick() {//打开修改窗口
			var status = riskModelCfg_dataset.getValue("status");
			if(status == "00"){
				 $.warn("无法修改使用中的模型");
				return;
			}
			riskModelCfg_dataset.setFieldReadOnly("riskModelTypeSel", true);
			riskModelCfg_dataset.setFieldReadOnly("actionBitmapSel", true);
			window_winUpd.open();
		}
		function btnUpdSub_postSubmit() {//点击修改窗口中的提交按钮
			$.success("修改成功！", function() {
				window_winUpd.close();
				riskModelCfg_dataset.cancelRecord();
				riskModelCfg_dataset.flushData(riskModelCfg_dataset.pageIndex);
			});
		}
		
		//------------***********************启用停用**************************------------------------
		function btnEnableOrDisable_onClickCheck(){
			var status = riskModelCfg_dataset.getValue("status");
			if(status == '00'){
				$.warn("该模型已被使用，无法停用！");
				return false;
			}else if(status == '99'){
				$.confirm("是否确定【启用】该模版？",function(){
					btnEnableOrDisableSubMit.click();
				},function(){
					return false;
				});
			}else if(status == '01'){
				$.confirm("是否确定【停用】该模版？",function(){
					btnEnableOrDisableSubMit.click();
				},function(){
					return false;
				});
			}
		}
		function btnEnableOrDisableSubMit_postSubmit(){
			$.success("操作成功!", function() {
				riskModelCfg_dataset.flushData(riskModelCfg_dataset.pageIndex);
			});
		}
		//----------------***********公有方法***************-----------------
		//修改param1到param3的标题（后来根据现场需求，废弃了param2和param3）
		function modifyParamLabel(modelArray){
			for(var i = 0;i<modelArray.length;i++){//修改param1到param3的列名
				var array = modelArray[i].split("-");
				modelDtst1_dataset.getField("param"+(i+1)).label =array[1];
				var span = $("td[columnname='param"+(i+1)+"']").find("span");
				span.html(array[1]);
				span.attr("title",array[1]);
				span.css("display","block");
				//因为有公有鼠标滚动方法存在，此处需注释掉，否则会有bug，如果被引用的阈值是第一条，按道理不能修改，这句话存在就可以修改了
				//modelDtst1_dataset.setFieldReadOnly("param"+(i+1),false);
				modelDtst1_dataset.setFieldRequired("param"+(i+1),true);
			}
			for(var j = modelArray.length;j<3;j++){//将无值的param1到param3的列名置空，并只读
				modelDtst1_dataset.getField("param"+(j+1)).label = "";
				var span = $("td[columnname='param"+(j+1)+"']").find("span");
				span.css("display","none");
				modelDtst1_dataset.setFieldReadOnly("param"+(j+1),true);
				modelDtst1_dataset.setFieldRequired("param"+(j+1),false);
			}
		}
		
		/**************公共方法当模型阈值鼠标滚动选中的行时**********************/
		function  modelDtst1_dataset_afterScroll(dataset, record){
			if(record){
				var valueIdStringArray = record.getValue("valueIdString").split(",");
				var valueId = record.getValue("valueId");
				if(valueId != "" && $.inArray(valueId,valueIdStringArray) != -1){
					//当阈值被其他风控原型引用时 无法修改和删除
					 //$("#btnDeleteSection").css("display", "none");
					btnDeleteSection.setHidden(true);
					dataset.setFieldReadOnly("param1",true);
				}else{
					dataset.setFieldReadOnly("param1",false);
					btnDeleteSection.setHidden(false);
					//$("#btnDeleteSection").css("display", "inline-block");
				}
			}
			
		}
	</script>
</snow:page>