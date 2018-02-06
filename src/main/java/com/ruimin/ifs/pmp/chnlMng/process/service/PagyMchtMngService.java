/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyMchtMngService.java
 * Author:   zqy
 * Date:     2016年8月01日 上午10:39:09
 * Description: 通道商户登记     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年8月08日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.MchtChnlRequestVO;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyIndirectPayCertCfg;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagySubMchtInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

/**
 * 名称：通道商户登记 功能：通道商户登记 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道商户登记操作Service")
@Service
public class PagyMchtMngService extends SnowService {
	public static PagyMchtMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyMchtMngService.class);
	}

	/**
	 * 分页查询通道商户登记信息
	 * @param string 
	 */
	public PageResult queryList(String chlId, String aplStat, String payMchtNo, String mchtName, String pagyNo,
			String mchtNo,String flag,String currentBrCode, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.queryList",
				RqlParam.map().set("chlId", StringUtils.isEmpty(chlId) ? "" : "%" + chlId + "%")
						.set("aplStat", StringUtils.isEmpty(aplStat) ? "" : "%" + aplStat + "%" )
						.set("payMchtNo", StringUtils.isEmpty(payMchtNo) ? ""
								: "%" + payMchtNo + "%")
						.set("mchtName", StringUtils.isEmpty(mchtName) ? ""
								: "%" + mchtName + "%")
						.set("pagyNo", StringUtils.isEmpty(pagyNo) ? "" : "%" + pagyNo + "%")
						.set("mchtNo", StringUtils.isEmpty(mchtNo) ? "" : "%" + mchtNo + "%")
						.set("flag", StringUtils.isEmpty(flag)?"":flag)
						.set("currentBrCode",currentBrCode),
				page);
	}

	   /**
     * 查询地区码
     */
    public PageResult selOrg(String qCtName, String pagyNo,String qCtCode, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.selOrg",
                RqlParam.map().set("qCtCode", StringUtils.isEmpty(qCtCode) ? "" : qCtCode )
                        .set("pagyNo", StringUtils.isEmpty(pagyNo) ? "" : pagyNo )
                        .set("qCtName", StringUtils.isEmpty(qCtName) ? "" :  qCtName ),
                page);
    }
    /**
     * 查询微信支付方式
     */
    public PageResult weixinBERL(String qwxberlId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.weixinBERL",
                RqlParam.map().set("qwxberlId", StringUtils.isEmpty(qwxberlId) ? "" : qwxberlId ),
                page);
    }
	public ChannelInfo queryChlNameById(String key) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(ChannelInfo.class, key);
	}

	public PagyInfo queryPagyNameByNo(String key) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(PagyInfo.class, key);
	}

	/**
	 * 组装商户通道请求实体类
	 * 
	 * @param pagySubMchtInfo
	 *            基本信息
	 * @param pagySubMchtInfoPagy
	 *            通道信息
	 * @return
	 * @throws SnowException
	 */
	public MchtChnlRequestVO generateMchtChnlReqVO(PagySubMchtInfo pagySubMchtInfo, PagySubMchtInfo pagySubMchtInfoPagy)
			throws SnowException {
		MchtChnlRequestVO mchtInfo = new MchtChnlRequestVO();// 商户通道请求-实体类
		/************ 组装过程 ************/
		/** 基本信息 */
		mchtInfo.setChlNo(pagySubMchtInfo.getChlId());// 渠道编号
		mchtInfo.setChlMerName(pagySubMchtInfo.getPayMchtName());// 渠道商户名称
		mchtInfo.setChlMerId(pagySubMchtInfo.getPayMchtNo());// 渠道商户号
		String pagyNo = pagySubMchtInfo.getPagyNo();// 获取通道号
		mchtInfo.setPagyNo(pagyNo);// 通道号
		mchtInfo.setMainMerId(StringUtils.isBlank(pagySubMchtInfo.getMainMchtNo())
				? queryMainMchtNo(pagySubMchtInfo.getChlId(), pagySubMchtInfo.getPayMchtNo(), pagyNo)
				: pagySubMchtInfo.getMainMchtNo());// 通道主商户编号
		if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_UNION_PAY)
				|| pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_BH_PAY)) {
			/** 银联全渠道 */
			mchtInfo.setMcc(pagySubMchtInfoPagy.getMchtMccSubCode());// MCC码
			mchtInfo.setAddressCode(pagySubMchtInfoPagy.getMchtAreaNo());// 地区码
			mchtInfo.setApplyMerName(pagySubMchtInfoPagy.getMchtName());// 申请商户全名
			mchtInfo.setApplyMerShortName(pagySubMchtInfoPagy.getMchtNameAbbr());// 申请商户简称
			//2017-03-28新增
			//mchtInfo.setWxRemark(pagySubMchtInfoPagy.getMchRamak());// 银联全渠道/本行通道 将 特殊计费类型和特殊计费档次存入wxRemark中传给通道核
			
		} else if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_WECHAT)
				) {
			/** 微信、支付宝共用字段 */
			mchtInfo.setApplyMerName(pagySubMchtInfoPagy.getMchtName());// 申请商户全名
			mchtInfo.setApplyMerShortName(pagySubMchtInfoPagy.getMchtNameAbbr());// 申请商户简称
			mchtInfo.setCustservPhone(pagySubMchtInfoPagy.getMchtSerPhone());// 客服电话
			mchtInfo.setContact(pagySubMchtInfoPagy.getMchtContact());// 联系人
			mchtInfo.setContactPhone(pagySubMchtInfoPagy.getMchtContactPhone());// 联系电话
			mchtInfo.setContactEmail(pagySubMchtInfoPagy.getMchtContactEmail());// 联系邮箱
			mchtInfo.setLevelOneCode(pagySubMchtInfoPagy.getMchtMccCode());// 一级行业编码
			mchtInfo.setLevelTwoCode(pagySubMchtInfoPagy.getMchtMccSubCode());// 二级行业编码
			mchtInfo.setLevelThreeCode("");// 三级行业编码-预留
			if (pagyNo.equals(MchtChnlRequestConstants.APPLY_PAGY_WECHAT)) {
				/** 微信特有字段 */
				mchtInfo.setWxRemark(pagySubMchtInfoPagy.getMchRamak());// 微信商户备注
			}
		}
		return mchtInfo;
	}

	/**
	 * 查询【主商户编号】
	 * 
	 * @param chlId
	 *            渠道ID
	 * @param payMchtNo
	 *            支付渠道商户号
	 * @param pagyNo
	 *            通道编号
	 * @return
	 */
	private String queryMainMchtNo(String chlId, String payMchtNo, String pagyNo) {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.queryMainMchtNo",
				RqlParam.map().set("chlId", chlId).set("payMchtNo", payMchtNo).set("pagyNo", pagyNo));
	}
	
	public String queryMchtNo(String mchtNo,String pagyNo) {
	        DBDao dao = DBDaos.newInstance();	        
	        return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.queryMchtNo",
	                RqlParam.map().set("mchtNo", mchtNo).set("pagyNo", pagyNo));
	}
	   public String queryPayCert(String mchtNo) {
           DBDao dao = DBDaos.newInstance();           
           return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.queryPayCert",
                   RqlParam.map().set("mchtNo", mchtNo));
   }
    public void updPagySub(PagySubMchtInfo pagySubMchtInfo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String mchtNo=pagySubMchtInfo.getMchtNo();
        String chlId=pagySubMchtInfo.getChlId();
        String payMchtNo=pagySubMchtInfo.getPayMchtNo();
        String mainMchtNo=pagySubMchtInfo.getMainMchtNo();
        String pagyNo=pagySubMchtInfo.getPagyNo();  
        String settlementMark=pagySubMchtInfo.getSettlementMark();
        String aplDate=BaseUtil.getCurrentDate();
        dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.updPagySub", RqlParam.map().set("mchtNo", mchtNo).set("chlId", chlId).set("payMchtNo", payMchtNo).set("mainMchtNo", mainMchtNo).set("pagyNo", pagyNo).set("aplDate", aplDate).set("settlementMark", settlementMark));
    }
    public void updPagySubSettlementMark(PagySubMchtInfo pagySubMchtInfo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String chlId=pagySubMchtInfo.getChlId();
        String payMchtNo=pagySubMchtInfo.getPayMchtNo();
        String mainMchtNo=pagySubMchtInfo.getMainMchtNo();
        String pagyNo=pagySubMchtInfo.getPagyNo();  
        String settlementMark=pagySubMchtInfo.getSettlementMark();
        String aplDate=BaseUtil.getCurrentDate();
        dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.updPagySubSettlementMark", RqlParam.map().set("chlId", chlId).set("payMchtNo", payMchtNo).set("mainMchtNo", mainMchtNo).set("pagyNo", pagyNo).set("aplDate", aplDate).set("settlementMark", settlementMark));
    }
    public void pagyIndirectPayCertCfgAdd(PagyIndirectPayCertCfg pagyIndirectPayCertCfg) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.insert(pagyIndirectPayCertCfg);
    }
    public void pagyIndirectPayCertCfgupd(PagyIndirectPayCertCfg pagyIndirectPayCertCfg) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String certifiId=pagyIndirectPayCertCfg.getCertifiId();
        String certifiPasswd=pagyIndirectPayCertCfg.getCertifiPasswd();
        dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.pagyMchtMng.pagyIndirectPayCertCfgupd", RqlParam.map().set("certifiId", certifiId).set("certifiPasswd", certifiPasswd));

    }
}
