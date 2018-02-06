package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
/**
 * @deprecated:商户信息辅表
 * 创建日期:2017-12-06 15:37:51
 */
@Table("pbs_mcht_assist_info_tmp")
public class PbsMchtAssistInfoTmp{

   @Id
   private String mchtId;
   /**商户协议版本号 **/
   private String mchtProVerNo;
   /**法人性别 1男，2女 **/
   private String mchtArtifSex;
//   /**结算账户类型（2对私，1对公） **/
//   private String accountType;
//   /**结算账户人身份 1法人 2法人亲属 **/
//   private String accountBoss;
   /**法人职业 **/
   private String mchtArtifJob;
   /**法人住址 **/
   private String mchtArtifAddress;
   /**法人亲属证件类型
             **/
   private String accountIdType;
   /**法人证件亲属证件号码 **/
   private String accountIdNo;
   /**法人证件国别/地区（中国CHN，香港HKG，澳门MAC，台湾CTN） **/
   private String mchtArtifCountryId;
   /**组织机构代码证件号 **/
   private String occNo;
   /**组织机构代码证生效日期 YYYY-MM-DD **/
   private String occSdate;
   /**组织机构代码证失效日期 YYYY-MM-DD **/
   private String occEdate;
   /**税务登记证号 **/
   private String trcNo;
   /**税务登记证生效日期 YYYY-MM-DD **/
   private String trcSdate;
   /**税务登记证失效日期 YYYY-MM-DD **/
   private String trcEdate;
   /**财务联系人 **/
   private String financialContact;
   /**财务联系人电话 **/
   private String financialTel;
   /**商户自定义 **/
   private String userDefined;
   /**商户简介 **/
   private String intro;
   /**保留域1 **/
   private String backup1;
   /**保留域2 **/
   private String backup2;
   /**保留域3 **/
   private String backup3;
   /**保留域4 **/
   private String backup4;
   /**保留域5 **/
   private String backup5;
   /**保留域6 **/
   private String backup6;
   /**商户审核结果通知地址 **/
   private String mchtAuditRsltUrl;
   /**一级行业类别编号 **/
   private String firCateCode;
   /**二级行业类别编号 **/
   private String secCateCode;
   /**三级行业类别编号 **/
   private String thdCateCode;
   /**渠道号 **/
   private String chlSysNo;
   
   private String accountType;

   private String setlAcctName;
   
   private String wxJsapiPath;
   
   private String wxAppid;
   
   
   
/**
 * @return the wxJsapiPath
 */
public String getWxJsapiPath() {
    return wxJsapiPath;
}
/**
 * @param wxJsapiPath the wxJsapiPath to set
 */
public void setWxJsapiPath(String wxJsapiPath) {
    this.wxJsapiPath = wxJsapiPath;
}
/**
 * @return the wxAppid
 */
public String getWxAppid() {
    return wxAppid;
}
/**
 * @param wxAppid the wxAppid to set
 */
public void setWxAppid(String wxAppid) {
    this.wxAppid = wxAppid;
}
public String getAccountType() {
    return accountType;
}
public void setAccountType(String accountType) {
    this.accountType = accountType;
}
public String getSetlAcctName() {
    return setlAcctName;
}
public void setSetlAcctName(String setlAcctName) {
    this.setlAcctName = setlAcctName;
}
/**
 * @return the mchtId
 */
public String getMchtId() {
    return mchtId;
}
/**
 * @param mchtId the mchtId to set
 */
public void setMchtId(String mchtId) {
    this.mchtId = mchtId;
}
/**
 * @return the mchtProVerNo
 */
public String getMchtProVerNo() {
    return mchtProVerNo;
}
/**
 * @param mchtProVerNo the mchtProVerNo to set
 */
public void setMchtProVerNo(String mchtProVerNo) {
    this.mchtProVerNo = mchtProVerNo;
}
/**
 * @return the mchtArtifSex
 */
public String getMchtArtifSex() {
    return mchtArtifSex;
}
/**
 * @param mchtArtifSex the mchtArtifSex to set
 */
public void setMchtArtifSex(String mchtArtifSex) {
    this.mchtArtifSex = mchtArtifSex;
}

/**
 * @return the mchtArtifJob
 */
public String getMchtArtifJob() {
    return mchtArtifJob;
}
/**
 * @param mchtArtifJob the mchtArtifJob to set
 */
public void setMchtArtifJob(String mchtArtifJob) {
    this.mchtArtifJob = mchtArtifJob;
}
/**
 * @return the mchtArtifAddress
 */
public String getMchtArtifAddress() {
    return mchtArtifAddress;
}
/**
 * @param mchtArtifAddress the mchtArtifAddress to set
 */
public void setMchtArtifAddress(String mchtArtifAddress) {
    this.mchtArtifAddress = mchtArtifAddress;
}
/**
 * @return the accountIdType
 */
public String getAccountIdType() {
    return accountIdType;
}
/**
 * @param accountIdType the accountIdType to set
 */
public void setAccountIdType(String accountIdType) {
    this.accountIdType = accountIdType;
}
/**
 * @return the accountIdNo
 */
public String getAccountIdNo() {
    return accountIdNo;
}
/**
 * @param accountIdNo the accountIdNo to set
 */
public void setAccountIdNo(String accountIdNo) {
    this.accountIdNo = accountIdNo;
}
/**
 * @return the mchtArtifCountryId
 */
public String getMchtArtifCountryId() {
    return mchtArtifCountryId;
}
/**
 * @param mchtArtifCountryId the mchtArtifCountryId to set
 */
public void setMchtArtifCountryId(String mchtArtifCountryId) {
    this.mchtArtifCountryId = mchtArtifCountryId;
}
/**
 * @return the occNo
 */
public String getOccNo() {
    return occNo;
}
/**
 * @param occNo the occNo to set
 */
public void setOccNo(String occNo) {
    this.occNo = occNo;
}
/**
 * @return the occSdate
 */
public String getOccSdate() {
    return occSdate;
}
/**
 * @param occSdate the occSdate to set
 */
public void setOccSdate(String occSdate) {
    this.occSdate = occSdate;
}
/**
 * @return the occEdate
 */
public String getOccEdate() {
    return occEdate;
}
/**
 * @param occEdate the occEdate to set
 */
public void setOccEdate(String occEdate) {
    this.occEdate = occEdate;
}
/**
 * @return the trcNo
 */
public String getTrcNo() {
    return trcNo;
}
/**
 * @param trcNo the trcNo to set
 */
public void setTrcNo(String trcNo) {
    this.trcNo = trcNo;
}
/**
 * @return the trcSdate
 */
public String getTrcSdate() {
    return trcSdate;
}
/**
 * @param trcSdate the trcSdate to set
 */
public void setTrcSdate(String trcSdate) {
    this.trcSdate = trcSdate;
}
/**
 * @return the trcEdate
 */
public String getTrcEdate() {
    return trcEdate;
}
/**
 * @param trcEdate the trcEdate to set
 */
public void setTrcEdate(String trcEdate) {
    this.trcEdate = trcEdate;
}
/**
 * @return the financialContact
 */
public String getFinancialContact() {
    return financialContact;
}
/**
 * @param financialContact the financialContact to set
 */
public void setFinancialContact(String financialContact) {
    this.financialContact = financialContact;
}
/**
 * @return the financialTel
 */
public String getFinancialTel() {
    return financialTel;
}
/**
 * @param financialTel the financialTel to set
 */
public void setFinancialTel(String financialTel) {
    this.financialTel = financialTel;
}
/**
 * @return the userDefined
 */
public String getUserDefined() {
    return userDefined;
}
/**
 * @param userDefined the userDefined to set
 */
public void setUserDefined(String userDefined) {
    this.userDefined = userDefined;
}
/**
 * @return the intro
 */
public String getIntro() {
    return intro;
}
/**
 * @param intro the intro to set
 */
public void setIntro(String intro) {
    this.intro = intro;
}
/**
 * @return the backup1
 */
public String getBackup1() {
    return backup1;
}
/**
 * @param backup1 the backup1 to set
 */
public void setBackup1(String backup1) {
    this.backup1 = backup1;
}
/**
 * @return the backup2
 */
public String getBackup2() {
    return backup2;
}
/**
 * @param backup2 the backup2 to set
 */
public void setBackup2(String backup2) {
    this.backup2 = backup2;
}
/**
 * @return the backup3
 */
public String getBackup3() {
    return backup3;
}
/**
 * @param backup3 the backup3 to set
 */
public void setBackup3(String backup3) {
    this.backup3 = backup3;
}
/**
 * @return the backup4
 */
public String getBackup4() {
    return backup4;
}
/**
 * @param backup4 the backup4 to set
 */
public void setBackup4(String backup4) {
    this.backup4 = backup4;
}
/**
 * @return the backup5
 */
public String getBackup5() {
    return backup5;
}
/**
 * @param backup5 the backup5 to set
 */
public void setBackup5(String backup5) {
    this.backup5 = backup5;
}
/**
 * @return the backup6
 */
public String getBackup6() {
    return backup6;
}
/**
 * @param backup6 the backup6 to set
 */
public void setBackup6(String backup6) {
    this.backup6 = backup6;
}
/**
 * @return the mchtAuditRsltUrl
 */
public String getMchtAuditRsltUrl() {
    return mchtAuditRsltUrl;
}
/**
 * @param mchtAuditRsltUrl the mchtAuditRsltUrl to set
 */
public void setMchtAuditRsltUrl(String mchtAuditRsltUrl) {
    this.mchtAuditRsltUrl = mchtAuditRsltUrl;
}
/**
 * @return the firCateCode
 */
public String getFirCateCode() {
    return firCateCode;
}
/**
 * @param firCateCode the firCateCode to set
 */
public void setFirCateCode(String firCateCode) {
    this.firCateCode = firCateCode;
}
/**
 * @return the secCateCode
 */
public String getSecCateCode() {
    return secCateCode;
}
/**
 * @param secCateCode the secCateCode to set
 */
public void setSecCateCode(String secCateCode) {
    this.secCateCode = secCateCode;
}
/**
 * @return the thdCateCode
 */
public String getThdCateCode() {
    return thdCateCode;
}
/**
 * @param thdCateCode the thdCateCode to set
 */
public void setThdCateCode(String thdCateCode) {
    this.thdCateCode = thdCateCode;
}
/**
 * @return the chlSysNo
 */
public String getChlSysNo() {
    return chlSysNo;
}
/**
 * @param chlSysNo the chlSysNo to set
 */
public void setChlSysNo(String chlSysNo) {
    this.chlSysNo = chlSysNo;
}
   
   
}