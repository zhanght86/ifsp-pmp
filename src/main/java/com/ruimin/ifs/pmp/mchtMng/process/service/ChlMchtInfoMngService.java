package com.ruimin.ifs.pmp.mchtMng.process.service;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsChlMchtInfo;

@Service
@SnowDoc(desc = "渠道商户号管理")
public class ChlMchtInfoMngService extends SnowService{

	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static ChlMchtInfoMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ChlMchtInfoMngService.class);
	}

	/**
	 * 查询渠道商户号信息
	 * @param qChlMchtName 
	 * @param qChlMchtNo 
	 * @param page
	 * @return
	 */
	public PageResult queryAll(String qChlMchtNo, String qChlMchtName, Page page) {
		DBDao dao = DBDaos.newInstance();
	    return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.ChlMchtInfoMng.queryAll",
	    		 RqlParam.map().set("qChlMchtNo", StringUtils.isBlank(qChlMchtNo) ? "" : "%" + qChlMchtNo + "%")
                 .set("qChlMchtName", StringUtils.isBlank(qChlMchtName) ? "" : "%" + qChlMchtName + "%")
                 ,page);
	}

	/**
	 * 修改渠道商户信息
	 * @param chlMchtInfo
	 * @throws SnowException 
	 */
	public void updateChlMchtInfo(PbsChlMchtInfo chlMchtInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(chlMchtInfo);
	}

	/**
	 * 新增渠道商户信息
	 * @param chlMchtInfo
	 * @throws SnowException 
	 */
	public void addChlMchtInfo(PbsChlMchtInfo chlMchtInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(chlMchtInfo);
	}

	/**
	 * 删除商户信息
	 * @param chlMchtInfo
	 * @throws SnowException 
	 */
	public void delChlMchtInfo(PbsChlMchtInfo chlMchtInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(chlMchtInfo);
	}
	
	
	
	
}
