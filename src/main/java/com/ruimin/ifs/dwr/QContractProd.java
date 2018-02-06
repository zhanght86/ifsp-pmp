/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.dwr 
 * FileName: QContractProd.java
 * Author:   Administrator
 * Date:     2017年7月14日 上午10:14:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年7月14日上午10:14:53                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.dwr;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtContractService;
/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年7月14日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class QContractProd {
    public String query(String conId) throws SnowException{
        String str=MchtContractService.getInstance().QContractProd(conId);
        if(str!=null){
            return "1";
        }
        return "0";
    }
}
