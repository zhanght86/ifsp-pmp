/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.comp 
 * FileName: RandomTxnAction.java
 * Author:   LJY
 * Date:     2017年12月12日 下午2:08:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月12日下午2:08:13                     1.0                  
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
import com.ruimin.ifs.pmp.mktActivity.process.service.RandomTxnService;

/**
 * 名称：〈立减营销活动查询〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月12日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */

@SnowDoc(desc = "立减营销活动查询")
@ActionResource
public class RandomTxnAction extends SnowAction{
    @Action
    @SnowDoc(desc = "立减营销活动查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
        String qActiveNm = queryBean.getParameter("qActiveNm");
        String qActiveNo = queryBean.getParameter("qActiveNo");
        String qMchtId = queryBean.getParameter("qMchtId");
        String qSDate = queryBean.getParameter("qSDate");
        String qEDate = queryBean.getParameter("qEDate");
        return RandomTxnService.getInstance().queryAll(qActiveNm,qActiveNo,qMchtId,qSDate,qEDate,queryBean.getPage());
    }
}
