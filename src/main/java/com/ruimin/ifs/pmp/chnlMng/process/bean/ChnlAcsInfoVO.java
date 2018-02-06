package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyMainMchtInfo;

/**
 * 
 * 通道接入信息附加字段 继承【支付通道主商户信息表-实体类】
 * 
 * @author zhangjc
 *
 */
public class ChnlAcsInfoVO extends PagyMainMchtInfo {
	private String pagyNo; // 通道编号
	private String pagyName; // 通道名称
	private String pagyStat; // 通道状态
	private String md5Date;// MD5生效日期
	private String md5EndDate;// MD5失效日期
	private String md5Passwd;// 密钥值
	private String md5EncryptWayType;// MD5加密方式
	private String needSign;// 需要加密
	/** 变量 certifiId . */
	private String certifiId;
	/** 变量 certifiPasswd . */
	private String certifiPasswd;
	/** 变量 certifiPath . */
	private String certifiPath;
	/** 变量 certifiType . */
	private String certifiType;
	/** 变量 certifiBl . */
	private String certifiBl;
	/** 变量 certifiDate . */
	private String certifiDate;
	/** 变量 certifiEndDate . */
	private String certifiEndDate;
	/** 变量 certifiUseType . */
	private String certifiUseType;
	/** 变量 encryptWayType . */
	private String encryptWayType;
	/** 变量 encryptType . */
	private String encryptType;
	/** 变量 certifiStatus . */
	private String certifiStatus;
	/** 变量 recCrtTs . */
	private String recCrtTs;
	/** 变量 recUpdTs . */
	private String recUpdTs;
	/** 变量 recCrtOpr . */
	private String recCrtOpr;
	/** 变量 recUpdOpr . */
	private String recUpdOpr;
	private String pagyTxnCode;// 交易编号
	private String pagyTxnName;// 交易名称
	private String acctTypeNo;// 账户类型编号
	private String acctTypeName;// 账户类型名称
	private String mainMchtNoAC;// 修改前接入编号-用于修改记录
	private String certifiCode;// 证书文件编号

	public String getPagyNo() {
		return pagyNo;
	}

	public void setPagyNo(String pagyNo) {
		this.pagyNo = pagyNo;
	}

	public String getPagyName() {
		return pagyName;
	}

	public void setPagyName(String pagyName) {
		this.pagyName = pagyName;
	}

	public String getPagyStat() {
		return pagyStat;
	}

	public void setPagyStat(String pagyStat) {
		this.pagyStat = pagyStat;
	}

	public String getMd5Date() {
		return md5Date;
	}

	public void setMd5Date(String md5Date) {
		this.md5Date = md5Date;
	}

	public String getMd5EndDate() {
		return md5EndDate;
	}

	public void setMd5EndDate(String md5EndDate) {
		this.md5EndDate = md5EndDate;
	}

	public String getMd5Passwd() {
		return md5Passwd;
	}

	public void setMd5Passwd(String md5Passwd) {
		this.md5Passwd = md5Passwd;
	}

	public String getCertifiId() {
		return certifiId;
	}

	public void setCertifiId(String certifiId) {
		this.certifiId = certifiId;
	}

	public String getCertifiPasswd() {
		return certifiPasswd;
	}

	public void setCertifiPasswd(String certifiPasswd) {
		this.certifiPasswd = certifiPasswd;
	}

	public String getCertifiPath() {
		return certifiPath;
	}

	public void setCertifiPath(String certifiPath) {
		this.certifiPath = certifiPath;
	}

	public String getCertifiType() {
		return certifiType;
	}

	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType;
	}

	public String getCertifiBl() {
		return certifiBl;
	}

	public void setCertifiBl(String certifiBl) {
		this.certifiBl = certifiBl;
	}

	public String getCertifiDate() {
		return certifiDate;
	}

	public void setCertifiDate(String certifiDate) {
		this.certifiDate = certifiDate;
	}

	public String getCertifiEndDate() {
		return certifiEndDate;
	}

	public void setCertifiEndDate(String certifiEndDate) {
		this.certifiEndDate = certifiEndDate;
	}

	public String getCertifiUseType() {
		return certifiUseType;
	}

	public void setCertifiUseType(String certifiUseType) {
		this.certifiUseType = certifiUseType;
	}

	public String getEncryptWayType() {
		return encryptWayType;
	}

	public void setEncryptWayType(String encryptWayType) {
		this.encryptWayType = encryptWayType;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	public String getCertifiStatus() {
		return certifiStatus;
	}

	public void setCertifiStatus(String certifiStatus) {
		this.certifiStatus = certifiStatus;
	}

	public String getRecCrtTs() {
		return recCrtTs;
	}

	public void setRecCrtTs(String recCrtTs) {
		this.recCrtTs = recCrtTs;
	}

	public String getRecUpdTs() {
		return recUpdTs;
	}

	public void setRecUpdTs(String recUpdTs) {
		this.recUpdTs = recUpdTs;
	}

	public String getRecCrtOpr() {
		return recCrtOpr;
	}

	public void setRecCrtOpr(String recCrtOpr) {
		this.recCrtOpr = recCrtOpr;
	}

	public String getRecUpdOpr() {
		return recUpdOpr;
	}

	public void setRecUpdOpr(String recUpdOpr) {
		this.recUpdOpr = recUpdOpr;
	}

	public String getNeedSign() {
		return needSign;
	}

	public void setNeedSign(String needSign) {
		this.needSign = needSign;
	}

	public String getMd5EncryptWayType() {
		return md5EncryptWayType;
	}

	public void setMd5EncryptWayType(String md5EncryptWayType) {
		this.md5EncryptWayType = md5EncryptWayType;
	}

	public String getPagyTxnCode() {
		return pagyTxnCode;
	}

	public void setPagyTxnCode(String pagyTxnCode) {
		this.pagyTxnCode = pagyTxnCode;
	}

	public String getPagyTxnName() {
		return pagyTxnName;
	}

	public void setPagyTxnName(String pagyTxnName) {
		this.pagyTxnName = pagyTxnName;
	}

	public String getAcctTypeNo() {
		return acctTypeNo;
	}

	public void setAcctTypeNo(String acctTypeNo) {
		this.acctTypeNo = acctTypeNo;
	}

	public String getAcctTypeName() {
		return acctTypeName;
	}

	public void setAcctTypeName(String acctTypeName) {
		this.acctTypeName = acctTypeName;
	}

	public String getMainMchtNoAC() {
		return mainMchtNoAC;
	}

	public void setMainMchtNoAC(String mainMchtNoAC) {
		this.mainMchtNoAC = mainMchtNoAC;
	}

	public String getCertifiCode() {
		return certifiCode;
	}

	public void setCertifiCode(String certifiCode) {
		this.certifiCode = certifiCode;
	}

}