<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="设备库存信息维护">
	<!-- 数据集定义 -->
	<snow:dataset id="paypMachInf" path="com.ruimin.ifs.term.dataset.PaypMachInf" init="true" submitMode="current"></snow:dataset>
    <snow:dataset id="orgtree" path="com.ruimin.ifs.mng.dataset.OrgTree" init="true" parameters="showvir=true"></snow:dataset>
	<!-- 查询 -->
	<snow:query id="querybtn" label="查询条件" dataset="paypMachInf"
		collapsible="false" fieldstr="qmachId,qbatchNo,qpropertyType,qmachType,qmachInst,qmachStat"
		border="false"></snow:query>


	<!-- 表单 -->
	<snow:grid dataset="paypMachInf" height="99%" label="查询列表" id="paypMachInfList" fitcolumns="true"
		fieldstr="machId,batchNo,propertyType,machType,machInst,machStat,companyName,opr"
		paginationbar="btStatus,bathAdd"></snow:grid>

    <!-- 入库窗口 -->
	<snow:window id="addPapyMachInf" closable="true" title="设备入库" modal="true" collapsible="false" 
		buttons="btSave1">
		<!-- 入库窗口新增字段 -->
		<snow:form id="papyMachAdd" dataset="paypMachInf" label="" border="false"
			fieldstr="machInst,propertyType,machType,machNo,confNo,machVersion,machNumber,companyName,propertyInst"></snow:form>
		<br/>
		<snow:button id="btSave" dataset="paypMachInf" ></snow:button>
	</snow:window>
    <!-- 批量入库窗口
	<snow:window id="bathAddPapyMachInf" closable="true" title="设备入库" modal="true" collapsible="false" 
		buttons="btSave1">
		<snow:form id="papyMachAdd" dataset="paypMachInf" label="" border="false"
			fieldstr="machInst,propertyType,machType,machNumber,companyName,propertyInst"></snow:form>
		<br/>
		<snow:button id="btSave" dataset="paypMachInf" ></snow:button>
	</snow:window>
     -->
	
	 <!-- 详情窗口 -->
	<snow:window id="windowDetailId" title="设备信息详情" modal="true" closable="true">
	<!-- 详情窗口新增字段 -->
		<snow:form id="formDetailId" dataset="paypMachInf" border="false" label="设备信息--> 详情 --> 参数详情" 
	fieldstr="machId,machType,machNo,confNo,machVersion,machStat,batchNo,machInst,propertyType,propertyInst,companyName,lastAudTlr,lastAudDateTime,crtTlr,crtDateTime,lastUpdTlr,lastUpdDateTime" collapsible="false" colnum="4"></snow:form>
	</snow:window>
	<script language="javascript">
	function btStatus_onClickCheck(button,commit) {
		var status = paypMachInf_dataset.getValue("machStat");
		if(isNull(status)){
			$.warn("提示：没有可选!");
		}
		if(status == '2'){
			$.warn("提示：该设备已经为已废弃状态!");
			return false;
		}else if(status == '1'){
			$.warn("提示：该设备已经为绑定状态,无法更改为废弃状态,请先解除绑定!");
			return false;
		}else{
			$.confirm("确认将该设备设置为已废弃状态?", function() {
				paypMachInf_dataset.setParameter("machStat", "2");
				commit();
			});
		}
	}
	function btStatus_postSubmit(btn){
		$.success("操作成功!", function() {
			paypMachInf_dataset.flushData(orgtree_dataset.pageIndex);
        });
	}
	function paypMachInfList_opr_onRefresh(record, fieldId, fieldValue){
		if(record){
			return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
		}else{
			return "&nbsp;";
		}
	}
	// 显示详情
	function openDetail(){
		paypMachInf_dataset.setReadOnly(true);
		window_windowDetailId.open();
	}
	


	function btnAdd_onClick(){
		paypMachInf_dataset.setReadOnly(false);
		window_addPapyMachInf.open();
	}
	
	
	
	function window_addPapyMachInf_beforeClose(wmf){		
		paypMachInf_dataset.cancelRecord();
	}
	
	 function btSave_onClickCheck(){
		 var machNumber = paypMachInf_dataset.getValue("machNumber");//取得设备数量
		 paypMachInf_dataset.setParameter("machNumber1", machNumber);//取得设备数量
		 return true;
	 }
	 
	 
	 
	 
	function btSave_postSubmit(btn){
		$.success("操作成功!", function() {
			window_addPapyMachInf.close();
			paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
        });
	}
		/**  * 判断是否null  * @param data  */ 
		function isNull(data) {
			return (data == "" || data == undefined || data == null) ? true : false;
		}
		
		
		var iscallback = false;
        function bathAdd_onClick(){
     	   iscallback = false;
     	   $.open("importFile", '文件导入', "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_BATHADD_LST%>", "750", "280", false, true, null, false,"关闭");
        }
        function importFile_onButtonClick(i,win,framewin){
     	   if(i==0){
     		   win.close();
     		  paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
     	   }else{
     		   framewin.excuteImport();
     		   importFileCallBack();
     	   }
        }
        function importFileCallBack(){
			iscallback = true;
			paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
		}
		
	</script>
</snow:page>
