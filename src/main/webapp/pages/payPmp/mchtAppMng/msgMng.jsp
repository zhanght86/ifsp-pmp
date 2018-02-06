<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="消息管理">
	<!-- 数据集加载 -->
		<snow:dataset id="msgMng" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.msgMng" init="true" submitMode="current" ></snow:dataset>		
	<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="msgMng" label="查询条件" fieldstr="qmsgDate,qmsgTitle,qmsgChn,qsendWay"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="msgMng" label="查询列表" fieldstr="msgChn,sendWay,msgDate,msgTime,msgType,msgTitle,opr"
		height = "99%" paginationbar="btnAdd,btnDel,btnSend"></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="" buttons="btnReturn" modal="true" closable="true" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="msgInfoDetail" label="消息信息" dataset="msgMng" fieldstr="msgChn,sendWay,msgDate,msgTime,msgType,msgExpiry,msgTitle,msgIntro,msgDesc,msgSign"></snow:form>
			<snow:button id="btnRtn" dataset="msgMng" hidden="false"></snow:button>
		</snow:window>
		
		<!-- 新增窗口 -->
		<snow:window id="winAdd" title=""  modal="true" closable="true" buttons="btnAddSub" width="850">
			<!-- No1 基本信息模块 -->
			<snow:form id="msgInfoDetail" label="消息新增" dataset="msgMng" fieldstr="msgChn,sendWay,msgType,msgExpiry,msgTitle,msgIntro,msgDesc,msgSign"></snow:form>
			<snow:button id="btnAddSub" dataset="msgMng" hidden="true"></snow:button>
		</snow:window>
		
		<!-- 推送窗口 -->
		<snow:window id="winSend" title=""  modal="true" closable="true" buttons="btnSendSub" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="msgInfoSend" label="消息推送" dataset="msgMng" fieldstr="deviceType,msgExpires,sendTm"></snow:form>
			<snow:button id="btnSendSub" dataset="msgMng" hidden="true"></snow:button>
		</snow:window>
		
		<!-- 推送查询窗口 -->
		<snow:window id="winSendQuery" title=""  modal="true" closable="true" buttons="btnQuerySub" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="msgSendQuery" label="消息推送查询" dataset="msgMng" fieldstr="deviceType"></snow:form>
			<snow:button id="btnQuerySub" dataset="msgMng" hidden="true"></snow:button>
		</snow:window>
		
		<!-- 消息查询结果窗口 -->
		<snow:window id="winSendQueryInfo" title=""  modal="true" closable="true" width="800">
			<!-- No1 基本信息模块 -->
			<snow:grid id="msgSendQueryInfo" label="消息推送查询结果" dataset="msgMng" fieldstr="msgId,msgStatus,sendTime,successCount" height = "99%"></snow:grid>
		</snow:window>
		<snow:button  hidden="true" id="btnDelSub" dataset="msgMng"></snow:button>
	<script type="text/javascript">
	var isDateTime = /^[0-9]{14}$/;
/****************************************详情******************************************/
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
 		if (record) {
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(){
		//全部字段设置为-只读
		msgMng_dataset.setReadOnly(true);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		msgMng_dataset.setReadOnly(false);//还原写入权限
		msgMng_dataset.clearData();	
		msgMng_dataset.flushData(msgMng_dataset.pageIndex);
	}

	function btnAdd_onClickCheck(){		
		return true;
	}
	
	/**打开修改窗口*/
	function btnAdd_onClick(){
		msgMng_dataset.setFieldRequired("msgChn",true);
		msgMng_dataset.setFieldRequired("sendWay",true);
		msgMng_dataset.setFieldRequired("msgType",true);
		msgMng_dataset.setFieldRequired("msgExpiry",true);
		msgMng_dataset.setFieldRequired("msgTitle",true);
		msgMng_dataset.setFieldRequired("msgIntro",true);
		msgMng_dataset.setFieldRequired("msgDesc",true);
		msgMng_dataset.setFieldRequired("msgSign",true);
		//打开窗口
		window_winAdd.open();
	}
		
	/**提交前检验数据格式*/
	var isDesc20 = /^\S{1,20}$/;
	var isDesc30 = /^\S{1,30}$/;
	var isDesc32 = /^\S{1,32}$/;
	var isDesc128 = /^\S{1,128}$/;
	function btnAddSub_onClickCheck(){
		var msgExpiry = msgMng_dataset.getValue("msgExpiry");
		var msgTitle = msgMng_dataset.getValue("msgTitle");
		var msgIntro = msgMng_dataset.getValue("msgIntro");
		var msgDesc = msgMng_dataset.getValue("msgDesc");
		var msgSign = msgMng_dataset.getValue("msgSign");
		
// 		if(msgExpiry != null && msgExpiry != ""){
// 			if(!isDateTime.test(msgExpiry)){			
// 				$.warn("【消息有效期】格式错误（14位数字yyyymmddhhmmss）");
// 				return false;
// 			}			
// 		}
		
		if(msgTitle != null && msgTitle != ""){
			if(msgTitle.length>64){			
				$.warn("【消息标题】长度错误（最大长度64位）");
				return false;
			}			
		}
		
		if(msgIntro != null && msgIntro != ""){
			if(msgIntro.length>128){			
				$.warn("【消息简介】长度错误（最大长度128位）");
				return false;
			}			
		}
		
		if(msgDesc != null && msgDesc != ""){
			if(msgDesc.length>2000){
				$.warn("【消息内容】长度错误（最大长度2000位）");
				return false;
			}			
		}
		
		if(msgSign != null && msgSign != ""){
			if(msgSign.length>128){
				$.warn("【消息落款】长度错误（最大长度128位）");
				return false;
			}			
		}
		
		return true;	
	} 
	
	/**提交*/
	function btnAddSub_postSubmit(){
		$.success("新增成功！",function(){
			window_winAdd.close();
			window.location.reload(true);
		});
	}
	
	/**窗口关闭后清除数据*/
	function window_winAdd_afterClose(){
		msgMng_dataset.clearData();	
		msgMng_dataset.flushData(msgMng_dataset.pageIndex);
	}
	
	function btnDel_onClickCheck(){
		$.confirm("确定要删除吗?", function() {
			btnDelSub.click();
		}, function() {
			return false;
		});
	}
	
	function btnDelSub_postSubmit() {
		$.success("操作成功！");
		msgMng_dataset.flushData(msgMng_dataset.pageIndex);
	}
	
	function btnRtn_onClick(){	
		window_winDetail.close();
		window.location.reload(true);
	}
	
	function btnSend_onClick(){
		msgMng_dataset.setFieldRequired("deviceType",true);
		msgMng_dataset.setFieldRequired("deviceType",true);
// 		msgMng_dataset.setFieldRequired("msgModelType",true);
		//打开窗口
		window_winSend.open();
	}
	
	//打开推送窗口
	function btnSendSub_onClickCheck(){
// 		var msgModelType = msgMng_dataset.getValue("msgModelType");
// 		if(msgModelType == "01"){
// 			msgMng_dataset.setFieldRequired("deviceType",true);
// 			msgMng_dataset.setFieldRequired("userId",false);
// 		}
// 		if(msgModelType == "02"){
// 			msgMng_dataset.setFieldRequired("userId",true);
// 			msgMng_dataset.setFieldRequired("deviceType",false);
// 		}
		msgMng_dataset.setValue("msgModelType","01");
		msgMng_dataset.setValue("messageType","1");
		var sendTm = msgMng_dataset.getValue("sendTm");
		sendTm = $.trim(sendTm);
		if(sendTm != null && sendTm !="" && sendTm <=60){
			$.warn("定时推送时间必须在60秒之后");
			return false;
		}
		return true;	
	} 
	
	/**提交*/
	function btnSendSub_postSubmit(){
		$.success("推送成功！",function(){
			window_winSend.close();
		});
	}
	
	/**窗口关闭后清除数据*/
	function window_winSend_afterClose(){
		msgMng_dataset.clearData();	
		msgMng_dataset.flushData(msgMng_dataset.pageIndex);
		window.location.reload(true);
	}
	
	function btnQuery_onClick(){
		window_winSendQuery.open();
	}
	
	//打开推送窗口
	function btnQuerySub_onClickCheck(){
		return true;
	} 
	
	/**提交*/
	function btnQuerySub_postSubmit(btn,param){
		window_winSendQuery.close();
		window_winSendQueryInfo.open();
	}
	
	</script>
</snow:page>