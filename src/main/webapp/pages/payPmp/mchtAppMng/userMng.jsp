<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="用户管理">
	<!-- 数据集加载 -->
		<!-- 商户信息数据集  -->
		<snow:dataset id="userMng" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.userMng" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset id="userInfo" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.userInfo" init="fasle" submitMode="current" ></snow:dataset>
		
	<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="userMng" label="查询条件" fieldstr="quserName,qmchtArtifId,qmchtSimpleName,qmchtId"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="userMng" label="查询列表" fieldstr="userId,phoneNo,userName,mchtArtifId,mchtId,mchtSimpleName,opr"
		height = "99%" paginationbar=""></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="详情信息"  modal="true" closable="true" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoDetail" label="用户详情" dataset="userMng" fieldstr="userId,phoneNo,userName,mchtId,mchtSimpleName,nickName"></snow:form>
			<snow:grid id="gridMain" dataset="userInfo" label="店员信息" fieldstr="userIdAss[200],empNo[100],userName[200],phoneNo[200]"
			height = "80%" paginationbar=""></snow:grid>
<%-- 			<snow:button id="btnRtn" dataset="userMng" hidden="false"></snow:button> --%>
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
		var userId = userMng_dataset.getValue("userId");
		userInfo_dataset.setParameter("userId",userId);
		userInfo_dataset.flushData(userInfo_dataset.pageIndex);
		//全部字段设置为-只读
		userMng_dataset.setReadOnly(true);
		userInfo_dataset.setReadOnly(true);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		userMng_dataset.clearData();
		userMng_dataset.flushData(userMng_dataset.pageIndex);
	}

	function btnRtn_onClick(){	
		window_winDetail.close();
		window.location.reload(true);
	}
	</script>
</snow:page>