<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动管理暂停与恢复审核页面">
	<!--------------- 活动临时信息: 数据集 ----------------->
    <snow:dataset id="activeInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>
    <snow:dataset id="activeInfoAudit" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>
	
	<snow:form id="formAddActive1" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,activeStat" collapsible="false" colnum="4"></snow:form>
	
	<!-- 审核部分 -->	
	<snow:form id="auditPart" dataset="activeInfoAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false"  ></snow:form>
    <!-- 通过与拒绝按钮 -->
	<snow:button id="btnAuditAgree" dataset="activeInfo" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="activeInfo" hidden="false" ></snow:button>
	<script type="text/javascript">
	function initCallGetter_post() {
		//获取从审核页面提交过来的审核信息编号 
		activeInfo_dataset.setParameter("auditId","<%=StringUtil.filtrateSpecialCharater( request.getParameter("auditId"))%>");
		activeInfo_dataset.setReadOnly(true);
		activeInfo_dataset.flushData(activeInfo_dataset.pageIndex); 
		}

	//审核拒绝的校验 
	  function btnAuditDisagree_onClickCheck(){
		  var auditView = activeInfoAudit_dataset.getValue("auditView").length;
		  activeInfo_dataset.setParameter("auditView",activeInfoAudit_dataset.getValue("auditView"));
		  if(auditView!=''){
	     		if(auditView>42){
	     		$.warn("审核拒绝的意见最多字数为42");
	     		return false;
	     	}
	     		return true;
	     }else {
	    	 $.warn("拒绝审核时，必须填写审核意见！ ");
	    	 return false;
	     }
	  }
	//审核通过的校验 
	  function btnAuditAgree_onClickCheck(){
		  var auditView = activeInfoAudit_dataset.getValue("auditView").length;
		  activeInfo_dataset.setParameter("auditView",activeInfoAudit_dataset.getValue("auditView"));
		  if(auditView!=''){
	     		if(auditView>42){
	     		$.warn("审核拒绝的意见最多字数为42");
	     		return false;
	     	}
	     		return true;
	     }
		  return true;		  
	  }
	//审核通过提交成功
	  function btnAuditAgree_postSubmit(btn,param){
		    var flag = param.flag; //从后台获取参数数据 
		    if(flag=="true"){ //步骤审核通过，流程申请成功 
		    	$.success("步骤审核通过，流程申请成功 ！",function(){
					parent.submitWin_onButtonClick();
					});
		    }else if(flag=="false") { //步骤审核通过，流程申请未成功 
		    	$.success("步骤审核通过，流程申请未结束 ！",function(){
					parent.submitWin_onButtonClick();
					});
		    }
			
		}
	//审核拒绝提交成功
	  function btnAuditDisagree_postSubmit(){
			$.success("步骤审核拒绝成功，流程申请中止！",function(){
				parent.submitWin_onButtonClick();
			});
		}
	
	
	</script>


</snow:page>