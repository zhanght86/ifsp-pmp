<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import=" com.ruimin.ifs.mng.comp.MchtAgentAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="代理商信息管理">
	<snow:dataset id="mchtAgent" path="com.ruimin.ifs.mng.dataset.mchtAgent" init="true" submitMode="current" ></snow:dataset>	
		<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="mchtAgent" label="查询条件" fieldstr="qagentId,qagentName"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="mchtAgent" label="代理商信息管理" fieldstr="agentId,agentName,opr"
		height = "99%" paginationbar="btnAdd,btnUpd,btnDlt"></snow:grid>
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="详情信息"  modal="true" closable="true">
			<!-- No1 基本信息模块 -->
			<snow:form id="frmAddBase" label="基本信息" dataset="mchtAgent" fieldstr="agentId,agentName,contactsName,contactsPhone,contactsAddr,zipNo"></snow:form>
			<snow:form id="frmAddSetl" label="结算账户信息" dataset="mchtAgent" fieldstr="setlAcctName,setlAcctNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlAcctAreaCode"></snow:form>
			<snow:form id="frmAddProfit" label="分润信息" dataset="mchtAgent" fieldstr="profitShareType,profitShareScale"></snow:form>
		</snow:window>
		<!-- 新增窗口 -->
		<snow:window id="winAdd" title="新增"  modal="true" closable="true"  buttons="btnAddSub">
			<!-- No1 基本信息模块 -->
			<snow:form id="frmAddBase" label="基本信息" dataset="mchtAgent" fieldstr="agentName,contactsName,contactsPhone,contactsAddr,zipNo"></snow:form>
			<snow:form id="frmAddSetl" label="结算账户信息" dataset="mchtAgent" fieldstr="setlAcctName,setlAcctNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlAcctAreaCode"></snow:form>
			<snow:form id="frmAddProfit" label="分润信息" dataset="mchtAgent" fieldstr="profitShareType,profitShareScale"></snow:form>
			<snow:button id="btnAddSub" dataset="mchtAgent" hidden="true"></snow:button>		
		</snow:window>
		<!-- 修改窗口 -->
		<snow:window id="winUpd" title="修改"  modal="true" closable="true" buttons="btnUpdSub">
			<!-- No1 基本信息模块 -->
			<snow:form id="frmAddBase" label="基本信息" dataset="mchtAgent" fieldstr="agentId,agentName,contactsName,contactsPhone,contactsAddr,zipNo"></snow:form>
			<snow:form id="frmAddSetl" label="结算账户信息" dataset="mchtAgent" fieldstr="setlAcctName,setlAcctNo,setlAcctInstitute,acctInstNo,setlAcctBankNo,setlAcctBankName,setlAcctAreaCode"></snow:form>
			<snow:form id="frmAddProfit" label="分润信息" dataset="mchtAgent" fieldstr="profitShareType,profitShareScale"></snow:form>
			<snow:button id="btnUpdSub" dataset="mchtAgent" hidden="true"></snow:button>
		</snow:window>
		      <!-- 删除按钮 -->
      <snow:button id="btnDltSubmit" dataset="mchtAgent" hidden="true"> </snow:button>
	<script type="text/javascript">
		/**详情显示*/
		function gridMain_opr_onRefresh(record) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
			}
		}
		/**打开详情窗口*/
		function onClickDetail(){
			//全部字段设置为-只读
			mchtAgent_dataset.setReadOnly(true);
			var profitShareType= mchtAgent_dataset.getValue("profitShareType");
			if(profitShareType=="02"){
				$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商分润比例(%)");				
				}
				if(profitShareType=="01"){
					$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商成本费率(‰)");				
				}
			//打开窗口
			window_winDetail.open();
		}
		/**窗口关闭后清除数据*/
		function window_winDetail_afterClose(){
			mchtAgent_dataset.setReadOnly(false);//还原写入权限
		}
		/**************************新增**************************/
		//点击新增按钮
		function btnAdd_onClick(){
			window_winAdd.open();
		}
		//分润方式切换，改变分润比例单位
		function mchtAgent_dataset_profitShareType_onSelect(v,record){
			if(v=="02"){
			$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商分润比例(%)");				
			}
			if(v=="01"){
				$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商成本费率(‰)");				
			}
		}
		function btnAddSub_onClickCheck(button,commit){
			//校验【基本信息】和【资质信息】
			if(!validInfo()){
				return false;
			}
			return true;
		 }
		function btnAddSub_postSubmit(){
			$.success("操作成功!",function(){
	 		   window_winAdd.close();
	 		  mchtAgent_dataset.flushData(mchtAgent_dataset.pageIndex);
	 	   });
		}
		/**窗口关闭后清除数据*/
		function window_winAdd_beforeClose(){
			mchtAgent_dataset.cancelRecord();
		}

		/**************************修改**************************/
		//点击修改按钮
		function btnUpd_onClick(){
			var agentId= mchtAgent_dataset.getValue("agentId");
			var profitShareType= mchtAgent_dataset.getValue("profitShareType");
			if(!agentId){
				$.warn("请先选择一条记录");
				return false;
			}
			if(profitShareType=="02"){
				$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商分润比例(%)");				
				}
				if(profitShareType=="01"){
					$("#frmAddProfit_0 #fieldlabel_profitShareScale").html("代理商成本费率(‰)");				
				}
			 window_winUpd.open();
		}
		function btnUpdSub_onClickCheck(button,commit){
			//校验【基本信息】和【资质信息】
			if(!validInfo()){
				return false;
			}
			return true;
		 }
		function btnUpdSub_postSubmit(){
			$.success("操作成功!",function(){
	 		   window_winUpd.close();
	 		  mchtAgent_dataset.flushData(mchtAgent_dataset.pageIndex);
	 	   });
		}

		function window_winUpd_afterClose(){
			mchtAgent_dataset.flushData(mchtAgent_dataset.pageIndex);
		}
		
		/**************************删除**************************/
		//点击删除按钮
		function btnDlt_onClick(){
			var agentId = mchtAgent_dataset.getValue("agentId");
			if(!agentId){
				$.warn("请先选择一条记录");
				return false;
			}
			$.confirm("是否确认删除？一经删除后该信息无法恢复！", function() {
				btnDltSubmit.click();
			}, function() {
				return false;
			});
		}
		//删除按钮回调
		function btnDltSubmit_postSubmit(){
			$.success("操作成功!",function(){
				mchtAgent_dataset.flushData(1);
	 	   });
		}
		function mchtAgent_dataset_setlAcctInstitute_onSelect(v,record){
			if(record!=null){
				var ptyCd = record.getValue("ptyCd");
				mchtAgent_dataset.setValue("acctInstNo",ptyCd);
			}else{
				mchtAgent_dataset.setValue("setlAcctInstitute","");
				mchtAgent_dataset.setValue("acctInstNo","");	
			}
			return record;
		}
	 	/**验证邮政编码*/
		var isZip = /^\d{6}$/;
		/**验证座机电话 */
		var isPhone = /^(\d{3,4}-)?\d{6,8}$/;
		/**验证手机电话 */
		var isCellPhone = /[1]+\d{10}$/;
		var isProfitScale = /^(0|([1-9]{1}([0-9]{1,2})?))(\.[\d]{1,2})?$/;
		/**校验字段【基本信息】和【资质信息】 */
		function validInfo(){
			//获取需校验字段【基本信息】
			var contactsPhone = mchtAgent_dataset.getValue("contactsPhone");//联系电话
			var zipNo = mchtAgent_dataset.getValue("zipNo");//邮编
			var profitShareScale = mchtAgent_dataset.getValue("profitShareScale");//分润比例			
			if(!(isPhone.test(contactsPhone)||(isCellPhone.test(contactsPhone))||contactsPhone==""||contactsPhone==null)){
	 			$.warn("【联系电话】格式错误，格式为【区号】（3或4位数字 + '-'）+ 6到8位数字，【区号】可以不输入，同时也支持11位手机号");
				return false;	 			
	 		}
			if(!((isZip.test(zipNo))||zipNo==""||zipNo==null)){
				$.warn("邮政编码格式错误(6位数字)");
				return false;				
			}
			if(profitShareScale==null||profitShareScale==""){
				$.warn("代理商分润比例或代理商成本费率不能为空");
				return false;
			}
			if(profitShareScale>1000){
				$.warn("分润比例不能大于1，请输入小于1000的数字");
				return false;
			}else if(!isProfitScale.test(profitShareScale) ){
				$.warn("分润比例格式有误(1000以内数字,可以包含两位小数)");
				return false;
			}
			return true;
		}
</script>
</snow:page>


