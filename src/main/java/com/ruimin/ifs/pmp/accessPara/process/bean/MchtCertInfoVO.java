package com.ruimin.ifs.pmp.accessPara.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 商户证书配置表 名称：<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月1日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("PBS_MCHT_CERT_INFO")
public class MchtCertInfoVO {

	@Id
	private String certifiId; // 证书编号
	private String certifiPasswd; // 证书密码（Base64编码）
	private String certifiPath; // 证书路径
	@Id
	private String certifiType; // 证书类型：01：公钥证书；02：私钥证书；03：MD5
	private String certifiBl; // 证书所属
	private String certifiDate; // 证书生效日期
	private String certifiEndDate; // 证书失效日期
	private String certifiUseType; // 使用类型(渠道号)
	private String certifiStatus; // 状态：00：启用；01：停用

	private String encryptWayType; // 加密方式:01：签名;02：加密
	private String encryptType; // 加密类型 :01：数字证书；02：Md5

	private String recCrtTs; // 创建日期
	private String recUpdTs; // 更新日期
	private String recCrtOpr; // 创建人
	private String recUpdOpr; // 更改人
	private String certifiCode; // 证书文件编号

	/**
	 * @return the certifiId
	 */
	public String getCertifiId() {
		return certifiId;
	}

	/**
	 * @param certifiId
	 *            the certifiId to set
	 */
	public void setCertifiId(String certifiId) {
		this.certifiId = certifiId;
	}

	/**
	 * @return the certifiPasswd
	 */
	public String getCertifiPasswd() {
		return certifiPasswd;
	}

	/**
	 * @param certifiPasswd
	 *            the certifiPasswd to set
	 */
	public void setCertifiPasswd(String certifiPasswd) {
		this.certifiPasswd = certifiPasswd;
	}

	/**
	 * @return the certifiPath
	 */
	public String getCertifiPath() {
		return certifiPath;
	}

	/**
	 * @param certifiPath
	 *            the certifiPath to set
	 */
	public void setCertifiPath(String certifiPath) {
		this.certifiPath = certifiPath;
	}

	/**
	 * @return the certifiType
	 */
	public String getCertifiType() {
		return certifiType;
	}

	/**
	 * @param certifiType
	 *            the certifiType to set
	 */
	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType;
	}

	/**
	 * @return the certifiBl
	 */
	public String getCertifiBl() {
		return certifiBl;
	}

	/**
	 * @param certifiBl
	 *            the certifiBl to set
	 */
	public void setCertifiBl(String certifiBl) {
		this.certifiBl = certifiBl;
	}

	/**
	 * @return the certifiDate
	 */
	public String getCertifiDate() {
		return certifiDate;
	}

	/**
	 * @param certifiDate
	 *            the certifiDate to set
	 */
	public void setCertifiDate(String certifiDate) {
		this.certifiDate = certifiDate;
	}

	/**
	 * @return the certifiEndDate
	 */
	public String getCertifiEndDate() {
		return certifiEndDate;
	}

	/**
	 * @param certifiEndDate
	 *            the certifiEndDate to set
	 */
	public void setCertifiEndDate(String certifiEndDate) {
		this.certifiEndDate = certifiEndDate;
	}

	/**
	 * @return the certifiUseType
	 */
	public String getCertifiUseType() {
		return certifiUseType;
	}

	/**
	 * @param certifiUseType
	 *            the certifiUseType to set
	 */
	public void setCertifiUseType(String certifiUseType) {
		this.certifiUseType = certifiUseType;
	}

	/**
	 * @return the certifiStatus
	 */
	public String getCertifiStatus() {
		return certifiStatus;
	}

	/**
	 * @param certifiStatus
	 *            the certifiStatus to set
	 */
	public void setCertifiStatus(String certifiStatus) {
		this.certifiStatus = certifiStatus;
	}

	/**
	 * @return the encryptWayType
	 */
	public String getEncryptWayType() {
		return encryptWayType;
	}

	/**
	 * @param encryptWayType
	 *            the encryptWayType to set
	 */
	public void setEncryptWayType(String encryptWayType) {
		this.encryptWayType = encryptWayType;
	}

	/**
	 * @return the encryptType
	 */
	public String getEncryptType() {
		return encryptType;
	}

	/**
	 * @param encryptType
	 *            the encryptType to set
	 */
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	/**
	 * @return the recCrtTs
	 */
	public String getRecCrtTs() {
		return recCrtTs;
	}

	/**
	 * @param recCrtTs
	 *            the recCrtTs to set
	 */
	public void setRecCrtTs(String recCrtTs) {
		this.recCrtTs = recCrtTs;
	}

	/**
	 * @return the recUpdTs
	 */
	public String getRecUpdTs() {
		return recUpdTs;
	}

	/**
	 * @param recUpdTs
	 *            the recUpdTs to set
	 */
	public void setRecUpdTs(String recUpdTs) {
		this.recUpdTs = recUpdTs;
	}

	/**
	 * @return the recCrtOpr
	 */
	public String getRecCrtOpr() {
		return recCrtOpr;
	}

	/**
	 * @param recCrtOpr
	 *            the recCrtOpr to set
	 */
	public void setRecCrtOpr(String recCrtOpr) {
		this.recCrtOpr = recCrtOpr;
	}

	/**
	 * @return the recUpdOpr
	 */
	public String getRecUpdOpr() {
		return recUpdOpr;
	}

	/**
	 * @param recUpdOpr
	 *            the recUpdOpr to set
	 */
	public void setRecUpdOpr(String recUpdOpr) {
		this.recUpdOpr = recUpdOpr;
	}

	public String getCertifiCode() {
		return certifiCode;
	}

	public void setCertifiCode(String certifiCode) {
		this.certifiCode = certifiCode;
	}

}
