package com.ruimin.ifs.pmp.payProdMng.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtMngVO;
import com.ruimin.ifs.pmp.payProdMng.common.constants.PayProdConstants;
import com.ruimin.ifs.pmp.payProdMng.process.bean.AccountTypeVO;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfoVO;
import com.ruimin.ifs.pmp.payProdMng.process.bean.TxnTypeVO;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.sysConf.process.bean.AccessTypeInfo;

@Service
public class PbsProdInfoService extends SnowService {
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static PbsProdInfoService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(PbsProdInfoService.class);
	}

	/**
	 * 产品信息表查询
	 * 
	 * @param qprodId
	 * @param qprodName
	 * @param qprodState
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryPbsProdInfo(String qprodId, String qprodName, String qprodState, Page page)
			throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.queryPbsProdInfo",
				RqlParam.map().set("qprodId", StringUtils.isBlank(qprodId) ? "" : "%" + qprodId + "%")
						.set("qprodName", StringUtils.isBlank(qprodName) ? "" : "%" + qprodName + "%")
						.set("qprodState", StringUtils.isBlank(qprodState) ? "" : "%" + qprodState + "%"),
				page);
	}

	// 机构下拉查询
	public PageResult querySelect(Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
        return  dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.querySelect",page);

	}
	
	   // 查询当前产品是否有商户使用
    public List<Object> queryMcht(String prodId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return  dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.queryMcht",RqlParam.map().set("prodId", StringUtils.isBlank(prodId) ? "" :prodId));
    }
	// 查询当前产品编号的最大值
	public String queryMaxPbsProdNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.queryMaxPbsProdNo");
	}

	// 插入产品信息表
	public void insertPbsProdInfo(PbsProdInfoVO pbsProdInfoVO, SessionUserInfo sessionUserInfo) throws SnowException {
		String nextPbsProdNo = "";
		// 查询当前产品编号的最大值
		String maxPbsProdNo = queryMaxPbsProdNo();
		if (!StringUtil.isBlank(maxPbsProdNo)) {
			// 获取PD后面的数字
			String prodNo = maxPbsProdNo.substring(2);
			int num = Integer.valueOf(prodNo.trim());
			String nextPbsProdNoSum = String.valueOf(num + 1);
			// 组装产品编号
			nextPbsProdNo = "PD" + nextPbsProdNoSum;
		} else {
			nextPbsProdNo = PayProdConstants.PROD_NO_START;
		}
		DBDao dao = DBDaos.newInstance();
		// 如果新增时操作员选择启用状态时，记录开通日期为当前系统日期。
		String prodState = pbsProdInfoVO.getProdState();
		if (PayProdConstants.PROD_STATE_00.equals(prodState)) {
			String openDate = BaseUtil.getCurrentDate();// 当前时间，8位
			pbsProdInfoVO.setOpenDate(openDate);// 启用日期添加
		}
		pbsProdInfoVO.setProdId(nextPbsProdNo);// 设置产品编号
		pbsProdInfoVO.setCrtTlr(sessionUserInfo.getTlrno());// 创建柜员
		pbsProdInfoVO.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建时间
		pbsProdInfoVO.setLastUpdTlr(sessionUserInfo.getTlrno());// 最后操作人
		pbsProdInfoVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后操作时间
		// 插入数据库中
		dao.insert(pbsProdInfoVO);
	}

	// 插入账户关联表
	public void insertAccountTypeInfo(PbsProdInfoVO pbsProdInfoVO, String txnTypeCode, String acctTypeCode,
			String prodId, SessionUserInfo sessionUserInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 账户关联对象
		AccountTypeVO accountTypeVO = new AccountTypeVO();
		accountTypeVO.setProdId(prodId);// 产品编号
		accountTypeVO.setAcctTypeCode(acctTypeCode);// 账户编号
		accountTypeVO.setTxnTypeCode(txnTypeCode);// 交易编号
		accountTypeVO.setDataState(pbsProdInfoVO.getProdState());// 设置数据有效状态
		accountTypeVO.setCrtTlr(sessionUserInfo.getTlrno());// 设置创建柜员
		accountTypeVO.setCrtDateTime(BaseUtil.getCurrentDateTime());// 设置创建时间
		accountTypeVO.setLastUpdTlr(sessionUserInfo.getTlrno());// 最后操作人
		accountTypeVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后操作时间
		dao.insert(accountTypeVO);
	}

	// 插入商品交易类型关联表
	public void insertTxnTypeInfo(PbsProdInfoVO pbsProdInfoVO, String txnTypeCode, String prodId,
			SessionUserInfo sessionUserInfo) throws SnowException {
		TxnTypeVO TxnTypeVO = new TxnTypeVO();
		DBDao dao = DBDaos.newInstance();
		TxnTypeVO.setProdId(prodId);// 设置产品编号
		TxnTypeVO.setTxnTypeCode(txnTypeCode);// 设置交易编号
		TxnTypeVO.setDataState(pbsProdInfoVO.getProdState());// 设置数据有效状态
		TxnTypeVO.setCrtTlr(sessionUserInfo.getTlrno());// 设置创建柜员
		TxnTypeVO.setCrtDateTime(BaseUtil.getCurrentDateTime());// 设置创建时间
		TxnTypeVO.setLastUpdTlr(sessionUserInfo.getTlrno());// 最后操作人
		TxnTypeVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后操作时间
		dao.insert(TxnTypeVO);
	}

	/**
	 * 启用停用操作
	 */

	public void updatePbsProdInfoState(PbsProdInfoVO pbsProdInfoVO) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();
		// 获取产品编号
		String prodId = pbsProdInfoVO.getProdId();
		// 产品状态 00有效 99无效
		String prodState = pbsProdInfoVO.getProdState();
		// 最近更新柜员
		String lastUpdTlr = sessionUserInfo.getTlrno();
		// 最近更新时间取系统时间
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		String sql;
		if (PayProdConstants.PROD_STATE_00.equals(prodState)) {
			pbsProdInfoVO.setProdState(PayProdConstants.PROD_STATE_99);
			// 更新产品信息表
			sql = "update PBS_PROD_INFO set PROD_STATE ='" + pbsProdInfoVO.getProdState() + "'," + "LAST_UPD_TLR= '"
					+ lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' " + " where PROD_ID = '"
					+ prodId + "' ";
			dao.executeUpdateSql(sql);
			// 更新交易类型关联表
			String sqlTxn = "update PBS_PROD_REL_TXN_TYPE set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
					+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
					+ " where PROD_ID = '" + prodId + "' ";
			dao.executeUpdateSql(sqlTxn);
			// 更新账户关联表
			String sqlAcct = "update PBS_PROD_REL_TXN_ACCT set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
					+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
					+ " where PROD_ID = '" + prodId + "' ";
			dao.executeUpdateSql(sqlAcct);
		}
		if (PayProdConstants.PROD_STATE_99.equals(prodState)) {
			// 如果是首次 99 变为 00；则添加启用时间，根据产品编号查询开通时间
			// 如果为空，则是首次
			PbsProdInfoVO checkTime = selectOpenTime(prodId);
			String prodOpenDate = checkTime.getOpenDate();
			if (prodOpenDate == null || prodOpenDate.length() == 0) {
				pbsProdInfoVO.setProdState(PayProdConstants.PROD_STATE_00);// 产品状态
				// 设置启用时间
				pbsProdInfoVO.setOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
				// 更新产品信息表中的数据
				sql = "update PBS_PROD_INFO set PROD_STATE ='" + pbsProdInfoVO.getProdState() + "'," + "LAST_UPD_TLR= '"
						+ lastUpdTlr + "'," + "OPEN_DATE= '" + pbsProdInfoVO.getOpenDate() + "',"
						+ "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' " + " where PROD_ID = '" + prodId + "' ";
				dao.executeUpdateSql(sql);
				// 更新交易类型关联表
				String sqlTxn = "update PBS_PROD_REL_TXN_TYPE set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
						+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
						+ " where PROD_ID = '" + prodId + "' ";
				dao.executeUpdateSql(sqlTxn);
				// 更新账户关联表
				String sqlAcct = "update PBS_PROD_REL_TXN_ACCT set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
						+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
						+ " where PROD_ID = '" + prodId + "' ";
				dao.executeUpdateSql(sqlAcct);
			} else {
				pbsProdInfoVO.setProdState(PayProdConstants.PROD_STATE_00);// 产品状态
				// 更新产品信息表中的数据
				sql = "update PBS_PROD_INFO set PROD_STATE ='" + pbsProdInfoVO.getProdState() + "'," + "LAST_UPD_TLR= '"
						+ lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' " + " where PROD_ID = '"
						+ prodId + "' ";
				dao.executeUpdateSql(sql);
				// 更新交易类型关联表
				String sqlTxn = "update PBS_PROD_REL_TXN_TYPE set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
						+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
						+ " where PROD_ID = '" + prodId + "' ";
				dao.executeUpdateSql(sqlTxn);
				// 更新账户关联表
				String sqlAcct = "update PBS_PROD_REL_TXN_ACCT set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
						+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
						+ " where PROD_ID = '" + prodId + "' ";
				dao.executeUpdateSql(sqlAcct);
			}
		}
		if (PayProdConstants.PROD_STATE_02.equals(prodState)) {
			// 其实设置为启用状态
			pbsProdInfoVO.setProdState(PayProdConstants.PROD_STATE_00);
			;
			// 设置启用日期
			pbsProdInfoVO.setOpenDate(BaseUtil.getCurrentDate());// 当前时间，8位
			// 更新产品信息表中的数据
			sql = "update PBS_PROD_INFO set PROD_STATE ='" + pbsProdInfoVO.getProdState() + "'," + "LAST_UPD_TLR= '"
					+ lastUpdTlr + "'," + "OPEN_DATE= '" + pbsProdInfoVO.getOpenDate() + "'," + "LAST_UPD_DATE_TIME= '"
					+ lastUpdDateTime + "' " + " where PROD_ID = '" + prodId + "' ";
			dao.executeUpdateSql(sql);
			// 更新交易类型关联表
			String sqlTxn = "update PBS_PROD_REL_TXN_TYPE set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
					+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
					+ " where PROD_ID = '" + prodId + "' ";
			dao.executeUpdateSql(sqlTxn);
			// 更新账户关联表
			String sqlAcct = "update PBS_PROD_REL_TXN_ACCT set DATA_STATE ='" + pbsProdInfoVO.getProdState() + "',"
					+ "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '" + lastUpdDateTime + "' "
					+ " where PROD_ID = '" + prodId + "' ";
			dao.executeUpdateSql(sqlAcct);
		}
	}

	/**
	 * 根据ID查询渠道启用时间
	 * 
	 * @param chlId
	 * @return
	 * @throws SnowException
	 */
	public PbsProdInfoVO selectOpenTime(String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsProdInfoVO pbsProdInfoVO = (PbsProdInfoVO) dao.selectOne(
				"com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.selectOpenTime",
				RqlParam.map().set("prodId", StringUtils.isBlank(prodId) ? "" : prodId.trim()));
		return pbsProdInfoVO;
	}

	// 根据产品编号查询签约商户信息
	public List<MchtMngVO> querysigned(String qprodId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll(
				"select s.MCHT_ID,s.MCHT_NAME " + "FROM PBS_MCHT_BASE_INFO s where MCHT_ID in(select MCHT_ID from "
						+ "PBS_MCHT_CONTRACT_INFO where CON_ID in(select CON_ID from PBS_MCHT_REL_CONTRACT_PROD "
						+ "where PROD_ID='" + qprodId + "'))",
				MchtMngVO.class);
	}

	// 新增操作时候查询是否有重复的产品
	public boolean checkAdd(String accessTypeCode, String txnTypeCode, String acctTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.check",
				RqlParam.map().set("qaccessTypeCode", StringUtils.isBlank(accessTypeCode) ? "" : accessTypeCode)
						.set("qtxnTypeCode", StringUtils.isBlank(txnTypeCode) ? "" : txnTypeCode)
						.set("qacctTypeCode", StringUtils.isBlank(acctTypeCode) ? "" : acctTypeCode.split(",")));
		// 当没有查到数据，说明是新产品，返回false
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	// 查询是否有重复的交易类型
	public List<TxnTypeVO> queryTxnTypeCode(String nextPbsProdNo, String txnTypeCodeFlg) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select * from PBS_PROD_REL_TXN_TYPE where TXN_TYPE_CODE=" + txnTypeCodeFlg
				+ " and PROD_ID= '" + nextPbsProdNo + "' ", TxnTypeVO.class);
	}

	// 更改操作的时候查询是否有重复的产品
	public boolean checkUpt(String accessTypeCode, String txnTypeCode, String acctTypeCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.check",
				RqlParam.map().set("accessTypeCode", StringUtils.isBlank(accessTypeCode) ? "" : accessTypeCode)
						.set("txnTypeCode", StringUtils.isBlank(txnTypeCode) ? "" : txnTypeCode)
						.set("acctTypeCode", StringUtils.isBlank(acctTypeCode) ? "" : acctTypeCode));
		// 当没有查到数据，说明是新产品，返回false
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

	// 更改操作的时候根绝产品ID查询是否有重复的产品
	public PbsProdInfoVO selectByProdId(String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsProdInfoVO pbsProdInfoVO = (PbsProdInfoVO) dao.selectOne(
				"com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.selectByProdId",
				RqlParam.map().set("qprodId", StringUtils.isBlank(prodId) ? "" : prodId.trim()));
		return pbsProdInfoVO;
	}

	// 更改操作的时候查询是否有重复的产品
	public PbsProdInfoVO checkUptHos(String accessTypeCode, String txnTypeCode, String acctTypeCode)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsProdInfoVO pbsProdInfoVO = (PbsProdInfoVO) dao.selectOne(
				"com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.checkUptHos",
				RqlParam.map().set("qaccessTypeCode", StringUtils.isBlank(accessTypeCode) ? "" : accessTypeCode)
						.set("qtxnTypeCode", StringUtils.isBlank(txnTypeCode) ? "" : txnTypeCode)
						.set("qacctTypeCode", StringUtils.isBlank(acctTypeCode) ? "" : acctTypeCode.split(",")));
		return pbsProdInfoVO;
	}

	// 删除商品交易关联表
	public void deleteTxnInfo(String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from PBS_PROD_REL_TXN_TYPE where trim(PROD_ID)='" + prodId.trim() + "'";
		dao.executeUpdateSql(sql);
	}

	// 删除商品交易关联表
	public void deleteAcctInfo(String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from PBS_PROD_REL_TXN_ACCT where trim(PROD_ID)='" + prodId.trim() + "'";
		dao.executeUpdateSql(sql);
	}

	// 更新商品信息
	public void updateTxnInfo(PbsProdInfoVO pbsProdInfoVO) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();
		// 获取产品编号
		String prodId = pbsProdInfoVO.getProdId();
		// 最近更新柜员
		String lastUpdTlr = sessionUserInfo.getTlrno();
		// 最近更新时间取系统时间
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		String sql = "update PBS_PROD_INFO set PROD_NAME ='" + pbsProdInfoVO.getProdName() + "',"
				+ "ACCESS_TYPE_CODE= '" + pbsProdInfoVO.getAccessTypeCode() + "'," + "PROD_DESC= '"
				+ pbsProdInfoVO.getProdDesc() + "'," + "LAST_UPD_TLR= '" + lastUpdTlr + "'," + "LAST_UPD_DATE_TIME= '"
				+ lastUpdDateTime + "' " + " where trim(PROD_ID) = '" + prodId.trim() + "' ";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 查询接入方式名称
	 * 
	 * @param prodIds
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getAccessTypeCodeName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");

		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.TXN_TYPE_INFO.getAccessTypeCodeName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/**
	 * 产品信息表查询
	 * 
	 * @param qprodId
	 * @param qprodName
	 * @param qprodState
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryPbsProd(String qprodId, String qprodName, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.queryPbsProd",
				RqlParam.map().set("qprodId", StringUtils.isBlank(qprodId) ? "" : "%" + qprodId + "%").set("qprodName",
						StringUtils.isBlank(qprodName) ? "" : "%" + qprodName + "%"),
				page);
	}
	 /**
     * 根据产品编号查询产品名称
     * @param key
     * @return
     * @throws SnowException
     */
	public List<Object> getProdName(String key) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = key.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.getProdName", RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/**
	 *查询上级商户产品信息
	 *@param mchtID
	 * */
	public PageResult queryPbsProd1(String mchtId,Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.payProdMng.rql.PBS_PROD_INFO.queryPbsProd1",
				RqlParam.map().set("mchtId",mchtId),
				page);
	}
}
