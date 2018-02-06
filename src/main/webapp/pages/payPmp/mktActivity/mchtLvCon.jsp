<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="评级配置">
	<!-- 评级配置基本信息数据集 -->	
	<snow:dataset id="MchtLvCon" path="com.ruimin.ifs.pmp.mktActivity.dataset.MchtLvCon" init="true" submitMode="current"></snow:dataset>
	<!-- 主表单 -->
	<snow:grid dataset="MchtLvCon" sortable="true" remotesort="true" id="gridId" fitcolumns="ture" fieldstr="mchtLv,lvDesc,dayDepositMix,busiCntMix,busiAmtMix,opr" 
	           height = "99%" paginationbar="btnAdd"> 
	</snow:grid>
			
	<!-- 新增 -->
	<snow:window id="windowAddId" title="新增" modal="true" closable="true" buttons="btnAddSave">
		<snow:form id="formAddId" dataset="MchtLvCon" border="false" label="新增" 
		 fieldstr="mchtLv,lvDesc,dayDepositMix,busiCntMix,busiAmtMix" 
		 collapsible="false" colnum="4">
        </snow:form>
		<snow:button id="btnAddSave" dataset="MchtLvCon" hidden="true"></snow:button>
	</snow:window>
	
	
	<!-- 修改 -->
	<snow:window id="windowModifyId" title="修改" modal="true" closable="true" buttons="btnUpSave">
		<snow:form id="formModifyId" dataset="MchtLvCon" border="false" label="修改" 
		           fieldstr="mchtLv,lvDesc,dayDepositMix,busiCntMix,busiAmtMix" 
		           collapsible="false" colnum="4">
		</snow:form>
		<snow:button id="btnUpSave" dataset="MchtLvCon" hidden="true"></snow:button>
	</snow:window>
	
	
	<!-- 删除 -->
	<div align="center">
	   <snow:button id="btnDelSave" dataset="MchtLvCon"  hidden="true"></snow:button>
	</div>
	

	<script type="text/javascript">
//************************************公共变量声明****************************************************  
	//var isAmt=/^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;//最大长度12位数字,最多包含两位小数
	var isAmt=/^(([1-9][\d]{0,2}(,\d\d\d)*)|([1-9][\d]*)|0)(\.[\d]{1,2})?$/; //支持千分位逗号,最多包含两位小数
	var isNum=/^\d{1,20}$/;//最大长度20位数字
	var isDesc=/^\S{1,16}$/;//最大长度16位
	
	
//************************************新增功能方法****************************************************  	
	//打开新增窗口
	function btnAdd_onClick(){
		window_windowAddId.open();
	}
	
	//点击提交时，判断输入框是否符合要求
	function btnAddSave_onClickCheck(){
		var mchtLv =MchtLvCon_dataset.getValue("mchtLv");
		var lvDesc =MchtLvCon_dataset.getValue("lvDesc");
		var dayDepositMix =MchtLvCon_dataset.getValue("dayDepositMix");
		var busiCntMix =MchtLvCon_dataset.getValue("busiCntMix");
		var busiAmtMix =MchtLvCon_dataset.getValue("busiAmtMix");
		if(mchtLv == ""){
			$.error("必须输入商户等级！");
			return false;
		}
		
		if(lvDesc == ""){
			$.error("必须输入评级说明！");
			return false;
		}else{
			if(!isDesc.test(lvDesc)){
				$.warn("评级说明格式错误（最大长度16位）");
 				return false;
				
			}
		}
		
		if(((dayDepositMix != null)&&(dayDepositMix != "")) || ((busiAmtMix != null)&&(busiAmtMix != "")) || (busiCntMix != 0))
			{
							
			}else{
					$.error("请至少输入一项数据！(最低日均存款、最低消费笔数、最低消费金额)");
					return false;	
				}
			
		if((dayDepositMix != null)&&(dayDepositMix != ""))
			{
			var maxL;
			if(dayDepositMix.indexOf(".")>=0){
				maxL = 12;
			}else{
				maxL = 10;
			}
			var str = dayDepositMix.replace(/[,.]/g,"");
				if(dayDepositMix.length>maxL){//去除,与.
					$.warn("最低日均存款金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
					return false;
				}
			
				if(!isAmt.test(dayDepositMix)){
	 				$.warn("最低日均存款金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
	 				return false;
	 			}
			}
		
		if((busiAmtMix != null)&&(busiAmtMix != ""))
			{
			var maxL;
			if(busiAmtMix.indexOf(".")>=0){
				maxL = 12;
			}else{
				maxL = 10;
			}
			var str = busiAmtMix.replace(/[,.]/g,"");
				if(str.length>maxL){//去除,与.
					$.warn("最低消费金额金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
					return false;
				}
				if(!isAmt.test(busiAmtMix)){
	 				$.warn("最低消费金额金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
	 				return false;
	 			}
			}
		
		if((busiCntMix != 0))
			{
				if(!isNum.test(busiCntMix)){
	 				$.warn("最低消费笔数次数格式错误（最大长度20位数字）");
	 				return false;
	 			} 
			}
 		
		return true;
		
	}
	
	//输入完毕提交验证
	function btnAddSave_postSubmit(){
		$.success("添加成功！",function(){			
			window_windowAddId.close();			
			//window.location.reload(true);		
		});
	}
	
	//新增窗口关闭后刷新数据集
	function window_windowAddId_afterClose(){
		MchtLvCon_dataset.flushData(MchtLvCon_dataset.pageIndex);
	} 
	
	 
//************************************操作栏显示方法****************************************************  	
	//操作栏显示修改，删除功能
	function gridId_opr_onRefresh(mchtLvSeq) {
		
		var mchtLvSeq =MchtLvCon_dataset.getValue("mchtLvSeq");
	
 		return "</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-pencil'><a href=\"JavaScript:onClickModify()\">修改</a>"+
 		"</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-pencil'><a href=\"JavaScript: onClickDelete(' " + mchtLvSeq + " ')\">删除</a>";
	
	}
	
	
//************************************修改功能方法**************************************************** 
	
	//打开修改窗口
	function onClickModify(){
		window_windowModifyId.open();
	}
	
	//点击提交时，判断输入框是否符合要求
	function btnUpSave_onClickCheck(){
		var mchtLv =MchtLvCon_dataset.getValue("mchtLv");
		var lvDesc =MchtLvCon_dataset.getValue("lvDesc");
		var dayDepositMix =MchtLvCon_dataset.getValue("dayDepositMix");
		var busiCntMix =MchtLvCon_dataset.getValue("busiCntMix");
		var busiAmtMix =MchtLvCon_dataset.getValue("busiAmtMix");
		
		if(mchtLv == ""){
			$.error("必须输入商户等级！");
			return false;
		}
		
	
		if(lvDesc == ""){
			$.error("必须输入评级说明！");
			return false;
		}else{
			if(!isDesc.test(lvDesc)){
				$.warn("评级说明格式错误（最大长度16位）");
 				return false;
				
			}
		}
		
		
		if(((dayDepositMix != null)&&(dayDepositMix != "")) || ((busiAmtMix != null)&&(busiAmtMix != "")) || (busiCntMix != 0))
		{
				
		}else{
			$.error("请至少输入一项数据！(最低日均存款、最低消费笔数、最低消费金额)");
			return false;	
		}
		
		
		
		if((dayDepositMix != null)&&(dayDepositMix != ""))
		{
			var maxL;
			if(dayDepositMix.indexOf(".")>=0){
				maxL = 12;
			}else{
				maxL = 10;
			}
			var str = dayDepositMix.replace(/[,.]/g,"");
			if(dayDepositMix.length>maxL){//去除,与.
				$.warn("最低日均存款金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
				return false;
			}
		
			if(!isAmt.test(dayDepositMix)){
 				$.warn("最低日均存款金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
 				return false;
 			}
		}
	
	if((busiAmtMix != null)&&(busiAmtMix != ""))
		{
		var maxL;
		if(busiAmtMix.indexOf(".")>=0){
			maxL = 12;
		}else{
			maxL = 10;
		}
		var str = busiAmtMix.replace(/[,.]/g,"");
			if(str.length>maxL){//去除,与.
				$.warn("最低消费金额金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
				return false;
			}
			if(!isAmt.test(busiAmtMix)){
 				$.warn("最低消费金额金额格式错误（有效数字整数部分最大10位,最多包含两位小数）");
 				return false;
 			}
		}
	
		if((busiCntMix != 0))
			{
				if(!isNum.test(busiCntMix)){
 					$.warn("最低消费笔数次数格式错误（最大长度20位数字）");
 					return false;
 				} 
			}
		
		return true;
	}
	
	
	
	//输入完毕提交验证
	function btnUpSave_postSubmit(){
		
		$.success("修改成功！",function(){
		window_windowModifyId.close();
		//window_windowModUpdate.close();
		});
	}
	
	
	//修改窗口关闭后刷新数据集
	function window_windowModifyId_afterClose(){
		MchtLvCon_dataset.flushData(MchtLvCon_dataset.pageIndex);
	}
	 
	
	
//************************************删除功能方法**************************************************** 
	
	//弹出删除确认信息
	function onClickDelete(mchtLvSeq){
			$.confirm("确认删除吗",
			function(){
					MchtLvCon_dataset.setParameter("mchtLvSeq",mchtLvSeq);
					//window_windowModDel.open();
					btnDelSave.click();
				},
			function(){
					return false;
			    });
		}
		
	
	//输入完毕提交验证
	function btnDelSave_postSubmit(){
		$.success("操作成功");
		MchtLvCon_dataset.flushData(MchtLvCon_dataset.pageIndex);
	}
	
	</script>
</snow:page>
