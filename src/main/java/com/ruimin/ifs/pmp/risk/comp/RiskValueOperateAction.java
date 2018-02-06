package com.ruimin.ifs.pmp.risk.comp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.process.bean.RiskValueOperateMapping;
import com.ruimin.ifs.pmp.risk.process.service.RiskValueOperateService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@SnowDoc(desc = "阈值操作")
@ActionResource
public class RiskValueOperateAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询阈值操作")
	public List<RiskValueOperateMapping> queryOp(QueryParamBean queryBean) throws SnowException {
		//查询条件
		String valueId = queryBean.getParameter("valueId");
		String riskModelId = queryBean.getParameter("riskModelId");
		String riskId = queryBean.getParameter("riskId");
		String sign = queryBean.getParameter("sign");
		if("addSign".equals(sign)){//新增
			List<RiskValueOperateMapping> result = new ArrayList<RiskValueOperateMapping>();
			String[] valueIds = valueId.split(",");
			if(!"".equals(valueId)){
				for(int i = 0;i<valueIds.length;i++){
					//查询阀值
					RiskValueOperateMapping vo1 = RiskValueOperateService.getInstance().queryValue(valueIds[i],riskModelId);
					RiskValueOperateMapping vo = new RiskValueOperateMapping();
					vo.setValueId(valueIds[i]);
					//vo.setValue(valueIds[i]+":"+changeValue(vo1.getValue(),acctMap));
					vo.setValue(valueIds[i]+":"+changeValue(vo1.getValue()));
					vo.setParam1("00");
					vo.setParam2("00");
					vo.setParam3("00");
					result.add(vo);
				}
			}
			return result;
		}else if("selectSign".equals(sign)){//点击详情时查询配置详情信息
			List<RiskValueOperateMapping> result = new ArrayList<RiskValueOperateMapping>();
			List<Object> lists = RiskValueOperateService.getInstance().queryAll(riskId,riskModelId);
			for(Object list : lists){
				RiskValueOperateMapping vo = (RiskValueOperateMapping)list;
				//vo.setValue(vo.getValueId()+":"+changeValue(vo.getValue(),acctMap));
				vo.setValue(vo.getValueId()+":"+changeValue(vo.getValue()));
				result.add(vo);
			}
			return result;
		}else{
			return null;
		}
	
		
	}
	/**
	 * 解析阈值
	 * @param str
	 * @param acctMap 
	 * @return
	 */
	public static  String changeValue(String str){
//		str = str.replaceAll("\\[", "");
//		str = str.replaceAll("\\]", "");
//		String str2 = "["+str+"]";
		String string = "";
		//JSONArray json = JSONArray.fromObject(str2);
		JSONArray json = JSONArray.fromObject(str);
		if(json.size()>0){
			  for(int i=0;i<json.size();i++){
			    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    String name = (String)job.get("name");
			    String value = (String)job.get("value");
			    String type = (String)job.get("type");
			    if("金额".equals(type)){
			    	value = BaseUtil.transFenToYuan(value);
			    }
			    string += name;
			    string += value;
			  }
			}
		return string;

		
	}
	/**
	 * 改变风控动作的显示
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 */
	public static String getName(FieldBean bean, String key, ServletRequest request) throws SnowException{
		String name = "";
		if(StringUtil.isEmpty(key)){
			return name;
		}
		if(key.equals("00")){
			name = "无";
		}
		if(key.equals("01")){
			name = "拒绝";
		}
		if(key.equals("03")){
			name = "警告";
		}
		return name;
	}
}
