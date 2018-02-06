package com.ruimin.ifs.pmp.mchtAppMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
@Table("MSS_AD_BASE_INFO")
public class AdBaseInfo {
	@Id
	private String adId;
	private String adPst;
	private String adInfo;
	private String picPst;
	private String adStat;
	private String picId;
	private String picUrl;
    public String getAdId() {
        return adId;
    }
    public void setAdId(String adId) {
        this.adId = adId;
    }
    public String getAdPst() {
        return adPst;
    }
    public void setAdPst(String adPst) {
        this.adPst = adPst;
    }
    public String getAdInfo() {
        return adInfo;
    }
    public void setAdInfo(String adInfo) {
        this.adInfo = adInfo;
    }
    public String getPicPst() {
        return picPst;
    }
    public void setPicPst(String picPst) {
        this.picPst = picPst;
    }
    public String getAdStat() {
        return adStat;
    }
    public void setAdStat(String adStat) {
        this.adStat = adStat;
    }
    public String getPicId() {
        return picId;
    }
    public void setPicId(String picId) {
        this.picId = picId;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
	
	
	
	
}
