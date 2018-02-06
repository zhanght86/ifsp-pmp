<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="券商app管理">
	<script src="../../common/upload/js/ajaxfileupload.js"></script>
	<!-- 券商app信息 dataset -->
	<snow:dataset id="appMng"
		path="com.ruimin.ifs.pmp.brokerageAppMng.dataset.brokerageAppMng"
		init="true" submitMode="current"></snow:dataset>

	<!-- 查询条件 -->
	<snow:query id="qryQuery" label="查询条件" dataset="appMng"
		collapsible="false" fieldstr="qAppId,qAppName,qTraderName,qOrgId,qStat"></snow:query>
	<!-- 数据列表 -->
	<snow:grid id="grdQueryList" dataset="appMng" height="99%"
		label="app信息列表" fitcolumns="true" 
		fieldstr="appid,appName,traderName,orgId,freeAmt,stat,opr"
		paginationbar="btnAdd,btnUpd,btnStatus"></snow:grid>

	<!-- 新增窗口 -->
	<snow:window id="windAdd" closable="true" width="900" title="新增券商app" modal="true" buttons="btnAddSubmit">
		<snow:form id="frmAdd" dataset="appMng" border="false" fieldstr="appid,appName,traderName,freeAmt,orgId,md5Key"></snow:form>
		<snow:button id="btnAddSubmit" dataset="appMng" hidden="true"></snow:button>
	</snow:window>
	<!-- 修改窗口 -->
	<snow:window id="windUpd" closable="true" width="900" title="修改券商app" modal="true" buttons="btnUpdSubmit">
		<snow:form id="frmUpd" dataset="appMng" border="false" fieldstr="appid,appName,traderName,freeAmt,orgId,md5Key"></snow:form>
		<snow:button id="btnUpdSubmit" dataset="appMng" hidden="true"></snow:button>
	</snow:window>
	<!-- 详情窗口 -->
	<snow:window id="windDtl" closable="true" width="900" title="新增券商app" modal="true">
		<snow:form id="frmDtl" dataset="appMng" border="false" fieldstr="appid,appName,traderName,freeAmt,stat,orgId,md5Key"></snow:form>
	</snow:window>
	<!-- 启用/停用按钮 -->
	<snow:button id="btnStatusSubmit" dataset="appMng" hidden="true"></snow:button>
	<script>
	var isAmt20 = /^([1-9][\d]{0,17}|[1-9][\d]{0,2}(\,[\d]{3})*|0)(\.[\d]{1,2})?$/;//最大长度20位，最多包含两位小数，支持带','格式，需和isAmtLength20匹配使用
	var isAmtLength20 = /^([\d]{0,18})(\.[\d]{1,2})?$/;//去除','，最大长度20位，最多包含两位小数
		//初始刷新数据列表
		function grdQueryList_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:showDetail()\">详情</a></span>";
			} else {
				return "&nbsp;";
			}
		}
		/**************************新增**************************/
		//点击新增按钮
		function btnAdd_onClick(){
			// 去方法中获md5的值
//			$.ajaxFileUpload({
//				url : '${pageContext.request.contextPath}/GetMd5ValueServlet',
//				success : function(data, status) {
//					var result = data;
//					if (data.length > 6) {
//						result = data.substring(data.length - 38,
//								data.length - 6);
//					}
//					appMng_dataset.setValue("md5Key", result);
//					return result;
//				},
//			});
			dwr.engine.setAsync(false);
			var data = GetMd5Value.reset();
			dwr.engine.setAsync(true);
			var result = data;
			appMng_dataset.setValue("md5Key", result);
			appMng_dataset.setFieldReadOnly("appid", false);
			window_windAdd.open();
		}
		//点击新增提交按钮检查
		function btnAddSubmit_onClickCheck(){
			var freeAmt = appMng_dataset.getValue("freeAmt");
			if(isAmt20.test(freeAmt)){					
				if(!isAmtLength20.test(freeAmt.replace(/,/g,''))){
					$.warn("【免密金额】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
					return false;
				}
			}else{
				$.warn("【免密金额】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
				return false;	
			}
			var appid = appMng_dataset.getValue("appid");
			if(appid.length !=11){
				$.warn("【appid】格式错误（必须为11位数字）");
				return false;
			}
			return true;
		}
		function btnAddSubmit_postSubmit(){
			$.success("操作成功!",function(){
				appMng_dataset.setFieldReadOnly("appid", true);
     		   window_windAdd.close();
     		   appMng_dataset.flushData(appMng_dataset.pageIndex);
     	   });
		}
		function window_windAdd_beforeClose(){
			appMng_dataset.setFieldReadOnly("appid", true);
			appMng_dataset.cancelRecord();
		}
		/**************************修改**************************/
		//点击修改按钮
		function btnUpd_onClick(){
			var appid = appMng_dataset.getValue("appid");
			if(!appid){
				$.warn("请先选择一条记录");
				return false;
			}
			 window_windUpd.open();
		}
		//点击修改提交按钮检查
		function btnUpdSubmit_onClickCheck(){
			var freeAmt = appMng_dataset.getValue("freeAmt");
			if(isAmt20.test(freeAmt)){					
				if(!isAmtLength20.test(freeAmt.replace(/,/g,''))){
					$.warn("【免密金额】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
					return false;
				}
			}else{
				$.warn("【免密金额】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
				return false;	
			}
			return true;
		}
		function btnUpdSubmit_postSubmit(){
			$.success("操作成功!",function(){
     		   window_windUpd.close();
     		   appMng_dataset.flushData(appMng_dataset.pageIndex);
     	   });
		}
		function window_windUpd_beforeClose(){
			appMng_dataset.cancelRecord();
		}
		/**************************启用/停用**************************/
		//点击启用/停用按钮
		function btnStatus_onClick(){
			var state = appMng_dataset.getValue("stat");
			if(!state){
				$.warn("请先选择一条记录");
				return false;
			}
			var confirmMessage ="";
			if(state == '00'){
				confirmMessage = "是否确认[停用]该APP信息？";
			}else if(state == '99'){
				confirmMessage = "是否确认[启用]该APP信息？";
			}
			$.confirm(confirmMessage, function() {
				btnStatusSubmit.click();
			}, function() {
				return false;
			});
		}
		//启用/停用按钮回调
		function btnStatusSubmit_postSubmit(){
			$.success("操作成功!",function(){
     		   appMng_dataset.flushData(appMng_dataset.pageIndex);
     	   });
		}
		/**************************详情**************************/
		function showDetail(){
			appMng_dataset.setFieldReadOnly("appName", true);
			appMng_dataset.setFieldReadOnly("traderName", true);
			appMng_dataset.setFieldReadOnly("freeAmt", true);
			appMng_dataset.setFieldReadOnly("orgId", true);
			//appMng_dataset.setFieldReadOnly("commAddr", true);
			appMng_dataset.setFieldReadOnly("md5Key", true);
			 window_windDtl.open();
		}
		function window_windDtl_beforeClose(wmf){
			appMng_dataset.setFieldReadOnly("appName", false);
			appMng_dataset.setFieldReadOnly("traderName", false);
			appMng_dataset.setFieldReadOnly("freeAmt", false);
			appMng_dataset.setFieldReadOnly("orgId", false);
			//appMng_dataset.setFieldReadOnly("commAddr", false);
			appMng_dataset.setFieldReadOnly("md5Key", false);
		}
	</script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/GetMd5Value.js"></script>
</snow:page>