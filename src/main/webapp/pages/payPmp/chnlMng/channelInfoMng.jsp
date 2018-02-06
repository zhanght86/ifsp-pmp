<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="渠道信息管理">
	<!-- 渠道信息数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.channelInfo" id="channelInfo" init="true" submitMode="current"></snow:dataset>
	<!-- 渠道权限配置数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.channelAuthInfo" id="channelAuthInfo" submitMode="all" init="fasle"></snow:dataset>
	
	
	<!-- 查询条件 -->
	<snow:query dataset="channelInfo" label="查询条件" collapsible="false"
		fieldstr="qchlId,qchlName,qchlStat,qchlAccNo"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="channelInfo" height="99%" label="渠道信息" id="gridId"
		fitcolumns="true"
		fieldstr="chlId,chlName,chlOpenDate,chlStat,chlAccNo,oper"
		paginationbar="btnAdd,btnMod,btnUpdate,powerAdd"></snow:grid>

	 <!-- 新增页面 -->
	<snow:window id="windowAddId" closable="true" width="850"
		title="新增" modal="true" buttons="btnSave">
		<snow:form id="formAddId" dataset="channelInfo"
			fieldstr="chlName,chlStat,chlSetlCycleType,chlSetlCycle,chlSetlTm,chlRateCode,chlAccNo"
			 label="渠道信息"></snow:form>
		<snow:button id="btnSave" dataset="channelInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 修改页面 -->
	<snow:window id="windowModId" closable="true" width="100%"
		title="修改" modal="true" buttons="btnSave1">
		<snow:form id="formModId" dataset="channelInfo" 
			fieldstr="chlId,chlName,chlStat,chlSetlCycleType,chlSetlCycle,chlSetlTm,chlRateCode,chlAccNo"
			label="渠道基本信息"></snow:form>
		<snow:button id="btnSave1" dataset="channelInfo" hidden="true"></snow:button>
	</snow:window>

	<div style="display: none;">
		<snow:button id="btnUpdateSub" dataset="channelInfo"></snow:button>
	</div>
	
	 <!-- 渠道权限配置窗体 -->		
	<snow:window id="windowPowerAdd" title="渠道权限" modal="true" closable="true" buttons="powerAddSave" width="900" height="600">
		<snow:form id="formPowerAdd" dataset="channelInfo" border="true" label="渠道基本信息" collapsible="true"  
			fieldstr="chlId,chlName"></snow:form>
			<br />
			<snow:grid id="gridPowerAdd" dataset="channelAuthInfo"
			fieldstr="payTxnTypeId[220],acctTypeNo[550]" height="320" border="true"
			label="渠道权限配置" toolbar="toolbarconfProdInfo" editable="true" fitcolumns="false" pagination="false"
			 collapsible="true">
		    </snow:grid>
		    <snow:toolbar id="toolbarconfProdInfo">
			<snow:button id="btnAddConfig" dataset="channelAuthInfo"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteConfig" dataset="channelAuthInfo"
				hidden="false"></snow:button>
		    </snow:toolbar>
        <!-- 按钮: 添加 -->
		<snow:button id="powerAddSave" dataset="channelInfo" hidden="true"></snow:button>
	</snow:window> 
	
	
	 <!-- 渠道详情窗口 -->
    <snow:window id="winChannelInfo" title="渠道详情" modal="true" closable="true"
		buttons="" width="900" height="700"> 
		<br />
		<p style="text-align:left;font-size:15px" >基本信息</p>
		<snow:form id="formChannelInfo" dataset="channelInfo" label="产品详情"
			border="false" collapsible="true" 
			fieldstr="chlId,chlName,chlStat">
		</snow:form>
		<br /> 
		<p style="text-align:left;font-size:15px" >清算信息</p>
		<snow:form id="formChannelInfo" dataset="channelInfo" label="产品详情"
			border="false" collapsible="true" 
			fieldstr="chlSetlCycleType,chlSetlCycle,chlSetlTm,chlRateCode,chlAccNo">
		</snow:form>
		<br /> 	
		<p style="text-align:left;font-size:15px" >渠道权限</p>
		<snow:grid id="gridChannelAuthInfo" dataset="channelAuthInfo"  fitcolumns="true" fieldstr="payTxnTypeId[220],acctTypeNo[550]" height="300" >
		</snow:grid>
	</snow:window> 


<script>
//添加详情链接
function gridId_oper_onRefresh(record, fieldId, fieldValue){
		return    "<span style='width:100%;text-align:center' class='fa fa-eye'><a href=\"JavaScript:onClickQuery()\">详情</a></span>";
}
//***************************************渠道新增********************************************//
 //点击新增按钮时
function btnAdd_onClick(){
	channelInfo_dataset.setReadOnly(false);
	window_windowAddId.open();
}
//点击保存按钮对数据进行校验     
function btnSave_onClickCheck(button, commit) {
	//获取渠道名称
	var chlName=channelInfo_dataset.getValue("chlName");
	//获取渠道状态
	var chlStat=channelInfo_dataset.getValue("chlStat");
	//获取清算周期类型                 
	var chlSetlCycleType=channelInfo_dataset.getValue("chlSetlCycleType");
	//获取清算周期
	var chlSetlCycle=channelInfo_dataset.getValue("chlSetlCycle");
	//获取清算时间
	var chlSetlTm=channelInfo_dataset.getValue("chlSetlTm");
	//获取费率模板
	var chlRateCode=channelInfo_dataset.getValue("chlRateCode");
	//获取清算账户
	var chlAccNo=channelInfo_dataset.getValue("chlAccNo");
	//=============字段非空验证==================//
	if(chlName== null || chlName==''){
		$.warn("请输入渠道名称！");
	    return false; 
	}if(chlStat== null || chlStat==''){
		$.warn("请选择渠道状态！");
	    return false; 
	}if(chlSetlCycleType== null || chlSetlCycleType==''){
		$.warn("请选择清算周期类型！");
	    return false; 
	}if(chlSetlCycle== null || chlSetlCycle==''){
		$.warn("请输入清算周期！");
	    return false; 
	}if(chlRateCode== null || chlRateCode==''){
		$.warn("请选择费率模板！");
	    return false; 
	}
	//=============字段格式，长度验证==================//
	if(chlName.length>21){
	    $.warn("渠道名称超过最大长度【21】！");
	    return false;
	}
	
   if(chlAccNo != null){
	   if(chlAccNo.length>13){
	     	$.warn("清算账户超过最大长度【13】！");
	     	return false;
	   }
   }
  //=============当清算周期选择为天时，清算日填入1~365，代表T+1~T+365；当清算周期选择为星期时，清算日填入1~7,
  //=============代表星期一到星期日；当清算周期选择为月度时，清算日填入1~31，代表月度的具体日期
   if("01"==chlSetlCycleType){
	   //此时清算日期输入为1~365
	   var reg=/^([1-9][0-9]?|[12][0-9]{2}|3[0-5][0-9]|36[0-5])$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误,当周期类型为【天】时,请输入【1~365】数字");
			return false;
		}
   }if("02"==chlSetlCycleType){
	   //此时清算为1~7数字
	   var reg=/^[1-7]$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误，当周期类型为【星期】时,请输入【1~7】数字");
			return false;
		}
   }if("03"==chlSetlCycleType){
	   //此时清算为1~31数字
	   var reg=/^([1-9]|[12][0-9]|3[01])$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误，当周期类型为【月度】时,请输入【1~31】数字");
			return false;
		}
   }
   //验证时分秒格式hhmmss
   var timeCheck=/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
   if(chlSetlTm!=null && chlSetlTm!=''){
	   if(!timeCheck.test(chlSetlTm)){
	    $.warn("清算时间格式错误，请输入时间:HH(24小时制):mm:ss");
		return false;
	   }
   }
   return true;
}
/**清算周期下拉事件（当【清算周期】改变选择时，清除字段【清算日】）*/
function channelInfo_dataset_chlSetlCycleType_onSelect(value,record){	
	channelInfo_dataset.setValue("chlSetlCycle","");
}
//插入数据成功时
function btnSave_postSubmit(){
	$.success("操作成功!", function() {
		window_windowAddId.close();
		channelInfo_dataset.flushData(channelInfo_dataset.pageIndex);
    });
}

//新增窗口关闭时，取消当前记录
function window_windowAddId_beforeClose(win){
	channelInfo_dataset.cancelRecord();
}

//***************************************渠道修改********************************************//

 //点击修改按钮时
function btnMod_onClick(){
	channelInfo_dataset.setFieldReadOnly("chlId",true);
	channelInfo_dataset.setFieldReadOnly("chlStat",true);
	window_windowModId.open();
	
}
//点击修改保存按钮对数据进行校验     
function btnSave1_onClickCheck(button, commit) {
	//获取渠道名称
	var chlName=channelInfo_dataset.getValue("chlName");
	//获取清算周期类型                 
	var chlSetlCycleType=channelInfo_dataset.getValue("chlSetlCycleType");
	//获取清算周期
	var chlSetlCycle=channelInfo_dataset.getValue("chlSetlCycle");
	//获取清算时间
	var chlSetlTm=channelInfo_dataset.getValue("chlSetlTm");
	//获取费率模板
	var chlRateCode=channelInfo_dataset.getValue("chlRateCode");
	//获取清算账户
	var chlAccNo=channelInfo_dataset.getValue("chlAccNo");
	//=============字段非空验证==================//
	if(chlName== null || chlName==''){
		$.warn("请输入渠道名称！");
	    return false; 
	}if(chlSetlCycleType== null || chlSetlCycleType==''){
		$.warn("请选择清算周期类型！");
	    return false; 
	}if(chlSetlCycle== null || chlSetlCycle==''){
		$.warn("请输入清算周期！");
	    return false; 
	}if(chlSetlTm== null || chlSetlTm==''){
		$.warn("请输入清算时间！");
	    return false; 
	}if(chlRateCode== null || chlRateCode==''){
		$.warn("请选择费率模板！");
	    return false; 
	}
	//=============字段格式，长度验证==================//
	if(chlName.length>21){
	    $.warn("渠道名称超过最大长度【21】！");
	    return false;
	}
	if(chlAccNo != null){
		if(chlAccNo.length>13){
		    $.warn("清算账户超过最大长度【13】！");
		    return false;
		 }
	}
  //=============当清算周期选择为天时，清算日填入1~365，代表T+1~T+365；当清算周期选择为星期时，清算日填入1~7,
  //=============代表星期一到星期日；当清算周期选择为月度时，清算日填入1~31，代表月度的具体日期
   if("01"==chlSetlCycleType){
	   //此时清算日期输入为1~365
	   var reg=/^([1-9][0-9]?|[12][0-9]{2}|3[0-5][0-9]|36[0-5])$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误，请输入【1~365】数字");
			return false;
		}
   }if("02"==chlSetlCycleType){
	   //此时清算为1~7数字
	   var reg=/^[1-7]$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误，请输入【1~7】数字");
			return false;
		}
   }if("03"==chlSetlCycleType){
	   //此时清算为1~31数字
	   var reg=/^([1-9]|[12][0-9]|3[01])$/;
	   if(!reg.test(chlSetlCycle)){
			$.warn("清算周期格式错误，请输入【1~31】数字");
			return false;
		}
   }
   //验证时分秒格式hhmmss
   var timeCheck=/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
   if(!timeCheck.test(chlSetlTm)){
	   $.warn("清算时间格式错误，请输入时间:HH(24小时制):mm:ss");
		return false;
   }
   return true;
}
//修改关闭时，只读模式设置回来
function window_windowModId_afterClose(){
	channelInfo_dataset.setFieldReadOnly("chlId",false);
	channelInfo_dataset.setFieldReadOnly("chlStat",false);
	channelInfo_dataset.flushData(channelInfo_dataset.pageIndex);
}
//修改数据成功时
function btnSave1_postSubmit(){
	$.success("操作成功!", function() {
		window_windowModId.close();
		channelInfo_dataset.flushData(channelInfo_dataset.pageIndex);
    });
}


//***************************************渠道启用/停用********************************************//
//点击启用/停用按钮时
function btnUpdate_onClick(){
	//得到渠道状态  02-未启用，00-启用，99-停用';
	var chlStat = channelInfo_dataset.getValue("chlStat");
	var chlId = channelInfo_dataset.getValue("chlId");
    var msg = "";
	if(chlStat == "00"){
	     msg = "是否要将该渠道状态修改为【停用】?";
	}else{
		 msg = "是否要将该渠道状态修改为【启用】?";
	}
	$.confirm(msg, function() {
		 channelInfo_dataset.setParameter("chlStat",chlStat);
		 channelInfo_dataset.setParameter("chlId",chlId);
		 btnUpdateSub.click();
		 }, function() {
		        return false;
		 });
}
var btnUpdateSub_postSubmit = function() {
	$.success("操作成功!", function() {
		channelInfo_dataset.flushData(channelInfo_dataset.pageIndex);
	});
}; 
//***************************************渠道权限********************************************//
//单击渠道权限按钮
function powerAdd_onClick() {
	channelInfo_dataset.setFieldReadOnly("chlId", true);
	channelInfo_dataset.setFieldReadOnly("chlName", true);
	var chlId = channelInfo_dataset.getValue("chlId");
	if (chlId == '') {
		$.warn("请先选择渠道信息！");
		return;
	}
	channelAuthInfo_dataset.setParameter("qchlId",chlId);
	channelAuthInfo_dataset.flushData(channelAuthInfo_dataset.pageIndex);

	window_windowPowerAdd.open();	
}
//单击保存，对数据进行验证
function powerAddSave_onClickCheck(button, commit) {
   //获取路由交易
   var payTxnTypeId=channelAuthInfo_dataset.getValue("payTxnTypeId");
  /*  if(payTxnTypeId == null || payTxnTypeId==''){
	   $.warn("请完善渠道权限,至少有一条渠道权限配置！");
     	return false;
   } */
   if(payTxnTypeId != null || payTxnTypeId != ''){
	   //对配置进行重复验证      
	   var firstRecord = channelAuthInfo_dataset.getFirstRecord();//获取产品配置第一行数据
			var exists = [];//存在的交易编号和 账户编号
			// 遍历
			while (firstRecord) {
				var payTxnTypeId = firstRecord.getValue("payTxnTypeId");	
				for(var i = 0; i < exists.length; i++){
	//				console.info(exists[i]);
					if(payTxnTypeId== exists[i].payTxnTypeId){
						$.warn("您配置的交易[ " + firstRecord.getValue("payTxnTypeIdName") + "] 出现重复!");
						return false;
					}
				}
				var exist = {};
				exist["payTxnTypeId"] = payTxnTypeId;
				exists.push(exist);
				firstRecord = firstRecord.getNextRecord();
			}
	   }
		return true;
}
//成功回调函数
function powerAddSave_postSubmit() {
	$.success("操作成功!", function() {
		//添加结束关闭窗体
		window_windowPowerAdd.close();
		channelInfo_dataset.flushData(channelInfo_dataset.pageIndex);
	});
}
//数据取消只读模式
function window_windowPowerAdd_afterClose(){
	channelInfo_dataset.setFieldReadOnly("chlId", false);
	channelInfo_dataset.setFieldReadOnly("chlName", false);
}
//***************************************详情功能********************************************//
//详情相关操作
function onClickQuery(){
	channelInfo_dataset.setFieldReadOnly("chlId", true);//渠道编号
	channelInfo_dataset.setFieldReadOnly("chlName", true);//渠道名称
	channelInfo_dataset.setFieldReadOnly("chlStat", true);//渠道状态
	channelInfo_dataset.setFieldReadOnly("chlSetlCycleType", true);//清算周期类型
	channelInfo_dataset.setFieldReadOnly("chlSetlCycle", true);//清算周期
	channelInfo_dataset.setFieldReadOnly("chlSetlTm", true);//清算时间
	channelInfo_dataset.setFieldReadOnly("chlRateCode", true);//费率模板
	channelInfo_dataset.setFieldReadOnly("chlAccNo", true);//清算账户
	channelAuthInfo_dataset.setFieldReadOnly("payTxnTypeId", true);//路由交易
	channelAuthInfo_dataset.setFieldReadOnly("acctTypeNo", true);//账户类型
	//刷新数据集
	var chlId = channelInfo_dataset.getValue("chlId");
	
	channelAuthInfo_dataset.setParameter("qchlId",chlId);
	channelAuthInfo_dataset.flushData(channelAuthInfo_dataset.pageIndex);
	window_winChannelInfo.open();	
}
//预览关闭窗口刷新
function window_winChannelInfo_afterClose(){
	channelInfo_dataset.setFieldReadOnly("chlId", false);//渠道编号
	channelInfo_dataset.setFieldReadOnly("chlName", false);//渠道名称
	channelInfo_dataset.setFieldReadOnly("chlStat", false);//渠道状态
	channelInfo_dataset.setFieldReadOnly("chlSetlCycleType", false);//清算周期类型
	channelInfo_dataset.setFieldReadOnly("chlSetlCycle", false);//清算周期
	channelInfo_dataset.setFieldReadOnly("chlSetlTm", false);//清算时间
	channelInfo_dataset.setFieldReadOnly("chlRateCode", false);//费率模板
	channelInfo_dataset.setFieldReadOnly("chlAccNo", false);//清算账户
	channelAuthInfo_dataset.setFieldReadOnly("payTxnTypeId", false);//路由交易
	channelAuthInfo_dataset.setFieldReadOnly("acctTypeNo", false);//账户类型
}


</script>

</snow:page>
