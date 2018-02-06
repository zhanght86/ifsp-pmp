<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="Accordion">
	<snow:accordion id="demo" >
		<snow:accordionitem title="子项一" id="item1" icon="fa fa-cog">
			hello1
		</snow:accordionitem>
		<snow:accordionitem title="子项二" id="item2" selected="true">
			hello2
		</snow:accordionitem>
		<snow:accordionitem title="子项三" id="item3"></snow:accordionitem>
		<snow:accordionitem title="子项四" id="item4"></snow:accordionitem>
	</snow:accordion>
	<script>
		
	<%-- 叶子菜单单击事件句柄AccordionMenu_{id}_onClick --%>
		function accordion_demo_onClick(record, node) {
			if (!record.getValue("url")) {
				alert("you click:" + node.text);
			} else {
				//openSubWin(node.id, "打开URL", node.attributes.url)
				$('#main').attr('src', _application_root + record.getValue("url"));
			}
		}
	</script>

</snow:page>
