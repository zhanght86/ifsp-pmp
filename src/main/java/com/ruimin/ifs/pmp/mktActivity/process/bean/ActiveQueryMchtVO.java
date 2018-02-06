package com.ruimin.ifs.pmp.mktActivity.process.bean;

import java.io.Serializable;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈商户基本信息临时表〉<br> 
 * 〈功能详细描述〉
 * 〈方法简述 - 方法描述〉
 *  
 * @author MJ
 */
//@Table("IMP_MCHT_INF_TMP")
public class ActiveQueryMchtVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String activeNo;
	//@Id
	private String mchtId;			// 商户编号
    
	private String isVirMcht;		// 是否虚拟商户
	private String mchtStat;		// 商户状态
	private String mchtMngNo;		// 上级商户号
	private String mchtName;		// 商户名称
	private String mchtCnAbbr;		// 商户简称
	private String mchtAddr;		// 商户地址
	private String mchtWhiteFlag;	// 白名单标志
	private String mchtAreaNo;		// 所在地区
	private String mchtOrgId;		// 所属机构
	private String mchtPersonName;	// 商户联系人
	private String mchtPhone;		// 商户联系电话
	private String mchtZipNo;		// 邮编
	private String mchtEmail;		// 电子邮箱
	private String mchtCustomNo;	// 客户号
	private String mchtFaxNo;		// 传真
	private String mchtContAddr;	// 商户联系地址
	private String mchtManager;		// 所属经理
	private String mchtServiceCode;	// 商户服务代码
	private String acquirBranch;	// 收单机构代码
	private String acceptBranch;	// 受理机构代码
	private String countryCode;		// 国家代码
	private String regionCode;		// 行政区划代码
	private String mchtGrp;			// 商户组别
	private String mchtMcc;			// 商户MCC
	private String acceptRegion;	// 受理地区代码
	private String slmtRegion;		// 清算地区代码
	private String txnMchtType;		// 交易商户类型
	private String realMchtType;	// 真实商户类型
	
	private String mchtScale;		//商户规模
	private String billSpecType;	//特殊计费类型
	private String billSpecValue;	//特殊计费档次
	
	private String mchtLoginName;	// 商户登录名
	private String mchtIndus;		// 行业分类
	private String mchtChnlIcpNo;	// ICP经营许可证号
	private String ownedCompany;	// 所属公司
	private String ownedDepartment;	// 所属部门
	private String withDrawFlag;	// 是否允许自设提现银行
	//private String mchtLogo;
	private String mchtUrl;			// 商户URL
	private String busContacts;		// 商务联系人
	private String busPhone;		// 商务联系电话
	private String busMail;			// 商务邮箱
	private String busComm;			// 商务QQ/MSN
	private String slmtContacts;	// 结算联系人
	private String slmtPhone;		// 结算联系电话
	private String slmtMail;		// 结算邮箱
	private String slmtComm;		// 结算QQ/MSN
	private String serviceContacts;	// 客服联系人
	private String servicePhone;	// 客服联系电话
	private String serviceMail;		// 客服邮箱
	private String serviceComm;		// 客服QQ/MSN
	
	private String recvDeposit;		// 应收保证金
	private String paidDeposit;		// 实收保证金
	private String mchtRegAddr;		// 商户注册地址
	private String mchtRegAmt;		// 注册资金
	private String mchtBusAddr;		// 营业地址
	private String mchtMngScope;	// 营业范围
	private String mchtTrcnNo;		// 税务登记证件号
	private String mchtLicnNo;		// 营业执照号码
	private String mchtLicnFlag;	// 营业号码重复标志
	private String mchtLicnDate;	// 营业执照有效期
	
	private String mchtArtifName;	// 法人姓名
	private String mchtArtifType;	// 法人证件类型
	private String mchtArtifId;		// 法人证件号码
	private String mchtArtifPhone;	// 法人联系电话
	
	
	private String mchtType;		// 商户类型
	private String mchtLevl;		// 商户级别
	private String mchtEnName;		// 商户英文名称
	private String mchtEnAbbr;		// 商户英文简称
	private String mchtEnAddr;		// 商户英文地址
	private String mchtEtpsAttr;	// 企业类性质
	private String mchtSacBrh;		// 签约网点
	private String mchtSacDate;		// 签约日期
	private String mchtAmrNo;		// 客户经理工号
	private String mchtAmrName;		// 客户经理姓名
	private String mchtAmrPhone;	// 客户经理联系电话
	private String mchtGrade;		// 商户评级
	private String remark;			// 备注
	
	private String longitude;		// 经度
	private String latitude;		// 维度
	private String auditFlag;		// 审核流程号
	
	private String mchtTxnUrl;			// 商城URL
	
	private String lastAudTlr;
	private String lastAudDateTime;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	private String inFlg;//商户参与状态
	private  boolean select;
	
	public String getActiveNo() {
		return activeNo;
	}
	public void setActiveNo(String activeNo) {
		this.activeNo = activeNo;
	}
	public boolean getSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public String getInFlg() {
		return inFlg;
	}
	public void setInFlg(String inFlg) {
		this.inFlg = inFlg;
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
	 * @return the isVirMcht
	 */
	public String getIsVirMcht() {
		return isVirMcht;
	}
	/**
	 * @param isVirMcht the isVirMcht to set
	 */
	public void setIsVirMcht(String isVirMcht) {
		this.isVirMcht = isVirMcht;
	}
	/**
	 * @return the mchtStat
	 */
	public String getMchtStat() {
		return mchtStat;
	}
	/**
	 * @param mchtStat the mchtStat to set
	 */
	public void setMchtStat(String mchtStat) {
		this.mchtStat = mchtStat;
	}
	/**
	 * @return the mchtMngNo
	 */
	public String getMchtMngNo() {
		return mchtMngNo;
	}
	/**
	 * @param mchtMngNo the mchtMngNo to set
	 */
	public void setMchtMngNo(String mchtMngNo) {
		this.mchtMngNo = mchtMngNo;
	}
	/**
	 * @return the mchtName
	 */
	public String getMchtName() {
		return mchtName;
	}
	/**
	 * @param mchtName the mchtName to set
	 */
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	/**
	 * @return the mchtCnAbbr
	 */
	public String getMchtCnAbbr() {
		return mchtCnAbbr;
	}
	/**
	 * @param mchtCnAbbr the mchtCnAbbr to set
	 */
	public void setMchtCnAbbr(String mchtCnAbbr) {
		this.mchtCnAbbr = mchtCnAbbr;
	}
	/**
	 * @return the mchtAddr
	 */
	public String getMchtAddr() {
		return mchtAddr;
	}
	/**
	 * @param mchtAddr the mchtAddr to set
	 */
	public void setMchtAddr(String mchtAddr) {
		this.mchtAddr = mchtAddr;
	}
	/**
	 * @return the mchtWhiteFlag
	 */
	public String getMchtWhiteFlag() {
		return mchtWhiteFlag;
	}
	/**
	 * @param mchtWhiteFlag the mchtWhiteFlag to set
	 */
	public void setMchtWhiteFlag(String mchtWhiteFlag) {
		this.mchtWhiteFlag = mchtWhiteFlag;
	}
	/**
	 * @return the mchtAreaNo
	 */
	public String getMchtAreaNo() {
		return mchtAreaNo;
	}
	/**
	 * @param mchtAreaNo the mchtAreaNo to set
	 */
	public void setMchtAreaNo(String mchtAreaNo) {
		this.mchtAreaNo = mchtAreaNo;
	}
	/**
	 * @return the mchtOrgId
	 */
	public String getMchtOrgId() {
		return mchtOrgId;
	}
	/**
	 * @param mchtOrgId the mchtOrgId to set
	 */
	public void setMchtOrgId(String mchtOrgId) {
		this.mchtOrgId = mchtOrgId;
	}
	/**
	 * @return the mchtPersonName
	 */
	public String getMchtPersonName() {
		return mchtPersonName;
	}
	/**
	 * @param mchtPersonName the mchtPersonName to set
	 */
	public void setMchtPersonName(String mchtPersonName) {
		this.mchtPersonName = mchtPersonName;
	}
	/**
	 * @return the mchtPhone
	 */
	public String getMchtPhone() {
		return mchtPhone;
	}
	/**
	 * @param mchtPhone the mchtPhone to set
	 */
	public void setMchtPhone(String mchtPhone) {
		this.mchtPhone = mchtPhone;
	}
	/**
	 * @return the mchtZipNo
	 */
	public String getMchtZipNo() {
		return mchtZipNo;
	}
	/**
	 * @param mchtZipNo the mchtZipNo to set
	 */
	public void setMchtZipNo(String mchtZipNo) {
		this.mchtZipNo = mchtZipNo;
	}
	/**
	 * @return the mchtEmail
	 */
	public String getMchtEmail() {
		return mchtEmail;
	}
	/**
	 * @param mchtEmail the mchtEmail to set
	 */
	public void setMchtEmail(String mchtEmail) {
		this.mchtEmail = mchtEmail;
	}
	/**
	 * @return the mchtCustomNo
	 */
	public String getMchtCustomNo() {
		return mchtCustomNo;
	}
	/**
	 * @param mchtCustomNo the mchtCustomNo to set
	 */
	public void setMchtCustomNo(String mchtCustomNo) {
		this.mchtCustomNo = mchtCustomNo;
	}
	/**
	 * @return the mchtFaxNo
	 */
	public String getMchtFaxNo() {
		return mchtFaxNo;
	}
	/**
	 * @param mchtFaxNo the mchtFaxNo to set
	 */
	public void setMchtFaxNo(String mchtFaxNo) {
		this.mchtFaxNo = mchtFaxNo;
	}
	/**
	 * @return the mchtContAddr
	 */
	public String getMchtContAddr() {
		return mchtContAddr;
	}
	/**
	 * @param mchtContAddr the mchtContAddr to set
	 */
	public void setMchtContAddr(String mchtContAddr) {
		this.mchtContAddr = mchtContAddr;
	}
	/**
	 * @return the mchtManager
	 */
	public String getMchtManager() {
		return mchtManager;
	}
	/**
	 * @param mchtManager the mchtManager to set
	 */
	public void setMchtManager(String mchtManager) {
		this.mchtManager = mchtManager;
	}
	/**
	 * @return the mchtServiceCode
	 */
	public String getMchtServiceCode() {
		return mchtServiceCode;
	}
	/**
	 * @param mchtServiceCode the mchtServiceCode to set
	 */
	public void setMchtServiceCode(String mchtServiceCode) {
		this.mchtServiceCode = mchtServiceCode;
	}
	/**
	 * @return the acquirBranch
	 */
	public String getAcquirBranch() {
		return acquirBranch;
	}
	/**
	 * @param acquirBranch the acquirBranch to set
	 */
	public void setAcquirBranch(String acquirBranch) {
		this.acquirBranch = acquirBranch;
	}
	/**
	 * @return the acceptBranch
	 */
	public String getAcceptBranch() {
		return acceptBranch;
	}
	/**
	 * @param acceptBranch the acceptBranch to set
	 */
	public void setAcceptBranch(String acceptBranch) {
		this.acceptBranch = acceptBranch;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the mchtGrp
	 */
	public String getMchtGrp() {
		return mchtGrp;
	}
	/**
	 * @param mchtGrp the mchtGrp to set
	 */
	public void setMchtGrp(String mchtGrp) {
		this.mchtGrp = mchtGrp;
	}
	/**
	 * @return the mchtMcc
	 */
	public String getMchtMcc() {
		return mchtMcc;
	}
	/**
	 * @param mchtMcc the mchtMcc to set
	 */
	public void setMchtMcc(String mchtMcc) {
		this.mchtMcc = mchtMcc;
	}
	/**
	 * @return the acceptRegion
	 */
	public String getAcceptRegion() {
		return acceptRegion;
	}
	/**
	 * @param acceptRegion the acceptRegion to set
	 */
	public void setAcceptRegion(String acceptRegion) {
		this.acceptRegion = acceptRegion;
	}
	/**
	 * @return the slmtRegion
	 */
	public String getSlmtRegion() {
		return slmtRegion;
	}
	/**
	 * @param slmtRegion the slmtRegion to set
	 */
	public void setSlmtRegion(String slmtRegion) {
		this.slmtRegion = slmtRegion;
	}
	/**
	 * @return the txnMchtType
	 */
	public String getTxnMchtType() {
		return txnMchtType;
	}
	/**
	 * @param txnMchtType the txnMchtType to set
	 */
	public void setTxnMchtType(String txnMchtType) {
		this.txnMchtType = txnMchtType;
	}
	/**
	 * @return the realMchtType
	 */
	public String getRealMchtType() {
		return realMchtType;
	}
	/**
	 * @param realMchtType the realMchtType to set
	 */
	public void setRealMchtType(String realMchtType) {
		this.realMchtType = realMchtType;
	}
	/**
	 * @return the mchtLoginName
	 */
	public String getMchtLoginName() {
		return mchtLoginName;
	}
	/**
	 * @param mchtLoginName the mchtLoginName to set
	 */
	public void setMchtLoginName(String mchtLoginName) {
		this.mchtLoginName = mchtLoginName;
	}
	/**
	 * @return the mchtIndus
	 */
	public String getMchtIndus() {
		return mchtIndus;
	}
	/**
	 * @param mchtIndus the mchtIndus to set
	 */
	public void setMchtIndus(String mchtIndus) {
		this.mchtIndus = mchtIndus;
	}
	/**
	 * @return the mchtChnlIcpNo
	 */
	public String getMchtChnlIcpNo() {
		return mchtChnlIcpNo;
	}
	/**
	 * @param mchtChnlIcpNo the mchtChnlIcpNo to set
	 */
	public void setMchtChnlIcpNo(String mchtChnlIcpNo) {
		this.mchtChnlIcpNo = mchtChnlIcpNo;
	}
	/**
	 * @return the ownedCompany
	 */
	public String getOwnedCompany() {
		return ownedCompany;
	}
	/**
	 * @param ownedCompany the ownedCompany to set
	 */
	public void setOwnedCompany(String ownedCompany) {
		this.ownedCompany = ownedCompany;
	}
	/**
	 * @return the ownedDepartment
	 */
	public String getOwnedDepartment() {
		return ownedDepartment;
	}
	/**
	 * @param ownedDepartment the ownedDepartment to set
	 */
	public void setOwnedDepartment(String ownedDepartment) {
		this.ownedDepartment = ownedDepartment;
	}
	/**
	 * @return the withDrawFlag
	 */
	public String getWithDrawFlag() {
		return withDrawFlag;
	}
	/**
	 * @param withDrawFlag the withDrawFlag to set
	 */
	public void setWithDrawFlag(String withDrawFlag) {
		this.withDrawFlag = withDrawFlag;
	}
	/**
	 * @return the mchtUrl
	 */
	public String getMchtUrl() {
		return mchtUrl;
	}
	/**
	 * @param mchtUrl the mchtUrl to set
	 */
	public void setMchtUrl(String mchtUrl) {
		this.mchtUrl = mchtUrl;
	}
	/**
	 * @return the busContacts
	 */
	public String getBusContacts() {
		return busContacts;
	}
	/**
	 * @param busContacts the busContacts to set
	 */
	public void setBusContacts(String busContacts) {
		this.busContacts = busContacts;
	}
	/**
	 * @return the busPhone
	 */
	public String getBusPhone() {
		return busPhone;
	}
	/**
	 * @param busPhone the busPhone to set
	 */
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}
	/**
	 * @return the busMail
	 */
	public String getBusMail() {
		return busMail;
	}
	/**
	 * @param busMail the busMail to set
	 */
	public void setBusMail(String busMail) {
		this.busMail = busMail;
	}
	/**
	 * @return the busComm
	 */
	public String getBusComm() {
		return busComm;
	}
	/**
	 * @param busComm the busComm to set
	 */
	public void setBusComm(String busComm) {
		this.busComm = busComm;
	}
	/**
	 * @return the slmtContacts
	 */
	public String getSlmtContacts() {
		return slmtContacts;
	}
	/**
	 * @param slmtContacts the slmtContacts to set
	 */
	public void setSlmtContacts(String slmtContacts) {
		this.slmtContacts = slmtContacts;
	}
	/**
	 * @return the slmtPhone
	 */
	public String getSlmtPhone() {
		return slmtPhone;
	}
	/**
	 * @param slmtPhone the slmtPhone to set
	 */
	public void setSlmtPhone(String slmtPhone) {
		this.slmtPhone = slmtPhone;
	}
	/**
	 * @return the slmtMail
	 */
	public String getSlmtMail() {
		return slmtMail;
	}
	/**
	 * @param slmtMail the slmtMail to set
	 */
	public void setSlmtMail(String slmtMail) {
		this.slmtMail = slmtMail;
	}
	/**
	 * @return the slmtComm
	 */
	public String getSlmtComm() {
		return slmtComm;
	}
	/**
	 * @param slmtComm the slmtComm to set
	 */
	public void setSlmtComm(String slmtComm) {
		this.slmtComm = slmtComm;
	}
	/**
	 * @return the serviceContacts
	 */
	public String getServiceContacts() {
		return serviceContacts;
	}
	/**
	 * @param serviceContacts the serviceContacts to set
	 */
	public void setServiceContacts(String serviceContacts) {
		this.serviceContacts = serviceContacts;
	}
	/**
	 * @return the servicePhone
	 */
	public String getServicePhone() {
		return servicePhone;
	}
	/**
	 * @param servicePhone the servicePhone to set
	 */
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	/**
	 * @return the serviceMail
	 */
	public String getServiceMail() {
		return serviceMail;
	}
	/**
	 * @param serviceMail the serviceMail to set
	 */
	public void setServiceMail(String serviceMail) {
		this.serviceMail = serviceMail;
	}
	/**
	 * @return the serviceComm
	 */
	public String getServiceComm() {
		return serviceComm;
	}
	/**
	 * @param serviceComm the serviceComm to set
	 */
	public void setServiceComm(String serviceComm) {
		this.serviceComm = serviceComm;
	}
	/**
	 * @return the recvDeposit
	 */
	public String getRecvDeposit() {
		return recvDeposit;
	}
	/**
	 * @param recvDeposit the recvDeposit to set
	 */
	public void setRecvDeposit(String recvDeposit) {
		this.recvDeposit = recvDeposit;
	}
	/**
	 * @return the paidDeposit
	 */
	public String getPaidDeposit() {
		return paidDeposit;
	}
	/**
	 * @param paidDeposit the paidDeposit to set
	 */
	public void setPaidDeposit(String paidDeposit) {
		this.paidDeposit = paidDeposit;
	}
	/**
	 * @return the mchtRegAddr
	 */
	public String getMchtRegAddr() {
		return mchtRegAddr;
	}
	/**
	 * @param mchtRegAddr the mchtRegAddr to set
	 */
	public void setMchtRegAddr(String mchtRegAddr) {
		this.mchtRegAddr = mchtRegAddr;
	}
	/**
	 * @return the mchtRegAmt
	 */
	public String getMchtRegAmt() {
		return mchtRegAmt;
	}
	/**
	 * @param mchtRegAmt the mchtRegAmt to set
	 */
	public void setMchtRegAmt(String mchtRegAmt) {
		this.mchtRegAmt = mchtRegAmt;
	}
	/**
	 * @return the mchtBusAddr
	 */
	public String getMchtBusAddr() {
		return mchtBusAddr;
	}
	/**
	 * @param mchtBusAddr the mchtBusAddr to set
	 */
	public void setMchtBusAddr(String mchtBusAddr) {
		this.mchtBusAddr = mchtBusAddr;
	}
	/**
	 * @return the mchtMngScope
	 */
	public String getMchtMngScope() {
		return mchtMngScope;
	}
	/**
	 * @param mchtMngScope the mchtMngScope to set
	 */
	public void setMchtMngScope(String mchtMngScope) {
		this.mchtMngScope = mchtMngScope;
	}
	/**
	 * @return the mchtTrcnNo
	 */
	public String getMchtTrcnNo() {
		return mchtTrcnNo;
	}
	/**
	 * @param mchtTrcnNo the mchtTrcnNo to set
	 */
	public void setMchtTrcnNo(String mchtTrcnNo) {
		this.mchtTrcnNo = mchtTrcnNo;
	}
	/**
	 * @return the mchtLicnNo
	 */
	public String getMchtLicnNo() {
		return mchtLicnNo;
	}
	/**
	 * @param mchtLicnNo the mchtLicnNo to set
	 */
	public void setMchtLicnNo(String mchtLicnNo) {
		this.mchtLicnNo = mchtLicnNo;
	}
	/**
	 * @return the mchtLicnFlag
	 */
	public String getMchtLicnFlag() {
		return mchtLicnFlag;
	}
	/**
	 * @param mchtLicnFlag the mchtLicnFlag to set
	 */
	public void setMchtLicnFlag(String mchtLicnFlag) {
		this.mchtLicnFlag = mchtLicnFlag;
	}
	/**
	 * @return the mchtLicnDate
	 */
	public String getMchtLicnDate() {
		return mchtLicnDate;
	}
	/**
	 * @param mchtLicnDate the mchtLicnDate to set
	 */
	public void setMchtLicnDate(String mchtLicnDate) {
		this.mchtLicnDate = mchtLicnDate;
	}
	/**
	 * @return the mchtArtifName
	 */
	public String getMchtArtifName() {
		return mchtArtifName;
	}
	/**
	 * @param mchtArtifName the mchtArtifName to set
	 */
	public void setMchtArtifName(String mchtArtifName) {
		this.mchtArtifName = mchtArtifName;
	}
	/**
	 * @return the mchtArtifType
	 */
	public String getMchtArtifType() {
		return mchtArtifType;
	}
	/**
	 * @param mchtArtifType the mchtArtifType to set
	 */
	public void setMchtArtifType(String mchtArtifType) {
		this.mchtArtifType = mchtArtifType;
	}
	/**
	 * @return the mchtArtifId
	 */
	public String getMchtArtifId() {
		return mchtArtifId;
	}
	/**
	 * @param mchtArtifId the mchtArtifId to set
	 */
	public void setMchtArtifId(String mchtArtifId) {
		this.mchtArtifId = mchtArtifId;
	}
	/**
	 * @return the mchtArtifPhone
	 */
	public String getMchtArtifPhone() {
		return mchtArtifPhone;
	}
	/**
	 * @param mchtArtifPhone the mchtArtifPhone to set
	 */
	public void setMchtArtifPhone(String mchtArtifPhone) {
		this.mchtArtifPhone = mchtArtifPhone;
	}
	/**
	 * @return the mchtType
	 */
	public String getMchtType() {
		return mchtType;
	}
	/**
	 * @param mchtType the mchtType to set
	 */
	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
	/**
	 * @return the mchtLevl
	 */
	public String getMchtLevl() {
		return mchtLevl;
	}
	/**
	 * @param mchtLevl the mchtLevl to set
	 */
	public void setMchtLevl(String mchtLevl) {
		this.mchtLevl = mchtLevl;
	}
	/**
	 * @return the mchtEnName
	 */
	public String getMchtEnName() {
		return mchtEnName;
	}
	/**
	 * @param mchtEnName the mchtEnName to set
	 */
	public void setMchtEnName(String mchtEnName) {
		this.mchtEnName = mchtEnName;
	}
	/**
	 * @return the mchtEnAbbr
	 */
	public String getMchtEnAbbr() {
		return mchtEnAbbr;
	}
	/**
	 * @param mchtEnAbbr the mchtEnAbbr to set
	 */
	public void setMchtEnAbbr(String mchtEnAbbr) {
		this.mchtEnAbbr = mchtEnAbbr;
	}
	/**
	 * @return the mchtEnAddr
	 */
	public String getMchtEnAddr() {
		return mchtEnAddr;
	}
	/**
	 * @param mchtEnAddr the mchtEnAddr to set
	 */
	public void setMchtEnAddr(String mchtEnAddr) {
		this.mchtEnAddr = mchtEnAddr;
	}
	/**
	 * @return the mchtEtpsAttr
	 */
	public String getMchtEtpsAttr() {
		return mchtEtpsAttr;
	}
	/**
	 * @param mchtEtpsAttr the mchtEtpsAttr to set
	 */
	public void setMchtEtpsAttr(String mchtEtpsAttr) {
		this.mchtEtpsAttr = mchtEtpsAttr;
	}
	/**
	 * @return the mchtSacBrh
	 */
	public String getMchtSacBrh() {
		return mchtSacBrh;
	}
	/**
	 * @param mchtSacBrh the mchtSacBrh to set
	 */
	public void setMchtSacBrh(String mchtSacBrh) {
		this.mchtSacBrh = mchtSacBrh;
	}
	/**
	 * @return the mchtSacDate
	 */
	public String getMchtSacDate() {
		return mchtSacDate;
	}
	/**
	 * @param mchtSacDate the mchtSacDate to set
	 */
	public void setMchtSacDate(String mchtSacDate) {
		this.mchtSacDate = mchtSacDate;
	}
	/**
	 * @return the mchtAmrNo
	 */
	public String getMchtAmrNo() {
		return mchtAmrNo;
	}
	/**
	 * @param mchtAmrNo the mchtAmrNo to set
	 */
	public void setMchtAmrNo(String mchtAmrNo) {
		this.mchtAmrNo = mchtAmrNo;
	}
	/**
	 * @return the mchtAmrName
	 */
	public String getMchtAmrName() {
		return mchtAmrName;
	}
	/**
	 * @param mchtAmrName the mchtAmrName to set
	 */
	public void setMchtAmrName(String mchtAmrName) {
		this.mchtAmrName = mchtAmrName;
	}
	/**
	 * @return the mchtAmrPhone
	 */
	public String getMchtAmrPhone() {
		return mchtAmrPhone;
	}
	/**
	 * @param mchtAmrPhone the mchtAmrPhone to set
	 */
	public void setMchtAmrPhone(String mchtAmrPhone) {
		this.mchtAmrPhone = mchtAmrPhone;
	}
	/**
	 * @return the mchtGrade
	 */
	public String getMchtGrade() {
		return mchtGrade;
	}
	/**
	 * @param mchtGrade the mchtGrade to set
	 */
	public void setMchtGrade(String mchtGrade) {
		this.mchtGrade = mchtGrade;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the lastAudTlr
	 */
	public String getLastAudTlr() {
		return lastAudTlr;
	}
	/**
	 * @param lastAudTlr the lastAudTlr to set
	 */
	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}
	/**
	 * @return the lastAudDateTime
	 */
	public String getLastAudDateTime() {
		return lastAudDateTime;
	}
	/**
	 * @param lastAudDateTime the lastAudDateTime to set
	 */
	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
	}
	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}
	/**
	 * @param crtTlr the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}
	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}
	/**
	 * @param crtDateTime the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}
	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}
	/**
	 * @param lastUpdTlr the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}
	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}
	/**
	 * @param lastUpdDateTime the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}
	/**
	 * @return the billSpecType
	 */
	public String getBillSpecType() {
		return billSpecType;
	}
	/**
	 * @param billSpecType the billSpecType to set
	 */
	public void setBillSpecType(String billSpecType) {
		this.billSpecType = billSpecType;
	}
	/**
	 * @return the billSpecValue
	 */
	public String getBillSpecValue() {
		return billSpecValue;
	}
	/**
	 * @param billSpecValue the billSpecValue to set
	 */
	public void setBillSpecValue(String billSpecValue) {
		this.billSpecValue = billSpecValue;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the auditFlag
	 */
	public String getAuditFlag() {
		return auditFlag;
	}
	/**
	 * @param auditFlag the auditFlag to set
	 */
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	/**
	 * @return the mchtScale
	 */
	public String getMchtScale() {
		return mchtScale;
	}
	/**
	 * @param mchtScale the mchtScale to set
	 */
	public void setMchtScale(String mchtScale) {
		this.mchtScale = mchtScale;
	}
	/**
	 * @return the mchtTxnUrl
	 */
	public String getMchtTxnUrl() {
		return mchtTxnUrl;
	}
	/**
	 * @param mchtTxnUrl the mchtTxnUrl to set
	 */
	public void setMchtTxnUrl(String mchtTxnUrl) {
		this.mchtTxnUrl = mchtTxnUrl;
	}
	
	
}

