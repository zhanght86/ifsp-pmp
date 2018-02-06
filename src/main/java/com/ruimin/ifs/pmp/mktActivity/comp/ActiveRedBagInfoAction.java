/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.comp 
 * FileName: ActiveRedBagInfo.java
 * Author:   LJY
 * Date:     2017年11月28日 下午5:17:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月28日下午5:17:43                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActiveRedBagInfoService;
import com.ruimin.ifs.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月28日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */

@SnowDoc(desc = "活动红包配置信息临时表操作Action")
@ActionResource
public class ActiveRedBagInfoAction extends SnowAction{
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
        String activeNo = queryBean.getParameter("activeNo");
        activeNo = StringUtil.isEmpty(activeNo)?"":activeNo;
        PageResult result = ActiveRedBagInfoService.getInstance().queryList(activeNo,queryBean.getPage());
        return result;
        
        
    }
}
