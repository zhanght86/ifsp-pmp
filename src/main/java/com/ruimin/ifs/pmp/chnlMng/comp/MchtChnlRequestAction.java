package com.ruimin.ifs.pmp.chnlMng.comp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.chnlMng.process.service.MchtChnlRequestService;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

/**
 * 
 * 通道商户请求接口
 * 
 * @author zhangjc
 *
 */
public class MchtChnlRequestAction {
	/**
	 * 通道商户请求接口方法
	 * 
	 * @param mchtInfo
	 *            商户通道请求-实体类
	 * @param applyWay
	 *            申请方式【预申请、指定通道申请、直接申请】
	 * @return
	 * @throws Exception
	 * @throws SnowException
	 */
	@Action
	public void directApplyRequest(MchtChnlRequestVO mchtInfo, String applyWay) throws Exception, SnowException {
		/** 加载SnowLog */
		Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);

		/******************************************** 计时开始 ********************************************/
		Long startTime = System.currentTimeMillis();

		try {
			MchtChnlRequestService.getInstance().vaildApplyWay(applyWay);// 校验申请方式是否正确
			log.info("通道商户请求接口【" + applyWay + "】-请求开始...");
			log.info("商户号：" + mchtInfo.getChlMerId());

			// -----------------------------------------STEP NO1
			// 组装报文-----------------------------------------//
			/** 校验请求参数 */
			MchtChnlRequestService.getInstance().vaildReqParam(mchtInfo, applyWay);

			/** 组装报文 */
			String requestMsg = MchtChnlRequestService.getInstance().buildApplyMsg(mchtInfo, applyWay);

			// -----------------------------------------STEP NO2
			// 传输阶段-----------------------------------------//
			/** 配置传输基本信息 */
			URL url = null;// 资源地址
			if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_PER)) {
				url = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_PER_SEVR_URL));
			} else if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_CHL)) {
				url = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_CHL_SEVR_URL));
			} else {
				SnowExceptionUtil.throwErrorException("申请方式【" + applyWay + "】无法识别！");
			}

			String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式

			/** 建立服务器连接 */
			HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);

			/** 发送报文 */
			HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);

			// -----------------------------------------STEP NO3
			// 获取响应-----------------------------------------//
			/** 获取服务端响应 */
			String retMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
			Map<String, String> serRetMap = new HashMap<String, String>();
			Gson gson = new Gson();
			serRetMap = gson.fromJson(retMsg, serRetMap.getClass());
			String respCode = serRetMap.get("respCode");// 响应码
			// 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
			if (!respCode.substring((respCode.length() - 4), respCode.length())
					.equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
				SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
			}

			/******************************************** 计时结束 ********************************************/
			log.info("通道商户请求接口【" + applyWay + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

		} catch (SnowException se) {
			SnowExceptionUtil.throwErrorException(se.getMessage());
			log.error("通道商户请求接口【" + applyWay + "】-请求失败：商户号【" + mchtInfo.getChlMerId() + "】，原因：" + se.getMessage());
			log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
		}
	}
}
