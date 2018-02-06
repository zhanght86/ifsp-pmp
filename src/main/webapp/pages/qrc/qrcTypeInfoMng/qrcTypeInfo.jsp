<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<!------------------------------------------------------------------------------------------------- snow自定义标签---------------------------------------------------------------------------------------------------->
	<snow:page title="二维码类型维护">
		<snow:dataset id="DataQrcTypeInfo" path="com.ruimin.ifs.pmp.qrcMng.dataset.qrcTypeInfo" submitMode="current" init="true"></snow:dataset>
		<!-- 查询条件 -->
		<snow:query label="请输入查询条件" id="queryId" dataset="DataQrcTypeInfo" fieldstr="QrcTypeNo,QrcTypeName,QrcTypeSat"></snow:query>
		<!-- 查询结果集分页显示 -->
		<snow:grid dataset="DataQrcTypeInfo" id="gridId" height="99%" fieldstr="qrcTypeNo,qrcTypeName,unitsType,unitsValues,qrcAmount,qrcTypeSat" label="二维码类型信息" paginationbar="btnAdd,btnUpdate,btnDel"></snow:grid>	
	   <!------------------------------------------------------------------------------------------------按钮------------------------------------------------------------------->	
		<!-- 新增 按钮--> 
		<snow:window id="windowAddId" title="二维码类型管理--> 新增" modal="true" closable="true" buttons="btnSave" width="820"> 
			<snow:form id="baseInfo1" dataset="DataQrcTypeInfo" border="false" label="" collapsible="true" colnum="4" labelwidth="200"  fieldstr="qrcTypeName,unitsType,unitsValues,qrcAmount" ></snow:form>
			<snow:button id="btnSave" dataset="DataQrcTypeInfo" hidden="true"></snow:button>
		</snow:window>	
	    <!-- 修改按钮-->
		<snow:window id="windowUpdateId" title="二维码类型管理--> 修改" modal="true" closable="true" buttons="btnUpdateSave" width="820">
			<snow:form id="baseInfo2" dataset="DataQrcTypeInfo" border="false" label="" labelwidth="200" fieldstr="qrcTypeName,unitsType,unitsValues,qrcAmount" ></snow:form> 
			<snow:button id="btnUpdateSave" dataset="DataQrcTypeInfo" hidden="true"></snow:button> 
		</snow:window> 
	   <!-- 停用/启用按钮 -->	
	   <snow:button id="btnDelete" dataset="DataQrcTypeInfo" hidden="true"></snow:button> 
	</snow:page>
	
<!------------------------------------------------------------------------------------------------JavaScript------------------------------------------------------------------->	   
   <script type="text/javascript">
   var isPositiveInt = /^[0-9]*[1-9][0-9]*$/; //判断是否为正整数 
   var isQrcAmount =  /^(([1-9]\d)|\d)$/;//0-99整数         
   
       /*二维码类型新增
        *@author ChengGX
        **/
		function btnAdd_onClick(){
			window_windowAddId.open();
			DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",false);
		}
   
		 /**
	        *二维码类型新增提交前的校验
	        *@author ChengGX
	        **/ 
		function btnSave_onClickCheck(){
			var unitsValues = DataQrcTypeInfo_dataset.getValue("unitsValues");
			if(unitsValues.length<=10){
				if(!isPositiveInt.test(unitsValues) && unitsValues !="0"){
					$.warn("有效期单位值不为空，且必须为整数！！");
		     		return false;
				   }
			}else {
				$.warn("有效期单位值长度不能超过10位！！");
				return false;
			}
			var qrcAmount = DataQrcTypeInfo_dataset.getValue("qrcAmount");
			if(!isQrcAmount.test(qrcAmount)){
				$.warn("生成数量只能为2位以内整数(含2位)！");
				return false;
			}
			return true;
			
		}
	       /**
	        *二维码类型新增提交
	        *@author ChengGX
	        **/ 
		function btnSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowAddId.close();
				DataQrcTypeInfo_dataset.flushData(DataQrcTypeInfo_dataset.pageIndex);
			});
		}
		function window_windowAddId_beforeClose(wmf){
			DataQrcTypeInfo_dataset.cancelRecord();
			DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",false);
		}
		/**
	        *二维码有效期单位类型来下来控制无限的输入值
	        *@author ChengGX
	        **/ 
	        
	    function DataQrcTypeInfo_dataset_unitsType_onSelect(v,record){
	    	DataQrcTypeInfo_dataset.setValue("unitsValues","");
	    	DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",false);
	    	if(v == '03'){
	    		DataQrcTypeInfo_dataset.setValue("unitsValues","0");
	    		DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",true);
	    	}

		}
	       /**
	        *二维码类型修改
	        *@author ChengGX
	        **/
		function btnUpdate_onClick(){
			DataQrcTypeInfo_dataset.setReadOnly(false);
			var qrcTypeName = DataQrcTypeInfo_dataset.getValue("qrcTypeName");
			DataQrcTypeInfo_dataset.setParameter("qrcTypeName",qrcTypeName);
			var unitsType = DataQrcTypeInfo_dataset.getValue("unitsType");
			DataQrcTypeInfo_dataset.setParameter("unitsType",unitsType);
			var unitsValues = DataQrcTypeInfo_dataset.getValue("unitsValues");
			DataQrcTypeInfo_dataset.setParameter("unitsValues",unitsValues);
			window_windowUpdateId.open();
		}
		 /**
	        *二维码类型修改窗体打开前的初始化
	        *@author ChengGX
	        **/  
		function window_windowUpdateId_beforeOpen(win){
			var unitsType = DataQrcTypeInfo_dataset.getValue("unitsType");
			if(unitsType=='03'){
				DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",true);	
			}else {
				DataQrcTypeInfo_dataset.setFieldReadOnly("unitsValues",false);
			}
		}
		 /**
	        *二维码类型修改提交前的校验
	        *@author ChengGX
	        **/ 
		function btnUpdateSave_onClickCheck(){
			var unitsValues = DataQrcTypeInfo_dataset.getValue("unitsValues");
			if(unitsValues.length<=10){
				if(!isPositiveInt.test(unitsValues) && unitsValues !="0"){
					$.warn("有效期单位值必须为整数！！");
		     		return false;
				   }
				
			}else {
				$.warn("有效期单位值长度不能超过10位！！");
				return false;
			}
			var qrcAmount = DataQrcTypeInfo_dataset.getValue("qrcAmount");
			if(!isQrcAmount.test(qrcAmount)){
				$.warn("生成数量只能为2位以内整数(含2位)！");
				return false;
			}
			return true;
			
		}
	       /**
	        *二维码类型提交
	        *@author ChengGX
	        **/
		function btnUpdateSave_postSubmit() {
			$.success("操作成功!", function() {
				window_windowUpdateId.close();
				DataQrcTypeInfo_dataset.flushData(DataQrcTypeInfo_dataset.pageIndex);
			});
		}
	 function window_windowUpdateId_afterClose(win){
		DataQrcTypeInfo_dataset.flushData(DataQrcTypeInfo_dataset.pageIndex);
	}
	       /**
	        *二维码类型停用启用
	        *@author ChengGX
	        **/
		  function btnDel_onClick(){
			var qrcTypeSat = DataQrcTypeInfo_dataset.getValue("qrcTypeSat");
			if(qrcTypeSat =='00'){
					$.confirm("是否确认设置类型为停用?", function() {
						btnDelete.click();
			        }, function() {
			        	return false;
			        });
			}else {
				$.confirm("是否确认设置类型为启用?", function() {
					btnDelete.click();
		        }, function() {
		        	return false;
		        });
			}
			return true;
		} 
		 
		 function btnDelete_postSubmit() {
			$.success("操作成功!", function() {
				DataQrcTypeInfo_dataset.flushData(DataQrcTypeInfo_dataset.pageIndex);
			});
		}  
		   
		 
</script> 