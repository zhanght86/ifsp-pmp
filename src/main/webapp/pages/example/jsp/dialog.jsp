<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="对话框">
	<snow:window id="fw" title="hello" width="300" closable="true" >
	<input id="btn2" type="button" value="close" onclick="hide()" />
	</snow:window>
	<snow:window id="fw2" width="300">
		<div>不能关闭</div>
		<input id="btn2" type="button" value="close" onclick="hide2()" />
	</snow:window>
	<input type="button" onclick="show()" value="显示对话框" />
	<input type="button" onclick="show2()" value="显示一个不能关闭的对话框" />
	<div id="dis" align="left">事件:</div>
	<script>
		function show(i) {
			window_fw.open();
		}
		function show2(i) {
			window_fw2.open();
		}
		function hide() {
			window_fw.close();
		}
		function hide2() {
			window_fw2.close();
		}
		function window_fw_beforeOpen(fw) {
			$("#dis").html($("#dis").html() + "<br/>触发打开之前事件.");
		}
		function window_fw_afterOpen(fw) {
			$("#dis").html($("#dis").html() + "<br/>触发打开之后事件.");
		}
		function window_fw_beforeClose(fw) {
			$("#dis").html($("#dis").html() + "<br/>触发关闭之前事件.");
		}
		function window_fw_afterClose(fw) {
			$("#dis").html($("#dis").html() + "<br/>触发关闭之后事件.");
		}
		function window_fw2_beforeClose(fw) {
			return false;
		}
	</script>

</snow:page>
