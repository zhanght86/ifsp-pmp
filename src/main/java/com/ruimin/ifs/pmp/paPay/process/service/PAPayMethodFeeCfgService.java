package com.ruimin.ifs.pmp.paPay.process.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.paPay.common.constants.PAMngConstants;
import com.ruimin.ifs.pmp.paPay.process.bean.PagyMixpayPayMethodFeeCfg;
import com.ruimin.ifs.pmp.pubTool.util.HttpClientUtils;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

@Service
public class PAPayMethodFeeCfgService {
	public String url = SysParamUtil.getParam(PAMngConstants.PAGY_PAY_CFG);
	/** 加载SnowLog */
	Logger log = SnowLog.getLogger(PAPayMethodFeeCfgService.class);

	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static PAPayMethodFeeCfgService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(PAPayMethodFeeCfgService.class);
	}

	/**
	 * 查询平安支付方式
	 * 
	 * @param pmtId
	 *            支付方式编号
	 * @param pmfId
	 * @param pmtTag
	 * @param pmtName
	 * @param page
	 * @return
	 */
	public PageResult queryMain(String pmtId, String pmfId, String pmtTag, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.paPay.rql.PAPayMethodFeeCfg.queryMain",
				RqlParam.map().set("pmtId", StringUtils.isBlank(pmtId) ? "" : "%" + pmtId + "%")
						.set("pmfId", StringUtils.isBlank(pmfId) ? "" : "%" + pmfId + "%")
						.set("pmtTag", StringUtils.isBlank(pmtTag) ? "" : "%" + pmtTag + "%"),
				page);
	}

	/**
	 * 修改
	 * 
	 * @param methodInfo
	 * @throws SnowException
	 */
	public void update(PagyMixpayPayMethodFeeCfg methodInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(methodInfo);
	}

	/**
	 * 支付费率查询
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void quyPayCfg() throws Exception {
		Map<String, String> reqMap=new HashMap<>();
		reqMap.put("encoding", "UTF-8");
		reqMap.put("chlId", "C0001");
		reqMap.put("aplType", "2");
		reqMap.put("pagySysId", "301");
		reqMap.put("pagyType", "03");
		reqMap.put("pagyAcqNo", "7591100001");
		String requestMsg = new Gson().toJson(reqMap);  
		String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
		/** 建立服务器连接 */
		HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(new URL(url), requestMehtod);
		/** 发送报文 */
		HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
		// 获取响应-----------------------------------------//
		/** 获取服务端响应 */
		String resMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
		Map<String, String> serRetMap = new HashMap<String, String>();
		Gson gson = new Gson();
		serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
		String respCode = serRetMap.get("respCode");// 响应码
		// 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
		if (respCode.substring((respCode.length() - 4), respCode.length())
				.equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
			log.info("支付费率查询成功");
		} else {
			log.info("支付费率查询失败");
			SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
		}
	}

}
