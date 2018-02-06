<%@page import="com.ruimin.ifs.pmp.pubTool.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<%
String picList = StringUtil.filtrateSpecialCharater(request.getParameter("picList"));
String[] picIds = null;
if(picList != null && picList.length() >0){
    picIds = picList.split("\\|");
}
%>
<snow:page title="图片列表">
	<snow:dataset id="MchtPic"
		path="com.ruimin.ifs.pmp.mcht.dataset.DeleteMchtPic" submitMode="current"
		init="false"></snow:dataset>
	<snow:button id="btnPicDelete" dataset="MchtPic" hidden="true"></snow:button>
<script> 
$(function(){ 
var offsetX=20-$("#imgtest").offset().left; 
var offsetY=20-$("#imgtest").offset().top; 
var sizey=2.5*$('#imgtest ul li img').height(); 
var sizex=2.5*$('#imgtest ul li img').width(); 
$("#imgtest ul li").mouseover(function(event) { 
var $target=$(event.target); 
if($target.is('img')) 
{ 
$("<img id='tip' src='"+$target.attr("src")+"'>").css({ 
"height":sizey, 
"width":sizex, 
"top":event.pageX+offsetX, 
"left":event.pageY+offsetY, 
"position":"absolute"
}).appendTo($("#imgtest")); 
} 
}).mouseout(function() { 
$("#tip").remove(); 
}).mousemove(function(event) { 
$("#tip").css( 
{ 
"left":event.pageX+offsetX, 
"top":event.pageY+offsetY,
"position":"absolute"
}); 
}); 
}) 

function btnPicDelete_needCheck(){
	return false;
}

function deletePic(picId){
	$.confirm("是否确认删除?", function(e) {
// 		MchtPic_dataset.setParameter("deletePicId", picId);
// 		btnPicDelete.click();
		window.parent.deletePic(picId);
	}, function() {
		return false;
	});
}

function btnPicDelete_postSubmit(){
	$.success("操作成功!", function() {
		
	});
}





</script> 
<style type="text/css"> 
img{ height:150px; width:190px; position:relative; border:5px solid #FFF;} 
#imgtest{ height:auto;width:100%; margin:0 auto; position:absolute; } 
#imgtest ul{ position:relative;width:100%; height:auto; background:#00F;} 
#imgtest ul li{ position:relative; height:160px; width:200px; list-style:none; float:left; margin:2px; border:1px solid #999;} 
</style> 
<div id="imgtest"> 
<ul id="imgList"> 
<%
	if(picIds != null && picIds.length>0){
		for(String picId : picIds){
%>
			<li>
				<img src="/tlPMP/downServlet?chnlNo=8001&fileId=<%=picId%>" />
				</br>
<%-- 				<a href="#" style="height: 10px;" onclick="deletePic('<%=picId%>')">删除</a> --%>
			</li>
<%
		}
	}
%>
</ul> 
</div> 

</snow:page>