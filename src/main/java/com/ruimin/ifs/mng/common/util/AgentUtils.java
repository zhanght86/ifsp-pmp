/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.common.util 
 * FileName: AgentUtils.java
 * Author:   Administrator
 * Date:     2017年6月13日 下午4:18:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月13日下午4:18:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.mng.common.util;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.mng.process.service.MchtAgentService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

import java.util.List;

/**
 * 名称：代理商工具类〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年6月13日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class AgentUtils {
    /**
     * 代理商ID生成方法
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    public String agentId() throws SnowException{
        String maxId = MchtAgentService.getInstance().agentId();
        String nextIdStr="";
        if(StringUtil.isBlank(maxId)){
        	nextIdStr = "9000000010";
		}else{
			Long nextLongId = Long.valueOf(maxId.substring(0,9))+1;
			nextIdStr = nextLongId.toString();
			char[] idCharArray = nextIdStr.toCharArray();
			int sum = 0;
			for(char tmp : idCharArray){
				int tmpInt = Character.getNumericValue(tmp);
				sum+=tmpInt;
			}
			nextIdStr+=String.valueOf(sum%10);
		}
        return nextIdStr;
    }
}
