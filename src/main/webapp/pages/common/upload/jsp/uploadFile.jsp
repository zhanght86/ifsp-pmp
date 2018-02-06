<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="上传文件">
	<snow:groupbox id="group" label="请选择上传文件" expand="false">
<%-- 		<snow:sform name="uploadFileForm" flowId="" isSubFile="true" > --%>
			<form action="">
				<div style="padding: 5px;">
					<input type="file" name="upFile" width="100px" style="border: opx" size="100"/>
					<input id="submit" type="button" value="提交"> 
				</div>
			</form>
			
			
<%-- 		</snow:sform> --%>
	</snow:groupbox>
	<script>
		var a = $('#submit');
		debuggr;
		a.click(function(){
			alert(a);
		});
		a.click();
		$('#submit').onClick(function(){
			debugger;
// 		    var formData = new FormData($('form')[0]);
		    $.ajax({
		        url: '/ifsPMP/uploadServlet?chnlNo=8001&DISK_PATH=/home/tomcat/apache-tomcat-6.0.39/mchtPicDir',  //server script to process data
		        type: 'POST',
		        xhr: function() {  // custom xhr
		        	debugger;
		            myXhr = $.ajaxSettings.xhr();
		            if(myXhr.upload){ // check if upload property exists
		                myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
		            }
		            return myXhr;
		        },
		        //Ajax事件
		        beforeSend: beforeSendHandler,
		        success: completeHandler,
		        error: errorHandler,
		        // Form数据
		        data: "text",
		        //Options to tell JQuery not to process data or worry about content-type
		        cache: false,
		        contentType: false,
		        processData: false
		    });
		});
	</script>
</snow:page>