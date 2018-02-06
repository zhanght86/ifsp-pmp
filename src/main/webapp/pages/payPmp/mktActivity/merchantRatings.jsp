<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="客户评级">
    <!-- 商户评级临时表数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.mktActivity.dataset.merchantRatings" id="merchantRatings" submitMode="current" init="true"></snow:dataset>
	<!-- 商户评级历史表数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.mktActivity.dataset.historyTable" id="historyTable" submitMode="all" init="false"></snow:dataset>
	<!-- 审核人数据集 -->
	<snow:dataset path="com.ruimin.ifs.pmp.mktActivity.dataset.accountInfo" id="accountInfo" submitMode="all" init="false"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryMerchantRatings" dataset="merchantRatings" fieldstr="qmchtId,qmchtNm,qmchtLvC" border="false"></snow:query>
	<snow:grid dataset="merchantRatings" id="merchantRatings" fieldstr="mchtId,mchtNm,mchtLvL,mchtLvC,dayDepositL,busiCntL,busiAmtL,MFlg,oper[200]" 
	           fitcolumns="true" height="99%" paginationbar=""></snow:grid>
	<!-- 变更商户等级窗体 -->
	<snow:window id="windowModifyId" title="变更" modal="true" closable="true" buttons="btnSub">
	    <center>商户评级基本信息</center>
		<snow:form id="formMod" dataset="merchantRatings" border="false" label="保存" 
		           fieldstr="mchtId,mchtNm,mchtLvC,dayDepositL,busiCntL,busiAmtL,MFlg,changeLv" 
		           collapsible="false" colnum="4">
		</snow:form>
		<snow:button id="btnSub" dataset="merchantRatings" hidden="true"></snow:button>
	</snow:window>
	<!-- 身份验证账号密码窗体 -->
	<snow:window id="windowMod" title="请输入账户信息" modal="true" closable="true" buttons="btnUpdate">
		<snow:form id="formModifyId" dataset="accountInfo" border="false" label="保存" 
		           fieldstr="username,password" 
		           collapsible="false" colnum="4">
		</snow:form>
		<snow:button id="btnUpdate" dataset="merchantRatings" hidden="true"></snow:button>
	</snow:window>
	<!-- 历史评级信息查詢 -->
	<snow:window id="onClickQueryId" title="历史评级信息" modal="true"
		closable="true" buttons="" width="750">
		<snow:form id="formModifyId" dataset="merchantRatings" border="false" label=""
			collapsible="true" colnum="4" labelwidth="100"
			fieldstr="mchtId,mchtNm,mchtLvL,mchtLvC">
		</snow:form>
		<snow:grid id="historyTable" width="750" dataset="historyTable" 
			fieldstr="rankDate[110],mchtLvC[110],dayDepositL[110],busiCntL[110],busiAmtL[110],MFlg[122]"
			fitcolumns="true" border="true" label="商户历史评级信息"  height="99%" pagination="true">
		</snow:grid>
	</snow:window>
	
	<script>
	//操作
	function merchantRatings_oper_onRefresh(mchtId){
		if(mchtId){
			var mchtId = mchtId.getValue("mchtId");
			return     "&nbsp;&nbsp;"+
				       "<i class='fa fa-search'></i>&nbsp;"+
                       "<a href=\"JavaScript:onClickQuery('" + mchtId + "')\">历史评级信息</a>"+
                       "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                       "<i class='fa fa-pencil'></i>&nbsp;"+
                       "<a href=\"JavaScript:onClickModify('" + mchtId + "')\">变更</a>";
	    }
	}	
	//变更的单击事件弹出修改对话框
	function onClickModify(mchtId){
		var termId = merchantRatings_dataset.getValue("mchtId");
		if (!mchtId) {
			return false;
		}
		merchantRatings_dataset.setFieldReadOnly("mchtId", true);
		merchantRatings_dataset.setFieldReadOnly("mchtNm", true);
		merchantRatings_dataset.setFieldReadOnly("mchtLvC", true);
		merchantRatings_dataset.setFieldReadOnly("dayDepositL", true);
		merchantRatings_dataset.setFieldReadOnly("busiCntL", true);
		merchantRatings_dataset.setFieldReadOnly("busiAmtL", true);
		merchantRatings_dataset.setFieldReadOnly("MFlg", true);
		window_windowModifyId.show();
	}
	//btnSub变更
	function btnSub_onClick(){
		//提交弹出身份验证窗口
		window_windowMod.open();
		//审核弹出框出来后把更改值设为只读模式
		merchantRatings_dataset.setFieldReadOnly("changeLv", true);
	}
	function btnSub_onClickCheck(){
		var changeLv=merchantRatings_dataset.getValue("changeLv");
		if(changeLv == null || changeLv == ""){
			$.error("变更值不能为空！");
			return false;
		}
		return true;
	}
	//如果关掉验证窗口则把更改窗口改为可编辑模式(定义窗口关闭后事件)
	function window_windowMod_afterClose(){
		merchantRatings_dataset.setFieldReadOnly("changeLv", false);
		//清空审核人数据集里面的内容
		accountInfo_dataset.clearData();
	}
	//当关闭修改框时，取消修改记录
	function window_windowModifyId_afterClose(){
		merchantRatings_dataset.cancelRecord();
	}
	
	//确认修改之前的验证
	function btnUpdate_onClickCheck(){
		var mchtId=merchantRatings_dataset.getValue("mchtId");
		var changeLv=merchantRatings_dataset.getValue("changeLv");
		accountInfo_dataset.setParameter("mchtId",mchtId);
		accountInfo_dataset.setParameter("changeLv",changeLv);
		var username=accountInfo_dataset.getValue("username");
		var password=accountInfo_dataset.getValue("password");
		accountInfo_dataset.setParameter("username",username);
		accountInfo_dataset.setParameter("password",password);
		if(username == null || username == ""){
			$.error("账号不能为空！");
			return false;
		}
		if(password == null || password == ""){
			$.error("密码不能为空！");
			return false;
		}
		return true;
	}
	function btnUpdate_postSubmit(){
		$.success("变更已审核！",function(){
			window_windowModifyId.close();
			window_windowMod.close();
			//清空审核人数据集里面的内容
			accountInfo_dataset.clearData();
			merchantRatings_dataset.flushData(merchantRatings_dataset.pageIndex);
		});
	}
	//弹出查询历史评级信息 
	function onClickQuery(){
		merchantRatings_dataset.setFieldReadOnly("mchtId", true);
		merchantRatings_dataset.setFieldReadOnly("mchtNm", true);
		merchantRatings_dataset.setFieldReadOnly("mchtLvL", true);
		merchantRatings_dataset.setFieldReadOnly("mchtLvC", true);
		var mchtId =merchantRatings_dataset.getValue("mchtId");
		historyTable_dataset.setParameter("qmchtId",mchtId);
		historyTable_dataset.flushData(historyTable_dataset.pageIndex);
		window_onClickQueryId.open();
	}
	//关闭历史评级信息窗口之后清楚数据集
	function window_onClickQueryId_afterClose(){
		//清空审核人数据集里面的内容
		historyTable_dataset.clearData();
		historyTable_dataset.flushData(historyTable_dataset.pageIndex);
	}
	</script>      
</snow:page>