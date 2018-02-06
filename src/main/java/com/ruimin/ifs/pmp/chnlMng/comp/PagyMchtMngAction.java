/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyMchtMngAction.java
 * Author:   zqy
 * Date:     2016年8月01日 上午10:39:09
 * Description: 通道商户登记     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.constant.IfspConstants;
import com.ruim.ifsp.utils.message.IfspFastJsonUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.ChnlAcsInfoConstants;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.common.constants.PagyMchtMngConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyIndirectPayCertCfg;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagySubMchtInfo;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyMchtMngService;
import com.ruimin.ifs.pmp.pubTool.util.Base64Coder;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

/**
 * 名称：通道商户登记 功能：通道商户登记 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道商户登记操作Action")
@ActionResource
public class PagyMchtMngAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询通道商户信息")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询参数
		String chlId = queryBean.getParameter("qchlId");
		String aplStat = queryBean.getParameter("qaplStat");
		String flag = "";
		if (!StringUtils.isEmpty(aplStat)) {
            switch (aplStat) {
                case "07":
                    aplStat = "9";
                    break;
                case "08":
                    aplStat = "00000011";
                    break;
                case "09":
                    aplStat = "00000000";
                    break;
                //如果申请状态为10,则放弃aplStat筛选条件建立新条件(sql)
                case "10":
                    aplStat = null;
                    flag = "e";
                    break;
            }
        }
		
		String payMchtNo = queryBean.getParameter("qpayMchtNo");
		String mchtName = queryBean.getParameter("qmchtName");
		String pagyNo = queryBean.getParameter("qpagyNo");// 通道编号
		String mchtNo = queryBean.getParameter("qmchtNo");// 通道名称
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 分页查询通道信息
		return PagyMchtMngService.getInstance().queryList(chlId, aplStat, payMchtNo, mchtName, pagyNo, mchtNo,flag,
				sessionUserInfo.getBrCode(),queryBean.getPage());

	}
	
	   @Action
	    @SnowDoc(desc = "查询地区码")
	    public PageResult selOrg(QueryParamBean queryBean) throws SnowException {
	        // 获取查询参数
	        String qCtName = queryBean.getParameter("qCtName");
	        String pagyNo = "312";
	        String qCtCode = queryBean.getParameter("qCtCode");
	        // 分页查询通道信息
	        return PagyMchtMngService.getInstance().selOrg(qCtName, pagyNo,qCtCode,queryBean.getPage());

	    }
	   
       @Action
       @SnowDoc(desc = "查询微信支付方式")
       public PageResult weixinBERL(QueryParamBean queryBean) throws SnowException {
           // 获取查询参数
           String qwxberlId = queryBean.getParameter("qwxberlId");
           // 分页查询通道信息
           return PagyMchtMngService.getInstance().weixinBERL(qwxberlId,queryBean.getPage());

       }

	/**
	 * 根据渠道号获取名称method
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getChlName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			ChannelInfo inf = PagyMchtMngService.getInstance().queryChlNameById(key);
			if (inf != null) {
				return inf.getChlName();
			}
		}
		return key;
	}

	/**
	 * 根据通道编号获取名称method
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getPagyName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			PagyInfo inf = PagyMchtMngService.getInstance().queryPagyNameByNo(key);
			if (inf != null) {
				return inf.getPagyName();
			}
		}
		return key;
	}

	/**
	 * 手工申请
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "手工申请")
	@Action(propagation = Propagation.REQUIRED)
	public void manApl(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的渠道基本信息
		UpdateRequestBean reqBean = updateMap.get("pagyMchtMng");
		// 基本信息
		PagySubMchtInfo pagySubMchtInfo = new PagySubMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pagySubMchtInfo);
		}
		// 调用接口-通道商户请求【当申请类型为"渠道商户申请"时调用】，对应接口【直接申请】
		if (pagySubMchtInfo.getAplType().equals(PagyMchtMngConstants.APPLY_TYPE_MCHT)) {
			// 商户通道请求-实体类
			MchtChnlRequestVO mchtInfo = PagyMchtMngService.getInstance().generateMchtChnlReqVO(pagySubMchtInfo, null);
			try {
				new MchtChnlRequestAction().directApplyRequest(mchtInfo, MchtChnlRequestConstants.APPLY_WAY_PER);
			} catch (Exception e) {
				SnowExceptionUtil.throwErrorException("申请失败-原因：" + e.getMessage());
			}
		}
		// 打印日志
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道商户登记]--手工申请：渠道商户号[payMchtNo]=" + pagySubMchtInfo.getPayMchtNo() });
	}

	/**
	 * 联机申请
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@SnowDoc(desc = "联机申请")
	@Action(propagation = Propagation.REQUIRED)
	public void onlineApl(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的渠道基本信息
		UpdateRequestBean reqBean = updateMap.get("pagyMchtMng");
		// 通道信息
		UpdateRequestBean reqBeanPagy = updateMap.get("pagyMchtMngTab");
		// 基本信息
		PagySubMchtInfo pagySubMchtInfo = new PagySubMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pagySubMchtInfo);
		}
		// 通道信息
		PagySubMchtInfo pagySubMchtInfoPagy = new PagySubMchtInfo();
		while (reqBeanPagy.hasNext()) {
			DataObjectUtils.map2bean(reqBeanPagy.next(), pagySubMchtInfoPagy);
		}
		// 调用接口-通道商户请求【当申请类型为"渠道商户申请"时调用】
		if (pagySubMchtInfo.getAplType().equals(PagyMchtMngConstants.APPLY_TYPE_MCHT)) {
			// 商户通道请求-实体类
			MchtChnlRequestVO mchtInfo = PagyMchtMngService.getInstance().generateMchtChnlReqVO(pagySubMchtInfo,
					pagySubMchtInfoPagy);
			// 申请状态-未申请，对应接口【指定通道申请】
			try {
				new MchtChnlRequestAction().directApplyRequest(mchtInfo, MchtChnlRequestConstants.APPLY_WAY_CHL);
			} catch (Exception e) {
				SnowExceptionUtil.throwErrorException("申请失败-原因：" + e.getMessage());
			}
		}
		// 打印日志
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道商户登记]--联机申请：渠道商户号[payMchtNo]=" + pagySubMchtInfo.getPayMchtNo() });
	}
	
		   /**
     * 平安通道标准进件
     * 
     * @param updateMap
     * @throws SnowException
     * @throws UnsupportedEncodingException 
     */
    @SnowDoc(desc = "平安通道标准进件")
    @Action(propagation = Propagation.REQUIRED)
    public void manStandard(Map<String, UpdateRequestBean> updateMap) throws SnowException, UnsupportedEncodingException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        // 获取页面传递的渠道基本信息
           UpdateRequestBean reqBean = updateMap.get("pagyMchtMng");
           // 基本信息
           PagySubMchtInfo pagySubMchtInfo = new PagySubMchtInfo();
           while (reqBean.hasNext()) {
               DataObjectUtils.map2bean(reqBean.next(), pagySubMchtInfo);
           }
           Long startTime = System.currentTimeMillis();
           URL url = null;
           String requestMsg=null;
           /** 加载SnowLog */
           Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
           try {
               log.info("平安通道标准进件【" + url + "】-请求开始...");
               log.info("平安通道标准进件" + pagySubMchtInfo);
               url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.PAGY_MCHT_MNG_STANDARD));  
               Map<String, Object> map = new HashMap<String, Object>();
               
               map.put("mchtId", pagySubMchtInfo.getPayMchtNo());// 商户号
               
               requestMsg = new Gson().toJson(map);            
               String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
               /** 建立服务器连接 */
               HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
               
               /** 发送报文 */
               HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
               
               // -----------------------------------------STEP NO3
               // 获取响应-----------------------------------------//
               /** 获取服务端响应 */
               String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
               Map<String, String> serRetMap = new HashMap<String, String>();
               Gson gson = new Gson();
               serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
               String respCode = serRetMap.get("respCode");// 响应码 
               // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
               if (respCode.substring((respCode.length() - 4), respCode.length())
                       .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
      
                   log.info("平安通道标准进件成功" + pagySubMchtInfo);
               }else{
                   log.info("平安通道标准进件失败" + pagySubMchtInfo);
                   SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
               }

               /******************************************** 计时结束 ********************************************/
               log.info("平安通道标准进件成功【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

           } catch (Exception se) {
               SnowExceptionUtil.throwErrorException(se.getMessage());
               log.error("平安通道标准进件成功【" + url + "差错订单号：" + pagySubMchtInfo + "】，原因：" + se.getMessage());
               log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
           }
    }
	
	/**
     * 平安通道联机申请
     * 
     * @param updateMap
     * @throws SnowException
     * @throws UnsupportedEncodingException 
     */
    @SnowDoc(desc = "平安通道联机申请")
    @Action(propagation = Propagation.REQUIRED)
    public void manPA(Map<String, UpdateRequestBean> updateMap) throws SnowException, UnsupportedEncodingException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        // 获取页面传递的渠道基本信息
           UpdateRequestBean reqBean = updateMap.get("pagyMchtMng");
           // 基本信息
           PagySubMchtInfo pagySubMchtInfo = new PagySubMchtInfo();
           PagyIndirectPayCertCfg pagyIndirectPayCertCfg=new PagyIndirectPayCertCfg();
           while (reqBean.hasNext()) {
               DataObjectUtils.map2bean(reqBean.next(), pagySubMchtInfo);
           }
           Long startTime = System.currentTimeMillis();
           URL url = null;
           String requestMsg=null;
           /** 加载SnowLog */
           Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
           try {
               log.info("联机申请【" + url + "】-请求开始...");
               log.info("联机申请" + pagySubMchtInfo);
               if("02".equals(pagySubMchtInfo.getAplStat())){
                   url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.PAGY_MCHT_MNG_PA_UPDATE));  
               }else{
                   url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.PAGY_MCHT_MNG_PA));                   
               }
               Map<String, Object> map = new HashMap<String, Object>();
               map.put("encoding", IfspConstants.UTF_8_ENCODING);// 编码方式
               map.put("chlId", "C0001");// 渠道ID
               map.put("aplType", "2");// 申请类型
               map.put("pagySysId", "312");// 请求系统编号
               map.put("pagyType", "03");// 通道接入类型               
               map.put("pagyAcqNo", pagySubMchtInfo.getMainMchtNo());// 通道机构接入编号
               map.put("chlMerId", pagySubMchtInfo.getPayMchtNo());// 渠道商户编号
               map.put("chlMerName",pagySubMchtInfo.getPayMchtName());// 渠道商户名称
               
               map.put("applyMerName", pagySubMchtInfo.getMchtName());// 申请商户全名
               map.put("applyMerShortName", pagySubMchtInfo.getMchtNameAbbr());// 申请商户简称
               map.put("custservPhone",pagySubMchtInfo.getMchtSerPhone() );// 客服电话
               map.put("contact", pagySubMchtInfo.getMchtContact());// 联系人
               map.put("contactPhone", pagySubMchtInfo.getMchtContactPhone());// 联系电话
               map.put("contactEmail", pagySubMchtInfo.getMchtContactEmail());// 联系邮箱
               map.put("crtTlr", sessionUserInfo.getTlrno());// 创建柜员
               map.put("lastUpdTlrs", sessionUserInfo.getTlrno());// 最近更新柜员
               map.put("subscribeAppid", pagySubMchtInfo.getMchtPublicNo());// 订阅公众号
               map.put("subAppid", pagySubMchtInfo.getSubAppid());// 支付公众号
               map.put("jsapiPath", pagySubMchtInfo.getWxJsapiPath());//授权目录
               Map<String, Object> reqData = new HashMap<String, Object>();
               map.put("reqData", reqData);// 自定义数据域
               Map<String, Object> payment = new HashMap<String, Object>();
               reqData.put("cityid", pagySubMchtInfo.getAddressCode());// 地区码
               reqData.put("address", pagySubMchtInfo.getAddress());// 详细地址
               reqData.put("tra_id", ContextUtil.getUUID());// 机构商户主键
               reqData.put("payment",payment);// 支付方式

               Map<String, Object> WeixinBERL = new HashMap<String, Object>();
               Map<String, Object> AlipayCS = new HashMap<String, Object>();
               payment.put("WeixinBERL", WeixinBERL);// 微信
               payment.put("AlipayCS", AlipayCS);// 支付宝

               
               WeixinBERL.put("category", pagySubMchtInfo.getWeixinCategory());// 微信分类编号
               WeixinBERL.put("fee", pagySubMchtInfo.getWeixinFee());// 微信费率
               AlipayCS.put("category", pagySubMchtInfo.getAliCategory());// 支付宝分类编号
               AlipayCS.put("fee", pagySubMchtInfo.getAliFee());// 支付宝费率
               
               requestMsg = new Gson().toJson(map);            
               String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
               /** 建立服务器连接 */
               HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
               
               /** 发送报文 */
               HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
               
               // -----------------------------------------STEP NO3
               // 获取响应-----------------------------------------//
               /** 获取服务端响应 */
               String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
               Map<String, String> serRetMap = new HashMap<String, String>();
               Gson gson = new Gson();
               serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
               String respCode = serRetMap.get("respCode");// 响应码 
               // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
               if (respCode.substring((respCode.length() - 4), respCode.length())
                       .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
      
                   log.info("联机申请成功" + pagySubMchtInfo);
               }else{
                   log.info("联机申请失败" + pagySubMchtInfo);
                   SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
               }

               /******************************************** 计时结束 ********************************************/
               log.info("联机申请成功【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

           } catch (Exception se) {
               SnowExceptionUtil.throwErrorException(se.getMessage());
               log.error("联机申请成功【" + url + "差错订单号：" + pagySubMchtInfo + "】，原因：" + se.getMessage());
               log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
           }
    }
	/**
     * 通道商户号录入
     * 
     * @param updateMap
     * @throws SnowException
	 * @throws UnsupportedEncodingException 
     */
    @SnowDoc(desc = "通道商户号录入")
    @Action(propagation = Propagation.REQUIRED)
    public void mchtNo(Map<String, UpdateRequestBean> updateMap) throws SnowException, UnsupportedEncodingException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
     // 获取页面传递的渠道基本信息
        UpdateRequestBean reqBean = updateMap.get("pagyMchtMng");
        // 基本信息
        PagySubMchtInfo pagySubMchtInfo = new PagySubMchtInfo();
        PagyIndirectPayCertCfg pagyIndirectPayCertCfg=new PagyIndirectPayCertCfg();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), pagySubMchtInfo);
        }
        String mchtNo=PagyMchtMngService.getInstance().queryMchtNo(pagySubMchtInfo.getMchtNo(),"311");
        String mchtNo2=PagyMchtMngService.getInstance().queryMchtNo(pagySubMchtInfo.getMchtNo(),"313");
        if("311".equals(pagySubMchtInfo.getPagyNo())){
            if(pagySubMchtInfo.getMchtNo().equals(mchtNo)){
                PagyMchtMngService.getInstance().updPagySub(pagySubMchtInfo);
                pagyIndirectPayCertCfg.setCertifiPasswd(Base64Coder.encoded(pagySubMchtInfo.getMd5Passwd()));
                pagyIndirectPayCertCfg.setCertifiId(pagySubMchtInfo.getMchtNo());// 证书编号
                PagyMchtMngService.getInstance().pagyIndirectPayCertCfgupd(pagyIndirectPayCertCfg); 
            }else{
                PagyMchtMngService.getInstance().updPagySub(pagySubMchtInfo);  
                /** 获取当前时间 */
                String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
                String currentDate = currentTime.substring(0, 8);// 当前日期，8位
                pagyIndirectPayCertCfg.setCertifiId(pagySubMchtInfo.getMchtNo());// 证书编号
                pagyIndirectPayCertCfg.setCertifiBl(pagySubMchtInfo.getMchtNo());// 证书所属
                pagyIndirectPayCertCfg.setCertifiUseType(pagySubMchtInfo.getPagyNo());// 证书使用类型
                pagyIndirectPayCertCfg.setCertifiStatus(ChnlAcsInfoConstants.CERT_STAT_ON);// 证书状态
                pagyIndirectPayCertCfg.setEncryptType(ChnlAcsInfoConstants.ENC_TYPE_MD5);// 加密类型
                pagyIndirectPayCertCfg.setEncryptWayType(ChnlAcsInfoConstants.ENC_WAY_TYPE_PASSWORD);// 加密方式
                pagyIndirectPayCertCfg.setCertifiType(ChnlAcsInfoConstants.CERT_TYPE_MD5);// 证书类型
                pagyIndirectPayCertCfg.setCertifiPasswd(Base64Coder.encoded(pagySubMchtInfo.getMd5Passwd()));
                pagyIndirectPayCertCfg.setCertifiDate(currentDate);
                PagyMchtMngService.getInstance().pagyIndirectPayCertCfgAdd(pagyIndirectPayCertCfg);            
            }
            Long startTime = System.currentTimeMillis();
            URL url = null;
            String requestMsg=null;
            /** 加载SnowLog */
            Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
            try {
                log.info("缓存同步【" + url + "】-请求开始...");
                log.info("缓存同步：" + pagySubMchtInfo);
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.CACHE_SYNCHRONIZATION));
                Map<String, Object> noticeRequest = new HashMap<String, Object>();
                noticeRequest.put("paySysId", "311");// 通道系统编号
                noticeRequest.put("cacheType", "00");// 缓存类型
                /* 调用调度中心502.receiveMessage */
                Map<String, Object> compExpressMap = new HashMap<String, Object>();
                compExpressMap.put("maxTimes", "1");// 调度中心通知系统最大次数
                compExpressMap.put("interval", "10");// 调度中心通知系统间隔时间

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("toIds", "311cache");// 调度中心通知系统的系统id
                map.put("context", IfspFastJsonUtil.mapTOjson(noticeRequest));// 通知内容
                map.put("compExpress", IfspFastJsonUtil.mapTOjson(compExpressMap));// 补偿方式
                requestMsg = new Gson().toJson(map);            
                String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
                /** 建立服务器连接 */
                HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
                
                /** 发送报文 */
                HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
                
                // -----------------------------------------STEP NO3
                // 获取响应-----------------------------------------//
                /** 获取服务端响应 */
                String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
                Map<String, String> serRetMap = new HashMap<String, String>();
                Gson gson = new Gson();
                serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
                String respCode = serRetMap.get("respCode");// 响应码 
                // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
                if (respCode.substring((respCode.length() - 4), respCode.length())
                        .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
       
                    log.info("缓存同步成功" + pagySubMchtInfo);
                }else{
                    log.info("缓存同步成功" + pagySubMchtInfo);
                    SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
                }

                /******************************************** 计时结束 ********************************************/
                log.info("缓存同步成功【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
            } catch (Exception se) {
                SnowExceptionUtil.throwErrorException(se.getMessage());
                log.error("缓存同步成功【" + url + "差错订单号：" + pagySubMchtInfo + "】，原因：" + se.getMessage());
                log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
            }
        }else if("313".equals(pagySubMchtInfo.getPagyNo())){
            if(pagySubMchtInfo.getMchtNo().equals(mchtNo2)){
                PagyMchtMngService.getInstance().updPagySub(pagySubMchtInfo);
                pagyIndirectPayCertCfg.setCertifiPasswd(Base64Coder.encoded(pagySubMchtInfo.getMd5Passwd()));
                pagyIndirectPayCertCfg.setCertifiId(pagySubMchtInfo.getMchtNo());// 证书编号
                PagyMchtMngService.getInstance().pagyIndirectPayCertCfgupd(pagyIndirectPayCertCfg); 
            }else{
                PagyMchtMngService.getInstance().updPagySub(pagySubMchtInfo);  
                /** 获取当前时间 */
                String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
                String currentDate = currentTime.substring(0, 8);// 当前日期，8位
                pagyIndirectPayCertCfg.setCertifiId(pagySubMchtInfo.getMchtNo());// 证书编号
                pagyIndirectPayCertCfg.setCertifiBl(pagySubMchtInfo.getMchtNo());// 证书所属
                pagyIndirectPayCertCfg.setCertifiUseType(pagySubMchtInfo.getPagyNo());// 证书使用类型
                pagyIndirectPayCertCfg.setCertifiStatus(ChnlAcsInfoConstants.CERT_STAT_ON);// 证书状态
                pagyIndirectPayCertCfg.setEncryptType(ChnlAcsInfoConstants.ENC_TYPE_MD5);// 加密类型
                pagyIndirectPayCertCfg.setEncryptWayType(ChnlAcsInfoConstants.ENC_WAY_TYPE_PASSWORD);// 加密方式
                pagyIndirectPayCertCfg.setCertifiType(ChnlAcsInfoConstants.CERT_TYPE_MD5);// 证书类型
                pagyIndirectPayCertCfg.setCertifiPasswd(Base64Coder.encoded(pagySubMchtInfo.getMd5Passwd()));
                pagyIndirectPayCertCfg.setCertifiDate(currentDate);
                PagyMchtMngService.getInstance().pagyIndirectPayCertCfgAdd(pagyIndirectPayCertCfg);   
            }
            Long startTime = System.currentTimeMillis();
            URL url = null;
            String requestMsg=null;
            /** 加载SnowLog */
            Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
            try {
                log.info("缓存同步【" + url + "】-请求开始...");
                log.info("缓存同步：" + pagySubMchtInfo);
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.CACHE_SYNCHRONIZATION));
                Map<String, Object> noticeRequest = new HashMap<String, Object>();
                noticeRequest.put("paySysId", "313");// 通道系统编号
                noticeRequest.put("cacheType", "00");// 缓存类型
                /* 调用调度中心502.receiveMessage */
                Map<String, Object> compExpressMap = new HashMap<String, Object>();
                compExpressMap.put("maxTimes", "1");// 调度中心通知系统最大次数
                compExpressMap.put("interval", "10");// 调度中心通知系统间隔时间

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("toIds", "313cache");// 调度中心通知系统的系统id
                map.put("context", IfspFastJsonUtil.mapTOjson(noticeRequest));// 通知内容
                map.put("compExpress", IfspFastJsonUtil.mapTOjson(compExpressMap));// 补偿方式
                requestMsg = new Gson().toJson(map);            
                String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
                /** 建立服务器连接 */
                HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
                
                /** 发送报文 */
                HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
                
                // -----------------------------------------STEP NO3
                // 获取响应-----------------------------------------//
                /** 获取服务端响应 */
                String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
                Map<String, String> serRetMap = new HashMap<String, String>();
                Gson gson = new Gson();
                serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
                String respCode = serRetMap.get("respCode");// 响应码 
                // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
                if (respCode.substring((respCode.length() - 4), respCode.length())
                        .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
       
                    log.info("缓存同步成功" + pagySubMchtInfo);
                }else{
                    log.info("缓存同步成功" + pagySubMchtInfo);
                    SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
                }

                /******************************************** 计时结束 ********************************************/
                log.info("缓存同步成功【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
            } catch (Exception se) {
                SnowExceptionUtil.throwErrorException(se.getMessage());
                log.error("缓存同步成功【" + url + "差错订单号：" + pagySubMchtInfo + "】，原因：" + se.getMessage());
                log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
            }
        }else{
            //支付宝直连录入通道子商户号
            PagyMchtMngService.getInstance().updPagySub(pagySubMchtInfo); 
        }
        

        /** step no 5 : 记录日志 */
        sessionUserInfo.addBizLog("update.log",
                new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                         " 修改通道商户号信息：通道商户号编号="+pagySubMchtInfo.getChlId()});
    }
}
