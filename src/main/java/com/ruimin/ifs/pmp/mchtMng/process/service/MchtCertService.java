package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

/**
 * 
 * 〈商户证书配置〉<br>
 * 
 * @author GH
 */
@Service
public class MchtCertService extends SnowService {

	public static MchtCertService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtCertService.class);
	}

	/**
	 * 
	 * 功能描述: 查询商户证书列表<br>
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public PageResult queryList(String mchtId, String mchtSimpleName, String certifiStatus, Page page)
			throws SnowException {

		DBDao dao = DBDaos.newInstance();

		/*
		 * List<Object> dataList =
		 * dao.selectList("com.ruimin.ifs.pmp.mcht.rql.mchtCert.queryList",
		 * RqlParam.map() .set("mchtId", StringUtils.isBlank(mchtId)?"" :
		 * "%"+mchtId+"%") .set("mchtSimpleName",
		 * StringUtils.isBlank(mchtSimpleName)?"" : "%"+mchtSimpleName+"%")
		 * .set("certifiStatus", StringUtils.isBlank(certifiStatus)?"" :
		 * "%"+certifiStatus+"%")); List<OnlCertifiUploadInfo> listNew = new
		 * ArrayList<OnlCertifiUploadInfo>(); int size;
		 * if(page.getLimit()>dataList.size()){ size=dataList.size(); }else{
		 * size=page.getLimit(); } for(int i=page.getOffset()-1;i<size;i++){
		 * OnlCertifiUploadInfo bean = (OnlCertifiUploadInfo) dataList.get(i);
		 * //BASE64解码 listNew.add(bean); } PageQueryResult result = new
		 * PageQueryResult(); result.setQueryResult(listNew);
		 * result.setTotalCount(dataList.size()); return result;
		 */

		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtCert.queryList",
				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%" + mchtId + "%")
						.set("mchtSimpleName", StringUtils.isBlank(mchtSimpleName) ? "" : "%" + mchtSimpleName + "%")
						.set("certifiStatus", StringUtils.isBlank(certifiStatus) ? "" : certifiStatus),
				page);
	}

	/**
	 * 通过商户号查询商户简称(临时表)
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtBaseInfo queryMchtSimpNameById(String mchtId) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.select(PbsMchtBaseInfo.class, mchtId);
	}

	/**
	 * 通过商户号查询商户简称(正式表)
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtBaseInfoReal queryMchtSimpNameRealById(String mchtId) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.select(PbsMchtBaseInfoReal.class, mchtId);
	}

	/**
	 * 通过证书编号获取证书名称
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public MchtCertInfoVO queryMchtCertNameById(String certifiId, String certifiType) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.select(MchtCertInfoVO.class, certifiId, certifiType);
	}

	/**
	 * 新增商户证书
	 * 
	 * @param mchtCertInfoVO
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	public void addMchtCertInfo(MchtCertInfoVO mchtCertInfoVO) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		mchtCertInfoVO.setRecCrtOpr(sessionUserInfo.getTlrno());// 创建柜员
		mchtCertInfoVO.setRecCrtTs(BaseUtil.getCurrentDateTime());// 创建时间
		mchtCertInfoVO.setRecUpdOpr(sessionUserInfo.getTlrno());// 最近更新柜员
		mchtCertInfoVO.setRecUpdTs(BaseUtil.getCurrentDateTime());// 最近更新时间

		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtCertInfoVO);
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
	 * 查询商户号是否重复
	 * 
	 * @param certifiBl
	 * @return
	 * @throws SnowException
	 */
	public Integer queryMcht(String certifiBl) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtCert.queryMchtExits",
				RqlParam.map().set("certifiBl", StringUtils.isBlank(certifiBl) ? "" : certifiBl));
	}

	/**
	 * 查询证书编号是否重复
	 * 
	 * @param certifiBl
	 * @return
	 * @throws SnowException
	 */
	public Integer queryCert(String certifiId) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtCert.queryCertExits",
				RqlParam.map().set("certifiId", StringUtils.isBlank(certifiId) ? "" : certifiId));
	}

}
