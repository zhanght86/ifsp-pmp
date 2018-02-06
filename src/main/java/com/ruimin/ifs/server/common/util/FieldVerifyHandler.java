/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: FieldVerifyHandler.java
 * Author:   GH
 * Date:     2015年9月10日 上午10:09:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.server.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.server.common.annotation.Empty.AllowEmpty;
import com.ruimin.ifs.server.common.annotation.Empty;
import com.ruimin.ifs.server.common.annotation.Stand;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public class FieldVerifyHandler {

	/** 是否校验 没注解的字段 不能为空 */
	private boolean flag;
	/** 非空字段集合 */
	private List<Field> notNullFieldList = new ArrayList<Field>();
	/** 待校验长度字段集合 */
	private Map<Field, Integer> lenFieldList = new HashMap<Field, Integer>();
	/** 待校验取值范围字段集合 */
	private Map<Field, String[]> scoreFieldList = new HashMap<Field, String[]>();
	/** 传入对象 */
	private Object bean;

	public FieldVerifyHandler(Object obj) throws IllegalArgumentException, IllegalAccessException {

		this.bean = obj;
		// 获取类注释
		if (!obj.getClass().isAnnotationPresent(VerifyInterface.class)) {
			throw new NullPointerException("未提供VerifyInterface注解.");
		}
		// try {
		analyzeFieldAnno(obj);
		// } catch (Exception e) {
		// throw e;
		// }
	}

	/**
	 * 分解字段Field注释
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private String analyzeFieldAnno(Object obj) throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {

			for (Field field : fields) {
				System.out.println("当前字段的: ===================" + field.getName());
				field.setAccessible(true);

				// 取得字段所有注释
				Annotation[] annos = field.getAnnotations();

				if (annos != null && annos.length > 0) {
					for (Annotation anno : annos) {
						if (anno instanceof Stand) {
							Stand stand = (Stand) anno;

							/*
							 * 是否为空
							 */
							if (AllowEmpty.NOTNULL.equals(stand.empty().toString())) { // 非空
								notNullFieldList.add(field);
							}
							/*
							 * 带校验值范围
							 */
							if (stand.scope() != null && ((String[]) stand.scope()).length > 0) {
								scoreFieldList.put(field, (String[]) stand.scope());
							} else {
								/*
								 * 校验长度
								 */
								if (stand.maxLen() > 0) {
									lenFieldList.put(field, stand.maxLen());
								}
							}

						}
					}

				}
			}
		}

		return null;

	}

	/**
	 * 基础校验
	 * 
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public FieldVerifyRs baseVerify() throws Exception {
		FieldVerifyRs rs = new FieldVerifyRs();
		Field field1 = verifyNotNull();
		if (field1 != null) {
			rs.setResult(false);
			rs.setType(1);
			rs.setField(field1);
			return rs;
		}
		Field field2 = verifyLen();
		if (field2 != null) {
			rs.setResult(false);
			rs.setType(2);
			rs.setField(field2);
			return rs;
		}
		Field field3 = verifyScore();
		if (field3 != null) {
			rs.setResult(false);
			rs.setType(3);
			rs.setField(field3);
			return rs;
		}

		rs.setResult(true);
		return rs;
	}

	/**
	 * 校验是否必输
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Field verifyNotNull() throws IllegalArgumentException, IllegalAccessException {

		if (notNullFieldList != null && notNullFieldList.size() > 0) {

			for (Field field : notNullFieldList) {
				Object fieldVaule = field.get(bean);

				if (fieldVaule == null) {
					return field;
				}
				if (fieldVaule instanceof String) { // 字符串
					if (((String) fieldVaule).length() <= 0) {
						return field;
					}
				} else if (fieldVaule instanceof Collection) { // Collection
					if (((Collection) fieldVaule).size() <= 0) {
						return field;
					}
				} else if (fieldVaule instanceof Map) { // MAP
					if (((Map) fieldVaule).size() <= 0) {
						return field;
					}
				} else if (fieldVaule instanceof String[]) { // String[]
					if (((String[]) fieldVaule).length <= 0) {
						return field;
					}
				}

			}

		}
		return null;
	}

	/**
	 * 校验长度
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Field verifyLen() throws IllegalArgumentException, IllegalAccessException {
		if (lenFieldList != null && lenFieldList.size() > 0) {
			Set<Field> set = lenFieldList.keySet();
			for (Field field : set) {
				Object fieldVaule = field.get(bean);
				int len = 0;
				if (fieldVaule instanceof String) { // STRING
					len = ((String) fieldVaule).getBytes().length;
				} else if (fieldVaule instanceof Collection) { // Collection
					len = ((Collection) fieldVaule).size();
				} else if (fieldVaule instanceof Map) { // MAP
					len = ((Map) fieldVaule).size();
				} else if (fieldVaule instanceof String[]) { // String[]
					len = ((String[]) fieldVaule).length;
				}
				System.out.println("当前字段:" + field.getName());
				System.out.println("当前长度: " + len);
				System.out.println("约定最大长度: " + lenFieldList.get(field));
				if (len > lenFieldList.get(field)) {
					return field;
				}
			}

		}
		return null;
	}

	/**
	 * 校验范围 (field 为字符串, 参数为 字符串)
	 * 
	 * @throws Exception
	 */
	private Field verifyScore() throws Exception {
		if (scoreFieldList != null && scoreFieldList.size() > 0) {
			Set<Field> set = scoreFieldList.keySet();
			for (Field field : set) {
				String fieldName = field.getName();
				Object fieldVaule = field.get(bean);

				if (!(fieldVaule instanceof String)) { // STRING
					throw new Exception("只有String类型的字段才提供scope属性,非法字段:" + fieldName);
				}
				if (!isContains(scoreFieldList.get(field), (String) fieldVaule)) {
					return field;
				}
			}

		}
		return null;
	}

	private boolean isContains(String[] arr, String comer) {
		if (arr == null || arr.length <= 0) {
			return false;
		}
		for (String str : arr) {
			if (str.equals(comer)) {
				return true;
			}
		}
		return false;
	}

}
