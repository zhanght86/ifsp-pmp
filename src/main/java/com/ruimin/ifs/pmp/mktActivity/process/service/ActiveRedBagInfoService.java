/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.service 
 * FileName: ActiveRedBagInfoService.java
 * Author:   LJY
 * Date:     2017年11月28日 下午5:53:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月28日下午5:53:57                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.TblActiveRedbagConfTmp;

import java.util.List;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月28日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@SnowDoc(desc = "营销活动红包配置临时信息Service")
@Service
public class ActiveRedBagInfoService {
    public static ActiveRedBagInfoService getInstance() throws SnowException {
        return ContextUtil.getSingleService(ActiveRedBagInfoService.class);
    }
    
    
    public PageResult queryList(String activeNo,Page page) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvRedBagConf.queryList", RqlParam.map().set("activeNo", activeNo), page);
    }


    /**
     * 批量添加红包配置
     * @param redList
     * @throws SnowException 
     */
    public void batchAddRedBag(List<TblActiveRedbagConfTmp> redList) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        if(null == redList || redList.size() == 0){
            return;
        }
        dao.insert(redList);
    }


    /**
     * 根据活动编号获取最大序号
     * @param activeNo
     */
    public String getMaSeqByActiveNo(String activeNo) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvRedBagConf.getMaxSeq", activeNo);
        
    }


    /**
     * 批量更新红包配置信息
     * @param updRedList
     */
    public void batchUpdRedBag(List<TblActiveRedbagConfTmp> updRedList) {
        DBDao dao = DBDaos.newInstance();
        if(null == updRedList || updRedList.size() == 0){
            return;
        }
        for(TblActiveRedbagConfTmp param : updRedList){
            dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvRedBagConf.updateRedConf", param);
        }
    }


    /**
     * 批量删除红包配置信息
     * @param delRedList
     */
    public void batchDelRedBag(List<TblActiveRedbagConfTmp> delRedList) {
        DBDao dao = DBDaos.newInstance();
        if(null == delRedList || delRedList.size() == 0){
            return;
        }
        for(TblActiveRedbagConfTmp param : delRedList){
            dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvRedBagConf.deleteRedConf", param);
        }
        
    }
    
    
    
    
}
