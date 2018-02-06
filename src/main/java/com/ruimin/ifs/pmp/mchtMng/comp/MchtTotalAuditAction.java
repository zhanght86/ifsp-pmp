/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.comp 
 * FileName: MchtTotalAuditAction.java
 * Author:   LJY
 * Date:     2017年11月16日 下午4:17:12
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年11月16日下午4:17:12                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.service.ContractAuditService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtContractService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtInPutService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngAuditService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtTotalAuditService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtTxnLimitService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.CommonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

/**
 * 名称：商户信息与合同审核(进件)Action<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年11月16日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */

@ActionResource
@SnowDoc(desc = "商户信息与合同审核相关操作")
public class MchtTotalAuditAction {
    static Logger log = SnowLog.getLogger(QrcMngAction.class);
    public final static String RESP_CODE_1020 = "1020";
    
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "通过")
    public synchronized Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
         //1.调用审核(由事务控制) 
         Map<String, Object> map = pass0(updateMap);
         //2.获取进件标志
         String whetherIn = String.valueOf(map.get("whetherIn"));
         //--01-进件   02-不进件
         if ("01".equals(whetherIn)) {
             //调用调度系统异步发起商户进件
             MchtInPutService.getInstance().mchtIn(String.valueOf(map.get("mchtId")),"接口进件");
         }
         //3.调用接口通知审核结果
         MchtInPutService.getInstance().notice(String.valueOf(map.get("mchtId")));
         return map;
        
    }
    
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "拒绝")
    public synchronized void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        //1.调用审核(由事务控制)
        Map<String, Object> map = refuse0(updateMap);
       
        //2.调用接口通知审核结果
        MchtInPutService.getInstance().notice(String.valueOf(map.get("mchtId")));
       
    }
    
    
    
    
    public Map<String, Object> pass0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        // 用于返回页面提示信息使用
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取商户信息
        UpdateRequestBean voBean = updateMap.get("mchtMngInfo");
        //获取合同信息
        UpdateRequestBean voBean1 = updateMap.get("mchtContractInfo");
        // 商户临时实体对象
        PbsMchtBaseInfo pbsMchtBaseInfo = new PbsMchtBaseInfo();
        while (voBean.hasNext()) {
            DataObjectUtils.map2bean(voBean.next(), pbsMchtBaseInfo);
        }
        //合同临时对象
        MchtContractVO mchtVo = new MchtContractVO();
        while (voBean1.hasNext()) {
            DataObjectUtils.map2bean(voBean1.next(), mchtVo);
        }
        // 获取公共方法实体对象
        AuditCommonAction auditCommonAction = new AuditCommonAction();
        // 获取审核意见
        String auditView = "审核通过\r\n"+voBean.getParameter("auditView");
        // 根据商户号查询，图片信息
        List<PbsMchtPicInfo> list = MchtMngAuditService.getInstance().selectPbsMchtPicInfo(pbsMchtBaseInfo.getMchtId());
        // 得到商户状态
        String mchtStat = pbsMchtBaseInfo.getMchtStat();
        // 获取商户审核信息编号
        String auditId = pbsMchtBaseInfo.getAuditId();
        //
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据操作员编号，获取角色编号
        String roleId = auditCommonAction.getRoleIdByTlrno(tlrno).toString();
        /******************add  start	20180105*******************/
//        pbsMchtBaseInfo.setMchtRegAmt(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getMchtRegAmt()));// 注册资金
//        pbsMchtBaseInfo.setRecvDeposit(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getRecvDeposit()));// 应收保证金
//        pbsMchtBaseInfo.setPaidDeposit(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getPaidDeposit()));// 实收保证金
        MchtMngService.getInstance().updMchtField(pbsMchtBaseInfo.getMchtId(),pbsMchtBaseInfo.getRiskLevel());
        
        MchtContractService.getInstance().updContField(mchtVo.getMchtId(),mchtVo.getSetlAcctAreaCode());
        /******************add  start	20180105*******************/
        //********************是否进件标志       20180110**************************
        String whetherIn = voBean.getParameter("whetherIn");
        map.put("whetherIn", whetherIn);
        //****************************************************************
        // 获取审核之前商户合同关联产品正式表中签约的交易类型
        ContractAuditAction con = new ContractAuditAction();
        String beforeDeal = con.getMchtDealTypeString(mchtVo.getMchtId());
        // 根据审核信息编号获取审核信息
        PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        
        if (MchtMngConstants.MCHT_STAT_03.equals(mchtStat)){//新增审核通过
            addMethod(auditCommonAction, roleId, pmpAuditInfo, auditId,
                    tlrno, list, pbsMchtBaseInfo, mchtVo,auditView,
                    map, beforeDeal);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[商户审核]--新增审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
            
        }
        
        if (MchtMngConstants.MCHT_STAT_04.equals(mchtStat)) {//修改审核通过
            
            uptMethod(auditCommonAction, roleId,  pmpAuditInfo, auditId,
                    tlrno,  list,  pbsMchtBaseInfo, mchtVo,  auditView,
                    map, beforeDeal);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[商户审核]--修改审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
            
        }
        map.put("mchtId", pbsMchtBaseInfo.getMchtId());
        return map;
    }
    
    
    
    public Map<String, Object> refuse0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取商户信息
        UpdateRequestBean voBean = updateMap.get("mchtMngInfo");
        //获取合同信息
        UpdateRequestBean voBean1 = updateMap.get("mchtContractInfo");
        
        // 获取拒绝原因
        String auditView ="审核拒绝\r\n"+voBean.getParameter("auditView");
        // 商户临时实体对象
        PbsMchtBaseInfo pbsMchtBaseInfo = new PbsMchtBaseInfo();
        while (voBean.hasNext()) {
            DataObjectUtils.map2bean(voBean.next(), pbsMchtBaseInfo);
        }
        //合同临时对象
        MchtContractVO mchtVo = new MchtContractVO();
        while (voBean1.hasNext()) {
            DataObjectUtils.map2bean(voBean1.next(), mchtVo);
        }
        // 获取公共方法实体对象
        AuditCommonAction auditCommonAction = new AuditCommonAction();
        // 根据商户号查询，图片信息
        List<PbsMchtPicInfo> list = MchtMngAuditService.getInstance().selectPbsMchtPicInfo(pbsMchtBaseInfo.getMchtId());
        // 得到商户状态
        String mchtStat = pbsMchtBaseInfo.getMchtStat();
        // 获取商户审核信息编号
        String auditId = pbsMchtBaseInfo.getAuditId();
        
        /******************add  start	20180105*******************/
//        pbsMchtBaseInfo.setMchtRegAmt(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getMchtRegAmt()));// 注册资金
//        pbsMchtBaseInfo.setRecvDeposit(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getRecvDeposit()));// 应收保证金
//        pbsMchtBaseInfo.setPaidDeposit(CommonUtil.transYuanToFen(pbsMchtBaseInfo.getPaidDeposit()));// 实收保证金
        MchtMngService.getInstance().updMchtField(pbsMchtBaseInfo.getMchtId(),pbsMchtBaseInfo.getRiskLevel());
        
        MchtContractService.getInstance().updContField(mchtVo.getMchtId(),mchtVo.getSetlAcctAreaCode());
        /******************add  start	20180105*******************/
        
        //
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据操作员编号，获取角色编号
        String roleId = auditCommonAction.getRoleIdByTlrno(tlrno).toString();
        // 根据审核信息编号获取审核信息
        PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
        if (pmpAuditInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 根据状态，判断是新增，修改,对用不同的操作
        if (MchtMngConstants.MCHT_STAT_04.equals(mchtStat)) {//修改审核拒绝
            uptMethodRefuse(auditCommonAction, roleId,pmpAuditInfo,
                    auditId, tlrno,  pbsMchtBaseInfo,mchtVo, list,  auditView,
                    mchtStat);
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[商户审核]--修改审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
        }
        if (MchtMngConstants.MCHT_STAT_03.equals(mchtStat)) {//新增拒绝
            // 新增被拒绝时，审核流程终止
            // 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
            PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(Integer.parseInt(roleId), auditId);
            if (pmpAuditStepInfo == null) {
                // 抛出异常
                SnowExceptionUtil.throwWarnException("获取审核流程步骤错误！");
            }
            // 1.记录此次审核记录，更新到审核记录表中
            auditCommonAction.updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                    auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝；解决原因
            auditCommonAction.updateAuditInfoRefuse(auditId);
            // 3.商户临时表数据中，商户状态MCHT_STAT，10 添加待提审
            MchtTotalAuditService.getInstance().updateMchtInfo(pbsMchtBaseInfo.getMchtId(),tlrno);
            // 4.合同信息临时表,合同状态12添加待提审
            MchtTotalAuditService.getInstance().updateMchtContract(pbsMchtBaseInfo.getMchtId(),tlrno);
            // 拒绝时，审核中止，新增数据留在临时表中
            
            /** 打印日志 */
            sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
                    sessionUserInfo.getBrno(), "[商户审核]--新增审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
        }
        map.put("mchtId", pbsMchtBaseInfo.getMchtId());
        return map;
    }
    
    
    /**
     * 新增审核通过
     * @param auditCommonAction
     * @param roleId
     * @param pmpAuditInfo
     * @param auditId
     * @param tlrno
     * @param list
     * @param pbsMchtBaseInfo
     * @param mchtVo
     * @param auditView
     * @param map
     * @param beforeDeal
     * @throws SnowException
     */
    public void addMethod(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo, String auditId,
            String tlrno, List<PbsMchtPicInfo> list, PbsMchtBaseInfo pbsMchtBaseInfo,MchtContractVO mchtVo, String auditView,
            Map<String, Object> map,String beforeDeal) throws SnowException{
     // 根据审核信息编号和角色编号查询，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(Integer.parseInt(roleId), auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前角色的审核步骤
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep((stepNo + 1), auditId);
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新审核记录表
            auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                    auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
            auditCommonAction.updateAuditInfoState(auditId);
     //-----------------------------------------商户信息部分---------------------------------------------------------------------------------//       
            // 3.商户临时表数据中，商户状态MCHT_STAT，00 正常；同步状态，SYNC_STATE 01：已变更未同步；
            MchtMngAuditService.getInstance().updateMchtInfoTmp(auditId, tlrno);
            // 4.把审核通过信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
            MchtMngAuditService.getInstance().insertMchtBaseInfo(pbsMchtBaseInfo, tlrno);
            // 5.临时表状态改为已同步
            MchtMngAuditService.getInstance().updateSyncState(auditId);
            // 6.把图片信息表数据插入到正式表中，有多条，循环操作
            MchtMngAuditService.getInstance().insertMchtPicInfo(list, tlrno);
            // 7.把商户信息辅表插入正式表
            MchtTotalAuditService.getInstance().insertMchtAssit(pbsMchtBaseInfo.getMchtId());
            
     //-----------------------------------------合同部分------------------------------------------------------------------------------------//       
            // 1.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
            ContractAuditService.getInstance().updateMchtInfoTmp(auditId);
            // 2.临时表的数据插入到正式表中
            // [1].把审核通过信息插入到正式表中，同时把临时表的同步状态，SYNC_STATE 改为 00：已同步；
            ContractAuditService.getInstance().insertContrBaseInfo(mchtVo, tlrno);
            // [2].将合同关联产品的信息插入到正式表中
            ContractAuditService.getInstance().insertContrRelPro(mchtVo, tlrno);
            // [3].将合同账户费率的信息插入到正式表中
            ContractAuditService.getInstance().insertContrAcctRate(mchtVo, tlrno);
            /*// [4].将交易限额的信息插入正式表中
            MchtTxnLimitService.getInstance().insertMchtTxnLitByMchtId(mchtVo, tlrno);*/
            // 3.更新完数据，将临时表状态改为已同步
            ContractAuditService.getInstance().updateTempSyncState(auditId);
            // 4.调用通道接口请求接口
            MchtChnlRequestVO mchtInfo = new MchtChnlRequestVO();
            String applyWay = MchtChnlRequestConstants.APPLY_WAY_PER;
            String mchtId = mchtVo.getMchtId();
            String mchtName = ContractAuditService.getInstance().selectMchtNameById(mchtId);
            MchtChnlRequestAction mchtChnlRequestAction = new MchtChnlRequestAction();
            mchtInfo.setChlMerId(mchtId);
            mchtInfo.setChlMerName(mchtName);
            try {
                mchtChnlRequestAction.directApplyRequest(mchtInfo, applyWay);
                
            } catch (Exception se) {
                SnowExceptionUtil.throwErrorException(se.getMessage());
            }
            map.put("flag", "true");
           /* ---调用生成商户二维码图片接口
            * //审核完获取商户签约的交易类型
            ContractAuditAction con = new ContractAuditAction();
            String afterDeal = con.getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
            //根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
            try {
                    log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
                    log.info("beforeDeal:"+beforeDeal);
                    log.info("afterDeal:"+afterDeal);
                    con.modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
                } catch (Exception e) {
                    log.error("上次二维码异常:"+e.getMessage());
                    SnowExceptionUtil.throwWarnException("错误原因:"+e.getMessage());
                    SnowExceptionUtil.throwErrorException(e.getMessage());
                }*/
            
        }else {// 该下个审核人了
            // 1.记录此次审核记录，更新到审核记录表中
            auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                    auditView);
            map.put("flag", "false");
            
        }
    }
    
    
    /**
     * 修改审核通过
     * @param auditCommonAction
     * @param roleId
     * @param pmpAuditInfo
     * @param auditId
     * @param tlrno
     * @param list
     * @param pbsMchtBaseInfo
     * @param mchtVo
     * @param auditView
     * @param map
     * @param beforeDeal
     * @throws SnowException
     */
    public void uptMethod(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo, String auditId,
            String tlrno, List<PbsMchtPicInfo> list, PbsMchtBaseInfo pbsMchtBaseInfo,MchtContractVO mchtVo, String auditView,
            Map<String, Object> map,String beforeDeal) throws SnowException {
        // 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(Integer.parseInt(roleId), auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 得到当前角色的审核步骤
        Integer stepNo = pmpAuditStepInfo.getStepNo();
        // 根据审核流程编号和步骤，查询下个审核步骤
        PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep((stepNo + 1), auditId);
        
        if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
            // 1.记录此次审核记录，更新到审核记录表中
            auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                    auditView);
            // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
            auditCommonAction.updateAuditInfoState(auditId);
  //-----------------------------------------商户信息-------------------------------------------------------------          
            // 3.商户临时表数据中，商户状态MCHT_STAT，00 正常；同步状态，SYNC_STATE 01：已变更未同步；
            MchtMngAuditService.getInstance().updateMchtInfoTmp(auditId, tlrno);
            // 4.把审核通过信息,更改对应正式表，同时把同步状态，SYNC_STATE 00：已同步；
            // 查询商户信息正式表中数据
            PbsMchtBaseInfoReal pbsMchtBaseInfoReal = MchtMngAuditService.getInstance()
                    .selectMchtInfoTmpReal(pbsMchtBaseInfo.getMchtId());
            MchtMngAuditService.getInstance().updMcht(pbsMchtBaseInfo, pbsMchtBaseInfoReal, tlrno);
            // 5.临时表状态改为已同步
            MchtMngAuditService.getInstance().updateSyncState(auditId);
            // 6.对应图片分两种情况：1.临时表图片信息为空，则正式表原来可能不为空，也可能为空。2.临时表信息不为空，正式表为空则插入，非空则更新
            if (list == null || list.size() == 0) {// 临时表为空，修改通过，正式表中根据对应编号数据删除
                MchtMngAuditService.getInstance().deleteMchtPicInfo(pbsMchtBaseInfo);
            }
            if (list != null && list.size() != 0) {
                // 根据商户号，查询正式表中是否有图片信息
                PbsMchtPicInfoReal pbsMchtPicInfoReal = MchtMngAuditService.getInstance()
                        .selectMchtPicInfoByMchtId(pbsMchtBaseInfo);
                if (pbsMchtPicInfoReal == null) {// 插入到正式表中,多条
                    MchtMngAuditService.getInstance().insertMchtPicInfo(list, tlrno);
                } else {// 更改正式表，多条
                    MchtMngAuditService.getInstance().updPic(list, tlrno);
                }
            }
            // 7.更新商户信息辅表
            MchtTotalAuditService.getInstance().updateMchtAssit(pbsMchtBaseInfo.getMchtId());
  //----------------------------------------合同部分-------------------------------------------------------------//          
            // 1.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
            ContractAuditService.getInstance().updateMchtInfoTmp(auditId);
            // 将正式表中的合同下的信息全都删除
            // [1].删除正式表中商户合同的基本信息的数据
            ContractAuditService.getInstance().delContrBaseInfo(mchtVo.getConId());
            // [2].删除正式表中商户产品的数据
            ContractAuditService.getInstance().delContrRelPro(mchtVo.getConId());
            // [3].删除正式表中商户账户费率的数据
            ContractAuditService.getInstance().delContrAcctRate(mchtVo.getConId());
            /*// [4].删除正式表中的交易限额
            MchtTxnLimitService.getInstance().delMchtTxnLitByMchtId(mchtVo.getMchtId());*/
            //从临时表取数据插入正式表
            // [1].将临时表中商户合同的基本信息插入到正式表中
            ContractAuditService.getInstance().insertContrBaseInfo(mchtVo, tlrno);
            // [2].将合同关联产品的信息插入到正式表中
            ContractAuditService.getInstance().insertContrRelPro(mchtVo, tlrno);
            // [3].将合同账户费率的信息插入到正式表中
            ContractAuditService.getInstance().insertContrAcctRate(mchtVo, tlrno);
            /*// [4].将交易限额临时表中的数据添加到正式表中
            MchtTxnLimitService.getInstance().insertMchtTxnLitByMchtId(mchtVo, tlrno);*/
            // 2.将临时表状态改为已同步
            ContractAuditService.getInstance().updateTempSyncState(auditId);
            map.put("flag", "true");
//            //审核完获取商户签约的交易类型
//            ContractAuditAction con = new ContractAuditAction();
//            String afterDeal = con.getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
//            //根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
//            try {
//                log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
//                log.info("beforeDeal:"+beforeDeal);
//                log.info("afterDeal:"+afterDeal);
//                con.modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
//            } catch (Exception e) {
//                log.error("上次二维码异常:"+e.getMessage());
//                SnowExceptionUtil.throwWarnException("错误原因:"+e.getMessage());
//                SnowExceptionUtil.throwErrorException(e.getMessage());
//            }
            
        }else {// 该下个审核人了
            // 1.记录此次审核记录，插入到审核记录表中
            auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                    auditView);
            map.put("flag", "false");
        }
        
    }
    
    /**
     * 修改审核拒绝
     * @param auditCommonAction
     * @param roleId
     * @param pmpAuditInfo
     * @param auditId
     * @param tlrno
     * @param pbsMchtBaseInfo
     * @param mchtVo
     * @param list
     * @param auditView
     * @param mchtStat
     * @throws SnowException
     */
    public void uptMethodRefuse(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo,
            String auditId, String tlrno, PbsMchtBaseInfo pbsMchtBaseInfo,MchtContractVO mchtVo, List<PbsMchtPicInfo> list, String auditView,
            String mchtStat) throws SnowException{
        // 修改被拒绝时，审核流程终止
        // 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
        PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(Integer.parseInt(roleId), auditId);
        if (pmpAuditStepInfo == null) {
            // 抛出异常
            SnowExceptionUtil.throwWarnException("获取审核信息错误！");
        }
        // 1.记录此次审核记录，更新到审核记录表中
        auditCommonAction.updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
                auditView);
        // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝；,审核意见
        auditCommonAction.updateAuditInfoRefuse(auditId);
        // 3.查询正式表数据，回滚到临时表中，此时包括商户信息表，及其图片信息表
        // 获取商户号
        String mchtId = pbsMchtBaseInfo.getMchtId();
        //---------------------------------商户信息部分--------------------------------------------------------------------//
        // 查询商户信息正式表中数据
        PbsMchtBaseInfoReal pbsMchtBaseInfoReal = MchtMngAuditService.getInstance().selectMchtInfoTmpReal(mchtId);
        // 正式表数据回滚到临时表中,当修改，冻结，恢复，注销，审核被拒绝时(审核编号不做回滚)
        MchtMngAuditService.getInstance().backMchtInfo(pbsMchtBaseInfo, pbsMchtBaseInfoReal, tlrno,auditId);
        // 回滚图片信息
        // 查询图片信息正式表中数据,可能有多条，仅仅修改时关系到图片信息表
        List<PbsMchtPicInfoReal> listPic = MchtMngAuditService.getInstance().selectMchtPicInfoReal(pbsMchtBaseInfo);
        // 正式表数据回滚到临时表中
        MchtMngAuditService.getInstance().backMchtPicInfo(list, listPic, tlrno);
        // 商户信息辅表回滚
        MchtTotalAuditService.getInstance().backMchtAssit(mchtId);
        //----------------------------------商户合同部分-------------------------------------------------------------------//
        // [1].删除临时表中商户合同的基本信息的数据
        ContractAuditService.getInstance().delContrBaseInfoTemp(mchtVo.getConId());
        // [2].删除临时表中商户产品的数据
        ContractAuditService.getInstance().delContrRelProTemp(mchtVo.getConId());
        // [3].删除临时表中商户账户费率的数据
        ContractAuditService.getInstance().delContrAcctRateTemp(mchtVo.getConId());
       /* // [4].删除临时表中的交易限额
        MchtTxnLimitService.getInstance().delMchtTxnLitTmpByMchtId(mchtVo.getMchtId());*/
        // 将正式表中的数据回滚到临时表中
        // [1].将正式表中的基础信息插入到临时表中(保留临时表的审核id)
        ContractAuditService.getInstance().insertContrBaseInfoTemp(mchtVo, tlrno,auditId);
        // [2].将正式表合同关联产品的信息插入到临时表中
        ContractAuditService.getInstance().insertContrRelProTemp(mchtVo, tlrno);
        // [3].将正式表合同账户费率的信息插入到临时表中
        ContractAuditService.getInstance().insertContrAcctRateTemp(mchtVo, tlrno);
       /* // [4].将正式表商户交易限额信息插入临时表
        MchtTxnLimitService.getInstance().insertMchtTxnLitTmpByMchtId(mchtVo, tlrno);*/
        // 临时表状态改为已同步
        ContractAuditService.getInstance().updateTempSyncState(auditId);
    }
    
}
