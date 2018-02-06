/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.comp 
 * FileName: PagySubMchtInfo.java
 * Author:   zqy
 * Date:     2016年8月01日 上午10:39:09
 * Description: 通道商户登记    
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年8月01日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：支付通道子商户信息信息表 功能：支付通道子商户信息信息表 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月01日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@Table("PAGY_SUB_MCHT_INFO")
public class PagySubMchtInfo {
	@Id
	// 渠道ID
	private String chlId;
	@Id
	// 申请类型
	private String aplType;
	@Id
	// 通道子商户号
	private String mchtNo;
	@Id
	// 主商户编号
	private String mainMchtNo;
	@Id
	// 支付渠道商户号
	private String payMchtNo;
	// 支付渠道商户名
	private String payMchtName;
	// 通道编号
	private String pagyNo;
	// 商户公众号
	private String mchtPublicNo;
	// 商户名称
	private String mchtName;
	// 商户简称
	private String mchtNameAbbr;
	// 商户客服电话
	private String mchtSerPhone;
	// 商户联系人
	private String mchtContact;
	// 商户联系人电话
	private String mchtContactPhone;
	// 商户联系人邮箱
	private String mchtContactEmail;
	// 商户行业编号
	private String mchtMccCode;
	// 商户行业名称
	private String mchtMccName;
	// 商户行业子编号
	private String mchtMccSubCode;
	// 商户行业子名称
	private String mchtMccSubName;
	// 商户备注
	private String mchRamak;
	// 申请用行业编码
	private String aplCategoryCode;
	// 申请日期
	private String aplDate;
	// 申请状态
	private String aplStat;
	// 失败原因
	private String aplFailedRes;
	// 创建柜员
	private String crtTlr;
	// 创建日期时间
	private String crtDateTime;
	// 最近更新柜员
	private String lastUpdTlr;
	// 最近更新日期时间
	private String lastUpdDateTime;

	private String mchtAreaNo;// 地区码
	private String mchtArea;// 所属地区
	//证书信息
	private String encryptType;
	private String md5Passwd;
	private String mainMchtAcsType;
	
	//平安进件增加参数
    private String weixinCategory;
    private String aliCategory;
    private String weixinFee;
    private String aliFee;
    private String address;
    private String mchtAreaSelPA;
    //支付公众号
    private String subAppid;
    //结算标示
    private String settlementMark;
    //订阅公众号
    private String subscribeAppid;
    
    private String addressCode;
    
    private String openId;
    
    private String openKey;
    
	private String wxJsapiPath;
    
	private String pagyRespMsg;
	
	private String tpamCttsStatusWx;
	
    private String tpamCttsStatusApi;
    
    private String tpamMchtStatus;

    private String tpamStoreStatus;

    private String tpamCttsStatus;
    
    
    
	public String getTpamCttsStatus() {
        return tpamCttsStatus;
    }

    public void setTpamCttsStatus(String tpamCttsStatus) {
        this.tpamCttsStatus = tpamCttsStatus;
    }

    public String getTpamCttsStatusWx() {
        return tpamCttsStatusWx;
    }

    public void setTpamCttsStatusWx(String tpamCttsStatusWx) {
        this.tpamCttsStatusWx = tpamCttsStatusWx;
    }

    public String getTpamCttsStatusApi() {
        return tpamCttsStatusApi;
    }

    public void setTpamCttsStatusApi(String tpamCttsStatusApi) {
        this.tpamCttsStatusApi = tpamCttsStatusApi;
    }

    public String getTpamMchtStatus() {
        return tpamMchtStatus;
    }

    public void setTpamMchtStatus(String tpamMchtStatus) {
        this.tpamMchtStatus = tpamMchtStatus;
    }

    public String getTpamStoreStatus() {
        return tpamStoreStatus;
    }

    public void setTpamStoreStatus(String tpamStoreStatus) {
        this.tpamStoreStatus = tpamStoreStatus;
    }

    public String getPagyRespMsg() {
        return pagyRespMsg;
    }

    public void setPagyRespMsg(String pagyRespMsg) {
        this.pagyRespMsg = pagyRespMsg;
    }

    public String getWxJsapiPath() {
        return wxJsapiPath;
    }

    public void setWxJsapiPath(String wxJsapiPath) {
        this.wxJsapiPath = wxJsapiPath;
    }
    
    
	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenKey() {
        return openKey;
    }

    public void setOpenKey(String openKey) {
        this.openKey = openKey;
    }


    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getSubscribeAppid() {
        return subscribeAppid;
    }

    public void setSubscribeAppid(String subscribeAppid) {
        this.subscribeAppid = subscribeAppid;
    }

    public String getSettlementMark() {
        return settlementMark;
    }

    public void setSettlementMark(String settlementMark) {
        this.settlementMark = settlementMark;
    }

    public String getMchtAreaSelPA() {
        return mchtAreaSelPA;
    }

    public void setMchtAreaSelPA(String mchtAreaSelPA) {
        this.mchtAreaSelPA = mchtAreaSelPA;
    }



    public String getWeixinCategory() {
        return weixinCategory;
    }

    public void setWeixinCategory(String weixinCategory) {
        this.weixinCategory = weixinCategory;
    }



    public String getAliCategory() {
        return aliCategory;
    }

    public void setAliCategory(String aliCategory) {
        this.aliCategory = aliCategory;
    }

    public String getWeixinFee() {
        return weixinFee;
    }

    public void setWeixinFee(String weixinFee) {
        this.weixinFee = weixinFee;
    }



    public String getAliFee() {
        return aliFee;
    }

    public void setAliFee(String aliFee) {
        this.aliFee = aliFee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the encryptType
     */
    public String getEncryptType() {
        return encryptType;
    }

    /**
     * @param encryptType the encryptType to set
     */
    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    /**
     * @return the md5Passwd
     */
    public String getMd5Passwd() {
        return md5Passwd;
    }

    /**
     * @param md5Passwd the md5Passwd to set
     */
    public void setMd5Passwd(String md5Passwd) {
        this.md5Passwd = md5Passwd;
    }

    /**
     * @return the mainMchtAcsType
     */
    public String getMainMchtAcsType() {
        return mainMchtAcsType;
    }

    /**
     * @param mainMchtAcsType the mainMchtAcsType to set
     */
    public void setMainMchtAcsType(String mainMchtAcsType) {
        this.mainMchtAcsType = mainMchtAcsType;
    }

    public String getMchtAreaNo() {
		return mchtAreaNo;
	}

	public void setMchtAreaNo(String mchtAreaNo) {
		this.mchtAreaNo = mchtAreaNo;
	}

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getMchtNo() {
		return mchtNo;
	}

	public void setMchtNo(String mchtNo) {
		this.mchtNo = mchtNo;
	}

	public String getMainMchtNo() {
		return mainMchtNo;
	}

	public void setMainMchtNo(String mainMchtNo) {
		this.mainMchtNo = mainMchtNo;
	}

	public String getPayMchtNo() {
		return payMchtNo;
	}

	public void setPayMchtNo(String payMchtNo) {
		this.payMchtNo = payMchtNo;
	}

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getMchtPublicNo() {
		return mchtPublicNo;
	}

	public void setMchtPublicNo(String mchtPublicNo) {
		this.mchtPublicNo = mchtPublicNo;
	}

	public String getMchtName() {
		return mchtName;
	}

	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	public String getMchtNameAbbr() {
		return mchtNameAbbr;
	}

	public void setMchtNameAbbr(String mchtNameAbbr) {
		this.mchtNameAbbr = mchtNameAbbr;
	}

	public String getMchtSerPhone() {
		return mchtSerPhone;
	}

	public void setMchtSerPhone(String mchtSerPhone) {
		this.mchtSerPhone = mchtSerPhone;
	}

	public String getMchtContact() {
		return mchtContact;
	}

	public void setMchtContact(String mchtContact) {
		this.mchtContact = mchtContact;
	}

	public String getMchtContactPhone() {
		return mchtContactPhone;
	}

	public void setMchtContactPhone(String mchtContactPhone) {
		this.mchtContactPhone = mchtContactPhone;
	}

	public String getMchtContactEmail() {
		return mchtContactEmail;
	}

	public void setMchtContactEmail(String mchtContactEmail) {
		this.mchtContactEmail = mchtContactEmail;
	}

	public String getMchtMccCode() {
		return mchtMccCode;
	}

	public void setMchtMccCode(String mchtMccCode) {
		this.mchtMccCode = mchtMccCode;
	}

	public String getMchtMccName() {
		return mchtMccName;
	}

	public void setMchtMccName(String mchtMccName) {
		this.mchtMccName = mchtMccName;
	}

	public String getMchtMccSubCode() {
		return mchtMccSubCode;
	}

	public void setMchtMccSubCode(String mchtMccSubCode) {
		this.mchtMccSubCode = mchtMccSubCode;
	}

	public String getMchtMccSubName() {
		return mchtMccSubName;
	}

	public void setMchtMccSubName(String mchtMccSubName) {
		this.mchtMccSubName = mchtMccSubName;
	}

	public String getMchRamak() {
		return mchRamak;
	}

	public void setMchRamak(String mchRamak) {
		this.mchRamak = mchRamak;
	}

	public String getAplCategoryCode() {
		return aplCategoryCode;
	}

	public void setAplCategoryCode(String aplCategoryCode) {
		this.aplCategoryCode = aplCategoryCode;
	}

	public String getAplDate() {
		return aplDate;
	}

	public void setAplDate(String aplDate) {
		this.aplDate = aplDate;
	}

	public String getAplStat() {
		return aplStat;
	}

	public void setAplStat(String aplStat) {
		this.aplStat = aplStat;
	}

	public String getCrtTlr() {
		return crtTlr;
	}

	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}

	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	public String getAplType() {
		return aplType;
	}

	public void setAplType(String aplType) {
		this.aplType = aplType;
	}

	public String getPayMchtName() {
		return payMchtName;
	}

	public void setPayMchtName(String payMchtName) {
		this.payMchtName = payMchtName;
	}

	public String getAplFailedRes() {
		return aplFailedRes;
	}

	public void setAplFailedRes(String aplFailedRes) {
		this.aplFailedRes = aplFailedRes;
	}

	public String getMchtArea() {
		return mchtArea;
	}

	public void setMchtArea(String mchtArea) {
		this.mchtArea = mchtArea;
	}

}
