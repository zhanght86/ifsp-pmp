<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="活动参与商户">
	<%
		String reqActiveNo = request.getParameter("activeNo");
	%>
	<script type="text/javascrpt"  src="../../public/lib/jquery/jquery-1.8.2.js"></script>
    <script type="text/javascrpt"  src="../../public/lib/jquery/jquery.form.js"></script>
     <!--------------- 活动临时信息: 数据集 ----------------->
    <snow:dataset id="activeInfo" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActvInfTmp" submitMode="current" init="true" parameters=""></snow:dataset>

	<!-- 商户参与配置临时表数据集 -->
	<snow:dataset id="mchtIn" path="com.ruimin.ifs.pmp.mktActivity.dataset.ActiveQueryMcht"  submitMode="all" init="true"></snow:dataset>
	
	<snow:form label="活动信息" id="formActiveInfo" dataset="activeInfo" fieldstr="activeNo,activeType,activeNm,activeLv" collapsible="false" colnum="4" border="false"></snow:form>
	<br/>
	<snow:query label="请选择查询条件" id="queryMchtIn" dataset="mchtIn" fieldstr="qmchtId,qmchtName,qmchtAreaNo,qinFlg,qmchtLevl" collapsible="false" colnum="4" border="false"></snow:query>
	<snow:grid id="gridMchtIn"   dataset="mchtIn" fieldstr="select,mchtId,mchtName,mchtAreaNo,inFlg,mchtLevl" 
			height="99%" border="true" label="参与商户" editable="false" fitcolumns="true" pagination="true" 
			collapsible="true">
	</snow:grid>
	<br/>
	<snow:button id="btnMchtInAudit" dataset="activeInfo" hidden="false"></snow:button>
	<snow:button id="btnMchtInSave" dataset="activeInfo" hidden="true"></snow:button>
	<script type ="text/javascript">
		function initCallGetter_post(){
			activeInfo_dataset.setFieldReadOnly("activeNm",true);
			activeInfo_dataset.setFieldReadOnly("activeType",true);
			activeInfo_dataset.setFieldReadOnly("activeLv",true);
			activeInfo_dataset.setFieldRequired("prodId",false);
			//activeInfo_dataset.setParameter("qActiveNo",activeNo);
			activeInfo_dataset.flushData(activeInfo_dataset.pageIndex);
			mchtIn_dataset.flushData(mchtIn_dataset.pageIndex);
		}
		
		function btnMchtInAudit_onClickCheck(){
			var firstRecord = mchtIn_dataset.getFirstRecord();
			if(firstRecord == null){
				$.warn("无参与商户信息，无法提交保存");
				return;
			}
			//window_windowAuditUser.open();
			btnMchtInSave.click();
		}
		
		
		/* function submitCheck(){
			
		} */
		
		//参与商户功能提交成功时
		function btnMchtInSave_postSubmit() {
	 		$.success("操作成功!", function() {
	 			/* window.opener.location.href = window.opener.location.href;
	 			 window.close(); */
	 			parent.mchtIn_onButtonClick();
	 			//window_windowAuditUser.close();
	 			//mchtIn_dataset.flushData(mchtIn_dataset.pageIndex);
	 		});
		 }
		
	</script>
</snow:page>