/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.service 
 * FileName: RandomTxnService.java
 * Author:   LJY
 * Date:     2017年12月12日 下午2:13:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月12日下午2:13:42                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月12日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class RandomTxnService extends SnowService{
    /**
     * 
     * @return 服务实例，单例
     * @throws SnowException
     */
    public static RandomTxnService getInstance() throws SnowException {
        // 根据class获取服务实例
        return ContextUtil.getSingleService(RandomTxnService.class);
    }
    
    
    public PageResult queryAll(String qActiveNm, String qActiveNo, String qMchtId, String qSDate, String qEDate, Page page) throws SnowException {
        // 获取一个DAO对象
        DBDao dao = DBDaos.newInstance();
        // 组装模糊查询
        return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.RandomTxn.queryRedInfo",
                   RqlParam.map().set("qActiveNm", StringUtil.isBlank(qActiveNm)? "" : "%"+qActiveNm+"%")
                   .set("qActiveNo", StringUtil.isBlank(qActiveNo)? "" : "%"+qActiveNo+"%")
                   .set("qMchtId", StringUtil.isBlank(qMchtId)? "" :"%"+qMchtId+"%")
                   .set("qSDate", StringUtil.isBlank(qSDate)? "" :qSDate)
                   .set("qEDate", StringUtil.isBlank(qEDate)? "" :qEDate)
                   ,page);
    }
    
}
