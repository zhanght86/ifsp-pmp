<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="">
	<div id="container" style="width: 200px; height: 200px; background: lightgray;">点击右键 ^_^</div>
	<snow:menu id="menu" width="150">
		<snow:menuitem desc="菜单项1" id="cd1" disabled="true"></snow:menuitem>
		<snow:menuitem desc="菜单项2" id="cd2" icon="fa fa-user">
			<snow:menuitem desc="菜单项2-1" id="cd2-1"></snow:menuitem>
			<snow:menuitem desc="菜单项2-2" id="cd2-2"></snow:menuitem>
		</snow:menuitem>
		<snow:menuitem desc="菜单项3" id="cd3" target="_blank" url="oo.xx.oo"></snow:menuitem>
	</snow:menu>

	<script>
		$("#container").bind("contextmenu", function(e) {
			menu_menu.show({
				top : e.pageY,
				left : e.pageX
			});
			return false;
		});
		function menu_menu_onClick(element, item) {
			alert(item.text);
		}
	</script>
</snow:page>
