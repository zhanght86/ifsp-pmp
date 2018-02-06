<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户二维码信息管理">

		<!-- 商户二维码基本信息数据集  -->
		<snow:dataset id="qrcBaseInfoMng" path="com.ruimin.ifs.pmp.mchtMng.dataset.qrcBaseInfoMng"
			 init="true" submitMode="current" ></snow:dataset>
	
		<!-- 查询栏 -->
		<snow:query dataset="qrcBaseInfoMng" label="查询条件" 
			fieldstr="qMchtId,qMchtSimpleName,qMchtUseStat,qmchtAmrNo,qMchtAmrName,qQrcCodeId,qQrcType"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="qrcBaseInfoMng" label="商户二维码基本信息" 
			fieldstr="qrcCodeId[180],mchtId[150],mchtSimpleName[200],qrcType,mchtAmrNo,mchtAmrName,mchtUseStat,crtDate,expiryDateTime,opr"
		height = "99%" paginationbar="btnDownload,btnAgainApply,btnStatus"></snow:grid>
 
 
 		<!-- 查看详情窗口 -->
		<snow:window id="windowDetailRisk" title="商户二维码详情" modal="true"
			closable="true"  >
			<p style="">商户信息</p>
			<snow:form id="formDetailRisk" dataset="qrcBaseInfoMng" border="false"
				label="基本信息"
				fieldstr="mchtId,mchtSimpleName"
				collapsible="false" colnum="4"></snow:form>
			<br />
			<snow:button id="onlyBtnDownload" dataset="qrcBaseInfoMng" hidden="false"></snow:button>
			<p>二维码信息</p>	
			<!-- 显示图片 -->
			<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
			<img id="qrcPicId" src="" style="height: 580px; width: 400px;" /></div>		
		</snow:window>
		
		<div style="display: none;">
			<!-- 确认启用/停用按钮 -->
			<snow:button id="btnStatusSave" dataset="qrcBaseInfoMng"></snow:button>
			<snow:button id="btnDownloadSave" dataset="qrcBaseInfoMng"></snow:button>
			<snow:button id="btnAgainApplySave" dataset="qrcBaseInfoMng"></snow:button>
		</div>
		
		
		<!-- 下载图片 -->
    	<iframe src="" style="display: none;" id="filedown"></iframe>
    	
		
	<script type="text/javascript">
	
	
	
		
		//-----------------------详情----------------------
		function gridMain_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<i class='fa fa-info'></i>&nbsp;<a href=\"JavaScript:showDetails()\">详情</a>";
			}
		}
		//详情链接点击时
		function showDetails() {
			//获取图片字段
			var qrcPicId = qrcBaseInfoMng_dataset.getValue("qrcPicId");
			//获取图片访问地址
			var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
			$("#qrcPicId").attr("src",picIp+qrcPicId);
			window_windowDetailRisk.open();
		}
		//详情框体关闭后
		function window_windowDetailRisk_beforeClose(win) {
			qrcBaseInfoMng_dataset.cancelRecord();
		}
		
		
		//---------------------启用停用-----------------------------
		function btnStatus_onClick() {
			var mchtId = qrcBaseInfoMng_dataset.getValue("mchtId");//商户号
			var qrcCodeId = qrcBaseInfoMng_dataset.getValue("qrcCodeId");//二维码序号
			if (qrcCodeId == '') {
				$.warn("请先选择要修改状态的商户二维码信息记录");
				return false;
			}
			qrcBaseInfoMng_dataset.setParameter("mchtId",mchtId);
			qrcBaseInfoMng_dataset.setParameter("qrcType",qrcBaseInfoMng_dataset.getValue("qrcType"));
			var mchtUseStat = qrcBaseInfoMng_dataset.getValue("mchtUseStat");//二维码使用状态
			var str = "是否要停用当前选中的商户二维码信息？";
			if (mchtUseStat == '0') {//1使用中，0暂停使用
				str = "是否要启用当前选中的商户二维码信息？";
			}
			$.confirm(str, function() {
				qrcBaseInfoMng_dataset.setParameter("qrcCodeId", qrcCodeId);
				qrcBaseInfoMng_dataset.setParameter("mchtUseStat", mchtUseStat);
				btnStatusSave.click();
			}, function() {
				return false;
			});
		}
		//修改状态成功后
		function btnStatusSave_postSubmit(btn) {
			$.success("操作成功!", function() {
				qrcBaseInfoMng_dataset.flushData(qrcBaseInfoMng_dataset.pageIndex);
			});
		}
		
		//-----------------------主界面下载按钮-------------------------
		function btnDownload_onClick(){
			var qrcCodeId = qrcBaseInfoMng_dataset.getValue("qrcCodeId");//二维码序号
			if (qrcCodeId == '') {
				$.warn("请先选择要下载的商户二维码信息");
				return false;
			}
			var qrcPicId = qrcBaseInfoMng_dataset.getValue("qrcPicId");
			var mchtId = qrcBaseInfoMng_dataset.getValue("mchtId");
			var url ='<snow:url flowId="com.ruimin.ifs.pmp.mchtMng.comp.QrcMngAction:downLoadTest?picId='+qrcPicId+'&mchtId='+mchtId+'"/>';
			document.getElementById("filedown").src = url;
		}
		
		
		//-----------------------详情页面下载按钮-------------------------
		function onlyBtnDownload_onClick(){
			var qrcCodeId = qrcBaseInfoMng_dataset.getValue("qrcCodeId");//二维码序号
			if (qrcCodeId == '') {
				$.warn("请先选择要下载的商户二维码信息");
				return false;
			}
			var qrcPicId = qrcBaseInfoMng_dataset.getValue("qrcPicId");
			var mchtId = qrcBaseInfoMng_dataset.getValue("mchtId");
			var url ='<snow:url flowId="com.ruimin.ifs.pmp.mchtMng.comp.QrcMngAction:downLoadTest?picId='+qrcPicId+'&mchtId='+mchtId+'"/>';
			document.getElementById("filedown").src = url;
		}
		

		
		//-------------------------重新申请--------------------
		function btnAgainApply_onClick(){
			var mchtId = qrcBaseInfoMng_dataset.getValue("mchtId");//商户号
			if (mchtId == '') {
				$.warn("请先选择要重新申请的商户二维码信息");
				return false;
			}
			qrcBaseInfoMng_dataset.setParameter("mchtId",mchtId);
			qrcBaseInfoMng_dataset.setParameter("qrcType",qrcBaseInfoMng_dataset.getValue("qrcType"));
			qrcBaseInfoMng_dataset.setParameter("qrcStat",qrcBaseInfoMng_dataset.getValue("qrcStat"));
			qrcBaseInfoMng_dataset.setParameter("mchtUseStat",qrcBaseInfoMng_dataset.getValue("mchtUseStat"));
			btnAgainApplySave.click();
		}
		function btnAgainApplySave_postSubmit(btn) {
			$.success("重新申请商户二维码成功!", function() {
				qrcBaseInfoMng_dataset.flushData(qrcBaseInfoMng_dataset.pageIndex);
			});
		}
		
	</script>
</snow:page>