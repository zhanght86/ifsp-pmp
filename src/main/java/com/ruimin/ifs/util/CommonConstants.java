/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruim.ifsp.paybus.cache 
 * FileName: CommonConstants.java
 * Author:   John
 * Date:     2016年8月15日 下午4:59:50
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   John           2016年8月15日下午4:59:50                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月15日 <br>
 * 作者：John <br>
 * 说明：<br>
 */
public class CommonConstants {

    private static String CONSTANTS_FILE = "SysParam.properties";
    private static String CONFIG_FILE_PATH = "/";
    
    public static String SIGN_CERT_PATH;
    public static String SIGN_CERT_PWD;
    
    private static Properties props = new Properties();
    static {
        InputStream ips = CommonConstants.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE_PATH + CONSTANTS_FILE);
        try {
            props.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        String message=props.getProperty(key);
        if(message != null && !"".equals(message)){
            return message;
        }else{
            return "";
        }
    }

}
