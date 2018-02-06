package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.google.gson.Gson;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveInfConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveRandomDiscountInfo;
import com.ruimin.ifs.pmp.pubTool.util.HttpClientUtils;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SnowDoc(desc = "活动临时信息Service")
@Service
public class ActvInfTmpService extends SnowService {
    public String url =SysParamUtil.getParam(MchtChnlRequestConstants.AMT_TOTAL_URL);
    
	public static ActvInfTmpService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ActvInfTmpService.class);
	}

	/**
	 * 根据页面传递参数查询活动列表
	 */
	public PageResult queryAll(String activeNo, String activeNm, String activeType, String activeStat, String prodId,
			String gpTp,String auditId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.queryAll",
                RqlParam.map().set("activeNo", StringUtil.isBlank(activeNo)?"":activeNo).set("activeNm", StringUtil.isBlank(activeNm)? "" : "%" + activeNm + "%")
                        .set("activeType", activeType).set("activeStat", StringUtil.isBlank(activeStat)?"":activeStat)
                        .set("prodId",StringUtil.isBlank(prodId)?"" : "%" +  prodId + "%")
                        .set("gpTp",StringUtil.isBlank(gpTp)?"": gpTp)
                        .set("auditId", StringUtil.isBlank(auditId)?"":auditId),
                page);
	}

	/**
	 * 根据活动方法编号查询活动列表（分页）
	 * 
	 * @param methodNo
	 *            活动方法编号
	 * @param page
	 *            分页对象
	 */
	public PageResult queryByMethod(String methodNo, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.queryListByMethod", methodNo, page);
	}

	/**
	 * 根据活动类型获取生成活动编号
	 * 
	 * @param activeType
	 *            活动类型
	 * @return 活动编号
	 */
	public String genActiveNoByType(String activeType) throws SnowException {
		StringBuffer activeNo = new StringBuffer();
		DBDao dao = DBDaos.newInstance();
		String currentMaxNo = (String) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.getMaxSeqByType",
				activeType);
		if (StringUtil.isEmpty(currentMaxNo)) {
			currentMaxNo = "AT000000";
		}
		int currentNoInt = Integer.valueOf(currentMaxNo.substring(4));
		activeNo.append(ActiveInfConstants.ACTIVE_NO_PRE).append(activeType.substring(0, 1)).append(
				StringUtil.leftPad(String.valueOf(++currentNoInt), ActiveInfConstants.ACTIVE_NO_SEQ_LENGTH, "0"));
		return activeNo.toString();
	}
	
	public void statusActive(ActiveInfTmpVO param)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.statusActive",
				param);
	}
	
	
	
	
	
	
	public void addActive(ActiveInfTmpVO param)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.addActive",
				param);
	}
	public void updateActive(ActiveInfTmpVO param)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvInfTmp.updateActive",
				param);
	}
	
	/**
     * 请求营销活动金额
     * @param activeNo
     * @param amtTotal
     * @return
     */
    @SuppressWarnings("unchecked")
    public String initAmtTotal(String activeNo,String amtTotal){
        Map<String , String> params = new HashMap<>();
        params.put("activityId",activeNo);
        params.put("totalAmt", amtTotal);
        String send = HttpClientUtils.send(params, "601.601005", url, false);
        params = new Gson().fromJson(send, params.getClass());
        return params.get("optFlag");// 响应标志
        
    }
    
    
    @SuppressWarnings("unchecked")
    public String initActiveRandomDiscountFunds(String activeNo, List<ActiveRandomDiscountInfo> ardList){
        Map<String , Object> params = new HashMap<>();
        params.put("activityId",activeNo);
        params.put("info",ardList);
        String send = HttpClientUtils.send(params, "601.601006", url, false);
        params = new Gson().fromJson(send, params.getClass());
        return String.valueOf(params.get("optFlag"));// 响应标志
    }
    
    
    
    
    
    
    
}
