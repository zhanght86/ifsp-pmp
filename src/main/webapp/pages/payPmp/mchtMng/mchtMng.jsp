<%@page import="com.ruimin.ifs.pmp.pubTool.util.SysParamUtil"%>
<%@page import="com.ruimin.ifs.util.SnowConstant"%>
<%@page import="com.ruimin.ifs.pmp.pubTool.common.constants.ImportPicConstants"%>
<%@ page import="java.util.*"%>
<%@page import="com.ruimin.ifs.framework.session.SessionUtil"%>
<%@page import="com.ruimin.ifs.pmp.mchtMng.comp.MchtMngAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="商户信息管理">
	<!-- 数据集加载 -->
		<!-- 商户信息数据集  -->
		<snow:dataset id="mchtInfo" path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMngInit" init="true" submitMode="current" ></snow:dataset>
		<!-- 审核信息记录数据集 -->
		<snow:dataset path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" id="checkNewRecordInfo" submitMode="current" init="false"></snow:dataset>
	<!-- 主页面元素 -->
		<!-- 查询栏 -->
		<snow:query dataset="mchtInfo" label="查询条件" fieldstr="qmchtId,qmchtSimpleName,qmchtMngSel,qmchtType,qmchtStat,qmchtOrgId,qChlMchtNo"></snow:query>		
		<!-- 主表单 -->
		<snow:grid id="gridMain" dataset="mchtInfo" label="商户信息管理" fieldstr="mchtId,mchtSimpleName,mchtType,chlSysNo,mchtStat,mchtOrg,mchtMngNo,regDate,opr"
		height = "99%" paginationbar="btnAdd,btnUpd,btnFrz,btnOff,btnAddRecord,btImport,btnNote"></snow:grid>

	<!-- 弹出窗口 -->
			<!-- 发送短信 -->
		<snow:window id="winDetailNote" title="发送短信"  modal="true" closable="true" buttons="btnNoteRecordSub">
			<!-- No1 基本信息模块 -->
			<snow:form id="mchtInfoDetailNote" label="发送确认" dataset="mchtInfo" fieldstr="mchtId,mchtSimpleName,mchtType,chlSysNo,mchtStat,mchtOrg,mchtMngNo,regDate,mchtPhone"></snow:form>
					<!-- No4 加载提交按钮 -->
			<snow:button id="btnNoteRecordSub" dataset="mchtInfo" hidden="true"></snow:button>
		</snow:window>		

		
		<snow:button id="btnOffSub" dataset="mchtInfo" hidden="true"></snow:button>
		<snow:button id="btnFrzSub" dataset="mchtInfo" hidden="true"></snow:button>
	<!-- 商户状体处于待审核，及其拒绝时，超链接窗口 -->
    <snow:window id="windowMchtStat" title="审核相关信息" modal="true" closable="true"
		buttons="" width="900" height="700"> 
		<p style="font" >商户基本信息</p>
		<snow:form id="formMchtInfo" dataset="mchtInfo" label="商户基本信息"
			border="false" collapsible="true" 
			fieldstr="mchtId,mchtOrg,mchtSimpleName,mchtType,mchtStat,auditId">
		</snow:form> 	
		<br />
		<p style="font" >审核流程信息</p>
		<snow:grid id="gridCheckNewRecordInfo" dataset="checkNewRecordInfo"  fitcolumns="true" fieldstr="stepNo[95],auditState[115],roleName[115],auditDatetIme[200],auditView[320]" height="400" >
		</snow:grid>
	</snow:window> 
		


	<script type="text/javascript">
/****************************************公有声明******************************************/
 	/*****************对商户状态增加超链接，当商户信息处于待审核或审核被拒绝状态时，点击状态字段，弹出查看审核记录（包括商户基本信息、审核记录列表（按步骤正序排序））*****************/
 	function gridMain_mchtStat_onRefresh(record, fieldId, fieldValue) {
	    //得到审核信息编号
	    var auditId=mchtInfo_dataset.getValue("auditId");
	    if(fieldValue == '03'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">添加待审核 </a></span>";	
	    }
	    if(fieldValue == '04'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改待审核 </a></span>";	
	    }
	    if(fieldValue == '05'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">冻结待审核 </a></span>";	
	    }
	    if(fieldValue == '06'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">恢复待审核 </a></span>";	
	    }
	    if(fieldValue == '07'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">注销待审核 </a></span>";	
	    }
	    if(fieldValue == '08'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增被拒绝 </a></span>";	
	    }
	    if(fieldValue == '09'){
	    	return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改被拒绝  </a></span>";	
	    }
	    if(fieldValue == '10'){
	    	return '添加待提审'; 
	    }
	    if(fieldValue == '11'){
	    	return '修改待提审'; 
	    }
	    if(fieldValue == '97'){
	    	return '待补录'; 
	    }
	    if(fieldValue == '98'){
	    	return '入网中'; 
	    }
	    if(fieldValue == '00'){
	    	return '正常';	
	    }
	    if(fieldValue == '01'){
	    	return '冻结';
	    }
	    if(fieldValue == '02'){
	    	return '注销';	
	    }
 	} 
 	 //点击状态按钮显示对应信息
    function onClickWin(auditId){
    	mchtInfo_dataset.setFieldReadOnly("mchtId", true);
        mchtInfo_dataset.setFieldReadOnly("mchtOrg", true);
        mchtInfo_dataset.setFieldReadOnly("mchtSimpleName", true);
        mchtInfo_dataset.setFieldReadOnly("mchtType", true);
        mchtInfo_dataset.setFieldReadOnly("mchtStat", true);
        mchtInfo_dataset.setFieldReadOnly("auditId", true);
        //根据审核信息编号，查询审核记录表
        var auditId = mchtInfo_dataset.getValue("auditId")
        checkNewRecordInfo_dataset.setParameter("auditId",auditId);
        checkNewRecordInfo_dataset.flushData(checkNewRecordInfo_dataset.pageIndex);
    	window_windowMchtStat.open();
    }
    //关闭窗体
	function window_windowMchtStat_afterClose(){
		mchtInfo_dataset.setFieldReadOnly("mchtId", false);
        mchtInfo_dataset.setFieldReadOnly("mchtOrg", false);
        mchtInfo_dataset.setFieldReadOnly("mchtSimpleName", false);
        mchtInfo_dataset.setFieldReadOnly("mchtName", false);
        mchtInfo_dataset.setFieldReadOnly("mchtType", false);
        mchtInfo_dataset.setFieldReadOnly("mchtStat", false);
	}
 	
	
	/****************************************发送短信******************************************/
	/**发送短信*/
	function btnNote_onClick(){
		//全部字段设置为-只读
		mchtInfo_dataset.setReadOnly(true);
		//打开窗口
		window_winDetailNote.open();
	}

	function window_winDetailNote_beforeOpen(win){
		var mchtStat = mchtInfo_dataset.getValue("mchtStat");//联系电话	
		var mchtPhone = mchtInfo_dataset.getValue("mchtPhone");//联系电话
		if(mchtStat!='00'){
			$.warn("商户状态为正常的才可以发生短信！");
			return false;
		}
		if(mchtPhone==''||mchtPhone==null){
			$.warn("请为商户配置手机号码才可以发生短信！");
			return false;
		}
	}
	/**提交*/
	function btnNoteRecordSub_postSubmit(){
		var mchtPhone = mchtInfo_dataset.getValue("mchtPhone");//联系电话	
	
		$.success("短信已发送至"+mchtPhone+"请注意查收！",function(){
			window_winDetailNote.close();
		mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);	
		});
	}
	function window_winDetailNote_afterClose(){
		mchtInfo_dataset.setReadOnly(false);
	}
	
/****************************************详情******************************************/
	/**详情显示*/
	function gridMain_opr_onRefresh(record) {
	
 		if (record) {
 			var mchtId=record.getValue('mchtId');
 			return "<span style='width:100%;text-align:center' class='fa fa-search'><a href=\"JavaScript:onClickDetail("+mchtId+")\">详情</a></span>";
 		}
	}
	
	/**打开详情窗口*/
	function onClickDetail(mchtId){
		$.open("btnDetail", '商户信息详情', "/pages/payPmp/mchtMng/mchtDetail.jsp?qmchtId="+mchtId, "1060", "1000", false, true, null, false,"关闭");
	}
	
	function btnDetail_onButtonClick(){
		btnDetail.close();
    }



	
/****************************************冻结/解冻******************************************/
 	/**数据补完并进行提示*/
	function btnFrz_onClickCheck(){
		/*****************设置特殊字段[下拉]*****************/
//		mchtInfo_dataset.setValue("mchtOrgSel",mchtInfo_dataset.getValue("mchtOrg"));//所属机构
//		mchtInfo_dataset.setValue("mchtAreaSel",mchtInfo_dataset.getValue("mchtArea"));//所属地区
//		mchtInfo_dataset.setValue("mchtMngSel",mchtInfo_dataset.getValue("mchtMng"));//上级商户
		var mchtStat = mchtInfo_dataset.getValue("mchtStat");
		var chlSysNo=mchtInfo_dataset.getValue("chlSysNo");
		// modify by lengjingyu 二维码内管支持冻结全部商户，包括内管进件和展业进件的商户   jira-1989
		if(mchtStat == '10' || mchtStat == '11'){
			$.warn("代理商未提审，不允许冻结/解冻！");
		    return false; 
		} 
		// modify end
		if(mchtStat == '03' || mchtStat == '04' || mchtStat == '05' || mchtStat == '06' || mchtStat == '07' || mchtStat == '97'){
			$.warn("商户处于待审核状态或待补录，不允许冻结/解冻！");
		    return false; 
		}
		var msg = ""
		if(mchtStat == "02"){
			$.warn("该商户已被注销！");
			return false;
		}
		if(mchtStat == "01"){
			msg = "是否要【解冻】商户?";
		}else{
			msg = "是否要【冻结】商户?";
		}
		$.confirm(msg, function() {
			btnFrzSub.click();
	       }, function() {
	        return false;
	       });
	}
 
 	/**操作生效*/
	function btnFrzSub_postSubmit(){
		$.success("操作成功！",function(){
			mchtInfo_dataset.clearData();	
			mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);		
		});		
	}
	
/****************************************注销******************************************/
	/**数据补完并进行提示*/
	function btnOff_onClickCheck(){
		/*****************设置特殊字段[下拉]*****************/
//		mchtInfo_dataset.setValue("mchtOrgSel",mchtInfo_dataset.getValue("mchtOrg"));//所属机构
//		mchtInfo_dataset.setValue("mchtAreaSel",mchtInfo_dataset.getValue("mchtArea"));//所属地区
//		mchtInfo_dataset.setValue("mchtMngSel",mchtInfo_dataset.getValue("mchtMng"));//上级商户
		var mchtStat = mchtInfo_dataset.getValue("mchtStat");
		var chlSysNo=mchtInfo_dataset.getValue("chlSysNo");
		if(chlSysNo == 'CS00001'){
			$.warn("进件商户无法注销！！");
			return false;
		}
		//新加注销条件限制
		if(mchtStat == '03' || mchtStat == '04' || mchtStat == '05' || mchtStat == '06' || mchtStat == '07'|| mchtStat == '08'|| mchtStat == '97'|| mchtStat == '98'){
			$.warn("商户处于待审核状态或待补录，不允许注销！");
		    return false; 
		}
		/* if(mchtStat == '10' || mchtStat == '11'){
			$.warn("代理商未提审，不允许注销！");
		    return false; 
		} */
		var msg = ""
			if(mchtStat == "02"){
				$.warn("该商户已被注销！");
				return false;
			}else{
				msg = "是否要【注销】商户?";
				mchtStat = "02";
			}
		
		$.confirm(msg, function() {
				btnOffSub.click();
	        }, function() {
	        	return false;
	        });
		}
 
 	/**操作生效*/
	function btnOffSub_postSubmit(){
		$.success("操作成功！",function(){
			mchtInfo_dataset.clearData();	
			mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);		
		});		
	}
 	/**商户批量导入*/
	function btImport_onClick(){
		iscallback = false;
		$.open("importFile", '文件导入', "/pages/payPmp/pubTool/importXls.jsp?type=<%=SnowConstant.PBS_MCHT_BASE_INFO_TMP%>", "750", "280", false, true, null, false,"关闭");
	}
	function importFile_onButtonClick(i,win,framewin){
		if(i==0){//取消按钮
			win.close();
			mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);	
		}else{
				framewin.excuteImport();
				//importFileCallBack();
				mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);	
				iscallback = true;
			}
		}
	//------------------------------------打开修改/补录窗口--------------------------------------------------------------
	function btnUpd_onClick(){
		var mchtId = mchtInfo_dataset.getValue("mchtId");//商户编号
		 if(mchtId == null || mchtId== ''){
			 $.warn("请先选择一条要修改的活动信息");
			 return;
		 }
			var mchtStat=mchtInfo_dataset.getValue("mchtStat");
			if(mchtStat == '03' || mchtStat == '04' || mchtStat == '05' || mchtStat == '06' || mchtStat == '07' || mchtStat == '97'||mchtStat == '10' ||mchtStat == '11'){
				$.warn("商户状态不允许修改！");
			    return false; 
			}
		    
			if(mchtInfo_dataset.getValue("mchtStat") == "01"){
				$.warn("该商户已被冻结！");
				return false;	
			}
			if(mchtInfo_dataset.getValue("mchtStat") == "02"){
				$.warn("该商户已被注销！");
				return false;	
			}		
		$.open("Upd", '商户信息修改', "/pages/payPmp/mchtMng/mchtUpdRec.jsp?qmchtId="+mchtId, "1060", "1000", false, true, null, false,"提交");
	}
	function btnAddRecord_onClick(){
		var mchtId = mchtInfo_dataset.getValue("mchtId");//商户编号
		 if(mchtId == null || mchtId== ''){
			 $.warn("请先选择一条要补录的信息");
			 return;
		 }
		var mchtStat=mchtInfo_dataset.getValue("mchtStat");
		if(mchtStat == '03' || mchtStat == '04' || mchtStat == '05' || mchtStat == '06' || mchtStat == '07'|| mchtStat == '00'|| mchtStat == '10'||mchtStat =='11'){
			$.warn("商户处于不是待补录，不允许补录！");
		    return false; 
		}
		// modify by lengjingyu 20180201 已冻结商户不允许补录   没有jira
		if(mchtInfo_dataset.getValue("mchtStat") == "01"){
			$.warn("该商户已被冻结！");
			return false;	
		}
		if(mchtInfo_dataset.getValue("mchtStat") == "02"){
			$.warn("该商户已被注销！");
			return false;	
		}	
		// modify end
		$.open("Upd", '商户信息补录', "/pages/payPmp/mchtMng/mchtUpdRec.jsp?qmchtId="+mchtId, "1060", "1000", false, true, null, false,"提交");
	}
	function Upd_onButtonClick(i, win, framewin) {
		framewin.doSave();
	}
	function saveMngUpReCallback() {
		Upd.close();
		mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);
	}
	/**打开新增窗口*/
	function btnAdd_onClick(){
		var mchtId = mchtInfo_dataset.getValue("mchtId");//商户编号	
		 $.open("btnAdd", "商户信息新增", "/pages/payPmp/mchtMng/mchtMngAdd.jsp",
   			   "1060", "1000", false, true, null, false, "提交");
	}
	function btnAdd_onButtonClick(i, win, framewin) {
		framewin.doSave();
	}
	function saveMngAddReCallback() {
		btnAdd.close();
		mchtInfo_dataset.flushData(mchtInfo_dataset.pageIndex);
	}
	</script>
</snow:page>