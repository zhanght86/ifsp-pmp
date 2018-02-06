package com.ruimin.ifs.pmp.mchtAppMng.process.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.EventBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MchtUserVO;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;

@Service
@SnowDoc(desc = "商户管理")
public class UserMngService extends SnowService{	
	
	/**
	 * 获取实例
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static UserMngService getInstance() throws SnowException{
		return ContextUtil.getSingleService(UserMngService.class);
	}
	

	/**
	 * 查询【商户信息】
	 * @param qmchtId 商户号【模糊查询】
	 * @param qmchtSimpleName 商户简称【模糊查询】
	 * @param qmchtId2 
	 * @param qmchtSimpleName2 
	 * @param qmchtType 商户类型
	 * @param qmchtStat 商户状态
	 * @param qmchtOrgId 所属机构
	 * @param auditId 
	 * @param qmchtName 商户全名
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String tlrno,String brcode, String roleId,String quserName, String qmchtArtifId, String qmchtSimpleName, String qmchtId, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		PageResult result = null;
		boolean flag = MchtMngService.getInstance().isAmr(roleId);
		//角色是客户经理，只可看见自己商户
		if(flag == true){
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryMassInfo", RqlParam.map()
					.set("tlrno", tlrno).set("brcode", brcode)
					.set("quserName",  StringUtils.isBlank(quserName) ? "" : "%"+quserName+"%")
					.set("qmchtArtifId", StringUtils.isBlank(qmchtArtifId) ? "" : "%"+qmchtArtifId+"%")
					.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%"+qmchtSimpleName+"%")
					.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%"),
					page);
		}else{
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryMassInfo2", RqlParam.map()
					.set("tlrno", tlrno).set("brcode", brcode)
					.set("quserName",  StringUtils.isBlank(quserName) ? "" : "%"+quserName+"%")
					.set("qmchtArtifId", StringUtils.isBlank(qmchtArtifId) ? "" : "%"+qmchtArtifId+"%")
					.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%"+qmchtSimpleName+"%")
					.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%"),
					page);
		}
		return result;
	}

	public PageResult queryInfo(String userId, Page page) {
		DBDao dao = DBDaos.newInstance();
		PageResult result = null;
		
		result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryMassInfo1", RqlParam.map()
				.set("userId",  StringUtils.isBlank(userId) ? "" : userId),
				page);			
		return result;
	}
	
	/**
	 * 根据用户号查询用户信息
	 * @param userId
	 * @return
	 */
	public MchtUserVO queryInfoById(String userId) {
		DBDao dao = DBDaos.newInstance();
		MchtUserVO mchtUserVO = new MchtUserVO();
		mchtUserVO = (MchtUserVO) dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryInfoById", userId);			
		return mchtUserVO;
	}

	public void update(EventBaseInfo eventVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(eventVo);
	}

	/**
	 * 根据设备类型查询用户信息
	 * @param deviceType
	 */
	public List queryInfoByDeviceType(String deviceType) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryInfoByDeviceType", deviceType);
	}
}
