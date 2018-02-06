<%@page import="com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户合同管理">
	<!-- 数据集加载 -->
		<!-- 主界面数据集  -->
		<snow:dataset  id="mchtContractInfo"  path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtContract" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset  id="ConToPro"  path="com.ruimin.ifs.pmp.mchtMng.dataset.ConToPro" init="false" submitMode="current" ></snow:dataset>
		<snow:dataset  id="ConToProDetail"  path="com.ruimin.ifs.pmp.mchtMng.dataset.ConToPro" init="false" submitMode="current" ></snow:dataset>
	    <snow:dataset id="conSerAcc" path="com.ruimin.ifs.pmp.mchtMng.dataset.conSerAcc" submitMode="all" init="false"></snow:dataset>
	    <snow:dataset id="conSerPro" path="com.ruimin.ifs.pmp.mchtMng.dataset.conSerPro" submitMode="all" init="true"></snow:dataset>
	    <!-- 审核信息记录数据集 -->
		<snow:dataset id="checkNewRecordInfo"  path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" submitMode="current" init="true"></snow:dataset>
	<!-- 清算信息选择数据集 -->
		<snow:dataset id="mchtSetlType"  path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtSetlType" submitMode="current" init="false"></snow:dataset>
	<!-- 页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="mchtContractInfo" fieldstr="qmchtId,qmchtSimpleName,qconId,qpaperConId,qsetlAcctNo,qconState" label="查询条件"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="mchtContractInfo" fieldstr="mchtId,mchtSimpleName,chlSysNo,conId,paperConId,startDate,conState,opr"
		height = "99%" paginationbar="btnAdd,btnUpd,btnFrz,btnServ" label="商户合同基本信息"></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 新增窗口 -->
		<snow:window id="winAdd" title="商户合同信息新增"  modal="true" closable="true" buttons="btnAddSub">
		  <p style="font">合同信息</p>
			<snow:form id="formAddActive1" dataset="mchtContractInfo"  border="false" label="合同信息" fieldstr="mchtId1,mchtSimpleName,paperConId,startDate,extendFlag,conTerm" collapsible="true" colnum="4"></snow:form>
			<br/>
			<%-- <p>交易限额</p>
			<snow:form id="formAddActive2" dataset="mchtContractInfo"  border="false" label="交易限额" fieldstr="limitOne,limitDay" collapsible="true" colnum="4"></snow:form>
			<br/> --%>
			<p>结算信息</p>
			<snow:form id="formAddActive3" dataset="mchtContractInfo"  border="false" label="结算日期" fieldstr="setlType,setlSymbol,setlCycle,accountType,accountBoss,specSetlDay,setlAcctName,setlAcctNo,setlCertType,setlCertNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlBankPhone,setlAcctAreaCode" collapsible="false" colnum="4"></snow:form>

					
			<snow:button id="btnAddSub" dataset="mchtContractInfo" hidden="true"></snow:button>
		</snow:window>

       <!-- 修改商户合同窗口 -->
		<snow:window id="winUpd" title="商户合同信息修改"  modal="true" closable="true" buttons="btnUpdSub,btnRtn">
		  <p style="font">合同信息</p>
			<snow:form id="formUpdActive1" dataset="mchtContractInfo"  border="false" label="合同信息" fieldstr="mchtId,mchtSimpleName,conId,paperConId,startDate,extendFlag,conTerm" collapsible="true" colnum="4"></snow:form>
			<br/>
			<%-- <p>交易限额</p>
			<snow:form id="formAddActive2" dataset="mchtContractInfo"  border="false" label="交易限额" fieldstr="limitOne,limitDay" collapsible="true" colnum="4"></snow:form>
			<br/> --%>
			<p>结算信息</p>
			<snow:form id="formUpdActive3" dataset="mchtContractInfo"  border="false" label="结算日期" fieldstr="setlType,setlSymbol,setlCycle,accountType,accountBoss,specSetlDay,setlAcctName,setlAcctNo,setlCertType,setlCertNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlBankPhone,setlAcctAreaCode" collapsible="false" colnum="4"></snow:form>
			<br/>
		
			<snow:button id="btnUpdSub" dataset="mchtContractInfo" hidden="true"></snow:button>
			<snow:button id="btnRtn" dataset="mchtContractInfo" hidden="true"></snow:button>
		</snow:window>
      <!-- 详情窗口 -->
      <snow:window id="winDetail" title="商户合同信息详情"  modal="true" closable="true" buttons="btnReturn">
		  <p style="font">合同信息</p>
			<snow:form id="formDetailActive1" dataset="mchtContractInfo"  border="false" label="合同信息" fieldstr="mchtId,mchtSimpleName,conId,paperConId,startDate,extendFlag,conTerm,endDate" collapsible="true" colnum="4"></snow:form>
			<br/>
			<%-- <p>交易限额</p>
			<snow:form id="formAddActive2" dataset="mchtContractInfo"  border="false" label="交易限额" fieldstr="limitOne,limitDay" collapsible="true" colnum="4"></snow:form>
			<br/> --%>
			<p>结算信息</p>
			<snow:form id="formDetailActive3" dataset="mchtContractInfo"  border="false" label="结算日期" fieldstr="setlType,setlSymbol,setlCycle,specSetlDay,accountType,accountBoss,setlAcctName,setlAcctNo,setlCertType,setlCertNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlBankPhone,setlAcctAreaCode" collapsible="false" colnum="4"></snow:form>
			<br/>
			
				<p>服务信息</p>
				<snow:tabs id="ConServiceDetail" showswitch="true" border="true" height="350">									
		</snow:tabs>
				<snow:button id="btnReturn" dataset="mchtContractInfo" hidden="true"></snow:button>
		</snow:window>
		<snow:button id="btnFrzSub" dataset="mchtContractInfo" hidden="true"></snow:button>
  <!-- 服务窗口 -->
		<snow:window id="winService" title="商户合同服务"  modal="true" closable="true" buttons="btnSerSub">
		  <p style="font">合同信息</p>
			<snow:form id="formServiceActive1" dataset="mchtContractInfo"  border="false" label="合同信息" fieldstr="mchtId,mchtSimpleName,conId,paperConId,startDate,extendFlag,conTerm" collapsible="true" colnum="4"></snow:form>
			<br/>
		 <p style="font">服务信息</p>
		  <ul>
                <li><snow:button id="add" desc="新增"></snow:button></li>
          </ul>
       	<snow:tabs id="ConService" showswitch="true" border="true" height="350">									
		</snow:tabs>
		<snow:button id="delTab" dataset="ConToPro" hidden="true"></snow:button>
		<snow:button id="btnSerSub" dataset="mchtContractInfo" hidden="true"></snow:button>
		</snow:window>
	<!-- 商户合同状态处于待审核，及其拒绝时，超链接窗口 -->
    <snow:window id="windowConStat" title="合同审核相关信息" modal="true" closable="true"
		buttons="" width="800" height="690"> 
		<p style="font" >商户合同基本信息</p>
		<snow:form id="formMchtInfo" dataset="mchtContractInfo" label="商户合同基本信息"
			border="false" 
			fieldstr="mchtId,mchtSimpleName,paperConId,startDate,extendFlag,conTerm,auditId">
		</snow:form> 	
		<br />
		<p style="font" >审核流程信息</p>
		<snow:grid id="gridCheckNewRecordInfo" dataset="checkNewRecordInfo" paginationbar=""  fieldstr="stepNo[90],auditState[100],roleName[110],auditDatetIme[130],auditView[270]" height="400" >
		</snow:grid>
	</snow:window> 
		
	<script type="text/javascript">
	
/****************************************详情******************************************/
	/**验证字符串长度*/
	var isDesc32 = /^\d{1,32}$/;//最大长度32位 
	var isDesc18= /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	//var isDesc120 = /^[A-Za-z0-9]{1,120}$/;//最大长度120位 
	var isDesc10 = /^\S{1,10}$/;//最大长度10位 
	var isDesc12 = /^\d{1,12}$/;//最大长度12位 
	var isDesc20 = /^\d{1,20}$/;//最大长度20位
	var isDesc3 = /^\d{1,3}$/;//最大长度3位 
	var isDesc2 = /^\d{1,2}$/;//最大长度2位 
	var isDesc1 = /^\d{1,1}$/;//最大长度1位 
	var isCellPhone = /^1[3|4|5|7|8][0-9]{9}$/;//银行预留手机号
	//详情 
	function showQlfOrNotDetail(showFlag){
		if(showFlag == true){
			$("#tabs_conPicTabsDetail").css("display","block");			
		}else{
			$("#tabs_conPicTabsDetail").css("display","none");			
		}		
	}
	
	//新增
	function showQlfOrNotAdd(showFlag){
		if(showFlag == true){
			$("#tabs_conPicTabsAdd").css("display","block");			
		}else{
			$("#tabs_conPicTabsAdd").css("display","none");
		}		
	}
	
	//修改
	function showQlfOrNotUpd(showFlag){
		if(showFlag == true){			
			$("#tabs_conPicTabsUpd").css("display","block");			
			
		}else{			
			$("#tabs_conPicTabsUpd").css("display","none");			
		}		
	}
	
	
	
	//详情显示
	function gridMain_opr_onRefresh(record, fieldId, fieldValue) {
 		if (record) {
 			return "<i class='fa fa-info'></i>&nbsp;<a href=\"JavaScript:onClickDetail()\">详情</a>";
 		}
	}
	//合同状态显示
	function gridMain_conState_onRefresh(record, fieldId, fieldValue) {
 		 var auditId = mchtContractInfo_dataset.getValue("auditId");
 	    if(fieldValue == '04'){
 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增待审核 </a></span>";	
 	    }
 	    if(fieldValue == '05'){
 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增被拒绝  </a></span>";	
 	    }
 	    if(fieldValue == '06'){
 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改待审核 </a></span>";	
 	    }
 	    if(fieldValue == '07'){
 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改被拒绝</a></span>";	
 	    }
 	   if(fieldValue == '08'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">中止待审核</a></span>";	
	    }
 	   if(fieldValue == '09'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">中止被拒绝</a></span>";	
	    }
 	   if(fieldValue == '10'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">恢复待审核</a></span>";	
	    }
	   if(fieldValue == '11'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">恢复被拒绝</a></span>";	
	    }
 	    if(fieldValue == '12'){
 	    	return '添加待提审'; 
 	    }
 	    if(fieldValue == '13'){
 	    	return '修改待提审'; 
 	    }
 	    if(fieldValue == '01'){
 	    	return '签订中'; 
 	    }
 	    if(fieldValue == '00'){
 	    	return '执行中';	
 	    }
 	    if(fieldValue == '02'){
 	    	return '即将到期';
 	    }
 	    if(fieldValue == '03'){
 	    	return '已过期';	
 	    }
 	   if(fieldValue == '99'){
	    	return '中止';	
	    }
	}
	/*点击合同状态对应的窗体信息 */
	 function onClickWin(auditId){
		 mchtContractInfo_dataset.setFieldReadOnly("mchtId", true);
		 mchtContractInfo_dataset.setFieldReadOnly("mchtSimpleName", true);
		 mchtContractInfo_dataset.setFieldReadOnly("paperConId", true);
		 mchtContractInfo_dataset.setFieldReadOnly("startDate", true);
		 mchtContractInfo_dataset.setFieldReadOnly("extendFlag", true);
		 mchtContractInfo_dataset.setFieldReadOnly("conTerm", true);
		 mchtContractInfo_dataset.setFieldReadOnly("auditId", true);
        //根据审核信息编号，查询审核记录表
        var auditId = mchtContractInfo_dataset.getValue("auditId");
        checkNewRecordInfo_dataset.setParameter("auditId",auditId);
        checkNewRecordInfo_dataset.flushData(checkNewRecordInfo_dataset.pageIndex);
    	window_windowConStat.open();
    }
	//关闭窗体
	function window_windowConStat_afterClose(){
		mchtContractInfo_dataset.setFieldReadOnly("mchtId", false);
		mchtContractInfo_dataset.setFieldReadOnly("mchtSimpleName", false);
		mchtContractInfo_dataset.setFieldReadOnly("paperConId", false);
		mchtContractInfo_dataset.setFieldReadOnly("startDate", false);
		mchtContractInfo_dataset.setFieldReadOnly("extendFlag", false);
		mchtContractInfo_dataset.setFieldReadOnly("conTerm", false);
		}
	/**打开详情窗口*/
	function onClickDetail(){
		ConToProDetail_dataset.setParameter("conId", mchtContractInfo_dataset.getValue("conId"));
		ConToProDetail_dataset.flushData();
		mchtContractInfo_dataset.setReadOnly(true);//全部设置只读
		window_winDetail.open();
	
	}
	/**窗口打开前初始化form显示*/
	function window_winDetail_beforeOpen(win){
		var mchtType = mchtContractInfo_dataset.getValue("mchtType");//商户类型
		if((mchtType == "05")){
			showQlfOrNotDetail(false);
		}
	}
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		mchtContractInfo_dataset.setReadOnly(false);//还原写入权限
		mchtContractInfo_dataset.clearData();	
		showQlfOrNotDetail(true);
		var len= ConToProDetail_dataset.length;
		while(len>0){
			tabs_ConServiceDetail.setClosable(len, true);
			len--
		}
		tabs_ConServiceDetail.closeAll();
		mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
	}
	
	
	function ConToProDetail_dataset_flushDataPost(dataset) {
		
		//加载完数据后动态加载tab页
		var len= ConToProDetail_dataset.length;
		while(len>0){
			var prodName = ConToProDetail_dataset.getValue("prodName");
			//var p2 = encodeURI(prodName);
			var p2 = encodeURI(encodeURI(prodName));
			tabs_ConServiceDetail.add(len,ConToProDetail_dataset.getValue("prodName"),"/pages/payPmp/mchtMng/conService.jsp?conId="+mchtContractInfo_dataset.getValue("conId")+"&prodId="+ConToProDetail_dataset.getValue("prodId")+"&prodName="+p2+"&level=three");
			tabs_ConServiceDetail.setClosable(len, false);
			ConToProDetail_dataset.deleteRecord();
			len--;
		}
		window_winDetail.open();
	}
	function tabs_ConServiceDetail_beforeTabChange(tabid) {
		tabs_ConServiceDetail.refresh(tabid,"");
	} 
	function btnReturn_onClick(){
		window_winDetail.close();
	}
 
/****************************************新增******************************************/
	 	/**窗口打开前初始化form显示*/
	function window_winAdd_beforeOpen(win){
		mchtContractInfo_dataset.setFieldReadOnly("paperConId",false);
		mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
		mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
//		mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
		
		mchtContractInfo_dataset.setFieldRequired("mchtId1",true);
		
		//mchtContractInfo_dataset.setFieldReadOnly("startDate",true);
		var now="<%= new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())%>";
		mchtContractInfo_dataset.setValue("startDate",now);
		//mchtContractInfo_dataset.setFieldReadOnly("conTerm",true);
		mchtContractInfo_dataset.setValue("conTerm","02");
		//mchtContractInfo_dataset.setFieldReadOnly("extendFlag",true);
		mchtContractInfo_dataset.setValue("extendFlag","01");
		//mchtContractInfo_dataset.setValue("specSetlDay","1");
		mchtContractInfo_dataset.setValue("setlCycle","01");
		//mchtContractInfo_dataset.setFieldReadOnly("setlType",true);
		//mchtContractInfo_dataset.setValue("setlType","01");
		//mchtContractInfo_dataset.setValue("setlTypeName","01");
		//mchtContractInfo_dataset.setFieldReadOnly("setlSymbol",true);
		mchtContractInfo_dataset.setValue("setlSymbol","01");
		//mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",true);
		mchtContractInfo_dataset.setValue("setlAcctType","1");
		//mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
		//mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
		//mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
		showQlfOrNotAdd(true);
	}
	
	function mchtContractInfo_dataset_mchtId1_onSelect(v,record){
		if(record!=null){
			var mchtId = record.getValue("mchtId");
		}
		mchtContractInfo_dataset.setValue("paperConId","");
		mchtContractInfo_dataset.setValue("mchtId1",mchtId);
		mchtContractInfo_dataset.setValue("mchtSimpleName",record.getValue("mchtSimpleName"));
		mchtContractInfo_dataset.setValue("mchtType",record.getValue("mchtType"));

		mchtContractInfo_dataset.setValue("accountBoss",record.getValue("accountBoss"));
		var mchtType = mchtContractInfo_dataset.getValue("mchtType");
		if(mchtType == "05"){
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",true);
			mchtContractInfo_dataset.setValue("paperConId","");
			mchtContractInfo_dataset.setValue("setlAcctType",0);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
			mchtContractInfo_dataset.setFieldRequired("paperConId",false);
			showQlfOrNotAdd(false);
		}else{
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",false);
			mchtContractInfo_dataset.setValue("setlAcctType","");
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
			mchtContractInfo_dataset.setFieldRequired("paperConId",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
			showQlfOrNotAdd(true);
		}
	}
	/**********设置参数*************/
	function mchtContractInfo_dataset_mchtId1_beforeOpen(dropDown, dropDownDataset){
			dropDown.cache = false;//每次都重新读取数据，不缓存
			dropDownDataset.setParameter("type","contract");//只查询状态不是停用的模型信息
			dropDownDataset.flushData(dropDownDataset.pageIndex);
	}
	function mchtContractInfo_dataset_setlAcctInstitute_onSelect(v,record){
		if(record!=null){
			var ptyCd = record.getValue("ptyCd");
			mchtContractInfo_dataset.setValue("acctInstNo",ptyCd);
		}else{
			mchtContractInfo_dataset.setValue("setlAcctInstitute","");	
			mchtContractInfo_dataset.setValue("acctInstNo","");	
		}
		return record;
	}
	 function checkInput(){
	     	if(mchtContractInfo_dataset.validate() == false){
	     		return false;
	     	}
	     	var mchtId1 = mchtContractInfo_dataset.getValue("mchtId1");
	     	if(mchtId1 =='' || mchtId1==null ){
	     		$.warn("商户号必须输入");
	     		return false;
	     	}
	    	mchtContractInfo_dataset.setParameter("mchtId1",mchtId1);
			
	    	var setlAcctType = mchtContractInfo_dataset.getValue("setlAcctType");	    	
	    	if(setlAcctType=='1'){
	    		//当预算类型不为00-无
//	    		if(mchtContractInfo_dataset.getValue("setlCertNo") == '' ){
//	    			$.warn("账户证件号码必须输入");
//	    			return false;
//	    		}
//	    		if(mchtContractInfo_dataset.getValue("setlCertType") == '' ){
//	    			$.warn("账户证件类型必须输入");
//	    			return false;
//	    		}
			var setlAcctInstitute =mchtContractInfo_dataset.getValue("setlAcctInstitute")
	    		if( setlAcctInstitute== '' ||setlAcctInstitute==null){
	    			$.warn("开户机构必须输入");
	    			return false;
	    		}
	    	}
	    	var mchtType =mchtContractInfo_dataset.getValue("mchtType");
	    	if(mchtType!="05"){
	    		if(mchtContractInfo_dataset.getValue("paperConId")==''){
	    			$.warn("纸质合同编号必须输入");
	    			return false;
	    		}
	    	}
	    
	    	var setlCycle = mchtContractInfo_dataset.getValue("setlCycle");
	    	var specSetlDay = mchtContractInfo_dataset.getValue("specSetlDay");
	    	if(setlCycle=='01'){
	    		if(!isDesc3.test(specSetlDay)){
	    			$.warn("请输入1~365天的结算日期");
	    			return false;
	    		}else if(parseInt(specSetlDay)<1 ||parseInt(specSetlDay) >365){
	    			$.warn("请输入1~365天的结算日期");
	    			return false;
	    		}
	    		
	    	}else if(setlCycle=='02'){
	    		if(!isDesc1.test(specSetlDay)){
	    			$.warn("请输入1~7天的结算日期");
	    			return false;
	    		}else if(parseInt(specSetlDay) <1 || parseInt(specSetlDay)>7){
	    			$.warn("请输入1~7天的结算日期");
	    			return false;
	    		}
	    	}
	    	else if(setlCycle=='03'){
	    		if(!isDesc2.test(specSetlDay)){
	    			$.warn("请输入1~31天的结算日期");
	    			return false;
	    		}else if(parseInt(specSetlDay) <1 || parseInt(specSetlDay)>31){
	    			$.warn("请输入1~31天的结算日期");
	    			return false;
	    		}
	    	}
				
	    	return true;
	    }
	
	
	function btnAdd_onClick(){
		/* mchtContractInfo_dataset.setFieldRequired( "setlAcctAreaCode", true); */
		window_winAdd.open();
	}
	
	function btnAddSub_onClickCheck(){
		var setlAcctInstitute =mchtContractInfo_dataset.getValue("setlAcctInstitute")
		var paperConId = mchtContractInfo_dataset.getValue("paperConId");
		var setlAcctName = mchtContractInfo_dataset.getValue("setlAcctName");
		var setlAcctNo = mchtContractInfo_dataset.getValue("setlAcctNo");
		var setlCertNo = mchtContractInfo_dataset.getValue("setlCertNo");
		var setlBankPhone = mchtContractInfo_dataset.getValue("setlBankPhone");//银行预留手机号
		//-------------------------当结算账户类型为对私才必输-------------------------------------
		var accountType = mchtContractInfo_dataset.getValue("accountType");
		var accountBoss = mchtContractInfo_dataset.getValue("accountBoss");
		if(accountType === "2"){
			if((accountBoss == "") || (accountBoss == null)){
				$.warn("当结算账户类型为对私时结算账户人身份必输!!");
				return false;
			}
		}
		//-------------------------当结算账户类型为对私才必输-------------------------------------
		if((setlBankPhone == "") || (setlBankPhone == null)){

		}else{
		if(!(isCellPhone.test(setlBankPhone))){
			$.warn("【手机号】格式错误,必须是11位手机号");
			return false;
		}			
		}
		
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		/* var limitOne = mchtContractInfo_dataset.getValue("limitOne");
		var limitDay = mchtContractInfo_dataset.getValue("limitDay");
		mchtContractInfo_dataset.setParameter("limitOne",limitOne);
		mchtContractInfo_dataset.setParameter("limitDay",limitDay);
		
		if(limitOne!=''){
			if(!isDesc12.test(limitOne)){
	     		$.warn("单笔交易限额:请输入数字，并且最大长度为12位");
	     		return false;
			}
		}
		if(limitDay!=''){
				if(!isDesc12.test(limitDay)){
		     		$.warn("日交易限额:请输入数字，并且最大长度为12位");
		     		return false;
				}
		} */
		
		
		//----2017年08-04修改------
		  //新增合同生效日不能为当前时间之前
		  var startDate = mchtContractInfo_dataset.getValue("startDate");
		//合同生效日期
		  var startDate_F = formatDate(startDate,"yyyyMMdd");
		  //当前日期
		  var currDate = formatDate((new Date()),"yyyyMMdd");
		
		
		
		
		  //合同生效日期
		 <%--  var startDate_F = "<%= new SimpleDateFormat("yyyyMMdd").format(startDate)%>";
		  //当前日期
		  var currDate = "<%= new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())%>"; --%>
		  if(currDate > startDate_F){
			  $.warn("合同生效日不能是今天之前的时间!");
			  return false;
		  }
		 //-------------------------
		
		
		
		
		
	     	if(paperConId!=''){
	     		if(paperConId.length>120){
	     		$.warn("纸合同编号:长度错误(最大长度为120位)");
	     		return false;
	     	}
	     }
	     	if(setlAcctName!=''){
	     		if(setlAcctName.length>32){
		     		$.warn("结算账户户名:长度错误(最大长度为32位)");
		     		return false;
		     	}
	     	}
	     	if(setlAcctNo!=''){
	     		// modify by lengjingyu  20180201   只校验长度   jira-2001	     		
	     		if(setlAcctNo.length>32){
		     		$.warn("结算账户账号最大长度只能是32位");
		     		return false;
		     	}
				// modify end
	     }
			var setlAcctBankNo =mchtContractInfo_dataset.getValue("setlAcctBankNo")	;    
			var setlCertType =mchtContractInfo_dataset.getValue("setlCertType")	;  
	           if(!(setlAcctBankNo.length==12)){
	     		$.warn("开户行号:请输入数字，并且只能有12位");
	     		return false;
	     	}
	     	if(setlCertNo!=''){
	     		if(setlCertType==''||setlCertType==null){
		     		$.warn("请输入账户证件类型!");
		     		return false;
	     		}else{
	     			//  modify by lengjingyu  20180201  证件类型与证通内部规范统一    jira-2008
	     			if(setlCertType=='10'){
	     			//  modify end
	     				if(!isDesc18.test(setlCertNo)){
				     		$.warn("账户证件类型为身份证时：请输入数字或末尾以x结束，并且长度为18位");
				     		return false;
				     	}
	     			}else{
	     				if(!isDesc32.test(setlCertNo)){
				     		$.warn("账户证件类型不是身份证时：请输入数字，并且最大长度为32位");
				     		return false;
				     	}
	     			}	     			
	     		}
	     	}
	     	var result = checkInput();
	     	if(!result){
	     		return;
	     	}
    		if(setlAcctInstitute== '' ||setlAcctInstitute==null){
    			$.warn("开户机构必须输入");
    			return false;
    		}
    	
		return true;
	}
	function mchtContractInfo_dataset_mchtId1_onSelect(v,record){
		if(record!=null){
			var mchtId = record.getValue("mchtId");
		}
		mchtContractInfo_dataset.setValue("paperConId","");
		mchtContractInfo_dataset.setValue("mchtId1",mchtId);
		mchtContractInfo_dataset.setValue("mchtSimpleName",record.getValue("mchtSimpleName"));
		mchtContractInfo_dataset.setValue("mchtType",record.getValue("mchtType"));
		var mchtType = mchtContractInfo_dataset.getValue("mchtType");
		if(mchtType == "05"){
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",true);
			mchtContractInfo_dataset.setValue("paperConId","");
			mchtContractInfo_dataset.setValue("setlAcctType",0);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
			mchtContractInfo_dataset.setFieldRequired("paperConId",false);
			showQlfOrNotAdd(false);
		}else{
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",false);
			mchtContractInfo_dataset.setValue("setlAcctType","");
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
			mchtContractInfo_dataset.setFieldRequired("paperConId",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
			showQlfOrNotAdd(true);
		}
	}
	//当结算账户类型发生变化时
	 function mchtContractInfo_dataset_setlAcctType_onSelect(v,record){
		if(record==null){
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
		}
		
		//0代表本行
		if('0'==v){
			//设置月结算日不可修改
			mchtContractInfo_dataset.setValue("setlCertType","");
			mchtContractInfo_dataset.setValue("setlCertNo","");
			mchtContractInfo_dataset.setValue("setlAcctInstitute","");
			mchtContractInfo_dataset.setValue("setlAcctInstituteName","");
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
		}else if('1'==v){//1代表他行
			//设置月结算日必须选择
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
//			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
		}else {
			mchtContractInfo_dataset.setValue("setlCertType","");
			mchtContractInfo_dataset.setValue("setlCertNo","");
			mchtContractInfo_dataset.setValue("setlAcctInstitute","");
			mchtContractInfo_dataset.setValue("setlAcctInstituteName","");
		}
	} 
	//当结算账户类型发生变化时
	function mchtContractInfo_dataset_setlCycle_onSelect(v,record){
		mchtContractInfo_dataset.setValue("specSetlDay","");
	}
	function btnAddSub_postSubmit(){
		$.success("添加成功，请点击服务继续完善信息！",function(){
			window_winAdd.close();
			mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);		
		});
	}
	function btnRtn_onClick(){
		mchtContractInfo_dataset.cancelRecord();
		window_winAdd.close();
	}
	function window_winAdd_beforeClose(wmf) {
		mchtContractInfo_dataset.cancelRecord();
		mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
		mchtContractInfo_dataset.setFieldRequired("mchtId1",false);
	}

/****************************************修改******************************************/	
   /**窗口打开前初始化form显示*/
	function window_winUpd_beforeOpen(win){
		var mchtType = mchtContractInfo_dataset.getValue("mchtType");
		mchtContractInfo_dataset.setFieldReadOnly("paperConId",false);
		//mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
		//mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
		//mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
		//mchtContractInfo_dataset.setFieldReadOnly("startDate",true);		
		//mchtContractInfo_dataset.setFieldReadOnly("conTerm",true);		
		//mchtContractInfo_dataset.setFieldReadOnly("extendFlag",true);		
		//mchtContractInfo_dataset.setFieldReadOnly("setlType",true);		
		//mchtContractInfo_dataset.setFieldReadOnly("setlSymbol",true);	
		//mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",true);
		
		
		if(mchtType == "05"){
			/* mchtContractInfo_dataset.setFieldRequired("paperConId",false);
			mchtContractInfo_dataset.setValue("paperConId",mchtContractInfo_dataset.getValue("mchtId"));
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",true);
			mchtContractInfo_dataset.setValue("setlAcctType",0);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",true);
			mchtContractInfo_dataset.setValue("setlCertType","");
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setValue("setlCertNo","");
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setValue("setlAcctInstitute","");
			mchtContractInfo_dataset.setValue("setlAcctInstituteName","");
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);  */
			showQlfOrNotUpd(false);
		}
		else{
			/* mchtContractInfo_dataset.setFieldRequired("paperConId",true);
			mchtContractInfo_dataset.setFieldReadOnly("paperConId",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false); */
			showQlfOrNotUpd(true);		
		}
		var setlAcctType = mchtContractInfo_dataset.getValue("setlAcctType");
		if('0'==setlAcctType){
			//设置月结算日不可修改
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",true);
			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",true);
		}else if('1'==setlAcctType){//1代表他行
			//设置月结算日必须选择
			mchtContractInfo_dataset.setFieldReadOnly("setlCertType",false);
			mchtContractInfo_dataset.setFieldReadOnly("setlCertNo",false);
//			mchtContractInfo_dataset.setFieldReadOnly("setlAcctInstitute",false);
		}
	}
    /**打开修改窗口*/
	function btnUpd_onClick(){
		  var conState = mchtContractInfo_dataset.getValue("conState");
		  var extendFlag = mchtContractInfo_dataset.getValue("extendFlag");
		  
		   if(conState =='03'){
			 $.warn("该条记录已经过期，不允许修改！"); 
			 return false;
		   }
		   if(conState =='99'){
				 $.warn("该条记录已经中止 ，不允许修改！"); 
				 return false;
			   }
		    if(conState == '04'||conState == '06'||conState == '08'||conState == '10'){
		    	$.warn("该条记录在待审核当中，不允许修改！ ");
	     		return false;
		    }
		    if(conState =='12'||conState =='13'){
				 $.warn("该合同状态不允许修改！"); 
				 return false;
			   }
		    
		var setlCycle = mchtContractInfo_dataset.getValue("setlCycle");
		if('0001'==setlCycle){
			//设置月结算日不可修改
			mchtContractInfo_dataset.setFieldReadOnly( "specSetlDay", true);
		}else if('0002'==setlCycle){
			//设置月结算日必须选择
			mchtContractInfo_dataset.setFieldReadOnly( "specSetlDay", false);
		}
		
		/* mchtContractInfo_dataset.setFieldRequired( "setlAcctAreaCode", true); */
		window_winUpd.open();
		
	}
	function btnUpdSub_onClickCheck(){
		var paperConId = mchtContractInfo_dataset.getValue("paperConId");
		var setlAcctName = mchtContractInfo_dataset.getValue("setlAcctName");
		var setlAcctNo = mchtContractInfo_dataset.getValue("setlAcctNo");
		var setlCertNo = mchtContractInfo_dataset.getValue("setlCertNo");
		var mchtType = mchtContractInfo_dataset.getValue("mchtType");
		var setlBankPhone = mchtContractInfo_dataset.getValue("setlBankPhone");//银行预留手机号
		
		//-------------------------当结算账户类型为对私才必输-------------------------------------
		var accountType = mchtContractInfo_dataset.getValue("accountType");
		var accountBoss = mchtContractInfo_dataset.getValue("accountBoss");
		if(accountType === "2"){
			if((accountBoss == "") || (accountBoss == null)){
				$.warn("当结算账户类型为对私时结算账户人身份必输!!");
				return false;
			}
		}
		//-------------------------当结算账户类型为对私才必输-------------------------------------
		
		if((setlBankPhone == "") || (setlBankPhone == null)){

		}else{
		if(!(isCellPhone.test(setlBankPhone))){
			$.warn("【手机号】格式错误,必须是11位手机号");
			return false;
		}			
		}
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
// 		 var limitOne = mchtContractInfo_dataset.getValue("limitOne");
// 		var limitDay = mchtContractInfo_dataset.getValue("limitDay");
// 		mchtContractInfo_dataset.setParameter("limitOne",limitOne);
// 		mchtContractInfo_dataset.setParameter("limitDay",limitDay);
// 		if(limitOne!=''){
// 			if(!isDesc12.test(limitOne)){
// 	     		$.warn("单笔交易限额:请输入数字，并且最大长度为12位");
// 	     		return false;
// 		 }
// 		}
// 		if(limitDay!=''){
// 				if(!isDesc12.test(limitDay)){
// 		     		$.warn("日交易限额:请输入数字，并且最大长度为12位");
// 		     		return false;
// 		  }
// 		} 
	     	if(paperConId!=''){
	     		if(paperConId.length>120){
	     		$.warn("纸合同编号:长度错误(最大长度为120位)");
	     		return false;
	     	}
	     }
			var setlAcctBankNo =mchtContractInfo_dataset.getValue("setlAcctBankNo")	     	
			var setlCertType =mchtContractInfo_dataset.getValue("setlCertType")
		     if(!(setlAcctBankNo.length==12)){
		     		$.warn("开户行号:请输入数字，并且只能有12位");
		     		return false;
		     	}
	     	if(setlAcctName!=''){
	     		if(setlAcctName.length>32){
		     		$.warn("结算账户户名:长度错误(最大长度为32位)");
		     		return false;
		     	}
	     	}
	     	if(setlAcctNo!=''){
				// modify by lengjingyu 20180201   只校验长度     jira-2001	     		
	     		if(setlAcctNo.length>32){
		     		$.warn("结算账户账号最大长度只能是32位");
		     		return false;
		     	}
				// modify end
	     }
	     	if(setlCertNo!=''){
	     		if(setlCertType==''||setlCertType==null){
		     		$.warn("请输入账户证件类型!");
		     		return false;
	     		}else{
	     			// modify by lengjingyu 证件类型与证通内部规范统一    jira-2008
	     			if(setlCertType=='10'){
	     			// modify by end
	     				if(!isDesc18.test(setlCertNo)){
				     		$.warn("账户证件类型为身份证时：请输入数字或末尾以x结束，并且长度为18位");
				     		return false;
				     	}
	     			}else{
	     				if(!isDesc32.test(setlCertNo)){
				     		$.warn("账户证件类型不是身份证时：请输入数字，并且最大长度为32位");
				     		return false;
				     	}
	     			}	     			
	     		}	     		
	     	}
		
    	
    	var setlAcctType = mchtContractInfo_dataset.getValue("setlAcctType");	    	
    	if(setlAcctType=='1'){
    		//当预算类型不为00-无
//    		if(mchtContractInfo_dataset.getValue("setlCertNo") == '' ){
//    			$.warn("账户证件号码必须输入");
//    			return false;
//   		}
//    		if(mchtContractInfo_dataset.getValue("setlCertType") == '' ){
//    			$.warn("账户证件类型必须输入");
//    			return false;
//    		}
    		if(mchtContractInfo_dataset.getValue("setlAcctInstitute") == '' ){
    			$.warn("开户机构必须输入");
    			return false;
    		}
    	}
    
    	var setlCycle = mchtContractInfo_dataset.getValue("setlCycle");
    	var specSetlDay = mchtContractInfo_dataset.getValue("specSetlDay");
    	if(setlCycle=='01'){
    		if(!isDesc3.test(specSetlDay)){
    			$.warn("请输入1~365天的结算日期");
    			return false;
    		}else if(parseInt(specSetlDay)<1 ||parseInt(specSetlDay) >365){
    			$.warn("请输入1~365天的结算日期");
    			return false;
    		}
    		
    	}else if(setlCycle=='02'){
    		if(!isDesc1.test(specSetlDay)){
    			$.warn("请输入1~7天的结算日期");
    			return false;
    		}else if(parseInt(specSetlDay) <1 || parseInt(specSetlDay)>7){
    			$.warn("请输入1~7天的结算日期");
    			return false;
    		}
    	}
    	else if(setlCycle=='03'){
    		if(!isDesc2.test(specSetlDay)){
    			$.warn("请输入1~31天的结算日期");
    			return false;
    		}else if(parseInt(specSetlDay) <1 || parseInt(specSetlDay)>31){
    			$.warn("请输入1~31天的结算日期");
    			return false;
    		}
    	}
		return true;
	}
	function btnUpdSub_postSubmit(){
		$.success("修改成功，请点击[服务]继续完善信息！",function(){
			window_winUpd.close();
			mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);			
		});
	}
	
    //格式化日期
    function formatDate(date,format){
      var paddNum = function(num){
        num += "";
        return num.replace(/^(\d)$/,"0$1");
      }
      //指定格式字符
      var cfg = {
         yyyy : date.getFullYear() //年 : 4位
        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        ,d  : date.getDate()   //日 : 如果1位的时候不补0
        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
        ,hh : date.getHours()  //时
        ,mm : date.getMinutes() //分
        ,ss : date.getSeconds() //秒
      }
      format || (format = "yyyy-MM-dd hh:mm:ss");
      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    } 
	function btnRtn_onClick(){
		mchtContractInfo_dataset.cancelRecord();
		window_winUpd.close();
	}
	function window_winUpd_beforeClose(wmf) {
		mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
		mchtContractInfo_dataset.cancelRecord();
		showQlfOrNotUpd(true);
		}
	/****************************************中止 ******************************************/	

 function btnFrz_onClick(){
	 var state=mchtContractInfo_dataset.getValue("conState");
	 var chlSysNo = mchtContractInfo_dataset.getValue("chlSysNo");
    if(state == '04'||state == '05'||state == '06'||state == '08'||state == '10'){
	    	$.warn("该条记录在待审核当中，不允许中止或恢复！ ");
   		return false;
	    }
   /*  if('12'==state ||'13'==state ){
   		$.warn("代理商未提审,不允许中止或恢复");
   		return false;
   	} */
   	if('01'==state ||'03'==state ){
   		$.warn("签订中/已到期合同不允许中止或恢复");
   		return false;
   	}
   	
   	if('00'==state ||'02'==state){
   		$.confirm("是否确定中止合同?", function() {
   			btnFrzSub.click();
   		}, function() {
   			return false;
   		});	
   	}
   	if('99'==state ){
   		$.confirm("是否确定恢复合同?", function() {
   			btnFrzSub.click();
   		}, function() {
   			return false;
   		});	
   	}
  }
	
   	function btnFrzSub_postSubmit() {
		$.success("操作成功!", function() {
			//定义到当前页
			mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
		});
	}
   	
	/*********************************服务****************************************/
   	function btnServ_onClick(){
   	 var conState=mchtContractInfo_dataset.getValue("conState");
   	 if(conState == null || conState ==''){
   		$.warn("请先选择一条合同记录！");
   		return false;
   	 }
    /*  if(conState == '12'||conState == '13'){
 	    	$.warn("代理商未提审，不允许配置服务！ ");
    		return false;
 	   } */
     if(conState == '04'||conState == '06'||conState == '08'||conState == '10'){
 	    	$.warn("该条记录在待审核当中，不允许配置服务！ ");
    		return false;
 	   }
     if(conState =='03'){
    	 $.warn("该合同记录已经过期 ，不允许配置服务！ ");
 		return false;
     }
     if(conState =='99'){
    	 $.warn("该合同记录已经中止，不允许配置服务！ ");
 		return false;
     }
     if(conState =='12'||conState =='13'){
		 $.warn("该合同状态不允许配置服务！"); 
		 return false;
	   }
     
        //根据合同ID去刷新数据集 
   		ConToPro_dataset.setParameter("conId", mchtContractInfo_dataset.getValue("conId"));
   		ConToPro_dataset.flushData();
   		mchtContractInfo_dataset.setFieldReadOnly( "mchtId", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "mchtSimpleName", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "conId", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "paperConId", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "startDate", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "extendFlag", true);
   		mchtContractInfo_dataset.setFieldReadOnly( "conTerm", true);
   	}

   	//窗体之间的切换  
   	var isAdd = false; //新增修改标志：true 为新增；false 为修改；
   	var isChanged = false; //数据改动标志： true 已修改未保存；false 为未修改 ；
   
   	//点击校验 
   	function add_onClickCheck(){
   		if(isChanged){
   			$.warn("请先保存当前配置后再新增配置！");
   			return false;
   		}
   		return true;
   	} 
   	
    var ids = 1;
	var str=99;
	var addlen=0;
    function add_onClick(){
    	isAdd = true;//新增修改标志改为：true-新增    level=one 指的事新增添加的tab
    	isChanged = true;//数据改动标志改为：true-已修改未保存 */ 
      	var conId=mchtContractInfo_dataset.getValue("conId");
    	var mchtId = mchtContractInfo_dataset.getValue("mchtId");	
    	tabs_ConService.add("id" + ids,"请选择支付产品","/pages/payPmp/mchtMng/conService.jsp?conId="+conId+"&level=two");
		str=ids;
		addlen++;
    	ids ++;
    }
    //设置tab标题 
    function setTabTitle(title){
    	var id = ids-1;    	
    	tabs_ConService.setTitle("id" + id,title);
    }
    
    //tab的切换事件 
   	function tabs_ConService_beforeTabChange(tabid) {
   		tabs_ConService.refresh(tabid);
   	    	 if(isChanged == true && ids!=tabid.replace("id","")){
   				$.warn("请保存合同产品配置");
   				return false;
   	    }
   	    	 return true;
   	}  
    //tab关闭事件
    var outWinClose = false; // 最外面的窗体关闭 
	function tabs_ConService_beforeTabClose(tabid) {
		if(outWinClose==false){
			var flag = confirm("确定是否删除此产品配置？");
			if(flag){
				var delFlag = tabid.substring(0,2);
				if(isChanged == true){
					if(delFlag != "id"){
						return false;
					}else{
						isChanged = false;
						return true;
					}
				}
				else{
						var del = tabs_ConService.getSelectedId().substring(3);//截取选中的tab ID获取到产品ID 
						var conId=mchtContractInfo_dataset.getValue("conId");
						ConToPro_dataset.setParameter("conId",conId);
						ConToPro_dataset.setParameter("prodId",del);				
						delTab.click(); //已经配置好的产品删除事件 
						return true;
				}
		}
			else {
				return false;
			}
		}else {
			if(isAdd == true && isChanged == true ){
				isAdd =false;
				isChanged = false;
			}
			return true;
		}
   	}  
	 
	function ConToPro_dataset_flushDataPost(dataset) {
		//加载完数据后动态加载tab页
		var len= ConToPro_dataset.length;
		while(len>0){
			var prodName = ConToPro_dataset.getValue("prodName");
			
			//var p2 = encodeURI(prodName);
			
			var p2 = encodeURI(encodeURI(prodName));
			var url = "/pages/payPmp/mchtMng/conService.jsp?conId="
					  +mchtContractInfo_dataset.getValue("conId")
					  +"&prodId="+ConToPro_dataset.getValue("prodId")
					  +"&prodName="+p2
					  +"&level=one"
					 ;
			
			tabs_ConService.add("tab"+ConToPro_dataset.getValue("prodId"),prodName,url);
			ConToPro_dataset.deleteRecord();
			ids++;
			len--;
		}
		window_winService.open();
	}
	
	function window_winService_beforeOpen(){
		//打开合同服务窗口开户行区划代码非必需
		mchtContractInfo_dataset.setFieldRequired( "setlAcctAreaCode", false);
	}
	
	// 往后台传递修改标志
	function getAllowModify(){
		return isAdd;
	}
	
	//接受子类返回的参数值用来操作
	function returnBack(prodId) {
		isAdd = false;
		isChanged = false;
        var oldTabId = tabs_ConService.getSelectedId();		
		$("li[tabid="+oldTabId+"]").attr("tabid","tab"+prodId);
	}
	function window_winService_beforeClose(){
		//关闭所有的tab
		outWinClose = true;
		//开户行区划代码必输
		mchtContractInfo_dataset.setFieldRequired( "setlAcctAreaCode", true);
	}
   	function btnSerSub_postSubmit() {
		$.success("操作成功!", function() {
			window_winService.close();
			mchtContractInfo_dataset.flushData(mchtContractInfo_dataset.pageIndex);
		});
	}
	/**窗口关闭后清除数据*/
	function window_winService_afterClose(){
		var len= ConToPro_dataset.length;
		while(len>0){
			tabs_ConService.setClosable(len, true);
			len--
		}
		while(addlen!=0){
		tabs_ConService.close("id" + str);
		str--;	
		addlen--;
		}
		tabs_ConService.closeAll();
		outWinClose = false;
	}
   	/***************清算方式下拉列表********************/
	function mchtContractInfo_dataset_mchtId1_onSelect(dropdown, record) {
		mchtContractInfo_dataset.setValue("setlType", "");
		mchtContractInfo_dataset.setValue("setlTypeName", "");
		mchtSetlType_dataset.setValue("qmchtId","");
		return true;
	}
	
	function mchtContractInfo_dataset_setlType_beforeOpen(dropdown, dpds) {
		if((dpds != null) && (dpds != "")){
			var mchtId1 = mchtContractInfo_dataset.getValue("mchtId1");
			var mchtId  = mchtContractInfo_dataset.getValue("mchtId");
			if (((mchtId1 == null) || (mchtId1 == ""))&&((mchtId == null) || (mchtId == ""))) {
				$.warn("请先选择商户号.");
				return false;
			}
			dpds.setParameter("qmchtId", (mchtId1 == null) || (mchtId1 == "")?mchtId:mchtId1);
			setlType_DropDown.cache = false;
		}		
		return true;
	}
	
	function mchtContractInfo_dataset_setlType_onSelect(dropdown, record) {
		
		
		if((record != null) && (record != "")){
			mchtContractInfo_dataset.setValue("setlType", record
					.getValue("setlType"));
			mchtContractInfo_dataset.setValue("setlTypeName", record
					.getValue("setlTypeName"));
		}		
		return true;
	}
	
	
	function mchtContractInfo_dataset_afterChange(dataset,field){
		
		if(field.fieldName == 'setlAcctType'){
		  mchtContractInfo_dataset.setValue("setlAcctName","");
		}
		if(field.fieldName == 'setlAcctNo'){
		
			var setlAcctType=mchtContractInfo_dataset.getValue("setlAcctType");
		
			
	       if(setlAcctType=="0"){
	    	
			var setlAcctNo=mchtContractInfo_dataset.getValue("setlAcctNo");
			if(setlAcctNo!=null&&setlAcctNo!=""){
				
			setlAcctNoForContract_dataset.setParameter("qsetlAcctNo", setlAcctNo);
			
			
			setlAcctNoForContract_dataset.flushData();
	       
	       }
			
	       }else if(setlAcctType==""||mchtContractInfo_dataset.getValue("setlAcctType")==null){
	    	
	    		$.warn("请先选择结算账户类型.");

	    		mchtContractInfo_dataset.setValue("setlAcctName","");
	       }else if(setlAcctType=="1"){
		
	     
	       }
		}
		

	}

	
	
	function setlAcctNoForContract_dataset_flushDataPost(dataset) {
	
		mchtContractInfo_dataset.setValue("setlAcctName",setlAcctNoForContract_dataset.getValue("setlAcctName"));
		
	}
	</script>
	 	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/QContractProd.js"></script>
	
</snow:page>