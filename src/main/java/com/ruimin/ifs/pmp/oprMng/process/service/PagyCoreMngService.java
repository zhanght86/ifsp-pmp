package com.ruimin.ifs.pmp.oprMng.process.service;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;

@Service
@SnowDoc(desc = "通道核心配置管理")
public class PagyCoreMngService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static PagyCoreMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyCoreMngService.class);
	}

	/**
	 * 查询【通道核心配置情况】
	 * 
	 * @param qpagyNo
	 *            通道编号
	 * @param qpagyTxnCode
	 *            通道交易编号
	 * @param qacctTypeNo
	 *            账户类型编号
	 * @param qmainMchtNo
	 *            主商户编号
	 * @param qpayTxnCode
	 *            接入交易编号
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAll(String qpagyNo, String qpagyTxnCode, String qacctTypeNo, String qmainMchtNo,
			String qpayTxnCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.oprMng.rql.pagyCoreMng.queryAll",
				RqlParam.map().set("qpagyNo", StringUtils.isBlank(qpagyNo) ? "" : qpagyNo)
						.set("qpagyTxnCode", StringUtils.isBlank(qpagyTxnCode) ? "" : qpagyTxnCode)
						.set("qacctTypeNo", StringUtils.isBlank(qacctTypeNo) ? "" : qacctTypeNo)
						.set("qmainMchtNo", StringUtils.isBlank(qmainMchtNo) ? "" : qmainMchtNo)
						.set("qpayTxnCode", StringUtils.isBlank(qpayTxnCode) ? "" : qpayTxnCode),
				page);
	}
}
