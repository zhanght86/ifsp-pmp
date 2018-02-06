package com.ruimin.ifs.pmp.mchtMng.process.bean;

public class MchtContractVO1 extends MchtContractVO {
	private String mchtSimpleName;
	private String picId1;// 图片编号[1]
	private String picId2;// 图片编号[2]
	private String picId3;// 图片编号[3]
	private String picId4;// 图片编号[4]
	private String picId5;// 图片编号[5]
	private String mchtType;// 商户类型
	// 限额交易新增加三个字段
	private String limitOne; // 单笔支付限额
	private String limitDay; // 单日限额
	private String dataState; // 数据的有效状态
	private String chlSysNo; // 进件渠道号
	
    public String getChlSysNo() {
        return chlSysNo;
    }

    public void setChlSysNo(String chlSysNo) {
        this.chlSysNo = chlSysNo;
    }

    public String getMchtSimpleName() {
		return mchtSimpleName;
	}

	public void setMchtSimpleName(String mchtSimpleName) {
		this.mchtSimpleName = mchtSimpleName;
	}

	public String getPicId1() {
		return picId1;
	}

	public void setPicId1(String picId1) {
		this.picId1 = picId1;
	}

	public String getPicId2() {
		return picId2;
	}

	public void setPicId2(String picId2) {
		this.picId2 = picId2;
	}

	public String getPicId3() {
		return picId3;
	}

	public void setPicId3(String picId3) {
		this.picId3 = picId3;
	}

	public String getPicId4() {
		return picId4;
	}

	public void setPicId4(String picId4) {
		this.picId4 = picId4;
	}

	public String getPicId5() {
		return picId5;
	}

	public void setPicId5(String picId5) {
		this.picId5 = picId5;
	}

	public String getMchtType() {
		return mchtType;
	}

	public void setMchtType(String mchtType) {
		this.mchtType = mchtType;
	}

	public String getLimitOne() {
		return limitOne;
	}

	public void setLimitOne(String limitOne) {
		this.limitOne = limitOne;
	}

	public String getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(String limitDay) {
		this.limitDay = limitDay;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

}
