// var
var _DEBUG = true;
// _DATE_PATTERN_ = "yyyyMMdd";
// _DATETIME_PATTERN_ = "yyyyMMddHHmmss";
// _TIME_PATTERN_ = "HHmmss";
// _VIEW_DATE_PATTERN = "yyyy-MM-dd";
// _VIEW_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
// _VIEW_TIME_PATTERN = "HH:mm:ss";
var $uuid = 0; 
var __record_gen__ = 0;
var _array_dataset = [];
var _tabset_list = [];
var _user_events = {
	me : function() {
		var rs = {};
		for ( var p in this) {
			rs[p] = this[p];
		}
		return rs;
	}
};
var _maxAutoGenID = 0;

function _getAutoGenID() {
	_maxAutoGenID++;
	return "__" + _maxAutoGenID;
}



//function parameters_setParameter(name, value, dataType) {
//	// var parameter;
//	// if (typeof (name) == "number") {
//	// var i = name;
//	// this[i].name = name;
//	// this[i].value = value;
//	// if (dataType) {
//	// this[i].dataType = dataType;
//	// }
//	// } else {
//	// var count = this.length;
//	// var founded = false;
//	// for (var i = 0; i < count; i++) {
//	// if (this[i].name == name) {
//	// founded = true;
//	// break;
//	// }
//	// }
//	// if (!founded) {
//	// i = count;
//	// this[i] = new Object();
//	//
//	// }
//	// this[i].name = name;
//	// this[i].value = value;
//	// if (dataType) {
//	// this[i].dataType = dataType;
//	// }
//	// }
//
//	this[name] = value;
//	if (value == null || value == undefined) {
//		this[name] = null;
//		delete this[name];
//	}
//}
//
//function parameters_getParameter(name) {
//	// if (typeof (name) == "number") {
//	// return this[name].value;
//	// } else {
//	// var count = this.length;
//	// for (var i = 0; i < count; i++) {
//	// if (this[i].name == name) {
//	// return this[i].value;
//	// break;
//	// }
//	// }
//	// return "";
//	// }
//	return this[name] || "";
//}

function _finishInitializtion() {
	for (var i = 0; i < _tabset_list.length; i++) {
		initElement(_tabset_list[i]);
	}
}

function initDocument() {
	fireUserEvent("document_onInit", []);
	var d1 = new Date();
	initElements(document.body);
	for (var i = 0; i < _array_dataset.length; i++) {
		var dataset = _array_dataset[i];
		if (dataset.masterDataset) {
			dataset.setMasterDataset(dataset.masterDataset, dataset.references);
		}
		var event_name = getElementEventName(dataset, "onFilterRecord");
		dataset.filtered = isUserEventDefined(event_name);
		dataset.initDocumentFlag = true;
		// dataset.refreshControls();
		dataset.pageElement = "document";
	}

	// tabset
//	_finishInitializtion();

	language = "javascript";

	fireUserEvent("document_afterInit", []);

	document.body.style.visibility = "visible";

	if (!$.browser.msie || parseInt($.browser.version) == 10) {
		UIUtil.loadFile("/public/lib/fontawesome/css/font.css");
	}
	UIUtil.loadFile("/public/lib/fontawesome/css/font-awesome.css");
	initCallGetter();
}

var _array_dropdown = new Array();

var _calendarControl = null;
var _tmp_dataset_date = null;

function createDropDown(id) {
	var dropdown = new Object();
	dropdown.id = id;
	// dropdown.clearCache = dropdown_clearCache;
	return dropdown;
}

function initDropDown(dropdown) {
	_array_dropdown[_array_dropdown.length] = dropdown;
}
// yjw
function getDropDownByID(ID) {
	for (var i = 0; i < _array_dropdown.length; i++) {
		if (_array_dropdown[i].id == ID)
			return _array_dropdown[i];
	}
	return window[ID];
}
// yjw
function getDropDowns() {
	return _array_dropdown;
}
function _initDropDown(datasetId, dropdownId, selectValues, ddsfiles, lable, fileMapString, field, height, readOnly, required, type) {
	var dropDown = getDropDownByID(dropdownId);
	if (!dropDown) {
		if (selectValues && (selectValues.lastIndexOf(';') == (selectValues.length - 1))) {
			selectValues = selectValues.substring(0, selectValues.length - 1);
		}
		if (required != "true") {
			// selectValues = ";" + selectValues;
		}
	}
	var dropDownDataset = getDatasetByID(datasetId);
	if (!dropDownDataset) {
		var dds_t = createDataset(datasetId, '', selectValues), dds_f;
		dds_t.readOnly = readOnly;
		dds_t.pageSize = 1000;
		dds_t.pageIndex = 1;
		dds_t.pageCount = 1;
		dds_t.masterDataset = "";
		dds_t.type = "dropdown";
		dds_t.references = "";
		dds_t.submitData = "allchange";
		dds_t.autoLoadPage = true;
		dds_t.autoLoadDetail = true;
		dds_t.downloadUrl = getDecodeStr("~2fextraservice~2fdownloaddata");
		dds_t.sessionKey = "";
		dds_t.insertOnEmpty = false;
		dds_t.tag = "";
		dds_t.initDocumentFlag = false;

		if (ddsfiles) {
			var temp = ddsfiles;
			var temps = temp.split(",");
			for (var i = 0; i < temps.length; i++) {
				dds_f = dds_t.addField(temps[i], "string");
				dds_f.label = lable;
				dds_f.size = 0;
				dds_f.scale = 0;
				dds_f.readOnly = false;
				dds_f.required = false;
				dds_f.nullable = true;
				dds_f.defaultValue = getDecodeStr("");
				dds_f.updatable = true;
				dds_f.valueProtected = false;
				dds_f.visible = true;
				dds_f.autoGenId = false;
				dds_f.tableName = "";
				// dds_f.fieldName = temps[i].split("=")[1];
				dds_f.tag = "";
				dds_f.editorType = "";
				dds_f.dropDown = "";
				dds_f.mask = getDecodeStr("");
				dds_f.maskErrorMessage = getDecodeStr("");
				dds_f.toolTip = getDecodeStr("");
				dds_f.lobDownloadURL = getDecodeStr("");
				dds_f.lobPopupURL = getDecodeStr("");
			}
		}
		initDataset(dds_t);

		// dropdown
		// eval("var "+dropdownId+" =createDropDown(dropdownId);var
		// dd_t="+dropdownId+";");
		var dd_t = createDropDown(dropdownId);
		if (type) {
			dd_t.type = type;
		} else {
			dd_t.type = "list";
		}
		dd_t.cache = true;
		dd_t.fixed = true;
		dd_t.fieldMap = fileMapString;
		dd_t.autoDropDown = true;
		dd_t.editable = true;
		if (height && height != "") {
			dd_t.height = height;
		} else {
			dd_t.height = 0;
		}
		dd_t.tag = "";
		dd_t.dataset = datasetId;
		dd_t.fields = field;
		dd_t.showHeader = false;
		// _array_dropdown[_array_dropdown.length]=dd_t;
		initDropDown(dd_t);
	} else {
	}
}

function _initDropDown_cust1(datasetId, dropDownId, type, fileMap, viewType, field, height, searchField, url) {
	// dropdown
	var dropDown = getDropDownByID(dropDownId);
	if (!dropDown) {
		var dd_t = createDropDown(dropDownId);
		dd_t.type = type;
		dd_t.cache = true;
		dd_t.fixed = true;
		dd_t.fieldMeta = "";
		dd_t.fieldMap = fileMap;
		dd_t.sessionKey = "dd";
		dd_t.autoDropDown = true;
		dd_t.editable = true;
		dd_t.tag = "";
		dd_t.viewType = viewType;
		dd_t.dataset = datasetId;
		dd_t.fields = field;
		dd_t.showHeader = false;
		dd_t.searchField = searchField;
		dd_t.url = url;
		if (height != '')
			dd_t.height = height;
		else
			dd_t.height = 0;
		initDropDown(dd_t);
	} else {
	}
}

function _initDropDown_cust2(datasetId, dropDownId, targetDataset, readOnly, type, fileMap, height, url, id) {
	var dropDown = getDropDownByID(dropDownId);
	if (!dropDown) {
		// dropdown dataset
		var dds_t = createDataset(datasetId, "", "");
		var dds_f;
		dds_t.readOnly = readOnly;
		dds_t.pageSize = 1000;
		dds_t.pageIndex = 1;
		dds_t.pageCount = 1;
		dds_t.masterDataset = "";
		dds_t.type = type;
		dds_t.references = "";
		dds_t.submitData = "allchange";
		dds_t.autoLoadPage = true;
		dds_t.autoLoadDetail = true;
		dds_t.downloadUrl = getDecodeStr("~2fextraservice~2fdownloaddata");
		dds_t.sessionKey = "";
		dds_t.insertOnEmpty = false;
		dds_t.tag = "";
		initDataset(dds_t);

		var dd_t = createDropDown(dropDownId);
		dd_t.type = type;
		dd_t.cache = true;
		dd_t.fixed = true;
		dd_t.fieldMeta = "";
		dd_t.fieldMap = fileMap;
		dd_t.sessionKey = "dd";
		dd_t.autoDropDown = true;
		dd_t.editable = true;
		dd_t.tag = "";
		dd_t.url = url;
		dd_t.targetDataset = targetDataset;
		dd_t.dataset = datasetId;
		dd_t.fieldId = id;
		if (height != '')
			dd_t.height = height;
		else
			dd_t.height = 0;
		initDropDown(dd_t);
	} else {
	}
}
function parseOptions(jq) {
	var opts = {};
	var s = $.trim(jq.attr("data-options"));
	if (s) {
		var s1 = s.substring(0, 1);
		var s2 = s.substring(s.length - 1, 1);
		if (s1 != "{") {
			s = "{" + s;
		}
		if (s2 != "}") {
			s = s + "}";
		}
		opts = (new Function("return " + s))();
	}
	return opts;
}
// function buildComboboxJSON(dataset) {
// var json = [];
// if (true) {// !required
// var row = {
// data : "",
// dataName : ""
// };
// json[0] = row;
// }
// var record = dataset.getFirstRecord();
// while (record) {
// if (record[0]) {
// var row = {};
// row.data = record[0];
// row.dataName = record[1];
// json.push(row);
// }
// record = record.getNextRecord();
// }
// return json;
// }

function buildDropdownDatasetJSON(dataset, viewfield) {
	var json = [];
	var record = dataset.firstUnit;
	var viewfields = viewfield.split(',');
	while (record) {
		var row = {};
		for (var i = 0; i < viewfields.length; i++) {
			row[viewfields[i]] = record.getValue(viewfields[i]);
		}
		json.push(row);
		record = record.nextUnit;
	}
	return json;
}

function buildDropdownTreeDatasetJSON(dataset, viewfield) {
	var json = [];
	var record = dataset.firstUnit;
	while (record) {
		var row = {};
		row.label = record.getString(viewfield);
		row.id = record.getString('_id');
		row.pid = record.getString("_parentId") || '0';
		row.items = [ {
			"value" : "ajax1.htm",
			"label" : "Loading..."
		} ]
		json.push(row);
		record = record.nextUnit;
	}
	return json;
}

//function _createParameters() {
//	var parameters = {};// new Array();
//	parameters.setParameter = parameters_setParameter;
//	parameters.getParameter = parameters_getParameter;
//	return parameters;
//}

function unfireUserEvent(function_name) {
	var eventInfo = _user_events[function_name];
	if (eventInfo == null) {
	} else {
		_user_events[function_name] = null;
	}
}

function _downloadData(dataset, pageSize, pageIndex) {
	try {
		if (dataset.sessionKey) {
			var result = new Object();
			return result;

		}
	} catch (e) {
		processException(e);
	}
}

function getDatasets() {
	return _array_dataset;
}

function _dataset_getField(fields, fieldName) {
	var field = null;
	if (typeof (fieldName) == "number") {
		field = fields[fieldName];
	} else if (typeof (fieldName) == "string") {
		var fieldIndex = fields["_index_" + fieldName];
		if (!isNaN(fieldIndex))
			field = fields[fieldIndex];
	}
	return field;
}

function dataset_getField(fieldName) {
	var dataset = this;
	return _dataset_getField(dataset.fields, fieldName);
}

function appendFromDataString(dataset, fieldStr, recordStr, init) {
	if (!recordStr)
		return;

	var records = [];
	try {
		records = Function("return " + recordStr + ";")();
	} catch (e) {
		return;
	}
	if (records.length == 0) {
		return;
	}

	var fields = null;
	if (fieldStr) {
		fields = fieldStr.split(",");
	}
	for (var i = 0; i < records.length; i++) {
		var record = records[i];
		var swapRec = {}// new Array();
		swapRec.$data = record;
		// if (fields != null) {
		// for (var j = 0; j < fields.length; j++) {
		// var index;
		// index = dataset.fields["_index_" + fields[j]];
		// swapRec[index] = record[fields[j]];
		// }
		// record = swapRec;
		// }
		pArray_insert(dataset, "end", null, swapRec);
		if (init) {
			initRecord(swapRec, dataset);
		}
	}
}

function transferToDataString(dataset) {
	var result = "";
	var i = 0;
	var record = dataset.getFirstRecord();
	while (record) {
		if (i != 0)
			result += ";";
		for (var j = 0; j < dataset.fields.fieldCount; j++) {
			if (j != 0)
				result += ",";
			result += getEncodeStr(record.getString(j));
		}
		record = record.getNextRecord();
		i++;
	}
	return result;
}

function createDataset(ID, fieldStr, recordStr) {
	var dataset = new pArray();
	dataset.fields = new Array();
	dataset.parameters = {};
	dataset.updateItems = new Array();
	dataset.fields.fieldCount = 0;
	dataset.addField = dataset_addField;
	dataset.pageSize = 9999;
	dataset.pageCount = 1;
	dataset.pageIndex = 1;
	dataset.autoLoadPage = true;
	// yjw add ,change the state in resetDataSetRecordState() of bank.js
	dataset.loadCompleted = false;
	dataset.cachedData = {};
	dataset.asyncData= {};

	dataset._saveOldValue = record_saveOldValue;
	dataset._getValue = record_getValue;
	dataset._getString = record_getString;
	dataset._getViewString = record_getViewString;
	dataset._setValue = record_setValue;
	dataset._getCurValue = record_getCurValue;
	dataset._setCurValue = record_setCurValue;
	dataset._getOldValue = record_getOldValue;
	dataset._setOldValue = record_setOldValue;
	dataset._getPrevRecord = record_getPrevRecord;
	dataset._getNextRecord = record_getNextRecord;

	dataset.validate = dataset_validate;
	dataset.getField = dataset_getField;
	dataset.getValue = dataset_getValue;
	dataset.getString = dataset_getString;
	dataset.getViewString = dataset_getViewString;
	dataset.setValue = dataset_setValue;
	dataset.setValue2 = dataset_setValue_2;
	dataset.getCurValue = dataset_getCurValue;
	dataset.setCurValue = dataset_setCurValue;
	dataset.getOldValue = dataset_getOldValue;
	dataset.setOldValue = dataset_setOldValue;
	dataset.disableControls = dataset_disableControls;
	dataset.enableControls = dataset_enableControls;
	dataset.disableEvents = dataset_disableEvents;
	dataset.enableEvents = dataset_enableEvents;
	dataset.refreshControls = dataset_refreshControls;
	dataset.setRecord = dataset_setRecord;
	dataset.setReadOnly = dataset_setReadOnly;
	// yjw add begin
	dataset.setAllFieldsReadOnly = dataset_setAllFieldsReadOnly;
	// yjw add end
	dataset.setFieldReadOnly = dataset_setFieldReadOnly;
	dataset.setFieldInvalid = dataset_setFieldInvalid;
	dataset.setFieldRequired = dataset_setFieldRequired;
	dataset.setFieldRule = dataset_setFieldRule;
	dataset.setFieldHidden = dataset_setFieldHidden;
	dataset.getFirstRecord = dataset_getFirstRecord;
	dataset.getLastRecord = dataset_getLastRecord;
	dataset.move = dataset_move;
	dataset.movePrev = dataset_movePrev;
	dataset.moveNext = dataset_moveNext;
	dataset.moveFirst = dataset_moveFirst;
	dataset.moveLast = dataset_moveLast;
	dataset.find = dataset_find;
//	dataset.findRecordByUUID = dataset_findRecordByUUID;
	dataset.locate = dataset_locate;
	dataset.updateRecord = dataset_updateRecord;
	dataset.cancelRecord = dataset_cancelRecord;
	dataset.insertRecord = dataset_insertRecord;
	dataset.deleteRecord = dataset_deleteRecord;
	dataset.copyRecord = dataset_copyRecord;
	dataset.loadPage = dataset_loadPage;
	dataset.loadDetail = dataset_loadDetail;
	dataset.isPageLoaded = dataset_isPageLoaded;
	dataset.moveToPage = dataset_moveToPage;
	dataset.setMasterDataset = dataset_setMasterDataset;
	dataset.flushData = dataset_flushData;
	dataset.clearData = dataset_clearData;
	dataset.sort = dataset_sort;
	dataset.setParameter = dataset_setParameter;
	dataset.getParameter = dataset_getParameter;
	//dataset.getParameterName = dataset_getParameterName;
	//dataset.getParameterCount = dataset_getParameterCount;
	dataset.getRealRecordCounts = dataset_getRealRecordCounts;
	dataset.toJson = dataset_toJson;

	if (ID) {
		dataset.id = ID;
		//if (ID.indexOf("tmp") == -1) {// tmp is dropdown tmp id,do not need
			// cache
			_array_dataset[_array_dataset.length] = dataset;
			_array_dataset[ID] = dataset;
		//}
	}

	if (fieldStr) {
		var fields = fieldStr.split(",");
		for (var i = 0; i < fields.length; i++) {
			dataset.addField(fields[i]);
		}
	}

	appendFromDataString(dataset, null, recordStr);

	return dataset;
}

function dataset_validate() {
	var dataset = this;
	// var valid = _FieldValid(dataset);
	// if (typeof (valid) == "string") {
	// return valid;
	// }
	// return !valid["isValid"];
	return validateDataset(dataset);
}

function dataset_setParameter(name, value, dataType) {
	this.parameters[name] = value;
}

function dataset_getParameter(name) {
	return this.parameters[name];
}

function dataset_getParameterName(index) {
	return this.parameters.getParameter(index);
}

function dataset_getParameterCount() {
	var dataset = this;
	return dataset.parameters.length;
}

function dataset_addField(name, dataType) {
	var dataset = this;
	try {
		if (getValidStr(name) == "")
			throw UIKit.i18n.constErrEmptyFieldName;

//		if (dataset.prepared)
//			throw UIKit.i18n.constErrAddDataField;

		var _name = name;
		var field = new Object;
		var i = dataset.fields.length;
		dataset.fields["_index_" + _name] = i;
		dataset.fields[i] = field;
		dataset.fields.fieldCount++;
		field.index = i;
		field.dataset = dataset;
		field.fields = dataset.fields;
		field.name = _name;
		field.label = name;
		field.fieldName = name;
		field.dataType = dataType;

		switch (dataType) {
		case "string": {
			field.align = "left";
			field.vAlign = "top";
			break;
		}

		case "byte":
		case "short":
		case "int":
		case "long":
		case "float":
		case "double":
		case "currency":
		case "number":
		case "bigdecimal": {
			field.align = "right";
			field.vAlign = "top";
			break;
		}

		case "boolean": {
			field.align = "center";
			field.vAlign = "middle";
			break;
		}

			/*
			 * added by wangpeng 20091126 BMS-2274 predatepostdate begin
			 */
		case "predate":
		case "postdate":
		case "date": {
			field.align = "left";
			field.vAlign = "top";
			break;
		}

		case "time": {
			field.align = "left";
			field.vAlign = "top";
			break;
		}
		case "timestamp": {
			field.align = "left";
			field.vAlign = "top";
			break;
		}

		default: {
			field.align = "left";
			field.vAlign = "top";
			break;
		}
		}
		return field;
	} catch (e) {
		processException(e);
	}
}

function _addUpdateItem(dataset) {
	var item = new Object();
	dataset.updateItems[dataset.updateItems.length] = item;
	return item;
}

function initFieldArray(dataset, fields) {
	var fieldCount = fields.fieldCount;
	fields.dataset = dataset;

	for (var i = 0; i < fieldCount; i++) {
		if (dataset.id) {
			if (fields[i].id && typeof (_element_property) != "undefined") {
				var root = _element_property[fields[i].id];
				if (root) {
					var property_count = root.length;
					for (var j = 0; j < property_count; j++)
						eval("fields[i]." + root[j].property + "=getDecodeStr(root[j].value)");
				}
			}
		}

		fields[fieldCount + i] = new Object;
		fields[fieldCount + i].name = "_cur_" + fields[i].name;
		fields[fieldCount + i].dataType = fields[i].dataType;
		fields["_index__cur_" + fields[i].name] = fieldCount + i;
		fields[fieldCount * 2 + i] = new Object;
		fields[fieldCount * 2 + i].name = "_old_" + fields[i].name;
		fields[fieldCount * 2 + i].dataType = fields[i].dataType;
		fields["_index__old_" + fields[i].name] = fieldCount * 2 + i;

		fields[i].readOnly = isTrue(fields[i].readOnly);
		fireUserEvent(getElementEventName(dataset, "onInitField"), [ dataset, fields[i] ]);
		// fireDatasetEvent(dataset, "onInitField", [dataset, fields[i]]);
	}
}

function initRecord(record, dataset, skipSaveOld) {
	// alert("record : " + record);
	record.dataset = dataset;
	record.fields = dataset.fields;
	record.recordState = "none";
	record.pageIndex = dataset.pageIndex;
	record.visible = true;

	record.saveOldValue = dataset._saveOldValue;
	record.getValue = dataset._getValue;
	record.getString = dataset._getString;
	record.getViewString = dataset._getViewString;
	record.setValue = dataset._setValue;
	record.getCurValue = dataset._getCurValue;
	record.setCurValue = dataset._setCurValue;
	record.getOldValue = dataset._getOldValue;
	record.setOldValue = dataset._setOldValue;
	record.getPrevRecord = dataset._getPrevRecord;
	record.getNextRecord = dataset._getNextRecord;
	record.getJsonValue = _record_getJsonValue;

	if (!skipSaveOld)
		record.saveOldValue();

	//
}

function initDataset(dataset) {
	if (dataset.prepared)
		return;

	dataset.disableControlCount = 1;
	dataset.disableEventCount = 1;
	try {
		if (dataset.id && typeof (_element_property) != "undefined") {
			var root = _element_property[dataset.id];
			if (root) {
				var property_count = root.length;
				for (var i = 0; i < property_count; i++)
					eval("dataset." + root[i].property + "=getDecodeStr(root[i].value)");
			}
		}

		// dataset.window = window;

		dataset.bof = true;
		dataset.eof = true;
		dataset.state = "none";
		dataset.readOnly = isTrue(dataset.readOnly);
		dataset.sortFields = "";
		dataset.loadedPage = new Array();
		if (dataset.pageIndex > 0)
			dataset.loadedPage[dataset.pageIndex - 1] = true;

		fireUserEvent(getElementEventName(dataset, "onInitDataset"), [ dataset ]);
		// fireDatasetEvent(dataset, "onInitDataset", [dataset]);
		dataset.setReadOnly(isTrue(dataset.readOnly));
		initFieldArray(dataset, dataset.fields);
		var record = dataset.firstUnit;
		while (record) {
			initRecord(record, dataset);
			record = record.nextUnit;
		}
		dataset.prepared = true;
	} finally {
		dataset.disableControlCount = 0;
		dataset.disableEventCount = 0;
	}

	if (dataset.pageIndex == 1 || !dataset.autoLoadPage) {
		dataset.moveFirst();
	} else {
		dataset.setRecord(dataset.getFirstRecord());
	}

	if (!dataset.record) {
		if (dataset.insertOnEmpty && !dataset.readOnly) {
			dataset.insertRecord();
		}
	}
	fireUserEvent(getElementEventName(dataset, "afterInitDataset"), [ dataset ]);
}

function isFieldEditable(dataset, field) {
	if (field) {
		var editable = !(dataset.readOnly || field.readOnly);
		if (dataset.record) {
			var recordState = dataset.record.recordState;
			editable = (editable && !((recordState == "none" || recordState == "modify") && field.valueProtected));
		}
	} else {
		var editable = true;
	}
	return editable;
}

function _dataset_setMasterDataset(dataset, masterDataset, referencesString) {
	if (dataset.masterDataset) {
		var array = dataset.masterDataset.detailDatasets;
		if (array)
			pArray_ex_delete(array, dataset);
	}

	if (typeof (masterDataset) == "string")
		masterDataset = getDatasetByID(masterDataset);
	dataset.masterDataset = masterDataset;
	if (masterDataset) {
		var array = masterDataset.detailDatasets;
		if (!array) {
			array = new pArray();
			masterDataset.detailDatasets = array;
		}
		pArray_ex_insert(array, dataset);

		var refs = referencesString.split(";");
		var field, fieldName;
		dataset.referencesString = referencesString;
		dataset.references = new Array();
		for (var i = 0; i < refs.length; i++) {
			var index = refs[i].indexOf("=");
			dataset.references[i] = new Object();

			if (index >= 0) {
				fieldName = refs[i].substr(0, index);
			} else {
				fieldName = refs[i];
			}
			field = masterDataset.getField(fieldName);

			if (field) {
				dataset.references[i].masterField = field.name;
				dataset.references[i].masterIndex = field.index;
			} else
				throw UIUtil.format(UIKit.i18n.constErrCantFindMasterField, fieldName);

			if (index >= 0) {
				fieldName = refs[i].substr(index + 1);
			} else {
				fieldName = refs[i];
			}
			field = dataset.getField(fieldName);

			if (field) {
				dataset.references[i].detailField = field.name;
				dataset.references[i].detailIndex = field.index;
			} else {
				throw UIUtil.format(UIKit.i18n.constErrCantFindDetailField, fieldName);
			}
		}
		delete refs;

		delete dataset.loaded_detail;
		dataset.loaded_detail = new Array;
		masterDataset.loadDetail();
	}
}

function dataset_setMasterDataset(masterDataset, referencesString) {
	var dataset = this;
	try {
		_dataset_setMasterDataset(dataset, masterDataset, referencesString);
	} catch (e) {
		processException(e);
	}
}

function _dataset_loadDetail(dataset) {
	if (dataset.detailDatasets) {
		var unit = dataset.detailDatasets.firstUnit;
		while (unit && unit.data) {
			var detail_dataset = unit.data;

			if (dataset.record && dataset.record.recordState != "insert" && dataset.record.recordState != "new") {
				try {
					validateDatasetCursor(detail_dataset);
					if (detail_dataset.bof && detail_dataset.eof) {
						var keycode_founded = false;
						if (dataset.record) {
							var keycode = "";
							for (var i = 0; i < detail_dataset.references.length; i++) {
								keycode += dataset.record[detail_dataset.references[i].masterIndex];
							}

							for (var i = 0; i < detail_dataset.loaded_detail.length; i++) {
								if (detail_dataset.loaded_detail[i] == keycode) {
									keycode_founded = true;
									break;
								}
							}
						}

						if (!keycode_founded) {
							var dataset_inserted = false;
							var event_result = fireDatasetEvent(detail_dataset, "beforeLoadDetail", [ detail_dataset, dataset ]);
							if (event_result === false)
								return;

							if (detail_dataset.references.length > 0) {
								for (var i = 0; i < detail_dataset.references.length; i++) {
									detail_dataset.setParameter(detail_dataset.references[i].detailField, dataset.getValue(detail_dataset.references[i].masterIndex));
									var masterValue = dataset.getValue(detail_dataset.references[i].masterIndex);
									var dataType = dataset.getField(detail_dataset.references[i].masterIndex).dataType;
									/*
									 * modified by wangpeng 20091126 BMS-2274
									 * predatepostdate begin
									 */
									if (dataType == "timestamp" || dataType == "date" || dataType == "time" || dataType == "predate" || dataType == "postdate") {

										detail_dataset.setParameter(detail_dataset.references[i].detailField, formatDateTime(masterValue, dataType));
									}
									/*
									 * modified by wangpeng 20091126 BMS-2274
									 * predatepostdate end
									 */
								}

								// var result=_downloadData(detail_dataset);
								var result = _downloadData_new(detail_dataset);
								if (result && result.recordStr) {
									appendFromDataString(detail_dataset, result.fieldStr, result.recordStr, true);
								}
								delete result;
							}

							detail_dataset.loaded_detail[detail_dataset.loaded_detail.length] = keycode;
						}
					}
				} catch (e) {
					processException(e);
				}
			}

			detail_dataset.refreshControls();
			detail_dataset.moveFirst();
			unit = unit.nextUnit;
		}
	}
}

function dataset_loadDetail() {
	var dataset = this;
	try {
		_dataset_loadDetail(dataset);
	} catch (e) {
		processException(e);
	}
}

function dataset_isPageLoaded(pageIndex) {
	var dataset = this;
	return dataset.loadedPage[pageIndex - 1];
}

function _dataset_loadPage(dataset, pageIndex) {
	if (!dataset.autoLoadPage || pageIndex < 1 || pageIndex > dataset.pageCount || dataset.isPageLoaded(pageIndex))
		return;
	if (dataset.masterDataset)
		throw UIKit.i18n.constErrLoadPageOnDetailDataset;
	if (dataset.sortFields)
		throw UIKit.i18n.constErrLoadPageAfterSort;

	/* modify by shen_antonio 20091221 dropdown pageload BMS-2375 begin . */
	var result = _downloadData_new(dataset, dataset.pageSize, pageIndex);
	/* modify by shen_antonio 20091221 dropdown pageload BMS-2375 end . */
	if (result && result.recordStr) {
		var tmpArray = new pArray();
		tmpArray.fields = dataset.fields;
		appendFromDataString(tmpArray, result.fieldStr, result.recordStr);
		var record = tmpArray.lastUnit;
		while (record) {
			initRecord(record, dataset);
			record.pageIndex = pageIndex;
			record = record.prevUnit;
		}

		var inserted = false;
		var record = dataset.lastUnit;
		while (record) {
			if (record.pageIndex < pageIndex) {
				pArray_insertArray(dataset, "after", record, tmpArray);
				inserted = true;
				break;
			}
			record = record.prevUnit;
		}
		if (!inserted)
			pArray_insertArray(dataset, "begin", null, tmpArray);
		delete tmpArray;

		dataset.loadedPage[pageIndex - 1] = true;
		dataset.refreshControls();
	}
	delete result;
}

function dataset_loadPage(pageIndex) {
	try {
		var dataset = this;
		_dataset_loadPage(dataset, pageIndex);
	} catch (e) {
		processException(e);
	}
}

function _dataset_clearData(dataset, disableControls) {
	// if(dataset.length==0)return;
	if (disableControls)
		dataset.disableControls();
	var autoLoadPage = dataset.autoLoadPage;
	dataset.autoLoadPage = false;
	try {
		// yjw
		dataset.modified = false;
		if (dataset.loaded_detail)
			delete dataset.loaded_detail;
		if (dataset.loadedPage)
			delete dataset.loadedPage;
		dataset.loaded_detail = new Array();
		dataset.loadedPage = new Array();
		if (dataset.pageIndex > 0)
			dataset.loadedPage[dataset.pageIndex - 1] = true;
		pArray_clear(dataset);
		dataset.cachedData= {};
		dataset.asyncdata= {};
		_do_dataset_setRecord(dataset, null)
//		dataset.moveFirst();
	} finally {
		// yjw
		dataset.modified = true;
		if (disableControls)
			dataset.enableControls();
		dataset.autoLoadPage = autoLoadPage;
	}
}

function dataset_clearData(disableControls) {
	try {
		var dataset = this;
		_dataset_clearData(dataset, disableControls);
	} catch (e) {
		processException(e);
	}
}

function freeDataset(dataset) {
	if (dataset.detailDatasets)
		pArray_clear(dataset.detailDatasets);
	if (dataset.editors)
		pArray_clear(dataset.editors);
	delete dataset.references;
	pArray_clear(dataset.fields);
	dataset.clearData();
	delete dataset;
}

function _dataset_flushData(dataset, pageIndex) {
	dataset.disableControls();
	try {
		dataset.clearData(true);

		var result = _downloadData(dataset, dataset.pageSize, pageIndex);
		if (result) {
			if (result.recordStr) {
				appendFromDataString(dataset, result.fieldStr, result.recordStr, true);
			}
			dataset.pageIndex = result.pageIndex;
			dataset.pageCount = result.pageCount;
		}
		delete result;
	} finally {
		dataset.enableControls();
		// dataset.refreshControls();
		dataset.loadDetail();
	}
}

function dataset_flushData(pageIndex) {
	try {
		var dataset = this;
		_dataset_flushData(dataset, pageIndex);
	} catch (e) {
		processException(e);
	}
}

function dataset_moveToPage(pageIndex) {
	try {
		var dataset = this;
		if (!dataset.isPageLoaded(pageIndex))
			_dataset_loadPage(dataset, pageIndex);
		var record = dataset.getFirstRecord();
		while (record) {
			if (record.pageIndex >= pageIndex) {
				_dataset_setRecord(dataset, record);
				break;
			}
			record = record.getNextRecord();
		}
	} catch (e) {
		processException(e);
	}
}

function record_saveOldValue() {
	var record = this;
	record.$olddata = $.extend({}, record.$data);
	// var fieldCount = record.fields.fieldCount;
	// for (var i = 0; i < fieldCount; i++) {
	// record[fieldCount + i] = record[i];
	// record[fieldCount * 2 + i] = record[i];
	// }
}

function _dataset_sort(dataset, fields) {

	function quickSort(_array, _fields, _low, _high) {

		function compareRecord(record, _mid_data) {
			if (_fields.length > 0) {
				var value1, value2;
				for (var i = 0; i < _fields.length; i++) {
					if (_field[i].ascend) {
						value1 = 1;
						value2 = -1;
					} else {
						value1 = -1;
						value2 = 1;
					}

					if (record.getValue(_fields[i].index) > _mid_data[i]) {
						return value1;
					} else if (record.getValue(_fields[i].index) < _mid_data[i]) {
						return value2;
					}
				}
			} else {
				if (record.recordno > _mid_data[0]) {
					return 1;
				} else if (record.recordno < _mid_data[0]) {
					return -1;
				}
			}
			return 0;
		}

		var low = _low;
		var high = _high;
		var mid = getInt((low + high) / 2);
		var mid_data = new Array();

		if (_fields.length > 0) {
			for (var i = 0; i < _fields.length; i++)
				mid_data[i] = _array[mid].getValue(_fields[i].index);
		} else {
			mid_data[0] = _array[mid].recordno;
		}

		do {
			while (compareRecord(_array[low], mid_data) < 0)
				low++;
			while (compareRecord(_array[high], mid_data) > 0)
				high--;

			if (low <= high) {
				var tmp = _array[low];
				_array[low] = _array[high];
				_array[high] = tmp;

				low++;
				high--;
			}
		} while (low <= high)

		if (high > _low)
			quickSort(_array, _fields, _low, high);
		if (_high > low)
			quickSort(_array, _fields, low, _high);
	}

	var _field = new Array();
	if (fields) {
		var fields_array = fields.split(",");
		for (var i = 0; i < fields_array.length; i++) {
			_field[i] = new Object();
			_field[i].ascend = true;

			var firstchar = fields_array[i].substring(0, 1);
			var fieldName;
			if (firstchar == "+" || firstchar == "-") {
				if (firstchar == "-")
					_field[i].ascend = false;
				fieldName = fields_array[i].substring(1, fields_array[i].length);
			} else {
				fieldName = fields_array[i];
			}

			for (var j = 0; j < dataset.fields.fieldCount; j++) {
				if (compareText(fieldName, dataset.fields[j].name)) {
					_field[i].index = j;
					break;
				}
			}
		}
	}

	function customSort(_array, _low, _high) {

		function compareRecord(record1, record2) {
			var event_name = getElementEventName(dataset, "onCompareRecord");
			if (isUserEventDefined(event_name)) {
				return fireUserEvent(event_name, [ record1.dataset, record1, record2 ]);
			}
		}

		var low = _low;
		var high = _high;
		var mid_record = _array[getInt((low + high) / 2)];

		do {
			while (compareRecord(_array[low], mid_record) < 0)
				low++;
			while (compareRecord(_array[high], mid_record) > 0)
				high--;

			if (low <= high) {
				var tmp = _array[low];
				_array[low] = _array[high];
				_array[high] = tmp;

				low++;
				high--;
			}
		} while (low <= high)

		if (high > _low)
			customSort(_array, _low, high);
		if (_high > low)
			customSort(_array, low, _high);
	}

	var _field = new Array();
	if (fields) {
		if (fields != "#custom") {
			var fields_array = fields.split(",");
			for (var i = 0; i < fields_array.length; i++) {
				_field[i] = new Object();
				_field[i].ascend = true;

				var firstchar = fields_array[i].substring(0, 1);
				var fieldName;
				if (firstchar == "+" || firstchar == "-") {
					if (firstchar == "-")
						_field[i].ascend = false;
					fieldName = fields_array[i].substring(1, fields_array[i].length);
				} else {
					fieldName = fields_array[i];
				}

				for (var j = 0; j < dataset.fields.fieldCount; j++) {
					if (compareText(fieldName, dataset.fields[j].name)) {
						_field[i].index = j;
						break;
					}
				}
			}
		}
	}

	if (!dataset.firstUnit)
		return;

	var tmp_array = new Array();
	try {
		var record = dataset.firstUnit;
		var i = 0;
		while (record) {
			tmp_array[i++] = record;
			if (!dataset.sortFields)
				record.recordno = i;
			record = record.nextUnit;
		}

		dataset.sortFields = fields;
		if (fields != "#custom") {
			quickSort(tmp_array, _field, 0, tmp_array.length - 1);
		} else {
			customSort(tmp_array, 0, tmp_array.length - 1);
		}

		dataset.firstUnit = null;
		dataset.lastUnit = null;
		dataset.length = 0;
		for (var i = 0; i < tmp_array.length; i++) {
			pArray_insert(dataset, "end", null, tmp_array[i]);
		}

		dataset.refreshControls();
	} finally {
		delete tmp_array;
		for (var i = 0; i < _field.length; i++)
			delete _field[i];
		delete _field;
	}
}

function dataset_sort(fields) {
	try {
		var dataset = this;
		_dataset_sort(dataset, fields);
	} catch (e) {
		processException(e);
	}
}

function dataset_setReadOnly(readOnly) {
	var dataset = this;
	dataset.readOnly = readOnly;
	_broadcastDatasetMsg(_notifyDatasetStateChanged, dataset);
}

// yjw add
function dataset_setAllFieldsReadOnly(readOnly) {
	var dataset = this;
	for (var i = 0; i < dataset.fields.length; i++) {
		if (typeof (dataset.fields[i].fieldName) != "undefined") {
			dataset_setFieldReadOnly2(dataset, dataset.fields[i].fieldName, readOnly);
		}
	}
}

function dataset_setFieldHidden(fieldName, hidden) {
	var dataset = this;
	var field = dataset.getField(fieldName);
	var fieldName = field.fieldName;
	var fieldDom = $("#editor_" + fieldName);
	var labelDom = $("#fldlabel_" + fieldName);
	switch (field.editType) {
	case "datalabel":
	case "textarea":
	case "radio":
	case "checkbox":
	case "text":
		if (fieldDom) {
			if (hidden)
				fieldDom.parent().hide();
			else
				fieldDom.parent().show();
		}
		if (labelDom) {
			if (hidden)
				labelDom.parent().hide();
			else
				labelDom.parent().show();
		}
		break;
	case "postdate":
	case "predate":
	case "timestamp":
	case "date":
	case "select":
		if (fieldDom) {
			if (hidden)
				fieldDom.parent().parent().hide();
			else
				fieldDom.parent().parent().show();
		}
		if (labelDom) {
			if (hidden)
				labelDom.parent().hide();
			else
				labelDom.parent().show();
		}
		break;
	}
}
function dataset_setFieldInvalid(fieldName, invalid) {
	var dataset = this;
	var field = dataset.getField(fieldName);
	if (field) {
		field.invalid = !!invalid;
		if (field.invalid) {
			_broadcastFieldMsg(_notifyFieldStateChanged, dataset, dataset.record, field, {
				invalid : invalid
			});
		}
	} else
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, dataset.id + "." + fieldName);
}
function dataset_setFieldReadOnly(fieldName, readOnly) {
	var dataset = this;
	var field = dataset.getField(fieldName);
	if (field) {
		field.readOnly = readOnly;
		// if(field.tag=="select"||field.tag=="selectCQ"){
		// var fieldName2 = fieldName+"Name";
		// dataset_setFieldReadOnly2(dataset,fieldName2,readOnly);
		// }
		_broadcastFieldMsg(_notifyFieldStateChanged, dataset, dataset.record, field, {
			readonly : readOnly
		});
	} else
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, dataset.id + "." + fieldName);
}
function dataset_setFieldRequired(fieldName, required) {
	var dataset = this;
	var field = dataset.getField(fieldName);
	if (field) {
		field.required = required;
		_broadcastFieldMsg(_notifyFieldStateChanged, dataset, dataset.record, field, {
			required : required
		});
	} else
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, dataset.id + "." + fieldName);
}
// by sq
function dataset_setFieldRule(fieldName, rule, message) {
	var dataset = this;
	var field = dataset.getField(fieldName);
	if (field) {
		field.mask = rule;
		field.maskErrorMessage = message;
	} else
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, dataset.id + "." + fieldName);
}
function dataset_setFieldReadOnly2(dataset, fieldName, readOnly) {
	var field2 = dataset.getField(fieldName);
	if (field2) {
		field2.readOnly = readOnly;
		_broadcastFieldMsg(_notifyFieldStateChanged, dataset, dataset.record, field2, {
			readonly : readOnly
		});
	} else
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, dataset.id + "." + fieldName);
}

function fireDatasetEvent(dataset, eventName, param) {
	if (dataset.disableEventCount > 0)
		return;
	var result;
	result = fireUserEvent(getElementEventName(dataset, eventName), param);
	return result;
}

function dataset_isRecordValid(record) {
	if (!record)
		return false;
	else {
		var result = (record.recordState != "delete" && record.recordState != "discard" && record.visible);
		var dataset = record.dataset;
		var masterDataset = dataset.masterDataset;

		if (result) {
			if (masterDataset) {
				if (!masterDataset.record)
					return false;
				for (var i = 0; i < dataset.references.length; i++) {
					// if
					// (masterDataset.getCurValue(dataset.references[i].masterIndex)!=
					// record[dataset.references[i].detailIndex]){
					// result=false;
					// break;
					// }
					var masterValue = getStringValue(masterDataset.getCurValue(dataset.references[i].masterIndex));
					var detailValue = getStringValue(getTypedValue(record[dataset.references[i].detailIndex], dataset.getField(dataset.references[i].detailIndex).dataType));
					if (compareText(masterValue, detailValue) == false) {
						result = false;
						break;
					}
				}
			}

			if (dataset.filtered && !(record == dataset.record && dataset.state != "none")) {
				var event_name = getElementEventName(dataset, "onFilterRecord");
				if (isUserEventDefined(event_name)) {
					if (!fireUserEvent(event_name, [ dataset, record ]))
						result = false;
				}
			}
		}
		return result;
	}
}

function dataset_setBofnEof(dataset, BofValue, EofValue) {
	if (dataset.bof != BofValue || dataset.eof != EofValue) {
		dataset.bof = BofValue;
		dataset.eof = EofValue;
		_broadcastDatasetMsg(_notifyDatasetStateChanged, dataset, dataset.record);
	}
}

function _do_dataset_setRecord(dataset, record) {
	// setRecord(null)
	// if(!record){
	// dataset.record="";
	// dataset.modified=false;
	// _broadcastDatasetMsg(_notifyDatasetCursorChanged, dataset, "");
	// }
	if (dataset.record != record) {
		if (dataset.record) {
			_dataset_updateRecord(dataset);
		}

		if (dataset.detailDatasets) {
			var unit = dataset.detailDatasets.firstUnit;
			while (unit) {
				var detailDataset = unit.data;
				_dataset_updateRecord(detailDataset);
				unit = unit.nextUnit;
			}
		}

		var event_result = fireDatasetEvent(dataset, "beforeScroll", [ dataset ]);
		if (event_result === false)
			return;

		dataset.record = record;
		dataset.modified = false;

		if (dataset.disableControlCount < 1)
			dataset.loadDetail();

		fireDatasetEvent(dataset, "afterScroll", [ dataset, record ]);
		_broadcastDatasetMsg(_notifyDatasetStateChanged, dataset, record);
	}
	_broadcastDatasetMsg(_notifyDatasetCursorChanged, dataset, record);
}

function _dataset_setRecord(dataset, record) {
	_do_dataset_setRecord(dataset, record);
	if (record) {
		dataset_setBofnEof(dataset, false, false);
		dataset_setBofnEof(dataset, false, false);
	}
}

function dataset_setRecord(record) {
	try {
		_dataset_setRecord(this, record);
	} catch (e) {
		processException(e);
	}
}

function validateDatasetCursor(dataset) {
	var down_found = false, up_found = false;
	var curRecord = (dataset.record) ? dataset.record : dataset.firstUnit;

	var record = curRecord;
	while (record) {
		if (dataset_isRecordValid(record)) {
			_do_dataset_setRecord(dataset, record);
			up_found = true;
			break;
		}
		record = _record_getPrevRecord(record);
	}

	var record = curRecord;
	while (record) {
		if (dataset_isRecordValid(record)) {
			_do_dataset_setRecord(dataset, record);
			down_found = true;
			break;
		}
		record = _record_getNextRecord(record);
	}

	if (!up_found && !down_found)
		_do_dataset_setRecord(dataset, null);
	dataset_setBofnEof(dataset, (!up_found), (!down_found));
}

function dataset_setState(dataset, state) {
	dataset.state = state;

	_broadcastDatasetMsg(_notifyDatasetStateChanged, dataset, dataset.record);
	fireDatasetEvent(dataset, "onStateChanged", [ dataset ]);
}

function dataset_getState(dataset) {
	return dataset.state;
}

function _record_getValue(record, fieldName) {
	var dataset = record.dataset;

	var field = dataset.getField(fieldName);
	if (!field) {
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, record.dataset.id + "." + fieldName);
	}

	var result = getTypedValue(record.$data[field.fieldName], field.dataType);
	return result;
}

function record_getValue(fieldName) {
	try {
		return _record_getValue(this, fieldName);
	} catch (e) {
		processException(e);
	}
}
function _record_getJsonValue(fieldName) {
	var record = this;
	var result = "";
	var field = record.dataset.getField(fieldName);
	if (field) {
		var value = record.getValue(field.fieldName);
		switch (field.dataType) {
		case "string": {
			result = getValidStr(value);
			break;
		}
		case "byte":
			;
		case "short":
			;
		case "int":
			;
		case "long": {
			if (!isNaN(value))
				result = value + "";
			break;
		}
		case "float":
			;
		case "double":
			;
		case "currency":
			;
		case "bigdecimal": {
			if (!isNaN(value))
				result = formatFloat(value, field.scale);
			break;
		}
		case "predate":
			;
		case "postdate":
			;
		case "date": {
			if (typeof (value) == "object" && !isNaN(value)) {
				result = value.format(_VIEW_DATE_PATTERN);
			} else {
				result = "";
			}
			break;
		}
		case "time":
			;
			{
				if (typeof (value) == "object" && !isNaN(value)) {
					result = value.format(_VIEW_TIME_PATTERN);
				} else {
					result = "";
				}
				break;
			}
		case "timestamp": {
			if (typeof (value) == "object" && !isNaN(value)) {
				result = value.format(_VIEW_DATETIME_PATTERN);
			} else {
				result = "";
			}
			break;
		}

		case "boolean":
			;
		default: {
			result = getValidStr(value);
			break;
		}
		}
	}

	return result;

}
function record_getString(fieldName) {
	var record = this, field, result = "";
	var field = record.dataset.getField(fieldName);
	if (field) {
		var value = record.getValue(field.fieldName);
		switch (field.dataType) {
		case "string": {
			result = getValidStr(value);
			break;
		}
		case "byte":
			;
		case "short":
			;
		case "int":
			;
		case "long": {
			if (!isNaN(value))
				result = value + "";
			break;
		}
		case "float":
			;
		case "double":
			;
		case "currency":
			;
		case "bigdecimal": {
			if (!isNaN(value))
				result = formatFloat(value, field.scale);
			break;
		}
			/*
			 * added by wangpeng 20091126 BMS-2274 predatepostdate begin
			 */
		case "predate":
			;
		case "postdate":
			;
			/* added by wangpeng 20091126 BMS-2274 predatepostdate end */
		case "date":
			;
		case "time":
			;
		case "timestamp": {
			result = formatDateTime(value, field.dataType);
			break;
		}

		case "boolean":
			;
		default: {
			result = getValidStr(value);
			break;
		}
		}
	}
	try {
		if (typeof (field.tag) != "undefined" && field.tag != "" && field.tag == "selectName" && value == "") {
			// youjinwu
			result = getFieldSelectNameValue(record, field);

		}
		/* modified by wangpeng 20091216 radio begin */
		else if (typeof (field.tag) != "undefined" && field.tag != "" && field.tag == "radioName" && value == "") {
			result = getFieldRadioNameValue(record, field);
		}
		/* modified by wangpeng 20091216 radio end */
	} catch (e) {
	} finally {
		return result;
	}
}
/** add by shen_antonio 20080524 for currency type. */
function record_getViewString(fieldName) {
	var record = this, field, result = "";
	var field = record.dataset.getField(fieldName);
	if (field) {
		var value = record.getValue(field.fieldName);
		switch (field.dataType) {
		case "string": {
			result = getValidStr(value);
			break;
		}
		case "byte":
			;
		case "short":
			;
		case "int":
			;
		case "long": {
			if (!isNaN(value))
				result = value + "";
			break;
		}
			/** add by shen_antonio 20080524 for add currency type. */
		case "currency": {
			if (!isNaN(value)) {
				result = formatFloat(value, field.scale);
				result = formatCurrency(result);
			}
			break;
		}
		case "float":
			;
		case "double":
			;
		case "bigdecimal": {
			if (!isNaN(value))
				result = formatFloat(value, field.scale);
			break;
		}

			/*
			 * added by wangpeng 20091126 BMS-2274 predatepostdate begin
			 */
		case "predate":
			;
		case "postdate":
			;
			/* added by wangpeng 20091126 BMS-2274 predatepostdate end */
		case "date":
			;
		case "time":
			;
		case "timestamp": {
			result = formatViewDateTime(value, field.dataType);
			break;
		}

		case "boolean":
			;
		default: {
			result = getValidStr(value);
			break;
		}
		}
	}
	try {
		if (typeof (field.tag) != "undefined" && field.tag != "" && field.tag == "selectName" && value == "") {
			// youjinwu
			result = getFieldSelectNameValue(record, field);

		}
		/* added by wangpeng 20091207 radio begin */
		else if (field.tag == "radioName" && value == "") {
			result = getFieldRadioNameValue(record, field);
		}
		/* added by wangpeng 20091207 radio end */
	} catch (e) {
	} finally {
		return result;
	}
}

function record_getString_2(record, fieldName) {
	var field, result = "";
	var field = record.dataset.getField(fieldName);

	if (field) {
		var value = record.getValue(field.fieldName);
		switch (field.dataType) {
		case "string": {
			result = getValidStr(value);
			break;
		}

		case "byte":
			;
		case "short":
			;
		case "int":
			;
		case "long": {
			if (!isNaN(value))
				result = value + "";

			break;
		}
		case "float":
			;
		case "double":
			;
		case "bigdecimal": {
			if (!isNaN(value))
				result = formatFloat(value, field.scale);
			break;
		}
			/*
			 * added by wangpeng 20091126 BMS-2274 predatepostdate begin
			 */
		case "predate":
			;
		case "postdate":
			;
			/* added by wangpeng 20091126 BMS-2274 predatepostdate end */
		case "date":
			;
		case "time":
			;
		case "timestamp": {
			result = formatDateTime(value, field.dataType);
			break;
		}

		case "boolean":
			;
		default: {
			result = getValidStr(value);
			break;
		}
		}
	}
	return result;
}

function _record_setValue(record, fieldName, value) {
	var dataset = record.dataset;
	var fields = record.fields;

	var field = dataset.getField(fieldName);
	if (!field) {
		throw UIUtil.format(UIKit.i18n.constErrCantFindField, record.dataset.id + "." + field.fieldName);
	}

	if (field.mask && field.mask != "null") {
		/* shen_antonio . */
		if (value == "" && !field.required) {
			// not need check
		} else {
			var valid = false;
			/** modified by wangpeng 20091224 BMS-2376 value begin */
			/** Modified by HuangWeijing 2009-12-03 JIRA:BMS-2297 Begin */
			/** evaltest */
			/**  */
			value = (value + "").replace(/\\/g, '\\\\');
			/**  */
			value = value.replace(/\"/g, '\\"');
			/* modified by wangpeng 20091125 BMS-2274 \ begin */
			// eval("valid="+field.mask+".test(\""+(value+'').replace(/\\/g,'\\\\')+"\");");
			var rule;
			try {
				rule = eval(field.mask);
				var valid = new RegExp(rule).test(value + '');
				if (!valid) {
					if (field.maskErrorMessage) {
						throw field.maskErrorMessage.replace("%s", value);
					} else {
						throw UIUtil.format(UIKit.i18n.constErrInputMask, value);
					}
				}

			} catch (e) {
			}
			/* modified by wangpeng 20091125 BMS-2274 \ end */
			/** Modified by HuangWeijing 2009-12-03 JIRA:BMS-2297 End */
			/** modified by wangpeng 20091224 BMS-2376 value end */

		}
	}

	switch (field.dataType) {
	case "int":
		value = getValidStr(value);
		value = parseInt(parseFloat(value));
		if (isNaN(value))
			value = "";
		break;
	case "float":
	case "double":
		value = getValidStr(value);
		value = parseFloat(value);
		if (isNaN(value))
			value = "";
		break;
	case "predate":
		;
	case "postdate":
		;
	case "date":
		if ($.type(value) != "date") {
			value = getValidStr(value);
			value = parseDate(value);
		}
		break;
	case "timestamp":
		if ($.type(value) != "date") {
			value = getValidStr(value);
			value = parseTimestamp(value);
		}
		break;
	case "time":
		if ($.type(value) != "date") {
			value = getValidStr(value);
			value = new Date("2000/01/01 " + value.replace(/-/g, "/"));
		}
		break;
	case "boolean":
		value = isTrue(value);
		break;
	}
	// yjw modify , checked before the methord executing
	if (dataset.loadCompleted) {
		var event_result = fireDatasetEvent(dataset, "beforeChange", [ dataset, field, value ]);
		if (event_result === false) {
			_broadcastFieldMsg(_notifyFieldDataChanged, dataset, record, field);
			return;
		}
	}
	var eventName = getElementEventName(dataset, "onSetValue");
	// yjw modify , checked before the methord executing
	if (isUserEventDefined(eventName) && dataset.record && dataset.loadCompleted) {
		value = fireUserEvent(eventName, [ dataset, field, value ]);
	}

	record.$data[fieldName] = value;
	// yjw modify , checked before the methord executing
	dataset.modified = true;
	// if(dataset.loadCompleted){
	if (dataset.isCheckAll) {
		dataset.record = record;
	}
	fireDatasetEvent(dataset, "afterChange", [ dataset, field ]);
	// }
	if (record.recordState == "none")
		record.recordState = "modify";
	if (dataset.state == "none")
		dataset_setState(dataset, "modify");
	// if(fieldName!="select") {
	_broadcastFieldMsg(_notifyFieldDataChanged, dataset, record, field);
	// }
}

function _record_setValue_2(record, fieldName, value) {

	var dataset = record.dataset;
	var fields = record.fields;
	var fieldIndex = -1;

	if (typeof (fieldName) == "number") {
		fieldIndex = fieldName;
	} else if (typeof (fieldName) == "string") {
		fieldIndex = fields["_index_" + fieldName];
	}

	if (typeof (fields[fieldIndex]) == "undefined") {
		throw UIUtil.format(UIKit.i18n.constErrCantFindField,record.dataset.id + "." + fieldName);
	}

	var field = fields[fieldIndex];

	if (getValidStr(field.mask) != "") {
		/* shen_antonio . */
		if (value == "" && !field.required) {
			// not need check
		} else {
			var valid = false;
			/* modified by wangpeng 20091125 BMS-2274 \ begin */
			eval("valid=" + field.mask + ".test(\"" + (value + '').replace(/\\/g, '\\\\') + "\");");
			/* modified by wangpeng 20091125 BMS-2274 \ end */
			if (!valid) {
				if (field.maskErrorMessage) {
					throw field.maskErrorMessage.replace("%s", value);
				} else {
					throw UIUtil.format(UIKit.i18n.constErrInputMask, value);
				}
			}
		}
	}

	switch (field.dataType) {
	case "int":
		value = getValidStr(value);
		value = parseInt(value);
		break;
	case "float":
		value = getValidStr(value);
		value = parseFloat(value);
		break;
	case "double":
		value = getValidStr(value);
		// oublejs�
		// 
		value = value * 1;
		break;
	case "predate":
		;
	case "postdate":
		;
	case "date":
		;
	case "timestamp":
		value = getValidStr(value);
		value = new Date(value.replace(/-/g, "/"));
		break;
	case "time":
		value = getValidStr(value);
		value = new Date("2000/01/01 " + value.replace(/-/g, "/"));
		break;
	case "boolean":
		value = isTrue(value);
		break;
	}
	// yjw modify
	if (dataset.loadCompleted) {
		var event_result = fireDatasetEvent(dataset, "beforeChange", [ dataset, field, value ]);
		if (event_result === false) {
			return;
		}
	}
	var eventName = getElementEventName(dataset, "onSetValue");
	if (isUserEventDefined(eventName) && dataset.record && dataset.loadCompleted) {
		value = fireUserEvent(eventName, [ dataset, field, value ]);
	}

	record[fieldIndex] = value;
	// yjw modify
	// dataset.modified=true;

	// fireDatasetEvent(dataset, "afterChange", [dataset, field]);

	// if (record.recordState=="none") record.recordState="modify";
	// if (dataset.state=="none") dataset_setState(dataset, "modify");
	// _broadcastFieldMsg(_notifyFieldDataChanged, dataset, record, field);
}
function record_setValue(fieldName, value) {
	try {
		_record_setValue(this, fieldName, value);
	} catch (e) {
		processException(e);
	}
}

function record_getCurValue(fieldName) {
	var record = this;
	if (typeof (fieldName) == "number") {
		return record.getValue(fieldName + record.fields.fieldCount);
	} else {
		return record.getValue("_cur_" + fieldName);
	}
}

function record_setCurValue(fieldName, value) {
	var record = this;
	if (typeof (fieldName) == "number") {
		record.setValue(fieldName + record.fields.fieldCount, value);
	} else {
		record.setValue("_cur_" + fieldName, value);
	}
}

function record_getOldValue(fieldName) {
	var record = this;
	if (typeof (fieldName) == "number") {
		return record.getValue(fieldName + record.fields.fieldCount * 2);
	} else {
		return record.getValue("_old_" + fieldName);
	}
}

function record_setOldValue(fieldName, value) {
	var record = this;
	if (typeof (fieldName) == "number") {
		record.setValue(fieldName + record.fields.fieldCount * 2, value);
	} else {
		record.setValue("_old_" + fieldName, value);
	}
}

function dataset_getValue(fieldName) {
	var dataset = this;
	if (dataset.record)
		return dataset.record.getValue(fieldName);
	else
		return "";
}

function dataset_getString(fieldName) {
	var dataset = this;
	if (dataset.record)
		return dataset.record.getString(fieldName);
	else
		return "";
}

function dataset_getViewString(fieldName) {
	var dataset = this;
	if (dataset.record)
		return dataset.record.getViewString(fieldName);
	else
		return "";
}

function dataset_setValue(fieldName, value) {
	try {
		var dataset = this;
		if (dataset.record) {
			dataset.record.setValue(fieldName, value);
		} else {
			dataset.insertRecord();
			dataset.record.setValue(fieldName, value);
			// throw constErrNoCurrentRecord;
		}
	} catch (e) {
		processException(e);
	}
}
function dataset_setValue_2(fieldName, value) {
	try {
		var dataset = this;
		if (dataset.record) {
			dataset.record.setValue(fieldName, value);
		} else {
			dataset.insertRecord();
			dataset.record.setValue(fieldName, value);
			// throw constErrNoCurrentRecord;
		}
	} catch (e) {
		processException(e);
	}
}
function dataset_getCurValue(fieldName) {
	var dataset = this;
	if (typeof (fieldName) == "number") {
		return dataset.getValue(fieldName + dataset.fields.fieldCount);
	} else {
		return dataset.getValue("_cur_" + fieldName);
	}
}

function dataset_setCurValue(fieldName, value) {
	var dataset = this;
	if (typeof (fieldName) == "number") {
		dataset.setValue(fieldName + dataset.fields.fieldCount, value);
	} else {
		dataset.setValue("_cur_" + fieldName, value);
	}
}

function dataset_getOldValue(fieldName) {
	var dataset = this;
	if (typeof (fieldName) == "number") {
		return dataset.getValue(fieldName + dataset.fields.fieldCount * 2);
	} else {
		return dataset.getValue("_old_" + fieldName);
	}
}

function dataset_setOldValue(fieldName, value) {
	var dataset = this;
	if (typeof (fieldName) == "number") {
		dataset.setValue(fieldName + dataset.fields.fieldCount * 2, value);
	} else {
		dataset.setValue("_old_" + fieldName, value);
	}
}

function _record_getPrevRecord(record) {
	var _record = record;
	while (_record) {
		_record = _record.prevUnit;
		if (dataset_isRecordValid(_record))
			return _record;
	}
}

function record_getPrevRecord() {
	return _record_getPrevRecord(this);
}

function _record_getNextRecord(record) {
	var _record = record;
	while (_record) {
		_record = _record.nextUnit;
		if (dataset_isRecordValid(_record))
			return _record;
	}
}

function record_getNextRecord() {
	return _record_getNextRecord(this);
}

function dataset_disableControls() {
	var dataset = this;
	dataset.disableControlCount = dataset.disableControlCount + 1;
}

function dataset_enableControls() {
	var dataset = this;
	dataset.disableControlCount = (dataset.disableControlCount > 0) ? dataset.disableControlCount - 1 : 0;
	dataset.refreshControls();

}

function dataset_disableEvents() {
	var dataset = this;
	dataset.disableEventCount = dataset.disableEventCount + 1;
}

function dataset_enableEvents() {
	var dataset = this;
	dataset.disableEventCount = (dataset.disableEventCount > 0) ? dataset.disableEventCount - 1 : 0;
}

function dataset_refreshControls() {
	var dataset = this;
	_broadcastDatasetMsg(_notifyDatasetRefresh, dataset, dataset.record);
	validateDatasetCursor(dataset);
}

function _dataset_move(dataset, count) {
	var _record = dataset.record;
	if (!_record)
		_record = dataset.getFirstRecord();
	if (!_record)
		return;
	var record = _record;

	if (count > 0) {
		var old_pageIndex = record.pageIndex;
		var eof = false;
		for (var i = 0; i < count; i++) {
			var pageIndex = 0;

			_record = record.getNextRecord();
			if (!_record || (_record && _record.pageIndex != old_pageIndex)) {
				if (old_pageIndex < dataset.pageCount) {
					if (!dataset.isPageLoaded(old_pageIndex + 1)) {
						if ((i + dataset.pageSize < count) && (old_pageIndex + 1 < dataset.pageCount)) {
							i += dataset.pageSize - 1;
							_record = record;
						} else {
							_dataset_loadPage(dataset, old_pageIndex + 1);
							_record = record.getNextRecord();
						}
					}
				}
				old_pageIndex++;
			}

			if (_record) {
				record = _record;
			} else {
				eof = true;
				break;
			}
		}
		dataset_setBofnEof(dataset, (!dataset_isRecordValid(dataset.record)), eof);
	} else {
		var old_pageIndex = record.pageIndex;
		var bof = false;
		for (var i = count; i < 0; i++) {
			var pageIndex = 0;

			_record = record.getPrevRecord();
			if (!_record || (_record && _record.pageIndex != old_pageIndex)) {
				if (old_pageIndex > 1) {
					if (!dataset.isPageLoaded(old_pageIndex - 1)) {
						if ((i + dataset.pageSize < 0) && (old_pageIndex > 1)) {
							i += dataset.pageSize - 1;
							_record = record;
						} else {
							_dataset_loadPage(dataset, old_pageIndex - 1);
							_record = record.getPrevRecord();
						}
					}
				}
				old_pageIndex--;
			}

			if (_record) {
				record = _record;
			} else {
				bof = true;
				break;
			}
		}
		dataset_setBofnEof(dataset, bof, (!dataset_isRecordValid(dataset.record)));
	}

	if (record)
		_do_dataset_setRecord(dataset, record);
}

function dataset_move(count) {
	var dataset = this;
	try {
		_dataset_move(dataset, count);
	} catch (e) {
		processException(e);
	}
}

function dataset_movePrev() {
	var dataset = this;
	try {
		_dataset_move(dataset, -1);
	} catch (e) {
		processException(e);
	}
}

function dataset_moveNext() {
	var dataset = this;
	try {
		_dataset_move(dataset, 1);
	} catch (e) {
		processException(e);
	}
}

function _dataset_getFirstRecord(dataset) {
	var record = dataset.firstUnit;
	if (record && !dataset_isRecordValid(record))
		record = record.getNextRecord();
	return record;
}

function dataset_getFirstRecord() {
	return _dataset_getFirstRecord(this);
}

function dataset_moveFirst() {
	var dataset = this;

	try {
		if (!dataset.isPageLoaded(1))
			_dataset_loadPage(dataset, 1);
		_do_dataset_setRecord(dataset, dataset.getFirstRecord());
		dataset_setBofnEof(dataset, true, (!dataset_isRecordValid(dataset.record)));
	} catch (e) {
		processException(e);
	}
}

function _dataset_getLastRecord(dataset) {
	var record = dataset.lastUnit;
	if (!dataset_isRecordValid(record) && record)
		record = record.getPrevRecord();
	return record;
}

function dataset_getLastRecord() {
	return _dataset_getLastRecord(this);
}

function dataset_moveLast() {
	var dataset = this;

	try {
		if (!dataset.isPageLoaded(dataset.pageCount))
			_dataset_loadPage(dataset, dataset.pageCount);
		_do_dataset_setRecord(dataset, dataset.getLastRecord());
		dataset_setBofnEof(dataset, (!dataset_isRecordValid(dataset.record)), true);
	} catch (e) {
		processException(e);
	}
}

function dataset_find(fieldNames, values, startRecord) {

	function isMatching(fieldNames, values, record) {
		var result = true;
		for (var j = 0; j < fieldNames.length && j < values.length; j++) {
			if (!compareText(record.getString(fieldNames[j]), values[j])) {
				result = false;
				break;
			}
		}
		return result;
	}

	if (!fieldNames || !values)
		return false;

	var dataset = this;
	if (!dataset.record)
		return;
	if (isMatching(fieldNames, values, dataset.record))
		return dataset.record;

	var record = (startRecord) ? startRecord : dataset.getFirstRecord();
	while (record) {
		if (isMatching(fieldNames, values, record))
			return record;
		record = record.getNextRecord();
	}
}
//function dataset_findRecordByUUID(uuid) {
//	var record = this.getFirstRecord();
//	while (record) {
//		if (record.id == uuid) {
//			return record;
//		}
//		record = record.getNextRecord();
//	}
//}
function dataset_locate(fieldName, value, startRecord) {

	function isMatching(fieldName, value, record) {
		var tmpValue = record.getString(fieldName);
		return (tmpValue && compareText(tmpValue.substr(0, len), value));
	}

	if (!value)
		return false;

	var dataset = this;
	if (!dataset.record)
		return;
	if (isMatching(fieldName, value, dataset.record))
		return dataset.record;

	var len = value.length;
	var record = (startRecord) ? startRecord : dataset.getFirstRecord();
	while (record) {
		if (isMatching(fieldName, value, record))
			return record;
		record = record.getNextRecord();
	}
}

function _dataset_insertRecord(dataset, mode) {
	_dataset_updateRecord(dataset);
	var event_result = fireDatasetEvent(dataset, "beforeInsert", [ dataset, mode ]);
	if (event_result === false)
		return;

	var _masterDataset = dataset.masterDataset;
	if (_masterDataset) {
		if (_masterDataset.record) {
			_dataset_updateRecord(_masterDataset);
		}
	}

	var pageIndex = (dataset.record) ? dataset.record.pageIndex : 1;
	var newRecord = {
		$data : {}
	};// new Array();
	pArray_insert(dataset, mode, dataset.record, newRecord);
	initRecord(newRecord, dataset);
	switch (mode) {
	case "begin": {
		newRecord.pageIndex = 1;
		break;
	}
	case "end": {
		newRecord.pageIndex = dataset.pageCount;
		break;
	}
	default: {
		newRecord.pageIndex = pageIndex;
		break;
	}
	}

	newRecord.recordState = "new";
	newRecord.recordno = 9999;

	var _masterDataset = dataset.masterDataset;
	if (_masterDataset) {
		if (_masterDataset.record) {
			for (var i = 0; i < dataset.references.length; i++) {
				var fieldIndex = dataset.references[i].masterIndex;
				if (_masterDataset.getString(fieldIndex) == "") {
					var field = _masterDataset.getField(fieldIndex);
					switch (field.dataType) {
					case "string":
						_masterDataset.setValue(fieldIndex, _getAutoGenID());
						break;
					case "byte":
						;
					case "short":
						;
					case "int":
						;
					case "long":
						;
					case "float":
						;
					case "double":
						;
					case "bigdecimal":
						;
						var maxnum = 0;
						var record = _masterDataset.firstUnit;
						while (record) {
							if (record.getValue(fieldIndex) > maxnum) {
								maxnum = record.getValue(fieldIndex);
							}
							record = record.nextUnit;
						}
						_masterDataset.setValue(fieldIndex, maxnum + 1);
						break;
					}
				}
			}
			_dataset_updateRecord(_masterDataset);

			for (var i = 0; i < dataset.references.length; i++) {
				var reference = dataset.references[i];
				newRecord[reference.detailIndex] = _masterDataset.getValue(reference.masterIndex);
			}
		} else {
			throw UIKit.i18n.constErrNoMasterRecord;
		}
	}
	var fieldCount = dataset.fields.fieldCount;
	for (var i = 0; i < fieldCount; i++) {
		var field = dataset.fields[i];
		var defaultValue = getValidStr(field.defaultValue);
		if (defaultValue != "") {
			// newRecord[i] = defaultValue;
			newRecord.$data[field.fieldName] = defalutValue;
		}
	}
	dataset_setState(dataset, "insert");
	_broadcastDatasetMsg(_notifyDatasetInsert, dataset, dataset.record, [ mode, newRecord ]);
	_dataset_setRecord(dataset, newRecord);
	var fieldCount = dataset.fields.fieldCount;
	for (var i = 0; i < fieldCount; i++) {
		var field = dataset.fields[i];
		if (field.autoGenId) {
			dataset.setValue(i, _getAutoGenID());
		}
	}
	fireDatasetEvent(dataset, "afterInsert", [ dataset, mode ]);
	dataset.modified = true;
}

function dataset_insertRecord(mode) {
	try {
		_dataset_insertRecord(this, mode);
		return true;
	} catch (e) {
		processException(e);
		return false;
	}
}

function _dataset_deleteRecord(dataset) {
	if (!dataset.record)
		return;

	if (dataset.detailDatasets) {
		var unit = dataset.detailDatasets.firstUnit;
		while (unit && unit.data) {
			var detail_dataset = unit.data;
			if (detail_dataset.references.length > 0) {
				_dataset_updateRecord(detail_dataset);
				detail_dataset.moveFirst();
				while (!detail_dataset.eof) {
					detail_dataset.deleteRecord();
				}
			}
			detail_dataset.refreshControls();
			unit = unit.nextUnit;
		}
	}

	needUpdateEditor = false;
	try {
		if (dataset.record.recordState == "new" || dataset.record.recordState == "insert") {
			var event_result = fireDatasetEvent(dataset, "beforeDelete", [ dataset ]);
			if (event_result === false)
				return;

			dataset.record.recordState = "discard";
		} else {
			var event_result = fireDatasetEvent(dataset, "beforeDelete", [ dataset ]);
			if (event_result === false)
				return;

			dataset.record.recordState = "delete";
			_changeMasterRecordState(dataset);
		}

		dataset.modified = false;

		fireDatasetEvent(dataset, "afterDelete", [ dataset ]);
		dataset_setState(dataset, "none");

		_broadcastDatasetMsg(_notifyDatasetDelete, dataset, dataset.record);
		validateDatasetCursor(dataset);
	} finally {
		needUpdateEditor = true;
	}
}

function dataset_deleteRecord() {
	try {
		_dataset_deleteRecord(this);
	} catch (e) {
		processException(e);
	}
}

function _dataset_updateRecord(dataset) {
	if (!dataset.record)
		return;
	if (!dataset_isRecordValid(dataset.record))
		return;
	_broadcastDatasetMsg(_notifyDatasetBeforeUpdate, dataset, dataset.record);

	if (dataset.modified) {
		var vd = validateDataset;
		vd(dataset, function(field, errmsg) {
			throw "[" + field.label + "]: " + errmsg;
		});
		var fieldCount = dataset.fields.fieldCount;
		// for (var i = 0; i < fieldCount; i++) {
		// if (!isTrue(dataset.fields[i].readOnly) &&
		// isTrue(dataset.fields[i].required) && dataset.getString(i) == "" &&
		// dataset.fields[i].tag != "selectName") {
		// throw constErrFieldValueRequired.replace("%s",
		// dataset.fields[i].label);
		// }
		// }
		var event_result = fireDatasetEvent(dataset, "beforeUpdate", [ dataset ]);
		if (event_result === false)
			return;

		var detaildatasets = new Array();
		if (dataset.detailDatasets) {
			var unit = dataset.detailDatasets.firstUnit;
			while (unit && unit.data) {
				var detail_dataset = unit.data;
				if (detail_dataset.references.length > 0) {
					var disableCount = detail_dataset.disableControlCount;
					detail_dataset.disableControlCount = 1;
					try {
						var changed = false;
						_dataset_updateRecord(detail_dataset);
						detail_dataset.moveFirst();
						while (!detail_dataset.eof) {
							for (var i = 0; i < detail_dataset.references.length; i++) {
								var detailIndex = detail_dataset.references[i].detailIndex;
								var masterIndex = detail_dataset.references[i].masterIndex;
								if (detail_dataset.getValue(detailIndex) != dataset.getValue(masterIndex)) {
									detail_dataset.setValue(detailIndex, dataset.getValue(masterIndex));
									changed = true;
								}
							}
							_dataset_updateRecord(detail_dataset);
							detail_dataset.moveNext();
						}
					} finally {
						detail_dataset.disableControlCount = disableCount;
					}

					if (changed) {
						detaildatasets[detaildatasets.length] = detail_dataset;
					}
				}
				unit = unit.nextUnit;
			}
		}

		switch (dataset.record.recordState) {
		case "none": {
			dataset.record.recordState = "modify";
			_changeMasterRecordState(dataset);
			break;
		}
		case "new": {
			dataset.record.recordState = "insert";
			_changeMasterRecordState(dataset);
			break;
		}
		}

		// for (var i = 0; i < fieldCount; i++) {
		// dataset.record[fieldCount + i] = dataset.record[i];
		// }
		// dataset.record = data
		dataset.modified = false;

		fireDatasetEvent(dataset, "afterUpdate", [ dataset ]);
		dataset_setState(dataset, "none");

		for (var i = 0; i < detaildatasets.length; i++) {
			detail_dataset.refreshControls();
			validateDatasetCursor(detail_dataset);
		}
	} else {
		if (dataset.record.recordState == "new") {
			dataset.record.recordState = "discard";
			dataset_setState(dataset, "none");
			_broadcastDatasetMsg(_notifyDatasetDelete, dataset, dataset.record);
			validateDatasetCursor(dataset);
		}
	}
}

function dataset_updateRecord() {
	try {
		_dataset_updateRecord(this);
		return true;
	} catch (e) {
		processException(e);
		return false;
	}
}

function _dataset_cancelRecord(dataset) {
	if (!dataset.record)
		return;

	needUpdateEditor = false;
	try {
		if (dataset.record.recordState == "new") {
			var event_result = fireDatasetEvent(dataset, "beforeCancel", [ dataset ]);
			if (event_result === false)
				return;

			dataset.record.recordState = "discard";

			fireDatasetEvent(dataset, "afterCancel", [ dataset ]);

			dataset_setState(dataset, "none");
			_broadcastDatasetMsg(_notifyDatasetDelete, dataset, dataset.record);
			validateDatasetCursor(dataset);
		} else if (dataset.modified) {
			var event_result = fireDatasetEvent(dataset, "beforeCancel", [ dataset ]);
			if (event_result === false)
				return;

			// var fieldCount = dataset.fields.fieldCount;
			// for (var i = 0; i < fieldCount; i++) {
			// dataset.record[i] = dataset.record[fieldCount + i];
			// }
			var treeid = dataset.record.$data._id;
			dataset.record.$data = $.extend({}, dataset.record.$olddata);
			dataset.record.$data._id = treeid;
			dataset.modified = false;
			fireDatasetEvent(dataset, "afterCancel", [ dataset ]);
			dataset_setState(dataset, "none");
			_broadcastDatasetMsg(_notifyDatasetRefreshRecord, dataset, dataset.record);
		}
	} finally {
		needUpdateEditor = true;
	}
}

function dataset_cancelRecord() {
	try {
		_dataset_cancelRecord(this);
	} catch (e) {
		processException(e);
	}
}

function _dataset_copyRecord(dataset, record, fieldMap) {
	if (fieldMap) {
		var fieldmaps = new Array();
		var fields = fieldMap.split(";");
		var field1 = "", field2 = "";
		for (var i = 0; i < fields.length; i++) {
			fieldmaps[i] = new Object();
			var index = fields[i].indexOf("=");
			if (index >= 0) {
				field1 = fields[i].substr(0, index);
				field2 = fields[i].substr(index + 1);
			} else {
				field1 = fields[i];
				field2 = fields[i];
			}

			var value = record.getValue(field2);
			if (typeof (value) != "undefined")
				dataset.setValue(field1, value);
		}
	} else {
		for (var i = 0; i < dataset.fields.fieldCount; i++) {
			var fieldName = dataset.getField(i).name;
			var field = record.dataset.getField(fieldName);
			if (field) {
				var value = record.getValue(fieldName);
				if (typeof (value) != "undefined")
					dataset.setValue(fieldName, value);
			}
		}
	}
}

function dataset_copyRecord(record, fieldMap) {
	var dataset = this;
	_dataset_copyRecord(dataset, record, fieldMap);
}

var _broadcasting = false;
function _broadcastDatasetMsg(proc, dataset, record, reserved) {
	if (dataset.disableControlCount > 0)
		return;
	_broadcasting = true;
	var pArray = dataset.editors;
	if (pArray) {
		var unit = pArray.firstUnit;
		while (unit && unit.data) {
			proc(unit.data, dataset, record, reserved);
			unit = unit.nextUnit;
		}
	}
	_broadcasting = false;
}

function _broadcastFieldMsg(proc, dataset, record, field, reserved) {
	if (dataset.disableControlCount > 0)
		return;
	_broadcasting = true;
	var pArray = dataset.editors;
	if (pArray) {
		var unit = pArray.firstUnit;
		while (unit && unit.data) {
			proc(unit.data, dataset, record, field, reserved);
			unit = unit.nextUnit;
		}
	}
	_broadcasting = false;
}

function _FieldValid(dataset) {
	if (dataset.disableControlCount > 0)
		return {
			fieldArray : new Array(),
			isValid : false
		};
	var pArray = dataset.editors;
	var fieldArray = new Array();
	var elementArray = new Array();
	var fieldCount = dataset.fields.fieldCount;
	for (var i = 0; i < fieldCount; i++) {
		fieldArray.push(dataset.fields[i]);
	}
	var isValid = false;
	if (pArray) {
		var unit = pArray.firstUnit;
		while (unit && unit.data) {
			var element = unit.data;
			switch (element.getAttribute("extra")) {
			case "dropDownSelect":
			case "editor":
			case "dockeditor":
				var dataField = element.getAttribute("dataField");
				var field = dataset.getField(dataField);
				if (!field.readOnly) {
					if ($(element).parent().is(":hidden") && validateQuery(false, element)) {
						return $(element).attr("dataField");
					}
					isValid = validateQuery(isValid, element);
					elementArray.push(element.getAttribute("dataField"));
				}
				break;
			}
			unit = unit.nextUnit;
		}
	}
	for (var i = 0; i < fieldArray.length;) {
		var isAdd = true;
		for (var j = 0; j < elementArray.length; j++) {
			if (fieldArray[i] && fieldArray[i].fieldName == elementArray[j]) {
				fieldArray.splice(i, 1);
				isAdd = false;
			}
		}
		if (isAdd)
			i++;
	}
	return {
		fieldArray : fieldArray,
		isValid : isValid
	};
}
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//
// function _notifyDatasetCursorChanged(element, dataset, record, reserved) {
// switch (element.getAttribute("extra")) {
// case "datagrid": {
// refreshDatagridCursor(element, dataset, record, reserved);
// refreshElement(element, 'cursorChanged')
// break;
// }
// case "rdatagrid":
// refreshReadonlyTableCursor(element, dataset, record, reserved);
// break;
// case "datalabel": {
// refreshDatalabelValue(element, dataset, record);
// break;
// }
// case "dropDownSelect":
// if (dataset.type == "dropdown")
// break;
// // record.dropdownKey=true;
// case "editor": {
// refreshInputValue(element, dataset, record);
// element.isUserInput = false;
// break;
// }
// }
// }
//
// function _notifyDatasetBeforeUpdate(element, dataset, record, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "dockeditor": {
// _window.updateEditorInput(element);
// break;
// }
// }
// }
//
// function _notifyDatasetStateChanged(element, dataset, record, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "editor":
// ;
// case "dropDownSelect":
// case "dockeditor": {
// var field = _window.getElementField(element);
// element.setReadOnly(!isFieldEditable(dataset, field));
// break;
// }
// case "datapilot": {
// _window.refreshDataPilot(element);
// break;
// }
// case "datatable": {
// if (element.activeRow)
// _window.refreshTableRowIndicate(element.activeRow);
// break;
// }
// }
// }
//
// function _notifyDatasetInsert(element, dataset, record, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "datagrid": {
// var grid = $(element);
// var json = {};
// var _record = reserved[1];
// var jqueryid = _record.getString("_id") || "_j" + new Date().getTime()
// + $.uuid++;
// var mode = reserved[0];
// _record._uuid = jqueryid;
// json.record = _record;
// json._recordUUID = jqueryid;
// for (var i = 0; i < dataset.fields.fieldCount; i++) {
// var fieldName = dataset.getField(i).fieldName;
// var fieldValue = _record.getJsonValue(fieldName);
// json[fieldName] = fieldValue;
// // if(grid.attr("treeGrid") == "true" && fieldName=="_id") {
// // jqueryid = fieldValue;
// // _record._uuid = jqueryid;
// // json._recordUUID = jqueryid;
// // }
// }
// if (grid.attr("treeGrid") == "false") {
// var opts = grid.datagrid("options");
// var selectedIndex = opts.selectedIndex;
// if (selectedIndex > -1) {
// if (mode == "begin") {
// grid.datagrid('insertRow', {
// index : 0,
// row : json
// });
// } else if (mode == "before") {
// grid.datagrid('insertRow', {
// index : selectedIndex,
// row : json
// });
// } else if (mode == "after") {
// grid.datagrid('insertRow', {
// index : selectedIndex + 1,
// row : json
// });
// } else {
// grid.datagrid('appendRow', json);
// }
// } else {
// grid.datagrid('appendRow', json);
// }
// grid.datagrid('selectRecord', jqueryid);
// opts.init = true;
// opts.editing = false;
// } else {
// var opts = grid.treegrid("options");
// var selectedId = opts.selectedId;
// var loadFilter = opts.loadFilter;
// opts.loadFilter = function(data, nodeId) {
// return data;
// };
// if (selectedId) {
// if (!record) {
// mode = "end";
// }
// if (mode == "begin") {
// grid.treegrid('insert', {
// before : dataset.firstUnit[opts.idField],
// row : json
// });
// } else if (mode == "before") {
// grid.treegrid("insert", {
// before : selectedId,
// data : json
// });
// } else if (mode == "after") {
// grid.treegrid('insert', {
// after : selectedId,
// data : json
// });
// } else if (mode == "child") {
// json._parentId = selectedId;
// _record[record.fields["_index__parentid"]] = selectedId;
// opts.loadFilter = loadFilter;
// grid.treegrid('expand', selectedId);
// opts.loadFilter = function(data, nodeId) {
// return data;
// };
// grid.treegrid('append', {
// parent : selectedId,
// data : [ json ]
// });
// //
// } else {
// grid.treegrid('append', {
// data : [ json ]
// });
// }
// } else {
// grid.treegrid('append', {
// data : [ json ]
// });
// }
// opts.loadFilter = loadFilter;
// opts.init = true;
// opts.editing = false;
// }
//
// break;
// }
// case 'rdatagrid':
// var grid = $(element);
// var json = {};
// var _record = reserved[1];
// var jqueryid = _record.getString("_id") || "_j" + new Date().getTime()
// + $.uuid++;
// var mode = reserved[0];
// _record._uuid = jqueryid;
// json.record = _record;
// json._recordUUID = jqueryid;
// for (var i = 0; i < dataset.fields.fieldCount; i++) {
// var fieldName = dataset.getField(i).fieldName;
// var fieldValue = _record.getJsonValue(fieldName);
// json[fieldName] = fieldValue;
// }
// var opts = grid.rdatagrid("options");
// var selectedIndex = opts.selectedIndex;
// if (selectedIndex > -1) {
// if (mode == "begin") {
// grid.rdatagrid('insertRow', {
// index : 0,
// row : json
// });
// } else if (mode == "before") {
// grid.rdatagrid('insertRow', {
// index : selectedIndex,
// row : json
// });
// } else if (mode == "after") {
// grid.rdatagrid('insertRow', {
// index : selectedIndex + 1,
// row : json
// });
// } else {
// grid.rdatagrid('appendRow', json);
// }
// } else {
// grid.rdatagrid('appendRow', json);
// }
// grid.rdatagrid('selectRecord', jqueryid);
// opts.init = true;
// opts.editing = false;
// break;
// }
// }
//
// function _notifyDatasetDelete(element, dataset, record, reserved) {
// switch (element.getAttribute("extra")) {
// case "datagrid": {
// if (record) {
// var grid = $(element);
// if (grid.attr("treeGrid") == "false") {
// var opts = grid.datagrid("options");
// var selectedIndex = opts.selectedIndex;
// grid.datagrid('deleteRow', selectedIndex);
// opts.selectedIndex--;
// opts.editing = false;
// } else {
// var nodes = grid.treegrid("getChildren", record._uuid);
// for (var i = 0; i < nodes.length; i++) {
// var r = nodes[i].record;
// if (r.recordSate == 'new' || r.recordState == "insert") {
// r.recordState = "discard";
// } else {
// r.recordState = "delete";
// }
// }
// grid.treegrid("remove", record._uuid);
// }
// }
// break;
// }
// case "rdatagrid":
// if (record) {
// var grid = $(element);
// var opts = grid.rdatagrid("options");
// var selectedIndex = opts.selectedIndex;
// grid.rdatagrid('deleteRow', selectedIndex);
// opts.selectedIndex--;
// }
// break;
// }
// }
//
// function _notifyDatasetRefreshRecord(element, dataset, record, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "datagrid": {
// if (record) {
// _window.refreshDataGridRow(element, dataset, record, reserved);
// }
// break;
// }
// case "rdatagrid": {
// if (record) {
// _window.refreshReadonlyTableRow(element, dataset, record, reserved);
// }
// break;
// }
// case "datalabel":
// ;
// refreshDatalabelValue(element, dataset, record);
// break;
// case "editor":
// ;
// case "dropDownSelect":
// ;
// case "dockeditor": {
// refreshInputValue(element, dataset, record);
// element.isUserInput = false;
// break;
// }
// }
// }
//
// function _notifyDatasetRefresh(element, dataset, record, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "datagrid":
// case "rdatagrid":
// refreshElementValue(element);
// break;
// case "datalabel":
// ;
// if (dataset.loadCompleted) {
// refreshDatalabelValue(element, dataset, record);
// }
// break;
// case "editor":
// case "dropDownSelect":
// dataset.modified = false;
// if (dataset.loadCompleted) {
// refreshInputValue(element, dataset, record);
// }
// break;
// }
// _notifyDatasetStateChanged(element, dataset, record, reserved);
//
// }
//
// function _notifyFieldDataChanged(element, dataset, record, field, reserved) {
// var _window = element.window;
// switch (element.getAttribute("extra")) {
// case "datagrid": {
// _window.refreshDataGridCellValue(element, dataset, field, record);
// break;
// }
// case "rdatagrid":
// refreshReadonlyTableCellValue(element, dataset, field, record);
// break;
// case "editor": {
// if (compareText(element.getAttribute("dataField"), field.name)) {
// refreshInputValue(element, dataset, record);
// }
// break;
// }
// case "datalabel": {
// if (compareText(element.getAttribute("dataField"), field.name)) {
// refreshDatalabelValue(element, dataset, record);
// }
// break;
// }
// case "dropDownSelect": {
// if (compareText(element.getAttribute("dataField"), field.name)) {
// record.dropdownKey = true;
// refreshInputValue(element, dataset, record);
// } else if (compareText(element.getAttribute("dataField") + 'Name',
// field.name)) {
// record.dropdownKey = false;
// refreshInputValue(element, dataset, record);
// }
// break;
// }
// }
//
// // if (_window.isFileIncluded("editor")) _window.sizeDockEditor();
// }
//
// function _notifyFieldStateChanged(element, dataset, record, field, reserved)
// {
// // editor,dropDownSelect
// var dataField = element.getAttribute("dataField");
// if (dataField == field.fieldName
// && element.getAttribute("extra") != 'fieldlabel') {
// if (reserved.state == "readonly") {
// element.setReadOnly(!isFieldEditable(dataset, field));
// } else if (reserved.state == "required") {
// $(element).prevAll("span.requiredlabel").html(
// reserved.value ? "*" : "&nbsp;");
// // UI
// _editor_setRequired(element, reserved.value);
// }
// } else if (element.getAttribute("extra") == 'rdatagrid') {
// if (reserved.state == "readonly") {
// if (field.name == 'select') {
// $.data(element, 'rdatagrid').dc.view
// .find(
// '.datagrid-header-check :checkbox,.datagrid-cell-check :checkbox')
// .each(function() {
// this.disabled = !isFieldEditable(dataset, field);
// });
// }
// }
//
// } else if (element.getAttribute("extra") == 'datagrid') {
// if (reserved.state == "readonly") {
// if (field.name == 'select') {
// $.data(element, 'datagrid').dc.view1
// .find(
// '.datagrid-header-check :checkbox,.datagrid-cell-check :checkbox')
// .each(function() {
// this.disabled = !isFieldEditable(dataset, field);
// });
// }
// } else if (reserved.state == "required") {
// // UI
// var col = null;
// var editedind = null;
// var ed = null;
// if ($(element).attr("treeGrid") == "false") {
// col = $(element).datagrid("getColumnOption", field.fieldName);
// editedind = $(element).datagrid("options").editIndex;
// ed = $(element).datagrid('getEditor', {
// index : editedind,
// field : field.fieldName
// });
// if (ed)
// _editor_setRequired(ed.target);
// } else {
// col = $(element).treegrid("getColumnOption", field.fieldName);
// editedind = $(element).treegrid("options").editId;
// ed = $(element).treegrid('getEditor', {
// id : editedind,
// field : field.fieldName
// });
// if (ed)
// _editor_setRequired(ed.target);
// }
// if (col) {
// col.required = reserved.value;
// if (col.editor && col.editor.options) {
// col.editor.options.required = reserved.value;
// }
// }
//
// }
// }
// }
//
// /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*/
function _resetRecordState(record) {
	record.saveOldValue();
	if (record.recordState == "delete") {
		record.recordState = "discard";
	} else if (record.recordState != "discard") {
		record.recordState = "none";
	}
}

function _resetDatasetsState(submitManager) {
	for (var i = 0; i < submitManager.datasets.length; i++) {
		var dataset = submitManager.datasets[i];
		dataset.disableControls();
	}
	try {
		for (var i = 0; i < submitManager.datasets.length; i++) {
			var dataset = submitManager.datasets[i];
			var record = dataset.firstUnit;
			while (record) {
				if (record.recordState != "none" && record.recordState != "discard") {
					var fieldCount = dataset.fields.fieldCount;
					for (var j = 0; j < fieldCount; j++) {
						var field = record.fields[j];
						if (field.dataType == "lob") {
							record.setValue(j, "");
						}
						if (_oidmap && record.recordState == "insert") {
							if (field.autoGenId) {
								var value = _oidmap[record.getString(j)];
								if (getValidStr(value) != "") {
									dataset.setRecord(record);
									dataset.setValue(j, value);
								}
								dataset.updateRecord();
							}
						}
					}
				}
				_resetRecordState(record);
				record = record.nextUnit;
			}
		}
	} finally {
		for (var i = 0; i < submitManager.datasets.length; i++) {
			var dataset = submitManager.datasets[i];
			dataset.enableControls();
		}
	}
}

// --------------------------------------------------

// yjw
//function setFieldDropDown(id, targetDataset) {
//	var _id_ds = getDatasetByID(targetDataset);
//	var _field = _dataset_getField(_id_ds.fields, id);
//	_field.dropdown = id + "_DropDown";
//}

//function initDropDownValues() {
//	var datasetAry = new Array();
//	datasetAry = getDatasets();
//	for (var k = 0; k < datasetAry.length; k++) {
//		var dataset = datasetAry[k];
//		if (!dataset.loadCompleted && dataset.type && dataset.type == "result" && dataset.id != "_tmp_dataset_date") {
//			for (var i = 0; i < dataset.fields.length; i++) {
//				if (dataset.fields[i].tag == "selectCQ") {
//					var fieldId = dataset.fields[i].fieldName;
//					var fieldName = fieldId + "Name";
//					var fieldIdMap = "";
//					var fieldNameMap = dataset.fields[i].viewField;// from
//					// getter
//					var _dropdown_id = dataset.fields[i].dropDown;
//					var _dropdown_dataset_id = dataset.fields[i].dropDownDataset;
//					var _dropdown_dataset = getDatasetByID(_dropdown_dataset_id);
//					if (_dropdown_dataset) {
//						var _dropdown = getDropDownByID(_dropdown_id);
//						if (_dropdown != "" && _dropdown != null && _dropdown.fieldMap != "") {
//							var fieldMapStr = _dropdown.fieldMap.split(";");
//							for (var j = 0; j < fieldMapStr.length; j++) {
//								var fieldMapStr2 = fieldMapStr[j].split("=");
//								if (fieldMapStr2[0] == dataset.fields[i].fieldName) {
//									fieldIdMap = fieldMapStr2[1];
//									if (fieldNameMap == "") {
//										fieldNameMap = fieldIdMap + "Name"; // from
//										// dataDic
//									}
//									break;
//								}
//							}
//							var record = dataset.firstUnit;
//							while (record) {
//								var fieldValue = record_getString_2(record, fieldId);
//								var _record = _dropdown_dataset.firstUnit;
//								while (_record) {
//									var fieldMapValue = record_getString_2(_record, fieldIdMap);
//									if (fieldMapValue == fieldValue) {
//										var fieldNameMapValue = record_getString_2(_record, fieldNameMap);
//										_record_setValue_2(record, fieldName, fieldNameMapValue);
//										break;
//									}
//									_record = _record.nextUnit;
//								}
//								record = record.nextUnit;
//							}
//						}
//					}
//				}
//			}
//		}
//		/* shen_antonio 20080129 . */
//		// select
//		else if (!dataset.loadCompleted && dataset.type && dataset.type == "dropdown") {
//			if (dataset.init && dataset.init == "true") {
//				// if(dataset.require!="true")dataset.insertRecord("begin");
//			}
//		}
//		/* . */
//
//	}
//}

//function getFieldSelectNameValue(record, field) {
//	var fieldName = field.fieldName;
//	var fieldNameMapValue = "";
//	if (fieldName.length > 4) {
//		var fieldNameMapValue = "";
//		var dataset = record.dataset;
//		var length = fieldName.length;
//		var fieldId = fieldName.substring(0, (length - 4));
//		var fieldIdMap = "";
//		var fieldNameMap = field.viewField;// from getter
//		var _dropdown_id = field.dropDown;
//		var _dropdown_dataset_id = field.dropDownDataset;
//		var _dropdown_dataset = getDatasetByID(_dropdown_dataset_id);
//		var _dropdown = getDropDownByID(_dropdown_id);
//		if (_dropdown_dataset) {
//			if (_dropdown.fieldMap != "") {
//				var fieldMapStr = _dropdown.fieldMap.split(";");
//				for (var j = 0; j < fieldMapStr.length; j++) {
//					var fieldMapStr2 = fieldMapStr[j].split("=");
//					if (fieldMapStr2[0] == fieldId) {
//						fieldIdMap = fieldMapStr2[1];
//						if (fieldNameMap == "") {
//							fieldNameMap = fieldIdMap + "Name"; // from dataDic
//						}
//						break;
//					}
//				}
//				if (record) {
//					var fieldValue = record_getString_2(record, fieldId);
//					if (fieldValue == "") {
//						return "";
//					}
//					var _record = _dropdown_dataset.firstUnit;
//					while (_record) {
//						var fieldMapValue = record_getString_2(_record, fieldIdMap);
//						if (fieldMapValue == fieldValue) {
//							fieldNameMapValue = record_getString_2(_record, fieldNameMap);
//							index = dataset.fields["_index_" + fieldId];
//							record[index] = fieldValue;
//							break;
//						}
//						_record = _record.nextUnit;
//					}
//					// if no match ,return code
//					if (fieldNameMapValue == "") {
//						fieldNameMapValue = fieldValue;
//						index = dataset.fields["_index_" + fieldId];
//						record[index] = fieldValue;
//					}
//				}
//			}
//		}
//	} else {
//	}
//	// field.tag="";
//	return fieldNameMapValue;
//}
//
///* added by wangpeng 20091207 radio begin */
//function getFieldRadioNameValue(record, field) {
//	var fieldName = field.fieldName;
//	var fieldNameMapValue = "";
//	if (fieldName.length > 4) {
//		var dataset = record.dataset;
//		var length = fieldName.length;
//		var valueField = fieldName.substring(0, (length - 4));
//		var radio = RadioRender.getRadio(field.radio);
//		if (radio) {
//			fieldNameMapValue = radio.getRadioNameValue(record.getValue(valueField));
//		}
//	}
//
//	return fieldNameMapValue;
//}
///* added by wangpeng 20091207 radio end */

function dataset_getRealRecordCounts() {
	var count = 0;
	var dataset = this;
	if (dataset.length == 0) {
		return count;
	} else {
		var record = dataset.firstUnit;
		while (record) {
			if (record.recordState != "discard") {
				count++;
			}
			record = record.nextUnit;
		}
	}
	return count;
}

// add by zzg
function dataset_toJson(opts) {
	var sumfieldstr = null;
	var dataset = this;
	// 
	if (dataset.masterDataset) {
		dataset.pageSize = dataset.length;
		dataset.pageCount = 1;
	}
	var json = {};
	json.pageIndex = dataset.pageIndex;
	json.pageCount = dataset.pageCount;
	var totalCount = dataset.totalCount || 0;
	var _record = dataset.getFirstRecord();
	var start = (dataset.pageIndex - 1) * dataset.pageSize;
	var end = start + dataset.pageSize;// Math.min(dataset.length,
	// dataset.pageIndex*dataset.pageSize);
	var n = -1;
	var rows = [];
	while (_record) {
		var result = fireUserEvent(dataset.id + "_onFilterRecord", [ dataset, _record ]);
		if (result) {
			_record = _record.getNextRecord();
			continue;
		}
		n++;
		// if (n < start) {
		// _record = _record.getNextRecord();
		// continue;
		// } else {
		// // dataset.record = _record;
		// }
		// if (n >= end) {
		// // break;
		// }
		var row = _record.$data;
		row.__id = _record.id;
		rows[rows.length] = row;
		_record = _record.getNextRecord();
	}
	return {
		Rows : rows,
		Total : totalCount
	};
}
/* shen_antonio . */
function initDefaultDataset(dataset) {
	if (dataset.getRealRecordCounts() == 0) {
		dataset.insertRecord("begin");
	}
	var fieldCount = dataset.fields.fieldCount;
	for (var i = 0; i < fieldCount; i++) {
		var field = dataset.getField(i);
		if (field.defaultValue && field.defaultValue != "" && dataset.getString(i) == "") {
			dataset.setValue(i, field.defaultValue);
		}
	}
}
/* . */

function copyDateSetParameter(datasetOrig, datasetNew) {
	datasetNew.parameters = $.extend(datasetNew.parameters, datasetOrig.parameters);
}

function copyDataset(disDatasetID, origDatasetID) {
	var _disDataset = createDataset(disDatasetID, "", "");
	_disDataset.flushData = dataset_flushData_new;
	var _origDataset = getDatasetByID(origDatasetID);
	_disDataset.fields = _origDataset.fields;
	var arr = disDatasetID.split("_");
	arr.pop();
	var newcqid =arr.join("_");
	_disDataset.parameters = $.extend({}, _origDataset.parameters);
	$.extend(_disDataset.parameters, {dsName: newcqid});
	_disDataset.readOnly = true;
	_disDataset.cqId = newcqid;//_origDataset.cqId;
	_disDataset.dspath = _origDataset.dspath;
	_disDataset.pageSize = _origDataset.pageSize;
	_disDataset.databusId = _origDataset.databusId;
	_disDataset.pageIndex = 1;
	_disDataset.pageCount = 1;
	_disDataset.masterDataset = _origDataset.masterDataset;
	_disDataset.references = _origDataset.references;
	_disDataset.submitData = _origDataset.submitData;
	_disDataset.autoLoadPage = _origDataset.autoLoadPage;
	_disDataset.autoLoadDetail = true;
	_disDataset.downloadUrl = getDecodeStr("~2fextraservice~2fdownloaddata");
	_disDataset.insertOnEmpty = _origDataset.insertOnEmpty;
	_disDataset.tag = "";
	_disDataset.type = _origDataset.type;
	_disDataset.sessionKey = "dd";
	_disDataset.init = _origDataset.init;
	_disDataset.pKey = _origDataset.pKey;
	initDataset(_disDataset);
	return _disDataset;
}

function _initField(dataset, fId, fType, props) {
	field = dataset.addField(fId, fType);
	field.nullable = true;
	field.updatable = true;
	field.visible = true;
	$.extend(field, props);

//	field.label = fDesc;
//	field.size = fSize;
//	field.scale = scale;
//	field.readOnly = readonly;
//	field.required = required === "true" || required === true;
//	field.defaultValue = getDecodeStr("");
//	field.valueProtected = false;
//	field.autoGenId = false;
//	field.tableName = "";
//	field.fieldName = fId;
//	field.editorType = "";
//	field.multiple = isTrue(multiple);
//	field.editType = editType;
//	field.prefix = prefix;
//	field.editable = isTrue(editable);
//	field.url = url;
//	field.fieldMap = fieldmap;
//	field.searchField = searchfield;
//	// eval("if(typeof("+mask+")!='undefined' && \""+mask+"\"!=\"null\"){
//	// eval(\"mask = "+mask+"\"); field.mask=\"/\" + mask + \"/\";};");
//	field.mask = mask;
//	// errAlert(constRulesMiss.replace("%s1",fId).replace("%s2",mask));
//	field.maskErrorMessage = maskErrMsg;
//	field.toolTip = toolTip;
//	field.lobDownloadURL = getDecodeStr("");
//	field.lobPopupURL = getDecodeStr("");
//	field.radio = radio;
//	field.RadioDataset = RadioDataset;
//	field.tag = tag;
//	field.viewField = viewField;
//	field.dropDown = dropDown;
//	field.dropDownDataset = dropDownDataset;
//	if(currencyAlign){
//		field.align = currencyAlign;
//	}
//	field.width = fWidth;//列表下拉框列宽度
//	field.minsize = minsize;

}

function _initDataset(dataset, cqId, pageSize, databusId, masterDataset, references, submitData, insertOnEmpty, sessionKey, paramString, init, type, pKey, expmaxrcd) {
	var _t = dataset;
	_t.expmaxrcd = expmaxrcd || 100;// add 在线导出允许的最大记录数
	_t.flushData = dataset_flushData_new;
	_t.readOnly = false;
	_t.cqId = cqId;
	_t.pageSize = pageSize;
	_t.databusId = databusId;
	_t.pageIndex = 1;
	_t.pageCount = 1;
	_t.masterDataset = masterDataset;
	_t.references = references;
	_t.submitData = submitData;
	_t.autoLoadPage = false;
	_t.autoLoadDetail = true;
	_t.downloadUrl = getDecodeStr("~2fextraservice~2fdownloaddata");
	_t.insertOnEmpty = insertOnEmpty;
	_t.tag = "";
	_t.type = type;
	_t.pKey = pKey;
	_t.sessionKey = sessionKey;
	converStr2DataSetParameter(paramString, _t);
	_t.setParameter("dsName", cqId, "string");
	_t.setParameter("nextPage", _t.pageIndex);
	_t.setParameter("everyPage", _t.pageSize);
	_t.setParameter("_session_key", _t.sessionKey);
	_t.setParameter("databusId", _t.databusId);
	_t.init = init;
	_t.initDocumentFlag = false;
}

function dataset_flushData_new(pageIndex, callback) {
	try {
		var dataset = this;
		var eventName = getElementEventName(dataset, "flushDataPre");
		if (isUserEventDefined(eventName)) {
			var event_result = fireUserEvent(getElementEventName(dataset, "flushDataPre"), [ dataset ]);
			if (typeof (result) == "boolean" && !result) {
				return;
			}
		}
		_dataset_flushData_new(dataset, pageIndex, callback);
		fireUserEvent(getElementEventName(dataset, "flushDataComplete"), [ dataset ]);
		// fireUserEvent(getElementEventName(dataset,
		// "flushDataPost"),[dataset]);
	} catch (e) {
		processException(e);
	}
}

function _dataset_flushData_new(dataset, pageIndex, callback) {
	pageIndex = pageIndex || 1;
	dataset.disableControls();
	// useLoadingImage(_theme_root + "/loading.gif");
	var loadDetail = false;
	try {
		
		if (dataset.sessionKey) {
			var _url = _application_root + dataset.downloadUrl;
			var pageSize = dataset.pageSize;
			var _paramMap = {};
			_paramMap = converDateSetParameter2Map(dataset, _paramMap);
			_paramMap.dsPath = dataset.dspath;
			_paramMap["nextPage"] = pageIndex;
			_paramMap["everyPage"] = pageSize;
			_paramMap["_session_key"] = dataset.sessionKey;
			_paramMap['cacheCount'] = dataset.flushed ? dataset.cacheCount != '' && dataset.cacheCount != undefined : dataset.cacheCount == 'ALLWAYS';
			dwr.engine.setAsync(true);
			var dstype = dataset.type;
			// var result = new Object();
			CommonQueryResultProcess.processAsyncBean(_paramMap, function(resultBean) {
				dataset.clearData(true);
				if (resultBean.resCd != '000000') {
					dataset.pageIndex = 1;
					dataset.pageCount = 0;
					var err = new Error(resultBean.resMsg);
					err.name = resultBean.resCd;
					throw err;
				} else if (resultBean.resultMap) {

					for ( var dsName in resultBean.resultMap) {
						var ds = getDatasetByID(dsName + (dstype == "dropdown" ? "_DropDownDataset" : "_dataset"));
						var result = resultBean.resultMap[dsName];

						ds.resCd = result.resCd;
						if (result.recordString) {
							appendFromDataString(ds, result.fieldString, result.recordString, true);
						}
						ds.pageIndex = pageIndex;
						ds.pageCount = result.pageCount;
						ds.totalCount = result.totalCount;
						converStr2DataSetParameter(result.parameterString, ds);
						var record = ds.firstUnit;
						var i = 0;
						while (record) {
							i++;
							initRecord(record, ds);
							if (i / pageSize < 1)
								record.pageIndex = 1;
							else {
								record.pageIndex = calcPageCount(i, pageSize);
							}
							record = record.nextUnit;
						}
						if (result.pageCount == 1) {
							ds.pageCount = calcPageCount(i, pageSize);
						}

						ds.enableControls();
						if (ds.type != "ref") {
							fireUserEvent(getElementEventName(ds, "flushDataPost"), [ ds ]);
						}
						ds.flushed = true;

					}
					if (_lastDataSetID == dataset.id) {
						_lastDataSetID = null;
						// initDropDownValues();
						resetDataSetRecordState();
						if (dataset.pagelet) {
							dataset.pagelet = false;
							eval("fireUserEvent(\"" + dataset.pageid + "_initCallGetter_post\");");
						} else {
							fireUserEvent("initCallGetter_post");
						}
					}
					callback && callback();
				}
			});
			delete _paramMap;
			dwr.engine.setAsync(true);
		}
	} catch (e) {
		processException(e);
	} finally {
	}
}

// changet dataset to list , the dataset's record is translated to map ,and will
// fill the map to the list ,submit the list
function translateDataset2List(datasetId) {
	var dataset = getDatasetByID(datasetId);
	var list = new Array();
	var record = dataset.firstUnit;
	var i = 0;
	while (record) {
		if (record.recordState != "none" && record.recordState != "discard") {
			var map = translateRecord2Map(dataset, record);
			list[i] = map;
			i++;
		}
		_resetRecordState(record);
		record = record.nextUnit;
	}
	return list;
}

// youjinwu

// yjw modify
// record,dataset
function resetDataSetRecordState() {
	var datasetAry = getDatasets();
	for (var k = 0; k < datasetAry.length; k++) {
		var dataset = datasetAry[k];
		if (!dataset.loadCompleted) {
			dataset.loadCompleted = true;
			if (dataset.type && dataset.type == "result") {
				var record = dataset.firstUnit;
				while (record) {
					record.recordState = "none";
					record = record.nextUnit;
				}
			}
		}
	}
}

/*
 */
/* modify by shen_antonio 20080123 . */
var _lastDataSetID = null;
function initCallGetter() {
	var datasetAry = getDatasets();
	useLoadingImage(_theme_root + "/loading.gif");
	fireUserEvent("initCallGetter_pre");
	dwr.engine.beginBatch();
	dwr.engine.setOrdered(true);
	// /** init dropdown. */
	// for (var i = 0; i < datasetAry.length; i++) {
	// var dataset = datasetAry[i];
	// if (dataset.type == "dropdown") {
	// if (dataset.init == "true") {
	// // _lastDataSetID = dataset.id;
	// // dataset.flushData(1);
	// } else {
	// // dataset.refreshControls();
	// }
	// } else {
	// }
	// }
	_lastDataSetID = null;
	for (var i = 0; i < datasetAry.length; i++) {
		var dataset = datasetAry[i];
		if (dataset.type == "result") {
			if (dataset.init == "true") {
				_lastDataSetID = dataset.id;
			}
		}
	}

	/** init result. */
	for (var i = 0; i < datasetAry.length; i++) {
		var dataset = datasetAry[i];
		fireUserEvent(getElementEventName(dataset, "requestInit"), []);
		if (dataset.type == "result") {
			if (dataset.init == "true") {
				dataset.flushData(1);
			}
		} else {
			// interface datasetdataset
			if (dataset.type && dataset.type == "interface") {
				// if(typeof(dataset.init) == "undefined"){
				dataset.refreshControls();
				// }
			}
		}
	}
	// initfalse
	if (!_lastDataSetID) {
		// initDropDownValues();
		resetDataSetRecordState();
		fireUserEvent("initCallGetter_post");
	}

	dwr.engine.setOrdered(false);
	dwr.engine.endBatch();
	// dwr.engine.setAsync(true);
	// fireUserEvent("initCallGetter_post",0);
}

function initLetCallGetter(id) {
	var datasetAry = getDatasets();
	useLoadingImage(_theme_root + "/loading.gif");
	fireUserEvent(id + "_initCallGetter_pre");
	// dwr.engine.setAsync(false);
	dwr.engine.beginBatch();
	dwr.engine.setOrdered(true);
	/** init dropdown. */
	for (var i = 0; i < datasetAry.length; i++) {
		var dataset = datasetAry[i];
		if (dataset.type && dataset.type == "dropdown") {
			if (!dataset.loadCompleted && dataset.init && dataset.init == "true") {
				// dataset.flushData(1);
				/*
				 * shen_antonio if(dataset.require!="true"){
				 * dataset.insertRecord("begin"); }
				 */
			} else {
				// dataset.refreshControls();
			}
		} else {
		}
	}

	_lastDataSetID = null;
	for (var i = 0; i < datasetAry.length; i++) {
		var dataset = datasetAry[i];
		if (dataset.type && dataset.type == "result") {
			if (!dataset.loadCompleted && dataset.init && dataset.init == "true") {
				_lastDataSetID = dataset.id;
			}
		} else {
			// interface datasetdataset
			if (!dataset.loadCompleted && dataset.type && dataset.type == "interface") {
				dataset.refreshControls();
			}
		}
	}

	/** init result. */
	for (var i = 0; i < datasetAry.length; i++) {
		var dataset = datasetAry[i];
		fireUserEvent(getElementEventName(dataset, "requestInit"), []);
		if (dataset.type && dataset.type == "result") {
			if (!dataset.loadCompleted && dataset.init && dataset.init == "true") {
				dataset.pagelet = true;
				dataset.pageid = id;
				dataset.flushData(1);
			}
		} else {
		}
	}
	// 
	if (!_lastDataSetID) {
		initDropDownValues();
		resetDataSetRecordState();
		eval("fireUserEvent(\"" + id + "_initCallGetter_post\");");
	}
	dwr.engine.setOrdered(false);
	dwr.engine.endBatch();
	// dwr.engine.setAsync(true);
	// fireUserEvent("initCallGetter_post",0);
}



function _resetRecordState2(button) {
	try {
		if (button.submitDataset) {
			datasetStr = button.submitDataset.split(";");
			for (var j = 0; j < datasetStr.length; j++) {
				var _dataSet = getDatasetByID(datasetStr[j]);
				_dataSet.type == "result";
				datasetAry[datasetAry.length] = _dataSet;
			}
		} else {
			datasetAry = getDatasets();
		}
		for (var k = 0; k < datasetAry.length; k++) {
			var dataset = datasetAry[k];
			if (dataset.type && dataset.type == "result") {
				try {
					var record = dataset.firstUnit;
					while (record) {
						_resetRecordState(record);
						record = record.nextUnit;
					}
				} catch (e) {
				} finally {
					dataset_setState(dataset, "none");
					dataset.modified = false;

				}
			}
		}
	} catch (e) {
	}
}
function converDateSetParameter2Map(dataset, param) {
	var parameters = $.extend(param, {
		_dataset_id : dataset.id
	}, dataset.parameters);
	return parameters;
}
function converDateSetParameter2Map_2(dataset, param) {
	// var pId, pVal;
	// var paramMap2 = new Object();
	// for (var i = 0; i < dataset.parameters.length; i++) {
	// pId = dataset.parameters[i].name;
	// pVal = dataset.parameters[i].value;
	// if (pVal != null) {
	// paramMap2[pId] = pVal;
	// }
	// }
	// paramMap2["_dataset_id"] = dataset.id;
	// return paramMap2;

	return converDateSetParameter2Map(dataset, param);

}

// 
function refreshProgress() {
	dwr.engine.setPreHook(function() {
	});
	dwr.engine.setPostHook(function() {
	});
	UploadMonitor.getUploadInfo(updateProgress);
}

function updateProgress(uploadInfo) {
	if (uploadInfo.inProgress) {
		fireUserEvent("uploadFileProgress", 0);
		var fileIndex = uploadInfo.fileIndex;
		var progressPercent = Math.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);
		document.getElementById('progressBarText').innerHTML = 'upload in progress: ' + progressPercent + '%';
		document.getElementById('progressBarBoxContent').style.width = parseInt(progressPercent * 3.5) + 'px';
		window.setTimeout('refreshProgress()', 1000);
	} else {
		fireUserEvent("uploadFileComplete", 0);
	}

	return true;
}

function startProgress() {
	document.getElementById('progressBar').style.display = 'block';
	document.getElementById('progressBarText').innerHTML = 'upload in progress: 0%';
	window.setTimeout("refreshProgress()", 1500);
	return true;
}

/** . */
function loanPageLet(url, paramMap, elementId) {
	funPreHook(_theme_root + "/loading.gif");
	var v_div1 = document.getElementById(elementId);
	uninitLet(v_div1);
	v_div1.innerHTML = "";
	createXMLHttpRequest();
	var queryString = createQueryString();
	var url = _application_root + url + "?_mpf_=false" + queryString;
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.send(queryString);
	function handleStateChange() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				parseResults();
			}
		}
	}
	function parseResults() {
		var v_div1 = document.getElementById(elementId);
		v_div1.innerHTML = xmlHttp.responseText;
		$.parser.parse($(v_div1));
		eval("_user_events_" + elementId + " = _user_events.me();");
		runScript(v_div1);
		initDocumentLet(v_div1);
		funPostHook();
	}
	function runScript(parent) {
		var v_div1 = parent;
		var arg = v_div1.getElementsByTagName("SCRIPT");
		for (var j = 0; j < arg.length; j++) {
			CQSPACE.Eval(arg[j].innerHTML);
		}
	}
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}
	function createQueryString() {
		var pId, pVal;
		var queryString = "";
		var keyArg = paramMap.keys();

		for (var i = 0; i < keyArg.length; i++) {
			pId = keyArg[i];
			pVal = paramMap.get(pId);
			queryString += "&" + pId + "=" + pVal;
		}
		return queryString;
	}
}
/*  */
function unloadPageLet(elementId) {
	funPreHook(_theme_root + "/loading.gif");
	var v_div1 = document.getElementById(elementId);
	uninitLet(v_div1);
	v_div1.innerHTML = "";
	funPostHook();
}

var CQSPACE = {}; //
CQSPACE.Eval = function(code) {
	// Modified by Weijing scripteval BMS-2138
	if (null == code || "" == code) {
		return;
	}
	// End
	if (!!(window.attachEvent && !window.opera)) {
		// ie
		execScript(code);
	} else {
		// not ie
		window.eval(code);
	}
};

/* 2 */
function loadPageWindows(id, title, url, paramMap, zone) {
	if (!$('#' + id)[0]) {
		$(document.body).append("<div id='" + id + "' title='" + title + "'><div id='" + id + "_" + zone + "'/></div>");
	}
	$('<input type="text" style="width:0;height:0;" name="_alertFocusCtr"/>').appendTo(document.body).focus().remove();
	var _win = $('#' + id).window({
		id : id,
		width : 600,
		minimizable : false,
		maximizable : false,
		height : 400,
		modal : true,
		onBeforeClose : function() {
			var panel = $.data(this, 'panel').panel;
			var p = panel.parent();
			p.removeClass("panel-noscroll");
			if (p[0].tagName == "BODY") {
				$("html").removeClass("panel-fit");
			}

			var eventName = id + "_onCloseCheck";
			if (isUserEventDefined(eventName)) {
				var event_result = fireUserEvent(eventName, [ $('#' + id)[0] ]);
				if (typeof (event_result) == "boolean") {
					if (event_result != true) {
						return false;
					}
				}
			}
			return true;
		},
		onClose : function() {
			if ($("#" + id + "_" + zone)[0]) {
				eval("_user_events = _user_events_" + id + "_" + zone + "||_user_events;");
				uninitLet($("#" + id + "_" + zone)[0]);
				$("#" + id + "_" + zone)[0].innerHTML = "";
			}
			$("div.validatebox-tip").each(function(i) {
				$(this).hide();
			});
		}
	});
	// if($.mise6) {
	_win.addClass('ie6scrollfixed');
	// }
	_win.window('maximize');
	loanPageLet(url, paramMap, id + "_" + zone);
	win.target = _win;
}
// /* */
// function unloadPageWindows(id){
// win = dhxWins.window(id);
// win.autoClosed = true;
// win.close();
// }
/* 2 */
function unloadPageWindows(id) {
	$('#' + id).window('close');
}

/*  */

/* print button function */
function _printButton_onclick() {
	showLoadingImage(_theme_root + "/loading.gif", true);
	var button = event.srcElement;
	var fieldStr = button.fieldStr;
	var dataset = button.componentDataset;
	var headTitle = button.headTitle;
	var newDatasetId = "_" + dataset + "_print";
	_dataset_print = copyDataset("_" + dataset + "_print", dataset);
	_dataset_print.pageIndex = 1;
	_dataset_print.pageSize = 10000;
	_dataset_print.setReadOnly(true);
	_dataset_print.flushData(1);
	printDatasetASExcel(_dataset_print, fieldStr, headTitle);
	_dataset_print.clearData();
	delete _dataset_print;
	showLoadingImage(_theme_root + "/loading.gif", false);
}

/*  */
function checkDelRecordSubmitNeeded(button) {
	var btnDataset = getDatasetByID(button.componentDataset);
	// 
	if (btnDataset.record.recordState == "new" || btnDataset.record.recordState == "insert") {
		return false;
	}
	return true;
}
/*  */
function checkBeforeDelRecordSubmit(button) {
	var btnDataset = getDatasetByID(button.componentDataset);
	var updateSize = isNaN(button.submitUpdateTotalListSize) ? 0 : button.submitUpdateTotalListSize;
	var deleteSize = isNaN(button.deleteSize) ? 0 : button.deleteSize;
	// 
	if (updateSize == 1 && (btnDataset.record.recordState == "modify" || btnDataset.record.recordState == "new" || btnDataset.record.recordState == "insert")) {
		return true;
	} else if (updateSize != 0 || deleteSize != 0) {
		return confirm(UIKit.i18n.constDatasetConfirmSubmitModifiedRecordsSameTime);
	}
	return true;
}

/* report-url */
function _printapplet_clearUrlList() {
	var _printApplet = document.getElementById('PrintApplet');
	_printApplet.clearUrlList();
}

/** url */
function _printapplet_addReportUrl(reportId, viewUrl, printUrl) {
	var _printApplet = document.getElementById('PrintApplet');
	var _printPath = _application_root + "/PrintServlet?";
	if (printUrl == null) {
		_printApplet.addReportUrl(_printPath + "report_id=" + reportId + "&" + viewUrl);
	} else {
		_printApplet.addReportUrl(_printPath + "report_id=" + reportId + "&" + viewUrl, _printPath + "report_id=" + reportId + "&" + printUrl);
	}
}

/**  */
function _printapplet_showViewForm() {
	var _printApplet = document.getElementById('PrintApplet');
	_printApplet.showViewForm();
}

function fieldChangeToLabel(id) {
	var scope = id ? $("#" + id) : $("body");
	$("span.combo", scope).each(function() {
		$(this).after($("<label/>").html($(this).find("input[type=text]:visible").val()));
		$(this).hide();
	});
	$(":checkbox,:radio", scope).each(function() {
		$(this).attr("disabled", "disabled");
	});
	$("input:visible:not(:checkbox,:radio)", scope).each(function() {
		$(this).after($("<label/>").html($(this).val()));
		$(this).hide();
	});
	$("textarea:visible", scope).each(function() {
		$(this).after($("<label/>").html($(this).val()));
		$(this).hide();
	});
}

function _changeMasterRecordState(dataset) {
}
function bindDataset(element) {
	var dataset = getElementDataset(element);
	if (dataset) {
		// var editors = dataset.editors || (dataset.editors = {});
		// dataset.bindEditor(jq[0]);
		var array = dataset.editors;
		if (!array) {
			array = new pArray();
			dataset.editors = array;
		}
		pArray_ex_insert(array, element);
		return dataset;
	}
}

function unbindDataset(element) {
	var dataset = getElementDataset(element);
	if (dataset) {
		var pArray = dataset.editors;
		if (pArray) {
			var unit = pArray.firstUnit;
			while (unit && unit.data) {
				if (unit.data == element) {
					pArray.deleteUnit(unit);
					break;
				}
				unit = unit.nextUnit;
			}
		}
	}
}

function bindDropdownDataset(element) {
	var datasetid = element.getAttribute("dropdowndatasetid");
	dataset = getDatasetByID(datasetid);
	if (dataset) {
		var array = dataset.editors;
		if (!array) {
			array = new pArray();
			dataset.editors = array;
		}
		pArray_ex_insert(array, element);
		return dataset;
	}
}
function bindDatasetEditor(dataset, element) {
	if (dataset) {
		var array = dataset.editors;
		if (!array) {
			array = new pArray();
			dataset.editors = array;
		}
		pArray_ex_insert(array, element);
		return dataset;
	}
}

function getElementDataset(element) {
	var dataset = element.getAttribute("datasetid");
	return getDatasetByID(dataset);
}

function getDatasetByID(ID) {
	return _array_dataset[ID];
}
function getElementField(element) {
	var dataset = getElementDataset(element);
	if (!dataset)
		return;
	return dataset.getField(element.getAttribute("datafield"));
}

function getElementEventName(element, eventName) {
	return element.id + "_" + eventName;
}
function isUserEventDefined(eventName) {
	return window[eventName] && typeof window[eventName] == 'function';
}
function fireUserEvent(eventName, args) {
	if (isUserEventDefined(eventName)) {
		return window[eventName].apply(this, args || []);
	}
}
//批量导出成功后处理
function BatchExportSuccess(data) {
	$.tip("添加批量导出任务：<a href='javascript:top.afterBatchExport()'>"+data.fileName+"</a>", 5000);
}
//批量导出失败后处理
function BatchExportFailed(code, message) {
	$.tip("导出失败", 5000);
}