package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mktActivity.process.bean.AcctGpConfVO;

/**
 * 
 * 〈账户分组〉<br>
 * 
 * @author zhang_junchi
 */
@Service
public class AcctGpConfService extends SnowService {

	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static AcctGpConfService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(AcctGpConfService.class);
	}

	/**
	 * 
	 * 功能描述: 查询分组编号，名称,成员数量，活动使用列表<br>
	 * 
	 * @param qNum
	 * @param qNum
	 * 
	 */
	public PageResult queryAcctGpConfinfo(String qgpTpNo, String qgpTpNm, Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.querydDistinctNoName",
				RqlParam.map().set("qgpTpNo", StringUtils.isBlank(qgpTpNo) ? "" : qgpTpNo).set("qgpTpNm",
						StringUtils.isBlank(qgpTpNm) ? "" : "%" + qgpTpNm + "%"),
				page);

	}

	/**
	 * 
	 * 功能描述: 查询全表<br>
	 * 
	 */
	public PageResult queryAll(String qgpTpNo, Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.queryAll",
				RqlParam.map().set("qgpTpNo", StringUtils.isBlank(qgpTpNo) ? "" : qgpTpNo), page);

	}

	/**
	 * 
	 * 功能描述: 查询活动信息<br>
	 * 
	 */
	public PageResult queryActiveInfTmp(String qgpTpNo, Page page) throws SnowException {

		DBDao dao = DBDaos.newInstance();

		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.queryActiveInfTmp",
				RqlParam.map().set("qgpTpNo", StringUtils.isBlank(qgpTpNo) ? "" : qgpTpNo), page);

	}

	/**
	 * 根据分组编号查询单条分组信息
	 * 
	 */
	public AcctGpConfVO getOneGpByNo(String gpTpNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (AcctGpConfVO) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.querydDistinctNoName",
				RqlParam.map().set("qgpTpNo", gpTpNo).set("qgpTpNm", ""));

	}

	/**
	 * 根据分组编号查询小组编号和名称，返回一条数据，编号和名称为两个字段，以逗号分割
	 * 用于活动管理-方法配置功能
	 * 
	 */
	public PageResult getGpNoAndName(String gpTpNo,Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return  dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.acctGpConf.getGpNoAndName",RqlParam.map().set("gpTpNo", gpTpNo),page);

	}

}
