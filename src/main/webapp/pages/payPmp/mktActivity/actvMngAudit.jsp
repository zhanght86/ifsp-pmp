<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动管理审核页面">
    <!--------------- 活动临时信息: 数据集 ----------------->
    <snow:dataset id="activeInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>
    <snow:dataset id="activeInfoAudit" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>
	<!--------------- 活动周期临时信息: 数据集 ----------------->
    <snow:dataset id="cycleInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvCycleConfTmp" submitMode="all" init="false" parameters=""></snow:dataset>
    <!--------------- 红包信息: 数据集 ----------------->
    <snow:dataset id="redBagConf" path="com.ruimin.ifs.pmp.mktActivity.dataset.TblActiveRedbagConfTmp" submitMode="all" init="false" parameters=""></snow:dataset>
    	
		<snow:form id="formAddActive1" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,budgetTp,activeLv,activeBudget,platBudget,acctLimitType,acctCntMax,cardGpNo" collapsible="false" colnum="4"></snow:form>
		<!-- 红包信息 -->
		<snow:grid id="gridDtlRed01"  dataset="redBagConf" fieldstr="redbagAmt[100],redbagCount[100]" 
			height="200" border="true" label="红包配置" editable="false" fitcolumns="true" pagination="true" 
			collapsible="true">
		</snow:grid>
		<snow:form id="RedConf" dataset="redBagConf"  border="true" label="红包总计" fieldstr="sumCount,sumConSume" collapsible="false" colnum="4"></snow:form>
		<snow:form id="formAddActive2" dataset="activeInfo"  border="false" label="支付产品" fieldstr="prodId" collapsible="false" colnum="4"></snow:form>
		<snow:form id="formAddActive3" dataset="activeInfo"  border="false" label="活动时间" fieldstr="SDate,EDate,cycleFlg" collapsible="false" colnum="4"></snow:form>
		<snow:grid id="gridAddCycle"  dataset="cycleInfo" fieldstr="dateTp[200],dateData[100]" 
			height="200" border="true" label="活动周期信息" editable="false" fitcolumns="true" pagination="false" 
			 collapsible="true">
		</snow:grid> 

	<!-- 审核部分 -->	
	<snow:form id="auditPart" dataset="activeInfoAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false"  ></snow:form>
     <br/>
     <!-- 通过与拒绝按钮 -->
	<snow:button id="btnAuditAgree" dataset="activeInfo" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="activeInfo" hidden="false" ></snow:button>


	<script type="text/javascript">
	function initCallGetter_post() {
		//获取从审核页面提交过来的审核信息编号 
		activeInfo_dataset.setParameter("auditId","<%=StringUtil.filtrateSpecialCharater( request.getParameter("auditId"))%>");
		activeInfo_dataset.setReadOnly(true);
		activeInfo_dataset.flushData(activeInfo_dataset.pageIndex); 
		var cycleFlg = activeInfo_dataset.getValue("cycleFlg");
		if('0'==cycleFlg||cycleFlg==''){
			$("#gridAddCycle").css("display", "none");
		}
		var activeType = activeInfo_dataset.getValue("activeType");
		if('21' != activeType){
			$("#gridDtlRed01").css("display", "none");
			$("#RedConf").css("display", "none");
		}
			cycleInfo_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
			cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
			redBagConf_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
			redBagConf_dataset.flushData(redBagConf_dataset.pageIndex);
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
		    //alert(flag);
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