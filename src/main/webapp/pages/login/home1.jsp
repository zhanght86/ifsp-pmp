<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Properties"%>
<%@ page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<snow:page title="home">
	<!--公告数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.platMng.dataset.home" id="home"
		submitMode="current" init="true"></snow:dataset>
	<!--审核数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.platMng.dataset.auditNewInfoUser" id="check"
 		submitMode="current" init="true"></snow:dataset> 
	<!-- 主页布局 -->
	<snow:layout id="layout1">
		<!-- 上面布局 -->
		<snow:Layouttop id="" height="140">
			<!-- 上面布局分成两个div -->
			<div style="width: 100%; margin: 0 auto">
				<!-- 第一个div显示平台名称 -->
				<div
					style="width: 70%; height: 140px; background-color: #438eb9; float: left">
					<p
						style="color: white; font-size: 40px; margin-top: 10px; margin-left: 48px;">证通二维码支付</p>
					<p
						style="color: white; font-size: 40px; margin-top: 15px; margin-left: 300px;">统一管理平台</p>
				</div>
				<!-- 第二个div显示右侧信息 -->
				<div
					style="width: 30%; height: 140px; background-color: #438eb9; float: left">
					<p
						style="color: white; font-size: 15px; margin-left: 50px; margin-top: 30px;"><%=SessionUtil.getSessionUserInfo(request).getBrName()%>的
					</p>
					<p style="color: white; font-size: 15px; margin-left: 50px;">
						<%=SessionUtil.getSessionUserInfo(request).getTlrName()%>,
						<%if(new Date().getHours()>=0 && new Date().getHours()<=12){//看看当前时间是在0点到中午12点之间 %>
						上午好
						<%}else{%>
						下午好<%}%>
					</p>
					<p style="color: white; font-size: 15px; margin-left: 50px;">
						登录日期：<%= 
                 new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())//获取系统时间 
                 %>
					</p>
					<p style="color: white; font-size: 15px; margin-left: 50px;">
						登录时间：<%= 
                 new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())//获取系统时间 
                 %>
					</p>
					<p style="color: white; font-size: 15px; margin-left: 50px;">
						登录IP&nbsp;&nbsp;&nbsp;&nbsp;：<%=SessionUtil.getSessionUserInfo(request).getIp()%>
					</p>
				</div>
			</div>
		</snow:Layouttop>
		<!-- 左侧布局占一半 -->
		<snow:Layoutright id="" width="50%">
			<!-- 系统公告  -->
			<snow:grid id="systemNotice" width="100%" dataset="home"
				fieldstr="crtDateTime[200],noticeTitle" fitcolumns="true" border="true"
				label="系统公告" height="99%" pagination="true">
			</snow:grid>
		</snow:Layoutright>
		<!-- 右侧布局占一半 -->
		<snow:Layoutleft id="" width="50%">
			<!-- 待审核,此处功能没定，此处的字段，数据集，及其查询内容与实际功能无关，仅仅作为页面展示 -->
			<snow:grid id="checkGrid" width="100%" dataset="check"
				fieldstr="crtDateTime[200],auditDesc" fitcolumns="true" border="true"
				label="待审核" height="99%" pagination="true">
			</snow:grid>
		</snow:Layoutleft>
		<snow:Layoutcenter id="" width="0%">
		</snow:Layoutcenter>
	</snow:layout>

	<!-- 公告表单窗体 -->
	<snow:window id="noticeTitle" title="系统公告" modal="true" closable="true"
		buttons="" width="850" height="700">
		<snow:form id="noticeTitle" dataset="home" border="false" label=""
			collapsible="true" colnum="4" labelwidth="100"
			fieldstr="noticeTitle,crtDateTime,noticeContent">
		</snow:form>
		<!-- 图片显示 -->
		<div
			style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
			<img id="img_noticePicId" src="" style="height: 550px; width: 820px;"/>
		</div>
	</snow:window>


	<script type="text/javascript">
	
	
	//页面显示该操作员可以审核的信息 
// 	function checkGrid_auditDesc_onRefresh(record, fieldId, fieldValue) {
// 		var flag = record.getValue("flag");
// 		var auditDesc = record.getValue("auditDesc");
// 		if(flag == "该我审核"){
// 			return "<span style='width:100%;text-align:center'><a href=\"JavaScript:openCheck()\">"+auditDesc+"</a></span>";
// 		}
//     } 
// 	function openCheck(){
//     	 var auditUrl =  check_dataset.getValue("auditUrl");
//     	 var auditId =  check_dataset.getValue("auditId");
//     	 var type = auditUrl.substring(4,8);
//     	 if(type == 'Term'){
    		 
//     		  window.location.href='../term/paypTerm/'+auditUrl+'?auditId='+ auditId;
//     	 }else{
    		 
//         	 window.location.href='../payPmp/mchtMng/'+auditUrl+'?auditId='+ auditId;
//     	 }
//      }
	
	function checkGrid_auditDesc_onRefresh(record, fieldId, fieldValue) {
		var auditDesc = record.getValue("auditDesc");
		var auditState = record.getValue("auditState");
		var flag = record.getValue("flag");
        if(auditState=="00"){
	    	if(flag=="1"){
	    		return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openCheck()\">"+auditDesc+"</a></span>" ;
	    	}else{
	    		return auditDesc;
	    	}
	   }else{
		    return auditDesc;
	   }    
    } 
	 //审核超链接
    function openCheck(){
   	 var auditUrl =  check_dataset.getValue("auditUrl");
   	 var auditId =  check_dataset.getValue("auditId");
   	 var flag = auditUrl.substring(4,8);
   	/* if(auditUrl=='balAcctErrorsQueryAudit.jsp'){
		 $.open("submitWin", "审核信息", "/pages/report/" + auditUrl + "?auditId=" + auditId,
    			   "1060", "1000", false, true, null, false, "返回");
	 }else if(flag == 'Term'){
   		  window.location.href='../term/paypTerm/'+auditUrl+'?auditId='+ auditId;
   	 }else{
   		 
   		 $.open("submitWin", "审核信息", "/pages/payPmp/mchtMng/" + auditUrl + "?auditId=" + auditId,
     			   "1060", "1000", false, true, null, false, "返回");
   	 } */
   	
   	switch(auditUrl)
	 {
	 case 'balAcctErrorsQueryAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/report/" + auditUrl + "?auditId=" + auditId,
    			   "1060", "1000", false, true, null, false, "返回");
	   break;
	 case 'mchtInfoAudit.jsp':case 'mchtContractAudit.jsp':case 'mchtTotalAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mchtMng/" + auditUrl + "?auditId=" + auditId,
    			   "1060", "1000", false, true, null, false, "返回");
	   break;
	 case 'actvMethodMngAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
  			   "1060", "700", false, true, null, false, "返回");
		 break;
	 case 'actvMngAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
  			   "1060", "700", false, true, null, false, "返回");
		 break;
	 case 'actvMngConfAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
  			   "1060", "700", false, true, null, false, "返回");
		 break;
	 case 'actvMngMchtAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
  			   "1060", "700", false, true, null, false, "返回");
		 break;
	 case 'actvMngSRAudit.jsp':
		 $.open("submitWin", "审核信息", "/pages/payPmp/mktActivity/" + auditUrl + "?auditId=" + auditId,
  			   "1060", "700", false, true, null, false, "返回");
		 break;
	 }
   	
   	
   	
   	
   	
   	
    }
    function submitWin_onButtonClick(){
    	submitWin.close();
    	check_dataset.flushData(1);
     }
	//系统公告链接 
		function systemNotice_noticeTitle_onRefresh(record, fieldId, fieldValue) {
		 			return "&nbsp;<a href=\"JavaScript:onClickNoticeTitle()\">" + fieldValue + "</a>";
	 	}
	 
	//打开公告窗口
	function onClickNoticeTitle(){
		home_dataset.setFieldReadOnly("crtDateTime", true);
		home_dataset.setFieldReadOnly("noticeTitle", true);
		home_dataset.setFieldReadOnly("noticePicId", true);
		var noticePicId=home_dataset.getValue("noticePicId");
		window_noticeTitle.open();
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		//组装图片路径
		$("#img_noticePicId").attr("src",picIp+noticePicId);
	}
	
	</script>
</snow:page>
