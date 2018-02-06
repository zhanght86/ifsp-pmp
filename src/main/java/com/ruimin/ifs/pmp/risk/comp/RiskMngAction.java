package com.ruimin.ifs.pmp.risk.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.common.constants.RiskModelCfgConstants;
import com.ruimin.ifs.pmp.risk.process.bean.RiskBaseCfg;
import com.ruimin.ifs.pmp.risk.process.bean.RiskProdMatchVo;
import com.ruimin.ifs.pmp.risk.process.bean.RiskValueOperateMapping;
import com.ruimin.ifs.pmp.risk.process.bean.RiskValueOperateVo;
import com.ruimin.ifs.pmp.risk.process.service.RiskMngService;


/**BMv**/
@ActionResource
@SnowDoc(desc = "风控管理")
public class RiskMngAction {
	/**
	 * 查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询：风控管理数据列表")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qriskId = queryBean.getParameter("qriskId");// 风险编号【模糊查询】
		String qriskName = queryBean.getParameter("qriskName");// 风险名称【模糊查询】
		String qprodId = queryBean.getParameter("qprodId");// 产品编号
		String qriskStauts = queryBean.getParameter("qriskStauts");// 风险状态

		/** 返回查询结果 */
		return RiskMngService.getInstance().queryMain(qriskId, qriskName, qprodId, qriskStauts, queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		// -----------------------风控基本信息------------------------
		// 获取页面传递的风控基本信息
		UpdateRequestBean reqBean = updateMap.get("riskInfo");
		RiskBaseCfg riskBaseCfg = new RiskBaseCfg();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), riskBaseCfg);
		}
		// 补充信息
		riskBaseCfg.setCrtTlr(sessionUserInfo.getTlrno());// 创建人为当前用户
		riskBaseCfg.setCrtDateTime(DateUtil.get14Date());// 创建时间为当前时间
		riskBaseCfg.setLastUpdTlr(sessionUserInfo.getTlrno());// 最后更新人为当前用户
		riskBaseCfg.setLastUpdDateTime(riskBaseCfg.getCrtDateTime());// 最后更新时间为当前时间
		riskBaseCfg.setOpenDate(riskBaseCfg.getOpenDate().replace("-", ""));// 开始日期去掉-
		riskBaseCfg.setEndDate(
				StringUtil.isEmpty(riskBaseCfg.getEndDate()) ? "-" : riskBaseCfg.getEndDate().replaceAll("-", ""));
		// 获取风控编号ID
		String riskId = RiskMngService.getInstance().genRiskId();
		riskBaseCfg.setRiskId(riskId);
		// 数据持久化
		RiskMngService.getInstance().saveRiskBase(riskBaseCfg);
		// ---------------------------风控产品-----------------------
		List<RiskProdMatchVo> riskProdMatchVoList = new ArrayList<>();
		String[] prodIdStr = riskBaseCfg.getProdId().split(",");
		for (int i = 0; i < prodIdStr.length; i++) {
			RiskProdMatchVo riskProdMatchVo = new RiskProdMatchVo();
			riskProdMatchVo.setProdId(prodIdStr[i]);
			riskProdMatchVo.setRiskId(riskId);
			riskProdMatchVo.setCrtTlr(sessionUserInfo.getTlrno());
			riskProdMatchVo.setCrtDateTime(DateUtil.get14Date());
			riskProdMatchVoList.add(riskProdMatchVo);
		}
		// 数据持久化
		RiskMngService.getInstance().saveRrodMatch(riskProdMatchVoList);
		// ---------------------------风控规则阈值操作-------------------
		UpdateRequestBean reqBean2 = updateMap.get("riskValueOperate");
		List<Map<String, String>> list = reqBean2.getTotalList();// 多条数据迭代赋值
		List<RiskValueOperateVo> riskValueOperateVoList = new ArrayList<>();
		RiskValueOperateMapping vo = new RiskValueOperateMapping();
		for (Map<String, String> map : list) {
			vo = DataObjectUtils.map2bean(map, RiskValueOperateMapping.class);

			for (int i = 1; i <= RiskModelCfgConstants.MCHT_RISK_GRADE_COUTN; i++) {// 规定了只有三个商户风险等级
				RiskValueOperateVo resultVo = new RiskValueOperateVo();
				resultVo.setRiskId(riskId);
				resultVo.setValueId(vo.getValueId());
				resultVo.setRiskLevel(i + "");
				if (i == 1) {
					resultVo.setRiskOperate(vo.getParam1());
				}
				if (i == 2) {
					resultVo.setRiskOperate(vo.getParam2());
				}
				if (i == 3) {
					resultVo.setRiskOperate(vo.getParam3());
				}
				riskValueOperateVoList.add(resultVo);
			}
		}
		// 数据持久化
		RiskMngService.getInstance().saveRiskValueOperate(riskValueOperateVoList);

		// 记录日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[风控管理]--新增:编号=[" + riskId + "]" });
		
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void modificationActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// -----------------------风控基本信息------------------------
		// 获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("riskInfo");
		RiskBaseCfg riskBaseCfg = new RiskBaseCfg();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), riskBaseCfg);
		}
		riskBaseCfg.setLastUpdTlr(sessionUserInfo.getTlrno());// 最后更新人为当前用户
		riskBaseCfg.setLastUpdDateTime(DateUtil.get14Date());// 最后更新时间为当前时间
		riskBaseCfg.setOpenDate(riskBaseCfg.getOpenDate().replace("-", ""));// 开始日期去掉-
		riskBaseCfg.setEndDate(
				StringUtil.isEmpty(riskBaseCfg.getEndDate()) ? "-" : riskBaseCfg.getEndDate().replaceAll("-", ""));
		// 数据持久化
		RiskMngService.getInstance().modifyRiskBase(riskBaseCfg);
		// ---------------------------风控产品-----------------------
		//先清空风控产品
		String riskId = riskBaseCfg.getRiskId();
		//保存新的信息
		RiskMngService.getInstance().deleteRrodMatch(riskId);
		List<RiskProdMatchVo> riskProdMatchVoList = new ArrayList<>();
		String[] prodIdStr = riskBaseCfg.getProdId().split(",");
		for (int i = 0; i < prodIdStr.length; i++) {
			RiskProdMatchVo riskProdMatchVo = new RiskProdMatchVo();
			riskProdMatchVo.setProdId(prodIdStr[i]);
			riskProdMatchVo.setRiskId(riskBaseCfg.getRiskId());
			riskProdMatchVo.setCrtTlr(sessionUserInfo.getTlrno());
			riskProdMatchVo.setCrtDateTime(DateUtil.get14Date());
			riskProdMatchVoList.add(riskProdMatchVo);
		}
		// 数据持久化
		RiskMngService.getInstance().saveRrodMatch(riskProdMatchVoList);
		// ---------------------------风控规则阈值操作-------------------
		
		UpdateRequestBean reqBean2 = updateMap.get("riskValueOperate");
		//先清空当前风控下的所有阀值操作
		RiskMngService.getInstance().deleteRiskValueOperate(riskId);//执行删除当前风控下面的所有阀值操作
		//新增页面上的信息
		List<Map<String, String>> list = reqBean2.getTotalList();// 多条数据迭代赋值
		List<RiskValueOperateVo> riskValueOperateVoList = new ArrayList<>();
		RiskValueOperateMapping vo = new RiskValueOperateMapping();
		for (Map<String, String> map : list) {
			vo = DataObjectUtils.map2bean(map, RiskValueOperateMapping.class);

			for (int i = 1; i <= RiskModelCfgConstants.MCHT_RISK_GRADE_COUTN; i++) {// 规定了只有三个商户风险等级
				RiskValueOperateVo resultVo = new RiskValueOperateVo();
				resultVo.setRiskId(riskId);
				resultVo.setValueId(vo.getValueId());
				resultVo.setRiskLevel(i + "");
				if (i == 1) {
					resultVo.setRiskOperate(vo.getParam1());
				}
				if (i == 2) {
					resultVo.setRiskOperate(vo.getParam2());
				}
				if (i == 3) {
					resultVo.setRiskOperate(vo.getParam3());
				}
				riskValueOperateVoList.add(resultVo);
			}
		}
		// 数据持久化
		RiskMngService.getInstance().saveRiskValueOperate(riskValueOperateVoList);
		 //记录日志
		 sessionUserInfo.addBizLog("update.log",
		 new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
		 "[风控管理]--修改:编号=[" + riskBaseCfg.getRiskId() + "]" });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用停用")
	public void stautsActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		 //获取当前用户
		 SessionUserInfo sessionUserInfo =
		 SessionUserInfo.getSessionUserInfo();
		 //获取页面传递的信息
		 UpdateRequestBean reqBean = updateMap.get("riskInfo");
		 String riskId = reqBean.getParameter("riskId");
		 String riskStauts = reqBean.getParameter("riskStauts");
		 riskStauts = "00".equals(riskStauts) ? "99" : "00";
		 RiskBaseCfg riskBaseCfg = new RiskBaseCfg();
		 riskBaseCfg.setRiskId(riskId);
		 riskBaseCfg.setRiskStauts(riskStauts);
		 riskBaseCfg.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前用户
		 riskBaseCfg.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间为当前时间
		 //数据持久化
		RiskMngService.getInstance().statusChange(riskBaseCfg);
		 //记日志
		 sessionUserInfo.addBizLog("update.log",
		 new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
		 "[风控管理]--启用/停用:编号=[" + riskId + "]" });
	}
}
