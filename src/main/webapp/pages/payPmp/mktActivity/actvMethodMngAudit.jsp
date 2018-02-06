<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动方法审核页面">
	<!--------------- 活动方法临时信息: 数据集 ----------------->
    <snow:dataset id="methodInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvMethodTmp" submitMode="current" init="true" parameters=""></snow:dataset>
    <!--------------- 活动方法临时审核信息: 数据集 ----------------->
    <snow:dataset id="methodInfoAudit" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvMethodTmp" submitMode="current" init="true" parameters=""></snow:dataset>
    <!--------------- 活动方法分段临时信息: 数据集 ----------------->
    <snow:dataset id="methodSectionInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.actvMethodSectionTmp" submitMode="all" init="false"></snow:dataset>
	
	<!-- 页面元素 -->
	<snow:form id="methodInfoDetail" dataset="methodInfo" label="活动方法详情" fieldstr="methodNo,methodTp,methodNm,param1Tp,param1Data,param2Tp,param2Data" ></snow:form>
    <!-- 分段信息列表 -->
	<snow:grid id="gridDtlExt01"  dataset="methodSectionInfo" fieldstr="sectionSeq[100],sectionLeft[100],sectionRight[100],sectionParam1[100],sectionParam2[100]" 
		height="200" border="true" label="方法分段信息" editable="false" fitcolumns="true" pagination="false" 
		collapsible="true">
	</snow:grid>   
	<!-- 审核部分 -->	
	<snow:form id="auditPart" dataset="methodInfoAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false"  ></snow:form>
     <br/>
     <!-- 通过与拒绝按钮 -->
	<snow:button id="btnAuditAgree" dataset="methodInfo" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="methodInfo" hidden="false" ></snow:button>

	<script type="text/javascript">
	//刷新数据集时调用
	function initCallGetter_post() {
		  //获取从审核页面提交过来的审核信息编号 
		  methodInfo_dataset.setParameter("auditId","<%=StringUtil.filtrateSpecialCharater( request.getParameter("auditId"))%>");
		  methodInfo_dataset.setReadOnly(true);
		  methodInfo_dataset.flushData(methodInfo_dataset.pageIndex); 
		  // 如果不设置dataset init为true，则得不到methodTp值
		  var methodTp = methodInfo_dataset.getValue("methodTp");
		  
	        if(methodTp =='11'){
	        	showSection(true);
	        }else if(methodTp=='21'){
	        	showSection(false);
	        }
		  var methodNo = methodInfo_dataset.getValue("methodNo");
	      var methodTp = methodInfo_dataset.getValue("methodTp");
	      methodSectionInfo_dataset.setParameter("methodNo",methodNo);
	      methodSectionInfo_dataset.setParameter("methodTp",methodTp);
		  methodSectionInfo_dataset.flushData(methodSectionInfo_dataset.pageIndex); 
	}
	
	
	
	 function methodInfo_dataset_flushDataPost(dataset){
		 var fieldValue = methodInfo_dataset.getValue("param1Data");
     		if(fieldValue.length!=0){
	        	var str = (fieldValue/100).toFixed(2) + '';
	        	 //console.log("str："+str);
	        	methodInfo_dataset.setValue("param1Data",str);
	        	//console.log("param1Data:"+methodInfo_dataset.getValue("param1Data"));
     	}
 	}
	
	
	
	 function showSection(showFlag){
    	 if(showFlag == true){
    		 $("#gridDtlExt01").css("display", "block");
     	 	
    	 }else if (showFlag==false){
    		$("#gridDtlExt01").css("display", "none");
    	 }
     }
	
	//审核拒绝的校验 
	  function btnAuditDisagree_onClickCheck(){
		  var auditView = methodInfoAudit_dataset.getValue("auditView").length;
		  methodInfo_dataset.setParameter("auditView",methodInfoAudit_dataset.getValue("auditView"));
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
		  var auditView = methodInfoAudit_dataset.getValue("auditView").length;
		  methodInfo_dataset.setParameter("auditView",methodInfoAudit_dataset.getValue("auditView"));
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