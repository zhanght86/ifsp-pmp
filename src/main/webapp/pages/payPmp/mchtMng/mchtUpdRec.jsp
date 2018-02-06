<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import="com.ruimin.ifs.pmp.mchtMng.comp.MchtMngAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户信息管理">
	<!-- 数据集加载 -->
		<!-- 商户信息数据集  -->
		<snow:dataset id="mchtInfo" path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMng" init="true" submitMode="current" ></snow:dataset>
		<!-- 商户辅表数据集  -->
		<snow:dataset id="PbsMchtAssistInfoTmp" path="com.ruimin.ifs.pmp.mchtMng.dataset.PbsMchtAssistInfoTmp" init="true" submitMode="current" ></snow:dataset>
	<!-- 主表单 -->
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoUpd" label="基本信息" dataset="mchtInfo" fieldstr="mchtId,mchtSimpleName,mchtName,mchtTypeUpd,mchtStat,mchtOrgSel,mchtPersonName,mchtPhone,telephone,mchtEmail,mchtContAddr,mchtMngSel,mchtAmrNo,mchtAmrName,riskLevel,mchtAreaNo,creditLimit"></snow:form>
			<!-- No2 辅表信息模块 -->
			<snow:form id="mchtAssistInfoUpd" label="辅表信息" dataset="PbsMchtAssistInfoTmp" fieldstr="chlSysNo,mchtProVerNo,accountType,setlAcctName,mchtArtifSex,mchtArtifJob,mchtArtifAddress,accountIdType,accountIdNo,mchtArtifCountryId,occNo,occSdate,occEdate,trcNo,trcSdate,trcEdate,financialContact,financialTel,firCateCode,secCateCode,thdCateCode,wxAppid,subscribeWxAppid,userDefined,intro,mchtAuditRsltUrl,wxJsapiPath"></snow:form>
			<!-- No3 资质信息模块 -->
			<snow:form id="qlfInfoUpd" label="资质信息" dataset="mchtInfo" fieldstr="mchtLicnNo,mchtLicnType,mchtLicnSdate,mchtLicnExpDate,mchtMngScope,mchtRegAmt,currencyType,organizationCode,mchtRegAddr,mchtTrcnNo,mchtArtifName,mchtArtifType,mchtArtifId,mchtArtifPhone,artifSdate,artifEdate,recvDeposit,paidDeposit" collapsible="false"></snow:form>
			<!-- No3.1 加载标签栏 -->
				<snow:tabs id="qlfPicTabsUpd" border="true" height="750">
					<!-- No3.1.0 标签【商户logo】 -->
					<snow:tab  id="tabUpd0" title="商户logo" closable="false">
						<div><iframe id="uploadUpd0" name="uploadUpd0" src="../../payPmp/pubTool/uploadImage.jsp?picNum=0"></iframe></div>
						<div id="picDivUpd0" style=" background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd0" src="" style="height: 550px; width: 820px;" /></div>						
					</snow:tab>
					<!-- No3.1.1 标签【营业执照】 -->
					<snow:tab  id="tabUpd1" title="营业执照" closable="false">
						<div><iframe id="uploadUpd1" name="uploadUpd1" src="../../payPmp/pubTool/uploadImage.jsp?picNum=1"></iframe></div>
						<div id="picDivUpd1" style=" background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd1" src="" style="height: 550px; width: 820px;" /></div>						
					</snow:tab>
					<!-- No3.1.2 标签【法定代表人证件正面】 -->
					<snow:tab id="tabUpd2" title="法定代表人证件正面" closable="false">
						<div><iframe id="uploadUpd2" name="uploadUpd2" src="../../payPmp/pubTool/uploadImage.jsp?picNum=2" width="800" height="220"></iframe></div>
						<div id="picDivUpd2" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd2" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.3 标签【组织机构】 -->
					<snow:tab id="tabUpd3" title="组织机构" closable="false">
						<div><iframe id="uploadUpd3" name="uploadUpd3" src="../../payPmp/pubTool/uploadImage.jsp?picNum=3" width="800" height="220"></iframe></div>
						<div id="picDivUpd3" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd3" src="" style="height: 550px; width: 820px;" /></div>		
					</snow:tab>
					<!-- No3.1.4 标签【ICP许可】 -->
					<snow:tab id="tabUpd4" title="ICP许可" closable="false">
						<div><iframe id="uploadUpd4" name="uploadUpd4" src="../../payPmp/pubTool/uploadImage.jsp?picNum=4" width="800" height="220"></iframe></div>
						<div id="picDivUpd4" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd4" src="" style="height: 550px; width: 820px;" /></div>						
					</snow:tab>
					<!-- No3.1.5 标签【税务许可】 -->
					<snow:tab id="tabUpd5" title="税务许可" closable="false">					
						<div><iframe id="uploadUpd5" name="uploadUpd5" src="../../payPmp/pubTool/uploadImage.jsp?picNum=5" width="800" height="220"></iframe></div>		
						<div id="picDivUpd5" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd5" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.6 标签【商户门店】 -->
					<snow:tab id="tabUpd6" title="商户门店" closable="false">					
						<div><iframe id="uploadUpd6" name="uploadUpd6" src="../../payPmp/pubTool/uploadImage.jsp?picNum=6" width="800" height="220"></iframe></div>		
						<div id="picDivUpd6" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd6" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.7 标签【法定代表人证件反面】 -->
					<snow:tab id="tabUpd7" title="法定代表人证件反面" closable="false">					
						<div><iframe id="uploadUpd7" name="uploadUpd7" src="../../payPmp/pubTool/uploadImage.jsp?picNum=7" width="800" height="220"></iframe></div>		
						<div id="picDivUpd7" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd7" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.8 标签【银行卡正面照】 -->
					<snow:tab id="tabUpd8" title="银行卡正面照" closable="false">					
						<div><iframe id="uploadUpd8" name="uploadUpd8" src="../../payPmp/pubTool/uploadImage.jsp?picNum=8" width="800" height="220"></iframe></div>		
						<div id="picDivUpd8" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd8" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.9 标签【店铺门头照(含有招牌)】 -->
					<snow:tab id="tabUpd9" title="店铺门头照" closable="false">					
						<div><iframe id="uploadUpd9" name="uploadUpd9" src="../../payPmp/pubTool/uploadImage.jsp?picNum=9" width="800" height="220"></iframe></div>		
						<div id="picDivUpd9" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd9" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>
				<snow:tabs id="qlfPicTabsUpd2" border="true" height="750">
					<!-- No3.1.10 标签【10-店铺全景照(店内环境)】 -->
					<snow:tab id="tabUpd10" title="店铺全景照" closable="false">					
						<div><iframe id="uploadUpd10" name="uploadUpd10" src="../../payPmp/pubTool/uploadImage.jsp?picNum=10" width="800" height="220"></iframe></div>		
						<div id="picDivUpd10" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd10" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.11 标签【11-收银台照】 -->
					<snow:tab id="tabUpd11" title="收银台照" closable="false">					
						<div><iframe id="uploadUpd11" name="uploadUpd11" src="../../payPmp/pubTool/uploadImage.jsp?picNum=11" width="800" height="220"></iframe></div>		
						<div id="picDivUpd11" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd11" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.12 标签【12-商品照】 -->
					<snow:tab id="tabUpd12" title="商品照" closable="false">					
						<div><iframe id="uploadUpd12" name="uploadUpd12" src="../../payPmp/pubTool/uploadImage.jsp?picNum=12" width="800" height="220"></iframe></div>		
						<div id="picDivUpd12" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd12" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.13 标签【13-开户许可证】 -->
					<snow:tab id="tabUpd13" title="开户许可证" closable="false">					
						<div><iframe id="uploadUpd13" name="uploadUpd13" src="../../payPmp/pubTool/uploadImage.jsp?picNum=13" width="800" height="220"></iframe></div>		
						<div id="picDivUpd13" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd13" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.14 标签【法定代表人手持身份证】 -->
					<snow:tab id="tabUpd14" title="法定代表人手持身份证" closable="false">
						<div><iframe id="uploadUpd14" name="uploadUpd14" src="../../payPmp/pubTool/uploadImage.jsp?picNum=14" width="800" height="220"></iframe></div>		
						<div id="picDivUpd14" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd14" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
						<!-- No3.1.18 标签【收款人证件正面】 -->
					<snow:tab id="tabUpd18" title="收款人证件正面" closable="false">
						<div><iframe id="uploadUpd18" name="uploadUpd18" src="../../payPmp/pubTool/uploadImage.jsp?picNum=18" width="800" height="220"></iframe></div>		
						<div id="picDivUpd18" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd18" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.19 标签【收款人证件反面】 -->
					<snow:tab id="tabUpd19" title="收款人证件反面" closable="false">
						<div><iframe id="uploadUpd19" name="uploadUpd19" src="../../payPmp/pubTool/uploadImage.jsp?picNum=19" width="800" height="220"></iframe></div>		
						<div id="picDivUpd19" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd19" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					
					<!-- No3.1.21 标签【21-租赁合同】 -->
					<snow:tab id="tabUpd21" title="租赁合同" closable="false">					
						<div><iframe id="uploadUpd21" name="uploadUpd21" src="../../payPmp/pubTool/uploadImage.jsp?picNum=21" width="800" height="220"></iframe></div>		
						<div id="picDivUpd21" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd21" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.22 标签【22-合同照片】 -->
					<snow:tab id="tabUpd22" title="合同照片" closable="false">					
						<div><iframe id="uploadUpd22" name="uploadUpd22" src="../../payPmp/pubTool/uploadImage.jsp?picNum=22" width="800" height="220"></iframe></div>		
						<div id="picDivUpd22" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd22" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.23 标签【23-授权证明】 -->
					<snow:tab id="tabUpd23" title="授权证明" closable="false">					
						<div><iframe id="uploadUpd23" name="uploadUpd23" src="../../payPmp/pubTool/uploadImage.jsp?picNum=23" width="800" height="220"></iframe></div>		
						<div id="picDivUpd23" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd23" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>
				<snow:tabs id="qlfPicTabsUpd3" border="true" height="750">
					<!-- No3.1.24 标签【收款人手持身份证照】—>其他2 -->
					<snow:tab id="tabUpd24" title="收款人手持身份证照" closable="false">
						<div><iframe id="uploadUpd24" name="uploadUpd24" src="../../payPmp/pubTool/uploadImage.jsp?picNum=24" width="800" height="220"></iframe></div>		
						<div id="picDivUpd24" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd24" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.26 标签【租赁合同承租人同名身份证正面照】—>其他2 -->
					<snow:tab id="tabUpd26" title="租赁合同承租人同名身份证正面照" closable="false">
						<div><iframe id="uploadUpd26" name="uploadUpd26" src="../../payPmp/pubTool/uploadImage.jsp?picNum=26" width="800" height="220"></iframe></div>		
						<div id="picDivUpd26" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd26" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.27 标签【租赁合同承租人同名身份证反面照】—>其他2 -->
					<snow:tab id="tabUpd27" title="租赁合同承租人同名身份证反面照" closable="false">
						<div><iframe id="uploadUpd27" name="uploadUpd27" src="../../payPmp/pubTool/uploadImage.jsp?picNum=27" width="800" height="220"></iframe></div>		
						<div id="picDivUpd27" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd27" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.28 标签【租赁合同承租人同名手持身份证照】—>其他2 -->
					<snow:tab id="tabUpd28" title="租赁合同承租人同名手持身份证照" closable="false">
						<div><iframe id="uploadUpd28" name="uploadUpd28" src="../../payPmp/pubTool/uploadImage.jsp?picNum=28" width="800" height="220"></iframe></div>		
						<div id="picDivUpd28" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd28" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.29 标签【租赁合同承租人同名银行卡照片】—>其他2 -->
					<snow:tab id="tabUpd29" title="租赁合同承租人同名银行卡照片" closable="false">
						<div><iframe id="uploadUpd29" name="uploadUpd29" src="../../payPmp/pubTool/uploadImage.jsp?picNum=29" width="800" height="220"></iframe></div>		
						<div id="picDivUpd29" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd29" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>	
				<snow:tabs id="qlfPicTabsUpd4" border="true" height="750">
					<!-- No3.1.30 标签【补充资料1】 -->
					<snow:tab id="tabUpd30" title="补充资料1" closable="false">
						<div><iframe id="uploadUpd30" name="uploadUpd30" src="../../payPmp/pubTool/uploadImage.jsp?picNum=30" width="800" height="220"></iframe></div>		
						<div id="picDivUpd30" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd30" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.31 标签【补充资料2】 -->
					<snow:tab id="tabUpd31" title="补充资料2" closable="false">
						<div><iframe id="uploadUpd31" name="uploadUpd31" src="../../payPmp/pubTool/uploadImage.jsp?picNum=31" width="800" height="220"></iframe></div>		
						<div id="picDivUpd31" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd31" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.32 标签【补充资料3】 -->
					<snow:tab id="tabUpd32" title="补充资料3" closable="false">
						<div><iframe id="uploadUpd32" name="uploadUpd32" src="../../payPmp/pubTool/uploadImage.jsp?picNum=32" width="800" height="220"></iframe></div>		
						<div id="picDivUpd32" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd32" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.33 标签【补充资料4】 -->
					<snow:tab id="tabUpd33" title="补充资料4" closable="false">
						<div><iframe id="uploadUpd33" name="uploadUpd33" src="../../payPmp/pubTool/uploadImage.jsp?picNum=33" width="800" height="220"></iframe></div>		
						<div id="picDivUpd33" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd33" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.34 标签【补充资料5】 -->
					<snow:tab id="tabUpd34" title="补充资料5" closable="false">
						<div><iframe id="uploadUpd34" name="uploadUpd34" src="../../payPmp/pubTool/uploadImage.jsp?picNum=34" width="800" height="220"></iframe></div>		
						<div id="picDivUpd34" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd34" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.35 标签【补充资料6】 -->
					<snow:tab id="tabUpd35" title="补充资料6" closable="false">
						<div><iframe id="uploadUpd35" name="uploadUpd35" src="../../payPmp/pubTool/uploadImage.jsp?picNum=35" width="800" height="220"></iframe></div>		
						<div id="picDivUpd35" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd35" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.36 标签【补充资料7】 -->
					<snow:tab id="tabUpd36" title="补充资料7" closable="false">
						<div><iframe id="uploadUpd36" name="uploadUpd36" src="../../payPmp/pubTool/uploadImage.jsp?picNum=36" width="800" height="220"></iframe></div>		
						<div id="picDivUpd36" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd36" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.37 标签【补充资料8】 -->
					<snow:tab id="tabUpd37" title="补充资料8" closable="false">
						<div><iframe id="uploadUpd37" name="uploadUpd37" src="../../payPmp/pubTool/uploadImage.jsp?picNum=37" width="800" height="220"></iframe></div>		
						<div id="picDivUpd37" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd37" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.38 标签【补充资料9】 -->
					<snow:tab id="tabUpd38" title="补充资料9" closable="false">
						<div><iframe id="uploadUpd38" name="uploadUpd38" src="../../payPmp/pubTool/uploadImage.jsp?picNum=38" width="800" height="220"></iframe></div>		
						<div id="picDivUpd38" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd38" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.39 标签【补充资料10】 -->
					<snow:tab id="tabUpd39" title="补充资料10" closable="false">
						<div><iframe id="uploadUpd39" name="uploadUpd39" src="../../payPmp/pubTool/uploadImage.jsp?picNum=39" width="800" height="220"></iframe></div>		
						<div id="picDivUpd39" style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picUpd39" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>
			<!-- No4 加载提交按钮 -->
			<snow:button id="btnUpdSub" dataset="mchtInfo" hidden="true"></snow:button>
	<script type="text/javascript">

 
 	/*****************正则表达式*****************/
 	/**验证邮箱*/
 	//var isEmail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
 	var isEmail= /^[a-zA-Z0-9]([a-zA-Z0-9\_\-\.]*)@([a-zA-Z0-9\_\-]*)\.([a-zA-Z]{2,3})$/;
 	/**验证身份证*/
	var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
 	/**验证邮政编码*/
	var isZig = /^\d{6}$/;
	/**验证座机电话 */
	var isPhone = /^(\d{3,4}-)?\d{6,8}(-\d{1,4})?$/;
	/**验证手机电话 */
	var isCellPhone = /1[3|4|5|7|8][0-9]{9}$/;
	/**验证金额*/
	var isAmt12 = /^([1-9][\d]{0,9}|[1-9][\d]{0,2}(\,[\d]{3})*|0)(\.[\d]{1,2})?$/;//最大长度12位，最多包含两位小数，支持带','格式，需和isAmtLength12匹配使用
	var isAmtLength12 = /^([\d]{0,10})(\.[\d]{1,2})?$/;//去除','，最大长度12位，最多包含两位小数
	var isAmt20 = /^([1-9][\d]{0,17}|[1-9][\d]{0,2}(\,[\d]{3})*|0)(\.[\d]{1,2})?$/;//最大长度20位，最多包含两位小数，支持带','格式，需和isAmtLength20匹配使用
	var isAmtLength20 = /^([\d]{0,18})(\.[\d]{1,2})?$/;//去除','，最大长度20位，最多包含两位小数
	/**验证字符串长度*/
	var isDesc11 = /^\S{1,11}$/;//最大长度11位 
	var isDesc42 = /^\S{1,42}$/;//最大长度42位 
	var isDesc21 = /^\S{1,21}$/;//最大长度21位 
	var isDesc32 = /^\S{1,32}$/;//最大长度32位 
	var isDesc64 = /^\S{1,64}$/;//最大长度64位 
	var isDesc8 = /^\S{1,8}$/;//最大长度8位
	var isDesc10 = /^\S{1,10}$/;//最大长度10位 
	var isDesc6 = /^\S{1,6}$/;//最大长度6位
	
	
	/*****************刷新数据集*****************/
	function initCallGetter_post() {
		/*****************设置特殊字段[只读]*****************/
//		mchtInfo_dataset.setFieldReadOnly("mchtStat", true);//商户状态
		mchtInfo_dataset.setFieldReadOnly("mchtOrgSel",true);//所属机构
		mchtInfo_dataset.setFieldReadOnly("mchtOrgSel",true);
		/*****************设置特殊字段[必输]*****************/
		mchtInfo_dataset.setFieldRequired("mchtId", true);//商户编号
		mchtInfo_dataset.setFieldRequired("mchtStat", true);//商户状态
		mchtInfo_dataset.setFieldRequired("mchtOrgSel", true);

		var mchtTypeUpd = mchtInfo_dataset.getValue("mchtTypeUpd");//商户类型	
 			$("#qlfInfoUpd").css("display","block");
		$("#tabs_qlfPicTabsUpd").css("display","block");
		$("#tabs_qlfPicTabsUpd2").css("display","block");
		$("#tabs_qlfPicTabsUpd3").css("display","block");
		$("#tabs_qlfPicTabsUpd4").css("display","block");
		
		mchtInfo_dataset.setValue("mchtOrgSel",mchtInfo_dataset.getValue("mchtOrg"));//所属机构
//		mchtInfo_dataset.setValue("mchtAreaSel",mchtInfo_dataset.getValue("mchtArea"));//所属地区
		mchtInfo_dataset.setValue("mchtMngSel",mchtInfo_dataset.getValue("mchtMng"));//上级商户
		
		//mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex); 
		//PbsMchtAssistInfoTmp_dataset.flushData(PbsMchtAssistInfoTmp_dataset.pageIndex);
		var wxJsapiPath=PbsMchtAssistInfoTmp_dataset.getValue("wxJsapiPath");
		if(wxJsapiPath.length==0){
			PbsMchtAssistInfoTmp_dataset.setValue("wxJsapiPath","https://www.ect888.com/payweb/pay/");			
		}
		
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
		//设置id为picUpd的src地址
		$("#picUpd0").attr("src",picIp+picId0);
		$("#picUpd1").attr("src",picIp+picId1);
		$("#picUpd2").attr("src",picIp+picId2);
		$("#picUpd3").attr("src",picIp+picId3);
		$("#picUpd4").attr("src",picIp+picId4);
		$("#picUpd5").attr("src",picIp+picId5);
		$("#picUpd6").attr("src",picIp+picId6);
		$("#picUpd7").attr("src",picIp+picId7);
		$("#picUpd8").attr("src",picIp+picId8);
		$("#picUpd9").attr("src",picIp+picId9);
		$("#picUpd10").attr("src",picIp+picId10);
		$("#picUpd11").attr("src",picIp+picId11);
		$("#picUpd12").attr("src",picIp+picId12);
		$("#picUpd13").attr("src",picIp+picId13);
		$("#picUpd14").attr("src",picIp+picId14);
		$("#picUpd18").attr("src",picIp+picId18);
		$("#picUpd19").attr("src",picIp+picId19);
		$("#picUpd21").attr("src",picIp+picId21);
		$("#picUpd22").attr("src",picIp+picId22);
		$("#picUpd23").attr("src",picIp+picId23);
		$("#picUpd24").attr("src",picIp+picId24);
		$("#picUpd26").attr("src",picIp+picId26);
		$("#picUpd27").attr("src",picIp+picId27);
		$("#picUpd28").attr("src",picIp+picId28);
		$("#picUpd29").attr("src",picIp+picId29);
		$("#picUpd30").attr("src",picIp+picId30);
		$("#picUpd31").attr("src",picIp+picId31);
		$("#picUpd32").attr("src",picIp+picId32);
		$("#picUpd33").attr("src",picIp+picId33);
		$("#picUpd34").attr("src",picIp+picId34);
		$("#picUpd35").attr("src",picIp+picId35);
		$("#picUpd36").attr("src",picIp+picId36);
		$("#picUpd37").attr("src",picIp+picId37);
		$("#picUpd38").attr("src",picIp+picId38);
		$("#picUpd39").attr("src",picIp+picId39);
		
		
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
	
	
	
	
		
	/*****************公共方法*****************/
	/***************图片控制***************/				
		function picJudge(lgPic,result,msg){
			if (lgPic != "" && result == "false"){		
				$.warn(msg);
				return false;
			}else return true;
		}
	

		function importFileCallBack(result,picSrc,picNum) {
			if (result) {				
				switch (picNum) {
				case "0":
					$("#picUpd0").attr("src",picSrc);
					break;
				case "1":
					$("#picUpd1").attr("src",picSrc);
					break;
					
				case "2":
					$("#picUpd2").attr("src",picSrc);
					break;
					
				case "3":
					$("#picUpd3").attr("src",picSrc);
					break;
					
				case "4":
					$("#picUpd4").attr("src",picSrc);
					break;
				
				case "5":
					$("#picUpd5").attr("src",picSrc);
					break;
				case "6":
					$("#picUpd6").attr("src",picSrc);
					break;
				case "7":
					$("#picUpd7").attr("src",picSrc);
					break;
				case "8":
					$("#picUpd8").attr("src",picSrc);
					break;
				case "9":
					$("#picUpd9").attr("src",picSrc);
					break;
				case "10":
					$("#picUpd10").attr("src",picSrc);
					break;
				case "11":
					$("#picUpd11").attr("src",picSrc);
					break;
				case "12":
					$("#picUpd12").attr("src",picSrc);
					break;
				case "13":
					$("#picUpd13").attr("src",picSrc);
					break;
				case "14":
					$("#picUpd14").attr("src",picSrc);
					break;
				case "18":
					$("#picUpd18").attr("src",picSrc);
					break;
				case "19":
					$("#picUpd19").attr("src",picSrc);
					break;
				case "21":
					$("#picUpd21").attr("src",picSrc);
					break;
				case "22":
					$("#picUpd22").attr("src",picSrc);
					break;
				case "23":
					$("#picUpd23").attr("src",picSrc);
					break;
				case "24":
					$("#picUpd24").attr("src",picSrc);
					break;
				case "26":
					$("#picUpd26").attr("src",picSrc);
					break;
				case "27":
					$("#picUpd27").attr("src",picSrc);
					break;
				case "28":
					$("#picUpd28").attr("src",picSrc);
					break;
				case "29":
					$("#picUpd29").attr("src",picSrc);
					break;
				case "30":
					$("#picUpd30").attr("src",picSrc);
					break;
				case "31":
					$("#picUpd31").attr("src",picSrc);
					break;
				case "32":
					$("#picUpd32").attr("src",picSrc);
					break;
				case "33":
					$("#picUpd33").attr("src",picSrc);
					break;
				case "34":
					$("#picUpd34").attr("src",picSrc);
					break;
				case "35":
					$("#picUpd35").attr("src",picSrc);
					break;
				case "36":
					$("#picUpd36").attr("src",picSrc);
					break;
				case "37":
					$("#picUpd37").attr("src",picSrc);
					break;
				case "38":
					$("#picUpd38").attr("src",picSrc);
					break;
				case "39":
					$("#picUpd39").attr("src",picSrc);
				default:
					break;
				}			
			}
		} 
	/**资质信息初始化，用于每次下拉改变了选项之后*/
	function initQlf(){
		mchtInfo_dataset.setValue("mchtLicnNo","");
		mchtInfo_dataset.setValue("mchtLicnType","");
		mchtInfo_dataset.setValue("mchtLicnExpDate","");
		//mchtInfo_dataset.setValue("mchtMngScope","");
		mchtInfo_dataset.setValue("mchtRegAmt","0.00");
		mchtInfo_dataset.setValue("mchtTrcnNo","");
		//mchtInfo_dataset.setValue("mchtArtifName","");
		mchtInfo_dataset.setValue("mchtArtifPhone","");
		mchtInfo_dataset.setValue("mchtArtifType","");
		mchtInfo_dataset.setValue("mchtArtifId","");
		/* mchtInfo_dataset.setValue("recvDeposit","0.00");
		mchtInfo_dataset.setValue("paidDeposit","0.00"); */	
	}
	
	
	/****微信下拉列表****/
	function PbsMchtAssistInfoTmp_dataset_firCateCode_beforeOpen(dropdown, dpds) {
		dpds.setParameter("quserCode", "01");
		dpds.setParameter("qcategoryLevel", "1");
		firCateCode_DropDown.cache = false;
		return true;
	}

	function PbsMchtAssistInfoTmp_dataset_firCateCode_onSelect(dropdown, record) {
		if ((record != null) && (record != "")) {
			var oldVal = PbsMchtAssistInfoTmp_dataset.getValue("firCateCode");
			var newVal = record.getValue("categoryCode");
			if (oldVal != newVal) {
				PbsMchtAssistInfoTmp_dataset.setValue("secCateCode", "");
				PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", "");
				//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccSubWxName", "");
			}
			//PbsMchtAssistInfoTmp_dataset.setValue("firCateCode", newVal);
			//console.log(PbsMchtAssistInfoTmp_dataset.getValue("firCateCode"));
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccName", record
				//	.getValue("categoryDesc"));
		} else {
			PbsMchtAssistInfoTmp_dataset.setValue("secCateCode", "");
			PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", "");
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccSubWxName", "");
		}
		return true;
	}

	function PbsMchtAssistInfoTmp_dataset_secCateCode_beforeOpen(dropdown, dpds) {
		if ((dpds != null) && (dpds != "")) {
			var firCateCode = PbsMchtAssistInfoTmp_dataset.getValue("firCateCode");
			if ((firCateCode == null) || (firCateCode == "")) {
				$.warn("请先选择一级行业编号.");
				return false;
			}
			dpds.setParameter("quserCode", "01");
			dpds.setParameter("qcategoryLevel", "2");
			dpds.setParameter("qpCategoryCode", firCateCode);
			secCateCode_DropDown.cache = false;
		}
		return true;
	}

	function PbsMchtAssistInfoTmp_dataset_thdCateCode_beforeOpen(dropdown, dpds) {
		if ((dpds != null) && (dpds != "")) {
			var secCateCode = PbsMchtAssistInfoTmp_dataset
					.getValue("secCateCode");
			if ((secCateCode == null) || (secCateCode == "")) {
				$.warn("请先选择二级行业编号.");
				return false;
			}
			dpds.setParameter("quserCode", "01");
			dpds.setParameter("qcategoryLevel", "3");
			dpds.setParameter("qpCategoryCode", secCateCode);
			thdCateCode_DropDown.cache = false;
		}
		return true;
	}

	function PbsMchtAssistInfoTmp_dataset_secCateCode_onSelect(dropdown, record) {
		if ((record != null) && (record != "")) {
			var oldVal = PbsMchtAssistInfoTmp_dataset.getValue("secCateCode");
			var newVal = record.getValue("categoryCode");
			if (oldVal != newVal) {
				PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", "");
				//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccThdWxName", "");
			}
			//PbsMchtAssistInfoTmp_dataset.setValue("secCateCode", newVal);
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccSubName", record
				//	.getValue("categoryDesc"));
		} else {
			PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", "");
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccSubWxName", "");
		}
		return true;
	}

	/* function mchtInfo_dataset_thdCateCode_onSelect(dropdown, record) {
		if ((record != null) && (record != "")) {
			//PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", record
				//	.getValue("categoryCode"));
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccThdName", record
				//	.getValue("categoryDesc"));
		} else {
			PbsMchtAssistInfoTmp_dataset.setValue("thdCateCode", "");
			//PbsMchtAssistInfoTmp_dataset.setValue("mchtMccThdWxName", "");
		}
		return true;
	} */

	
	
	
	
	
	
	
	
	
	/**机构管理下拉事件*/
	function mchtInfo_dataset_mchtOrgSel_onSelect(value,record){
		if(record == null){
			mchtInfo_dataset.setValue("mchtOrgId","");//所属机构编号
			mchtInfo_dataset.setValue("mchtOrgSel","");//所属机构名称
		}else{
			mchtInfo_dataset.setValue("mchtOrgId",record.getValue("brcode"));//所属机构编号
			mchtInfo_dataset.setValue("mchtOrgSel",record.getValue("brname"));//所属机构名称
		}		
	}
	
	/**上级商户下拉事件*/
	function mchtInfo_dataset_mchtMngSel_onSelect(value,record){
		if(record == null){
			mchtInfo_dataset.setValue("mchtMngNo","");//上级商户编号
			mchtInfo_dataset.setValue("mchtMngSel","");//上级商户名称
		}else{
			if(mchtInfo_dataset.getValue("mchtId") == record.getValue("mchtId")){
				$.warn("上级商户不能选择自己！");
				mchtInfo_dataset.setValue("mchtMngNo","");//上级商户编号
				mchtInfo_dataset.setValue("mchtMngSel","");//上级商户名称
			}else{
				mchtInfo_dataset.setValue("mchtMngNo",record.getValue("mchtId"));//上级商户编号
				mchtInfo_dataset.setValue("mchtMngSel",record.getValue("mchtSimpleName"));//上级商户名称		
			}				
		}		
	}
	
	/**校验字段(与接口进件统一) */
	function validInfo(){
		//---------------------------------接口上送部分---------------------------------------------------------------------------------------------
		var mchtName = mchtInfo_dataset.getValue("mchtName");//商户全名
		var mchtSimpleName = mchtInfo_dataset.getValue("mchtSimpleName");//商户简称
		//var mchtType   DDIC:1804                                                                                                 商户类型  
		var mchtPersonName = mchtInfo_dataset.getValue("mchtPersonName");//联系人
		var mchtArtifType=mchtInfo_dataset.getValue("mchtArtifType");
		var mchtArtifId = mchtInfo_dataset.getValue("mchtArtifId");//法定代表人证件号码
		var mchtPhone = mchtInfo_dataset.getValue("mchtPhone");//联系电话
		var mchtMngNo = mchtInfo_dataset.getValue("mchtMngNo");//上级商户号(mchtMng.dtst size:32位限制)
		var mchtContAddr = mchtInfo_dataset.getValue("mchtContAddr");//联系地址(mchtMng.dtst size:128位限制)
		var mchtEmail = mchtInfo_dataset.getValue("mchtEmail");//联系邮箱
		var telephone = mchtInfo_dataset.getValue("telephone");//座机电话
		var mchtAreaNo = mchtInfo_dataset.getValue("mchtAreaNo");//所属城市编号(接口字段名:addressCode  mchtMngAdd.dtst size:8位限制)
		var mchtArtifName = mchtInfo_dataset.getValue("mchtArtifName");//法定代表人姓名(mchtMng.dtst size:32位限制)
		var artifSdate = mchtInfo_dataset.getValue("artifSdate");//法定代表人证件生效日期(mchtMng.dtst datebox控件控制长度8位)
		var artifEdate = mchtInfo_dataset.getValue("artifEdate");//法定代表人证件失效日期(mchtMng.dtst datebox控件控制长度8位)
		//var mchtLicnType DDIC:1819                                                                                                      营业执照类别
		var mchtLicnNo = mchtInfo_dataset.getValue("mchtLicnNo");//营业执照号码
		var mchtLicnSdate = mchtInfo_dataset.getValue("mchtLicnSdate");//营业执照生效日期(mchtMng.dtst datebox控件控制长度8位)
		var mchtLicnExpDate = mchtInfo_dataset.getValue("mchtLicnExpDate");//营业执照失效日期(接口字段名:mchtLicnEdate mchtMng.dtst datebox控件控制长度8位)
		var creditLimit = mchtInfo_dataset.getValue("creditLimit");//信用卡限制标志(mchtMng.dtst datasource="LIST:00,可用信用卡;01,禁用信用卡")
		//--------------------------------------------------------------------------------------------------------------------------------------------
		var mchtMngSel = mchtInfo_dataset.getValue("mchtMngSel");//上级商户
		var mchtZipNo = mchtInfo_dataset.getValue("mchtZipNo");//邮政编码
		var mchtOrgSel = mchtInfo_dataset.getValue("mchtOrgSel");//所属机构
		var mchtAmrNo = mchtInfo_dataset.getValue("mchtAmrNo");//业务员号
		var mchtAmrName = mchtInfo_dataset.getValue("mchtAmrName");//业务员姓名
		
		var mchtMngScope = mchtInfo_dataset.getValue("mchtMngScope");//经营范围
		var mchtRegAmt = mchtInfo_dataset.getValue("mchtRegAmt");//注册资金
		var mchtTrcnNo = mchtInfo_dataset.getValue("mchtTrcnNo");//税务登记证号码
		var mchtArtifPhone = mchtInfo_dataset.getValue("mchtArtifPhone");//法定代表人电话
		var recvDeposit = mchtInfo_dataset.getValue("recvDeposit");//应收保证金
		var paidDeposit = mchtInfo_dataset.getValue("paidDeposit");//实收保证金	
		
		//校验辅表日期
		var occSdate = PbsMchtAssistInfoTmp_dataset.getValue("occSdate");//组织机构代码证生效日期
		var occEdate = PbsMchtAssistInfoTmp_dataset.getValue("occEdate");//组织机构代码证失效日期
		var trcSdate = PbsMchtAssistInfoTmp_dataset.getValue("trcSdate");//税务登记证生效日期
		var trcEdate = PbsMchtAssistInfoTmp_dataset.getValue("trcEdate");//税务登记证失效日期 
		
		if(telephone!=null&&telephone!=""){
			if(!(isPhone.test(telephone))){
				$.warn("固定电话格式错误");
				return false;
			}
		}
		if(mchtEmail!=null&&mchtEmail!=""){
			if(!(isEmail.test(mchtEmail))){
				$.warn("邮箱格式错误");
				return false;
			}
		}	
		/* if(mchtArtifPhone==null||mchtArtifPhone==""){
			
		}else{
			if(!isDesc11.test(mchtArtifPhone)){
				$.error("【法定代表人电话】长度超过限制！");
				return false;
			}
			
		} */
		//校验过程
		if((mchtSimpleName == "") || (mchtSimpleName == null)){
			$.error("【商户简称】必须输入！");
			return false;
		}
		/* if(!isDesc64.test(mchtSimpleName)){
			$.warn("【商户简称】长度错误（最大长度64位）");
			return false;
		} */
		if((mchtName == "") || (mchtName == null)){
			$.error("【商户全名】必须输入！");
			return false;
		}
		/* if(!isDesc64.test(mchtName)){
			$.warn("【商户全名】长度错误（最大长度64位）");
			return false;
		} */
		if((mchtPersonName == "") || (mchtPersonName == null)){
			$.error("【联系人】必须输入！");
			return false;
		}
		/* if(!isDesc32.test(mchtPersonName)){
			$.warn("【联系人】长度错误（最大长度32位）");
			return false;
		} */
		if((mchtPhone == "") || (mchtPhone == null)){

		}else{
		if(!(isCellPhone.test(mchtPhone))){
			$.warn("【手机号】格式错误,必须是11位手机号");
			return false;
		}			
		}
			if((mchtLicnNo != "") && (mchtLicnNo != null)){
				if(mchtLicnNo.length>30){
					$.warn("【营业执照号码】长度错误（最大长度30位）");
					return false;
				}
			}
			/* if((mchtMngScope != "") && (mchtMngScope != null)){
				if(!isDesc42.test(mchtMngScope)){
					$.warn("【经营范围】长度错误（最大长度42位）");
					return false;
				}
			} */
			if((mchtRegAmt != "") && (mchtRegAmt != null)){
				if(isAmt20.test(mchtRegAmt)){					
					if(!isAmtLength20.test(mchtRegAmt.replace(/,/g,''))){
						$.warn("【注册资金】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
						return false;
					}
				}else{
					$.warn("【注册资金】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
					return false;	
				}
			}
			if((mchtTrcnNo != "") && (mchtTrcnNo != null)){
				if(mchtTrcnNo.length>30){
					$.warn("【税务登记证号码】长度错误（最大长度30位）");
					return false;
				}
			}

			if((mchtArtifId != "") && (mchtArtifId != null)){
				/* if(mchtArtifId.length>32){
					$.warn("【法定代表人证件号码】长度错误（最大长度32位）");
					return false;
				} */
				if("00"==mchtArtifType){
					if(!regIdNo.test(mchtArtifId)){ 
						$.warn("【身份证号】填写有误"); 
						return false; 
					}
				}
			}
			 if((recvDeposit != "") && (recvDeposit != null)){
				if(isAmt12.test(recvDeposit)){					
					if(!isAmtLength12.test(recvDeposit.replace(/,/g,''))){
						$.warn("【应收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
						return false;
					}
				}else{
					$.warn("【应收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
					return false;	
				}				
			}
			if((paidDeposit != "") && (paidDeposit != null)){
				if(isAmt12.test(paidDeposit)){					
					if(!isAmtLength12.test(paidDeposit.replace(/,/g,''))){
						$.warn("【实收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
						return false;
					}
				}else{
					$.warn("【实收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
					return false;	
				}
			} 
			if((artifSdate !="")&&(artifSdate != null)&&(artifEdate !="")&&(artifEdate != null)){
				if(artifSdate>artifEdate){
					$.warn("法定代表人证件生效日期不能在失效日期之后!!!");
					return false;
				}
			}
			if((mchtLicnSdate !="")&&(mchtLicnSdate != null)&&(mchtLicnExpDate !="")&&(mchtLicnExpDate != null)){
				if(mchtLicnSdate>mchtLicnExpDate){
					$.warn("营业执照生效日期不能在失效日期之后!!!");
					return false;
				}
			}
			
			if((occSdate !="")&&(occSdate != null)&&(occEdate !="")&&(occEdate != null)){
				if(occSdate>occEdate){
					$.warn("组织机构代码证生效日期不能在失效日期之后!!!");
					return false;
				}
			}
			if((trcSdate !="")&&(trcSdate != null)&&(trcEdate !="")&&(trcEdate != null)){
				if(trcSdate>trcEdate){
					$.warn("税务登记证生效日期不能在失效日期之后!!!");
					return false;
				}
			}
			
		return true;
	}


	/**********窗口***********/
	/**根据商户类型下拉选项，改变form显示*/
/* 	 function mchtInfo_dataset_mchtTypeRecord_onSelect(value,record){
		//当商户类型为"02-政府机构"、"03-事业单位"、"05-虚拟商户"、取消选择，不显示资质信息和资质图片
 		if((value == "02") || (value == "03") || (value == "05") || (value == "")){
 			showQlfOrNotRecord(false);
 		}else {
 			showQlfOrNotRecord(true);
 		}
 		mchtInfo_dataset.setValue("mchtType",value);
		initQlf();//初始化资质信息
	} 
	 */
	
	
	/**********窗口***********/
	/**根据商户类型下拉选项，改变form显示*/
	 function mchtInfo_dataset_mchtTypeUpd_onSelect(value,record){
		
 		$("#qlfInfoUpd").css("display","block");
		mchtInfo_dataset.setValue("mchtType",value);
		initQlf();//初始化资质信息
		window.frames["uploadUpd0"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=0");
		window.frames["uploadUpd1"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=1");
		window.frames["uploadUpd2"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=2");
		window.frames["uploadUpd3"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=3");
		window.frames["uploadUpd4"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=4");
		window.frames["uploadUpd5"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=5");
		window.frames["uploadUpd6"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=6");
		window.frames["uploadUpd7"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=7");
		window.frames["uploadUpd8"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=8");
		window.frames["uploadUpd9"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=9");
		window.frames["uploadUpd10"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=10");
		window.frames["uploadUpd11"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=11");
		window.frames["uploadUpd12"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=12");
		window.frames["uploadUpd13"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=13");
		window.frames["uploadUpd14"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=14");
		window.frames["uploadUpd18"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=18");
		window.frames["uploadUpd19"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=19");
		window.frames["uploadUpd21"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=21");
		window.frames["uploadUpd22"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=22");
		window.frames["uploadUpd23"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=23");
		window.frames["uploadUpd24"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=24");
		window.frames["uploadUpd26"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=26");
		window.frames["uploadUpd27"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=27");
		window.frames["uploadUpd28"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=28");
		window.frames["uploadUpd29"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=29");
		window.frames["uploadUpd30"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=30");
		window.frames["uploadUpd31"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=31");
		window.frames["uploadUpd32"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=32");
		window.frames["uploadUpd33"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=33");
		window.frames["uploadUpd34"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=34");
		window.frames["uploadUpd35"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=35");
		window.frames["uploadUpd36"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=36");
		window.frames["uploadUpd37"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=37");
		window.frames["uploadUpd38"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=38");
		window.frames["uploadUpd39"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=39");
	} 
	
	
	/**提交前检验数据格式*/
	 function btnUpdSub_onClickCheck(){

		//校验【基本信息】和【资质信息】
			if(!validInfo()){
				return false;
			}else{
				validInfo();
			}
		
			var mchtTypeUpd = mchtInfo_dataset.getValue("mchtTypeUpd");//商户种类
			var mchtArtifName = mchtInfo_dataset.getValue("mchtArtifName");//法定代表人姓名
			var accountType = PbsMchtAssistInfoTmp_dataset.getValue("accountType");//结算账户类型
			var setlAcctName = PbsMchtAssistInfoTmp_dataset.getValue("setlAcctName");//结算账户户名
			if((mchtTypeUpd == "") || (mchtTypeUpd == null)){
				$.warn("请输入:商户种类");
				return false;
			}
			if((mchtArtifName == "") || (mchtArtifName == null)){
				$.warn("请输入:法定代表人姓名");
				return false;
			}
			if((accountType == "") || (accountType == null)){
				$.warn("请输入:结算账户类型");
				return false;
			}
			if((setlAcctName == "") || (setlAcctName == null)){
				$.warn("请输入:结算账户户名");
				return false;
			}

			
		//获取【图片信息】
		var picId0 = window.frames["uploadUpd0"].document.getElementById("lgPic").value;//图片名【商户logo】
		var picId1 = window.frames["uploadUpd1"].document.getElementById("lgPic").value;//图片名【营业执照】
		var picId2 = window.frames["uploadUpd2"].document.getElementById("lgPic").value;//图片名【法定代表人证件】
		var picId3 = window.frames["uploadUpd3"].document.getElementById("lgPic").value;//图片名【组织机构】
		var picId4 = window.frames["uploadUpd4"].document.getElementById("lgPic").value;//图片名【ICP许可】
		var picId5 = window.frames["uploadUpd5"].document.getElementById("lgPic").value;//图片名【税务许可】
		var picId6 = window.frames["uploadUpd6"].document.getElementById("lgPic").value;//图片名【商户app门店照】
		var picId7 = window.frames["uploadUpd7"].document.getElementById("lgPic").value;//图片名【】
		var picId8 = window.frames["uploadUpd8"].document.getElementById("lgPic").value;//图片名【】
		var picId9 = window.frames["uploadUpd9"].document.getElementById("lgPic").value;//图片名【】
		var picId10 = window.frames["uploadUpd10"].document.getElementById("lgPic").value;//图片名【】
		var picId11 = window.frames["uploadUpd11"].document.getElementById("lgPic").value;//图片名【】
		var picId12 = window.frames["uploadUpd12"].document.getElementById("lgPic").value;//图片名【】
		var picId13 = window.frames["uploadUpd13"].document.getElementById("lgPic").value;//图片名【】
		var picId14 = window.frames["uploadUpd14"].document.getElementById("lgPic").value;//图片名【】
		var picId18 = window.frames["uploadUpd18"].document.getElementById("lgPic").value;//图片名【收款人证件正面】
		var picId19 = window.frames["uploadUpd19"].document.getElementById("lgPic").value;//图片名【收款人证件反面】
		var picId21 = window.frames["uploadUpd21"].document.getElementById("lgPic").value;//图片名【租赁合同(或产权证明书)】
		var picId22 = window.frames["uploadUpd22"].document.getElementById("lgPic").value;//图片名【合同照片】
		var picId23 = window.frames["uploadUpd23"].document.getElementById("lgPic").value;//图片名【授权证明(委托收款通知书)】
		var picId24 = window.frames["uploadUpd24"].document.getElementById("lgPic").value;//图片名【收款人手持身份证照】
		var picId26 = window.frames["uploadUpd26"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名身份证正面照】
		var picId27 = window.frames["uploadUpd27"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名身份证反面照】
		var picId28 = window.frames["uploadUpd28"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名手持身份证照】
		var picId29 = window.frames["uploadUpd29"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名银行卡照片】
		var picId30 = window.frames["uploadUpd30"].document.getElementById("lgPic").value;//图片名【补充资料1】
		var picId31 = window.frames["uploadUpd31"].document.getElementById("lgPic").value;//图片名【补充资料2】
		var picId32 = window.frames["uploadUpd32"].document.getElementById("lgPic").value;//图片名【补充资料3】
		var picId33 = window.frames["uploadUpd33"].document.getElementById("lgPic").value;//图片名【补充资料4】
		var picId34 = window.frames["uploadUpd34"].document.getElementById("lgPic").value;//图片名【补充资料5】
		var picId35 = window.frames["uploadUpd35"].document.getElementById("lgPic").value;//图片名【补充资料6】
		var picId36 = window.frames["uploadUpd36"].document.getElementById("lgPic").value;//图片名【补充资料7】
		var picId37 = window.frames["uploadUpd37"].document.getElementById("lgPic").value;//图片名【补充资料8】
		var picId38 = window.frames["uploadUpd38"].document.getElementById("lgPic").value;//图片名【补充资料9】
		var picId39 = window.frames["uploadUpd39"].document.getElementById("lgPic").value;//图片名【补充资料10】
		//未修改的图片名		
		var picIdOrg0 = mchtInfo_dataset.getValue("picId0");//原图片名【商户logo】
		var picIdOrg1 = mchtInfo_dataset.getValue("picId1");//原图片名【营业执照】
		var picIdOrg2 = mchtInfo_dataset.getValue("picId2");//原图片名【法定代表人证件】
		var picIdOrg3 = mchtInfo_dataset.getValue("picId3");//原图片名【组织机构】
		var picIdOrg4 = mchtInfo_dataset.getValue("picId4");//原图片名【ICP许可】
		var picIdOrg5 = mchtInfo_dataset.getValue("picId5");//原图片名【税务许可】
		var picIdOrg6 = mchtInfo_dataset.getValue("picId6");//原图片名【商户app门店照】
		var picIdOrg7 = mchtInfo_dataset.getValue("picId7");
		var picIdOrg8 = mchtInfo_dataset.getValue("picId8");
		var picIdOrg9 = mchtInfo_dataset.getValue("picId9");
		var picIdOrg10 = mchtInfo_dataset.getValue("picId10");
		var picIdOrg11 = mchtInfo_dataset.getValue("picId11");
		var picIdOrg12 = mchtInfo_dataset.getValue("picId12");
		var picIdOrg13 = mchtInfo_dataset.getValue("picId13");
		var picIdOrg14 = mchtInfo_dataset.getValue("picId14");
		var picIdOrg18 = mchtInfo_dataset.getValue("picId18");
		var picIdOrg19 = mchtInfo_dataset.getValue("picId19");
		var picIdOrg21 = mchtInfo_dataset.getValue("picId21");
		var picIdOrg22 = mchtInfo_dataset.getValue("picId22");
		var picIdOrg23 = mchtInfo_dataset.getValue("picId23");
		var picIdOrg24 = mchtInfo_dataset.getValue("picId24");
		var picIdOrg26 = mchtInfo_dataset.getValue("picId26");
		var picIdOrg27 = mchtInfo_dataset.getValue("picId27");
		var picIdOrg28 = mchtInfo_dataset.getValue("picId28");
		var picIdOrg29 = mchtInfo_dataset.getValue("picId29");
		var picIdOrg30 = mchtInfo_dataset.getValue("picId30");
		var picIdOrg31 = mchtInfo_dataset.getValue("picId31");
		var picIdOrg32 = mchtInfo_dataset.getValue("picId32");
		var picIdOrg33 = mchtInfo_dataset.getValue("picId33");
		var picIdOrg34 = mchtInfo_dataset.getValue("picId34");
		var picIdOrg35 = mchtInfo_dataset.getValue("picId35");
		var picIdOrg36 = mchtInfo_dataset.getValue("picId36");
		var picIdOrg37 = mchtInfo_dataset.getValue("picId37");
		var picIdOrg38 = mchtInfo_dataset.getValue("picId38");
		var picIdOrg39 = mchtInfo_dataset.getValue("picId39");

		//组装图片名信息
		if(picId0.length!=0){
			mchtInfo_dataset.setValue("picId0","00" + picId0);
		}else{
			if(picIdOrg0.length==0){
				mchtInfo_dataset.setValue("picId0","00" + picIdOrg0);
			}else{
				if('00'==picIdOrg0.substring(0,2)){
					mchtInfo_dataset.setValue("picId0", picIdOrg0);					
				}else{
					mchtInfo_dataset.setValue("picId0","00" + picIdOrg0);
				}
			}
		}
		if(picId1.length!=0){
			mchtInfo_dataset.setValue("picId1","01" + picId1);
		}else{
			if(picIdOrg1.length==0){
				mchtInfo_dataset.setValue("picId1","01" + picIdOrg1);
			}else{
				if('01'==picIdOrg1.substring(0,2)){
					mchtInfo_dataset.setValue("picId1", picIdOrg1);					
				}else{
					mchtInfo_dataset.setValue("picId1","01" + picIdOrg1);
				}
			}
		}
		if(picId2.length!=0){
			mchtInfo_dataset.setValue("picId2","02" + picId2);
		}else{
			if(picIdOrg2.length==0){
				mchtInfo_dataset.setValue("picId2","02" + picIdOrg2);
			}else{
				if('02'==picIdOrg2.substring(0,2)){
					mchtInfo_dataset.setValue("picId2", picIdOrg2);					
				}else{
					mchtInfo_dataset.setValue("picId2","02" + picIdOrg2);
				}
			}
		}
		if(picId3.length!=0){
			mchtInfo_dataset.setValue("picId3","03" + picId3);
		}else{
			if(picIdOrg3.length==0){
				mchtInfo_dataset.setValue("picId3","03" + picIdOrg3);
			}else{
				if('03'==picIdOrg3.substring(0,2)){
					mchtInfo_dataset.setValue("picId3", picIdOrg3);		
				}else{
					mchtInfo_dataset.setValue("picId3","03" + picIdOrg3);
				}
			}
		}
		if(picId4.length!=0){
			mchtInfo_dataset.setValue("picId4","04" + picId4);
		}else{
			if(picIdOrg4.length==0){
				mchtInfo_dataset.setValue("picId4","04" + picIdOrg4);
			}else{
				if('04'==picIdOrg4.substring(0,2)){
					mchtInfo_dataset.setValue("picId4", picIdOrg4);					
				}else{
					mchtInfo_dataset.setValue("picId4","04" + picIdOrg4);
				}
			}
		}
		if(picId5.length!=0){
			mchtInfo_dataset.setValue("picId5","05" + picId5);
		}else{
			if(picIdOrg5.length==0){
				mchtInfo_dataset.setValue("picId5","05" + picIdOrg5);
			}else{
				if('05'==picIdOrg5.substring(0,2)){
					mchtInfo_dataset.setValue("picId5", picIdOrg5);					
				}else{
					mchtInfo_dataset.setValue("picId5","05" + picIdOrg5);
				}
			}
		}
		if(picId6.length!=0){
			mchtInfo_dataset.setValue("picId6","06" + picId6);
		}else{
			if(picIdOrg6.length==0){
				mchtInfo_dataset.setValue("picId6","06" + picIdOrg6);
			}else{
				if('06'==picIdOrg6.substring(0,2)){
					mchtInfo_dataset.setValue("picId6", picIdOrg6);					
				}else{
					mchtInfo_dataset.setValue("picId6","06" + picIdOrg6);
				}
			}
		}
		if(picId7.length!=0){
			mchtInfo_dataset.setValue("picId7","07" + picId7);
		}else{
			if(picIdOrg7.length==0){
				mchtInfo_dataset.setValue("picId7","07" + picIdOrg7);
			}else{
				if('07'==picIdOrg7.substring(0,2)){
					mchtInfo_dataset.setValue("picId7", picIdOrg7);					
				}else{
					mchtInfo_dataset.setValue("picId7","07" + picIdOrg7);
				}
			}
		}
		if(picId8.length!=0){
			mchtInfo_dataset.setValue("picId8","08" + picId8);
		}else{
			if(picIdOrg8.length==0){
				mchtInfo_dataset.setValue("picId8","08" + picIdOrg8);
			}else{
				if('08'==picIdOrg8.substring(0,2)){
					mchtInfo_dataset.setValue("picId8", picIdOrg8);					
				}else{
					mchtInfo_dataset.setValue("picId8","08" + picIdOrg8);
				}
			}
		}
		if(picId9.length!=0){
			mchtInfo_dataset.setValue("picId9","09" + picId9);
		}else{
			if(picIdOrg9.length==0){
				mchtInfo_dataset.setValue("picId9","09" + picIdOrg9);
			}else{
				if('09'==picIdOrg9.substring(0,2)){
					mchtInfo_dataset.setValue("picId9", picIdOrg9);					
				}else{
					mchtInfo_dataset.setValue("picId9","09" + picIdOrg9);
				}
			}
		}
		if(picId10.length!=0){
			mchtInfo_dataset.setValue("picId10","10" + picId10);
		}else{
			if(picIdOrg10.length==0){
				mchtInfo_dataset.setValue("picId10","10" + picIdOrg10);
			}else{
				if('10'==picIdOrg10.substring(0,2)){
					mchtInfo_dataset.setValue("picId10", picIdOrg10);					
				}else{
					mchtInfo_dataset.setValue("picId10","10" + picIdOrg10);
				}
			}
		}
		if(picId11.length!=0){
			mchtInfo_dataset.setValue("picId11","11" + picId11);
		}else{
			if(picIdOrg11.length==0){
				mchtInfo_dataset.setValue("picId11","11" + picIdOrg11);
			}else{
				if('11'==picIdOrg11.substring(0,2)){
					mchtInfo_dataset.setValue("picId11", picIdOrg11);					
				}else{
					mchtInfo_dataset.setValue("picId11","11" + picIdOrg11);
				}
			}
		}
		if(picId12.length!=0){
			mchtInfo_dataset.setValue("picId12","12" + picId12);
		}else{
			if(picIdOrg12.length==0){
				mchtInfo_dataset.setValue("picId12","12" + picIdOrg12);
			}else{
				if('12'==picIdOrg12.substring(0,2)){
					mchtInfo_dataset.setValue("picId12", picIdOrg12);					
				}else{
					mchtInfo_dataset.setValue("picId12","12" + picIdOrg12);
				}
			}
		}
		
		if(picId13.length!=0){
			mchtInfo_dataset.setValue("picId13","13" + picId13);
		}else{
			if(picIdOrg13.length==0){
				mchtInfo_dataset.setValue("picId13","13" + picIdOrg13);
			}else{
				if('13'==picIdOrg13.substring(0,2)){
					mchtInfo_dataset.setValue("picId13", picIdOrg13);					
				}else{
					mchtInfo_dataset.setValue("picId13","13" + picIdOrg13);
				}
			}
		}
		if(picId14.length!=0){
			mchtInfo_dataset.setValue("picId14","14" + picId14);
		}else{
			if(picIdOrg14.length==0){
				mchtInfo_dataset.setValue("picId14","14" + picIdOrg14);
			}else{
				if('14'==picIdOrg14.substring(0,2)){
					mchtInfo_dataset.setValue("picId14", picIdOrg14);					
				}else{
					mchtInfo_dataset.setValue("picId14","14" + picIdOrg14);
				}
			}
		}
		if(picId18.length!=0){
			mchtInfo_dataset.setValue("picId18","18" + picId18);
		}else{
			if(picIdOrg18.length==0){
				mchtInfo_dataset.setValue("picId18","18" + picIdOrg18);
			}else{
				if('18'==picIdOrg18.substring(0,2)){
					mchtInfo_dataset.setValue("picId18", picIdOrg18);					
				}else{
					mchtInfo_dataset.setValue("picId18","18" + picIdOrg18);
				}
			}
		}
		if(picId19.length!=0){
			mchtInfo_dataset.setValue("picId19","19" + picId19);
		}else{
			if(picIdOrg19.length==0){
				mchtInfo_dataset.setValue("picId19","19" + picIdOrg19);
			}else{
				if('19'==picIdOrg19.substring(0,2)){
					mchtInfo_dataset.setValue("picId19", picIdOrg19);					
				}else{
					mchtInfo_dataset.setValue("picId19","19" + picIdOrg19);
				}
			}
		}
		if(picId21.length!=0){
			mchtInfo_dataset.setValue("picId21","21" + picId21);
		}else{
			if(picIdOrg21.length==0){
				mchtInfo_dataset.setValue("picId21","21" + picIdOrg21);
			}else{
				if('21'==picIdOrg21.substring(0,2)){
					mchtInfo_dataset.setValue("picId21", picIdOrg21);					
				}else{
					mchtInfo_dataset.setValue("picId21","21" + picIdOrg21);
				}
			}
		}
		if(picId22.length!=0){
			mchtInfo_dataset.setValue("picId22","22" + picId22);
		}else{
			if(picIdOrg22.length==0){
				mchtInfo_dataset.setValue("picId22","22" + picIdOrg22);
			}else{
				if('22'==picIdOrg22.substring(0,2)){
					mchtInfo_dataset.setValue("picId22", picIdOrg22);					
				}else{
					mchtInfo_dataset.setValue("picId22","22" + picIdOrg22);
				}
			}
		}
		if(picId23.length!=0){
			mchtInfo_dataset.setValue("picId23","23" + picId23);
		}else{
			if(picIdOrg23.length==0){
				mchtInfo_dataset.setValue("picId23","23" + picIdOrg23);
			}else{
				if('23'==picIdOrg23.substring(0,2)){
					mchtInfo_dataset.setValue("picId23", picIdOrg23);					
				}else{
					mchtInfo_dataset.setValue("picId23","23" + picIdOrg23);
				}
			}
		}
		if(picId24.length!=0){
			mchtInfo_dataset.setValue("picId24","24" + picId24);
		}else{
			if(picIdOrg24.length==0){
				mchtInfo_dataset.setValue("picId24","24" + picIdOrg24);
			}else{
				if('24'==picIdOrg24.substring(0,2)){
					mchtInfo_dataset.setValue("picId24", picIdOrg24);					
				}else{
					mchtInfo_dataset.setValue("picId24","24" + picIdOrg24);
				}
			}
		}
		if(picId26.length!=0){
			mchtInfo_dataset.setValue("picId26","26" + picId26);
		}else{
			if(picIdOrg26.length==0){
				mchtInfo_dataset.setValue("picId26","26" + picIdOrg26);
			}else{
				if('26'==picIdOrg26.substring(0,2)){
					mchtInfo_dataset.setValue("picId26", picIdOrg26);					
				}else{
					mchtInfo_dataset.setValue("picId26","26" + picIdOrg26);
				}
			}
		}
		if(picId27.length!=0){
			mchtInfo_dataset.setValue("picId27","27" + picId27);
		}else{
			if(picIdOrg27.length==0){
				mchtInfo_dataset.setValue("picId27","27" + picIdOrg27);
			}else{
				if('27'==picIdOrg27.substring(0,2)){
					mchtInfo_dataset.setValue("picId27", picIdOrg27);					
				}else{
					mchtInfo_dataset.setValue("picId27","27" + picIdOrg27);
				}
			}
		}
		if(picId28.length!=0){
			mchtInfo_dataset.setValue("picId28","28" + picId28);
		}else{
			if(picIdOrg28.length==0){
				mchtInfo_dataset.setValue("picId28","28" + picIdOrg28);
			}else{
				if('28'==picIdOrg28.substring(0,2)){
					mchtInfo_dataset.setValue("picId28", picIdOrg28);					
				}else{
					mchtInfo_dataset.setValue("picId28","28" + picIdOrg28);
				}
			}
		}
		if(picId29.length!=0){
			mchtInfo_dataset.setValue("picId29","29" + picId29);
		}else{
			if(picIdOrg29.length==0){
				mchtInfo_dataset.setValue("picId29","29" + picIdOrg29);
			}else{
				if('29'==picIdOrg29.substring(0,2)){
					mchtInfo_dataset.setValue("picId29", picIdOrg29);					
				}else{
					mchtInfo_dataset.setValue("picId29","29" + picIdOrg29);
				}
			}
		}
		if(picId30.length!=0){
			mchtInfo_dataset.setValue("picId30","30" + picId30);
		}else{
			if(picIdOrg30.length==0){
				mchtInfo_dataset.setValue("picId30","30" + picIdOrg30);
			}else{
				if('30'==picIdOrg30.substring(0,2)){
					mchtInfo_dataset.setValue("picId30", picIdOrg30);					
				}else{
					mchtInfo_dataset.setValue("picId30","30" + picIdOrg30);
				}
			}
		}
		if(picId31.length!=0){
			mchtInfo_dataset.setValue("picId31","31" + picId31);
		}else{
			if(picIdOrg31.length==0){
				mchtInfo_dataset.setValue("picId31","31" + picIdOrg31);
			}else{
				if('31'==picIdOrg31.substring(0,2)){
					mchtInfo_dataset.setValue("picId31", picIdOrg31);					
				}else{
					mchtInfo_dataset.setValue("picId31","31" + picIdOrg31);
				}
			}
		}
		if(picId32.length!=0){
			mchtInfo_dataset.setValue("picId32","32" + picId32);
		}else{
			if(picIdOrg32.length==0){
				mchtInfo_dataset.setValue("picId32","32" + picIdOrg32);
			}else{
				if('32'==picIdOrg32.substring(0,2)){
					mchtInfo_dataset.setValue("picId32", picIdOrg32);					
				}else{
					mchtInfo_dataset.setValue("picId32","32" + picIdOrg32);
				}
			}
		}
		if(picId33.length!=0){
			mchtInfo_dataset.setValue("picId33","33" + picId33);
		}else{
			if(picIdOrg33.length==0){
				mchtInfo_dataset.setValue("picId33","33" + picIdOrg33);
			}else{
				if('33'==picIdOrg33.substring(0,2)){
					mchtInfo_dataset.setValue("picId33", picIdOrg33);					
				}else{
					mchtInfo_dataset.setValue("picId33","33" + picIdOrg33);
				}
			}
		}
		if(picId34.length!=0){
			mchtInfo_dataset.setValue("picId34","34" + picId34);
		}else{
			if(picIdOrg34.length==0){
				mchtInfo_dataset.setValue("picId34","34" + picIdOrg34);
			}else{
				if('34'==picIdOrg34.substring(0,2)){
					mchtInfo_dataset.setValue("picId34", picIdOrg34);					
				}else{
					mchtInfo_dataset.setValue("picId34","34" + picIdOrg34);
				}
			}
		}
		if(picId35.length!=0){
			mchtInfo_dataset.setValue("picId35","35" + picId35);
		}else{
			if(picIdOrg35.length==0){
				mchtInfo_dataset.setValue("picId35","35" + picIdOrg35);
			}else{
				if('35'==picIdOrg35.substring(0,2)){
					mchtInfo_dataset.setValue("picId35", picIdOrg35);					
				}else{
					mchtInfo_dataset.setValue("picId35","35" + picIdOrg35);
				}
			}
		}
		if(picId36.length!=0){
			mchtInfo_dataset.setValue("picId36","36" + picId36);
		}else{
			if(picIdOrg36.length==0){
				mchtInfo_dataset.setValue("picId36","36" + picIdOrg36);
			}else{
				if('36'==picIdOrg36.substring(0,2)){
					mchtInfo_dataset.setValue("picId36", picIdOrg36);					
				}else{
					mchtInfo_dataset.setValue("picId36","36" + picIdOrg36);
				}
			}
		}
		if(picId37.length!=0){
			mchtInfo_dataset.setValue("picId37","37" + picId37);
		}else{
			if(picIdOrg37.length==0){
				mchtInfo_dataset.setValue("picId37","37" + picIdOrg37);
			}else{
				if('37'==picIdOrg37.substring(0,2)){
					mchtInfo_dataset.setValue("picId37", picIdOrg37);					
				}else{
					mchtInfo_dataset.setValue("picId37","37" + picIdOrg37);
				}
			}
		}
		if(picId38.length!=0){
			mchtInfo_dataset.setValue("picId38","38" + picId38);
		}else{
			if(picIdOrg38.length==0){
				mchtInfo_dataset.setValue("picId38","38" + picIdOrg38);
			}else{
				if('38'==picIdOrg38.substring(0,2)){
					mchtInfo_dataset.setValue("picId38", picIdOrg38);					
				}else{
					mchtInfo_dataset.setValue("picId38","38" + picIdOrg38);
				}
			}
		}
		if(picId39.length!=0){
			mchtInfo_dataset.setValue("picId39","39" + picId39);
		}else{
			if(picIdOrg39.length==0){
				mchtInfo_dataset.setValue("picId39","39" + picIdOrg39);
			}else{
				if('39'==picIdOrg39.substring(0,2)){
					mchtInfo_dataset.setValue("picId39", picIdOrg39);					
				}else{
					mchtInfo_dataset.setValue("picId39","39" + picIdOrg39);
				}
			}
		}
		var picIdUpd1=mchtInfo_dataset.getValue("picId1");
		var picIdUpd2=mchtInfo_dataset.getValue("picId2");
		var picIdUpd3=mchtInfo_dataset.getValue("picId3");
		var picIdUpd7=mchtInfo_dataset.getValue("picId7");
		var picIdUpd8=mchtInfo_dataset.getValue("picId8");
		var picIdUpd9=mchtInfo_dataset.getValue("picId9");
		var picIdUpd13=mchtInfo_dataset.getValue("picId13");
		var picIdUpd14=mchtInfo_dataset.getValue("picId14");
		var picIdUpd18=mchtInfo_dataset.getValue("picId18");
		var picIdUpd19=mchtInfo_dataset.getValue("picId19");
		var picIdUpd21=mchtInfo_dataset.getValue("picId21");
		var picIdUpd22=mchtInfo_dataset.getValue("picId22");
		var picIdUpd23=mchtInfo_dataset.getValue("picId23");
		var picIdUpd24=mchtInfo_dataset.getValue("picId24");
		var picIdUpd26=mchtInfo_dataset.getValue("picId26");
		var picIdUpd27=mchtInfo_dataset.getValue("picId27");
		var picIdUpd28=mchtInfo_dataset.getValue("picId28");
		var picIdUpd29=mchtInfo_dataset.getValue("picId29");

		if('01'==mchtTypeUpd){
			if('1'==accountType){
					if(picIdUpd1.length<=2){
						$.warn("图片:请上传营业执照");
						return false;
					}
					if(picIdUpd2.length<=2){
						$.warn("图片:请上传法人证件正面");
						return false;
					}
					if(picIdUpd7.length<=2){
						$.warn("图片:请上传法人证件反面");
						return false;
					}
					if(picIdUpd9.length<=2){
						$.warn("图片:请上传店铺门头照(含有招牌)");
						return false;
					}
					if(picIdUpd13.length<=2){
						$.warn("图片:请上传开户许可证");
						return false;
					}			
			}else{
				if(mchtArtifName===setlAcctName){
					if(picIdUpd1.length<=2){
						$.warn("图片:请上传营业执照");
						return false;
					}
					if(picIdUpd2.length<=2){
						$.warn("图片:请上传法人证件正面");
						return false;
					}
					if(picIdUpd7.length<=2){
						$.warn("图片:请上传法人证件反面");
						return false;
					}
					if(picIdUpd8.length<=2){
						$.warn("图片:请上传银行卡正面照(收款人银行卡照片)");
						return false;
					}
					if(picIdUpd9.length<=2){
						$.warn("图片:请上传店铺门头照(含有招牌)");
						return false;
					}
					// modify by lengjingyu 20180127  企业商户结算到法定代表人对私账户，不再需要委托收款通知书   jira-1947
					/* if(picIdUpd23.length<=2){
						$.warn("图片:请上传授权证明(委托收款通知书)");
						return false;
					} */
					// modify end
				}else{
					if(picIdUpd1.length<=2){
						$.warn("图片:请上传营业执照");
						return false;
					}
					if(picIdUpd2.length<=2){
						$.warn("图片:请上传法人证件正面");
						return false;
					}
					if(picIdUpd7.length<=2){
						$.warn("图片:请上传法人证件反面");
						return false;
					}
					if(picIdUpd8.length<=2){
						$.warn("图片:请上传银行卡正面照(收款人银行卡照片)");
						return false;
					}
					if(picIdUpd9.length<=2){
						$.warn("图片:请上传店铺门头照(含有招牌)");
						return false;
					}
					if(picIdUpd18.length<=2){
						$.warn("图片:请上传收款人证件正面");
						return false;
					}
					if(picIdUpd19.length<=2){
						$.warn("图片:请上传收款人证件反面");
						return false;
					}
					if(picIdUpd23.length<=2){
						$.warn("图片:请上传授权证明(委托收款通知书)");
						return false;
					}	
				}
				
			}
		}
		
		if('04'==mchtTypeUpd){
			if('2'==accountType){
				if(mchtArtifName===setlAcctName){
					if(picIdUpd1.length<=2){
						$.warn("图片:请上传营业执照");
						return false;
					}
					if(picIdUpd2.length<=2){
						$.warn("图片:请上传法人证件正面");
						return false;
					}
					if(picIdUpd7.length<=2){
						$.warn("图片:请上传法人证件反面");
						return false;
					}
					if(picIdUpd8.length<=2){
						$.warn("图片:请上传银行卡正面照(收款人银行卡照片)");
						return false;
					}
					if(picIdUpd9.length<=2){
						$.warn("图片:请上传店铺门头照(含有招牌)");
						return false;
					}
					// modify by lengjingyu 20180127   法人代表手持身份证改为选填  jira-1947
					/* if(picIdUpd14.length<=2){
						$.warn("图片:请上传法人手持身份证");
						return false;
					} */
					// modify end
					/* if(picIdUpd22.length<=2){
						$.warn("图片:请上传合同照片");
						return false;
					} */
				}else{
					if(picIdUpd1.length<=2){
						$.warn("图片:请上传营业执照");
						return false;
					}
					if(picIdUpd2.length<=2){
						$.warn("图片:请上传法人证件正面");
						return false;
					}
					if(picIdUpd7.length<=2){
						$.warn("图片:请上传法人证件反面");
						return false;
					}
					if(picIdUpd8.length<=2){
						$.warn("图片:请上传银行卡正面照(收款人银行卡照片)");
						return false;
					}
					if(picIdUpd9.length<=2){
						$.warn("图片:请上传店铺门头照(含有招牌)");
						return false;
					}
					// modify by lengjingyu 20180127   法人代表手持身份证改为选填  jira-1947
					/* if(picIdUpd14.length<=2){
						$.warn("图片:请上传法人手持身份证");
						return false;
					} */
					// modify end
					if(picIdUpd18.length<=2){
						$.warn("图片:请上传收款人证件正面");
						return false;
					}
					if(picIdUpd19.length<=2){
						$.warn("图片:请上传收款人证件反面");
						return false;
					}
					/* if(picIdUpd22.length<=2){
						$.warn("图片:请上传合同照片");
						return false;
					} */
					if(picIdUpd23.length<=2){
						$.warn("图片:请上传授权证明(委托收款通知书)");
						return false;
					}
					// modify by lengjingyu 20180130   按照协议定义，24_收款人手持身份证照，不应该是必填项   jira-2012
					/* if(picIdUpd24.length<=2){
						$.warn("图片:请上传收款人手持身份证照");
						return false;
					} */
					// modify end 
				}
			}else{
				$.warn("商户为个体工商和个人商户结算方式不能为对公!");
				return false;
			}
		}
		if('06'==mchtTypeUpd){
			if('2'==accountType){
				if(picIdUpd9.length<=2){
					$.warn("图片:请上传店铺门头照(含有招牌)");
					return false;
				}
				if(picIdUpd21.length<=2){
					$.warn("图片:请上传租赁合同(或产权证明书)");
					return false;
				}
				/* if(picIdUpd22.length<=2){
					$.warn("图片:请上传合同照片");
					return false;
				} */
				if(picIdUpd26.length<=2){
					$.warn("图片:请上传租赁合同承租人同名身份证正面照");
					return false;
				}
				if(picIdUpd27.length<=2){
					$.warn("图片:请上传租赁合同承租人同名身份证反面照");
					return false;
				}
				if(picIdUpd28.length<=2){
					$.warn("图片:请上传租赁合同承租人同名手持身份证照");
					return false;
				}
				if(picIdUpd29.length<=2){
					$.warn("图片:请上传租赁合同承租人同名银行卡照片");
					return false;
				}
			}else{
				$.warn("商户为个体工商和个人商户结算方式不能为对公!");
				return false;
			}
		}
		
		btnUpdSub.setDisabled(true);// 点击后八秒内不能再次提交
		var timer = setInterval(function(){//开启定时器
			btnUpdSub.setDisabled(false);
			clearInterval(timer);    //清除定时器
		},8000);
		
		return true;
	} 
	
	 function doSave(){
		 btnUpdSub.click();
	 }
	/**提交*/
	function btnUpdSub_postSubmit(){
		$.success("操作成功！",function(){
			parent.saveMngUpReCallback();
		});
	}
	
	function importFile_onButtonClick(i,win,framewin){
		if(i==0){//取消按钮
			win.close();
			mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);	
		}else{
				framewin.excuteImport();
				mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);	
				iscallback = true;
			}
		}

	function mchtInfo_dataset_afterChange(dataset,field){
		if(field.fieldName=="mchtArtifId"){
			var mchtArtifType=mchtInfo_dataset.getValue("mchtArtifType");
			var mchtArtifId = mchtInfo_dataset.getValue("mchtArtifId");
			if("00"==mchtArtifType){
				if(!regIdNo.test(mchtArtifId)){ 
					$.warn("【身份证号】填写有误"); 
					return false; 
				}
			}
		}
		
		if(field.fieldName=="mchtType"){
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
	}
	</script>
</snow:page>