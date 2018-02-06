<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="Message">
	<div id="buttons">
		<snow:button id="warn" desc="警告框"></snow:button>
		<snow:button id="info" desc="信息提示框"></snow:button>
		<snow:button id="error" desc="错误提示框"></snow:button>
		<snow:button id="success" desc="正确提示框"></snow:button>
		<snow:button id="confirm" desc="确认框"></snow:button>
		<snow:button id="custtitle" desc="自定义标题"></snow:button>
		<snow:button id="autoclose" desc="2秒自动关闭框"></snow:button>
		<hr>
		<snow:button id="tip" desc="右下提示框"></snow:button>
		<snow:button id="tip2" desc="右下提示框,2秒消息"></snow:button>
	</div>
	<script type="text/javascript">
		var i = 0;
		$("#buttons").delegate(".l-button", "click", function() {
			switch (this.id) {
			case "warn":
				$.warn("这是一个警告");
				break;
			case "info":
				$.alert("这是一个信息提示\n这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告这是一个警告");
				break;
			case "error":
				$.error("这是一个错误提示");
				break;
			case "success":
				$.success("这是一个正确提示", function() {
					alert("确定")
				});
				break;
			case "confirm":
				$.confirm("是否退出?", function() {
					$.info("确定");
				}, function() {
					$.info("取消");
				});
				break;
			case "custtitle":
				$.alert("提示信息", "自定义标题");
				break;
			case "autoclose":
				$.error("这是一个错误提示,2秒自动关闭", null, null, 2000);
				break;
			case "tip":
				$.tip("您收到一条新的短消息" + (i++), "hello")
				break;
			case "tip2":
				$.tip("您收到一条新的短消息" + (i++), 2000)
				break;
			default:
				break;
			}
		});
	</script>

</snow:page>
