/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SysCQMethod.java
 * Author:   GH
 * Date:     2015年6月4日 上午10:50:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.util;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.process.service.DataDicEntryService;


import com.ruimin.ifs.po.TblDataDic;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 〈字段转义〉<br>
 * 
 * @author GH
 */
public class SysCQMethod {

    /**
     * 
     * 功能描述: 字符串转日期格式 <br>
     * 〈功能详细描述〉
     * 
     * @param element
     * @param value
     * @param request
     * @return
     * @throws AppException
     */
    public static String toDateStr(FieldBean bean, String key, ServletRequest request) throws SnowException {
        return BaseUtil.convertStringToDateStr(key);
    }


    /**
     * 
     * 功能描述: 分转元 <br>
     * 〈功能详细描述〉
     * 
     * @param element
     * @param value
     * @param request
     * @return
     * @throws AppException
     */
    public static String fenToYuan(FieldBean bean, String key, ServletRequest request) throws SnowException {
        if (key==null||"".equals(key)) {
            return "";
        }else {
            
            return BaseUtil.transFenToYuan(key);
        }
    }
    
    
//    /**
//     * 根据 微信主商户号类型 获取 类型名称
//     * 
//     * @param bean
//     * @param key
//     * @param request
//     * @return
//     */
//    public static String getWxMainMchtType(FieldBean bean, String key, ServletRequest request) throws SnowException {
//        if (StringUtils.isNotBlank(key)) {
//            ImpMchtEvalModl modlInfo = EvalModlService.getInstance().query(key);
//            if (modlInfo != null) {
//                return modlInfo.getEvalModlName();
//            }
//        }
//        return key;
//    }
    
/*    *//**
     * 根据计费代码获取计费名称
     * 
     * @param bean
     * @param key
     * @param request
     * @return
     *//*
    public static String getDiscName(FieldBean bean, String key, ServletRequest request) throws SnowException {
        if (StringUtils.isNotBlank(key)) {
        	DBDao dao = DBDaos.newInstance();
//        	ImpDiscIdInf inf = MchtDiscService.getInstance().query(key);
        	ImpDiscIdInf inf = dao.select(ImpDiscIdInf.class,key);
            if (inf != null) {
                return inf.getDiscName();
            }
        }
        return key;
    }*/
    
 
    
    /**
     * 商户特殊计费动态查询
     * 
     * added by mj 2015-11-24
     */
    public static String getDataDicName(FieldBean bean, String key, ServletRequest request) throws SnowException {
        if (StringUtils.isNotBlank(key)) {
        	String[] keys = key.split("\\|");
        	if(keys.length>1){
        		if("1347".equals(keys[0])){
	        		TblDataDic inf = DataDicEntryService.getInstance().query("1348",keys[1],keys[2]);
	        		if (inf != null) {
	        			return inf.getDataName();
	        		}
        		}
        	}else{
        		TblDataDic inf = DataDicEntryService.getInstance().query(null,null,key);
        		if (inf != null) {
        			return inf.getDataName();
        		}
        	}
        }
        return key;
    }
}
