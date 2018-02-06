package com.ruimin.ifs.pmp.mchtAppMng.process.service;

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
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MchtUserGroupBaseInfo;

@Service
public class MchtUserGroupMngService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtUserGroupMngService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(MchtUserGroupMngService.class);
	}
	/**
	 * 首页查询
	 * @param qdeviceType
	 * @param qappMchtUserGroup
	 * @param qupdFlag
	 * @param page
	 * @return
	 */
	public PageResult queryMain(String qgroupId, String qmchtId, String qdeviceType, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.mchtUserGroupMng.queryMain",RqlParam.map()	                         
				.set("qgroupId", StringUtils.isBlank(qgroupId) ? "" : "%"+qgroupId+"%")	                         
				.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%")
				.set("qdeviceType", StringUtils.isBlank(qdeviceType) ? "" : qdeviceType),
				page);
	}
	
	/**
	 * 新增
	 * @param mchtUserGroupBaseInfo
	 * @throws SnowException
	 */
	public void add(MchtUserGroupBaseInfo mchtUserGroupBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtUserGroupBaseInfo);
	}
	
	/**
	 * 更新
	 * @param mchtUserGroupBaseInfo
	 * @throws SnowException
	 */
	public void udp(MchtUserGroupBaseInfo mchtUserGroupBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(mchtUserGroupBaseInfo);
	}
	
	/**
	 * 根据组号查询信息
	 * @param MchtUserGroupBaseInfo
	 * @param string 
	 * @return
	 */
	public MchtUserGroupBaseInfo queryByGroupId(String groupId) {
		DBDao dao = DBDaos.newInstance();
		MchtUserGroupBaseInfo mchtUserGroupBaseInfo = (MchtUserGroupBaseInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.mchtUserGroupMng.queryByGroupId",groupId);
		return mchtUserGroupBaseInfo;
	}
	
}
