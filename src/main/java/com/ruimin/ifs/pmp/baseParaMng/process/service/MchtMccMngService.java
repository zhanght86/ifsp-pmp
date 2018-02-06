package com.ruimin.ifs.pmp.baseParaMng.process.service;

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
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtGrpInfo;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.PbsMchtMccInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@Service
@SnowDoc(desc = "商户组别管理")
public class MchtMccMngService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtMccMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtMccMngService.class);
	}

	/**
	 * 商户组别页面查询
	 * 
	 * @throws SnowException
	 */
	public PageResult queryMain(String qmccId, String qmccDesc, String qmchtGrpId, Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.mchtMccMng.queryMainInfo",
				RqlParam.map().set("qmccId", StringUtils.isBlank(qmccId) ? "" : qmccId)
						.set("qmccDesc", StringUtils.isBlank(qmccDesc) ? "" : "%" + qmccDesc + "%")
						.set("qmchtGrpId", StringUtils.isBlank(qmchtGrpId) ? "" : qmchtGrpId),
				page);
	}

	/**
	 * 功能描述: 查询<br>
	 * 
	 * @param id
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtGrpInfo queryGrpName(String id) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return dao.select(PbsMchtGrpInfo.class, id);
	}

	/**
	 * 判断商户Mcc是否存在
	 * 
	 * @param mchtGrpNo
	 * @return
	 * @throws SnowException
	 */
	public int checkMchtMccExist(String mccId) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.mchtMccMng.checkMchtMccExist",
				RqlParam.map().set("mccId", mccId));
	}

	/**
	 * 新增
	 * 
	 * @param PbsMchtMccInfo
	 *            商户MCC
	 * @throws SnowException
	 */
	public void addMchtMcc(PbsMchtMccInfo mchtMccMngVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		mchtMccMngVO.setMccState("00");
		mchtMccMngVO.setCrtTlr(sessionUserInfo.getTlrno());
		mchtMccMngVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		mchtMccMngVO.setCrtDateTime(BaseUtil.getCurrentDateTime());
		mchtMccMngVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());

		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtMccMngVO);
	}

	/**
	 * 修改
	 * 
	 * @param PbsMchtMccInfo
	 *            商户Mcc
	 * @throws SnowException
	 */
	public void updMchtMcc(PbsMchtMccInfo mchtMccMngVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		mchtMccMngVO.setLastUpdTlr(sessionUserInfo.getTlrno());

		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		mchtMccMngVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());

		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.mchtMccMng.updMchtMcc", mchtMccMngVO);
	}

	/**
	 * 在MCC表中查找改组别下的MCC信息记录
	 * 
	 * @param mchtGrpNo
	 * @return
	 * @throws SnowException
	 */
	public int queryGrpByMchtMccId(String mccId) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.mchtMccMng.queryGrpByMchtMccId",
				RqlParam.map().set("mccId", mccId).set("grpState", "00").set("pagyNo", "301"));
	}

	/**
	 * 删除该组别
	 * 
	 * @param mchtGrpId
	 * @throws SnowException
	 */
	public void delMchtMcc(String mccId) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.mchtMccMng.delMchtMcc",
				RqlParam.map().set("mccId", StringUtils.isBlank(mccId) ? "" : mccId));
	}
}
