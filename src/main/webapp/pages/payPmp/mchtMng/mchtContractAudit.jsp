<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户合同审核页面">
	<!-- 主界面数据集  -->
		<snow:dataset  id="mchtContractInfo"  path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtContract" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset  id="mchtContractInfoAudit"  path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtContract" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset  id="ConToProDetail"  path="com.ruimin.ifs.pmp.mchtMng.dataset.ConToPro" init="false" submitMode="current" ></snow:dataset>
	<!-- 页面元素 -->
		  <p style="font"></p>
			<snow:form id="formAddActive1" dataset="mchtContractInfo"  border="true" label="合同信息"  fieldstr="mchtId,mchtSimpleName,paperConId,startDate,extendFlag,conTerm" collapsible="false" colnum="3"></snow:form>
			<snow:form id="formAddActive2" dataset="mchtContractInfo"  border="true" label="结算日期"  fieldstr="setlType,setlSymbol,setlCycle,specSetlDay,accountType,accountBoss,setlAcctName,setlAcctNo,setlCertType,setlCertNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlBankPhone,setlAcctAreaCode" collapsible="false" colnum="3"></snow:form>
		    <%-- <snow:form id="formAddActive4" dataset="mchtContractInfo"  border="false" label="交易限额" width="200" fieldstr="limitOne,limitDay" collapsible="true" colnum="3"></snow:form> --%>
		    	<p>服务信息</p>
			<snow:tabs id="ConServiceDetail" showswitch="true" border="true" height="350">	</snow:tabs>
		    <snow:form id="formAddActive3" dataset="mchtContractInfoAudit"  border="true" label="审核意见" width="800" fieldstr="whetherIn,goToPagy,auditView" collapsible="false" colnum="3" ></snow:form>
	<snow:button id="btnAuditAgree" dataset="mchtContractInfo" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="mchtContractInfo" hidden="false" ></snow:button>
	<snow:button id="btnReturn" dataset="mchtContractInfo" hidden="false" ></snow:button>
	<br/><br/><br/>
	  <script type="text/javascript">
	  //刷新数据集时调用
	  function initCallGetter_post() {
		  mchtContractInfo_dataset.setFieldRequired("setlAcctAreaCode",false);
		  mchtContractInfoAudit_dataset.setFieldRequired("setlAcctAreaCode",false);
		  //获取从审核页面提交过来的审核信息编号 
		  mchtContractInfo_dataset.setParameter("auditId","<%=StringUtil.filtrateSpecialCharater( request.getParameter("auditId"))%>");
		  mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
		  ConToProDetail_dataset.setParameter("conId", mchtContractInfo_dataset.getValue("conId"));
		  ConToProDetail_dataset.flushData();
		  mchtContractInfo_dataset.setReadOnly(true);
	  }
	  function ConToProDetail_dataset_flushDataPost(dataset) {
			//加载完数据后动态加载tab页
			var len= ConToProDetail_dataset.length;
			while(len>0){
				var prodName = ConToProDetail_dataset.getValue("prodName");
				var p2 = encodeURI(encodeURI(prodName));
				tabs_ConServiceDetail.add(len,ConToProDetail_dataset.getValue("prodName"),"/pages/payPmp/mchtMng/conService.jsp?conId="+mchtContractInfo_dataset.getValue("conId")+"&prodId="+ConToProDetail_dataset.getValue("prodId")+"&prodName="+p2+"&level=three");
				tabs_ConServiceDetail.setClosable(len, false);
				ConToProDetail_dataset.deleteRecord();
				len--;
			}
			
		}
	  function tabs_ConServiceDetail_beforeTabChange(tabid) {
			tabs_ConServiceDetail.refresh(tabid,"");
		} 
	  
	
		function mchtContractInfoAudit_dataset_afterChange(dataset,field){
			if(field.fieldName=="whetherIn"){
				var whetherIn = mchtContractInfoAudit_dataset.getValue("whetherIn")
				var goToPagy = mchtContractInfoAudit_dataset.getValue("goToPagy")
				if(whetherIn!=''||whetherIn!=null){
					 if(whetherIn == '02'){
						 mchtContractInfoAudit_dataset.setValue("goToPagy","");
						 mchtContractInfoAudit_dataset.setFieldReadOnly("goToPagy",true);
						 mchtContractInfoAudit_dataset.setFieldRequired("goToPagy",false);
					 }else if(whetherIn == '01'){
						 mchtContractInfoAudit_dataset.setFieldReadOnly("goToPagy",false);
						 mchtContractInfoAudit_dataset.setFieldRequired("goToPagy",true);
					 }
				  }	
			
			}
			}
	  
	  
	  
	  
	  //审核拒绝的校验 
	  function btnAuditDisagree_onClickCheck(){
		  var auditView = mchtContractInfoAudit_dataset.getValue("auditView").length;
		  mchtContractInfo_dataset.setParameter("auditView",mchtContractInfoAudit_dataset.getValue("auditView"));
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
		  //------2018-01-10   是否进件标志----------------------------------------
		  var whetherIn = mchtContractInfoAudit_dataset.getValue("whetherIn")
		  if(whetherIn==''||whetherIn==null){
			  $.warn("进件标志不能为空！！！");
	     		return false;
		  }
		  mchtContractInfo_dataset.setParameter("whetherIn",whetherIn);
		  //-----------------------------------------------------------------
		  var auditView = mchtContractInfoAudit_dataset.getValue("auditView").length;
		  mchtContractInfo_dataset.setParameter("auditView",mchtContractInfoAudit_dataset.getValue("auditView"));
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
			window.location.href='../../payPmp/platMng/auditNewInfo.jsp';
		}
	</script>
</snow:page>