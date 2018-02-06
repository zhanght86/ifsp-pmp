/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.comp 
 * FileName: ActvInfAuditAction.java
 * Author:   LJY
 * Date:     2017年10月23日 下午3:28:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月23日下午3:28:38                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveInfConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO2;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActInfAuditService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月23日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@ActionResource
@SnowDoc(desc = "活动管理审核相关操作")
public class ActvInfAuditAction extends SnowAction{
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "通过")
    public Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        Map<String, Object> map = new HashMap<String, Object>();
        UpdateRequestBean voBean = updateMap.get("activeInfo");
        //临时对象
        ActiveInfTmpVO2 tmpVO = new  ActiveInfTmpVO2();
        while (voBean.hasNext()) {
            DataObjectUtils.map2bean(voBean.next(),tmpVO);
        }
        // 获取审核意见
        String auditView = voBean.getParameter("auditView");
        //得到审核状态
        String auditFlag = tmpVO.getAuditFlag();
        //得到审核信息编号
        String auditId = tmpVO.getAuditId();
        //得到审核信息
        PmpAuditInfo pmpAuditInfo = ActInfAuditService.getInstance().getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        //得到操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据状态对应不同的操作
        if (ActiveInfConstants.AUDIT_FLAG_01.equals(auditFlag)) {
           addMethod(tmpVO, auditId, auditView, "11", tlrno, map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--新增审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_03.equals(auditFlag)) {
            updateMethod(tmpVO, auditId, auditView, "11", tlrno, map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--修改审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_04.equals(auditFlag)) {
            actConf(tmpVO,auditId,"11",tlrno,auditView, map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--配置审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_05.equals(auditFlag)) {
            actMcht(tmpVO,auditId,"11", tlrno,auditView,map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--参与商户审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_06.equals(auditFlag)) {
            StopRecMethod(tmpVO,tlrno,auditId,"11",auditView,map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--暂停审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_07.equals(auditFlag)) {
            StopRecMethod(tmpVO,tlrno,auditId,"11",auditView,map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--恢复审核通过:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        return map;
        
        
        
        
    }
    
    
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "拒绝")
    public void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        UpdateRequestBean voBean = updateMap.get("activeInfo");
        //临时对象
        ActiveInfTmpVO2 tmpVO = new  ActiveInfTmpVO2();
        while (voBean.hasNext()) {
            DataObjectUtils.map2bean(voBean.next(),tmpVO);
        }
        // 获取审核意见
        String auditView = voBean.getParameter("auditView");
        //得到审核状态
        String auditFlag = tmpVO.getAuditFlag();
        //得到审核信息编号
        String auditId = tmpVO.getAuditId();
        //得到审核信息
        PmpAuditInfo pmpAuditInfo = ActInfAuditService.getInstance().getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        //得到操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据状态对应不同的操作
        if (ActiveInfConstants.AUDIT_FLAG_01.equals(auditFlag)) {
            addMethodRef(auditId,auditView,"11", tlrno);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--新增审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_03.equals(auditFlag)) {
            updateMethodRef(tmpVO, auditId, auditView,"11", tlrno);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--修改审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_04.equals(auditFlag)) {
            actConfRef(tmpVO,auditId, "11", tlrno, auditView);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--配置审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_05.equals(auditFlag)) {
            actMchtRef( tmpVO, auditId, "11", tlrno,auditView);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--参与商户审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_06.equals(auditFlag)) {
            StopRecMethodRef(tmpVO,tlrno,auditId, "11",auditView);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--暂停审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
        if (ActiveInfConstants.AUDIT_FLAG_07.equals(auditFlag)) {
            StopRecMethodRef(tmpVO,tlrno,auditId, "11",auditView);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动管理审核]--恢复审核拒绝:活动编号[activeNo]=" + tmpVO.getActiveNo() });
        }
    }
    
    /**
     * 新增通过
     * @param tmpVO
     * @param auditId
     * @param auditView
     * @param roleId
     * @param tlrno
     * @throws SnowException
     */
    public void addMethod(ActiveInfTmpVO2 tmpVO,String auditId,String auditView,String roleId,String tlrno,Map<String, Object> map) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = ActInfAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
         // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActInfAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId, tlrno);
            // 4.把审核通过信息插入到正式表中(正式表此处发生变化)
            // 1)插入tbl_active_inf,将临时表状态改为已同步
            ActInfAuditService.getInstance().insertActiveInfo(tmpVO, tlrno);
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            // 2)插入tbl_active_prd_conf,将临时表状态改为已同步
            ActInfAuditService.getInstance().insertProInfo(tmpVO.getActiveNo(), tlrno);
            ActInfAuditService.getInstance().updateProSync(tmpVO.getActiveNo(),tlrno);
            // 3)插入tbl_active_cycle_conf,将临时表状态改为已同步
            if ("1".equals(tmpVO.getCycleFlg())) {//如果是周期活动就插
                ActInfAuditService.getInstance().insertCycC(tmpVO.getActiveNo(), tlrno);
                ActInfAuditService.getInstance().updateCycSync(tmpVO.getActiveNo(),tlrno);
            }
            // 4)如果活动状态为21-红包立减,插入tbl_active_redbag_conf,将临时表状态改为已同步
            if (ActiveInfConstants.ACTIVE_TYPE_21.equals(tmpVO.getActiveType())) {
                ActInfAuditService.getInstance().insertRedConf(tmpVO.getActiveNo());
                ActInfAuditService.getInstance().updateRedSync(tmpVO.getActiveNo());
            }
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
            
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
    }
    
    /**
     * 新增被拒绝
     * @param tmpVO
     * @param auditId
     * @param auditView
     * @param roleId
     * @param tlrno
     * @throws SnowException
     */
    public void addMethodRef(String auditId,String auditView,String roleId,String tlrno) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
            ActInfAuditService.getInstance().updateAuditInfoRefuse(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 02新增被拒绝,并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updAuditFlagRef(auditId, tlrno);
        }
    
    
    
    
    /**
     * 修改通过
     * @param tmpVO
     * @param auditId
     * @param auditView
     * @param roleId
     * @param tlrno
     * @param map
     * @throws SnowException
     */
    public void updateMethod(ActiveInfTmpVO2 tmpVO,String auditId,String auditView,String roleId,String tlrno,Map<String, Object> map) throws SnowException{
     // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = ActInfAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActInfAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId, tlrno);
            // 4. 根据审核过的信息来修改正式表(正式表此处发生变化)
            // 1)修改tbl_active_inf,将临时表状态改为已同步
            ActInfAuditService.getInstance().updateActReal(tmpVO,tlrno);
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            // 2)修改tbl_active_prd_conf,将临时表状态改为已同步
            ActInfAuditService.getInstance().updateProReal(tmpVO.getActiveNo(), tlrno);
            ActInfAuditService.getInstance().updateProSync(tmpVO.getActiveNo(),tlrno);
            // 3)修改tbl_active_cycle_conf,将临时表状态改为已同步
            if ("1".equals(tmpVO.getCycleFlg())) {//如果是周期活动就修改
                ActInfAuditService.getInstance().updateCycReal(tmpVO.getActiveNo(), tlrno);
                ActInfAuditService.getInstance().updateCycSync(tmpVO.getActiveNo(),tlrno);
            }
            // 4)如果活动状态为21-红包立减,修改tbl_active_redbag_conf,将临时表状态改为已同步
            if (ActiveInfConstants.ACTIVE_TYPE_21.equals(tmpVO.getActiveType())) {
                ActInfAuditService.getInstance().updateRedConf(tmpVO.getActiveNo());
                ActInfAuditService.getInstance().updateRedSync(tmpVO.getActiveNo());
            }
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
        
    }
    
    /**
     * 修改拒绝
     * @param tmpVO
     * @param auditId
     * @param auditView
     * @param roleId
     * @param tlrno
     * @throws SnowException
     */
    public void updateMethodRef(ActiveInfTmpVO2 tmpVO,String auditId,String auditView,String roleId,String tlrno) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
            ActInfAuditService.getInstance().updateAuditInfoRefuse(auditId);
            // 3.回滚活动信息表,置为已同步
            ActInfAuditService.getInstance().backActInfo(tlrno, tmpVO.getActiveNo());
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            // 4.回滚活动产品配置表,置为已同步
            ActInfAuditService.getInstance().backActPro(tlrno, tmpVO.getActiveNo());
            ActInfAuditService.getInstance().updateProSync(tmpVO.getActiveNo(),tlrno);
            // 5.回滚活动周期表,置为已同步
            ActInfAuditService.getInstance().backActCyc(tlrno, tmpVO.getActiveNo());
            ActInfAuditService.getInstance().updateCycSync(tmpVO.getActiveNo(),tlrno);
            // 6.如果活动状态为21-红包立减,则回滚红包配置信息表,置为已同步
            if (ActiveInfConstants.ACTIVE_TYPE_21.equals(tmpVO.getActiveType())) {
                ActInfAuditService.getInstance().backActRed( tmpVO.getActiveNo());
                ActInfAuditService.getInstance().updateRedSync(tmpVO.getActiveNo());
            }
    }
    
    
    
    
    
    
    
    
    
    /**
     * 暂停与恢复
     * @param tmpVO
     * @param tlrno
     * @param auditId
     * @param roleId
     * @param auditView
     * @param map
     * @throws SnowException
     */
    public void StopRecMethod(ActiveInfTmpVO2 tmpVO,String tlrno,String auditId,String roleId,String auditView,
            Map<String, Object> map) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = ActInfAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActInfAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId, tlrno);
            // 4. 修改正式表状态
            ActInfAuditService.getInstance().setStopRec(tmpVO.getActiveStat(), tmpVO.getActiveNo(), tlrno);
            //将临时表状态改为已同步
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            //----------------------------------活动暂停通过，则将商户信息置为退出状态-------------------------------//
            if (ActiveInfConstants.ACTIVE_STATE_PAUSE.equals(tmpVO.getActiveStat())) {
                ActInfAuditService.getInstance().quite(tmpVO.getActiveNo(),tlrno);
            }
            
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
    }
    
    /**
     * 暂停与恢复拒绝
     * @param tmpVO
     * @param tlrno
     * @param auditId
     * @param roleId
     * @param auditView
     * @throws SnowException
     */
    public void StopRecMethodRef(ActiveInfTmpVO2 tmpVO,String tlrno,String auditId,String roleId,String auditView
         ) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
            ActInfAuditService.getInstance().updateAuditInfoRefuse(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为  00正常，活动状态改为(tmpVO.getActiveStat())并将同步状态改为同步
            String activeStat = "";
            if (ActiveInfConstants.ACTIVE_STATE_ONGOING.equals(tmpVO.getActiveStat())) {//活动中变为暂停
                activeStat = ActiveInfConstants.ACTIVE_STATE_PAUSE;
                
            }else if (ActiveInfConstants.ACTIVE_STATE_PAUSE.equals(tmpVO.getActiveStat())) {//活动中变为活动中
                activeStat = ActiveInfConstants.ACTIVE_STATE_ONGOING;
            }
            ActInfAuditService.getInstance().updateSRActInfo(auditId,activeStat, tlrno);
            
    }
    
    
    
    /**
     * 活动配置方法通过
     * @param auditId
     * @param roleId
     * @param tlrno
     * @param auditView
     * @param map
     * @throws SnowException
     */
    public void actConf(ActiveInfTmpVO2 tmpVO,String auditId,String roleId,String tlrno,String auditView,Map<String, Object> map) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = ActInfAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActInfAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId, tlrno);
            // 4. 修改正式表状态
            ActInfAuditService.getInstance().modifyActMethodConf(tmpVO.getActiveNo(), tlrno);
            //设置活动配置表同步状态为已同步
            ActInfAuditService.getInstance().actMCSync(tmpVO.getActiveNo());
            // 5.将临时表状态改为已同步
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        
    }
    } 
    
    /**
     * 活动配置拒绝
     * @param tmpVO
     * @param auditId
     * @param roleId
     * @param tlrno
     * @param auditView
     * @throws SnowException
     */
    public void actConfRef(ActiveInfTmpVO2 tmpVO,String auditId,String roleId,String tlrno,String auditView) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
            ActInfAuditService.getInstance().updateAuditInfoRefuse(auditId);
            // 3.将活动信息表恢复为正常,未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId,tlrno);
            // 4.回滚活动方法配置表,置为已同步
            ActInfAuditService.getInstance().backActMethodC(tlrno, tmpVO.getActiveNo());
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
                
    } 
    
    /**
     * 活动参与商户通过
     * @param auditId
     * @param roleId
     * @param tlrno
     * @param auditView
     * @param map
     * @throws SnowException
     */
    public void actMcht(ActiveInfTmpVO2 tmpVO,String auditId,String roleId,String tlrno,String auditView,
            Map<String, Object> map) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = ActInfAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActInfAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId, tlrno);
            // 4. 修改正式表状态
            ActInfAuditService.getInstance().modifyActMcht(tmpVO.getActiveNo(), tlrno);
            // 5.将临时表状态改为已同步
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
            ActInfAuditService.getInstance().actMchtSync(tlrno,tmpVO.getActiveNo());
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActInfAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        
    }
    
    }
    
    /**
     * 活动参与商户拒绝
     * @param tmpVO
     * @param auditId
     * @param roleId
     * @param tlrno
     * @param auditView
     * @throws SnowException
     */
    public void actMchtRef(ActiveInfTmpVO2 tmpVO,String auditId,String roleId,String tlrno,String auditView
            ) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActInfAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
            // 1.记录此次审核记录，更新审核记录表
            ActInfAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
            ActInfAuditService.getInstance().updateAuditInfoRefuse(auditId);
            // 3.将活动信息表恢复为正常,未同步
            ActInfAuditService.getInstance().updateActiveInfTmpVO(auditId,tlrno);
            // 4.回滚参与商户表,置为已同步
            ActInfAuditService.getInstance().backActMcht(tlrno, tmpVO.getActiveNo());
            ActInfAuditService.getInstance().updateActSync(tmpVO.getActiveNo(),tlrno);
        
    }
    
}
