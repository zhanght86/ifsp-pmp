<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="用户管理">
	<!-- 数据集加载 -->
		<!-- 商户信息数据集  -->
		<snow:dataset id="suggMng" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.suggMng" init="true" submitMode="current" ></snow:dataset>
		<snow:dataset id="suggMng2" path="com.ruimin.ifs.pmp.mchtAppMng.dataset.suggMng2" init="false" submitMode="current" ></snow:dataset>
	<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="suggMng" label="查询条件" fieldstr="qcrtDate,qrate"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="suggMng" label="查询列表" fieldstr="suggStat,crtDate,rate,userEmail,opr"
		height = "99%" paginationbar=""></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="详情信息"  modal="true" closable="true" width="800">
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoDetail" label="意见建议" dataset="suggMng" fieldstr="crtDate,rate,userEmail,suggInfo"></snow:form>
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
		suggMng_dataset.setReadOnly(true);
		//打开窗口
		window_winDetail.open();
	}
	
	/**窗口关闭后清除数据*/
	function window_winDetail_afterClose(){
		var suggStat = suggMng_dataset.getValue("suggStat");
		if(suggStat == "1"){
			var suggId = suggMng_dataset.getValue("suggId");
			suggMng2_dataset.setParameter("suggId",suggId);
			suggMng2_dataset.flushData(suggMng2_dataset.pageIndex);
// 			var url = '<snow:url flowId="com.ruimin.ifs.pmp.mchtAppMng.comp.SuggMngAction:update"/>';
// 			window.location.href=url+"&suggId="+suggId;		
		}
		suggMng_dataset.clearData();
		suggMng_dataset.flushData(suggMng_dataset.pageIndex);
	}

	</script>
</snow:page>