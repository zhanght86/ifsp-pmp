/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.service 
 * FileName: ActInfAuditService.java
 * Author:   LJY
 * Date:     2017年10月23日 下午3:40:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月23日下午3:40:11                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveInfConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfVO2;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO2;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfVO2;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMchtInInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMchtInInfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodConfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveProdConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveProdConfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.TblActiveRedbagConf;
import com.ruimin.ifs.pmp.mktActivity.process.bean.TblActiveRedbagConfTmp;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;
import com.ruimin.ifs.util.BaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月23日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class ActInfAuditService {
    /**
     * 服务单例
     * @return
     * @throws SnowException
     */
    public static ActInfAuditService getInstance() throws SnowException {
        // 根据class获取服务实例
        return ContextUtil.getSingleService(ActInfAuditService.class);
    }
    
    /**
     * 根据审核业务类型查询审核步骤
     * @param auditType
     * @return
     * @throws SnowException
     */
    public List<PmpAuditProcStep> selectTepList( String auditType) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        List<Object> objList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.selectTepList",
                RqlParam.map().set("auditType", auditType));
        List<PmpAuditProcStep> beanList = new ArrayList<>();
        for (Object procStep : objList) {
            beanList.add((PmpAuditProcStep) procStep);
        }
        
        return beanList;
    }
    
    
    /**
     * 插入到审核步骤表中
     * @param list
     * @param auditId
     * @throws SnowException
     */
    
    public void addStepInfo(List<PmpAuditProcStep> list, String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 临时表实体内容，对应到正式表实体对象中，最近操作时间，操作人
        for (PmpAuditProcStep pmpAuditProcStep : list) {
            // 获取记录表对象
            PmpAuditStepInfo pmpAuditStepInfo = new PmpAuditStepInfo();
            pmpAuditStepInfo.setSeqId(ContextUtil.getUUID());
            pmpAuditStepInfo.setAuditId(auditId);
            pmpAuditStepInfo.setStepNo(pmpAuditProcStep.getStepNo());
            pmpAuditStepInfo.setAuditState(ActiveInfConstants.AUDIT_STATE_00);
            pmpAuditStepInfo.setRoleId(pmpAuditProcStep.getAuditRoleId());
            dao.insert(pmpAuditStepInfo);
        }
    }
    
    /**
     * 新增审核流水(公共)
     * @param ifsCtCdTmp
     * @param sessionUserInfo
     * @throws SnowException
     */
    public void addAuditInfo(String auditProcId,ActiveInfTmpVO tmpVO, SessionUserInfo sessionUserInfo) throws SnowException {
        // 审核信息实体对象
        PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
        PmpAuditInfo.setAuditId(tmpVO.getAuditId()); //审核信息编号
        // 获取审核标识状态，根据状态设置不同的审核流水业务类型
        String checkStat = tmpVO.getAuditFlag();
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_01)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_50);// 审核业务类型,50-活动管理新增
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息新增");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngAudit.jsp");// 审核信息路径
        }
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_03)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_51);// 审核业务类型,51-活动管理修改
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息修改");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngAudit.jsp");// 审核信息路径
        }
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_04)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_52);// 审核业务类型,52-活动管理配置
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息配置审核");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngConfAudit.jsp");// 审核信息路径
        }
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_05)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_53);// 审核业务类型,53-活动管理参与商户
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息参与商户审核");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngMchtAudit.jsp");// 审核信息路径
        }
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_06)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_54);// 审核业务类型,54-活动管理暂停
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息暂停");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngSRAudit.jsp");// 审核信息路径
        }
        if (checkStat.equals(ActiveInfConstants.AUDIT_FLAG_07)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_55);// 审核业务类型,55-活动管理恢复
            PmpAuditInfo.setAuditDesc("活动管理(" + tmpVO.getActiveNo() + ")信息恢复");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngSRAudit.jsp");// 审核信息路径
        }
        PmpAuditInfo.setAuditProcId(auditProcId);// 审核流程编号
        PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
        // 14位
        PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
        PmpAuditInfo.setApplyBrNo(sessionUserInfo.getBrno());// 申请机构编号
        
        PmpAuditInfo.setAuditState(ActiveInfConstants.AUDIT_STATE_00);// 审核状体00-未审核；01-审核通过；02-审核拒绝；
        PmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
        PmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
        // 14位
        DBDao dao = DBDaos.newInstance();
        dao.insert(PmpAuditInfo);
    }
    
    
    /**
     * 审核流水
     * @param ifsCtCdTmp
     * @param sessionUserInfo
     * @throws SnowException
     */
    public void addAuditInfo2(String auditProcId,String activeNo,String auditId,String auditFlag, SessionUserInfo sessionUserInfo) throws SnowException {
        // 审核信息实体对象
        PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
        PmpAuditInfo.setAuditId(auditId); //审核信息编号
        // 获取审核标识状态，根据状态设置不同的审核流水业务类型
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_01)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_50);// 审核业务类型,50-活动管理新增
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息新增");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngAudit.jsp");// 审核信息路径
        }
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_03)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_51);// 审核业务类型,51-活动管理修改
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息修改");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngAudit.jsp");// 审核信息路径
        }
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_04)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_52);// 审核业务类型,52-活动管理配置
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息配置审核");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngConfAudit.jsp");// 审核信息路径
        }
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_05)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_53);// 审核业务类型,53-活动管理参与商户
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息参与商户审核");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngMchtAudit.jsp");// 审核信息路径
        }
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_06)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_54);// 审核业务类型,54-活动管理暂停
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息暂停");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngSRAudit.jsp");// 审核信息路径
        }
        if (auditFlag.equals(ActiveInfConstants.AUDIT_FLAG_07)) {
            PmpAuditInfo.setAuditType(ActiveInfConstants.AUDIT_TYPE_55);// 审核业务类型,55-活动管理恢复
            PmpAuditInfo.setAuditDesc("活动管理(" + activeNo + ")信息恢复");// 审核信息描述
            PmpAuditInfo.setAuditUrl("actvMngSRAudit.jsp");// 审核信息路径
        }
        PmpAuditInfo.setAuditProcId(auditProcId);// 审核流程编号
        PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
        // 14位
        PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
        PmpAuditInfo.setApplyBrNo(sessionUserInfo.getBrno());// 申请机构编号
       
        PmpAuditInfo.setAuditState(ActiveInfConstants.AUDIT_STATE_00);// 审核状体00-未审核；01-审核通过；02-审核拒绝；
        PmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
        PmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
        // 14位
        DBDao dao = DBDaos.newInstance();
        dao.insert(PmpAuditInfo);
    }
    
    
    
    /**
     * 根据审核编号查询审核信息
     * @param auditId
     * @return
     * @throws SnowException
     */
    public PmpAuditInfo getAuditInfoByAduitId(String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return (PmpAuditInfo) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.getAuditInfoByAduitId",
                RqlParam.map().set("auditId", auditId));
    }
    
    /**
     * 根据审核信息编号查询审核流程步骤表，得到审核步骤
     * 
     * @param
     * @throws SnowException
     */
    public PmpAuditStepInfo getAuditStepInfo(String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.getAuditStepInfo",
                RqlParam.map().set("auditId", auditId));
    }
    
    /**
     * 根据审核流程编号和步骤，查询下一审核步骤
     * 
     * @param
     * @throws SnowException
     */
    public PmpAuditStepInfo getNextStep(int stepNo, String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.getNextStep",
                RqlParam.map().set("auditId", auditId).set("stepNo", stepNo));
    }
    
    
    /**
     * 审核通过，记录此次审核记录，插入到审核记录表中
     */
    public void updateAuditStepInfo(String seqId, Integer roleId, String tlrno, String auditView) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 审核意见
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update PMP_AUDIT_STEP_INFO set AUDIT_STATE ='01',AUDIT_OPR_NO='" + tlrno + "',AUDIT_DATET_IME='"
                + time + "',AUDIT_VIEW='" + auditView + "' where SEQ_ID='" + seqId + "'";
        dao.executeUpdateSql(sql);

    }
    
    
    /**
     * 审核拒绝，记录此次审核记录，插入到审核记录表中
     */
    public void updateAuditStepInfoRefuse(String seqId, Integer roleId, String tlrno, String auditView)
            throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 审核意见
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update PMP_AUDIT_STEP_INFO set AUDIT_STATE ='02',AUDIT_OPR_NO='" + tlrno + "',AUDIT_DATET_IME='"
                + time + "',AUDIT_VIEW='" + auditView + "' where SEQ_ID='" + seqId + "'";
        dao.executeUpdateSql(sql);

    }

    /**
     * 审核通过，更改审核信息表状态，AUDIT_STATE 01-审核通过;
     * 
     * @param prodId
     * @throws SnowException
     */
    public void updateAuditInfoState(String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 获取最后更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update PMP_AUDIT_INFO set AUDIT_STATE ='01',LAST_UPD_DATE_TIME='" + time + "' where AUDIT_ID='"
                + auditId + "'";
        dao.executeUpdateSql(sql);
    }

    /**
     * 更改审核信息表状态,AUDIT_STATE 02-审核拒绝;
     * 
     * @param prodId
     * @throws SnowException
     */
    public void updateAuditInfoRefuse(String auditId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 获取最后更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update PMP_AUDIT_INFO set AUDIT_STATE ='02',LAST_UPD_DATE_TIME='" + time + "' where AUDIT_ID='"
                + auditId + "'";
        dao.executeUpdateSql(sql);
    }
    
    
    /**
     * 更新活动信息临时表
     * @param activeInfo
     * @param opr
     * @throws SnowException
     */
    public void updActInf(String auditId,String auditFlag,String activeNo,String opr) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update tbl_active_inf_tmp set AUDIT_ID='"+auditId+"' ,AUDIT_FLAG= '"+auditFlag+
                "',UPDATE_TIME='"+time+"',UPDATE_OPR= '"+opr+"'where ACTIVE_NO='"+activeNo+"'";
        dao.executeUpdateSql(sql);
    }
    
    
    /**
     * 根据审核编号将活动信息表恢复为正常
     * @throws SnowException
     */
    public void updateActiveInfTmpVO(String auditId,String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取最近更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "UPDATE tbl_active_inf_tmp t SET t.AUDIT_FLAG = '"+ActiveInfConstants.AUDIT_FLAG_00+"',t.AUDIT_OPR='"+
                tlrno+"',t.AUDIT_TIME='"+time+"',t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_UNDONE
                +"' WHERE t.AUDIT_ID='"+auditId+"'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 新增审核过的活动信息插入正式表中
     * @param tmpVO
     * @param tlrno
     * @throws SnowException
     */
    public void insertActiveInfo(ActiveInfTmpVO2 tmpVO,String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        ActiveInfVO2 VO = new ActiveInfVO2();
        //查询临时表信息
        ActiveInfTmpVO2 tmpByActNo = (ActiveInfTmpVO2)dao.selectOne(
                "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActInfoByActNoTmp",
                RqlParam.map().set("activeNo", tmpVO.getActiveNo()));
        ReflectionUtil.copyProperties(tmpByActNo,VO,new String[]{"syncFlag","auditId","mchtCount"});
        VO.setUpdateOpr(tlrno);//设置最后更新操作员编号
        VO.setUpdateTime(BaseUtil.getCurrentDateTime());//设置最后更新时间
        dao.insert(VO);
    }
    
    /**
     * 新增审核过的活动配置的产品插入正式表
     * @param tmpVO
     * @param tlrno
     * @throws SnowException
     */
    public void insertProInfo(String activeNo,String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        ActiveProdConfVO VO = new ActiveProdConfVO();
        //查询临时表信息
        List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectProInfo",
                RqlParam.map().set("activeNo", activeNo));
       // List<ActiveProdConfVO> realList = new ArrayList<>();
        List<ActiveProdConfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveProdConfTmpVO[0]));
        for (ActiveProdConfTmpVO activeProdConfTmpVO : asList) {
            ReflectionUtil.copyProperties(activeProdConfTmpVO,VO,new String[]{"syncFlag"});
            VO.setUpdateOpr(tlrno);
            VO.setUpdateTime(BaseUtil.getCurrentDateTime());
           // realList.add(VO);
            dao.insert(VO);
        }
       // dao.insert(realList);
    }
    
    /**
     * 新增审核过的活动周期信息插入正式表
     * @param activeNo
     * @param tlrno
     * @throws SnowException
     */
   public void insertCycC(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取正式表实体对象
       ActiveCycleConfVO  VO = new ActiveCycleConfVO();
       //查询临时表信息
       List<Object> selectList = dao.selectList(
               "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectCyc",
               RqlParam.map().set("activeNo", activeNo));
       //List<ActiveCycleConfVO> realList = new ArrayList<>();
       List<ActiveCycleConfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveCycleConfTmpVO[0]));
       for (ActiveCycleConfTmpVO activeCycleConfTmpVO : asList) {
           ReflectionUtil.copyProperties(activeCycleConfTmpVO,VO,new String[]{"syncFlag"});
           VO.setUpdateOpr(tlrno);
           VO.setUpdateTime(BaseUtil.getCurrentDateTime());
           //realList.add(VO);
           dao.insert(VO);
    }
      // dao.insert(realList);
   }
   
   
   /**
    * 更新活动信息表状态为已同步
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void updateActSync(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_inf_tmp t SET t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_DONE
               +"',t.UPDATE_OPR='"+tlrno+"',t.UPDATE_TIME='"+time
       +"' WHERE t.ACTIVE_NO='"+activeNo+"'";
       dao.executeUpdateSql(sql);
   }
   
   /**
    * 更新活动产品配置表状态为已同步
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void updateProSync(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_prd_conf_tmp t SET t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_DONE
               +"',t.UPDATE_OPR='"+tlrno+"',t.UPDATE_TIME='"+time
               +"' WHERE t.ACTIVE_NO='"+activeNo+"'";
       dao.executeUpdateSql(sql);
   }
    
   /**
    * 更新活动周期表状态为已同步
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void updateCycSync(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_cycle_conf_tmp t SET t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_DONE
               +"',t.UPDATE_OPR='"+tlrno+"',t.UPDATE_TIME='"+time
               +"' WHERE t.ACTIVE_NO='"+activeNo+"' ";
       dao.executeUpdateSql(sql);
   }
   
    /**
     * 根据活动信息临时表更新正式表
     * @param tmpVO
     * @param tlrno
     * @throws SnowException
     */
   public void updateActReal(ActiveInfTmpVO2 tmpVO,String tlrno ) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取正式表实体对象
       ActiveInfVO2 VO = new ActiveInfVO2();
       //查询临时表信息
       ActiveInfTmpVO2 tmpByActNo = (ActiveInfTmpVO2)dao.selectOne(
               "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActInfoByActNoTmp",
               RqlParam.map().set("activeNo", tmpVO.getActiveNo()));
       ReflectionUtil.copyProperties(tmpByActNo,VO,new String[]{"syncFlag","auditId","mchtCount"});
       VO.setUpdateOpr(tlrno);//设置最后更新操作员编号
       VO.setUpdateTime(BaseUtil.getCurrentDateTime());//设置最后更新时间
       dao.update(VO);
   }
   
   /**
    * 根据活动产品配置表更新正式表
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void updateProReal(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取正式表实体对象
       ActiveProdConfVO VO = new ActiveProdConfVO();
       //根据活动编号删除正式表信息
       String sql = "DELETE FROM tbl_active_prd_conf WHERE ACTIVE_NO= '"+activeNo+"'"; 
       dao.executeSql(sql);
       //查询临时表信息
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectProInfo",
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveProdConfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveProdConfTmpVO[0]));
       for (ActiveProdConfTmpVO activeProdConfTmpVO : asList) {
           ReflectionUtil.copyProperties(activeProdConfTmpVO,VO,new String[]{"syncFlag"});
           VO.setUpdateOpr(tlrno);
           VO.setUpdateTime(BaseUtil.getCurrentDateTime());
           dao.insert(VO);
       
       }
      
   }
   
   /**
    * 根据活动周期临时表更新正式表
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void updateCycReal(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取正式表实体对象
       ActiveCycleConfVO2  VO = new ActiveCycleConfVO2();
       //查询临时表信息
       List<Object> selectList = dao.selectList(
               "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectCyc",
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveCycleConfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveCycleConfTmpVO[0]));
       for (ActiveCycleConfTmpVO activeCycleConfTmpVO : asList) {
           ReflectionUtil.copyProperties(activeCycleConfTmpVO,VO,new String[]{"syncFlag"});
           VO.setUpdateOpr(tlrno);
           VO.setUpdateTime(BaseUtil.getCurrentDateTime());
           //根据活动编号查询活动周期信息正式表
           String sql = "select * from tbl_active_cycle_conf where ACTIVE_NO='"+activeNo
                   +"' AND DATE_SEQ='"+VO.getDateSeq()+"'";
           List<Object> list = dao.executeQuerySql(sql, ActiveCycleConfVO.class);
           if (list.size()!=0&&null!=list) {
               dao.update(VO);
           }else{
               dao.insert(VO);
           }
           
    }
   }
   
   /**
    * 更新正式表活动状态(暂停与恢复)
    * @param activeStat
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void setStopRec(String activeStat,String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       String sql ="UPDATE tbl_active_inf SET ACTIVE_STAT ='"+activeStat
               +"',UPDATE_OPR='"+tlrno+"',UPDATE_TIME='"+time+"' WHERE ACTIVE_NO='"+activeNo+"'";
       dao.executeUpdateSql(sql);
       
   }
   
   
   /**
    * 根据活动配置方法表更新正式表
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void modifyActMethodConf(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       //正式表对象
       ActiveMethodConfVO VO = new ActiveMethodConfVO();
       //根据活动编号删除所有正式表记录
       String sql = "DELETE FROM tbl_active_method_conf WHERE ACTIVE_NO = '"+activeNo+"'";
       dao.executeSql(sql);
       //查询临时表记录
       List<Object> selectList = dao.selectList(
               "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActMethod", 
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveMethodConfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveMethodConfTmpVO[0]));
       for (ActiveMethodConfTmpVO activeMethodConfTmpVO : asList) {
           ReflectionUtil.copyProperties(activeMethodConfTmpVO,VO,new String[]{"syncFlag"});
           VO.setUpdateOpr(tlrno);
           VO.setUpdateTime(BaseUtil.getCurrentDateTime());
           dao.insert(VO);
    }
       
   }
   
   /**
    * 根据活动参与商户临时表修改正式表
    * @param activeNo
    * @param tlrno
    * @throws SnowException
    */
   public void modifyActMcht(String activeNo,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       //正式表对象
       ActiveMchtInInfVO VO = new ActiveMchtInInfVO();
       //根据活动编号查询临时表数据
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectMcht",
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveMchtInInfTmpVO> asList = Arrays.asList(selectList.toArray(new ActiveMchtInInfTmpVO[0]));
       for (ActiveMchtInInfTmpVO activeMchtInInfTmpVO : asList) {
           ReflectionUtil.copyProperties(activeMchtInInfTmpVO,VO,new String[]{"syncFlag"});
           //查询正式表记录是否存在,是则修改,否则新增
           String sql = "SELECT * from tbl_active_mcht_in_inf WHERE ACTIVE_NO='"+activeNo
                   +"' AND MCHT_ID='"+VO.getMchtId()+"'";
           List<Object> list = dao.executeQuerySql(sql, ActiveMchtInInfVO.class);
           if (list.size()!=0&&null!=list) {
               dao.update(VO);
           }else{
               dao.insert(VO);
           }
    }
       
       
   }
   
   
   /**
    * 根据审核编号将活动信息表修改为新增被拒绝
    * @throws SnowException
    */
   public void updAuditFlagRef(String auditId,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取最近更新时间
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_inf_tmp t SET t.AUDIT_FLAG = '"+ActiveInfConstants.AUDIT_FLAG_02+"',t.AUDIT_OPR='"+
               tlrno+"',t.AUDIT_TIME='"+time+"',t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_UNDONE
               +"' WHERE t.AUDIT_ID='"+auditId+"'";
       dao.executeUpdateSql(sql);
   }
   
   
   
   
   /**
    * 根据审核编号将活动信息表恢复为正常,已同步,活动状态还原
    * @throws SnowException
    */
   public void updateSRActInfo(String auditId,String activeStat,String tlrno) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       // 获取最近更新时间
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_inf_tmp t SET t.AUDIT_FLAG = '"
               +ActiveInfConstants.AUDIT_FLAG_00+"',t.AUDIT_OPR='"+tlrno+"',t.ACTIVE_STAT='"+activeStat
               +"',t.AUDIT_TIME='"+time+"',t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_DONE
               +"' WHERE t.AUDIT_ID='"+auditId+"'";
       dao.executeUpdateSql(sql);
   }
  
   /**
    * 回滚数据活动信息
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void backActInfo(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       //根据活动编号查询活动正式表数据
       ActiveInfVO2 VO = (ActiveInfVO2)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActInfReal",
               RqlParam.map().set("activeNo", activeNo));
       //根据活动编号查询活动临时表数据
       ActiveInfTmpVO2 tmpVO = (ActiveInfTmpVO2)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActInfoByActNoTmp",
               RqlParam.map().set("activeNo", activeNo));
       ReflectionUtil.copyProperties(VO, tmpVO);
       tmpVO.setAuditOpr(tlrno);
       tmpVO.setAuditTime(time);
       tmpVO.setUpdateOpr(tlrno);
       tmpVO.setUpdateTime(time);
       tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);
       dao.update(tmpVO);
       
       
   }
   
   /**
    * 回滚活动产品配置信息
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void backActPro(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       ActiveProdConfTmpVO tmpVO = new ActiveProdConfTmpVO();
       //根据活动编号删除临时表活动产品配置信息
       String sql = "DELETE FROM tbl_active_prd_conf_tmp WHERE active_no ='"+activeNo+"'";
       dao.executeSql(sql);
       //根据活动编号查询正式表活动产品配置信息
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectProInfoReal", 
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveProdConfVO> asList = Arrays.asList(selectList.toArray(new ActiveProdConfVO[0]));
       for (ActiveProdConfVO activeProdConfVO : asList) {
           ReflectionUtil.copyProperties(activeProdConfVO, tmpVO);
           tmpVO.setUpdateOpr(tlrno);
           tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_DONE);
           tmpVO.setUpdateTime(time);
           dao.insert(tmpVO);
    }
       
   }
   
   /**
    * 回滚活动周期信息
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void backActCyc(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       ActiveCycleConfTmpVO tmpVO = new ActiveCycleConfTmpVO();
       //根据活动编号删除周期活动临时表信息
       String sql = "DELETE FROM tbl_active_cycle_conf_tmp WHERE active_no ='"+activeNo+"'";
       dao.executeSql(sql);
       //根据活动编号查询周期活动正式表信息
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectCycReal", 
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveCycleConfVO> asList = Arrays.asList(selectList.toArray(new ActiveCycleConfVO[0]));
       for (ActiveCycleConfVO activeCycleConfVO : asList) {
           ReflectionUtil.copyProperties(activeCycleConfVO, tmpVO);
           tmpVO.setUpdateOpr(tlrno);
           tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_DONE);
           tmpVO.setUpdateTime(time);
           dao.insert(tmpVO);
    }
       
   }
   
   /**
    * 回滚活动方法配置
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void backActMethodC(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       ActiveMethodConfTmpVO tmpVO =new ActiveMethodConfTmpVO();
       //根据活动编号删除活动方法配置信息
       String sql = "DELETE FROM tbl_active_method_conf_tmp WHERE active_no ='"+activeNo+"'";
       dao.executeSql(sql);
       //根据活动编号查询周期活动正式表信息
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectActMethodReal", 
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveMethodConfVO> asList = Arrays.asList(selectList.toArray(new ActiveMethodConfVO[0]));
       for (ActiveMethodConfVO activeMethodConfVO : asList) {
           ReflectionUtil.copyProperties(activeMethodConfVO, tmpVO);
           tmpVO.setUpdateOpr(tlrno);
           tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_DONE);
           tmpVO.setUpdateTime(time);
           dao.insert(tmpVO);
    }
       
   }
   
   /**
    * 回滚参与商户信息
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void backActMcht(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       ActiveMchtInInfTmpVO tmpVO = new ActiveMchtInInfTmpVO();
       //根据活动编号删除活动参与商户
       String sql = "DELETE FROM tbl_active_mcht_in_inf_tmp WHERE active_no ='"+activeNo+"'";
       dao.executeSql(sql);
       //根据活动编号查询周期活动正式表信息
       List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectMchtReal", 
               RqlParam.map().set("activeNo", activeNo));
       List<ActiveMchtInInfVO> asList = Arrays.asList(selectList.toArray(new ActiveMchtInInfVO[0]));
       for (ActiveMchtInInfVO activeMchtInInfVO : asList) {
           ReflectionUtil.copyProperties(activeMchtInInfVO, tmpVO);
           tmpVO.setUpdateOpr(tlrno);
           tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_DONE);
           tmpVO.setUpdateTime(time);
           dao.insert(tmpVO);
    }
   }
   
   /**
    * 活动方法配置已同步
    * @throws SnowException
    */
   public void actMCSync(String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String sql = "UPDATE tbl_active_method_conf_tmp SET SYNC_FLAG='"
       +ActiveInfConstants.SYNC_STATE_DONE+"' WHERE ACTIVE_NO='"+activeNo+"'";
       dao.executeSql(sql);
       
   }
   
   /**
    * 参与商户同步
    * @param tlrno
    * @param activeNo
    * @throws SnowException
    */
   public void actMchtSync(String tlrno,String activeNo) throws SnowException{
       DBDao dao = DBDaos.newInstance();
       String time = BaseUtil.getCurrentDateTime();
       String sql = "UPDATE tbl_active_mcht_in_inf_tmp set SYNC_FLAG = '"
       +ActiveInfConstants.SYNC_STATE_DONE+"',UPDATE_OPR='"+tlrno+"',UPDATE_TIME='"
               +time+"' WHERE ACTIVE_NO='"+activeNo+"' ";
       dao.executeSql(sql);
   }
   
   /**
    * 
    * 退出商户
    * @throws SnowException
    */
   public void quite(String activeNo,String tlrno) throws SnowException {
       DBDao dao = DBDaos.newInstance();
       String time1 = BaseUtil.getCurrentDate();
       String time2 = BaseUtil.getCurrentDateTime();
       String sql1 = "UPDATE tbl_active_mcht_in_inf t SET t.IN_FLG = '02',t.OUT_DATE='"+time1+"',t.UPDATE_OPR = '"+tlrno+"',t.UPDATE_TIME='"+time2+"' WHERE t.ACTIVE_NO = '"+activeNo+"' ";
       String sql2 = "UPDATE tbl_active_mcht_in_inf_tmp t SET t.IN_FLG = '02',t.OUT_DATE='"+time1+"',t.UPDATE_OPR = '"+tlrno+"',t.UPDATE_TIME='"+time2+"' WHERE t.ACTIVE_NO = '"+activeNo+"' ";
       dao.executeUpdateSql(sql1);
       dao.executeUpdateSql(sql2);
   }

/**
 * 新增审核过的活动红包配置信息插入正式表
 * @param activeNo
 * @throws SnowException 
 */
public void insertRedConf(String activeNo) throws SnowException {
    DBDao dao = DBDaos.newInstance();
    // 获取正式表实体对象
    TblActiveRedbagConf  VO = new TblActiveRedbagConf();
    //查询临时表信息(数据状态为1-有效)
    List<Object> selectList = dao.selectList(
            "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectRedConf",
            RqlParam.map().set("activeNo", activeNo));
    List<TblActiveRedbagConfTmp> asList = Arrays.asList(selectList.toArray(new TblActiveRedbagConfTmp[0]));
    for (TblActiveRedbagConfTmp activeRedConfTmpVO : asList) {
        ReflectionUtil.copyProperties(activeRedConfTmpVO,VO,new String[]{"syncFlag","dataStat","updateTime"});
        dao.insert(VO);
 }
}

/**
 * 更新活动红包配置临时表状态为已同步
 * @param activeNo
 * @param tlrno
 * @throws SnowException 
 */
public void updateRedSync(String activeNo) throws SnowException {
    DBDao dao = DBDaos.newInstance();
    String time = BaseUtil.getCurrentDateTime();
    String sql = "UPDATE tbl_active_redbag_conf_tmp t SET t.SYNC_FLAG='"+ActiveInfConstants.SYNC_STATE_DONE
            +"',t.UPDATE_TIME='"+time
            +"' WHERE t.ACTIVE_NO='"+activeNo+"' ";
    dao.executeUpdateSql(sql);
    
}

/**
 * 根据活动红包配置临时表更新正式表
 * @param activeNo
 * @throws SnowException 
 */
public void updateRedConf(String activeNo) throws SnowException {
    DBDao dao = DBDaos.newInstance();
    // 获取正式表实体对象
    TblActiveRedbagConf  VO = new TblActiveRedbagConf();
    // 根据活动编号删除正式表红包配置信息
    String sql = "DELETE FROM tbl_active_redbag_conf WHERE active_no ='"+activeNo+"'";
    dao.executeSql(sql);
    //查询临时表信息(数据状态为1-有效)
    List<Object> selectList = dao.selectList(
            "com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectRedConf",
            RqlParam.map().set("activeNo", activeNo));
    List<TblActiveRedbagConfTmp> asList = Arrays.asList(selectList.toArray(new TblActiveRedbagConfTmp[0]));
    for (TblActiveRedbagConfTmp activeRedConfTmpVO : asList) {
        ReflectionUtil.copyProperties(activeRedConfTmpVO,VO,new String[]{"syncFlag","dataStat","updateTime"});
        dao.insert(VO);
 }
    
}

/**
 * 回滚活动红包配置表
 * @param activeNo
 * @throws SnowException 
 */
public void backActRed(String activeNo) throws SnowException {
    DBDao dao = DBDaos.newInstance();
    String time = BaseUtil.getCurrentDateTime();
    TblActiveRedbagConfTmp tmpVO = new TblActiveRedbagConfTmp();
    //根据活动编号删除红包配置活动临时表信息
    String sql = "DELETE FROM tbl_active_redbag_conf_tmp WHERE active_no ='"+activeNo+"'";
    dao.executeSql(sql);
    //根据活动编号查询红包配置正式表信息
    List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMngAudit.selectRedConfReal", 
            RqlParam.map().set("activeNo", activeNo));
    List<TblActiveRedbagConf> asList = Arrays.asList(selectList.toArray(new TblActiveRedbagConf[0]));
    for (TblActiveRedbagConf activeRedConfVO : asList) {
        ReflectionUtil.copyProperties(activeRedConfVO, tmpVO);
        tmpVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_DONE);
        tmpVO.setUpdateTime(time);
        tmpVO.setDataState(ActiveInfConstants.DATA_STATE_VALID);
        dao.insert(tmpVO);
 }
    
}
   
   
}
