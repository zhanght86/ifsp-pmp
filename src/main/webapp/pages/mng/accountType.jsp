<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="账户类型查询">
	
	<snow:dataset id="accountType" path="com.ruimin.ifs.pmp.sysConf.dataset.accountType" submitMode="current" init="true"></snow:dataset>
	
	<snow:query label="请输入查询条件" id="queryId" dataset="accountType" fieldstr="qAcctTypeNo,qAcctTypeName"></snow:query>
	
	<snow:grid   id="gridId"  exporter="exporterId" dataset="accountType" fieldstr="acctTypeNo,acctTypeName,acctTypeDesc,acctTypeStat,opr" paginationbar="btnAdd" label="账户信息" height="99%" fitcolumns="true"></snow:grid>
	<!--<snow:exporter dataset="accountType" id="exporterId" type="XLS|CSV" ></snow:exporter>-->
	
	<!-- 增加 -->
	<snow:window id="windowAddId" title="账户类型新增 --> 新增" modal="true" closable="true" buttons="btnSave1">
		<snow:form id="formAddId" dataset="accountType" border="false" fieldstr="acctTypeName,acctTypeDesc" collapsible="false" colnum="2"></snow:form>
		<snow:button id="btnSave1" dataset="accountType" hidden="true"></snow:button>
	</snow:window>
	
	<!-- 修改 -->
	<snow:window id="windowModifyId" title="账户类型编辑--> 编辑" modal="true" closable="true" buttons="btnUpdate">
		<snow:form id="formModifyId" dataset="accountType" border="false" fieldstr="acctTypeNo,acctTypeName,acctTypeDesc" collapsible="true" colnum="2"></snow:form>
		<snow:button id="btnUpdate" dataset="accountType" hidden="true"></snow:button>
	</snow:window>
	<!-- 启用/停用-->
	<!-- button  一般都会使用hidden属性 -->
	<snow:button id="btnDelete" dataset="accountType" hidden="true"></snow:button>
	
	
<script type="text/javascript">
/**验证字符串长度*/
var isDesc42 = /^\S{1,42}$/;//最大长度42位 
var isDesc21 = /^\S{1,21}$/;//最大长度21位 
var isDesc10 = /^\S{1,10}$/;//最大长度10位 
var isDesc6 = /^\S{1,6}$/;//最大长度6位
   
	function btnAdd_onClick() { 
		window_windowAddId.open();
		}
	/* 在页面上的按钮提交，控制的窗体上的按钮的事件，窗体一般包括form */
	function btnSave1_onClick(){
		var result = checkInput();
     	if(!result){
          	return false;
     	}
    		return true;
	}
	 function btnSave1_onClickCheck(button,commit){
		 var acctTypeName = accountType_dataset.getValue("acctTypeName");
		 var acctTypeDesc = accountType_dataset.getValue("acctTypeDesc");
	     	if(acctTypeName!=''){
	     		if(!isDesc21.test(acctTypeName)){
	     		$.warn("账户类型名称最大长度只能是21位");
	     		return false;
	     	}
	     }
	     	if(acctTypeDesc!=''){
	     		if(!isDesc42.test(acctTypeDesc)){
		     		$.warn("账户描述的最大长度只能是42位");
		     		return false;
		     	}
	     	}
	     	return true;
		 
	 }
	function btnSave1_postSubmit() {
		$.success("操作成功!", function() {
				window_windowAddId.close();
				accountType_dataset.flushData(accountType_dataset.pageIndex);
			});
		}
	
	function btnUpdate_onClick(){
		var result = checkInput();
     	if(!result){
     		return false;
     	}
    		return true;
	}
	function btnUpdate_postSubmit() {
			$.success("操作成功!", function() {
				/* 保存成功了就立马关掉窗口 */
				window_windowModifyId.close();
				accountType_dataset.flushData(accountType_dataset.pageIndex);
			});
		}
	 function btnUpdate_onClickCheck(button,commit){
		 var acctTypeName = accountType_dataset.getValue("acctTypeName");
		 var acctTypeDesc = accountType_dataset.getValue("acctTypeDesc");
	     	if(acctTypeName!=''){
	     		if(!isDesc21.test(acctTypeName)){
	     		$.warn("账户类型名称最大长度只能是21位");
	     		return false;
	     	}
	     }
	     	if(acctTypeDesc!=''){
	     		if(!isDesc42.test(acctTypeDesc)){
		     		$.warn("账户描述的最大长度只能是42位");
		     		return false;
		     	}
	     	}
	     	return true;
		 
	 }	
	/* XXX_[字段id]_onRefresh(record,fieldname) 单元格刷新事件，返回html片断  */	
	function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				var acctTypeNo = record.getValue("acctTypeNo");
				return "<i class='fa fa-pencil'></i>&nbsp;<a href=\"JavaScript:onClickModify()\">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-edit'></i>&nbsp;<a href=\"JavaScript:onClickDelete('" + acctTypeNo + "')\">启用/停用</a>";
			}
		}
		/* 点击修改和删除触发的超链接调用的方法 */
	function onClickModify() {
			window_windowModifyId.open();
		}
	 
	/* 消息框提示  $.confirm(message[,okCallback,cancelCallback])	确认框 */
	function onClickDelete(id){
    	var state=accountType_dataset.getValue("acctTypeStat");
    	if('00'==state){
    		$.confirm("确定要停用该账户吗?", function() {
    			btnDelete.click();
    		}, function() {
    			return false;
    		});	
    	}
    	if('99'==state){
    		$.confirm("确定要启用该账户吗?", function() {
    			btnDelete.click();
    		}, function() {
    			return false;
    		});	
    	}
	}
		
		/* 当用户单击按钮时触发,主要用于决定是否要执行操作前检查 */
	//function btnDelete_needCheck(btn) {
		//	return false;}

	function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				//定义到当前页
				accountType_dataset.flushData(accountType_dataset.pageIndex);
			});
		}
	function window_windowModifyId_beforeClose(wmf) {
			accountType_dataset.cancelRecord();
		}
	
	function window_windowAddId_beforeClose(wmf) {
		accountType_dataset.cancelRecord();
		}
	
	
	</script>
</snow:page>