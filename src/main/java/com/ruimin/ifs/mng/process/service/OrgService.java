package com.ruimin.ifs.mng.process.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.po.ImpTermInfTmp;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.po.IfsCtCd;
import com.ruimin.ifs.po.TblDataDic;
import com.ruimin.ifs.util.SeqNoGenUtil;

@SnowDoc(desc = "机构操作")
public class OrgService extends SnowService {

	public static OrgService getInstance() throws SnowException {
		return ContextUtil.getSingleService(OrgService.class);
	}

	/**
	 * 查询所有机构
	 * 
	 * @return
	 * @throws SnowException
	 */
	public List<Object> listAll() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.listAll");
	}

	/**
	 * 查询机构列表
	 * 
	 * @param currentBrClass
	 *            当参数不为空，查询该类型上级机构列表；当参数为空则查询全部机构列表
	 * @return
	 * @throws SnowException
	 */
	public List<Object> listUpOrg(String currentBrClass) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryUpOrg",
				RqlParam.map().set("currentBrClass", currentBrClass == null ? "" : currentBrClass));
	}

	/**
	 * 分页查询下级机构
	 * 
	 * @param queryMap
	 *            封装的机构信息
	 * @param page
	 *            分页查询
	 * @return 满足条件下机构管理的信息
	 * @throws SnowException
	 */
	public PageResult listOrg(Map<String, String> queryMap, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult result = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryOrg", queryMap, page);
		return result;
	}

	/**
	 * 不分页查询下级机构
	 * 
	 * @param brCode
	 *            机构号
	 * @return 该机构号下对应的机构信息
	 * @throws SnowException
	 */
	public List<Object> listOrg(String brCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> result = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryDownOrg",
				RqlParam.map().set("brCode", brCode));
		return result;
	}

	/**
	 * 根据主键获取机构
	 * 
	 * @param brCode
	 *            机构号
	 * @return 该机构号下对应的机构信息
	 * @throws SnowException
	 */
	public TblBctl queryOrgById(String brCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(TblBctl.class, brCode);
	}

	/**
	 * 根据机构编号+机构编号查询
	 * 
	 * @param brno
	 *            机构编号
	 * @return 该机构编号下对应的条数
	 * @throws SnowException
	 */
	public int queryOrgByBrNo(String brno, String brcode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryOrgByBrNo",
				RqlParam.map().set("brno", brno).set("brcode", brcode));
	}

	/**
	 * 根据机构编号查询
	 * 
	 * @param brno
	 *            机构编号
	 * @return
	 */
	public int queryOrgByBrnoId(String brno) {
		DBDao dao = DBDaos.newInstance();
		int br = (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryOrgByBrnoId",
				RqlParam.map().set("brno", brno));
		return br;
	}

	/**
	 * 查询总行是否存在
	 * 
	 * @return 返回存在的条数
	 * @throws SnowException
	 */
	public int queryOrgByBrClass() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryOrgByBrClass");
	}

	/**
	 * 新增机构
	 * 
	 * @param bctl
	 *            机构管理实体类对象
	 * @throws SnowException
	 */
	public void addOrg(TblBctl bctl) throws SnowException {
		// 生成机构code
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		bctl.setLastUpdDate(DateUtil.getCurrDate());
		bctl.setLastUpdTlr(sessionUserInfo.getTlrno());
		bctl.setStatus("00");
		DBDao dao = DBDaos.newInstance();
		dao.insert(bctl);
		getLogger().info("添加新机构信息:" + bctl.getBrcode());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"添加新机构:brcode=" + bctl.getBrcode() });
	}

	/**
	 * 机构信息修改
	 * 
	 * @param bctl
	 *            机构管理实体类对象
	 * @throws SnowException
	 */
	public void updateOrg(TblBctl bctl) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		bctl.setLastUpdDate(DateUtil.getCurrDate());
		bctl.setLastUpdTlr(sessionUserInfo.getTlrno());
		bctl.setStatus("00");
		DBDao dao = DBDaos.newInstance();
		dao.update(bctl);
		getLogger().info("修改机构信息:" + bctl.getBrcode());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"修改机构信息:brcode=" + bctl.getBrcode() });
	}

	/**
	 * 设置机构启用/停用
	 * 
	 * @param brcode
	 *            机构号
	 * @param status
	 *            数据状态
	 * @throws SnowException
	 */
	public void updateOrgStatus(String brcode, String status) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TblBctl bctl = queryOrgById(brcode);
		bctl.setStatus(status);
		bctl.setLastUpdDate(DateUtil.getCurrDate());
		bctl.setLastUpdTlr(sessionUserInfo.getTlrno());
		DBDao dao = DBDaos.newInstance();
		dao.update(bctl);
		getLogger().info("设置机构[" + bctl.getBrcode() + "]有效性为:" + bctl.getStatus());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"设置机构[" + bctl.getBrcode() + "]有效性为:" + bctl.getStatus() });
	}

	/**
	 * 查询该机构级别
	 * 
	 * @param typeNo
	 * @return
	 * @throws SnowException
	 */
	public Map<String, String> getDataDicByTypeNo(String typeNo) {
		DBDao dao = DBDaos.newInstance();
		Map<String, String> result = new HashMap<String, String>();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.datadic.getDataDicByTypeNo",
				RqlParam.map().set("typeNo", typeNo));
		for (Object inf : list) {
			TblDataDic dic = (TblDataDic) inf;
			result.put(dic.getDataName().trim(), dic.getDataNo());
		}
		return result;
	}

	/**
	 * 查询所有的内部机构号
	 * 
	 * @return
	 */
	public Map<String, String> getAllBrcode() {
		DBDao dao = DBDaos.newInstance();
		Map<String, String> result = new HashMap<String, String>();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.othmng.getAllOrg");
		for (Object inf : list) {
			TblBctl bctl = (TblBctl) inf;
			result.put(bctl.getBrname().trim(), bctl.getBrcode());
		}
		return result;
	}

	/**
	 * 查询所有的地区编号
	 * 
	 * @return
	 */
	public Map<String, String> getAllCity() {
		DBDao dao = DBDaos.newInstance();
		Map<String, String> result = new HashMap<String, String>();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.othmng.getAllCity");
		for (Object inf : list) {
			IfsCtCd city = (IfsCtCd) inf;
			result.put(city.getCtName().trim(), city.getCtCode());
		}
		return result;
	}

	/**
	 * 机构管理的新增
	 * 
	 * @param list
	 *            机构管理的集合
	 * @throws SnowException
	 */
	public void addOrgList(List<TblBctl> list) throws SnowException {
		// TODO Auto-generated method stub
		DBDao dao = DBDaos.newInstance();
		dao.insert(list);
	}

	/* add by ttt 20151109 */
	/**
	 * 通过机构名称查询是否有下级机构
	 * 
	 * @param brcode
	 *            机构号
	 * @return 该机构号下对应的条数
	 * @throws SnowException
	 */
	public int queryOrgByOrgCode(String brcode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryOrgByOrgName",
				RqlParam.map().set("blnUpBrcode", brcode));
	}
	/**
     * 通过机构名称查询此机构是否有商户使用
     * 
     * @param brcode
     *            机构号
     * @return 该机构号下对应的条数
     * @throws SnowException
     */
    public int queryOrgMchtOrgCode(String brcode) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryOrgMchtOrgCode",
                RqlParam.map().set("blnUpBrcode", brcode));
    }
	/**
	 * 通过机构号删除机构信息
	 * 
	 * @param id
	 *            机构号
	 * @throws SnowException
	 */
	public void delOrgByCode(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(TblBctl.class, id);
	}

	// 查询所有机构信息。上级可看到下级，下级看不到上级机构信息
	/**
	 * 功能描述: 查询所有机构信息 <br>
	 *
	 * @param brno
	 *            商户号
	 * @param brname
	 *            商户简称
	 * @param blnUpBrcode
	 *            商户状态
	 * @return 返回类型
	 * @throws SnowException
	 */
	public PageResult queryListTmp(Map<String, String> param, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectListIn("com.ruimin.ifs.mng.rql.othmng.queryOrgByParam", param, page);
	}

	/**
	 * 查询所有内部机构号
	 * 
	 * @return 返回类型 List
	 * @throws SnowException
	 */
	public List<String> queryBrcode() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> listObj = dao.selectList("com.ruimin.ifs.mng.rql.othmng.queryBrcode");
		List<String> listStr = formatListObj2String(listObj);
		return listStr;
	}

	/**
	 * List<Object>转换为List<String>
	 * 
	 * @param listObj
	 * @return 返回类型 List
	 * @throws SnowException
	 */
	private List<String> formatListObj2String(List<Object> listObj) throws SnowException {
		List<String> listStr = new ArrayList<String>();
		if (listObj != null && listObj.size() == 0) {
			listStr = null;
		} else {
			for (Object Obj : listObj) {
				listStr.add(String.valueOf(Obj));
			}
		}
		return listStr;
	}

	/**
	 * 获取操作员所属机构及下属机构
	 * 
	 * @param tlrno
	 *            操作员编号
	 * @param brname
	 *            机构名称[模糊查询]
	 * @param page
	 *            分页查询
	 * @return
	 */
	public PageResult selOrg(String tlrno, String brname, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.mng.rql.othmng.selOrg",
				RqlParam.map().set("tlrno", tlrno).set("brname", StringUtils.isBlank(brname) ? "" : "%" + brname + "%"),
				page);
	}

}
