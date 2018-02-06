/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.bean 
 * FileName: QrsRoutMng.java
 * Author:   LJY
 * Date:     2017年9月25日 下午4:31:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年9月25日下午4:31:00                     1.0                  
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
 * 日期：2017年9月25日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */

@Table("QRS_ROUTE_INFO")
public class QrsRouteInfo {
    @Id
    public String routeId;//路由标识ID
    public String mchtId;//渠道商户号
    public String prodId;//产品ID
    public String acctType;//账户类型
    public String pagyNo;//通道ID
    public String priority;//优先级 1-99  高-低
    public String status;//0-启动，1-关闭
    public String routeDesc;//路由说明
    public String crtTlr;//创建柜员
    public String crtDateTime;//创建日期时间
    public String lastUpdTlr;//最近更新柜员
    public String lastUpdDateTime;//最近更新日期时间
 

    public String getRouteId() {
        return routeId;
    }
 
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getMchtId() {
        return mchtId;
    }

    public void setMchtId(String mchtId) {
        this.mchtId = mchtId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
  
    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getPagyNo() {
        return pagyNo;
    }
 
    public void setPagyNo(String pagyNo) {
        this.pagyNo = pagyNo;
    }

    public String getPriority() {
        return priority;
    }
 
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }
   
    public String getCrtTlr() {
        return crtTlr;
    }
 
    public void setCrtTlr(String crtTlr) {
        this.crtTlr = crtTlr;
    }

    public String getCrtDateTime() {
        return crtDateTime;
    }

    public void setCrtDateTime(String crtDateTime) {
        this.crtDateTime = crtDateTime;
    }
   
    public String getLastUpdTlr() {
        return lastUpdTlr;
    }

    public void setLastUpdTlr(String lastUpdTlr) {
        this.lastUpdTlr = lastUpdTlr;
    }
 
    public String getLastUpdDateTime() {
        return lastUpdDateTime;
    }

    public void setLastUpdDateTime(String lastUpdDateTime) {
        this.lastUpdDateTime = lastUpdDateTime;
    }
    
}
