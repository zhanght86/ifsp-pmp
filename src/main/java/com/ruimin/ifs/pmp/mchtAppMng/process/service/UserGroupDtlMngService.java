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
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.UserGroupDtlBaseInfo;

@Service
public class UserGroupDtlMngService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static UserGroupDtlMngService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(UserGroupDtlMngService.class);
	}
	/**
	 * 首页查询
	 * @param qdeviceType
	 * @param quserName 
	 * @param quserId 
	 * @param qappMchtUserGroup
	 * @param qupdFlag
	 * @param quserName 
	 * @param quserId 
	 * @param page
	 * @return
	 */
	public PageResult queryMain(String qgroupId, String qmchtId, String qdeviceType, String quserId, String quserName, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userGroupDtlMng.queryMain",RqlParam.map()	                         
				.set("qgroupId", StringUtils.isBlank(qgroupId) ? "" : "%"+qgroupId+"%")	                         
				.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%")
				.set("qdeviceType", StringUtils.isBlank(qdeviceType) ? "" : qdeviceType)
				.set("quserId", StringUtils.isBlank(quserId) ? "" : "%"+quserId+"%")
				.set("quserName", StringUtils.isBlank(quserName) ? "" : "%"+quserName+"%"),
				page);
	}
	
	/**
	 * 新增
	 * @param mchtUserGroupBaseInfo
	 * @throws SnowException
	 */
	public void add(UserGroupDtlBaseInfo userGroupDtlBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(userGroupDtlBaseInfo);
		MchtUserGroupBaseInfo mchtUserGroupBaseInfo = MchtUserGroupMngService.getInstance().queryByGroupId(userGroupDtlBaseInfo.getGroupId());
		Integer userCount = mchtUserGroupBaseInfo.getUserCount();
		if(userCount == null){
			mchtUserGroupBaseInfo.setUserCount(1);
		}else{
			mchtUserGroupBaseInfo.setUserCount(userCount+1);				
		}
		dao.update(mchtUserGroupBaseInfo);
	}
	
	/**
	 * 更新
	 * @param mchtUserGroupBaseInfo
	 * @throws SnowException
	 */
	public void udp(UserGroupDtlBaseInfo userGroupDtlBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(userGroupDtlBaseInfo);
	}
	
	/**
	 * 判断该组信息是否已经存在
	 * @param userGroupDtlBaseInfo
	 */
	public UserGroupDtlBaseInfo queryIfExit(UserGroupDtlBaseInfo userGroupDtlBaseInfo) {
		DBDao dao = DBDaos.newInstance();
		return (UserGroupDtlBaseInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.userGroupDtlMng.queryIfExit", RqlParam.map()	                         
				.set("groupId", userGroupDtlBaseInfo.getGroupId()).set("userId", userGroupDtlBaseInfo.getUserId()));
	}
	
	/**
	 * 根据店员用户号查询店长用户号
	 * @param userId
	 * @return
	 */
	public String queryUserIdMngByAss(String userIdAss) {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.userGroupDtlMng.queryUserIdMngByAss", userIdAss);
	}
	
	/**
	 * 根据店长用户号获取商户号
	 * @param userId
	 */
	public String queryMchtIdByUserId(String userId) {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.userGroupDtlMng.queryMchtIdByUserId", userId);
	}
	
}
