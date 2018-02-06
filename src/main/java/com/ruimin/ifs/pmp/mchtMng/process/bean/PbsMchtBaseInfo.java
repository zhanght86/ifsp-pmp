package com.ruimin.ifs.pmp.mchtMng.process.bean;

import java.io.Serializable;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 
 * 商户信息基本表-实体类
 * 
 * @author zhangjc
 *
 */
@Table("PBS_MCHT_BASE_INFO_TMP")
public class PbsMchtBaseInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544597974962656442L;
	@Id
	private String mchtId;
	private String mchtSimpleName;
	private String mchtName;
	private String mchtType;
	private String mchtStat;
	private String mchtMngNo;
	private String mchtPersonName;
	private String mchtPhone;
	private String mchtContAddr;
	private String mchtOrgId;
	private String mchtAmrNo;
	private String mchtAmrName;
	
	private String mchtAreaNo;
	private String mchtZipNo;
	private String mchtAmrPhone;
	private String mchtLicnType;
	private String mchtLicnNo;
	private String mchtLicnExpDate;
	private String mchtMngScope;
	private String mchtTrcnNo;
	private String mchtRegAmt;
	private String mchtRegAddr;
	private String recvDeposit;
	private String paidDeposit;
	private String mchtArtifName;
	private String mchtArtifPhone;
	private String mchtArtifType;
	private String mchtArtifId;
	private String lastAudTlr;
	private String lastAudDateTime;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	private String remark;
	private String regDate;
	private String mchtEmail;

	/** 新加字段 . */
	private String auditId;// 审核信息编号
	private String syncState;// 审核标志位
	private String riskLevel;//商户风险等级
	private String isExistAgent;//是否存在代理商
	private String agentId;//代理商
//	private String capitalSharerateid;//本金分笔规则编号
	private String currencyType;//货币类型
	private String organizationCode;//组织机构代码
	private String telephone;
	private String artifSdate;//法人证件生效日期
	private String artifEdate;//法人证件失效日期
	private String mchtLicnSdate;//营业执照生效日期
		
	private String creditLimit;
	
	public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

	
    public String getArtifSdate() {
        return artifSdate;
    }
    
    public void setArtifSdate(String artifSdate) {
        this.artifSdate = artifSdate;
    }
   
    public String getArtifEdate() {
        return artifEdate;
    }
    
    public void setArtifEdate(String artifEdate) {
        this.artifEdate = artifEdate;
    }
    
    public String getMchtLicnSdate() {
        return mchtLicnSdate;
    }
    
    public void setMchtLicnSdate(String mchtLicnSdate) {
        this.mchtLicnSdate = mchtLicnSdate;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * @return the organizationCode
     */
    public String getOrganizationCode() {
        return organizationCode;
    }
    /**
     * @param organizationCode the organizationCode to set
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    /**
     * @return the currencyType
     */
    public String getCurrencyType() {
        return currencyType;
    }
    /**
     * @param currencyType the currencyType to set
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    /**
     * @return the capitalSharerateid
     */
//    public String getCapitalSharerateid() {
//        return capitalSharerateid;
//    }
//    /**
//     * @param capitalSharerateid the capitalSharerateid to set
//     */
//    public void setCapitalSharerateid(String capitalSharerateid) {
//        this.capitalSharerateid = capitalSharerateid;
//    }
    /**
     * @return the riskLevel
     */
    public String getRiskLevel() {
        return riskLevel;
    }
    /**
     * @param riskLevel the riskLevel to set
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    /**
     * @return the isExistAgent
     */
    public String getIsExistAgent() {
        return isExistAgent;
    }
    /**
     * @param isExistAgent the isExistAgent to set
     */
    public void setIsExistAgent(String isExistAgent) {
        this.isExistAgent = isExistAgent;
    }
    /**
     * @return the agentId
     */
    public String getAgentId() {
        return agentId;
    }
    /**
     * @param agentId the agentId to set
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getMchtType() {
		return mchtType;
	}
	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	public String getMchtSimpleName() {
		return mchtSimpleName;
	}
	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}
	public String getMchtStat() {
		return mchtStat;
	}
	public void setMchtStat(String mchtStat) {
		this.mchtStat = mchtStat;
	}
	public String getMchtMngNo() {
		return mchtMngNo;
	}
	public void setMchtMngNo(String mchtMngNo) {
		this.mchtMngNo = mchtMngNo;
	}
	public String getMchtAreaNo() {
		return mchtAreaNo;
	}
	public void setMchtAreaNo(String mchtAreaNo) {
		this.mchtAreaNo = mchtAreaNo;
	}
	public String getMchtPersonName() {
		return mchtPersonName;
	}
	public void setMchtPersonName(String mchtPersonName) {
		this.mchtPersonName = mchtPersonName;
	}
	public String getMchtPhone() {
		return mchtPhone;
	}
	public void setMchtPhone(String mchtPhone) {
		this.mchtPhone = mchtPhone;
	}
	public String getMchtContAddr() {
		return mchtContAddr;
	}
	public void setMchtContAddr(String mchtContAddr) {
		this.mchtContAddr = mchtContAddr;
	}
	public String getMchtZipNo() {
		return mchtZipNo;
	}
	public void setMchtZipNo(String mchtZipNo) {
		this.mchtZipNo = mchtZipNo;
	}
	public String getMchtOrgId() {
		return mchtOrgId;
	}
	public void setMchtOrgId(String mchtOrgId) {
		this.mchtOrgId = mchtOrgId;
	}
	public String getMchtAmrNo() {
		return mchtAmrNo;
	}
	public void setMchtAmrNo(String mchtAmrNo) {
		this.mchtAmrNo = mchtAmrNo;
	}
	public String getMchtAmrName() {
		return mchtAmrName;
	}
	public void setMchtAmrName(String mchtAmrName) {
		this.mchtAmrName = mchtAmrName;
	}
	public String getMchtAmrPhone() {
		return mchtAmrPhone;
	}
	public void setMchtAmrPhone(String mchtAmrPhone) {
		this.mchtAmrPhone = mchtAmrPhone;
	}
	public String getMchtLicnType() {
		return mchtLicnType;
	}
	public void setMchtLicnType(String mchtLicnType) {
		this.mchtLicnType = mchtLicnType;
	}
	public String getMchtLicnNo() {
		return mchtLicnNo;
	}
	public void setMchtLicnNo(String mchtLicnNo) {
		this.mchtLicnNo = mchtLicnNo;
	}
	public String getMchtLicnExpDate() {
		return mchtLicnExpDate;
	}
	public void setMchtLicnExpDate(String mchtLicnExpDate) {
		this.mchtLicnExpDate = mchtLicnExpDate;
	}
	public String getMchtMngScope() {
		return mchtMngScope;
	}
	public void setMchtMngScope(String mchtMngScope) {
		this.mchtMngScope = mchtMngScope;
	}
	public String getMchtTrcnNo() {
		return mchtTrcnNo;
	}
	public void setMchtTrcnNo(String mchtTrcnNo) {
		this.mchtTrcnNo = mchtTrcnNo;
	}
	public String getMchtRegAmt() {
		return mchtRegAmt;
	}
	public void setMchtRegAmt(String mchtRegAmt) {
		this.mchtRegAmt = mchtRegAmt;
	}
	public String getMchtRegAddr() {
		return mchtRegAddr;
	}
	public void setMchtRegAddr(String mchtRegAddr) {
		this.mchtRegAddr = mchtRegAddr;
	}
	public String getRecvDeposit() {
		return recvDeposit;
	}
	public void setRecvDeposit(String recvDeposit) {
		this.recvDeposit = recvDeposit;
	}
	public String getPaidDeposit() {
		return paidDeposit;
	}
	public void setPaidDeposit(String paidDeposit) {
		this.paidDeposit = paidDeposit;
	}
	public String getMchtArtifName() {
		return mchtArtifName;
	}
	public void setMchtArtifName(String mchtArtifName) {
		this.mchtArtifName = mchtArtifName;
	}
	public String getMchtArtifPhone() {
		return mchtArtifPhone;
	}
	public void setMchtArtifPhone(String mchtArtifPhone) {
		this.mchtArtifPhone = mchtArtifPhone;
	}
	public String getMchtArtifType() {
		return mchtArtifType;
	}
	public void setMchtArtifType(String mchtArtifType) {
		this.mchtArtifType = mchtArtifType;
	}
	public String getMchtArtifId() {
		return mchtArtifId;
	}
	public void setMchtArtifId(String mchtArtifId) {
		this.mchtArtifId = mchtArtifId;
	}
	public String getLastAudTlr() {
		return lastAudTlr;
	}
	public void setLastAudTlr(String lastAudTlr) {
		this.lastAudTlr = lastAudTlr;
	}
	public String getLastAudDateTime() {
		return lastAudDateTime;
	}
	public void setLastAudDateTime(String lastAudDateTime) {
		this.lastAudDateTime = lastAudDateTime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMchtEmail() {
		return mchtEmail;
	}
	public void setMchtEmail(String mchtEmail) {
		this.mchtEmail = mchtEmail;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getSyncState() {
		return syncState;
	}
	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}

	

	
}
