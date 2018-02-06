<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="地区信息维护">
	<!-- 数据集定义 -->
	<snow:dataset id="citytree" path="com.ruimin.ifs.mng.dataset.CityTree"
		init="true" parameters="showvir=true"></snow:dataset>
	<snow:dataset id="citymanger"
		path="com.ruimin.ifs.mng.dataset.CityManager" init="false"
		submitMode="current"></snow:dataset>

	<!-- 查询 -->
	<snow:query id="querybtn" label="查询条件" dataset="citymanger"
		collapsible="false" fieldstr="qCtCode,qCtName,qupCtCode,qctFlg"
		border="false"></snow:query>

	<!-- 表单 -->
	<snow:grid dataset="citymanger" height="99%" label="地区信息" id="citylist"
		fitcolumns="true" fieldstr="ctCode,ctName,ctFlg,upCtCode,tlCtCode,opr"
		paginationbar="btAdd,btUpt,btnDelete"></snow:grid>

	<!-- 新增窗口 -->
	<snow:window id="win" closable="true" title="地区信息--新增" modal="true"
		buttons="btSave">
		<snow:form id="citywin" dataset="citymanger" label="" border="false"
			fieldstr="ctCode,ctName,ctFlg,upCtCode,tlCtCode"></snow:form>
		<br />
		<snow:button id="btSave" dataset="citymanger" hidden="true"></snow:button>
	</snow:window>

	<!-- 修改窗口 -->
	<snow:window id="winupdate" closable="true" title="地区信息--修改"
		modal="true" buttons="btUpdate">
		<snow:form id="citywinupd" dataset="citymanger" label=""
			border="false" fieldstr="ctCode,ctName,ctFlg,upCtCode,tlCtCode"></snow:form>
		<br />
		<snow:button id="btUpdate" dataset="citymanger" hidden="true"></snow:button>
	</snow:window>

		<!-- 详情窗口 -->
	<snow:window id="winDetail" closable="true" title="地区信息--详情"
		modal="true" >
		<snow:form id="citywinDetail" dataset="citymanger" label=""
			border="false" fieldstr="ctCode,ctName,ctFlg,upCtCode,tlCtCode"></snow:form>
		<br />
	</snow:window>
	<snow:button id="btnDeleteSub" dataset="citymanger" hidden="true"></snow:button>
	<snow:button id="btnShowDetail" dataset="citymanger" hidden="true"></snow:button>

	<script language="javascript">
	
	var isNum4 = /^\d{4}$/; 	// 	长度为四位的数字
	var isChar20 = /^\S{1,20}$/;//	最大长度20位 汉字		
	function citytree_dataset_afterScroll(ds,record){
		if(record) {
			citymanger_interface_dataset.setParameter('upCtCode',record.getValue("_id"));
			citymanger_interface_dataset_querybtn.click();
		}
	}
		
	function citylist_opr_onRefresh(record, fieldId, fieldValue){
		if(record){
			return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>"; 
		}else{
			return "&nbsp;";
		}
	}
	
	// 显示详情
	function openDetail(){
		citymanger_dataset.setReadOnly(true);
		window_winDetail.open();
	}
	
	// 查询框数据刷新
	function citymanger_interface_dataset_qupCtCode_beforeOpen(dropdown,dtst) {
		qupCtCode_DropDown.cache=false;
		return true;
	}
	
	//上级地区下拉框打开前事件
	function citymanger_dataset_upCtCode_beforeOpen(dropdown,dtst){
		upCtCode_DropDown.cache=false;
		return true;
	}
	
	//************上级地区暂用*****************
	function citymanger_dataset_upCtCode_onSelect(v,record) {
		var ctFlg = citymanger_dataset.getValue("ctFlg");
		if(ctFlg == null || ctFlg == ''){
			$.warn("提示：请先选择地区标识!");
			return false;
		}
		if(record != null){
			var upCtName = record.getValue("_text");
			var upCtId 	 = record.getValue("_id");
			var upCtflg  = record.getValue("_check");
		}else{
			citymanger_dataset.setValue("upCtCode",null);
			citymanger_dataset.setValue("upCtCodeName",null);
		}
		//如果地区表示为 2
		if('2'==ctFlg){
			if(upCtflg=='1'){
				citymanger_dataset.setValue("upCtCode",upCtId);
				citymanger_dataset.setValue("upCtCodeName",upCtName);
			}
			if(upCtflg=='2'){
				$.warn("提示：请正确选择上级地区,只能选择省级!");
			}
			if(upCtflg=='3'){
				$.warn("提示：请正确选择上级地区,只能选择省级!");
			}
		}
		if('3'==ctFlg){
			if(upCtflg=='1'){
				citymanger_dataset.setValue("upCtCode",upCtId);
				citymanger_dataset.setValue("upCtCodeName",upCtName);
			}
			if(upCtflg=='2'){
				citymanger_dataset.setValue("upCtCode",upCtId);
				citymanger_dataset.setValue("upCtCodeName",upCtName);
			}
			if(upCtflg=='3'){
				$.warn("提示：请正确选择上级地区,请选择市级,省级!");
			}
		}
	} 
	
	// 地区标识选择为省时，上级地区不能选择；
	// 地区标识选择为其他，上级地区必选；
	function citymanger_dataset_ctFlg_onSelect(v) {
		if(v=='1'){
			citymanger_dataset.setValue("upCtCode","");
			citymanger_dataset.setValue("upCtCodeName","");
			citymanger_dataset.setFieldRequired("upCtCode",false);
			citymanger_dataset.setFieldReadOnly("upCtCode",true);
		}else{
			citymanger_dataset.setFieldRequired("upCtCode",true);
			citymanger_dataset.setFieldReadOnly("upCtCode",false);
		}
	}
	
	function setFieldRequre(){
		if(citymanger_dataset.getValue("ctFlg")=='1'){
			citymanger_dataset.setFieldReadOnly("upCtCode",true);
			citymanger_dataset.setFieldRequired("upCtCode",false);
		}else if(citymanger_dataset.getValue("ctFlg")=='2'){
			citymanger_dataset.setFieldReadOnly("upCtCode",false);
			citymanger_dataset.setFieldRequired("upCtCode",true);
		}else{
			citymanger_dataset.setFieldReadOnly("upCtCode",false);
			citymanger_dataset.setFieldRequired("upCtCode",true);
		}
	}

	
	/***********************************新增地区信息**************************************/
	
	function btAdd_onClick(){
		citymanger_dataset.setReadOnly(false);
		citymanger_dataset.setFieldReadOnly("ctCode",false);
		citymanger_dataset.setFieldReadOnly("ctFlg",false);
		setFieldRequre();
		window_win.open();
	}
	
	function window_win_beforeClose(wmf){		
		citymanger_dataset.cancelRecord();
	}
	
	/**提交前检验数据格式*/	
	function btSave_onClickCheck() {
		var ctCode = citymanger_dataset.getValue("ctCode");
		var ctName = citymanger_dataset.getValue("ctName");
		var upCtCodeName = citymanger_dataset.getValue("upCtCodeName");
		var tlCtCode = citymanger_dataset.getValue("tlCtCode");
		// 地区编号
		if (!isNum4.test(ctCode)) {
			$.warn("提示：地区编号由长度为4位数字组成!");
			return false;
		}
		// 地区名称		
		if (!isChar20.test(ctName)) {
			$.warn("提示：地区名称由最大长度为20位字符组成!");
			return false;
		}
		// 上级地区
		if(ctName == upCtCodeName){
			$.warn("提示：上级地区不能和自身地区名称相同!");
			return false;
		}
		return true;
	}

	function btSave_postSubmit(btn){
		$.success("操作成功!", function() {
			window_win.close();
			citymanger_dataset.flushData(citymanger_dataset.pageIndex);
			citymanger_dataset.flushData(citymanger_dataset.pageIndex);
        });
	}
	

	/***********************************修改信息修改***************************************/	
	// 字段属性设置
	function openupdate(){
		citymanger_dataset.setReadOnly(false);
		citymanger_dataset.setFieldReadOnly("ctCode",true);
		citymanger_dataset.setFieldReadOnly("ctFlg",true);
		setFieldRequre();
		btUpdate.setHidden(false);
	}
	function btUpt_onClick(){	
		openupdate();		
		window_winupdate.open();
	}
	/**提交前检验数据格式*/	
	function btUpdate_onClickCheck() {
		var ctName = citymanger_dataset.getValue("ctName");
		var upCtCodeName = citymanger_dataset.getValue("upCtCodeName");
		var tlCtCode = citymanger_dataset.getValue("tlCtCode");
		
		var upCtCode = citymanger_dataset.getValue("upCtCode");
		citymanger_dataset.setParameter("id",upCtCode);
		// 地区名称		
		if (!isChar20.test(ctName)) {
			$.warn("提示：地区名称由最大长度为20位字符组成!");
			return false;
		}
		// 上级地区
		if(ctName == upCtCodeName){
			$.warn("提示：上级地区不能和自身地区名称相同!");
			return false;
		}
		return true;
	}
	
	function window_winupdate_beforeClose(wmf){
		citymanger_dataset.cancelRecord();
	}
	function btUpdate_postSubmit(btn){
		$.success("操作成功!", function() {
			window_winupdate.close();
			citymanger_dataset.flushData(citymanger_dataset.pageIndex);
			citytree_dataset.flushData(citytree_dataset.pageIndex);
        });
	}	
	
	
	/********************************* 地区删除 ***************************************/
	function btnDelete_onClick(){
		citymanger_dataset.setFieldRequired("upCtCode",false);
		var ctCodeD = citymanger_dataset.getValue("ctCode");
		var ctFlgD = citymanger_dataset.getValue("ctFlg");
		$.confirm("是否确认删除该地区?  注:一经删除无法恢复!", function() {
			citymanger_dataset.setParameter("ctCodeD",ctCodeD);
			citymanger_dataset.setParameter("ctFlgD",ctFlgD);
			btnDeleteSub.click();
        }, function() {
        	return false;
        });
	}
	function btnDeleteSub_postSubmit(btn){
		$.success("操作成功!", function() {
			citymanger_dataset.flushData(citymanger_dataset.pageIndex);
			citytree_dataset.flushData(citytree_dataset.pageIndex);
        });
	}
	
			
	/********************************* 文件导入 ***************************************/
    function btnImput_onClick(){
 	   $.open("submitWin", "文件导入", "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.FILE_CITY_LST%>",
 			   "450", "340", false, true, null, false, "关闭");
		}
	
    function submitWin_onButtonClick(i,win,framewin){
		if(i==0){//取消按钮
			win.close();
			citytree_dataset.flushData(citytree_dataset.pageIndex);
		}else{
			framewin.excuteImport();
		importFileCallBack();
			}
		}

		//导入完成，刷新页面
	function importFileCallBack(ret) {
		if (ret) {
			citymanger_dataset.flushData(citymanger_dataset.pageIndex);
			citytree_dataset.flushData(citytree_dataset.pageIndex);	
		}
	}
	
	
	</script>
</snow:page>
