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
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@Service
@SnowDoc(desc = "商户组别管理")
public class MchtGrpMngService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtGrpMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtGrpMngService.class);
	}

	/**
	 * 商户组别页面查询
	 * 
	 * @throws SnowException
	 */
	public PageResult queryMain(String qmchtGrpNo, String qgrpDesc, Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.baseParaMng.rql.mchtGrpMng.queryMainInfo",
				RqlParam.map().set("qmchtGrpNo", StringUtils.isBlank(qmchtGrpNo) ? "" : qmchtGrpNo).set("qgrpDesc",
						StringUtils.isBlank(qgrpDesc) ? "" : "%" + qgrpDesc + "%"),
				page);
	}

	/**
	 * 判断商户组别是否存在
	 * 
	 * @param mchtGrpNo
	 * @return
	 * @throws SnowException
	 */
	public int checkMchtGrpExist(String mchtGrpNo) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.mchtGrpMng.checkMchtGrpExist",
				RqlParam.map().set("mchtGrpNo", mchtGrpNo));
	}

	/**
	 * 新增
	 * 
	 * @param PbsMchtGrpInfo
	 *            商户组别
	 * @throws SnowException
	 */
	public void addMchtGrp(PbsMchtGrpInfo mchtGrpMngVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		mchtGrpMngVO.setGrpState("00");
		mchtGrpMngVO.setCrtTlr(sessionUserInfo.getTlrno());
		mchtGrpMngVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		mchtGrpMngVO.setCrtDateTime(BaseUtil.getCurrentDateTime());
		mchtGrpMngVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());

		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtGrpMngVO);
	}

	/**
	 * 修改
	 * 
	 * @param PbsMchtGrpInfo
	 *            商户组别
	 * @throws SnowException
	 */
	public void updMchtGrp(PbsMchtGrpInfo mchtGrpMngVO) throws SnowException {

		// 补充实体类信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		mchtGrpMngVO.setLastUpdTlr(sessionUserInfo.getTlrno());
		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		mchtGrpMngVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());

		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.mchtGrpMng.updMchtGrp", mchtGrpMngVO);
	}

	/**
	 * 在MCC表中查找改组别下的MCC信息记录
	 * 
	 * @param mchtGrpNo
	 * @return
	 * @throws SnowException
	 */
	public int queryMccByMchtGrpNo(String mchtGrpNo) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.baseParaMng.rql.mchtGrpMng.queryMccByMchtGrpNo",
				RqlParam.map().set("mchtGrpNo", mchtGrpNo));
	}

	/**
	 * 删除该组别
	 * 
	 * @param mchtGrpNo
	 * @throws SnowException
	 */
	public void delMchtGrp(String mchtGrpNo) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		dao.delete(PbsMchtGrpInfo.class, mchtGrpNo);
	}
}
