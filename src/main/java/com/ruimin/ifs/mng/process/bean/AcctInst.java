/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.process.bean 
 * FileName: AcctInst.java
 * Author:   Administrator
 * Date:     2017年6月5日 下午4:16:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月5日下午4:16:23                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.mng.process.bean;

import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：政通内部机构号实体类<br> 
 * 版本：1.0 <br>
 * 日期：2017年6月5日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("PBS_ACCT_INST_INFO")
public class AcctInst {
    private String acctInstNo;//开户机构编号
    private String acctInstName;//开户机构名称
    private String dataState;//数据有效状态
    private String crtTlr;//创建柜员
    private String crtDateTime;//创建日期时间
    private String lastUpdTlr;//最近更新柜员
    private String lastUpdDateTime;//最近更新日期时间
    
    /**
     * @return the dataState
     */
    public String getDataState() {
        return dataState;
    }
    /**
     * @param dataState the dataState to set
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
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
     * @return the acctInstNo
     */
    public String getAcctInstNo() {
        return acctInstNo;
    }
    /**
     * @param acctInstNo the acctInstNo to set
     */
    public void setAcctInstNo(String acctInstNo) {
        this.acctInstNo = acctInstNo;
    }
    /**
     * @return the acctInstName
     */
    public String getAcctInstName() {
        return acctInstName;
    }
    /**
     * @param acctInstName the acctInstName to set
     */
    public void setAcctInstName(String acctInstName) {
        this.acctInstName = acctInstName;
    }
    
}
