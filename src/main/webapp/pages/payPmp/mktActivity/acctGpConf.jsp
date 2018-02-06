<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="账户分组">
	<!-- 账户分组主页面查询数据集 -->
	<snow:dataset id="AcctGpConf"
		path="com.ruimin.ifs.pmp.mktActivity.dataset.AcctGpConf" init="true"
		submitMode="current"></snow:dataset>
	
	<!-- 账户分组详情显示数据集 -->	
	<snow:dataset id="AcctGpConfQueryAll"
		path="com.ruimin.ifs.pmp.mktActivity.dataset.AcctGpConfQueryAll" init="true"
		submitMode="current"></snow:dataset>
	
	<!-- 账户分组活动信息显示数据集 -->		
	<snow:dataset id="AcctGpConfQueryActive"
		path="com.ruimin.ifs.pmp.mktActivity.dataset.AcctGpConfQueryActive" init="true"
		submitMode="current"></snow:dataset>
		
	<!-- 查询栏 -->
	<snow:query id="queryId" dataset="AcctGpConf" label="请输入查询条件" fieldstr="qgpTpNo,qgpTpNm"></snow:query>

	<!-- 主界面表单 -->
		<snow:grid dataset="AcctGpConf" sortable="true" remotesort="true" id="gridId" 
		fitcolumns="ture" fieldstr="gpTpNo,gpTpNm,gpNumber,acState,opr" height = "99%"> 
		</snow:grid>
	
	<!-- 详情信息表单 -->
	<snow:window id="detail" title="分组详情" modal="true"
		closable="true" buttons="" width="850" >
		<snow:form id="baseInfo1" dataset="AcctGpConf" border="false" label=""
			collapsible="true" colnum="4" labelwidth="100"
			fieldstr="gpTpNo,gpTpNm">
		</snow:form>
		<snow:grid id="gridDetail" width="835" dataset="AcctGpConfQueryAll" fitcolumns="ture"
			fieldstr="gpSeq,gpNm,memTp,memNm,acctTpLen,acctTp" label="成员信息"
			border="true"  editable="false" pagination="true" height = "99%">		
		</snow:grid>
	</snow:window>
	
	<!-- 活动情况表单 -->
	<snow:window id="active" title="分组详情" modal="true"
		closable="true" buttons="" width="850" >
		<snow:form id="baseInfo2" dataset="AcctGpConf" border="false" label=""
			collapsible="true" colnum="4" labelwidth="100"
			fieldstr="gpTpNo,gpTpNm">
		</snow:form>
		<snow:grid id="gridActive" width="835" dataset="AcctGpConfQueryActive" fitcolumns="ture"
			fieldstr="activeNo,activeNm,activeType,SDate,EDate" label="活动信息"
			border="true"  editable="false" pagination="true" height = "99%">		
		</snow:grid>
	</snow:window>
	
	<script type="text/javascript">
	
	//  *********************************活动使用功能方法**************************************
	
	//活动使用情况显示
	function gridId_acState_onRefresh(record, fieldId, fieldValue) {
			if (fieldValue=="未使用") {
	 			return "</i>&nbsp;<a>未使用</a>";
	 		}else{
	 			return "</i>&nbsp;<a href=\"JavaScript:onClickActive()\">使用中</a>";
	 		}
 	}
	
	//打开活动信息窗口
	function onClickActive(){
		AcctGpConf_dataset.setFieldReadOnly("gpTpNo", true);
		AcctGpConf_dataset.setFieldReadOnly("gpTpNm", true);
		
		var qgpTpNo =AcctGpConf_dataset.getValue("gpTpNo");
		AcctGpConfQueryActive_dataset.setParameter("qgpTpNo",qgpTpNo);//把分组编号的值插到查询分组编号中
		AcctGpConfQueryActive_dataset.flushData(AcctGpConfQueryActive_dataset.pageIndex);
		
		window_active.open();	
	}
	
	//关闭活动信息窗口后清除数据集内容
	function window_active_afterClose(){
		AcctGpConfQueryActive_dataset.flushData(AcctGpConfQueryActive_dataset.pageIndex);
	}
	
//  *********************************详情显示功能方法**************************************
	
	//详情显示
	function gridId_opr_onRefresh(record, fieldId, fieldValue) {
 		if (record) {
 			return "<i class='fa fa-search'></i>&nbsp;<a href=\"JavaScript:onClickDetail()\">详情</a>";
 		}
 	}
	
	//打开详情窗口
	function onClickDetail(){
		AcctGpConf_dataset.setFieldReadOnly("gpTpNo", true);
		AcctGpConf_dataset.setFieldReadOnly("gpTpNm", true);
		
		var qgpTpNo =AcctGpConf_dataset.getValue("gpTpNo");
		AcctGpConfQueryAll_dataset.setParameter("qgpTpNo",qgpTpNo);//把分组编号的值插到查询分组编号中
		AcctGpConfQueryAll_dataset.flushData(AcctGpConfQueryAll_dataset.pageIndex);
		
		window_detail.open();	
	}
	
	//关闭详情窗口后清除数据集内容
	function window_detail_afterClose(){
		AcctGpConfQueryAll_dataset.flushData(AcctGpConfQueryAll_dataset.pageIndex);
	}
	</script>
</snow:page>
