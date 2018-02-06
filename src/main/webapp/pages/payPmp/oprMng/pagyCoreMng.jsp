<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="通道核心配置管理">
	<!-- 数据集加载 -->
		<!-- 查询页面数据集 -->
		<snow:dataset id="pagyCoreMng" path="com.ruimin.ifs.pmp.oprMng.dataset.pagyCoreMng" init="true" ></snow:dataset>
		<!-- 修改表单数据集 -->
		<snow:dataset id="pagyCoreUpd" path="com.ruimin.ifs.pmp.oprMng.dataset.pagyCoreMng" init="true" ></snow:dataset>
	
	<!-- 查询界面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="pagyCoreMng" label="查询条件" fieldstr="qpagyNo,qpagyTxnCode,qacctTypeNo,qmainMchtNo,qpayTxnCode"></snow:query>		
		<!-- 查询展示表单 -->
		<snow:grid id="gridMain" dataset="pagyCoreMng" label="通道核心配置管理" fieldstr="pagyName,pagyTxnName,acctTypeName,mainMchtName,payTxnResp,opr" 
		height = "99%" paginationbar="btnAdd,btnUpd,btnOpen"></snow:grid>

	<!-- 弹出窗口 -->
		<!-- 详情窗口 -->
		<snow:window id="winDetail" title="详情信息"  modal="true" closable="true" width="800">
			<snow:form id="formDetail" dataset="pagyCoreMng" fieldstr="pagyNo,pagyName,pagyTxnCode,pagyTxnName,acctTypeNo,acctTypeName,mainMchtNo,mainMchtName,payTxnCode,payTxnResp" ></snow:form>			
		</snow:window>
		<!-- 修改窗口 -->		
		<snow:window id="winUpd" title="修改配置"  modal="true" closable="true" width="800">
			<snow:form id="formUpd" dataset="pagyCoreMng" fieldstr="pagyNo,pagyName" ></snow:form>
			<br/>
         	<snow:grid dataset="pagyCoreUpd" fieldstr="pagyTxnName,acctTypeName,mainMchtName,payTxnResp" 
            id="gridUpd" fitcolumns="true" editable="" height="120" width="780"></snow:grid>
		</snow:window>
	
	<!-- 配置说明 -->	
	<div>
		<h4>配置说明：</h4>
		<h5>(1)配置完成后，点击"返回通道核心页面"按钮，可回到本页面；</h5>
		<h5>(2)如果配置被停用，需重新配置；</h5>
		<h5>(3)如果新增配置时找不到相关参数，说明配置已存在。找到非启用状态的配置，重新激活即可；</h5>
		<h5></h5>
	</div>

	<script type="text/javascript">
	/*************************************设置需配置字段*****************************************/	
	/**通道交易*/
	function gridMain_pagyTxnName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var needSet = record.getValue("needSet");//需配置的字段名
			if(needSet == "2"){
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyTxnBaseInfo.jsp','false','0')\">新增</a></span>";
			}else{
				return record.getValue("pagyTxnName");
			}
		}		
	}
	
	/**账户类型*/
	function gridMain_acctTypeName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var needSet = record.getValue("needSet");//需配置的字段名
			var pagyTxnCode = record.getValue("pagyTxnCode");
			if(needSet == "3"){
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyTxnBaseInfo.jsp','" + pagyTxnCode +"','0')\">配置</a></span>";
			}else{
				return record.getValue("acctTypeName");
			}
		}		
	}
	
	/**主商户*/
	function gridMain_mainMchtName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var needSet = record.getValue("needSet");//需配置的字段名
			var pagyNo = record.getValue("pagyNo");
			if(needSet == "4"){				
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../chnlMng/chnlAcsInfo.jsp','" + pagyNo +"','0')\">配置</a></span>";
			}else{
				return record.getValue("mainMchtName");
			}
		}	
	}
	
	/**接入交易*/
	function gridMain_payTxnResp_onRefresh(record,fieldId,fieldValue){
		if(record){
			var needSet = record.getValue("needSet");//需配置的字段名
			if(needSet == "5"){				
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyPayTxnBaseInfo.jsp','null','0')\">配置</a></span>";
			}else{
				return record.getValue("payTxnResp");
			}
		}		
	}
	
	/**跳转页面,参数1【路径】，参数2【传参】，参数3【是否为修改】*/
	function openSet(setUrl,param,updFlag){
		window.location.href=setUrl + '?pagyCoreFlag=true&param=' + param +'&updFlag=' + updFlag;
	} 
	
	
	/****************************************详情******************************************/
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
 		if (record) {
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail()\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(){
		window_winDetail.open();
	}
	
	
	/****************************************修改******************************************/
	/**点击修改按钮*/
	function btnUpd_onClickCheck(){
		/**将本条记录的参数发送后台*/
		pagyCoreUpd_dataset.setParameter("qpagyNo",pagyCoreMng_dataset.getValue("pagyNo"));
		pagyCoreUpd_dataset.setParameter("qpagyTxnCode",pagyCoreMng_dataset.getValue("pagyTxnCode"));
		pagyCoreUpd_dataset.setParameter("qacctTypeNo",pagyCoreMng_dataset.getValue("acctTypeNo"));
		pagyCoreUpd_dataset.setParameter("qmainMchtNo",pagyCoreMng_dataset.getValue("mainMchtNo"));
		pagyCoreUpd_dataset.setParameter("qpayTxnCode",pagyCoreMng_dataset.getValue("payTxnCode"));
		pagyCoreUpd_dataset.flushData(pagyCoreUpd_dataset.pageIndex);
		return true;
	}
	
	/**打开修改窗口*/
	function btnUpd_onClick(){		
		window_winUpd.open();
	}
	
	/*************************************设置修改字段****************************************/	
	/**通道交易*/
	function gridUpd_pagyTxnName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var pagyTxnCode = record.getValue("pagyTxnCode");
			if(pagyTxnCode != ""){
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyTxnBaseInfo.jsp','" + pagyTxnCode +"','true')\">修改</a></span>";
			}
		}		
	}
	
	/**账户类型*/
	function gridUpd_acctTypeName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var pagyTxnCode = record.getValue("pagyTxnCode");
			if(record.getValue("acctTypeName") != ""){
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyTxnBaseInfo.jsp','" + pagyTxnCode +"','true')\">修改</a></span>";
			}
		}		
	}
	
	/**主商户*/
	function gridUpd_mainMchtName_onRefresh(record,fieldId,fieldValue){
		if(record){
			var mainMchtNo = record.getValue("mainMchtNo");
			if(mainMchtNo != ""){				
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../chnlMng/chnlAcsInfo.jsp','" + mainMchtNo +"','true')\">修改</a></span>";
			}
		}	
	}
	
	/**接入交易*/
	function gridUpd_payTxnResp_onRefresh(record,fieldId,fieldValue){
		if(record){
			var payTxnCode = record.getValue("payTxnCode");
			if(payTxnCode != ""){			
				return "<span style='width:100%;text-align:center' class='fa fa-check'><a href=\"JavaScript:openSet('../oprMng/pagyPayTxnBaseInfo.jsp','" + payTxnCode +"','true')\">修改</a></span>";
			}
		}		
	}
	
	
	/*************************************新增通道交易 ****************************************/	
	function btnAdd_onClick(){
		window.location.href='../oprMng/pagyTxnBaseInfo.jsp?pagyCoreFlag=true';
	}
	
	
	/*************************************开放渠道交易接入权限 ****************************************/	
	function pagyCoreMng_dataset_afterScroll(ds, record) {
		if (record) {
			if(record.getValue("payTxnResp") != ""){
				btnOpen.setDisabled(false);
			}else{
				btnOpen.setDisabled(true);
			}
		}					
	}
	
	/**跳转渠道配置页面*/
	function btnOpen_onClick(){		
		window.location.href='../chnlMng/channelInfoMng.jsp?pagyCoreFlag=true';
	}
	</script>
</snow:page>