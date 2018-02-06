/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.dwr 
 * FileName: MchtAccessRemove.java
 * Author:   Administrator
 * Date:     2017年7月6日 上午9:33:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年7月6日上午9:33:30                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.dwr;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.pmp.payProdMng.common.constants.PayProdConstants;
import com.ruimin.ifs.pmp.payProdMng.process.service.PbsProdInfoService;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年7月6日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class MchtAccessRemove {
    public String remove(String prodId,String prodState) throws SnowException{
        if (PayProdConstants.PROD_STATE_00.equals(prodState)) {
            List<Object> str=PbsProdInfoService.getInstance().queryMcht(prodId);
                return String.valueOf(str.size());
        }
        return "0";
    }

}
