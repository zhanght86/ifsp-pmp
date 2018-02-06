<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动方法管理">
	<style>
		* {margin: 0;padding: 0;}
		#box {margin: 30px auto;width: 100%;height: 100%;}
	</style>
	 <script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascrpt"  src="../../public/lib/jquery/jquery.form.js"></script>
    <!--------------- 活动方法临时信息: 数据集 ----------------->
    <snow:dataset id="methodInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvMethodTmp" submitMode="current" init="true" parameters=""></snow:dataset>
     <!--------------- 活动方法分段临时信息: 数据集 ----------------->
    <snow:dataset id="methodSectionInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.actvMethodSectionTmp" submitMode="all" init="false"></snow:dataset>
     <!--------------- 活动临时信息: 数据集 ----------------->
	<snow:dataset id="activeInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.actvInfTmpByMethod" submitMode="all" init="false"></snow:dataset>
	<!--------------- 审核信息记录数据集 ----------------->
	<snow:dataset path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" id="checkNewRecordInfo" submitMode="current" init="false"></snow:dataset>
	
	<!-- 页面查询框体 -->
	<snow:query label ="请输入查询条件" id= "methodQuery" dataset= "methodInfo" fieldstr ="qMethodNo,qMethodName,qMethodType"></snow:query>
	
	<!-- 活动方法信息列表框体 -->
	<snow:grid dataset ="methodInfo" id="methodGrid" height="99%" fieldstr= "methodNo,methodNm,methodTp,param1Tp,param1Data,param2Tp,param2Data,useState,auditFlag,opr"   paginationbar="btnAdd,btnDelete,btnUpdate" fitcolumns ="true"></snow:grid>
	
	<!-- 详情查看框体 -->
	<snow:window id ="windowDetails" title="活动方法详情" modal= "true" closable ="true"  width= "1000" height= "600">
		<!-- 活动方法基本信息展示 -->
		<snow:form label ="活动方法基本信息" dataset="methodInfo" fieldstr= "methodNo,methodTp,methodNm,param1Tp,param1Data,param2Tp,param2Data"
            id= "corePost" colnum ="4" border="true" collapsible= "true"></snow:form >
        <!-- 活动方法分段信息列表展示 -->
        <!-- 分段信息列表 -->
		<snow:grid id="gridDtlExt01"  dataset="methodSectionInfo" fieldstr="sectionSeq[100],sectionLeft[100],sectionRight[100],sectionParam1[100],sectionParam2[100]" 
			height="99%" border="true" label="方法分段信息" editable="false" fitcolumns="true" pagination="false" 
			collapsible="true">
		</snow:grid> 
	</snow:window>
	
	
	<!-- 新增活动方法框体 -->
	<snow:window id="windowAddMethod" title="活动方法新增" modal="true" closable="true" buttons="btnAddAudit">
		<center>方法基本信息</center>
		<snow:form id="formAddMethod" dataset="methodInfo"  border="false" label="方法基本信息" fieldstr="methodNo,methodTp,methodNm,param1Tp,param1Data,param2Tp,param2Data" collapsible="false" colnum="4"></snow:form>
		<!-- 分段信息列表 -->
		<snow:grid id="gridAddExt01"  dataset="methodSectionInfo" fieldstr="sectionLeft[100],sectionRight[100],sectionParam1[100],sectionParam2[100]" 
			height="99%" border="true" label="方法分段信息" editable="true" fitcolumns="true" pagination="false" 
			toolbar="toolbarExt01" collapsible="true">
		</snow:grid> 
		<snow:toolbar id="toolbarExt01">
			<snow:button id="btnAddSection01" dataset="methodSectionInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="methodSectionInfo" hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnAddAudit" dataset="methodInfo" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 使用状态详情框体 -->
	<snow:window id="windowUseDetail" title="方法详情" modal="true" closable="true">
		<snow:form id="formUseDetail" dataset="methodInfo"  border="false" label="方法基本信息" fieldstr="methodNo,methodTp,methodNm,param1Tp,param1Data,param2Tp,param2Data" collapsible="false" colnum="4"></snow:form>
		<snow:grid id="gridUseDetail" width="99%" dataset="activeInfo" fieldstr="activeNo[120],activeNm[140],activeType[120],SDate[120],EDate[120]" 
			height="200" border="true" label="活动列表信息" editable="false" fitcolumns="true" pagination="true" collapsible="true">
		</snow:grid> 
	</snow:window>
	
	<!-- 修改活动方法框体 -->
	<snow:window id ="windowModify" title="活动方法修改" modal= "true" closable ="true"  width= "1000" height= "600" buttons="btnUpdateAudit">
		<!-- 活动方法基本信息展示 -->
		<snow:form label ="活动方法基本信息" dataset="methodInfo" fieldstr= "methodNo,methodTp,methodNm,param1Tp,param1Data,param2Tp,param2Data"
            id= "corePost" colnum ="4" border="true" collapsible= "true"></snow:form >
        <!-- 活动方法分段信息列表展示 -->
        <!-- 分段信息列表 -->
		<snow:grid id="gridModExt01"  dataset="methodSectionInfo" fieldstr="sectionLeft[100],sectionRight[100],sectionParam1[100],sectionParam2[100]" 
			height="99%" border="true" label="方法分段信息" editable="true" fitcolumns="true" pagination="false" 
			collapsible="true" toolbar="toolbarExt02" >
		</snow:grid> 
		<snow:toolbar id="toolbarExt02">
			<snow:button id="btnAddSection01" dataset="methodSectionInfo" hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="methodSectionInfo" hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnUpdateAudit" dataset="methodInfo" hidden="true"></snow:button>
	</snow:window>
	<!-- 处于待审核，及拒绝时，超链接窗口 -->
    <snow:window id="windowAuditFlag" title="审核相关信息" modal="true" closable="true"
		buttons="" width="900" height="550"> 
		<p style="font" >活动方法基本信息</p>
		<snow:form id="formMchtInfo" dataset="methodInfo" label="活动方法基本信息"
			border="false" collapsible="true" 
			fieldstr="methodNo,methodTp,methodNm,auditId">
		</snow:form>
		<p style="font" >审核流程信息</p>
		<snow:grid id="gridCheckNewRecordInfo" dataset="checkNewRecordInfo"  fitcolumns="true" fieldstr="stepNo[95],auditState[115],roleName[115],auditDatetIme[200],auditView[320]" height="300" >
		</snow:grid>
	</snow:window> 
	<snow:button id="btnAddSave" dataset="methodInfo" hidden="true"></snow:button>
	<snow:button id="btnDeleteSave" dataset="methodInfo" hidden="true"></snow:button>
	<snow:button id="btnUpdateSave" dataset="methodInfo" hidden="true"></snow:button>
	<script type ="text/javascript">
		//  *********************************公共变量声明******************************   
		var isAmt=/^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
		var isNum=/^\d{1,10}$/;
		var sectionSeq=/^\d{1,2}$/;
		var isPercentNum = /^(\d{1,2}|100)$/;
		/** 1-99 */
		var isPercentNum2 = /^[1-9][0-9]{0,1}$/
		
		//  *********************************查询列表功能方法****************************** 
	     //当列表页面加载后，在操作栏追加操作连接
		 function methodGrid_opr_onRefresh(record, fieldId, fieldValue) {
	         if (record) {
	              return "<a href=\"JavaScript:showDetails()\">详情</a>" ;
	        }
	  	}
		//当列表页面加载后，根据方法类型对参数1做数据转换
		 function methodGrid_param1Data_onRefresh(record, fieldId, fieldValue) {
			 var methodTp = record.getValue("methodTp");
	        if(/* methodTp =='11' ||*/  methodTp=='21' || methodTp=='31' || methodTp=='32'){
	        	if(fieldValue.length!=0){
		        	var str = (fieldValue/100).toFixed(2) + '';
		        	return str;
	        	}
	        }
	        return fieldValue;
	  	}
		//当列表页面加载后，取使用状态字段值，如果为使用中，则可以点击查询使用的活动方法列表
	   	 function methodGrid_useState_onRefresh(record, fieldId, fieldValue) {
	        if (fieldValue=='使用中') {
	             return "<a href=\"JavaScript:showUseActive()\">使用中</a>" ;
	       }
	        return fieldValue;
	    }
		
	 //  *********************************审核功能****************************** 	
	   //当列表页面加载后，刷新审核字段
	   	 function methodGrid_auditFlag_onRefresh(record, fieldId, fieldValue){
	   	//得到审核信息编号
	 	    var auditId=methodInfo_dataset.getValue("auditId");
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
	 	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">删除待审核 </a></span>";	
	 	    }
	 	    if(fieldValue == '00'){
	 	    	return '正常';	
	 	    }
	   }
		
	   	 //点击状态按钮显示对应信息
	     function onClickWin(auditId){
	    	 methodInfo_dataset.setFieldReadOnly("methodNo", true);
	    	 methodInfo_dataset.setFieldReadOnly("methodTp", true);
	    	 methodInfo_dataset.setFieldReadOnly("methodNm", true);
	    	 methodInfo_dataset.setFieldReadOnly("auditId", true);
	         //根据审核信息编号，查询审核记录表
	         var auditId = methodInfo_dataset.getValue("auditId")
	         checkNewRecordInfo_dataset.setParameter("auditId",auditId);
	         checkNewRecordInfo_dataset.flushData(checkNewRecordInfo_dataset.pageIndex);
	     	 window_windowAuditFlag.open();
	     }
	   	 
	   	 
	     //当框体关闭时，取消字段的只读
	     function window_windowAuditFlag_beforeClose(wmf){
			 methodInfo_dataset.setFieldReadOnly( "methodNo", false);
			 methodInfo_dataset.setFieldReadOnly( "methodTp", false);
			 methodInfo_dataset.setFieldReadOnly( "methodNm", false);
			 methodInfo_dataset.setFieldReadOnly("auditId", false);
			 methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
	  	 }
	   	 
	   	 
	    /*  //审核框体打开后
		 function window_windowAuditFlag_afterOpen(win){
			 var methodTp = methodInfo_dataset.getValue("methodTp");
			 //当方法类型为11-首单立减时
			 if("11"==methodTp){
				 showDtlSection(true);
				 var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔减免上限");
	    		columnParam1.attr("title","单笔减免上限");
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
			 }
		 } */
	   	 
	   	 
		
		//  *********************************详细信息功能方法****************************** 
	     //点击详情连接时，查看活动方法详细信息
		 function showDetails(){
			 methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
	    	 methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
			 methodInfo_dataset.setFieldReadOnly( "methodNo", true);
			 methodInfo_dataset.setFieldReadOnly( "methodTp", true);
			 methodInfo_dataset.setFieldReadOnly( "methodNm", true);
			 methodInfo_dataset.setFieldReadOnly( "param1Data", true);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", true);
			 var methodTp = methodInfo_dataset.getValue("methodTp");
		        if(methodTp=='21'){
		        	var fieldValue = methodInfo_dataset.getValue("param1Data");
		        	if(fieldValue.length!=0){
			        	var str = (fieldValue/100).toFixed(2) + '';
			        	methodInfo_dataset.setValue("param1Data",str);
		        	}
		        }
	         var methodNo = methodInfo_dataset.getValue("methodNo");
	         var methodTp = methodInfo_dataset.getValue("methodTp");
	         methodSectionInfo_dataset.setParameter("methodNo",methodNo);
	         methodSectionInfo_dataset.setParameter("methodTp",methodTp);
	         methodSectionInfo_dataset.flushData(methodSectionInfo_dataset.pageIndex);
	         window_windowDetails.open();
	  	 }
	     //详情框体打开后
		 function window_windowDetails_afterOpen(win){
			 var methodTp = methodInfo_dataset.getValue("methodTp");
			 //当方法类型为11-手续费减免时
			 if("11"==methodTp){
				 showDtlSection(true);
				 var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔减免上限");
	    		columnParam1.attr("title","单笔减免上限");
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
			 }else if("21"==methodTp){
				//当方法类型为21-t+0结算时
				 showDtlSection(false);
			 }
	//--------------------------------------活动类型待拓展---------------------------------------------------------		 
		/* 	 else if("31"==methodTp){
				 //当方法类型为31-打折时
			 	showDtlSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔折扣(%)");
	    		columnParam1.attr("title","单笔折扣(%)");
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
			 }else if("32"==methodTp){
				 //当方法类型为32-折后减后凑整
				 showDtlSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔折扣(%)");
	    		columnParam1.attr("title","单笔折扣(%)");
	    		//启用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","block");
	    		columnParam2.html("折后减免");
	    		columnParam2.attr("title","折后减免");
			 }else if("33"==methodTp){
				//当方法类型为33-满额顺序抽奖时
				 showDtlSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("奖励等级");
	    		columnParam1.attr("title","奖励等级");
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
			 }else if("34"==methodTp){
				//当方法类型为34-满额随机抽奖时
				 showDtlSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("中奖概率(%)");
	    		columnParam1.attr("title","中奖概率(%)");
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
			 } */
			 
	//-------------------------------------------------------------------------------------------------------		 
		 }
		//控制是否显示分段信息列表和工具栏
	     function showDtlSection(showFlag){
	    	 if(showFlag == true){
	    		 $("#gridDtlExt01").css("display", "block");
	     	 	
	    	 }else if (showFlag==false){
	    		$("#gridDtlExt01").css("display", "none");
	    	 }
	     }
	     //当详情框体关闭时，取消字段的只读
	     function window_windowDetails_beforeClose(wmf){
	    	 methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
	    	 methodInfo_dataset.setFieldReadOnly( "param2Tp", false);
			 methodInfo_dataset.setFieldReadOnly( "methodNo", false);
			 methodInfo_dataset.setFieldReadOnly( "methodTp", false);
			 methodInfo_dataset.setFieldReadOnly( "methodNm", false);
			 methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", false);
			 methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
	  	 }
	 //  *********************************新增活动方法功能方法******************************
     	//当点击新增按钮时，弹出新增窗体
	     function btnAdd_onClick(){
	    	 methodInfo_dataset.setFieldReadOnly( "methodNo", true);
	    	 methodInfo_dataset.setFieldReadOnly( "methodTp", false);
	    	 $("#gridAddExt01").css("display", "none");
	    	 $("#btnAddSection01").css("display", "none");
	  		 $("#btnDeleteSection01").css("display", "none");
	  		 methodSectionInfo_dataset.setParameter("methodNo",null);
	  		 methodSectionInfo_dataset.flushData(methodSectionInfo_dataset.pageIndex);
	    	 window_windowAddMethod.open();
	     }
	     //当添加活动方法框体关闭时,取消记录
	     function window_windowAddMethod_beforeClose(wmf){
	    	 methodInfo_dataset.cancelRecord();
	    	 methodInfo_dataset.setReadOnly(false);
	     }
	     //方法类型下拉列表改变时
	     function methodInfo_dataset_methodTp_onSelect(v,record){
	    	 
	    	 //当选择11-首单立减时，参数限制类型1、2转换值，并清空data1、2，data2可以修改参数
	    	if("11"==v){
	    		methodInfo_dataset.setValue("param1Tp","41");
	    		methodInfo_dataset.setValue("param2Tp","");
	    		methodInfo_dataset.setValue("param2Data","");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param1Data", false);
	    		methodInfo_dataset.setFieldRequired( "param1Tp", true);
	    		methodInfo_dataset.setFieldRequired( "param1Data", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Tp", false);
	    		methodInfo_dataset.setFieldRequired( "param2Data", false);
	    		//留下单笔手续费减免上限-金额类输入
	    		showSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔减免上限");
	    		columnParam1.attr("title","单笔减免上限");
	    		methodSectionInfo_dataset.getField("sectionParam1").label="单笔减免上限";
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
	    		methodSectionInfo_dataset.setFieldReadOnly("sectionParam2",true);
	    		methodSectionInfo_dataset.setFieldRequired("sectionParam2",false);
	    	}else if("21"==v){
	    		//当选择21 红包立减时,参数限制值1为最小参与金额,2为立减红包/交易金额最大百分比
	    		methodInfo_dataset.setValue("param1Tp","51");
	    		methodInfo_dataset.setValue("param2Tp","52");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param1Data", false);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", false);
	    		methodInfo_dataset.setFieldRequired( "param1Tp", true);
	    		methodInfo_dataset.setFieldRequired( "param2Tp", true);
	    		methodInfo_dataset.setFieldRequired( "param1Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Data", true);
	    		showSection(false);
	    	}
	   //-----------------------------活动类型待拓展-----------------------------------------------------   	
	    	/*else if("31"==v){
	    		//当选择31-打折时，参数限制类型1转换值，类型2赋空值，不可以操作，非必须输入
	    		//methodInfo_dataset.setValue("param1Tp","12");
	    		methodInfo_dataset.setValue("param1Data","");
	    		//methodInfo_dataset.setValue("param2Tp","");
	    		methodInfo_dataset.setValue("param2Data","");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Data", false);
	    		showSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔折扣(%)");
	    		columnParam1.attr("title","单笔折扣(%)");
	    		methodSectionInfo_dataset.getField("sectionParam1").label="单笔折扣";
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
	    		methodSectionInfo_dataset.setFieldReadOnly("sectionParam2",true);
	    		methodSectionInfo_dataset.setFieldRequired("sectionParam2",false);
	    	}else if("32"==v){
	    		//当选择32-折后减后凑整时，参数限制类型1转换值，类型2赋空值，不可以操作，非必须输入
	    		//methodInfo_dataset.setValue("param1Tp","12");
	    		methodInfo_dataset.setValue("param1Data","");
	    		//methodInfo_dataset.setValue("param2Tp","");
	    		methodInfo_dataset.setValue("param2Data","");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Data", false);
	    		showSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("单笔折扣(%)");
	    		columnParam1.attr("title","单笔折扣(%)");
	    		methodSectionInfo_dataset.getField("sectionParam1").label="单笔折扣";
	    		//启用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","block");
	    		columnParam2.html("折后减免");
	    		columnParam2.attr("title","折后减免");
	    		methodSectionInfo_dataset.getField("sectionParam2").label="折后减免";
	    		methodSectionInfo_dataset.setFieldReadOnly("sectionParam2",false);
	    		methodSectionInfo_dataset.setFieldRequired("sectionParam2",true);
	    	}else if("33"==v){
	    		//当选择33-满额顺序抽奖时，参数限制类型1转换值，类型2赋空值，不可以操作，非必须输入
	    		//methodInfo_dataset.setValue("param1Tp","32");
	    		methodInfo_dataset.setValue("param1Data","");
	    		//methodInfo_dataset.setValue("param2Tp","");
	    		methodInfo_dataset.setValue("param2Data","");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Data", false);
	    		showSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("奖励等级");
	    		columnParam1.attr("title","奖励等级");
	    		methodSectionInfo_dataset.getField("sectionParam1").label="奖励等级";
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
	    		methodSectionInfo_dataset.setFieldReadOnly("sectionParam2",true);
	    		methodSectionInfo_dataset.setFieldRequired("sectionParam2",false);	
	    	}else if("34"==v){
	    		//当选择34-满额随机抽奖时，参数限制类型1转换值，类型2赋空值，不可以操作，非必须输入
	    		//methodInfo_dataset.setValue("param1Tp","32");
	    		methodInfo_dataset.setValue("param1Data","");
	    		//methodInfo_dataset.setValue("param2Tp","");
	    		methodInfo_dataset.setValue("param2Data","");
	    		methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
	    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
	    		methodInfo_dataset.setFieldRequired( "param2Data", false);
	    		showSection(true);
	    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
	    		columnParam1.html("中奖概率(%)");
	    		columnParam1.attr("title","中奖概率(%)");
	    		methodSectionInfo_dataset.getField("sectionParam1").label="中奖概率";
	    		//禁用第二列
	    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
	    		columnParam2.css("display","none");
	    		methodSectionInfo_dataset.setFieldReadOnly("sectionParam2",true);
	    		methodSectionInfo_dataset.setFieldRequired("sectionParam2",false);	
	    	} */
	   //--------------------------------------------------------------------------------------------------------   		 
	    	methodSectionInfo_dataset.flushData(methodSectionInfo_dataset.pageIndex);
	     }
	     //控制是否显示分段信息列表和工具栏
	     function showSection(showFlag){
	    	 if(showFlag == true){
	    		$("#gridAddExt01").css("display", "block");
	     	 	$("#btnAddSection01").css("display", "");
	     	  	$("#btnDeleteSection01").css("display", "");
	    	 }else if (showFlag==false){
	    		$("#gridAddExt01").css("display", "none");
	    	 	$("#btnAddSection01").css("display", "none");
	    	  	$("#btnDeleteSection01").css("display", "none");
	    	 }
	     }
	     //新增提交按钮点击时，校验提交内容并打开审核用户信息验证
	     function btnAddAudit_onClick(){
	     	var result = methodInfo_dataset.validate();
	     	if(result == false){
	     		return;
	     	}
	     	if(methodSectionInfo_dataset.validate() == false){
	     		return;
	     	}
	     	if(methodInfo_dataset.getValue("methodNm").length>20){
	     		$.warn("方法名称最大长度只能是20位");
	     		return;
	     	}
	     	var checkResult = checkInputByType();
	     	if(!checkResult){
	     		return;
	     	}
	     	//window_windowAuditUser.open();
	     	btnAddSave.click();
	     }
	     //新增或修改时，输入内容格式校验
	     function checkInputByType(){
	     	//根据方法类型判断值
	     	var methodTp = methodInfo_dataset.getValue("methodTp");
	     	//活动类型为11-手续费减免
	     	if(methodTp == '11'){
	     		 if(!isNum.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("首单用户限制数量格式错误（最大长度10位数字）");
	     			return false;
	     		} 
	     		var firstRecord = methodSectionInfo_dataset.getFirstRecord();
	     		if(firstRecord == null || firstRecord ==''){
	     			$.warn("当前方法类型必须输入分段信息");
	     			return false;
	     		}
	     		
	     		while(firstRecord){
	     			if(!checkSectionRange(firstRecord)){
	     				return false;
	     			}
	     			if(!isAmt.test(firstRecord.getValue("sectionParam1"))){
	     				$.warn("分段信息“单笔减免上限”格式有误(最大长度10位数字,最多包含两位小数)");
	     				return false;
	     			}
	     			firstRecord = firstRecord.getNextRecord();
	     		}
	     	}else if(methodTp == '21'){
	     		//活动类型为21 红包立减
	     		//校验字段1(立减红包)
	     		if(!isAmt.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("立减红包金额格式错误（最大长度10位数字,最多包含两位小数）");
	     			return false;
	     		}
	     		//校验字段2(立减红包/交易金额最大百分比(单位:%))
	     		if(!isPercentNum2.test(methodInfo_dataset.getValue("param2Data"))){
	     			$.warn("立减红包/交易金额最大百分比格式错误（1-99）");
	     			return false;
	     		}
	     		
	     	}
	     	
	  //-------------------------活动类型待拓展-------------------------------------------------------------------   	
/* 	     	else if(methodTp == '31'){
	     		//活动类型为31-打折
	     		if(!isAmt.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("单月单商户营销金额格式错误（最大长度10位数字,最多包含两位小数）");
	     			return false;
	     		}
	     		var firstRecord = methodSectionInfo_dataset.getFirstRecord();
	     		if(firstRecord == null || firstRecord ==''){
	     			$.warn("当前方法类型必须输入分段信息");
	     			return false;
	     		}
	     		
	     		while(firstRecord){
	     			if(!checkSectionRange(firstRecord)){
	     				return false;
	     			}
	     			if(!isPercentNum.test(firstRecord.getValue("sectionParam1"))){
	     				$.warn("分段信息“单笔折扣”格式有误(1到100的整数,不允许包含小数)");
	     				return false;
	     			}
	     			firstRecord = firstRecord.getNextRecord();
	     		}
	     	}else if(methodTp == '32'){
	     		//32-折后减后凑整
	     		if(!isAmt.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("单月单商户营销金额格式错误（最大长度10位数字,最多包含两位小数）");
	     			return false;
	     		}
	     		var firstRecord = methodSectionInfo_dataset.getFirstRecord();
	     		if(firstRecord == null || firstRecord ==''){
	     			$.warn("当前方法类型必须输入分段信息");
	     			return false;
	     		}
	     		
	     		while(firstRecord){
	     			
	     			if(!checkSectionRange(firstRecord)){
	     				return false;
	     			}
	     			if(!isPercentNum.test(firstRecord.getValue("sectionParam1"))){
	     				$.warn("分段信息“单笔折扣”格式有误(1到100的整数,不允许包含数字)");
	     				return false;
	     			}
	     			if(!isAmt.test(firstRecord.getValue("sectionParam2"))){
	     				$.warn("分段信息“折后减免”格式有误(最大长度10位数字,最多包含两位小数)");
	     				return;
	     			}
	     			firstRecord = firstRecord.getNextRecord();
	     		}
	     	}else if(methodTp == '33'){
	     		//33-满额顺序抽奖
	     		if(!isNum.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("活动单商户营销次数格式错误（最大长度10位数字）");
	     			return false;
	     		}
	     		var firstRecord = methodSectionInfo_dataset.getFirstRecord();
	     		if(firstRecord == null || firstRecord ==''){
	     			$.warn("当前方法类型必须输入分段信息");
	     			return false;
	     		}
	     		while(firstRecord){
	     			var awardLv = firstRecord.getValue("sectionParam1");
	     			if(!checkSectionRange(firstRecord)){
	     				return false;
	     			}
	     			if(awardLv.length >= 6){
	     				$.warn("分段信息“奖励等级”格式有误(最大长度6位字符)");
	     				return false;
	     			}
	     			
	     			firstRecord = firstRecord.getNextRecord();
	     		}
	     	}else if(methodTp == '34'){
	     		//34-满额随机抽奖
	     		if(!isNum.test(methodInfo_dataset.getValue("param1Data"))){
	     			$.warn("活动单商户营销次数格式错误（最大长度10位数字）");
	     			return false;
	     		}
	     		var firstRecord = methodSectionInfo_dataset.getFirstRecord();
	     		if(firstRecord == null || firstRecord ==''){
	     			$.warn("当前方法类型必须输入分段信息");
	     			return false;
	     		}
	     		
	     		while(firstRecord){
	     			if(!checkSectionRange(firstRecord)){
	     				return false;
	     			}
	     			if(!isPercentNum.test(firstRecord.getValue("sectionParam1"))){
	     				$.warn("分段信息“中奖概率”格式有误(1到100的整数,不允许包含数字)");
	     				return false;
	     			}
	     			firstRecord = firstRecord.getNextRecord();
	     		}
	     	} */
	     	
  	//---------------------------------------------------------------------------------------------------- 	
	     	return true;
	     }
	     function checkSectionRange(firstRecord){
	    	 if(!isAmt.test(firstRecord.getValue("sectionLeft"))){
  				$.warn("分段信息“左闭”格式有误(最大长度10位数字,最多包含两位小数)");
  				return false;
  			}
	    	 if(firstRecord.getValue("sectionRight") !=''){
	    		 if(!isAmt.test(firstRecord.getValue("sectionRight"))){
	   				$.warn("分段信息“右开”格式有误(最大长度10位数字,最多包含两位小数)");
	   				return false;
	   			}
	   			if(parseFloat(firstRecord.getValue("sectionLeft")) >= parseFloat(firstRecord.getValue("sectionRight"))){
	   				$.warn("分段信息“左闭”必须小于“右开”");
	   				return false;
	   			}
	    	 }
  			return true;
	     }
	   
	     function btnAddSave_postSubmit() {
	 		$.success("操作成功!", function() {
	 			window_windowAddMethod.close();
	 			methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
	 		});
	 	}
	 
	   //  *********************************使用状态功能方法******************************
	   	//当点击使用状态为使用中的时候，查询获取活动列表
	   	function showUseActive(){
	   		methodInfo_dataset.setFieldReadOnly( "methodNo", true);
	   		methodInfo_dataset.setFieldReadOnly( "methodTp", true);
	   		methodInfo_dataset.setFieldReadOnly( "methodNm", true);
	   		methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
	   		methodInfo_dataset.setFieldReadOnly( "param1Data", true);
	   		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	   		methodInfo_dataset.setFieldReadOnly( "param2Data", true);
		     var adPostId= methodInfo_dataset.getValue("methodNo" );
		     activeInfo_dataset.setParameter( "methodNo", adPostId);
		     activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
		     
		     window_windowUseDetail.open();
	   	}
	    //当使用状态详细信息框体关闭时，取消字段的只读
	    function window_windowUseDetail_beforeClose(wmf){
	    	
			 methodInfo_dataset.setFieldReadOnly( "methodTp", false);
			 methodInfo_dataset.setFieldReadOnly( "methodNm", false);
			 methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", false);
	 	 }
		//  *********************************删除方法功能方法******************************
	    //删除按钮点击关闭时
	    function  btnDelete_onClickCheck(){
	    	var methodNo=methodInfo_dataset.getValue("methodNo");
			if(methodNo==null||methodNo==''){
				$.warn("请先选择要删除的方法信息！");
				return false;
			}
			var useState=methodInfo_dataset.getValue("useState");
			if(useState=='使用中'){
				$.warn("不能删除使用中的方法！");
				return false;
			}
			var auditFlag=methodInfo_dataset.getValue("auditFlag");
			if(auditFlag == '01'|| auditFlag == '03' || auditFlag == '04'){
				$.warn("方法处于待审核状态，不允许删除！");
			    return false; 
			}
			if(auditFlag == '02'){
				$.warn("无法删除未生效的方法！");
			    return false; 
			}
			//弹出审核用户输入信息
			$.confirm("删除后数据不可恢复,是否确认？", function() {
				methodInfo_dataset.setParameter("methodNo",methodInfo_dataset.getValue("methodNo"));
				btnDeleteSave.click();
			}, function() {
			})
	    }
	
		//删除成功后
	    function btnDeleteSave_postSubmit(){
	    	$.success("操作成功!", function() {
				methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
			});
	    }
	 
	  //  *********************************修改活动方法功能方法******************************
	  //更新按钮点击
	  function btnUpdate_onClick(){
		  var methodNo=methodInfo_dataset.getValue("methodNo");
		  if(methodNo ==''){
			  $.warn("请先选择要修改的活动方法");
			  return;
		  }
		  var auditFlag=methodInfo_dataset.getValue("auditFlag");
			if(auditFlag == '01'|| auditFlag == '03' || auditFlag == '04'){
				$.warn("方法处于待审核状态，不允许修改！");
			    return false; 
			}
		  var methodTp=methodInfo_dataset.getValue("methodTp");
		  methodInfo_dataset.setFieldReadOnly( "methodNo", true);
		  methodInfo_dataset.setFieldReadOnly( "methodTp", true);
		  methodSectionInfo_dataset.setParameter("methodNo",methodNo);
		  methodSectionInfo_dataset.setParameter("methodTp",methodTp);
	      methodSectionInfo_dataset.flushData(methodSectionInfo_dataset.pageIndex);
		  window_windowModify.open();
	  }
	  //修改框体打开后，对form和grid数据进行处理
	  function window_windowModify_afterOpen(win){
		  var methodTp = methodInfo_dataset.getValue("methodTp");
		  //当方法类型为11-首单立减
		  if("11"==methodTp){
			 showModSection(true);
			 methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
			 methodInfo_dataset.setFieldRequired("param1Tp", true);
			 methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			 methodInfo_dataset.setFieldRequired("param1Data", true);
	    	 methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", true);
		     var columnParam1 = $("td[columnname='sectionParam1']").find("span");
    		 columnParam1.html("单笔减免上限");
    		 columnParam1.attr("title","单笔减免上限");
    		 methodSectionInfo_dataset.getField("sectionParam1").label="单笔减免上限";
    		 //禁用第二列
    		 var columnParam2 = $("td[columnname='sectionParam2']").find("span");
    		 columnParam2.css("display","none");
    		 /* //转换参数1分为元
    		 var fieldValue = methodInfo_dataset.getValue("param1Data");
    		 if(fieldValue.length!=0){
		         var str = (fieldValue/100).toFixed(2) + '';
		         methodInfo_dataset.setValue("param1Data",str);
    		 } */
		  }else if("21"==methodTp){
			 //当方法类型为21-红包立减时
			 showModSection(false);
			 methodInfo_dataset.setFieldReadOnly( "param1Tp", true);
	    	 methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
	    	 methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", false);
			 //转换参数1分为元
    		 var fieldValue = methodInfo_dataset.getValue("param1Data");
	         var str = (fieldValue/100).toFixed(2) + '';
	         methodInfo_dataset.setValue("param1Data",str);
		  }
	//--------------------------------------------活动类型待拓展---------------------------------------------------	  
		 /*  else if("31"==methodTp){
			 //当方法类型为31-打折时
		 	 showModSection(true);
		  	 methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
    		 methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
    		 methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			 methodInfo_dataset.setFieldReadOnly( "param2Data", true);
    		 var columnParam1 = $("td[columnname='sectionParam1']").find("span");
    		 columnParam1.html("单笔折扣(%)");
    		 columnParam1.attr("title","单笔折扣(%)");
    		 methodSectionInfo_dataset.getField("sectionParam1").label="单笔折扣";
    		 //禁用第二列
    		 var columnParam2 = $("td[columnname='sectionParam2']").find("span");
    		 columnParam2.css("display","none");
    		 //转换参数1分为元
    		 var fieldValue = methodInfo_dataset.getValue("param1Data");
	         var str = (fieldValue/100).toFixed(2) + '';
	         methodInfo_dataset.setValue("param1Data",str);
		  }else if("32"==methodTp){
			 //当方法类型为32-折后减后凑整
			showModSection(true);
			methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
    		methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			methodInfo_dataset.setFieldReadOnly( "param2Data", true);
    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
    		columnParam1.html("单笔折扣(%)");
    		columnParam1.attr("title","单笔折扣(%)");
    		 methodSectionInfo_dataset.getField("sectionParam1").label="单笔折扣";
    		//启用第二列
    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
    		columnParam2.css("display","block");
    		columnParam2.html("折后减免");
    		columnParam2.attr("title","折后减免");
    		 methodSectionInfo_dataset.getField("sectionParam2").label="折后减免";
    		//转换参数1分为元
   		 	var fieldValue = methodInfo_dataset.getValue("param1Data");
	        var str = (fieldValue/100).toFixed(2) + '';
	        methodInfo_dataset.setValue("param1Data",str);
		 }else if("33"==methodTp){
			//当方法类型为33-满额顺序抽奖时
			showDtlSection(true);
			methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
    		methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			methodInfo_dataset.setFieldReadOnly( "param2Data", true);
    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
    		columnParam1.html("奖励等级");
    		columnParam1.attr("title","奖励等级");
    		methodSectionInfo_dataset.getField("sectionParam1").label="奖励等级";
    		//禁用第二列
    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
    		columnParam2.css("display","none");
		 }else if("34"==methodTp){
			//当方法类型为34-满额随机抽奖时
			showDtlSection(true);
			showSection(true);
			methodInfo_dataset.setFieldReadOnly( "param1Tp", false);
    		methodInfo_dataset.setFieldReadOnly( "param2Tp", true);
    		methodInfo_dataset.setFieldReadOnly( "param1Data", false);
			methodInfo_dataset.setFieldReadOnly( "param2Data", true);
    		var columnParam1 = $("td[columnname='sectionParam1']").find("span");
    		columnParam1.html("中奖概率(%)");
    		columnParam1.attr("title","中奖概率(%)");
    		methodSectionInfo_dataset.getField("sectionParam1").label="中奖概率";
    		//禁用第二列
    		var columnParam2 = $("td[columnname='sectionParam2']").find("span");
    		columnParam2.css("display","none");
		 } */
	  }
	 //修改框关闭后，对form和grid数据进行处理
	 function window_windowModify_afterClose(win){
		 methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
	 }
	 //控制是否显示分段信息列表和工具栏
     function showModSection(showFlag){
    	 if(showFlag == true){
    		 $("#gridModExt01").css("display", "block");
     	 	
    	 }else if (showFlag==false){
    		$("#gridModExt01").css("display", "none");
    	 }
     }
	 //修改框体点击提交按钮时间
	 function btnUpdateAudit_onClick(){
	 	var result = methodInfo_dataset.validate();
     	if(result == false){
     		return;
     	}
     	if(methodSectionInfo_dataset.validate() == false){
     		return;
     	}
     	if(methodInfo_dataset.getValue("methodNm").length>20){
     		$.warn("方法名称最大长度只能是20位");
     		return;
     	}
     	var checkResult = checkInputByType();
     	if(!checkResult){
     		return;
     	}
     	btnUpdateSave.click();
	 }
	
	 //修改提交成功后
    function btnUpdateSave_postSubmit(){
    	$.success("操作成功!", function() {
			methodInfo_dataset.flushData(methodInfo_dataset.pageIndex);
			window_windowModify.close();
		});
    }
	
	</script>
</snow:page>