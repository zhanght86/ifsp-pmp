package com.ruimin.ifs.pmp.mchtMng.comp;

import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.pmp.mchtMng.process.service.AuditCommonService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;

@ActionResource
@SnowDoc(desc = "审核流程公共的action")
public class AuditCommonAction extends SnowAction {

	/*
	 * 根据操作员编号，获取操作员的角色编号
	 */
	public Integer getRoleIdByTlrno(String tlrno) throws SnowException {
		return AuditCommonService.getInstance().getRoleIdByTlrno(tlrno);
	}

	// 根据审核信息编号获取审核信息实体类

	public PmpAuditInfo getAuditInfoByAduitId(String auditId) throws SnowException {
		return AuditCommonService.getInstance().getAuditInfoByAduitId(auditId);
	}

	// 根据审核信息编号和角色编号查询审核流程步骤表，得到审核步骤
	public PmpAuditStepInfo getAuditStepInfo(Integer roleId, String auditId) throws SnowException {
		return AuditCommonService.getInstance().getAuditStepInfo(roleId, auditId);
	}

	// 根据审核流程编号和步骤，查询下个审核步骤
	public PmpAuditStepInfo getNextStep(Integer stepNo, String auditId) throws SnowException {
		return AuditCommonService.getInstance().getNextStep(stepNo, auditId);
	}

	// 审核通过操作之后，将审核步骤插入到审核记录表中
	public void updateAuditStepInfo(String seqId, Integer roleId, String tlrno, String auditView) throws SnowException {
		AuditCommonService.getInstance().updateAuditStepInfo(seqId, roleId, tlrno, auditView);
	}

	// 审核拒绝操作之后，将审核步骤插入到审核记录表中
	public void updateAuditStepInfoRefuse(String seqId, Integer roleId, String tlrno, String auditView)
			throws SnowException {
		AuditCommonService.getInstance().updateAuditStepInfoRefuse(seqId, roleId, tlrno, auditView);
	}

	// 更改审核信息表状态，AUDIT_STATE 01-审核通过
	public void updateAuditInfoState(String auditId) throws SnowException {
		AuditCommonService.getInstance().updateAuditInfoState(auditId);
	}

	// 更改审核信息表状态，AUDIT_STATE 02-审核拒绝
	public void updateAuditInfoRefuse(String auditId) throws SnowException {
		AuditCommonService.getInstance().updateAuditInfoRefuse(auditId);
	}
}
