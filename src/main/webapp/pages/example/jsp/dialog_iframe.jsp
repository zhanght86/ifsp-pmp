<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="弹出框">
	<div id="buttons">
		<snow:button id="openurl1" desc="窗口-不缓存页面"></snow:button>
		<snow:button id="openurl2" desc="窗口-缓存页面"></snow:button>
		<snow:button id="openurl3" desc="窗口-最大化"></snow:button>
	</div>
	<script>
		$("#buttons").delegate(".l-button", "click", function() {
			switch (this.id) {
			case "openurl1":
				$.open(this.id, this.innerText, "/pages/example/jsp/dialog.jsp");
				break;
			case "openurl2":
				$.open(this.id, this.innerText, "/pages/example/jsp/dialog.jsp", "400", "400", true);
				break;
			case "openurl3":
				$.open(this.id, this.innerText, "/pages/example/jsp/dialog.jsp", "400", "400", true, false, null, true);
				break;
			default:
				break;
			}
		})
	</script>

</snow:page>
