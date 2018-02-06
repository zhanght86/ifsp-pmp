package com.ruimin.ifs.pmp.txnQuery.process.bean;

/**
 * 支付业务系统订单信息表实体对象
 */
public class PbsOrderInfoVO {
	private String TxnSeqId;
	private String txnDate;
	private String txnTime;
	private String AccessType;
	private String txnTemlType;
	private String PayProduct;
	private String TxnType;
	private String TxnSubType;
	private String mchtId;
	private String mchtName;
	private String SecMerId;
	private String SecMerName;
	private String MerMcc;
	private String BrhId;
	private String MerUserId;
	private String TermId;
	private String termIp;
	private String txnOrderId;
	private String txnOrderFlag;
	private String txnOrderTime;
	private String txnOrderTimeEnd;
	private String txnOrderAmt;
	private String orderBody;
	private String orderDetail;
	private String orderProductId;
	private String frontEndUrl;
	private String backEndUrl;
	private String origTxnSeqId;
	private String bankId;
	private String bankName;
	private String txnAccType;
	private String txnAccNo;
	private String txnHandleCount;
	private String orderGoodsTag;
	private String txnAmt;
	private String txnAmtFee;
	private String txnRefundMode;
	private String txnRefundPan;
	private String srAmtSum;
	private String txnCurrencyCode;
	private String txnState;
	private String txnRespCode;
	private String txnRespMsg;
	private String txnPayTimeEnd;
	private String lastUpdTime;
	private String pagyTxnSsn;
	private String pagyTxnTm;
	private String pagyRespCode;
	private String pagyRespMsg;
	private String settleAmt;
	private String settleDate;
	private String settleCurrencyCode;
	private String tpamOutId;
	private String pagySeqId;
	private String tpamErrCode;
	private String tpamErrMsg;
	private String tpamMsgid;
	private String payTxnAccNo;
    private String activeNo;
    private String activeNm;
    private String favourableAmt;
	private String pointId;
	
	
    
	public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getActiveNo() {
        return activeNo;
    }

    public void setActiveNo(String activeNo) {
        this.activeNo = activeNo;
    }

    public String getActiveNm() {
        return activeNm;
    }

    public void setActiveNm(String activeNm) {
        this.activeNm = activeNm;
    }

    public String getFavourableAmt() {
        return favourableAmt;
    }

    public void setFavourableAmt(String favourableAmt) {
        this.favourableAmt = favourableAmt;
    }

    /**
     * @return the tpamMsgid
     */
    public String getTpamMsgid() {
        return tpamMsgid;
    }

    /**
     * @param tpamMsgid the tpamMsgid to set
     */
    public void setTpamMsgid(String tpamMsgid) {
        this.tpamMsgid = tpamMsgid;
    }

    /**
     * @return the payTxnAccNo
     */
    public String getPayTxnAccNo() {
        return payTxnAccNo;
    }

    /**
     * @param payTxnAccNo the payTxnAccNo to set
     */
    public void setPayTxnAccNo(String payTxnAccNo) {
        this.payTxnAccNo = payTxnAccNo;
    }

    /**
     * @return the pagySeqId
     */
    public String getPagySeqId() {
        return pagySeqId;
    }

    /**
     * @param pagySeqId the pagySeqId to set
     */
    public void setPagySeqId(String pagySeqId) {
        this.pagySeqId = pagySeqId;
    }

    /**
     * @return the tpamOutId
     */
    public String getTpamOutId() {
        return tpamOutId;
    }

    /**
     * @param tpamOutId the tpamOutId to set
     */
    public void setTpamOutId(String tpamOutId) {
        this.tpamOutId = tpamOutId;
    }

    /** 额外附加字段 */
	private String txnAccTypeName;

	/** 额外附加字段结束 */

	public String getTxnSeqId() {
		return TxnSeqId;
	}

	public void setTxnSeqId(String txnSeqId) {
		TxnSeqId = txnSeqId;
	}



	/**
     * @return the txnDate
     */
    public String getTxnDate() {
        return txnDate;
    }

    /**
     * @param txnDate the txnDate to set
     */
    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    /**
     * @return the txnTime
     */
    public String getTxnTime() {
        return txnTime;
    }

    /**
     * @param txnTime the txnTime to set
     */
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getAccessType() {
		return AccessType;
	}

	public void setAccessType(String accessType) {
		AccessType = accessType;
	}



	/**
     * @return the txnTemlType
     */
    public String getTxnTemlType() {
        return txnTemlType;
    }

    /**
     * @param txnTemlType the txnTemlType to set
     */
    public void setTxnTemlType(String txnTemlType) {
        this.txnTemlType = txnTemlType;
    }

    public String getPayProduct() {
		return PayProduct;
	}

	public void setPayProduct(String payProduct) {
		PayProduct = payProduct;
	}

	public String getTxnType() {
		return TxnType;
	}

	public void setTxnType(String txnType) {
		TxnType = txnType;
	}

	public String getTxnSubType() {
		return TxnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		TxnSubType = txnSubType;
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

    public String getSecMerId() {
		return SecMerId;
	}

	public void setSecMerId(String secMerId) {
		SecMerId = secMerId;
	}

	public String getSecMerName() {
		return SecMerName;
	}

	public void setSecMerName(String secMerName) {
		SecMerName = secMerName;
	}

	public String getMerMcc() {
		return MerMcc;
	}

	public void setMerMcc(String merMcc) {
		MerMcc = merMcc;
	}

	public String getBrhId() {
		return BrhId;
	}

	public void setBrhId(String brhId) {
		BrhId = brhId;
	}

	public String getMerUserId() {
		return MerUserId;
	}

	public void setMerUserId(String merUserId) {
		MerUserId = merUserId;
	}

	public String getTermId() {
		return TermId;
	}

	public void setTermId(String termId) {
		TermId = termId;
	}

	public String getTermIp() {
		return termIp;
	}

	public void setTermIp(String termIp) {
		this.termIp = termIp;
	}

	public String getTxnOrderId() {
		return txnOrderId;
	}

	public void setTxnOrderId(String txnOrderId) {
		this.txnOrderId = txnOrderId;
	}

	public String getTxnOrderFlag() {
		return txnOrderFlag;
	}

	public void setTxnOrderFlag(String txnOrderFlag) {
		this.txnOrderFlag = txnOrderFlag;
	}

	public String getTxnOrderTime() {
		return txnOrderTime;
	}

	public void setTxnOrderTime(String txnOrderTime) {
		this.txnOrderTime = txnOrderTime;
	}

	public String getTxnOrderTimeEnd() {
		return txnOrderTimeEnd;
	}

	public void setTxnOrderTimeEnd(String txnOrderTimeEnd) {
		this.txnOrderTimeEnd = txnOrderTimeEnd;
	}

	public String getTxnOrderAmt() {
		return txnOrderAmt;
	}

	public void setTxnOrderAmt(String txnOrderAmt) {
		this.txnOrderAmt = txnOrderAmt;
	}

	public String getOrderBody() {
		return orderBody;
	}

	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getFrontEndUrl() {
		return frontEndUrl;
	}

	public void setFrontEndUrl(String frontEndUrl) {
		this.frontEndUrl = frontEndUrl;
	}

	public String getBackEndUrl() {
		return backEndUrl;
	}

	public void setBackEndUrl(String backEndUrl) {
		this.backEndUrl = backEndUrl;
	}

	public String getOrigTxnSeqId() {
		return origTxnSeqId;
	}

	public void setOrigTxnSeqId(String origTxnSeqId) {
		this.origTxnSeqId = origTxnSeqId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTxnAccType() {
		return txnAccType;
	}

	public void setTxnAccType(String txnAccType) {
		this.txnAccType = txnAccType;
	}

	public String getTxnAccNo() {
		return txnAccNo;
	}

	public void setTxnAccNo(String txnAccNo) {
		this.txnAccNo = txnAccNo;
	}

	public String getTxnHandleCount() {
		return txnHandleCount;
	}

	public void setTxnHandleCount(String txnHandleCount) {
		this.txnHandleCount = txnHandleCount;
	}

	public String getOrderGoodsTag() {
		return orderGoodsTag;
	}

	public void setOrderGoodsTag(String orderGoodsTag) {
		this.orderGoodsTag = orderGoodsTag;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getTxnAmtFee() {
		return txnAmtFee;
	}

	public void setTxnAmtFee(String txnAmtFee) {
		this.txnAmtFee = txnAmtFee;
	}

	public String getTxnRefundMode() {
		return txnRefundMode;
	}

	public void setTxnRefundMode(String txnRefundMode) {
		this.txnRefundMode = txnRefundMode;
	}

	public String getTxnRefundPan() {
		return txnRefundPan;
	}

	public void setTxnRefundPan(String txnRefundPan) {
		this.txnRefundPan = txnRefundPan;
	}

	public String getSrAmtSum() {
		return srAmtSum;
	}

	public void setSrAmtSum(String srAmtSum) {
		this.srAmtSum = srAmtSum;
	}

	public String getTxnCurrencyCode() {
		return txnCurrencyCode;
	}

	public void setTxnCurrencyCode(String txnCurrencyCode) {
		this.txnCurrencyCode = txnCurrencyCode;
	}

	public String getTxnState() {
		return txnState;
	}

	public void setTxnState(String txnState) {
		this.txnState = txnState;
	}

	public String getTxnRespCode() {
		return txnRespCode;
	}

	public void setTxnRespCode(String txnRespCode) {
		this.txnRespCode = txnRespCode;
	}

	public String getTxnRespMsg() {
		return txnRespMsg;
	}

	public void setTxnRespMsg(String txnRespMsg) {
		this.txnRespMsg = txnRespMsg;
	}

	public String getTxnPayTimeEnd() {
		return txnPayTimeEnd;
	}

	public void setTxnPayTimeEnd(String txnPayTimeEnd) {
		this.txnPayTimeEnd = txnPayTimeEnd;
	}

	public String getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public String getPagyTxnSsn() {
		return pagyTxnSsn;
	}

	public void setPagyTxnSsn(String pagyTxnSsn) {
		this.pagyTxnSsn = pagyTxnSsn;
	}

	public String getPagyTxnTm() {
		return pagyTxnTm;
	}

	public void setPagyTxnTm(String pagyTxnTm) {
		this.pagyTxnTm = pagyTxnTm;
	}

	public String getPagyRespCode() {
		return pagyRespCode;
	}

	public void setPagyRespCode(String pagyRespCode) {
		this.pagyRespCode = pagyRespCode;
	}

	public String getPagyRespMsg() {
		return pagyRespMsg;
	}

	public void setPagyRespMsg(String pagyRespMsg) {
		this.pagyRespMsg = pagyRespMsg;
	}

	public String getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}

	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}

	public String getTxnAccTypeName() {
		return txnAccTypeName;
	}

	public void setTxnAccTypeName(String txnAccTypeName) {
		this.txnAccTypeName = txnAccTypeName;
	}

    /**
     * @return the tpamErrCode
     */
    public String getTpamErrCode() {
        return tpamErrCode;
    }

    /**
     * @param tpamErrCode the tpamErrCode to set
     */
    public void setTpamErrCode(String tpamErrCode) {
        this.tpamErrCode = tpamErrCode;
    }

    /**
     * @return the tpamErrMsg
     */
    public String getTpamErrMsg() {
        return tpamErrMsg;
    }

    /**
     * @param tpamErrMsg the tpamErrMsg to set
     */
    public void setTpamErrMsg(String tpamErrMsg) {
        this.tpamErrMsg = tpamErrMsg;
    }
	
	
	
	

}
