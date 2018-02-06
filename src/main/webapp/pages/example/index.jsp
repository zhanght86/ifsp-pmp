<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="Demo">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/pages/example/css/demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/fontawesome/css/font.css"/>
	<snow:layout id="layoutid">
		<snow:Layouttop id="top1" height="58">
			<div class="demo-header">
				<div class="demo-header-banner fa" >&nbsp;</div>
			</div>
		</snow:Layouttop>
		<snow:Layoutcenter id="center1" collapsed="false">
			<snow:tabs id="demo" border="false">
				<snow:tab title="主页" id="home" closable="false">&nbsp;</snow:tab>
			</snow:tabs>
		</snow:Layoutcenter>
		<snow:Layoutleft id="left" collapsed="false" width="205" title="菜单">
			<snow:accordion id="menus">
				<snow:accordionitem id="m1" title="表单(Form)">
					<snow:button desc="文本框" id="textinput" />
					<snow:button desc="下拉框" id="selectinput" />
					<snow:button desc="日期框" id="dateinput" />
					<snow:button desc="表单验证" id="validate" />
					<snow:button desc="按钮" id="button" />
				</snow:accordionitem>
				<snow:accordionitem id="m2" title="表格(Grid)">
					<snow:button desc="表格-普通" id="normalgrid" />
					<snow:button desc="表格-工具栏" id="toolbargrid" />
					<snow:button desc="表格-多表头" id="multicolumngrid"></snow:button>
<%-- 					<snow:button desc="表格-排序" id="sortgrid" /> --%>
				</snow:accordionitem>
				<snow:accordionitem id="m3" title="窗口(Window)">
					<snow:button desc="消息框" id="message" />
					<snow:button desc="浮动窗口" id="floatwindow" />
					<snow:button desc="弹出窗口" id="openwindow" />
				</snow:accordionitem>
				<snow:accordionitem id="m4" title="菜单(Menu)">
					<snow:button desc="右键菜单" id="rightmenu" />
				</snow:accordionitem>
				<snow:accordionitem id="m5" title="树(Tree)">
					<snow:button desc="同步树" id="synctree" />
					<snow:button desc="异步树" id="asynctree" />
<%-- 					<snow:button desc="带复选框的树" id="treewithcheckbox" /> --%>
				</snow:accordionitem>
				<snow:accordionitem id="m6" title="布局(Layout)">
					<snow:button desc="标签页" id="tabs_normal" />
					<snow:button desc="滑动菜单" id="accordion" />
					<snow:button desc="普通布局" id="basiclayout" />
					<snow:button desc="嵌套布局" id="nestedlayout" />
					<snow:button desc="可折叠面板" id="groupbox" />
				</snow:accordionitem>
				<snow:accordionitem id="m7" title="范例">
					<snow:button desc="范例1" id="demo" />
<%-- 					<snow:button desc="范例2" id="demo2" /> --%>
<%-- 					<snow:button desc="范例3" id="demo3" /> --%>
				</snow:accordionitem>
			</snow:accordion>
		</snow:Layoutleft>
	</snow:layout>

	<script>
		var actions = {
			textinput : '/pages/example/code/tab.jsp?jsp=textinput&dataset=Fields',
			selectinput : '/pages/example/code/tab.jsp?jsp=select&dataset=Fields',
			dateinput : '/pages/example/code/tab.jsp?jsp=dateinput&dataset=Fields',
			validate : '/pages/example/code/tab.jsp?jsp=validate&dataset=GroupValidateFields',
			button : '/pages/example/code/tab.jsp?jsp=button&dataset=DemoButton',
			tabs_normal : '/pages/example/code/tab.jsp?jsp=dynamictab',
			rightmenu : '/pages/example/code/tab.jsp?jsp=contextmenu',
			message : '/pages/example/code/tab.jsp?jsp=messagebox',
			floatwindow : '/pages/example/code/tab.jsp?jsp=dialog',
			openwindow : '/pages/example/code/tab.jsp?jsp=dialog_iframe',
			synctree : '/pages/example/code/tab.jsp?jsp=synctree&dataset=StaticTreeNode',
			asynctree : '/pages/example/code/tab.jsp?jsp=asynctree&dataset=StaticTreeNode',
// 			treewithcheckbox : '/pages/example/code/tab.jsp?jsp=treewithcheckbox&dataset=StaticTreeNode',
			normalgrid : '/pages/example/code/tab.jsp?jsp=datagrid_default&dataset=GridFields',
			toolbargrid : '/pages/example/code/tab.jsp?jsp=datagrid_toolbar&dataset=GridFields',
// 			sortgrid : '/pages/example/code/tab.jsp?jsp=datagrid_sort&dataset=GridFields',
 			multicolumngrid : '/pages/example/code/tab.jsp?jsp=datagrid_multihead&dataset=GridFields',
			accordion : '/pages/example/code/tab.jsp?jsp=accordion',
			basiclayout : '/pages/example/code/tab.jsp?jsp=basiclayout',
			nestedlayout : '/pages/example/code/tab.jsp?jsp=nestedlayout',
			groupbox : '/pages/example/code/tab.jsp?jsp=groupbox',
			demo : '/pages/example/code/tab.jsp?jsp=demo',	
// 			demo2 : '/pages/example/code/tab.jsp?jsp=demo2',	
// 			demo3 : '/pages/example/code/tab.jsp?jsp=demo3'	
		}
		$("#accordion_menus").delegate(".l-button", "click", function(e) {
			var id = this.id;
			var title = this.innerText;
			tabs_demo.add(id, title, actions[id]);
		})
	</script>
</snow:page>