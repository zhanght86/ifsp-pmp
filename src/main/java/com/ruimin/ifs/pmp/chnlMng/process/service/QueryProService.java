/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.service 
 * FileName: QueryProService.java
 * Author:   LJY
 * Date:     2017年10月24日 下午3:43:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月24日下午3:43:08                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月24日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class QueryProService {
    
    public static QueryProService getInstance() throws SnowException {
        return ContextUtil.getSingleService(QueryProService.class);
    }
    
    /**
     * 产品信息表查询
     * 
     * @param qprodId
     * @param qprodName
     * @param qprodState
     * @param page
     * @return
     * @throws SnowException
     */
    public PageResult queryPbsProdInfo(String qprodId, String qprodName, String qprodState, Page page)
            throws SnowException {
        // 获取一个DAO对象
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryPbsProdInfo",
                RqlParam.map().set("qprodId", StringUtils.isBlank(qprodId) ? "" : "%" + qprodId + "%")
                        .set("qprodName", StringUtils.isBlank(qprodName) ? "" : "%" + qprodName + "%")
                        .set("qprodState", StringUtils.isBlank(qprodState) ? "" : "%" + qprodState + "%"),
                page);
    }
    
    
    /**
     * 通过产品编号查找产品名
     * @param proId
     * @return
     */
    public List<Object> getProdNameByIds(String prodIds) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        String[] prodIdArray = prodIds.split(",");
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryProdByIds", 
                RqlParam.map().set("prodIdArray", prodIdArray));
        
    }

    
}
