package com.ruimin.ifs.pmp.accessPara.process.service;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtCertInfoVO;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@Service
public class AccessParaService extends SnowService{
	
	public static AccessParaService getInstance() throws SnowException {
		return ContextUtil.getSingleService(AccessParaService.class);
	}

	public PageResult queryList(String certifiId, String certifiStatus, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.accessPara.rql.accessPara.queryList",
				RqlParam.map().set("certifiId", StringUtils.isBlank(certifiId) ? "" : "%" + certifiId + "%")
						.set("certifiStatus", StringUtils.isBlank(certifiStatus) ? "" : certifiStatus),
				page);
	}

	/**
	 * 更新商户证书的信息和状态
	 * 
	 * @param mchtCertInfoVO
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void update(MchtCertInfoVO mchtCertInfoVO) throws SnowException {

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		mchtCertInfoVO.setRecUpdOpr(sessionUserInfo.getTlrno());// 最近更新柜员
		mchtCertInfoVO.setRecUpdTs(BaseUtil.getCurrentDateTime());// 最近更新时间

		DBDao dao = DBDaos.newInstance();
		dao.update(mchtCertInfoVO);
	}

	/**
	 * 查询证书编号是否重复
	 * 
	 * @param certifiBl
	 * @return
	 * @throws SnowException
	 */
	/*public Integer queryCert(String certifiId) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.accessPara.rql.accessPara.queryCertExits",
				RqlParam.map().set("certifiId", StringUtils.isBlank(certifiId) ? "" : certifiId));
		
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtCert.queryMchtExits",
				RqlParam.map().set("certifiBl", StringUtils.isBlank(certifiBl) ? "" : certifiBl));
	}*/

	/**
	 * 新增商户证书
	 * 
	 * @param mchtCertInfoVO
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void addCertInfo(MchtCertInfoVO mchtCertInfoVO) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		mchtCertInfoVO.setRecCrtOpr(sessionUserInfo.getTlrno());// 创建柜员
		mchtCertInfoVO.setRecCrtTs(BaseUtil.getCurrentDateTime());// 创建时间
		mchtCertInfoVO.setRecUpdOpr(sessionUserInfo.getTlrno());// 最近更新柜员
		mchtCertInfoVO.setRecUpdTs(BaseUtil.getCurrentDateTime());// 最近更新时间

		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtCertInfoVO);
	}
}
