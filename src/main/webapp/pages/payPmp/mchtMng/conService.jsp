<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%
Boolean initData = true;
%>
<snow:page title="商户合同服务">	
	<snow:dataset id="conSerPro" path="com.ruimin.ifs.pmp.mchtMng.dataset.conSerPro" submitMode="all" init="true"></snow:dataset>
	<snow:dataset id="conSerAcc" path="com.ruimin.ifs.pmp.mchtMng.dataset.conSerAcc" submitMode="all" init="false"></snow:dataset>
	<snow:form id="proShow" dataset="conSerPro"  border="false" label="合同信息" fieldstr="prodName1" collapsible="true" colnum="4"></snow:form>	
	<snow:grid id="gridService" dataset="conSerAcc" fieldstr="acctTypeCode,rateId" height="250" border="true" editable="true"
			fitcolumns="true" pagination="false" collapsible="true"></snow:grid>
			
	<snow:button id="btnAddSer" dataset="conSerAcc" hidden="false" ></snow:button>
<script type="text/javascript">
function initCallGetter_post() {	
	  var level = "<%=StringUtil.filtrateSpecialCharater(request.getParameter("level"))%>";
	  var prodId =  "<%=StringUtil.filtrateSpecialCharater(request.getParameter("prodId")) %>";
	  if(level != "two"){
			 <%
		  String str = StringUtil.filtrateSpecialCharater(request.getParameter("prodName"));
		 if(str == null){
			 str = "";
		 }
		  String roleName = java.net.URLDecoder.decode(str,"UTF-8"); 
 		  if(str != null){
 			String prodName = new String(str.getBytes("ISO-8859-1"),"UTF-8");
		   %>
		 
		   conSerPro_dataset.setValue("prodName1","<%=roleName%>");
			conSerPro_dataset.setFieldReadOnly("prodName1",true); 
		    conSerAcc_dataset.setParameter("prodId",prodId);
		    conSerAcc_dataset.setParameter("conId","<%=StringUtil.filtrateSpecialCharater(request.getParameter("conId"))%>"); 
			conSerAcc_dataset.flushData(conSerAcc_dataset.pageIndex);
			if(level == "three"){
				 conSerAcc_dataset.setReadOnly(true);
				 btnAddSer.setHidden(true);				 
			}
		 <% 
		 }
		 %>
	  }else {
		  conSerPro_dataset.setFieldReadOnly("prodName1",false);
		  conSerAcc_dataset.setReadOnly(false);
	  }
  
}

	function conSerPro_dataset_prodName1_beforeOpen(dropDown,dropDownDataset){
		dropDown.cache =false;
    	var mchtId = <%=StringUtil.filtrateSpecialCharater(request.getParameter("mchtId"))%>;
//     	alert("商户号： "+mchtId);
    	dropDownDataset.setParameter("mchtId",mchtId);
    	dropDownDataset.flushData(dropDownDataset.pageIndex)
	}
	
    //产品下拉出账户菜单 
	function  conSerPro_dataset_prodName1_onSelect(v,record){
		if(record!=null){
			var prodName = record.getValue("prodName");
			var prodId = record.getValue("prodId");
			conSerPro_dataset.setValue("prodName1",prodName);
			parent.setTabTitle(record.getValue("prodName"));
			conSerPro_dataset.setParameter("prodId",prodId);
			conSerAcc_dataset.setParameter("prodId",prodId);
			conSerAcc_dataset.setParameter("flag","yes");//选择产品的时候加载出账户和费率 
			conSerAcc_dataset.flushData(conSerAcc_dataset.pageIndex);
		}else{ 
			conSerPro_dataset.setValue("prodName1","");
			conSerPro_dataset.setParameter("prodId","");
			conSerAcc_dataset.setParameter("prodId","");
			conSerAcc_dataset.setParameter("flag","yes");
			parent.setTabTitle("请选择支付产品 ");
			conSerAcc_dataset.flushData(conSerAcc_dataset.pageIndex);
		}
	}
	 //保存按钮的检验 
	function btnAddSer_onClickCheck(){		
		var firstRecord = conSerAcc_dataset.getFirstRecord();//获取账号和费率配置第一行数据
		var prodName = conSerPro_dataset.getValue("prodName1");
		conSerPro_dataset.setParameter("conId","<%=StringUtil.filtrateSpecialCharater(request.getParameter("conId"))%>"); 
		var AllowModify =	parent.getAllowModify();//是否允许修改 
		conSerAcc_dataset.setParameter("AllowModify",AllowModify);
		if(prodName ==''){
			$.warn("请输入支付产品");
			return false;
		}
		// 遍历
		while (firstRecord) {
			var rateId = firstRecord.getValue("rateId");
				if (rateId =='') {
					$.warn("请输入费率配置信息");
					return false;
			}
			firstRecord = firstRecord.getNextRecord();
		}
		return true;
	}
	 
	 //保存提交
	function btnAddSer_postSubmit() {
		$.success("操作成功!", function() {
			conSerPro_dataset.setFieldReadOnly("prodName1",true);
			var prodId = conSerAcc_dataset.getParameter("prodId"); 
			parent.returnBack(prodId);
		});
	}
	
</script>
	
</snow:page>