package com.ruimin.ifs.pmp.mchtMng.comp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.mchtMng.common.constants.AuditConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtQrcCodeConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.QrcBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.ContractAuditService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtContractService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtInPutService;
import com.ruimin.ifs.pmp.mchtMng.process.service.QrcMngService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

@ActionResource
@SnowDoc(desc = "商户合同审核")
public class ContractAuditAction extends SnowAction {
	static Logger log = SnowLog.getLogger(QrcMngAction.class);
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核通过")
	public synchronized Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		//1.调用审核(由事务控制) 
		Map<String, Object> map = pass0(updateMap);
		//2.获取进件标志
        String whetherIn = String.valueOf(map.get("whetherIn"));
        //--01-进件   02-不进件
        if ("01".equals(whetherIn)) {
            //查询临时表商户状态是否是正常的,如正常则调用调度系统异步发起商户进件
            List<Object> mchtStat = MchtInPutService.getInstance().getMchtInf(String.valueOf(map.get("mchtId")));
            if (IfspDataVerifyUtil.isNotEmptyList(mchtStat)) {
                MchtInPutService.getInstance().mchtIn(String.valueOf(map.get("mchtId")),"合同");
            }
        }
		return map;
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "审核拒绝")
    public synchronized Map<String, Object> refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
         //调用审核(由事务控制)
        Map<String, Object> map = refuse0(updateMap);
        return map;
    }
	
	
	public synchronized Map<String, Object> pass0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
	    // 获取商户信息
	    UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");
	    // 最先为返回值做准备
	    Map<String, Object> map = new HashMap<String, Object>();
	    // 审核通用的action
	    AuditCommonAction auditCommonAction = new AuditCommonAction();
	    /** 导入实体类 */
	    String auditView = reqBean.getParameter("auditView");
	    MchtContractVO mchtVo = new MchtContractVO();
	    while (reqBean.hasNext()) {
	        DataObjectUtils.map2bean(reqBean.next(), mchtVo);
	    }
	    //获取审核之前商户合同关联产品正式表中签约的交易类型
	    String beforeDeal = getMchtDealTypeString(mchtVo.getMchtId());
	    //System.out.println("beforeDeal:"+beforeDeal);
	    /* 获取合同的状态 */
	    String conState = mchtVo.getConState();
	    /* 获取审核流程编号 */
	    String auditId = mchtVo.getAuditId();
	    /* 获取操作员的编号 */
	    String tlrno = SessionUserInfo.getSessionUserInfo().getTlrno();
	    /* 获取操作员的角色编号 */
	    int roleId = auditCommonAction.getRoleIdByTlrno(tlrno);
	    //********************是否进件标志       20180110**************************
        String whetherIn = reqBean.getParameter("whetherIn");
        map.put("whetherIn", whetherIn);
        //****************************************************************
	    // 根据审核信息编号获取审核信息实体类
	    PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
	    // 如果没有审核信息，则报异常
	    if (pmpAuditInfo == null) {
	        SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
	    }
	    if (MchtContractConstants.MCHT_STAT_04.equals(conState)) {
	        // 新增待审核通过
	        try {
	            addAduitContr(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, auditView, mchtVo, map,beforeDeal);
	            // -----------------20180111   修改辅表结算账户类型和结算账户户名---------------
	            ContractAuditService.getInstance().updPbsassit(mchtVo.getMchtId(),mchtVo.getAccountType(),mchtVo.getSetlAcctName());
	        } catch (Exception se) {
	            SnowExceptionUtil.throwErrorException(se.getMessage());
	            SnowExceptionUtil.throwWarnException("错误原因:"+se);
	        }
	    } else if (MchtContractConstants.MCHT_STAT_06.equals(conState)) {
	        // 修改待审核通过
	        updateAduitContr(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, auditView, mchtVo, map,beforeDeal);
	        // -----------------20180111   修改辅表结算账户类型和结算账户户名---------------
	        ContractAuditService.getInstance().updPbsassit(mchtVo.getMchtId(),mchtVo.getAccountType(),mchtVo.getSetlAcctName());
	       
	    } else if (MchtContractConstants.MCHT_STAT_08.equals(conState)) {
	        // 中止待审核通过
	        offAduitContr(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, auditView, mchtVo, map,beforeDeal);
	    } else if (MchtContractConstants.MCHT_STAT_10.equals(conState)) {
	        // 恢复待审核通过
	        onAduitContr(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, auditView, mchtVo, map,beforeDeal);
	    }
	    map.put("mchtId", mchtVo.getMchtId());
	    return map;
	}
	
	
	public synchronized Map<String, Object> refuse0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
	    // 获取商户合同信息
	    UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");
	    String auditView = reqBean.getParameter("auditView");
	    // 审核通用的action
	    AuditCommonAction auditCommonAction = new AuditCommonAction();
	    // 最先为返回值做准备
	    Map<String, Object> map = new HashMap<String, Object>();
	    /** 导入实体类 */
	    MchtContractVO mchtVo = new MchtContractVO();
	    while (reqBean.hasNext()) {
	        DataObjectUtils.map2bean(reqBean.next(), mchtVo);
	    }
	    /* 获取审核流程编号 */
	    String auditId = mchtVo.getAuditId();
	    /* 获取操作员的编号 */
	    String tlrno = SessionUserInfo.getSessionUserInfo().getTlrno();
	    /* 获取操作员的角色编号 */
	    int roleId = auditCommonAction.getRoleIdByTlrno(tlrno);
	    // 根据审核信息编号获取审核信息实体类
	    PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
	    if (pmpAuditInfo == null) {
	        SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
	    } // 审核被拒绝方法
	    auditContrRefuse(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, auditView, mchtVo);
	    return map;
	}

	// 新增待审核同意
	public void addAduitContr(AuditCommonAction auditCommonAction, int roleId, PmpAuditInfo pmpAuditInfo,
			String auditId, String tlrno, String auditView, MchtContractVO mchtVo, Map<String, Object> map,String beforeDeal
			)
			throws SnowException, Exception {
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(roleId, pmpAuditInfo.getAuditId());
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 得到当前角色的审核步骤
		int stepNo = pmpAuditStepInfo.getStepNo();
		// 根据审核流程编号和步骤，查询下个审核步骤
		PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep(stepNo + 1, pmpAuditInfo.getAuditId());
		if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
			// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
			auditCommonAction.updateAuditInfoState(auditId);
			// 3.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
			ContractAuditService.getInstance().updateMchtInfoTmp(auditId);
			// 临时表的数据插入到正式表中
			// 4.把审核通过信息插入到正式表中，同时把临时表的同步状态，SYNC_STATE 改为 00：已同步；
			ContractAuditService.getInstance().insertContrBaseInfo(mchtVo, tlrno);
			// 5.将临时表中的图片信息插入到正式表中
			ContractAuditService.getInstance().insertContrPicInfo(mchtVo, tlrno);
			// 6.将合同关联产品的信息插入到正式表中
			ContractAuditService.getInstance().insertContrRelPro(mchtVo, tlrno);
			// 7.将合同账户费率的信息插入到正式表中
			ContractAuditService.getInstance().insertContrAcctRate(mchtVo, tlrno);
			//----2017年04-01修改---隐藏单笔交易限额和日交易限额
			// 将临时表的数据插入到正式表中
			//MchtTxnLimitService.getInstance().insertMchtTxnLitByMchtId(mchtVo, tlrno);
			// 8.更新完数据，将临时表状态改为已同步
			ContractAuditService.getInstance().updateTempSyncState(auditId);
			// 调用通道接口请求接口
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
			map.put("flag", "yes");
			
			//审核完获取商户签约的交易类型
			String afterDeal = getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
					//System.out.println("afterDeal"+afterDeal);
			//根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
			try {
					log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
					log.info("beforeDeal:"+beforeDeal);
					log.info("afterDeal:"+afterDeal);
					modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
				} catch (Exception e) {
					log.error("上次二维码异常:"+e.getMessage());
					SnowExceptionUtil.throwWarnException("错误原因:"+e.getMessage());
					SnowExceptionUtil.throwErrorException(e.getMessage());
				}
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--新增待审核通过:合同编号[conId]=" + mchtVo.getConId() });
		} else {// 该下个审核人了
				// 9.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			map.put("flag", "false");
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--新增待审核此步通过:合同编号[conId]=" + mchtVo.getConId() });
		}
	}

	// 修改待审核同意
	public void updateAduitContr(AuditCommonAction auditCommonAction, int roleId, PmpAuditInfo pmpAuditInfo,
			String auditId, String tlrno, String auditView, MchtContractVO mchtVo, Map<String, Object> map
			,String beforeDeal)
			throws SnowException {
		log.debug("进入修改待审核方法中,传入的beforeDeal为："+beforeDeal);
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(roleId, pmpAuditInfo.getAuditId());
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 得到当前角色的审核步骤
		int stepNo = pmpAuditStepInfo.getStepNo();
		// 根据审核流程编号和步骤，查询下个审核步骤
		PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep(stepNo + 1, pmpAuditInfo.getAuditId());
		if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
			// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
			auditCommonAction.updateAuditInfoState(auditId);
			// 3.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
			ContractAuditService.getInstance().updateMchtInfoTmp(auditId);
			// 将正式表中的合同下的信息全都删除
			// 4.删除正式表中商户合同的基本信息的数据
			ContractAuditService.getInstance().delContrBaseInfo(mchtVo.getConId());
			// 5.删除正式表中商户合同的下图片基本信息的数据
			ContractAuditService.getInstance().delContrPicInfo(mchtVo.getConId());
			// 6.删除正式表中商户产品的数据
			ContractAuditService.getInstance().delContrRelPro(mchtVo.getConId());
			// 7.删除正式表中商户账户费率的数据
			ContractAuditService.getInstance().delContrAcctRate(mchtVo.getConId());
			// 删除正式表中的交易限额
			//MchtTxnLimitService.getInstance().delMchtTxnLitByMchtId(mchtVo.getMchtId());
			//----2017年04-01修改---隐藏单笔交易限额和日交易限额
			// 将临时表中的数据添加到正式表中
			//MchtTxnLimitService.getInstance().insertMchtTxnLitByMchtId(mchtVo, tlrno);
			// 将临时表中的数据插入到正式表中
			// 8.将临时表中商户合同的基本信息插入到正式表中
			ContractAuditService.getInstance().insertContrBaseInfo(mchtVo, tlrno);
			// 9.将临时表中的图片信息插入到正式表中
			ContractAuditService.getInstance().insertContrPicInfo(mchtVo, tlrno);
			// 10.将合同关联产品的信息插入到正式表中
			ContractAuditService.getInstance().insertContrRelPro(mchtVo, tlrno);
			// 11.将合同账户费率的信息插入到正式表中
			ContractAuditService.getInstance().insertContrAcctRate(mchtVo, tlrno);
			// 12.将临时表状态改为已同步
			ContractAuditService.getInstance().updateTempSyncState(auditId);
			map.put("flag", "true");
			//审核完获取商户签约的交易类型
			String afterDeal = getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
			//System.out.println("afterDeal"+afterDeal);
			//根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
			try {
					log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
					log.info("beforeDeal:"+beforeDeal);
					log.info("afterDeal:"+afterDeal);
					modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
				} catch (Exception e) {
					log.error("错误原因:"+e.getMessage());
					SnowExceptionUtil.throwWarnException("错误原因:"+e);
					SnowExceptionUtil.throwErrorException(e.getMessage());
				}
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--修改待审核全部通过:合同编号[conId]=" + mchtVo.getConId() });
			
			
		} else {// 该下个审核人了
				// 9.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			map.put("flag", "false");
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--修改待审核此步通过:合同编号[conId]=" + mchtVo.getConId() });
		}
	}

	// 中止待审核同意
	public void offAduitContr(AuditCommonAction auditCommonAction, int roleId, PmpAuditInfo pmpAuditInfo,
			String auditId, String tlrno, String auditView, MchtContractVO mchtVo, Map<String, Object> map
			,String beforeDeal)
			throws SnowException {
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(roleId, pmpAuditInfo.getAuditId());
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 得到当前角色的审核步骤
		int stepNo = pmpAuditStepInfo.getStepNo();
		// 根据审核流程编号和步骤，查询下个审核步骤
		PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep(stepNo + 1, pmpAuditInfo.getAuditId());
		if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
			// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
			auditCommonAction.updateAuditInfoState(auditId);
			// 3.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
			ContractAuditService.getInstance().offMchtInfoTmp(auditId);
			// 正式表的合同状态，设为99:中止
			ContractAuditService.getInstance().offMchtInfo(mchtVo.getConId());
			map.put("flag", "true");
			//审核完获取商户签约的交易类型
			String afterDeal = getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
					//System.out.println("afterDeal"+afterDeal);
					//根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
					try {
						log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
						log.info("beforeDeal:"+beforeDeal);
						log.info("afterDeal:"+afterDeal);
							modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
						} catch (Exception e) {
							log.error("错误原因:"+e.getMessage());
							 SnowExceptionUtil.throwWarnException("错误原因:"+e);
							 SnowExceptionUtil.throwErrorException(e.getMessage());
						}
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--中止待审核全部通过:合同编号[conId]=" + mchtVo.getConId() });
			
		} else {// 该下个审核人了
				// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			/** 打印日志 */
			map.put("flag", "false");
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--中止待审核此步通过:合同编号[conId]=" + mchtVo.getConId() });
		}
	}

	// 恢复待审核同意
	public void onAduitContr(AuditCommonAction auditCommonAction, int roleId, PmpAuditInfo pmpAuditInfo, String auditId,
			String tlrno, String auditView, MchtContractVO mchtVo, Map<String, Object> map
			,String beforeDeal) throws SnowException {
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(roleId, pmpAuditInfo.getAuditId());
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 得到当前角色的审核步骤
		int stepNo = pmpAuditStepInfo.getStepNo();
		// 根据审核流程编号和步骤，查询下个审核步骤
		PmpAuditStepInfo pmpAuditProcStepNo = auditCommonAction.getNextStep(stepNo + 1, pmpAuditInfo.getAuditId());
		if (pmpAuditProcStepNo == null) {// 没有下个审核人了，审核结束，全部通过
			// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
			auditCommonAction.updateAuditInfoState(auditId);
			// 根据记录来查询实际的合同的状态，不同的状态，恢复时的状态是不一样的
			MchtContractVO m = MchtContractService.getInstance().selectOneByConId(mchtVo.getConId());
			if (MchtContractConstants.MCHT_CONTR_STAT_DEADED.equals(m.getConState())) { // 已经到期，恢复时变为已经到期
				mchtVo.setConState(MchtContractConstants.MCHT_CONTR_STAT_DEADED);
			} else if (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(m.getConState())
					|| MchtContractConstants.MCHT_CONTR_STAT_ON.equals(m.getConState())
					|| MchtContractConstants.MCHT_STAT_10.equals(m.getConState())) {
				mchtVo.setConState(MchtContractConstants.MCHT_CONTR_STAT_NORMAL); // 签订中和即将到期，恢复成执行中
			}
			// 3.商户合同临时表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
			ContractAuditService.getInstance().onMchtInfoTmp(auditId, mchtVo);
			// 正式表的合同状态，设为00:执行中
			ContractAuditService.getInstance().onMchtInfo(mchtVo.getConId());
			map.put("flag", "true");
			//审核完获取商户签约的交易类型
			String afterDeal = getMchtDealTypeStringFromTmp(mchtVo.getMchtId());
					//System.out.println("afterDeal"+afterDeal);
					//根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
					try {
						log.info("mchtVo.getMchtId():"+mchtVo.getMchtId());
						log.info("beforeDeal:"+beforeDeal);
						log.info("afterDeal:"+afterDeal);
							modifyMchtQrc(mchtVo.getMchtId(),beforeDeal,afterDeal);
						} catch (Exception e) {
							log.error("错误原因"+e.getMessage());
							SnowExceptionUtil.throwWarnException("错误原因:"+e);
							SnowExceptionUtil.throwErrorException(e.getMessage());
						}
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--恢复待审核全部通过:合同编号[conId]=" + mchtVo.getConId() });
			
			
		} else {// 该下个审核人了
				// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			map.put("flag", "false");
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--恢复待审核此步通过:合同编号[conId]=" + mchtVo.getConId() });
		}
	}

	public void auditContrRefuse(AuditCommonAction auditCommonAction, int roleId, PmpAuditInfo pmpAuditInfo,
			String auditId, String tlrno, String auditView, MchtContractVO mchtVo) throws SnowException {
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(roleId, pmpAuditInfo.getAuditId());
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		/* 获取合同的状态 */
		String conState = mchtVo.getConState();
		// 得到当前角色的审核步骤
		// 1.记录此次审核记录，插入到审核记录表中
		auditCommonAction.updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
		// 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝；
		auditCommonAction.updateAuditInfoRefuse(auditId);
		// 3.新增审核被拒绝，商户合同临时表数据中，合同状态CON_STATE，05 新增被拒绝
		if (MchtContractConstants.MCHT_STAT_04.equals(conState)) {
			// 新增被拒绝会有合同状态变化，不用回滚，其余的都需要回滚，不用设置合同状态和同步标志
			ContractAuditService.getInstance().MchtContrInfoTmpRefuse(auditId);
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--新增待审核拒绝:合同编号[conId]=" + mchtVo.getConId() });
		} // 4.修改被拒绝，商户合同临时表数据中， 合同的下的正式表中的所有有关数据记录更新到临时表中
		else {
			// 全部将此合同在正式表中的数据回滚到临时表中
			// 5.删除临时表中商户合同的基本信息的数据
			ContractAuditService.getInstance().delContrBaseInfoTemp(mchtVo.getConId());
			// 6.删除临时表中商户合同的下图片基本信息的数据
			ContractAuditService.getInstance().delContrPicInfoTemp(mchtVo.getConId());
			// 7.删除临时表中商户产品的数据
			ContractAuditService.getInstance().delContrRelProTemp(mchtVo.getConId());
			// 8.删除临时表中商户账户费率的数据
			ContractAuditService.getInstance().delContrAcctRateTemp(mchtVo.getConId());
			// 删除临时表中的交易限额
			//MchtTxnLimitService.getInstance().delMchtTxnLitTmpByMchtId(mchtVo.getMchtId());
			// 将正式表中的数据回滚到临时表中
			//MchtTxnLimitService.getInstance().insertMchtTxnLitTmpByMchtId(mchtVo, tlrno);
			// 9.将正式表中的基础信息插入到临时表中
			ContractAuditService.getInstance().insertContrBaseInfoTemp(mchtVo, tlrno,auditId);
			// 10.将正式表中的图片信息插入到临时表中
			ContractAuditService.getInstance().insertContrPicInfoTemp(mchtVo, tlrno);
			// 11.将正式表合同关联产品的信息插入到临时表中
			ContractAuditService.getInstance().insertContrRelProTemp(mchtVo, tlrno);
			// 12.将正式表合同账户费率的信息插入到临时表中
			ContractAuditService.getInstance().insertContrAcctRateTemp(mchtVo, tlrno);
			// 13.临时表状态改为已同步
			ContractAuditService.getInstance().updateTempSyncState(auditId);

			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同审核管理]--审核被拒绝:合同编号[conId]=" + mchtVo.getConId() });
		} // 14.中止/恢复被拒绝，商户合同临时表数据中，合同的下的正式表中的合同的状态记录更新到临时表中
			// 同步状态，SYNC_STATE 00：未同步；
		ContractAuditService.getInstance().updateTempSyncState(auditId);
	}
	
	/**
	 * 查询商户合同关联产品正式表
	 * 根据商户号查询该商户合同签约的支付产品，判断支付产品是否包含：指定商户一码付的产品号
	 * 返回字符串规则说明：1代表有，0代表没有。
	 * @param mchtId 商户号
	 * @return
	 * @throws SnowException
	 */
	public String getMchtDealTypeString(String mchtId) throws SnowException {
		//String  dealTypeString = "";//返回的结果
		String conId = QrcMngService.getInstance().getCodIdByMchtId(mchtId);//合同编号
		if(conId == null){
			log.debug("getMchtDealTypeString方法,conId为空");
			return "0";
		}
		/**获取指定产品号*/
		String filtProdId = SysParamUtil.getParam(AuditConstants.MCHT_QRC_PROD_ID);
		if(IfspDataVerifyUtil.isBlank(filtProdId)){
			return "0";
		}
		/**获取合同关联产品正式表的产品信息列表，判断是否包含指定产品号*/
		List<Object> conProdList = MchtContractService.getInstance().getProdIdListByConId(conId);
		if(IfspDataVerifyUtil.isEmptyList(conProdList) ){
			return "0";
		}
		for(Object tmpObj : conProdList){
			String prodId = tmpObj.toString();
			if(prodId.equals(filtProdId)){
				return "1";
			}
		}
		return "0";
//		//查询该商户下面所有签约的不重复的交易类型编号
//		String txnTypeCode = QrcMngService.getInstance().getTxnTypeCodeByConId(conId);
//		//System.out.println("txnTypeCode"+txnTypeCode);
//		//交易类型1006：微信订单主扫  1007：支付宝订单主扫   1028:鼎融易主扫
//		log.debug("txnTypeCode:"+txnTypeCode);
//		dealTypeString += txnTypeCode.indexOf("1006") != -1 ? "1" : "0";
//		dealTypeString += txnTypeCode.indexOf("1007") != -1 ? "1" : "0";
//		dealTypeString += txnTypeCode.indexOf("1028") != -1 ? "1" : "0";
//		log.debug("dealTypeString:"+dealTypeString);
		
		//return dealTypeString;
	}
	
	/**
	 * 查询合同关联产品临时表
	 * 根据商户号查询该商户合同临时表中签约的支付产品(因为审核完就是将临时表中的数据更新到正式表中)，判断支付产品是否包含：指定配置产品。
	 * 根据包含的不同交易类型返回不同的字符串。
	 * 返回字符串规则说明：1代表有，0代表没有
	 * @param mchtId 商户号
	 * @return
	 * @throws SnowException
	 */
	public String getMchtDealTypeStringFromTmp(String mchtId) throws SnowException {
		//String  dealTypeString = "";//返回的结果
		String conId = QrcMngService.getInstance().getCodIdByMchtIdFromTmp(mchtId);//合同编号
		if(conId == null){
			return "0";
		}
		
		/**获取指定产品号*/
		String filtProdId = SysParamUtil.getParam(AuditConstants.MCHT_QRC_PROD_ID);
		if(IfspDataVerifyUtil.isBlank(filtProdId)){
			return "0";
		}
		/**获取合同关联产品临时表的产品信息列表，判断是否包含指定产品号*/
		List<Object> conProdList = MchtContractService.getInstance().getTmpProdIdListByConId(conId);
		if(IfspDataVerifyUtil.isEmptyList(conProdList) ){
			return "0";
		}
		for(Object tmpObj : conProdList){
			String prodId = tmpObj.toString();
			if(prodId.equals(filtProdId)){
				return "1";
			}
		}
		return "0";
		
		
		
//		//查询该商户下面所有签约的不重复的交易类型编号
//		String txnTypeCode = QrcMngService.getInstance().getTxnTypeCodeByConIdFromTmp(conId);
//		//System.out.println("txnTypeCode"+txnTypeCode);
//		//交易类型1006：微信订单主扫  1007：支付宝订单主扫   1028:鼎融易主扫
//		dealTypeString += txnTypeCode.indexOf("1006") != -1 ? "1" : "0";
//		dealTypeString += txnTypeCode.indexOf("1007") != -1 ? "1" : "0";
//		dealTypeString += txnTypeCode.indexOf("1028") != -1 ? "1" : "0";
//		return dealTypeString;
	}
	/**
	 * 根据商户合同审核之前和审核之后签约的交易类型来决定是否给商户新增/停用二维码信息
	 * 审核前后产品是否包含一码付产品：PD1001
	 * @param beforeDeal  合同审核之前的包含产品
	 * @param afterDeal	     合同审核完之后的包含产品
	 * @throws Exception 
	 */
	public void modifyMchtQrc(String mchtId,String beforeDeal,String afterDeal) throws Exception{
		if(beforeDeal != null && afterDeal != null && beforeDeal.equals(afterDeal)){//前后没改变，则不需要给商户在新增或停用二维码
			return ;
		}else{
			/**如果审核后不生成二维码，则认为审核前的商户一码付二维码全部作废*/
			if(afterDeal.equals("0")){
				new QrcMngAction().stopMchtQrc(mchtId,MchtQrcCodeConstants.QRC_TYPE_ONE_CODE);
			}else if(afterDeal.equals("1")){
				String mchtName = QrcMngService.getInstance().getMchtSimpleNameByMchtId(mchtId);//根据商户号查询商户简称
				QrcBaseInfo vo = new QrcBaseInfo();
				vo.setMchtId(mchtId);
				vo.setMchtSimpleName(mchtName);
				vo.setQrcType(MchtQrcCodeConstants.QRC_TYPE_ONE_CODE);//二维码类型-一码付
				
				/**获取商户一码付的背景图名称*/
				String oneCodeImgName = SysParamUtil.getParam(AuditConstants.QRC_ONECODE_BG_IMG_NAME);
				//需要给商户生成二维码信息
				new QrcMngAction().addMchtQrc(vo,oneCodeImgName);
			}
	
			
//			String qrcBackGroundImageName = "";
//			if("111".equals(afterDeal) ){//微信支付宝鼎融易
//				qrcBackGroundImageName = "qrcBackground7.png";
//			}else if("110".equals(afterDeal)){//微信支付宝
//				qrcBackGroundImageName = "qrcBackground4.png";
//			}else if("101".equals(afterDeal)){//微信鼎融易
//				qrcBackGroundImageName = "qrcBackground5.png";
//			}else if("011".equals(afterDeal)){//支付宝鼎融易
//				qrcBackGroundImageName = "qrcBackground6.png";
//			}else if("100".equals(afterDeal)){//微信
//				qrcBackGroundImageName = "qrcBackground1.png";
//			}else if("010".equals(afterDeal)){//支付宝
//				qrcBackGroundImageName = "qrcBackground2.png";
//			}else if("001".equals(afterDeal)){//鼎融易
//				qrcBackGroundImageName = "qrcBackground3.png";
//			}
//			String mchtName = QrcMngService.getInstance().getMchtSimpleNameByMchtId(mchtId);//根据商户号查询商户简称
//			QrcBaseInfo vo = new QrcBaseInfo();
//			vo.setMchtId(mchtId);
//			vo.setMchtSimpleName(mchtName);
//			vo.setQrcType(MchtQrcCodeConstants.QRC_TYPE_ONE_CODE);//二维码类型-一码付
//			if("000".equals(beforeDeal)){//审核前商户没有微信/二维码/鼎融易主扫
//				if(afterDeal != "000"){//审核完后有微信/二维码/鼎融易主扫
//					//需要给商户生成二维码信息
//					new QrcMngAction().addMchtQrc(vo,qrcBackGroundImageName);
//				}
//			}else{//审核前商户有 微信/二维码/鼎融易主扫
//				//需要将商户原有的二维码信息停用
//				new QrcMngAction().stopMchtQrc(vo.getMchtId(),vo.getQrcType());
//				if(!"000".equals(afterDeal)){//审核完后有微信/二维码/鼎融易主扫
//					//需要给商户生成二维码信息
//					new QrcMngAction().addMchtQrc(vo,qrcBackGroundImageName);
//				}
//			}
		}
	}
}
