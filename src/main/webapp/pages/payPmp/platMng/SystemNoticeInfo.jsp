<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>

<%@ page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>


<snow:page title="系统公告">
	<!-- 系统公告数据集 -->
	<snow:dataset
		path="com.ruimin.ifs.pmp.platMng.dataset.systemNoticeInfo"
		id="systemNoticeInfo" submitMode="current" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="systemNoticeInfo"
		dataset="systemNoticeInfo"
		fieldstr="qcrtDateTime,qnoticeTitle,qnoticeState" border="false"></snow:query>
	<snow:grid dataset="systemNoticeInfo" label="查询列表"
		id="systemNoticeInfo"
		fieldstr="crtDateTime,noticeTitle,noticeState,oper[250]" fitcolumns="true"
		height="99%" paginationbar="btnAdd,btnUpt,disableEnable"></snow:grid>

	<!-- ========================公告预览窗口==================================================== -->
	<snow:window id="noticeTitle" title="系统公告" modal="true" closable="true"
		buttons="" width="850" height="700">
		<snow:form id="systemNoticeInfo" dataset="systemNoticeInfo"
			border="false" label="" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="noticeTitle,crtDateTime,noticeContent">
		</snow:form>
		<!-- 图片显示 -->
		<div
			style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
			<img id="img_noticePicId" src="" style="height: 550px; width: 820px;" />
		</div>
	</snow:window>
	<style type="text/css">
#box {
	width: 750px;
	height: 550px;
	border: 1px solid 0000;
}
</style>
	<!-- ========================公告新增窗口==================================================== -->
	<snow:window id="windowAddId" title="系统公告--> 新增" modal="true"
		closable="true" buttons="btnSave" height="750">
		<snow:form id="systemNoticeInfo" dataset="systemNoticeInfo"
			border="false" label="保存" fieldstr="noticeTitle,noticeContent" collapsible="false"
			colnum="4">
		</snow:form>
			<div><iframe id="upload" name="upload" src="../../payPmp/pubTool/uploadImage.jsp" ></iframe></div>
			<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd" src="" style="height: 550px; width: 820px;" /></div>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="systemNoticeInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- ======================系统公告修改窗体===================================================== -->
	<snow:window id="windowModifyId" title="系统公告--> 修改" modal="true"
		closable="true" buttons="btnUpdate" height="750">
		<snow:form id="systemNoticeInfo" dataset="systemNoticeInfo"
			border="false" label="修改" fieldstr="noticeTitle,noticeContent" collapsible="false"
			colnum="4">
		</snow:form>
		<!-- 图片显示 -->
		<div><iframe id="uploadUpdate" name="uploadUpdate" src="../../payPmp/pubTool/uploadImage.jsp" ></iframe></div>
		<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picUpd" src="" style="height: 550px; width: 820px;" /></div>		
		<!-- 按钮: 修改 -->
		<snow:button id="btnUpdate" dataset="systemNoticeInfo" hidden="true"></snow:button>
	</snow:window>


	<!-- 启用/停用按钮 -->
	<snow:button id="btnDisableEnable" dataset="systemNoticeInfo"
		hidden="true"></snow:button>
	<script type="text/javascript">
	
	/***************图片控制***************/
	function picJudge(lgPic,result){
		if (lgPic != "" && result == "false"){		
			$.warn("请上传已选择的图片！");
			return false;
		}else return true;
	}
	
	function importFileCallBack(result,picSrc,picNum) {
		if (result) {
			$("#picAdd").attr("src",picSrc);
			$("#picUpd").attr("src",picSrc);
		}
	}
	
	
	//系统公告链接
	function systemNoticeInfo_oper_onRefresh(record, fieldId, fieldValue) {
	 			return "<span style='width:100%;text-align:center' class='fa fa-eye'><a href=\"JavaScript:onClickNoticeTitle()\">预览</a></span>";
 	}
	/* =======================================预览公告=============================================== */
	//打开公告窗口
	function onClickNoticeTitle(){
		systemNoticeInfo_dataset.setFieldReadOnly("crtDateTime", true);
		systemNoticeInfo_dataset.setFieldReadOnly("noticeTitle", true);
		systemNoticeInfo_dataset.setFieldReadOnly("noticePicId", true);
		systemNoticeInfo_dataset.setFieldReadOnly("noticeContent", true);
		var noticePicId=systemNoticeInfo_dataset.getValue("noticePicId");
		window_noticeTitle.open();
		
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		$("#img_noticePicId").attr("src",picIp+noticePicId);
		
		systemNoticeInfo_dataset.setParameter("noticeNo",noticeNo);
		systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
	}
	//如果关掉验证窗口则把更改窗口改为可编辑模式(定义窗口关闭后事件)
	function window_noticeTitle_afterClose(){
		systemNoticeInfo_dataset.setFieldReadOnly("crtDateTime", false);
		systemNoticeInfo_dataset.setFieldReadOnly("noticeTitle", false);
		systemNoticeInfo_dataset.setFieldReadOnly("noticePicId", false);
		systemNoticeInfo_dataset.setFieldReadOnly("noticeContent", false);
	}
	
	/* =======================================新增公告=============================================== */
	//新增单击按钮打开新增窗体
	function btnAdd_onClick() {		 
		window_windowAddId.open();
	}
	//确认保存之前验证操作
	function btnSave_onClickCheck(button, commit) {	
		
		//设置系统公告标题
		var noticeTitle=systemNoticeInfo_dataset.getValue("noticeTitle");
		if(noticeTitle.length>21){
     		$.warn("系统公告标题超过最大长度【21】！");
     		return false;
     	}
		systemNoticeInfo_dataset.setParameter("noticeTitle",noticeTitle);
		
		
		//设置公告内容
		var noticeContent = systemNoticeInfo_dataset.getValue("noticeContent");
		if(noticeContent.length>200){
			$.warn("系统公告内容超过最大长度【200】！");
     		return false;
		}
		systemNoticeInfo_dataset.setParameter("noticeContent",noticeContent);
		/** 设置图片信息 */
		var picId = window.frames["upload"].document.getElementById("lgPic").value;
		systemNoticeInfo_dataset.setParameter("noticePicId", picId);
		// 点击后三秒内不能再次提交
		btnSave.setDisabled(true);
		var timer = setInterval(function(){//开启定时器
			btnSave.setDisabled(false);
			clearInterval(timer);    //清除定时器
		},3000); 
		return picJudge(picId, window.frames["upload"].document.getElementById("result").value);
	}
	//保存成功
	function btnSave_postSubmit() {
		$.success("操作成功!", function() {
			lgPicList = '';
			window.frames["upload"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
			// 清空图片显示信息
			window.frames["upload"].document.getElementById("lgPic").value='';
			window_windowAddId.close();
			systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
		});
	}
	//新增关闭窗口刷新
	function window_windowAddId_afterClose(){
		lgPicList = '';
		window.frames["upload"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
		// 清空图片显示信息
		window.frames["upload"].document.getElementById("lgPic").value='';
		systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
		$("#picAdd").attr("src","");
		$("#picUpd").attr("src","");
	}
	
	//启用、停用系统公告单击事件
    function disableEnable_onClick(){
    	var noticeState=systemNoticeInfo_dataset.getValue("noticeState");
    	if('00'==noticeState){
    		$.confirm("确定要停用吗?", function() {
    			btnDisableEnable.click();
    		}, function() {
    			return false;
    		});	
    	}
    	if('99'==noticeState){
    		$.confirm("确定要启用吗?", function() {
    			btnDisableEnable.click();
    		}, function() {
    			return false;
    		});	
    	}
	}
	function btnDisableEnable_postSubmit() {
		$.success("操作成功！");
		systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
	}
	
	/* =============================================修改公告============================================ */
	//单击修改函数，打开修改窗体
	function btnUpt_onClick(){
		var noticePicId=systemNoticeInfo_dataset.getValue("noticePicId");
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		$("#picUpd").attr("src",picIp+noticePicId);
		window_windowModifyId.show();
	}
	//确认修改单击事件
	function btnUpdate_onClickCheck(){
		 //设置系统公告标题
		var noticeTitle=systemNoticeInfo_dataset.getValue("noticeTitle");
		if(noticeTitle.length>21){
     		$.warn("系统公告标题超过最大长度【21】！");
     		return false;
     	}
		systemNoticeInfo_dataset.setParameter("noticeTitle",noticeTitle);
		
		//设置公告内容
		var noticeContent = systemNoticeInfo_dataset.getValue("noticeContent");
		if(noticeContent.length>200){
			$.warn("系统公告内容超过最大长度【200】！");
     		return false;
		}
		systemNoticeInfo_dataset.setParameter("noticeContent",noticeContent);
		
		/** 设置图片信息 */
		var picId = window.frames["uploadUpdate"].document.getElementById("lgPic").value;
		systemNoticeInfo_dataset.setParameter("noticePicId", picId);
		// 点击后三秒内不能再次提交
		btnSave.setDisabled(true);
		var timer = setInterval(function(){//开启定时器
			btnSave.setDisabled(false);
			clearInterval(timer);    //清除定时器
		},3000); 	
		return picJudge(picId, window.frames["uploadUpdate"].document.getElementById("result").value);
	}
	//变更关闭窗口刷新
	function window_windowModifyId_afterClose(){
		lgPicList = '';
		window.frames["uploadUpdate"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
		// 清空图片显示信息
		window.frames["uploadUpdate"].document.getElementById("lgPic").value='';
		systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
		$("#picAdd").attr("src","");
		$("#picUpd").attr("src","");
	}
	//修改成功
	function btnUpdate_postSubmit() {
		$.success("操作成功!", function() {
			lgPicList = '';
			window.frames["uploadUpdate"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
			// 清空图片显示信息
			window.frames["uploadUpdate"].document.getElementById("lgPic").value='';
			//关闭窗体，刷新数据
			window_windowModifyId.close();
			systemNoticeInfo_dataset.flushData(systemNoticeInfo_dataset.pageIndex);
		});		
	}
	</script>

</snow:page>
