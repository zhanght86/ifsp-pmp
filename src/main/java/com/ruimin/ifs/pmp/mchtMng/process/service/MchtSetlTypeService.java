/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyCategoryNameService.java
 * Author:   zqy
 * Date:     2016年8月02日 上午10:39:09
 * Description: 类目名词    
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年8月02日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mktActivity.process.bean.AcctGpConfVO;

/**
 * 名称：清算方式 
 * 功能：清算方式
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月02日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "清算方法查询操作Service")
@Service
public class MchtSetlTypeService extends SnowService {
    public static MchtSetlTypeService getInstance() throws SnowException {
        return ContextUtil.getSingleService(MchtSetlTypeService.class);
    }
    
    /**
     * 分页查询清算方法
     */
    public PageResult queryList(String qmchtId, Page page){
        DBDao dao=DBDaos.newInstance();
        String mchtMngNo = (String)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtSetlType.queryMchtMngId",
                RqlParam.map().set("qmchtId", StringUtils.isEmpty(qmchtId) ? "" : qmchtId));
        if(null == mchtMngNo||"".equals(mchtMngNo)){
            return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtSetlType.querySeltTypeNoSetlType", 
                    page);
        }else{
            return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtSetlType.querySeltTypeSetlType",
                    page);   
        }
    }
    
    
    /**
     * 根据结算编号查询结算方式
     * @param dataNo
     * @return
     * @throws SnowException
     */
    public String getNameById(String dataNo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        //return (AcctGpConfVO) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.querydDistinctNoName",
          //      RqlParam.map().set("qgpTpNo", gpTpNo).set("qgpTpNm", ""));
        return (String)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtSetlType.queryNameById", 
                dataNo);

    }

}
