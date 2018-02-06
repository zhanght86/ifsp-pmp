/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-3-8       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2010 Huateng Software, Inc. All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with Huateng.
 *
 * Warning:
 * =============================================================================
 *
 */
package com.ruimin.ifs.pmp.pubTool.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Title:JSON操作对象
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-8
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class JSONBean {

	private Map<String, Object> dataMap = null;

	private List<Object> dataList = null;

	private JSONObject object = null;

	private JSONArray array = null;

	public JSONBean() {
		dataMap = new LinkedHashMap<String, Object>();
		dataList = new LinkedList<Object>();
	}

	/**
	 * 添加JSON格式数据
	 * 
	 * @param id
	 * @param name
	 */
	public void addJSONElement(String id, Object name) {
		dataMap.put(id, name);
	}

	/**
	 * 添加JSON数组对象
	 * 
	 * @param obj
	 */
	public void addJSONArrayElement(Object obj) {
		dataList.add(obj);
	}

	/**
	 * 返回JSON格式对象
	 * 
	 * @return
	 */
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * 返回JSON格式数组
	 * 
	 * @return
	 */
	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	/**
	 * 添加子节点数组
	 * 
	 * @param id
	 * @param list
	 */
	public void addChild(String id, List<Object> list) {
		dataMap.put(id, list);
	}

	/**
	 * 将集合转换为JSON数组格式
	 * 
	 * @param list
	 * @return
	 */
	public static String genListToJSON(List<Object> list) {
		return JSONArray.fromObject(list).toString();
	}

	/**
	 * 将MAP转换为JSON数据
	 * 
	 * @param map
	 * @return
	 */
	public static String genMapToJSON(Map<String, Object> map) {
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 解析JSON数组数据
	 * 
	 * @param jsonData
	 * @return
	 */
	public JSONArray parseJSONArrayData(String jsonData) {
		array = JSONArray.fromObject(jsonData);
		return array;
	}

	/**
	 * 获得JSON数组数据中的子元素对象
	 * 
	 * @param jsonData
	 * @param index
	 * @return
	 */
	public JSONObject getJSONDataAt(int index) {
		object = (JSONObject) array.get(index);
		return object;
	}

	/**
	 * 获得JSON对象中的元素
	 * 
	 * @param key
	 * @return
	 */
	public Object getElementByKey(String key) {
		return object.get(key);
	}

	/**
	 * 获得JSON对象中的字符串元素
	 * 
	 * @param key
	 * @return
	 */
	public String getStringElementByKey(String key) {
		return object.getString(key);
	}

	/**
	 * 输出JOSN信息
	 */
	public String toString() {
		return genMapToJSON(this.getDataMap());
	}

	public JSONObject getObject() {
		return object;
	}

	public void setObject(JSONObject object) {
		this.object = object;
	}

	public JSONArray getArray() {
		return array;
	}

	public void setArray(JSONArray array) {
		this.array = array;
	}
}
