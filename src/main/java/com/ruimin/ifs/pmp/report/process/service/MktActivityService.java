/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.process.service 
 * FileName: MktActivityService.java
 * Author:   LJY
 * Date:     2017年11月1日 下午1:03:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月1日下午1:03:25                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.process.service;

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
 * 日期：2017年11月1日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class MktActivityService {
    /**
     * 获取实例
     * 
     * @return 服务实例，单例
     * @throws SnowException
     */
    public static MktActivityService getInstance() throws SnowException {
        return ContextUtil.getSingleService(MktActivityService.class);
    }
    
    
    public PageResult pagequery(String qTxnDt,String qStlmDate,String qActiveNo,String qCardNo,Page page) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.report.rql.mktactivity.queryList", 
                RqlParam.map().set("qTxnDt", StringUtil.isBlank(qTxnDt)? "" : qTxnDt)
                .set("qStlmDate",StringUtil.isBlank(qStlmDate)? "" : qStlmDate)
                .set("qActiveNo",StringUtil.isBlank(qActiveNo)? "" : "%"+qActiveNo+"%")
                .set("qCardNo",StringUtil.isBlank(qCardNo)? "" : "%"+qCardNo+"%")
                , page);
    }
    
    
    /**
     * 报表下载查询
     * @param qTxnDt
     * @param qStlmDate
     * @param qActiveNo
     * @return
     * @throws SnowException
     */
    public List<Object> doutAgent(String qTxnDt,String qStlmDate,String qActiveNo,String qCardNo) throws SnowException{
        /** 查询操作 **/
        DBDao dao = DBDaos.newInstance();
        List<Object> detail = dao.selectList("com.ruimin.ifs.pmp.report.rql.mktactivity.queryList2",
                RqlParam.map()
                .set("qTxnDt", StringUtils.isBlank(qTxnDt) ? "" : qTxnDt)
                .set("qStlmDate", StringUtils.isBlank(qStlmDate) ? "" : qStlmDate)
                .set("qActiveNo", StringUtils.isBlank(qActiveNo) ? ""
                        : "%" + qActiveNo + "%")
                .set("qCardNo",StringUtil.isBlank(qCardNo)? "" : "%"+qCardNo+"%")
               );
        if (detail.size() == 0) {
            SnowExceptionUtil.throwErrorException("数据记录不存在，无法生成报表");
        }
        return detail;
    }
    
    
}
