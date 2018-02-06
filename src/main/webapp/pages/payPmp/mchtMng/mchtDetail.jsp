<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@page
	import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import="com.ruimin.ifs.pmp.mchtMng.comp.MchtMngAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户信息管理">
	<!-- 数据集加载 -->
	<!-- 商户信息数据集  -->
	<snow:dataset id="mchtInfo"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMng" init="true"
		submitMode="current"></snow:dataset>
	<!-- 商户辅表信息数据集  -->
	<snow:dataset id="PbsMchtAssistInfoTmp"
		path="com.ruimin.ifs.pmp.mchtMng.dataset.PbsMchtAssistInfoTmp" init="true"
		submitMode="current"></snow:dataset>
	
	<!--  基本信息模块 -->
	<snow:form id="mchtInfoDetail" label="基本信息" dataset="mchtInfo"
		fieldstr="mchtName,mchtType,mchtStat,mchtOrg,mchtPersonName,mchtPhone,telephone,mchtEmail,mchtContAddr,mchtMngSel,mchtAmrNo,mchtAmrName,riskLevel,mchtAreaNo,creditLimit"></snow:form>
	
	<!-- 商户辅表信息 -->
	<snow:form id="winDetailForm" dataset="PbsMchtAssistInfoTmp"  label="辅表信息"
		fieldstr="chlSysNo,mchtProVerNo,mchtArtifSex,accountType,setlAcctName,mchtArtifJob,mchtArtifAddress,accountIdType,accountIdNo,mchtArtifCountryId,occNo,occSdate,occEdate,trcNo,trcSdate,trcEdate,financialContact,financialTel,firCateCode,secCateCode,thdCateCode,wxAppid,subscribeWxAppid,userDefined,intro,mchtAuditRsltUrl,wxJsapiPath"
		/>
	
	<!-- No2 资质信息模块 -->
	<snow:form id="qlfInfoDetail" label="资质信息" dataset="mchtInfo"
		fieldstr="mchtLicnNo,mchtLicnType,mchtLicnSdate,mchtLicnExpDate,mchtMngScope,mchtRegAmt,currencyType,organizationCode,mchtRegAddr,mchtTrcnNo,mchtArtifName,mchtArtifType,mchtArtifId,mchtArtifPhone,artifSdate,artifEdate,recvDeposit,paidDeposit"
		collapsible="false"/>
	<!-- No3 资质图片模块 -->
	<!-- No3.1 加载标签栏 -->
	<snow:tabs id="qlfPicTabsDetail" showswitch="true" border="true"
		height="550">
		<!-- No3.1.0 标签【商户logo】 -->
		<snow:tab id="tabDetail0" title="商户logo" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail0" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.1 标签【营业执照】 -->
		<snow:tab id="tabDetail1" title="营业执照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail1" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.2 标签【法定代表人证件正面】 -->
		<snow:tab id="tabDetail2" title="法定代表人证件正面" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail2" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.3 标签【组织机构】 -->
		<snow:tab id="tabDetail3" title="组织机构" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail3" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.4 标签【ICP许可】 -->
		<snow:tab id="tabDetail4" title="ICP许可" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail4" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.5 标签【税务许可】 -->
		<snow:tab id="tabDetail5" title="税务许可" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail5" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.6 标签【商户APP门店图】 -->
		<snow:tab id="tabDetail6" title="商户门店" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail6" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.7 标签【法定代表人证件反面】 -->
		<snow:tab id="tabDetail7" title="法定代表人证件反面" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail7" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.8 标签【银行卡正面照】 -->
		<snow:tab id="tabDetail8" title="银行卡正面照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail8" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.9 标签【店铺门头照(含有招牌)】 -->
		<snow:tab id="tabDetail9" title="店铺门头照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail9" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
	</snow:tabs>
	<snow:tabs id="qlfPicTabsDetail2" showswitch="true" border="true"
		height="550">
		<!-- No3.1.10 标签【店铺全景照(店内环境)】 -->
		<snow:tab id="tabDetail10" title="店铺全景照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail10" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.11 标签【收银台照】 -->
		<snow:tab id="tabDetail11" title="收银台照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail11" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.12 标签【商品照】 -->
		<snow:tab id="tabDetail12" title="商品照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail12" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.13 标签【开户许可证】 -->
		<snow:tab id="tabDetail13" title="开户许可证" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail13" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.14 标签【法定代表人手持身份证】 -->
		<snow:tab id="tabDetail14" title="法定代表人手持身份证" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail14" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.18 标签【收款人证件正面】 -->
		<snow:tab id="tabDetail18" title="收款人证件正面" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail18" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.19 标签【收款人证件反面】 -->
		<snow:tab id="tabDetail19" title="收款人证件反面" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail19" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.21 标签【租赁合同(或产权证明书)】 -->
		<snow:tab id="tabDetail21" title="租赁合同" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail21" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.22 标签【合同照片】 -->
		<snow:tab id="tabDetail22" title="合同照片" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail22" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.23 标签【授权证明(委托收款通知书)】 -->
		<snow:tab id="tabDetail23" title="授权证明" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail23" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
	</snow:tabs>
	<snow:tabs id="qlfPicTabsDetail3" showswitch="true" border="true"
		height="550">
		<!-- No3.1.24 标签【收款人手持身份证照】 -->
		<snow:tab id="tabDetail24" title="收款人手持身份证照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail24" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.26 标签【租赁合同承租人同名身份证正面照】 -->
		<snow:tab id="tabDetail26" title="租赁合同承租人同名身份证正面照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail26" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.27 标签【租赁合同承租人同名身份证反面照】 -->
		<snow:tab id="tabDetail27" title="租赁合同承租人同名身份证反面照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail27" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.28 标签【租赁合同承租人同名手持身份证照】 -->
		<snow:tab id="tabDetail28" title="租赁合同承租人同名手持身份证照" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail28" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.29 标签【租赁合同承租人同名银行卡照片】 -->
		<snow:tab id="tabDetail29" title="租赁合同承租人同名银行卡照片" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail29" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
	</snow:tabs>
	<snow:tabs id="qlfPicTabsDetail4" showswitch="true" border="true"
		height="550">
		<!-- No3.1.30 标签【补充资料1】 -->
		<snow:tab id="tabDetail30" title="补充资料1" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail30" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.31 标签【补充资料2】 -->
		<snow:tab id="tabDetail31" title="补充资料2" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail31" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.32 标签【补充资料3】 -->
		<snow:tab id="tabDetail32" title="补充资料3" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail32" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.33 标签【补充资料4】 -->
		<snow:tab id="tabDetail33" title="补充资料4" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail33" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.34 标签【补充资料5】 -->
		<snow:tab id="tabDetail34" title="补充资料5" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail34" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.35 标签【补充资料6】 -->
		<snow:tab id="tabDetail35" title="补充资料6" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail35" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.36 标签【补充资料7】 -->
		<snow:tab id="tabDetail36" title="补充资料7" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail36" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.37 标签【补充资料8】 -->
		<snow:tab id="tabDetail37" title="补充资料8" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail37" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.38 标签【补充资料9】 -->
		<snow:tab id="tabDetail38" title="补充资料9" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail38" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
		<!-- No3.1.39 标签【补充资料10】 -->
		<snow:tab id="tabDetail39" title="补充资料10" closable="false">
			<div
				style="width: 100%; height: 100%; background-color: FFFFFF; float: left; text-align: center">
				<img id="picDetail39" src="" style="height: 550px; width: 820px;" />
			</div>
		</snow:tab>
	</snow:tabs>
	<script>
	function initCallGetter_post() {
		//全部字段设置为-只读
		mchtInfo_dataset.setReadOnly(true);
		PbsMchtAssistInfoTmp_dataset.setReadOnly(true);
		
		//设置必输字段
		mchtInfo_dataset.setFieldRequired("mchtId",true);//商户编号
		mchtInfo_dataset.setFieldRequired("mchtStat",true);//商户状态
//		mchtInfo_dataset.setFieldRequired("mchtArea",true);//所属地区
//		mchtInfo_dataset.setFieldRequired("mchtContAddr",true);//联系地址
		
		//获取图片编号字段
		var picId0 = mchtInfo_dataset.getValue("picId0");
		var picId1 = mchtInfo_dataset.getValue("picId1");
		var picId2 = mchtInfo_dataset.getValue("picId2");
		var picId3 = mchtInfo_dataset.getValue("picId3");
		var picId4 = mchtInfo_dataset.getValue("picId4");
		var picId5 = mchtInfo_dataset.getValue("picId5");
		var picId6 = mchtInfo_dataset.getValue("picId6");
		var picId7 = mchtInfo_dataset.getValue("picId7");
		var picId8 = mchtInfo_dataset.getValue("picId8");
		var picId9 = mchtInfo_dataset.getValue("picId9");
		var picId10 = mchtInfo_dataset.getValue("picId10");
		var picId11 = mchtInfo_dataset.getValue("picId11");
		var picId12 = mchtInfo_dataset.getValue("picId12");
		var picId13 = mchtInfo_dataset.getValue("picId13");
		var picId14 = mchtInfo_dataset.getValue("picId14");
		var picId18 = mchtInfo_dataset.getValue("picId18");
		var picId19 = mchtInfo_dataset.getValue("picId19");
		var picId21 = mchtInfo_dataset.getValue("picId21");
		var picId22 = mchtInfo_dataset.getValue("picId22");
		var picId23 = mchtInfo_dataset.getValue("picId23");
		var picId24 = mchtInfo_dataset.getValue("picId24");
		var picId26 = mchtInfo_dataset.getValue("picId26");
		var picId27 = mchtInfo_dataset.getValue("picId27");
		var picId28 = mchtInfo_dataset.getValue("picId28");
		var picId29 = mchtInfo_dataset.getValue("picId29");
		var picId30 = mchtInfo_dataset.getValue("picId30");
		var picId31 = mchtInfo_dataset.getValue("picId31");
		var picId32 = mchtInfo_dataset.getValue("picId32");
		var picId33 = mchtInfo_dataset.getValue("picId33");
		var picId34 = mchtInfo_dataset.getValue("picId34");
		var picId35 = mchtInfo_dataset.getValue("picId35");
		var picId36 = mchtInfo_dataset.getValue("picId36");
		var picId37 = mchtInfo_dataset.getValue("picId37");
		var picId38 = mchtInfo_dataset.getValue("picId38");
		var picId39 = mchtInfo_dataset.getValue("picId39");
		
		//获取图片访问地址
		var picIp ="<%= SysParamUtil.getParam(ImportPicConstants.SHOW_IMAGE)%>";
		//设置id为picDetail的src地址
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
		$("#picDetail18").attr("src",picIp+picId18);
		$("#picDetail19").attr("src",picIp+picId19);
		$("#picDetail21").attr("src",picIp+picId21);
		$("#picDetail22").attr("src",picIp+picId22);
		$("#picDetail23").attr("src",picIp+picId23);
		$("#picDetail24").attr("src",picIp+picId24);
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
		mchtInfo_dataset.setValue("mchtOrgSel",mchtInfo_dataset.getValue("mchtOrg"));//所属机构
//		mchtInfo_dataset.setValue("mchtAreaSel",mchtInfo_dataset.getValue("mchtArea"));//所属地区
		mchtInfo_dataset.setValue("mchtMngSel",mchtInfo_dataset.getValue("mchtMng"));//上级商户
		
		var mchtType = mchtInfo_dataset.getValue("mchtType");
		if('06'==mchtType){
			mchtInfo_dataset.setFieldRequired("mchtArtifName", false);//法定代表人姓名
			mchtInfo_dataset.setFieldRequired("mchtArtifPhone", false);//法定代表人电话
			mchtInfo_dataset.setFieldRequired("mchtArtifType", false);//法定代表人证件类型
			mchtInfo_dataset.setFieldRequired("mchtArtifId", false);//法定代表人证件号码
			mchtInfo_dataset.setFieldRequired("artifSdate", false);//法定代表人证件生效日期
			mchtInfo_dataset.setFieldRequired("artifEdate", false);//法定代表人证件失效日期

			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifSex", false);//法定代表人性别
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifJob", false);//法定代表人职业
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifAddress", false);//法定代表人住址
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("accountIdType", false);//法定代表人亲属证件类型
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("accountIdNo", false);//法定代表人证件亲属证件号码
			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifCountryId", false);//法定代表人证件国别/地区
		}else{
			mchtInfo_dataset.setFieldRequired("mchtArtifName", true);//法定代表人姓名
			mchtInfo_dataset.setFieldRequired("mchtArtifType", true);//法定代表人证件类型
			mchtInfo_dataset.setFieldRequired("mchtArtifId", true);//法定代表人证件号码
			mchtInfo_dataset.setFieldRequired("artifSdate", true);//法定代表人证件生效日期
			mchtInfo_dataset.setFieldRequired("artifEdate", true);//法定代表人证件失效日期

			PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifSex", true);//法定代表人性别
		}
	}
	
	</script>
</snow:page>