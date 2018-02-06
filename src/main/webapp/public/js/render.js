/*!
 * autoparse
 */
(function(g, $, undefined) {
	"use strict";
	var VERSION = 1.1;

	/** parse variables */
	var KEY_EXTRA = "extra", COMMON_EDITOR = "*";

	/** util variables */
	var DDIC = {}, KEY_LIGER_MANAGER = "manager", EVENT_NAME_SPLITOR = "_", KEY_OPTION = "data-options", VALUE_KEY = "key", TEXT_KEY = "value";

	var InitedUIs = [];
	var $log = $.noop;
	var util = g.UIUtil || (g.UIUtil = {
		loadFile : function(url, filetype) {
			var fileref;
			filetype = filetype || "css";
			if (filetype == "js") {
				fileref = document.createElement('script');
				fileref.setAttribute("type", "text/javascript");
				fileref.setAttribute("src", this.webpath(url));
			} else if (filetype == "css") {
				fileref = document.createElement('link');
				fileref.setAttribute("rel", "stylesheet");
				fileref.setAttribute("type", "text/css");
				fileref.setAttribute("href", this.webpath(url));
			}
			if (fileref) {
				var doc = g.document;
				var heads = doc.getElementsByTagName("head");
				if (heads.length) {
					heads[0].appendChild(fileref);
				} else {
					doc.documentElement.appendChild(fileref);
				}
			}

		},
		parseOptions : function(element) {
			var option = {};
			var option_str = element.getAttribute(KEY_OPTION);
			if (option_str) {
				if (option_str.indexOf("{") != 0) {
					option_str = "{" + option_str;
				}
				if (option_str.lastIndexOf("}") != option_str.length - 1) {
					option_str += "}";
				}
				try {
					option = Function("return " + option_str + ";")();
				} catch (e) {
					$log("data-options parse error:" + option_str);
					throw e;
				}
				element.removeAttribute(KEY_OPTION);
			}
			return option;
		},
		iconHTML : function(cls, defaultCls) {
			if (cls) {
				if (cls.indexOf("/") > -1) {
					return "<img src='" + this.webpath(cls) + "'>";
				} else {
					return "<i class=\"" + cls + "\"></i>";
				}
			} else {
				if (defaultCls) {
					return this.iconHTML(defaultCls);
				} else {
					return "";
				}
			}
		},
		join : function() {
			return Array.prototype.join.apply(arguments, [ EVENT_NAME_SPLITOR ]);
		},
		lazyCall : function(fn, timeout) {
			fn && setTimeout(fn, timeout || 1);
		},
		str_map : function(string, split1, split2, fn) {// a,A;b,B
			split1 = split1 || ",";
			split2 = split2 || ";";

			var strs = string.split(split2);
			var map = {};
			for (var i = 0; i < strs.length; i++) {
				var str = strs[i];
				var index = str.indexOf(split1);
				if (index > -1) {
					var key = str.substr(0, index);
					var value = str.substr(index + 1);
					if (fn) {
						if (fn(key, value) === false) {
							return map;
						}
					}
					map[key] = value;
				}
			}
			return map;
		},
		DDIC : function(key, values) {
			if (arguments.length === 1) {
				return DDIC[key] || [];
			} else if (arguments.length > 1 && values) {
				if (typeof values == "string") {
					var texts = [];
					var valArr = values.split(",");
					for (var i = 0, len = valArr.length; i < len; i++) {
						texts.push(DDIC[key]["$" + valArr[i]] || valArr[i]);
					}
					return texts.join(",");
				} else if (typeof values == "number") {
					return DDIC[key][values][VALUE_KEY] || "";
				} else if (values.constructor === Array) {
					if (!DDIC[key]) {
						DDIC[key] = values;
						for (var i = 0, len = values.length; i < len; i++) {
							var v = values[i];
							values["$" + v[VALUE_KEY]] = v[TEXT_KEY];
						}
					}
				} else {
					throw "param not match";
				}
			}
		},
		webpath : function(url) {
			var webroot = g._application_root;
			if (url) {
				if (url.indexOf("http") == 0) {
					return url;
				} else if (url.indexOf("/") == 0) {
					return webroot + url;
				} else {
					return webroot + "/" + url;
				}
			}
			return url;
		},
		format : function(source, params) {
			if (arguments.length === 1) {
				return source;
			}
			if (arguments.length > 2 && params.constructor !== Array) {
				params = Array.prototype.slice.apply(arguments, 1);
			}
			if (params.constructor !== Array) {
				params = [ params ];
			}
			$.each(params, function(i, n) {
				source = source.replace(new RegExp("\\{" + i + "\\}", "g"), function() {
					return n;
				});
			});
			return source;
		},
		guid : function() {
			var S4 = function() {
				return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
			};
			return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
		}
	});
	var i18nMap = {};
	var currentLang;
	var shouldInitChildren = {
		window : true,
		form : true,
		formpanel : true,
		layout : true,
		accordion : true,
		portal : true,
		tabs : true,
		toolbar : true
	};
	var ui = g.UIKit || (g.UIKit = {
		version : VERSION,
		defaultLanguage : 'zh_CN',
		lang : function(language, messages) {
			if (arguments.length === 1) {
				if (i18nMap[language]) {
					currentLang = language;
				} else {
					currentLang = this.defaultLanguage;
				}
				$.extend(ui.i18n, i18nMap[currentLang]);
			} else if (arguments.length > 1) {
				var l = i18nMap[language] || (i18nMap[language] = {});
				$.extend(l, messages || {});
			} else {
				return currentLang;
			}
		},
		i18n : {},
		properties : {},// ui组件的默认属性值
		rules : {},// 规则验证集，用于扩展
		extend : function(editor) {// 扩展组件
			$.extend(this.editors, editor);
		},
		event : {// grid event
			cursorChanged : {},
			onRefresh : {},
			onInsert : {},
			onDelete : {},
			onCanceled : {}
		},
		editors : {// 第三方组件
			"*" : {
				render : function(jq, options) {// 初始化+渲染
				},
				valueChanged : function(jq, dataset, record) {// 值改变事件
				},
				statusChanged : function(jq, status, value) {// 状态改变事件
				},
				columnOption : function(field, options) {// 表格中列属性
				}
			}
		}
	});

	function log(object) {
		// if (console && console.log) {
		// console.log(object);
		// }
	}

	function debugEnabled(bool) {
		if (bool !== false) {
			$log = log;
		} else {
			$log = $.noop;
		}
	}

	var ELEMENT_NODE = 1;
	function initElements(context) {
		var element = context || g.document.body;
		var initChildren = initElement(element);
		if (initChildren) {
			var childNodes = element.childNodes;
			var len = childNodes.length;
			var nodes = [];
			for (var i = 0; i < len; i++) {// warn: here use len, it"s may not
				// right when DOM nodes changed
				var childNode = childNodes[i];
				if (childNode.nodeType == ELEMENT_NODE) {
					if (childNode.tagName == "SCRIPT" || childNode.tagName == "STYLE" || childNode.tagName == "LINK") {
						continue;
					}
					nodes[nodes.length] = childNode;
				}
			}
			for (var i = 0; i < nodes.length; i++) {
				var node = nodes[i];
				initElements(node);
			}
		}
	}

	function initElement(element) {
		// $log("DEBUG: initElement " + element + " #" + element.id);
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		if (ui.editors[extra]) {
			var options = util.parseOptions(element);
			// jq.data("options", options);
			var manager = ui.editors[extra].render(jq, options);
			jq.manager(manager);
			InitedUIs.push(manager);
			// initEditorValidation(jq, options)
		}
		return !extra || shouldInitChildren[extra];
	}
	function initEditorValidation(jq, options) {
		var extra = jq[0].getAttribute(KEY_EXTRA);
		var validation = ui.editors[extra].validation || ui.editors[COMMON_EDITOR].validation;
		validation(jq, options);
	}
	function editorValueChangedHandler(extra) {
		return ui.editors[extra] ? ui.editors[extra].valueChanged || ui.editors[COMMON_EDITOR].valueChanged : null;
	}
	function editorStatusChangedHandler(extra) {
		return ui.editors[extra] ? ui.editors[extra].statusChanged || ui.editors[COMMON_EDITOR].statusChanged : null;
	}
	function notifyError(name, extra) {
		return function() {
			// $log("ERROR: " + name + " [" + extra + "] no reflect!");
		}
	}

	// when dataset cursor changed
	function _notifyDatasetCursorChanged(element, dataset, record, reserved) {
		if (dataset.type == 'dropdown')
			return;
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = ui.event.cursorChanged[extra] || editorValueChangedHandler(extra) || notifyError("_notifyDatasetCursorChanged", extra);
		handler(jq, dataset, record, reserved);
	}

	function _notifyDatasetBeforeUpdate(element, dataset, record, reserved) {
	}

	// when dataset state changed
	function _notifyDatasetStateChanged(element, dataset, record, field, options) {
		if (dataset.type == 'dropdown')
			return;
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = editorStatusChangedHandler(extra) || notifyError("_notifyDatasetStateChanged", extra);
		// if (options) {
		// for ( var state in options) {
		// handler(jq, field, state, options[state]);
		// }
		// } else {
		// handler(jq, field, "readonly", dataset.readOnly);
		// }
		var field = getElementField(element);
		if (field) {
			handler(jq, field, "readonly", dataset.readOnly || field.readOnly);
		}
	}

	// when insert record, only refresh grid
	function _notifyDatasetInsert(element, dataset, record, reserved) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = ui.event.onInsert[extra];
		handler && handler(jq, dataset, record, reserved[1], reserved[0]);
	}

	// when delete record
	function _notifyDatasetDelete(element, dataset, record, reserved) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = ui.event.onDelete[extra];
		handler && handler(jq, dataset, record);
	}

	// when load dataset
	function _notifyDatasetRefresh(element, dataset, record, reserved) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = ui.event.onRefresh[extra];
		handler && handler(jq, dataset, record);
		// _notifyDatasetStateChanged(element, dataset, record, reserved);
	}

	// when cancel record
	function _notifyDatasetRefreshRecord(element, dataset, record, reserved) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var handler = editorValueChangedHandler(extra) || notifyError("_notifyDatasetRefreshRecord", extra);
		handler(jq, dataset, record);
	}

	// when datafield value change, refresh grid cell & input value
	function _notifyFieldDataChanged(element, dataset, record, field, reserved) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var fieldid = element.getAttribute("dataField");
		if (!fieldid || (dataset.record == record && (field.fieldName == fieldid || field.fieldName == fieldid + "Name"))) {
			var handler = editorValueChangedHandler(extra) || notifyError("_notifyFieldDataChanged", extra);
			handler(jq, dataset, record, field);
		}
	}

	// when datafield readonly or required
	function _notifyFieldStateChanged(element, dataset, record, field, options) {
		var jq = $(element);
		var extra = element.getAttribute(KEY_EXTRA);
		var fieldid = element.getAttribute("dataField");
		if (!fieldid || (dataset.record == record && field.fieldName == fieldid)) {
			var handler = editorStatusChangedHandler(extra) || notifyError("_notifyFieldStateChanged", extra);
			for ( var state in options) {
				handler(jq, field, state, options[state]);
			}
		}
	}

	function unrender() {
		for (var i = 0, len = InitedUIs.length; i < len; i++) {
			if (InitedUIs[i]) {
				InitedUIs[i].destroy();
			}

		}
		InitedUIs = [];
	}
	$(window).bind("unload", function() {
		unrender();
		var jframe = $('iframe', g.document.body);
		if (jframe.length) {
			var frame = jframe[0];
			frame.src = "about:blank";
			try {
				frame.contentWindow.document.write('');
				frame.contentWindow.close();
			} catch (e) {
			}
			$.browser.msie && CollectGarbage();

			jframe.remove();
		}
	});

	$.extend(g, {
		$log : log,
		debugEnabled : debugEnabled,
		initElements : initElements,
		_notifyDatasetCursorChanged : _notifyDatasetCursorChanged,
		_notifyDatasetBeforeUpdate : _notifyDatasetBeforeUpdate,
		_notifyDatasetStateChanged : _notifyDatasetStateChanged,
		_notifyDatasetInsert : _notifyDatasetInsert,
		_notifyDatasetDelete : _notifyDatasetDelete,
		_notifyDatasetRefreshRecord : _notifyDatasetRefreshRecord,
		_notifyDatasetRefresh : _notifyDatasetRefresh,
		_notifyFieldDataChanged : _notifyFieldDataChanged,
		_notifyFieldStateChanged : _notifyFieldStateChanged
	});
})(window, jQuery);
// $(function() {
// debugEnabled();
// });
/*
 * ! 常用方法
 * 
 */
(function(g, $, undefined) {
	"use strict";
	var properties = g.UIKit.properties;
	var util = $.extend(g.UIUtil, {
		jqtype : function(datatype) {
			var mapping = {
				number : "number",
				byte : "number",
				short : "number",
				int : "number",
				long : "number",
				float : "number",
				double : "number",
				currency : "number",
				bigdecimal : "number",
				date : "date",
				timestamp : "date",
				time : "date",
				boolean : "boolean",
				string : "string"
			}
			return mapping[datatype] || "string";
		},
		obj_str : function(value, field) {
			var type = $.type(value);
			var str;
			switch (type) {
			case "date":
				if (value != 'Invalid Date') {
					var format;
					if (field.dataType == "date") {
						format = properties.dateViewFormat;// "yyyyMMdd";
					} else if (field.dataType == "timestamp") {
						format = properties.datetimeViewFormat;// "yyyyMMddHHmmss";
					} else if (field.dataType == "time") {
						format = properties.timeViewFormat;// "HHmmss";
					} else {
						format = properties.dateViewFormat;// "yyyyMMdd";
					}
					str = value.format(format);
				} else {
					str = "";
					throw "Invalid Date [" + value + "]";
				}
				break;
			case "undefined":
			case "null":
				str = "";
				break;
			case "number":
				str = isNaN(value) ? "" : ("" + value);
				break;
			default:// boolean
				str = "" + value;
				break;
			}
			return str;
		},
		str_obj : function(str, datatype) {
			var obj;
			switch (datatype) {
			case "date":
				str = str.replace(new RegExp("[^\\d]", "g"), "");
				if (str.length >= 8)
					obj = new Date(str.substr(0, 4) + "/" + str.substr(4, 2) + "/" + str.substr(6, 2));
				if (obj == 'Invalid Date') {
					obj == null;
					throw "Invalid Date[" + str + "]";
				}
				break;
			case "time":
				str = str.replace(new RegExp("[^\\d]", "g"), "");
				if (str.length >= 6)
					obj = new Date("1999/01/01 " + str.substr(0, 2) + "/" + str.substr(2, 2) + "/" + str.substr(4, 2));
				if (obj == 'Invalid Date') {
					obj == null;
					throw "Invalid Time[" + str + "]";
				}
				break;
			case "timestamp":
				str = str.replace(new RegExp("[^\\d]", "g"), "");
				if (str.length >= 14)
					obj = new Date(str.substr(0, 4) + "/" + str.substr(4, 2) + "/" + str.substr(6, 2) + " " + str.substr(8, 2) + ":" + str.substr(10, 2) + ":" + str.substr(12, 2));
				if (obj == 'Invalid Date') {
					obj == null;
					throw "Invalid Timestamp[" + str + "]";
				}
				break;
			case "int":
			case "short":
			case "long":
			case "float":
			case "double":
			case "byte":
			case "bigdecimal":
			case "number":
				obj = isNaN(parseFloat(str)) ? "" : (str * 1);
				break;
			case "currency":
				str = str.replace(/,/g, "");
				obj = isNaN(parseFloat(str)) ? "" : (str * 1);
				break;
			case "boolean":
				obj = str == "true";
				break;
			default:
				obj = str;
				break;
			}
			return obj;
		},
		getTypedValue : function(value, field) {
			var srctype = $.type(value);
			var type = this.jqtype(field.dataType);
			var result;
			if (srctype == type) {
				result = value;
			} else {
				var str = this.obj_str(value, srctype);
				result = this.str_obj(str, field.dataType);
			}
			return result;
		},
		getViewString : function(value, field) {
			var type = field.dataType;
			value = this.getTypedValue(value, field);
			if (!value && value !== 0) {
				return "";
			}
			var str;
			switch (type) {
			case "date":
				if (value != 'Invalid Date') {
					str = value.format(properties.dateFormat);
				} else {
					str = "";
					throw "Invalid Date [" + value + "]";
				}
				break;
			case "timestamp":
				if (value != 'Invalid Date') {
					str = value.format(properties.datetimeFormat);
				} else {
					str = "";
					throw "Invalid Date [" + value + "]";
				}
				break;
			case "int":
			case "short":
			case "long":
			case "float":
			case "double":
			case "byte":
			case "bigdecimal":
			case "number":
			case "currency":
				str = isNaN(value) ? "" : ("" + value);
				str = formatFloat(str, field.scale || 0);
				if (type == "currency") {
					str = properties.prefix + formatCurrency(str);
				}
				break;
			default:
				str = "" + value;
				if (value)
					str = value.replace(/\^p/g, "\n"); // 替换回车符
				break;
			}
			return str;
		}
	});

	var JQ_DATA_LIGER_MANAGER = "manager", JQ_DATA_REQUIRED_SPAN = "requriedSpan";
	$.fn.extend({
		manager : function(manager) {
			if (arguments.length == 0) {
				return this.data(JQ_DATA_LIGER_MANAGER);
			} else if (arguments.length == 1) {
				this.data(JQ_DATA_LIGER_MANAGER, manager);
			}
		},
		requireElement : function(jq) {
			if (arguments.length == 0) {
				return this.data(JQ_DATA_REQUIRED_SPAN);
			} else if (arguments.length == 1) {
				this.data(JQ_DATA_REQUIRED_SPAN, jq);
			}
		}
	});

	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"H+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		};
		if (/(y+)/.test(format))
			format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(format))
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		return format;
	};
})(window, jQuery);
/*
 * ! 组件渲染
 * 
 */
(function(g, $, undefined) {
	"use strict";
	// window.onerror = function(errorMessage, scriptURI, lineNumber) {
	// alert(errorMessage + ":" + scriptURI + ":" + lineNumber)
	// }
	var KEY_EXTRA = "extra";
	var EVENT_DISABLED = "$event_disabled";
	var OID = "__id";
	var UID_C = 0;

	/** Json Data Key */
	var MAP_KEY_ROWS = "Rows";
	var MAP_KEY_TOTAL = "Total";

	/** Tree Key */
	var TREE_KEY_HASCHILD = "_hasChild";
	var TREE_KEY_ID = "_id";
	var TREE_KEY_PID = "_parentId";
	var TREE_KEY_TEXT = "_text";
	var TREE_KEY_STATE = "_state";
	var TREE_KEY_CHECKED = "_checked";
	var TREE_KEY_STATUS = "_status";
	var TREE_KEY_ICON = "_icon";
	var TREE_KEY_CANSELECTED = "_canSelected";
	var TREE_KEY_CHILDREN = "children";

	var TAB_TICKET = "_ticket_";

	var util = g.UIUtil;

	function tmpDsId(suffix) {
		return "tmp_" + (UID_C++) + "_" + suffix;
	}

	var event_lock = 0;
	function fireonce(fn) {
		if (event_lock === 0) {
			event_lock++;
			fn();
			event_lock--;
			if (event_lock < 0)
				event_lock = 0;
		}
	}

	var ui = g.UIKit;
	var i18n = ui.i18n;
	// var i18n = $.extend(ui.i18n, {
	// sizeTooLang : "位数超长({0})",
	// required : "必须输入",
	// exportTitleSuffix : "导出",
	// exportMaxRecord : "联机在线导出允许的最大记录数为{0}条"
	// });
	var properties = $.extend(ui.properties, {
		maxDigits : 12,
		prefix : "",
		maxDate : new Date(2500, 0, 1),
		timeFormat : "HH:mm:ss",
		dateFormat : "yyyy-MM-dd",
		datetimeFormat : "yyyy-MM-dd HH:mm:ss",
		dateViewFormat : "yyyyMMdd",
		datetimeViewFormat : "yyyyMMddHHmmss"
	});

	var columnproperties = function(dataset, field, columnoptions) {
		ui.editors[field.editType] && ui.editors[field.editType].columnOption && ui.editors[field.editType].columnOption(dataset, field, columnoptions);
	}

	function ExportTabSet(tabs) {
		function _add(id, title, content, options) {
			content = content || "";
			options = options || {};
			var option = {
				tabid : id,
				text : title,
				content : content,
				showClose : true
			}
			if (options.closeable === false) {
				option.showClose = false
			}

			$.extend(option, options);
			if (content.indexOf("/") == 0) {
				content = g._application_root + content;
				option.content = "<div class='inner-iframe' url='" + content + "' >loading</div>";
			} else if (content.indexOf("http") == 0) {
				option.content = "<div class='inner-iframe' url='" + content + "' >loading</div>";
			}
			tabs.addTabItem(option);
		}
		function _select(id) {
			tabs.selectTabItem(id);
		}
		function _isExist(id) {
			return tabs.isTabItemExist(id);
		}
		function _close(id) {
			if (id == undefined) {
				id = _getSelectedId();
			}
			tabs.removeTabItem(id);
		}
		function _getSelectedId() {
			return tabs.getSelectedTabItemID();
		}
		function _getSelected() {
			return tabs.getSelectedTabItem();
		}
		function _closeAll() {
			tabs.removeAll();
		}
		function _closeOther(id) {
			if (id == undefined) {
				id = _getSelectedId();
			}
			tabs.removeOther(id);
		}
		function _closeLeft(id) {
			if (id == undefined) {
				id = _getSelectedId();
			}
			tabs.removeLeft(id);
		}
		function _closeRight(id) {
			if (id == undefined) {
				id = _getSelectedId();
			}
			tabs.removeRight(id);
		}
		function _setTitle(id, title) {
			if (arguments.length == 1) {
				tabs.setHeader(_getSelectedId(), id);
			} else if (arguments.length == 2) {
				tabs.setHeader(id, title);
			}
		}
		function _setIconCls(id, icon) {
			if (arguments.length == 1) {
				tabs.setIcon(_getSelectedId(), id);
			} else if (arguments.length == 2) {
				tabs.setIcon(id, icon);
			}
		}
		function _setEnable(id, isEnable) {
			if (arguments.length == 1) {
				tabs.setEnable(_getSelectedId(), id);
			} else if (arguments.length == 2) {
				tabs.setEnable(id, isEnable);
			}
		}
		function _setClosable(id, closable) {
			if (arguments.length == 1) {
				tabs.setClosable(_getSelectedId(), id);
			} else if (arguments.length == 2) {
				tabs.setClosable(id, closable);
			}
		}
		function _openTab(id, title, content, options) {
			if (_isExist(id)) {
				_select(id);
			} else {
				_add(id, title, content, options);
			}
		}
		function _refresh(id, url) {
			tabs.reload(id, url);
		}
		function _length() {
			return tabs.getTabItemCount();
		}
		return {
			// options : _options,
			add : _add,
			select : _select,
			isExist : _isExist,
			setEnable : _setEnable,
			setIconCls : _setIconCls,
			setTitle : _setTitle,
			setClosable : _setClosable,
			// getTitle : _getTitle,
			getSelected : _getSelected,
			// getSelectedIndex : _getSelectedIndex,
			getSelectedId : _getSelectedId,
			// getTab : _getTab,
			// getTabIndex : _getTabIndex,
			close : _close,
			closeAll : _closeAll,
			closeOther : _closeOther,
			closeRight : _closeRight,
			closeLeft : _closeLeft,
			refresh : _refresh,
			length : _length,
			// setParams : _setParams,
			// addParams : _addParams,
			open : _openTab
		// setActiveTabIndex : _select,
		// setActiveTab : _select
		};
	}

	$.extend(ui.event.cursorChanged, {
		grid : function(jq, dataset, record, reserved) {
			// if (jq.attr("fieldstr").indexOf("select")!==0) {
			// jq.jqxGrid("clearselection");
			// }
			// if (record) {
			// var rowid = record.$uid;
			// var rowIndex = jq.jqxGrid("getrowboundindexbyid", rowid);
			// jq.jqxGrid("selectrow", rowIndex);
			// }
			if (record) {
				if (!jq.manager().options.checkbox) {
					jq.manager().select(record.id);
				}

			}
		},
		tree : function(jq, dataset, record, reserved) {
			if (record) {
				jq.manager().selectNode(record.getValue(TREE_KEY_ID));
			}
		}
	});
	$.extend(ui.event.onInsert, {
		grid : function(jq, dataset, record, newRecord, mode) {
			var row = newRecord.$data, nextrow;
			row[OID] = newRecord.id;
			if (record) {
				nextrow = record.$data;
				nextrow[OID] = record.id;
			}

			switch (mode) {
			case "begin":
				if (dataset.firstUnit) {
					var firstrow = dataset.firstUnit.$data;
					firstrow[OID] = dataset.firstUnit.id;
					jq.manager().addRow(row, firstrow, true);
				} else {
					jq.manager().addRow(row);
				}
				break;
			case "before":
				jq.manager().addRow(row, nextrow, true);
				break;
			case "after":
				jq.manager().addRow(row, nextrow, false);
				break;
			case "end":
			default:
				jq.manager().addRow(row);
				break;
			}

		},
		tree : function(jq, dataset, record, newRecord, mode) {
			var row = newRecord.$data, nextrow = record.$data;
			row[OID] = newRecord.id;
			row[TREE_KEY_ID] = util.guid();
			row[TREE_KEY_TEXT] = "";
			// row[]
			// var TREE_KEY_HASCHILD = "_hasChild_";
			// var TREE_KEY_ID = "_id";
			// var TREE_KEY_PID = "_parentId";
			// var TREE_KEY_TEXT = "_text";
			// var TREE_KEY_CHECKED = "_checked";
			// var TREE_KEY_STATUS = "_status";
			// var TREE_KEY_ICON = "_icon";
			nextrow[OID] = record.id;
			var manager = jq.manager();
			var nearNode = record.getValue(TREE_KEY_ID);
			var parentNode = manager.getParentId(nearNode);
			switch (mode) {
			case "append":
				row[TREE_KEY_PID] = nextrow[TREE_KEY_PID];
				jq.manager().append(nearNode, [ row ]);
				break;
			case "before":
				jq.manager().append(parentNode, [ row ], nearNode, false);
				break;
			case "after":
				jq.manager().append(parentNode, [ row ], nearNode, true);
				break;
			default:
				jq.manager().append(parentNode, [ row ]);
				break;
			}

		}
	});
	$.extend(ui.event.onDelete, {
		grid : function(jq, dataset, record) {
			jq.manager().deleteRow(record.id);
		},
		tree : function(jq, dataset, record) {
			jq.manager().remove(record.$data[TREE_KEY_ID]);
		}
	});

	$.extend(ui.event.onRefresh, {
		grid : function(jq, dataset, record, reserved) {
			// var _dataset = getElementDataset(jq[0]);
			var json = dataset.toJson();
			// var source = {
			// localdata : [],
			// datatype : "array",
			// totalrecords : dataset.pageSize * dataset.pageCount
			// };
			var manager = jq.manager();
			manager.options.newPage = dataset.pageIndex;
			manager.set({
				data : json
			});
			if (manager.enabledCheckbox()) {
				$(".l-grid-hd-row", manager.gridview1).removeClass("l-checked");
			}
		},
		combogrid : function(jq, dataset) {
			var combobox = jq.manager();
			if (combobox && combobox.grid && jq.data("tmpds") == dataset.id) {
				var json = dataset.toJson();
				var source = {
					localdata : [],
					datatype : "array",
					totalrecords : 0
				};
				combobox.grid.set({
					data : json
				});
				combobox.dataLoaded = true;
			}
		},
		combotree : function(jq, dataset) {
			var manager = jq.manager();
			if (manager.tree && jq.data("tmpds") == dataset.id && !manager.remoteLoading) {
				var json = dataset.toJson();
				manager.treeManager.setData(json[MAP_KEY_ROWS]);
				manager.dataLoaded = true;
			}
		},
		tree : function(jq, dataset) {
			var manager = jq.manager();
			var json = dataset.toJson();
			manager.setData(json[MAP_KEY_ROWS]);
		}
	});

	function unC(fieldid) {
		var c = [];
		var i = fieldid.indexOf("[");
		if (i > -1) {
			c[0] = fieldid.substring(0, i);
			var width = fieldid.substring(i + 1, fieldid.length - 1);
			if (width.indexOf("%") > -1 || width.indexOf("px") > -1) {
				c[1] = width;
			} else {
				c[1] = parseFloat(width);
			}
		} else {
			c[0] = fieldid
		}
		return c;
	}

	// function getComboData(datafield) {
	// var keyValues = {};
	// var dataset = getDatasetByID(datafield + "_DropDownDataset");
	// if (dataset) {
	// if (dataset.state != "none" || !dataset.jsonData) {
	// var jsondata = dataset.toJson()[MAP_KEY_ROWS];
	// dataset.jsonData = jsondata;
	// for (var i = 0; i < jsondata.length; i++) {
	// dataset.jsonData["$" + jsondata[i].data] = jsondata[i].dataName;
	// }
	// dataset.state == "none";
	// }
	// return dataset.jsonData;
	// }
	// return [];
	// }
	// function getComboText(datafield, value) {
	// var combodata = getComboData(datafield);
	// return combodata["$" + value] || value;
	// }

	function fireUIValueChangeEvent(element, value) {
		if (!g._broadcasting) {
			$log('fireUIValueChangeEvent')
			var jq = $(element);
			var dataset = getElementDataset(element);
			var datafield = element.getAttribute("datafield");
			dataset.setValue(datafield, value);
		}
	}
	function fireUIValueChangeEvent2(element, value) {
		var jq = $(element);
		var dataset = getElementDataset(element);
		var datafield = element.getAttribute("datafield");
		dataset.setValue(datafield, value);
	}
	function fireUIValueChangeHandler(fn, args) {
		if (!g._broadcasting) {
			fn && fn.apply(this, args);
		}
	}

	ui.extend({
		"*" : {
			valueChanged : function(jq, dataset, record) {
				var fieldid = jq.attr("datafield");
				var value = record ? record.getValue(fieldid) : "";
				var manager = jq.manager();
				if (manager) {
					manager.setValue(value);
					validateElement(jq);
				}
			},
			statusChanged : function(jq, field, status, value) {
				switch (status) {
				case "required":
					// jq.requireElement().text(value ? "*" : "");
					jq.manager().setRequired(value);
					break;
				case "readonly":
					var mng = jq.manager();
					mng && mng.set("readonly", value);
					break;
				case "invalid":
					util.showErrTip(jq, value);
					break;
				}
			},
			columnOption : function(dataset, field, options) {

			},
			validation : function(jq, options) {
			}
		}
	});

	ui.extend({
		layout : {
			render : function(jq, options) {
				$.extend(options, {
					allowTopResize : false,
					allowBottomResize : false
				});
				jq.ligerLayout(options);
			}
		}
	});
	ui.extend({
		accordion : {
			render : function(jq, options) {
				$.extend(options, {
					changeHeightOnResize : true
				});
				jq.ligerAccordion(options);
			}
		}
	});
	ui.extend({
		tabs : {
			render : function(jq, options) {
				var id = jq.attr("id");
				options = $.extend({
					showSwitch : options.showswitch,
					ShowSwitchInTab : options.showswitch,
					changeHeightOnResize : true
				}, options, {
					onBeforeSelectTabItem : function(tabid, oldtabid) {
						if (tabid != oldtabid) {
							var eventName = util.join(jq.attr("id"), "beforeTabChange");
							if (window[eventName]) {
								return window[eventName](tabid, oldtabid);
							}
						}

					},
					onBeforeRemoveTabItem : function(tabid) {
						var eventName = util.join(jq.attr("id"), "beforeTabClose");
						if (window[eventName]) {
							return  window[eventName](tabid);
						}
						return true;
					},
					onAfterAddTabItem : function(tabid) {
						var g = this, p = this.options;
						var tabitem = $("li[tabid=" + tabid + "]", g.tab.links.ul);
						tabitem.attr(TAB_TICKET, util.guid());
					},
					onAfterSelectTabItem : function(tabid, oldtabid) {
						var g = this, p = this.options;
						var contentitem = $(".l-tab-content-item[tabid=" + tabid + "]", g.tab.content);
						var iframeholder = $("div.inner-iframe", contentitem);
						function triggerFocus() {
							try {
								var eventName = util.join(id, "onFocus");
								window[eventName] && window[eventName](tabid);
							} catch (e) {
							}
						}
						function triggerTagChange(tabid, oldtabid) {
							var eventName = util.join(jq.attr("id"), "afterTabChange");
							if (window[eventName]) {
								setTimeout(function() {
									window[eventName](tabid, oldtabid);
								}, 1);
							}
						}
						if (iframeholder.length) {
							var url = iframeholder.attr("url");
							var iframe = $("<iframe frameborder='0' style='width:100%;height:100%;'></iframe>");
							iframeholder.after(iframe);
							iframe.attr("src", url);
							iframe.bind('load.tab', function() {
								// iframe[0].contentWindow[id] =
								// iframe[0].contentWindow.parent[id];
								$(iframe[0].contentWindow).focus(triggerFocus).focus();
								triggerTagChange(tabid, oldtabid);
							});// .bind('focus.tab', triggerFocus);
							iframeholder.remove();
						} else {
							triggerFocus();
							if (tabid != oldtabid) {
								triggerTagChange(tabid, oldtabid);
							}
						}
					}
				});
				var tabs = jq.ligerTab(options);
				g[id] = ExportTabSet(tabs);
				return tabs;
			}
		}
	});
	ui.extend({
		menu : {
			render : function(jq, options) {
				options = $.extend({}, options);
				var menu = $.ligerMenu(options);
				var id = jq.attr("id");
				var eventName = util.join(id, "onClick");
				menu.bind("click", function(item) {
					fireUserEvent(eventName, [ jq[0], item ]);
				});
				g[id] = menu;
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {

			}
		}
	});
	ui.extend({
		form : {
			render : function(jq, options) {
				var toggle = jq.find(".togglebtn");
				toggle.toggle(function() {
					toggle.addClass("togglebtn-down");
					jq.find("form").slideUp("fast");
				}, function() {
					toggle.removeClass("togglebtn-down");
					jq.find("form").slideDown("fast");
				});
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {
			}
		}
	});
	ui.extend({
		listbox : {
			render : function(jq, options) {
				var manager = jq.ligerListBox(options);
				return manager;
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {
			}
		}
	});
	ui.extend({
		panel : {
			render : function(jq, options) {
				options = $.extend({}, options);
				var manager = jq.ligerPanel(options);
				return manager;

			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {

			}
		}
	});

	ui.extend({
		portal : {
			render : function(jq, options) {
				options = $.extend({
					draggable : true
				}, options);
				var manager = jq.ligerPortal(options);
				return manager;

			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {

			}
		}
	});

	function ExportWindow(win) {
		function _show() {
			win.show();
		}
		function _hide() {
			win.hide();
		}
		function _setTitle(title) {
			win.set({
				title : title
			});
		}
		return {
			open : _show,
			show : _show,
			close : _hide,
			hide : _hide,
			setTitle : _setTitle
		}
	}
	ui.extend({
		window : {
			render : function(jq, options) {
				$.extend(options, {
					target : jq,
					allowClose : options.closable,
					showMax : options.maximize,
					// showMin : options.minimize,
					showToggle : options.collapsible,
					isResize : options.resizable,
					isDrag : options.draggable
				});
				var id = jq.attr("id");

				var buttons = options.buttons;
				if (buttons) {
					if (typeof buttons == 'string') {
						var buttonClickEventName = util.join(id, "onButtonClick");
						buttons = buttons.split(",");
						var btns = options.buttons = [];
						for (var i = 0, len = buttons.length; i < len; i++) {
							var button;
							var realbutton = g[buttons[i]];
							if (realbutton && realbutton.tagName) {
								button = {
									id : realbutton.id,
									text : realbutton.getAttribute("desc"),
									icon : realbutton.getAttribute("icon"),
									onclick : (function(button) {
										return function(item, dialog) {
											button.click();
										}
									})(realbutton)
								}
								realbutton.allwaysHidden = true;
								btns.push(button);
							} else {
								// button = {
								// text : buttons[i],
								// onclick : (function(i) {
								// return function(item, dialog) {
								// fireUserEvent(buttonClickEventName, [ i,
								// g[id] ])
								// }
								// })(i)
								// }
								// btns.push(button);
							}

						}
					}
				}
				var win = $.ligerDialog(options);
				var exportwin = g[id] = ExportWindow(win);

				var openBeforeEventName = util.join(id, "beforeOpen");
				var closeBeforeEventName = util.join(id, "beforeClose");
				var openAfterEventName = util.join(id, "afterOpen");
				var closeAfterEventName = util.join(id, "afterClose");
				win.bind("beforeshow", function() {
					win.updatePosition();
					return g.fireUserEvent(openBeforeEventName, [ exportwin ]);
				});
				win.bind("aftershow", function() {
					// if(win.wintoggle &&
					// win.wintoggle.hasClass("l-dialog-extend")) {
					// win._saveStatus();
					// }
					return g.fireUserEvent(openAfterEventName, [ exportwin ]);
				});
				win.bind("beforehide", function() {
					return g.fireUserEvent(closeBeforeEventName, [ exportwin ]);
				});
				win.bind("afterhide", function() {
					// win.extend();
					var tips = liger.find('Tip');
					for (var i = 0, len = tips.length; i < len; i++) {
						var tip = tips[i];
						tip.remove();
					}
					jq.find(".l-text-invalid").removeClass("l-text-invalid");
					jq.find(".l-textarea-invalid").removeClass("l-textarea-invalid");
					return g.fireUserEvent(closeAfterEventName, [ exportwin ]);
				});
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {

			}
		}
	});

	function ExportTree(tree, ds, async) {
		function _refresh(_id, callback) {
			if (typeof id == 'function') {
				callback = _id;
				ds.flushData(1, callback);
			} else {
				if (async && _id) {
					tree.loadData(tree.getNodeDom(_id), "dwr", {
						_id : _id
					});
				} else {
					ds.flushData(1, callback);
				}

			}

		}
		function _exist(_id) {
			if (_id) {
				if (_id.getValue) {
					return _exist(_id.getValue(TREE_KEY_ID));
				} else {
					return !!tree.getDataByID(_id);
				}
			}
			return false;

		}
		function _expandAll() {
			tree.expandAll();
		}
		function _expandTo(_id) {
			_exist(_id) && tree.expandTo(_id);
		}
		function _expand(_id, level) {
			_exist(_id) && tree.expand(_id, level);
		}
		function _collapseAll() {
			tree.collapseAll();
		}
		function _collapse(_id) {
			_exist(_id) && tree.collapse(_id);
		}
		function _getSelected() {
			return ds.record;// tree.getSelected();
		}
		function _getChecked() {
			var nodes = tree.getChecked() || [];
			var records = [];
			for (var i = 0, len = nodes.length; i < len; i++) {
				records.push(ds.cachedData[nodes[i].data[OID]]);
			}
			return records;
		}
		function _getUnChecked() {
			var nodes = tree.getUnChecked() || [];
			var records = [];
			for (var i = 0, len = nodes.length; i < len; i++) {
				records.push(ds.cachedData[nodes[i].data[OID]]);
			}
			return records;
		}
		function _getIndeterminate() {
			var nodes = tree.getIndeterminate() || [];
			var records = [];
			for (var i = 0, len = nodes.length; i < len; i++) {
				records.push(ds.cachedData[nodes[i].data[OID]]);
			}
			return records;
		}
		function _select(_id) {
			if (_id) {
				if (_id.getValue) {
					ds.setRecord(_id);
				} else if (_id[TREE_KEY_ID]) {
					_select(_id[TREE_KEY_ID]);
				} else {
					var data = tree.getDataByID(_id);
					data && ds.setRecord(ds.cachedData[data[OID]]);
				}
			}

		}

		function _find(_id) {
			if (_id) {
				if (_id.getValue) {
					return _id;
				} else if (_id[TREE_KEY_ID]) {
					return _find(_id[TREE_KEY_ID]);
				} else {
					var data = tree.getDataByID(_id);
					return data ? ds.cachedData[data[OID]] : null;
				}
			}

		}

		function _checkAll() {
			tree.checkAll();
		}

		function _uncheckAll() {
			tree.uncheckAll();
		}
		function _check(_id, cascade) {
			if (_id) {
				if ($.type(_id) == 'array') {
					for (var i = 0, len = _id.length; i < len; i++) {
						_check(_id[i], cascade);
					}
				} else if (_id.getValue) {
					_check(_id.getValue(TREE_KEY_ID), cascade)
				} else if (_id[TREE_KEY_ID]) {
					_check(_id[TREE_KEY_ID], cascade);
				} else {
					_exist(_id) && tree.checkNode(_id, cascade);
				}
			}

		}

		function _uncheck(_id) {
			if (_id) {
				if ($.type(_id) == 'array') {
					for (var i = 0, len = _id.length; i < len; i++) {
						_uncheck(_id[i], cascade);
					}
				} else if (_id.getValue) {
					_uncheck(_id.getValue(TREE_KEY_ID), cascade)
				} else if (_id[TREE_KEY_ID]) {
					_uncheck(_id[TREE_KEY_ID], cascade);
				} else {
					_exist(_id) && tree.unCheckNode(_id, cascade);
				}
			}
		}
		function _getRoot() {
			return _getRoots()[0];
		}
		function _getRoots() {
			var nodes = tree.getRoots();
			var records = [];
			for (var i = 0, len = nodes.length; i < len; i++) {
				var rec = ds.cachedData[nodes[i][OID]];
				records.push(rec);
			}
			return records;
		}
		return {
			refresh : _refresh,
			getSelected : _getSelected,
			getChecked : _getChecked,
			getUnChecked : _getUnChecked,
			getIndeterminate : _getIndeterminate,
			select : _select,
			find : _find,
			checkAll : _checkAll,
			unCheckAll : _uncheckAll,
			check : _check,
			uncheck : _uncheck,
			expandAll : _expandAll,
			expandTo : _expandTo,
			expand : _expand,
			collapseAll : _collapseAll,
			collapse : _collapse,
			getRoot : _getRoot,
			getRoots : _getRoots
		// this.toggle = _toggle;
		// this.append = _append;
		// this.insert = _insert;
		// this.remove = _remove;
		// this.isLeaf = _isLeaf;
		// this.getParent = _getParent;
		// this.getRoot = _getRoot;
		// this.getRoots = _getRoots;
		}
	}

	function mergeRecords(sourceDataset, targetDataset, _id) {
		if (_id) {
			var datas = targetDataset.asyncData;
			if (datas[_id]) {
				for (var i = 0, len = datas[_id].length; i < len; i++) {
					pArray_delete(targetDataset, datas[_id][i]);
					delete targetDataset.cachedData[datas[_id][i].id];
				}
			}
			datas[_id] = [];
			pArray_insertArray(targetDataset, "end", null, sourceDataset);
			var rec = sourceDataset.firstUnit;
			while (rec) {
				datas[_id].push(rec);
				targetDataset.cachedData[rec.id] = rec;
				rec.dataset = targetDataset;
				rec = rec.nextUnit;
			}
		} else {
			pArray_insertArray(targetDataset, "end", null, sourceDataset);
			var rec = sourceDataset.firstUnit;
			while (rec) {
				targetDataset.cachedData[rec.id] = rec;
				rec.dataset = targetDataset;
				rec = rec.nextUnit;
			}
		}

	}
	ui.extend({
		tree : {
			render : function(jq, options) {
				var id = jq.attr("id");
				$.extend(options, {
					data : [],
					slide : false,
					idFieldName : TREE_KEY_ID,
					textFieldName : TREE_KEY_TEXT,
					parentIDFieldName : TREE_KEY_PID,
					isExpand : function(param) {
						return param.data[TREE_KEY_STATE] == "open";
					},
					checkbox : options.checkboxs === true,
					needCancel : false
				});
				if (options.contextmenu) {
					options.onContextmenu = function(node, e) {
						var cmenu = g[options.contextmenu];
						if (cmenu) {
							jq.manager().trigger("beforeSelect", [ node ]);
							cmenu.show({
								top : e.pageY,
								left : e.pageX
							});
							var eventName = util.join(id, "onContextmenu");
							cmenu.srcRecord = ds.cachedData[node.data[OID]];
							fireUserEvent(eventName, [ cmenu, cmenu.srcRecord ]);
						}
						return false;
					}
				}
				var ds = bindDataset(jq[0]);

				if (options.async) {
					$.extend(options, {
						isLeaf : function(data) {
							if (!data)
								return false;
							return !isTrue(data[TREE_KEY_HASCHILD]) && !(data[TREE_KEY_CHILDREN] && data[TREE_KEY_CHILDREN].length > 0);
						},
						delay : function(e) {
							var data = e.data;
							if (isTrue(data[TREE_KEY_HASCHILD]) && !(data[TREE_KEY_CHILDREN] && data[TREE_KEY_CHILDREN].length > 0)) {
								return {
									url : 'dwr',
									parms : {
										_id : data[options.idFieldName]
									}
								}
							} else {
								return false;
							}

						}
					})
				}
				var manager = jq.ligerTree(options);
				var ddstmp = copyDataset(tmpDsId(ds.id), ds.id);
				ddstmp.init = true;
				manager.bind("remoteLoad", function(param, success, error) {
					ddstmp.setParameter("_id", param._id);
					ddstmp.flushData(1, (function(_id) {
						return function() {
							var json = ddstmp.toJson();
							mergeRecords(ddstmp, ds, _id);
							success(json[MAP_KEY_ROWS]);
						}
					})(param._id), error);
					ddstmp.setParameter("_id", null);
					param = {};
				});
				manager.bind("beforeSelect", function(event) {
					if (g[options.contextmenu]) {
						g[options.contextmenu].hide();
					}
					var data = event.data;
					var target = event.target;
					if (data) {
						var record = ds.cachedData[data[OID]];
						var oldrecord = record.dataset.record;
						if (record != oldrecord) {
							var abort = fireUserEvent(util.join(id, "beforeSelect"), [ oldrecord, record, target ]);
							if (abort === false)
								return;
							record.dataset.setRecord(record);
						} else {
							// liger.unselect(rowid);
						}
					}
					return false;
				});
				manager.bind("select", function(event) {
					fireUserEvent(util.join(id, "onSelect"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				manager.bind("beforeCheck", function(data, target) {
					if (g[options.contextmenu]) {
						g[options.contextmenu].hide();
					}
					return fireUserEvent(util.join(id, "beforeCheck"), [ ds.cachedData[data[OID]], target ]);
				});
				manager.bind("beforeUnCheck", function(data, target) {
					if (g[options.contextmenu]) {
						g[options.contextmenu].hide();
					}
					return fireUserEvent(util.join(id, "beforeUnCheck"), [ ds.cachedData[data[OID]], target ]);
				});
				manager.bind("beforeCheckAll", function() {
					if (g[options.contextmenu]) {
						g[options.contextmenu].hide();
					}
					return fireUserEvent(util.join(id, "beforeCheckAll"));
				});
				manager.bind("beforeUnCheckAll", function() {
					if (g[options.contextmenu]) {
						g[options.contextmenu].hide();
					}
					return fireUserEvent(util.join(id, "beforeUnCheckAll"));
				});
				manager.bind("check", function(event) {
					fireUserEvent(util.join(id, "afterCheck"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				manager.bind("uncheck", function(data, target) {
					fireUserEvent(util.join(id, "afterUnCheck"), [ ds.cachedData[data[OID]], target ]);
				});
				manager.bind("afterCheckAll", function() {
					fireUserEvent(util.join(id, "afterCheckAll"));
				});
				manager.bind("afterUnCheckAll", function() {
					fireUserEvent(util.join(id, "afterUnCheckAll"));
				});
				manager.bind("beforeExpand", function(event) {
					fireUserEvent(util.join(id, "beforeExpand"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				manager.bind("expand", function(event) {
					fireUserEvent(util.join(id, "afterExpand"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				manager.bind("beforeCollapse", function(event) {
					fireUserEvent(util.join(id, "beforeCollapse"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				manager.bind("collapse", function(event) {
					fireUserEvent(util.join(id, "afterCollapse"), [ ds.cachedData[event.data[OID]], event.target ]);
				});
				g[id] = ExportTree(manager, ds, options.async);
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var value = record.getValue(TREE_KEY_ID);
					manager.update(value, record.$data);
				}
			},
			statusChanged : function(jq, field, status, value) {
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {

			}
		}
	});
	ui.extend({
		fieldtip : {
			render : function(jq, options) {
				var eventName = util.join(jq.attr("nm"), "onTip");
				if (isUserEventDefined(eventName)) {
					var opt = {};
					var lbl = fireUserEvent(eventName, [ jq[0], opt ]);
					if (lbl)
						jq.html(lbl);
					if (opt.wrap) {
						jq.addClass("form-field-tip-bottom");
					}
				}
				return null;
			}
		}
	});
	ui.extend({
		textbox : {
			render : function(jq, options) {
				bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));

				var manager = jq.ligerTextBox(options);
				manager.bind("changeValue", function(value) {
					// if (!manager.invalid) {
					fireUIValueChangeEvent2(this.element, value);
					// }
				});
				return manager;
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'textbox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="textbox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});
	ui.extend({
		textarea : {
			render : ui.editors.textbox.render,
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'textarea',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<textarea id="grid_editor_' + field.fieldName + '" extra="textarea" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '"></textarea>'
					}
				})
			}
		}
	});

	ui.extend({
		fieldlabel : {
			render : function(jq, options) {
				var label = jq.attr("label");
				var eventName = getElementEventName(jq[0], "onRefresh");
				if (isUserEventDefined(eventName)) {
					var lbl = fireUserEvent(eventName, [ jq[0], label ]);
					if (lbl)
						jq.html(lbl);
				} else {
					var field = getElementField(jq[0]);
					if (field) {
						label = field.label;
					}
					jq.html(label);
				}
			},
			valueChanged : function(jq, dataset, record) {
			},
			statusChanged : function(jq, status, value) {
			}
		}
	});

	ui.extend({
		datalabel : {
			render : function(jq, options) {
				bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));

			},
			statusChanged : function(jq, status, value) {
			},
			valueChanged : function(jq, dataset, record) {
				var fieldid = jq.attr("datafield");
				var value = record ? record.getValue(fieldid) : "";
				var eventName = util.join(jq.attr("id"), "onRefresh")
				if (g.isUserEventDefined(eventName)) {
					var v = g.fireUserEvent(eventName, [ jq[0], value ]);
					jq.html(v);
				} else {
					var field = dataset.getField(fieldid);
					var v = value;
					if (field.datasource && (field.datasource.indexOf("DDIC") > -1 || field.datasource.indexOf("LIST") > -1)) {
						v = util.DDIC(util.join(dataset.id, fieldid), value);
					} else {
						v = util.getViewString(value, field);
					}
					jq.html(v);

				}

			}
		}
	});

	function checkBeforeQuerySubmit(button) {
		var result = fireUserEvent(getElementEventName(button, "needCheck"), [ button ]);
		if (result === false) {
			return true;
		}
		return validateDataset(getElementDataset(button));
	}
	function checkRequireBeforeSubmit(button) {
		var result = fireUserEvent(getElementEventName(button, "needCheck"), [ button ]);
		if (result === false) {
			return true;
		}
		/* . */
		// modify
		var updateSize = isNaN(button.submitUpdateTotalListSize) ? 0 : button.submitUpdateTotalListSize;
		var noneSize = isNaN(button.noneListSize) ? 0 : button.noneListSize;
		var deleteSize = isNaN(button.deleteSize) ? 0 : button.deleteSize;
		if (updateSize == 0 && noneSize == 0 && deleteSize == 0) {
			errAlert(i18n.checkModify)
			return false;
		} else if (updateSize != 0) {
			var datasetAry = [];
			if (button.submitDataset) {
				var datasetStr = button.submitDataset.split(";");
				for (var j = 0; j < datasetStr.length; j++) {
					var _dataSet = getDatasetByID(datasetStr[j]);
					_dataSet.type == "result";
					datasetAry[datasetAry.length] = _dataSet;
				}
			} else {
				datasetAry = getDatasets();
			}
			var errInfo = "";// 
			var errInfo2 = "";// 
			var isValid = false;
			for (var k = 0; k < datasetAry.length; k++) {
				var dataset = datasetAry[k];
				var checkedResult = validateDataset(dataset);
				if (!checkedResult) {
					return false;
				}
			}
		}
		return true;
	}
	function translateRecord2Map(dataset, record) {
		var reply = {};
		var key;
		var value;
		for (var j = 0; j < dataset.fields.fieldCount; j++) {
			key = dataset.getField(j).fieldName;
			value = record.getString(key);
			reply[key] = value;
		}
		if (record.recordState == "new") {
			reply["recordState"] = "insert";
		} else {
			reply["recordState"] = record.recordState;
		}
		return reply;
	}
	function translateDataset2Bean(button) {
		var submitUpdateTotalListSize = 0;
		var deleteSize = 0;
		var noneListSize = 0;
		var beanMap = new Object();
		var mutilBeanMap = {};
		var datasetAry = [];
		var dataBusId = "";
		var btnDataset = getElementDataset(button);
		var funcId = button.funcId;
		var btnCqId = btnDataset.cqId;
		var btnDSPath = btnDataset.dspath;
		var btnId = button.id;
		// dataset
		if (button.submitDataset) {
			var datasetStr = button.submitDataset.split(";");
			for (var j = 0; j < datasetStr.length; j++) {
				var _dataSet = getDatasetByID(datasetStr[j]);
				_dataSet.type = "result";
				datasetAry[datasetAry.length] = _dataSet;
			}
		} else {
			datasetAry = getDatasets();
		}
		for (var k = 0; k < datasetAry.length; k++) {
			var dataset = datasetAry[k];
			var totalList = [];
			if (dataset.type == "result") {
				var record = dataset.firstUnit;
				while (record) {
					if (dataset.submitData == "allchange") {
						if (record.recordState != "none" && record.recordState != "discard") {
							var map = translateRecord2Map(dataset, record);
							totalList[totalList.length] = map;
							if (record.recordState != "delete") {
								submitUpdateTotalListSize++;
							}
							if (record.recordState == "delete") {
								deleteSize++;
							}
						}
					} else if (dataset.submitData == "current") {
						if (dataset.record == record) {
							var map = translateRecord2Map(dataset, record);
							totalList[totalList.length] = map;
							submitUpdateTotalListSize++;
						}
					} else if (dataset.submitData == "selected") {
						if (record.getValue("select")) {
							var map = translateRecord2Map(dataset, record);
							totalList[totalList.length] = map;
							submitUpdateTotalListSize++;
						}
					} else if (dataset.submitData == "all") {
						if (record.recordState != "discard") {
							var map = translateRecord2Map(dataset, record);
							totalList[totalList.length] = map;
							if (record.recordState != "none") {
								submitUpdateTotalListSize++;
							} else {
								noneListSize++;
							}
						}
					} else {
					}
					record = record.nextUnit;
				}
				var recordParMap = {};
				recordParMap = converDateSetParameter2Map_2(datasetAry[k]);
				var datasetid = dataset.id;
				if (dataBusId == "")
					dataBusId = dataset.databusId;
				datasetid = dataset.cqId;
				var files = [];
				var accept;
				var maxsize = 0;
				$(":file[datasetid='" + datasetid + "_dataset']").each(function(i) {
					if (!this.value) {
						return;
					}
					if (i == 0) {
						var t = $(this);
						var opts = t.data("data-options") || {};
						accept = opts.accept;
						maxsize = opts.maxsize;
					}

					if (maxsize > 0) {
						var fileSize = 0;
						if (this.files) {
							if (!this.files.length) {
								return;
							}
							fileSize = this.files[0].size;
						} else {
							var obj_img = $("<img>").appendTo(document.body);
							obj_img[0].dynsrc = this.value;
							fileSize = obj_img[0].fileSize;
						}
						if (fileSize > maxsize) {
							throw util.format(i18n.fileSizeTooLarge, maxsize, fileSize);
						}
					}
					if (accept) {
						var s = accept.replace("[*.]", ".*\\.").replace(",", "|")
						if (!this.value.test(s)) {
							throw util.format(i18n.fileSizeTooLarge, maxsize, fileSize);
						}
					}

					files.push(this);
				});
				var bean = {
					id : datasetid,
					paramMap : recordParMap,
					totalList : totalList,
					recodeIndex : 0,
					file : {
						maxsize : maxsize,
						accept : accept,
						files : files
					}
				};
				beanMap[datasetid] = bean;
			}

		}
		mutilBeanMap["databusId"] = dataBusId;
		var multiMap = {
			dsName : btnCqId,
			dsPath : btnDSPath,
			// updCqId : btnCqId,
			updateResult : beanMap,
			paramMap : mutilBeanMap,
			updBtnId : btnId,
			funcId : funcId
		};
		button.submitUpdateTotalListSize = submitUpdateTotalListSize;
		button.noneListSize = noneListSize;
		button.deleteSize = deleteSize;
		return multiMap;
	}

	function megerURL(applicationRoot, url) {
		var v_url = url;
		if (v_url && v_url.length > 0) {
			var s = v_url.substring(0, 1);
			if (s == '/') {
				return applicationRoot + v_url;
			} else {
				return applicationRoot + "/" + v_url;
			}
		} else {
			return applicationRoot + "/";
		}
	}
	function _button_click(button) {

		if (button.defaultOperation) {
			// set_cur_event_button(true);
			switch (button.defaultOperation.toLowerCase()) {
			case "submitform":
				var dataset = g.getDatasetByID(button.datasetid);
				var form = document.createElement("FORM");
				form.method = "post";
				if (button.url) {
					form.action = megerURL(g._application_root, _transDataActionURL);
				} else {
					form.action = g._application_root + "#";
				}
				form.style.visibility = "hidden";
				for (var i = 0; i < dataset.fields.fieldCount; i++) {
					form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + dataset.getField(i).fieldName + "\" >");
				}
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"_button_id\" >");
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"CQId\" >");
				document.body.appendChild(form);
				for (var i = 0; i < dataset.fields.fieldCount; i++) {
					form[dataset.getField(i).fieldName].value = dataset.getString(i);
				}
				form["_button_id"].value = button.id;
				form["CQId"].value = dataset.cqId;
				form.submit();
				break;
			case "asyncsubmit":
				var bean = translateDataset2Bean(button);
				var checkAnswer = checkRequireBeforeSubmit(button);
				if (checkAnswer) {
					var updateClass = button.updateclass;
					var targetFrame = button.targetFrame == "" ? "_self" : button.targetFrame;
					CommonQueryUpdateProcess.savaOrUpdate(bean, function(result) {
						var resultBean = result;
						if (resultBean.resCd == '000000') {
							try {
								var buttonReturnParam = resultBean.returnParam;
								button.returnParam = buttonReturnParam;
								var _restlt = true;
								_restlt = fireUserEvent(getElementEventName(button, "postSubmit"), [ button, buttonReturnParam ]);
								var url = button.url;
								var path;
								if (url != "" && url != "#") {
									path = util.webpath(url);
								} else if (url == "#") {
									path = "#";
								} else {
									path = _successURL;
								}
								if (path != "#") {
									if (targetFrame == "_self") {
										window.location.href = path;
									} else {
										window.open(path, targetFrame);
									}
								}
							} catch (e) {
							} finally {
								_resetRecordState2(button);
							}
							// for background validate
						} else if (resultBean.resCd == '111111') {
							var buttonReturnParam = resultBean.paramMap;
							var dataset = getDatasetByID(button.datasetid);
							for ( var property in buttonReturnParam) {
								if (!buttonReturnParam.hasOwnProperty(property))
									continue;
								dataset.setFieldInvalid(property, buttonReturnParam[property]);
							}
							return;
						} else {
							var err = new Error(result.resMsg);
							err.name = result.resCd;
							var abort = fireUserEvent(getElementEventName(button, "postException"), [ button, err ]);
							if (abort !== false) {
								throw err;
							}
						}
					});
				}
				break;
			case "asyncqrysubmit":
				var dataset = getDatasetByID(button.datasetid);
				if (!checkBeforeQuerySubmit(button))
					return;
				var _paramMap = new Object();
				_paramMap = converDateSet2Map(dataset);
				_paramMap = converDateSetParameter2Map(dataset, _paramMap);
				CommonQueryResultProcess.processAsyncBean(_paramMap, function(result) {
					var resultBean = result;
					resultBean.url = g._application_root + button.url;
					if (resultBean.resCd != '000000') {
						var err = new Error(resultBean.resMsg);
						err.name = resultBean.resCd;
						throw err;
						// errAlert(resultBean.resMsg);
					} else {
						if (resultBean.pageCount == 0) {
							wrnAlert(constNoFoundRecode);
							return;
						}
						/** set param . */
						converStr2DataSetParameter(resultBean.parameterString, dataset);
						var form = document.createElement("FORM");
						form.method = "post";
						if (button.url) {
							// form.action =
							// g._application_root +
							// button.url;
							form.action = megerURL(g._application_root, button.url);
						} else {
							form.action = g._application_root + "#";
						}
						form.style.visibility = "hidden";
						for (var i = 0; i < dataset.fields.fieldCount; i++) {
							form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + dataset.getField(i).fieldName + "\"  value=\"" + dataset.getString(i) + "\">");
						}
						var pId, pVal;
						var paramStr = converDateSetParameter2Str(dataset);
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"_paramStr_\" value=\"" + paramStr + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"CQId\" value=\"" + resultBean.cqId + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"fieldString\" value=\"" + resultBean.fieldString + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"recordString\" value=\"" + resultBean.recordString + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"recordOrigString\" value=\"" + resultBean.recordOrigString + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"pageCount\" value=\"" + resultBean.pageCount + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"pageIndex\" value=\"" + resultBean.pageIndex + "\">");
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"pageSize\" value=\"" + resultBean.pageSize + "\">");
						document.body.appendChild(form);
						form.submit();
					}
				});
				break;
			case "addrecord":
				// checkBeforeQuerySubmit(button);
				var dataset = getDatasetByID(button.datasetid);
				dataset.insertRecord("end");
				fireUserEvent(getElementEventName(button, "onClick"), [ button ]);
				break;
			case "none":
				fireUserEvent(getElementEventName(button, "onClick"), [ button ]);
				break;
			case "delrecord":
				var dataset = getDatasetByID(button.datasetid);
				var count2 = dataset.getRealRecordCounts();
				if (count2 != 0) {
					dataset.deleteRecord();
				} else {
				}
				break;
			case "asyncqrysubmitflush":
				var dataset = getDatasetByID(button.datasetid);
				if (!checkBeforeQuerySubmit(button))
					return;
				var resultDataset = getDatasetByID(button.resultDataset);
				copyDateSetParameter(dataset, resultDataset);
				for (var i = 0, len = dataset.fields.length; i < len; i++) {
					if (dataset.fields[i].fieldName) {
						var fieldid = dataset.getField(i).fieldName;
						resultDataset.setParameter(fieldid, dataset.getString(fieldid));
					}
				}
				resultDataset.flushed = false;// cacheCount
				resultDataset.flushData(1);
				break;
			case "asyncqrysubmitreset":
				var dataset = getDatasetByID(button.datasetid);
				dataset.clearData();
				break;
			case "href":
				if (button.url) {
					/* shen_antonio 20080308. */
					var targetFrame = button.targetFrame == "" ? "_self" : button.targetFrame;
					/* . */
					// var path = g._application_root +
					// "/"+button.url;
					var path = megerURL(g._application_root, button.url);
					if (targetFrame == "_self") {
						window.location.href = path;
					} else {
						window.open(path, targetFrame);
					}
				}
				break;
			case "modesubmit":
				var dataset = getDatasetByID(button.componentDataset);
				/* shen_antonio 20080121. */
				var targetFrame = button.targetFrame == "" ? "_self" : button.targetFrame;
				/* . */
				for (var i = 0; i < dataset.fields.fieldCount; i++) {
					// form.insertAdjacentHTML("beforeEnd",
					// "<input
					// type=\"hidden\"
					// name=\""+dataset.getField(i).fieldName+"\"
					// >");
				}
				window.showModalDialog(megerURL(g._application_root, button.url), "", "dialogHeight:600px; dialogWidth:600px; status:no; help:no; scroll:auto");
				break;
			/* added by wangpeng 20091116 BMS-2274 begin */
			case "delrecordasysubmit":
				var dataset = getDatasetByID(button.componentDataset);
				var count2 = dataset.getRealRecordCounts();
				if (count2 != 0) {
					var bean = translateDataset2Bean(button);
					var needSubmit = checkDelRecordSubmitNeeded(button);
					var confirmSubmit = true;
					if (needSubmit) {
						confirmSubmit = checkBeforeDelRecordSubmit(button);
					} else {
						dataset.deleteRecord();
						break;
					}
					if (confirmSubmit) {
						// dataset.deleteRecord();
						if (needSubmit) {
							var updateClass = button.updateclass;
							var targetFrame = button.targetFrame == "" ? "_self" : button.targetFrame;
							CommonQueryUpdateProcess.savaOrUpdate(updateClass, bean, function(result) {
								var resultBean = result;
								if (resultBean.resCd == '000000') {
									try {
										var editDataset = getDatasetByID(button.componentDataset);
										editDataset.deleteRecord();
										var buttonReturnParam = resultBean.paramMap;
										button.returnParam = buttonReturnParam;
										var _restlt = true;
										_restlt = fireUserEvent(getElementEventName(button, "postSubmit"), [ button ]);
										var url = button.url;
										var path;
										if (url != "" && url != "#") {
											path = megerURL(g._application_root, url);
										} else if (url == "#") {
											path = "#";
										} else {
											path = _successURL;
										}
										if (path != "#") {
											if (targetFrame == "_self") {
												window.location.href = path;
											} else {
												window.open(path, targetFrame);
											}
										}
									} catch (e) {
									} finally {
										_resetRecordState2(button);
									}
								} else {
									var err = new Error(result.resMsg);
									err.name = result.resCd;
									throw err;
								}
							});
						}
					}
				}
				break;
			}
		} else {
			fireUserEvent(getElementEventName(button, "onClick"), [ button ]);
		}
	}
	ui.extend({
		button : {
			render : function(jq, options) {
				// if (!options.width) {
				// options.width = jq.actual("outerWidth")
				// }
				$.extend(options, {
					text : options.desc
				});
				var button = jq[0];
				var buttonid = button.id;
				var datasetid = button.datasetid = jq.attr("datasetid");
				button.url = options.url;
				button.updateclass = options.updateClass;
				button.flowid = options.flowid;
				button.resultDataset = options.resultDataset;
				button.submitDataset = options.submitDataset;
				button.targetFrame = options.targetFrame;
				button.defaultOperation = options.type;
				button.funcId = options.funcId;

				options.click = function(event) {
					var doClick = _button_click;
					function commit() {
						doClick(button);
					}
					if (new Date().getTime() - ($.data(this, "clickedtime") || 0) > 250) {
						var button = jq[0];
						try {
							if (g.isUserEventDefined(button.id + "_" + "onClickCheck")) {

								var result = g.fireUserEvent(button.id + "_" + "onClickCheck", [ button, commit ]);
								if (result === true) {
									doClick(button);
									doClick = $.noop;
								} else {
									return false;
								}
							} else {
								_button_click(button);
							}
						} catch (e) {
							processException(e);
						} finally {
							// set_cur_event_button(false);
						}

					} else {
						$log("WARN:button click too fast.");
					}
					$.data(this, "clickedtime", new Date().getTime());
				};
				// var id = jq.attr("id");
				var manager = jq.ligerButton(options);
				jq.data("clickedtime", new Date().getTime());
				button.setDisabled = function(f) {
					if (f) {
						manager.setDisabled();
						if (datasetid) {
							$("div[datasetid='" + datasetid + "']  .l-toolbar-item[toolbarid=" + buttonid + "]").addClass("l-toolbar-item-disable");
						}
					} else {
						manager.setEnabled();
						if (datasetid) {
							$("div[datasetid='" + datasetid + "']  .l-toolbar-item[toolbarid=" + buttonid + "]").removeClass("l-toolbar-item-disable");
						}
					}
				}
				button.setTitle = function(text) {

				}
				button.setIcon = function(icon) {

				}
				button.setHidden = function(f) {
					if (f) {
						button.style.display = "none";
						if (datasetid) {
							$("div[datasetid='" + datasetid + "']  .l-toolbar-item[toolbarid=" + buttonid + "]").hide();
							var ref = $("div[refid='" + buttonid + "']");
							ref.hide();
							// var toolbar = ref.closest(".l-dialog-buttons");
							// if (!toolbar.find(".l-dialog-btn:visible")[0]) {
							// toolbar.hide();
							// }

						}
					} else {
						if (!button.allwaysHidden) {
							button.style.display = "";
						}
						if (datasetid) {
							$("div[datasetid='" + datasetid + "']  .l-toolbar-item[toolbarid=" + buttonid + "]").show();
							var ref = $("div[refid='" + buttonid + "']");
							// var toolbar = ref.closest(".l-dialog-buttons");
							ref.show();
							// toolbar.show();
						}
					}
				}
				return manager;
			}
		}
	});
	ui.extend({
		password : {
			render : function(jq, options) {
				bindDataset(jq[0]);

				options = $.extend({
					disabled : options.readonly
				}, options);
				delete options.readonly;

				// jq.requireElement(jq.next("span.required"));
				var manager = jq.ligerTextBox(options);
				manager.bind('changeValue', function(value) {
					fireUIValueChangeEvent2(this.element, value);
				});
				return manager;
			}
		}
	});
	ui.extend({
		filebox : {
			render : function(jq, options) {

			}
		}
	});
	ui.extend({
		numberbox : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));
				if (ds) {
					var field = getElementField(jq[0]);
					if (field) {
						if (field.scale) {
							$.extend(options, {
								number : true
							});
						} else {
							$.extend(options, {
								digits : true
							});
						}
						if (field.dataType == 'currency') {
							$.extend(options, {
								currency : true,
								digits : false,
								number : false
							});
						}
					}
				}

				var manager = jq.ligerTextBox(options);
				ds && manager.bind("changeValue", function(value) {
					// if (!manager.invalid) {
					fireUIValueChangeEvent2(this.element, value);
					// }
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value);
					manager.checkValue();
					validateElement(jq);
				}
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'numberbox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="numberbox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});
	ui.extend({
		integerbox : ui.editors.numberbox,
		doublebox : ui.editors.numberbox,
		currencybox : ui.editors.numberbox
	});
	ui.extend({
		datebox : {
			render : function(jq, options) {
				var dataset = bindDataset(jq[0]);
				options = $.extend({
					format : properties.dateFormat
				}, options);
				// jq.requireElement(jq.next("span.required"));
				var manager = jq.ligerDateEditor(options);
				manager.bind("changedate", function(value) {
					fireUIValueChangeEvent(this.element, value);
					validateElement($(this.element));
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value ? value.format(properties.dateFormat) : '');
				}
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'datebox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="datebox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});
	ui.extend({
		datetimebox : {
			render : function(jq, options) {
				bindDataset(jq[0]);
				options = $.extend({
					showTime : true
				}, options);
				// jq.requireElement(jq.next("span.required"));
				var manager = jq.ligerDateEditor(options);
				manager.bind("changedate", function(value) {
					fireUIValueChangeEvent(this.element, value ? value : "");
					validateElement($(this.element));
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value ? value.format(properties.datetimeFormat) : '');
				}
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'datetimebox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="datetimebox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});

	ui.extend({
		radiobox : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				jq.requireElement(jq.next("span.required"));
				// jq.bind("click", function() {
				// var fieldid = jq.attr("datafield");
				// var checked = ds.getValue(fieldid);
				// fireUIValueChangeEvent(this, !checked);
				// })
				// if (options.readonly) {
				// jq.prop("disabled", true);
				// }
				var manager = jq.ligerRadio(options);
				manager.bind("change", function(value) {
					fireUIValueChangeEvent(jq[0], value);
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : false;
					// jq.prop("checked", isTrue(value))
					manager.setValue(value);
				}
			},
			// statusChanged : function(jq, field, status, value) {
			// switch (status) {
			// case "required":
			// jq[0].requiredspan && jq[0].requiredspan.text(value ? "*" : "");
			// break;
			// case "readonly":
			// jq.prop("disabled", value);
			// break;
			// case "invalid":
			// util.showErrTip(jq, value);
			// break;
			// }
			// },
			statusChanged : function(jq, field, status, value) {
				switch (status) {
				case "required":
					jq.requireElement().text(value ? "*" : "");
					break;
				case "readonly":
					var mng = jq.manager();
					mng && mng.set("readonly", value);
					break;
				case "invalid":
					util.showErrTip(jq, value);
					break;
				}
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'radiobox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<div id="grid_editor_' + field.fieldName + '" extra="radiobox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '"></div>'
					}
				})
			}
		}
	});
	ui.extend({
		radioboxs : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				jq.requireElement(jq.next("span.required"));
				var id = jq.attr("id");
				var field = getElementField(jq[0]);
				var ddicKey = util.join(ds.id, field.fieldName);
				$.extend(options, {
					disabled : options.readonly,
					data : util.DDIC(ddicKey),
					rowSize : options.cols || 2,
					valueFieldID : tmpDsId("checkbox_val"),
					valueField : "key",
					textField : "value"
				});
				var manager = jq.ligerRadioList(options);
				manager.bind("click", function(value) {
					fireUIValueChangeEvent(jq[0], value);
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value);
				}
			},
			statusChanged : function(jq, field, status, value) {
				switch (status) {
				case "required":
					jq.requireElement().text(value ? "*" : "");
					break;
				case "readonly":
					jq.manager().set("disabled", value);
					break;
				case "invalid":
					util.showErrTip(jq, value);
					break;
				}
			},
			columnOption : function(dataset, field, options) {
				field.multiple = false;
				$.extend(true, options, {
					editor : {
						type : 'combobox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combobox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});
	ui.extend({
		checkbox : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// if (options.readonly) {
				// jq.prop("disabled", true);
				// }
				// jq.requireElement(jq.next("span.required"));
				// ds && jq.bind("click", function() {
				// fireUIValueChangeEvent(this, this.checked);
				// })

				jq.requireElement(jq.next("span.required"));
				var manager = jq.ligerCheckBox(options);
				ds && manager.bind("change", function(value) {
					fireUIValueChangeEvent(jq[0], value);
				})
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : false;
					// jq.prop("checked", isTrue(value))
					manager.setValue(value);
				}
			},
			statusChanged : function(jq, field, status, value) {
				switch (status) {
				case "required":
					jq.requireElement().text(value ? "*" : "");
					break;
				case "readonly":
					var mng = jq.manager();
					mng && mng.set("readonly", value);
					break;
				case "invalid":
					util.showErrTip(jq, value);
					break;
				}
			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'checkbox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<div id="grid_editor_' + field.fieldName + '" extra="checkbox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '"></div>'
					}
				})
			}
		}
	});

	ui.extend({
		checkboxs : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				jq.requireElement(jq.next("span.required"));
				var id = jq.attr("id");
				var field = getElementField(jq[0]);
				var ddicKey = util.join(ds.id, field.fieldName);
				$.extend(options, {
					disabled : options.readonly,
					data : util.DDIC(ddicKey),
					rowSize : options.cols || 2,
					valueFieldID : tmpDsId("checkbox_val"),
					valueField : "key",
					textField : "value"
				});
				var manager = jq.ligerCheckBoxList(options);
				manager.bind("click", function(value) {
					fireUIValueChangeEvent(jq[0], value);
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value);
				}
			},
			statusChanged : function(jq, field, status, value) {
				switch (status) {
				case "required":
					jq.requireElement().text(value ? "*" : "");
					break;
				case "readonly":
					jq.manager().set("disabled", value);
					break;
				case "invalid":
					util.showErrTip(jq, value);
					break;
				}
			},
			columnOption : function(dataset, field, options) {
				field.multiple = true;
				$.extend(true, options, {
					editor : {
						type : 'combobox',
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combobox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});

	var combo_check_ing = false;
	ui.extend({
		combobox : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// bindDropdownDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));

				var field = getElementField(jq[0]);
				var multiple = false;
				var autocomplete = false;
				if (field) {
					multiple = field.multiple;
					// autocomplete = field.editable;
				}

				var ddicKey = util.join(ds.id, field.fieldName);
				$.extend(options, {
					disabled : options.readonly,
					data : util.DDIC(ddicKey),
					isMultiSelect : multiple,
					autocomplete : autocomplete,
					valueField : 'key',
					textField : 'value'
				});

				var manager = jq.ligerComboBox(options);
				manager.bind("selected", function(value) {
					if (!g._broadcasting) {
						var eventName = util.join(ds.id, field.fieldName, "onSelect");
						fireUserEvent(eventName, [ value ]);
						validateElement(jq);
					}
					fireUIValueChangeEvent(this.element, multiple ? value.split(";").join(";") : value);
				});
				return manager;
			},
			valueChanged : function(jq, dataset, record) {
				var manager = jq.manager();
				if (manager) {
					// var options = manager.options;
					var fieldid = jq.attr("datafield");
					var value = record ? record.getValue(fieldid) : "";
					manager.setValue(value);
				}

			},
			columnOption : function(dataset, field, options) {
				$.extend(true, options, {
					editor : {
						type : 'combobox',
						autoheight : true,
						options : {
							readonly : field.readOnly,
							required : field.required
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combobox" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '">'
					}
				})
			}
		}
	});
	ui.extend({
		combotree : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));
				var dds = bindDropdownDataset(jq[0]);
				var ddstmp = copyDataset(tmpDsId(dds.id), dds.id);
				bindDatasetEditor(ddstmp, jq[0]);
				jq.data("tmpds", ddstmp.id);
				var dropdown = getDropDownByID(jq.attr("dropdownid"));
				var datafield = jq.attr("datafield");
				var viewfieldstr = options.viewfield;
				var viewfield = viewfieldstr.split(",")[0] || TREE_KEY_TEXT;
				var multi = options.multiple;
				// var oid = "_id", pid = "_pid";
				var fieldmap = util.str_map(options.fieldmap, "=");
				$.extend(options, {
					disabled : options.readonly,
					valueField : fieldmap[datafield] || OID,
					textField : viewfield,
					selectBoxWidth : 300,
					selectBoxHeight : 240,
					tree : {
						data : [],
						idFieldName : TREE_KEY_ID,
						textFieldName : viewfield,
						slide : false,
						isExpand : 1,
						parentIDFieldName : TREE_KEY_PID,
						// switchPageSizeApplyComboBox : true,
						checkbox : multi
					// isLeaf : function(data) {
					// if (!data)
					// return false;
					// return !isTrue(data._hasChild_);
					// },
					// delay : function(e) {
					// var data = e.data;
					// if (isTrue(data._hasChild_)) {
					// return {
					// url : 'dwr',
					// param : data._id
					// }
					// } else {
					// return false;
					// }
					//
					// }
					}
				});
				// ddstmp.setParameter("async", true);
				if (options.async || dds.async) {
					$.extend(options.tree, {
						isLeaf : function(data) {
							if (!data)
								return false;
							return !isTrue(data[TREE_KEY_HASCHILD]);
						},
						delay : function(e) {
							var data = e.data;
							if (isTrue(data[TREE_KEY_HASCHILD])) {
								return {
									url : 'dwr',
									parms : {
										_id : data[TREE_KEY_ID]
									}
								}
							} else {
								return false;
							}

						}
					})
				}
				var searchfieldstr = options.searchfield;
				if (searchfieldstr) {
					var searchfields = searchfieldstr.split(",");
					var conditions = [];
					for (var i = 0, len = searchfields.length; i < len; i++) {
						var fid = searchfields[i];
						if (fid) {
							var f = dds.getField(fid);
							var condition = {
								name : fid,
								label : f.label,
								width : f.width || 100,
								type : 'text'
							};
							conditions.push(condition);
						}

					}
					options.condition = {
						fields : conditions
					};
				} else {
					dropdown.init = true;
				}
				// 增加初始化设置
				var eventName = util.join(ds.id, datafield, "init");
				var event_result = fireUserEvent(eventName, [ options ]);

				var combo = jq.ligerComboBox(options);

				// function _loadData() {
				// var json = ddstmp.toJson();
				// combo.treeManager.setData(json[MAP_KEY_ROWS]);
				// combo.dataloaded = true;
				// }
				function _selectRow(combo) {
					var tree = combo.treeManager;

					var initvalue = ds.getValue(datafield);
					var initvalueMap = {};
					// combo.clearBox();
					if (multi) {
						tree.uncheckAll();
					}
					combo.disableEvent("select");
					if (initvalue) {
						var initvalues = initvalue.split(",");
						for (var i = 0, len = initvalues.length; i < len; i++) {
							initvalueMap[initvalues[i]] = true;
						}
						var dropdownKey = fieldmap[datafield] || TREE_KEY_ID;
						if (multi) {

							combo.disableEvent("check");
							for (var i = 0, len = initvalues.length; i < len; i++) {
								tree.checkNode(initvalues[i]);
							}
							combo.enableEvent("check");
						} else {
							tree.selectNode(function(data) {
								return initvalueMap[data[dropdownKey]];
							});
						}
					} else {
						if (!multi) {
							tree.selectNode(function() {
								return false;
							});
						}
					}
					combo.enableEvent("select");

				}
				combo.bind("remoteLoad", function(param, success, error) {
					ddstmp.setParameter("_id", param._id);
					combo.remoteLoading = true;
					ddstmp.flushData(1, (function(_id) {
						return function() {
							combo.remoteLoading = false;
							var json = ddstmp.toJson();
							mergeRecords(ddstmp, dds, _id);
							success(json[MAP_KEY_ROWS]);
							_selectRow(combo);
						}
					})(param._id), function() {
						combo.remoteLoading = false;
						error();
					});
					ddstmp.setParameter("_id", null);
				});
				combo.bind("afterappend", function() {
					_selectRow(combo);
				});
				combo.bind("beforeOpen", function() {
					var eventName = util.join(ds.id, datafield, "beforeOpen");
					var event_result = fireUserEvent(eventName, [ dropdown, ddstmp ]);
					if (event_result === false) {
						return event_result;
					}
					if (!this.dataLoaded || !dropdown.cache) {
						if (!dropdown.cache) {
							this.dataLoaded = false;
						}
						// if (isTrue(ddstmp.init)) {
						ddstmp.flushData(1, function() {
							mergeRecords(ddstmp, dds);
							_selectRow(combo);
						});
						// this.dataLoaded = true;
						// }
					} else {
						_selectRow(combo);
					}
				});
				combo.bind("clear", function() {
					this.trigger("treeselect");
					if (multi) {
						this.treeManager.uncheckAll();
					}
				})
				combo.bind("treeselect", function(checked, rowdatas) {
					var that = this;
					var eventName = util.join(ds.id, datafield, "onSelect");

					if (isUserEventDefined(eventName) && !options.multiple) {
						var rowdata = rowdatas ? rowdatas[0] : null;
						var record = rowdata ? dds.cachedData[rowdata[OID]] : null;

						fireUserEvent(eventName, [ dropdown, record ])
						return;
					}

					var currentValue = {};
					if (rowdatas) {

						for ( var p in fieldmap) {
							var c_val = ds.getValue(p);
							var arr_val = c_val ? c_val.split(",") : [];
							currentValue[p] = arr_val;
						}

						for (var i = 0, len = rowdatas.length; i < len; i++) {
							var rowdata = rowdatas[i];
							var record = dds.cachedData[rowdata[OID]];
							for ( var p in fieldmap) {
								var n_val = record.getValue(fieldmap[p]);
								if (options.multiple) {
									var foundIndex = -1;
									for (var j = 0, len2 = currentValue[p].length; j < len2; j++) {
										if (currentValue[p][j] == n_val) {
											foundIndex = j;
											break;
										}
									}
									if (foundIndex > -1 && !checked) {
										currentValue[p].splice(foundIndex, 1);
									} else if (foundIndex == -1 && checked) {
										currentValue[p].push(n_val);
									}
								} else {
									currentValue[p] = [ n_val ];
								}
							}
						}

					}

					for ( var p in fieldmap) {
						var values = currentValue[p] || [];
						ds.setValue(p, values.join(","));
					}
					validateElement(jq);

				});
				return combo;
			},
			valueChanged : function(jq, dataset, record, field) {
				var manager = jq.manager();
				if (manager) {
					var elementField = jq.attr("datafield");
					var fieldid = field ? field.fieldName : elementField;
					var value = record ? record.getValue(fieldid) : "";
					if (elementField + "Name" == fieldid) {
						manager.setText(value);
					} else {
						// if (!field) {
						var text = record ? record.getValue(fieldid + "Name") : "";
						manager.setValue(value, text || value);
						// }
					}
				}
			},
			columnOption : function(dataset, field, options) {
				var dropdownid = util.join(field.fieldName, "DropDown");
				var dropdown = getDropDownByID(dropdownid);
				$.extend(true, options, {
					textField : field.fieldName + "Name",
					editor : {
						type : 'combotree',
						options : {
							readonly : field.readOnly,
							autoheight : true,
							required : field.required,
							multiple : field.multiple,
							viewfield : dropdown.fields,
							fieldmap : dropdown.fieldMap,
							searchfield : dropdown.searchField
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combotree" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '" dropdownid="' + dropdown.id + '" dropdowndatasetid="' + dropdown.dataset + '">'
					}
				})
			}
		}
	});

	// function findRecordsByFieldMap(value, dataset, datafield, fieldmap) {
	// var records = [];
	// if (value && fieldmap && datafield && dataset) {
	// var map = util.str_map(fieldmap, "=");
	// var fid = map[datafield];
	// var values = value.split(",");
	// for (var i = 0, len = values.length; i < len; i++) {
	// var record = dataset.find([ fid ], [ values[i] ]);
	// records.push(record);
	// }
	// }
	//
	// return records;
	// }
	ui.extend({
		combodialog : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));
				var dropdown = getDropDownByID(jq.attr("dropdownid"));
				var datafield = jq.attr("datafield");
				var multi = options.multiple;
				var fieldmap = util.str_map(options.fieldmap, "=");

				$.extend(options, {
					disabled : options.readonly,
					onButtonClick : function() {
						var eventName = util.join(ds.id, datafield, "beforeOpen");
						var event_result = fireUserEvent(eventName, [ dropdown, options ]);
						if (event_result === false) {
							return event_result;
						}
						if (options.url) {
							var dialog = $.open(util.join("$combodialog", ds.id, datafield), options.title || 'a', options.url, options.selectBoxWidth || 600, options.selectBoxHeight || 400, true, true, options.closeCallback, options.popmaximized, [ "确定", "取消" ]);
							dialog.onClick = function(index) {
								if (index == 0) {// OK
									try {
										if (this.iframe.contentWindow.dropDown_onGetRecord) {
											var records = this.iframe.contentWindow.dropDown_onGetRecord();
											jq.manager().trigger("dialogselect", [ records.dataset ? [ records ] : records ]);
										}
									} catch (e) {

									}
								}
								this.close();
							}
						}
					}
				});
				// 增加初始化设置
				var eventName = util.join(ds.id, datafield, "init");
				var event_result = fireUserEvent(eventName, [ options ]);

				var combo = jq.ligerPopupEdit(options);

				combo.bind("clear", function() {
					this.trigger("dialogselect");
				})
				combo.bind("dialogselect", function(rowdatas) {
					var that = this;
					var eventName = util.join(ds.id, datafield, "onSelect");

					if (isUserEventDefined(eventName)) {
						fireUserEvent(eventName, [ dropdown, rowdatas ]);
						return;
					}

					var currentValue = {};
					if (rowdatas) {
						for ( var p in fieldmap) {
							currentValue[p] = [];
						}
						for (var i = 0, len = rowdatas.length; i < len; i++) {
							var record = rowdatas[i];
							for ( var p in fieldmap) {
								var n_val = record.getValue(fieldmap[p]);
								currentValue[p].push(n_val);
							}
						}
					}

					for ( var p in fieldmap) {
						var values = currentValue[p] || [];
						ds.setValue(p, values.join(","));
					}

					validateElement(jq);
				});
				return combo;
			},
			valueChanged : function(jq, dataset, record, field) {
				var manager = jq.manager();
				if (manager) {
					var elementField = jq.attr("datafield");
					var fieldid = field ? field.fieldName : elementField;
					var value = record ? record.getValue(fieldid) : "";
					if (elementField + "Name" == fieldid) {
						manager.setText(value);
					} else {
						// if (!field) {
						var text = record ? record.getValue(fieldid + "Name") : "";
						manager.setValue(value, text || value);
						// }
	
					}
				}
			},
			columnOption : function(dataset, field, options) {
				var dropdownid = util.join(field.fieldName, "DropDown");
				var dropdown = getDropDownByID(dropdownid);
				$.extend(true, options, {
					textField : field.fieldName + "Name",
					editor : {
						type : 'combodialog',
						options : {
							readonly : field.readOnly,
							autoheight : true,
							required : field.required,
							multiple : field.multiple,
							viewfield : dropdown.fields,
							fieldmap : dropdown.fieldMap,
							url : dropdown.url
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combodialog" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '" dropdownid="' + dropdown.id + '" dropdowndatasetid="' + dropdown.dataset + '">'
					}
				})
			}
		}
	});

	ui.extend({
		combogrid : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				// jq.requireElement(jq.next("span.required"));
				var dds = bindDropdownDataset(jq[0]);
				var ddstmp = copyDataset(tmpDsId(dds.id), dds.id);
				bindDatasetEditor(ddstmp, jq[0]);
				jq.data("tmpds", ddstmp.id);
				var dropdown = getDropDownByID(jq.attr("dropdownid"));
				var datafield = jq.attr("datafield");
				var viewfieldstr = options.viewfield
				var viewfields = viewfieldstr.split(",");
				var columns = [];
				for (var i = 0, len = viewfields.length; i < len; i++) {
					var fid = viewfields[i];
					if (fid) {
						var f = dds.getField(fid);
						var column = {
							display : f.label,
							name : fid,
							align : f.align,
							width : f.width || "auto"
						};
						columns.push(column);
					}

				}
				var multi = options.multiple;
				var fieldmap = util.str_map(options.fieldmap, "=");
				var pagerByServer = undefined;

				$.extend(options, {
					valueField : OID,
					textField : OID,
					selectBoxWidth : 300,
					selectBoxHeight : 240,
					disabled : options.readonly,
					grid : {
						columns : columns,
						url : "#",
						pageStatMessage : "{from}~{to}/{total}",
						data : [],
						showPageSize : false,
						showRefresh : false,
						fixedCellHeight : false,
						switchPageSizeApplyComboBox : true,
						enabledSort : false,
						pageSize : dds.pageSize,
						checkbox : multi,
						rownumbers : false,
						allowHideColumn : false,
						onchangePage : function(page) {
							if (pagerByServer) {
								ddstmp.flushData(page, function() {
									_selectRow(jq.manager());
								});
							} else {
								util.lazyCall(function() {
									_selectRow(jq.manager());
								});
							}
						}

					}
				});
				var searchfieldstr = options.searchfield;
				if (searchfieldstr) {
					var searchfields = searchfieldstr.split(",");
					var conditions = [];
					for (var i = 0, len = searchfields.length; i < len; i++) {
						var fid = searchfields[i];
						if (fid) {
							var f = dds.getField(fid);
							var condition = {
								name : fid,
								label : f.label,
								width : "auto",
								type : 'text'
							};
							conditions.push(condition);
						}

					}
					options.condition = {
						fields : conditions
					};
				} else {
					dropdown.init = true;
				}
				// 增加初始化设置
				var eventName = util.join(ds.id, datafield, "init");
				var event_result = fireUserEvent(eventName, [ options ]);
				var combo = jq.ligerComboBox(options);
				function _selectRow(combo) {
					var initvalue = ds.getValue(datafield);
					var initvalueMap = {};
					combo.clearBox();
					var cgrid = combo.grid;
					if (initvalue) {
						var initvalues = initvalue.split(",");
						for (var i = 0, len = initvalues.length; i < len; i++) {
							initvalueMap[initvalues[i]] = true;
						}
						var fieldmap = util.str_map(options.fieldmap, "=");
						var dropdownKey = fieldmap[datafield];

						// var oldfn = combo.grid.unbind("SelectRow");
						cgrid.disableEvent("SelectRow");

						for ( var p in cgrid.records) {
							var rowdata = cgrid.records[p];
							if (initvalueMap[rowdata[dropdownKey]]) {
								cgrid.select(rowdata[OID]);
							}
						}
						cgrid.enableEvent("SelectRow");

						// if (oldfn) {
						// for (var i = 0, len = oldfn.length; i < len; i++) {
						// var handler = oldfn[0].handler;
						// combo.grid.bind("SelectRow", handler);
						// }
						// }
					}
				}
				combo.bind("combosearch", function(rules) {
					for (var i = 0, len = rules.length; i < len; i++) {
						ddstmp.setParameter(rules[i].field, rules[i].value);
					}
					ddstmp.flushData(1, function() {
						_selectRow(combo);
					});
				});
				combo.bind("beforeOpen", function() {
					var eventName = util.join(ds.id, datafield, "beforeOpen");
					var event_result = fireUserEvent(eventName, [ dropdown, ddstmp ]);
					if (event_result === false) {
						return event_result;
					}
					if (!this.dataLoaded || !dropdown.cache) {
						if (!dropdown.cache) {
							this.dataLoaded = false;
						}
						if (ddstmp.init) {
							ddstmp.flushData(1, function() {
								_selectRow(combo);
								if (pagerByServer == undefined) {
									pagerByServer = ddstmp.length <= ddstmp.pageSize;
								}
							});
							// this.dataLoaded = true;
						}
					} else {
						_selectRow(combo);
					}
				});
				combo.bind("clear", function() {
					this.trigger("gridselect");
				})
				combo.bind("gridselect", function(checked, rowdatas) {
					var that = this;
					var eventName = util.join(ds.id, datafield, "onSelect");

					if (isUserEventDefined(eventName) && !options.multiple) {
						var rowdata = rowdatas ? rowdatas[0] : null;
						var record = rowdata ? ddstmp.cachedData[rowdata[OID]] : null;
						if (!fireUserEvent(eventName, [ dropdown, record ])) {
							return;
						}
					}

					var currentValue = {};
					if (rowdatas) {
						for ( var p in fieldmap) {
							var c_val = ds.getValue(p);
							var arr_val = c_val ? c_val.split(",") : [];
							currentValue[p] = arr_val;
						}
						for (var i = 0, len = rowdatas.length; i < len; i++) {
							var rowdata = rowdatas[i];

							// var rowdatas = [rowdata];
							var record = ddstmp.cachedData[rowdata[OID]];
							for ( var p in fieldmap) {
								var n_val = record.getValue(fieldmap[p]);
								if (options.multiple) {
									var found = null;
									var foundIndex = -1;
									for (var j = 0, len2 = currentValue[p].length; j < len2; j++) {
										if (currentValue[p][j] == n_val) {
											// found = currentValue[p];
											foundIndex = j;
											break;
										}
									}
									if (foundIndex > -1 && !checked) {
										currentValue[p].splice(foundIndex, 1);
									} else if (foundIndex == -1 && checked) {
										currentValue[p].push(n_val);
									}
								} else {
									currentValue[p] = [ n_val ];
								}
							}
						}

					}

					for ( var p in fieldmap) {
						var values = currentValue[p] || [];
						ds.setValue(p, values.join(","));
					}
					validateElement(jq);

				});
				return combo;
			},
			valueChanged : function(jq, dataset, record, field) {
				var manager = jq.manager();
				if (manager) {
					var elementField = jq.attr("datafield");
					var fieldid = field ? field.fieldName : elementField;
					var value = record ? record.getValue(fieldid) : "";
					if (elementField + "Name" == fieldid) {
						manager.setText(value);
					} else {
						// if (!field) {
						var text = record ? record.getValue(fieldid + "Name") : "";
						manager.setValue(value, text || value);
						// }
	
					}
				}
			},
			columnOption : function(dataset, field, options) {
				var dropdownid = util.join(field.fieldName, "DropDown");
				var dropdown = getDropDownByID(dropdownid);
				$.extend(true, options, {
					textField : field.fieldName + "Name",
					editor : {
						type : 'combogrid',
						getText : function(value, fid) {
							return dataset.getString(fid);
						},
						options : {
							readonly : field.readOnly,
							autoheight : true,
							required : field.required,
							multiple : field.multiple,
							viewfield : dropdown.fields,
							fieldmap : dropdown.fieldMap,
							searchfield : dropdown.searchField
						},
						body : '<input id="grid_editor_' + field.fieldName + '" extra="combogrid" datasetid="' + dataset.id + '" datafield="' + field.fieldName + '" dropdownid="' + dropdown.id + '" dropdowndatasetid="' + dropdown.dataset + '">'
					}
				})
			}
		}
	});

	ui.extend({
		"toolbar" : {
			render : function(jq, options) {
			},
			valueChanged : function(jq, dataset, record) {
			},
			statusChanged : function(jq, field, status, value) {
			},
			columnOption : function(dataset, field, options) {
			},
			validation : function(jq, options) {
			}
		}
	});

	function _initColumn(options, gridid, ds, fid, c) {
		var field = ds.getField(fid);

		var column = {
			display : field.label,
			type : "string",
			editor : {
				type : 'text'
			},
			align : field.align,
			headAlign : field.headalign || options.headalign || field.align,
			name : field.fieldName
		};

		if (options.fitcolumns === false) {
			column.minWidth = Math.min(60, c || 60);
		}
		var cellrender = util.join(gridid, fid, "onRefresh");// [record,fid]
		if (isUserEventDefined(cellrender)) {
			(function(event, fid) {
				column.render = function(row) {
					return fireUserEvent(event, [ ds.cachedData[row[OID]], fid, row[fid] ]);
				}
			})(cellrender, fid);
		} else {
			(function(event, ds, field) {
				column.render = function(row) {
					var fid = field.fieldName;
					var editType = field.editType;
					switch (editType) {
					case "combobox":
					case "radioboxs":
					case "checkboxs":
						return util.DDIC(util.join(ds.id, fid), row[fid]);
					case "combogrid":
					case "combotree":
					case "combodialog":
						return util.getViewString(row[fid + "Name"], field);
					case "checkbox":
						return '<a class="l-checkbox' + (util.getTypedValue(row[fid], field) ? ' l-checkbox-checked' : '') + '"></a>';
					case "radiobox":
						return '<a class="l-radio' + (util.getTypedValue(row[fid], field) ? ' l-radio-checked' : '') + '"></a>';
					default:
						return util.getViewString(row[fid], field);
					}

					// var rec = ds.cachedData[row[OID]];
					// return rec.getViewString(fid);
					// return fireUserEvent(event, [
					// ds.cachedData[row[OID]],
					// field.fieldName ]);
				}
			})(cellrender, ds, field);
		}

		columnproperties(ds, field, column);
		// columnproperties(field, column);
		if (c) {
			column.width = c;
		}

		return column;
	}
	ui.extend({
		grid : {
			render : function(jq, options) {
				var ds = bindDataset(jq[0]);
				var fieldstr = options.fieldstr;
				var fieldids = fieldstr.split(",");
				if (!fieldstr) {
					throw "dataset fieldstr is empty!";
				}
				var isselectfirst = fieldids[0] == "select";
				var columns = [];
				var dataset = getElementDataset(jq[0]);
				var isdropdown = ds.type == "dropdown";
				var gridid = jq.attr("id");
				for (var i = isselectfirst ? 1 : 0; i < fieldids.length; i++) {
					var fieldid = fieldids[i];
					var c = unC(fieldid);
					var fid = c[0];

					var column;
					// 例如：组合列1{f1;组合列2{f2|f3}}
					var hd1idx = fid.indexOf("{");
					if (hd1idx > -1) {// 第一层
						var hd1 = fid.substring(0, hd1idx);// 组合列1
						var fid1 = fid.substring(hd1idx + 1, fid.length - 1);// f1;组合列2{f2|f3}
						column = {
							display : hd1,
							headAlign : "center",
							columns : []
						}
						var hd2idx = fid1.indexOf("{");
						if (hd2idx > -1) {// 第二层
							var fid1s = fid1.split(";");
							for (var j = 0, len2 = fid1s.length; j < len2; j++) {
								var hd2idx = fid1s[j].indexOf("{");
								if (hd2idx > -1) {// 组合列2{f2|f3}
									var hd2 = fid1s[j].substring(0, hd2idx);// 组合列2
									var fid2 = fid1s[j].substring(hd2idx + 1, fid1s[j].length - 1);// f2|f3
									var column2 = {
										display : hd2,
										headAlign : "center",
										columns : []
									}
									var fid2s = fid2.split("|");
									for (var k = 0, len3 = fid2s.length; k < len3; k++) {
										var cc = unC(fid2s[k]);
										column2.columns.push(_initColumn(options, gridid, ds, cc[0], cc[1]))
									}
									column.columns.push(column2);
								} else {// f1
									var cc = unC(fid1s[j]);
									column.columns.push(_initColumn(options, gridid, ds, cc[0], cc[1]))
								}
							}
						} else {
							var fid1s = fid1.split(/[|;]/g);
							for (var j = 0, len2 = fid1s.length; j < len2; j++) {
								var cc = unC(fid1s[j]);
								column.columns.push(_initColumn(options, gridid, ds, cc[0], cc[1]))
							}
						}
					} else {
						column = _initColumn(options, gridid, ds, fid, c[1]);
					}
					columns.push(column);
				}

				var pageSizeOptions = [ 10, 20, 30, 40, 50, 100 ];
				if ($.inArray(ds.pageSize, pageSizeOptions) == -1) {
					pageSizeOptions.push(ds.pageSize);
					pageSizeOptions.sort(function(a, b) {
						return a - b;
					});
				}
				var pageCache = options.pageCache;
				options = $.extend({
					columns : columns,
					dataAction : pageCache ? "local" : "server"
				}, options, {
					// selectRowButtonOnly:true,
					url : pageCache ? null : "#",
					usePager : options.pagination,
					title : options.label,
					isScroll : options.height != "",
					// allowAdjustColWidth : options.adjust !== false,
					enabledSort : options.sortable,
					pageSizeOptions : pageSizeOptions,
					pageSize : ds.pageSize,
					switchPageSizeApplyComboBox : true,
					fixedCellHeight : false,
					// rownumbers : true,
					enabledEdit : options.editable,
					checkbox : isselectfirst,
					onChangeSort : function(sort, order) {
						if (options.remotesort) {
							ds.setParameter("sort", sort);
							ds.setParameter("order", order);
							ds.flushData(ds.pageIndex);
						} else {
							ds.sort((order == "desc" ? "-" : "") + sort);
						}
					}
				});
				if (options.exporter && g[options.exporter]) {
					options.exportbar = g[options.exporter].types;
				}

				if (options.textKey && options.idKey && options.pidKey) {
					options.tree = {
						columnName : options.textKey,
						// columnId : options.textKey,
						idField : options.idKey,
						parentIDField : options.pidKey

					};
					// options.async=true;
					if (options.async) {
						$.extend(options.tree, {
							isParent : function(row) {
								return (row.children && row.children.length > 0) || isTrue(row[TREE_KEY_HASCHILD]);
							},
							isExtend : function(row) {
								return row.children && row.children.length > 0;
							}
						});
					} else {

					}
					options.enabledSort = false;// TODO tree sort not support
				}
				var dblclick = util.join(gridid, "onDblClick");// [record,index,tr]
				if (isUserEventDefined(dblclick)) {
					(function(event) {
						options.onDblClickRow = function(data, rowindex, rowobj) {
							return fireUserEvent(event, [ ds.cachedData[data[OID]], rowindex, rowobj ]);
						}
					})(dblclick);
				}

				var stylerender = util.join(gridid, "onRowClass");// [record]
				if (isUserEventDefined(stylerender)) {
					(function(event) {
						options.rowAttrRender = function(rowdata, rowid) {

							return fireUserEvent(event, [ ds.cachedData[rowdata[OID]] ]);
						}
					})(stylerender);
				}

				var liger = jq.ligerGrid(options);
				if (options.async) {
					// var ddstmp = copyDataset(tmpDsId(ds.id), ds.id);
					// ddstmp.init = false;
					// liger.bind("treeExpand", function(rowdata) {
					// ddstmp.setParameter("_id", rowdata[TREE_KEY_ID]);
					// ddstmp.flushData(1, function() {
					// rowdata[TREE_KEY_STATUS] = "open";
					// var rec = ddstmp.firstUnit;
					// while(rec) {
					// rec.dataset = ds;
					// pArray_insert(ds, "end", null, rec);
					// rec = rec.nextUnit;
					// }
					// var json = ddstmp.toJson();
					// liger.appendRange(json[MAP_KEY_ROWS], rowdata, null,
					// false);
					// ddstmp.setParameter("_id", null);
					// }, function() {
					// ddstmp.setParameter("_id", null);
					// });
					// ddstmp.setParameter("_id", null);
					// });
				}
				liger.bind("checkRow", function(checked, rowdatas) {
					if (options.checkbox) {
						for (var i = 0, len = rowdatas.length; i < len; i++) {
							var record = ds.cachedData[rowdatas[i][OID]];
							record.setValue("select", checked);
						}
					}
				});
				liger.bind("checkAllRow", function(checked) {
					if (options.checkbox) {
						var rec = ds.firstUnit;
						while (rec) {
							rec.setValue("select", checked);
							rec = rec.nextUnit;
						}
					}
				});
				liger.bind("beforeEdit", function(editparam){
					var col =editparam.column;
					var f = ds.getField(col.columnname);
					if (f && f.readOnly) {
						return false;
					}
					
				});
				liger.bind("clickRow", function(rowdata, rowid, rowobj) {
					if (options.checkbox) {
						var record = ds.cachedData[rowid];
						var oldrecord = record.dataset.record;
						if (record != oldrecord) {
							record.dataset.setRecord(record);
						} else {
							// liger.unselect(rowid);
						}
					} else {
						var record = ds.cachedData[rowid];
						var oldrecord = record.dataset.record;
						if (record != oldrecord) {
							record.dataset.setRecord(record);
							var newrecord = record.dataset.record;
							if (newrecord == oldrecord) {
								setTimeout(function() {
									jq.manager().cancelEdit(record.id);
									jq.manager().select(oldrecord.id);
								}, 0);
							}
						}
					}

				});
				liger.bind("changePageSize", function(pageSize) {
					ds.pageSize = pageSize;
					if (!pageCache) {
						ds.flushData(1);
					}
				});
				liger.bind("changePage", function(page) {
					if (!pageCache) {
						ds.flushData(page);
					}
				});
				liger.bind("reload", function() {
					ds.flushData(ds.pageIndex);
				});
				/*
				 * //todo liger.bind("beforeSubmitEdit", function(e) { var
				 * record = ds.cachedData[e.record[OID]]; if (record) {
				 * record.setValue(e.column.name, e.value); } return false; });
				 */
				return liger;
			},
			statusChanged : function(jq, field, status, value) {
				if (status == "readonly") {
					// if (field) {
					// jq.jqxGrid("setcolumnproperty", field.fieldName,
					// "editable", !value);
					// } else {
					// jq.jqxGrid({
					// editable : !value
					// });
					// }
				}

			},
			valueChanged : function(jq, dataset, record, field) {
				var row = {};
				if (record) {
					// row[OID] = record.id;
					// for (var i = 0; i < dataset.fields.fieldCount; i++) {
					// var _f = dataset.getField(i);
					// var fieldName = _f.fieldName;
					// var v = record.getJsonValue(fieldName);
					// row[fieldName] = v;
					// }
					row = record.$data;
					row[OID] = record.id;
					var manager = jq.manager();
					if (manager.editor.editing)
						return;
					if (field && field.fieldName == "select") {
						var checked = record.getValue(field.fieldName);
						var opts = manager.options;// jq.data("options");
						if (opts.checkbox) {
							var op = checked ? "select" : "unselect";
							manager[op](row[OID]);
						} else {
							manager.update(row[OID], row);
						}

					} else {
						manager.update(row[OID], row);
						manager._fixHeight.ligerDefer(manager, 10);
					}
				}

			}
		}
	});

	ui.grid = {};
	ui.grid.getEditor = function(e) {
		var type = e.type, control = e.control, master = e.master;
		if (!type)
			return null;
		var inputTag = 0;
		if (control)
			control = control.substr(0, 1).toUpperCase() + control.substr(1);
		return $.extend({
			create : function(container, editParm, bound) {
				var editor = editParm.column.editor;
				var edittype = editor.type;
				var options = editor.options || (editor.options = {});
				$.extend(options, bound);
				var inputBody = $(editor.body).appendTo(container);
				editParm.body = inputBody[0];

				var manager = ui.editors[type].render(inputBody, options);
				manager.bind("valid", function(valid) {
					master.editor.invalid = !valid;
				})
				inputBody.manager(manager);
				return manager;
			},
			getValue : function(editor, editParm) {
				var field = editParm.field || editParm.column;
				if (editor.getValue) {
					var value = editor.getValue();
					return value;
				}
			},
			setValue : function(editor, value, editParm) {
				var field = editParm.field || editParm.column;
				if (editor.setValue) {
					editor.disableEvent("selected");
					editor.setValue(value, editParm.text);
					editor.enableEvent("selected");

					// 优化：如果控件是checkbox或radiobox，单击改变值
					var editor = editParm.column.editor;
					var edittype = editor.type;
					if (edittype == "checkbox" || edittype == "radiobox") {
						setTimeout(function() {
							$(editParm.body).manager().link.trigger("click");
						}, 10);
					}
				}
			},
			getText : function(editor, editParm) {
				if (editor.getText) {
					return editor.getText();
				}
			},
			setText : function(editor, value, editParm) {
				if (editor.setText) {
					editor.setText(value);
				}
			},
			getSelected : function(editor, editParm) {
				if (editor.getSelected) {
					return editor.getSelected();
				}
			},
			resize : function(editor, width, height, editParm) {
				if (editParm.field)
					width = width - 2;
				if (editor.resize)
					editor.resize(width, height);
			},
			setEnabled : function(editor, isEnabled) {
				if (isEnabled) {
					if (editor.setEnabled)
						editor.setEnabled();
				} else {
					if (editor.setDisabled)
						editor.setDisabled();
				}
			},
			destroy : function(editor, editParm) {
				// util.hideErrTip($(editParm.body));
				if (editor.destroy)
					editor.destroy();
				// setTimeout(function() {
				unbindDataset(editParm.body);
				// }, 1);
			}
		}, e);
	}
	function errAlert(msg) {
		$.error(msg);
	}

	$.extend(g, {
		errAlert : errAlert
	});
})(window, jQuery);

/*
 * ! 组件校验
 * 
 */
(function(g, $, undefined) {
	"use strict";
	var ui = g.UIKit;
	var util = g.UIUtil;
	var i18n = ui.i18n;

	var properties = $.extend(ui.properties, {
		maxDigits : 12
	});

	var rules = $
			.extend(
					ui.rules,
					{
						isLoginName : "^[A-Za-z][A-Za-z0-9\\_\\@\\.]+$",
						isFaxNumber : "^[0-9\\-]+$",
						isNumOrWord : "^[A-Za-z0-9]+$",
						isPassWord : "^[A-Za-z0-9\\_\\@]+$",
						isNum : "^[0-9]+$",
						isNotEmpty : ".+",
						isNotSpace : "^\\S+$",
						isPositiveNumber : "^\\d+\\.?\\d*$",
						isPositiveNumber_12_2 : "^\\d{1,10}(\\.\\d{0,2}){0,1}$",
						isPositiveNumber_10_2 : "^\\d{1,8}(\\.\\d{0,2}){0,1}$",
						isPositiveNumber_11_2 : "^\\d{1,9}(\\.\\d{0,2}){0,1}$",
						isPositiveNumber_10_3 : "^\\d{1,7}(\\.\\d{0,3}){0,1}$",
						isPositiveInteger : "^\\d*[1-9]+\\d*$",
						isPositiveNumber_15_3 : "^\\d{1,12}(\\.\\d{0,3}){0,1}$",
						isPositiveNumber_16_2 : "^\\d{1,14}(\\.\\d{0,2}){0,1}$",
						isMobile : "^13\\d{9}$",
						isZipCode : "^\\d{6}$",
						isBpNumber : "^\\d{5,8}[-]?\\d{3,7}$",
						isPhoneNumber : "^[\\d-]{6,32}$",
						isFileName : "^[a-zA-Z0-9\\.-_]+$",
						isWord : "^\\w+$",
						isPidNumber : "^[1-9]\\d{14,17}[xX]?$",
						isValidDate : "^((1[6-9]|[2-9]\\d)?\\d{2})-(((0[13578]|1[02])-31)|((0[1,3-9]|1[0-2])-(29|30)))$|^(((1[6-9]|[2-9]\\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)))-(02-29)$|^((1[6-9]|[2-9]\\d)?\\d{2})-((0[1-9])|(1[0-2]))-(0[1-9]|1\\d|2[0-8])$",
						isValidDateWithTime : "^((1[6-9]|[2-9]\\d)?\\d{2})-(((0[13578]|1[02])-31)|((0[1,3-9]|1[0-2])-(29|30)))(\\s((\\b0\\d\\b)|(1\\d)|(2[0-3])):((\\b0\\d\\b)|([1-5]\\d))){0,1}$|^(((1[6-9]|[2-9]\\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)))-(02-29)(\\s((\\b0\\d\\b)|(1\\d)|(2[0-3])):((\\b0\\d\\b)|([1-5]\\d))){0,1}$|^((1[6-9]|[2-9]\\d)?\\d{2})-((0[1-9])|(1[0-2]))-(0[1-9]|1\\d|2[0-8])(\\s((\\b0\\d\\b)|(1\\d)|(2[0-3])):((\\b0\\d\\b)|([1-5]\\d))){0,1}$",
						isEmail : "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
						isChineseName : "^[\u4E00-\u9FA5]{2,}$",
						isChinese : "^[\u4E00-\u9FA5]+$",
						isURL : "^(http://){0,1}.+\\..+\\..+$",
						isValidDateText : "^[0-9]{4}[-\\.\/][0-1]{1}[0-9]{1}[-\\.\/][0-9]{1,2}$",
						isDbString : "[^'%\"<>]+",
						isMonthDay : "^\\d{1,31}$",
						isSpecNumber : "^\\-?\\d*[0-9]+\\d*$",
						isPosiInteger : "^\\d*[0-9]+\\d*$",
						isBillDt : "^((1[6-9]|[2-9]\\d)?\\d{2})(0[1-9]|10|11|12)$",
						isNumber1 : "^\\d{1}$",
						isNumber2 : "^\\d{2}$",
						isNumber3 : "^\\d{3}$",
						isNumber4 : "^\\d{4}$",
						isNumber5 : "^\\d{5}$",
						isNumber6 : "^\\d{6}$",
						isNumber7 : "^\\d{7}$",
						isNumber8 : "^\\d{8}$",
						isNumber9 : "^\\d{9}$",
						isNumber10 : "^\\d{10}$",
						isNumber11 : "^\\d{11}$",
						isNumber12 : "^\\d{12}$",
						isNumber13 : "^\\d{13}$",
						isNumber22 : "^\\d{22}$",
						isPositiveNumber11 : "^\\d{11}$",
						isPassWord6 : "^[A-Za-z0-9\\_\\@]{6}$",
						isMon : "^((\\d{1,6}\\.\\d{1,2})|(\\d{1,6}))$",
						isValidDateFormat : "(\\d{4}\\-((0[1-9])|(1[012]))\\-((0[1-9])|([1-2][0-9])|(3[01])))",
						isValidDateFormat_YYYYMMDD : "(\\d{4}\\((0[1-9])|(1[012]))\\((0[1-9])|([1-2][0-9])|(3[01])))",
						isMon14 : "^((\\d{1,12}\\.\\d{1,2})|(\\d{1,12}))$",
						isTime : "^(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$",
						isNumber620 : "^\\d{6,20}$",
						isNumber015 : "^\\d{0,15}$",
						isPosInt2 : "^\\d{1,2}$",
						isTlrno : "^\\d{8}$",
						isRate_8_6 : "^\\d{1,2}(\\.\\d{0,6}){0,1}$",
						isRate_6_2 : "^\\d{1,4}(\\.\\d{0,2}){0,1}$",
						isRate_5_2 : "^\\d{1,3}(\\.\\d{0,2}){0,1}$",
						isRate_5_4 : "^\\d{1,1}(\\.\\d{0,4}){0,1}$",
						isRate_18_2 : "^\\d{1,16}(\\.\\d{0,2}){0,1}$",
						isShiBorRate_5_4 : "^[-]{0,1}\\d{1,1}(\\.\\d{0,4}){0,1}$",
						isDraftNumber : "^[A-Z][A-Z]\\d{10}$|^\\d{16}$",
						isOrgCode : "^[0-9A-Z]{8}-[0-9X]$",
						shiBorRate : "^[-]{0,1}\\d{1,4}(\\.\\d{0,2}){0,1}$",
						isNotQuote : "^[^\']*$",
						max : function(value, max) {
							var num = value * 1;
							var max = max * 1;
							return num < max;
						}
					});
	var validator = {
		enable : false,
		handler : null,
		validate : function(element, timeout) {
			if (validator.handler) {
				clearTimeout(validator.handler);
			}
			validator.handler = setTimeout(function() {
				validateElement(element);
			}, timeout || 100);
		},
		onfocusin : function(event) {
			validator.enable = true;
		},
		onfocusout : function(event) {
			validateElement(this);
		},
		onkeyup : function(event) {
			validator.validate(this, 100);
		},
		onclick : function(event) {
			validator.enable = true;
		}
	};
	function isempty(s) {
		return !s && s !== 0;
	}
	function validateElement(jq) {
		var ds = getElementDataset(jq[0]);

		if (ds && ds.modified) {
			hideErr(jq);
			var datafield = jq.attr("datafield");

			var field = ds && datafield ? ds.getField(datafield) : null;
			var manager = jq.manager();
			var isValid = validFieldValue(field, manager ? manager.getValue() : jq.val(), function(field, errmsg) {
				showErr(jq, errmsg);
				return false;
			});
			if (manager) {
				manager.setValid(isValid);
			}

		}
		return true;
	}
	function validFieldValue(field, value, onError) {
		var isValid = true;
		if (!field) {
			return isValid;
		}
		if (field.tag == "selectName" || field.tag == "radioName") {
			return isValid;
		}
		if (field && !field.readOnly) {
			onError = onError || function() {
				return false;
			};
			var empty = isempty(value);
			if (isTrue(field.required) && empty) {
				isValid = false;
				var abort = onError(field, util.format(i18n.required, field.label));
				if (abort === false) {
					return isValid;
				}
			} else if (field.minsize && empty) {
				isValid = false;
				var abort = onError(field, util.format(i18n.sizeTooShort, field.minsize));
				if (abort === false) {
					return isValid;
				}
			} else if (!empty) {
				var ruleMask = field.mask || "";
				var errmsg = field.maskErrorMessage;
				var rindex = ruleMask.indexOf("|");
				var rule = rindex > -1 ? ruleMask.substring(0, rindex) : ruleMask;
				var args = rindex > -1 ? ruleMask.substring(rindex + 1) : "";
				if (rule && rules[rule]) {
					var ruleValue = rules[rule];
					var checkok = true;
					if (typeof ruleValue == "function") {
						args = args.split("|");
						args.unshift(value);
						checkok = ruleValue.apply(g, args);
					} else if (typeof ruleValue == "string") {
						if (ruleValue) {
							checkok = new RegExp(ruleValue).test(value);
						}
					} else {
						throw "field.rule[" + ruleMask + "] not right";
					}
					if (!checkok) {
						isValid = false;
						var abort = onError(field, errmsg);
						if (abort === false) {
							return isValid;
						}
					}
				}
				var strvalue = value + "";
				if (util.jqtype(field.dataType) == "number") {
					var scale = parseInt(field.scale) || 0;
					var size = parseInt(field.size) || properties.maxDigits + scale;
					if (strvalue) {
						var numstr = strvalue.replace(/,/g, '');
						var digits = numstr.split(".");
						var intDigits = (digits[0] || "0").length;
						var decimalDigits = (digits[1] || "").length;
						if (decimalDigits > scale || intDigits + decimalDigits > size) {
							isValid = false;
							var abort = onError(field, util.format(i18n.sizeTooLang, size + "," + scale));
							if (abort === false) {
								return isValid;
							}
						}
					}
					// check for chinese
				} else if (field.dataType == "string") {
					if (field.size && strvalue) {
						var size = parseInt(field.size) || 0;
						if (strvalue.replace(/[^\x00-\xff]/g, '**').length > size) {
							isValid = false;
							var abort = onError(field, util.format(i18n.sizeTooLang, size));
							if (abort === false) {
								return isValid;
							}
						}
					}
					if (field.minsize) {
						var size = parseInt(field.minsize) || 0;
						if (strvalue.replace(/[^\x00-\xff]/g, '**').length < size) {
							isValid = false;
							var abort = onError(field, util.format(i18n.sizeTooShort, size));
							if (abort === false) {
								return isValid;
							}
						}
					}

				}

			}

		}
		return isValid;
	}
	function showErr(jq, errmsg) {
		if (jq.is(":visible")) {
			jq.ligerTip({
				distanceX : 5,
				distanceY : -3,
				content : errmsg
			})
			if (jq.hasClass("l-textarea")) {
				jq.parent().addClass("l-textarea-invalid");
			} else if (jq.hasClass("l-text-field")) {
				jq.parent().addClass("l-text-invalid");
			}
		} else {
			// $.error(errmsg);
		}
	}
	function hideErr(jq) {
		jq.ligerHideTip();
		if (jq.hasClass("l-textarea")) {
			jq.parent().removeClass("l-textarea-invalid");
		} else {
			jq.parent().removeClass("l-text-invalid");
		}
	}

	$.extend(util, {
		showErrTip : showErr,
		hideErrTip : hideErr
	});
	function initValidation() {
		function delegate(event) {
			var eventType = "on" + event.type;
			if (validator[eventType]) {
				validator[eventType].call($(this), event);
			}
		}
		var validateHolder = $(document.body);
		var delegated = validateHolder.data("delegated");
		if (!delegated) {
			// validateHolder.delegate("input:not(:button,:radio,:checkbox),
			// textarea", "focusin focusout keyup", delegate);
			validateHolder.delegate(".l-text-field, .l-textarea-field", "focusin focusout keyup", delegate);
			validateHolder.delegate(":radio,:checkbox", "click", delegate);
			validateHolder.data("delegated", true);
		}
	}

	function validateDataset(dataset, onErr) {
		if (dataset.record == null) {
			return true;
		}
		var isValid = true;
		var errInfo = [];
		onErr = onErr || function(field, errmsg) {
			errInfo.push("[" + field.label + "]: " + errmsg);
			dataset.setFieldInvalid(field.fieldName, errmsg);
		}
		var fields = dataset.fields;
		var fieldCount = fields.fieldCount;
		for (var i = 0; i < fieldCount; i++) {
			var field = fields[i]
			isValid = isValid && validFieldValue(field, dataset.getString(field.fieldName), onErr);
		}
		if (errInfo.length) {
			isValid = false;
			$.error(errInfo.join("\r\n"));
			return isValid;
		}
		return isValid;
	}

	g.initValidation = initValidation;
	g.validateElement = validateElement;
	g.validateDataset = validateDataset;
})(window, jQuery);
/*
 * ! 窗口扩展
 * 
 */
(function(g, $, undefined) {
	"use strict";
	var util = g.UIUtil;

	function _alert(message, title, type, onBtnClick, timeout) {
		if (typeof title == 'function') {// 参数错位，自动调整
			timeout = onBtnClick;
			onBtnClick = title;
			title = null;
		} else if (typeof title == 'number') {
			timeout = title;
			onBtnClick = null;
			title = null;
		}
		message = ("<div>" + message + "</div>").replace(/\n/g, "<br>");
		var manager = $.ligerDialog.alert(message, title, type, onBtnClick);
		if (timeout) {
			setTimeout(function() {
				manager.close();
			}, timeout);
		}
		return {
			close : function() {
				manager.close();
			}
		};
	}
	function extendAlert(message, title, onBtnClick, timeout) {
		return _alert(message, title, "none", onBtnClick, timeout);
	}
	function extendSuccess(message, title, onBtnClick, timeout) {
		return _alert(message, title, "success", onBtnClick, timeout);
	}
	function extendError(message, title, onBtnClick, timeout) {
		return _alert(message, title, "error", onBtnClick, timeout);
	}
	function extendWarn(message, title, onBtnClick, timeout) {
		return _alert(message, title, "warn", onBtnClick, timeout);
	}
	function extendConfirm(message, title, onOk, onCancel) {
		if (typeof title == 'function') {// 参数错位，自动调整
			onCancel = onOk;
			onOk = title;
			title = null;
		}
		var manager = $.ligerDialog.confirm("<div>" + message + "</div>", title, function(yes) {
			if (yes) {
				if (typeof onOk == 'function') {
					onOk();
				}
			} else {
				if (typeof onCancel == 'function') {
					onCancel();
				}
			}
		});
		return {
			close : function() {
				manager.close();
			}
		};
	}
	function extendWait(message, title, timeout) {
		if (typeof title == 'number') {
			timeout = title;
			title = null;
		}
		var manager = $.ligerDialog.waitting("<div>" + message + "</div>", title);
		if (timeout) {
			setTimeout(function() {
				manager.close();
			}, timeout);
		}
		return {
			close : function() {
				manager.close();
			}
		}
	}
	function extendTip(message, title, timeout) {
		if (typeof title == 'number') {
			timeout = title;
			title = null;
		}
		var manager = $.ligerDialog.tip({
			title : title,
			content : "<div>" + message + "</div>"
		});
		if (timeout) {
			setTimeout(function() {
				manager.close();
			}, timeout);
		}
		return {
			close : function() {
				manager.close();
			},
			html : function(message) {
				manager.set('content', "<div>" + (message || "") + "</div>");
			}
		}
	}
	function extendWindow(id, title, url, width, height, cache, modal, closeCallback, maximized, buttons) {
		cache = cache || false;
		var win = g[id];
		if (win && win.open) {
			win.open(id, title, url, width, height, cache, modal, closeCallback, maximized, buttons);
		} else {
			win = g[id] = {};
			var options = {
				title : title || "新建窗口",
				url : util.webpath(url),
				width : width * 1 || 400,
				height : height * 1 || 200,
				showMax : true,
				showToggle : true,
				modal : modal !== false,
				isResize : true,
				slide : false
			};
			if (buttons) {
				if (typeof buttons == 'string') {
					buttons = buttons.split(",");
				}
				var buttonClickEventName = util.join(id, "onButtonClick");
				var btns = options.buttons = [];
				for (var i = 0, len = buttons.length; i < len; i++) {
					btns.push({
						text : buttons[i],
						onclick : (function(i) {
							return function(item, dialog) {
								var innerWindow;
								try {
									innerWindow = win.iframe.contentWindow
								} catch (e) {

								}
								fireUserEvent(buttonClickEventName, [ i, win, innerWindow ])
								if (win.onClick) {
									win.onClick(i);
								}
							}
						})(i)
					});
				}
			}
			var manager = $.ligerDialog.open(options);
			$.extend(win, {
				iframe : manager.jiframe[0],
				open : function(id, title, url, width, height, cache, modal, closeCallback, maximized, buttons) {
					var _url = manager.jiframe.attr("src");
					if (url) {
						url = util.webpath(url);
						if (url != _url) {
							manager.jiframe.attr("src", url);
						} else {
							if (!cache) {
								manager.jiframe.attr("src", url);
							}
						}
					} else {
						if (!cache) {
							manager.jiframe.attr("src", _url);
						}
					}
					manager._setTitle(title);
					manager.show();
				},
				close : function() {
					manager.hide();
				},
				hide : function() {
					manager.hide();
				},
				max : function() {
					manager.max();
				}
			})
			manager.bind("beforehide", function() {
				var checked = true;
				if (typeof closeCallback == "function") {
					checked = closeCallback();
				}
				if (checked) {
					return g.fireUserEvent(id + "_onCloseCheck", [ win ]);
				} else {
					return false;
				}

			});
		}
		if (maximized) {
			win.max()
		}
		return win;
	}
	$.alert = $.info = extendAlert;
	$.success = extendSuccess;
	$.error = extendError;
	$.warn = extendWarn;
	$.tip = extendTip;
	$.confirm = extendConfirm;
	$.open = extendWindow;
	$.waiting = extendWait;
	// $.prompt = extendPrompt; // TODO

})(window, jQuery);

/*
 * ! 表格导出
 * 
 */
(function(g, $, undefined) {
	"use strict";
	var util = g.UIUtil;
	var i18n = g.UIKit.i18n;

	function CreateExporter(element, cqId, types, downloadURL) {
		var jq = $(element);
		var init = false;
		var startEl = jq.find("[name='" + cqId + "_startPage']");
		var endEl = jq.find("[name='" + cqId + "_endPage']");
		var allEl = jq.find("[name='" + cqId + "_allPage']");
		var fieldstrEl = jq.find("[name='" + cqId + "_expElements']");
		var zipEl = jq.find("[name='" + cqId + "_complex']");
		var batchEl = jq.find("[name='" + cqId + "_expAll']");
		var exportEls = jq.find("[name='" + cqId + "_expElements']");

		var tipEl = jq.find(".exporter-tip");

		var dataset = getDatasetByID(cqId + "_dataset");
		var interfaceDataset = getDatasetByID(cqId + "_interface_dataset");
		var downloadAction = downloadURL;

		var _tmpdowniframe = $(".inner-iframe", element);
		var exportTarget;
		if (!_tmpdowniframe[0]) {
			exportTarget = "exporter-iframe-" + new Date().getTime();
			jq.append("<iframe style='display:none' name='" + exportTarget + "'></iframe>");
		} else {
			exportTarget = _tmpdowniframe.attr("name");
		}

		return {
			types : types,
			show : function(type) {
				this.type = type || "CSV";
				var exportWin = g[element.getAttribute("id")];
				exportWin.setTitle(this.type.toUpperCase() + i18n.exportTitleSuffix);
				exportWin.show();
				if (!init) {
					startEl.bind("change", function(e) {
						var num = this.value * 1;
						var message = "";
						var maxpage = Math.ceil(dataset.expmaxrcd * 1.0 / dataset.pageSize);
						if (!isNaN(num)) {
							if (num < 1) {
								num = 1;
								this.value = num;
							} else {
								var endNum = endEl.val() * 1;
								if (!isNaN(endNum)) {
									if (endNum < num) {
										num = endNum;
										this.value = num;
									} else if (!batchEl.prop("checked") && endNum - num + 1 > maxpage) {
										endEl.val(num + maxpage - 1);
										message = util.format(i18n.exportMaxRecord, dataset.expmaxrcd);
									}
								}
							}

						}
						tipEl.html(message);
					})
					endEl.bind("change", function(e) {
						var num = this.value * 1;
						var message = "";
						var maxpage = Math.ceil(dataset.expmaxrcd * 1.0 / dataset.pageSize);
						if (!isNaN(num)) {
							if (num < 1) {
								num = 1;
								this.value = num;
							} else {
								var startNum = startEl.val() * 1;
								if (!isNaN(startNum)) {
									if (num < startNum) {
										num = startNum;
										this.value = num;
									} else if (!batchEl.prop("checked") && num - startNum + 1 > maxpage) {
										this.value = startNum + maxpage - 1;
										message = util.format(i18n.exportMaxRecord, dataset.expmaxrcd);
									}
								}
							}
						}
						tipEl.html(message);
					});

					allEl.bind("click", function(e) {
						startEl.manager().set({
							disabled : this.checked
						});
						endEl.manager().set({
							disabled : this.checked
						});
					});
					init = true;
				}
			},
			download : function() {
				var start = startEl.val();
				var end = endEl.val();
				var all = allEl.prop("checked");
				var zip = zipEl.prop("checked");
				var batch = batchEl.prop("checked");
				var fieldstr = exportEls.manager().getValue();
				if (fieldstr) {
					this.exportFile(this.type, batch, zip, fieldstr, start, end, all);
					var exportWin = g[element.getAttribute("id")];
					exportWin.hide();
				} else {
					$.warn(i18n.exportSelectRequired);
				}

			},
			exportFile : function(exptype, batch, zip, fieldstr, start, end, all) {
				var dt = dataset;
				var idt = interfaceDataset;

				fieldstr = fieldstr || exportEls.manager().getValue();

				var form = document.createElement("FORM");
				form.method = "post";
				form.action = util.webpath("/export.ifs");
				form.style.visibility = "hidden";
				form.style.display = "none";
				form.target = exportTarget;

				if (idt) {
					copyDateSetParameter(idt, dt);
					dt.setParameter('everyPage', dt.pageSize);
					dt.setParameter('nextPage', dt.pageIndex);
					for (var i = 0; i < idt.fields.fieldCount; i++) {
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + idt.getField(i).fieldName + "\" value=\"" + idt.getString(i) + "\"/>");
					}
				}
				// 获取查询页面参数，并放到表单中
				for ( var p in dt.parameters) {
					if (dt.parameters[p] != null && typeof dt.parameters[p] != 'function') {
						if (form[p])
							continue;
						form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + p + "\" value=\"" + dt.parameters[p] + "\"/>");
					}
				}
				// 是否批量导出
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"dsName\" value=\"" + cqId + "\"/>");
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"dsPath\" value=\"" + dt.dspath + "\"/>");
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"_process\" value=\"" + downloadAction + "\"/>");

				// 是否批量导出
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_expAll\" value=\"" + (batch ? 1 : 0) + "\"/>");
				// 是否压缩
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_complex\" value=\"" + (zip ? 1 : 0) + "\"/>");
				// 要导出的字段
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_expElements\" value=\"" + getEncodeStr(fieldstr) + "\"/>");
				// 开始页
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_startPage\" value=\"" + start + "\"/>");
				// 结束页
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_endPage\" value=\"" + end + "\"/>");
				// 是否全部导出
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"" + cqId + "_allPage\" value=\"" + (all ? 1 : 0) + "\"/>");
				// 导出文件类型
				form.insertAdjacentHTML("beforeEnd", "<input type=\"hidden\" name=\"expType\" value=\"" + exptype + "\"/>");
				// 将FORM追加到BODY中
				document.body.appendChild(form);
				// 提交表单
				form.submit();
				document.body.removeChild(form);

			}
		}
	}

	g.CreateExporter = CreateExporter;
})(window, jQuery);