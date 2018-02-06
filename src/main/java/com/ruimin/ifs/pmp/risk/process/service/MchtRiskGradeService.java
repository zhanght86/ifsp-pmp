package com.ruimin.ifs.pmp.risk.process.service;

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
import com.ruimin.ifs.pmp.risk.process.bean.MchtRiskGradeVo;

@Service
public class MchtRiskGradeService extends SnowService {
	//获取单利实体类
	public static MchtRiskGradeService getInstance() throws SnowException{
		return ContextUtil.getSingleService(MchtRiskGradeService.class);
	}
	/**
	 * 查询
	 * @param qMchtNo
	 * @param qMchtName
	 * @param qRiskLevel
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryList(String qMchtNo,String qMchtName,String qRiskLevel,Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectListIn("com.ruimin.ifs.pmp.risk.rql.mchtRiskGrade.queryList", RqlParam.map()
				.set("qMchtNo",StringUtils.isBlank(qMchtNo) ? "" : "%"+qMchtNo+"%")
				.set("qMchtName",StringUtils.isBlank(qMchtName) ? "" : "%"+qMchtName+"%")
				.set("qRiskLevel",StringUtils.isBlank(qRiskLevel) ? "" : "%"+qRiskLevel+"%"), page);
	}
	/**
	 * 手工评级
	 * @param mchtRiskGradeVo
	 * @throws SnowException
	 */
	public void handWorkGrade(MchtRiskGradeVo mchtRiskGradeVo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.mchtRiskGrade.handWorkGrade", mchtRiskGradeVo);
		
	} 
}
