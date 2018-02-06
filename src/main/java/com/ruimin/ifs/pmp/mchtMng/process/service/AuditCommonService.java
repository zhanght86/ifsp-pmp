package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@Service
@SnowDoc(desc = "审核流程公共的service")
public class AuditCommonService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static AuditCommonService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AuditCommonService.class);
	}

	/**
	 * 根据操作员编号，查询角色编号
	 * 
	 * @param
	 * @throws SnowException
	 */
	public Integer getRoleIdByTlrno(String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getRoleIdByTlrno",
				RqlParam.map().set("tlrno", tlrno));
	}

	/**
	 * 根据审核信息编号获取审核信息
	 * 
	 * @param roleId
	 * @param auditId
	 * @return
	 * @throws SnowException
	 */
	public PmpAuditInfo getAuditInfoByAduitId(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getAuditInfoByAduitId",
				RqlParam.map().set("auditId", auditId));
	}

	/**
	 * 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
	 * 
	 * @param
	 * @throws SnowException
	 */
	public PmpAuditStepInfo getAuditStepInfo(Integer roleId, String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getAuditStepInfo",
				RqlParam.map().set("auditId", auditId).set("roleId", roleId));
	}

	/**
	 * 根据审核流程编号和步骤，查询下一审核步骤
	 * 
	 * @param
	 * @throws SnowException
	 */
	public PmpAuditStepInfo getNextStep(int stepNo, String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PmpAuditStepInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getNextStep",
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
	 * 审核通过，更改审核信息表状态，AUDIT_STATE 01-审核通过；
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
	 * 更改审核信息表状态，AUDIT_STATE 02-审核拒绝；
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

}
