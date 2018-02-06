/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.comp 
 * FileName: QueryAcctTypeAction.java
 * Author:   LJY
 * Date:     2017年10月24日 下午2:41:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月24日下午2:41:41                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.QueryAcctTypeService;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccountType;
import com.ruimin.ifs.util.StringUtil;

import java.util.List;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月24日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@SnowDoc(desc = "账户类型查询Action")
@ActionResource
public class QueryAcctTypeAction extends SnowAction{
    /**
     * * 功能描述: 账户类型查询
     * 
     * @param queryBean
     *            查询数据集
     * @return 返回类型 PageResult
     * @throws SnowException
     */
    @SnowDoc(desc = "账户类型查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
        String qAcctTypeNo = queryBean.getParameter("qAcctTypeNo");
        String qAcctTypeName = queryBean.getParameter("qAcctTypeName");
        return QueryAcctTypeService.getInstance().queryList(qAcctTypeNo, qAcctTypeName, queryBean.getPage());
    }
    
    /**
     * 根据账户号查询账户名
     * @param bean
     * @param key
     * @param request
     * @return
     * @throws SnowException
     */
    public static String getAcctN(FieldBean bean, String key, ServletRequest request) throws SnowException{
        StringBuffer acctName = new StringBuffer();
        if (StringUtil.isEmpty(key)) {
            return acctName.toString();
        }
        List<Object> acctNameByIds = QueryAcctTypeService.getInstance().getAcctNameByIds(key);
        if (null == acctNameByIds|| acctNameByIds.size() == 0) {
            return acctName.toString();
        }
        for (Object object : acctNameByIds) {
            AccountType acct = (AccountType)object;
            acctName.append(acct.getAcctTypeName()).append("|");
        }
        return acctName.toString().substring(0,acctName.toString().length()-1);
    }
    
    
    
}
