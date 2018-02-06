<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="二维码路由管理">
	<!-- 二维码路由信息数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.chnlMng.dataset.qrsRoutMng" id="qrsRoutMng" init="true" submitMode="current"></snow:dataset>
	<!-- 查询条件 -->
	<snow:query dataset="qrsRoutMng" label="查询条件" collapsible="false"
		fieldstr="qrouteId,qqmchtId"></snow:query>
	<!-- 显示表格 -->
	<snow:grid dataset="qrsRoutMng" height="99%" label="二维码路由信息" id="gridId"
		fitcolumns="true"
		fieldstr="routeId,qmchtId,qprodId,qacctType,qpagyNo,priority,status,routeDesc"
		paginationbar="btnAdd,btnMod,btnDelete,btnRef"></snow:grid>
	<!-- 新增窗口 -->	
	<snow:window id="winAdd" title="二维码路由新增" resizable="true" modal="true" closable="true" width="770" buttons="btnAddSub">
		<snow:form id="qrsRoutMngAdd" label="基本信息" dataset="qrsRoutMng" fieldstr="mchtId,prodId,acctType,pagyNo,priority,status,routeDesc"></snow:form>
		<snow:button id="btnAddSub" dataset="qrsRoutMng" hidden="true"></snow:button>
	</snow:window>
	<!-- 修改窗口 -->	
	<snow:window id="winUpd" title="二维码路由修改" resizable="true" modal="true" closable="true" width="770" buttons="btnUpdSub">
		<snow:form id="qrsRoutMngUdp" label="基本信息" dataset="qrsRoutMng" fieldstr="routeId,mchtId,prodId,acctType,pagyNo,priority,status,routeDesc"></snow:form>
		<snow:button id="btnUpdSub" dataset="qrsRoutMng" hidden="true"></snow:button>
	</snow:window>
	<snow:button id="btnDeleteSub" dataset="qrsRoutMng" hidden="true"></snow:button>
	<snow:button id="btnRefSub" dataset="qrsRoutMng" hidden="true"></snow:button>
	
	<script type="text/javascript">
	/******************************************Check****************************************/
	function checkInput(){
		//商户编号
		var mchtId = qrsRoutMng_dataset.getValue("mchtId");
		//产品编号
		var prodId = qrsRoutMng_dataset.getValue("prodId");
		//账户类型
		var acctType = qrsRoutMng_dataset.getValue("acctType");
		if(mchtId!=null&&mchtId!=""){
			if(mchtId.indexOf("*")>='0'){
				return true;
			}else if(mchtId.length>'128'){
				$.warn("添加商户不能超过8个!!");
				return false;
			}
			return true;
		}
		if(prodId!=null&&prodId!=""){
			if(prodId.indexOf("*")>='0'){
				return true;
			}else if(prodId.length>'128'){
				$.warn("添加产品不能超过18个!!");
				return false;
			}
			return true;
		}
		if(acctType!=null&&acctType!=""){
			if(acctType.indexOf("*")>='0'){
				return true;
			}else if(acctType.length>'128'){
				$.warn("添加账户类型不能超过25个!!");
				return false;
			}
			return true;
		}
		
	}
	
	
	/****************************************** 1.-->新增************************************/
	function btnAdd_onClick(){
		qrsRoutMng_dataset.setFieldRequired("routeId",false);
		window_winAdd.open();
		
	}
	
	function window_winAdd_afterClose(){
		qrsRoutMng_dataset.cancelRecord();	
	}

	 function btnAddSub_onClickCheck(button,commit){	
		if(!checkInput()){
			return false;
		}else{
			return true;
		}
	 }
	
	/**提交*/
	function btnAddSub_postSubmit(){
		$.success("添加成功！",function(){
			window_winAdd.close();
			qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex);	
		});
	}
	/****************************************** 2.-->修改************************************/
	/**打开修改窗口*/
	function btnMod_onClick(){
		window_winUpd.open();
		qrsRoutMng_dataset.setFieldReadOnly("routeId",true);
	}
	
	function window_winUpd_afterClose(){
		qrsRoutMng_dataset.cancelRecord();
	}
	
	function btnUpdSub_onClickCheck(button,commit){	
		if(!checkInput()){
			return false;
		}else{
			return true;
		}
	}
	
	/**提交*/
	function btnUpdSub_postSubmit(){
		$.success("修改成功！",function(){
			window_winUpd.close();
			qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex);
		});
	}
	
	
	/****************************************** 3.-->删除************************************/
	function btnDelete_onClick(){		
		
			var foo = qrsRoutMng_dataset.getValue("routeId");
			$.confirm("是否确认删除?注:一经删除无法恢复!!!", function() {
				qrsRoutMng_dataset.setParameter("foo",foo);
				btnDeleteSub.click();
	        }, function() {
	        	return false;
	        });
		
	}	
	/**提交*/
	function btnDeleteSub_postSubmit(){
		$.success("删除成功！",function(){
			if(qrsRoutMng_dataset.length == '1'){
				if(qrsRoutMng_dataset.pageIndex == '1'){
					qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex);
				}else{
					qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex-1);
				}
			}else{
				qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex);	
			}
		});
	}
	/**************************************** 4.-->刷新缓存************************************/
	function btnRef_onClick(){
		$.confirm("是否刷新缓存?", function() {
			btnRefSub.click();
        }, function() {
        	return false;
        });
	}
	
	function btnRefSub_postSubmit(){
		$.success("刷新成功！",function(){
			qrsRoutMng_dataset.flushData(qrsRoutMng_dataset.pageIndex);
		});
		
	}
	</script>
</snow:page>