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
import com.ruimin.ifs.mng.process.bean.BiNationregionEntryVO;
import com.ruimin.ifs.po.TblDataDic;

/*
 * 国家地区代码维护
 *
 */
@Service
public class BiNationregionEntryService extends SnowService {

	public static BiNationregionEntryService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BiNationregionEntryService.class);
	}

	public PageResult queryList(String qid, String qnationregionNumber, String cnEnFullName, String cnEnShortName,
			Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryBiNationregionEntry",
				RqlParam.map().set("qid", qid == null ? "" : "%" + qid.toUpperCase() + "%")
						.set("qnationregionNumber", qnationregionNumber == null ? "" : "%" + qnationregionNumber + "%")
						.set("cnEnFullName", cnEnFullName == null ? "" : "%" + cnEnFullName + "%").set("cnEnShortName",
								cnEnShortName == null ? "" : "%" + cnEnShortName.toUpperCase() + "%"),
				page);
	}

	public void saveBiNationregionEntry(BiNationregionEntryVO biNationregionEntryVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(biNationregionEntryVO);
	}

	public void updateBiNationregionEntry(BiNationregionEntryVO biNationregionEntryVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(biNationregionEntryVO);
	}

	public void deletBiNationregionEntry(BiNationregionEntryVO biNationregionEntryVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(biNationregionEntryVO);
	}

	public void deleteBiNationregionEntryById(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblDataDic.class, id);
	}
}
