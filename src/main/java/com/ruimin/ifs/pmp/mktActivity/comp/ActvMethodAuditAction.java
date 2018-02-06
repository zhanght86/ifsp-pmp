/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.comp 
 * FileName: ActvMethodAuditAction.java
 * Author:   LJY
 * Date:     2017年10月22日 下午10:21:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月22日下午10:21:22                     1.0                  
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
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfVO;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActMethodAuditService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodSectionTmpService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodTmpService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月22日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@ActionResource
@SnowDoc(desc = "活动方法审核相关操作")
public class ActvMethodAuditAction extends SnowAction{
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "通过")
    public Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        Map<String, Object> map = new HashMap<String, Object>();
        UpdateRequestBean voBean = updateMap.get("methodInfo");
        //临时对象
        ActiveMethodInfTmpVO tmpVO = new ActiveMethodInfTmpVO();
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
        PmpAuditInfo pmpAuditInfo = ActMethodAuditService.getInstance().getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        //得到操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据状态,判断是新增,修改,删除对应不同的操作
        if (ActiveMethodConstants.AUDIT_FLAG_01.equals(auditFlag)) {
            addMethod(tmpVO, auditId, auditView, "11", tlrno, map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--新增审核通过:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
        if (ActiveMethodConstants.AUDIT_FLAG_03.equals(auditFlag)) {
            updMethod(auditId, "11", tlrno, auditView, tmpVO, map);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--修改审核通过:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
        if (ActiveMethodConstants.AUDIT_FLAG_04.equals(auditFlag)) {
            delMethod(auditId, tmpVO, map, "11", tlrno, auditView);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--删除审核通过:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
        return map;
    }
    
    
    
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "拒绝")
    public void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        //1.获取活动方法信息
        UpdateRequestBean voBean = updateMap.get("methodInfo");
        //2.建立临时对象
        ActiveMethodInfTmpVO tmpVO = new ActiveMethodInfTmpVO();
        if (voBean.hasNext()) {
            DataObjectUtils.map2bean(voBean.next(), tmpVO);
        }
        //3.根据对象得到审核标识状态和审核编号
        String auditId = tmpVO.getAuditId();
        String auditFlag = tmpVO.getAuditFlag();
        //4.获取审核意见
        String auditView = voBean.getParameter("auditView");
        //5.得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        //6.根据审核编号查询审核信息
        PmpAuditInfo pmpAuditInfo = ActMethodAuditService.getInstance().getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        //7.根据状态判断是新增,修改还是删除
        if (ActiveMethodConstants.AUDIT_FLAG_01.equals(auditFlag)) {
         // 新增被拒绝时，审核流程终止
         // 根据审核流程编号查询审核流程步骤表，得到审核步骤
            PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
            if (pmpAuditStepInfo == null) {
                // 抛出异常
                SnowExceptionUtil.throwWarnException("获取审核信息错误！");
            }
         // 1.记录此次审核记录，更新到审核记录表PMP_AUDIT_STEP_INFO,并将状态AUDIT_STATE置为 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), 11, tlrno, auditView);
         // 2.更改审核信息表状态，AUDIT_FLAG 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditInfoRefuse(auditId);
         // 3.更改临时表AUDIT_FLAG状态为 02-新增被拒绝
            ActMethodAuditService.getInstance().updAuditFlagRef(tmpVO.getMethodNo(),tlrno);
         // 拒绝时，审核中止，新增数据留在临时表中
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--新增审核拒绝:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
        if (ActiveMethodConstants.AUDIT_FLAG_03.equals(auditFlag)) {
         // 根据审核流程编号查询审核流程步骤表，得到审核步骤
            PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
            if (pmpAuditStepInfo == null) {
                // 抛出异常
                SnowExceptionUtil.throwWarnException("获取审核信息错误！");
            }
         // 1.记录此次审核记录，更新到审核记录表PMP_AUDIT_STEP_INFO,并将状态AUDIT_STATE置为 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), 11, tlrno, auditView);
         // 2.更改审核信息表状态，AUDIT_FLAG 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditInfoRefuse(auditId);
         // 3.查询正式表的信息
            ActiveMethodInfVO VO = ActMethodAuditService.getInstance().selectMethodInfoReal(tmpVO.getMethodNo());
         // 4.更改临时表AUDIT_FLAG状态为 00-正常,并回滚数据
            // 1)回滚活动信息表
            ActMethodAuditService.getInstance().backMethodInfo(tmpVO, VO, tlrno);
            // 2)回滚分段信息表
            ActMethodAuditService.getInstance().backSectionInfo(tmpVO.getMethodNo(), tlrno);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--修改审核拒绝:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
        if (ActiveMethodConstants.AUDIT_FLAG_04.equals(auditFlag)) {
         // 根据审核流程编号查询审核流程步骤表，得到审核步骤
            PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
            if (pmpAuditStepInfo == null) {
                // 抛出异常
                SnowExceptionUtil.throwWarnException("获取审核信息错误！");
            }
         // 1.记录此次审核记录，更新到审核记录表PMP_AUDIT_STEP_INFO,并将状态AUDIT_STATE置为 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), 11, tlrno, auditView);
         // 2.更改审核信息表状态，AUDIT_FLAG 02-审核拒绝
            ActMethodAuditService.getInstance().updateAuditInfoRefuse(auditId);
         // 3.更改临时表AUDIT_FLAG状态为 00-正常
            ActMethodAuditService.getInstance().updMethodInfNormal(auditId, tlrno);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[活动方法审核]--删除审核拒绝:活动方法编号[methodNo]=" + tmpVO.getMethodNo() });
        }
    }
    
    /**
     * 新增通过
     * @param ifsCtCdTmp
     * @param auditId
     * @param auditView
     * @param roleId
     * @param tlrno
     * @param map
     * @throws SnowException
     */
    public void addMethod(ActiveMethodInfTmpVO tmpVO,String auditId,String auditView,String roleId,String tlrno,
            Map<String, Object> map) throws SnowException{
        // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        
        PmpAuditStepInfo pmpAuditProcStepNo = ActMethodAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
         // 1.记录此次审核记录，更新审核记录表
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
         // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActMethodAuditService.getInstance().updateAuditInfoState(auditId);
         // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActMethodAuditService.getInstance().updateActiveMethodInfTmpVO(auditId, tlrno);
         // 4.把审核通过信息插入到正式表中(正式表此处发生变化)
            // 1)插入方法信息表 
            ActMethodAuditService.getInstance().insertMethodInfo(tmpVO, tlrno);
            // 2)插入方法分段信息表
            ActMethodAuditService.getInstance().insertSectionInfo(tmpVO.getMethodNo(),tlrno);
            //5.把临时表状态改为已同步
            ActMethodAuditService.getInstance().updMSyncFlag(tmpVO.getMethodNo());
            ActMethodAuditService.getInstance().updSSyncFlag(tmpVO.getMethodNo());
            
         //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
        
    }
    
    /**
     * 修改通过
     * @param auditId
     * @param roleId
     * @param tlrno
     * @param auditView
     * @param ifsCtCdTmp
     * @param map
     * @throws SnowException
     */
    public void updMethod(String auditId,String roleId,String tlrno,String auditView,ActiveMethodInfTmpVO tmpVO, 
            Map<String, Object> map) throws SnowException{
     // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        
        PmpAuditStepInfo pmpAuditProcStepNo = ActMethodAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActMethodAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中,把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActMethodAuditService.getInstance().updateActiveMethodInfTmpVO(auditId, tlrno);
            // 4. 根据审核过的信息来修改正式表(正式表此处发生变化)
            //1)方法信息表更新
            ActMethodAuditService.getInstance().updMethodInfo(tmpVO.getMethodNo(), tlrno);
            //2)分段信息表更新
            ActMethodAuditService.getInstance().updSectionInfo(tmpVO.getMethodNo(),tlrno);
            //5.把临时表状态改为已同步
            ActMethodAuditService.getInstance().updMSyncFlag(tmpVO.getMethodNo());
            ActMethodAuditService.getInstance().updSSyncFlag(tmpVO.getMethodNo());
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
        
    }
    
    /**
     * 删除通过
     * @param auditId
     * @param ifsCtCdTmp
     * @param map
     * @param roleId
     * @param tlrno
     * @param auditView
     * @throws SnowException
     */
    public void delMethod(String auditId,ActiveMethodInfTmpVO tmpVO ,Map<String, Object> map,String roleId,String tlrno,String auditView) throws SnowException{
     // 根据审核信息编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = ActMethodAuditService.getInstance().getAuditStepInfo(auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前审核步骤编号
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        
        PmpAuditStepInfo pmpAuditProcStepNo = ActMethodAuditService.getInstance().getNextStep((stepNo+1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
            ActMethodAuditService.getInstance().updateAuditInfoState(auditId);
            // 3.临时表数据中
            // 1)把审核标识状态AUDIT_FLAG改为 00正常并将同步状态改为未同步：SYNC_FLAG 00-已变更未同步
            ActMethodAuditService.getInstance().updateActiveMethodInfTmpVO(auditId, tlrno);
            // 2)删除方法下所有的分段信息(将DATA_STAT置为无效-0)
            ActvMethodSectionTmpService.getInstance().deleteSectionByMethodNo(tmpVO, tlrno);
            // 3)删除活动方法信息(将METHOD_STAT置为删除-01)
            ActvMethodTmpService.getInstance().deleteMethodByNo(tmpVO, tlrno);
            // 4. 根据审核过的信息来修改正式表(正式表此处发生变化)
            //1)方法信息表更新
            ActMethodAuditService.getInstance().updMethodInfo(tmpVO.getMethodNo(), tlrno);
            //2)分段信息表更新
            ActMethodAuditService.getInstance().updSectionInfo(tmpVO.getMethodNo(),tlrno);
            //5.把临时表状态改为已同步
            ActMethodAuditService.getInstance().updMSyncFlag(tmpVO.getMethodNo());
            ActMethodAuditService.getInstance().updSSyncFlag(tmpVO.getMethodNo());
            //设置审核步骤标识位传给jsp,true表示审核流程结束了,false表示还有审核流程
            map.put("flag", "true");
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            ActMethodAuditService.getInstance().updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno, auditView);
            map.put("flag", "false");
        }
    }
}
