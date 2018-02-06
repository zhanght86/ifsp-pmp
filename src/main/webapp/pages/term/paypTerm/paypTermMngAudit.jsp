<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="终端信信息审核页面">
	<!-- 主界面数据集  -->
		<!--初始化paypTermInf.dtst-->
	<snow:dataset id="paypTermInf"  path="com.ruimin.ifs.term.dataset.PaypTermInf" init="true" submitMode="current" ></snow:dataset>
	
	<snow:dataset id="paypTermInfAudit"  path="com.ruimin.ifs.term.dataset.PaypTermInf" init="true" submitMode="current" ></snow:dataset>
	<!--初始化paypMachInf.dtst-->
	<snow:dataset id="paypMachInf" path="com.ruimin.ifs.term.dataset.PaypMachInf" init="false"  submitMode="current" ></snow:dataset>
	
	<!-- 页面元素 -->
		
		<!-- 终端审核部分信息展示 -->
    <snow:window id="windowDetailId1" title="终端审核部分信息" modal="true" closable="true" show="false" width="1100" height="770">
		<!-- 终端基本信息 -->
		<snow:form id="formDetailId1" dataset="paypTermInf" border="true" label="终端部分信息"
			fieldstr="termId,mchtId,mchtName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,parmDownFlag,icFlag,icParmVer,keyVer"
			collapsible="false" colnum="4" width="800"></snow:form>
	    <div class="l-form"  style="width:100%" id="iconDiv2" >
            <!--********************动态加载终端功能********************-->
            
         </div>
         <snow:form id="formAuditView1" dataset="paypTermInfAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false" colnum="3" ></snow:form>
        
         <snow:button id="btnAuditAgree" dataset="paypTermInf" hidden="false" ></snow:button>
		 <snow:button id="btnAuditDisagree" dataset="paypTermInf" hidden="false" ></snow:button>
		 <snow:button id="btnReturn" dataset="paypTermInf" hidden="false" ></snow:button>
     </snow:window>
     
     <snow:window id="windowDetailId2" title="终端审核部分信息" modal="true" closable="true" show="false"  width="1200" height="770">
		<!-- 终端基本信息 -->
		<snow:form id="formDetailId2" dataset="paypTermInf" border="true" label="终端部分信息"
		      fieldstr="termId,mchtId,mchtName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,parmDownFlag,icFlag,icParmVer,keyVer"
			collapsible="false" colnum="4"  width="800"></snow:form>
		<!-- 终端权限信息 -->
		
	   <div class="l-form" style="width:100%" id="iconDiv3" >
            <!--********************动态加载终端功能********************-->
        </div>

		<!-- 终端绑定的设备库存信息  根据情况是否显示 -->
		<snow:form id="formDetailId3" dataset="paypMachInf" border="true" label="设备信息"
			fieldstr="machId,machType,machNo,confNo,machVersion,machStat,batchNo,machInst,propertyType,propertyInst,companyName"
			collapsible="false" colnum="4" width="800"></snow:form>
		
		<snow:form id="formAuditView2" dataset="paypTermInfAudit"  border="true" label="审核意见" width="800" fieldstr="auditView" collapsible="false" colnum="3" ></snow:form>
		
		<snow:button id="btnAuditAgree" dataset="paypTermInf" hidden="false" ></snow:button>
		<snow:button id="btnAuditDisagree" dataset="paypTermInf" hidden="false" ></snow:button>
		<snow:button id="btnReturn" dataset="paypTermInf" hidden="false" ></snow:button>
	</snow:window>
	
     
	  <script type="text/javascript">
	  
	  //刷新数据集时调用
	  function initCallGetter_post() {
		  //获取从审核页面提交过来的审核信息编号 
		  paypTermInf_dataset.setParameter("auditId","<%=request.getParameter("auditId")%>");
		  paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
		  paypTermInf_dataset.setReadOnly(true);
		  paypMachInf_dataset.setReadOnly(true);
		  var machId = paypTermInf_dataset.getValue("machId");// 绑定的设备号
			//根据情况是否显示   有绑定的设备信息要显示设备详情，否则不显示
			if (isNull(machId)) { 
	            paypTermInf_dataset.setValue("flag", "detail1");  //为了方便 修改 对终端功能做组装  临时 做标记
	            window_windowDetailId1.open();//只显示终端基本信息和权限信息
			} else {
				// 根据    绑定设备号    参数 查询绑定的设备信息详情 
				var machId1 = "K"+ machId.substring(1);
				paypMachInf_dataset.setParameter("qmachId", machId1);
				paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
				window_windowDetailId2.open();//显示终端基本信息和权限信息还有绑定的设备库存信息
				   //开始处理终端功能显示 
				paypTermInf_dataset.setValue("flag", "detail2");  //为了方便 修改 对终端功能做组装  临时 做标记
				
			}
	  }
	
	  /**  * 判断是否null  * @param data  */
		function isNull(data) {
			return (data == "" || data == undefined || data == null) ? true : false;
		}
	  
	  //审核拒绝的校验 
	  function btnAuditDisagree_onClickCheck(){
		  var auditView = paypTermInfAudit_dataset.getValue("auditView").length;
		  paypTermInf_dataset.setParameter("auditView",paypTermInfAudit_dataset.getValue("auditView"));
		  if(auditView!=''){
	     		if(auditView>42){
	     		$.warn("审核拒绝的意见最多字数为42");
	     		return false;
	     	}
	     		return true;
	     }else {
	    	 $.warn("拒绝审核，审核意见必填 ");
	    	 return false;
	     }
	  }
	//审核通过的校验 
	  function btnAuditAgree_onClickCheck(){
		  var auditView = paypTermInfAudit_dataset.getValue("auditView").length;
		  paypTermInf_dataset.setParameter("auditView",paypTermInfAudit_dataset.getValue("auditView"));
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
		    var words = null;   //提示语 
		    if(flag=="Passtrue"){ //步骤审核通过，流程申请成功 
		    	words = "步骤审核通过，流程申请成功 ！"; 
		    }else if(flag=="Passfalse") { //步骤审核通过，流程申请未成功 
		        words = "步骤审核通过，流程申请未结束 ！"
		    }
		    $.success(words,function(){
				window.location.href='../../payPmp/platMng/auditNewInfo.jsp';
				});
			
		}
	//审核拒绝提交成功
	  function btnAuditDisagree_postSubmit(){
			$.success("步骤审核拒绝成功，流程申请中止！",function(){
			window.location.href='../../payPmp/platMng/auditNewInfo.jsp';
			});
		}
	//返回按钮点击事件
		function btnReturn_onClick(){
			window.location.href='../../payPmp/platMng/auditNewInfo.jsp';
		}
	</script>
</snow:page>