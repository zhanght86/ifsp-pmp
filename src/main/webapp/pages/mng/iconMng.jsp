<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="图标管理">
	<snow:dataset id="icon" path="com.ruimin.ifs.mng.dataset.Icon" init="true" parameters="cache=false;iconCssFile=/public/lib/fontawesome/css/font-awesome.css"></snow:dataset>
	<snow:query id="queryId" dataset="icon" fieldstr="qName" label="请输入查询条件"></snow:query>
	<ul id="iconDiv"></ul>
	<script>
	 	$(function(){
	 		$("#iconDiv").delegate("li", "hover", function() {
				$(this).toggleClass("icon-hover");
			})
	 		
	 	});
		function icon_dataset_flushDataPost() {
			var iconHtml = [];
			var rec = icon_dataset.firstUnit;
			while (rec) {
				iconHtml.push("<li class='icon-view' style='display:inline;'>" + UIUtil.iconHTML(rec.getValue("clsname")) + "<code><span>fa fa-</span>" + rec.getValue("name") + "</code></li>")
				rec = rec.nextUnit;
			}

			$("#iconDiv").html(iconHtml);
		
		}
	</script>
</snow:page>
