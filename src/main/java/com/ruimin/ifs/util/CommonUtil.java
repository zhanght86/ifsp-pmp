/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: CommonUtil.java
 * Author:   zhujianhua
 * Date:     2015-12-18 下午5:35:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.util;

import java.math.BigDecimal;
//import java.text.SimpleDateFormat;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.framework.dataset.field.FieldBean;
//import com.ruimin.ifs.pmp.common.util.StringUtil;

/**
 * 工具类<br> 
 * @author zhujianhua
 */
public class CommonUtil {
    /*
     * 分转元，整数位每隔三位逗号隔开
     */
    public static String transFenToYuan(FieldBean bean, String key,ServletRequest request){
    	if(key == null || "".equals(key.trim()))
                return "0.00";
            try {
                BigDecimal bigDecimal = new BigDecimal(key.trim());
                key = bigDecimal.movePointLeft(2).toString();
        } catch (Exception e) {
                return key;
        }
        //取整数位
        String[] split = key.split("\\.");
        String str1 = split[0];
        str1 = new StringBuilder(str1).reverse().toString();     //先将字符串颠倒顺序  
            String str2 = "";
            for(int i=0;i<str1.length();i++){  
                if(i*3+3>str1.length()){  
                    str2 += str1.substring(i*3, str1.length());  
                    break;  
                }  
                str2 += str1.substring(i*3, i*3+3)+",";  
            }  
            if(str2.endsWith(",")){  
                str2 = str2.substring(0, str2.length()-1);  
            }  
            //最后再将顺序反转过来,拼接小数位
            return new StringBuilder(str2).reverse().append(".").append(split[1]).toString();
    }
    
    /*
     * 分转元，整数位不用逗号隔开
     */
    public static String transFenToYuan2(FieldBean bean, String key,ServletRequest request){
        if(key == null || "".equals(key.trim()))
            return "0.00";
        try {
            BigDecimal bigDecimal = new BigDecimal(key.trim());
            key = bigDecimal.movePointLeft(2).toString();
    } catch (Exception e) {
            return key;
    }
        //取整数位
        String[] split = key.split("\\.");
        String str1 = split[0];
        String str2 = split[1];
        return str1+"."+str2;
    }
    
    
    
    
    //时间 六位格式转换
    public static String transTime(FieldBean bean, String key,ServletRequest request){
        if(key == null || "".equals(key.trim()))
            return "";
        return key.replaceAll("^(\\d{2})(\\d{2})(\\d{2})$","$1"+":"+"$2"+":"+"$3");
    }
    
    //日期时间 十四位格式转换
    public static String dateTime(FieldBean bean, String key,ServletRequest request){
        if(key == null || "".equals(key.trim()))
            return "";
        return key.replaceAll("^(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})$","$1"+"-"+"$2"+"-"+"$3"+" "+"$4"+":"+"$5"+":"+"$6");
    }
    
    
    public static String formatMonney(FieldBean bean, String key,ServletRequest request){
        return StringUtil.formatMonney(key);
    }
    
    public static String transYuanToFen(String yuan){
        if(yuan == null || "".equals(yuan.trim())){
            return "0";
        }
        if("D00000000000".equals(yuan.trim()) || "C00000000000".equals(yuan.trim())){
        	return "0";
        }
        yuan = yuan.replaceAll(",", "");
        BigDecimal a = new BigDecimal(yuan);
        BigDecimal b = new BigDecimal(100);
        long longValue = a.multiply(b).longValue();
        String fen = String.valueOf(longValue);
        return fen;
    }
    
    public static String yuanToFen(String amt){
    	if(amt==null){
    		return "0";
    	}else{
    		int posIndex = -1;
    		String str = "";
    		StringBuilder sb = new StringBuilder();
    		if(amt!=null && amt.length()>0 && !amt.equalsIgnoreCase("null")){
    			posIndex = amt.indexOf(".");
    			if(posIndex>0){
    				int len = amt.length();
    				if(len==posIndex+1){
    					str = amt.substring(0,posIndex);
    					if(str=="0"){
    						str = "";
    					}
    					sb.append(str).append("00");
    				}else if(len == posIndex+2){
    					str = amt.substring(0,posIndex);
    					if(str=="0"){
    						str = "";
    					}
    					sb.append(str).append(amt.substring(posIndex+1,posIndex+2)).append("0");
    				}else if(len == posIndex+3){
    					str = amt.substring(0,posIndex);
    					if(str=="0"){
    						str = "";
    					}
    					sb.append(str).append(amt.substring(posIndex+1, posIndex+3));
    				}else{
    					str = amt.substring(0,posIndex);
    					if(str=="0"){
    						str="";
    					}
    					sb.append(str).append(amt.substring(posIndex+1,posIndex+3));
    				}
    			}else{
    				sb.append(amt).append("00");
    			}
    		}else{
    			sb.append("0");
    		}
    		str = removeZero(sb.toString());
    		if(str !=null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null")){
    			return str;
    		}else{
    			return "0";
    		}
    	}
    }
    
    public static String removeZero(String str){
    	char ch;
    	String result = "";
    	if(str != null && str.trim().length()>0 && !str.trim().equalsIgnoreCase("null")){
			for(int i=0;i<str.length();i++){
				ch = str.charAt(i);
				if(ch!='0'){
					result = str.substring(i);
					break;
				}
			}
    	}else{
    		result = "";
    	}
    	return result;
    }
}
