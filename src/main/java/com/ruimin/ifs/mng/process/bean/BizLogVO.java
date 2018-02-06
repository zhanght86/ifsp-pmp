package com.ruimin.ifs.mng.process.bean;

public class BizLogVO {
	private String id;
	private String txnDate;
	private String txnStartTime;
	private String txnEndTime;
	private Long txnRunTime;
	private String brCode;
	private String oprcode;
	private String ipAdr;
	private String funcId;
	private String oprTxnCd;
	private String txnBizLog1;
	private String txnBizLog2;
	private String txnStatus;
	private String txnFailLog;
	private String tlrName;
	private String tlrno;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnStartTime() {
		return txnStartTime.substring(0, 2) + ":" + txnStartTime.substring(2, 4) + ":" + txnStartTime.substring(4);
	}

	public void setTxnStartTime(String txnStartTime) {
		this.txnStartTime = txnStartTime;
	}

	public String getTxnEndTime() {
		return txnEndTime.substring(0, 2) + ":" + txnEndTime.substring(2, 4) + ":" + txnEndTime.substring(4);
	}

	public void setTxnEndTime(String txnEndTime) {
		this.txnEndTime = txnEndTime;
	}

	public Long getTxnRunTime() {
		return txnRunTime;
	}

	public void setTxnRunTime(Long txnRunTime) {
		this.txnRunTime = txnRunTime;
	}

	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}

	public String getOprcode() {
		return oprcode;
	}

	public void setOprcode(String oprcode) {
		this.oprcode = oprcode;
	}

	public String getIpAdr() {
		return ipAdr;
	}

	public void setIpAdr(String ipAdr) {
		this.ipAdr = ipAdr;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getOprTxnCd() {
		return oprTxnCd;
	}

	public void setOprTxnCd(String oprTxnCd) {
		this.oprTxnCd = oprTxnCd;
	}

	public String getTxnBizLog1() {
		return txnBizLog1;
	}

	public void setTxnBizLog1(String txnBizLog1) {
		this.txnBizLog1 = txnBizLog1;
	}

	public String getTxnBizLog2() {
		return txnBizLog2;
	}

	public void setTxnBizLog2(String txnBizLog2) {
		this.txnBizLog2 = txnBizLog2;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnFailLog() {
		return txnFailLog;
	}

	public void setTxnFailLog(String txnFailLog) {
		this.txnFailLog = txnFailLog;
	}

	public String getTlrName() {
		return tlrName;
	}

	public void setTlrName(String tlrName) {
		this.tlrName = tlrName;
	}

	public String getTlrno() {
		return tlrno;
	}

	public void setTlrno(String tlrno) {
		this.tlrno = tlrno;
	}

	public BizLogVO(String id, String txnDate, String txnStartTime, String txnEndTime, Long txnRunTime, String brCode,
			String oprcode, String ipAdr, String funcId, String oprTxnCd, String txnBizLog1, String txnBizLog2,
			String txnStatus, String txnFailLog, String tlrName, String tlrno) {
		super();
		this.id = id;
		this.txnDate = txnDate;
		this.txnStartTime = txnStartTime;
		this.txnEndTime = txnEndTime;
		this.txnRunTime = txnRunTime;
		this.brCode = brCode;
		this.oprcode = oprcode;
		this.ipAdr = ipAdr;
		this.funcId = funcId;
		this.oprTxnCd = oprTxnCd;
		this.txnBizLog1 = txnBizLog1;
		this.txnBizLog2 = txnBizLog2;
		this.txnStatus = txnStatus;
		this.txnFailLog = txnFailLog;
		this.tlrName = tlrName;
		this.tlrno = tlrno;
	}

	public BizLogVO() {
	}

}
