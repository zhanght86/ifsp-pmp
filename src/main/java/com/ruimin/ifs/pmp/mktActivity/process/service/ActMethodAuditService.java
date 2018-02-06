/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.service 
 * FileName: AuditCommService.java
 * Author:   LJY
 * Date:     2017年10月20日 下午4:06:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月20日下午4:06:03                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodSectionConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MethodSectionInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MethodSectionInfVO;
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
 * 日期：2017年10月20日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class ActMethodAuditService {
    /**
     * 服务单例
     * @return
     * @throws SnowException
     */
    public static ActMethodAuditService getInstance() throws SnowException {
        // 根据class获取服务实例
        return ContextUtil.getSingleService(ActMethodAuditService.class);
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
            pmpAuditStepInfo.setAuditState(ActiveMethodConstants.AUDIT_STATE_00);
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
    public void addAuditInfo(String auditProcId,ActiveMethodInfTmpVO tmpVO, SessionUserInfo sessionUserInfo) throws SnowException {
        // 审核信息实体对象
        PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
        PmpAuditInfo.setAuditId(tmpVO.getAuditId()); //审核信息编号
        // 获取审核标识状态，根据状态设置不同的审核流水业务类型
        String checkStat = tmpVO.getAuditFlag();
        if (checkStat.equals(ActiveMethodConstants.AUDIT_FLAG_01)) {
            PmpAuditInfo.setAuditType(ActiveMethodConstants.AUDIT_TYPE_40);// 审核业务类型,40-活动方法新增
            PmpAuditInfo.setAuditDesc("活动方法(" + tmpVO.getMethodNo() + ")信息新增");// 审核信息描述
        }
        if (checkStat.equals(ActiveMethodConstants.AUDIT_FLAG_03)) {
            PmpAuditInfo.setAuditType(ActiveMethodConstants.AUDIT_TYPE_41);// 审核业务类型,41-活动方法修改
            PmpAuditInfo.setAuditDesc("活动方法(" + tmpVO.getMethodNo() + ")信息修改");// 审核信息描述
        }
        if (checkStat.equals(ActiveMethodConstants.AUDIT_FLAG_04)) {
            PmpAuditInfo.setAuditType(ActiveMethodConstants.AUDIT_TYPE_42);// 审核业务类型,42-活动方法删除
            PmpAuditInfo.setAuditDesc("活动方法(" + tmpVO.getMethodNo() + ")信息删除");// 审核信息描述
        }
     
        PmpAuditInfo.setAuditProcId(auditProcId);// 审核流程编号
        PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
        // 14位
        PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
        PmpAuditInfo.setApplyBrNo(sessionUserInfo.getBrno());// 申请机构编号
        PmpAuditInfo.setAuditUrl("actvMethodMngAudit.jsp");// 审核信息路径
        PmpAuditInfo.setAuditState(ActiveMethodConstants.AUDIT_STATE_00);// 审核状体00-未审核；01-审核通过；02-审核拒绝；
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
     * 更改审核信息表状态，AUDIT_STATE 02-审核拒绝;
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
     * 修改审核标识状态为正常,同步状态为未同步
     * @param auditId
     * @param tlrno
     * @throws SnowException
     */
    public void updateActiveMethodInfTmpVO(String auditId, String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取最近更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update tbl_active_method_inf_tmp set AUDIT_FLAG ='"+ActiveMethodConstants.AUDIT_FLAG_00+
                "',AUDIT_TIME ='"+time+"',AUDIT_OPR='"+tlrno+"',SYNC_FLAG='"+ActiveMethodConstants.SYNC_STATE_UNDONE+"' where AUDIT_ID='" + auditId + "'";
        dao.executeUpdateSql(sql);
        
    }
    
    /**
     * 修改审核标识状态为正常,同步状态为已同步
     * @param auditId
     * @param tlrno
     * @throws SnowException
     */
    public void updMethodInfNormal(String auditId, String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取最近更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update tbl_active_method_inf_tmp set AUDIT_FLAG ='"+ActiveMethodConstants.AUDIT_FLAG_00+
                "',AUDIT_TIME ='"+time+"',AUDIT_OPR='"+tlrno+"',SYNC_FLAG='"+ActiveMethodConstants.SYNC_STATE_DONE+"' where AUDIT_ID='" + auditId + "'";
        dao.executeUpdateSql(sql);
        
    }
    
    /**
     * 把审核通过信息插入到正式表中
     * 
     * @throws SnowException
     */
    public void insertMethodInfo(ActiveMethodInfTmpVO tmpVO, String tlrno) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        ActiveMethodInfVO VO = new ActiveMethodInfVO();
        // 临时表实体内容，对应到正式表实体对象中
        //根据活动方法编号查询临时表数据
        ActiveMethodInfTmpVO tmpVOByMethodNo = (ActiveMethodInfTmpVO)dao.selectOne(
                "com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectMethodInfoByMethodNo", 
                RqlParam.map().set("methodNo",tmpVO.getMethodNo()));
        ReflectionUtil.copyProperties(tmpVOByMethodNo,VO,new String[]{"syncFlag","auditId"});
        //设置最后更新操作员编号
        VO.setUpdateOpr(tlrno);
        // 插入正式表中
        dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.insertMethodInfo", VO);
        //dao.insert(VO);
    }
    /**
     * 把分段信息信息插入到正式表中
     * 
     * @throws SnowException
     */
    public void insertSectionInfo(String methodNo, String tlrno) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        MethodSectionInfVO VO = new MethodSectionInfVO();
        // 临时表实体内容，对应到正式表实体对象中
        //根据活动方法编号查询临时表数据
        List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectSectionInfo",
                RqlParam.map().set("methodNo",methodNo));
        List<MethodSectionInfTmpVO> sectionList = Arrays.asList(selectList.toArray(new MethodSectionInfTmpVO[0]));
        //List<MethodSectionInfVO> realList = new ArrayList<>();
                for (MethodSectionInfTmpVO methodSectionInfTmpVO : sectionList) {
                    ReflectionUtil.copyProperties(methodSectionInfTmpVO,VO,new String[]{"syncFlag"});
                    //realList.add(VO); 
                    dao.insert(VO);
                }
        // 插入正式表中
       // dao.insert(realList);
    }
    
    
    
    /**
     * 临时表状态改为已同步
     * 
     * @param auditId
     * @throws SnowException
     */
    public void updMSyncFlag(String methodNo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = "update TBL_ACTIVE_METHOD_INF_TMP set SYNC_FlAG='" + ActiveMethodConstants.SYNC_STATE_DONE
                + "' where METHOD_NO='" + methodNo + "'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 临时表状态改为已同步
     * 
     * @param auditId
     * @throws SnowException
     */
    public void updSSyncFlag(String methodNo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = "update TBL_METHOD_SECTION_INF_TMP set SYNC_FlAG='" + ActiveMethodConstants.SYNC_STATE_DONE
                + "' where METHOD_NO='" + methodNo + "'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 根据审核过的信息来修改正式表
     * @param tmpVO
     * @param tlrno
     * @throws SnowException
     */
    public void updMethodInfo(String methodNo, String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        ActiveMethodInfVO VO = new ActiveMethodInfVO();
        //根据活动方法编号查询临时表数据
        ActiveMethodInfTmpVO tmpVOByMethodNo = (ActiveMethodInfTmpVO)dao.selectOne(
                "com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectMethodInfoByMethodNo", 
                RqlParam.map().set("methodNo",methodNo));
        //将临时表对象注入真实表对象
        ReflectionUtil.copyProperties(tmpVOByMethodNo,VO,new String[]{"syncFlag","auditId"});
        //设置最后更新操作员编号
        VO.setUpdateOpr(tlrno);
        //修改正式表
        dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.updateMethodInfo", VO);
        //dao.update(VO);
    }
    
    /**
     * 根据审核过的信息来修改正式表
     * @param methodNo
     * @param tlrno
     * @throws SnowException
     */
    public void updSectionInfo(String methodNo, String tlrno) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        // 获取正式表实体对象
        MethodSectionInfVO VO = new MethodSectionInfVO();
        // 临时表实体内容，对应到正式表实体对象中
        //根据活动方法编号查询临时表数据
        List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectSectionInfo",
                RqlParam.map().set("methodNo",methodNo));
        List<MethodSectionInfTmpVO> sectionList = Arrays.asList(selectList.toArray(new MethodSectionInfTmpVO[0]));
                for (MethodSectionInfTmpVO methodSectionInfTmpVO : sectionList) {
                    ReflectionUtil.copyProperties(methodSectionInfTmpVO,VO,new String[]{"syncFlag"});
                    //根据活动方法编号和分段编号查询真实表是否有记录
                    String sql = "select * from tbl_method_section_inf where METHOD_NO = '"+methodNo+"'"
                            + "and SECTION_SEQ ='"+VO.getSectionSeq()+"'";
                    List<Object> list = dao.executeQuerySql(sql, MethodSectionInfVO.class);
                    if (list.size()!=0&&null!=list) {
                        dao.update(VO);
                    }else{
                        dao.insert(VO);
                    }
                   
                }
    }
    
    /**
     * 修改审核状态为删除待审核
     * @param methodNo
     * @throws SnowException
     */
    public void updAuditFlagDel(String methodNo,String auditId ,String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取最近更新时间
        String time = BaseUtil.getCurrentDateTime();
        // 获取正式表实体对象
        String sql = "update TBL_ACTIVE_METHOD_INF_TMP set AUDIT_FLAG='" + ActiveMethodConstants.AUDIT_FLAG_04
                +"',AUDIT_ID='"+auditId+"',UPDATE_OPR='"+tlrno+ "',UPDATE_TIME='"+time+"' where METHOD_NO='" + methodNo + "'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 修改审核状态为新增被拒绝
     * @param methodNo
     * @throws SnowException
     */
    public void updAuditFlagRef(String methodNo,String tlrno) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        // 获取最近更新时间
        String time = BaseUtil.getCurrentDateTime();
        String sql = "update TBL_ACTIVE_METHOD_INF_TMP set AUDIT_FLAG='" + ActiveMethodConstants.AUDIT_FLAG_02
                +"',UPDATE_OPR='"+tlrno+ "',UPDATE_TIME='"+time+"' where METHOD_NO='" + methodNo + "'";
        dao.executeUpdateSql(sql);
    }
    
    /**
     * 根据活动方法编号查询方法信息
     * @param methodNo
     * @return
     */
    public ActiveMethodInfVO selectMethodInfoReal(String methodNo){
        DBDao dao = DBDaos.newInstance();
        return (ActiveMethodInfVO)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectMethodInfoReal", 
                RqlParam.map().set("methodNo", methodNo));
        
    }
    
    
    
    /**
     * 回滚数据方法，修改审核被拒绝时
     * 
     * @param mchtId
     * @return
     * @throws SnowException
     */
    public void backMethodInfo(ActiveMethodInfTmpVO tmpVO, ActiveMethodInfVO VO, String tlrno)
            throws SnowException {
        DBDao dao = DBDaos.newInstance();
        //根据活动方法编号查询临时表数据
        ActiveMethodInfTmpVO tmpVOByMethodNo = (ActiveMethodInfTmpVO)dao.selectOne(
                "com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectMethodInfoByMethodNo", 
                RqlParam.map().set("methodNo",tmpVO.getMethodNo()));
        // 把正式表数据copy到临时表中
        ReflectionUtil.copyProperties(VO, tmpVOByMethodNo);
        tmpVOByMethodNo.setAuditFlag(ActiveMethodConstants.AUDIT_FLAG_00);
        tmpVOByMethodNo.setUpdateOpr(tlrno);
        tmpVOByMethodNo.setUpdateTime(BaseUtil.getCurrentDateTime());
        dao.update(tmpVOByMethodNo);
    }
    
    /**
     * 回滚数据方法，修改审核被拒绝时
     * 
     * @param mchtId
     * @return
     * @throws SnowException
     */
    public void backSectionInfo( String methodNo, String tlrno)
            throws SnowException {
        DBDao dao = DBDaos.newInstance();
        
        MethodSectionInfTmpVO tmpVO = new MethodSectionInfTmpVO();
        
       //根据活动方法编号查询正式表分段信息
        List<Object> selectList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodAudit.selectSectionInfoReal",
                RqlParam.map().set("methodNo",methodNo));
        List<MethodSectionInfVO> sectionList = Arrays.asList(selectList.toArray(new MethodSectionInfVO[0]));
        //把活动所有分段信息置为删除状态
        String sql = "update tbl_method_section_inf_tmp set data_state='"+ActiveMethodSectionConstants.DATA_STATE_INVALID+
                "' where METHOD_NO = '"+methodNo+"'";
        dao.executeUpdateSql(sql);
        for (MethodSectionInfVO methodSectionInfVO : sectionList) {
            ReflectionUtil.copyProperties(methodSectionInfVO,tmpVO);
            tmpVO.setSyncFlag(ActiveMethodConstants.SYNC_STATE_DONE);
            tmpVO.setUpdateOpr(tlrno);
            tmpVO.setUpdateTime(BaseUtil.getCurrentDateTime());
            dao.update(tmpVO);
        }
      
    }
    
}
