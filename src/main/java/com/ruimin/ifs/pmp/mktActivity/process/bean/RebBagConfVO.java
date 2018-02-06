/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mktActivity.process.bean 
 * FileName: RebBagConfVO.java
 * Author:   LJY
 * Date:     2017年12月25日 下午5:55:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月25日下午5:55:33                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mktActivity.process.bean;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月25日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class RebBagConfVO extends TblActiveRedbagConfTmp{
   
    private String sumConSume;
    private String sumCount;
    /**
     * @return the sumConSume
     */
    public String getSumConSume() {
        return sumConSume;
    }
    /**
     * @param sumConSume the sumConSume to set
     */
    public void setSumConSume(String sumConSume) {
        this.sumConSume = sumConSume;
    }
    /**
     * @return the sumCount
     */
    public String getSumCount() {
        return sumCount;
    }
    /**
     * @param sumCount the sumCount to set
     */
    public void setSumCount(String sumCount) {
        this.sumCount = sumCount;
    }
    
}
