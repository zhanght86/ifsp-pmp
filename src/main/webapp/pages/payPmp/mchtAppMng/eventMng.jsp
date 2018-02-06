<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="事件申报管理">
	<!-- 数据集加载 -->
		<snow:dataset id="eventMng" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.eventMng" init="true" submitMode="current" ></snow:dataset>		
	<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="eventMng" label="查询条件" fieldstr="qcrtDate,qeventTitle,qphoneNo,qmchtId,qmchtSimpleName,qeventStat"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="eventMng" label="查询列表" fieldstr="crtDate,phoneNo,userName,mchtId,mchtSimpleName,eventTitle,eventStat,opr"
		height = "99%" paginationbar="btnUpd"></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="" buttons="btnReturn" modal="true" closable="true" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="eventtInfoDetail" label="事件信息" dataset="eventMng" fieldstr="crtDate,eventTitle,userId,phoneNo,userName,mchtId,mchtSimpleName,eventInfo"></snow:form>
			<snow:form id="eventInfoDo" label="事件处理" dataset="eventMng" fieldstr="eventStat,eventFdback"></snow:form>
			<snow:button id="btnRtn" dataset="eventMng" hidden="false"></snow:button>
		</snow:window>
		
		<snow:window id="winUpd" title=""  modal="true" closable="true" buttons="btnUpdSub" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="eventtInfoDetail" label="事件信息" dataset="eventMng" fieldstr="crtDate,eventTitle,userId,phoneNo,userName,mchtId,mchtSimpleName,eventInfo"></snow:form>
			<snow:form id="eventInfoDo" label="事件处理" dataset="eventMng" fieldstr="eventStat1,eventFdback"></snow:form>
			<snow:button id="btnUpdSub" dataset="eventMng" hidden="true"></snow:button>
		</snow:window>		
	<script type="text/javascript">
	
/****************************************详情******************************************/
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
 		if (record) {
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(){
		//全部字段设置为-只读
		eventMng_dataset.setReadOnly(true);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		eventMng_dataset.clearData();	
		eventMng_dataset.flushData(eventMng_dataset.pageIndex);
	}
	var isDesc64 = /^\S{1,64}$/;
	function btnUpd_onClickCheck(){		
		/*****************设置特殊字段[只读]*****************/
		eventMng_dataset.setFieldReadOnly("crtDate", true);//
		eventMng_dataset.setFieldReadOnly("eventTitle", true);
		eventMng_dataset.setFieldReadOnly("userId", true);
		eventMng_dataset.setFieldReadOnly("phoneNo", true);
		eventMng_dataset.setFieldReadOnly("userName", true);
		eventMng_dataset.setFieldReadOnly("mchtId", true);
		eventMng_dataset.setFieldReadOnly("mchtSimpleName", true);
		eventMng_dataset.setFieldReadOnly("eventInfo", true);
		/*****************设置特殊字段[必输]*****************/
		eventMng_dataset.setFieldReadOnly("eventStat1", false);//
		eventMng_dataset.setFieldReadOnly("eventFdback", false);//
		eventMng_dataset.setFieldRequired("eventStat1", true);//
		return true;
	}
	
	/**打开修改窗口*/
	function btnUpd_onClick(){
		var eventStat=eventMng_dataset.getValue("eventStat");
		if(!eventStat){
			$.warn("请先选择一条事件信息");
			return false;
		}
		var eventFdback=eventMng_dataset.getValue("eventFdback");
		if(eventStat == "2" || eventStat == "3"){
			$.warn("该事件已处理过！");
		}else{
			//打开窗口
			window_winUpd.open();			
		}
	}
		
	/**提交前检验数据格式*/
	
	function btnUpdSub_onClickCheck(){
		var eventStat1=eventMng_dataset.getValue("eventStat1");
		var eventFdback=eventMng_dataset.getValue("eventFdback");
		if(eventStat1 == "" || eventStat1 == null){
			$.error("【申报状态】必须输入！");
			return false;
		}
		
		if(eventFdback != "" && eventFdback != null){
			if(!isDesc64.test(eventFdback)){
				$.warn("【事件反馈】长度错误（最大长度64位）");
				return false;
			}			
		}
		return true;	
	} 
	
	/**提交*/
	function btnUpdSub_postSubmit(){
		$.success("处理完毕！",function(){
			window_winUpd.close();
			window.location.reload(true);
		});
	}
	
	/**窗口关闭后清除数据*/
	function window_winUpd_afterClose(){
		eventMng_dataset.clearData();	
		eventMng_dataset.flushData(eventMng_dataset.pageIndex);
	}
	
	function btnRtn_onClick(){	
		window_winDetail.close();
		window.location.reload(true);
	}
	
	
	</script>
</snow:page>