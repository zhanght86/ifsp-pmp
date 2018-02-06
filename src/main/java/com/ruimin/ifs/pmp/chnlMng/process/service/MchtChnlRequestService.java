package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

@Service
@SnowDoc(desc = "通道商户请求接口")
public class MchtChnlRequestService extends SnowService {
	/** 加载SnowLog */
	Logger log = SnowLog.getLogger(MchtChnlRequestService.class);

	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtChnlRequestService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtChnlRequestService.class);
	}

	/**
	 * 校验【申请方式】是否缺失
	 * 
	 * @param applyWay
	 *            申请方式
	 * @throws SnowException
	 */
	public void vaildApplyWay(String applyWay) throws SnowException {
		if (StringUtils.isBlank(applyWay)) {
			SnowExceptionUtil.throwErrorException("【申请方式】不能为空");
		} else {
			if (!(applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_PER)
					|| applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_CHL))) {
				SnowExceptionUtil.throwErrorException("【申请方式】无法识别");
			}
		}
	}

	/**
	 * 校验【必要请求参数】是否缺失
	 * 
	 * @param mchtInfo
	 *            商户通道请求-实体类
	 * @param applyWay
	 *            申请方式
	 * @throws SnowException
	 */
	public void vaildReqParam(MchtChnlRequestVO mchtInfo, String applyWay) throws SnowException {
		Long startTime = System.currentTimeMillis();
		SnowLog.getServerLog().info("校验必要请求参数是否缺失【开始】...");
		if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_PER)) {
			if (StringUtils.isBlank(mchtInfo.getChlMerId())) {
				SnowExceptionUtil.throwErrorException("缺失必要参数：【渠道商户编号】");
			}
			if (StringUtils.isBlank(mchtInfo.getChlMerName())) {
				SnowExceptionUtil.throwErrorException("缺失必要参数：【渠道商户名称】");
			}
		} else if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_CHL)) {
			if (StringUtils.isBlank(mchtInfo.getPagyNo())) {
				SnowExceptionUtil.throwErrorException("缺失必要参数：【申请通道编号】");
			}
			if (StringUtils.isBlank(mchtInfo.getMainMerId())) {
				SnowExceptionUtil.throwErrorException("缺失必要参数：【通道主商户编号】");
			}
			if (StringUtils.isBlank(mchtInfo.getChlMerId())) {
				SnowExceptionUtil.throwErrorException("缺失必要参数：【渠道子商户编号】");
			}
		}

		long endTime = System.currentTimeMillis();
		SnowLog.getServerLog().info("校验必要请求参数是否缺失【结束】，耗时：" + (endTime - startTime) + " ms ");
	}

	/**
	 * 组建报文
	 * 
	 * @param mchtInfo
	 *            商户通道请求-实体类
	 * @param applyWay
	 *            申请方式
	 * @return
	 */
	public String buildApplyMsg(MchtChnlRequestVO mchtInfo, String applyWay) throws SnowException {
		Long startTime = System.currentTimeMillis();
		SnowLog.getServerLog().info("组建报文-" + applyWay + "【开始】...");

		/** 初始化报文 */
		Map<String, String> resultMap = new HashMap<String, String>();

		/** 组建报文 */
		// ---------------------公共字段------------------------//
		resultMap.put("encoding", SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_ENCODING));// 编码方式
		resultMap.put("chlNo", StringUtils.isBlank(mchtInfo.getChlNo())
				? SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_CHNLNO) : mchtInfo.getChlNo());// 渠道编号

		// ---------------------预申请字段------------------------//
		if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_PER)) {
			resultMap.put("applyType", MchtChnlRequestConstants.APPLY_TYPE_CHL_MCHT);// 申请类型-渠道商户申请
			resultMap.put("chlMerName", mchtInfo.getChlMerName());// 渠道商户名称
			resultMap.put("chlMerId", mchtInfo.getChlMerId());// 渠道商户编号
		}

		// ---------------------指定通道申请字段------------------------//
		if (applyWay.equals(MchtChnlRequestConstants.APPLY_WAY_CHL)) {
			resultMap.put("pagyNo", mchtInfo.getPagyNo());// 申请通道编号
			resultMap.put("mainMerId", mchtInfo.getMainMerId());// 通道主商户编号
			resultMap.put("chlMerId", mchtInfo.getChlMerId());// 渠道子商户编号
			String pagyNo = mchtInfo.getPagyNo();// 通道编号
			if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_UNION_PAY)
					|| pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_BH_PAY)) {
				log.info("通道类型【银联全渠道】");
				resultMap.put("mcc", mchtInfo.getMcc());// MCC码
				resultMap.put("addressCode", mchtInfo.getAddressCode());// 地区码
				resultMap.put("applyMerName", mchtInfo.getApplyMerName());// 申请商户全名
				resultMap.put("applyMerShortName", mchtInfo.getApplyMerShortName());// 申请商户简称
				//2017-03-28新增
		        //resultMap.put("wxRemark", mchtInfo.getWxRemark());// 银联全渠道/本行通道 将 特殊计费类型和特殊计费档次存入wxRemark中传给通道核心
				
			} else if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_WECHAT)) {
				log.info("通道类型【微信通道】");
				/** 获取微信行业编号 */
				mchtInfo.setUserCode(MchtChnlRequestConstants.USER_CODE_WECHAT);// 类目使用方-微信
				String wxBusinessCode = queryAplCategoryCode(mchtInfo);// 微信行业编号
				if (StringUtils.isBlank(wxBusinessCode)) {
					SnowExceptionUtil.throwErrorException("无法获取【微信行业编号】，请求中止！");
				}
				resultMap.put("applyMerName", mchtInfo.getApplyMerName());// 申请商户全名
				resultMap.put("applyMerShortName", mchtInfo.getApplyMerShortName());// 申请商户简称
				resultMap.put("custservPhone", mchtInfo.getCustservPhone());// 客服电话
				resultMap.put("contact", mchtInfo.getContact());// 联系人
				resultMap.put("contactPhone", mchtInfo.getContactPhone());// 联系电话
				resultMap.put("contactEmail", mchtInfo.getContactEmail());// 联系邮箱
				resultMap.put("wxBusinessCode", wxBusinessCode);// 微信行业编号
				resultMap.put("wxRemark", mchtInfo.getWxRemark());// 微信商户备注
			} else if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_ALI_PAY)) {
				log.info("通道类型【支付宝通道】");
				/** 获取支付宝行业编号 */
				mchtInfo.setUserCode(MchtChnlRequestConstants.USER_CODE_ALI_PAY);// 类目使用方-支付宝
				String zfbBusinessCode = queryAplCategoryCode(mchtInfo);// 支付宝行业编号
				if (StringUtils.isBlank(zfbBusinessCode)) {
					SnowExceptionUtil.throwErrorException("无法获取【支付宝行业编号】，请求中止！");
				}
				resultMap.put("applyMerName", mchtInfo.getApplyMerName());// 申请商户全名
				resultMap.put("applyMerShortName", mchtInfo.getApplyMerShortName());// 申请商户简称
				resultMap.put("custservPhone", mchtInfo.getCustservPhone());// 客服电话
				resultMap.put("contact", mchtInfo.getContact());// 联系人
				resultMap.put("contactPhone", mchtInfo.getContactPhone());// 联系电话
				resultMap.put("contactEmail", mchtInfo.getContactEmail());// 联系邮箱
				resultMap.put("zfbBusinessCode", zfbBusinessCode);// 支付宝行业编号
			}
		} else {
			log.info("通道类型【通用】");
		}
		String retMsg = new Gson().toJson(resultMap);// 结果报文

		long endTime = System.currentTimeMillis();
		SnowLog.getServerLog().info("组建报文-" + applyWay + "【完成】：" + retMsg + "，耗时：" + (endTime - startTime) + " ms ");
		return retMsg;
	}

	/**
	 * 查询【申请用类目编号】
	 * 
	 * @param mchtInfo
	 *            商户通道请求-实体类
	 * @return
	 */
	public String queryAplCategoryCode(MchtChnlRequestVO mchtInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.mchtChnlRequest.queryAplCategoryCode",
				RqlParam.map().set("userCode", mchtInfo.getUserCode())
						.set("levelOneCode",
								StringUtils.isNotBlank(mchtInfo.getLevelOneCode()) ? mchtInfo.getLevelOneCode()
										: MchtChnlRequestConstants.LEVEL_ONE_CODE_DEFAULT)
						.set("levelTwoCode",
								StringUtils.isNotBlank(mchtInfo.getLevelTwoCode()) ? mchtInfo.getLevelTwoCode()
										: MchtChnlRequestConstants.LEVEL_TWO_CODE_DEFAULT)
						.set("levelThreeCode", StringUtils.isNotBlank(mchtInfo.getLevelThreeCode())
								? mchtInfo.getLevelThreeCode() : MchtChnlRequestConstants.LEVEL_THREE_CODE_DEFAULT));
	}

	/**
	 * 查询【地区码】
	 * 
	 * @param mchtId
	 *            商户编号
	 * @return
	 */
	public String queryAreaCode(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.mchtChnlRequest.queryAreaNo", mchtId);
	}
}
