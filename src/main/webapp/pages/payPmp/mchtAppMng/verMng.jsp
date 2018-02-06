<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="版本管理">
	<!-- 系统公告数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.mchtAppMng.dataset.verMng" id="verMng" submitMode="current" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="verMng" dataset="verMng" fieldstr="qdeviceType,qappVer,qupdFlag" border="false"></snow:query>
	<snow:grid dataset="verMng" label="查询列表" id="gridMain"
		fieldstr="deviceType,appVer,updFlag,lastUpdDateTime,opr" fitcolumns="true"
		height="99%" paginationbar="btnAdd,btnUpd"></snow:grid>
		
	<!-- 详情窗口 -->
	<snow:window id="winDetail" title="" buttons="btnReturn" modal="true" closable="true" width="800">
		<!-- No1 基本信息模块 -->
		<snow:form id="adInfoDetail" label="版本详情" dataset="verMng" fieldstr="deviceType,appVer,updFlag,lastUpdDateTime,updMsg"></snow:form>
	</snow:window>	
	
	<!-- 新增 -->
	<snow:window id="winAdd" title="版本新增" modal="true" closable="true"
		buttons="btnSave" width="850" height="500">
		<snow:form id="verAdd" dataset="verMng"
			border="false" label="保存" collapsible="true" colnum="4"
			labelwidth="100" fieldstr="deviceType,appVer,updFlag,updMsg">
		</snow:form>
		<!-- 按钮: 添加 -->
		<snow:button id="btnSave" dataset="verMng" hidden="true"></snow:button>
	</snow:window>
		
<style type="text/css">
#box {
	width: 750px;
	height: 550px;
	border: 1px solid 0000;
}
</style>

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
		verMng_dataset.setReadOnly(true);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		verMng_dataset.clearData();	
		verMng_dataset.flushData(verMng_dataset.pageIndex);
	}
	
	var isDesc120 = /^\S{1,120}$/;
	var isVer = /^\d+(\.\d+)*$/;
	/* =======================================新增=============================================== */
	//新增单击按钮
	function btnAdd_onClick() {
		verMng_dataset.setReadOnly(false);
		verMng_dataset.setFieldRequired("deviceType", true);
		verMng_dataset.setFieldRequired("appVer", true);
		verMng_dataset.setFieldRequired("updFlag", true);
		window_winAdd.open();
	}
	
	//确认保存之前验证操作
	function btnSave_onClickCheck() {	
		var appVer = verMng_dataset.getValue("appVer");
		appVer = $.trim(appVer);
		var updMsg = verMng_dataset.getValue("updMsg");
		updMsg = $.trim(updMsg);
		if(appVer == "" || appVer == null){
			$.warn("【版本号】必须输入！");
			return false;
		}
		if(!isVer.test(appVer)){
			$.warn("【版本号】格式错误！");
			return false;
		}
		if(appVer != null && appVer !=""){			
			if(appVer.length>10){
				$.warn("【版本号】长度错误（最大长度10位）");
				return false;
			}
		}
		if(updMsg != null && updMsg !=""){			
			if(updMsg.length>120){
				$.warn("【版本信息】长度错误（最大长度120位）");
				return false;
			}
		}
		return true;			
		
	}
	//保存成功
	function btnSave_postSubmit() {
		$.success("操作成功!", function() {
			window_winAdd.close();
			verMng_dataset.flushData(verMng_dataset.pageIndex);
		});
	}
	//新增关闭窗口刷新
	function window_winAdd_afterClose(){
		verMng_dataset.flushData(verMng_dataset.pageIndex);
		window.location.reload(true);
	}
	
	</script>
</snow:page>
