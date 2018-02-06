/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.process.bean 
 * FileName: MchtAgent.java
 * Author:   Administrator
 * Date:     2017年6月5日 下午4:58:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月5日下午4:58:03                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.mng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：代理机构实体类<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年6月5日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("PBS_AGENT_INFO")
public class MchtAgent {
    @Id
    private String agentId;//代理商编号
    private String agentName;//代理商名称
    private String setlAcctName;//结算账户户主名
    private String setlAcctNo;//结算账户帐号
    private String setlAcctInstitute;//开户机构
    private String setlAcctBankNo;//开户行行号
    private String setlAcctBankName;//开户行行名
    private String setlAcctAreaCode;//开户行区划代码
    private String contactsName;//联系人姓名
    private String contactsPhone;//联系电话
    private String contactsAddr;//联系地址
    private String zipNo;//邮编
    private String profitShareType;//分润方式
    private String profitShareScale;//分润比例
    private String crtTlr;//创建柜员
    private String crtDateTime;//创建日期时间
    private String lastUpdTlr;//最近更新柜员
    private String lastUpdDateTime;//最近更新日期时间
    
    
    
    /**
     * @return the setlAcctInstitute
     */
    public String getSetlAcctInstitute() {
        return setlAcctInstitute;
    }
    /**
     * @param setlAcctInstitute the setlAcctInstitute to set
     */
    public void setSetlAcctInstitute(String setlAcctInstitute) {
        this.setlAcctInstitute = setlAcctInstitute;
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
    /**
     * @return the agentName
     */
    public String getAgentName() {
        return agentName;
    }
    /**
     * @param agentName the agentName to set
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    /**
     * @return the setlAcctName
     */
    public String getSetlAcctName() {
        return setlAcctName;
    }
    /**
     * @param setlAcctName the setlAcctName to set
     */
    public void setSetlAcctName(String setlAcctName) {
        this.setlAcctName = setlAcctName;
    }
    /**
     * @return the setlAcctNo
     */
    public String getSetlAcctNo() {
        return setlAcctNo;
    }
    /**
     * @param setlAcctNo the setlAcctNo to set
     */
    public void setSetlAcctNo(String setlAcctNo) {
        this.setlAcctNo = setlAcctNo;
    }
    /**
     * @return the contactsName
     */
    public String getContactsName() {
        return contactsName;
    }
    /**
     * @param contactsName the contactsName to set
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }
    /**
     * @return the contactsPhone
     */
    public String getContactsPhone() {
        return contactsPhone;
    }
    /**
     * @param contactsPhone the contactsPhone to set
     */
    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }
    /**
     * @return the contactsAddr
     */
    public String getContactsAddr() {
        return contactsAddr;
    }
    /**
     * @param contactsAddr the contactsAddr to set
     */
    public void setContactsAddr(String contactsAddr) {
        this.contactsAddr = contactsAddr;
    }
    /**
     * @return the zipNo
     */
    public String getZipNo() {
        return zipNo;
    }
    /**
     * @param zipNo the zipNo to set
     */
    public void setZipNo(String zipNo) {
        this.zipNo = zipNo;
    }
    /**
     * @return the profitShareType
     */
    public String getProfitShareType() {
        return profitShareType;
    }
    /**
     * @param profitShareType the profitShareType to set
     */
    public void setProfitShareType(String profitShareType) {
        this.profitShareType = profitShareType;
    }
    /**
     * @return the profitShareScale
     */
    public String getProfitShareScale() {
        return profitShareScale;
    }
    /**
     * @param profitShareScale the profitShareScale to set
     */
    public void setProfitShareScale(String profitShareScale) {
        this.profitShareScale = profitShareScale;
    }
	public String getSetlAcctBankNo() {
		return setlAcctBankNo;
	}
	public void setSetlAcctBankNo(String setlAcctBankNo) {
		this.setlAcctBankNo = setlAcctBankNo;
	}
	public String getSetlAcctBankName() {
		return setlAcctBankName;
	}
	public void setSetlAcctBankName(String setlAcctBankName) {
		this.setlAcctBankName = setlAcctBankName;
	}
	public String getSetlAcctAreaCode() {
		return setlAcctAreaCode;
	}
	public void setSetlAcctAreaCode(String setlAcctAreaCode) {
		this.setlAcctAreaCode = setlAcctAreaCode;
	}
    
}
