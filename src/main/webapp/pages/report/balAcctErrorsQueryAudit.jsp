<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户合同审核页面">
	<!-- 主界面数据集  -->
	<snow:dataset id="balAcctErrors"
		path="com.ruimin.ifs.pmp.report.dataset.balAcctErrorsQuery"
		submitMode="current" init="true"></snow:dataset>
		<snow:dataset id="balAcctErrorsAudit"
		path="com.ruimin.ifs.pmp.report.dataset.balAcctErrorsQuery"
		submitMode="current" init="true"></snow:dataset>
	<!-- 页面元素 -->
		<snow:window id="winDetail" title="审核信息审核" collapsible="false" show="true" height="900" width="1060" modal="true" closable="true">
	
		  <p style="font"></p>
		<snow:form id="frmReturns" dataset="balAcctErrors" border="false"  fieldstr="stlmDate,txnAmt,chlMerId,chlMerName,pagyName,thirdSsn,chlTxnSsn,txnDate,txnType,errStat,corrStat" collapsible="false" colnum="4"></snow:form>
		
		    <snow:form id="formAddActive3" dataset="balAcctErrorsAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false" colnum="3" ></snow:form>
	<snow:button id="btnAuditAgree" dataset="balAcctErrors" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="balAcctErrors" hidden="false" ></snow:button>
	<snow:button id="btnReturn" dataset="balAcctErrors" hidden="false" ></snow:button>
   </snow:window>
	  <script type="text/javascript">
	  //刷新数据集时调用
	  function initCallGetter_post() {
		  //获取从审核页面提交过来的审核信息编号 
		 balAcctErrors_dataset.setParameter("auditId","<%=StringUtil.filtrateSpecialCharater( request.getParameter("auditId"))%>");
		 balAcctErrors_dataset.flushData(balAcctErrors_dataset.pageIndex);
		 balAcctErrors_dataset.setReadOnly(true);
	  }

	  //审核拒绝的校验 
	  function btnAuditDisagree_onClickCheck(){
		  var auditView = balAcctErrorsAudit_dataset.getValue("auditView").length;
		  balAcctErrors_dataset.setParameter("auditView",balAcctErrorsAudit_dataset.getValue("auditView"));
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
		  var auditView = balAcctErrorsAudit_dataset.getValue("auditView").length;
		  balAcctErrors_dataset.setParameter("auditView",balAcctErrorsAudit_dataset.getValue("auditView"));
		  if(auditView!=''){
	     		if(auditView>42){
	     		$.warn("审核拒绝的意见最多字数为42");
	     		return false;
	     	}
	     		return true;
	     }
		// 点击后三秒内不能再次提交
			btnAuditAgree.setDisabled(true);
			var timer = setInterval(function(){//开启定时器
				btnAuditAgree.setDisabled(false);
				clearInterval(timer);    //清除定时器
			},30000); 
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
		    }else if(flag=="yes"){ //步骤审核通过，新增流程申请成功  
		    	$.success("该步审核通过！新增审核成功！请到【通道商户登记】补录信息！",function(){
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
	//返回按钮点击事件
		function btnReturn_onClick(){
			window.location.href='../payPmp/platMng/auditNewInfo.jsp';
		}
	</script>
</snow:page>