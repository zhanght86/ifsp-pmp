/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.process.service 
 * FileName: MchtTotalAuditService.java
 * Author:   LJY
 * Date:     2017年11月17日 上午9:25:37
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月17日上午9:25:37                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfoTmp;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;
import com.ruimin.ifs.util.BaseUtil;


/**
 * 名称：商户信息与合同审核<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月17日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class MchtTotalAuditService {
    public static MchtTotalAuditService getInstance() throws SnowException{
        return ContextUtil.getSingleService(MchtTotalAuditService.class);
    }
    
    /**
     * 根据商户编号查询商户信息辅临时表数据
     * @param pbsMchtBaseInfo
     * @return
     */
    public PbsMchtAssistInfoTmp selectMchtAssist(String mchtId){
        DBDao dao = DBDaos.newInstance();
        return (PbsMchtAssistInfoTmp)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtTotalAudit.selectMchtAssit", 
                RqlParam.map().set("mchtId", mchtId));
        
        
    }
    
    /**
     * 根据商户编号查询商户信息辅真实表数据
     * @param mchtId
     * @return
     */
    public PbsMchtAssistInfo selectMchtAssistReal(String mchtId){
        DBDao dao = DBDaos.newInstance();
        return (PbsMchtAssistInfo)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtTotalAudit.selectMchtAssitReal", 
                RqlParam.map().set("mchtId", mchtId));
        
        
    }
    
    /**
     * 商户信息辅临时表插入正式表
     * @param mchtId
     * @throws SnowException
     */
    public void insertMchtAssit(String mchtId) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        //查询临时表信息
        PbsMchtAssistInfoTmp voTemp = selectMchtAssist(mchtId);
        //建立正式表对象
        PbsMchtAssistInfo vo = new PbsMchtAssistInfo();
        //把临时表对象注入正式表
        ReflectionUtil.copyProperties(voTemp,vo);
        dao.insert(vo);
    }
    
    /**
     * 更新商户信息辅表
     * @param mchtId
     * @throws SnowException
     */
    public void updateMchtAssit(String mchtId) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        //查询临时表信息
        PbsMchtAssistInfoTmp voTemp = selectMchtAssist(mchtId);
        //查询真实表数据
        PbsMchtAssistInfo vo = selectMchtAssistReal(mchtId);
        // 修改审核通过，把临时表修改的那条数据copy到对应的正式表中
        ReflectionUtil.copyProperties(voTemp,vo);
        dao.update(vo);
    }
    
    
    /**
     * 回滚商户信息辅临时表
     * @param mchtId
     * @throws SnowException
     */
    public void backMchtAssit(String mchtId) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        //查询临时表信息
        PbsMchtAssistInfoTmp voTemp = selectMchtAssist(mchtId);
        //查询真实表数据
        PbsMchtAssistInfo vo = selectMchtAssistReal(mchtId);
        // 修改审核拒绝，把正式表的那条数据copy到对应的临时表中
        ReflectionUtil.copyProperties(vo,voTemp);
        dao.update(voTemp);
        
    }
    
    /**
     * 更新商户信息状态为添加待提审
     * @param mchtId
     * @param tlrNo
     * @throws SnowException
     */
    public void updateMchtInfo(String mchtId,String tlrNo) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        String time = BaseUtil.getCurrentDateTime();
        String sql = "UPDATE pbs_mcht_base_info_tmp p SET p.MCHT_STAT='"+MchtMngConstants.MCHT_STAT_10
                +"',p.LAST_UPD_DATE_TIME='"+time+"',p.LAST_UPD_TLR='"+tlrNo+"' WHERE p.MCHT_ID='"+mchtId+"'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 更新商户合同状态为添加待提审
     * @param mchtId
     * @param tlrNo
     * @throws SnowException
     */
    public void updateMchtContract(String mchtId,String tlrNo) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        String time = BaseUtil.getCurrentDateTime();
        String sql = "UPDATE pbs_mcht_contr_info_tmp p SET p.CON_STATE='"+MchtContractConstants.MCHT_STAT_12
                +"',p.LAST_UPD_DATE_TIME='"+time+"',p.LAST_UPD_TLR='"+tlrNo+"' WHERE p.MCHT_ID='"+mchtId+"'";
        dao.executeUpdateSql(sql);
    }
    
}
