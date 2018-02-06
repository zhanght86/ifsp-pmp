<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="用户黑名单">
	<script src="../common/upload/js/ajaxfileupload.js"></script>
	<!-------------- 用户黑名单数据集 ---------------->
	<snow:dataset id="UserBlackList" path="com.ruimin.ifs.pmp.risk.dataset.UserBlackList" submitMode="current" init="true"></snow:dataset>
	
	<snow:query label="查询条件" id="userBlackListQuery" collapsible="false" border="false" dataset="UserBlackList" fieldstr="qBlacklistStatus,qBlacklistValue" ></snow:query>
	<snow:grid dataset="UserBlackList" height="99%" label="用户黑名单信息" fitcolumns="true" id="userBlackListGrid" fieldstr="blacklistId,blacklistType[140],blacklistValue[240],blacklistStatus[140],startDate,endDate" 
		paginationbar="btnAdd,btnMod,btnStatus,btnDel,btnImport"  ></snow:grid>
		
	
	<!-------------- 新增窗口 ---------------->
	<snow:window id="windowAddUserBlackList" title="黑名单信息新增" width="800" modal="true" closable="true" buttons="btnAddSave">
		<snow:form id="userBlackListAddForm" dataset="UserBlackList" border="false" label="" 
 				fieldstr="blacklistId,blacklistType,blacklistValue,startDate,endDate" >
		</snow:form> 
		<snow:button id="btnAddSave" dataset="UserBlackList" hidden="true"></snow:button>
	</snow:window> 
	
	
	<!-------------- 修改窗口 ---------------->
 	<snow:window id="windowModifyUserBlackList" title="黑名单信息修改" width="810" modal="true" closable="true" buttons="btnModSave"> 
		<snow:form id="userBlackListModifyForm" dataset="UserBlackList" border="false" label="" 
 				fieldstr="blacklistId,blacklistType,blacklistValue,startDate,endDate" > 
		</snow:form>
		<snow:button id="btnModSave" dataset="UserBlackList" hidden="true"></snow:button>
	</snow:window>
	
	
	<div style="display: none;">
		<!-- 确认删除按钮 -->
		<snow:button id="btnDelSave" dataset="UserBlackList"></snow:button>
		<!-- 确认启用/停用按钮 -->
		<snow:button id="btnStatusSave" dataset="UserBlackList"></snow:button>
	</div>
	
 	<script> 
		//16位纯数字
 		var isNumCode=/^\d{15,19}$/;
 		var isNumBin=/^\d{2,10}$/;
 		var isNumAndDot = /^[0-9]{0}([0-9]|[.])+$/;
 	
	        function userBlackListGrid_endDate_onRefresh(record, fieldId, fieldValue){
	        	 var endDate = record.getValue("endDate");
	     	       if(endDate == null || endDate ==""){
	        			return "——";
	        		}else{
	        			return fieldValue.substr(0,4)+"-"+fieldValue.substr(4,2)+"-"+fieldValue.substr(6);
	        		}
	        }
	  	
		//------------------------新增------------------------------------
		//新增按钮点击时，打开新增窗口
		function btnAdd_onClick(){ 
			UserBlackList_dataset.setFieldReadOnly("blacklistId", true);
			//UserBlackList_dataset.setFieldReadOnly("blacklistType", false);
			//---------新增固定死为01 卡号黑名单----------
			UserBlackList_dataset.setFieldReadOnly("blacklistType", true);
			UserBlackList_dataset.setValue("blacklistType", "01");
 			window_windowAddUserBlackList.open();
 		} 
		 //新增提交按钮点击时，校验提交内容
 		function btnAddSave_onClickCheck(){ 
 			var result = checkInput();
	     	if(!result){
	     		return;
	     	}  
	     return true;
		} 
 		 //当新增或修改提交时，校验输入信息
	    function checkInput(){
	     	if(UserBlackList_dataset.validate() == false){
	     		return false;
	     	}
	     	var blacklistType = UserBlackList_dataset.getValue("blacklistType");
	     	//去除所有的空格
	     	var blacklistValue = UserBlackList_dataset.getValue("blacklistValue").replace(/\s+/g, "");
	     	if(blacklistValue == null || blacklistValue == ""){
	     		$.warn("卡号不能为空");
	     		return false;
	     	}
	     	if(blacklistType == '01'){
	     		if(!isNumCode.test(blacklistValue)){
	     			$.warn("卡号只能是15到19位纯数字");
	     			return false;
	     		}
	     	}
// 	     	if(blacklistType == '02'){
// 	     		if(!isNumBin.test(blacklistValue)){
// 	     			$.warn("卡BIN只能是2到10位纯数字");
// 	     			return false;
// 	     		}
// 	     	}
// 	     	if(blacklistType == '03'){
// 	     		if(!isNumAndDot.test(blacklistValue)){
// 	     			$.warn("IP必须是数字加小数点的格式");
// 	     			return false;
// 	     		}
// 	     	}
	     	var startDate = UserBlackList_dataset.getValue("startDate");
	     	var endDate = UserBlackList_dataset.getValue("endDate");
	     	if(startDate != "" && endDate != ""){
	     		if(startDate > endDate){
	     			$.warn("开始日期不能早于结束日期");
	    			return false;
	     		}
	     		if(Date.parse(endDate)<Date.parse(new Date())){
	     			$.warn("结束日期必须大于当天");
	    			return false;
	     		}
	     	}
	    	return true;
	    }
 		
		
 		//当添加黑名单信息框体关闭时，取消记录
 		function window_windowAddUserBlackList_beforeClose(wmf){ 
 			UserBlackList_dataset.cancelRecord(); 
 		} 
 		//数据提交成功后进入
 		function btnAddSave_postSubmit() {
 			$.success("新增成功!", function() { 
 				window_windowAddUserBlackList.close(); 
 				UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex); 
 			}); 
 		} 
	    
	   
		
		
		//--------------------------------修改 ------------------------------
		//点击修改按钮，打开修改窗口
 		function btnMod_onClick() { 
			var blacklistId = UserBlackList_dataset.getValue("blacklistId");
			if(blacklistId == ''){
				$.warn("请先选择要修改的黑名单记录");
				return false;
			}
 			UserBlackList_dataset.setFieldReadOnly("blacklistId", true);
 			UserBlackList_dataset.setFieldReadOnly("blacklistType", true);
 			window_windowModifyUserBlackList.open(); 
 		} 
		
 		//修改提交按钮点击时，校验提交内容
 		function btnModSave_onClickCheck(){ 
 			
 			var result = checkInput();
	     	if(!result){
	     		return;
	     	}  
	     return true;
		} 
		//修改成功之后
 		function btnModSave_postSubmit() { 
 			$.success("修改成功!", function() { 
 				window_windowModifyUserBlackList.close(); 
 				UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex); 
 			}); 
 		} 
 		//当修改黑名单信息框体关闭时，取消记录
 		function window_windowModifyUserBlackList_beforeClose(wmf){ 
 			UserBlackList_dataset.cancelRecord(); 
		} 
		
 		//-------------------------------删除---------------------------------

		function btnDel_onClick(){ 
			var blacklistId = UserBlackList_dataset.getValue("blacklistId");
			if(blacklistId == ''){
				$.warn("请先选择要删除的黑名单记录");
				return false;
			}
 			$.confirm("是否确认删除", function() { 
 				UserBlackList_dataset.setParameter("blacklistId",blacklistId);
 				btnDelSave.click(); 
 			},function(){
 				return false;
 			}); 
 		} 
 		//删除成功后
	    function btnDelSave_postSubmit(btn){
	    	$.success("操作成功!", function() {
	    		UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex);
			});
	    }
 		
 		//-------------------------启用/停用--------------------------------------
	   function btnStatus_onClick(){
		   var blacklistId = UserBlackList_dataset.getValue("blacklistId");
			if(blacklistId == ''){
				$.warn("请先选择要修改状态的黑名单记录");
				return false;
			}
			 var blacklistStatus = UserBlackList_dataset.getValue("blacklistStatus");
			 var str = "是否要停用当前选中的黑名单？";
			 if(blacklistStatus == '99'){//00使用中，99暂停使用
				 str = "是否要启用当前选中的黑名单？";
			 }
			$.confirm(str, function() { 
				UserBlackList_dataset.setParameter("blacklistId",blacklistId);
				UserBlackList_dataset.setParameter("blacklistStatus",blacklistStatus);
				btnStatusSave.click(); 
			},function(){
				return false;
			}); 
 		}
	 //修改状态成功后
	    function btnStatusSave_postSubmit(btn){
	    	$.success("操作成功!", function() {
	    		UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex);
			});
	    }
	 
	 //-----------------------excel导入----------------------------
	      var iscallback = false;
          function btnImport_onClick(){
        	iscallback = false;
          	$.open("importFile","文件导入","/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_BLACKLIST_LST%>","750", "280", false, true, null, false,"关闭");
          }
          function importFile_onButtonClick(i,win,framewin){
        	  if(i==0){
        		  win.close();
        		  UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex);
        	  }else{
        		  framewin.excuteImport();
        		  importFileCallBack();
        	  }
          }
         function importFileCallBack(){
   		 	iscallback = true;
   		 	UserBlackList_dataset.flushData(UserBlackList_dataset.pageIndex);			
   		}
		
	</script>
</snow:page>