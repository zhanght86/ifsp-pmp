<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ruimin.ifs.core.util.*"%>

<%@ page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>


<snow:page title="广告位管理">
	<!-- 系统公告数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.mchtAppMng.dataset.adMng" id="adMng" submitMode="current" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="adMng" dataset="adMng" fieldstr="qadPst,qadInfo,qadStat" border="false"></snow:query>
	<snow:grid dataset="adMng" label="查询列表" id="gridMain"
		fieldstr="adPst,picPst,adInfo,adStat[250],opr" fitcolumns="true"
		height="99%" paginationbar="btnAdd,btnUpd,disableEnable,btnDel"></snow:grid>
		
	<!-- 详情窗口 -->
	<snow:window id="winDetail" title="" buttons="btnReturn" modal="true" closable="true" width="850">
		<!-- No1 基本信息模块 -->
		<snow:form id="adInfoDetail" label="广告详情" dataset="adMng" fieldstr="adPst,picPst,adInfo,adStat"></snow:form>
		<div
			style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
			<img id="img_adPicIdDetail" src="" style="height: 300px; width: 820px;" />
		</div>
	</snow:window>	
	
	<!-- 新增 -->
	<snow:window id="winAdd" title="广告位新增" modal="true" closable="true"
		buttons="btnSave" width="850" height="700">
		<snow:form id="adAdd" dataset="adMng"
			border="false" label="保存" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="adPst,picPst,adStat,adInfo">
		</snow:form>
		<div id="box" style="width: 800; height: 600">
			<div><iframe id="upload" name="upload" src="../../payPmp/pubTool/uploadImage.jsp"></iframe></div>
			<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center">
				<img id="picAdd" src="" style="height: 300px; width: 820px;" />
			</div>
		</div>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="adMng" hidden="true"></snow:button>
	</snow:window>
	
	<!-- ======================修改窗体===================================================== -->
	<snow:window id="winUpd" title="广告位修改" modal="true"
		closable="true" buttons="btnUpdate" width="850" height="750">
		<snow:form id="adUpd" dataset="adMng"
			border="false" label="" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="adPst,picPst,adStat,adInfo">
		</snow:form>
		<!-- 图片显示 -->
		
		<div id="box" style="width: 100%; height: 400px">
			<iframe id="uploadUpdate" name="uploadUpdate"
				src="../../payPmp/pubTool/uploadImage.jsp" style="height: 40%"></iframe>
		<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
			<img id="picUpd" src="" style="height: 300px; width: 820px;" />
		</div>
		</div>
		<!-- 按钮: 修改 -->
		<snow:button id="btnUpdate" dataset="adMng" hidden="true"></snow:button>
	</snow:window>


	<!-- 启用/停用按钮 -->
	<snow:button id="btnAdd" dataset="adMng" hidden="true"></snow:button>
	<snow:button id="btnDisableEnable" dataset="adMng" hidden="true"></snow:button>
	<snow:button id="btnDelSub" dataset="adMng" hidden="true"></snow:button>
	
<style type="text/css">
#box {
	/* width: 750px;
	height: 550px;
	border: 1px solid 0000; */
}
</style>

	<script type="text/javascript">
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
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
 		if (record) {
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(){
		//全部字段设置为-只读
		adMng_dataset.setReadOnly(true);
		var picId=adMng_dataset.getValue("picId");
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		$("#img_adPicIdDetail").attr("src",picIp+picId);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		adMng_dataset.clearData();	
		adMng_dataset.flushData(adMng_dataset.pageIndex);
	}
	
	var isDesc250 = /^\S{1,250}$/;
	var isDesc32 = /^\S{1,32}$/;
	var isPicId = /^[0-9]{32}$/;
	/* =======================================新增公告=============================================== */
	//新增单击按钮
	function btnAdd_onClick() {
		var length = adMng_dataset.length;
		if(length<7){
			adMng_dataset.setReadOnly(false);
			adMng_dataset.setFieldRequired("adPst", true);
			adMng_dataset.setFieldRequired("picPst", true);
			adMng_dataset.setFieldRequired("adStat", true);
			window_winAdd.open();
		}else{
			$.warn("广告数量已达上限！");
			adMng_dataset.cancelRecord();
		}
	}
	
	//确认保存之前验证操作
	function btnSave_onClickCheck() {	
		var adPst = adMng_dataset.getValue("adPst");
		var picPst = adMng_dataset.getValue("picPst");
		var adStat = adMng_dataset.getValue("adStat");
		var adInfo = adMng_dataset.getValue("adInfo");
		var picId = window.frames["upload"].document.getElementById("lgPic").value;
		
		if(adPst == "" || adPst == null){
			$.warn("【广告位置】必须输入！");
			return false;
		}
		if(picPst == "" || picPst == null){
			$.warn("【图片位置】必须输入！");
			return false;
		}
		if(adStat == "" || adStat == null){
			$.warn("【使用状态】必须输入！");
			return false;
		}
		if(adInfo != null && adInfo !=""){			
			if(adInfo.length>128){
				$.warn("【广告描述】长度错误（最大长度128位）");
				return false;
			}
		}
		if(picId == null || picId == ""){
			$.warn("请上传广告位图片！");
			return false;
		}
		adMng_dataset.setValue("picId",picId);	
	
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
			window_winAdd.close();
			adMng_dataset.flushData(adMng_dataset.pageIndex);
		});
	}
	//新增关闭窗口刷新
	function window_winAdd_afterClose(){
		lgPicList = '';
		window.frames["upload"].location.replace("../../payPmp/pubTool/uploadImage.jsp");
		// 清空图片显示信息
		window.frames["upload"].document.getElementById("lgPic").value='';
		adMng_dataset.flushData(adMng_dataset.pageIndex);
		$("#picAdd").attr("src","");
		$("#picUpd").attr("src","");
		window.location.reload(true);
	}
	
	//启用、停用系统公告单击事件
    function disableEnable_onClick(){
		var adId = adMng_dataset.getValue("adId");
    	if(adId != null && adId != ""){
	    	var adStat=adMng_dataset.getValue("adStat");
	    	if('0'== adStat){
	    		$.confirm("确定要停用吗?", function() {
	    			btnDisableEnable.click();
	    		}, function() {
	    			return false;
	    		});	
	    	}
	    	if('1'== adStat){
	    		$.confirm("确定要启用吗?", function() {
	    			btnDisableEnable.click();
	    		}, function() {
	    			return false;
	    		});	
	    	}
		}
	}
	function btnDisableEnable_postSubmit() {
		$.success("操作成功！");
		adMng_dataset.flushData(adMng_dataset.pageIndex);
	}
	
	//删除系统公告单击事件
    function btnDel_onClick(){
		var adId = adMng_dataset.getValue("adId");
    	if(adId != null && adId != ""){
    		$.confirm("确定要删除吗?", function() {
	    		btnDelSub.click();
    		}, function() {
    			return false;
    		});	
		}
	}
	function btnDelSub_postSubmit() {
		$.success("操作成功！");
		adMng_dataset.flushData(adMng_dataset.pageIndex);
	}
	
	/* =============================================修改公告============================================ */
	//单击修改函数，打开修改窗体
	function btnUpd_onClick(){
		adMng_dataset.setFieldRequired("adPst", true);
		adMng_dataset.setFieldRequired("picPst", true);
		adMng_dataset.setReadOnly(false);
		adMng_dataset.setFieldReadOnly("adStat", true);
		var picId=adMng_dataset.getValue("picId");
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		$("#picUpd").attr("src",picIp+picId);
		
		var adId = adMng_dataset.getValue("adId");
		if(adId != null && adId != ""){
			window_winUpd.show();			
		}
	}
	//确认修改单击事件
	function btnUpdate_onClickCheck(){
		var adPst = adMng_dataset.getValue("adPst");
		var picPst = adMng_dataset.getValue("picPst");
		var picId = window.frames["uploadUpdate"].document.getElementById("lgPic").value;
		//var picIdOrg = adMng_dataset.getValue("picId");
		if(adPst == "" || adPst == null){
			$.warn("【广告位置】必须输入！");
			return false;
		}
		if(picPst == "" || picPst == null){
			$.warn("【图片位置】必须输入！");
			return false;
		}
		if(picId != null && picId != ""){
			adMng_dataset.setValue("picId",picId);
		}
		// 点击后三秒内不能再次提交
		btnSave.setDisabled(true);
		var timer = setInterval(function(){//开启定时器
			btnSave.setDisabled(false);
			clearInterval(timer);    //清除定时器
		},3000); 
		return picJudge(picId, window.frames["uploadUpdate"].document.getElementById("result").value);
	}
	//变更关闭窗口刷新
	function window_winUpd_afterClose(){
		lgPicList = '';
		window.frames["uploadUpdate"].location.replace("../../payPmp/pubTool/uploadImage_ad.jsp");
		// 清空图片显示信息
		window.frames["uploadUpdate"].document.getElementById("lgPic").value='';
		adMng_dataset.flushData(adMng_dataset.pageIndex);
		$("#picAdd").attr("src","");
		$("#picUpd").attr("src","");
		window.location.reload(true);
	}
	//修改成功
	function btnUpdate_postSubmit() {
		$.success("操作成功!", function() {
			lgPicList = '';
			window.frames["uploadUpdate"].location.replace("../../payPmp/pubTool/uploadImage_ad.jsp");
			// 清空图片显示信息
			window.frames["uploadUpdate"].document.getElementById("lgPic").value='';
			//关闭窗体，刷新数据
			window_winUpd.close();
 			adMng_dataset.flushData(adMng_dataset.pageIndex);
		});		
	}
	</script>
</snow:page>
