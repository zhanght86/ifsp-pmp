 
dwr.engine.setErrorHandler(function errh(errorString, exception) {
	if (typeof (exception) == "object") {
		if (exception.name == "GD0101") {
			// GD0101=操作员无此功能权限
			errAlert(errorString);
			setTimeout('returnRoot()', 2000);
			errAlert = function() {
			};
		} else if (exception.name == "GD0102") {
			// GD0102=操作员会话无效
			errAlert(errorString);
			setTimeout('returnRoot()', 2000);
			errAlert = function() {
			};
		} else if (exception.name == "GD0103") {
			// GD0103=此会话已经被其他操作员绑定
			errAlert(errorString);
			top.location.href = _application_root;
			errAlert = function() {
			};
		} else {
			errAlert(errorString);
		}
	} else {
		errAlert(errorString);
	}
	try {
		for (var i=0,l=_array_dataset.length;i<l;i++) {
			_array_dataset[i].disableControlCount=0;
		}
	} catch(e) {
		
	}
});

function returnRoot() {
	top.location.href = _application_root;
}

function useLoadingMessage(message) {
	var loadingMessage = message || "Loading";
	var w;
	dwr.engine.setPreHook(function() {
		w = $.waiting(loadingMessage, "");
	});

	dwr.engine.setPostHook(function() {
		w.close();
	});
}

function useLoadingImage() {
	var windowMask = $('<div class="l-window-mask-nobackground dwr-mask"><div class="l-loading"></div></div>').appendTo(document.body)
	dwr.engine.setPreHook(function() {
		windowMask.show();
	});
	dwr.engine.setPostHook(function() {
		windowMask.hide();
	});
}
