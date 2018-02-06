<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="通道接入信息">
	<!-- 数据集加载 -->
		<!-- 主要信息数据集 -->
		<snow:dataset id="mainInfo" path="com.ruimin.ifs.pmp.chnlMng.dataset.chnlAcsInfo" init="true" submitMode="current" ></snow:dataset>
		<!-- 功能清单数据集【新增】 -->
		<snow:dataset id="funcTreeAdd" path="com.ruimin.ifs.pmp.chnlMng.dataset.chnlAcsFuncTree" init="false"></snow:dataset>
		<!-- 功能清单数据集【修改】 -->
		<snow:dataset id="funcTreeUpd" path="com.ruimin.ifs.pmp.chnlMng.dataset.chnlAcsFuncTree" init="false"></snow:dataset>
		<!-- 功能清单数据集【详情】 -->
		<snow:dataset id="funcTreeDetail" path="com.ruimin.ifs.pmp.chnlMng.dataset.chnlAcsFuncTree" init="false"></snow:dataset>
		
	<!-- 查询界面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="mainInfo" label="查询条件" fieldstr="qpagyNo,qmainMchtAcsType,qmainMchtNo,qmainMchtName,qmainMchtStat"></snow:query>		
		<!-- 查询展示表单 -->
		<snow:grid id="gridMain" dataset="mainInfo" label="通道接入信息" fieldstr="pagyName,mainMchtAcsType,mainMchtNo,mainMchtName,mainMchtStat,opr"
		height = "99%" paginationbar="btnAdd,btnUpd,btnStp,btnPagyCore"></snow:grid>
		
	<!-- 弹出窗口 -->
		<!-- 新增窗口 -->
		<snow:window id="winAdd" title="新增信息"  modal="true" closable="true" width="900" buttons="btnAddSub">
			<!-- No1 基本信息模块 -->
			<snow:form id="baseInfoAdd" label="基本信息" dataset="mainInfo" fieldstr="pagyNameSel,mainMchtAcsType,mainMchtNo,mainMchtName,mainMchtStat,mainMchtPublicNo,mainMchtSetlCycleType,mainMchtSetlCycle,mainMchtSetlAccno,mainMchtSetlTm,mainMchtRateCode,mainMchtMccCode,mainMchtMccName"></snow:form>
			<!-- No2 证书信息模块 -->
			<snow:form id="certInfoAdd" label="证书信息" dataset="mainInfo" fieldstr="encryptType" collapsible="false"></snow:form>
				<!-- No2.1 加密算法【RSA】 --> 
				<snow:form id="certRsaAdd" dataset="mainInfo" fieldstr="certifiName,certifiPasswd,certifiDate,certifiEndDate"></snow:form>
				<div id="btnCertAddSubDiv" style="height: 60x;"><snow:button id="btnCertAddSub" dataset="mainInfo" hidden="false"></snow:button></div>
					<!-- No2.1.1 加载签名 --> 
					<snow:form id="certNeedSignAdd" dataset="mainInfo" fieldstr="needSign"></snow:form>
					<snow:form id="certSignAdd" dataset="mainInfo" fieldstr="签名生效日期{md5Date},签名失效日期{md5EndDate},md5Passwd"></snow:form>
				<!-- No2.2 加密算法【MD5】 --> 
				<snow:form id="certMd5Add" dataset="mainInfo" fieldstr="md5Date,md5EndDate,md5Passwd"></snow:form>			
			<!-- No3 功能清单模块 -->
			<div id="treeFuncAddDiv">功能清单<snow:tree id="treeFuncAdd" dataset="funcTreeAdd" checkboxs="true"></snow:tree></div>		
			<!-- No4 加载提交按钮 -->
			<snow:button id="btnAddSub" dataset="mainInfo" hidden="true"></snow:button>
		</snow:window>
		
		<!-- 修改窗口 -->
		<snow:window id="winUpd" title="修改信息"  modal="true" closable="true" width="900" buttons="btnUpdSub">
			<!-- No1 基本信息模块 -->
			<snow:form id="baseInfoUpd" label="基本信息" dataset="mainInfo" fieldstr="pagyNameSel,mainMchtAcsType,mainMchtNo,mainMchtName,mainMchtStat,mainMchtPublicNo,mainMchtSetlCycleType,mainMchtSetlCycle,mainMchtSetlAccno,mainMchtSetlTm,mainMchtRateCode,mainMchtMccCode,mainMchtMccName"></snow:form>
			<!-- No2 证书信息模块 -->
			<snow:form id="certInfoUpd" label="证书信息" dataset="mainInfo" fieldstr="encryptType" collapsible="false"></snow:form>
				<!-- No2.1 加密算法【RSA】 --> 
				<snow:form id="certRsaUpd" dataset="mainInfo" fieldstr="certifiName,certifiPasswd,certifiDate,certifiEndDate"></snow:form>
				<div id="btnCertUpdSubDiv" style="height: 60x;"><snow:button id="btnCertUpdSub" dataset="mainInfo" hidden="false"></snow:button></div>
					<!-- No2.1.1 加载签名 --> 
					<snow:form id="certNeedSignUpd" dataset="mainInfo" fieldstr="needSign"></snow:form>
					<snow:form id="certSignUpd" dataset="mainInfo" fieldstr="签名生效日期{md5Date},签名失效日期{md5EndDate},md5Passwd"></snow:form>
				<!-- No2.2 加密算法【MD5】 --> 
				<snow:form id="certMd5Upd" dataset="mainInfo" fieldstr="md5Date,md5EndDate,md5Passwd"></snow:form>			
			<!-- No3 功能清单模块 -->		
			<div id="treeFuncUpdDiv">功能清单<snow:tree id="treeFuncUpd" dataset="funcTreeUpd" checkboxs="true"></snow:tree></div>	
			<!-- No4 加载提交按钮 -->
			<snow:button id="btnUpdSub" dataset="mainInfo" hidden="true"></snow:button>
		</snow:window>
		
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="详情信息"  modal="true" closable="true" width="900">
			<!-- No1 基本信息模块 -->
			<snow:form id="baseInfoDetail" label="基本信息" dataset="mainInfo" fieldstr="pagyNameSel,mainMchtAcsType,mainMchtNo,mainMchtName,mainMchtStat,mainMchtPublicNo,mainMchtSetlCycleType,mainMchtSetlCycle,mainMchtSetlAccno,mainMchtSetlTm,mainMchtRateCode,mainMchtMccCode,mainMchtMccName"></snow:form>
			<!-- No2 证书信息模块 -->
			<snow:form id="certInfoDetail" label="证书信息" dataset="mainInfo" fieldstr="encryptType" collapsible="false"></snow:form>
				<!-- No2.1 加密算法【RSA】 --> 
				<snow:form id="certRsaDetail" dataset="mainInfo" fieldstr="certifiName,certifiPasswd,certifiDate,certifiEndDate"></snow:form>
					<!-- No2.1.1 加载签名 --> 
					<snow:form id="certNeedSignDetail" dataset="mainInfo" fieldstr="needSign"></snow:form>
					<snow:form id="certSignDetail" dataset="mainInfo" fieldstr="签名生效日期{md5Date},签名失效日期{md5EndDate},md5Passwd"></snow:form>
				<!-- No2.2 加密算法【MD5】 --> 
				<snow:form id="certMd5Detail" dataset="mainInfo" fieldstr="md5Date,md5EndDate,md5Passwd"></snow:form>
			<!-- No3 功能清单模块 -->
			<div id="treeFuncDetailDiv">功能清单<snow:tree id="treeFuncDetail" dataset="funcTreeDetail" checkboxs="false"></snow:tree></div>				
		</snow:window>
		
	<!-- 启用/停用 -->
	<div style="display: none;"><snow:button id="btnStpSub" dataset="mainInfo"></snow:button></div>
	
		
	<script type="text/javascript">
/****************************************公有声明******************************************/ 
 	/*****************正则表达式*****************/
 	/**验证时间格式*/
 	var isTime = /^(?:[01]\d|2[0-3])(?::[0-5]\d){2}$/;//时分秒，hh:mm:ss
 	var isDay = /^([1-9][0-9]?|[12][0-9]{2}|3[0-5][0-9]|36[0-5])$/;//天,1~365
 	var isWeek = /^[1-7]$/;//星期，1~7
 	var isMonth = reg=/^([1-9]|[12][0-9]|3[01])$/;//月度，1~31
 	
 	/**验证字符串长度*/
 	var isDesc50 = /^\S{1,50}$/;//最大长度50位 
	var isDesc42 = /^\S{1,42}$/;//最大长度42位 
	var isDesc40 = /^\S{1,40}$/;//最大长度40位 
	var isDesc32 = /^\S{1,32}$/;//最大长度32位 
	var isDesc21 = /^\S{1,21}$/;//最大长度21位 
	var isDesc18 = /^\S{1,18}$/;//最大长度18位
	var isDesc13 = /^\S{1,13}$/;//最大长度13位
	var isDesc10 = /^\S{1,10}$/;//最大长度10位 
	var isDesc100 = /^\S{1,100}$/;//最大长度100位 

	
 	/*****************公共常量*****************/
 	var write = "write";//常量-读
 	var read = "read";//常量-写	
 	var rsa = "01";//常量-RSA加密算法
 	var md5 = "02";//常量-MD5加密算法
 	var init = "init";//常量-初始状态
 	var needSign = "01";//常量-需要签名
 	var noSign = "00";//常量-不需要签名 
 	var oprType_ADD = "ADD";//操作类型-新增
 	var oprType_UPD = "UPD";//操作类型-修改
 	var oprType_DETAIL = "DETAIL";//操作类型-详情
 	
	/*****************公共方法*****************/
	/**控制字段【公众号/应用ID】读写方式*/
	function publicReadOrWrite(showFlag){
		if(showFlag == "write"){
			mainInfo_dataset.setFieldReadOnly("mainMchtPublicNo",false);
			mainInfo_dataset.setFieldRequired("mainMchtPublicNo",true);
		}else{
			mainInfo_dataset.setFieldRequired("pagyNameSel",true);
			mainInfo_dataset.setFieldReadOnly("mainMchtPublicNo",true);
			mainInfo_dataset.setFieldRequired("mainMchtPublicNo",false);
			mainInfo_dataset.setValue("mainMchtPublicNo","");//清除字段【公众号/应用ID】的值
		}
	}
 		
 	/**通道名称【查询】下拉事件前，向后台发送一个标识，只查询启用状态下的通道*/
 	function mainInfo_interface_dataset_qpagyNo_beforeOpen(dropdown, dpds) {
 		dpds.setParameter("queryType","onlyUsing");
 		return true;
 	}
	
 	/**通道名称下拉事件前，向后台发送一个标识，只查询启用状态下的通道*/
 	function mainInfo_dataset_pagyNameSel_beforeOpen(dropdown, dpds) {
 		dpds.setParameter("queryType","onlyUsing");
 		return true;
 	}
 	
	/**通道名称下拉事件（当【通道名称】选择"微信"时，字段【公众号/应用ID】必须输入）*/
	function mainInfo_dataset_pagyNameSel_onSelect(value,record){		
		if(record == null){
			publicReadOrWrite(read);
			mainInfo_dataset.setValue("pagyNameSel","");
			mainInfo_dataset.setValue("pagyName","");
			mainInfo_dataset.setValue("pagyNo",""); 	
			funcTreeAdd_dataset.setParameter("qpagyNo","");
			funcTreeAdd_dataset.flushData(funcTreeAdd_dataset.pageIndex);
		}else{
			var pagyNo = record.getValue("pagyNo");
			var pagyName = record.getValue("pagyName");
			if((pagyNo == "302")){
				publicReadOrWrite(write);
			}else{
				publicReadOrWrite(read);
			}
			mainInfo_dataset.setValue("pagyNameSel",pagyName);
			mainInfo_dataset.setValue("pagyName",pagyName);
			mainInfo_dataset.setValue("pagyNo",pagyNo); 
			funcTreeAdd_dataset.setParameter("qpagyNo",pagyNo);
			funcTreeAdd_dataset.flushData(funcTreeAdd_dataset.pageIndex);
		}				
	}  
	
	/**清算周期下拉事件（当【清算周期】改变选择时，清除字段【清算日】）*/
	function mainInfo_dataset_mainMchtSetlCycleType_onSelect(value,record){	
		 mainInfo_dataset.setValue("mainMchtSetlCycle","");
	}
	
	/**清空证书字段*/
	function removeCertInfo(){
		mainInfo_dataset.setValue("certifiDate",""); 
		mainInfo_dataset.setValue("certifiEndDate",""); 
		mainInfo_dataset.setValue("certifiName",""); 
		mainInfo_dataset.setValue("certifiPasswd",""); 
		mainInfo_dataset.setValue("needSign",""); 
		mainInfo_dataset.setValue("md5Date",""); 
		mainInfo_dataset.setValue("md5EndDate",""); 
		mainInfo_dataset.setValue("md5Passwd",""); 
	}
	
	/**清空签名字段*/
	function removeSignInfo(){
		mainInfo_dataset.setValue("md5Date",""); 
		mainInfo_dataset.setValue("md5EndDate",""); 
		mainInfo_dataset.setValue("md5Passwd","");  
	}
	
	/**控制字段【加密算法】对应form显示*/
	function encFormShowOrNotAdd(showFlag){		 
		if(showFlag == rsa){
			 $("#certRsaAdd").css("display","block");
			 $("#certMd5Add").css("display","none");
			 $("#certNeedSignAdd").css("display","block"); 
			 $("#certSignAdd").css("display","none"); 
			 $("#btnCertAddSubDiv").css("display","block");
		}else if(showFlag == md5){
			 $("#certMd5Add").css("display","block");
			 $("#certRsaAdd").css("display","none");
			 $("#certNeedSignAdd").css("display","none"); 
			 $("#certSignAdd").css("display","none"); 	
			 $("#btnCertAddSubDiv").css("display","none");
		}else{
			 $("#certRsaAdd").css("display","none");
			 $("#certMd5Add").css("display","none"); 
			 $("#certNeedSignAdd").css("display","none"); 
			 $("#certSignAdd").css("display","none"); 
			 $("#btnCertAddSubDiv").css("display","none");
		 }
	 }	 
	
	/**控制字段【加密算法】对应form显示*/
	function encFormShowOrNotUpd(showFlag){		 
		if(showFlag == rsa){
			 $("#certRsaUpd").css("display","block");
			 $("#certMd5Upd").css("display","none");
			 $("#certNeedSignUpd").css("display","block"); 
			 $("#certSignUpd").css("display","none"); 
			 $("#btnCertUpdSubDiv").css("display","block");
		}else if(showFlag == md5){
			 $("#certMd5Upd").css("display","block");
			 $("#certRsaUpd").css("display","none");
			 $("#certNeedSignUpd").css("display","none"); 
			 $("#certSignUpd").css("display","none"); 	
			 $("#btnCertUpdSubDiv").css("display","none");
		}else{
			 $("#certRsaUpd").css("display","none");
			 $("#certMd5Upd").css("display","none"); 
			 $("#certNeedSignUpd").css("display","none"); 
			 $("#certSignUpd").css("display","none"); 
			 $("#btnCertUpdSubDiv").css("display","none");
		 }
	 }	 
	
	/**控制字段【加密算法】对应form显示*/
	function encFormShowOrNotDetail(showFlag){		 
		if(showFlag == rsa){
			 $("#certRsaDetail").css("display","block");
			 $("#certMd5Detail").css("display","none");
			 $("#certNeedSignDetail").css("display","block"); 
			 $("#certSignDetail").css("display","none"); 
			 $("#btnCertDetailSubDiv").css("display","block");
		}else if(showFlag == md5){
			 $("#certMd5Detail").css("display","block");
			 $("#certRsaDetail").css("display","none");
			 $("#certNeedSignDetail").css("display","none"); 
			 $("#certSignDetail").css("display","none"); 	
			 $("#btnCertDetailSubDiv").css("display","none");
		}else{
			 $("#certRsaDetail").css("display","none");
			 $("#certMd5Detail").css("display","none"); 
			 $("#certNeedSignDetail").css("display","none"); 
			 $("#certSignDetail").css("display","none"); 
			 $("#btnCertDetailSubDiv").css("display","none");
		 }
	 }
	
	/**控制字段【加密算法】对应字段必须输入*/
	function encFormReqOrNot(showFlag){	
		mainInfo_dataset.setFieldRequired("encryptType",true);
		if(showFlag == rsa){
			mainInfo_dataset.setFieldRequired("certifiDate",true);
			mainInfo_dataset.setFieldRequired("certifiEndDate",true);
			mainInfo_dataset.setFieldRequired("certifiName",true);
			mainInfo_dataset.setFieldRequired("certifiPasswd",true);
			mainInfo_dataset.setFieldRequired("md5Date",false);
			mainInfo_dataset.setFieldRequired("md5EndDate",false);
			mainInfo_dataset.setFieldRequired("md5Passwd",false);
		}else if(showFlag == md5){
			mainInfo_dataset.setFieldRequired("certifiDate",false);
			mainInfo_dataset.setFieldRequired("certifiEndDate",false);
			mainInfo_dataset.setFieldRequired("certifiName",false);
			mainInfo_dataset.setFieldRequired("certifiPasswd",false);
			mainInfo_dataset.setFieldRequired("md5Date",true);
			mainInfo_dataset.setFieldRequired("md5EndDate",true);
			mainInfo_dataset.setFieldRequired("md5Passwd",true);	
		}else{			
			mainInfo_dataset.setFieldRequired("certifiDate",false);
			mainInfo_dataset.setFieldRequired("certifiEndDate",false);
			mainInfo_dataset.setFieldRequired("certifiName",false);
			mainInfo_dataset.setFieldRequired("certifiPasswd",false);
			mainInfo_dataset.setFieldRequired("md5Date",false);
			mainInfo_dataset.setFieldRequired("md5EndDate",false);
			mainInfo_dataset.setFieldRequired("md5Passwd",false);			 
		 }
	}
	
	/**加密算法下拉事件（当【加密算法】改变选择时，显示对应form）*/
	 function mainInfo_dataset_encryptType_onSelect(value,record){	
		encFormShowOrNotAdd(value);
		encFormShowOrNotUpd(value);
		encFormReqOrNot(value);
		removeCertInfo();
	}
	
	/**控制字段【是否需要签名】对应form显示*/
	function signFormShowOrNotAdd(showFlag){
		if(showFlag == needSign){
			$("#certSignAdd").css("display","block"); 
			mainInfo_dataset.setFieldRequired("md5Date",true);
			mainInfo_dataset.setFieldRequired("md5EndDate",true);
			mainInfo_dataset.setFieldRequired("md5Passwd",true);
		}else{
			$("#certSignAdd").css("display","none"); 
			mainInfo_dataset.setFieldRequired("md5Date",false);
			mainInfo_dataset.setFieldRequired("md5EndDate",false);
			mainInfo_dataset.setFieldRequired("md5Passwd",false);
		}
	}
	
	/**控制字段【是否需要签名】对应form显示*/
	function signFormShowOrNotUpd(showFlag){
		if(showFlag == needSign){
			$("#certSignUpd").css("display","block"); 
			mainInfo_dataset.setFieldRequired("md5Date",true);
			mainInfo_dataset.setFieldRequired("md5EndDate",true);
			mainInfo_dataset.setFieldRequired("md5Passwd",true);
		}else{
			$("#certSignUpd").css("display","none"); 
			mainInfo_dataset.setFieldRequired("md5Date",false);
			mainInfo_dataset.setFieldRequired("md5EndDate",false);
			mainInfo_dataset.setFieldRequired("md5Passwd",false);
		}
	}
	
	/**控制字段【是否需要签名】对应form显示*/
	function signFormShowOrNotDetail(showFlag){
		if(showFlag == needSign){
			$("#certSignDetail").css("display","block"); 
			mainInfo_dataset.setFieldRequired("md5Date",true);
			mainInfo_dataset.setFieldRequired("md5EndDate",true);
			mainInfo_dataset.setFieldRequired("md5Passwd",true);
		}else{
			$("#certSignDetail").css("display","none"); 
			mainInfo_dataset.setFieldRequired("md5Date",false);
			mainInfo_dataset.setFieldRequired("md5EndDate",false);
			mainInfo_dataset.setFieldRequired("md5Passwd",false);
		}
	}
	
	/**是否需要签名下拉事件（当【是否需要签名】改变选择时，显示对应form）*/
	function mainInfo_dataset_needSign_onSelect(value,record){	
		signFormShowOrNotAdd(value);
		signFormShowOrNotUpd(value);
		removeSignInfo();
	}
	
	/**根据【通道编号】判断功能清单显示*/
	function funcTreeShowAdd(){
		funcTreeAdd_dataset.setParameter("qpagyNo",mainInfo_dataset.getValue("pagyNo"));
		funcTreeAdd_dataset.flushData(funcTreeAdd_dataset.pageIndex);
	}
	
	/**根据【通道编号】判断功能清单显示*/
	function funcTreeShowDetail(){
		funcTreeDetail_dataset.setParameter("qpagyNo",mainInfo_dataset.getValue("pagyNo"));
		funcTreeDetail_dataset.flushData(funcTreeDetail_dataset.pageIndex);
	}
	
	/**校验字段【基本信息】 */
	function validBaseInfo(){
		//获取需校验字段
		var pagyNo = mainInfo_dataset.getValue("pagyNo");//通道编号
		var mainMchtAcsType = mainInfo_dataset.getValue("mainMchtAcsType");//接入方式
		var mainMchtNo = mainInfo_dataset.getValue("mainMchtNo");//接入编号
		var mainMchtName = mainInfo_dataset.getValue("mainMchtName");//接入名称
		var mainMchtStat = mainInfo_dataset.getValue("mainMchtStat");//接入状态
		var mainMchtPublicNo = mainInfo_dataset.getValue("mainMchtPublicNo");//公众号/应用ID
		var mainMchtSetlCycleType = mainInfo_dataset.getValue("mainMchtSetlCycleType");//清算周期
		var mainMchtSetlCycle = mainInfo_dataset.getValue("mainMchtSetlCycle");//清算日
		var mainMchtSetlAccno = mainInfo_dataset.getValue("mainMchtSetlAccno");//清算账号
		var mainMchtSetlTm = mainInfo_dataset.getValue("mainMchtSetlTm");//清算时间
		var mainMchtMccCode = mainInfo_dataset.getValue("mainMchtMccCode");//行业编号
		var mainMchtMccName = mainInfo_dataset.getValue("mainMchtMccName");//行业名称		
		//正则匹配
		if(pagyNo == "" || pagyNo == null){
			$.error("请选择通道！");
			return false;
		}
		if(mainMchtAcsType == "" || mainMchtAcsType == null){
			$.error("请选择接入方式！");
			return false;
		}
		if(!isDesc32.test(mainMchtNo)){
			$.warn("接入编号长度错误【最大32位】");
			return false;
		}
		if(!isDesc21.test(mainMchtName)){
			$.warn("接入名称长度错误【最大21位】");
			return false;
		}
		if(mainMchtStat == "" || mainMchtStat == null){
			$.error("请选择接入状态！");
			return false;
		}
		if((pagyNo == "302")){
			if((mainMchtPublicNo == "") || (mainMchtPublicNo == null)){
				$.error("请选择公众号/应用ID！");
				return false;
			}else{
				if(!isDesc32.test(mainMchtPublicNo)){
					$.warn("公众号/应用ID长度错误【最大32位】");
					return false;
				}
			}					
		}		
		if(mainMchtSetlCycleType == "" || mainMchtSetlCycleType == null){
			$.error("请选择清算周期！");
			return false;
		}else{
			if(mainMchtSetlCycleType == "01"){
				if(!isDay.test(mainMchtSetlCycle)){
					$.warn("清算周期为【天】，清算日格式错误【小于等于365的整数】");
					return false;
				}
			}else if(mainMchtSetlCycleType == "02"){
				if(!isWeek.test(mainMchtSetlCycle)){
					$.warn("清算周期为【星期】，清算日格式错误【小于等于7的整数】");
					return false;
				}
			}else{
				if(!isMonth.test(mainMchtSetlCycle)){
					$.warn("清算周期为【月度】，清算日格式错误【小于等于31的整数】");
					return false;
				}
			}
		}
		if((mainMchtSetlAccno == "") || (mainMchtSetlAccno == null)){
			$.error("请选择清算账号！");
			return false;
		}else{
			if(!isDesc40.test(mainMchtSetlAccno)){
				$.warn("清算账号长度错误【最大40位】");
				return false;
			} 
		}		
		if((mainMchtSetlTm == "") || (mainMchtSetlTm == null)){
			$.error("请选择清算时间！");
			return false;
		}else{
			if(!isTime.test(mainMchtSetlTm)){
				$.warn("清算时间格式错误【hh:mm:ss(英文:)】");
				return false;
			}
		}				
		if(!((mainMchtMccCode == "" || mainMchtMccCode == null) && (mainMchtMccName == "" || mainMchtMccName == null))){
			if(!isDesc50.test(mainMchtMccCode)){
				$.warn("行业编号长度错误【最大50位】,如果已经输入了【行业名称】，则此项必须输入");
				return false;
			}
			if(!isDesc42.test(mainMchtMccName)){
				$.warn("行业名称长度错误【最大42位】,如果已经输入了【行业编号】，则此项必须输入");
				return false;
			}
		}	
		return true;		
	}
	
	/**校验字段【证书信息】 */
	function validCertInfo(){
		//获取需校验字段
		var encryptType = mainInfo_dataset.getValue("encryptType");//加密类型
		var certifiName = mainInfo_dataset.getValue("certifiName");//证书名称
		var certifiPasswd = mainInfo_dataset.getValue("certifiPasswd");//证书密码
		var md5Passwd = mainInfo_dataset.getValue("md5Passwd");//密钥值		
		//正则匹配
		if(encryptType == rsa){
			if((certifiName == "") || (certifiName == null)){
				$.error("请上传证书！");
				return false;
			}
		}		
		if((certifiPasswd != "") && (certifiPasswd != null)){
			if(!isDesc100.test(certifiPasswd)){
				$.warn("证书密码长度错误【最大100位】");
				return false;
			}
		}
		if((md5Passwd != "") && (md5Passwd != null)){
			if(!isDesc100.test(md5Passwd)){
				$.warn("密钥值长度错误【最大100位】");
				return false;
			}
		}
		return true;
	}
	
	/**功能清单，勾选节点组装发送后台*/
	function reInTreeNodeAddOrUpd(oprType){	
		var checkedArr = "";//初始化数据容器
		if(oprType == oprType_ADD){
			checkedArr = tree_treeFuncAdd.getChecked();//勾选节点数组
		}else {
			checkedArr = tree_treeFuncUpd.getChecked();//勾选节点数组
		}
		var nodeId = "";//勾选节点
		var nodePid = "";//勾选节点的父节点
		var funcStr = "";//存放组装后的节点信息【不同节点之间逗号分隔】
		var commaFlag = false;
		for (var i = 0; i < checkedArr.length; i++) {
			if (commaFlag == true) {
				funcStr += ",";
			}
			nodeId = checkedArr[i].getValue("_id");
			nodePid = checkedArr[i].getValue("_parentId");
			if(nodePid != ""){
				nodeId = nodePid + "/" +nodeId;//拼接节点信息【父节点/节点】  
				funcStr += nodeId;
				commaFlag = true;
			}else{
				commaFlag = false;
				continue;
			}						
		}
		mainInfo_dataset.setParameter("funcStr", funcStr);
	}
		
	//功能清单加载完成后执行操作
	function funcTreeUpd_dataset_flushDataPost(funcTreeUpd) {
		tree_treeFuncUpd.check(mainInfo_dataset.getValue("checkNode").split(","));
	}
	
	//功能清单加载完成后执行操作
	function funcTreeDetail_dataset_flushDataPost(funcTreeDetail) {
		tree_treeFuncDetail.expandAll();
	}
	
	/**撤销字段必输设置*/
	function requiredRemove(){
		mainInfo_dataset.setFieldRequired("pagyNameSel",false);	
		mainInfo_dataset.setFieldRequired("mainMchtPublicNo",false);	
		mainInfo_dataset.setFieldRequired("encryptType",false);
		mainInfo_dataset.setFieldRequired("certifiDate",false);
		mainInfo_dataset.setFieldRequired("certifiEndDate",false);
		mainInfo_dataset.setFieldRequired("certifiName",false);
		mainInfo_dataset.setFieldRequired("certifiPasswd",false);
		mainInfo_dataset.setFieldRequired("md5Date",false);
		mainInfo_dataset.setFieldRequired("md5EndDate",false);
		mainInfo_dataset.setFieldRequired("md5Passwd",false);
	}
	

/****************************************新增******************************************/
	/**********查询界面***********/
	/**打开新增窗口*/
	function btnAdd_onClick(){
		window_winAdd.open();
	}

	/**窗口打开前初始化form状态*/
	function window_winAdd_beforeOpen(win){
		// Step1  基本信息form
		publicReadOrWrite(init);	
		// Step2  证书信息form
		encFormShowOrNotAdd(init);
		encFormReqOrNot(init);
		// Step3 功能清单tree
		funcTreeAdd_dataset.setParameter("qoprType",oprType_ADD);//操作类型
		funcTreeAdd_dataset.flushData(funcTreeAdd_dataset.pageIndex);
		funcTreeShowAdd();
	}
	
	/**********窗口***********/
	/**提交前检验数据格式*/
	function btnAddSub_onClickCheck(button,commit){
		// Step1 基本信息验证模块
		if(!validBaseInfo()){
			return false;
		}else{
			validBaseInfo();
		}		
		// Step2 证书信息验证模块
		if(!validCertInfo()){
			return false;
		}else{
			validCertInfo();
		}
		//Step3 功能清单，勾选节点组装发送后台
		reInTreeNodeAddOrUpd(oprType_ADD);
		return true;
	}
	
	/**【上传证书】按钮单击前检查*/
	function btnCertAddSub_onClickCheck(){
		var mainMchtNo = mainInfo_dataset.getValue("mainMchtNo");//接入编号，也可认为是主商户号
		if((mainMchtNo == "") || (mainMchtNo == null)){
			$.error("证书无法上传，请先输入接入编号！");
			return false;
		}else{
			if(!isDesc32.test(mainMchtNo)){
				$.warn("接入编号长度错误【最大32位】");
				return false;
			}			
		}
		return true;
	}
	
	/**【上传证书】按钮单击事件*/
	function btnCertAddSub_onClick(){
		$.open("submitWin", "上传证书", "/pages/payPmp/pubTool/importCer.jsp?certType=01" ,
		 		"450", "340", false, true, null, false, "");			
	}
	
	/**上传成功返回证书路径*/
	function callBackCertPath(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiPath",ret);
		}
	}
	
	/**上传成功返回证书文件编号*/
	function callBackCertCode(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiCode",ret);
		}
	}
	
	/**上传成功返回证书名称*/
	function callBackCertName(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiName",ret);
		}
	}
	
	/**提交*/
	function btnAddSub_postSubmit(){
		$.success("添加成功！",function(){
			window_winAdd.close();			
		});
	}
	
	/**窗口关闭后清除数据*/
	function window_winAdd_afterClose(){
		requiredRemove();//撤销字段必输设置	 
		funcTreeAdd_dataset.setParameter("qpagyNo","");
		funcTreeAdd_dataset.flushData(funcTreeAdd_dataset.pageIndex);
		mainInfo_dataset.clearData();	
		mainInfo_dataset.flushData(mainInfo_dataset.pageIndex);
	}
	
	
/****************************************修改******************************************/
	/**********查询界面***********/	
	/**向后台发送交互参数*/
	function btnUpd_onClickCheck(){	
		mainInfo_dataset.setParameter("mainMchtNo", mainInfo_dataset.getValue("mainMchtNo"));
		return true;
	}
	
	/**打开修改窗口*/
	function btnUpd_postSubmit(btn,param){	
		var checkNode = param.checkNode; // 根据后台返回的数据,进行相应的勾选
		mainInfo_dataset.setValue("checkNode",checkNode);
		funcTreeUpd_dataset.setParameter("mainMchtNo",mainInfo_dataset.getValue("mainMchtNo"));//接入编号
		funcTreeUpd_dataset.setParameter("qoprType",oprType_UPD);//操作类型
		funcTreeUpd_dataset.setParameter("qpagyNo",mainInfo_dataset.getValue("pagyNo"));//通道编号
		funcTreeUpd_dataset.flushData(funcTreeUpd_dataset.pageIndex);
		mainInfo_dataset.setValue("mainMchtNoAC",mainInfo_dataset.getValue("mainMchtNo"));//修改前接入编号
		window_winUpd.open();
	}

	/**窗口打开前初始化form状态*/
	function window_winUpd_beforeOpen(win){
		// Step1 基本信息form
		//控制【公众号/应用Id】字段
		var pagyNo = mainInfo_dataset.getValue("pagyNo");//通道编号
		if((pagyNo == "302")){
			publicReadOrWrite(write);
		}else{
			publicReadOrWrite(read);
		}
		//设置只读字段
		mainInfo_dataset.setFieldReadOnly("pagyNameSel",true);//通道名称
		mainInfo_dataset.setFieldReadOnly("mainMchtStat",true);//接入状态
		
		// Step2 证书信息form
		//控制加密算法form
		var encryptType = mainInfo_dataset.getValue("encryptType");//加密算法 
		encFormShowOrNotUpd(encryptType);
		encFormReqOrNot(encryptType);
		//控制签名form			
		var signFlag = mainInfo_dataset.getValue("md5EncryptWayType");//md5加密方式【对应加密方式-01，判断签名时使用】
		if(encryptType == rsa){
			if(signFlag == "01"){
				signFormShowOrNotUpd(needSign);
				mainInfo_dataset.setValue("needSign","01");
			}else{
				signFormShowOrNotUpd(noSign);
				mainInfo_dataset.setValue("needSign","00");
			}
		}
	}
	
	
	/**********窗口***********/
	/**提交前检验数据格式*/
	function btnUpdSub_onClickCheck(button,commit){
		// Step1 基本信息验证模块
		if(!validBaseInfo()){
			return false;
		}else{
			validBaseInfo();
		}		
		// Step2 证书信息验证模块
		if(!validCertInfo()){
			return false;
		}else{
			validCertInfo();
		}	
		//Step3 功能清单，勾选节点组装发送后台
		reInTreeNodeAddOrUpd(oprType_UPD);
		return true;
	}
	
	/**【上传证书】按钮单击前检查*/
	function btnCertUpdSub_onClickCheck(){
		var mainMchtNo = mainInfo_dataset.getValue("mainMchtNo");//接入编号，也可认为是主商户号
		if((mainMchtNo == "") || (mainMchtNo == null)){
			$.error("证书无法上传，请先输入接入编号！");
			return false;
		}else{
			if(!isDesc32.test(mainMchtNo)){
				$.warn("接入编号长度错误【最大32位】");
				return false;
			}			
		}
		return true;
	}
	
	/**【上传证书】按钮单击事件*/
	function btnCertUpdSub_onClick(){		
		$.open("submitWin", "上传证书", "/pages/payPmp/pubTool/importCer.jsp?certType=01",
		 		"450", "340", false, true, null, false, "");			
	}
	
	/**上传成功返回证书路径*/
	function callBackCertPath(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiPath",ret);
		}
	}
	
	/**上传成功返回证书文件编号*/
	function callBackCertCode(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiCode",ret);
		}
	}
	
	/**上传成功返回证书名称*/
	function callBackCertName(ret) {
		if (ret) {
			mainInfo_dataset.setValue("certifiName",ret);
		}
	}
	
	/**提交*/
	function btnUpdSub_postSubmit(){
		$.success("添加成功！",function(){
			window_winUpd.close();			
		});
	}
	
	/**窗口关闭后清除数据*/
	function window_winUpd_afterClose(){
		//还原写入权限
		mainInfo_dataset.setFieldReadOnly("pagyNameSel",false);//通道名称
		mainInfo_dataset.setFieldReadOnly("mainMchtStat",false);//接入状态
		requiredRemove();//撤销字段必输设置	 
		funcTreeUpd_dataset.setParameter("qpagyNo","");
		funcTreeUpd_dataset.flushData(funcTreeUpd_dataset.pageIndex);
		mainInfo_dataset.clearData();	
		mainInfo_dataset.flushData(mainInfo_dataset.pageIndex);
	}
	
	
/****************************************详情******************************************/
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
 		if (record) {
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(){
		mainInfo_dataset.setReadOnly(true);//全部字段设置为-只读
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口打开前初始化form显示*/
	 function window_winDetail_beforeOpen(win){
		//控制基本信息form
		var pagyNo = mainInfo_dataset.getValue("pagyNo");//通道编号		
		if((pagyNo == "302")){
			mainInfo_dataset.setFieldRequired("mainMchtPublicNo",true);
		}
		mainInfo_dataset.setFieldRequired("pagyNameSel",true);
		//控制加密算法form
		var encryptType = mainInfo_dataset.getValue("encryptType");//加密算法
		mainInfo_dataset.setFieldRequired("encryptType",true);//【选择加密算法】设置只读
		encFormShowOrNotDetail(encryptType);
		encFormReqOrNot(encryptType);
		//控制签名form			
		var signFlag = mainInfo_dataset.getValue("md5EncryptWayType");//md5加密方式【对应加密方式-01，判断签名时使用】
		if(encryptType == rsa){
			if(signFlag == "01"){
				signFormShowOrNotDetail(needSign);
				mainInfo_dataset.setValue("needSign","01");
			}else{
				signFormShowOrNotDetail(noSign);
				mainInfo_dataset.setValue("needSign","00");
			}
		}
		//功能清单tree
		funcTreeDetail_dataset.setParameter("mainMchtNo",mainInfo_dataset.getValue("mainMchtNo"));//接入编号
		funcTreeDetail_dataset.setParameter("qoprType",oprType_DETAIL);//操作类型
		funcTreeShowDetail();
	} 
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		mainInfo_dataset.setReadOnly(false);//还原写入权限
		requiredRemove();//撤销字段必输设置
		funcTreeDetail_dataset.setParameter("qpagyNo","");
		funcTreeDetail_dataset.flushData(funcTreeDetail_dataset.pageIndex);
		mainInfo_dataset.clearData();	
		mainInfo_dataset.flushData(mainInfo_dataset.pageIndex);
	}	
	
	
/****************************************启用/停用******************************************/
 	/**按钮触发*/
	function btnStp_onClickCheck(){
		/*****************设置特殊字段[下拉]*****************/
		var mainMchtStat = mainInfo_dataset.getValue("mainMchtStat");
		var msg = ""
		if(mainMchtStat == "00"){
			msg = "是否要【停用】商户?";
		}else{
			msg = "是否要【启用】商户?";
		}
		$.confirm(msg, function() {
			btnStpSub.click();
	       }, function() {
	        return false;
	       });
	}
 
 	/**操作生效*/
	function btnStpSub_postSubmit(){
		$.success("操作成功！",function(){
			mainInfo_dataset.clearData();	
			mainInfo_dataset.flushData(mainInfo_dataset.pageIndex);		
		});		
	}
	
 	
/****************************************（如需要）返回通道核心配置页面,且查询需配置记录******************************************/
 	function initCallGetter_post() {
		/**该部分内容为通道核心参数配置功能所需，不开放通道核心参数配置可以注释掉该模块代码*/
 		var pagyCoreFlag = <%=StringUtil.filtrateSpecialCharater(request.getParameter("pagyCoreFlag"))%>;
 		var param = <%=StringUtil.filtrateSpecialCharater(request.getParameter("param"))%>;
 		var updFlag = <%=StringUtil.filtrateSpecialCharater(request.getParameter("updFlag"))%>;
 		if(pagyCoreFlag == null){
 			btnPagyCore.setHidden(true);
 		}
 		if(param){
 			if(updFlag){
 				mainInfo_dataset.setParameter("qmainMchtNoAC",param);
 			}else{
 				mainInfo_dataset.setParameter("qpagyNo",param);
 			}			
 			mainInfo_dataset.flushData(mainInfo_dataset.pageIndex);
 		}
	}
	
	function btnPagyCore_onClick(){
		window.location.href='../oprMng/pagyCoreMng.jsp';
	}
	
	</script>
</snow:page>