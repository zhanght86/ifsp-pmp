package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.sysConf.process.bean.PassBankVO;

@SnowDoc(desc = "通道银行信息配置")
public class PassBankService extends SnowService {

	public static PassBankService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PassBankService.class);
	}

	/**
	 * 查询出所有通道状态正常的通道编号和通道名称
	 * 
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAllPassInfo(Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.paydep.sysConf.rql.passInfo.queryPassNoName", page);
	}

	/**
	 * 根据通道名称，银行编号，银行名称查询记录
	 * 
	 * @param qPassName
	 * @param qBankCode
	 * @param qBankName
	 * @param page
	 * @return
	 */
	public PageResult queryPassBankByCodeInfo(String qPassName, String qBankCode, String qBankName, Page page) {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.paydep.sysConf.rql.passBank.queryPassBankInfo",
				RqlParam.map().set("qPassName", StringUtils.isBlank(qPassName) ? "" : qPassName)
						.set("qBankCode", StringUtils.isBlank(qBankCode) ? "" : qBankCode)
						.set("qBankName", StringUtils.isBlank(qBankName) ? "" : "%" + qBankName + "%"),
				page);
	}

	/**
	 * 通过通道编号和银行编号检查该条记录是否存在
	 * 
	 * @param passNo
	 * @param bankNo
	 * @return
	 */
	public int queryPassBankExits(String passNo, String bankNo) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.paydep.sysConf.rql.passBank.queryPassBankExits",
				RqlParam.map().set("passNo", StringUtils.isBlank(passNo) ? "" : "%" + passNo + "%").set("bankNo",
						StringUtils.isBlank(bankNo) ? "" : "%" + bankNo + "%"));
	}

	/**
	 * 添加一条通道银行配置信息
	 * 
	 * @param passBankVO
	 * @throws SnowException
	 */
	public void addPassBankInfo(PassBankVO passBankVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		passBankVO.setDataState("00");
		passBankVO.setCrtTlr(sessionUserInfo.getTlrno());
		passBankVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		passBankVO.setCrtDateTime(DateUtil.getCurrDate());
		passBankVO.setLastUpdDateTime(DateUtil.getCurrDate());

		DBDao dao = DBDaos.newInstance();
		dao.insert(passBankVO);

		getLogger().info("添加通道银行配置:passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo() + "bankName="
				+ passBankVO.getBankName());

		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						"添加通道银行配置:passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo() + "bankName="
								+ passBankVO.getBankName() });
	}

	/**
	 * 修改一条通道银行配置信息
	 * 
	 * @param passBankVO
	 * @throws SnowException
	 */
	public void updPassBankInfo(PassBankVO passBankVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		passBankVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		passBankVO.setLastUpdDateTime(DateUtil.getCurrDate());

		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.paydep.sysConf.rql.passBank.updPassBankInfo", passBankVO);

		getLogger().info("修改通道银行配置:passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo() + "bankName="
				+ passBankVO.getBankName());

		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						"修改通道银行配置:passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo() + "bankName="
								+ passBankVO.getBankName() });
	}

	/**
	 * 更新通道银行实体类
	 * 
	 * @param passBankVO
	 * @throws SnowException
	 */
	public void updatePassBankState(String passNo, String bankNo, String dataState) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		PassBankVO passBankVO = new PassBankVO();
		passBankVO.setPassNo(passNo);
		passBankVO.setBankNo(bankNo);
		passBankVO.setDataState(dataState);
		passBankVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		passBankVO.setLastUpdDateTime(DateUtil.getCurrDate());

		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.paydep.sysConf.rql.passBank.updatePassBankState", passBankVO);

		getLogger().info("修改通道银行配置:passNo=" + passBankVO.getPassNo() + "bankNo=" + passBankVO.getBankNo() + "dataState="
				+ passBankVO.getDataState());

	}

}
