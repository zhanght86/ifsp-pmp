/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.bean 
 * FileName: PagyIndirectPayCertCfg.java
 * Author:   Administrator
 * Date:     2017年9月13日 下午2:42:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年9月13日下午2:42:08                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年9月13日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("pagy_indirect_pay_cert_cfg")
public class PagyIndirectPayCertCfg {
    @Id
    /** 变量 certifiId . */
    private String certifiId;
    /** 变量 certifiPasswd . */
    private String certifiPasswd;
    /** 变量 certifiPath . */
    private String certifiPath;
    @Id
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
    private String certifiCode;
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCertifiCode() {
        return certifiCode;
    }

    public void setCertifiCode(String certifiCode) {
        this.certifiCode = certifiCode;
    }

}

