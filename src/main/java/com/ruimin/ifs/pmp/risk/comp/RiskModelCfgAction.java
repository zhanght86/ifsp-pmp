package com.ruimin.ifs.pmp.risk.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;

import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.risk.common.constants.RiskModelCfgConstants;

import com.ruimin.ifs.pmp.risk.process.bean.RiskModelCfgVO;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelVO;
import com.ruimin.ifs.pmp.risk.process.service.RiskModelCfgService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ruimin.ifs.pmp.pubTool.util.BitmapUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

@ActionResource
@SnowDoc(desc = "风控模型管理")
public class RiskModelCfgAction {
	/**
	 * 查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {

		/** 查询条件 */
		String qriskModelId = queryBean.getParameter("qriskModelId");// 模型编号
		String qriskModelName = queryBean.getParameter("qriskModelName");// 模型名称
		String qriskModelType = queryBean.getParameter("qriskModelType");// 模型类型
		String qstatus = queryBean.getParameter("qstatus");// 模型状态
		String qactionBitmap = BitmapUtil.stringToBitmap(queryBean.getParameter("qactionBitmap"), ",");// 风控动作
		// 获取结果集
		PageResult r = RiskModelCfgService.getInstance().queryAll(qriskModelId, qriskModelName, qriskModelType, qstatus,
				qactionBitmap, queryBean.getPage());
		PageQueryResult result = new PageQueryResult();

		List<RiskModelCfgVO> list = (List<RiskModelCfgVO>) r.getQueryResult();
		int aa = r.getTotalCount();
		System.out.println(aa);
		
		for (int i = 0; i < list.size(); i++) {
			// 转换位图
			list.get(i).setActionBitmap(BitmapUtil.bitmapToString(list.get(i).getActionBitmap(), ","));
			//将阈值模型变成key-name-type的形式存入changeValueModel中给到jsp页面使用
			String valueModel = list.get(i).getValueModel();
//			valueModel = valueModel.replaceAll("\\[", "");
//			valueModel = valueModel.replaceAll("\\]", "");
//			valueModel = "["+valueModel+"]";
			String newValueModel = "";
			JSONArray json = JSONArray.fromObject(valueModel);
			if(json.size()>0){
				 for(int a=0;a<json.size();a++){
					 JSONObject job = json.getJSONObject(a);
					 String key = (String)job.get("key");
					 String name = (String)job.get("name");
					 String type =  (String)job.get("type");
					 if(a+1 == json.size()){
						 newValueModel += key+"-"+name+"-"+type;
					 }else{
						 newValueModel +=key+"-"+name+"-"+type+",";
					 }
				 }
			}
			list.get(i).setChangeValueModel(newValueModel);
		}
		result.setTotalCount(r.getTotalCount());
		result.setQueryResult(list);
		/** 返回查询结果 */
		return result;
	}

	/**
	 * 修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void updModel(Map<String, UpdateRequestBean> updateMap) throws SnowException {

		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("riskModelCfg");
		
		SessionUserInfo userInfo = SessionUserInfo.getSessionUserInfo();
		
		/** 导入实体类 */
		RiskModelCfgVO page = new RiskModelCfgVO();
		RiskModelVO model = new RiskModelVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), page);
		}
		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		// 转换位图
		model.setActionBitmap(BitmapUtil.stringToBitmap(page.getActionBitmapSel(), ","));
		model.setRiskModelDesc(page.getRiskModelDesc());
		model.setRiskModelId(page.getRiskModelId());
		model.setRiskModelType(page.getRiskModelTypeSel());
		model.setRiskModelName(page.getRiskModelName());
		model.setLastUpdDateTime(DateUtil.get14Date());
		model.setLastUpdTlr(userInfo.getTlrno());
		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/

		model.setStatus(RiskModelCfgConstants.MODEL_STAT_NORMAL);// 修改时状态改为：启用，暂不审核

		RiskModelCfgService.getInstance().updModel(model);
		
		userInfo.addBizLog("update.log",new String[]{userInfo.getTlrno(), userInfo.getBrno(),"修改风控模型，模型编号="+model.getRiskModelId()});
	}
	
	@Action()
	@SnowDoc(desc = "启用/停用")
	public void enableOrDisable(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("riskModelCfg");
		
		SessionUserInfo userInfo = SessionUserInfo.getSessionUserInfo();
		RiskModelVO model = new RiskModelVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), model);
		}
		String bizLogMessage="";
		if(RiskModelCfgConstants.MODEL_STAT_PAUSE.equals(model.getStatus())){
			model.setStatus(RiskModelCfgConstants.MODEL_STAT_NORMAL);
			bizLogMessage = "启用风控模型，风控模型编号="+model.getRiskModelId();
		}else{
			model.setStatus(RiskModelCfgConstants.MODEL_STAT_PAUSE);
			bizLogMessage = "停用风控模型，风控模型编号="+model.getRiskModelId();
		}
		model.setLastUpdTlr(userInfo.getTlrno());
		model.setLastUpdDateTime(DateUtil.get14Date());
		
		RiskModelCfgService.getInstance().enableOrDisable(model);
		
		userInfo.addBizLog("update.log",new String[]{userInfo.getTlrno(), userInfo.getBrno(),bizLogMessage});
	}
	
	
	/**
	 * 根据模型编号查询模型名称
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getRiskModelName(FieldBean bean, String key, ServletRequest request) throws SnowException{
		StringBuffer strBuf= new StringBuffer();
	       if(StringUtil.isEmpty(key)){
	    	   return strBuf.toString();
	       }
	       List<Object> riskModelList = RiskModelCfgService.getInstance().getRiskModelName(key);
	       if(riskModelList == null || riskModelList.size() ==0){
	    	   return strBuf.toString();
	       } 
	       for(Object riskModelObj : riskModelList){
	    	   RiskModelCfgVO model = (RiskModelCfgVO)riskModelObj;
	    	   strBuf.append(model.getRiskModelName()).append(",");
	       }
	       return strBuf.toString().substring(0,strBuf.toString().length()-1);
	}

}
