package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.po.TblCurInf;
import com.ruimin.ifs.po.TblCurRate;

/**
 * 汇率信息维护
 * 
 * @author shaoqin
 *
 */
@Service
public class CurrrateMngService extends SnowService {

	public static CurrrateMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(CurrrateMngService.class);
	}

	public PageResult queryList(String curcd, String tocurcd, String currrateDate, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryCurrrateMng",
				RqlParam.map().set("curcd", curcd == null ? "" : curcd).set("tocurcd", tocurcd == null ? "" : tocurcd)
						.set("currrateDate", currrateDate == null ? "" : currrateDate),
				page);
	}

	/**
	 * 根据中文简称获取中文名称
	 * 
	 * @param brCode
	 * @return
	 * @throws SnowException
	 */
	public TblCurInf queryCurcdName(String curcd) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TblCurInf.class, curcd);
	}

	public void saveTblCurRate(TblCurRate tblCurRate) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(tblCurRate);
	}

	public void updateTblCurRate(TblCurRate tblCurRate) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(tblCurRate);
	}

	public void deletTblCurRate(TblCurRate tblCurRate) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(tblCurRate);
	}

	public void deleteTblCurRateById(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblCurRate.class, id);
	}
}
