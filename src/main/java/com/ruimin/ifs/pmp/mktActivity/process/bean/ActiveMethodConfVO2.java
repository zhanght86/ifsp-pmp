package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("TBL_ACTIVE_METHOD_CONF")
public class ActiveMethodConfVO2 {
    @Id
	private String activeNo;
	private String mchtLevel;
	@Id
	private String gpSeq;
	private String methodNo;
	private String updateTime;
	private String updateOpr;
	private String dataState;
	@Id
	private String confSeq;
	
	public String getConfSeq() {
		return confSeq;
	}
	public void setConfSeq(String confSeq) {
		this.confSeq = confSeq;
	}
	public String getDataState() {
			return dataState;
		}
		public void setDataState(String dataState) {
			this.dataState = dataState;
		}

	public String getActiveNo() {
		return activeNo;
	}

	public void setActiveNo(String activeNo) {
		this.activeNo = activeNo;
	}

	public String getMchtLevel() {
		return mchtLevel;
	}

	public void setMchtLevel(String mchtLevel) {
		this.mchtLevel = mchtLevel;
	}

	public String getGpSeq() {
		return gpSeq;
	}

	public void setGpSeq(String gpSeq) {
		this.gpSeq = gpSeq;
	}

	public String getMethodNo() {
		return methodNo;
	}

	public void setMethodNo(String methodNo) {
		this.methodNo = methodNo;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateOpr() {
		return updateOpr;
	}

	public void setUpdateOpr(String updateOpr) {
		this.updateOpr = updateOpr;
	}

}
