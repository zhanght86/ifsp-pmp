package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
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
import com.ruimin.ifs.framework.utils.SysParamUtil;
import com.ruimin.ifs.po.TblSysParam;

/*
 * 系统参数service
 *
 */
@Service
public class SysParamsSecService extends SnowService {

	public static SysParamsSecService getInstance() throws SnowException {
		return ContextUtil.getSingleService(SysParamsSecService.class);
	}

	public PageResult queryList(String queryParamId, String queryOprcode1, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.querySysParam",
				RqlParam.map().set("queryParamId", queryParamId == null ? "" : "%" + queryParamId.toUpperCase() + "%")
						.set("queryOprcode1", queryOprcode1 == null ? "" : "%" + queryOprcode1.toUpperCase() + "%"),
				page);
	}

	public String queryValueByMagicId(String magicId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryValueByMagicId", magicId);
	}

	public void updateSysParamsSec(TblSysParam tblSysParam) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(tblSysParam);
		SysParamUtil.getInstance().update(tblSysParam.getMagicId(), tblSysParam.getParamId(),
				tblSysParam.getParamValueTx());
	}

	/* add by ttt 20151104 */
	public void delSysParams(String paramId, String magicId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblSysParam.class, paramId, magicId);
	}

	/**
	 * 新增参数
	 * 
	 * @param
	 * @throws SnowException
	 */
	public void addSysParams(TblSysParam tblSysParam) throws SnowException {

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		tblSysParam.setLastUpdTlr(sessionUserInfo.getTlrno());
		tblSysParam.setParamChangFlag("1");
		tblSysParam.setLastUpdDate(DateUtil.getCurrDate());
		DBDao dao = DBDaos.newInstance();
		dao.insert(tblSysParam);
		getLogger().info("添加新参数信息:" + tblSysParam.getParamId());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"添加新地区:getParamId=" + tblSysParam.getParamId() });
	}

	/* end add */

}
