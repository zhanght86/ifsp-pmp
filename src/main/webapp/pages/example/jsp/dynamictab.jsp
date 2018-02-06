<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="DynamicTabSet">
	<snow:layout id="layoutid">
		<snow:Layoutcenter id="center1" collapsed="false" title="">
			<div id="tabs" extra="tabs" style="width: 100%; overflow: hidden" data-options="height:'100%'">
				<div title="我的主页3" showClose="true" lselected="true" >
					<div id="Div1" style="margin: 10px; height: 300px;">
						我的主页3<i class="fa fa-user"></i>
					</div>
				</div><span style="margin-right: "></span>
				<div title="我的主页4" showClose="true">
					<div id="Div2" style="margin: 10px; height: 300px;">我的主页4</div>
				</div>
			</div>
		</snow:Layoutcenter>
		<snow:Layoutleft id="left" collapsed="false" width="180" title="API操作">
			<ul>
				<li><snow:button id="add" desc="新增"></snow:button></li>
				<li><snow:button id="close" desc="关闭当前"></snow:button></li>
				<li><snow:button id="closeAll" desc="关闭所有"></snow:button></li>
				<li><snow:button id="updateIcon" desc="更新图标"></snow:button></li>
				<li><snow:button id="updateTitle" desc="更新标题"></snow:button></li>
				<li><snow:button id="cancelIcon" desc="取消图标"></snow:button></li>
				<li><snow:button id="closable" desc="可关闭"></snow:button> <snow:button id="noclasable" desc="不可关闭"></snow:button></li>
				<li><snow:button id="openFirst" desc="打开第1个标签页"></snow:button></li>
				<li><snow:button id="freshFirst" desc="刷新第1个标签页"></snow:button></li>
				<li><snow:button id="contextmenu" desc="绑定右键菜单"></snow:button></li>
				<li><snow:button id="uncontextmenu" desc="解绑右键菜单"></snow:button></li>
				<li><snow:button id="enable" desc="可用"></snow:button></li>
				<li><snow:button id="disable" desc="禁用"></snow:button></li>
			</ul>
		</snow:Layoutleft>
	</snow:layout>

	<script>
	
		var ids = 0;
		function add_onClick(){
			tabs.add("id" + ids, "title" + ids, "helloworld" + ids);
			ids ++;
		}
		
		function close_onClick(){
			tabs.close();
		}
		
		function closeAll_onClick(){
			tabs.closeAll();
		}
		
		function updateIcon_onClick(){
			tabs.setIconCls(tabs.getSelectedId(), "fa fa-save");
		}
		
		function updateTitle_onClick(){
			tabs.setTitle(tabs.getSelectedId(), "我是新标题");			
		}
		
		function cancelIcon_onClick(){
			tabs.setIconCls(tabs.getSelectedId(), "");
		}
		
		function closable_onClick(){
			tabs.setClosable(true);
		}
		
		function noclasable_onClick(){
			tabs.setClosable(false);
		}
		
		function openFirst_onClick(){
			// 选中第一个标签
		}
		
		function freshFirst_onClick(){
			// 刷新第一个标签
		}
		
		function contextmenu_onClick(){
			// 绑定右键
		}
		
		function uncontextmenu_onClick(){
			// 解绑右键
		}
		
		// 启用 样式未实现
		function enable_onClick(){
			tabs.setEnable(tabs.getSelectedId(),true);
		}
		
		// 禁用 样式未实现
		function disable_onClick(){
			tabs.setEnable(tabs.getSelectedId(),false);
		}

	
	
// 		$(function() {
// 			var cnt = 0;
// 			$("#layoutleft_left").delegate("button", "click", function(e) {
// 				var id = e.currentTarget.id;
// 				switch (id) {
// 				case 'add':
// 					cnt++;
// 					tabs.add("id" + cnt, "title" + cnt, 'hello' + cnt);
// 					break;
// 				case 'close':
// 					tabs.close();
// 					break;
// 				case 'closeAll':
// 					tabs.closeAll();
// 					break;
// 				case 'selectFirst':
// 					tabs.select("id0");
// 					break;
// 				case 'updateTitle':
// 					tabs.setTitle("newtitle");
// 					break;
// 				case 'closable':
// 					tabs.setClosable(true)
// 					break;
// 				case 'noclasable':
// 					tabs.setClosable(false);
// 					break;
// 				default:
// 					break;
// 				}
// 			});
// 		})
		
	</script>

</snow:page>
