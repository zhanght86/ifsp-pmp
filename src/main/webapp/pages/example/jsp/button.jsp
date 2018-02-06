<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="按钮示例">
	<snow:dataset id="DemoButton" path="com.ruimin.ifs.example.dataset.DemoButton"></snow:dataset>
	<snow:button id="btn1" dataset="DemoButton" />
	<snow:button id="btn2" dataset="DemoButton" />
	<snow:button id="btn3" dataset="DemoButton" plain="true" />
	<snow:button id="btn4" dataset="DemoButton" plain="true" />
	<snow:button id="btn8" dataset="DemoButton" />

	<snow:button id="btn5" desc="点击我" />
	<snow:button id="btn6" desc="禁用的按钮" />
	<snow:button id="btn7" icon="/public/theme/Aqua/images/icon/pdf_image_export.gif" desc="这是一个gif图片"></snow:button>
	<snow:button id="btn9" desc="点击我隐藏" />
	
	<snow:button id="btn10" desc="文件下载" />
	<iframe src="" style="display: none;" id="filedown"></iframe>
	
	<script>
		function btn10_onClick(btn){
			var url = '<snow:url flowId="com.ruimin.ifs.example.comp.DemoAction:downLoadTest"/>';
			document.getElementById("filedown").src = url;
		}
	
		function btn5_onClick(btn) {
			$.alert("你点击了按钮点击我");
		}
		function initCallGetter_post() {
			alert(123);
			btn6.setDisabled(true);
		}
		function btn6_onClick(btn) {
			$.alert("你点击了禁用的按钮");
		}
		function btn8_onClickCheck(btn, commit) {
			$.confirm("确认提交？", function() {
				commit();
			}, function() {
			})
		}
		function btn8_onClick(btn, commit) {
			$.alert("提交成功")
		}
		function btn9_onClick(btn, commit) {
			btn.setHidden(true);
			setTimeout(function() {
				btn.setHidden(false);
			}, 2000);
		}
	</script>


	

</snow:page>
