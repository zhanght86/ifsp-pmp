package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractAcctRate;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractPicVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractProduct;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbcMchtRelContractProd;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtContractAcctRate;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtContractInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtContractPicInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;

import java.util.List;

import org.slf4j.Logger;

@Service
@SnowDoc(desc = "商户合同审核管理")
public class ContractAuditService extends SnowService {
    static Logger log = SnowLog.getLogger(ContractAuditService.class);
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static ContractAuditService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ContractAuditService.class);
	}

	/**
	 * 审核拒绝，记录此次审核记录，插入到审核记录表中
	 */
	public void updateAuditStepInfoRefuse(String seqId, Integer roleId, String tlrno, String auditView)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 审核意见
		String time = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod1",
				RqlParam.map().set("auditState", MchtContractConstants.AUDIT_STATE_02).set("auditOprNo", tlrno)
						.set("auditDatetIme", time).set("auditView", auditView).set("seqId", seqId));

	}

	/**
	 * 中止审核通过:商户合同临时表数据中，合同状态CON_STATE改为:99 执行中；同步状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void offMchtInfoTmp(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod2",
				RqlParam.map().set("conState", MchtContractConstants.MCHT_CONTR_STAT_OFF)
						.set("syncState", MchtContractConstants.SYNC_STATE_01).set("auditId", auditId));
	}

	/**
	 * 中止审核通过:商户合同正式表中的数据，合同状态CON_STATE改为:99 执行中；同步状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void offMchtInfo(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod3",
				RqlParam.map().set("conState", MchtContractConstants.MCHT_CONTR_STAT_OFF)
						.set("syncState", MchtContractConstants.SYNC_STATE_00).set("conId", conId));
	}

	/**
	 * 恢复审核通过:商户合同正式表数据中，合同状态CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void onMchtInfo(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod3",
				RqlParam.map().set("conState", MchtContractConstants.MCHT_CONTR_STAT_NORMAL)
						.set("syncState", MchtContractConstants.SYNC_STATE_00).set("conId", conId));
	}

	/**
	 * 审核通过，商户合同临时表数据中，改 合同状态为CON_STATE，00 执行中；同步状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateMchtInfoTmp(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod4",
				RqlParam.map().set("conState", MchtContractConstants.MCHT_CONTR_STAT_NORMAL)
						.set("syncState", MchtContractConstants.SYNC_STATE_01).set("auditId", auditId));
	}

	/**
	 * 商户恢复审核通过:商户合同临时表数据中，合同状态 CON_STATE 改为对应的状态，恢复审核通过时，状态相应的变化；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void onMchtInfoTmp(String auditId, MchtContractVO mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String conState = mchtVo.getConState();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod4",
				RqlParam.map().set("conState", conState).set("syncState", MchtContractConstants.SYNC_STATE_01)
						.set("auditId", auditId));
	}

	/**
	 * 审核通过:把商户合同的基本信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @throws SnowException
	 */
	public void insertContrBaseInfo(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		PbsMchtContractInfo pbsMchtContractInfo = new PbsMchtContractInfo();
		MchtContractVO mchtContractVO = new MchtContractVO();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		mchtContractVO = (MchtContractVO) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractInfoTemp",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		ReflectionUtil.copyProperties(mchtContractVO, pbsMchtContractInfo);
		// 最近更新操作员，最近更新时间
		pbsMchtContractInfo.setLastUpdTlr(tlrno);
		// 同步状态变更为00
		pbsMchtContractInfo.setSyncState(MchtContractConstants.SYNC_STATE_00);
		pbsMchtContractInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		// 正式表不为空，则插入到临时表中。
		dao.insert(pbsMchtContractInfo);
	}

	/**
	 * 审核通过:把商户合同的图片基本信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @throws SnowException
	 */
	public void insertContrPicInfo(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		PbsMchtContractPicInfo pbsMchtContractPicInfo = new PbsMchtContractPicInfo();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractPIcInfoTemp",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), pbsMchtContractPicInfo);
			// 最近更新操作员，最近更新时间
			pbsMchtContractPicInfo.setLastUpdTlr(tlrno);
			pbsMchtContractPicInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(pbsMchtContractPicInfo);
		}

	}

	/**
	 * 审核通过:把商户合同的图片基本信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @throws SnowException
	 */
	public void insertContrRelPro(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		PbcMchtRelContractProd pbcMchtRelContractProd = new PbcMchtRelContractProd();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractRelProdTemp",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), pbcMchtRelContractProd);
			// 最近更新操作员，最近更新时间
			pbcMchtRelContractProd.setLastUpdTlr(tlrno);
			pbcMchtRelContractProd.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(pbcMchtRelContractProd);
		}

	}

	/**
	 * 审核通过:把商户合同的图片基本信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @throws SnowException
	 */
	public void insertContrAcctRate(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		PbsMchtContractAcctRate pbsMchtContractAcctRate = new PbsMchtContractAcctRate();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContrAcctRateTemp",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), pbsMchtContractAcctRate);
			// 最近更新操作员，最近更新时间
			pbsMchtContractAcctRate.setLastUpdTlr(tlrno);
			pbsMchtContractAcctRate.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(pbsMchtContractAcctRate);
		}

	}

	/**
	 * 把审核通过把正式表中的数据回滚到临时表中
	 * 
	 * @throws SnowException
	 */
	public void insertContrBaseInfoTemp(MchtContractVO mchtVo, String tlrno,String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		PbsMchtContractInfo pbsMchtContractInfo = new PbsMchtContractInfo();
		MchtContractVO mchtContractVO = new MchtContractVO();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		pbsMchtContractInfo = (PbsMchtContractInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractInfo",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		ReflectionUtil.copyProperties(pbsMchtContractInfo, mchtContractVO);
		// 最近更新操作员，最近更新时间
		mchtContractVO.setLastUpdTlr(tlrno);
		// 同步状态变更为00
		mchtContractVO.setSyncState(MchtContractConstants.SYNC_STATE_01);
		mchtContractVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		//2017-12-20     保留临时表的审核id和商户信息表临时表一致
		mchtContractVO.setAuditId(auditId);
		// 正式表不为空，则插入到临时表中。
		dao.insert(mchtContractVO);
	}

	/**
	 * 把审核通过把正式表中的数据回滚到临时表中
	 * 
	 * @throws SnowException
	 */
	public void insertContrPicInfoTemp(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		MchtContractPicVO mchtContractPicVO = new MchtContractPicVO();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractPIcInfo",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), mchtContractPicVO);
			// 最近更新操作员，最近更新时间
			mchtContractPicVO.setLastUpdTlr(tlrno);
			mchtContractPicVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(mchtContractPicVO);
		}

	}

	/**
	 * 把审核通过把正式表中的数据回滚到临时表中
	 * 
	 * @throws SnowException
	 */
	public void insertContrRelProTemp(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		MchtContractProduct mchtContractProduct = new MchtContractProduct();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContractRelProd",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), mchtContractProduct);
			// 最近更新操作员，最近更新时间
			mchtContractProduct.setLastUpdTlr(tlrno);
			mchtContractProduct.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(mchtContractProduct);
		}

	}

	/**
	 * 把审核通过把正式表中的数据回滚到临时表中
	 * 
	 * @throws SnowException
	 */
	public void insertContrAcctRateTemp(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String conId = mchtVo.getConId();
		MchtContractAcctRate mchtContractAcctRate = new MchtContractAcctRate();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		List<Object> List = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.queryMchtContrAcctRate",
				RqlParam.map().set("conId", conId));
		// 临时bean 和 正式的bean进行对接转化
		for (int i = 0; i < List.size(); i++) {
			// 正式表数据copy到临时表中
			ReflectionUtil.copyProperties(List.get(i), mchtContractAcctRate);
			// 最近更新操作员，最近更新时间
			mchtContractAcctRate.setLastUpdTlr(tlrno);
			mchtContractAcctRate.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			// 同步状态变更为00
			dao.insert(mchtContractAcctRate);
		}

	}

	/* 删除临时表商户合同的基本信息 */
	public void delContrBaseInfoTemp(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractInfoTemp",
				RqlParam.map().set("conId", conId));
	}

	/* 删除临时表商户合同的基本信息 */
	public void delContrPicInfoTemp(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractPIcInfoTemp",
				RqlParam.map().set("conId", conId));
	}

	/* 删除临时表商户合同关联的产品 */
	public void delContrRelProTemp(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractRelProdTemp",
				RqlParam.map().set("conId", conId));
	}

	/* 删除临时表商户合同账户费率关联表 */
	public void delContrAcctRateTemp(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContrAcctRateTemp",
				RqlParam.map().set("conId", conId));
	}

	/* 删除正式表商户合同的基本信息 */
	public void delContrBaseInfo(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractInfo",
				RqlParam.map().set("conId", conId));
	}

	/* 删除正式表商户合同关联的图片信息 */
	public void delContrPicInfo(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractPIcInfo",
				RqlParam.map().set("conId", conId));
	}

	/* 删除正式表商户合同关联的产品信息 */
	public void delContrRelPro(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContractRelProd",
				RqlParam.map().set("conId", conId));
	}

	/* 删除正式表商户合同账户费率关联表 */
	public void delContrAcctRate(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.deleteMchtContrAcctRate",
				RqlParam.map().set("conId", conId));
	}

	/**
	 * 更新完数据到正式表，将临时表的同状态改为:00-已同步
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateTempSyncState(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod5",
				RqlParam.map().set("syncState", MchtContractConstants.SYNC_STATE_00).set("auditId", auditId));
	}

	/**
	 * 商户合同临时表，商户合同状态，新增待审核被拒绝时，CON_STATE 05：新增被拒绝；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void MchtContrInfoTmpRefuse(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod6",
				RqlParam.map().set("conState", MchtContractConstants.MCHT_STAT_05).set("auditId", auditId));
	}

	/**
	 * 记录此次审核记录，插入到审核记录表中，审核拒绝
	 */
	public void insertAuditStepInfoRefuse(String auditId, Integer stepNo, Integer roleId, String tlrno)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取审核记录实体对象
		PmpAuditStepInfo pmpAuditStepInfo = new PmpAuditStepInfo();
		pmpAuditStepInfo.setSeqId(ContextUtil.getUUID());// 记录编号
		pmpAuditStepInfo.setAuditId(auditId);// 审核信息编号
		pmpAuditStepInfo.setStepNo(stepNo);// 审核步骤编号
		pmpAuditStepInfo.setAuditState(MchtContractConstants.AUDIT_STATE_02);// 此时审核通过02-审核拒绝；
		pmpAuditStepInfo.setRoleId(roleId);// 审核角色
		pmpAuditStepInfo.setAuditOprNo(tlrno);// 审核人编号
		pmpAuditStepInfo.setAuditDatetIme(BaseUtil.getCurrentDateTime());// 审核日期时间
																			// 14位
		pmpAuditStepInfo.setAuditView("");// 审核意见
		dao.insert(pmpAuditStepInfo);
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
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updateMethod7",
				RqlParam.map().set("auditState", MchtContractConstants.AUDIT_STATE_02).set("lastUpdDateTime", time)
						.set("auditId", auditId));
	}

	/**
	 * 商户正式表中，根据商户ID获取商户基本信息表中的商户名字；
	 * 
	 * @param mchtId
	 * @throws SnowException
	 */
	public String selectMchtNameById(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.selectMchtNameById",
				RqlParam.map().set("mchtId", mchtId));

	}

    /**
     * @param mchtId 
     * @param accountType
     * @param setlAcctName
     * @throws SnowException 
     */
    public void updPbsassit(String mchtId,String accountType, String setlAcctName) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = "SELECT * FROM pbs_mcht_assist_info WHERE MCHT_ID = '"+mchtId+"'";
        List<Object> list = dao.executeQuerySql(sql, PbsMchtAssistInfo.class);
        if (IfspDataVerifyUtil.isNotEmptyList(list)) {
            log.info("开始更新商户("+mchtId+")信息辅表临时表结算账户类型与结算账户户名....");
            dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updPbsassistTmp", 
                    RqlParam.map().set("mchtId", mchtId).set("accountType", accountType).set("setlAcctName", setlAcctName));
            log.info("更新结束....");
            log.info("开始更新商户("+mchtId+")信息辅表正式表结算账户类型与结算账户户名....");
            dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.updPbsassist", 
                    RqlParam.map().set("mchtId", mchtId).set("accountType", accountType).set("setlAcctName", setlAcctName));
            log.info("更新结束....");
        }
        
    }
}
