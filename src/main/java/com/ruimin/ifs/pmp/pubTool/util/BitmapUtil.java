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

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 
 * 
 * 处理数字型位图方法
 * 可处理基础值为0统一长度的位图
 * @author zhangjc
 *
 */


public class BitmapUtil {


	
	public static String bitmapToString(String bitmap,String split) {
		
		if(bitmap==null){return null;}
		if("".equals(bitmap)){return "";}
		
		int length=bitmap.length();
		String r="";
		String r2="";
		String result="";
        for(int i=0;i<length;i++){
			r+="0";
		}
        r2=r;
		
		for(int i=0;i<length;i++){
			
			if(!bitmap.substring(i, i+1).equals("0")){
		
				r2=bitmap.substring(i, i+1)+r2.substring(i+1);
	
			
			 while(r2.length()<length){
		        	
				 r2="0"+r2;
		        }
			 
         result+=r2+",";
         
         r2=r;
			}
		}
		result=result.substring(0, result.length()-1);
		return result;
		
	}


	public static String stringToBitmap(String s,String split) {
		
		int length=0;	
	
		
		if(s==null){
			return null;
		}
		if("".equals(s)){return "";}
		String[] list =s.split(split);
		
		length=list[0].length();
	   
		String result="";
		BigDecimal b =new BigDecimal("0");
		
		for(String r:list){
			
			if(length!=r.length()){
				
			}
			
			b=b.add(new BigDecimal(r)); 
			
		}
		
        result=b.toString();

        while(result.length()<length){
        	
        	result="0"+result;
        }

		return result;
	}
}
