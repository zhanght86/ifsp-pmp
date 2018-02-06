<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo"%>
<%@page import="com.ruimin.ifs.framework.utils.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.CommonFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="操作员管理">
	<%
	    String contextPath = request.getContextPath();
		SessionUserInfo si = SessionUserInfo.getSessionUserInfo();
			String tlrno = si.getTlrno();
			String defPwd = SysParamUtil.getInstance().getString("PSWD.DEFAULT_PWD", "111111");
			String ramdPwd = CommonFunction.getRandomCharAndNumr(8);
	%>
	<!-- 操作员管理 -->
	<snow:dataset id="OperMngEntry" path="com.ruimin.ifs.mng.dataset.OperMngEntry" 
	init="true" submitMode="current" ></snow:dataset>
	
	<!-- 机构树 -->
	<snow:dataset id="orgtree" path="com.ruimin.ifs.mng.dataset.OrgTree" 
	init="true" parameters="showvir=true"></snow:dataset>
	
	<!-- 角色管理 -->
	<snow:dataset id="RoleMngEntry" path="com.ruimin.ifs.mng.dataset.RoleMngEntry" 
	init="false" parameters="apprFlag=1"></snow:dataset>
	
	<!-- 机构管理 -->
	<snow:dataset id="orgManger" path="com.ruimin.ifs.mng.dataset.OrgManager" 
	init="false" submitMode="current"></snow:dataset>
			
	<!-- 查询条件 -->
	<snow:query id="querybtn" label="查询条件" dataset="OperMngEntry" collapsible="false" 
	fieldstr="qtlrno,qtlrname,qbrcode,qflag,qisLock"  border="false" ></snow:query>
	
	<!-- 操作员信息显示列表 -->
	<snow:grid dataset="OperMngEntry" label="机构用户" height="99%" width="100%"  id="gridId" 
	fieldstr="tlrno[100],tlrName,flag[100],brcode,isLock[100],lastaccesstm,lastlogouttm,opr[100]" 
	paginationbar="btnMod,btnDelete,unLock,btnStatus"></snow:grid>
				
	<snow:window id="windowId" title="操作员信息" modal="true" closable="true" width="770" buttons="btnSave">
		<snow:form id="formModifyId" dataset="OperMngEntry" label="*" border="false" 
		fieldstr="tlrno,tlrName,workNo,phone,mobile,email,brcode" collapsible="false" colnum="4"></snow:form>
		<br>
		<snow:grid dataset="RoleMngEntry" id="gridRoleId" height="300" 
		fieldstr="select,roleId[300],roleName[300]"></snow:grid>
		<snow:button id="btnSave" dataset="OperMngEntry" hidden="true"></snow:button>
	</snow:window>
			
	<snow:window id="windowIdDetail" title="操作员信息详情" modal="true" closable="true" width="780">
		<snow:form id="formDetailId" dataset="OperMngEntry" label="*" border="false" fieldstr="tlrno,tlrName,workNo,phone,mobile,email,brcode" collapsible="false" colnum="4"></snow:form>
		<br>
		<snow:grid dataset="RoleMngEntry" id="gridRoleDetailId" height="300" 
		fieldstr="roleId[300],roleName[400]"></snow:grid>		
	</snow:window>
			
	<div style="display: none;">
		<snow:button id="btnStatusSub" dataset="OperMngEntry"></snow:button>
		<snow:button id="unLockSub" dataset="OperMngEntry"></snow:button>
		<snow:button id="btnDeleteSub" dataset="OperMngEntry"></snow:button>
		<snow:button id="btnShowDetail" dataset="OperMngEntry"></snow:button>
		<snow:button id="btnResetPwdSub" dataset="OperMngEntry"></snow:button>
		</div>
	<script type="text/javascript" src="<%=contextPath%>/pages/login/js/md5.js"></script>
	<script>
	    //对座机号、手机号和邮箱进行校验
        var isPhone=/^((0\d{2,3})-)(\d{7,8})$/;
        var isMobile=/^1[3|4|5|7|8]\d{9}$/;
        var isEmail= /^[A-Za-z0-9\u4e00-\u9fa5\.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        
        // 检查是否是用户本身
        function checkOwn(){
			var foo = OperMngEntry_dataset.getValue("tlrno");
			if(foo=="<%=tlrno%>") {
				$.error("您不可以对自己进行操作!" );
				return false;
			}
			return true;
		}
        
        // 按钮提交时事件
        function btnSeleceted_postSubmit(btn, param){
			returnPara = param.roleList;
		}
        
        var btnStatusSub_postSubmit = unLockSub_postSubmit = btnDeleteSub_postSubmit = btnResetPwdSub_postSubmit = btLoginStatusSub_postSubmit = function() {
			$.success("操作成功!", function() {
				OperMngEntry_dataset.flushData(OperMngEntry_dataset.pageIndex);
			});
		};
		var btnStatusSub_needCheck = unLockSub_needCheck = btnDeleteSub_needCheck = btnResetPwdSub_needCheck = btLoginStatusSub_needCheck = btnSeleceted_needCheck = function() {
			return false;
		};
	
        // 操作员机构选择时事件
		function OperMngEntry_dataset_brcode_onSelect(dropdown, record, editor){
			if(record!=null){
		        RoleMngEntry_dataset.setParameter("brcode",record.getValue('_id'));
			    RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
			    OperMngEntry_dataset.setValue('brcode',record.getValue('_id'));
			    OperMngEntry_dataset.setValue('brcodeName',record.getValue('_text'));
			}else{
				RoleMngEntry_dataset.setParameter("brcode","");
				RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
				OperMngEntry_dataset.setValue('brcode',"");
			    OperMngEntry_dataset.setValue('brcodeName',"");
			}
			return true;
		}
			
		function orgtree_dataset_afterScroll(ds,record){
			if(record) {
				OperMngEntry_interface_dataset.setParameter("upbrcode",record.getValue("_id"));
				OperMngEntry_interface_dataset_querybtn.click();
			}
		}
		
		
		/************************************* 1.-->新增和修改 **********************************/
		
		// 新增时设置数据集属性
		function btnAdd_onClick(){
			OperMngEntry_dataset.setReadOnly(false);
			OperMngEntry_dataset.setFieldReadOnly("tlrno",false);
			OperMngEntry_dataset.setFieldReadOnly("workNo",true);
			OperMngEntry_dataset.setParameter("paramStat", "add");
			var pas = hex_md5("<%=defPwd%>");
			OperMngEntry_dataset.setParameter("password", pas);
			var foo = OperMngEntry_dataset.getValue("tlrno");
			RoleMngEntry_dataset.setParameter("tlrno", foo);
			RoleMngEntry_dataset.setParameter("param", "add");
			RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
			window_windowId.open();
		}
		//数据集字段发生变化时，实现工号的反显
		function OperMngEntry_dataset_afterChange(dataset,field){
			if(field.fieldName=="tlrno"){
				var workno = OperMngEntry_dataset.getValue("tlrno");
				OperMngEntry_dataset.setValue("workNo",workno);
			}	
		}
		
		// 修改时设置数据集属性
		function btnMod_onClick(){
			if(checkOwn()){
				OperMngEntry_dataset.setFieldReadOnly("tlrno",true);
				OperMngEntry_dataset.setFieldReadOnly("tlrName",false);
				OperMngEntry_dataset.setFieldReadOnly("workNo",true);
				OperMngEntry_dataset.setFieldReadOnly("phone",false);
				OperMngEntry_dataset.setFieldReadOnly("mobile",false);
				OperMngEntry_dataset.setFieldReadOnly("email",false);
				OperMngEntry_dataset.setFieldReadOnly("brcode",false);
				OperMngEntry_dataset.setParameter("paramStat", "mod");
				var foo = OperMngEntry_dataset.getValue("tlrno");
				OperMngEntry_dataset.setParameter("tlrno", foo);
				RoleMngEntry_dataset.setParameter("tlrno", foo);
				RoleMngEntry_dataset.setParameter("param", "mod");
				RoleMngEntry_dataset.flushData(RoleMngEntry_dataset.pageIndex);
				window_windowId.open();
			}
		}
		// 角色列表
		function gridRoleId_select_onRefresh(record, fieldId, fieldValue){
 			if(record){
 				var roleId = record.getValue("roleId");
 				var select = record.getValue("select");
 				var returnSelect = returnPara.split(",");
 				for (var i = 0; i < returnSelect.length; i++) {
					if(returnSelect[i] == roleId){
						return "<>";
					}
				}
				return ""; 
 			} 
 		}
		
		
		function window_windowId_beforeClose(wmf){
			OperMngEntry_dataset.setParameter("paramStat", "");
			OperMngEntry_dataset.cancelRecord();
		}
		
		// 保存时字段格式的合法性检查
		function btnSave_onClickCheck(){
			var hasRoleSelected = 0;
			var roleRecord = RoleMngEntry_dataset.getFirstRecord();
			var roleIdList = [];
			var tlrno = OperMngEntry_dataset.getValue("tlrno");
			var brcode = OperMngEntry_dataset.getValue("brcode");
			orgManger_dataset.setParameter("brcode",brcode);
			var phone=OperMngEntry_dataset.getValue("phone");
			var mobile=OperMngEntry_dataset.getValue("mobile");
			var email=OperMngEntry_dataset.getValue("email");
			var workNo=OperMngEntry_dataset.getValue("workNo");
			var tlrName=OperMngEntry_dataset.getValue("tlrName");
			if(tlrno.length>20){
				$.warn("操作员编号长度有误(最大长度为20位)");
				return false;
			}
			if(tlrName.length>10){
				$.warn("操作员名称长度有误(最大长度为10位)");
				return false;
			}
			if(phone!=""&&phone.length>13){
				$.warn("座机号码格式有误(最大长度为13位)");
				return false;
			}
			if(phone!=""&&!isPhone.test(phone)){
				$.warn("座机号码格式有误(正确格式为：0xx-xxxxxxxx或0xxx-xxxxxxx)");
				return false;
			}
			if(mobile!=""&&!isMobile.test(mobile)){
				$.warn("手机号码格式有误！");
				return false;
			}
			if(email!=""&&!isEmail.test(email)||email.length>40){
				$.warn("邮箱格式有误！(最大长度为40位)");
				return false;
			}
			while(roleRecord){
				var v_selected = roleRecord.getValue("select");
				if( v_selected == true ){
					roleIdList.push(roleRecord.getValue("roleId"));
					hasRoleSelected ++ ;
				}
				roleRecord=roleRecord.getNextRecord();
		   	}
		   	if (hasRoleSelected>1) {
		   		$.warn("警告:最多只能选取一个角色!");
		   		return false;
		   	}
		   	if (hasRoleSelected<1) {
		   		$.warn("警告:至少选取一个角色!");
		   		return false;
		   	}
			
			OperMngEntry_dataset.setParameter("tlrno", tlrno);
			OperMngEntry_dataset.setParameter("s", roleIdList.join(","));
			return true;
		}
		
		function btnSave_postSubmit(){
			$.success("操作成功!", function() {
				window_windowId.close();
				OperMngEntry_dataset.flushData(OperMngEntry_dataset.pageIndex);
	        });
			
		}

		
		
		/****************************************** 2.-->删除************************************/
		function btnDelete_onClick(){		
			if(checkOwn()){
				var foo = OperMngEntry_dataset.getValue("tlrno");
				$.confirm("是否确认删除该用户?注:一经删除无法恢复!!!", function() {
					OperMngEntry_dataset.setParameter("foo",foo);
					btnDeleteSub.click();
		        }, function() {
		        	return false;
		        });
			}
		}	
		
		/****************************************** 3.-->解锁 ************************************/
		function unLock_onClick(){
			if(checkOwn()){
				var foo = OperMngEntry_dataset.getValue("isLock");
				var msg = "";
				if(foo == "0"){
					$.warn("该用户处于未锁定状态,无需再次解锁!");
		 			return false;
				}else{
					msg = "是否要解锁该用户?";
				}
				$.confirm(msg, function() {
					OperMngEntry_dataset.setParameter("foo","0");
					unLockSub.click();
		        }, function() {
		        	return false;
		        });
			}
		}
		
		
		/****************************************** 4.-->启用停用 ************************************/
		function btnStatus_onClick(){
			if(checkOwn()){
				var foo = OperMngEntry_dataset.getValue("flag");
				var msg = "";
				if(foo == "00"){
					msg = "是否要将该操作员状态修改为【停用】?";
					foo = "99";
				}else{
					msg = "是否要将该操作员状态修改为【启用】?";
					foo = "00";
				}
				$.confirm(msg, function() {
					OperMngEntry_dataset.setParameter("foo",foo);
					btnStatusSub.click();
		        }, function() {
		        	return false;
		        });
			}
		}
		
		
		/****************************************** 5.-->重置密码 ************************************/

//		function btnResetPwd_onClick(){
//			if(checkOwn()){
//				var foo = OperMngEntry_dataset.getValue("tlrno");
//				$.confirm("是否确认重置该用户密码?", function() {
//					dwr.engine.setAsync(false);
//					var password = "<%=ramdPwd%>";
//					var pwd = hex_md5(password);
//					var parm = foo+"_"+pwd;
//			        var newPwd = PwdDwr.reset(parm);
//			        dwr.engine.setAsync(true);
//			        $.alert("<br/><div style='text-align:center;'>请妥善保存新密码，以免丢失!<div/><br/><div style='text-align:center; color:red; font-size:18px;'>" + password + "</div><br/>");
//			        

		
		
		/****************************************** 6.-->详情 ************************************/
		function btnShowDetail_onClick(){
			OperMngEntry_dataset.setReadOnly(true);
			var foo = OperMngEntry_dataset.getValue("tlrno");
			RoleMngEntry_dataset.setParameter("tlrno", foo);
			RoleMngEntry_dataset.setParameter("param", "detail");
			RoleMngEntry_dataset.flushData(1);
			window_windowIdDetail.open();
		}		
		function gridId_opr_onRefresh(record, fieldId, fieldValue){
			if(record){
				return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
			}else{
				return "&nbsp;";
			}
		}
		function openDetail(){
			btnShowDetail.click();
		}
		
		
		/****************************************** 强制签退（功能保留）************************************/
		function btLoginStatus_onClick(){
			if(checkOwn()){
				var foo = OperMngEntry_dataset.getValue("tlrno");
				$.confirm("是否确认强制签退该用户?", function() {
					OperMngEntry_dataset.setParameter("foo","0");
					btLoginStatusSub.click();
		        }, function() {
		        	return false;
		        });
			}
		}
	</script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/PwdDwr.js"></script>
</snow:page>
