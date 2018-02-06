<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="支付通道交易">
	<!-- 支付通道交易信息： 数据集 -->
	<snow:dataset id="pagyTxnBaseInfo" init="true" submitMode="current"
		path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnBaseInfo"></snow:dataset>

	<!-- 支付通道交易账户类型银行关系： 数据集 -->
	<snow:dataset id="pagyTxnAcctBankRel" init="false" submitMode="all"
		path="com.ruimin.ifs.pmp.chnlMng.dataset.pagyTxnAcctBankRel"></snow:dataset>

	<!-- 查询条件 -->
	<snow:query id="querybtn" collapsible="false" border="false"
		label="查询条件" dataset="pagyTxnBaseInfo"
		fieldstr="qpagyTxnCode,qpagyTxnName"></snow:query>

	<!-- 支付通道交易信息列表显示 -->
	<snow:grid dataset="pagyTxnBaseInfo" id="gridId" label="支付通道交易信息"
		fitcolumns="true" height="99%" paginationbar="btAdd,btUpt,btState,btnPagyCore"
		fieldstr="pagyTxnCode,pagyTxnName,pagyName,txnType,bindCheck,cancelTxncode,refundTxnCode,queryTxnCode,validateTxnCode,pagyTxnStat,opr"></snow:grid>

	<!-- 新增 -->
	<snow:window id="winAddId" title="添加通道交易" modal="true" closable="true"
		width="800" buttons="btAddSave">
		<p style="">支付通道交易基础信息</p>
		<!-- 1.支付通道交易基础信息 -->
		<snow:form id="AddPagyTxnfrom" dataset="pagyTxnBaseInfo"
			label="通道交易基础信息" collapsible="true"
			fieldstr="pagyTxnName,txnCode,pagyNo,txnType,pagyTxnStat,bindCheck,cancelTxncode,refundTxnCode,queryTxnCode,validateTxnCode"
			border="false">
		</snow:form>
		<br />

		<!-- 2.支付通道交易账户类型银行关系列表 -->
		<snow:grid dataset="pagyTxnAcctBankRel"
			fieldstr="acctTypeNoRel[360],bankNoRel[360]" id="gridAddConfig" label="支付通道交易账户类型银行关系列表"
			fitcolumns="true" height="300" editable="true" toolbar="toolbarExt01">
		</snow:grid>
		<snow:toolbar id="toolbarExt01">
			<snow:button id="btnAddSection01" dataset="pagyTxnAcctBankRel"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteSection01" dataset="pagyTxnAcctBankRel"
				hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btAddSave" dataset="pagyTxnBaseInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 修改 -->
	<snow:window id="winUpdId" title="修改通道交易" modal="true" closable="true"
		width="800" buttons="btUptSave">
		<!-- 1.支付通道交易基础信息 -->
		<snow:form dataset="pagyTxnBaseInfo" id="UpdPagyTxnfrom"
			label="支付通道交易基础信息" collapsible="true"
			fieldstr="pagyTxnCode,pagyTxnName,pagyNo,txnType,pagyTxnStat,bindCheck,cancelTxncode,refundTxnCode,queryTxnCode,validateTxnCode"></snow:form>
		<br />

		<!-- 2.支付通道交易账户类型银行关系列表 -->
		<snow:grid id="gridUptConfig" dataset="pagyTxnAcctBankRel"
			label="支付通道交易账户类型银行关系列表" editable="true" height="300" border="true"
			fieldstr="acctTypeNoRel[360],bankNoRel[360]" fitcolumns="false"
			pagination="false" toolbar="toolbarConfigupt" collapsible="true">
		</snow:grid>
		<snow:toolbar id="toolbarConfigupt">
			<snow:button id="btnAddSection02" dataset="pagyTxnAcctBankRel"
				hidden="false"></snow:button>
			<snow:button id="btnDeleteSection02" dataset="pagyTxnAcctBankRel"
				hidden="false"></snow:button>
		</snow:toolbar>
		<snow:button id="btUptSave" dataset="pagyTxnBaseInfo" hidden="true"></snow:button>
	</snow:window>

	<!-- 详情页面 -->
	<snow:window id="windowDetailId" closable="true" width="800" title="支付通道交易详情" modal="true">
		<!-- 1.支付通道交易基础信息 -->
		<p>通道交易基础信息</p>
		<br>
		<snow:form id="formDetailId" dataset="pagyTxnBaseInfo" border="true"
			fieldstr="pagyTxnCode,pagyTxnName,pagyNo,txnType,pagyTxnStat,bindCheck,cancelTxncode,refundTxnCode,queryTxnCode,validateTxnCode" ></snow:form>
			
		<!-- 支付通道交易账户类型银行关系列表 -->
		<snow:grid id="gridDetailConfig" dataset="pagyTxnAcctBankRel"
			fieldstr="acctTypeNoRelName[360],bankNoRelName[360]"
			height="250" border="true" label="支付通道交易账户类型银行关系列表" editable="false"
			fitcolumns="true" pagination="false" collapsible="true"></snow:grid>				
	</snow:window>
	
	<!-- 确定修改银行有效状态 -->
	<div style="display: none;">
		<snow:button id="btStateSave" dataset="pagyTxnBaseInfo"></snow:button>
	</div>

	<script>
		var isShowSer3 = /^[0]{1}[0]{1}[1-9]{1}|[0]{1}[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{2}$/; // 显示顺序为 001~999整数组成！
		var isBankName21 = /^\S{1,21}$/; // 银行名称由21个汉子组成
		/*下拉事件*/
		function pagyTxnBaseInfo_dataset_txnType_onSelect(value,record){
			if(value == "1"){
				pagyTxnBaseInfo_dataset.setFieldRequired("bindCheck",true);
			}else{
				pagyTxnBaseInfo_dataset.setFieldRequired("bindCheck",false);
			}
		}
		// 输入内容格式校验
		function checkInputByType() {
			var pagyTxnName = pagyTxnBaseInfo_dataset.getValue("pagyTxnName");
			if(!isBankName21.test(pagyTxnName)){
				$.warn("通道交易名称不能超过21个汉字！");
				return false;
			}
			
			var currentRecord = pagyTxnAcctBankRel_dataset.getFirstRecord();
			if (currentRecord != null && currentRecord != '') {
				// 遍历 dataset 验证限额格式
				while (currentRecord) {
					if(currentRecord.getValue("acctTypeNoRel") == ""){
						$.warn("账户类型必须输入！！！");
						return false;
					}
					if(currentRecord.getValue("bankNoRel") == ""){
						$.warn("银行编号必须输入！！！");
						return false;
					}
					// 获取下一行dataset数据集
					currentRecord = currentRecord.getNextRecord();
				}
			}
			return true;
		}

		/*===================================TOP1:支付通道交易新增=======================================*/		
		function btAdd_onClick() {
			pagyTxnBaseInfo_dataset.setReadOnly(false);
			pagyTxnAcctBankRel_dataset.setReadOnly(false);
			pagyTxnBaseInfo_dataset.setFieldRequired("txnCode",true); 
			pagyTxnBaseInfo_dataset.setFieldReadOnly("pagyNo",false);			
			// 刷新通道交易账户银行关系数据
			pagyTxnAcctBankRel_dataset.setParameter("uptRel","NoData");
			pagyTxnAcctBankRel_dataset.flushData(pagyTxnAcctBankRel_dataset.pageIndex);
			window_winAddId.open();	
		}
		
		function window_winAddBankInfo_beforeClose(wmf) {
			pagyTxnBaseInfo_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
		}
		function btAddSave_onClickCheck() {
			var pagyNo  = pagyTxnBaseInfo_dataset.getValue("pagyNo");
			var txnCode = pagyTxnBaseInfo_dataset.getValue("txnCode");
			var pagyTxnName = pagyTxnBaseInfo_dataset.getValue("pagyTxnName");
			if(!isShowSer3.test(txnCode)){
				$.warn("交易序号的格式为 001~999整数组成！");
				return false;
			}		
			if(!isBankName21.test(pagyTxnName)){
				$.warn("通道交易名称不能超过21个汉字！");
				return false;
			}
			pagyTxnBaseInfo_dataset.setParameter("pagyNo",pagyNo);
			pagyTxnBaseInfo_dataset.setParameter("txnCode",txnCode);	
			return true;
		}
		//保存
		function btAddSave_postSubmit() {
			$.success("操作成功!", function() {
				window_winAddId.close();
			});
		}
		/**窗口关闭后清除数据*/
		function window_winAddId_afterClose(){
			pagyTxnBaseInfo_dataset.setFieldRequired("txnCode",false); 
			
			pagyTxnBaseInfo_dataset.clearData();
			pagyTxnBaseInfo_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
			
			pagyTxnAcctBankRel_dataset.clearData();
			pagyTxnAcctBankRel_dataset.setParameter("uptRel","NoData");
			pagyTxnAcctBankRel_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
		}

/*===================================TOP2:支付通道交易修改=======================================*/
		function window_winUpdId_beforeOpen(){
			pagyTxnBaseInfo_dataset.setReadOnly(false);
			pagyTxnAcctBankRel_dataset.setReadOnly(false);
			pagyTxnBaseInfo_dataset.setFieldReadOnly("pagyTxnCode",true);
			pagyTxnBaseInfo_dataset.setFieldReadOnly("pagyNo",true);
			pagyTxnBaseInfo_dataset.setFieldReadOnly("pagyTxnStat",true);
			// 停用状态的数据不可修改
        	var Stat = pagyTxnBaseInfo_dataset.getValue("pagyTxnStat");
        	if(Stat=="02" || Stat=="99"){
        		$.warn("该条数据的状态为未启用或是停用,不可以进行修改！！");
        		return false;
        	}
        	return true;
        }
		function btUpt_onClick() {
			// 刷新通道交易账户银行关系数据
			var pagyTxnCode = pagyTxnBaseInfo_dataset.getValue("pagyTxnCode");
			pagyTxnAcctBankRel_dataset.setParameter("uptRel",pagyTxnCode);
			pagyTxnAcctBankRel_dataset.flushData(pagyTxnAcctBankRel_dataset.pageIndex);			
			window_winUpdId.open();
		}
		function window_winUpdId_beforeClose(wmf) {
			pagyTxnBaseInfo_dataset.cancelRecord();
			pagyTxnAcctBankRel_dataset.cancelRecord();
		}
		function btUptSave_onClickCheck() {
			if (pagyTxnBaseInfo_dataset.validate() == false) {
				return false;
			}
			if (pagyTxnAcctBankRel_dataset.validate() == false) {
				return false;
			}
			//校验信息
			var checkResult = checkInputByType();
			if (!checkResult) {
				return false;
			}
			return true;
		}
		function btUptSave_postSubmit() {
			$.success("操作成功!", function() {
				window_winUpdId.close();
			});
		}	
		/**窗口关闭后清除数据*/
		function window_winUpdId_afterClose(){	
			pagyTxnBaseInfo_dataset.setFieldReadOnly("pagyTxnStat",false);
			pagyTxnBaseInfo_dataset.clearData();
			pagyTxnBaseInfo_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
			
			pagyTxnAcctBankRel_dataset.clearData();
			pagyTxnAcctBankRel_dataset.setParameter("uptRel","NoData");
			pagyTxnAcctBankRel_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);	
		}
		
/*===================================TOP3:详情 =======================================*/
		//向显示列表中添加详情链接
		function gridId_opr_onRefresh(record, fieldId, fieldValue) {
			if (record) {
				return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
			} else {
				return "&nbsp;";
			}
		}		
		function openDetail() {
			// 设置显示字段为可读
			pagyTxnBaseInfo_dataset.setReadOnly(true);
			pagyTxnAcctBankRel_dataset.setReadOnly(true);				
			// 刷新该编号列表信息
			var pagyTxnCode = pagyTxnBaseInfo_dataset.getValue("pagyTxnCode");
			pagyTxnAcctBankRel_dataset.setParameter("uptRel",pagyTxnCode);
			pagyTxnAcctBankRel_dataset.flushData(pagyTxnAcctBankRel_dataset.pageIndex);
			window_windowDetailId.open();
			
		}
		function window_windowDetailId_afterClose(){
			pagyTxnBaseInfo_dataset.setReadOnly(false);
			pagyTxnAcctBankRel_dataset.setReadOnly(false);
		}
		
/*===================================TOP3:停用启用=======================================*/

		//启用/停用 -- 单击事件
		function btState_onClick() {
			var pagyTxnCode = pagyTxnBaseInfo_dataset.getValue("pagyTxnCode");//通道交易编号
			var pagyTxnStat = pagyTxnBaseInfo_dataset.getValue("pagyTxnStat");//数据有效状态
			var msg = "";
			if (pagyTxnStat == "99") {
				msg = "是否要将该通道交易状态改为【启用】?";
				pagyTxnStat = "00";
			} else if(pagyTxnStat == "00"){
				msg = "是否要将该通道交易状态改为【停用】?";
				pagyTxnStat = "99";
			} else{
				msg = "是否要将该通道交易状态改为【启用】?";
				pagyTxnStat = "00";
			}

			$.confirm(msg, function() {
				pagyTxnBaseInfo_dataset.setParameter("pagyTxnCode", pagyTxnCode);
				pagyTxnBaseInfo_dataset.setParameter("pagyTxnStat", pagyTxnStat);
				btStateSave.click(); //触发更新有效状态
			}, function() {
				return false;
			});
		}

		//更新有效状态提交后事件
		function btStateSave_postSubmit() {
			$.success("操作成功!", function() {
				pagyTxnBaseInfo_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
			});
		}

/*===================================TOP4:页面跳转按参数数据查询=======================================*/		
		function initCallGetter_post(){
			var pagyCoreFlag = <%=StringUtil.filtrateSpecialCharater(request.getParameter("pagyCoreFlag"))%>;
			var pagyTxnCode = <%=StringUtil.filtrateSpecialCharater(request.getParameter("param"))%>;
			if(pagyCoreFlag == null){
	 			btnPagyCore.setHidden(true);
	 		}
			if(pagyTxnCode != null){
				pagyTxnBaseInfo_dataset.setParameter("qpagyTxnCode", pagyTxnCode);
				pagyTxnBaseInfo_dataset.flushData(pagyTxnBaseInfo_dataset.pageIndex);
			}
		}
		
		function btnPagyCore_onClick(){
			window.location.href='../oprMng/pagyCoreMng.jsp';
		}
	
	</script>
</snow:page>
