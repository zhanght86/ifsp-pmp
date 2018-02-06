/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.service 
 * FileName: QueryAcctTypeService.java
 * Author:   LJY
 * Date:     2017年10月24日 下午2:43:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月24日下午2:43:14                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.util.StringUtil;

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
public class QueryAcctTypeService {
   
    public static QueryAcctTypeService getInstance() throws SnowException {
        return ContextUtil.getSingleService(QueryAcctTypeService.class);
    }
    
    /**
     * * 功能描述: 账户类型查询
     * 
     * @param queryBean
     *            qAcctTypeCode
     * @param queryBean
     *            qAcctTypeName
     * @param queryBean
     *            page
     * @return 返回类型 PageResult
     * @throws SnowException
     */
    public PageResult queryList(String qAcctTypeNo, String qAcctTypeName, Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryAcctType",
                RqlParam.map().set("qAcctTypeNo", qAcctTypeNo == null ? "" : qAcctTypeNo).set("qAcctTypeName",
                        qAcctTypeName == null ? "" : "%" + qAcctTypeName + "%"),
                page);
    }
    
    
    /**
     * 根据账户编号查询帐号名
     * @param acctNo
     * @return
     */
    public List<Object> getAcctNameByIds(String acctNo){
        DBDao dao = DBDaos.newInstance();
        String[] acctNoArr = acctNo.split(",");
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryAcctNameByIds",
                RqlParam.map().set("acctNoArr", acctNoArr));
    }
    
    
}
