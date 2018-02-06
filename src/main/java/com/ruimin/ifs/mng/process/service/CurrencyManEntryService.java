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

/**
 * 币种信息维护
 * 
 * @author shaoqin
 *
 */
@Service
public class CurrencyManEntryService extends SnowService {

	public static CurrencyManEntryService getInstance() throws SnowException {
		return ContextUtil.getSingleService(CurrencyManEntryService.class);
	}

	public PageResult queryList(String qcurcd, String qcurname, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryCurrencyManEntry",
				RqlParam.map().set("qcurcd", qcurcd == null ? "" : "%" + qcurcd.toUpperCase() + "%").set("qcurname",
						qcurname == null ? "" : "%" + qcurname.toUpperCase() + "%"),
				page);
	}

	public TblCurInf checkId(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TblCurInf.class, id);
	}

	public void saveDataDicEntry(TblCurInf tblCurInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(tblCurInf);
	}

	public void updateDataDicEntry(TblCurInf tblCurInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(tblCurInf);
	}

	public void deletDataDicEntry(TblCurInf tblCurInf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(tblCurInf);
	}

	public void deleteDataDicEntryById(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblCurInf.class, id);
	}
}
