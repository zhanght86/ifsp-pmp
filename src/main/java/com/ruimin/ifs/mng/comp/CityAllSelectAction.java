package com.ruimin.ifs.mng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.common.constants.CityMngConstants;
import com.ruimin.ifs.mng.process.service.CityService;
import com.ruimin.ifs.po.IfsCtCd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

@SnowDoc(desc = "地区下拉查询")
@ActionResource
public class CityAllSelectAction extends SnowAction {
	private static final String VIR_CTCODE = "0000";

	/**
	 * 地区查询
	 * 
	 * @param queryBean
	 * @return List<TreeNode>
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "地区查询")
	public List<TreeNode> queryAllCity(QueryParamBean queryBean) throws SnowException {
		CityService service = CityService.getInstance();
		List<TreeNode> list = new ArrayList<TreeNode>();
		boolean bl = Boolean.parseBoolean(queryBean.getParameter("showvir", "false"));
		// 创建虚拟节点
		if (bl) {
			TreeNode vnode = new TreeNode();
			vnode.setIconCls("fa fa-bank");
			vnode.setText("地区树");
			vnode.setId(VIR_CTCODE);
			vnode.setState(TreeNode.EXPAND_STATUS_OPEN);
			list.add(vnode);
		}
		List<IfsCtCd> cityList = service.listAll();
		for (IfsCtCd city : cityList) {
			TreeNode node = new TreeNode();
			node.setAttributes(city);
			node.setIconCls("fa fa-bank");
			node.setText(city.getCtName());
			node.setId(city.getCtCode());
			node.setState(TreeNode.EXPAND_STATUS_OPEN);
			node.setCheckedStatus(city.getCtFlg());
			/*
			 * if ("1".equals(city.getCtFlg())) { node.setPid(VIR_CTCODE); if
			 * (city.getCtName().indexOf("北京") != -1 ||
			 * city.getCtName().indexOf("上海") != -1 ||
			 * city.getCtName().indexOf("天津") != -1 ||
			 * city.getCtName().indexOf("重庆") != -1) {
			 * node.setCanSelected(true); } else { node.setCanSelected(true); }
			 * else {
			 */
			node.setPid(city.getUpCtCode());
			node.setCanSelected(true);
			list.add(node);
		}
		return list;
	}

	/**
	 * 根据省级代码分页查询市级地区信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "根据省级代码分页查询市级地区信息")
	public PageResult queryCityByCode(QueryParamBean queryBean) throws SnowException {
		String upCtCode = queryBean.getParameter("qupCtCode", VIR_CTCODE);
		if (upCtCode.equals(VIR_CTCODE)) {
			upCtCode = "";
		}
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("upCtCode", upCtCode);
		queryMap.put("ctFlg", queryBean.getParameter("qctFlg", ""));
		queryMap.put("ctCode", queryBean.getParameter("qCtCode", ""));
		String ctName = queryBean.getParameter("qCtName", "");
		if (StringUtils.isNotBlank(ctName)) {
			ctName = "%" + ctName.trim() + "%";
		}
		queryMap.put("ctName", ctName);
		return CityService.getInstance().listChildCity(queryMap, queryBean.getPage());
	}
	/**
	 * 根据省级代码分页查询市级地区信息(去除直辖市省级码以及中国码)
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "根据省级代码分页查询市级地区信息")
	public PageResult queryCityByCode1(QueryParamBean queryBean) throws SnowException {
	    String upCtCode = queryBean.getParameter("qupCtCode", VIR_CTCODE);
	    if (upCtCode.equals(VIR_CTCODE)) {
	        upCtCode = "";
	    }
	    Map<String, String> queryMap = new HashMap<String, String>();
	    queryMap.put("upCtCode", upCtCode);
	    queryMap.put("ctFlg", queryBean.getParameter("qctFlg", ""));
	    queryMap.put("ctCode", queryBean.getParameter("qCtCode", ""));
	    String ctName = queryBean.getParameter("qCtName", "");
	    if (StringUtils.isNotBlank(ctName)) {
	        ctName = "%" + ctName.trim() + "%";
	    }
	    queryMap.put("ctName", ctName);
	    return CityService.getInstance().listChildCity1(queryMap, queryBean.getPage());
	}

	/**
	 * 地区信息新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增地区")
	public void addCity(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("citymanger");
		IfsCtCd ifsCtCd = new IfsCtCd();
		// 地区信息填充到实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsCtCd);
		}
		// 地区字段信息验证
		if (StringUtils.isBlank(ifsCtCd.getCtCode())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "地区编码不能为空!");
		}
		if (StringUtils.isBlank(ifsCtCd.getCtName())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "地区名称不能为空!");
		}
		if (StringUtils.isBlank(ifsCtCd.getCtFlg())) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "地区标识不能为空!");
		}
		// 检查地区编码是否重复
		int count = CityService.getInstance().queryCityByCtCode(ifsCtCd.getCtCode());
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0046", ifsCtCd.getCtCode());
		}
		if (ifsCtCd.getCtFlg().equals(CityMngConstants.City_Flg_1) && ifsCtCd.getUpCtCode() != null
				&& !"".equals(ifsCtCd.getUpCtCode())) {

			SnowExceptionUtil.throwWarnException("WEB_E0045", "省级地区没有所属上级地区!");
		}
		if (ifsCtCd.getCtFlg().equals(CityMngConstants.City_Flg_2)
				&& (ifsCtCd.getUpCtCode() == null || "".equals(ifsCtCd.getUpCtCode()))) {

			SnowExceptionUtil.throwWarnException("WEB_E0045", "市级地区所属上级地区不能为空!");
		}
		CityService.getInstance().addCity(ifsCtCd);
	}

	/**
	 * 地区信息修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "地区修改")
	public void updateCity(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("citymanger");
		IfsCtCd ifsCtCd = new IfsCtCd();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsCtCd);
		}

		if (ifsCtCd.getCtFlg().equals(CityMngConstants.City_Flg_1) && ifsCtCd.getUpCtCode() != null
				&& !"".equals(ifsCtCd.getUpCtCode())) {

			SnowExceptionUtil.throwWarnException("WEB_E0045", "省级地区没有所属上级地区!");
		}
		if (ifsCtCd.getCtFlg().equals(CityMngConstants.City_Flg_2)
				&& (ifsCtCd.getUpCtCode() == null || "".equals(ifsCtCd.getUpCtCode()))) {

			SnowExceptionUtil.throwWarnException("WEB_E0045", "市级地区所属上级地区不能为空!");
		}

		CityService.getInstance().updateCity(ifsCtCd);
	}

	/**
	 * 地区信息删除
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delCity(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("citymanger");
		String ctCodeD = reqBean.getParameter("ctCodeD");
		String ctFlgD = reqBean.getParameter("ctFlgD");
	//if (CityMngConstants.City_Flg_1.equals(ctFlgD)) {
			// 检查是否存在下级地区
			int count = CityService.getInstance().queryCityByUpCtCode(ctCodeD);
			if (count > 0) {
				SnowExceptionUtil.throwWarnException("WEB_E0045", "该地区下存在子级地区，不允许删除！");
			}
		//} else {
			// 检查该地区是否存在所属机构
			int contOrg = CityService.getInstance().queryOrgByAreaCd(ctCodeD);
			if (contOrg > 0) {
				SnowExceptionUtil.throwWarnException("WEB_E0045", "该地区已经被使用，不允许删除！");
			}
		//}
		// 判断是否有商户引用该地区
		int countMcht = CityService.getInstance().queryMchtByCtCode(ctCodeD);
		if (countMcht > 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "该地区已经被使用，不允许删除！");
		}

		CityService.getInstance().delCityById(ctCodeD);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "删除地区,id=" + ctCodeD });
	}

	/**
	 * 根据地区代码获取地区名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCityName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			IfsCtCd city = CityService.getInstance().queryCityById(key);
			if (city != null) {
				return city.getCtName();
			}
		}
		return key;
	}
}
