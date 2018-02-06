package com.ruimin.ifs.pmp.report.process.bean;

import java.io.Serializable;

public class BthAcctNoParam implements Serializable {
    private String paygNo;

    private String acctNoFlag;

    private String acctDesc;

    private String acctNo;

    private String operNo;

    private String ctrTime;

    private String lastUpdateTime;

    private String addField;

    private String addField1;

    public String getPaygNo() {
        return paygNo;
    }

    public void setPaygNo(String paygNo) {
        this.paygNo = paygNo == null ? null : paygNo.trim();
    }

    public String getAcctNoFlag() {
        return acctNoFlag;
    }

    public void setAcctNoFlag(String acctNoFlag) {
        this.acctNoFlag = acctNoFlag == null ? null : acctNoFlag.trim();
    }

    public String getAcctDesc() {
        return acctDesc;
    }

    public void setAcctDesc(String acctDesc) {
        this.acctDesc = acctDesc == null ? null : acctDesc.trim();
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo == null ? null : operNo.trim();
    }

    public String getCtrTime() {
        return ctrTime;
    }

    public void setCtrTime(String ctrTime) {
        this.ctrTime = ctrTime == null ? null : ctrTime.trim();
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime == null ? null : lastUpdateTime.trim();
    }

    public String getAddField() {
        return addField;
    }

    public void setAddField(String addField) {
        this.addField = addField == null ? null : addField.trim();
    }

    public String getAddField1() {
        return addField1;
    }

    public void setAddField1(String addField1) {
        this.addField1 = addField1 == null ? null : addField1.trim();
    }
}