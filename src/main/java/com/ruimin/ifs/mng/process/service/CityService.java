package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.mng.common.constants.CityMngConstants;
import com.ruimin.ifs.po.IfsCtCd;
import com.ruimin.ifs.util.BaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SnowDoc(desc = "地区操作")
public class CityService extends SnowService {

	public static CityService getInstance() throws SnowException {
		return ContextUtil.getSingleService(CityService.class);
	}

	/**
	 * 查询所有地区
	 * 
	 * @return list 地区信息list集合
	 * @throws SnowException
	 */
	public List<IfsCtCd> listAll() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<IfsCtCd> list = dao.selectAll(IfsCtCd.class);
		return list;
	}

	/**
	 * 分页查询下级机构
	 * 
	 * @param queryMap
	 *            查询变量map集合
	 * @param page
	 *            前端页面变量
	 * @return
	 * @throws SnowException
	 */
	public PageResult listChildCity(Map<String, String> queryMap, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult result = dao.selectList("com.ruimin.ifs.mng.rql.othmng.queryCityChild", queryMap, page);
		return result;
	}
	/**
	 * 分页查询下级机构
	 * 
	 * @param queryMap
	 *            查询变量map集合
	 * @param page
	 *            前端页面变量
	 * @return
	 * @throws SnowException
	 */
	public PageResult listChildCity1(Map<String, String> queryMap, Page page) throws SnowException {
	    DBDao dao = DBDaos.newInstance();
	    PageResult result = dao.selectList("com.ruimin.ifs.mng.rql.othmng.queryCityChild1", queryMap, page);
	    return result;
	}

	/**
	 * 根据主键获取地区
	 * 
	 * @param ctCode
	 *            地区编号
	 * @return IfsCtCd 地区信息实体类
	 * @throws SnowException
	 */
	public IfsCtCd queryCityById(String ctCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(IfsCtCd.class, ctCode);
	}

	/**
	 * 根据地区编号查询该条地区信息是否存在
	 * 
	 * @param ctCode
	 *            地区编号
	 * @return
	 * @throws SnowException
	 */
	public int queryCityByCtCode(String ctCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryCityByCtCode",
				RqlParam.map().set("ctCode", ctCode));
	}

	/**
	 * 查询上级地区编号为upCtCode的地区数量
	 * 
	 * @param upCtCode
	 *            上级地区编号
	 * @return
	 * @throws SnowException
	 */
	public int queryCityByUpCtCode(String upCtCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryCityByUpCtCode",
				RqlParam.map().set("upCtCode", upCtCode));
	}

	/**
	 * 根据地区编号查询机构数量
	 * 
	 * @param areaCd
	 *            地区编号
	 * @return
	 * @throws SnowException
	 */
	public int queryOrgByAreaCd(String areaCd) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryOrgByAreaCd",
				RqlParam.map().set("areaCd", areaCd));
	}

	/**
	 * 根据地区编号从PBS_MCHT_BASE_INFO查询该地区有没有被使用
	 * 
	 * @param areaCd
	 *            地区编号
	 * @return
	 * @throws SnowException
	 */
	public int queryMchtByCtCode(String areaCd) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.mng.rql.othmng.queryMchtByCtCode",
				RqlParam.map().set("areaCd", areaCd));
	}

	/**
	 * 查询出所有省级地区，并返回地区编号和地区名称
	 * 
	 * @return
	 */
	public Map<String, String> getAllCity() {
		DBDao dao = DBDaos.newInstance();
		Map<String, String> result = new HashMap<String, String>();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.othmng.getAllCity");
		for (Object inf : list) {
			IfsCtCd city = (IfsCtCd) inf;
			if (CityMngConstants.City_Flg_1.equals(city.getCtFlg())) {
				result.put(city.getCtCode(), city.getCtName().trim());
			}
		}
		return result;
	}

	/**
	 * 地区信息新增
	 * 
	 * @param city
	 *            地区实体类
	 * @throws SnowException
	 */
	public void addCity(IfsCtCd city) throws SnowException {

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		city.setCrtOprId(sessionUserInfo.getTlrno());
		city.setLstUpdOprId(sessionUserInfo.getTlrno());
		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		city.setRecCrtTm(BaseUtil.getCurrentDateTime());
		city.setRecUpdTm(BaseUtil.getCurrentDateTime());
		DBDao dao = DBDaos.newInstance();
		dao.insert(city);
		getLogger().info("添加新地区信息:" + city.getCtCode());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[地区管理]--添加新地区:地区编号[ctCode]=" + city.getCtCode() });
	}

	/**
	 * 新增地区信息列表
	 * 
	 * @param list
	 *            地区信息list集合
	 * @throws SnowException
	 */
	public void addCityList(List<IfsCtCd> list) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(list);
	}

	/**
	 * 新增一条地区信息
	 * 
	 * @param inf
	 *            地区信息的实体类
	 * @throws SnowException
	 */
	public void addCityList1(IfsCtCd inf) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(inf);
	}

	/**
	 * 地区信息修改
	 * 
	 * @param city
	 *            地区信息实体类
	 * @throws SnowException
	 */
	public void updateCity(IfsCtCd city) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		city.setLstUpdOprId(sessionUserInfo.getTlrno());
		// 获得系统当前日期时间 格式:yyyyMMddHHmmss
		city.setRecUpdTm(BaseUtil.getCurrentDateTime());
		DBDao dao = DBDaos.newInstance();
		dao.update(city);
		getLogger().info("修改地区信息:" + city.getCtCode());
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[地区管理]--修改地区信息:地区编号[ctCode]=" + city.getCtCode() });
	}

	/**
	 * 删除地区
	 * 
	 * @param id
	 *            地区编号
	 * @throws SnowException
	 */
	public void delCityById(String id) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		DBDao dao = DBDaos.newInstance();
		dao.delete(IfsCtCd.class, id);

		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[地区管理]--删除地区信息:地区编号[ctCode]=" + id });
	}

}
