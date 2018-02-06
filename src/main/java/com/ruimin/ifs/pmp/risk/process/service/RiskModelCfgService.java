package com.ruimin.ifs.pmp.risk.process.service;

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
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelVO;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelValuesVO;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

@Service
@SnowDoc(desc = "模型管理")
public class RiskModelCfgService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static RiskModelCfgService getInstance() throws SnowException {
		return ContextUtil.getSingleService(RiskModelCfgService.class);
	}

	/**
	 * 查询【模型信息】
	 * 
	 * @param qriskModelId
	 *            模型编号【模糊查询】
	 * @param qriskModelName
	 *            模型名称【模糊查询】
	 * @param qriskModelType
	 *            模型类型
	 * @param qstatus
	 *            模型状态
	 * @param qactionBitmap
	 *            风控动作
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAll(String qriskModelId, String qriskModelName, String qriskModelType, String qstatus,
			String qactionBitmap, Page page) throws SnowException {
//		String[] actionParamArray = {};
//		String[] qstatusArray = {};
//		if(qstatus != null){
//			qstatusArray = qstatus.split(",");
//		}
//		if(qactionBitmap != null){
//			actionParamArray = qactionBitmap.split(",");
//			if(qactionBitmap.equals("11")){//往数组中增加"01"和"10"
//				actionParamArray = (String[])ArrayUtils.add(actionParamArray, "01");
//				actionParamArray = (String[])ArrayUtils.add(actionParamArray, "10");
//			}else{
//				actionParamArray = (String[])ArrayUtils.add(actionParamArray, "11");
//			}
//		}
		
//		DBDao dao = DBDaos.newInstance();
//		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.queryAll",
//				RqlParam.map().set("qriskModelId", StringUtils.isBlank(qriskModelId) ? "" : "%" + qriskModelId + "%")
//						.set("qriskModelName", StringUtils.isBlank(qriskModelName) ? "" : "%" + qriskModelName + "%")
//						.set("qriskModelType", StringUtils.isBlank(qriskModelType) ? "" : qriskModelType)
//						.set("qstatus", StringUtils.isBlank(qstatus) ? null : qstatusArray)
//						.set("qactionBitmap", StringUtils.isBlank(qactionBitmap) ? null : actionParamArray),
//				page);
		
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.queryAll",
				RqlParam.map().set("qriskModelId", StringUtils.isBlank(qriskModelId) ? "" : "%" + qriskModelId + "%")
						.set("qriskModelName", StringUtils.isBlank(qriskModelName) ? "" : "%" + qriskModelName + "%")
						.set("qriskModelType", StringUtils.isBlank(qriskModelType) ? "" : qriskModelType)
						.set("qstatus", StringUtils.isBlank(qstatus) ? null : qstatus)
						.set("qactionBitmap", StringUtils.isBlank(qactionBitmap) ? null : qactionBitmap),
				page);
	}

	public void updModel(RiskModelVO mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(mchtVo);

	}
	
	
	public void enableOrDisable(RiskModelVO model){
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.enableOrDisable", model);
	}
	/**
	 * 查询【模型阈值信息】
	 * 
	 * @param qriskModelId
	 *            模型编号
	 * @return
	 * @throws SnowException
	 */
	public List<Object> queryCfg(String riskModelId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.queryCfg",
				RqlParam.map().set("riskModelId", StringUtils.isBlank(riskModelId) ? "" : riskModelId));
	}

	/**
	 * 保存【模型阈值信息】
	 * 
	 * @return
	 * @throws SnowException
	 */
	public void updateCfgValue(List<RiskModelValuesVO> list, String riskModelId) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		dao.executeSql("delete from RISK_MODEL_VALUES where RISK_MODEL_ID=" + riskModelId);

		if (list != null && list.size() > 0) {
			dao.insert(list);
		}

	}

	/**
	 * 获取最大阈值编号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String getMaxId(String riskModelId) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		List<String> s = dao.queryAll(
				"select VALUE_ID from RISK_MODEL_VALUES where RISK_MODEL_ID=" + riskModelId + "order by VALUE_ID desc",
				String.class);

		if (s == null || s.isEmpty()) {

			return "";
		} else
			return s.get(0);
	}

	/**
	 * 获取阈值描述
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String getValueExplain(String riskModelId) throws SnowException {
		DBDao dao = DBDaos.newInstance();

		List<String> s = dao.queryAll("select VALUE_EXPLAIN from RISK_MODEL_CFG where RISK_MODEL_ID=" + riskModelId,
				String.class);

		if (s == null || s.isEmpty()) {

			return "";
		} else
			return s.get(0);

	}

	/**
	 * 根据模型编号，查询模型名称
	 * 
	 * @param key
	 * @return
	 * @throws SnowException
	 */
	public List<Object> getRiskModelName(String key) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] modelIdList = key.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.getRiskModelName",
				RqlParam.map().set("prodIdArray", modelIdList));
	}
	/**
	 * 批量新增模型阀值配置信息
	 * @param addRiskModelList
	 * @throws SnowException
	 */
	public void batchAddRiskModel(List<RiskModelValuesVO> addRiskModelList) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if(null == addRiskModelList || addRiskModelList.size() == 0){
			return;
		}
		dao.insert(addRiskModelList);
	}
	/**
	 * 批量修改模型阀值配置信息
	 * @param updRiskModelList
	 * @throws SnowException
	 */
	public void batchUpdRiskModel(List<RiskModelValuesVO> updRiskModelList) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(null == updRiskModelList || updRiskModelList.size() == 0){
			return;
		}
		for(RiskModelValuesVO param:updRiskModelList){
			dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.updRiskModel",param);
		}
	}
	/**
	 * 批量删除模型阀值配置信息
	 * @param dltRiskModelList
	 * @throws SnowException
	 */
	public void batchDeleteRiskModel(List<RiskModelValuesVO> dltRiskModelList) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(null == dltRiskModelList || dltRiskModelList.size() == 0){
			return;
		}
		for(RiskModelValuesVO param:dltRiskModelList){
			dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.deleteRiskModel",RqlParam.map()
					.set("riskModelId", param.getRiskModelId()).set("valueId", param.getValueId()));
		}
	}
	/**
	 * 根据模型编号查询模型阈值模板
	 * @param riskModelId
	 * @return
	 * @throws SnowException
	 */
	public String getValueModel(String riskModelId) throws SnowException{
		DBDao dao = DBDaos.newInstance();

		List<String> s = dao.queryAll("select VALUE_MODEL from RISK_MODEL_CFG where RISK_MODEL_ID=" + riskModelId,
				String.class);

		if (s == null || s.isEmpty()) {

			return "";
		} else
			return s.get(0);
	}
	
	
	/**
	 * 根据模型编号，查询当前模型被哪些风控引用，这些引用当者配置了哪些当前模型里面的阈值
	 * @param riskModelId
	 * @return
	 */
	public String getValueIdString(String riskModelId){
		DBDao dao = DBDaos.newInstance();
		String valueIdString = (String) dao.selectOne("com.ruimin.ifs.pmp.risk.rql.riskModelCfg.getValueIdString",RqlParam.map()
				.set("riskModelId", riskModelId));
		if (StringUtil.isEmpty(valueIdString)) {
			valueIdString = "";
		}
		return valueIdString;
		
	}
}
