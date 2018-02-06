/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.process.service 
 * FileName: MchtNetExpenseDailyService.java
 * Author:   LJY
 * Date:     2017年8月21日 下午12:55:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年8月21日下午12:55:27                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年8月21日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@Service
@SnowDoc(desc = "商户网点费用信息service")
public class MchtNetExpenseDailyService {
    /**
     * 获取实例
     * 
     * @return 服务实例，单例
     * @throws SnowException
     */
    public static MchtNetExpenseDailyService getInstance() throws SnowException {
        return ContextUtil.getSingleService(MchtNetExpenseDailyService.class);
    }
    /**
     * 
     * 功能描述: 商户网点费用信息分页查询<br>
     * 〈功能详细描述〉
     *
     * @param qTxnDtStart
     * @param qTxnDtEnd
     * @param qInAcctMerId
     * @param qInAcctMerName
     * @param page
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public PageResult pagequery(String qTxnDtStart,String qTxnDtEnd,String qInAcctMerId,String qInAcctMerName,Page page) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtNetExpenseDaily.queryList", 
                RqlParam.map().set("qTxnDtStart", StringUtil.isBlank(qTxnDtStart)? "" : qTxnDtStart)
                .set("qTxnDtEnd",StringUtil.isBlank(qTxnDtEnd)? "" : qTxnDtEnd)
                .set("qInAcctMerId",StringUtil.isBlank(qInAcctMerId)? "" : "%"+qInAcctMerId+"%")
                .set("qInAcctMerName",StringUtil.isBlank(qInAcctMerName)? "" : "%"+qInAcctMerName+"%"), page);
           
    }
    /**
     * 报表下载查询
     * @param qTxnDtStart
     * @param qTxnDtEnd
     * @param qInAcctMerId
     * @param qInAcctMerName
     * @return
     */
    public List<Object> doutAgent(String qTxnDtStart,String qTxnDtEnd,String qInAcctMerId,String qInAcctMerName) throws SnowException{
        /** 查询操作 **/
        DBDao dao = DBDaos.newInstance();
        List<Object> detail = dao.selectList("com.ruimin.ifs.pmp.report.rql.mchtNetExpenseDaily.queryList",
                RqlParam.map()
                .set("qTxnDtStart", StringUtils.isBlank(qTxnDtStart) ? "" : qTxnDtStart)
                .set("qTxnDtEnd", StringUtils.isBlank(qTxnDtEnd) ? "" : qTxnDtEnd)
                .set("qInAcctMerId", StringUtils.isBlank(qInAcctMerId) ? ""
                        : "%" + qInAcctMerId + "%")
                .set("qInAcctMerName", StringUtils.isBlank(qInAcctMerName) ? ""
                        : "%" + qInAcctMerName + "%"));
        if (detail.size() == 0) {
            SnowExceptionUtil.throwErrorException("数据记录不存在，无法生成报表");
        }
        return detail;
    }
    
}
