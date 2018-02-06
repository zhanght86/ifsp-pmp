<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="商户信息">
	<!-- 主界面数据集  -->
	    <!-- 商户信息数据集  -->
		<snow:dataset id="mchtInfoAdd" path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMngAdd" init="true" submitMode="current" ></snow:dataset>
		<!-- 商户辅表数据集  -->
		<snow:dataset id="PbsMchtAssistInfoTmp" path="com.ruimin.ifs.pmp.mchtMng.dataset.PbsMchtAssistInfoTmpAdd" init="true" submitMode="current" ></snow:dataset>
		<!-- 新增窗口 -->
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoAdd" label="基本信息" dataset="mchtInfoAdd" fieldstr="mchtSimpleName,mchtName,mchtType,mchtOrgSel,mchtPersonName,mchtAreaNo,mchtPhone,telephone,mchtEmail,mchtContAddr,mchtMngSel,mchtAmrNo,mchtAmrName,riskLevel,creditLimit" ></snow:form>		
			
			<snow:form id="winAddForm" dataset="PbsMchtAssistInfoTmp" label="商户辅表" fieldstr="chlSysNo,mchtProVerNo,accountType,setlAcctName,mchtArtifSex,mchtArtifJob,mchtArtifAddress,accountIdType,accountIdNo,mchtArtifCountryId,occNo,occSdate,occEdate,trcNo,trcSdate,trcEdate,financialContact,financialTel,firCateCode,secCateCode,thdCateCode,wxAppid,subscribeWxAppid,userDefined,intro,mchtAuditRsltUrl,wxJsapiPath"
			collapsible="true"  colnum="4" ></snow:form>
			<!-- No2 资质信息模块 -->
			<snow:form id="qlfInfoAdd" label="资质信息" dataset="mchtInfoAdd" fieldstr="mchtLicnNo,mchtLicnType,mchtLicnSdate,mchtLicnExpDate,mchtMngScope,mchtRegAmt,currencyType,organizationCode,mchtRegAddr,mchtTrcnNo,mchtArtifName,mchtArtifType,mchtArtifId,mchtArtifPhone,artifSdate,artifEdate,recvDeposit,paidDeposit" collapsible="false"></snow:form>

			<!-- No3.1 加载标签栏 -->
				<snow:tabs id="qlfPicTabsAdd" border="true" height="750">
					<!-- No3.1.0 标签【商户logo】 -->
					<snow:tab  id="tabAdd0" title="商户logo" closable="false">				
						<div><iframe id="uploadAdd0" name="uploadAdd0" src="../../payPmp/pubTool/uploadImage.jsp?picNum=0"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd0" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.1 标签【营业执照】 -->
					<snow:tab  id="tabAdd1" title="营业执照" closable="false">				
						<div><iframe id="uploadAdd1" name="uploadAdd1" src="../../payPmp/pubTool/uploadImage.jsp?picNum=1"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd1" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.2 标签【法定代表人证件】 -->
					<snow:tab id="tabAdd2" title="法定代表人证件" closable="false">
						<div><iframe id="uploadAdd2" name="uploadAdd2" src="../../payPmp/pubTool/uploadImage.jsp?picNum=2"></iframe></div>			
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd2" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.3 标签【组织机构】 —>门店照片 -->
					<snow:tab id="tabAdd3" title="组织机构" closable="false">
						<div><iframe id="uploadAdd3" name="uploadAdd3" src="../../payPmp/pubTool/uploadImage.jsp?picNum=3"></iframe></div>			
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd3" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.4 标签【ICP许可】 —>店内经营 -->
					<snow:tab id="tabAdd4" title="ICP许可" closable="false">
						<div><iframe id="uploadAdd4" name="uploadAdd4" src="../../payPmp/pubTool/uploadImage.jsp?picNum=4"></iframe></div>			
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd4" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.5 标签【税务许可】—>其他2 -->
					<snow:tab id="tabAdd5" title="税务许可" closable="false">
						<div><iframe id="uploadAdd5" name="uploadAdd5" src="../../payPmp/pubTool/uploadImage.jsp?picNum=5"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd5" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.6 标签【商户APP门店图】—>其他2 -->
					<snow:tab id="tabAdd6" title="商户门店" closable="false">
						<div><iframe id="uploadAdd6" name="uploadAdd6" src="../../payPmp/pubTool/uploadImage.jsp?picNum=6"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd6" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.7 标签【法定代表人证件反面】—>其他2 -->
					<snow:tab id="tabAdd7" title="法定代表人证件反面" closable="false">
						<div><iframe id="uploadAdd7" name="uploadAdd7" src="../../payPmp/pubTool/uploadImage.jsp?picNum=7"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd7" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>							
					<!-- No3.1.8 标签【银行卡正面照】—>其他2 -->
					<snow:tab id="tabAdd8" title="银行卡正面照" closable="false">
						<div><iframe id="uploadAdd8" name="uploadAdd8" src="../../payPmp/pubTool/uploadImage.jsp?picNum=8"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd8" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.9 标签【店铺门头照(含有招牌)】—>其他2 -->
					<snow:tab id="tabAdd9" title="店铺门头照" closable="false">
						<div><iframe id="uploadAdd9" name="uploadAdd9" src="../../payPmp/pubTool/uploadImage.jsp?picNum=9"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd9" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
				</snow:tabs>
				<snow:tabs id="qlfPicTabsAdd2" border="true" height="750">
					<!-- No3.1.10 标签【10-店铺全景照(店内环境)】—>其他2 -->
					<snow:tab id="tabAdd10" title="店铺全景照" closable="false">
						<div><iframe id="uploadAdd10" name="uploadAdd10" src="../../payPmp/pubTool/uploadImage.jsp?picNum=10"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd10" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.11 标签【11-收银台照】—>其他2 -->
					<snow:tab id="tabAdd11" title="收银台照" closable="false">
						<div><iframe id="uploadAdd11" name="uploadAdd11" src="../../payPmp/pubTool/uploadImage.jsp?picNum=11"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd11" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.12 标签【12-商品照】—>其他2 -->
					<snow:tab id="tabAdd12" title="商品照" closable="false">
						<div><iframe id="uploadAdd12" name="uploadAdd12" src="../../payPmp/pubTool/uploadImage.jsp?picNum=12"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd12" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.13 标签【13-开户许可证】 -->
					<snow:tab id="tabAdd13" title="开户许可证" closable="false">
						<div><iframe id="uploadAdd13" name="uploadAdd13" src="../../payPmp/pubTool/uploadImage.jsp?picNum=13"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd13" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.14 标签【法定代表人手持身份证】 -->
					<snow:tab id="tabAdd14" title="法定代表人手持身份证" closable="false">
						<div><iframe id="uploadAdd14" name="uploadAdd14" src="../../payPmp/pubTool/uploadImage.jsp?picNum=14"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd14" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.18 标签【收款人证件正面】 -->
					<snow:tab id="tabAdd18" title="收款人证件正面" closable="false">
						<div><iframe id="uploadAdd18" name="uploadAdd18" src="../../payPmp/pubTool/uploadImage.jsp?picNum=18"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd18" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.19 标签【收款人证件反面】 -->
					<snow:tab id="tabAdd19" title="收款人证件反面" closable="false">
						<div><iframe id="uploadAdd19" name="uploadAdd19" src="../../payPmp/pubTool/uploadImage.jsp?picNum=19"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd19" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.21 标签【租赁合同(或产权证明书)】—>其他2 -->
					<snow:tab id="tabAdd21" title="租赁合同" closable="false">
						<div><iframe id="uploadAdd21" name="uploadAdd21" src="../../payPmp/pubTool/uploadImage.jsp?picNum=21"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd21" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.22 标签【22-合同照片】—>其他2 -->
					<snow:tab id="tabAdd22" title="合同照片" closable="false">
						<div><iframe id="uploadAdd22" name="uploadAdd22" src="../../payPmp/pubTool/uploadImage.jsp?picNum=22"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd22" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>	
					<!-- No3.1.23 标签【授权证明(委托收款通知书)】—>其他2 -->
					<snow:tab id="tabAdd23" title="授权证明" closable="false">
						<div><iframe id="uploadAdd23" name="uploadAdd23" src="../../payPmp/pubTool/uploadImage.jsp?picNum=23"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd23" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>
				<snow:tabs id="qlfPicTabsAdd3" border="true" height="750">
					<!-- No3.1.24 标签【收款人手持身份证照】—>其他2 -->
					<snow:tab id="tabAdd24" title="收款人手持身份证照" closable="false">
						<div><iframe id="uploadAdd24" name="uploadAdd24" src="../../payPmp/pubTool/uploadImage.jsp?picNum=24"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd24" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.26 标签【租赁合同承租人同名身份证正面照】—>其他2 -->
					<snow:tab id="tabAdd26" title="租赁合同承租人同名身份证正面照" closable="false">
						<div><iframe id="uploadAdd26" name="uploadAdd26" src="../../payPmp/pubTool/uploadImage.jsp?picNum=26"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd26" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.27 标签【租赁合同承租人同名身份证反面照】—>其他2 -->
					<snow:tab id="tabAdd27" title="租赁合同承租人同名身份证反面照" closable="false">
						<div><iframe id="uploadAdd27" name="uploadAdd27" src="../../payPmp/pubTool/uploadImage.jsp?picNum=27"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd27" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.28 标签【租赁合同承租人同名手持身份证照】—>其他2 -->
					<snow:tab id="tabAdd28" title="租赁合同承租人同名手持身份证照" closable="false">
						<div><iframe id="uploadAdd28" name="uploadAdd28" src="../../payPmp/pubTool/uploadImage.jsp?picNum=28"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd28" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.29 标签【租赁合同承租人同名银行卡照片】—>其他2 -->
					<snow:tab id="tabAdd29" title="租赁合同承租人同名银行卡照片" closable="false">
						<div><iframe id="uploadAdd29" name="uploadAdd29" src="../../payPmp/pubTool/uploadImage.jsp?picNum=29"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd29" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>
				<snow:tabs id="qlfPicTabsAdd4" border="true" height="750">
					<!-- No3.1.30 标签【补充资料1】 -->
					<snow:tab id="tabAdd30" title="补充资料1" closable="false">
						<div><iframe id="uploadAdd30" name="uploadAdd30" src="../../payPmp/pubTool/uploadImage.jsp?picNum=30"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd30" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.31 标签【补充资料2】 -->
					<snow:tab id="tabAdd31" title="补充资料2" closable="false">
						<div><iframe id="uploadAdd31" name="uploadAdd31" src="../../payPmp/pubTool/uploadImage.jsp?picNum=31"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd31" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.32 标签【补充资料3】 -->
					<snow:tab id="tabAdd32" title="补充资料3" closable="false">
						<div><iframe id="uploadAdd32" name="uploadAdd32" src="../../payPmp/pubTool/uploadImage.jsp?picNum=32"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd32" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.33 标签【补充资料4】 -->
					<snow:tab id="tabAdd33" title="补充资料4" closable="false">
						<div><iframe id="uploadAdd33" name="uploadAdd33" src="../../payPmp/pubTool/uploadImage.jsp?picNum=33"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd33" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.34 标签【补充资料5】 -->
					<snow:tab id="tabAdd34" title="补充资料5" closable="false">
						<div><iframe id="uploadAdd34" name="uploadAdd34" src="../../payPmp/pubTool/uploadImage.jsp?picNum=34"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd34" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.35 标签【补充资料6】 -->
					<snow:tab id="tabAdd35" title="补充资料6" closable="false">
						<div><iframe id="uploadAdd35" name="uploadAdd35" src="../../payPmp/pubTool/uploadImage.jsp?picNum=35"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd35" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.36 标签【补充资料7】 -->
					<snow:tab id="tabAdd36" title="补充资料7" closable="false">
						<div><iframe id="uploadAdd36" name="uploadAdd36" src="../../payPmp/pubTool/uploadImage.jsp?picNum=36"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd36" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.37 标签【补充资料8】 -->
					<snow:tab id="tabAdd37" title="补充资料8" closable="false">
						<div><iframe id="uploadAdd37" name="uploadAdd37" src="../../payPmp/pubTool/uploadImage.jsp?picNum=37"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd37" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.38 标签【补充资料9】 -->
					<snow:tab id="tabAdd38" title="补充资料9" closable="false">
						<div><iframe id="uploadAdd38" name="uploadAdd38" src="../../payPmp/pubTool/uploadImage.jsp?picNum=38"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd38" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
					<!-- No3.1.39 标签【补充资料10】 -->
					<snow:tab id="tabAdd39" title="补充资料10" closable="false">
						<div><iframe id="uploadAdd39" name="uploadAdd39" src="../../payPmp/pubTool/uploadImage.jsp?picNum=39"></iframe></div>		
						<div style="width: 100%; height: 70%; background-color: FFFFFF; float: left ;text-align: center"><img id="picAdd39" src="" style="height: 550px; width: 820px;" /></div>
					</snow:tab>
				</snow:tabs>	
				
			
			<!-- No4 加载提交按钮 -->
			<snow:button id="btnAddSub" dataset="mchtInfoAdd" hidden="true"></snow:button>
	
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
		
		/***************图片控制***************/				
		function picJudge(lgPic,result,msg){
			if (lgPic != "" && result == "false"){		
				$.warn(msg);
				return false;
			}else return true;
		}
		
		function initCallGetter_post() {
			PbsMchtAssistInfoTmp_dataset.setValue("wxJsapiPath","https://www.ect888.com/payweb/pay/");			
		}

		function importFileCallBack(result,picSrc,picNum) {
			if (result) {				
				switch (picNum) {
				case "0":
					$("#picAdd0").attr("src",picSrc);
					break;
				case "1":
					$("#picAdd1").attr("src",picSrc);
					break;
					
				case "2":
					$("#picAdd2").attr("src",picSrc);
					break;
					
				case "3":
					$("#picAdd3").attr("src",picSrc);
					break;
					
				case "4":
					$("#picAdd4").attr("src",picSrc);
					break;
				
				case "5":
					$("#picAdd5").attr("src",picSrc);
					break;
				case "6":
					$("#picAdd6").attr("src",picSrc);
					break;
				case "7":
					$("#picAdd7").attr("src",picSrc);
					break;
				case "8":
					$("#picAdd8").attr("src",picSrc);
					break;
				case "9":
					$("#picAdd9").attr("src",picSrc);
					break;
				case "10":
					$("#picAdd10").attr("src",picSrc);
					break;
				case "11":
					$("#picAdd11").attr("src",picSrc);
					break;
				case "12":
					$("#picAdd12").attr("src",picSrc);
					break;
				case "13":
					$("#picAdd13").attr("src",picSrc);
					break;
				case "14":
					$("#picAdd14").attr("src",picSrc);
					break;
				case "18":
					$("#picAdd18").attr("src",picSrc);
					break;
				case "19":
					$("#picAdd19").attr("src",picSrc);
					break;
				case "21":
					$("#picAdd21").attr("src",picSrc);
					break;
				case "22":
					$("#picAdd22").attr("src",picSrc);
					break;
				case "23":
					$("#picAdd23").attr("src",picSrc);
					break;
				case "24":
					$("#picAdd24").attr("src",picSrc);
					break;
				case "26":
					$("#picAdd26").attr("src",picSrc);
					break;
				case "27":
					$("#picAdd27").attr("src",picSrc);
					break;
				case "28":
					$("#picAdd28").attr("src",picSrc);
					break;
				case "29":
					$("#picAdd29").attr("src",picSrc);
					break;
				case "30":
					$("#picAdd30").attr("src",picSrc);
					break;
				case "31":
					$("#picAdd31").attr("src",picSrc);
					break;
				case "32":
					$("#picAdd32").attr("src",picSrc);
					break;
				case "33":
					$("#picAdd33").attr("src",picSrc);
					break;
				case "34":
					$("#picAdd34").attr("src",picSrc);
					break;
				case "35":
					$("#picAdd35").attr("src",picSrc);
					break;
				case "36":
					$("#picAdd36").attr("src",picSrc);
					break;
				case "37":
					$("#picAdd37").attr("src",picSrc);
					break;
				case "38":
					$("#picAdd38").attr("src",picSrc);
					break;
				case "39":
					$("#picAdd39").attr("src",picSrc);
					break;
				default:
					break;
				}			
			
			}
		}
		//新增
		function showQlfOrNotAdd(showFlag){
			if(showFlag == true){
				$("#qlfInfoAdd").css("display","block");			
				$("#tabs_qlfPicTabsAdd").css("display","block");			
				$("#tabs_qlfPicTabsAdd2").css("display","block");			
				$("#tabs_qlfPicTabsAdd3").css("display","block");			
				$("#tabs_qlfPicTabsAdd4").css("display","block");			
			}else{
				$("#qlfInfoAdd").css("display","none");
				$("#tabs_qlfPicTabsAdd").css("display","block");			
				$("#tabs_qlfPicTabsAdd2").css("display","block");			
				$("#tabs_qlfPicTabsAdd3").css("display","block");			
				$("#tabs_qlfPicTabsAdd4").css("display","block");			
			}		
		}
		
		/**资质信息初始化，用于每次下拉改变了选项之后*/
		function initQlf(){
			mchtInfoAdd_dataset.setValue("mchtLicnNo","");
			mchtInfoAdd_dataset.setValue("mchtLicnType","");
			mchtInfoAdd_dataset.setValue("mchtLicnExpDate","");
			mchtInfoAdd_dataset.setValue("mchtRegAmt","0.00");
			mchtInfoAdd_dataset.setValue("mchtTrcnNo","");
			mchtInfoAdd_dataset.setValue("mchtArtifPhone","");
			mchtInfoAdd_dataset.setValue("mchtArtifType","");
			mchtInfoAdd_dataset.setValue("mchtArtifId","");
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
		function mchtInfoAdd_dataset_mchtOrgSel_onSelect(value,record){
			if(record == null){
				mchtInfoAdd_dataset.setValue("mchtOrgId","");//所属机构编号
				mchtInfoAdd_dataset.setValue("mchtOrgSel","");//所属机构名称
			}else{
				mchtInfoAdd_dataset.setValue("mchtOrgId",record.getValue("brcode"));//所属机构编号
				mchtInfoAdd_dataset.setValue("mchtOrgSel",record.getValue("brname"));//所属机构名称
			}		
		}
		
		/**上级商户下拉事件*/
		function mchtInfoAdd_dataset_mchtMngSel_onSelect(value,record){
			if(record == null){
				mchtInfoAdd_dataset.setValue("mchtMngNo","");//上级商户编号
				mchtInfoAdd_dataset.setValue("mchtMngSel","");//上级商户名称
			}else{
				if(mchtInfoAdd_dataset.getValue("mchtId") == record.getValue("mchtId")){
					$.warn("上级商户不能选择自己！");
					mchtInfoAdd_dataset.setValue("mchtMngNo","");//上级商户编号
					mchtInfoAdd_dataset.setValue("mchtMngSel","");//上级商户名称
				}else{
					mchtInfoAdd_dataset.setValue("mchtMngNo",record.getValue("mchtId"));//上级商户编号
					mchtInfoAdd_dataset.setValue("mchtMngSel",record.getValue("mchtSimpleName"));//上级商户名称		
				}				
			}		
		}
		
		/**校验字段(与接口进件统一) */
		function validInfo(){
			//---------------------------------接口上送部分---------------------------------------------------------------------------------------------
			var mchtName = mchtInfoAdd_dataset.getValue("mchtName");//商户全名
			var mchtSimpleName = mchtInfoAdd_dataset.getValue("mchtSimpleName");//商户简称
			//var mchtType   DDIC:1804                                                                                                 商户类型  
			var mchtPersonName = mchtInfoAdd_dataset.getValue("mchtPersonName");//联系人
			var mchtArtifType= mchtInfoAdd_dataset.getValue("mchtArtifId");
			var mchtArtifId = mchtInfoAdd_dataset.getValue("mchtArtifId");//法定代表人证件号码
			var mchtPhone = mchtInfoAdd_dataset.getValue("mchtPhone");//联系电话
			var mchtMngNo = mchtInfoAdd_dataset.getValue("mchtMngNo");//上级商户号(mchtMngAdd.dtst size:32位限制)
			var mchtContAddr = mchtInfoAdd_dataset.getValue("mchtContAddr");//联系地址(mchtMngAdd.dtst size:128位限制)
			var mchtEmail = mchtInfoAdd_dataset.getValue("mchtEmail");//联系邮箱
			var telephone = mchtInfoAdd_dataset.getValue("telephone");//座机电话
			var mchtAreaNo = mchtInfoAdd_dataset.getValue("mchtAreaNo");//所属城市编号(接口字段名:addressCode  mchtMngAdd.dtst size:8位限制)
			var mchtArtifName = mchtInfoAdd_dataset.getValue("mchtArtifName");//法定代表人姓名(mchtMngAdd.dtst size:32位限制)
			var artifSdate = mchtInfoAdd_dataset.getValue("artifSdate");//法定代表人证件生效日期(mchtMngAdd.dtst datebox控件控制长度8位)
			var artifEdate = mchtInfoAdd_dataset.getValue("artifEdate");//法定代表人证件失效日期(mchtMngAdd.dtst datebox控件控制长度8位)
			//var mchtLicnType DDIC:1819                                                                                                      营业执照类别
			var mchtLicnNo = mchtInfoAdd_dataset.getValue("mchtLicnNo");//营业执照号码
			var mchtLicnSdate = mchtInfoAdd_dataset.getValue("mchtLicnSdate");//营业执照生效日期(mchtMngAdd.dtst datebox控件控制长度8位)
			var mchtLicnExpDate = mchtInfoAdd_dataset.getValue("mchtLicnExpDate");//营业执照失效日期(接口字段名:mchtLicnEdate mchtMngAdd.dtst datebox控件控制长度8位)
			var creditLimit = mchtInfoAdd_dataset.getValue("creditLimit");//信用卡限制标志(mchtMngAdd.dtst datasource="LIST:00,可用信用卡;01,禁用信用卡")
			//--------------------------------------------------------------------------------------------------------------------------------------------
			var mchtMngSel = mchtInfoAdd_dataset.getValue("mchtMngSel");//上级商户
			var mchtZipNo = mchtInfoAdd_dataset.getValue("mchtZipNo");//邮政编码
			var mchtOrgSel = mchtInfoAdd_dataset.getValue("mchtOrgSel");//所属机构
			var mchtAmrNo = mchtInfoAdd_dataset.getValue("mchtAmrNo");//业务员号
			var mchtAmrName = mchtInfoAdd_dataset.getValue("mchtAmrName");//业务员姓名
			
			var mchtMngScope = mchtInfoAdd_dataset.getValue("mchtMngScope");//经营范围
			var mchtRegAmt = mchtInfoAdd_dataset.getValue("mchtRegAmt");//注册资金
			var mchtTrcnNo = mchtInfoAdd_dataset.getValue("mchtTrcnNo");//税务登记证号码
			var mchtArtifPhone = mchtInfoAdd_dataset.getValue("mchtArtifPhone");//法定代表人电话
			var recvDeposit = mchtInfoAdd_dataset.getValue("recvDeposit");//应收保证金
			var paidDeposit = mchtInfoAdd_dataset.getValue("paidDeposit");//实收保证金	
			
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
				$.warn("【商户简称】长度错误（最大长度64位并且不能有空格）!");
				return false;
			} */
			if((mchtName == "") || (mchtName == null)){
				$.error("【商户全名】必须输入！");
				return false;
			}
			/* if(!isDesc64.test(mchtName)){
				$.warn("【商户全名】长度错误（最大长度64位并且不能有空格）!");
				return false;
			} */
			if((mchtPersonName == "") || (mchtPersonName == null)){
				$.error("【联系人】必须输入！");
				return false;
			}
			/* if(!isDesc32.test(mchtPersonName)){
				$.warn("【联系人】长度错误（最大长度32位）并且不能有空格!");
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
						$.warn("【经营范围】长度错误（最大长度42位）并且不能有空格!");
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
							  alert("【身份证号】填写有误"); 
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
		function mchtInfoAdd_dataset_mchtType_onSelect(value,record){
		 
			//当商户类型为"02-政府机构"、"03-事业单位"、"05-虚拟商户"、取消选择，不显示资质信息

			showQlfOrNotAdd(true);			
			initQlf();//初始化资质信息
			window.frames["uploadAdd0"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=0");
			window.frames["uploadAdd1"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=1");
			window.frames["uploadAdd2"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=2");
			window.frames["uploadAdd3"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=3");
			window.frames["uploadAdd4"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=4");
			window.frames["uploadAdd5"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=5");
			window.frames["uploadAdd6"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=6");
			window.frames["uploadAdd7"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=7");
			window.frames["uploadAdd8"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=8");
			window.frames["uploadAdd9"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=9");
			window.frames["uploadAdd10"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=10");
			window.frames["uploadAdd11"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=11");
			window.frames["uploadAdd12"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=12");
			window.frames["uploadAdd13"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=13");
			window.frames["uploadAdd14"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=14");
			window.frames["uploadAdd18"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=18");
			window.frames["uploadAdd19"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=19");
			window.frames["uploadAdd21"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=21");
			window.frames["uploadAdd22"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=22");
			window.frames["uploadAdd23"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=23");
			window.frames["uploadAdd24"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=24");
			window.frames["uploadAdd26"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=26");
			window.frames["uploadAdd27"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=27");
			window.frames["uploadAdd28"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=28");
			window.frames["uploadAdd29"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=29");
			window.frames["uploadAdd30"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=30");
			window.frames["uploadAdd31"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=31");
			window.frames["uploadAdd32"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=32");
			window.frames["uploadAdd33"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=33");
			window.frames["uploadAdd34"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=34");
			window.frames["uploadAdd35"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=35");
			window.frames["uploadAdd36"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=36");
			window.frames["uploadAdd37"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=37");
			window.frames["uploadAdd38"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=38");
			window.frames["uploadAdd39"].location.replace("../../payPmp/pubTool/uploadImage.jsp?picNum=39");
		}
		
		
		/**提交前检验数据格式*/
		 function btnAddSub_onClickCheck(button,commit){	
			//校验【基本信息】和【资质信息】
			if(!validInfo()){
				return false;
			}else{
				validInfo();
			}
			
					
			//获取需校验字段【图片信息】
			var picId0 = window.frames["uploadAdd0"].document.getElementById("lgPic").value;//图片名【商户logo】
			var picId1 = window.frames["uploadAdd1"].document.getElementById("lgPic").value;//图片名【营业执照】
			var picId2 = window.frames["uploadAdd2"].document.getElementById("lgPic").value;//图片名【法定代表人证件正面】
			var picId3 = window.frames["uploadAdd3"].document.getElementById("lgPic").value;//图片名【组织机构】
			var picId4 = window.frames["uploadAdd4"].document.getElementById("lgPic").value;//图片名【ICP许可】
			var picId5 = window.frames["uploadAdd5"].document.getElementById("lgPic").value;//图片名【税务许可】
			var picId6 = window.frames["uploadAdd6"].document.getElementById("lgPic").value;//图片名【商户门店】
			var picId7 = window.frames["uploadAdd7"].document.getElementById("lgPic").value;//图片名【法定代表人证件反面】
			var picId8 = window.frames["uploadAdd8"].document.getElementById("lgPic").value;//图片名【银行卡正面照】
			var picId9 = window.frames["uploadAdd9"].document.getElementById("lgPic").value;//图片名【店铺门头照(含有招牌)】
			var picId10 = window.frames["uploadAdd10"].document.getElementById("lgPic").value;//图片名【店铺全景照(店内环境)】
			var picId11 = window.frames["uploadAdd11"].document.getElementById("lgPic").value;//图片名【收银台照】
			var picId12 = window.frames["uploadAdd12"].document.getElementById("lgPic").value;//图片名【商品照】
			var picId13 = window.frames["uploadAdd13"].document.getElementById("lgPic").value;//图片名【开户许可证】
			var picId14 = window.frames["uploadAdd14"].document.getElementById("lgPic").value;//图片名【法定代表人手持身份证】
			var picId18 = window.frames["uploadAdd18"].document.getElementById("lgPic").value;//图片名【收款人证件正面】
			var picId19 = window.frames["uploadAdd19"].document.getElementById("lgPic").value;//图片名【收款人证件反面】
			var picId21 = window.frames["uploadAdd21"].document.getElementById("lgPic").value;//图片名【租赁合同(或产权证明书)】
			var picId22 = window.frames["uploadAdd22"].document.getElementById("lgPic").value;//图片名【合同照片】
			var picId23 = window.frames["uploadAdd23"].document.getElementById("lgPic").value;//图片名【授权证明(委托收款通知书)】
			var picId24 = window.frames["uploadAdd24"].document.getElementById("lgPic").value;//图片名【收款人手持身份证照】
			var picId26 = window.frames["uploadAdd26"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名身份证正面照】
			var picId27 = window.frames["uploadAdd27"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名身份证反面照】
			var picId28 = window.frames["uploadAdd28"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名手持身份证照】
			var picId29 = window.frames["uploadAdd29"].document.getElementById("lgPic").value;//图片名【租赁合同承租人同名银行卡照片】
			var picId30 = window.frames["uploadAdd30"].document.getElementById("lgPic").value;//图片名【补充资料1】
			var picId31 = window.frames["uploadAdd31"].document.getElementById("lgPic").value;//图片名【补充资料2】
			var picId32 = window.frames["uploadAdd32"].document.getElementById("lgPic").value;//图片名【补充资料3】
			var picId33 = window.frames["uploadAdd33"].document.getElementById("lgPic").value;//图片名【补充资料4】
			var picId34 = window.frames["uploadAdd34"].document.getElementById("lgPic").value;//图片名【补充资料5】
			var picId35 = window.frames["uploadAdd35"].document.getElementById("lgPic").value;//图片名【补充资料6】
			var picId36 = window.frames["uploadAdd36"].document.getElementById("lgPic").value;//图片名【补充资料7】
			var picId37 = window.frames["uploadAdd37"].document.getElementById("lgPic").value;//图片名【补充资料8】
			var picId38 = window.frames["uploadAdd38"].document.getElementById("lgPic").value;//图片名【补充资料9】
			var picId39 = window.frames["uploadAdd39"].document.getElementById("lgPic").value;//图片名【补充资料10】

			//组装图片名信息
			mchtInfoAdd_dataset.setValue("picId0","00" + picId0);
			mchtInfoAdd_dataset.setValue("picId1","01" + picId1);
			mchtInfoAdd_dataset.setValue("picId2","02" + picId2);
			mchtInfoAdd_dataset.setValue("picId3","03" + picId3);
			mchtInfoAdd_dataset.setValue("picId4","04" + picId4);
			mchtInfoAdd_dataset.setValue("picId5","05" + picId5);
			mchtInfoAdd_dataset.setValue("picId6","06" + picId6);
			mchtInfoAdd_dataset.setValue("picId7","07" + picId7);
			mchtInfoAdd_dataset.setValue("picId8","08" + picId8);
			mchtInfoAdd_dataset.setValue("picId9","09" + picId9);
			mchtInfoAdd_dataset.setValue("picId10","10" + picId10);
			mchtInfoAdd_dataset.setValue("picId11","11" + picId11);
			mchtInfoAdd_dataset.setValue("picId12","12" + picId12);
			mchtInfoAdd_dataset.setValue("picId13","13" + picId13);
			mchtInfoAdd_dataset.setValue("picId14","14" + picId14);
			mchtInfoAdd_dataset.setValue("picId18","18" + picId18);
			mchtInfoAdd_dataset.setValue("picId19","19" + picId19);
			mchtInfoAdd_dataset.setValue("picId21","21" + picId21);
			mchtInfoAdd_dataset.setValue("picId22","22" + picId22);
			mchtInfoAdd_dataset.setValue("picId23","23" + picId23);
			mchtInfoAdd_dataset.setValue("picId24","24" + picId24);
			mchtInfoAdd_dataset.setValue("picId26","26" + picId26);
			mchtInfoAdd_dataset.setValue("picId27","27" + picId27);
			mchtInfoAdd_dataset.setValue("picId28","28" + picId28);
			mchtInfoAdd_dataset.setValue("picId29","29" + picId29);
			mchtInfoAdd_dataset.setValue("picId30","30" + picId30);
			mchtInfoAdd_dataset.setValue("picId31","31" + picId31);
			mchtInfoAdd_dataset.setValue("picId32","32" + picId32);
			mchtInfoAdd_dataset.setValue("picId33","33" + picId33);
			mchtInfoAdd_dataset.setValue("picId34","34" + picId34);
			mchtInfoAdd_dataset.setValue("picId35","35" + picId35);
			mchtInfoAdd_dataset.setValue("picId36","36" + picId36);
			mchtInfoAdd_dataset.setValue("picId37","37" + picId37);
			mchtInfoAdd_dataset.setValue("picId38","38" + picId38);
			mchtInfoAdd_dataset.setValue("picId39","39" + picId39);
			
			btnAddSub.setDisabled(true);// 点击后八秒内不能再次提交
			var timer = setInterval(function(){//开启定时器
				btnAddSub.setDisabled(false);
				clearInterval(timer);    //清除定时器
			},8000);
			return true;
		} 
		
		function doSave(){
			btnAddSub.click();
		 }
		/**提交*/
		function btnAddSub_postSubmit(){
			$.success("操作成功！",function(){
				parent.saveMngAddReCallback();
			});
		}

		function importFile_onButtonClick(i,win,framewin){
			if(i==0){//取消按钮
				win.close();
				mchtInfoAdd_dataset.flushData(mchtInfoAdd_dataset.pageIndex);	
			}else{
					framewin.excuteImport();
					mchtInfoAdd_dataset.flushData(mchtInfoAdd_dataset.pageIndex);	
					iscallback = true;
				}
			}
		
		function mchtInfoAdd_dataset_afterChange(dataset,field){
			if(field.fieldName=="mchtArtifId"){
				var mchtArtifType=mchtInfoAdd_dataset.getValue("mchtArtifType");
				var mchtArtifId = mchtInfoAdd_dataset.getValue("mchtArtifId");
				if("00"==mchtArtifType){
					if(!regIdNo.test(mchtArtifId)){ 
						$.warn("【身份证号】填写有误"); 
						return false; 
					}
				}
				
			}
			
			if(field.fieldName=="mchtType"){
				var mchtType = mchtInfoAdd_dataset.getValue("mchtType");
				if('06'==mchtType){
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifName", false);//法定代表人姓名
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifPhone", false);//法定代表人电话
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifType", false);//法定代表人证件类型
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifId", false);//法定代表人证件号码
					mchtInfoAdd_dataset.setFieldRequired("artifSdate", false);//法定代表人证件生效日期
					mchtInfoAdd_dataset.setFieldRequired("artifEdate", false);//法定代表人证件失效日期

					PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifSex", false);//法定代表人性别
					PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifJob", false);//法定代表人职业
					PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifAddress", false);//法定代表人住址
					PbsMchtAssistInfoTmp_dataset.setFieldRequired("accountIdType", false);//法定代表人亲属证件类型
					PbsMchtAssistInfoTmp_dataset.setFieldRequired("accountIdNo", false);//法定代表人证件亲属证件号码
					PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifCountryId", false);//法定代表人证件国别/地区
				}else{
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifName", true);//法定代表人姓名
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifType", true);//法定代表人证件类型
					mchtInfoAdd_dataset.setFieldRequired("mchtArtifId", true);//法定代表人证件号码
					mchtInfoAdd_dataset.setFieldRequired("artifSdate", true);//法定代表人证件生效日期
					mchtInfoAdd_dataset.setFieldRequired("artifEdate", true);//法定代表人证件失效日期

					PbsMchtAssistInfoTmp_dataset.setFieldRequired("mchtArtifSex", true);//法定代表人性别
				}
			}
				
		}
		
	</script>
</snow:page>