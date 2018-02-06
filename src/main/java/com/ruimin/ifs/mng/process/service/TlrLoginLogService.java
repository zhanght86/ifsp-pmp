package com.ruimin.ifs.mng.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.mng.process.bean.LoginLogVO;

@Service
public class TlrLoginLogService extends SnowService {
	public static TlrLoginLogService getInstance() throws SnowException {
		return ContextUtil.getSingleService(TlrLoginLogService.class);
	}

	/**
	 * 机构下拉查询
	 * 
	 * @return
	 * @throws SnowException
	 */
	public List<LoginLogVO> queryBctlAllSelect() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select brcode,brno,brname from ifs_org", LoginLogVO.class);
	}

	/**
	 * 登录日志查询
	 * 
	 * @param queryTlrno
	 *            操作员编号
	 * @param queryBrcode
	 *            机构名称
	 * @param page
	 *            分页查询
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryList(String queryTlrno, String queryBrcode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryLogByTlrno",
				RqlParam.map().set("queryTlrno", queryTlrno == null ? "" : "%" + queryTlrno + "%").set("queryBrcode",
						queryBrcode == null ? "" : queryBrcode),
				page);
	}

	/**
	 * 登录日志详情查询
	 * 
	 * @param queryTlrno
	 *            操作员编号
	 * @param queryBrcode
	 *            机构名称
	 * @param queryLastaccesstm
	 * @param queryLastlogouttm
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryListAll(String queryTlrno, String queryBrcode, String queryLastaccesstm,
			String queryLastlogouttm, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryLogDetail",
				RqlParam.map().set("queryTlrno", queryTlrno == null ? "" : "%" + queryTlrno + "%")
						.set("queryBrcode", queryBrcode == null ? "" : queryBrcode)
						.set("queryLastaccesstm", queryLastaccesstm == null ? "" : queryLastaccesstm)
						.set("queryLastlogouttm", queryLastlogouttm == null ? "" : queryLastlogouttm),
				page);
	}

}
