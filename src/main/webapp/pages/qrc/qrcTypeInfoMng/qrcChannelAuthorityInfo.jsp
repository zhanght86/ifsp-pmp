<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
	<snow:page title="二维码渠道权限管理">
		<snow:dataset id="DataQrcChannelAuthorityInfo" path="com.ruimin.ifs.pmp.qrcMng.dataset.qrcChannelAuthorityInfo" submitMode="current" init="true"></snow:dataset>
		<!-- 查询条件 -->
		<snow:query label="请输入查询条件" id="queryId" dataset="DataQrcChannelAuthorityInfo" fieldstr="qChlNo,qQrcTypeNo"></snow:query>
		<!-- 查询结果集分页显示 -->
		<snow:grid dataset="DataQrcChannelAuthorityInfo" id="gridId" height="99%" label="渠道二维码权限信息"fieldstr="chlNo,qrcTypeNo" paginationbar="btnAdd,btnUpdate,btnDel"></snow:grid>	
	   <!------------------------------------------------------------------------------------------------按钮------------------------------------------------------------------->	
		<!-- 新增 按钮--> 
		<snow:window id="windowAddId" title="渠道二维码权限管理--> 新增" modal="true" closable="true" buttons="btnSave" width="820"> 
			<snow:form id="baseInfo1" dataset="DataQrcChannelAuthorityInfo" border="false" label="" collapsible="true" colnum="4" labelwidth="200"  fieldstr="chlNo,qrcTypeNo" ></snow:form>
			<snow:button id="btnSave" dataset="DataQrcChannelAuthorityInfo" hidden="true"></snow:button>
		</snow:window>	
	    <!-- 修改按钮-->
		<snow:window id="windowUpdateId" title="渠道二维码权限管理--> 修改" modal="true" closable="true" buttons="btnUpdateSave" width="820">
			<snow:form id="baseInfo2" dataset="DataQrcChannelAuthorityInfo" border="false" label="" labelwidth="200" fieldstr="chlNo,qrcTypeNo" ></snow:form> 
			<snow:button id="btnUpdateSave" dataset="DataQrcChannelAuthorityInfo" hidden="true"></snow:button> 
		</snow:window> 
	   <!-- 删除按钮 -->	
	   <snow:button id="btnDelete" dataset="DataQrcChannelAuthorityInfo" hidden="true"></snow:button>  
	</snow:page>
	
<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->	   
   <script type="text/javascript">
       /**
        *二维码渠道权限管理新增
        *@author zhaodk
        **/
		function btnAdd_onClick(){
			window_windowAddId.open();
		}
	       /**
	        *二维码渠道权限管理新增提交
	        *@author zhaodk
	        **/ 
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				DataQrcChannelAuthorityInfo_dataset.flushData(DataQrcChannelAuthorityInfo_dataset.pageIndex);
			});
		}
		function window_windowAddId_beforeClose(wmf){
			DataQrcChannelAuthorityInfo_dataset.cancelRecord();
		}
	       /**
	        *二维码渠道权限管理修改
	        *@author zhaodk
	        **/
		function btnUpdate_onClick(){
			DataQrcChannelAuthorityInfo_dataset.setReadOnly(false);
			var chlNo = DataQrcChannelAuthorityInfo_dataset.getValue("chlNo");
			DataQrcChannelAuthorityInfo_dataset.setParameter("chlNo",chlNo);
			var qrcTypeNo = DataQrcChannelAuthorityInfo_dataset.getValue("qrcTypeNo");
			DataQrcChannelAuthorityInfo_dataset.setParameter("qrcTypeNo",qrcTypeNo);
			window_windowUpdateId.open();
		}
	       /**
	        *二维码渠道权限管理提交
	        *@author zhaodk
	        **/
		function btnUpdateSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUpdateId.close();
				DataQrcChannelAuthorityInfo_dataset.flushData(DataQrcChannelAuthorityInfo_dataset.pageIndex);
			});
		}
	       /**
	        *二维码渠道权限管理删除
	        *@author zhaodk
	        **/
		function btnDel_onClick(){
			var chlAuNo = DataQrcChannelAuthorityInfo_dataset.getValue("chlAuNo");
			$.confirm("是否确认删除该渠道吗!", function() {
				DataQrcChannelAuthorityInfo_dataset.setParameter("chlAuNo",chlAuNo);
				btnDelete.click();
	        }, function() {
	        	return false;
	        });
		}
		function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				DataQrcChannelAuthorityInfo_dataset.flushData(DataQrcChannelAuthorityInfo_dataset.pageIndex);
			});
		}
</script> 