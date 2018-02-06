/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpAuditSeqInfoService.java
 * Author:   MJ
 * Date:     2015年12月7日 下午6:39:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.mng.process.bean.ImpAuditSeqInfo;
import com.ruimin.ifs.po.ImpTermInfTmp;
import com.ruimin.ifs.util.BaseUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author MJ
 */
public class ImpAuditSeqInfoService extends SnowService {

	public static ImpAuditSeqInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ImpAuditSeqInfoService.class);
	}

	/**
	 * 插入商户审核流水
	 * 
	 * @param mchtId
	 * @param auditId
	 * @param auditLevel
	 * @param currrole
	 * @param auditSta
	 * @param opr
	 * @return
	 * @throws SnowException
	 */
	public String insert(String mchtId, String auditId, String auditLevel, String currrole, String auditSta, String opr,
			String type) throws SnowException {
		ImpAuditSeqInfo seqInfo = new ImpAuditSeqInfo();
		seqInfo.setSeqId(ContextUtil.getUUID());
		seqInfo.setAuditId(auditId);
		seqInfo.setAuditIndex(mchtId);
		seqInfo.setAuditLevel(auditLevel);// 审核级别
		seqInfo.setAuditRole(currrole);
		seqInfo.setAuditType(type);// 商户审核
		seqInfo.setAuditOpr(SessionUserInfo.getSessionUserInfo().getTlrno());
		seqInfo.setAuditStat(auditSta);
		seqInfo.setAuditCrt(BaseUtil.getCurrentDateTime());
		seqInfo.setOprDesc("[编号:" + mchtId + "]" + opr);
		save(seqInfo);
		return seqInfo.getSeqId();
	}

	// /**
	// * 插入商户审核流水
	// * @param baseInfo
	// * @param currrole
	// * @param opr
	// * @return
	// * @throws SnowException
	// */
	// public String insert(ImpMchtInfTmp baseInfo, String currrole, String opr)
	// throws SnowException{
	// ImpAuditSeqInfo seqInfo = new ImpAuditSeqInfo();
	// seqInfo.setSeqId(ContextUtil.getUUID());
	// seqInfo.setAuditId(baseInfo.getAuditFlag());
	// seqInfo.setAuditIndex(baseInfo.getMchtId());
	// seqInfo.setAuditLevel("00");// 审核级别
	// seqInfo.setAuditRole(currrole);
	// seqInfo.setAuditType("00");// 商户审核
	// seqInfo.setAuditOpr(SessionUserInfo.getSessionUserInfo().getTlrno());
	// seqInfo.setAuditStat("1");
	// seqInfo.setAuditCrt(BaseUtil.getCurrentDateTime());
	// seqInfo.setOprDesc(opr + "[编号:" + baseInfo.getMchtId() + "]");
	// save(seqInfo);
	// return seqInfo.getSeqId();
	// }

	/**
	 * 插入审核流水
	 * 
	 * @param mchtId
	 * @param opr
	 * @return
	 * @throws SnowException
	 */
	public String insert(String id, String opr, String type) throws SnowException {
		ImpAuditSeqInfo seqInfo = new ImpAuditSeqInfo();
		seqInfo.setSeqId(ContextUtil.getUUID());
		seqInfo.setAuditIndex(id);
		seqInfo.setAuditId("0000");
		seqInfo.setAuditLevel("0");// 审核级别
		seqInfo.setAuditRole("111");
		seqInfo.setAuditType(type);// 商户审核
		seqInfo.setAuditOpr(SessionUserInfo.getSessionUserInfo().getTlrno());
		seqInfo.setAuditStat("1");
		seqInfo.setAuditCrt(BaseUtil.getCurrentDateTime());
		seqInfo.setOprDesc(opr + "[编号:" + id + "]");
		save(seqInfo);
		return seqInfo.getSeqId();
	}

	/**
	 * 插入终端审核流水
	 * 
	 * @param baseInfo
	 * @param currrole
	 * @param opr
	 * @return
	 * @throws SnowException
	 */
	public String insert(ImpTermInfTmp baseInfo, String currrole, String opr) throws SnowException {
		ImpAuditSeqInfo seqInfo = new ImpAuditSeqInfo();
		seqInfo.setSeqId(ContextUtil.getUUID());
		seqInfo.setAuditId(baseInfo.getMisc1());
		seqInfo.setAuditIndex(baseInfo.getTermId());
		seqInfo.setAuditLevel("00");// 审核级别
		seqInfo.setAuditRole(currrole);
		seqInfo.setAuditType("01");// 商户审核
		seqInfo.setAuditOpr(SessionUserInfo.getSessionUserInfo().getTlrno());
		seqInfo.setAuditStat("1");
		seqInfo.setAuditCrt(BaseUtil.getCurrentDateTime());
		seqInfo.setOprDesc(opr + "[编号:" + baseInfo.getTermId() + "]");
		save(seqInfo);
		return seqInfo.getSeqId();
	}

	public void save(ImpAuditSeqInfo info) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(info);
	}

	public ImpAuditSeqInfo queryLastNodeById(String seqId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(ImpAuditSeqInfo.class, seqId);
	}
}
