package com.ruimin.ifs.pmp.risk.process.service;








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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.risk.process.bean.UserBlackListVo;

@Service
public class UserBlackListService extends SnowService {
	//获取单利实体类
	public static UserBlackListService getInstance()throws SnowException{
		return ContextUtil.getSingleService(UserBlackListService.class);
	}
	/**
	 * 查询
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryList(String qBlacklistType,String qBlacklistStatus,String qBlacklistValue,Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectListIn("com.ruimin.ifs.pmp.risk.rql.userBlackList.queryList", RqlParam.map()
				.set("qBlacklistType",StringUtils.isBlank(qBlacklistType) ? "" : "%"+qBlacklistType+"%")
				.set("qBlacklistStatus",StringUtils.isBlank(qBlacklistStatus) ? "" : "%"+qBlacklistStatus+"%")
				.set("qBlacklistValue",StringUtils.isBlank(qBlacklistValue) ? "" : "%"+qBlacklistValue+"%"), page);
	}
	/**
	 * 新增
	 * @param userBlackListVo
	 * @throws SnowException
	 */
	public void save(UserBlackListVo userBlackListVo)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.insert(userBlackListVo);
	}
	/**
	 * 修改
	 * @param uerBlackListVo
	 * @throws SnowException
	 */
	public void modify(UserBlackListVo userBlackListVo)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.userBlackList.modify", userBlackListVo);
	}
	/**
	 * 删除
	 * @param userBlackListVo
	 * @throws SnowException
	 */
	public void delete(String blacklistId)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from RISK_USER_BLACKLIST where BLACKLIST_ID = '"+blacklistId+"'";
		dao.executeUpdateSql(sql);
	}
	
	/**
	 * 获取黑名单最大ID
	 * @return
	 * @throws SnowException
	 */
	public String getMaxId() throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.risk.rql.userBlackList.getMaxId");
	}
	/**
	 * 启用/停用
	 * @param userBlackListVo
	 * @throws SnowException
	 */
	public void statusChange(UserBlackListVo userBlackListVo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.userBlackList.statusChange",userBlackListVo );
		
	}
	/**
	 * 根据类型和值查询用户黑名单
	 * @param blacklistType
	 * @param blacklistValue
	 * @return
	 * @throws SnowException
	 */
	public UserBlackListVo selectBlackListByTypeAndValue(String blacklistType, String blacklistValue)throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (UserBlackListVo)dao.selectOne("com.ruimin.ifs.pmp.risk.rql.userBlackList.selectBlackListByTypeAndValue", RqlParam.map().set("blacklistType", blacklistType).set("blacklistValue", blacklistValue));
	}
	/**
	 * 根据id查询黑名单信息
	 * @param blacklistId
	 * @return
	 * @throws SnowException
	 */
	public UserBlackListVo selectBlackListById(String blacklistId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (UserBlackListVo)dao.selectOne("com.ruimin.ifs.pmp.risk.rql.userBlackList.selectBlackListById", RqlParam.map().set("blacklistId", blacklistId));
	}
	
	/**
	 * 查询数据库中所有已有的黑名单，用户excel批量导入时判断
	 * @return
	 * @throws SnowException
	 */
	public List<Object> selectAllBlackList() throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.userBlackList.selectAllBlackList",RqlParam.map().set("",""));
	}
}
