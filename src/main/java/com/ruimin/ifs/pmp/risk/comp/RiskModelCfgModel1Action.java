package com.ruimin.ifs.pmp.risk.comp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;

import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.bean.SaveDataUser;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.CommonUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelCfgVO;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelVO;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelValueMapping;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelValuesModel1VO;
import com.ruimin.ifs.pmp.risk.process.bean.RiskModelValuesVO;
import com.ruimin.ifs.pmp.risk.process.service.RiskModelCfgService;
import com.ruimin.ifs.pmp.sysConf.process.service.AccountTypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

@ActionResource
@SnowDoc(desc = "风控模型管理范例1")
public class RiskModelCfgModel1Action {
	/**
	 * 查询模型阈值信息
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public List<RiskModelValuesModel1VO> queryCfg(QueryParamBean queryBean) throws SnowException {

		/** 查询条件 */
		String riskModelId = queryBean.getParameter("riskModelId");// 模型编号
		
		List<RiskModelValuesModel1VO> result = new ArrayList<>();
		//根据模型编号查询模型阈值信息
		List<Object> r = RiskModelCfgService.getInstance().queryCfg(riskModelId);
		//根据模型编号查询模型阈值说明
		String valueExplain=RiskModelCfgService.getInstance().getValueExplain(riskModelId);
		//根据模型编号查询模型阈值模板
		String valueModel = RiskModelCfgService.getInstance().getValueModel(riskModelId);
		List<String> typeArray = null;
		if(valueModel == null || valueModel == "" ){
			
		}else{
			typeArray = jsonToList(valueModel);
		}
		
		String valueIdString =  RiskModelCfgService.getInstance().getValueIdString(riskModelId);
		//根据模型编号查询阈值模板
		for (Object r2 : r) {
			RiskModelValuesVO r3 = (RiskModelValuesVO) r2;
			//RiskModelValueMapping r3 = (RiskModelValueMapping) r2;
			RiskModelValuesModel1VO r4 = new RiskModelValuesModel1VO();
			r4.setValueIdString(valueIdString);
			r4.setValueId(r3.getValueId());
			r4.setCrtDateTime(r3.getCrtDateTime());
			r4.setCrtTlr(r3.getCrtTlr());
			r4.setLastUpdDateTime(r3.getLastUpdDateTime());
			r4.setLastUpdTlr(r3.getLastUpdTlr());
			// 获取阈值字段value
			List<String> listString = JSON.parseArray("["+r3.getValue()+"]", String.class);
			r4.setValueExplain(valueExplain);
			// 生成模型参数配置字段
			for(int a = 0;a<listString.size();a++){
				List<Map<String, Object>> listMap = JSON.parseObject(listString.get(a),
						new TypeReference<List<Map<String, Object>>>() {});
				if(listString.size() == typeArray.size() && typeArray != null){
					String type = typeArray.get(a);
					for (Map<String, Object> m : listMap) {
						//-----------根据现场需求param2和param3弃用
						// 解析阈值json
						if ("param1".equals(m.get("key"))&&!"".equals((String) m.get("value"))) {
							
							if("金额".equals(type)){//如果是金额类型的，分转元显示出来
								r4.setParam1(CommonUtil.transFenToYuan((String) m.get("value")));
								r4.setValueExplain(r4.getValueExplain().replace("{param1}", CommonUtil.transFenToYuan( (String)m.get("value"))));
							}else{
								r4.setValueExplain(r4.getValueExplain().replace("{param1}",  (String)m.get("value")));
								r4.setParam1((String) m.get("value"));
							}
						}
						if ("param2".equals(m.get("key"))&&!"".equals((String) m.get("value"))) {
							
							if("金额".equals(type)){
								r4.setParam2(CommonUtil.transFenToYuan((String) m.get("value")));
								r4.setValueExplain(r4.getValueExplain().replace("{param2}",  CommonUtil.transFenToYuan((String)m.get("value"))));
							}else{
								r4.setValueExplain(r4.getValueExplain().replace("{param2}",  (String)m.get("value")));
								r4.setParam2((String) m.get("value"));
							}
						}
						if ("param3".equals(m.get("key"))&&!"".equals((String) m.get("value"))) {
							
							if("金额".equals(type)){
								r4.setParam3(CommonUtil.transFenToYuan((String) m.get("value")));
								r4.setValueExplain(r4.getValueExplain().replace("{param3}",CommonUtil.transFenToYuan((String)m.get("value"))));
							}else{
								r4.setValueExplain(r4.getValueExplain().replace("{param3}",  (String)m.get("value")));
								r4.setParam3((String) m.get("value"));
							}
						}
					}
					
				}
			}
			result.add(r4);
		}
		/** 返回查询结果 */
		return result;
	}

	/**
	 * 模型阈值修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "模型阈值修改")
	public void update(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 更新操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取数据集
		UpdateRequestBean reqBean = updateBean.get("modelDtst1");
		String riskModelId = reqBean.getParameter("riskModelId");//模型编号
		//查询最大阀值编号
		String maxValueId = RiskModelCfgService.getInstance().getMaxId(riskModelId);
		int valueId;
		if("".equals(maxValueId)){
			valueId = 0;
		}else{
			
			valueId = Integer.valueOf(maxValueId.substring(2).trim());
		}
		//根据模型编号查询模型阈值说明
		String valueExplain=RiskModelCfgService.getInstance().getValueExplain(riskModelId);
		//将模型阈值说明以逗号分开
		String[] valueExplainList = valueExplain.split(",");
		String[] newValueExplainList = new String[valueExplainList.length];
		//截取{之前的文字
		for (int i = 0; i < newValueExplainList.length; i++) {
			newValueExplainList[i] = valueExplainList[i].substring(0, valueExplainList[i].lastIndexOf("{"));
		}
		//根据模型编号查询模型阈值模板
		String valueModel = RiskModelCfgService.getInstance().getValueModel(riskModelId);
		List<String> typeArray = jsonToList(valueModel);
		List<String> singArray = jsonToListSing(valueModel);
		//先建好模型阈值新增数组（如果有新增的阈值，将新增的阈值放入数组中）
		List<RiskModelValuesVO> addRiskModelList = new ArrayList<>();
		//模型阈值修改数组
		List<RiskModelValuesVO> updRiskModelList = new ArrayList<>();
		//模型阈值删除数组
		List<RiskModelValuesVO> dltRiskModelList = new ArrayList<>();
		while(reqBean.hasNext()){
			Map<String, String> map = reqBean.next();
			RiskModelValuesVO riskModelValuesVO = new RiskModelValuesVO();
			if(reqBean.getRecodeState() == UpdateRequestBean.NONE){//如果没有改变模型阈值
				continue;
			}
			//封装模型信息
			riskModelValuesVO.setValueId(map.get("valueId"));//阀值编号
			riskModelValuesVO.setRiskModelId(riskModelId);//模型编号
			riskModelValuesVO.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人
			riskModelValuesVO.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间
			String value = "";
			if(valueExplainList.length == typeArray.size()){
				//-----------根据现场需求param2和param3弃用，即现场的需求size为1
				if(valueExplainList.length == 3 ){//阈值有三个限制阈值时
					for(int x = 0;x<typeArray.size();x++){
						if("金额".equals(typeArray.get(x))){
							map.put("param"+(x+1),CommonUtil.transYuanToFen(map.get("param"+(x+1))));
						}
					}
					value = "[{\"key\":\"param1\",\"name\":\""+newValueExplainList[0]+"\",\"value\":\"" + map.get("param1")
				+ "\",\"type\":\""+typeArray.get(0)+"\",\"sing\":\""+singArray.get(0)+"\"}],[{\"key\":\"param2\",\"name\":\""+newValueExplainList[1]+"\",\"value\":\"" + map.get("param2")
				+ "\",\"type\":\""+typeArray.get(1)+"\",\"sing\":\""+singArray.get(0)+"\"}],[{\"key\":\"param3\",\"name\":\""+newValueExplainList[2]+"\",\"value\":\"" +  map.get("param3") + "\",\"type\":\""+typeArray.get(2)+"\",\"sing\":\""+singArray.get(0)+"\"}]";
				}else if(valueExplainList.length == 2){//阈值有两个限制阈值时
					for(int x = 0;x<typeArray.size();x++){
						if("金额".equals(typeArray.get(x))){
							map.put("param"+(x+1),CommonUtil.transYuanToFen(map.get("param"+(x+1))));
						}
					}
					value = "[{\"key\":\"param1\",\"name\":\""+newValueExplainList[0]+"\",\"value\":\"" + map.get("param1")
					+ "\",\"type\":\""+typeArray.get(0)+"\",\"sing\":\""+singArray.get(0)+"\"}],[{\"key\":\"param2\",\"name\":\""+newValueExplainList[1]+"\",\"value\":\"" + map.get("param2")
					+ "\",\"type\":\""+typeArray.get(1)+"\",\"sing\":\""+singArray.get(0)+"\"}]";
				}else if(valueExplainList.length == 1){//阈值有一个限制阈值时
					for(int x = 0;x<typeArray.size();x++){
						if("金额".equals(typeArray.get(x))){
							map.put("param"+(x+1),CommonUtil.transYuanToFen(map.get("param"+(x+1))));
						}
					}					
					value = "[{\"key\":\"param1\",\"name\":\""+newValueExplainList[0]+"\",\"value\":\"" + map.get("param1")
					+ "\",\"type\":\""+typeArray.get(0)+"\",\"sing\":\""+singArray.get(0)+"\"}]";
				}else{
					
				}
			}
			
			riskModelValuesVO.setValue(value);//阀值
			if(reqBean.getRecodeState() == UpdateRequestBean.DELETE){//删除
				dltRiskModelList.add(riskModelValuesVO);
			}
			if(reqBean.getRecodeState() == UpdateRequestBean.INSERT){//新增
				StringBuffer riskId = new StringBuffer();
				riskId.append("YZ").append(StringUtil.leftPad(String.valueOf(++valueId),4,"0"));
				String currentValueId = riskId.toString();
				riskModelValuesVO.setValueId(currentValueId);//阀值序号
				riskModelValuesVO.setCrtTlr(riskModelValuesVO.getLastUpdTlr());//创建人
				riskModelValuesVO.setCrtDateTime(riskModelValuesVO.getLastUpdDateTime());//创建时间
				addRiskModelList.add(riskModelValuesVO);
			}
			if(reqBean.getRecodeState() == UpdateRequestBean.MODIFY){//修改
				updRiskModelList.add(riskModelValuesVO);
			}
		}
		//数据持久化---批量新增修改删除模型阀值配置
		RiskModelCfgService.getInstance().batchAddRiskModel(addRiskModelList);
		RiskModelCfgService.getInstance().batchUpdRiskModel(updRiskModelList);
		RiskModelCfgService.getInstance().batchDeleteRiskModel(dltRiskModelList);
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[风控模型管理]--参数配置:模型编号=[" +riskModelId + "]" });

	}
	/**
	 * 解析json字符窜，将json中key是type的值取出放在数组里面返回(type标示这列是笔数类型/金额类型/占比类型)
	 * @param json
	 * @return
	 */
	public static List<String> jsonToList(String valueModel){
//		valueModel = valueModel.replaceAll("\\[", "");
//		valueModel = valueModel.replaceAll("\\]", "");
//		valueModel = "["+valueModel+"]";
		//将阈值模板jason解析成数组(各种转换只为拿到阈值的字段类型：金额类型，笔数类型，占比类型)
		JSONArray json = JSONArray.fromObject(valueModel);
		List<String> typeArray = new ArrayList<>();
		if(json.size()>0){
			for(int i=0;i<json.size();i++){
				JSONObject job = json.getJSONObject(i);// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				typeArray.add((String)job.get("type"));
			}
		}	
		return typeArray;
	}
	/**
	 * 解析json字符窜，将json中key是sing的值取出放在数组里面返回
	 * @param json
	 * @return
	 */
	public static List<String> jsonToListSing(String valueModel){
//		valueModel = valueModel.replaceAll("\\[", "");
//		valueModel = valueModel.replaceAll("\\]", "");
//		valueModel = "["+valueModel+"]";
		//将阈值模板jason解析成数组(各种转换只为拿到阈值的字段类型：金额类型，笔数类型，占比类型)
		JSONArray json = JSONArray.fromObject(valueModel);
		List<String> singArray = new ArrayList<>();
		if(json.size()>0){
			for(int i=0;i<json.size();i++){
				JSONObject job = json.getJSONObject(i);// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				singArray.add((String)job.get("sing"));
			}
		}	
		return singArray;
	}
}
