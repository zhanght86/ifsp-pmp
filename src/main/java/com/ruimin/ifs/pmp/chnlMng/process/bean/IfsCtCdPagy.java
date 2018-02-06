/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.bean 
 * FileName: IfsCtCdPagy.java
 * Author:   Administrator
 * Date:     2017年10月12日 下午6:44:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年10月12日下午6:44:29                     1.0                  
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
 * 日期：2017年10月12日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Table("ifs_ct_cd_pagy")
public class IfsCtCdPagy {
    private String ctName;
    @Id
    private String pagyNo;
    @Id
    private String ctCode;
    private String ctFlg;
    /**
     * @return the ctName
     */
    public String getCtName() {
        return ctName;
    }
    /**
     * @param ctName the ctName to set
     */
    public void setCtName(String ctName) {
        this.ctName = ctName;
    }
    /**
     * @return the pagyNo
     */
    public String getPagyNo() {
        return pagyNo;
    }
    /**
     * @param pagyNo the pagyNo to set
     */
    public void setPagyNo(String pagyNo) {
        this.pagyNo = pagyNo;
    }
    /**
     * @return the ctCode
     */
    public String getCtCode() {
        return ctCode;
    }
    /**
     * @param ctCode the ctCode to set
     */
    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }
    /**
     * @return the ctFlg
     */
    public String getCtFlg() {
        return ctFlg;
    }
    /**
     * @param ctFlg the ctFlg to set
     */
    public void setCtFlg(String ctFlg) {
        this.ctFlg = ctFlg;
    }
    

}
