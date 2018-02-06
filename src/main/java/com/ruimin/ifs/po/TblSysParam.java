package com.ruimin.ifs.po;

import com.ruimin.ifs.framework.core.bean.SysParam;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_sys_param")
public class TblSysParam implements SysParam {
	@Id
	private String paramId;
	@Id
	private String magicId;
	private String paramStartTm;
	private String paramEndTm;
	private String paramChangFlag;
	private String paramValueTx;
	private String lastUpdTlr;
	private String lastUpdFunc;
	private String lastUpdDate;
	private String desc0;
	private String st;
	// private String isLock;
	// private String isDel;
	private boolean lock;
	private boolean del;

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getMagicId() {
		return magicId;
	}

	public void setMagicId(String magicId) {
		this.magicId = magicId;
	}

	public String getParamStartTm() {
		return paramStartTm;
	}

	public void setParamStartTm(String paramStartTm) {
		this.paramStartTm = paramStartTm;
	}

	public String getParamEndTm() {
		return paramEndTm;
	}

	public void setParamEndTm(String paramEndTm) {
		this.paramEndTm = paramEndTm;
	}

	public String getParamChangFlag() {
		return paramChangFlag;
	}

	public void setParamChangFlag(String paramChangFlag) {
		this.paramChangFlag = paramChangFlag;
	}

	public String getParamValueTx() {
		return paramValueTx;
	}

	public void setParamValueTx(String paramValueTx) {
		this.paramValueTx = paramValueTx;
	}

	public String getLastUpdTlr() {
		return lastUpdTlr;
	}

	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}

	public String getLastUpdFunc() {
		return lastUpdFunc;
	}

	public void setLastUpdFunc(String lastUpdFunc) {
		this.lastUpdFunc = lastUpdFunc;
	}

	public String getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getDesc0() {
		return desc0;
	}

	public void setDesc0(String desc0) {
		this.desc0 = desc0;
	}

	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}

	/**
	 * @param st
	 *            the st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}

	// /**
	// * @return the isLock
	// */
	// public String getIsLock() {
	// return isLock;
	// }
	//
	// /**
	// * @param isLock the isLock to set
	// */
	// public void setIsLock(String isLock) {
	// this.isLock = isLock;
	// }
	//
	// /**
	// * @return the isDel
	// */
	// public String getIsDel() {
	// return isDel;
	// }
	//
	// /**
	// * @param isDel the isDel to set
	// */
	// public void setIsDel(String isDel) {
	// this.isDel = isDel;
	// }

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	@Override
	public String group() {
		return this.paramId;
	}

	@Override
	public String key() {
		return this.magicId;
	}

	@Override
	public String value() {
		return this.paramValueTx;
	}

}
