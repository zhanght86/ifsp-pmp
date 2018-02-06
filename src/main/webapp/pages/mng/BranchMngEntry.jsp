<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="机构信息维护">
	<snow:dataset id="orgtree" path="com.ruimin.ifs.mng.dataset.OrgTree"
		init="false" parameters="showvir=true"></snow:dataset>
	<snow:dataset id="orgmanger"
		path="com.ruimin.ifs.mng.dataset.OrgManager" init="true"
		submitMode="current"></snow:dataset>

	<snow:query id="querybtn" label="查询条件" dataset="orgmanger"
		collapsible="false" fieldstr="qbrno,qbrname,blnUpBrcode,qbrclass"
		border="false"></snow:query>
	<snow:grid dataset="orgmanger" height="99%" label="机构信息" id="orglist"
		fitcolumns="true"
		fieldstr="brno[100],brname,brclass[180],blnUpBrcode,status,areaCd[180],opr[100]"
		paginationbar="btAdd,btUpt,btnDelete"></snow:grid>

	<snow:window id="win" closable="true" width="800" title="机构新增"
		modal="true" buttons="btSave">
		<snow:form id="orgwin" dataset="orgmanger" label="基本信息" border="true"
			fieldstr="brno,brname,brclass,blnUpBrcode,areaCd"></snow:form>
		<br />
		<snow:form id="orgwin" dataset="orgmanger" label="联系人信息" border="true"
			fieldstr="linkman,teleno,postno,address"></snow:form>
		<br />
		<snow:button id="btSave" dataset="orgmanger" hidden="true"></snow:button>
	</snow:window>

	<snow:window id="winupdate" closable="true" width="800" title="机构修改"
		modal="true" buttons="btUpdate">
		<snow:form id="orgwinupd" dataset="orgmanger" label="基本信息" border="true"
			fieldstr="brno,brname,brclass,blnUpBrcode,areaCd"></snow:form>
		<br />
		<snow:form id="orgwinupd" dataset="orgmanger" label="联系人信息" border="true"
			fieldstr="linkman,teleno,postno,address"></snow:form>
		<br />
		<snow:button id="btUpdate" dataset="orgmanger" hidden="true"></snow:button>
	</snow:window>
	<snow:window id="winupdate1" closable="true" width="800" title="机构详情"
		modal="true" buttons="btDetail">
		<snow:form id="orgwinupd" dataset="orgmanger" label="基本信息" border="true"
			fieldstr="brno,brname,brclass,blnUpBrcode,areaCd"></snow:form>
		<br />
		<snow:form id="orgwinupd" dataset="orgmanger" label="联系人信息" border="true"
			fieldstr="linkman,teleno,postno,address"></snow:form>
		<br />
		<snow:button id="btDetail" dataset="orgmanger" hidden="true"></snow:button>
	</snow:window>
    <!-- 删除按钮 -->
    <snow:button id="btnDeleteSub" dataset="orgmanger" hidden="true"></snow:button>
    
	
	<script language="javascript">
	function orgtree_dataset_afterScroll(ds,record){
		if(record) {
			orgmanger_interface_dataset.setParameter('upbrcode',record.getValue("_id"));
			orgmanger_interface_dataset_querybtn.click();
		}
	}
	// 上级机构下拉框打开前事件刷新
	function orgmanger_interface_dataset_blnUpBrcode_beforeOpen(dropDown,dropDownDataset) {
		blnUpBrcode_DropDown.cache=false;
		return true;
	}
	//查询上级机构下拉框打开前事件刷新
	function orgmanger_interface_dataset_qblnUpBrcode_beforeOpen(dropdown,dtst){
		qblnUpBrcode_DropDown.cache=false;
		return true;
	} 
	//设置该字段在新增 修改时是否是必须输入
	function setFieldRequre(){
		if(orgmanger_dataset.getValue("brclass")=='1'){
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",true);
			orgmanger_dataset.setFieldRequired("blnUpBrcode",false);
		}else {
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",false);
			orgmanger_dataset.setFieldRequired("blnUpBrcode",true);
		}
	}
	function btAdd_onClick(){
		setFieldRequre();
		orgmanger_dataset.setFieldReadOnly("brno",false);
		//orgmanger_dataset.setFieldRequired("cupBrhId",true);
		orgmanger_dataset.setReadOnly(false);
		window_win.open();
	}
	
	function window_win_beforeClose(wmf){
		setFieldRequre();
		/* orgmanger_dataset.setFieldRequired("blnUpBrcode",true);
		orgmanger_dataset.setFieldReadOnly("blnUpBrcode",false); */
		orgmanger_dataset.cancelRecord();
	}
	
	function orglist_opr_onRefresh(record, fieldId, fieldValue){
		if(record){
			return "<span style='width:100%;text-align:center' class='fa fa-info'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
		}else{
			return "&nbsp;";
		}
	}
	
	function btSave_onClickCheck(){
		var rel=/^\d{1,20}$/;
		var isMobile =/^1[3|4|5|7|8]\d{9}$/;
		var reg=/^\d{6}$/;
		var rx = /^(0\d{2,3}-?)?\d{7,8}$/;
		var brno = orgmanger_dataset.getValue("brno");
		var brname = orgmanger_dataset.getValue("brname");
		var linkman = orgmanger_dataset.getValue("linkman");
		var teleno = orgmanger_dataset.getValue("teleno");
		var postno = orgmanger_dataset.getValue("postno");
		var address = orgmanger_dataset.getValue("address");
		if(!rel.test(brno)){
			$.warn("机构编号为1—20位的数字");
			return false;
		}
		if(brname.length>20){
			$.warn("机构名称最长为20位");
			return false;
		}
		if(linkman.length>6){
			$.warn("联系人名称最长为6位");
			return false;
		}
		if( !(rx.test(teleno) || isMobile.test(teleno)) && teleno != ""){
		    $.warn("联系电话的格式不对");
		     return false;
	    }
		if(postno != "" && !reg.test(postno)){
		    $.warn("邮政编码的格式不对");
		     return false;
	    }
		if(address.length>20){
			$.warn("机构地址最长为20位");
			return false;
		}
		return true;
	}
	function btSave_postSubmit(btn){
		$.success("操作成功!", function() {
			window_win.close();
			orgtree_dataset.flushData(orgtree_dataset.pageIndex);
        });
	}
	function btUpdate_onClickCheck(){
		var rel=/^\d{1,20}$/;
		var isMobile =/^1[3|4|5|7|8]\d{9}$/;
		var reg=/^\d{6}$/;
		var rx = /^(0\d{2,3}-?)?\d{7,8}$/;
		var brno = orgmanger_dataset.getValue("brno");
		var brname = orgmanger_dataset.getValue("brname");
		var linkman = orgmanger_dataset.getValue("linkman");
		var teleno = orgmanger_dataset.getValue("teleno");
		var postno = orgmanger_dataset.getValue("postno");
		var address = orgmanger_dataset.getValue("address");
	    if(!rel.test(brno)){
			$.warn("机构编号为1—20位的数字");
			return false;
		} 
		if(address.length>20){
			$.warn("机构地址最长为20位");
			return false;
		}
		if(brname.length>20){
			$.warn("机构名称最长为20位");
			return false;
		}
		if(linkman.length>6){
			$.warn("联系人名称最长为6位");
			return false;
		}
		if( !(rx.test(teleno) || isMobile.test(teleno)) && teleno != ""){
		    $.warn("联系电话的格式不对");
		     return false;
	    }
		if(postno!="" && !reg.test(postno)){
		    $.warn("邮政编码的格式不对");
		     return false;
	    }
		return true;
	}
	function btUpt_onClick(){
		orgmanger_dataset.setReadOnly(false);
		btUpdate.setHidden(false);
		orgmanger_dataset.setFieldReadOnly("brno",true);
		//orgmanger_dataset.setFieldRequired("cupBrhId",true);
		var brclass = orgmanger_dataset.getValue("brclass");
		orgmanger_dataset.setParameter("brclass",brclass);
		setFieldRequre();
		/* if(brclass=="1"){
			orgmanger_dataset.setFieldRequired("blnUpBrcode",false);
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",true);

		} */
		window_winupdate.open();
	}
	/* function window_winupdate_beforeOpen(){
   	 var dataState = orgmanger_dataset.getValue("status");
   	 if(dataState=='99'){
   		 $.warn("该条数据状态被设置为停用,不可以修改");
   		 return false;
   	 }
   	 return true;
    } */
	function openupdate(){
		orgmanger_dataset.setReadOnly(false);
		btUpdate.setHidden(false);
		var brclass = orgmanger_dataset.getValue("brclass");
		orgmanger_dataset.setParameter("brclass",brclass);
		setFieldRequre();
		/* if(brclass=="1"){
			orgmanger_dataset.setFieldRequired("blnUpBrcode",false);
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",true);

		} */
		window_winupdate.open();
	}
	
	function window_winupdate_beforeClose(wmf){
		setFieldRequre();
		/* orgmanger_dataset.setFieldRequired("blnUpBrcode",true);
		orgmanger_dataset.setFieldReadOnly("blnUpBrcode",false); */
		orgmanger_dataset.cancelRecord();
	}
	function btUpdate_postSubmit(btn){
		$.success("操作成功!", function() {
			window_winupdate.close();
			orgtree_dataset.flushData(orgtree_dataset.pageIndex);
        });
	}
	
	/* function btStatus_onClickCheck(button,commit) {
		var status = orgmanger_dataset.getValue("status");
		if(status == '99'){
			$.confirm("确认将该机构设置为启用?", function() {
				orgmanger_dataset.setParameter("setstatus", "00");
				commit();
			});
		} else {
			$.confirm("确认将该机构设置为停用?", function() {
				orgmanger_dataset.setParameter("setstatus", "99");
				commit();
			});
		}
	}
	
	function btStatus_postSubmit(btn){
		$.success("操作成功!", function() {
			orgtree_dataset.flushData(orgtree_dataset.pageIndex);
        });
	} */
	
	
	
	function openDetail(){
		orgmanger_dataset.setReadOnly(true);
		btDetail.setHidden(true);
		window_winupdate1.open();
	}
	
	function orgmanger_dataset_brclass_onSelect(v) {
		if(v=='1'){
			orgmanger_dataset.setValue("blnUpBrcode",null);
			orgmanger_dataset.setValue("blnUpBrcodeName",null);
			orgmanger_dataset.setFieldRequired("blnUpBrcode",false);
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",true);			
		}else{
			orgmanger_dataset.setFieldRequired("blnUpBrcode",true);
			orgmanger_dataset.setFieldReadOnly("blnUpBrcode",false);
			orgmanger_dataset.setValue("blnUpBrcode",null);
			orgmanger_dataset.setValue("blnUpBrcodeName",null);
		}
	}
	function orgmanger_dataset_brclass_beforeOpen(dropdown,dtst){
		//$(id).val(vale)
		orgmanger_dataset.setValue("blnUpBrcode",null);
	}
	//上级机构下拉框打开前事件
	function orgmanger_dataset_blnUpBrcode_beforeOpen(dropdown,dtst){
		//dropdown.cache=false;
		var brClass = orgmanger_dataset.getValue("brclass");
		if(brClass == null || brClass ==''){
			$.warn("请先选择机构类型");
			return false;
		}else{
			dtst.setParameter("currentBrClass",brClass);
			blnUpBrcode_DropDown.cache=false;
			//dtst.flushData(dtst.pageIndex);
			return true;
		}
	}
	
	
	var iscallback = false;
	function btImport_onClick(){
		iscallback = false;
		$.open("importFile", '文件导入', "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_ORG_LST%>", "750", "280", false, true, null, false,"关闭");
	}
	function importFile_onButtonClick(i,win,framewin){
		if(i==0){//取消按钮
			win.close();
			orgtree_dataset.flushData(orgtree_dataset.pageIndeframewinx);
		}else{
				framewin.excuteImport();
				importFileCallBack();
			}
		}
	
		function importFileCallBack(){
			iscallback = true;
			orgtree_dataset.flushData(orgtree_dataset.pageIndex);			
		}
	
	
	function btnDelete_onClick(){
		setFieldRequre();
		//orgmanger_dataset.setFieldRequired("cupBrhId",false);
		var brcode = orgmanger_dataset.getValue("brcode");
		$.confirm("是否确认删除该机构?  注:一经删除无法恢复!", function() {
			orgmanger_dataset.setParameter("brcode",brcode);
			btnDeleteSub.click();
        }, function() {
        	return false;
        });
	}
	function btnDeleteSub_postSubmit(btn){
		$.success("操作成功!", function() {
			window_winupdate.close();
			orgtree_dataset.flushData(orgtree_dataset.pageIndex);
        });
	}
	
</script>
</snow:page>
