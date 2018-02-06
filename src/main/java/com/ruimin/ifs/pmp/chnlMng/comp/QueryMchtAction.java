/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: QueryMchtAction.java
 * Author:   LJY
 * Date:     2017年12月5日 下午3:36:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月5日下午3:36:35                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.QueryMchtService;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.util.StringUtil;

import java.util.List;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月5日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@SnowDoc(desc = "商户简称查询Action")
@ActionResource
public class QueryMchtAction extends SnowAction{
    @Action
    @SnowDoc(desc = "商户信息查询")
    public PageResult queryMchtInfo(QueryParamBean queryBean) throws SnowException{
        String mchtId = queryBean.getParameter("qmchtId");
        String mchtSimpleName = queryBean.getParameter("qmchtSimpleName");
        return QueryMchtService.getInstance().queryMchtInfo(mchtId,mchtSimpleName, queryBean.getPage());
    }
    
    
    /**
     * 根据商户编号得到商户简称
     * @param bean
     * @param key
     * @param request
     * @return
     * @throws SnowException
     */
    public static String getMchtName(FieldBean bean, String key, ServletRequest request) throws SnowException{
        StringBuffer mchtName = new StringBuffer();
        if (StringUtil.isEmpty(key)) {
            return mchtName.toString();
        }
        List<Object> mchtNameByIds = QueryMchtService.getInstance().getMchtNameByIds(key);
        if (null == mchtNameByIds|| mchtNameByIds.size() == 0) {
            return mchtName.toString();
        }
        for (Object object : mchtNameByIds) {
            //PbsMchtBaseInfoReal pBaseInfo = (PbsMchtBaseInfoReal)object;
            //mchtName.append(pBaseInfo.getMchtSimpleName()).append("|");
            mchtName.append(object.toString()).append("|");
        }
        return mchtName.toString().substring(0,mchtName.toString().length()-1);
    }
}
