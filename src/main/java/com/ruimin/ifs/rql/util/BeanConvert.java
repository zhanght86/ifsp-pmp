package com.ruimin.ifs.rql.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruimin.ifs.rql.exception.RqlException;
import com.ruimin.ifs.rql.log.Log;
import com.ruimin.ifs.rql.log.Logs;
import com.ruimin.ifs.rql.ognl.OgnlOps;
import com.ruimin.ifs.rql.page.RqlPage;
import com.ruimin.ifs.rql.type.ParamTypeRegist;

/**
 * <PRE>
 * Filename:    BeanConvert.java  
 * Description: 对象实体转换工具类 
 * Copyright:   Copyright (c)2015
 * Company:     Shanghai Ruimin Software Systems Co., Ltd. 
 * </PRE>
 * @author      jonay  
 * @version     1.0
 * <PRE>
 * Create at:   2011-6-9 下午01:15:09  
 * Modification History:  
 * Date         Author      Version     Description
 * ------------------------------------------------------------------  
 * 2011-6-9     zhiguo.zhao 1.0         新建 
 * </PRE>   
 */ 
public class BeanConvert {

    /**
     * Log
     * @since 1.0
     */
    private static Log log = Logs.get();
	
	/**
	 * <P>将记录集转换成指定的类型
	 *
	 * @param resultSet 结果集
	 * @param clazz Class类
	 * @param onlyOne 是否只有一列
	 * @return 对象
	 * @since 1.0
	 */
	public static Object toBean(ResultSet resultSet, Class<?> clazz, boolean onlyOne) {
		try {
			ResultSetMetaData md = resultSet.getMetaData();   
		    int columnCount = md.getColumnCount();
		    if (resultSet.next()) {
		    	if (onlyOne && columnCount != 1) {
		    	    log.error("resultset can only has one record!");
	    			throw new RqlException("resultset can only has one record!");
	    		}
		    	if (ParamTypeRegist.isSimpleType(clazz.getName())) {
		    		try {
		    			return OgnlOps.convertValue(resultSet.getObject(1), clazz);
					} catch (Exception e) {
					    log.error(e);
						throw new RqlException(e);
					}
		    	} else {//有默认构造函数的实体类,可以被newInstance
		    		Object bean = clazz.newInstance();
			   		for (int i = 1; i <= columnCount; i++) {
						String colname = md.getColumnName(i).toLowerCase();
						String propname = col2pro(colname);
						Object value = resultSet.getObject(i);
						try {
							//Ognl.setValue(propname, bean, value);
            				ReflectUtil.setValue(bean, propname, value);
						} catch (Exception e) {
		                    log.warn(propname + " not exist in " + clazz);
						}
					}
					return bean;
		    	}
		    } else if (onlyOne) {
                log.error("no record exists!");
		    	throw new RqlException("no record exists!");
		    }
		    if (onlyOne && resultSet.next()) {
                log.error("one more record exist!");
		    	throw new RqlException("one more record exist!");
		    }
		    
		    return null;
		} catch (SQLException e) {
            log.error(e);
            throw new RqlException(e);
        } catch (InstantiationException e) {
            log.error(e);
            throw new RqlException(e);
        } catch (IllegalAccessException e) {
            log.error(e);
            throw new RqlException(e);
        } finally {
            try {
                if (null != resultSet) resultSet.close();
            } catch (SQLException e) {
                log.error(e);
                throw new RqlException(e);
            }
        }
	}
	
    /**
	 * <P>将结果集转换成指定类型的集合
	 *
	 * @param resultSet 结果集
	 * @param clazz Class类
	 * @return 集合
	 * @since 1.0
	 */
	public static List<Object> toBeanList(ResultSet resultSet, Class<?> clazz){
	    return toBeanList(resultSet, clazz, 0, Integer.MAX_VALUE);
	}
	/**
     * <P>将结果集转换成指定类型的集合
     *
     * @param resultSet 结果集
     * @param clazz Class类
     * @param offset 偏移量
     * @param limit 界限
     * @return 集合
     * @since 1.1
     */
//	@Deprecated
//    public static List<Object> toBeanList(ResultSet resultSet, Class<?> clazz, int offset, int limit){
//        try {
//            log.debug("toBeanList(): class:" + clazz + "\toffset:" + offset + "\tlimit:" + limit);
//            List<Object> list = new ArrayList<Object>();
//            ResultSetMetaData md = resultSet.getMetaData();   
//            int columnCount = md.getColumnCount();
//            log.debug("columnCount:" + columnCount);
//            if (offset > 0) {
//                resultSet.absolute(offset);
//                if (resultSet.getRow() < 1) {
//                    log.warn("ResultSet has no current row!");
//                    return list;
//                }
//            } else if (!resultSet.next()) {
//                return list;
//            }
//            do {
//                if (offset > 0 && resultSet.getRow() > limit)
//                   break;
//                if (ParamTypeRegist.isSimpleType(clazz.getName())) {
//                    if (columnCount != 1) {
//                        log.error("record can only has one column!");
//                        throw new RqlException("record can only has one column!");
//                    }
//                    list.add(OgnlOps.convertValue(resultSet.getObject(1), clazz));
//                } else {//有默认构造函数的实体类,可以被newInstance
//                    Object bean = clazz.newInstance();
//                    for (int i = 1; i <= columnCount; i++) {
//                        String colname = md.getColumnName(i).toLowerCase();
//                        String propname = col2pro(colname);
//                        Object value = resultSet.getObject(i);
//                        try {
//                            Ognl.setValue(propname, bean, value);
//                        } catch (Exception e) {
//                            log.warn("warning! " + propname + " not exist in " + clazz);
//                        }
//                    }
//                    list.add(bean);
//                }
//            } while (resultSet.next());
//            return list;
//        } catch (SQLException e) {
//            log.error(e);
//            throw new RqlException(e);
//        } catch (InstantiationException e) {
//            log.error(e);
//            throw new RqlException(e);
//        } catch (IllegalAccessException e) {
//            log.error(e);
//            throw new RqlException(e);
//        } finally {
//            try {
//                if (null != resultSet) resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
	public static List<Object> toBeanList(ResultSet resultSet, Class<?> clazz, int offset, int limit){
	    return toBeanPage(resultSet, clazz, offset, limit).getResult();
	}
	public static RqlPage toBeanPage(ResultSet resultSet, Class<?> clazz) {
	    return toBeanPage(resultSet, clazz, 0, Integer.MAX_VALUE);
	}
    public static RqlPage toBeanPage(ResultSet resultSet, Class<?> clazz, int offset, int limit) {
        try {
            log.debug("convert ==== class:" + clazz + "\toffset:" + offset + "\tlimit:" + limit);
            List<Object> list = new ArrayList<Object>();
            RqlPage page = new RqlPage();
            page.setOffset(offset);
            page.setLimit(limit);
            ResultSetMetaData md = resultSet.getMetaData();   
            int columnCount = md.getColumnCount();
            log.debug("columnCount:" + columnCount);
            int totalCount = 0;
            if (offset > 0) {
                resultSet.last();
                totalCount = resultSet.getRow();
                resultSet.absolute(offset);
                log.debug("totalCount:" + totalCount);
                if (totalCount < 1) {
                    log.warn("ResultSet has no current row!");
                    return page;
                }
            } else if (!resultSet.next()) {
                return page;
            }
            do {
                if (offset > 0 && resultSet.getRow() > limit)
                   break;
                if (ParamTypeRegist.isSimpleType(clazz.getName())) {
                    if (columnCount != 1) {
                        log.error("record can only has one column!");
                        throw new RqlException("record can only has one column!");
                    }
                    list.add(OgnlOps.convertValue(resultSet.getObject(1), clazz));
                } else {//有默认构造函数的实体类,可以被newInstance
                    Object bean = clazz.newInstance();
                    for (int i = 1; i <= columnCount; i++) {
//                        String colname = md.getColumnName(i).toLowerCase();
                    	String colname = md.getColumnLabel(i).toLowerCase();
                        String propname = col2pro(colname);
                        Object value = resultSet.getObject(i);
                        try {
                            //Ognl.setValue(propname, bean, value);
            				ReflectUtil.setValue(bean, propname, value);
                        } catch (Exception e) {
                            log.warn("warning! " + propname + " not exist in " + clazz);
                        }
                    }
                    list.add(bean);
                }
            } while (resultSet.next());
            page.setCount(totalCount);
            page.setResult(list);
            return page;
        } catch (SQLException e) {
            log.error(e);
            throw new RqlException(e);
        } catch (InstantiationException e) {
            log.error(e);
            throw new RqlException(e);
        } catch (IllegalAccessException e) {
            log.error(e);
            throw new RqlException(e);
        } finally {
            try {
                if (null != resultSet) resultSet.close();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }
    /**
     * <P>按约定的规范来转换列名至属性名 如 CLASS_ROOM -> classRoom
     *
     * @param value 列名
     * @return 属性名
     * @since 1.0
     */
    public static String col2pro(String value) {
        return db2var(value, false);
    }
    
    
    /**
     * <P>按约定的规范来转换表名[或列名]至类名[或属性名] 如 CLASS_ROOM -> classRoom
     *
     * @param value 表名[或列名]
     * @param isTable 是否为表
     * @return 类名[或属性名]
     * @since 1.0
     */
    private static String db2var(String value, boolean isTable) {
        if (value == null || value.equals("")) {
            log.warn("db2var(): value is null");
            return value;
        }
        if (isTable) value = "_" + value;
        int len = value.length();
        StringBuffer var = new StringBuffer();
        boolean toupper = false;
        for (int i = 0; i < len; i ++) {
            char ch = value.charAt(i);
            if (ch == '_') {
                toupper = true;
            } else {
                var.append(toupper ? String.valueOf(ch).toUpperCase() : ch);
                toupper = false;
            }
        }
        //log.debug("db2var(): " + value + " -> " + var);
        return var.toString();
    }
    
    /**
     * <P>按约定的规范来转换类名[或属性名]至表名[或列名] 如 classRoom -> CLASS_ROOM 
     *
     * @param value 类名[或属性名]
     * @return 表名[或列名]
     * @since 1.0
     */
    public static String var2db(String value) {
        if (value == null || value.equals("")) {
            log.warn("var2db(): value is null");
            return value;
        }
        StringBuffer sb = new StringBuffer("");
        if (value.contains(",")) {
        	String[] vars = value.split(",");
        	for (int i = 0; i < vars.length; i++) {
        		String var = vars[i];
        		if (i > 0) {
        			sb.append(",");
        		}
        		sb.append(var.substring(0,1));
        		sb.append(var.substring(1).replaceAll("[A-Z]", "_$0"));
            }
        	
        } else {
        	sb.append(value.substring(0,1).toUpperCase() + value.substring(1).replaceAll("[A-Z]", "_$0"));
        }
        String str = sb.toString().toUpperCase();
        //log.debug("var2db(): " + value + " -> " + str);
        return str;
    }
    /**
	 * Test
	 */
//    public static void main(String[] args) {
//        System.out.println(col2pro("class_room_mate"));
//        System.out.println(db2var("class_room_mate", true));
//        System.out.println(var2db("classRoomMate"));
//    }
}
