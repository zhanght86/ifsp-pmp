package com.ruimin.ifs.pmp.chnlMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("weixinberl")
public class WeixinBERL {
    @Id
    private String wxberlId;
    private String wxberlName;
    public String getWxberlId() {
        return wxberlId;
    }
    public void setWxberlId(String wxberlId) {
        this.wxberlId = wxberlId;
    }
    public String getWxberlName() {
        return wxberlName;
    }
    public void setWxberlName(String wxberlName) {
        this.wxberlName = wxberlName;
    }
    
    
}
