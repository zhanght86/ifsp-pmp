package com.ruimin.ifs.term.process.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.term.common.constants.TermAuditConstants;
import com.ruimin.ifs.term.common.constants.TermConstants;
import com.ruimin.ifs.term.process.bean.PaypTermInf;
import com.ruimin.ifs.term.process.bean.PaypTermInf_temp;
import com.ruimin.ifs.term.process.bean.PaypTermKey;
import com.union.api.TUnionTransInfo;
import com.union.api.UnionEsscAPI;

/**
 * 
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司 Author: chenggx Date:
 * 2016年10月24日下午4:25:43 History: //修改记录 Version: 0.1 Desc: 审核的service
 */

public class CommonAuditService extends SnowService {
	public static CommonAuditService getInstance() throws SnowException {
		return ContextUtil.getSingleService(CommonAuditService.class);
	}

	/**
	 * 
	 * 功能描述: 加入到终端审核的方法入口
	 * 
	 * @param termTemp
	 * @throws SnowException
	 */
	public void addAuditEntry(PaypTermInf_temp termTemp) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		termTemp.setAuditId(ContextUtil.getUUID());
		termTemp.setSyncState(TermAuditConstants.SYNC_STATE_00);
		addPmpAduitInfo(termTemp, sessionUserInfo);
	}

	/**
	 * 
	 * 功能描述: 审核流程中通过的入口
	 * 
	 * @param paypTermInf
	 * @param auditView
	 * @param map
	 * @return map
	 * @throws SnowException
	 */
	public Map<String, Object> passAuditEntry(PaypTermInf_temp paypTermInf, String auditView, Map<String, Object> map)
			throws SnowException {
		// 获取审核的Id
		String auditId = paypTermInf.getAuditId();

		String tlrno = SessionUserInfo.getSessionUserInfo().getTlrno();
		// 获取当前操作员的角色编号
		int roleId = getRoleIdByTlrno(tlrno);

		// 根据审核信息编号获取审核信息实体类
		PmpAuditInfo pmpAuditInfo = getAuditInfoByAduitId(auditId);
		if (pmpAuditInfo == null) {
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 调用审核通过处理的方法
		handlePassAduitTerm(roleId, auditId, tlrno, auditView, paypTermInf, map);
		return map;
	}

	/**
	 * 
	 * 功能描述: 审核流程中拒绝的入口
	 * 
	 * @param paypTermInf
	 * @param auditView
	 * @throws SnowException
	 */
	public void refuseAuditEntry(PaypTermInf_temp paypTermInf, String auditView) throws SnowException {
		// 获取审核的Id
		String auditId = paypTermInf.getAuditId();

		String tlrno = SessionUserInfo.getSessionUserInfo().getTlrno();
		// 获取当前操作员的角色编号
		int roleId = getRoleIdByTlrno(tlrno);

		// 根据审核信息编号获取审核信息实体类
		PmpAuditInfo pmpAuditInfo = getAuditInfoByAduitId(auditId);
		if (pmpAuditInfo == null) {
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 调用审核拒绝的方法
		handleRefuseAduitTerm(roleId, auditId, tlrno, auditView, paypTermInf);
	}

	/**
	 * 
	 * 功能描述: 根据操作员的机构编号获取审核流程编号
	 * 
	 * @param brno
	 * @return String
	 * @throws SnowException
	 */
	public String selectAuditIdByBrno(String brno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.selectAuditIdByBrno",
				RqlParam.map().set("brno", brno));

	}

	/**
	 * 功能描述：根据操作员编号，查询角色编号
	 * 
	 * @param tlrno
	 * @return Integer
	 * @throws SnowException
	 */
	public Integer getRoleIdByTlrno(String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.getRoleIdByTlrno",
				RqlParam.map().set("tlrno", tlrno));
	}

	/**
	 * 根据审核信息编号获取审核信息
	 * 
	 * @param auditId
	 * @return PmpAuditInfo
	 * @throws SnowException
	 */
	public PmpAuditInfo getAuditInfoByAduitId(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditInfo) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.getAuditInfoByAduitId",
				RqlParam.map().set("auditId", auditId));
	}

	/**
	 * 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
	 * 
	 * @param roleId
	 * @param auditId
	 * @return PmpAuditStepInfo
	 * @throws SnowException
	 */
	public PmpAuditStepInfo getAuditStepInfo(Integer roleId, String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.getAuditStepInfo",
				RqlParam.map().set("auditId", auditId).set("roleId", roleId));
	}

	/**
	 * 功能描述：根据审核流程编号和步骤，查询下一审核步骤
	 * 
	 * @param auditId
	 * @param stepNo
	 * @return PmpAuditStepInfo
	 * @throws SnowException
	 */
	public PmpAuditStepInfo getNextStep(int stepNo, String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.getNextStep",
				RqlParam.map().set("auditId", auditId).set("stepNo", stepNo));
	}

	/**
	 * 功能描述：审核通过，记录此次审核记录，插入到审核记录表中
	 * 
	 * @param auditId
	 * @param stepNo
	 * @return PmpAuditStepInfo
	 * @throws SnowException
	 */
	public void updateAuditStepInfo(String seqId, Integer roleId, String tlrno, String auditView) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 审核意见
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateAuditStepInfo",
				RqlParam.map().set("auditState", TermAuditConstants.AUDIT_STATE_01).set("auditOprNo", tlrno)
						.set("auditDatetIme", time).set("auditView", auditView).set("seqId", seqId));
	}

	/**
	 * 
	 * 功能描述: 审核拒绝，记录此次审核记录，插入到审核记录表中
	 * 
	 * @param seqId
	 * @param roleId
	 * @param tlrno
	 * @return auditView
	 * @throws SnowException
	 */
	public void updateAuditStepInfoRefuse(String seqId, Integer roleId, String tlrno, String auditView)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 审核意见
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateAuditStepInfoRefuse",
				RqlParam.map().set("auditState", TermAuditConstants.AUDIT_STATE_02).set("auditOprNo", tlrno)
						.set("auditDatetIme", time).set("auditView", auditView).set("seqId", seqId));
	}

	/**
	 * 功能描述:审核通过，更改审核信息表状态，AUDIT_STATE 01-审核通过；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateAuditInfoState(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最后更新时间
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateAuditInfoState",
				RqlParam.map().set("auditState", TermAuditConstants.AUDIT_STATE_01).set("lastUpdDateTime", time)
						.set("auditId", auditId));
	}

	/**
	 * 功能描述:更改审核信息表状态，AUDIT_STATE 02-审核拒绝；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateAuditInfoRefuse(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最后更新时间
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateAuditInfoRefuse",
				RqlParam.map().set("auditState", TermAuditConstants.AUDIT_STATE_02).set("lastUpdDateTime", time)
						.set("auditId", auditId));
	}

	/**
	 * 
	 * 功能描述: 根据操作员的步骤去查询具体的审核步骤
	 * 
	 * @param tlrno
	 * @param auditType
	 * @return List<PmpAuditProcStep>
	 * @throws SnowException
	 */
	public List<PmpAuditProcStep> selectTepList(String tlrno, String auditType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll(
				"select * from PMP_AUDIT_PROC_STEP a left join PMP_AUDIT_PROC_INFO b on a.audit_proc_id=b.audit_proc_id left join IFS_ORG c on b.br_class=c.brclass left join IFS_STAFF d on c.brcode=d.brcode  where d.TLRNO='"
						+ tlrno + "' and b.audit_proc_type='" + auditType + "'",
				PmpAuditProcStep.class);
	}

	/**
	 * 
	 * 功能描述: 更新审核信息表
	 * 
	 * @param termTemp
	 * @param sessionUserInfo
	 * @throws SnowException
	 */
	public void addPmpAduitInfo(PaypTermInf_temp termTemp, SessionUserInfo sessionUserInfo) throws SnowException {
		String brno = sessionUserInfo.getBrno();
		String auditProcId = selectAuditIdByBrno(brno);
		if (auditProcId == null) {
			SnowExceptionUtil.throwErrorException("未找到审核流程！");
		}
		PmpAuditInfo pmpAuditInfo = new PmpAuditInfo();
		pmpAuditInfo.setAuditId(termTemp.getAuditId());
		pmpAuditInfo.setAuditProcId(auditProcId);
		String termStat = termTemp.getTermStat();
		String machIdTemp = termTemp.getMachId();
		String machId = null;
		if (machIdTemp != "" && machIdTemp.length() != 0) {

			machId = (String) termTemp.getMachId().substring(0, 1);
		}
		if (TermConstants.TERM_STAT_00.equals(termStat) || TermConstants.TERM_STAT_02.equals(termStat)) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_20);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")信息登记");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		} else if (TermConstants.TERM_STAT_03.equals(termStat)
				|| (TermConstants.TERM_STAT_01.equals(termStat) && !machId.equals("Y") && !machId.equals("N"))) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_21);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")信息变更");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		} else if (TermConstants.TERM_STAT_05.equals(termStat)) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_22);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")启用");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		} else if (TermConstants.TERM_STAT_07.equals(termStat)) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_23);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")停用");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		} else if (machId.equals("Y")) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_24);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")绑定");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		} else if (machId.equals("N")) {
			pmpAuditInfo.setAuditType(TermAuditConstants.AUDIT_TYPE_25);
			pmpAuditInfo.setAuditDesc("终端(" + termTemp.getTermId() + ")解绑");
			pmpAuditInfo.setAuditUrl("paypTermMngAudit.jsp");
		}
		pmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
																		// 14位
		pmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
		pmpAuditInfo.setApplyBrNo(brno);// 申请机构编号
		pmpAuditInfo.setAuditState(TermAuditConstants.AUDIT_STATE_00);
		pmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
		pmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
																		// 14位
		DBDao dao = DBDaos.newInstance();
		dao.insert(pmpAuditInfo);

		// 更新审核信息表
		addPmpAuditStepInfo(termTemp, sessionUserInfo, pmpAuditInfo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				" [终端审核管理]--更新审核信息表信息:终端编号[termId]=" + termTemp.getTermId() });

	}

	/**
	 * 
	 * 功能描述: 更新审核记录表
	 * 
	 * @param termTemp
	 * @param sessionUserInfo
	 * @throws SnowException
	 */
	public void addPmpAuditStepInfo(PaypTermInf_temp termTemp, SessionUserInfo sessionUserInfo,
			PmpAuditInfo pmpAuditInfo) throws SnowException {
		List<PmpAuditProcStep> list = selectTepList(sessionUserInfo.getTlrno(), pmpAuditInfo.getAuditType());
		// 循环插入到审核记录表中
		if (list == null || list.size() == 0) {
			SnowExceptionUtil.throwWarnException("未找到审核步骤！");
		}
		addStepInfo(list, termTemp.getAuditId());
	}

	/**
	 * 
	 * 功能描述: 获取流程步骤的list,初始化插入到信息记录表中
	 * 
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
			pmpAuditStepInfo.setAuditState(TermAuditConstants.AUDIT_STATE_00);
			pmpAuditStepInfo.setRoleId(pmpAuditProcStep.getAuditRoleId());
			dao.insert(pmpAuditStepInfo);
		}
	}

	/**
	 * 
	 * 功能描述: 更改临时表的状态termState 启用-01 、syncState 已变更未同步-01 和审核员的信息
	 * 
	 * @param auditId
	 * @param tlrno
	 * @return void
	 * @throws SnowException
	 */
	public void updateTempStatAndSync(String auditId, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最后更新时间
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTempStatAndSync",
				RqlParam.map().set("termStat", TermConstants.TERM_STAT_01)
						.set("syncState", TermAuditConstants.SYNC_STATE_01).set("auditId", auditId)
						.set("lastAudTlr", tlrno).set("lastAudDateTime", time));

	}

	/**
	 * 
	 * 功能描述: 更改临时表的最近审核员的信息
	 * 
	 * @param termId
	 * @param tlrno
	 * @return void
	 * @throws SnowException
	 */
	public void updateTemplastAudInfo(String termId, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最后更新时间
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTemplastAudInfo",
				RqlParam.map().set("termId", termId).set("lastAudTlr", tlrno).set("lastAudDateTime", time));

	}

	/**
	 * 
	 * 功能描述: 更改临时表syncState 已同步-00
	 * 
	 * @param auditId
	 * @return void
	 * @throws SnowException
	 */
	public void updateTempSyncState(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTempSyncState",
				RqlParam.map().set("syncState", TermAuditConstants.SYNC_STATE_00).set("auditId", auditId));

	}

	/**
	 * 
	 * 功能描述: 更改临时表termStat的状态
	 * 
	 * @param auditId
	 * @return void
	 * @throws SnowException
	 */
	public void updateTempTermStat(String auditId, String termStat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTempTermStat",
				RqlParam.map().set("termStat", termStat).set("auditId", auditId));

	}

	/**
	 * 
	 * 功能描述: 更改正式表termStat的状态
	 * 
	 * @param auditId
	 * @return void
	 * @throws SnowException
	 */
	public void updateTermStat(String termId, String termStat) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTermStat",
				RqlParam.map().set("termStat", termStat).set("termId", termId));

	}

	/**
	 * 
	 * 功能描述: 更改临时表的machId
	 * 
	 * @param auditId
	 * @param machId
	 * @return void
	 * @throws SnowException
	 */
	public void updateTermMachId(String auditId, String machId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.updateTermMachId",
				RqlParam.map().set("auditId", auditId).set("machId", machId));

	}

	/**
	 * 
	 * 功能描述: 终端信息待审核类状态的通过
	 * 
	 * @param roleId
	 * @param auditId
	 * @param tlrno
	 * @param auditView
	 * @param paypTermInf
	 * @param map
	 * @throws SnowException
	 */
	public void handlePassAduitTerm(int roleId, String auditId, String tlrno, String auditView,
			PaypTermInf_temp paypTermInf, Map<String, Object> map) throws SnowException {
		// 加载日志
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String logFlag = null;// 打印日志用
		// 获取终端信息的状态
		String termStat = paypTermInf.getTermStat();
		String machIdTemp = paypTermInf.getMachId();
		String machId = null;
		if (machIdTemp != "" && machIdTemp.length() != 0) {

			machId = (String) paypTermInf.getMachId().substring(0, 1);
		}
		// 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = getAuditStepInfo(roleId, auditId);
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 得到当前角色的审核步骤
		int stepNo = pmpAuditStepInfo.getStepNo();
		// 根据审核流程编号和步骤，查询下个审核步骤
		PmpAuditStepInfo pmpAuditProcStepCurrent = getNextStep(stepNo + 1, auditId);
		if (pmpAuditProcStepCurrent == null) {
			// 1.记录此次审核记录，插入到审核记录表中
			updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			// 2.更改审核信息表状态，AUDIT_STATE 01-审核通过
			updateAuditInfoState(auditId);
			// 3.更改终端信息临时表的终端状态，termStat-01 启用，设置同步状态为SYNC_STATE
			// 01：已变更未同步，并且更新最近操作员信息
			updateTempStatAndSync(auditId, tlrno);
			// 4.把状态审核通过的信息插入到正式表中，设置正式表的同步状态，SYNC_STATE 00，已同步
			// 5.删除终端信息正式表中的终端信息记录
			deleteTermFromal(paypTermInf);
			if (TermConstants.TERM_STAT_03.equals(termStat)) { // 如果是修改待审核
				logFlag = "修改待审核通过结束";
			} else if (TermConstants.TERM_STAT_07.equals(termStat)) {
				// 6.将临时表和正式表的终端信息状态都改成99-停用
				updateTempTermStat(auditId, TermConstants.TERM_STAT_99);
				logFlag = "停用待审核通过结束";
			} else if (TermConstants.TERM_STAT_05.equals(termStat)) {
				// 6.将正式表的终端信息状态都改成01-启用
				updateTempTermStat(auditId, TermConstants.TERM_STAT_01);
				logFlag = "启用待审核通过结束";
			} else if (("Y").equals(machId)) {
				String mach_Id = "K" + paypTermInf.getMachId().substring(1);
				updateTermMachId(auditId, mach_Id);
				logFlag = "绑定待审核通过结束";
			} else if (("N").equals(machId)) {
				String machIdReal = "K" + paypTermInf.getMachId().substring(1);
				PaypMachInfoService.getInstance().updateMachMachStat(TermConstants.MACH_STAT_0, machIdReal);
				String mach_Id = null;
				updateTermMachId(auditId, mach_Id);
				logFlag = "绑定待审核通过结束";
			} else {
				try {
					addTermKey(paypTermInf.getMchtId(), paypTermInf.getTermId());
				} catch (Exception e) {
					SnowLog.getServerLog().info("密钥生成失败");
				}
			}
			termTempToFormal(paypTermInf, tlrno);
			// 7.然后更新完数据，将临时表状态改为已同步
			updateTempSyncState(auditId);
			// 8.生成密钥
			map.put("flag", "Passtrue");
		} else {
			// 该下个审核人
			// 1.记录此次审核记录，插入到审核记录表中
			updateAuditStepInfo(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
			updateTemplastAudInfo(paypTermInf.getTermId(), tlrno);
			map.put("flag", "Passfalse");
			logFlag = "待审核通过未结束";
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[终端信息审核管理]--'" + logFlag + "':终端信息编号[termId]=" + paypTermInf.getTermId() });

	}

	/**
	 * 
	 * 功能描述: 终端信息待审核类状态的拒绝
	 * 
	 * @param roleId
	 * @param auditId
	 * @param tlrno
	 * @param auditView
	 * @param paypTermInf
	 * @throws SnowException
	 */
	public void handleRefuseAduitTerm(int roleId, String auditId, String tlrno, String auditView,
			PaypTermInf_temp paypTermInf) throws SnowException {
		// 加载日志
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();
		String logFlag = null;
		// 获取终端信息的状态
		String termStat = paypTermInf.getTermStat();
		String machIdTemp = paypTermInf.getMachId();
		String machId = null;
		if (machIdTemp != "" && machIdTemp.length() != 0) {

			machId = (String) paypTermInf.getMachId().substring(0, 1);
		}
		// 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
		PmpAuditStepInfo pmpAuditStepInfo = getAuditStepInfo(roleId, auditId);
		if (pmpAuditStepInfo == null) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("获取审核步骤错误！");
		}
		// 1.记录此次审核记录，插入到审核记录表中
		updateAuditStepInfoRefuse(pmpAuditStepInfo.getSeqId(), roleId, tlrno, auditView);
		// 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝
		updateAuditInfoRefuse(auditId);
		if (TermConstants.TERM_STAT_00.equals(termStat)) {
			// 3.更改终端信息临时表的终端状态，termStat-02 新增被拒绝,不需要回滚
			updateTempTermStat(auditId, TermConstants.TERM_STAT_02);
			logFlag = "新增待审核拒绝";

		} else if (TermConstants.TERM_STAT_05.equals(termStat) || TermConstants.TERM_STAT_07.equals(termStat)) {
			// 3.将正式表中的设备信息状态更新到临时表中
			deleteTermTemp(paypTermInf);
			termFormalToTemp(paypTermInf, tlrno);
			logFlag = "启用/停用待审核拒绝";
		} else if (TermConstants.TERM_STAT_03.equals(termStat)) {
			// 3.删掉临时表中的信息，将正式表中的数据回滚到临时表中
			PaypTermInf payptif = (PaypTermInf) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.queryPaypTermInf",
					RqlParam.map().set("termId", paypTermInf.getTermId()));
			if (payptif != null) {

				deleteTermTemp(paypTermInf);
				termFormalToTemp(paypTermInf, tlrno);
			} else {
				updateTempTermStat(auditId, TermConstants.TERM_STAT_02);
			}
			logFlag = "修改待审核拒绝";
		} else if (machId.equals("Y")) {
			// 3.删掉临时表中的信息，将正式表中的数据回滚到临时表中
			String machIdReal = "K" + paypTermInf.getMachId().substring(1);
			PaypMachInfoService.getInstance().updateMachMachStat(TermConstants.MACH_STAT_0, machIdReal);
			deleteTermTemp(paypTermInf);
			termFormalToTemp(paypTermInf, tlrno);
			logFlag = "绑定待审核拒绝";
		} else if (machId.equals("N")) {
			// 3.删掉临时表中的信息，将正式表中的数据回滚到临时表中
			deleteTermTemp(paypTermInf);
			termFormalToTemp(paypTermInf, tlrno);
			logFlag = "解绑待审核拒绝";
		}
		// 4.然后更新完数据，将临时表状态改为已同步
		updateTempSyncState(auditId);
		updateTemplastAudInfo(paypTermInf.getTermId(), tlrno);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[终端信息审核管理]--'" + logFlag + "':终端信息编号[termId]=" + paypTermInf.getTermId() });

	}

	/**
	 * 功能描述: 审核通过，将临时表的终端信息实体类同步到正式表中，并将正式表中的同步状态sync_state设置成00-已同步
	 * 
	 * @param paypTermInf
	 * @param tlrno
	 * @throws SnowException
	 */
	public void termTempToFormal(PaypTermInf_temp paypTermInf, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PaypTermInf paypTerm = new PaypTermInf();
		String auditId = paypTermInf.getAuditId();
		PaypTermInf_temp paypTermTemp = new PaypTermInf_temp();
		paypTermTemp = (PaypTermInf_temp) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.queryPaypTermInfTmp",
				RqlParam.map().set("auditId", auditId));
		// 临时bean 和 正式的bean进行对接转化
		ReflectionUtil.copyProperties(paypTermTemp, paypTerm);
		paypTerm.setLastUpdTlr(tlrno);
		paypTerm.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		paypTerm.setSyncState(TermAuditConstants.SYNC_STATE_00);
		dao.insert(paypTerm);
	}

	/**
	 * 功能描述: 审核拒绝，将正式表的终端信息实体类同步到临时表中
	 * 
	 * @param paypTermInf
	 * @param tlrno
	 * @throws SnowException
	 */
	public void termFormalToTemp(PaypTermInf_temp paypTermInf, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String termId = paypTermInf.getTermId();
		PaypTermInf_temp paypTerm = new PaypTermInf_temp();
		PaypTermInf payptif = (PaypTermInf) dao.selectOne("com.ruimin.ifs.term.rql.commonAudit.queryPaypTermInf",
				RqlParam.map().set("termId", termId));
		// 临时bean 和 正式的bean进行对接转化
		ReflectionUtil.copyProperties(payptif, paypTerm);
		paypTerm.setAuditId(paypTermInf.getAuditId());
		paypTerm.setLastUpdTlr(tlrno);
		paypTerm.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		paypTerm.setSyncState(TermAuditConstants.SYNC_STATE_00);
		dao.insert(paypTerm);
	}

	/**
	 * 
	 * 功能描述: 删除终端信息正式表的记录
	 * 
	 * @param paypTermInf
	 * @throws SnowException
	 */
	public void deleteTermFromal(PaypTermInf_temp paypTermInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String termId = paypTermInf.getTermId();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.deleteTermFromal", RqlParam.map().set("termId", termId));
	}

	/**
	 * 
	 * 功能描述: 删除终端信息临时表的记录
	 * 
	 * @param paypTermInf
	 * @throws SnowException
	 */
	public void deleteTermTemp(PaypTermInf_temp paypTermInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String termId = paypTermInf.getTermId();
		dao.executeUpdate("com.ruimin.ifs.term.rql.commonAudit.deleteTermTemp", RqlParam.map().set("termId", termId));
	}

	/**
	 * 添加秘钥表
	 * 
	 * @param pagyTermKey
	 * @throws SnowException
	 */
	public void insertPagyTermKey(PaypTermKey paypTermKey) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(paypTermKey);
	}

	/**
	 * 添加秘钥表
	 * 
	 * @param pagyTermKey
	 * @throws SnowException
	 */
	public void updatePagyTermKey(PaypTermKey paypTermKey) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(paypTermKey);
	}

	/**
	 * 添加秘钥
	 * 
	 * @throws SnowException
	 */
	public void addTermKey(String mchtId, String termId) throws SnowException {

		String zmk = "posp.sd-sm4-" + termId + ".zmk";
		String zak = "posp.sd-sm4-" + termId + ".zak";
		String zpk = "posp.sd-sm4-" + termId + ".zpk";

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		List<String> ipList = new ArrayList<String>();
		List<Integer> portList = new ArrayList<Integer>();
		ipList.add(0, SysParamUtil.getParam(MchtChnlRequestConstants.IP));
		portList.add(0, Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.PORT)));
		UnionEsscAPI api = new UnionEsscAPI(ipList, portList,
				Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.TIME_OUT)),
				SysParamUtil.getParam(MchtChnlRequestConstants.SYS_ID),
				SysParamUtil.getParam(MchtChnlRequestConstants.APP_ID),
				Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.COUNT)),
				SysParamUtil.getParam(MchtChnlRequestConstants.TLV_OR_XML_FLAG));// ATM
																					// SD
																					// TA

		PaypTermKey paypTermKey = new PaypTermKey();
		paypTermKey.setKeyIndex("posp.sd-sm4-" + termId);// 唯一主键
		paypTermKey.setTermZmkName(zmk);// 终端主秘钥名称
		paypTermKey.setMchtId(mchtId);
		paypTermKey.setTermId(termId);// 终端号
		paypTermKey.setKeySt("1");
		paypTermKey.setTmkSt("0");// 有效
		paypTermKey.setCrtDateTime(DateUtil.get14Date());
		paypTermKey.setCrtOpr(sessionUserInfo.getTlrno());
		paypTermKey.setKeyFlag("01");// 国密秘钥
		insertPagyTermKey(paypTermKey);

		TUnionTransInfo transInfoZmk = api.unionAPIServiceE110(zmk, // 秘钥名称
				"1", // 秘钥组
				"SM4", // 算法标识
				"ZMK", // 秘钥类型
				128, // 秘钥长度
				0, // 更新秘钥标示（0：新增，1：更新）
				1, // 允许使用旧秘钥标示（1：允许，0：禁止）
				1, // 允许导入标示（1：允许，0：不允许）
				1, // 允许导出标示（1：允许，0：不允许）
				0, // 有效天数（0：永久有效）
				1, // 启用标示（1：启用并生效）
				0, // 生效日期
				"", // 秘钥申请平台（可为空）
				"", // 秘钥分发平台
				1, // 模式（可选）
				1, // 输出标示（指定秘钥保护输出）
				0, // 输出标示
				"posp.sd-sm4-root.zmk"// 保护秘钥
		);

		if (transInfoZmk.getResponseCode() != 0) {
			// 抛出异常
			SnowExceptionUtil.throwErrorException("【ZMK秘钥生成失败】" + transInfoZmk.getResponseRemark());
		} else {
			paypTermKey.setTermZmk(transInfoZmk.getReturnBody().getKeyValue());
			paypTermKey.setTermZmkChk(transInfoZmk.getReturnBody().getCheckValue());
			// paypTermKey.setTermZakName(zak);
			updatePagyTermKey(paypTermKey);
			TUnionTransInfo transInfoZak = api.unionAPIServiceE110(zak, // 秘钥名称
					"1", // 秘钥组
					"SM4", // 算法标识
					"ZAK", // 秘钥类型
					128, // 秘钥长度
					0, // 更新秘钥标示（0：新增，1：更新）
					1, // 允许使用旧秘钥标示（1：允许，0：禁止）
					1, // 允许导入标示（1：允许，0：不允许）
					1, // 允许导出标示（1：允许，0：不允许）
					0, // 有效天数（0：永久有效）
					1, // 启用标示（1：启用并生效）
					0, // 生效日期
					"", // 秘钥申请平台（可为空）
					"", // 秘钥分发平台
					1, // 模式（可选）
					1, // 输出标示（指定秘钥保护输出）
					0, // 输出标示
					zmk// 保护秘钥
			);
			TUnionTransInfo transInfoZpk = api.unionAPIServiceE110(zpk, // 秘钥名称
					"1", // 秘钥组
					"SM4", // 算法标识
					"ZPK", // 秘钥类型
					128, // 秘钥长度
					0, // 更新秘钥标示（0：新增，1：更新）
					1, // 允许使用旧秘钥标示（1：允许，0：禁止）
					1, // 允许导入标示（1：允许，0：不允许）
					1, // 允许导出标示（1：允许，0：不允许）
					0, // 有效天数（0：永久有效）
					1, // 启用标示（1：启用并生效）
					0, // 生效日期
					"", // 秘钥申请平台（可为空）
					"", // 秘钥分发平台
					1, // 模式（可选）
					1, // 输出标示（指定秘钥保护输出）
					0, // 输出标示
					zmk// 保护秘钥
			);
			if (transInfoZak.getResponseCode() == 0) {
				paypTermKey.setTermZakName(zak);
				updatePagyTermKey(paypTermKey);
			} else {
				// 抛出异常
				SnowExceptionUtil.throwErrorException("【ZAK秘钥生成失败】" + transInfoZak.getResponseRemark());
			}
			if (transInfoZpk.getResponseCode() == 0) {
				paypTermKey.setTermZpkName(zpk);
				updatePagyTermKey(paypTermKey);
			} else {
				// 抛出异常
				SnowExceptionUtil.throwErrorException("【ZPK秘钥生成失败】" + transInfoZpk.getResponseRemark());
			}
		}

		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[终端秘钥申请]--:终端信息编号[termId]=" + termId });

	}

	/**
	 * 添加秘钥
	 * 
	 * @throws SnowException
	 */
	public void updateTermKey(PaypTermKey paypTermKey) throws SnowException {

		String zmk = "posp.sd-sm4-" + paypTermKey.getTermId() + ".zmk";
		String zak = "posp.sd-sm4-" + paypTermKey.getTermId() + ".zak";
		String zpk = "posp.sd-sm4-" + paypTermKey.getTermId() + ".zpk";

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		List<String> ipList = new ArrayList<String>();
		List<Integer> portList = new ArrayList<Integer>();
		ipList.add(0, SysParamUtil.getParam(MchtChnlRequestConstants.IP));
		portList.add(0, Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.PORT)));
		UnionEsscAPI api = new UnionEsscAPI(ipList, portList,
				Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.TIME_OUT)),
				SysParamUtil.getParam(MchtChnlRequestConstants.SYS_ID),
				SysParamUtil.getParam(MchtChnlRequestConstants.APP_ID),
				Integer.parseInt(SysParamUtil.getParam(MchtChnlRequestConstants.COUNT)),
				SysParamUtil.getParam(MchtChnlRequestConstants.TLV_OR_XML_FLAG));// ATM
																					// SD
																					// TA

		if (paypTermKey.getTermZmk() == null) {
			TUnionTransInfo transInfoZmk = api.unionAPIServiceE110(zmk, // 秘钥名称
					"1", // 秘钥组
					"SM4", // 算法标识
					"ZMK", // 秘钥类型
					128, // 秘钥长度
					0, // 更新秘钥标示（0：新增，1：更新）
					1, // 允许使用旧秘钥标示（1：允许，0：禁止）
					1, // 允许导入标示（1：允许，0：不允许）
					1, // 允许导出标示（1：允许，0：不允许）
					0, // 有效天数（0：永久有效）
					1, // 启用标示（1：启用并生效）
					0, // 生效日期
					"", // 秘钥申请平台（可为空）
					"", // 秘钥分发平台
					1, // 模式（可选）
					1, // 输出标示（指定秘钥保护输出）
					0, // 输出标示
					"posp.sd-sm4-root.zmk"// 保护秘钥
			);

			if (transInfoZmk.getResponseCode() != 0) {
				// 抛出异常
				SnowExceptionUtil.throwErrorException("【ZMK秘钥生成失败】" + transInfoZmk.getResponseRemark());
			} else {
				paypTermKey.setTermZmk(transInfoZmk.getReturnBody().getKeyValue());
				paypTermKey.setTermZmkChk(transInfoZmk.getReturnBody().getCheckValue());
				// paypTermKey.setTermZakName(zak);
				updatePagyTermKey(paypTermKey);
				TUnionTransInfo transInfoZak = api.unionAPIServiceE110(zak, // 秘钥名称
						"1", // 秘钥组
						"SM4", // 算法标识
						"ZAK", // 秘钥类型
						128, // 秘钥长度
						0, // 更新秘钥标示（0：新增，1：更新）
						1, // 允许使用旧秘钥标示（1：允许，0：禁止）
						1, // 允许导入标示（1：允许，0：不允许）
						1, // 允许导出标示（1：允许，0：不允许）
						0, // 有效天数（0：永久有效）
						1, // 启用标示（1：启用并生效）
						0, // 生效日期
						"", // 秘钥申请平台（可为空）
						"", // 秘钥分发平台
						1, // 模式（可选）
						1, // 输出标示（指定秘钥保护输出）
						0, // 输出标示
						zmk// 保护秘钥
				);
				TUnionTransInfo transInfoZpk = api.unionAPIServiceE110(zpk, // 秘钥名称
						"1", // 秘钥组
						"SM4", // 算法标识
						"ZPK", // 秘钥类型
						128, // 秘钥长度
						0, // 更新秘钥标示（0：新增，1：更新）
						1, // 允许使用旧秘钥标示（1：允许，0：禁止）
						1, // 允许导入标示（1：允许，0：不允许）
						1, // 允许导出标示（1：允许，0：不允许）
						0, // 有效天数（0：永久有效）
						1, // 启用标示（1：启用并生效）
						0, // 生效日期
						"", // 秘钥申请平台（可为空）
						"", // 秘钥分发平台
						1, // 模式（可选）
						1, // 输出标示（指定秘钥保护输出）
						0, // 输出标示
						zmk// 保护秘钥
				);
				if (transInfoZak.getResponseCode() == 0) {
					paypTermKey.setTermZakName(zak);
					updatePagyTermKey(paypTermKey);
				} else {
					// 抛出异常
					SnowExceptionUtil.throwErrorException("【ZAK秘钥生成失败】" + transInfoZak.getResponseRemark());
				}
				if (transInfoZpk.getResponseCode() == 0) {
					paypTermKey.setTermZpkName(zpk);
					updatePagyTermKey(paypTermKey);
				} else {
					// 抛出异常
					SnowExceptionUtil.throwErrorException("【ZPK秘钥生成失败】" + transInfoZpk.getResponseRemark());
				}
			}

		} else {
			if (paypTermKey.getTermZakName() == null) {
				TUnionTransInfo transInfoZak = api.unionAPIServiceE110(zak, // 秘钥名称
						"1", // 秘钥组
						"SM4", // 算法标识
						"ZAK", // 秘钥类型
						128, // 秘钥长度
						0, // 更新秘钥标示（0：新增，1：更新）
						1, // 允许使用旧秘钥标示（1：允许，0：禁止）
						1, // 允许导入标示（1：允许，0：不允许）
						1, // 允许导出标示（1：允许，0：不允许）
						0, // 有效天数（0：永久有效）
						1, // 启用标示（1：启用并生效）
						0, // 生效日期
						"", // 秘钥申请平台（可为空）
						"", // 秘钥分发平台
						1, // 模式（可选）
						1, // 输出标示（指定秘钥保护输出）
						0, // 输出标示
						zmk// 保护秘钥
				);
				if (transInfoZak.getResponseCode() == 0) {
					paypTermKey.setTermZakName(zak);
					updatePagyTermKey(paypTermKey);
				} else {
					// 抛出异常
					SnowExceptionUtil.throwErrorException("【ZAK秘钥生成失败】" + transInfoZak.getResponseRemark());
				}
			}
			if (paypTermKey.getTermZpkName() == null) {
				TUnionTransInfo transInfoZpk = api.unionAPIServiceE110(zpk, // 秘钥名称
						"1", // 秘钥组
						"SM4", // 算法标识
						"ZPK", // 秘钥类型
						128, // 秘钥长度
						0, // 更新秘钥标示（0：新增，1：更新）
						1, // 允许使用旧秘钥标示（1：允许，0：禁止）
						1, // 允许导入标示（1：允许，0：不允许）
						1, // 允许导出标示（1：允许，0：不允许）
						0, // 有效天数（0：永久有效）
						1, // 启用标示（1：启用并生效）
						0, // 生效日期
						"", // 秘钥申请平台（可为空）
						"", // 秘钥分发平台
						1, // 模式（可选）
						1, // 输出标示（指定秘钥保护输出）
						0, // 输出标示
						zmk// 保护秘钥
				);
				if (transInfoZpk.getResponseCode() == 0) {
					paypTermKey.setTermZpkName(zpk);
					updatePagyTermKey(paypTermKey);
				} else {
					// 抛出异常
					SnowExceptionUtil.throwErrorException("【ZPK秘钥生成失败】" + transInfoZpk.getResponseRemark());
				}
			}
		}

		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[终端秘钥申请]--:终端信息编号[termId]=" + paypTermKey.getTermId() });

	}
}
