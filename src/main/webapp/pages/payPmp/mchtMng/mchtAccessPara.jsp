<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户接入参数配置">
	<!--  <script src="../../common/upload/js/ajaxfileupload.js"></script>-->

	<!-- 1.商户证书配置数据集 -->
	<snow:dataset id="MchtAccessPara"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.MchtAccessPara"
		submitMode="current" init="true"></snow:dataset>
	<!-- 2.查询 -->
	<snow:query label="查询条件" id="queryId" collapsible="false"
		border="false" dataset="MchtAccessPara"
		fieldstr="qMchtId,qmchtSimpleName,qcertifiStatus"></snow:query>

	<!-- 3.主表单 -->
	<snow:grid dataset="MchtAccessPara" height="99%" label="商户接入参数信息"
		fitcolumns="true" id="gridId"
		fieldstr="certifiBl,mchtSimpleName,certifiDate,certifiEndDate,certifiStatus,opr"
		paginationbar="btnAdd,btnUpt,btnStatus"></snow:grid>

	<!-- 3.新增 -->
	<snow:window id="windowAddId" title="商户接入参数--> 新增" width="850"
		modal="true" closable="true" buttons="btnAddSave">
		<snow:form id="baseInfo1" dataset="MchtAccessPara" border="false" label=""
			fieldstr="mchtId,certifiStatus,certifiDate,certifiEndDate,certifiPasswd">
		</snow:form>
		<snow:button id="btnAddSave" dataset="MchtAccessPara" hidden="true"></snow:button>
	</snow:window>

	<!-- 4.修改 -->
	<snow:window id="windowUpdateId" title="商户接入参数--> 修改" width="850"
		modal="true" closable="true" buttons="btnUpdSave">
		<snow:form id="baseInfo2" dataset="MchtAccessPara" border="false" label=""
			fieldstr="mchtId,certifiDate,certifiEndDate,certifiStatus,certifiPasswd">
		</snow:form>
		<snow:button id="btnUpdSave" dataset="MchtAccessPara" hidden="true"></snow:button>
	</snow:window>

	<!-----5.状态修改配置框体 ----->
	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="MchtAccessPara" hidden="true"></snow:button>
	</div>

	<!-- 6.详情窗口 -->
	<snow:window id="winDetail" title="详情信息" modal="true" closable="true">
		<!-- 基本信息模块 -->
		<snow:form id="MchtCertDetail" label="基本信息" dataset="MchtAccessPara"
			fieldstr="mchtId,certifiType,certifiDate,certifiEndDate,certifiPasswd">
		</snow:form>
	</snow:window>

	<script>
		var isChar32 = /^\S{1,32}$/;//	最大长度280位字符，32个汉子
		// 保存前字段检查
		function checkField(flag) {
			var mchtId = MchtAccessPara_dataset.getValue("mchtId");
			var certifiDate = MchtAccessPara_dataset.getValue("certifiDate");
			var certifiEndDate = MchtAccessPara_dataset.getValue("certifiEndDate");
			var certifiPasswd = MchtAccessPara_dataset.getValue("certifiPasswd");
			//当前时间yyyyMMdd
			var currDate = formatDate((new Date()),"yyyyMMdd");
			//生效日yyyyMMdd
			var certifiDate_F = formatDate(certifiDate,"yyyyMMdd");
			//失效日yyyyMMdd
			var certifiEndDate_F = formatDate(certifiEndDate,"yyyyMMdd");
		 	
			if (mchtId == '' || mchtId == null) {
				$.warn("提示：商户编号不能为空!");
				return false;
			}
			if (flag=="add"){
				//生效日期验证
				if(currDate > certifiDate_F){
					  $.warn("生效日期不能是今天之前的时间!");
					  return false;
				}
			}
			
			if(certifiDate == null || certifiDate == ''){
				$.warn("提示：生效日期不能为空!");
				return false;
			}
					
			if(certifiEndDate == null || certifiEndDate == ''){
				$.warn("提示：失效日期不能为空!");
				return false;
			}
			
			// 判断md5的值位数为32位
//			if (!isChar32.test(certifiPasswd)) {
//				$.warn("提示：md5的值由最大长度为32位字符组成!");
//				return false;
//		}

			// 生效日期不能大于失效日期
			if (certifiDate_F > certifiEndDate_F) {
				$.warn("提示：生效日期不能大于失效日期!")
				return false;
			}
			return true;
		}
		
		 //格式化日期
	    function formatDate(date,format){
	      var paddNum = function(num){
	        num += "";
	        return num.replace(/^(\d)$/,"0$1");
	      }
	      //指定格式字符
	      var cfg = {
	         yyyy : date.getFullYear() //年 : 4位
	        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
	        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
	        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
	        ,d  : date.getDate()   //日 : 如果1位的时候不补0
	        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
	        ,hh : date.getHours()  //时
	        ,mm : date.getMinutes() //分
	        ,ss : date.getSeconds() //秒
	      }
	      format || (format = "yyyy-MM-dd hh:mm:ss");
	      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
	    } 
		
		

		/************************************* 1.商户证书配置:新增 ***************************************/
		function btnAdd_onClick() {
			MchtAccessPara_dataset.setFieldReadOnly("mchtId", false);
			MchtAccessPara_dataset.setFieldReadOnly("certifiPasswd", true);
			MchtAccessPara_dataset.setFieldReadOnly("certifiDate", false);
			MchtAccessPara_dataset.setFieldReadOnly("certifiEndDate", false);
			MchtAccessPara_dataset.setFieldReadOnly("certifiStatus", false);
			dwr.engine.setAsync(false);
			var data = GetMd5Value.reset();
			dwr.engine.setAsync(true);
			var result = data;
			MchtAccessPara_dataset.setValue("certifiPasswd", result);
			// 去方法中获md5的值
//			$.ajaxFileUpload({
//				url : '${pageContext.request.contextPath}/GetMd5ValueServlet',
//				success : function(data, status) {
//					var result = data;
//					if (data.length > 6) {
//						result = data.substring(data.length - 38,data.length - 6);
//					}
//					MchtAccessPara_dataset.setValue("certifiPasswd", result);
//					return result;
//				},
//			});
			window_windowAddId.open();
		}

		function window_windowAddId_beforeClose(wmf) {
			MchtAccessPara_dataset.cancelRecord();
		}

		/* 上传商户证书文件  */
		function btnAddSave_onClickCheck() {
			if (!checkField("add")) {
				return false;
			}
			return true;
		}

		function btnAddSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				MchtAccessPara_dataset.flushData(MchtAccessPara_dataset.pageIndex);
			});
		}

		/************************************* 2.商户接入参数:修改 ***************************************/
		// 在窗口打开前检查数据状态
		function window_windowUpdateId_beforeOpen() {
			var certifiStatus = MchtAccessPara_dataset.getValue("certifiStatus");
			if (certifiStatus == "99") {
				$.warn("该条数据为停用状态，不能修改！");
				return false;
			}
			
			var certifiType = MchtAccessPara_dataset.getValue("certifiType");
			if(certifiType != "03"){
				$.warn("该条数据的证书类型不是MD5,不能进行修改操作！");
				return false;
			}
			return true;
		}

		function btnUpt_onClick() {
			MchtAccessPara_dataset.setFieldReadOnly("mchtId", true);
			MchtAccessPara_dataset.setFieldReadOnly("certifiPasswd", true);
			MchtAccessPara_dataset.setFieldReadOnly("certifiDate", false);
			MchtAccessPara_dataset.setFieldReadOnly("certifiEndDate", false);
			MchtAccessPara_dataset.setFieldReadOnly("certifiStatus", false);
			window_windowUpdateId.open();
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
				MchtAccessPara_dataset.flushData(MchtAccessPara_dataset.pageIndex);
			});
		}
		function window_windowUpdateId_beforeClose(wmf) {
			MchtAccessPara_dataset.cancelRecord();
		}

		/*********************************** 3.商户接入参数:启用/停用 ************************************/
		function btnStatusSub_needCheck() {
			return false;
		}
		function btnStatus_onClick() {
			var dataState = MchtAccessPara_dataset.getValue("certifiStatus");
			var msg = "";
			if (dataState == "01") {
				msg = "是否要将该商户接入参数状态修改为【启用】?";
				dataState = "00";
			} else {
				msg = "是否要将该商户接入参数状态修改为【停用】?";
				dataState = "01";
			}
			$.confirm(msg, function() {
				MchtAccessPara_dataset.setParameter("certifiStatus", dataState);
				btnStatusSub.click();
			}, function() {
				return false;
			});
		}
		function btnStatusSub_postSubmit() {
			$.success("操作成功!", function() {
				MchtAccessPara_dataset.flushData(MchtAccessPara_dataset.pageIndex);
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
			//全部字段设置为-只读	
			MchtAccessPara_dataset.setReadOnly(true);
			//打开窗口
			window_winDetail.open();
		}
		function window_winDetail_beforeClose(wmf) {
			MchtAccessPara_dataset.setReadOnly(false);
		}
	</script>
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script> -->
 	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/GetMd5Value.js"></script>
</snow:page>