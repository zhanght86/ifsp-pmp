package com.ruimin.ifs.pmp.mchtMng.comp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.message.IfspFastJsonUtil;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
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
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfoTmp;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtAssistMngService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtInPutService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngAuditService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;

@ActionResource
@SnowDoc(desc = "商户信息审核相关操作")
public class MchtMngAuditAction {
    static Logger log = SnowLog.getLogger(MchtMngAuditAction.class);
	
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "通过")
	public synchronized Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
	    //1.审核审核
	    Map<String, Object> map = pass0(updateMap);
	    //2.获取进件标志
        String whetherIn = String.valueOf(map.get("whetherIn"));
        //--01-进件   02-不进件
        if ("01".equals(whetherIn)) {
            //查询该商户下是否有执行中的合同,如果有则调商户进件接口通知进件,并返回审核结果
            List<Object> list = MchtInPutService.getInstance().getConInf(String.valueOf(map.get("mchtId")));
            if (IfspDataVerifyUtil.isNotEmptyList(list)) {
                MchtInPutService.getInstance().mchtIn(String.valueOf(map.get("mchtId")),"基本信息");
            }
        }
		return map;
	}
	
    @Action(propagation = Propagation.REQUIRED)
   @SnowDoc(desc = "拒绝")
    public synchronized void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        refuse0(updateMap);
    }
	
	
	
	
	public Map<String, Object> pass0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
	    // 用于返回页面提示信息使用
	    Map<String, Object> map = new HashMap<String, Object>();
	    // 获取商户信息
	    UpdateRequestBean voBean = updateMap.get("mchtMngInfo");
	    UpdateRequestBean assMap = updateMap.get("PbsMchtAssistInfoTmp");
	    // 商户临时实体对象
	    PbsMchtBaseInfo pbsMchtBaseInfo = new PbsMchtBaseInfo();
	    PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();
	    
	    while (voBean.hasNext()) {
	        DataObjectUtils.map2bean(voBean.next(), pbsMchtBaseInfo);
	    }
	    
	    while(assMap.hasNext()){
	        DataObjectUtils.map2bean(assMap.next(), assistInfoTmp);
	    }
	    // 获取公共方法实体对象
	    AuditCommonAction auditCommonAction = new AuditCommonAction();
	    // 获取审核意见
	    String auditView = voBean.getParameter("auditView");
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
	    //********************是否进件标志       20180110**************************
        String whetherIn = voBean.getParameter("whetherIn");
        map.put("whetherIn", whetherIn);
        //****************************************************************
	    // 根据审核信息编号获取审核信息
	    PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
	    if (pmpAuditInfo == null) {
	        // 抛出异常
	        SnowExceptionUtil.throwWarnException("获取审核信息错误！");
	    }
	       // 商户全称同步通道商户表
	    MchtMngAuditService.getInstance().updPagySubpagyName(pbsMchtBaseInfo);
	    // 根据状态，判断是新增，修改，停用/启用，注销；对应不同的操作
	    if (MchtMngConstants.MCHT_STAT_03.equals(mchtStat)) {// 如果是新增待审核；通过
	        addMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, list, pbsMchtBaseInfo,assistInfoTmp, auditView, map);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--新增审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_04.equals(mchtStat)) {// 04 修改待审核；通过
	        uptMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, list, pbsMchtBaseInfo,assistInfoTmp, auditView, map);
	        //--------------------20180112------------同步一下商户信息辅表与合同表结算账户类型与结算账户户名字段----------------------------------
	        MchtMngAuditService.getInstance().syncContractAndPbsAssit(pbsMchtBaseInfo.getMchtId(),assistInfoTmp.getAccountType(),assistInfoTmp.getSetlAcctName());
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--修改审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_05.equals(mchtStat)) {// 05 冻结待审核；通过
	        frzMethod(auditCommonAction, mchtStat, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo, auditView,
	                map);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--冻结审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_06.equals(mchtStat)) {// 06 恢复待审核；通过
	        frzMethod(auditCommonAction, mchtStat, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo, auditView,
	                map);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--恢复审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_07.equals(mchtStat)) {// 07 注销待审核；通过
	        frzMethod(auditCommonAction, mchtStat, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo, auditView,
	                map);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--注销审核通过:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    map.put("mchtId", pbsMchtBaseInfo.getMchtId());
	    return map;
	    
	}
	
	public void refuse0(Map<String, UpdateRequestBean> updateMap) throws SnowException {
	    // 获取商户信息
	    UpdateRequestBean voBean = updateMap.get("mchtMngInfo");
	    // 获取拒绝原因
	    String auditView = voBean.getParameter("auditView");
	    UpdateRequestBean assMap = updateMap.get("PbsMchtAssistInfoTmp");
	    // 商户临时实体对象
	    PbsMchtBaseInfo pbsMchtBaseInfo = new PbsMchtBaseInfo();
	    PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();
	    
	    while (voBean.hasNext()) {
	        DataObjectUtils.map2bean(voBean.next(), pbsMchtBaseInfo);
	    }
	    
	    while(assMap.hasNext()){
	        DataObjectUtils.map2bean(assMap.next(), assistInfoTmp);
	    }
	    // 获取公共方法实体对象
	    AuditCommonAction auditCommonAction = new AuditCommonAction();
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
	    // 根据审核信息编号获取审核信息
	    PmpAuditInfo pmpAuditInfo = auditCommonAction.getAuditInfoByAduitId(auditId);
	    if (pmpAuditInfo == null) {
	        // 抛出异常
	        SnowExceptionUtil.throwWarnException("获取审核信息错误！");
	    }
	    // 根据状态，判断是新增，修改，停用/启用，注销；对用不同的操作
	    if (MchtMngConstants.MCHT_STAT_03.equals(mchtStat)) {// 如果是新增待审核；拒绝
	        // 新增被拒绝时，审核流程终止
	        // 根据审核流程编号和角色编号查询审核流程步骤表，得到审核步骤
	        PmpAuditStepInfo pmpAuditStepInfo = auditCommonAction.getAuditStepInfo(Integer.parseInt(roleId), auditId);
	        if (pmpAuditStepInfo == null) {
	            // 抛出异常
	            SnowExceptionUtil.throwWarnException("获取审核流程步骤错误！");
	        }
	        // 得到当前角色的审核步骤
	        // Integer stepNo=pmpAuditStepInfo.getStepNo();
	        // 1.记录此次审核记录，更新到审核记录表中
	        auditCommonAction.updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
	                auditView);
	        // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝；解决原因
	        auditCommonAction.updateAuditInfoRefuse(auditId);
	        // 3.商户临时表数据中，商户状态MCHT_STAT，08 新增被拒绝；同步状态，SYNC_STATE 00：已同步；
	        MchtMngAuditService.getInstance().updateMchtInfoTmpRefuse(auditId, tlrno);
	        // 拒绝时，审核中止，新增数据留在临时表中
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--新增审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_04.equals(mchtStat)) {// 04 修改待审核；拒绝
	        refusePubMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo,assistInfoTmp, list, auditView,
	                mchtStat);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--修改审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_05.equals(mchtStat)) {// 05 冻结待审核；拒绝
	        refusePubMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo,assistInfoTmp,  list, auditView,
	                mchtStat);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--冻结审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_06.equals(mchtStat)) {// 06 恢复待审核；拒绝
	        refusePubMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo,assistInfoTmp,  list, auditView,
	                mchtStat);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--恢复审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    if (MchtMngConstants.MCHT_STAT_07.equals(mchtStat)) {// 07 注销待审核；拒绝
	        refusePubMethod(auditCommonAction, roleId, pmpAuditInfo, auditId, tlrno, pbsMchtBaseInfo,assistInfoTmp,  list, auditView,
	                mchtStat);
	        /** 打印日志 */
	        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
	                sessionUserInfo.getBrno(), "[商户审核]--注销审核拒绝:商户编号[mchtId]=" + pbsMchtBaseInfo.getMchtId() });
	    }
	    
	}

	/**
	 * 新增通过
	 * 
	 * @param roleId
	 * @param pmpAuditInfo
	 * @param auditId
	 * @param tlrno
	 * @param pbsMchtPicInfo
	 * @param pbsMchtBaseInfo
	 * @param assistInfoTmp 
	 * @throws SnowException
	 */
	public void addMethod(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo, String auditId,
			String tlrno, List<PbsMchtPicInfo> list, PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtAssistInfoTmp assistInfoTmp, String auditView,
			Map<String, Object> map) throws SnowException {
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
			// 3.商户临时表数据中，商户状态MCHT_STAT，00 正常；同步状态，SYNC_STATE 01：已变更未同步；
			MchtMngAuditService.getInstance().updateMchtInfoTmp(auditId, tlrno);
			// 4.把审核通过信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
			MchtMngAuditService.getInstance().insertMchtBaseInfo(pbsMchtBaseInfo, tlrno);
			// 5.临时表状态改为已同步
			MchtMngAuditService.getInstance().updateSyncState(auditId);
			// 6.把图片信息表数据插入到正式表中，有多条，循环操作
			MchtMngAuditService.getInstance().insertMchtPicInfo(list, tlrno);
			// 7.把辅表信息放入正式表中
			PbsMchtAssistInfo assistInfo=new PbsMchtAssistInfo();
			ReflectionUtil.copyProperties(assistInfoTmp, assistInfo);
			MchtAssistMngService.getInstance().addAssistInfo(assistInfo);
			map.put("flag", "true");
			
			
		} else {// 该下个审核人了
				// 1.记录此次审核记录，更新到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
					auditView);
			map.put("flag", "false");
		}
	}

	/**
	 * 修改通过
	 * 
	 * @param roleId
	 * @param pmpAuditInfo
	 * @param auditId
	 * @param tlrno
	 * @param pbsMchtPicInfo
	 * @param pbsMchtBaseInfo
	 * @param assistInfoTmp 
	 * @throws SnowException
	 */
	public void uptMethod(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo, String auditId,
			String tlrno, List<PbsMchtPicInfo> list, PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtAssistInfoTmp assistInfoTmp, String auditView,
			Map<String, Object> map) throws SnowException {
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
			
			//---------2017、03、17新增--------商户门户相关------新增开始----------
//			try {
//				new UserMngAction().modifyMssUserBaseInf(pbsMchtBaseInfo);
//			} catch (Exception e) {
//				SnowExceptionUtil.throwErrorException(e.getMessage());
//			}
			//---------2017、03、17新增--------商户门户相关------新增结束----------
			
			// 1.记录此次审核记录，更新到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
					auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
			auditCommonAction.updateAuditInfoState(auditId);
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
			// 7.修改商户辅表信息
			PbsMchtAssistInfo assistInfo=new PbsMchtAssistInfo();
			ReflectionUtil.copyProperties(assistInfoTmp, assistInfo);
			PbsMchtAssistInfo selectByMchtId = MchtAssistMngService.getInstance().selectByMchtId(assistInfo.getMchtId());
			if (IfspDataVerifyUtil.isNotBlank(selectByMchtId)) {
				MchtAssistMngService.getInstance().updAssistInfo(assistInfo);
			}else{
				MchtAssistMngService.getInstance().addAssistInfo(assistInfo);
			}
			map.put("flag", "true");
		} else {// 该下个审核人了
				// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
					auditView);
			map.put("flag", "false");
		}
	}

	/**
	 * 冻结/解冻方法,注销，审核通过时
	 * 
	 * @param roleId
	 * @param pmpAuditInfo
	 * @param auditId
	 * @param tlrno
	 * @param pbsMchtPicInfo
	 * @param pbsMchtBaseInfo
	 * @throws SnowException
	 */
	public void frzMethod(AuditCommonAction auditCommonAction, String mchtStat, String roleId,
			PmpAuditInfo pmpAuditInfo, String auditId, String tlrno, PbsMchtBaseInfo pbsMchtBaseInfo, String auditView,
			Map<String, Object> map) throws SnowException {
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
			// 3.商户临时表数据中，商户状态MCHT_STAT，01 冻结；；同步状态，SYNC_STATE 01：已变更未同步；
			if (MchtMngConstants.MCHT_STAT_05.equals(mchtStat)) {// 冻结
				pbsMchtBaseInfo.setMchtStat(MchtMngConstants.MCHT_STAT_FRZ);
				pbsMchtBaseInfo.setSyncState(MchtContractConstants.SYNC_STATE_01);
			}
			if (MchtMngConstants.MCHT_STAT_06.equals(mchtStat)) {// 恢复
				pbsMchtBaseInfo.setMchtStat(MchtMngConstants.MCHT_STAT_NORMAL);
				pbsMchtBaseInfo.setSyncState(MchtContractConstants.SYNC_STATE_01);
			}
			if (MchtMngConstants.MCHT_STAT_07.equals(mchtStat)) {// 注销
				pbsMchtBaseInfo.setMchtStat(MchtMngConstants.MCHT_STAT_OFF);
				pbsMchtBaseInfo.setSyncState(MchtContractConstants.SYNC_STATE_01);
				//---------2017、03、17新增--------商户门户相关------新增开始----------
//				try {
//					new UserMngAction().dropMssUserMchtRlt(pbsMchtBaseInfo);
//				} catch (Exception e) {
//					SnowExceptionUtil.throwErrorException(e.getMessage());
//				}
				//---------2017、03、17新增--------商户门户相关------新增结束----------
			}
			MchtMngAuditService.getInstance().updateMchtInfoTmpByFrz(pbsMchtBaseInfo);
			// 4.把审核通过信息,更改对应正式表，同时把同步状态，SYNC_STATE 00：已同步；
			PbsMchtBaseInfoReal pbsMchtBaseInfoReal = MchtMngAuditService.getInstance()
					.selectMchtInfoTmpReal(pbsMchtBaseInfo.getMchtId());
			MchtMngAuditService.getInstance().updMchtFrz(pbsMchtBaseInfo, pbsMchtBaseInfoReal, tlrno);
			// 5.临时表状态改为已同步
			MchtMngAuditService.getInstance().updateSyncState(auditId);
			map.put("flag", "true");
		} else {// 该下个审核人了
				// 1.记录此次审核记录，插入到审核记录表中
			auditCommonAction.updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), Integer.parseInt(roleId), tlrno,
					auditView);
			map.put("flag", "false");
		}
	}

	/**
	 * 修改，冻结，恢复，注销，审核被拒绝时
	 * 
	 * @param roleId
	 * @param pmpAuditInfo
	 * @param auditId
	 * @param tlrno
	 * @param pbsMchtBaseInfo
	 * @param assistInfoTmp 
	 * @param assistInfoTmp 
	 * @param list
	 * @throws SnowException
	 */
	public void refusePubMethod(AuditCommonAction auditCommonAction, String roleId, PmpAuditInfo pmpAuditInfo,
			String auditId, String tlrno, PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtAssistInfoTmp assistInfoTmp, List<PbsMchtPicInfo> list, String auditView,
			String mchtStat) throws SnowException {
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
		// 查询商户信息正式表中数据
		PbsMchtBaseInfoReal pbsMchtBaseInfoReal = MchtMngAuditService.getInstance().selectMchtInfoTmpReal(mchtId);
		// 正式表数据回滚到临时表中,当修改，冻结，恢复，注销，审核被拒绝时
		MchtMngAuditService.getInstance().backMchtInfo(pbsMchtBaseInfo, pbsMchtBaseInfoReal, tlrno,auditId);
		// 获取商户状态，05 06 07 不用回滚图片信息
		if (MchtMngConstants.MCHT_STAT_04.equals(mchtStat)) {
			// 查询图片信息正式表中数据,可能有多条，仅仅修改时关系到图片信息表
			List<PbsMchtPicInfoReal> listPic = MchtMngAuditService.getInstance().selectMchtPicInfoReal(pbsMchtBaseInfo);
			// 正式表数据回滚到临时表中
			MchtMngAuditService.getInstance().backMchtPicInfo(list, listPic, tlrno);
		}


//		PbsMchtAssistInfo selectByMchtId = MchtAssistMngService.getInstance().selectByMchtId(assistInfo.getMchtId());
//		if (IfspDataVerifyUtil.isNotBlank(selectByMchtId)) {
//			MchtAssistMngService.getInstance().updAssistInfo(assistInfo);
//		}else{
//			MchtAssistMngService.getInstance().addAssistInfo(assistInfo);
//		}
		//回滚商户辅助信息
		PbsMchtAssistInfo assistInfo=MchtAssistMngService.getInstance().selectByMchtId(mchtId);
		if (IfspDataVerifyUtil.isNotBlank(assistInfo)) {
			PbsMchtAssistInfoTmp assistInfoTemp=new PbsMchtAssistInfoTmp();
			ReflectionUtil.copyProperties(assistInfo, assistInfoTemp);
			MchtAssistMngService.getInstance().updAssistInfoTmp(assistInfoTemp);
		}
//		else{
//			MchtAssistMngService.getInstance().deleteAssistTmp(assistInfoTmp);
//		}
		
	}
}