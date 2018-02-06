<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="银行列表">
	<!-- 银行信息： 数据集 -->
	<snow:dataset id="PmpBankInfo" init="true" submitMode="current"
		path="com.ruimin.ifs.pmp.sysConf.dataset.PmpBankInfo"></snow:dataset>

	<!-- 银行限额信息： 数据集 -->
	<snow:dataset id="PmpBankLimitInfo" init="false" submitMode="all"
		path="com.ruimin.ifs.pmp.sysConf.dataset.PmpBankLimitInfo"></snow:dataset>

	<!-- 查询条件 -->
	<snow:query id="querybtn" collapsible="false" border="false"
		label="查询条件" dataset="PmpBankInfo"
		fieldstr="qBankNo,qBankName,qDataState,qselfOtherFlag"></snow:query>
	
	<!-- 银行列表显示 -->
	<snow:grid dataset="PmpBankInfo" id="gridId" label="银行信息"
		fitcolumns="true" height="99%" paginationbar="btnAdd,btnUpt,btnStatus"
		fieldstr="bankNo,bankName,showSer,selfOtherFlag,dataState,opr"></snow:grid>

	<!-- 新增 -->
	<snow:window id="winAddBankInfo" title="添加银行信息" modal="true"
		closable="true" width="715" buttons="btnAddSave">
		<p style="">银行信息</p>

		<!-- 1.银行信息 -->
		<snow:form dataset="PmpBankInfo" id="rnfrom" label="银行信息" collapsible="true" 
			fieldstr="bankName,showSer,selfOtherFlag" border="false">		
		</snow:form>
		<br />
		
		<!-- 2.限额列表 -->
		<snow:grid id="gridBankLimitConfig" dataset="PmpBankLimitInfo"
			fieldstr="custType[150],limitOne[160],limitDay[160],limitMon[160]"
			height="300" border="true" label="限额列表" editable="true"
			pagination="false" toolbar="toolbarConfigAdd" collapsible="true">
		</snow:grid>
		<br />
		
		<snow:toolbar id="toolbarConfigAdd">
			<snow:button id="btnAddConfig" dataset="PmpBankLimitInfo"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="PmpBankLimitInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		
		<br />
		<!-- 3.银行logo图片上传 -->
		<p style="">银行图标上传</p>
		<div id="logoPic" style="width: 700; height: 500">
			<div><iframe id="uploadAdd" name="uploadAdd" src="../../payPmp/pubTool/uploadImage.jsp?picType=pmpBank" style="height: 100%; width: 100%;"></iframe></div>
			<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd" src="" style="height: 66px; width: 276px;" /></div>
		</div>
		<snow:button id="btnAddSave" dataset="PmpBankInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 修改 -->
	<snow:window id="winUpdBankInfo" title="修改银行信息" modal="true"
		closable="true" width="765" buttons="btnUptSave">
		<!-- 1.银行信息 -->
		<snow:form dataset="PmpBankInfo" id="fromUpd" label="银行信息" collapsible="true" 
			fieldstr="bankNo,bankName,showSer,selfOtherFlag"></snow:form>
		<br />
		
		<!-- 2.限额列表 -->
		<snow:grid id="gridBankLimitConfigupt" dataset="PmpBankLimitInfo"
			fieldstr="custType[170],limitOne[170],limitDay[170],limitMon[170]"
			height="250" border="true" label="限额列表" editable="true"
			fitcolumns="false" pagination="false" toolbar="toolbarConfigupt" collapsible="true">
		</snow:grid>
		<br />
		
		<snow:toolbar id="toolbarConfigupt">
			<snow:button id="btnAddConfig" dataset="PmpBankLimitInfo"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="PmpBankLimitInfo"
				hidden="false"></snow:button>
		</snow:toolbar>
		
		<!-- 3.银行logo图片修改 -->
		<div id="logoPic" style="width: 700; height: 200"><iframe id="uploadUpd" name="uploadUpd" src="" style="height: 100%; width: 100%;"></iframe></div>
		<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picUpd" src="" style="height: 66px; width: 276px;" /></div>		
		<snow:button id="btnUptSave" dataset="PmpBankInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 详情页面 -->
	<snow:window id="windowDetailId" closable="true" width="770" title="银行信息详情" modal="true">
		<!-- 1.银行信息 -->
		<p>银行信息</p>
		<br>
		<snow:form id="formDetailId" dataset="PmpBankInfo" border="true"
			fieldstr="bankNo,bankName,showSer,selfOtherFlag" ></snow:form>
			
		<!-- 分段信息列表 -->
		<snow:grid id="gridBankLimitConfigDetailId" dataset="PmpBankLimitInfo"
			fieldstr="custType[170],limitOne[170],limitDay[170],limitMon[170]"
			height="250" border="true" label="限额列表" editable="false"
			fitcolumns="true" pagination="false" collapsible="true"></snow:grid>
		
		<p>银行图标</p>
		<div style="width: 100%; height: 170px; background-color: FFFFFF; float: center; 
			text-align: center"><img id="picIdDetailId" src="" style="height: 66px; width: 276px;" />
		</div>			
	</snow:window>

	<!-- 确定修改银行有效状态 -->
	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="PmpBankInfo"></snow:button>
	</div>

	<script>
		//  *********************************公共变量声明******************************   
		var isAmt = /^([1-9][\d|,]{0,8}|0)(\.[\d]{1,2})?$/;
		var isNum = /^\d{1,10}$/;
		var isBankName16 = /^\S{1,16}$/; // 银行名称由16个汉子组成
		var isShowSer2 = /^[1-9]{1}[0-9]{1}|[0]{1}[1-9]{1}$/; // 显示顺序为 01~99整数组成！
		var isCustomName = /^\d{1,20}$/;
		var sectionSeq = /^\d{1,2}$/;
		var isPercentNum = /^(\d{1,2}|100)$/;

		//新增或修改时，输入内容格式校验
		function checkInputByType() {
			//银行信息校验
			if (!isBankName16.test(PmpBankInfo_dataset.getValue("bankName"))) {
				$.warn("银行名称最长由16个汉字组成！");
				return false;
			}
			if (!isShowSer2.test(PmpBankInfo_dataset.getValue("showSer"))) {
				$.warn("显示顺序为 01~99整数组成！");
				return false;
			}			
			if(PmpBankInfo_dataset.getValue("picId")==null || PmpBankInfo_dataset.getValue("picId") == ''){
				$.warn("请上传银行图标！");
				return false;
			}

			//银行限额列表校验
			var currentRecord = PmpBankLimitInfo_dataset.getFirstRecord();
			if (currentRecord != null && currentRecord != '') {
				// 遍历 dataset 验证限额格式
				while (currentRecord) {
					// 单笔限额验证
					if (!isAmt.test(currentRecord.getValue("limitOne"))) {
						$.warn("单笔限额格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					// 单日限额验证
					if (!isAmt.test(currentRecord.getValue("limitDay"))) {
						$.warn("当日限额格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					// 单月限额验证
					if (!isAmt.test(currentRecord.getValue("limitMon"))) {
						$.warn("当月限额格式错误（最大长度10位数字,最多包含两位小数）");
						return false;
					}
					// 单笔限额和单日限额
					var limitOneCheck = currentRecord.getValue("limitOne")*100;
					var limitDayCheck = currentRecord.getValue("limitDay")*100;
					var limitMonCheck = currentRecord.getValue("limitMon")*100;
					if (limitOneCheck > limitDayCheck) {
						$.warn("当日限额不能小于单笔限额");
						return false;
					}
					// 单日限额和单月限额
					if (limitDayCheck > limitMonCheck) {
						$.warn("当月限额不能小于当日限额");
						return false;
					}
					// 获取下一行dataset数据集
					currentRecord = currentRecord.getNextRecord();
				}
			}
			
			return true;
		}
		
		/***************图片控制***************/
		function picJudge(lgPic,result){
			if (lgPic != "" && result == "false"){		
				$.warn("请上传已选择的图片！");
				return false;
			}else return true;
		}
		
		function importFileCallBack(result,picSrc){
			if(result){
				$("#picAdd").attr("src",picSrc);
				$("#picUpd").attr("src",picSrc);
			}
		}

		/*===================================TOP1:产品银行新增=======================================*/		
		//添加银行信息--单击事件
		function btnAdd_onClick() {	
			PmpBankInfo_dataset.setFieldRequired("selfOtherFlag",true) // 本行他行标志
			PmpBankLimitInfo_dataset.setParameter("bankNo", null);
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
			
			// 显示新增银行图标界面
			$("#uploadAdd").attr("src","../../payPmp/pubTool/uploadImage.jsp?picType=pmpBank");
			
			window_winAddBankInfo.open();
			
		}
		//添加银行 window -关闭事件 刷新grid
		function window_winAddBankInfo_beforeClose() {
			PmpBankInfo_dataset.flushData(PmpBankInfo_dataset.pageIndex);
		}
		//添加银行 保存按钮点击时，校验内容并提交请求
		function btnAddSave_onClickCheck() {
			if (PmpBankInfo_dataset.validate() == false) {
				return false;
			}
			if (PmpBankLimitInfo_dataset.validate() == false) {
				return false;
			}

			/** 设置图片信息 */
			var picId = window.frames["uploadAdd"].document.getElementById("lgPic").value;
			PmpBankInfo_dataset.setValue("picId", picId);
			//校验银行 银行新增信息
			var checkResult = checkInputByType();
			if (!checkResult) {
				return false;
			}			
			return picJudge(picId, window.frames["uploadAdd"].document.getElementById("result").value);
		}
		//保存
		function btnAddSave_postSubmit() {
			$.success("操作成功!", function() {
				window_winAddBankInfo.close();
			});
		}		
		/**窗口关闭后清除数据*/
		function window_winAddBankInfo_afterClose(){	
			PmpBankInfo_dataset.clearData();	
			PmpBankInfo_dataset.flushData(PmpBankInfo_dataset.pageIndex);
			
			PmpBankLimitInfo_dataset.clearData();	
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
			
			window.frames["uploadAdd"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
			$("#picAdd").attr("src","");
			$("#picUpd").attr("src","");
		}

		/*===================================TOP2:产品银行修改=======================================*/
		
		// 在窗口打开前检查数据状态
		function window_winUpdBankInfo_beforeOpen(){
			var state = PmpBankInfo_dataset.getValue("dataState");
			if(state == "99"){
				$.warn("该条数据为停用状态，不能修改！");
				return false;
			}
			return true;
		}
		
		//修改银行信息 -- 单击事件
		function btnUpt_onClick() {
			PmpBankInfo_dataset.setFieldRequired("selfOtherFlag",true) // 本行他行标志
			var bankNo = PmpBankInfo_dataset.getValue("bankNo");
			if (bankNo == '') {
				$.warn("请选择要修改的银行");
				return;
			}

			PmpBankInfo_dataset.setFieldRequired("picId",false) // 图标
			PmpBankInfo_dataset.setFieldReadOnly("bankNo", true);
			PmpBankInfo_dataset.setFieldReadOnly("dataState", true);
			PmpBankLimitInfo_dataset.setParameter("bankNo", bankNo);
			
			// 显示银行图标
			var picId=PmpBankInfo_dataset.getValue("picId");
			var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
			$("#picUpd").attr("src",picIp+picId);
			
			// 显示上传图标界面
			$("#uploadUpd").attr("src","../../payPmp/pubTool/uploadImage.jsp?picType=pmpBank");
			
			// 刷新限额列表信息
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
			window_winUpdBankInfo.open();
		}

		//修改银行 window -关闭事件 刷新grid
		function window_winUpdBankInfo_beforeClose() {
			PmpBankInfo_dataset.flushData(PmpBankInfo_dataset.pageIndex);
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
		}

		//修改银行 保存按钮点击时，校验内容并提交请求
		function btnUptSave_onClickCheck() {
			if (PmpBankInfo_dataset.validate() == false) {
				return false;
			}
			if (PmpBankLimitInfo_dataset.validate() == false) {
				return false;
			}

			var checkResult = checkInputByType();//校验银行 银行限额信息
			if (!checkResult) {
				return false;
			}
			/** 设置图片信息 */
			var updNewPicId = window.frames["uploadUpd"].document.getElementById("lgPic").value;			
			if(updNewPicId!=null && updNewPicId!=""){
				PmpBankInfo_dataset.setValue("picId", updNewPicId);
			}
			return picJudge(updNewPicId, window.frames["uploadUpd"].document.getElementById("result").value);
		}
		//修改银行 提交后
		function btnUptSave_postSubmit() {
			$.success("操作成功!", function() {
				window_winUpdBankInfo.close();
			});
		}		
		/**窗口关闭后清除数据*/
		function window_winUpdBankInfo_afterClose(){	
			PmpBankInfo_dataset.clearData();	
			PmpBankInfo_dataset.flushData(PmpBankInfo_dataset.pageIndex);
			
			PmpBankLimitInfo_dataset.clearData();	
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
			
			window.frames["uploadUpd"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
			$("#picAdd").attr("src","");
			$("#picUpd").attr("src","");
		}

		/*===================================TOP3:产品银行停用启用=======================================*/

		//启用/停用 -- 单击事件
		function btnStatus_onClick() {
			var bankNo = PmpBankInfo_dataset.getValue("bankNo");//银行编号
			var dataState = PmpBankInfo_dataset.getValue("dataState");//数据有效状态

			var msg = "";
			if (dataState == "99") {
				msg = "是否要将该银行【启用】?";
				dataState = "00";
			} else {
				msg = "是否要将该银行【停用】?";
				dataState = "99";
			}

			$.confirm(msg, function() {
				PmpBankInfo_dataset.setParameter("bankNo", bankNo);//银行编号
				PmpBankInfo_dataset.setParameter("dataState", dataState);//有效状态
				btnStatusSub.click(); //触发更新有效状态
			}, function() {
				return false;
			});
		}

		//更新有效状态提交后事件
		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				PmpBankInfo_dataset.flushData(PmpBankInfo_dataset.pageIndex);
			});
		}
		
		/*===================================TOP3:详情 =======================================*/
		//向显示列表中添加详情链接
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
			} else {
				return "&nbsp;";
			}
		}		
		function openDetail() {
			// 设置显示字段为可读
			PmpBankInfo_dataset.setFieldReadOnly("picId",true);
			PmpBankInfo_dataset.setFieldReadOnly("bankNo",true);
			PmpBankInfo_dataset.setFieldReadOnly("bankName",true);
			PmpBankInfo_dataset.setFieldReadOnly("showSer",true);
			PmpBankInfo_dataset.setFieldReadOnly("selfOtherFlag",true);		
			// 显示银行图标
			var picId=PmpBankInfo_dataset.getValue("picId");
			var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
			$("#picIdDetailId").attr("src",picIp+picId);			
			// 刷新该银行编号下的银行列表信息
			var bankNo = PmpBankInfo_dataset.getValue("bankNo");
			PmpBankLimitInfo_dataset.setParameter("bankNo",bankNo);		
			PmpBankLimitInfo_dataset.flushData(PmpBankLimitInfo_dataset.pageIndex);
			window_windowDetailId.open();
			
		}
		function window_windowDetailId_afterClose(){
			PmpBankInfo_dataset.setFieldReadOnly("picId",false);
			PmpBankInfo_dataset.setFieldReadOnly("bankNo",false);
			PmpBankInfo_dataset.setFieldReadOnly("bankName",false);
			PmpBankInfo_dataset.setFieldReadOnly("showSer",false);
			PmpBankInfo_dataset.setFieldReadOnly("selfOtherFlag",false);
		}
		
		
	</script>
</snow:page>
