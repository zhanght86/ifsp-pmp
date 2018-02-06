<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="接入方式管理">
	<!-- 接入方式的dataset -->
	<snow:dataset id="accessTypeInfo"
		path="com.ruimin.ifs.pmp.sysConf.dataset.accessTypeInfo"
		init="true" submitMode="current"></snow:dataset>
	<!-- 交易类型的dataset -->
	<snow:dataset id="txnTypeInfo"
		path="com.ruimin.ifs.pmp.sysConf.dataset.txnTypeInfo" init="false"
		parameters="apprFlag=1"></snow:dataset>
	<!-- 接入方式与交易类型关联的dataset -->
	<snow:dataset id="relAccessTypeTxnType"
		path="com.ruimin.ifs.pmp.sysConf.dataset.relAccessTypeTxnType"
		init="false" parameters="apprFlag=1"></snow:dataset>

	<!-- 查询条件 -->
	<snow:query id="queryId" label="查询条件" dataset="accessTypeInfo"
		collapsible="false"
		fieldstr="qaccessTypeCode,qaccessTypeName,qdataState"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="accessTypeInfo" height="99%" label="机构信息"
		id="gridId" fitcolumns="true"
		fieldstr="accessTypeCode,accessTypeName,accessTypeDesc,dataState,opr"
		paginationbar="btnAdd,btnMod,btnUpdate"></snow:grid>

	<!-- 新增页面 -->
	<snow:window id="windowAddId" closable="true" width="800" title="接入方式新增" modal="true" buttons="btnSave" height="550">
            <snow:form id="addId" dataset="accessTypeInfo" border="false" 
            fieldstr="accessTypeName,accessTypeDesc"></snow:form>
            <br/>
            <snow:grid  id="gridAddId" dataset="txnTypeInfo"  fitcolumns="true" fieldstr="select,txnTypeCode[337],txnTypeName[337]" height="300" ></snow:grid>
            <snow:button id="btnSave" dataset="accessTypeInfo" hidden="true"></snow:button>
    </snow:window>

	<!-- 修改页面 -->
	<snow:window id="windowModId" closable="true" width="800" height="550"
		title="接入方式信息修改" modal="true" buttons="btnSave1">
		<snow:form id="formModId" dataset="accessTypeInfo" border="false"
			collapsible="false" colnum="4" label="接入方式基本信息"
			fieldstr="accessTypeCode,accessTypeName,accessTypeDesc"></snow:form>
		<br />
		<snow:grid id="gridModId" dataset="relAccessTypeTxnType" border="true"
			fieldstr="txnTypeCode[680]" label="交易类型信息" editable="true"
			fitcolumns="true" pagination="false" toolbar="toolbarExt02"
			collapsible="true" height="340"></snow:grid>
		<snow:toolbar id="toolbarExt02">
			<snow:button id="btnAddSection01" dataset="relAccessTypeTxnType"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="relAccessTypeTxnType"
				hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btnSave1" dataset="accessTypeInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 详情页面 -->
	<snow:window id="windowIdDetail" title="接入方式详情" modal="true"
		closable="true" width="790" >
		<snow:form id="formDetailId" dataset="accessTypeInfo" label="*"
			border="false"
			fieldstr="accessTypeCode,accessTypeName,accessTypeDesc"
			collapsible="false" colnum="4" width="99%"></snow:form>
		<br />
		<snow:grid id="gridDetailId" dataset="txnTypeInfo" height="250"
			fieldstr="txnTypeCode[350],txnTypeName[350]"></snow:grid>
	</snow:window>

	<div style="display: none;">
		<snow:button id="btnShowDetail" dataset="accessTypeInfo" hidden="true"></snow:button>
		<snow:button id="btnUpdateSub" dataset="accessTypeInfo" hidden="true"></snow:button>
	</div>

	<script>    
//点击添加按钮时
function btnAdd_onClick(){
			accessTypeInfo_dataset.setReadOnly(false);
			txnTypeInfo_dataset.setParameter("qtxnTypeCode","");
			txnTypeInfo_dataset.flushData(txnTypeInfo_dataset.pageIndex);
			window_windowAddId.open();
		}
		
		
function btnSeleceted_postSubmit(btn, param){
	returnPara = param.roleList;
}


function gridAddId_select_onRefresh(record, fieldId, fieldValue){
		if(record){
			var txnTypeCode = record.getValue("txnTypeCode");
			var select = record.getValue("select");
			var returnSelect = returnPara.split(",");
			for (var i = 0; i < returnSelect.length; i++) {
			if(returnSelect[i] == txnTypeCode){
				return "<>";
			}
		}
		return ""; 
		} 
	}

		
		
//点击保存按钮时，进行校验	
function btnSave_onClickCheck(){
			var accessTypeName=accessTypeInfo_dataset.getValue("accessTypeName");
			var accessTypeDesc=accessTypeInfo_dataset.getValue("accessTypeDesc");
			if(accessTypeName.length>21){
				$.warn("接入方式名称过长，最大不超过21位！");
				return false;
			}
			if(accessTypeDesc.length>42){
				$.warn("接入方式描述过长，最大不超过42位！");
				return false;
			}
			var hasRoleSelected = 0;
			var roleRecord = txnTypeInfo_dataset.getFirstRecord();
			var roleIdList = [];
			var accessTypeCode = accessTypeInfo_dataset.getValue("accessTypeCode");
			
			while(roleRecord){
				var v_selected = roleRecord.getValue("select");
				if( v_selected == true ){
					roleIdList.push(roleRecord.getValue("txnTypeCode"));
					hasRoleSelected ++ ;
				}
				roleRecord=roleRecord.getNextRecord();
		   	}
		   	if (hasRoleSelected<1) {
		   		$.warn("请至少选择一种交易类型");
		   		return false;
		   	}
		   	
			accessTypeInfo_dataset.setParameter("accessTypeCode", accessTypeCode);
			accessTypeInfo_dataset.setParameter("s", roleIdList.join(","));
			return true;
		}
		
		
//点击保存按钮提交时		
function btnSave_postSubmit(){
	$.success("操作成功!", function() {
		window_windowAddId.close();
		accessTypeInfo_dataset.flushData(accessTypeInfo_dataset.pageIndex);
    });
}


//当新增接入方式框体关闭时，取消当前记录
function window_windowAddId_beforeClose(wmf){
	accessTypeInfo_dataset.cancelRecord();
}
		
	//修改按钮点击时
	function btnMod_onClick(){
	       accessTypeInfo_dataset.setFieldReadOnly("accessTypeCode",true);
	       accessTypeInfo_dataset.setFieldReadOnly("accessTypeName",false);
		  accessTypeInfo_dataset.setFieldReadOnly("accessTypeDesc",false);
		  relAccessTypeTxnType_dataset.setFieldReadOnly("relNo",true);
		  var accessTypeCode = accessTypeInfo_dataset.getValue("accessTypeCode");
		  relAccessTypeTxnType_dataset.setParameter("accessTypeCode", accessTypeCode);
		  relAccessTypeTxnType_dataset.flushData(relAccessTypeTxnType_dataset.pageIndex);
		  
		  window_windowModId.open();
	}
		
		//修改窗口关闭时
		function window_windowModId_beforeClose(wmf) {
			accessTypeInfo_dataset.flushData(accessTypeInfo_dataset.pageIndex);
		}
		
		
		//修改保存
		function btnSave1_onClickCheck(){
		    var accessTypeName=accessTypeInfo_dataset.getValue("accessTypeName");
			var accessTypeDesc=accessTypeInfo_dataset.getValue("accessTypeDesc");
			if(accessTypeName.length>21){
				$.warn("接入方式名称过长，最大不超过21位！");
				return false;
			}
			if(accessTypeDesc.length>42){
				$.warn("接入方式描述过长，最大不超过42位！");
				return false;
			} 
			var firstRecord = relAccessTypeTxnType_dataset.getFirstRecord();
		   	if (firstRecord==null|| firstRecord == '') {
		   		$.warn("请至少添加一种交易类型");
		   		return false;
		   	}
		   	while (firstRecord) {
				var txnTypeCode = firstRecord.getValue("txnTypeCode");
					if (txnTypeCode =='') {
						$.warn("交易名称不能为空");
						return false;
				}
				firstRecord = firstRecord.getNextRecord();
			}
			return true;
		}
		
		//修改保存成功 
		function btnSave1_postSubmit(){
			$.success("操作成功!", function() {
				window_windowModId.close();
				accessTypeInfo_dataset.flushData(accessTypeInfo_dataset.pageIndex);
	        });
		}
		
		
		function btnUpdate_onClick(){
				var dataState = accessTypeInfo_dataset.getValue("dataState");
				var msg = "";
				if(dataState == "00"){
					msg = "是否【停用】该接入方式?";
					dataState = "99";
				}else{
					msg = "是否【启用】该接入方式?";
					dataState = "00";
				}
				$.confirm(msg, function() {
					accessTypeInfo_dataset.setParameter("dataState",dataState);
					btnUpdateSub.click();
		        }, function() {
		        	return false;
		        });
		}
		
		
		var btnUpdateSub_postSubmit= function() {
			$.success("操作成功!", function() {
				accessTypeInfo_dataset.flushData(accessTypeInfo_dataset.pageIndex);
			});
		};
		
		
		function btnShowDetail_onClick(){
			accessTypeInfo_dataset.setReadOnly(true);
			var accessTypeCode = accessTypeInfo_dataset.getValue("accessTypeCode");
			txnTypeInfo_dataset.setParameter("accessTypeCode", accessTypeCode);
			txnTypeInfo_dataset.setParameter("param", "detail");
			txnTypeInfo_dataset.flushData(txnTypeInfo_dataset.pageIndex);
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
		function window_windowIdDetail_beforeClose(){
			txnTypeInfo_dataset.setReadOnly(false);
			txnTypeInfo_dataset.setParameter("param", "");
			txnTypeInfo_dataset.flushData(txnTypeInfo_dataset.pageIndex);
		}
	</script>
</snow:page>