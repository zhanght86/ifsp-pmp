<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动管理">
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascrpt"  src="../../public/lib/jquery/jquery.form.js"></script>
     <!--------------- 活动临时信息: 数据集 ----------------->
    <snow:dataset id="activeInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>
     <!--------------- 活动周期临时信息: 数据集 ----------------->
    <snow:dataset id="cycleInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvCycleConfTmp" submitMode="all" init="false" parameters=""></snow:dataset>
    <!--------------- 红包信息: 数据集 ----------------->
    <snow:dataset id="redBagConf" path="com.ruimin.ifs.pmp.mktActivity.dataset.TblActiveRedbagConfTmp" submitMode="all" init="false" parameters=""></snow:dataset>
    
     <!--------------- 活动方法配置信息: 数据集 ----------------->
    <snow:dataset id="methodConfig" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvMethodConfTmp" submitMode="all" init="false" parameters=""></snow:dataset>
    
    <!--------------- 账户分组配置信息: 数据集 ----------------->
    <snow:dataset id="acctGp" path="com.ruimin.ifs.pmp.mktActivity.dataset.AcctGpConfForActive" submitMode="current" init="false" parameters=""></snow:dataset>
    <!--------------- 审核信息记录数据集 ----------------->
	<snow:dataset path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" id="checkNewRecordInfo" submitMode="current" init="false"></snow:dataset>
	
    <!-- 页面查询框体 -->
	<snow:query label ="请输入查询条件" id= "activeQuery" dataset= "activeInfo" fieldstr ="qActiveNo,qActiveNm,qActiveType,qActiveStat,qProdId,qCardGpNo"></snow:query>

    <!-- 活动列表框体 -->
	<snow:grid dataset ="activeInfo" id="actvListGrid"	height="99%" fieldstr= "activeNo,activeNm,activeType,activeStat,SDate,EDate,budgetTp,activeBudget[108],platBudget[108],mchtCount,auditFlag,opr"   paginationbar="btnAdd,btnMod,btnStatus,btnConfig,btnMchtIn" fitcolumns ="true"></snow:grid>

	<!-- 新增活动框体 -->
	<snow:window id="windowAddActive" title="活动新增" modal="true" closable="true" buttons="btnAddAudit">
		<p style="font">活动基本信息</p>
		<snow:form id="formAddActive1" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,budgetTp,activeLv,activeBudget,platBudget,acctLimitType,acctCntMax,cardGpNo" collapsible="false" colnum="4"></snow:form>
		<!-- 红包信息 -->
		<snow:grid id="gridAddRed01"  dataset="redBagConf" fieldstr="redbagAmt[100],redbagCount[100]" 
			height="300" border="true" label="红包配置" editable="true" fitcolumns="true" pagination="true" 
			toolbar="toolbarAddRed01" collapsible="true">
		</snow:grid>
		<snow:toolbar id="toolbarAddRed01">
			<snow:button id="btnAddSection01" dataset="redBagConf" hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="redBagConf" hidden="false"></snow:button>
		</snow:toolbar> 
		<br/>
		<p>支付产品</p>
		<snow:form id="formAddActive2" dataset="activeInfo"  border="false" label="支付产品" fieldstr="prodId" collapsible="false" colnum="4"></snow:form>
		<br/>
		<p>活动时间</p>
		<snow:form id="formAddActive3" dataset="activeInfo"  border="false" label="活动时间" fieldstr="SDate,EDate,cycleFlg" collapsible="false" colnum="4"></snow:form>
		<snow:grid id="gridAddCycle"  dataset="cycleInfo" fieldstr="dateTp[200],dateData[100]" 
			height="300" border="true" label="活动周期信息" editable="true" fitcolumns="true" pagination="false" 
			toolbar="toolbarAddCycle" collapsible="true">
		</snow:grid> 
		<snow:toolbar id="toolbarAddCycle">
			<snow:button id="btnAddCycle01" dataset="cycleInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteCycle01" dataset="cycleInfo" hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnAddAudit" dataset="activeInfo" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 查看活动详情框体 -->
	<snow:window id="windowDetailActive" title="活动详情" modal="true" closable="true">
		<p style="font">活动基本信息</p>
		<snow:form id="formDetailActive1" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,budgetTp,activeLv,activeBudget,platBudget,acctLimitType,acctCntMax,cardGpNo,activeStat" collapsible="false" colnum="4"></snow:form>
		<!-- 红包信息 -->
		<snow:grid id="gridDtlRed01"  dataset="redBagConf" fieldstr="redbagAmt[100],redbagCount[100],redbagConsume[100]" 
			height="300" border="true" label="红包配置" editable="false" fitcolumns="true" pagination="true" 
			collapsible="true">
		</snow:grid>
		<snow:form id="RedConf" dataset="redBagConf"  border="true" label="红包总计" fieldstr="sumCount,sumConSume" collapsible="false" colnum="4"></snow:form>
		<br/>
		<p>支付产品</p>
		<snow:form id="formDetailActive2" dataset="activeInfo"  border="false" label="支付产品" fieldstr="prodId" collapsible="false" colnum="4"></snow:form>
		<br/>
		<p>活动时间</p>
		<snow:form id="formDetailActive3" dataset="activeInfo"  border="false" label="活动时间" fieldstr="SDate,EDate,cycleFlg" collapsible="false" colnum="4"></snow:form>
		<snow:grid id="gridDetailCycle"  dataset="cycleInfo" fieldstr="dateTp[200],dateData[100]" 
			height="300" border="true" label="活动周期信息" editable="false" fitcolumns="true" pagination="false" 
			collapsible="true">
		</snow:grid> 
	</snow:window>
	<!-- 修改活动框体 -->
	<snow:window id="windowUpdActive" title="活动修改" modal="true" closable="true" buttons="btnModAudit">
		<p style="font">活动基本信息</p>
		<snow:form id="formUpdActive1" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,budgetTp,activeLv,activeBudget,platBudget,acctLimitType,acctCntMax,cardGpNo" collapsible="false" colnum="4"></snow:form>
		<!-- 红包信息 -->
		<snow:grid id="gridUpdRed01"  dataset="redBagConf" fieldstr="redbagAmt[100],redbagCount[100],redbagConsume[100]" 
			height="300" border="true" label="红包配置" editable="false" fitcolumns="true" pagination="true" 
			collapsible="true">
		</snow:grid>
		<snow:form id="RedConf2" dataset="redBagConf"  border="true" label="红包总计" fieldstr="sumCount,sumConSume" collapsible="false" colnum="4"></snow:form>
		<!-- 
		<snow:toolbar id="toolbarUpdRed01">
			<snow:button id="btnAddSection01" dataset="redBagConf" hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="redBagConf" hidden="false"></snow:button>
		</snow:toolbar> 
		 -->
		<br/>
		<p>支付产品</p>
		<snow:form id="formUpdActive2" dataset="activeInfo"  border="false" label="支付产品" fieldstr="prodId" collapsible="false" colnum="4"></snow:form>
		<br/>
		<p>活动时间</p>
		<snow:form id="formUpdActive3" dataset="activeInfo"  border="false" label="活动时间" fieldstr="SDate,EDate,cycleFlg" collapsible="false" colnum="4"></snow:form>
		<snow:grid id="gridUpdCycle"  dataset="cycleInfo" fieldstr="dateTp[200],dateData[100]" 
			height="300" border="true" label="活动周期信息" editable="true" fitcolumns="true" pagination="false" 
			toolbar="toolbarUpdCycle" collapsible="true">
		</snow:grid> 
		<snow:toolbar id="toolbarUpdCycle">
			<snow:button id="btnAddCycle01" dataset="cycleInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteCycle01" dataset="cycleInfo" hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnModAudit" dataset="activeInfo" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 活动配置框体 -->
	<snow:window id="windowConfig" title="活动配置" modal="true" closable="true" buttons="btnConfigAudit" width="1100">
		<p style="font">活动基本信息</p>
		<snow:form id="formConfigActive" dataset="activeInfo"  border="false" label="活动基本信息" fieldstr="activeNo,activeType,activeNm,budgetTp,activeLv,activeBudget,platBudget,acctLimitType,acctCntMax,cardGpNo,activeStat" collapsible="false" colnum="4"></snow:form>
		<br/>
		<snow:grid id="gridMethodConfig"  dataset="methodConfig" fieldstr="mchtLevel[100],methodParam1[150],methodParam2[150],methodParam3[150],methodParam4[150],methodParam5[150],methodParam6[150]" 
			height="300" border="true" label="方法配置" editable="true" fitcolumns="false" pagination="false" 
			toolbar="toolbarConfig" collapsible="true">
		</snow:grid> 
		<snow:toolbar id="toolbarConfig">
			<snow:button id="btnAddConfig" dataset="methodConfig" hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="methodConfig" hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnConfigAudit" dataset="activeInfo" hidden="true"></snow:button>
	</snow:window>
	<!-- 处于待审核，及拒绝时，超链接窗口 -->
    <snow:window id="windowAuditFlag" title="审核相关信息" modal="true" closable="true"
		buttons="" width="900" height="550"> 
		<p style="font" >活动基本信息</p>
		<snow:form id="formActInfo" dataset="activeInfo" label="活动方法基本信息"
			border="false" collapsible="true" 
			fieldstr="activeNo,activeNm,activeType,activeStat,auditId">
		</snow:form>
		<p style="font" >审核流程信息</p>
		<snow:grid id="gridCheckNewRecordInfo" dataset="checkNewRecordInfo"  fitcolumns="true" fieldstr="stepNo[95],auditState[115],roleName[115],auditDatetIme[200],auditView[320]" height="300" >
		</snow:grid>
	</snow:window> 
	<div id="InputLab"></div>
	<snow:button id="btnAddSave" dataset="activeInfo" hidden="true"></snow:button>
	<snow:button id="btnModSave" dataset="activeInfo" hidden="true"></snow:button>
	<snow:button id="btnStatusSave" dataset="activeInfo" hidden="true"></snow:button>
	<snow:button id="btnConfigSave" dataset="activeInfo" hidden="true"></snow:button>
	<script type ="text/javascript">
	
		var isAmt=/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
		var isNum=/^\d{1,10}$/;
		var isMMDD = /^(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])$/;
		var isDay = /^(0[1-9]|[1-2][0-9]|3[0-1]|[1-9]{1})$/;
		var isWeekDay =/^[1-7]{1}$/;
		
		
	//  *********************************审核功能****************************** 	
		   //当列表页面加载后，刷新审核字段
		   	 function actvListGrid_auditFlag_onRefresh(record, fieldId, fieldValue){
		   	//得到审核信息编号
		 	    var auditId=activeInfo_dataset.getValue("auditId");
		 	    if(fieldValue == '01'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增待审核 </a></span>";	
		 	    }
		 	    if(fieldValue == '02'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增被拒绝</a></span>";	
		 	    }
		 	    if(fieldValue == '03'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改待审核 </a></span>";	
		 	    }
		 	    if(fieldValue == '04'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">配置待审核 </a></span>";	
		 	    }
		 	    if(fieldValue == '05'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">参与商户待审核 </a></span>";	
		 	    }
		 	   	if(fieldValue == '06'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">暂停待审核 </a></span>";	
		 	    }
		 	   	if(fieldValue == '07'){
		 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">恢复待审核 </a></span>";	
		 	    }
		 	    if(fieldValue == '00'){
		 	    	return '正常';	
		 	    }
		   }
			
		   	 //点击状态按钮显示对应信息
		     function onClickWin(auditId){
		    	 activeInfo_dataset.setFieldReadOnly("activeNo", true);
		    	 activeInfo_dataset.setFieldReadOnly("activeNm", true);
		    	 activeInfo_dataset.setFieldReadOnly("activeType", true);
		    	 activeInfo_dataset.setFieldReadOnly("activeStat", true);
		    	 activeInfo_dataset.setFieldReadOnly("auditId", true);
		         //根据审核信息编号，查询审核记录表
		         var auditId = activeInfo_dataset.getValue("auditId")
		         checkNewRecordInfo_dataset.setParameter("auditId",auditId);
		         checkNewRecordInfo_dataset.flushData(checkNewRecordInfo_dataset.pageIndex);
		     	 window_windowAuditFlag.open();
		     }
		   	 
		   	 //审核界面关闭后还原设置
		     function window_windowAuditFlag_afterClose(wmf){
		    	// activeInfo_dataset.setFieldReadOnly("activeNo", false);
		    	 activeInfo_dataset.setFieldReadOnly("activeNm", false);
		    	 //activeInfo_dataset.setFieldReadOnly("activeType", false);
		    	 //activeInfo_dataset.setFieldReadOnly("activeStat", false);
		    	 //activeInfo_dataset.setFieldReadOnly("auditId", false);
			    }
		   	 
		
		
		
		//  *********************************查询列表-方法****************************** 
		 //当列表页面加载后，在操作栏追加操作连接
		 function actvListGrid_opr_onRefresh(record, fieldId, fieldValue) {
	         if (record) {
	              return "<a href=\"JavaScript:showDetails()\">详情</a>" ;
	        }
	  	}
		//  *********************************新增活动-方法******************************
		//是否显示红包信息
		function showRedBag(showFlag){
	    	 if(showFlag == true){
	    		 //redBagConf_dataset.setParameter("activeNo",null);
		 	  	 //redBagConf_dataset.flushData(redBagConf_dataset.pageIndex);
	    		 $("#gridAddRed01").css("display", "block");
	    		 $("#gridUpdRed01").css("display", "block");
	    		 $("#btnAddSection01").css("display", "");
		     	 $("#btnDeleteSection01").css("display", "");
	    	 }else if (showFlag==false){
	    		
	    		$("#gridAddRed01").css("display", "none");
	    		$("#gridUpdRed01").css("display", "none");
	    		$("#btnAddSection01").css("display", "none");
	    	  	$("#btnDeleteSection01").css("display", "none");
	    	 }
	     }
		
		//当新增活动按钮点击时
		function btnAdd_onClick(){
			$("#gridAddCycle").css("display", "none");
	    	$("#btnAddCycle01").css("display", "none");
	  		$("#btnDeleteCycle01").css("display", "none");
	  		$("#gridAddRed01").css("display", "none");
    		$("#btnAddSection01").css("display", "none");
    	  	$("#btnDeleteSection01").css("display", "none");
	  		cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
	  		//var activeNo = activeInfo_dataset.getValue("activeNo");
	  		redBagConf_dataset.setParameter("activeNo",null);
	  		redBagConf_dataset.flushData(redBagConf_dataset.pageIndex);
	    	window_windowAddActive.open();
		}
		
		//活动类型发生变化时候
		function activeInfo_dataset_activeType_onSelect(v,record){
			if('11'== v){//当活动类型为收单立减时
				$("#formAddActive1_8 #aCM").remove();
				$("#formUpdActive1_8 #aCM").remove();
				//当00-无  时
				activeInfo_dataset.setValue("acctLimitType","1");//设置为全活动
				activeInfo_dataset.setValue("acctCntMax","1");//设置限制次数为1
				activeInfo_dataset.setFieldReadOnly( "acctLimitType", true);
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", true);
				activeInfo_dataset.setFieldRequired("acctCntMax", true);
				activeInfo_dataset.setFieldReadOnly( "budgetTp", false);
				//activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
				showRedBag(false);
			}else if('21'== v){//当活动类型为红包立减时
				//拼接div
				$("#formAddActive1_8 #input_activeInfo_acctCntMax").append("<div id='aCM' style='float:left;width=100px;height=30px;color:red'>&nbsp;&nbsp;&nbsp'限制次数'对于微信被扫和支付宝被扫交易无效</div>");
				$("#formUpdActive1_8 #input_activeInfo_acctCntMax").append("<div id='aCM' style='float:left;width=100px;height=30px;color:red'>&nbsp;&nbsp;&nbsp'限制次数'对于微信被扫和支付宝被扫交易无效</div>");
				activeInfo_dataset.setValue("acctLimitType","3");//设置为单日
				activeInfo_dataset.setValue("acctCntMax","");
				activeInfo_dataset.setValue("budgetTp","00");//设置预算类型为无
				activeInfo_dataset.setFieldReadOnly( "budgetTp", true);
				activeInfo_dataset.setValue("activeBudget","");//活动金额置空
				activeInfo_dataset.setFieldReadOnly( "activeBudget", true);
				activeInfo_dataset.setFieldReadOnly( "acctLimitType", true);
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", false);
				activeInfo_dataset.setFieldRequired("acctCntMax", false);
				activeInfo_dataset.setFieldReadOnly( "platBudget", true);
				activeInfo_dataset.setFieldRequired("platBudget", false);
				showRedBag(true);
			}
		}
		
		//预算类型发生变化时
		function activeInfo_dataset_budgetTp_onSelect(v,record){
			activeInfo_dataset.setValue("activeBudget","");
			activeInfo_dataset.setValue("platBudget","");
			if('00'==v){
				//当00-无  时
				activeInfo_dataset.setFieldReadOnly( "activeBudget", true);
				activeInfo_dataset.setFieldReadOnly( "platBudget", true);
			}else if('01'==v){
				//01-银行全资	
				activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
				activeInfo_dataset.setFieldReadOnly( "platBudget", true);
			}else if('02'==v){
				//02-比例分摊	
				activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
				activeInfo_dataset.setFieldReadOnly( "platBudget", false);
			}else if('03'==v){
				//03-顺序分摊	
				activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
				activeInfo_dataset.setFieldReadOnly( "platBudget", false);
			}
		}
		//账户限制类型变化时
		function activeInfo_dataset_acctLimitType_onSelect(v,record){
			activeInfo_dataset.setValue("acctCntMax","");
			if('0'==v){
				//当0-无限制  时
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", true);
			}else{
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", false);
			}
		}
		//是否是周期活动发生变化时
		function activeInfo_dataset_cycleFlg_onSelect(v,record){
			if('0'==v || v==''){
				//当0-不是  时
				cycleInfo_dataset.setParameter("activeNo","");
				cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
				$("#gridAddCycle").css("display", "none");
				$("#gridUpdCycle").css("display", "none");
		    	$("#btnAddCycle01").css("display", "none");
		  		$("#btnDeleteCycle01").css("display", "none");
			}else if('1'==v){
				cycleInfo_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
				cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
				$("#gridAddCycle").css("display", "block");
				$("#gridUpdCycle").css("display", "block");
		    	$("#btnAddCycle01").css("display", "");
		  		$("#btnDeleteCycle01").css("display", "");
			}
		}
		
		
		//当添加活动框体关闭时，取消记录
	    function window_windowAddActive_beforeClose(wmf){
	    	//关闭窗口时移除拼接的div
			$("#formAddActive1_8 #aCM").remove();
			$("#formUpdActive1_8 #aCM").remove();
	    	activeInfo_dataset.cancelRecord();
	    }
		//当添加框体关闭后,将只读权限放开
	    function window_windowAddActive_afterClose(wmf){
	    	activeInfo_dataset.setReadOnly(false);
	    }
	    //新增提交按钮点击时，校验提交内容并打开审核用户信息验证
	    function btnAddAudit_onClick(){
	     	var result = checkInput();
	     	if(!result){
	     		return;
	     	}
	     	btnAddSave.click();
	    }
	    //当新增或修改提交时，校验输入信息
	    function checkInput(){
	    	var result = activeInfo_dataset.validate();
	     	if(result == false){
	     		return false;
	     	}
	     	if(cycleInfo_dataset.validate() == false){
	     		return false;
	     	}
	     	if(activeInfo_dataset.getValue("activeNm").length>40){
	     		$.warn("活动名称最大长度只能是40位");
	     		return false;
	     	}
	    	var budgetTp = activeInfo_dataset.getValue("budgetTp");
	    	if(budgetTp!='00'){
	    		//当预算类型不为00-无
	    		if(!isAmt.test(activeInfo_dataset.getValue("activeBudget"))){
	    			$.warn("活动预算格式有误（最大长度10位数字,最多包含两位小数）");
	    			return false;
	    		}
	    	}
	    	if(budgetTp=='02' || budgetTp=='03'){
	    		//当活动类型为02-比例分摊，03-顺序分摊时
	    		if(!isAmt.test(activeInfo_dataset.getValue("platBudget"))){
	    			$.warn("平台预算格式有误（最大长度10位数字,最多包含两位小数）");
	    			return false;
	    		}
	    	}
	    	
    		var acctCntMax = activeInfo_dataset.getValue("acctCntMax");
    		if(acctCntMax!=null &&acctCntMax != ''){
	    		if(!isNum.test(activeInfo_dataset.getValue("acctCntMax"))){
	    			$.warn("限制次数格式错误（最大长度10位数字）");
	    			return false;
	    		}
    		}
	    	if(activeInfo_dataset.getValue("EDate")!=''){
	    		if(activeInfo_dataset.getValue("EDate")< activeInfo_dataset.getValue("SDate")){
	    			$.warn("活动结束日期不能早于开始日期");
	    			return false;
	    		}
	    	}
	    	var cycleFlag = activeInfo_dataset.getValue("cycleFlg");
	    	if(cycleFlag =='1'){
	    		//当周期活动标志为1-是
	    		var firstRecord = cycleInfo_dataset.getFirstRecord();
	    		if(firstRecord == null || firstRecord ==''){
	     			$.warn("周期活动时周期信息必须输入");
	     			return false;
	     		}
	    		while(firstRecord){
	    			var cycleType = firstRecord.getValue("dateTp");
	    			var dateData = firstRecord.getValue("dateData")
	    			if(cycleType == '01'){
	    				//当周期类型为01-具体日期
	    				if(!isMMDD.test(dateData)){
	    	    			$.warn("周期类型为[具体日期]时，周期数据格式必须为月份和日期（各两位数字）");
	    	    			return false;
	    	    		}
	    			}else if(cycleType == '02'){
	    				//当周期类型为02-每月N日
	    				if(!isDay.test(dateData)){
	    	    			$.warn("周期类型为[每月N日]时，周期数据格式必须为日期（一到两位日期数字）");
	    	    			return false;
	    	    		}
	    			}else if(cycleType == '03'){
	    				//当周期类型为03-每周星期N
	    				if(!isWeekDay.test(dateData)){
	    	    			$.warn("周期类型为[每周星期N]时，周期数据格式必须为1到7之间数字（1代表星期一，7代表星期日）");
	    	    			return false;
	    	    		}
	    			}else if(cycleType == '04'){
	    				//当周期类型为04-工作日节假日
	    				if(dateData !='1' && dateData!='0'){
	    	    			$.warn("周期类型为[工作日节假日]时，周期数据格式必须为1或0（1代表工作日，0代表节假日）");
	    	    			return false;
	    	    		}
	    			}
	    			firstRecord = firstRecord.getNextRecord();
	    		}
	    	}
	    	var activeType = activeInfo_dataset.getValue("activeType");
	    	if("21" == activeType){
	    		var firstRecord = redBagConf_dataset.getFirstRecord();
	    		if(firstRecord == null || firstRecord ==''){
	     			$.warn("红包立减时必须配置红包信息");
	     			return false;
	     		}
	    		while(firstRecord){
	    			var redbagAmt = firstRecord.getValue("redbagAmt");
	    			var redbagCount = firstRecord.getValue("redbagCount")
	    			if(!isAmt.test(redbagAmt)){
	    				$.warn("红包金额格式错误!!");
	    				return false;
	    			}
	    			if(!isNum.test(redbagCount)){
	    				$.warn("红包数量格式错误(只能为数字)!!");
	    				return false;
	    			}
	    			
	    			firstRecord = firstRecord.getNextRecord();
	    		}
	    		
	    	}
	    	
	    	
	    	return true;
	    }
	    
	    function btnAddSave_onClickCheck(){
	    	 // 点击后5秒内不能再次提交
			btnAddSave.setDisabled(true);
			var timer = setInterval(function(){//开启定时器
				btnAddSave.setDisabled(false);
				clearInterval(timer);    //清除定时器
			},5000); 
		  return true;	
	    }
	  	
	     function btnAddSave_postSubmit() {
	 		$.success("操作成功!", function() {
	 			window_windowAddActive.close();
	 			activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
	 		});
	 	}
	  	
	  	
	 //  *********************************恢复/暂停活动-方法******************************
	 //点击恢复/暂停方法按钮
	 function btnStatus_onClick(){
		 var activeNo = activeInfo_dataset.getValue("activeNo");
		 if(activeNo == ''){
			 $.warn("请先选择要修改的方法记录");
			 return;
		 }
		 var activeStat = activeInfo_dataset.getValue("activeStat");
		 if(activeStat =='99'){
			 $.warn("该活动已结束,不允许继续操作");
			 return;
		 }
		 var auditFlag = activeInfo_dataset.getValue("auditFlag");
		 if(auditFlag == '01'||auditFlag=='02'|| auditFlag == '03' || auditFlag == '04'|| auditFlag == '05'|| auditFlag == '06'|| auditFlag == '07'){
				$.warn("活动处于待审核状态，不允许修改状态！");
			    return false; 
		  }
		 var message,statuFlag;
		 if(activeStat =='12'){
			 message="是否确认恢复该活动?";
			 statuFlag="1";
		 }else{
			 message="是否确认暂停该活动(活动暂停会清空该活动下商户)?";
			 statuFlag="0";
		 }
		
			$.confirm(message, function() {
				activeInfo_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
				activeInfo_dataset.setParameter("statuFlag",statuFlag);
				//window_windowStatusAuditUser.open();
				btnStatusSave.click();
			}, function() {
			});
	 	}
	 	
	 
	  //状态操作成功后
	    function btnStatusSave_postSubmit(){
	    	$.success("操作成功!", function() {
	    		activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
				//auditUser_dataset.clearData();
				//window_windowStatusAuditUser.close();
			});
	    }
	   
	 //  *********************************活动详情-方法******************************
	 //详情链接点击时
	 function showDetails(){
		 activeInfo_dataset.setFieldReadOnly("activeNm",true);
		 activeInfo_dataset.setFieldReadOnly("activeType",true);
		 activeInfo_dataset.setFieldReadOnly("SDate",true);
		 activeInfo_dataset.setFieldReadOnly("EDate",true);
		 activeInfo_dataset.setFieldReadOnly("budgetTp",true);
		 activeInfo_dataset.setFieldReadOnly("activeBudget",true);
		 activeInfo_dataset.setFieldReadOnly("platBudget",true);
		 activeInfo_dataset.setFieldReadOnly("activeLv",true);
		 activeInfo_dataset.setFieldReadOnly("acctLimitType",true);
		 activeInfo_dataset.setFieldReadOnly("acctCntMax",true);
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",true);
		 activeInfo_dataset.setFieldReadOnly("cycleFlg",true);
		 activeInfo_dataset.setFieldReadOnly("prodId",true);
		 
		 cycleInfo_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
		 cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
		 var activeNo = activeInfo_dataset.getValue("activeNo");
  		 redBagConf_dataset.setParameter("activeNo",activeNo);
  		 redBagConf_dataset.flushData(redBagConf_dataset.pageIndex);
		 window_windowDetailActive.open();
	 }
	 //详情框体打开后
	 function window_windowDetailActive_afterOpen(win){
		 var cycleFlag = activeInfo_dataset.getValue("cycleFlg");
		 if('0'==cycleFlag || cycleFlag==''){
			//当0-不是  时
			cycleInfo_dataset.clearData();
			$("#gridDetailCycle").css("display", "none");
		}else if('1'==cycleFlag){
			$("#gridDetailCycle").css("display", "block");
		}
		 var activeType = activeInfo_dataset.getValue("activeType");
		 if('21' == activeType){
			 $("#gridDtlRed01").css("display", "block");
			 $("#RedConf").css("display", "block");
	    	 $("#RedConf2").css("display", "block");
		 }else{
			 $("#gridDtlRed01").css("display", "none");
			 $("#RedConf").css("display", "none");
	    	 $("#RedConf2").css("display", "none");
		 }
		 
	 }
	//详情框体关闭后
	 function window_windowDetailActive_beforeClose(win){
		 activeInfo_dataset.setFieldReadOnly("activeNm",false);
		 activeInfo_dataset.setFieldReadOnly("activeType",false);
		 activeInfo_dataset.setFieldReadOnly("SDate",false);
		 activeInfo_dataset.setFieldReadOnly("EDate",false);
		 activeInfo_dataset.setFieldReadOnly("budgetTp",false);
		 activeInfo_dataset.setFieldReadOnly("activeBudget",false);
		 activeInfo_dataset.setFieldReadOnly("platBudget",false);
		 activeInfo_dataset.setFieldReadOnly("activeLv",false);
		 activeInfo_dataset.setFieldReadOnly("acctLimitType",false);
		 activeInfo_dataset.setFieldReadOnly("acctCntMax",false);
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",false);
		 activeInfo_dataset.setFieldReadOnly("cycleFlg",false);
		 activeInfo_dataset.setFieldReadOnly("prodId",false);
		 activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
		 cycleInfo_dataset.setParameter("activeNo","");
		 cycleInfo_dataset.clearData();
	 }
	 //  *********************************活动修改******************************
	 //修改活动按钮点击时
	  function btnMod_onClick(){
		 
		 var activeNo = activeInfo_dataset.getValue("activeNo");
		 if(activeNo == ''){
			 $.warn("请先选择要修改的方法记录");
			 return;
		 }
		 var activeStat = activeInfo_dataset.getValue("activeStat");
		 if(activeStat =='99'){
			 $.warn("该活动已结束,不允许继续修改");
			 return;
		 }
		 var auditFlag = activeInfo_dataset.getValue("auditFlag");
		 if(auditFlag == '01'|| auditFlag == '03' || auditFlag == '04'|| auditFlag == '05'|| auditFlag == '06'|| auditFlag == '07'){
				$.warn("活动处于待审核状态，不允许修改！");
			    return false; 
		  }
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",true);
		 activeInfo_dataset.setFieldReadOnly("activeType",true);
		 activeInfo_dataset.setFieldReadOnly("activeLv",true);
		 activeInfo_dataset.setFieldRequired("activeLv",false);
		 cycleInfo_dataset.setParameter("activeNo",activeInfo_dataset.getValue("activeNo"));
		 cycleInfo_dataset.flushData(cycleInfo_dataset.pageIndex);
		 var activeNo = activeInfo_dataset.getValue("activeNo");
  		 redBagConf_dataset.setParameter("activeNo",activeNo);
  		 redBagConf_dataset.flushData(redBagConf_dataset.pageIndex);
		 window_windowUpdActive.open();
	 }
	
	 
	 //修改活动框体打开后
	 function  window_windowUpdActive_beforeOpen(wmf){
		 
		 var budgetTp = activeInfo_dataset.getValue("budgetTp");
		activeInfo_dataset.setFieldReadOnly("budgetTp",true);
		//初始化预算
		if('00'==budgetTp){
			//当00-无  时
			activeInfo_dataset.setFieldReadOnly( "activeBudget", true);
			activeInfo_dataset.setFieldReadOnly( "platBudget", true);
		}else if('01'==budgetTp){
			//01-银行全资	
			activeInfo_dataset.setFieldReadOnly( "activeBudget", true);
			activeInfo_dataset.setFieldReadOnly( "platBudget", true);
		}
		/* else if('02'==budgetTp){
			//02-比例分摊	
			activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
			activeInfo_dataset.setFieldReadOnly( "platBudget", false);
		}else if('03'==budgetTp){
			//03-顺序分摊	
			activeInfo_dataset.setFieldReadOnly( "activeBudget", false);
			activeInfo_dataset.setFieldReadOnly( "platBudget", false);
		} */
		//初始化周期
		var cycleTp = activeInfo_dataset.getValue("cycleFlg");
		
		if('0'==cycleTp || cycleTp==''){
			//当0-不是  时
			$("#gridUpdCycle").css("display", "none");
	    	$("#btnAddCycle01").css("display", "none");
	  		$("#btnDeleteCycle01").css("display", "none");
		}else if('1'==cycleTp){
			$("#gridUpdCycle").css("display", "block");
	    	$("#btnAddCycle01").css("display", "");
	  		$("#btnDeleteCycle01").css("display", "");
		}
	     	
		//初始化红包配置信息
		var activeType = activeInfo_dataset.getValue("activeType");
		if('21' == activeType){
			$("#gridUpdRed01").css("display", "block");
			$("#RedConf").css("display", "block");
	    	$("#RedConf2").css("display", "block");
   			$("#btnAddSection01").css("display", "");
	     	$("#btnDeleteSection01").css("display", "");
	     	$("#formUpdActive1_8 #input_activeInfo_acctCntMax").append("<div id='aCM' style='float:left;width=100px;height=30px;color:red'>&nbsp;&nbsp;&nbsp'限制次数'对于微信被扫和支付宝被扫交易无效</div>");
		}else{
			$("#gridUpdRed01").css("display", "none");
			$("#RedConf").css("display", "none");
	    	$("#RedConf2").css("display", "none");
   			$("#btnAddSection01").css("display", "none");
	     	$("#btnDeleteSection01").css("display", "none");
		}
		
		//初始化账户限制
		var acctLimitTp = activeInfo_dataset.getValue("acctLimitType");
		
		if('0'==acctLimitTp){
			//当0-无限制  时
			activeInfo_dataset.setFieldReadOnly( "acctCntMax", true);
		}else{
			//活动状态为首单立减-11,账户限制与限制次数置灰
			if('11'==activeType){
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", true);
				activeInfo_dataset.setFieldRequired( "acctCntMax", true);
				activeInfo_dataset.setFieldReadOnly("acctLimitType",true);
			//活动状态为红包立减-21,账户限制置灰,限制次数放开
			}else if('21'==activeType){
				activeInfo_dataset.setFieldReadOnly("acctLimitType",true);
				activeInfo_dataset.setFieldRequired("acctCntMax", false);
				activeInfo_dataset.setFieldReadOnly( "acctCntMax", false);
			}
			}
		
	  }
	 
	
	 function  window_windowUpdActive_beforeClose(wmf){
		 //关闭窗口时移除拼接的提示div
		 $("#formAddActive1_8 #aCM").remove();
		 $("#formUpdActive1_8 #aCM").remove();
		 activeInfo_dataset.setFieldReadOnly("budgetTp",false);
		 activeInfo_dataset.setFieldReadOnly("activeBudget",false);
		 activeInfo_dataset.setFieldReadOnly("platBudget", false);
		 activeInfo_dataset.setFieldReadOnly("activeLv",false);
		 activeInfo_dataset.setFieldRequired("activeLv",true);
	 }
	 
	 //修改保存按钮点击时
	 function btnModAudit_onClick(){
		 var result = checkInput();
	     	if(!result){
	     		return;
	     	}
	     	btnModSave.click();
	 }
	
  	//修改活动成功后回调方法
     function btnModSave_postSubmit() {
 		$.success("操作成功!", function() {
 			//window_windowUpdAuditUser.close();
 			//auditUser_dataset.clearData();
 			window_windowUpdActive.close();
 		});
 	}
  	
	//修改活动框体关闭后
	 function  window_windowUpdActive_afterClose(wmf){
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",false);
		 activeInfo_dataset.setFieldReadOnly("activeType",false);
		 activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
		 cycleInfo_dataset.setParameter("activeNo","");
		 cycleInfo_dataset.clearData();
	}
	 
	 //  *********************************活动配置-方法******************************
	 //点击配置按钮时
	 function btnConfig_onClick(){
		 var activeNo=activeInfo_dataset.getValue("activeNo");
		if(activeNo==null||activeNo==''){
			$.warn("请先选择要进行配置的活动信息！");
			return false;
		}
		 var activeStat = activeInfo_dataset.getValue("activeStat");
		 if(activeStat =='99'){
			 $.warn("该活动已结束,不允许继续操作");
			 return;
		 }
		 var auditFlag = activeInfo_dataset.getValue("auditFlag");
		 if(auditFlag == '01'||auditFlag=='02' ||auditFlag == '03' || auditFlag == '04'|| auditFlag == '05'|| auditFlag == '06'|| auditFlag == '07'){
				$.warn("活动处于待审核状态，不允许配置！");
			    return false; 
		  }
		 window_windowConfig.open(); 
		 acctGp_dataset.setParameter("qGpTpNo",activeInfo_dataset.getValue("cardGpNo"));
		 acctGp_dataset.flushData(acctGp_dataset.pageIndex,function(){
			 methodConfig_dataset.setParameter("qActiveNo",activeNo);
			 methodConfig_dataset.flushData(methodConfig_dataset.pageIndex,function(){
				 windowConfig_init();
			 });
			});
	 }
	 
	 //配置框体打开时，动态改变grid
	 function window_windowConfig_beforeOpen(){
		 activeInfo_dataset.setFieldReadOnly("activeNm",true);
		 activeInfo_dataset.setFieldReadOnly("activeType",true);
		 activeInfo_dataset.setFieldReadOnly("activeLv",true);
		 activeInfo_dataset.setFieldReadOnly("budgetTp",true);
		 activeInfo_dataset.setFieldReadOnly("activeBudget",true);
		 activeInfo_dataset.setFieldReadOnly("platBudget",true);
		 activeInfo_dataset.setFieldReadOnly("acctLimitType",true);
		 activeInfo_dataset.setFieldReadOnly("acctCntMax",true);
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",true);
	 }
	 //dataset初始化后，初始化方法配置grid
	 function windowConfig_init(){
		 var firstRecord = acctGp_dataset.getFirstRecord();
		 var seqs = firstRecord.getValue("gpNm");
		 var seqNm = seqs.split(",");
		 for(var i=0;i<seqNm.length;i++){
			 //改变field的lable
			 methodConfig_dataset.getField("methodParam"+(i+1)).label=seqNm[i];
			 var span = $("td[columnname='methodParam"+(i+1)+"']").find("span");
			 span.html(seqNm[i]);
			 span.attr("title",seqNm[i]);
			 span.css("display","block");
			 methodConfig_dataset.setFieldReadOnly(("methodParam"+(i+1)),false);
			 methodConfig_dataset.setFieldRequired(("methodParam"+(i+1)),true);
		 }
		 for(var j=seqNm.length;j<6;j++){
			 //改变field的lable
			 methodConfig_dataset.getField("methodParam"+(j+1)).label="";
			 var span = $("td[columnname='methodParam"+(j+1)+"']").find("span");
			 span.css("display","none");
			 methodConfig_dataset.setFieldReadOnly(("methodParam"+(j+1)),true);
			 methodConfig_dataset.setFieldRequired(("methodParam"+(j+1)),false);
		 }
	 }
	 //当下拉选择框展开时,根据活动类型加载dataset
	 function methodConfig_dataset_methodParam1_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 function methodConfig_dataset_methodParam2_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 function methodConfig_dataset_methodParam3_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 function methodConfig_dataset_methodParam4_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 function methodConfig_dataset_methodParam5_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 function methodConfig_dataset_methodParam6_beforeOpen(dropDown,dropDownDataset){
		 dropDown.cache=false;
		 dropDownDataset.setParameter("qActiveType",activeInfo_dataset.getValue("activeType"));
		 dropDownDataset.flushData(dropDownDataset.pageIndex);
	 }
	 
	 function btnConfigAudit_onClick(){
		var firstRecord = methodConfig_dataset.getFirstRecord();
  		if(firstRecord == null || firstRecord ==''){
  			$.warn("暂未配置活动方法，不能提交");
  			return;
  		}
		if(!methodConfig_dataset.validate()){
			return;
		}
		/* var mchtLevels = new Array();
		while(firstRecord){
			if($.inArray(firstRecord.getValue("mchtLevel"),mchtLevels) != -1){
				$.warn("商户等级不允许重复,请检查");
				return;
			}else{
				mchtLevels.push(firstRecord.getValue("mchtLevel"));
			}
			firstRecord = firstRecord.getNextRecord();
		} */
		btnConfigSave.click();
	 }
	
	 function btnConfigSave_postSubmit() {
 		$.success("操作成功!", function() {
 			//window_windowConfigAuditUser.close();
 			window_windowConfig.close();
 			activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
 		});
	 }
	 
	//配置框体关闭时
	 function window_windowConfig_beforeClose(){
		 activeInfo_dataset.setFieldReadOnly("activeNm",false);
		 activeInfo_dataset.setFieldReadOnly("activeType",false);
		 activeInfo_dataset.setFieldReadOnly("activeLv",false);
		 activeInfo_dataset.setFieldReadOnly("budgetTp",false);
		 activeInfo_dataset.setFieldReadOnly("activeBudget",false);
		 activeInfo_dataset.setFieldReadOnly("platBudget",false);
		 activeInfo_dataset.setFieldReadOnly("acctLimitType",false);
		 activeInfo_dataset.setFieldReadOnly("acctCntMax",false);
		 activeInfo_dataset.setFieldReadOnly("cardGpNo",false);
		 activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
	}
	 //  *********************************参与商户-方法******************************
	 //参与商户按钮点击时
	 function btnMchtIn_onClick(){
		 var activeNo =activeInfo_dataset.getValue("activeNo");
		 if(activeNo == null || activeNo== ''){
			 $.warn("请先选择一条要配置的活动信息");
			 return;
		 }
		 var activeStat = activeInfo_dataset.getValue("activeStat");
		 if(activeStat =='99'){
			 $.warn("该活动已结束,不允许继续操作");
			 return;
		 }else if(activeStat == '12'){
			  $.warn("活动暂停中，不允许添加商户！");
			    return false; 
		  }
		 var auditFlag = activeInfo_dataset.getValue("auditFlag");
		 if(auditFlag == '01'|| auditFlag=='02' ||auditFlag == '03' || auditFlag == '04'|| auditFlag == '05'|| auditFlag == '06'|| auditFlag == '07'){
				$.warn("活动处于待审核状态，不允许添加商户！");
			    return false; 
		  }
		 $.open("mchtIn", "参与商户", "/pages/payPmp/mktActivity/actvMchtIn.jsp?qActiveNo="+activeNo, "1100", "800", false, true, null,false, "返回");
	 }
	 
	 
	 function mchtIn_onButtonClick (i,win,framewin){
			if(i==1){//取消按钮
				win.close();
				activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
			}
				mchtIn.close();
				activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);	
				
		}
	 
	 
	 /* 
	 function mchtIn_onButtonClick (i,win,framewin){
			if(i==1){//取消按钮
				win.close();
				activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
			}else if(i==0){
				var checkResult = framewin.submitCheck();
			}
						
		} */
	
	 
	
	</script>
</snow:page>