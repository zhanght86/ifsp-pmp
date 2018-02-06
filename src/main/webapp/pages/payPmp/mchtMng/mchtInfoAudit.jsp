<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户信息审核页面">
	<!-- 主界面数据集  -->
	    <!-- 商户信息数据集  -->
		<snow:dataset id="mchtMngInfo" path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMng" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset id="mchtMngInfoAudit" path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMng" init="false" submitMode="current" ></snow:dataset>
		<!-- 商户辅表信息数据集  -->
	<snow:dataset id="PbsMchtAssistInfoTmp"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.PbsMchtAssistInfoTmp" init="false"
		submitMode="current"></snow:dataset>
	<!-- 页面元素 -->
	<snow:window id="winDetail" title="审核信息审核" collapsible="false" show="true" height="900" width="1060" modal="true" closable="true" resizable="true">
		  <p style="font"></p>
		  	<p></p>
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoDetail" label="基本信息" dataset="mchtMngInfo" fieldstr="mchtId,mchtSimpleName,mchtName,mchtType,mchtStat,mchtOrg,mchtPersonName,mchtPhone,telephone,mchtEmail,mchtContAddr,mchtMngSel,mchtAmrNo,mchtAmrName,riskLevel,creditLimit"></snow:form>
			<!-- No2 资质信息模块 -->
			<br/>
			<!-- 商户辅表信息 -->
			<snow:form id="winDetailForm" dataset="PbsMchtAssistInfoTmp"  label="辅表信息"
		fieldstr="chlSysNo,mchtProVerNo,accountType,setlAcctName,mchtArtifSex,mchtArtifJob,mchtArtifAddress,accountIdType,accountIdNo,mchtArtifCountryId,occNo,occSdate,occEdate,trcNo,trcSdate,trcEdate,financialContact,financialTel,firCateCode,secCateCode,thdCateCode,wxAppid,subscribeWxAppid,userDefined,intro,mchtAuditRsltUrl,wxJsapiPath"
		/>
			<br/>
			<snow:form id="qlfInfoDetail" label="资质信息" dataset="mchtMngInfo" fieldstr="mchtLicnNo,mchtLicnType,mchtLicnSdate,mchtLicnExpDate,mchtMngScope,mchtRegAmt,currencyType,organizationCode,mchtRegAddr,mchtTrcnNo,mchtArtifName,mchtArtifType,mchtArtifId,mchtArtifPhone,artifSdate,artifEdate,recvDeposit,paidDeposit,creditLimit" collapsible="false"></snow:form>
			<br/>
			<!-- 资质图片模块 -->
				<!-- 加载标签栏 -->
				<snow:tabs id="qlfPicTabsDetail" showswitch="true" border="true" height="590">
					<!-- 0 标签【商户logo】 -->
					<snow:tab  id="tabDetail0" title="商户logo" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail0" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 1 标签【营业执照】 -->
					<snow:tab  id="tabDetail1" title="营业执照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail1" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 2 标签【法定代表人证件正面】 -->
					<snow:tab id="tabDetail2" title="法定代表人证件正面" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail2" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 3 标签【组织机构】 -->
					<snow:tab id="tabDetail3" title="组织机构" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail3" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 4 标签【ICP许可】 -->
					<snow:tab id="tabDetail4" title="ICP许可" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail4" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 5 标签【税务许可】 -->
					<snow:tab id="tabDetail5" title="税务许可" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail5" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- 6 标签【商户门店】 -->
					<snow:tab id="tabDetail6" title="商户门店" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail6" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 7 标签【法定代表人证件反面】 -->
					<snow:tab id="tabDetail7" title="法定代表人证件反面" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail7" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 8 标签【银行卡正面照】 -->
					<snow:tab id="tabDetail8" title="银行卡正面照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail8" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 9 标签【店铺门头照(含有招牌) -->
					<snow:tab id="tabDetail9" title="店铺门头照(含有招牌)" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail9" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
				</snow:tabs>
				<snow:tabs id="qlfPicTabsDetail2" showswitch="true" border="true" height="590">					
					<!-- 10 标签【10-店铺全景照(店内环境)】 -->
					<snow:tab id="tabDetail10" title="店铺全景照(店内环境)" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail10" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 11 标签【11-收银台照】 -->
					<snow:tab id="tabDetail11" title="收银台照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail11" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 12 标签【12-商品照】 -->
					<snow:tab id="tabDetail12" title="商品照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail12" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 13 标签【13-开户许可证】 -->
					<snow:tab id="tabDetail13" title="开户许可证" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail13" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- No3.1.14 标签【法定代表人手持身份证】 -->
					<snow:tab id="tabDetail14" title="法定代表人手持身份证" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail14" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>							
				</snow:tabs>
				<snow:tabs id="qlfPicTabsDetail3" showswitch="true" border="true" height="590">
					<!-- No3.1.18 标签【收款人证件正面】 -->
					<snow:tab id="tabDetail18" title="收款人证件正面" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail18" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>								
					<!-- No3.1.19 标签【收款人证件反面】 -->
					<snow:tab id="tabDetail19" title="收款人证件反面" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail19" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- 21 标签【21-租赁合同】 -->
					<snow:tab id="tabDetail21" title="租赁合同" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail21" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 22 标签【22-合同照片】 -->
					<snow:tab id="tabDetail22" title="合同照片" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail22" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>					
					<!-- 23 标签【23-授权证明】 -->
					<snow:tab id="tabDetail23" title="授权证明" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail23" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- No3.1.24 标签【收款人手持身份证照】 -->
					<snow:tab id="tabDetail24" title="收款人手持身份证照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail24" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
				</snow:tabs>
				<snow:tabs id="qlfPicTabsDetail4" showswitch="true" border="true" height="590">
					<!-- No3.1.26 标签【租赁合同承租人同名身份证正面照】 -->
					<snow:tab id="tabDetail26" title="租赁合同承租人同名身份证正面照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail26" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.27 标签【租赁合同承租人同名身份证反面照】 -->
					<snow:tab id="tabDetail27" title="租赁合同承租人同名身份证反面照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail27" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.28 标签【租赁合同承租人同名手持身份证照】 -->
					<snow:tab id="tabDetail28" title="租赁合同承租人同名手持身份证照" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail28" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.29 标签【租赁合同承租人同名银行卡照片】 -->
					<snow:tab id="tabDetail29" title="租赁合同承租人同名银行卡照片" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail29" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
				</snow:tabs>		
				<snow:tabs id="qlfPicTabsDetail5" showswitch="true" border="true" height="590">
					<!-- No3.1.30 标签【补充资料1】 -->
					<snow:tab id="tabDetail30" title="补充资料1" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail30" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.31 标签【补充资料2】 -->
					<snow:tab id="tabDetail31" title="补充资料2" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail31" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.32 标签【补充资料3】 -->
					<snow:tab id="tabDetail32" title="补充资料3" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail32" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.33 标签【补充资料4】 -->
					<snow:tab id="tabDetail33" title="补充资料4" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail33" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.34 标签【补充资料5】 -->
					<snow:tab id="tabDetail34" title="补充资料5" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail34" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.35 标签【补充资料6】 -->
					<snow:tab id="tabDetail35" title="补充资料6" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail35" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.36 标签【补充资料7】 -->
					<snow:tab id="tabDetail36" title="补充资料7" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail36" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.37 标签【补充资料8】 -->
					<snow:tab id="tabDetail37" title="补充资料8" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail37" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.38 标签【补充资料9】 -->
					<snow:tab id="tabDetail38" title="补充资料9" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail38" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
					<!-- No3.1.39 标签【补充资料10】 -->
					<snow:tab id="tabDetail39" title="补充资料10" closable="false">
						<div style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center"><img id="picDetail39" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>	
				</snow:tabs>
		    <snow:form id="formAddActive3" dataset="mchtMngInfoAudit"  border="true" label="审核意见" width="800" fieldstr="whetherIn,goToPagy,auditView" collapsible="false"  ></snow:form>
			<br/>
	<snow:button id="btnAuditAgree" dataset="mchtMngInfo" hidden="false" ></snow:button>
	<snow:button id="btnAuditDisagree" dataset="mchtMngInfo" hidden="false" ></snow:button>
	<snow:button id="btnReturn" dataset="mchtMngInfo" hidden="false" ></snow:button>
   </snow:window>
	
	  <script type="text/javascript">
	  var isDesc42 = /^\d{1,42}$/;//最大长度42位 
	  function initCallGetter_post() {
		  //接收页面传过来的数据
          var auditId="<%=StringUtil.filtrateSpecialCharater(request.getParameter("auditId")) %>";
		  mchtMngInfo_dataset.setParameter("auditId",auditId);
		  mchtMngInfo_dataset.flushData(mchtMngInfo_dataset.pageIndex);
		  PbsMchtAssistInfoTmp_dataset.setParameter("qmchtId",mchtMngInfo_dataset.getValue("mchtId"));
		  PbsMchtAssistInfoTmp_dataset.flushData(PbsMchtAssistInfoTmp_dataset.pageIndex); 
		  mchtMngInfo_dataset.setReadOnly(true);
		  PbsMchtAssistInfoTmp_dataset.setReadOnly(true);
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifSex", false);//法定代表人性别
		  // modify by lengjingyu 20180201  内管支持冻结展业进件的商户(审核过)    jira-1989
	      var mchtStat = mchtMngInfo_dataset.getValue("mchtStat");
		  if ('05'==mchtStat||'06' == mchtStat) {
			$("#formAddActive3_0").css("display","none");
			// 02-不进件
			mchtMngInfoAudit_dataset.setValue("whetherIn","02");
		  }
	      // modify end
	  }
	  function mchtMngInfo_dataset_flushDataPost(dataset){
		    var mchtType = mchtMngInfo_dataset.getValue("mchtType");//商户类型
 	  	if((mchtType == "02") || (mchtType == "03") || (mchtType == "05")){
				$("#qlfInfoDetail").css("display","none");			
			}else{
				$("#qlfInfoDetail").css("display","block");			
				$("#tabs_qlfPicTabsDetail").css("display","block");
				$("#tabs_qlfPicTabsDetail2").css("display","block");
				$("#tabs_qlfPicTabsDetail3").css("display","block");
				$("#tabs_qlfPicTabsDetail4").css("display","block");
				$("#tabs_qlfPicTabsDetail5").css("display","block");
			}
		//获取图片编号字段
	      var picId0 = mchtMngInfo_dataset.getValue("picId0");
	      var picId1 = mchtMngInfo_dataset.getValue("picId1");
	      var picId2 = mchtMngInfo_dataset.getValue("picId2");
	      var picId3 = mchtMngInfo_dataset.getValue("picId3");
	      var picId4 = mchtMngInfo_dataset.getValue("picId4");
	      var picId5 = mchtMngInfo_dataset.getValue("picId5");
	      var picId6 = mchtMngInfo_dataset.getValue("picId6");
	      var picId7 = mchtMngInfo_dataset.getValue("picId7");
	      var picId8 = mchtMngInfo_dataset.getValue("picId8");
	      var picId9 = mchtMngInfo_dataset.getValue("picId9");
	      var picId10 = mchtMngInfo_dataset.getValue("picId10");
	      var picId11 = mchtMngInfo_dataset.getValue("picId11");
	      var picId12 = mchtMngInfo_dataset.getValue("picId12");
	      var picId13 = mchtMngInfo_dataset.getValue("picId13");
	      var picId14 = mchtMngInfo_dataset.getValue("picId14");
	      var picId15 = mchtMngInfo_dataset.getValue("picId15");
	      var picId16 = mchtMngInfo_dataset.getValue("picId16");
	      var picId17 = mchtMngInfo_dataset.getValue("picId17");
	      var picId18 = mchtMngInfo_dataset.getValue("picId18");
		  var picId19 = mchtMngInfo_dataset.getValue("picId19");
		  var picId20 = mchtMngInfo_dataset.getValue("picId20");
		  var picId21 = mchtMngInfo_dataset.getValue("picId21");
		  var picId22 = mchtMngInfo_dataset.getValue("picId22");
		  var picId23 = mchtMngInfo_dataset.getValue("picId23");
		  var picId24 = mchtMngInfo_dataset.getValue("picId24");
		  var picId25 = mchtMngInfo_dataset.getValue("picId25");
		  var picId26 = mchtMngInfo_dataset.getValue("picId26");
		  var picId27 = mchtMngInfo_dataset.getValue("picId27");
		  var picId28 = mchtMngInfo_dataset.getValue("picId28");
	  	  var picId29 = mchtMngInfo_dataset.getValue("picId29");
		  var picId30 = mchtMngInfo_dataset.getValue("picId30");
		  var picId31 = mchtMngInfo_dataset.getValue("picId31");
		  var picId32 = mchtMngInfo_dataset.getValue("picId32");
		  var picId33 = mchtMngInfo_dataset.getValue("picId33");
		  var picId34 = mchtMngInfo_dataset.getValue("picId34");
		  var picId35 = mchtMngInfo_dataset.getValue("picId35");
		  var picId36 = mchtMngInfo_dataset.getValue("picId36");
		  var picId37 = mchtMngInfo_dataset.getValue("picId37");
		  var picId38 = mchtMngInfo_dataset.getValue("picId38");
		  var picId39 = mchtMngInfo_dataset.getValue("picId39");
	      //获取图片访问地址
	      var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
			
	      $("#picDetail0").attr("src",picIp+picId0);
	      $("#picDetail1").attr("src",picIp+picId1);
	      $("#picDetail2").attr("src",picIp+picId2);
	      $("#picDetail3").attr("src",picIp+picId3);
	      $("#picDetail4").attr("src",picIp+picId4);
	      $("#picDetail5").attr("src",picIp+picId5);
	      $("#picDetail6").attr("src",picIp+picId6);
	      $("#picDetail7").attr("src",picIp+picId7);
	      $("#picDetail8").attr("src",picIp+picId8);
	      $("#picDetail9").attr("src",picIp+picId9);
	      $("#picDetail10").attr("src",picIp+picId10);
	      $("#picDetail11").attr("src",picIp+picId11);
	      $("#picDetail12").attr("src",picIp+picId12);
	      $("#picDetail13").attr("src",picIp+picId13);
	      $("#picDetail14").attr("src",picIp+picId14);
	      $("#picDetail15").attr("src",picIp+picId15);
	      $("#picDetail16").attr("src",picIp+picId16);
	      $("#picDetail17").attr("src",picIp+picId17);
	      $("#picDetail18").attr("src",picIp+picId18);
		  $("#picDetail19").attr("src",picIp+picId19);
		  $("#picDetail20").attr("src",picIp+picId20);
	      $("#picDetail21").attr("src",picIp+picId21);
		  $("#picDetail22").attr("src",picIp+picId22);
		  $("#picDetail23").attr("src",picIp+picId23);
		  $("#picDetail24").attr("src",picIp+picId24);
		  $("#picDetail25").attr("src",picIp+picId25);
		  $("#picDetail26").attr("src",picIp+picId26);
		  $("#picDetail27").attr("src",picIp+picId27);
		  $("#picDetail28").attr("src",picIp+picId28);
		  $("#picDetail29").attr("src",picIp+picId29);
		  $("#picDetail30").attr("src",picIp+picId30);
		  $("#picDetail31").attr("src",picIp+picId31);
		  $("#picDetail32").attr("src",picIp+picId32);
		  $("#picDetail33").attr("src",picIp+picId33);
		  $("#picDetail34").attr("src",picIp+picId34);
		  $("#picDetail35").attr("src",picIp+picId35);
		  $("#picDetail36").attr("src",picIp+picId36);
		  $("#picDetail37").attr("src",picIp+picId37);
		  $("#picDetail38").attr("src",picIp+picId38);
		  $("#picDetail39").attr("src",picIp+picId39);
	      
	      mchtMngInfo_dataset.setReadOnly(true);
	      mchtMngInfo_dataset.setValue("mchtOrgSel",mchtMngInfo_dataset.getValue("mchtOrg"));//所属机构
//			mchtInfo_dataset.setValue("mchtAreaSel",mchtInfo_dataset.getValue("mchtArea"));//所属地区
			mchtMngInfo_dataset.setValue("mchtMngSel",mchtMngInfo_dataset.getValue("mchtMng"));//上级商户
	  }
	  function btnAuditDisagree_onClickCheck(){
		  mchtMngInfo_dataset.setFieldRequired("mchtSimpleName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtPersonName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtEmail",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnExpDate",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtMngScope",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtRegAmt",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtTrcnNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifId",false);
		  //mchtMngInfo_dataset.setFieldRequired("mchtAreaSel",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtTypeUpd",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtStat",false);
		  mchtMngInfo_dataset.setFieldRequired("isExistAgent",false);
		  mchtMngInfo_dataset.setFieldRequired("riskLevel",false);
		  
		  mchtMngInfo_dataset.setFieldRequired("mchtContAddr",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAreaNo",false);
		  mchtMngInfo_dataset.setFieldRequired("artifSdate",false);
		  mchtMngInfo_dataset.setFieldRequired("artifEdate",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnSdate",false);
		  
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtContAddr",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAreaNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("artifSdate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("artifEdate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnSdate",false);

		 
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtSimpleName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtPersonName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtEmail",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnExpDate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtMngScope",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtRegAmt",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtTrcnNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifId",false);
		 // mchtMngInfoAudit_dataset.setFieldRequired("mchtAreaSel",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtTypeUpd",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtStat",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("isExistAgent",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("riskLevel",false);
		  
		  var auditView = mchtMngInfoAudit_dataset.getValue("auditView")
		  mchtMngInfo_dataset.setParameter("auditView",auditView);
		  
		  var auditView = mchtMngInfoAudit_dataset.getValue("auditView").length;
		  if(auditView!=''){
	     		if(auditView>42){
	     		$.warn("审核拒绝的意见最多字数为42");
	     		return false;
	     	    }
	        
	     }else {
	    	 $.warn("拒绝审核，审核意见必填 ");
	    	 return false;
	     }
		  return true;  
	  }
	  function btnAuditAgree_onClickCheck(){
		  mchtMngInfo_dataset.setFieldRequired("mchtSimpleName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtPersonName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtEmail",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAmrPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnExpDate",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtMngScope",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtRegAmt",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtTrcnNo",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifName",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifPhone",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifType",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtArtifId",false);
		//  mchtMngInfo_dataset.setFieldRequired("mchtAreaSel",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtTypeUpd",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtStat",false);
		  mchtMngInfo_dataset.setFieldRequired("isExistAgent",false);
		  mchtMngInfo_dataset.setFieldRequired("riskLevel",false);
		  
		  mchtMngInfo_dataset.setFieldRequired("mchtContAddr",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtAreaNo",false);
		  mchtMngInfo_dataset.setFieldRequired("artifSdate",false);
		  mchtMngInfo_dataset.setFieldRequired("artifEdate",false);
		  mchtMngInfo_dataset.setFieldRequired("mchtLicnSdate",false);
		  
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtContAddr",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAreaNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("artifSdate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("artifEdate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnSdate",false);

		  
		  
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtSimpleName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtPersonName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtEmail",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtAmrPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtLicnExpDate",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtMngScope",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtRegAmt",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtTrcnNo",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifName",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifPhone",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifType",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtArtifId",false);
		 // mchtMngInfoAudit_dataset.setFieldRequired("mchtAreaSel",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtTypeUpd",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtTypeUpd",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("mchtStat",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("isExistAgent",false);
		  mchtMngInfoAudit_dataset.setFieldRequired("riskLevel",false);
		  
		  //------2018-01-10   是否进件标志----------------------------------------
		  var whetherIn = mchtMngInfoAudit_dataset.getValue("whetherIn")
		  if(whetherIn==''||whetherIn==null){
			  $.warn("进件标志不能为空！！！");
	     		return false;
		  } 
		  mchtMngInfo_dataset.setParameter("whetherIn",whetherIn);
		  //-----------------------------------------------------------------
		  var auditView = mchtMngInfoAudit_dataset.getValue("auditView")
		  mchtMngInfo_dataset.setParameter("auditView",auditView);
		  
		  var auditView = mchtMngInfoAudit_dataset.getValue("auditView").length;
		  
		  //2017-04-01新增
			btnAuditAgree.setDisabled(true);// 点击后三秒内不能再次提交
			var timer = setInterval(function(){//开启定时器
				btnAuditAgree.setDisabled(false);
				clearInterval(timer);    //清除定时器
			},3000);
		  
		  return true;  
	  }
	  
		function mchtMngInfoAudit_dataset_afterChange(dataset,field){
			if(field.fieldName=="whetherIn"){
				var whetherIn = mchtMngInfoAudit_dataset.getValue("whetherIn")
				var goToPagy = mchtMngInfoAudit_dataset.getValue("goToPagy")
				if(whetherIn!=''||whetherIn!=null){
					 if(whetherIn == '02'){
						 mchtMngInfoAudit_dataset.setValue("goToPagy","");
						 mchtMngInfoAudit_dataset.setFieldReadOnly("goToPagy",true);
						 mchtMngInfoAudit_dataset.setFieldRequired("goToPagy",false);
					 }else if(whetherIn == '01'){
						 mchtMngInfoAudit_dataset.setFieldReadOnly("goToPagy",false);
						 mchtMngInfoAudit_dataset.setFieldRequired("goToPagy",true);
					 }
				  }	
			
			}
			}
	  
	  
	  
	  
	  
	  function btnAuditAgree_postSubmit(btn,param){
		    var flag = param.flag;
		    if("true" == flag){
		    	$.success("当前步骤审核通过，流程申请成功！",function(){
		    		parent.submitWin_onButtonClick();
				});
		    }
			if("false" == flag){
				$.success("当前步骤审核通过，流程申请未结束！",function(){
		    		parent.submitWin_onButtonClick();
				});
			}
		}
	  function btnAuditDisagree_postSubmit(){
			$.success("审核拒绝成功,流程申请结束！",function(){
	    		parent.submitWin_onButtonClick();
			});
		}
	//返回按钮点击事件
		function btnReturn_onClick(){
			window.location.href='../../payPmp/platMng/auditNewInfo.jsp';
		}
	</script>
</snow:page>