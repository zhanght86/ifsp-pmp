package com.ruimin.ifs.mng.process.service;

import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.po.TblResInfo;
import com.ruimin.ifs.po.TblRole;

/**
 * 岗位管理sevice
 * 
 * @author shaoqin
 *
 */
@Service
public class RoleMngEntryService extends SnowService {
	public static RoleMngEntryService getInstance() throws SnowException {
		return ContextUtil.getSingleService(RoleMngEntryService.class);
	}

	public List<Object> queryAll() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryRoleVo");
	}

	public List<Object> queryAllByParam(String qRoleName, String qStatus) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.querySelectedByParam", RqlParam.map()
				.set("qRoleName", qRoleName == null ? "" : qRoleName).set("qStatus", qStatus == null ? "" : qStatus));
	}

	public List<Object> queryAllByTlrno(String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.querySelected",
				RqlParam.map().set("tlrno", tlrno == null ? "" : tlrno));
	}

	public PageResult queryStaffRoleRef(String roleId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryStaffRoleInfo",
				RqlParam.map().set("roleId", roleId == null ? "" : roleId), page);
	}

	public int deleteRoleResInf(String roleId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.executeUpdate("com.ruimin.ifs.mng.rql.sysmng.deleteRoleResInf",
				RqlParam.map().set("roleId", roleId == null ? "" : roleId));
	}

	public List<Object> queryResInf(String roleId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryResInf",
				RqlParam.map().set("roleId", roleId == null ? "" : roleId));
	}

	public TblRole checkId(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TblRole.class, id);
	}

	public void saveRoleMngEntry(TblRole tblRole) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(tblRole);
	}

	public void updateRoleMngEntry(TblRole tblRole) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(tblRole);
	}

	public void updateRoleMngStatusEntry(TblRole tblRole) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		/* add by ttt for 更新相关用户的有效标识 20151110 */
		dao.executeSql("update ifs_staff set flag = '0' where roleId = '" + tblRole.getRoleId() + "'");
		/* end add */
	}

	public void deletRoleMngEntry(TblRole tblRole) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(tblRole);
	}

	public void deleteRoleMngEntryById(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblRole.class, id);
	}

	public void saveResInfo(List<TblResInfo> s) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(s);
	}

	public List<Object> queryAllListByTlrno(String tlrno) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryListSelected",
				RqlParam.map().set("tlrno", tlrno == null ? "" : tlrno));
	}

	/**
	 * 根据操作员查询角色
	 * 
	 * added by mj 2015-11-20
	 */
	public String queryRoleByTlrno(String tlrno) {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryListByTlr",
				RqlParam.map().set("tlrno", tlrno == null ? "" : tlrno));
	}

	public PageResult queryRoleByTlrno(String tlrno, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryPageByTlr",
				RqlParam.map().set("tlrno", tlrno == null ? "" : tlrno), page);
	}

	/* add by ttt 20151109 */
	public PageResult listRole(Map<String, String> queryMap, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult result = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryRole", queryMap, page);
		return result;
	}
	/* 角色查询 service */
	public PageResult listRole1(Map<String, String> queryMap, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult result = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryRole1", queryMap, page);
		return result;
	}

	/* 通过角色ID查询是否有相关联的操作员 */
	public int queryOprById(String roleId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryOprById",
				RqlParam.map().set("roleId", roleId));
	}

}
