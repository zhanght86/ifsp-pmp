<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>

<snow:page title="图片上传">
	<style>
	*{ margin:0; padding:0;}
	#box{ margin:30px auto; width:100%; height:100%; }
	</style>
	<link rel="stylesheet" type="text/css" href="../css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="../css/diyUpload.css">
	<script type="text/javascript" src="../js/webuploader.html5only.min.js"></script>
	<script type="text/javascript" src="../js/diyUpload.js"></script>
	<snow:dataset id="MchtPic" path="com.ruimin.ifs.pmp.mcht.dataset.MchtPic" submitMode="current" init="true"></snow:dataset>
	<snow:form id="picInfo" dataset="MchtPic" border="true" label="基本信息" collapsible="true" colnum="6" labelwidth="130"
				fieldstr="mchtPicType" >
	</snow:form>
	<div id="box">
		<div id="uploadPic" ></div>
	</div>
	<script>
		$('#uploadPic').diyUpload({
			url:'/tlPMP/uploadServlet',
			success:function( data ) {
				debugger;
				console.info( data );
			},
			error:function( err ) {
				debugger;
				console.info( err );	
			}
		});
		
	</script>
</snow:page>