/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.service 
 * FileName: QueryMchtService.java
 * Author:   LJY
 * Date:     2017年12月5日 下午3:40:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月5日下午3:40:11                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;

import java.util.List;

/**
 * 名称：商户下拉<br> 
 * 功能：查询商户信息<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月5日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */

public class QueryMchtService extends SnowService{
    public static QueryMchtService getInstance() throws SnowException {
        return ContextUtil.getSingleService(QueryMchtService.class);
    }

    /**
     *根据商户编号查询商户简称
     * @param key
     * @return
     */
    public List<Object> getMchtNameByIds(String mchtIds) {
        DBDao dao = DBDaos.newInstance();
        String[] mchtIdArray = mchtIds.split(",");
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryMchtByIds", 
                RqlParam.map().set("mchtIdArray", mchtIdArray));
    }

    /**
     *查询商户信息
     * @param mchtId
     * @param mchtSimpleName
     * @param page
     * @return
     */
    public PageResult queryMchtInfo(String mchtId, String mchtSimpleName, Page page) {
        // 获取一个DAO对象
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryMchtInfo",
                RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%" + mchtId + "%")
                        .set("mchtSimpleName", StringUtils.isBlank(mchtSimpleName) ? "" : "%" + mchtSimpleName + "%"),
                page);
    }
    
    
    
}
