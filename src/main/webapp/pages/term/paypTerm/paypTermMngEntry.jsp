<%@page import="com.ruimin.ifs.framework.core.SessionUserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="终端信息维护">
 	<!--终端信息管理数据集-->
	<snow:dataset id="paypTermInf"  path="com.ruimin.ifs.term.dataset.PaypTermInf" init="true" submitMode="current" ></snow:dataset>
    <!--设备库存信息数据集-->
	<snow:dataset id="paypMachInf" path="com.ruimin.ifs.term.dataset.PaypMachInf" init="false"  submitMode="current" ></snow:dataset>
	<!-- 产品信息数据集 -->
	<snow:dataset id="mchtProdInf"  path="com.ruimin.ifs.term.dataset.MchtProdInf" init="false" submitMode="current" ></snow:dataset> 
	<!-- 商户基本信息管理数据集 -->
	<snow:dataset id="mcht3Info"  path="com.ruimin.ifs.pmp.mchtMng.dataset.mchtMng3" init="false" submitMode="current" ></snow:dataset> 
	<!-- 审核信息记录数据集 -->
	<snow:dataset id="checkNewRecordInfo"  path="com.ruimin.ifs.pmp.platMng.dataset.auditNewRecordInfo" submitMode="current" init="false"></snow:dataset>
    
	<!-----------------查询条件 ----------------->
	 <snow:query label="查询条件" id="queryId" collapsible="false" dataset="paypTermInf" fieldstr="mchtId,qmchtName,qtermId,qtermStat,qtermType,qmachInst" border="false"></snow:query>
	<!----------------- 主界面显示 ----------------->
    <snow:grid dataset="paypTermInf" id="gridId1" label="查询列表" fitcolumns="true" height="99%" 
    paginationbar="btnAdd,btnMod,btStatus,btStatus2,batDownLoad" fieldstr="termId,mchtId,secMerId,termType,mchtSimpleName,mchtOrg,termStat,bindStat,opr,count" ></snow:grid>

    <!----------------- 新增 ----------------->
     <snow:window id="windowAddId" title="终端信息新增" modal="true" closable="true" >
     <!-- 新增时不显示parmDownFlag(是否参数下载),isFlag(是否ic卡支持) 两个字段 -->
        <snow:form id="formAddId" dataset="paypTermInf"  border="true" label="基本信息"
         fieldstr="termId,qmchtId,mchtName,machInstName,termType,termInstAdrr,bindTelNo,bindTelNo1,mchtNoFlag" collapsible="false" colnum="3"></snow:form>
	     <!-- 选择子商户 -->
	     <snow:form id="selectSubMcht" dataset="paypTermInf" border="true" 
	     fieldstr="qmchtNo" collapsible="false"></snow:form>
	  	 <!--终端功能div  -->
	     <div class="l-form"  style="width:100%" id="iconDiv" >
	        <!--********************动态加载终端功能********************-->
	     </div>
	    <div id="btSelect" style="display:none">
			<snow:button id="btnSelectAll1" desc="全选" hidden="false"></snow:button>&nbsp;
			<snow:button id="btnUnSelectAll1" desc="全不选" hidden="false"></snow:button>&nbsp;
		</div>
		  <snow:button id="btnSave1" dataset="paypTermInf" hidden="false"></snow:button>
    </snow:window>
    
      <!-----------------修改 ----------------->
    <snow:window id="windowUpdateId" title="终端信息详情" modal="true"
		closable="true" show="false">
		<!-- 终端基本信息 -->
		<snow:form id="formUpdateId" dataset="paypTermInf" border="false"
			label="终端信息--> 详情 --> 参数详情"
			fieldstr="termId,mchtId,secMerId,mchtName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,parmDownFlag,icFlag"
			collapsible="false" colnum="4"></snow:form>
		<!-- 终端权限信息 -->
		 <div class="l-form"  style="width:100%" id="iconDiv4" >
            <!--********************动态加载终端功能********************-->
         </div>
		<div id="btSelectUpdate" style="display:none">
            <snow:button id="btnSelectAllUpdate" desc="全选" hidden="false"></snow:button>&nbsp;
            <snow:button id="btnUnSelectAllUpdate" desc="全不选" hidden="false"></snow:button>&nbsp;
        </div>
		<snow:button id="btnUpdateSave" dataset="paypTermInf" hidden="false"></snow:button>
	</snow:window>
	
	
    <!-----------------详情 ----------------->
    <!-- 详情窗口1 -->
    <snow:window id="windowDetailId1" title="终端信息详情" modal="true"
		closable="true" show="false">
		<!-- 终端基本信息 -->
		<snow:form id="formDetailId" dataset="paypTermInf" border="false"
			label="终端信息--> 详情 --> 参数详情"
			fieldstr="termId,mchtId,mchtName,secMerId,mchtSimpleName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,parmDownFlag,icFlag,icParmVer,keyVer,lastUpdTlr,lastUpdDateTime"
			collapsible="false" colnum="4"></snow:form>
	    <div class="l-form"  style="width:100%" id="iconDiv2" >
            <!--********************动态加载终端功能********************-->
            
         </div>
     </snow:window>
     <!-- 详情窗口1 -->    
   	<snow:window id="windowDetailId2" title="终端信息详情" modal="true" closable="true" show="false">
		<!-- 终端基本信息 -->
		<snow:form id="formDetailId" dataset="paypTermInf" border="false"
			label="终端信息--> 详情 --> 参数详情"
		      fieldstr="termId,mchtId,mchtName,secMerId,mchtSimpleName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,parmDownFlag,icFlag,icParmVer,keyVer,lastUpdTlr,lastUpdDateTime"
			collapsible="false" colnum="4"></snow:form>
		<!-- 终端权限信息 -->
		
	   <div class="l-form" style="width:100%" id="iconDiv3" >
            <!--********************动态加载终端功能********************-->
        </div>

		<!-- 终端绑定的设备库存信息  根据情况是否显示 -->
		<snow:form id="formDetailId3" dataset="paypMachInf" border="true"
			label="设备信息"
			fieldstr="machId,machType,machNo,confNo,machVersion,machStat,batchNo,machInst,propertyType,propertyInst,companyName"
			collapsible="false" colnum="4"></snow:form>

	</snow:window>
	
	
  <!-- 终端信息状态处于待审核，及其拒绝时，超链接显示信息的窗口 -->
    <snow:window id="windowTermStat" title="终端审核相关信息" modal="true" closable="true"
		buttons="" width="850" height="550"> 
		<snow:form id="formPartDetailId" dataset="paypTermInf" label="终端信息基本信息" border="true" 
			fieldstr="termId,mchtId,mchtName,machInst,termType,termInstAdrr,bindTelNo,bindTelNo1,auditId">
		</snow:form> 	
		<br />
		<p style="font" >审核流程信息</p>
		<snow:grid id="gridCheckNewRecordInfo" dataset="checkNewRecordInfo" paginationbar=""  fieldstr="stepNo[90],auditState[100],roleName[110],auditDatetIme[130],auditView[270]" height="400" >
		</snow:grid>
	</snow:window> 
	<!-- 显示可以绑定的终端信息 如果未绑定设备，点击绑定按钮时弹出的窗口-->
   	<snow:window id="windowNoBingTermId"  title="可绑定的终端信息"  closable="true">
	 	<snow:grid dataset="paypMachInf"  width="1000"  height="80%" label="查询列表" id="paypMachInfList" fitcolumns="true" 
		fieldstr="machId,batchNo,propertyType,machType,machInst,machStat,companyName,opr2"
	      ></snow:grid>
	</snow:window>
	
    <!-- 绑定和解绑按钮-->
    <snow:button id="btnBingMachId" dataset="paypTermInf"  hidden="true"></snow:button>
    <snow:button id="btnUnBingMachId" dataset="paypTermInf"  hidden="true"></snow:button>
    <snow:button id="btnUpdateTermStatu" dataset="paypTermInf"  hidden="true"></snow:button>
    <snow:button id="btnKey" dataset="paypTermInf"  hidden="true"></snow:button>
	<iframe name="dfrm" id="dfrm" height="0" width="0" src="" style="display:none"></iframe>
<script>
//--------------------页面刷新显示时改变表格中某些字段的显示-------------------------------------
	//主界面表格操作栏刷新显示之前
	function gridId1_opr_onRefresh(record, fieldId, fieldValue) {
		if (record) {
			return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:openDetail()\">详情</a></span>";
		} else {
			return "&nbsp;";
		}
	}
	//主界面表格  绑定状态    刷新显示之前根据是否存在  绑定的设备号 判断是否已经绑定  只用于显示
	function gridId1_bindStat_onRefresh(record, fieldId, fieldValue) {
		var auditId = null ;
		var auditFlag = null;
		auditId = record.getValue("auditId");//审核信息编号
		if(fieldValue == null || fieldValue ==''){
			return "未绑定";
		}else{//bindStat(绑定状态)映射的是machId(绑定设备号)字段
		    auditFlag = fieldValue.substring(0,1);
			if (auditFlag == 'K') {
				return "已绑定";
			} else if (auditFlag == 'Y')
				return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">绑定待审核</a></span>";
			else if(auditFlag == 'N')
				return "<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">解绑待审核</a></span>";
		}
	
	}
	//     终端状态
	function gridId1_termStat_onRefresh(record, fieldId, fieldValue) {
 		 var auditId = record.getValue("auditId");//审核信息编号
 		 var span = "<input id='bath_termType' value='"+fieldValue+"' style='display:none'/>";
 	    if(fieldValue == '00'){
 	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增待审核 </a></span>";	
 	    }
 	    if(fieldValue == '02'){
 	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">新增被拒绝  </a></span>";	
 	    }
 	    if(fieldValue == '03'){
 	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改待审核 </a></span>";	
 	    }
 	    if(fieldValue == '04'){
 	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">修改被拒绝</a></span>";	
 	    }
 	   if(fieldValue == '05'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">启用待审核</a></span>";	
	    }
 	   if(fieldValue == '06'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">启用被拒绝</a></span>";	
	    }
 	   if(fieldValue == '07'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">停用待审核</a></span>";	
	    }
	   if(fieldValue == '08'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">停用被拒绝</a></span>";	
	    }
	   if(fieldValue == '09'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">绑定待审核</a></span>";	
	    }
	   if(fieldValue == '10'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">绑定被拒绝</a></span>";	
	    }
	   if(fieldValue == '11'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">解绑待审核</a></span>";	
	    }
	   if(fieldValue == '12'){
	    	return span+"<span style='width:100%;text-align:center'><a href=\"JavaScript:onClickWin('"+auditId+"')\">解绑被拒绝</a></span>";	
	    }
 	    if(fieldValue == '13'){
 	    	return span+'未绑定'; 
 	    }
 	    if(fieldValue == '14'){
 	    	return span+'解绑';	
 	    }
 	    if(fieldValue == '99'){
 	    	return span+'停用';
 	    }
 	    if(fieldValue == '01'){
 	    	return span+'启用';	
 	    }
	}
	function gridId1_mchtId_onRefresh(record, fieldId, fieldValue) {
	    return fieldValue;
	}
	//秘钥文件下载
	function gridId1_count_onRefresh(record, fieldId, fieldValue) {
		if (record) {
			var zmk=record.getValue("zmk");
			var zak=record.getValue("zak");
			var zpk=record.getValue("zpk");
			//alert(zmk+"-------"+zak+"======"+zpk)
			if(zmk!=null&&zmk!=""&&zak!=null&&zak!=""&&zpk!=null&&zpk!=""){
				return "<span style='width:100%;text-align:center' class='fa fa-download'><a href=\"JavaScript:downLoadFile()\">下载</a></span>";
			}else{
				return "<span style='width:100%;text-align:center' class='fa fa-exchange'><a href=\"JavaScript:makeKey()\">申请秘钥</a></span>";
			}
		} else {
			return "&nbsp;";
		}
	}
	//秘钥文件下载  下载   超链接点击
	function downLoadFile(){
		var termId=paypTermInf_dataset.getValue("termId");
		var mchtId=paypTermInf_dataset.getValue("mchtId");
		var url="<snow:url flowId="com.ruimin.ifs.term.comp.PaypTermInfoAction:downLoadFile" />"+"&termId="+termId+"&mchtId="+mchtId;
		document.getElementById("dfrm").src=url;
	}
	//秘钥文件下载  申请秘钥   超链接点击
	function makeKey(){
		var  termStat = paypTermInf_dataset.getValue("termStat");//获取终端信息的状态
		if(termStat=='01'){
			btnKey.click();//影藏的申请秘钥按钮点击链接到 action
		}else{
			$.warn("非启用状态的终端信息不允许申请秘钥！");
		}	
	}
	function btnKey_postSubmit(btn,param) {
		if(param.errorCode=='00000'){
			$.success("秘钥申请成功");
			
		}else{
			$.error("秘钥申请失败");
		}
		paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
	}
	//-------------------公共方法------------------------
	/**  * 判断是否null  * @param data  */
	function isNull(data) {
		return (data == "" || data == undefined || data == null) ? true : false;
	}
	//判断是否显示子商户
	function showSubMcht(showFlag){
		if(showFlag == true){
			$("#selectSubMcht").css("display","block");
		}
		else{
			$("#selectSubMcht").css("display","none");
		}
	}
	//当数据集发生变化时,判断是不是是否选择子商户 1:是 2:否
	function paypTermInf_dataset_afterChange(dataset,field){
		if(field.fieldName=="mchtNoFlag"){//判断是否是  "是否选择子商户"字段发生改变了
			if(paypTermInf_dataset.getValue("mchtNoFlag") == "1"){
				showSubMcht(true);	
			}else{
				showSubMcht(false);
				paypTermInf_dataset.setValue("qmchtNo","");
			}
		}
	}
  //-------------------------批量下载----------------------------
  function batDownLoad_onClickCheck() {
	var  mchtId  = document.getElementById("editor_query_mchtId").value;//获取商家号码
	var  qmchtName  =  document.getElementById("editor_query_qmchtName").value;//获取商家号码
	var  qtermId  =    document.getElementById("editor_query_qtermId").value;//获取商家号码
	var  stat  =    document.getElementById("editor_query_qtermStat").value;//获取商家号码
	var  qtermStat  = document.getElementById("bath_termType").value;//获取商家号码
	var  termType  =  document.getElementById("editor_query_qtermType").value;//获取商家号码
	var  qmachInst  =  document.getElementById("editor_query_qmachInst_val").value;//获取商家号码
	//获取终端类型序号
	if (termType == "传统POS") {
		termType = "1";
	}else if (termType == "固话POS") {
		termType = "2";
	}else if (termType == "移动POS") {
		termType = "3";
	}else if (termType == "mPos") {
		termType = "4";
	}
	//设置终端状态
	if(stat == null || stat == ""){
		qtermStat = "";
	}
	qmchtName = encodeURI(qmchtName);
	var param = "&mchtId="+mchtId+"&qmchtName="+qmchtName+"&qtermId="+qtermId
		+"&qtermStat="+qtermStat+"&qtermType="+termType+"&qmachInst="+qmachInst;
	var url="<snow:url flowId="com.ruimin.ifs.term.comp.PaypTermInfoAction:batDownLoadFile" />"+param;
	document.getElementById("dfrm").src=url;
}
	/**
		打印Object详细内容
	*/
	function writeObj(obj) {
		var description = "";
		for ( var i in obj) {
			var property = obj[i];
			description += i + " = " + property + "\n";
		}
	}
	
	//-------------终端状态超链接点击------------------
	 function onClickWin(auditId){
		 paypTermInf_dataset.setFieldReadOnly("termId", true);
		 paypTermInf_dataset.setFieldReadOnly("mchtId", true);
		 paypTermInf_dataset.setFieldReadOnly("mchtName", true);
		 paypTermInf_dataset.setFieldReadOnly("machInst", true);
		 paypTermInf_dataset.setFieldReadOnly("termType", true);
		 paypTermInf_dataset.setFieldReadOnly("termStat", true);
		 paypTermInf_dataset.setFieldReadOnly("termInstAdrr", true);
		 paypTermInf_dataset.setFieldReadOnly("bindTelNo", true);
		 paypTermInf_dataset.setFieldReadOnly("bindTelNo1", true);
		 paypTermInf_dataset.setFieldReadOnly("auditId", true);
       //根据审核信息编号，查询审核记录表
       var auditId = paypTermInf_dataset.getValue("auditId");
       checkNewRecordInfo_dataset.setParameter("auditId",auditId);
       checkNewRecordInfo_dataset.flushData(checkNewRecordInfo_dataset.pageIndex);
       window_windowTermStat.open();
   }
	//关闭窗体
	function window_windowTermStat_afterClose(){
		 paypTermInf_dataset.setFieldReadOnly("termId", false);
		 paypTermInf_dataset.setFieldReadOnly("mchtId", false);
		 paypTermInf_dataset.setFieldReadOnly("mchtName", false);
		 paypTermInf_dataset.setFieldReadOnly("machInst", false);
		 paypTermInf_dataset.setFieldReadOnly("termType", false);
		 paypTermInf_dataset.setFieldReadOnly("termStat", false);
		 paypTermInf_dataset.setFieldReadOnly("termInstAdrr", false);
		 paypTermInf_dataset.setFieldReadOnly("bindTelNo", false);
		 paypTermInf_dataset.setFieldReadOnly("bindTelNo1", false);
		 paypTermInf_dataset.setFieldReadOnly("auditId", false);
		}
	//---------------点击详情超链接【显示详情】--------------------------------
	function openDetail() {
		paypTermInf_dataset.setReadOnly(true);
		paypMachInf_dataset.setReadOnly(true);
		var machId = paypTermInf_dataset.getValue("machId");// 绑定的设备号
		//根据情况是否显示   有绑定的设备信息要显示设备详情，否则不显示
		if (isNull(machId)) { 
			  //开始处理终端功能显示 
            var  mchtId  = paypTermInf_dataset.getValue("mchtId");//获取商家号码
            mchtProdInf_dataset.setParameter("mchtId", mchtId);
            mchtProdInf_dataset.flushData(mchtProdInf_dataset.pageIndex);
            paypTermInf_dataset.setValue("flag", "detail1");  //为了方便 修改 对终端功能做组装  临时 做标记
            window_windowDetailId1.open();//只显示终端基本信息和权限信息
		} else {
			// 根据    绑定设备号    参数 查询绑定的设备信息详情 
			var machId1 = "K"+machId.substring(1);
			paypMachInf_dataset.setParameter("qmachId", machId1);
			paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
			window_windowDetailId2.open();//显示终端基本信息和权限信息还有绑定的设备库存信息
			   //开始处理终端功能显示 
            var  mchtId  = paypTermInf_dataset.getValue("payMchtId");//获取商家号码
            mchtProdInf_dataset.setParameter("mchtId", mchtId);
            mchtProdInf_dataset.flushData(mchtProdInf_dataset.pageIndex);
			paypTermInf_dataset.setValue("flag", "detail2");  //为了方便 修改 对终端功能做组装  临时 做标记
			
		}

	}
	//-----------------------修改---------------------------
	//修改窗口打开之前
	function window_windowUpdateId_beforeOpen(){
		paypTermInf_dataset.setFieldReadOnly("secMerId",true);
	}
	//修改 弹出窗口
	function btnMod_onClickCheck(button, commit) {
		paypTermInf_dataset.setReadOnly(false);
		paypTermInf_dataset.setFieldReadOnly("termId", true);
		paypTermInf_dataset.setFieldReadOnly("mchtId", true);
		paypTermInf_dataset.setFieldReadOnly("mchtName", true);
		paypTermInf_dataset.setFieldReadOnly("machInst", true);
		paypTermInf_dataset.setFieldReadOnly("termStat", true);
		  //开始处理终端功能显示 
        var  mchtId  = paypTermInf_dataset.getValue("payMchtId");//获取商家号码
        var  termStat = paypTermInf_dataset.getValue("termStat");//获取终端信息的状态
        var bindStat = paypTermInf_dataset.getValue("bindStat");
		if (!isNull(bindStat)) {//如果终端有绑定设备信息 执行是否解除绑定
			var tip = bindStat.substring(0,1);
			if(tip == 'N' || tip == 'Y'){
				$.warn("待审核状态的终端信息不允许修改！");
				return false ;
			}
		} 
        if(!(termStat == '01' || termStat == '02')){
        	$.warn("终端状态为非启用状态的终端信息不允许修改");
        	return false;
        }
        mchtProdInf_dataset.setParameter("mchtId", mchtId);
        mchtProdInf_dataset.flushData(mchtProdInf_dataset.pageIndex);
        paypTermInf_dataset.setValue("flag", "detail3");  //为了方便 修改 对终端功能做组装  临时 做标记
        
        //2017-03-23新增
        paypTermInf_dataset.setFieldRequired("termInstAdrr",true);
        
		window_windowUpdateId.open();
	}
	//修改 保存
	function btnUpdateSave_onClickCheck(button) {
		//获取  select  选中的值
        var paypTermInfSelectStr="";
        $("input[name='paypTermInfSelectUpdate']").each(function(e){
            if($(this).attr("checked")=='checked'){
                paypTermInfSelectStr+='@'+$(this).val();
            }
         });
        paypTermInf_dataset.setValue("termParm",paypTermInfSelectStr);
//         $("input[name='SelectUpdate']").each(function(e){
//             if($(this).attr("checked")=='checked'){
//                 SelectStr+='@'+$(this).val();
//             }
//          });
//         paypTermInf_dataset.setValue("termParm",SelectStr);
		paypTermInf_dataset.setParameter("paramCmd", "update");
		return true;
	}
	//全选
    function btnSelectAllUpdate_onClick() {
        $("input[name='paypTermInfSelectUpdate']").each(function(e){
                $(this).attr("checked", true);  
             });
    }
    //全不选
    function btnUnSelectAllUpdate_onClick() {
          $("input[name='paypTermInfSelectUpdate']").each(function(e){
              $(this).attr("checked", false);  
           });
    }
	function btnUpdateSave_postSubmit() {
		$.success("修改成功!", function() {
			window_windowUpdateId.close();
			paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
		});
	}
	function window_windowUpdateId_beforeClose(){
		paypTermInf_dataset.setFieldReadOnly("secMerId",false); //还原字段
		//2017-03-23新增
		paypTermInf_dataset.setFieldRequired("termInstAdrr",false);
		paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
	}
	
	//--------------------------启用停用---------------------------
	function btStatus_onClickCheck(button, commit) {
		var termStat = paypTermInf_dataset.getValue("termStat");// 绑定的设备号
		var mchtId = paypTermInf_dataset.getValue("mchtId");//  终端信息 唯一约束
		var termId = paypTermInf_dataset.getValue("termId");//  终端信息 唯一约束
		var  termStat = paypTermInf_dataset.getValue("termStat");//获取终端信息的状态
		var bindStat = paypTermInf_dataset.getValue("bindStat");
		if (!isNull(bindStat)) {//如果终端有绑定设备信息 执行是否解除绑定
			var tip = bindStat.substring(0,1);
			if(tip == 'N' || tip == 'Y'){
				$.warn("待审核状态的终端信息不允许启动或者停用！");
				return false ;
			}
		} 
	        if(!(termStat == '01' || termStat == '99')){
	        	$.warn("终端状态为待审核状态的终端信息不允许启和停用！");
	        	return false;
	        }
		if(isNull(mchtId)){
            $.warn("提示：请选择一个终端进行操作!");
            return false;
        }
		if(isNull(termStat)){
			termStat = 00;
		}
		//1 启用   4 停用
		if(termStat==01){
			 $.confirm("是否置为【停用】状态? ", function() {
				 updateTermStat('99' ,termId);
	            }, function() {
	                return false;
	            });
		}else{
			 $.confirm("是否置为【启用】状态? ", function() {
                 updateTermStat('01' ,termId);
                }, function() {
                    return false;
                });
		}
	}
	//更改启用或 停用状态
	function updateTermStat(termStat,termId) {
	   paypTermInf_dataset.setParameter("termStat", termStat);
	   paypTermInf_dataset.setParameter("termId", termId);
	   btnUpdateTermStatu.click();//启用停用保存按钮
	}
	function btnUpdateTermStatu_postSubmit(btn) {
	   $.success("操作成功!", function() {
	       paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
	   });
	}
	//-----------------绑定解绑-------------------------------
	function btStatus2_onClickCheck(button, commit) {
		var termId = paypTermInf_dataset.getValue("termId");// termId
		var termStat = paypTermInf_dataset.getValue("termStat");//终端状态
		//00-新增待审核  01-启用  02-新增被拒绝  03-修改待审核  04-修改被拒绝  05-启用待审核  06-启用被拒绝  07-停用待审核  08-停用被拒绝  09-绑定待审核  10-绑定被拒绝
		//11-解绑待审核  12-解绑被拒绝  13-未绑定  14-已绑定  99-停用
		if(termStat != '01' ){//终端只要不是启动状态都无法绑定或者解绑
			$.warn("终端状态为非启用状态的终端信息不允许绑定或者解绑！");
			return false;
		}
		if(isNull(termId)){//没有选中表格中的记录
			$.warn("提示：请选择一个终端进行操作!");
			return false;
		}
		//bindStat(绑定状态) 实际映射字段machId(绑定设备号)
		var bindStat = paypTermInf_dataset.getValue("bindStat");//K201608147496195
		if (!isNull(bindStat)) {//如果终端已经有绑定设备信息 (则说明点击代表解绑操作)
			var tip = bindStat.substring(0,1);
			if(tip == 'N' || tip == 'Y'){//未绑定-->绑定，要将绑定的设备编号取出头字母，然后替换成Y,表示绑定待审核
				$.warn("待审核状态的终端信息不允许绑定或者解绑！");
				return false ;
			}
			$.confirm("该终端已经绑定,是否确认解除绑定? ", function() {
				unBingMachId();
			}, function() {
				return false;
			});
		} else {
			//带参数 查询可以绑定的设备信息
			paypMachInf_dataset.setReadOnly(true);
			paypMachInf_dataset.setParameter("paramCMD", "NoBing");
			paypMachInf_dataset.flushData(paypMachInf_dataset.pageIndex);
			window_windowNoBingTermId.open();
		}
	}
	// 终端绑定设备号，弹出框显示所有未绑定的终端设别信息那个窗口终端操作栏超链接
	function paypMachInfList_opr2_onRefresh(record, fieldId, fieldValue) {
		if (record) {
			return "<span style='width:100%;text-align:center' class='fa fa-list'><a href=\"JavaScript:bingMachId()\">绑定</a></span>";
		} else {
			return "&nbsp;";
		}
	}
	//终端绑定设备号  执行  
	function bingMachId() {
		var machId = paypMachInf_dataset.getValue("machId");// payp_mach_inf 设备库存编号
		var termId = paypTermInf_dataset.getValue("termId");//payp_term_inf  终端编号  唯一约束
		if (isNull(machId) || isNull(termId)) {
			$.warn("提示：无法获取 设备库存编号 或者 终端信息号, 请刷新页面");
			return false;
		}
		paypTermInf_dataset.setParameter("machId", machId);
		paypTermInf_dataset.setParameter("termId", termId);
		
		btnBingMachId.click();//执行操作//调用后台的方法com.ruimin.ifs.term.comp.PaypMachInfAction:updateMachMachStat
	}
	function btnBingMachId_postSubmit(btn) {
		$.success("进入绑定流程成功!", function() {
			window_windowNoBingTermId.close();
			paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
		});
	}


	//解除终端绑定设备号   执行
	function unBingMachId() {
		var machId = paypTermInf_dataset.getValue("machId");// 绑定的设备号
		var termId = paypTermInf_dataset.getValue("termId");//payp_term_inf  终端唯一约束
		if (isNull(machId) || isNull(termId)) {
			$.warn("提示：无法获取 设备库存编号 或者 终端信息号, 请刷新页面");
			return false;
		}
		paypTermInf_dataset.setParameter("machId", machId);
		paypTermInf_dataset.setParameter("termId", termId);
		btnUnBingMachId.click();//执行解除绑定操作
	}
	function btnUnBingMachId_postSubmit(btn) {
		$.success("进入解除绑定流程成功!", function() {
			paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
		});
	}

  //-----------------新增-----------------------------------------
  	//窗口打开前不显示子商户选择
	function window_windowAddId_beforeOpen(){
		showSubMcht(false);
		paypTermInf_dataset.setValue("qmchtNo","");
		paypTermInf_dataset.setFieldRequired("qmchtId",true);//设置 字段必输
	}
  	//点击新增按钮打开新增窗口
	function btnAdd_onClick() {
		paypTermInf_dataset.setReadOnly(false);
		paypTermInf_dataset.setFieldRequired("termInstAdrr",true);//终端安装地址必须输入
        paypTermInf_dataset.setValue("parmDownFlag", "1");//是否参数下载
        paypTermInf_dataset.setValue("icFlag", "1");//是否支持IC卡
        paypTermInf_dataset.setValue("termId", "后台自动生成");//终端编号
        paypTermInf_dataset.setFieldReadOnly("termId", true);
        paypTermInf_dataset.setFieldReadOnly("termStat", false);//终端状态
        //打开新增窗口
        window_windowAddId.open();
    }
  	//当新增窗口关闭之前字段还原
	function window_windowAddId_beforeClose(wmf) {//增加窗口 关闭以后执行 操作  （必须有）
		paypTermInf_dataset.setFieldReadOnly("mchtName", false);
		paypTermInf_dataset.setFieldReadOnly("machInstName", false);  //还原字段输入权限
		paypTermInf_dataset.setFieldRequired("qmchtId",false);
		paypTermInf_dataset.cancelRecord();
		 $("#iconDiv").html('');//动态加载终端功能的div清空
		//2017-03-23新增
		paypTermInf_dataset.setFieldRequired("termInstAdrr",false);//终端安装地址
	}
  	//新增窗口的保存按钮
	function btnSave1_postSubmit() {
		$.success("保存成功!", function() {
			window_windowAddId.close();
			paypTermInf_dataset.flushData(paypTermInf_dataset.pageIndex);
		});
	}
	//新增窗口的保存按钮点击之前的检查
	function btnSave1_onClickCheck(button) {
		//获取  select  选中的值
		  var paypTermInfSelectStr="";
		  $("input[name='paypTermInfSelect']").each(function(e){
			  if($(this).attr("checked")=='checked'){
				  paypTermInfSelectStr+='@'+$(this).val();
			  }
           });
		  paypTermInf_dataset.setValue("termParm",paypTermInfSelectStr);
		  paypTermInf_dataset.setParameter("paramCmd", "add");
		return true;
	}
	//全选
	function btnSelectAll1_onClick() {
		$("input[name='paypTermInfSelect']").each(function(e){
			    $(this).attr("checked", true);  
			 });
	}
	//全不选
	function btnUnSelectAll1_onClick() {
		  $("input[name='paypTermInfSelect']").each(function(e){
			  $(this).attr("checked", false);  
           });
	}
	//新增时,当商户编号下拉框选择完之后
	function paypTermInf_dataset_qmchtId_onSelect(dropdown,record) {
		paypTermInf_dataset.setValue("qmchtId",record.getValue("mchtId"));//商户号
		paypTermInf_dataset.setValue("mchtName", record.getValue("mchtName"));//商户名称
		paypTermInf_dataset.setValue("machInstName",record.getValue("mchtOrg"));//机构名称
		paypTermInf_dataset.setValue("machInst",record.getValue("mchtOrg"));//机构号
		paypTermInf_dataset.setValue("mchtId",record.getValue("mchtNo"));//设置商户号
		paypTermInf_dataset.setValue("payMchtId",record.getValue("mchtId"));//商户号
		paypTermInf_dataset.setValue("mchtNoFlag","2");
		paypTermInf_dataset.setValue("qmchtNo","");  //设置子商户下拉框字段为空
		paypTermInf_dataset.setFieldReadOnly("mchtName", true);
		paypTermInf_dataset.setFieldReadOnly("machInstName", true);
		paypTermInf_dataset.setValue("flag", "addPaypTermInf");  //为了方便 修改 对终端功能做组装  临时 做标记
		var  mchtId  = record.getValue("mchtId");//获取商家号码
		mchtProdInf_dataset.setParameter("mchtId", mchtId);
		mchtProdInf_dataset.flushData(mchtProdInf_dataset.pageIndex);
		}
	//新增时选择子商户下拉窗口打开前
	function paypTermInf_dataset_qmchtNo_beforeOpen(dropDown,dropDownDataset){
		dropDown.cache = false;
		var mchtId = paypTermInf_dataset.getValue("qmchtId");
		if(mchtId==null || mchtId ==""){
			$.warn("请先选择上级商户!");
			return false;
		}
		dropDownDataset.setParameter("mchtId",mchtId);
		dropDownDataset.flushData(dropDownDataset.pageIndex);
	}
	//新增时子商户选择框
	function paypTermInf_dataset_qmchtNo_onSelect(dropdown,record){
		paypTermInf_dataset.setValue("qmchtNo",record.getValue("mchtId"));
// 		paypTermInf_dataset.setValue("mchtName", record.getValue("mchtName"));//商户名称
// 		paypTermInf_dataset.setValue("machInstName",record.getValue("mchtOrg"));//机构名称
// 		paypTermInf_dataset.setValue("machInst",record.getValue("mchtOrg")); //机构号
		//新增字段设置值
		paypTermInf_dataset.setValue("secMerId",record.getValue("mchtId"));  //设置子商户编号值到对应字段
		paypTermInf_dataset.setFieldReadOnly("mchtName", true);
		paypTermInf_dataset.setFieldReadOnly("machInstName", true);
		paypTermInf_dataset.setValue("flag", "addPaypTermInf");  //为了方便 修改 对终端功能做组装  临时 做标记
		var  mchtId  = record.getValue("mchtId");//获取商家号码
	}
	
	

	

	
	
	
	function mchtProdInf_dataset_flushDataPost() {
	     var flag_if = paypTermInf_dataset.getValue("flag");//  标记    增加   修改   详情  
	     var termParm = paypTermInf_dataset.getValue("termParm");// 绑定的设备号
	     if(flag_if=='addPaypTermInf'){////【增加时】  终端 功能显示 
	             var rec = mchtProdInf_dataset.firstUnit;
	             var flags = false;//是否有显示
	             var iconHtml = [];
	             iconHtml.push('<div class="l-group l-group-hasicon"><div class="form-icon">&nbsp;</div><span>终端功能</span>');
	             iconHtml.push(' </div>');
	             iconHtml.push(' <table class="form-table" style="width:100%">');
	             iconHtml.push(' <tbody>');
	             var rec = mchtProdInf_dataset.firstUnit;
	             var j = 5;//5个排列
	             var i;
	             i = j;
	             while (rec) {
	                 if(i%j==0){
	                     iconHtml.push('<tr id="formAddId'+i+'_0">');
	                 }
	                 var value = rec.getValue("txnTypeName");
	                 var code = rec.getValue("txnTypeCode");
	                 var flag =code;
	                 var  datasetId = "paypTermInf";
	                 iconHtml.push( "<td class='form-label' id='label_"+datasetId+"_"+flag+"'><label extra='fieldlabel' id='fieldlabel_"+flag+"' datasetid='"+datasetId+"' datafield='"+flag+"' label='"+value+"'>"+value+"</label></td>");
	                 iconHtml.push("<td class='form-input' ><input class='l-checkbox' value='"+code+"' name='paypTermInfSelect' checked='checked' style='zoom:120%;' type='checkbox'></input></td>");
	                if(i%j==0){
	                      iconHtml.push(' </tr>');
	                }
	                 rec = rec.nextUnit;
	                 i++;
	                 flags = true;
	             }
	             iconHtml.push(' </tbody></table>  </form>');
	      if(flags){//当商家关联的产品对应的  时候显示
	           $("#btSelect").css("display","block"); 
	           $("#iconDiv").html(''); 
	           $("#iconDiv").html(iconHtml);
	      }else{
	            $("#iconDiv").html(''); 
	            $("#btSelect").css("display","none");   
	      }
	               
	     }else if(flag_if=='detail1'){//【详情1时】
	             var rec = mchtProdInf_dataset.firstUnit;
	             var flags = false;//是否有显示
	             var iconHtml = [];
	             iconHtml.push('<div class="l-group l-group-hasicon"><div class="form-icon">&nbsp;</div><span>终端功能</span>');
	             iconHtml.push(' </div>');
	             iconHtml.push(' <table class="form-table" style="width:100%">');
	             iconHtml.push(' <tbody>');
	             var rec = mchtProdInf_dataset.firstUnit;
	             var j = 5;//5个排列
	             var i;
	             i = j;
	             while (rec) {
	                 if(i%j==0){
	                     iconHtml.push('<tr id="formAddId'+i+'_0">');
	                 }
	                 var value = rec.getValue("txnTypeName");
	                 var code = rec.getValue("txnTypeCode");
	                 var flag =code;
	                 var  datasetId = "paypTermInf";
	                 iconHtml.push( "<td class='form-label' id='label_"+datasetId+"_"+flag+"'><label extra='fieldlabel' id='fieldlabel_"+flag+"' datasetid='"+datasetId+"' datafield='"+flag+"' label='"+value+"'>"+value+"</label></td>");
	                if(termParm.indexOf(code) > -1){
	                                 iconHtml.push("<td class='form-input' ><input class='l-checkbox' disabled='true' value='"+code+"' name='paypTermInfSelect' checked='checked' style='zoom:120%;' type='checkbox'></input></td>");
	                             }else{
	                                 iconHtml.push("<td class='form-input' ><input class='l-checkbox' disabled='true' value='"+code+"' name='paypTermInfSelect'  style='zoom:120%;' type='checkbox'></input></td>");
	                             }
	                 if(i%j==0){
	                      iconHtml.push(' </tr>');
	                }
	                 rec = rec.nextUnit;
	                 i++;
	                 flags = true;
	             }
	             iconHtml.push(' </tbody></table>  </form>');
	      if(flags){// 
	           $("#iconDiv2").html(''); 
	           $("#iconDiv2").html(iconHtml);
	      }else{
	            $("#iconDiv2").html(''); 
	      }
	     } else if(flag_if=='detail2'){//【详情2时】
	         var rec = mchtProdInf_dataset.firstUnit;
	         var flags = false;//是否有显示
	         var iconHtml = [];
	         iconHtml.push('<div class="l-group l-group-hasicon"><div class="form-icon">&nbsp;</div><span>终端功能</span>');
	         iconHtml.push(' </div>');
	         iconHtml.push(' <table class="form-table" style="width:100%">');
	         iconHtml.push(' <tbody>');
	         var rec = mchtProdInf_dataset.firstUnit;
	         var j = 5;//5个排列
	         var i;
	         i = j;
	         while (rec) {
	             if(i%j==0){
	                 iconHtml.push('<tr id="formAddId'+i+'_0">');
	             }
	             var value = rec.getValue("txnTypeName");
	           
	             var code = rec.getValue("txnTypeCode");
	             
	             var flag =code;
	             var  datasetId = "paypTermInf";
	             iconHtml.push( "<td class='form-label' id='label_"+datasetId+"_"+flag+"'><label extra='fieldlabel' id='fieldlabel_"+flag+"' datasetid='"+datasetId+"' datafield='"+flag+"' label='"+value+"'>"+value+"</label></td>");
	             
	            if(termParm.indexOf(code) > -1){
	                             iconHtml.push("<td class='form-input' ><input class='l-checkbox' disabled='true' value='"+code+"' name='paypTermInfSelect' checked='checked' style='zoom:120%;' type='checkbox'></input></td>");
	                         }else{
	                             iconHtml.push("<td class='form-input' ><input class='l-checkbox' disabled='true' value='"+code+"' name='paypTermInfSelect'  style='zoom:120%;' type='checkbox'></input></td>");
	     
	                         }
	             if(i%j==0){
	                  iconHtml.push(' </tr>');
	            }
	             rec = rec.nextUnit;
	             i++;
	             flags = true;
	         }
	                 iconHtml.push(' </tbody></table>  </form>');
	          if(flags){//当商家关联的产品对应的  时候显示
	               $("#iconDiv3").html(''); 
	               $("#iconDiv3").html(iconHtml);
	          }else{
	                $("#iconDiv3").html(''); 
	          }
	     }else if(flag_if=='detail3'){//【修改时】
	         
	          var rec = mchtProdInf_dataset.firstUnit;
	             var flags = false;//是否有显示
	             var iconHtml = [];
	             iconHtml.push('<div class="l-group l-group-hasicon"><div class="form-icon">&nbsp;</div><span>终端功能</span>');
	             iconHtml.push(' </div>');
	             iconHtml.push(' <table class="form-table" style="width:100%">');
	             iconHtml.push(' <tbody>');
	             var rec = mchtProdInf_dataset.firstUnit;
	             var j = 5;//5个排列
	             var i;
	             i = j;
	             while (rec) {
	                 if(i%j==0){
	                     iconHtml.push('<tr id="formAddId'+i+'_0">');
	                 }
	                 var value = rec.getValue("txnTypeName");
	               
	                 var code = rec.getValue("txnTypeCode");
	                 
	                 var flag =code;
	                 var  datasetId = "paypTermInf";
	                 iconHtml.push( "<td class='form-label' id='label_"+datasetId+"_"+flag+"'><label extra='fieldlabel' id='fieldlabel_"+flag+"' datasetid='"+datasetId+"' datafield='"+flag+"' label='"+value+"'>"+value+"</label></td>");
	                 
	                if(termParm.indexOf(code) > -1){
	                                 iconHtml.push("<td class='form-input' ><input class='l-checkbox' value='"+code+"' name='paypTermInfSelectUpdate' checked='checked' style='zoom:120%;' type='checkbox'></input></td>");
	                             }else{
	                                 iconHtml.push("<td class='form-input' ><input class='l-checkbox' value='"+code+"' name='paypTermInfSelectUpdate'  style='zoom:120%;' type='checkbox'></input></td>");
	         
	                             }
	                 if(i%j==0){
	                      iconHtml.push(' </tr>');
	                }
	                 rec = rec.nextUnit;
	                 i++;
	                 flags = true;
	             }
	                     iconHtml.push(' </tbody></table>  </form>');
	              if(flags){//当商家关联的产品对应的  时候显示
	                   $("#iconDiv4").html(''); 
	                   $("#iconDiv4").html(iconHtml);
	                   $("#btSelectUpdate").css("display","block"); 
	              }else{
	                    $("#iconDiv4").html(''); 
	                    $("#btSelectUpdate").css("display","none");   
	              }
	     }
	    
	} 
	
	
</script> 
</snow:page>