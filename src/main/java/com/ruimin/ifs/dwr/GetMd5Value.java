/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.dwr 
 * FileName: GetMd5Value.java
 * Author:   Administrator
 * Date:     2017年6月24日 下午12:21:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月24日下午12:21:25                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.dwr;

import com.ruim.ifsp.signature.IfspSdkSignAtureUtil;
import com.ruimin.ifs.core.exception.SnowException;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年6月24日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class GetMd5Value {
    public String reset() throws SnowException{
        // 系统随机生成MD5值
       /* String md5Value = IfspSdkSignAtureUtil.getMd5Key().getMd5Key();
        System.out.println(md5Value);
        return md5Value;*/
        // ------2017.12.13 商户进件修改签名包------
        String md5Value = IfspSdkSignAtureUtil.getMd5Key().getCertId();
        System.out.println(md5Value);
        return md5Value;
    }
}
