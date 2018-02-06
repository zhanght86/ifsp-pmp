<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="通道商户登记">
	<!-- 通道商户登记数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyMchtMng"
		id="pagyMchtMng" init="true" submitMode="current"></snow:dataset>
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyMchtMng"
		id="pagyMchtMngTab" init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query dataset="pagyMchtMng" label="查询条件" collapsible="false"
		fieldstr="qchlId,qaplStat,qpayMchtNo,qmchtName,qpagyNo,qmchtNo"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="pagyMchtMng" height="99%" label="通道商户信息" id="gridId"
		fitcolumns="true"
		fieldstr="chlName[130],payMchtNo,payMchtName[150],pagyName[110],mainMchtNo,mchtNo,aplStat[110],aplFailedRes,aplDate[120]" 
		paginationbar="btnOnlinePA,btnOnlineStandard,btnDetailApl,btnManApl,btnMchtNo"></snow:grid>
	
	<!-- 手工申请弹窗 -->
	<snow:window id="windowManApl" closable="true" width="800"
		title="手工申请" modal="true" buttons="btnSubmitManApl">
		<snow:form id="formMchtMan" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="aplType,combogrId,payMchtNo,payMchtName"
			collapsible="false" colnum="4" label="商户信息"></snow:form>
		<snow:button id="btnSubmitManApl" dataset="pagyMchtMng" hidden="true"></snow:button>
	</snow:window>
	<!-- 通道商户号录入弹窗 -->
	<snow:window id="winMchtNo" closable="true" width="800"
		title="通道商户号录入" modal="true" buttons="btnSubmitMchtNo">
		<snow:form id="formMchtMan" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="aplType,chlId,payMchtNo,payMchtName"
			collapsible="false" colnum="4" label="渠道商户信息"></snow:form>
		<snow:form id="formMchtNo" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="pagyName,mainMchtNo,mchtNo,settlementMark"
			collapsible="false" colnum="4" label="通道商户信息"></snow:form>
		<snow:form id="formMd5Add" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="encryptType,md5Passwd"
			collapsible="false" colnum="4" label="证书信息"></snow:form>			
		<snow:button id="btnSubmitMchtNo" dataset="pagyMchtMng" hidden="true"></snow:button>
	</snow:window>
		
		<!-- 平安银行联机申请 -->
	<snow:window id="windowOnlinePA" closable="true" width="800"
		title="联机申请" modal="true" buttons="btnSubmitManPA">
		<snow:form id="formMchtManPA" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="payMchtNo,payMchtName"
			collapsible="false" colnum="4" label="申请信息"></snow:form>	
		<snow:form id="formMchtNoPA" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,address,addressCode,weixinCategory,weixinFee,aliCategory,aliFee,subAppid,subscribeAppid,wxJsapiPath"
			collapsible="false" colnum="4" label="申请信息完善"></snow:form>
		<snow:button id="btnSubmitManPA" dataset="pagyMchtMng" hidden="true"></snow:button>
	</snow:window>
	
				<!-- 平安银行标准进件 -->
	<snow:window id="windowOnlineStandard" closable="true" width="800"
		title="联机申请" modal="true" buttons="btnSubmitManStandard">
		<snow:form id="formMchtManStandard" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="payMchtNo,payMchtName"
			collapsible="false" colnum="4" label="申请信息"></snow:form>	
		<snow:form id="formMchtNoStandard" dataset="pagyMchtMng" border="true" labelwidth="95" 
			fieldstr="mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,address,addressCode,weixinCategory,weixinFee,aliCategory,aliFee,subAppid,subscribeAppid,wxJsapiPath"
			collapsible="false" colnum="4" label="申请信息完善"></snow:form>
		<snow:button id="btnSubmitManStandard" dataset="pagyMchtMng" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 联机申请弹窗 -->
	<snow:window id="windowOnlineApl" closable="true" width="800" title="联机申请" buttons="btnSubmitOnlineApl" modal="true">
		<snow:form id="formMchtOnlineApl" dataset="pagyMchtMng" border="true" labelwidth="95"
			fieldstr="aplType,chlName,payMchtNo,payMchtName,aplStat"
			collapsible="false" colnum="4" label="商户信息"></snow:form>
		<snow:form id="formOnlineApl" dataset="pagyMchtMng" border="true" labelwidth="95"
			fieldstr="pagyName" collapsible="false" colnum="4" label="通道上级信息"></snow:form>
		<snow:tabs id="tabsOnlineApl" showswitch="false" border="true" height="250">
			<snow:tab  id="online301" title="银联全渠道" closable="false">
			<!-- 2017-03-28新增  房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次 -->
			<snow:form id="formOnlineApl301" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="MCC组别{mchtMccYl}[3],MCC类型{mchtMccSubYl}[3],mchtName,mchtNameAbbr"
			collapsible="false" colnum="4" label="银联全渠道"></snow:form>
<%-- 				<snow:form id="formOnlineApl301" dataset="pagyMchtMngTab" border="false" labelwidth="95" --%>
<%-- 			fieldstr="MCC组别{mchtMccYl}[3],MCC类型{mchtMccSubYl}[3],speBillType,speBillGrade,mchtName,mchtNameAbbr" --%>
<%-- 			collapsible="false" colnum="4" label="银联全渠道"></snow:form>	 --%>
			<snow:form id="formOnlineAreaSel" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="mchtAreaSel" collapsible="false" colnum="4"></snow:form>			
			</snow:tab>
			<snow:tab  id="online302" title="微信" closable="false">
				<snow:form id="formOnlineApl302" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,mchtMccWx,mchtMccSubWx,mchRamak"
			collapsible="false" colnum="4" label="微信"></snow:form>		
			</snow:tab>
			<snow:tab  id="online304" title="支付宝" closable="false">
				<snow:form id="formOnlineApl304" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="商户号{zfbMchtNo},mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,mchtMccZfb,mchtMccSubZfb"
			collapsible="false" colnum="4" label="支付宝"></snow:form>		
			</snow:tab>	
			<snow:tab  id="online303" title="本行通道" closable="false">
			<!-- 2017-03-28新增  房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次 -->
			<snow:form id="formOnlineApl303" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="MCC组别{mchtMccYl}[3],MCC类型{mchtMccSubYl}[3],mchtName,mchtNameAbbr"
			collapsible="false" colnum="4" label="本行通道"></snow:form>
<%-- 				<snow:form id="formOnlineApl303" dataset="pagyMchtMngTab" border="false" labelwidth="95" --%>
<%-- 			fieldstr="MCC组别{mchtMccYl}[3],MCC类型{mchtMccSubYl}[3],speBillType,speBillGrade,mchtName,mchtNameAbbr" --%>
<%-- 			collapsible="false" colnum="4" label="本行通道"></snow:form>	 --%>
			<snow:form id="formOnlineAreaSel" dataset="pagyMchtMngTab" border="false" labelwidth="95"
			fieldstr="mchtAreaSel" collapsible="false" colnum="4"></snow:form>	
			</snow:tab>							
		</snow:tabs>
		<snow:button id="btnSubmitOnlineApl" dataset="pagyMchtMng" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 详情查看弹窗 -->
	<snow:window id="windowDetail" closable="true" width="800" title="详情查看" modal="true">
		<snow:form id="formMchtDetail" dataset="pagyMchtMng" border="true" labelwidth="95"
			fieldstr="aplType,chlName,payMchtNo,payMchtName,aplDate,aplStat"
			collapsible="false" colnum="4" label="商户信息"></snow:form>
		<snow:form id="formDetail" dataset="pagyMchtMng" border="true" labelwidth="95"
			fieldstr="pagyName,mainMchtNo,mchtNo,settlementMark,encryptType,md5Passwd" collapsible="false" colnum="4" label="通道上级信息"></snow:form>
				<snow:form id="formDetail" dataset="pagyMchtMng" border="true" labelwidth="95"
				fieldstr="mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,address,addressCode,weixinCategory,weixinFee,aliCategory,aliFee,subAppid,subscribeAppid,wxJsapiPath,openId,openKey,pagyRespMsg,tpamCttsStatusWx,tpamCttsStatusApi,tpamMchtStatus,tpamStoreStatus"
			 collapsible="false" colnum="4" label="通道申请信息"></snow:form>	
		<snow:tabs id="tabsDetail" showswitch="false" border="true" height="250">
			<snow:tab  id="detail301" title="银联全渠道" closable="false">
			<!-- 2017-03-28新增  房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次 -->
			<snow:form id="formDetail301" dataset="pagyMchtMng" border="false" labelwidth="95"
			fieldstr="MCC码{mchtMccCode},mchtArea,mchtNo,mchtName,mchtNameAbbr"
			collapsible="false" colnum="4" label="银联全渠道"></snow:form>	
<%-- 				<snow:form id="formDetail301" dataset="pagyMchtMng" border="false" labelwidth="95" --%>
<%-- 			fieldstr="MCC码{mchtMccCode},mchtArea,mchtNo,mchtName,mchtNameAbbr,speBillType,speBillGrade" --%>
<%-- 			collapsible="false" colnum="4" label="银联全渠道"></snow:form>		 --%>
			</snow:tab>
			<snow:tab  id="detail302" title="微信" closable="false">
				<snow:form id="formDetail302" dataset="pagyMchtMng" border="false" labelwidth="95"
			fieldstr="mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,类目编号{mchtMccCode},mchRamak"
			collapsible="false" colnum="4" label="微信"></snow:form>		
			</snow:tab>	
			<snow:tab  id="detail304" title="支付宝" closable="false">
				<snow:form id="formDetail304" dataset="pagyMchtMng" border="false" labelwidth="95"
			fieldstr="商户号{zfbMchtNo},mchtName,mchtNameAbbr,mchtSerPhone,mchtContact,mchtContactPhone,mchtContactEmail,行业编号{mchtMccCode}"
			collapsible="false" colnum="4" label="支付宝"></snow:form>		
			</snow:tab>	
			<snow:tab  id="detail303" title="本行通道" closable="false">
			<!-- 2017-03-28新增  房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次 -->
			<snow:form id="formDetail303" dataset="pagyMchtMng" border="false" labelwidth="95"
			fieldstr="MCC码{mchtMccCode},mchtArea,mchtNo,mchtName,mchtNameAbbr"
			collapsible="false" colnum="4" label="本行通道"></snow:form>
<%-- 				<snow:form id="formDetail303" dataset="pagyMchtMng" border="false" labelwidth="95" --%>
<%-- 			fieldstr="MCC码{mchtMccCode},mchtArea,mchtNo,mchtName,mchtNameAbbr,speBillType,speBillGrade" --%>
<%-- 			collapsible="false" colnum="4" label="本行通道"></snow:form>		 --%>
			</snow:tab>								
		</snow:tabs>
	</snow:window>



<script>
/**************************公有部分*************************************/
	/**验证长度*/
	var isDesc21 = /^\S{1,21}$/;//最大长度21位 
	var isDesc24 = /^\S{1,24}$/;//最大长度24位 
	var isDesc120 = /^\S{1,120}$/;//最大长度120位 
	/**验证客服电话*/
	var isPhone = /^(\d{3,4}-)?\d{6,8}$/;
	/**验证联系电话*/
	var isCellPhone = /[1]+\d{10}$/;	
	/**验证邮箱*/
	//var isEmail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;	
	var isEmail= /^[a-zA-Z0-9]([a-zA-Z0-9\.]*)@([a-zA-Z0-9]*)\.([a-zA-Z]{2,3})$/;
        
	//字段校验
	function validParam(){
		//字段非空，长度验证
		if (pagyMchtMng_dataset.validate() == false) {
			return false;
		}
		if (pagyMchtMngTab_dataset.validate() == false) {
			return false;
		}
		if(!(pagyMchtMngTab_dataset.getValue("payMchtName") == null || pagyMchtMngTab_dataset.getValue("payMchtName") == "")){
			if(!isDesc21.test(pagyMchtMngTab_dataset.getValue("payMchtName"))){
				$.warn("【渠道商户名】长度错误（最大长度21位）");
				return false;
			}
		}
		var mchtName = pagyMchtMngTab_dataset.getValue("mchtName");
		if(! (mchtName == null || mchtName == "")){
			if(mchtName.length > 40){
				$.warn("【商户全名】长度错误（40个汉字）");
				return false;
			}
		}	
		var mchtNameAbbr = pagyMchtMngTab_dataset.getValue("mchtNameAbbr");
		if(!(mchtNameAbbr == null || mchtNameAbbr == "")){
			if(mchtNameAbbr.length > 40){
				$.warn("【商户简称】长度错误（40个汉字）");
				return false;
			}
		}
	
		//获取申请渠道
		var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");
		//获取商户简称
		var mchtNameAbbr = pagyMchtMngTab_dataset.getValue("mchtNameAbbr");
		if (mchtNameAbbr == null || mchtNameAbbr == '') {
			$.warn("请输入商户简称");
			return false;
		}
		if ('302' == pagyNo || '304' == pagyNo) {
			//验证客服电话格式
			var mchtSerPhone = pagyMchtMngTab_dataset.getValue("mchtSerPhone");
			if (!(isPhone.test(mchtSerPhone) || (isCellPhone.test(mchtSerPhone)))) {
				$.warn("【客服电话】格式错误，格式为（3或4位数字 + '-'）【区号】+ 6到8位数字【座机电话】，【区号】可以不输入，同时也支持11位手机号");
				return false;
			}
			//验证联系电话格式
			var mchtContactPhone = pagyMchtMngTab_dataset.getValue("mchtContactPhone");
			if (!(isPhone.test(mchtContactPhone) || (isCellPhone.test(mchtContactPhone)))){
				$.warn("【联系电话】格式错误，格式为（3或4位数字 + '-'）【区号】+ 6到8位数字【座机电话】，【区号】可以不输入，同时也支持11位手机号");
				return false;
			}
			//如果联系邮箱非空，则验证格式，为空，则不做验证
			var mchtContactEmail = pagyMchtMngTab_dataset.getValue("mchtContactEmail");
			if (!isEmail.test(mchtContactEmail)) {
				$.warn("【联系邮箱】格式错误");
				return false;
			}			
		}
		if ('301' == pagyNo) {
			//获取MCC组别
			var mchtMccYl = pagyMchtMngTab_dataset.getValue("mchtMccYl");
			if (mchtMccYl == null || mchtMccYl == "") {
				$.warn("请选择MCC组别");
				return false;
			}
		}
		if ('303' == pagyNo) {
			//获取MCC组别
			var mchtMccYl = pagyMchtMngTab_dataset.getValue("mchtMccYl");
			if (mchtMccYl == null || mchtMccYl == "") {
				$.warn("请选择MCC组别");
				return false;
			}
		}
		return true;
	}
	
	function setFieldRequired(required, requiredwx, requiredzfb, requiredyl) {
		pagyMchtMngTab_dataset.setFieldRequired("mchtNameAbbr", required);
		pagyMchtMngTab_dataset.setFieldRequired("mchtName", required);
		pagyMchtMngTab_dataset.setFieldRequired("mchtSerPhone", required);
		pagyMchtMngTab_dataset.setFieldRequired("mchRamak", requiredwx);		
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccWx", requiredwx);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubWx", requiredwx);		
		pagyMchtMngTab_dataset.setFieldRequired("mchtContact", required);
		pagyMchtMngTab_dataset.setFieldRequired("mchtContactPhone", required);
		pagyMchtMngTab_dataset.setFieldRequired("mchtContactEmail", required);
		pagyMchtMngTab_dataset.setFieldRequired("zfbMchtNo", requiredzfb);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccZfb", requiredzfb);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubZfb", requiredzfb);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccYl", requiredyl);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubYl", requiredyl);
		pagyMchtMngTab_dataset.setFieldRequired("mchtNameAbbr", requiredyl);
		//pagyMchtMngTab_dataset.setFieldRequired("mchtName", requiredyl);
	}
	
	function setFieldUnRequired(){	
		pagyMchtMng_dataset.setFieldRequired("aplType", false);
		pagyMchtMng_dataset.setFieldRequired("pagyName", false);
		pagyMchtMng_dataset.setFieldRequired("chlName", false);
		pagyMchtMng_dataset.setFieldRequired("aplStat", false);
		pagyMchtMng_dataset.setFieldRequired("payMchtNo", false);
		pagyMchtMng_dataset.setFieldRequired("payMchtName", false);

		pagyMchtMngTab_dataset.setFieldRequired("mchtMccYlNm", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubYlNm", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccYl", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubYl", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtNameAbbr", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtName", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtSerPhone", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtContact", false);			
		pagyMchtMngTab_dataset.setFieldRequired("mchtContactPhone", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtContactEmail", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccWx", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubWx", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccWxNm", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubWxNm", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchRamak", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtAreaSel", false);
		pagyMchtMngTab_dataset.setFieldRequired("zfbMchtNo", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccZfb", false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtMccSubZfb", false);
		
		pagyMchtMng_dataset.setFieldRequired("mainMchtNo", false);//主商户编号
		pagyMchtMng_dataset.setFieldRequired("aplDate", false);//申请日期		
	}
	
	//申请类型选择0-渠道申请时，渠道商户号、渠道商户名隐藏
	function pagyMchtMng_dataset_aplType_onSelect(v) {
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
		pagyMchtMng_dataset.setFieldRequired("payMchtNo", false);
		pagyMchtMng_dataset.setFieldRequired("payMchtName", false);
		if(v == ""){
			pagyMchtMng_dataset.setValue("payMchtNo", "");
			pagyMchtMng_dataset.setValue("payMchtName", "");
		} else if (v == '1') {			
			pagyMchtMng_dataset.setValue("payMchtNo", "");
			pagyMchtMng_dataset.setValue("payMchtName", "");
		} else if (v == "2"){
			pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", false);
			pagyMchtMng_dataset.setFieldReadOnly("payMchtName", false);
			pagyMchtMng_dataset.setFieldRequired("payMchtNo", true);
			pagyMchtMng_dataset.setFieldRequired("payMchtName", true);
		}
	}

	function pagyMchtMng_dataset_pagyNo_onSelect(v, record) {
		var oldVal = pagyMchtMng_dataset.getValue("pagyNo");
		if(record != null){
			var newVal = record.getValue("pagyNo");	
		}
		if (oldVal == newVal) {
			return true;
		}
		pagyMchtMngTab_dataset.clearData();
		if (newVal == null || "" == newVal) {//清除选择	
			$("#tabs_tabsManApl").css("display", "none");
			$("li[tabid='301']").css("display", "none");
			$("li[tabid='302']").css("display", "none");
			$("li[tabid='303']").css("display", "none");
			$("li[tabid='304']").css("display", "none");
			$("#301").css("display", "none");
			$("#302").css("display", "none");
			$("#303").css("display", "none");
			$("#304").css("display", "none");
			setFieldRequired(false, false, false, false);
		}else if (newVal == '301') {//银联全渠道		
			$("#tabs_tabsManApl").css("display", "block");
			$("li[tabid='301']").css("display", "block");
			$("li[tabid='302']").css("display", "none");
			$("li[tabid='303']").css("display", "none");
			$("li[tabid='304']").css("display", "none");
			$("#301").css("display", "block");
			$("#302").css("display", "none");
			$("#303").css("display", "none");
			$("#304").css("display", "none");
			setFieldRequired(false, false, false, true);
		} else if (newVal == '302') {//微信通道
			$("#tabs_tabsManApl").css("display", "block");
			$("li[tabid='301']").css("display", "none");
			$("li[tabid='303']").css("display", "none");
			$("li[tabid='302']").css("display", "block");
			$("li[tabid='304']").css("display", "none");
			$("#301").css("display", "none");
			$("#303").css("display", "none");
			$("#302").css("display", "block");
			$("#304").css("display", "none");
			setFieldRequired(true, true, false, false);
		} else if (newVal == '304') {//支付宝通道
			$("#tabs_tabsManApl").css("display", "block");
			$("li[tabid='301']").css("display", "none");
			$("li[tabid='302']").css("display", "none");
			$("li[tabid='303']").css("display", "none");
			$("li[tabid='304']").css("display", "block");
			$("#301").css("display", "none");
			$("#302").css("display", "none");
			$("#303").css("display", "none");
			$("#304").css("display", "block");
			setFieldRequired(true, false, true, false);
		} else if (newVal == '303') {//本行通道
			$("#tabs_tabsManApl").css("display", "block");
			$("li[tabid='301']").css("display", "none");
			$("li[tabid='302']").css("display", "none");
			$("li[tabid='303']").css("display", "block");
			$("li[tabid='304']").css("display", "none");
			$("#301").css("display", "none");
			$("#302").css("display", "none");
			$("#303").css("display", "block");
			$("#304").css("display", "none");
			setFieldRequired(true, false, true, false);
		} else {		
			$("#tabs_tabsManApl").css("display", "none");
			$("li[tabid='301']").css("display", "none");
			$("li[tabid='302']").css("display", "none");
			$("li[tabid='304']").css("display", "none");
			$("#301").css("display", "none");
			$("#302").css("display", "none");
			$("#304").css("display", "none");
			setFieldRequired(false, false, false, false);
			//清空【申请通道】
			pagyMchtMng_dataset.setValue("pagyNoName","");
			pagyMchtMng_dataset.setValue("pagyNo","");
			$.warn("不支持该通道的申请.");		
			return false;
		}
		return true;
	}

	/****微信下拉列表****/
	function pagyMchtMngTab_dataset_mchtMccWx_beforeOpen(dropdown, dpds) {
		dpds.setParameter("quserCode", "01");
		dpds.setParameter("qcategoryLevel", "1");
		mchtMccWx_DropDown.cache = false;
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccWx_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			var oldVal = pagyMchtMngTab_dataset.getValue("mchtMccWx");
			var newVal = record.getValue("categoryCode");
			if (oldVal != newVal) {
				pagyMchtMngTab_dataset.setValue("mchtMccSubWx", "");
				pagyMchtMngTab_dataset.setValue("mchtMccSubWxName", "");
			}
			pagyMchtMngTab_dataset.setValue("mchtMccCode", newVal);
			pagyMchtMngTab_dataset.setValue("mchtMccName", record
					.getValue("categoryDesc"));
		}else{
			pagyMchtMngTab_dataset.setValue("mchtMccSubWx", "");
			pagyMchtMngTab_dataset.setValue("mchtMccSubWxName", "");
		}		
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccSubWx_beforeOpen(dropdown, dpds) {
		if((dpds != null) && (dpds != "")){
			var mchtMccWx = pagyMchtMngTab_dataset.getValue("mchtMccWx");
			if ((mchtMccWx == null) || (mchtMccWx == "")) {
				$.warn("请先选择一级类目.");
				return false;
			}
			dpds.setParameter("quserCode", "01");
			dpds.setParameter("qcategoryLevel", "2");
			dpds.setParameter("qpCategoryCode", mchtMccWx);
			mchtMccSubWx_DropDown.cache = false;
		}		
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccSubWx_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			pagyMchtMngTab_dataset.setValue("mchtMccSubCode", record
					.getValue("categoryCode"));
			pagyMchtMngTab_dataset.setValue("mchtMccSubName", record
					.getValue("categoryDesc"));
		}		
		return true;
	}
	
	/****支付宝下拉列表****/
	function pagyMchtMngTab_dataset_mchtMccZfb_beforeOpen(dropdown, dpds) {
		dpds.setParameter("quserCode", "02");
		dpds.setParameter("qcategoryLevel", "1");
		mchtMccZfb_DropDown.cache = false;
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccZfb_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			var oldVal = pagyMchtMngTab_dataset.getValue("mchtMccZfb");
			var newVal = record.getValue("categoryCode");
			if (oldVal != newVal) {
				pagyMchtMngTab_dataset.setValue("mchtMccSubZfb", "");
				pagyMchtMngTab_dataset.setValue("mchtMccSubZfbName", "");
			}
			pagyMchtMngTab_dataset.setValue("mchtMccCode", newVal);
			pagyMchtMngTab_dataset.setValue("mchtMccName", record
					.getValue("categoryDesc"));
		}else{
			pagyMchtMngTab_dataset.setValue("mchtMccSubZfb", "");
			pagyMchtMngTab_dataset.setValue("mchtMccSubZfbName", "");
		}		
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccSubZfb_beforeOpen(dropdown, dpds) {
		if((dpds != null) && (dpds != "")){
			var mchtMccZfb = pagyMchtMngTab_dataset.getValue("mchtMccZfb");
			if ((mchtMccZfb == null) || (mchtMccZfb == "")) {
				$.warn("请先选择一级行业.");
				return false;
			}
			dpds.setParameter("quserCode", "02");
			dpds.setParameter("qcategoryLevel", "2");
			dpds.setParameter("qpCategoryCode", mchtMccZfb);
			mchtMccSubZfb_DropDown.cache = false;
		}		
		return true;
	}
	
	function pagyMchtMngTab_dataset_mchtMccSubZfb_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			pagyMchtMngTab_dataset.setValue("mchtMccSubCode", record
					.getValue("categoryCode"));
			pagyMchtMngTab_dataset.setValue("mchtMccSubName", record
					.getValue("categoryDesc"));
		}		
		return true;
	}
	
	/****银联下拉列表****/
	function pagyMchtMngTab_dataset_mchtMccYl_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			var oldVal = pagyMchtMngTab_dataset.getValue("mchtMccYl");
			var newVal = record.getValue("mchtGrpNo");
			if (oldVal != newVal) {
				pagyMchtMngTab_dataset.setValue("mchtMccSubYl", "");
				pagyMchtMngTab_dataset.setValue("mchtMccSubYlName", "");
			}
			pagyMchtMngTab_dataset.setValue("mchtMccCode", newVal);
			pagyMchtMngTab_dataset.setValue("mchtMccName", record
					.getValue("grpDesc"));
		}else{
			pagyMchtMngTab_dataset.setValue("mchtMccSubYl", "");
			pagyMchtMngTab_dataset.setValue("mchtMccSubYlName", "");
		}		
		return true;
	}
	function pagyMchtMngTab_dataset_mchtMccSubYl_beforeOpen(dropdown, dpds) {
		if((dpds != null) && (dpds != "")){
			var mchtMccYl = pagyMchtMngTab_dataset.getValue("mchtMccYl");
			if ((mchtMccYl == null) || (mchtMccYl == "")) {
				$.warn("请先选择商户组别.");
				return false;
			}
			dpds.setParameter("qmchtGrpId", mchtMccYl);
			mchtMccSubYl_DropDown.cache = false;
		}		
		return true;
	}
	function pagyMchtMngTab_dataset_mchtMccSubYl_onSelect(dropdown, record) {
		if((record != null) && (record != "")){
			pagyMchtMngTab_dataset.setValue("mchtMccSubCode", record
					.getValue("mccId"));
			pagyMchtMngTab_dataset.setValue("mchtMccSubName", record
					.getValue("mccDesc"));
		}		
		return true;
	}

//	function pagyMchtMng_dataset_afterScroll(ds, record) {
//		if (record) {
//			if (record.getValue("aplStat") == '00'
//					|| record.getValue("aplStat") == '03') {
//				btnOnlineApl.setDisabled(false);
//			} else {
//				btnOnlineApl.setDisabled(true);
//			}
//		}
//	}
	
	function setDisplyDefault(useFlag) {
		$("#tabs_tabsOnlineApl").css("display", "block");
		$("#tabs_tabsDetail").css("display", "block");
		if (pagyMchtMng_dataset.getValue("pagyNo") == '301') {//银联全渠道
			$("li[tabid='online301']").css("display", "block");
			$("li[tabid='online302']").css("display", "none");
			$("li[tabid='online303']").css("display", "none");
			$("li[tabid='online304']").css("display", "none");
			$("#online301").css("display", "block");
			$("#online302").css("display", "none");
			$("#online303").css("display", "none");
			$("#online304").css("display", "none");
			$("li[tabid='detail301']").css("display", "block");
			$("li[tabid='detail302']").css("display", "none");
			$("li[tabid='detail303']").css("display", "none");
			$("li[tabid='detail304']").css("display", "none");
			$("#detail301").css("display", "block");
			$("#detail302").css("display", "none");
			$("#detail303").css("display", "none");
			$("#detail304").css("display", "none");
		} else if (pagyMchtMng_dataset.getValue("pagyNo") == '302') {//微信通道
			$("li[tabid='online301']").css("display", "none");
			$("li[tabid='online303']").css("display", "none");
			$("li[tabid='online302']").css("display", "block");
			$("li[tabid='online304']").css("display", "none");
			$("#online301").css("display", "none");
			$("#online303").css("display", "none");
			$("#online302").css("display", "block");
			$("#online304").css("display", "none");
			$("li[tabid='detail301']").css("display", "none");
			$("li[tabid='detail303']").css("display", "none");
			$("li[tabid='detail302']").css("display", "block");
			$("li[tabid='detail304']").css("display", "none");
			$("#detail301").css("display", "none");
			$("#detail303").css("display", "none");
			$("#detail302").css("display", "block");
			$("#detail304").css("display", "none");
		} else if (pagyMchtMng_dataset.getValue("pagyNo") == '304') {//支付宝通道
			$("li[tabid='online301']").css("display", "none");
			$("li[tabid='online302']").css("display", "none");
			$("li[tabid='online303']").css("display", "none");
			$("li[tabid='online304']").css("display", "block");
			$("#online301").css("display", "none");
			$("#online302").css("display", "none");
			$("#online303").css("display", "none");
			$("#online304").css("display", "block");
			$("li[tabid='detail301']").css("display", "none");
			$("li[tabid='detail302']").css("display", "none");
			$("li[tabid='detail303']").css("display", "none");
			$("li[tabid='detail304']").css("display", "block");
			$("#detail301").css("display", "none");
			$("#detail302").css("display", "none");
			$("#detail303").css("display", "none");
			$("#detail304").css("display", "block");
		} else if (pagyMchtMng_dataset.getValue("pagyNo") == '303') {//本行通道
			$("li[tabid='online301']").css("display", "none");
			$("li[tabid='online302']").css("display", "none");
			$("li[tabid='online304']").css("display", "none");
			$("li[tabid='online303']").css("display", "block");
			$("#online301").css("display", "none");
			$("#online302").css("display", "none");
			$("#online304").css("display", "none");
			$("#online303").css("display", "block");
			$("li[tabid='detail301']").css("display", "none");
			$("li[tabid='detail302']").css("display", "none");
			$("li[tabid='detail304']").css("display", "none");
			$("li[tabid='detail303']").css("display", "block");
			$("#detail301").css("display", "none");
			$("#detail302").css("display", "none");
			$("#detail304").css("display", "none");
			$("#detail303").css("display", "block");
		} else {
			$("#tabs_tabsOnlineApl").css("display", "none");
			$("#tabs_tabsDetail").css("display", "none");
		}		
		
		var aplType = pagyMchtMng_dataset.getValue("aplType");//申请类型			
		//设置必输字段
		pagyMchtMng_dataset.setFieldRequired("aplType", true);
		pagyMchtMng_dataset.setFieldRequired("chlName", true);
		pagyMchtMng_dataset.setFieldRequired("pagyName", true);
		pagyMchtMng_dataset.setFieldRequired("aplStat", true);
		if(aplType == "2"){
			pagyMchtMng_dataset.setFieldRequired("payMchtNo", true);
			pagyMchtMng_dataset.setFieldRequired("payMchtName", true);
		}
		
		if(useFlag == "Detail"){
			pagyMchtMng_dataset.setFieldRequired("mainMchtNo", true);//主商户编号
			pagyMchtMng_dataset.setFieldRequired("aplDate", true);//申请日期
			var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");//申请通道编号
			pagyMchtMng_dataset.setFieldRequired("mchtNameAbbr", true);
			pagyMchtMng_dataset.setFieldRequired("mchtName", true);
			if(pagyNo == "301"){//银联全渠道			
				pagyMchtMng_dataset.setFieldRequired("mchtNo", true);
				pagyMchtMng_dataset.setFieldRequired("mchtMccCode", true);
				pagyMchtMng_dataset.setFieldRequired("mchtArea", true);
			}else if(pagyNo == "302"){//微信通道				
				pagyMchtMng_dataset.setFieldRequired("mchtSerPhone", true);
				pagyMchtMng_dataset.setFieldRequired("mchtContact", true);			
				pagyMchtMng_dataset.setFieldRequired("mchtContactPhone", true);
				pagyMchtMng_dataset.setFieldRequired("mchtContactEmail", true);
				pagyMchtMng_dataset.setFieldRequired("mchtMccCode", true);
				pagyMchtMng_dataset.setFieldRequired("mchRamak", true);
			}else if(pagyNo == "304"){//支付宝通道
				pagyMchtMng_dataset.setFieldRequired("zfbMchtNo", true);
				pagyMchtMng_dataset.setFieldRequired("mchtSerPhone", true);
				pagyMchtMng_dataset.setFieldRequired("mchtContact", true);			
				pagyMchtMng_dataset.setFieldRequired("mchtContactPhone", true);
				pagyMchtMng_dataset.setFieldRequired("mchtContactEmail", true);
				pagyMchtMng_dataset.setFieldRequired("mchtMccCode", true);				
			}
		}		
	}

/*****************************手工申请**********************************/
    //点击手工申请按钮时
	function btnManApl_onClick(){   
		// modify by lengjingyu 20180201  setReadOnly有缺陷，避免使用    没有jira
		pagyMchtMng_dataset.setFieldReadOnly("aplType",true);
		pagyMchtMng_dataset.setFieldReadOnly("combogrId",true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo",true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName",true);
		// modify end
		window_windowManApl.open();
	}
	
	
	//手工申请成功时
	function btnSubmitManApl_postSubmit() {
		$.success("申请成功，请点击【联机申请】或【通道商户号录入】补录信息!", function() {
			window_windowManApl.close();			
			setFieldUnRequired();			
		});
	}
	
	//窗口关闭时，取消当前记录
	function window_windowManApl_beforeClose(win){
		//清空数据集
		pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);
		pagyMchtMngTab_dataset.flushData(pagyMchtMngTab_dataset.pageIndex);
	}

	
/*****************************联机申请**********************************/
 	function btnOnlineApl_onClickCheck(){
		var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
		if(pagyNo!='304'){
			$.warn("请选择支付宝直连通道");
			return false;
		}
 		//赋值【商户编号】
		pagyMchtMng_dataset.setParameter("payMchtNo",pagyMchtMng_dataset.getValue("payMchtNo"));
 		return true;
	}
 
	//点击联机申请按钮时
	function btnOnlineApl_postSubmit(btn,param) {	
		//设置地区码
		var areaNo = param.mchtAreaNo;
		var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");//申请通道编号
		if(pagyNo == "301" || pagyNo == "303"){
			if(areaNo != "" && areaNo != null){
				pagyMchtMngTab_dataset.setValue("mchtAreaNo",areaNo);
				$("#formOnlineAreaSel").css("display", "none");		
			}else{
				pagyMchtMngTab_dataset.setFieldRequired("mchtAreaSel",true);
				$("#formOnlineAreaSel").css("display", "block");
			}				
		}		
		setDisplyDefault("OnlineApl");
		btnSubmitOnlineApl.setHidden(false);
		
		//设置只读
		pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
		pagyMchtMng_dataset.setFieldReadOnly("chlName", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
		pagyMchtMng_dataset.setFieldReadOnly("aplStat",true);
		pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);		
		
		//初始化下拉字段
		pagyMchtMngTab_dataset.setValue("mchtMccYl","");//MCC组别【银联】
		pagyMchtMngTab_dataset.setValue("mchtMccYlName","");
		pagyMchtMngTab_dataset.setValue("mchtMccSubYl","");//MCC类型【银联】
		pagyMchtMngTab_dataset.setValue("mchtMccSubYlName","");
		pagyMchtMngTab_dataset.setValue("mchtMccWx","");//一级类目【微信】
		pagyMchtMngTab_dataset.setValue("mchtMccWxNm","");
		pagyMchtMngTab_dataset.setValue("mchtMccSubWx","");//二级类目【微信】
		pagyMchtMngTab_dataset.setValue("mchtMccSubWxNm","");
		pagyMchtMngTab_dataset.setValue("mchtMccZfb","");//一级行业【支付宝】
		pagyMchtMngTab_dataset.setValue("mchtMccZfbNm","");
		pagyMchtMngTab_dataset.setValue("mchtMccSubZfb","");//二级行业【支付宝】
		pagyMchtMngTab_dataset.setValue("mchtMccSubZfbNm","");
		
		//通道信息的dataset中非必输
		pagyMchtMngTab_dataset.setFieldRequired("chlId",false);
		pagyMchtMngTab_dataset.setFieldRequired("aplType",false);
		pagyMchtMngTab_dataset.setFieldRequired("pagyNo",false);
		pagyMchtMngTab_dataset.setFieldRequired("mchtName",true);
		
		//设置通道字段				
		var aplType = pagyMchtMng_dataset.getValue("aplType");//申请类型
		if(pagyNo == "301"){//银联渠道
			setFieldRequired(false, false, false, true);
			pagyMchtMngTab_dataset.setFieldRequired("mchtName",true);
			//---------2017-03-28新增   房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次,并显示默认值
// 			pagyMchtMngTab_dataset.setValue("speBillType","00");
// 			pagyMchtMngTab_dataset.setValue("speBillGrade","0");
// 			pagyMchtMngTab_dataset.setFieldRequired("speBillType",true);
		}else if(pagyNo == "302"){//微信通道
			pagyMchtMngTab_dataset.setFieldRequired("mchtName",true);
			pagyMchtMngTab_dataset.setFieldReadOnly("mchRamak",true);//商户备注，渠道申请使用渠道号，渠道商户申请使用渠道商户号
			if(aplType == "1"){
				pagyMchtMngTab_dataset.setValue("mchRamak",pagyMchtMng_dataset.getValue("chlId"));
			}else if(aplType == "2"){
				pagyMchtMngTab_dataset.setValue("mchRamak",pagyMchtMng_dataset.getValue("payMchtNo"));
			}
			setFieldRequired(true, true, false, false);
		}else if(pagyNo == "304"){//支付宝通道
			pagyMchtMngTab_dataset.setFieldRequired("mchtName",true);
			pagyMchtMngTab_dataset.setFieldReadOnly("zfbMchtNo",true);//商户号，渠道申请使用渠道号，渠道商户申请使用渠道商户号
			if(aplType == "1"){
				pagyMchtMngTab_dataset.setValue("zfbMchtNo",pagyMchtMng_dataset.getValue("chlId"));
			}else if(aplType == "2"){
				pagyMchtMngTab_dataset.setValue("zfbMchtNo",pagyMchtMng_dataset.getValue("payMchtNo"));
			}
			setFieldRequired(true, false, true, false);
		}else if(pagyNo == "303"){
			setFieldRequired(false, false, false, true);
			pagyMchtMngTab_dataset.setFieldRequired("mchtName",true);
			//---------2017-03-28新增   房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次,并显示默认值
// 			pagyMchtMngTab_dataset.setValue("speBillType","00");
// 			pagyMchtMngTab_dataset.setValue("speBillGrade","0");
// 			pagyMchtMngTab_dataset.setFieldRequired("speBillType",true);
		}					
		//获取查询到的 信息赋值
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtName",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtNameAbbr",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtContactPhone",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtSerPhone",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtContactEmail",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtContact",true);
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtAreaSel",true);
		
		pagyMchtMngTab_dataset.setValue("mchtName",param.mchtName);
		pagyMchtMngTab_dataset.setValue("mchtNameAbbr",param.mchtNameAbbr);
		pagyMchtMngTab_dataset.setValue("mchtContactPhone",param.mchtContactPhone);
		pagyMchtMngTab_dataset.setValue("mchtSerPhone",param.mchtSerPhone);
		pagyMchtMngTab_dataset.setValue("mchtContactEmail",param.mchtContactEmail);
		pagyMchtMngTab_dataset.setValue("mchtContact",param.mchtContact);
		//pagyMchtMngTab_dataset.setValue("mchtAreaSel",param.mchtAreaNo);
		pagyMchtMngTab_dataset.setValue("mchtAreaSel",param.ctName);
		
		window_windowOnlineApl.open();
	}
	
	//提交前校验
	function btnSubmitOnlineApl_onClickCheck(button, commit) {
		var validFlag = validParam();
		if(!validFlag){
			return false;
		}else{ 
			//2017-03-28新增,提交之前将特殊计费类型+特殊计费档次存入mchRamak中
// 			var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");
// 			if(pagyNo == "301" ||pagyNo == "303"){
// 				var speBillType = pagyMchtMngTab_dataset.getValue("speBillType");
// 				var speBillGrade = pagyMchtMngTab_dataset.getValue("speBillGrade");
// 				pagyMchtMngTab_dataset.setValue("mchRamak",speBillType+speBillGrade);
// 			}
			return true;
		}		
	}
	
	//联机申请成功时
	function btnSubmitOnlineApl_postSubmit() {
		$.success("申请成功!", function() {
			window_windowOnlineApl.close();
		});
	}
	
	//联机申请窗体关闭
	function window_windowOnlineApl_beforeClose(){
		//2017-03-28新增
// 		pagyMchtMngTab_dataset.setFieldRequired("speBillType",false);
		
		//还原写入权限
		pagyMchtMng_dataset.setFieldReadOnly("aplType", false);
		pagyMchtMng_dataset.setFieldReadOnly("chlName", false);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", false);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName", false);
		pagyMchtMng_dataset.setFieldReadOnly("aplStat",false);
		pagyMchtMng_dataset.setFieldReadOnly("pagyName", false);
		//还原商户全名和简称权限
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtName",false)
		pagyMchtMngTab_dataset.setFieldReadOnly("mchtNameAbbr",false)
		//清除字段必输
		setFieldUnRequired();
		//清空数据集
		pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);		
		pagyMchtMngTab_dataset.flushData(pagyMchtMngTab_dataset.pageIndex);			
	}
	
	
/*****************************详情查看**********************************/	
	//点击详情按钮时
	function btnDetailApl_onClick() {
		pagyMchtMng_dataset.setReadOnly(true);
		setDisplyDefault("Detail");
		btnSubmitOnlineApl.setHidden(true);
		//设置商户备注【仅当申请类型选择-渠道商户申请，且申请通道为-微信时】				
		var aplType = pagyMchtMng_dataset.getValue("aplType");//申请类型
		var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");//申请通道
		if(pagyNo == "302"){
			if(aplType == "1"){
				pagyMchtMng_dataset.setValue("mchRamak",pagyMchtMng_dataset.getValue("chlId"));
			}else if(aplType == "2"){
				pagyMchtMng_dataset.setValue("mchRamak",pagyMchtMng_dataset.getValue("payMchtNo"));
			}
		}
		//设置商户号【仅当申请类型选择-渠道商户申请，且申请通道为-支付宝时】
		if(pagyNo == "304"){
			if(aplType == "1"){
				pagyMchtMng_dataset.setValue("zfbMchtNo",pagyMchtMng_dataset.getValue("chlId"));
			}else if(aplType == "2"){
				pagyMchtMng_dataset.setValue("zfbMchtNo",pagyMchtMng_dataset.getValue("payMchtNo"));
			}
		}
		//---------2017-03-28新增   房管局,当为银联渠道和本行通道时,显示特殊计费类型和特殊计费档次,联机申请完成时将  殊计费类型  +特殊计费档次 存入 mchRamak 中
		//所以点击详情显示时需要截取mchRamak前两位赋值给特殊计费类型字段,截取第三位赋值给特殊计费档次字段
// 		var mchRamak = pagyMchtMngTab_dataset.getValue("mchRamak");//商户备注
// 		if(mchRamak != null  && mchRamak != "" && mchRamak.length > 2 ){//商户备注不为空
// 			if(pagyNo == "301" ||pagyNo == "303"){//301 银联渠道   303 本行通道
// 				pagyMchtMngTab_dataset.setValue("speBillType",mchRamak.substr(0, 2));//特殊计费类型
// 				pagyMchtMngTab_dataset.setValue("speBillGrade",mchRamak.substr(2, 1));//特殊计费档次
// 				pagyMchtMngTab_dataset.setFieldReadOnly("speBillType", true);
// 			}
// 		}else{//商户备注为空
// 			if(pagyNo == "301" ||pagyNo == "303"){
// 				pagyMchtMngTab_dataset.setFieldReadOnly("speBillType", true);
// 			}
// 		}

		window_windowDetail.open();
	}
	
	//详情窗口关闭，还原字段属性
	function window_windowDetail_afterClose(){
		var pagyNo = pagyMchtMng_dataset.getValue("pagyNo");//申请通道编号
		if(pagyNo == "301"){//银联全渠道
			pagyMchtMng_dataset.setFieldRequired("mchtMccCode", false);
			pagyMchtMng_dataset.setFieldRequired("mchtArea", false);
			pagyMchtMng_dataset.setFieldRequired("mchtNo", false);
		}else if(pagyNo == "302"){//微信通道
			pagyMchtMng_dataset.setFieldRequired("mchtSerPhone", false);
			pagyMchtMng_dataset.setFieldRequired("mchtContact", false);			
			pagyMchtMng_dataset.setFieldRequired("mchtContactPhone", false);
			pagyMchtMng_dataset.setFieldRequired("mchtContactEmail", false);
			pagyMchtMng_dataset.setFieldRequired("mchtMccCode", false);
			pagyMchtMng_dataset.setFieldRequired("mchRamak", false);
		}else if(pagyNo == "304"){
			pagyMchtMng_dataset.setFieldRequired("zfbMchtNo", false);
			pagyMchtMng_dataset.setFieldRequired("mchtSerPhone", false);
			pagyMchtMng_dataset.setFieldRequired("mchtContact", false);			
			pagyMchtMng_dataset.setFieldRequired("mchtContactPhone", false);
			pagyMchtMng_dataset.setFieldRequired("mchtContactEmail", false);
			pagyMchtMng_dataset.setFieldRequired("mchtMccCode", false);			
		}	
		
		//2017-03-28新增
// 		pagyMchtMngTab_dataset.setFieldReadOnly("speBillType", false);
		
		pagyMchtMng_dataset.setFieldRequired("mchtNameAbbr", false);
		pagyMchtMng_dataset.setFieldRequired("mchtName", false);
		pagyMchtMng_dataset.setReadOnly(false);
		setFieldUnRequired();
		//清空数据集
		pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);
		pagyMchtMngTab_dataset.flushData(pagyMchtMngTab_dataset.pageIndex);
	}
	/**************************通道商户号录入**************************/
	//点击新增按钮
	function btnMchtNo_onClick(){
		var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
		var mchtNo= pagyMchtMng_dataset.getValue("mchtNo");
		var mainMchtAcsType= pagyMchtMng_dataset.getValue("mainMchtAcsType");
		if(mainMchtAcsType==01){
			$("#formMd5Add").css("display","block");	
		}else{
			$("#formMd5Add").css("display","none");
		}
		if(!pagyNo){
			$.warn("请先选择一条记录");
			return false;
		}
		if(pagyNo=='311'){
				pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
				pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
				pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
				pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
				pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
				pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
				pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
				pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
				pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
				pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
				window_winMchtNo.open();
		}else if(pagyNo=='304'){				
			pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
			pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
			pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
			pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
			pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
			pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
			pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
			pagyMchtMng_dataset.setFieldReadOnly("encryptType", true);
			pagyMchtMng_dataset.setFieldRequired("md5Passwd",true);
			pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
			pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
			pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
			pagyMchtMng_dataset.setValue("mchtNo", "2088721697888611");
			pagyMchtMng_dataset.setValue("settlementMark", "00");
			pagyMchtMng_dataset.setFieldReadOnly("settlementMark", true);
			pagyMchtMng_dataset.setValue("md5Passwd", "123");
			window_winMchtNo.open();			
	}else if(pagyNo=='313'){				
		pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
		pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
		pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
		pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
		pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
		pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
		pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
		pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
		pagyMchtMng_dataset.setValue("settlementMark", "00");
		pagyMchtMng_dataset.setFieldReadOnly("settlementMark", true);
		window_winMchtNo.open();
	}else {				
			$.warn("请选择威福通通道或支付宝直连通道或中信通道!");
			return false;			
	}
	}
	
	 function window_winMchtNo_beforeOpen(){
		    var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
			var mchtNo= pagyMchtMng_dataset.getValue("mchtNo");
			var mainMchtAcsType= pagyMchtMng_dataset.getValue("mainMchtAcsType");
			if(mainMchtAcsType==01){
				$("#formMd5Add").css("display","block");	
			}else{
				$("#formMd5Add").css("display","none");
			}
			if(pagyNo=='311'){
				pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
				pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
				pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
				pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
				pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
				pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
				pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
				pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
				pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
				pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
				// modify by lengjingyu  20180201   打开页面结算标志为非只读   没有jira
				pagyMchtMng_dataset.setFieldReadOnly("settlementMark", false);
				// modify end
		}else if(pagyNo=='304'){				
			pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
			pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
			pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
			pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
			pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
			pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
			pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
			pagyMchtMng_dataset.setFieldReadOnly("encryptType", true);
			pagyMchtMng_dataset.setFieldRequired("md5Passwd",true);
			pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
			pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
			pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
			pagyMchtMng_dataset.setValue("mchtNo", "2088721697888611");
			pagyMchtMng_dataset.setValue("settlementMark", "00");
			pagyMchtMng_dataset.setFieldReadOnly("settlementMark", true);
			pagyMchtMng_dataset.setValue("md5Passwd", "123");
	}else if(pagyNo=='313'){				
		pagyMchtMng_dataset.setFieldReadOnly("aplType", true);
		pagyMchtMng_dataset.setFieldReadOnly("chlId", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
		pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
		pagyMchtMng_dataset.setFieldReadOnly("pagyName", true);
		pagyMchtMng_dataset.setFieldReadOnly("mainMchtNo", true);
		pagyMchtMng_dataset.setFieldRequired("mchtNo",true);
		pagyMchtMng_dataset.setFieldReadOnly("mchtNo", false);
		pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
		pagyMchtMng_dataset.setFieldReadOnly("md5Passwd", false);
		pagyMchtMng_dataset.setValue("settlementMark", "00");
		pagyMchtMng_dataset.setFieldReadOnly("settlementMark", true);
	}
			
		 
	 }
	
	 function btnSubmitMchtNo_onClickCheck(button,commit){	
			var mchtNo= pagyMchtMng_dataset.getValue("mchtNo");
			var encryptType= pagyMchtMng_dataset.getValue("encryptType");
			var md5Passwd= pagyMchtMng_dataset.getValue("md5Passwd");
			var settlementMark= pagyMchtMng_dataset.getValue("settlementMark");
			var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
			if(settlementMark == ""|| (settlementMark == null)){
				$.warn("请选择结算标示！");
				return false;
				}
			if(pagyNo=='311'||pagyNo=='313'){
				if(mchtNo == ""|| (mchtNo == null)){
					$.warn("请选择输入通道商户号！");
					return false;
					}
					if(encryptType == ""|| (encryptType == null)){
						$.warn("请选择选择加密算法！");
						return false;
						}
					if(md5Passwd == ""|| (md5Passwd == null)){
						$.warn("请输入密钥值！");
						return false;
						}
			}else if(pagyNo=='304'){
				if(mchtNo == ""|| (mchtNo == null)){
					$.warn("请选择输入通道商户号！");
					return false;
					}
			}
			
			return true;
	 }
	function btnSubmitMchtNo_postSubmit(){
		$.success("操作成功!",function(){
 		   window_winMchtNo.close();
 		  pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);
 	   });
	}
	/**窗口关闭后清除数据*/
		function window_winMchtNo_beforeClose(){
			pagyMchtMng_dataset.cancelRecord();
			pagyMchtMng_dataset.setFieldRequired("mchtNo",false);
			pagyMchtMng_dataset.setFieldReadOnly("encryptType", false);
			pagyMchtMng_dataset.setFieldRequired("md5Passwd",false);
		}
		/**************************平安银行联机申请**************************/
		var isDesc256 = /^\S{1,256}$/;//最大长度42位 
		var isDesc64 = /^\S{1,64}$/;//最大长度64位 
		var isDesc32 = /^\S{1,32}$/;//最大长度42位 
		var isDesc300 = /^\S{1,300}$/;//最大长度42位
		/**验证手机电话 */
		var isCellPhone = /1[3|4|5|7|8][0-9]{9}$/;
		/**域名校验 */
		var isPath=/^((http:\/\/)|(https:\/\/))?(www\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}/;
	 	/**验证邮箱*/
	 	//var isEmail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 	var isEmail= /^[a-zA-Z0-9]([a-zA-Z0-9\.]*)@([a-zA-Z0-9]*)\.([a-zA-Z]{2,3})$/;
		//点击shenq按钮
		function btnOnlinePA_onClick(){
			var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
			 var aplStat= pagyMchtMng_dataset.getValue("aplStat");//申请状态
			 var wxJsapiPath= pagyMchtMng_dataset.getValue("wxJsapiPath");
			if(pagyNo!='312'){
				$.warn("请选择平安通道！");
				return false;
			}
			if(wxJsapiPath==null||wxJsapiPath==''){
				pagyMchtMng_dataset.setValue("wxJsapiPath", "https://www.ect888.com/payweb/pay/");				
			}
            pagyMchtMng_dataset.setFieldReadOnly("payMchtNo", true);
            pagyMchtMng_dataset.setFieldReadOnly("payMchtName", true);
            pagyMchtMng_dataset.setFieldReadOnly("mchtName", false);
            pagyMchtMng_dataset.setFieldReadOnly("mchtNameAbbr", false);
            pagyMchtMng_dataset.setFieldReadOnly("mchtSerPhone", false);
            pagyMchtMng_dataset.setFieldReadOnly("mchtContact", false);
            pagyMchtMng_dataset.setFieldReadOnly("mchtContactPhone", false);
            pagyMchtMng_dataset.setFieldReadOnly("mchtContactEmail", false);
            pagyMchtMng_dataset.setFieldReadOnly("address", false);
            pagyMchtMng_dataset.setFieldReadOnly("addressCode", false);
            pagyMchtMng_dataset.setFieldReadOnly("weixinCategory", false);
            pagyMchtMng_dataset.setFieldReadOnly("weixinFee", false);
            pagyMchtMng_dataset.setFieldReadOnly("aliCategory", false);
            pagyMchtMng_dataset.setFieldReadOnly("aliFee", false);
            pagyMchtMng_dataset.setFieldReadOnly("subscribeAppid", false);
            pagyMchtMng_dataset.setFieldReadOnly("subAppid", false);
            pagyMchtMng_dataset.setFieldReadOnly("wxJsapiPath", false);
			if(aplStat=='02'){
	            pagyMchtMng_dataset.setFieldReadOnly("wxJsapiPath", true);
	            pagyMchtMng_dataset.setFieldReadOnly("subAppid", true);
	            pagyMchtMng_dataset.setFieldReadOnly("subscribeAppid", true);

			}
			window_windowOnlinePA.open();
		}
		 function btnSubmitManPA_onClickCheck(button,commit){	
			 var aplStat= pagyMchtMng_dataset.getValue("aplStat");//申请状态
				var mchtName= pagyMchtMng_dataset.getValue("mchtName");//商户全名
				var mchtNameAbbr= pagyMchtMng_dataset.getValue("mchtNameAbbr");//商户简称
				var mchtSerPhone= pagyMchtMng_dataset.getValue("mchtSerPhone");//客服电话
				var mchtContact= pagyMchtMng_dataset.getValue("mchtContact");//联系人
				var mchtContactPhone= pagyMchtMng_dataset.getValue("mchtContactPhone");//联系电话
				var mchtContactEmail= pagyMchtMng_dataset.getValue("mchtContactEmail");//联系邮箱
				var address= pagyMchtMng_dataset.getValue("address");//详细地址
				var mchtAreaSelPA= pagyMchtMng_dataset.getValue("addressCode");//地区码
				var weixinBERL= pagyMchtMng_dataset.getValue("weixinCategory");//微信支付方式
				var weixinBERLfee= pagyMchtMng_dataset.getValue("weixinFee");//微信支付方式费率
				var alipayCS= pagyMchtMng_dataset.getValue("aliCategory");//支付宝支付方式
				var alipayCSfee= pagyMchtMng_dataset.getValue("aliFee");//支付宝支付方式费率
				var subscribeAppid= pagyMchtMng_dataset.getValue("subscribeAppid");//订阅公众号
				var wxJsapiPath= pagyMchtMng_dataset.getValue("wxJsapiPath");//订阅公众号

				if(mchtName == ""|| (mchtName == null)){
					$.warn("请选择商户全名！");
					return false;
				}
				if(mchtNameAbbr == ""|| (mchtNameAbbr == null)){
					$.warn("请选择商户简称！");
					return false;
				}				
				if(mchtSerPhone == ""|| (mchtSerPhone == null)){
					$.warn("请选择客服电话！");
					return false;
				}				
				if(mchtContact == ""|| (mchtContact == null)){
					$.warn("请选择联系人！");
					return false;
				}				
				if(mchtContactPhone == ""|| (mchtContactPhone == null)){
					$.warn("请选择联系电话！");
					return false;
				}				
				if(mchtContactEmail == ""|| (mchtContactEmail == null)){
					$.warn("请选择联系邮箱！");
					return false;
				}				
				if(address == ""|| (address == null)){
					$.warn("请选择详细地址！");
					return false;
				}				
				if(mchtAreaSelPA == ""|| (mchtAreaSelPA == null)){
					$.warn("请选择地区码！");
					return false;
				}				
				if(weixinBERL == ""|| (weixinBERL == null)){
					$.warn("请选择微信支付方式！");
					return false;
				}				
				if(weixinBERLfee == ""|| (weixinBERLfee == null)){
					$.warn("请选择微信支付方式费率！");
					return false;
				}				
				if(alipayCS == ""|| (alipayCS == null)){
					$.warn("请选择支付宝支付方式！");
					return false;
				}				
				if(alipayCSfee == ""|| (alipayCSfee == null)){
					$.warn("请选择支付宝支付方式费率！");
					return false;
				}
				if(wxJsapiPath == ""|| (wxJsapiPath == null)){

				}else{
				if(!(isPath.test(wxJsapiPath))){
					$.warn("【授权目录】格式错误！");
					return false;
				}					
				}
				if(subscribeAppid != ""&& (subscribeAppid != null)){
					if(!isDesc256.test(subscribeAppid)){
						$.error("【订阅公众号】长度超过限制！");
						return false;
					}
				}
				if(!isDesc256.test(mchtName)){
					$.error("【商户全名】长度超过限制！");
					return false;
				}
				if(!isDesc64.test(mchtNameAbbr)){
					$.error("【商户简称】长度超过限制！");
					return false;
				}
				if(!isDesc32.test(mchtSerPhone)){
					$.error("【客服电话】长度超过限制！");
					return false;
				}
				if(!isDesc32.test(mchtContact)){
					$.error("【联系人】长度超过限制！");
					return false;
				}
				if(!isDesc32.test(mchtContactPhone)){
					$.error("【联系电话】长度超过限制！");
					return false;
				}
				if(!isDesc64.test(mchtContactEmail)){
					$.error("【联系邮箱】长度超过限制！");
					return false;
				}
				if(!isDesc300.test(address)){
					$.error("【详细地址】长度超过限制！");
					return false;
				}
				if(!(isCellPhone.test(mchtContactPhone))){
					$.warn("【手机号】格式错误,必须是11位手机号");
					return false;
				}
				if(!(isEmail.test(mchtContactEmail))){
					$.warn("邮箱格式错误");
					return false;
				}
				// 点击后三秒内不能再次提交
				btnSubmitManPA.setDisabled(true);
				var timer = setInterval(function(){//开启定时器
					btnSubmitManPA.setDisabled(false);
					clearInterval(timer);    //清除定时器
				},3000); 
			  	return true;	
		 }
			function btnSubmitManPA_postSubmit(){
				$.success("操作成功!",function(){
					window_windowOnlinePA.close();
		 		  pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);
		 	   });
			}
			function window_windowOnlinePA_beforeClose(){
				pagyMchtMng_dataset.cancelRecord();
			}
			
	  			/**************************平安银行标准进件**************************/
		function btnOnlineStandard_onClick(){
			var pagyNo= pagyMchtMng_dataset.getValue("pagyNo");
			if(pagyNo!='312'){
				$.warn("请选择平安通道！");
				return false;
			}
			pagyMchtMng_dataset.setReadOnly(true);
			window_windowOnlineStandard.open();	
		}
			
	 function btnSubmitManStandard_postSubmit(button,commit){
		function btnSubmitManStandard_postSubmit(){
			$.success("操作成功!",function(){
				window_windowOnlineStandard.close();
	 	   });
		}
	 }
		function window_windowOnlineStandard_beforeClose(){
			pagyMchtMng_dataset.setReadOnly(false);
			pagyMchtMng_dataset.cancelRecord();
	 		  pagyMchtMng_dataset.flushData(pagyMchtMng_dataset.pageIndex);
		}

	  		
</script>
</snow:page>
