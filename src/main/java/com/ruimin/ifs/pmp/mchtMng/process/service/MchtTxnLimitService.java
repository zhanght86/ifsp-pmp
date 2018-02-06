package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtTxnLimitInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtTxnLimitInfoTmp;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;

/**
 * 
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司 Author: chengGX Date:
 * 2016年9月21日下午3:06:32 History: //修改记录 Version: 0.1 Desc:
 */
@Service
@SnowDoc(desc = "交易限额")
public class MchtTxnLimitService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtTxnLimitService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtTxnLimitService.class);
	}

	/**
	 * 
	 * 功能描述: 增加临时表中交易限额的实体类
	 * 
	 * @param MchtTxnLimitInfoTmp
	 * @return void
	 * @throws SnowException
	 */
	public void addMchtTxnLitTmp(MchtTxnLimitInfoTmp mchtTxnLitTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtTxnLitTmp);
	}

	/**
	 * 功能描述: 修改临时表中交易限额的实体类
	 * 
	 * @param MchtTxnLimitInfoTmp
	 * @return void
	 * @throws SnowException
	 */
	public void updMchtTxnLitTmp(MchtTxnLimitInfoTmp mchtTxnLitTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(mchtTxnLitTmp);
	}

	/**
	 * 功能描述: 删除临时表中交易限额的实体类
	 * 
	 * @param MchtTxnLimitInfoTmp
	 * @return void
	 * @throws SnowException
	 */
	public void delMchtTxnLitTmp(MchtTxnLimitInfoTmp mchtTxnLitTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(mchtTxnLitTmp);
	}

	/**
	 * 功能描述: 删除临时表交易限额的实体类
	 * 
	 * @param String
	 * @return void
	 * @throws SnowException
	 */
	public void delMchtTxnLitTmpByMchtId(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtTxnLimitInfo.delMchtTxnLitTmpByMchtId", mchtId);
	}

	/**
	 * 功能描述: 删除正式表交易限额的实体类
	 * 
	 * @param String
	 * @return void
	 * @throws SnowException
	 */
	public void delMchtTxnLitByMchtId(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtTxnLimitInfo.delMchtTxnLitByMchtId", mchtId);
	}

	/**
	 * 审核通过:把商户合同的基本信息插入到正式表中；
	 * 
	 * @throws SnowException
	 */
	public void insertMchtTxnLitByMchtId(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String mchtId = mchtVo.getMchtId();
		MchtTxnLimitInfo mchtTxnLimitInfo = new MchtTxnLimitInfo();
		MchtTxnLimitInfoTmp mchtTxnLimitInfoTmp = new MchtTxnLimitInfoTmp();
		// 临时表实体内容，对应到正式表实体对象中最近操作时间，操作人
		mchtTxnLimitInfoTmp = (MchtTxnLimitInfoTmp) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.mchtTxnLimitInfo.queryMchtTxnLitTmpByMchtId",
				RqlParam.map().set("mchtId", mchtId));
		// 临时bean 和 正式的bean进行对接转化
		ReflectionUtil.copyProperties(mchtTxnLimitInfoTmp, mchtTxnLimitInfo);
		// 最近更新操作员，最近更新时间
		mchtTxnLimitInfo.setLastUpdTlr(tlrno);
		mchtTxnLimitInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		// 正式表不为空，则插入到临时表中。
		dao.insert(mchtTxnLimitInfo);
	}

	/**
	 * 审核拒绝把正式表中的数据回滚到临时表中
	 * 
	 * @throws SnowException
	 */
	public void insertMchtTxnLitTmpByMchtId(MchtContractVO mchtVo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		String mchtId = mchtVo.getMchtId();
		MchtTxnLimitInfo mchtTxnLimitInfo = new MchtTxnLimitInfo();
		MchtTxnLimitInfoTmp mchtTxnLimitInfoTmp = new MchtTxnLimitInfoTmp();

		// 临时表实体内容，对应到正式表实体对象中，最近操作时间，操作人
		mchtTxnLimitInfo = (MchtTxnLimitInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.mchtTxnLimitInfo.queryMchtTxnLitByMchtId",
				RqlParam.map().set("mchtId", mchtId));
		// 正式表数据copy到临时表中
		ReflectionUtil.copyProperties(mchtTxnLimitInfo, mchtTxnLimitInfoTmp);
		// 最近更新操作员，最近更新时间
		mchtTxnLimitInfoTmp.setLastUpdTlr(tlrno);
		mchtTxnLimitInfoTmp.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		dao.insert(mchtTxnLimitInfoTmp);

	}
}
