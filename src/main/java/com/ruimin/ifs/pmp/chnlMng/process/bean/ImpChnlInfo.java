/* Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpChnlInfo.java
 * Author:   GH
 * Date:     2015年8月12日 下午12:17:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.chnlMng.process.bean;

import java.io.Serializable;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈通道信息〉<br> 
 *  
 * @author GH
 */
@Table("IMP_CHNL_INFO")
public class ImpChnlInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	/**
     * 通道编号
     */
    @Id
    private String chnlNo;
    /**
     * 通道名称
     */
	private String chnlName;
    /**
     * 通道类型
     */	
	private String chnlTp;
	/**
     * 通道状态
     */
	private String chnlStat;
	/**
     * 备注
     */
	private String remark;
	
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;
	
	
	/**
	 * @return the chnlNo
	 */
	public String getChnlNo() {
		return chnlNo;
	}
	/**
	 * @param chnlNo the chnlNo to set
	 */
	public void setChnlNo(String chnlNo) {
		this.chnlNo = chnlNo;
	}
	/**
	 * @return the chnlName
	 */
	public String getChnlName() {
		return chnlName;
	}
	/**
	 * @param chnlName the chnlName to set
	 */
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}
	/**
	 * @return the chnlTp
	 */
	public String getChnlTp() {
		return chnlTp;
	}
	/**
	 * @param chnlTp the chnlTp to set
	 */
	public void setChnlTp(String chnlTp) {
		this.chnlTp = chnlTp;
	}

	/**
	 * @return the chnlStat
	 */
	public String getChnlStat() {
		return chnlStat;
	}
	/**
	 * @param chnlStat the chnlStat to set
	 */
	public void setChnlStat(String chnlStat) {
		this.chnlStat = chnlStat;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}
	/**
	 * @param crtTlr the crtTlr to set
	 */
	public void setCrtTlr(String crtTlr) {
		this.crtTlr = crtTlr;
	}
	/**
	 * @return the crtDateTime
	 */
	public String getCrtDateTime() {
		return crtDateTime;
	}
	/**
	 * @param crtDateTime the crtDateTime to set
	 */
	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}
	/**
	 * @return the lastUpdTlr
	 */
	public String getLastUpdTlr() {
		return lastUpdTlr;
	}
	/**
	 * @param lastUpdTlr the lastUpdTlr to set
	 */
	public void setLastUpdTlr(String lastUpdTlr) {
		this.lastUpdTlr = lastUpdTlr;
	}
	/**
	 * @return the lastUpdDateTime
	 */
	public String getLastUpdDateTime() {
		return lastUpdDateTime;
	}
	/**
	 * @param lastUpdDateTime the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}
	
	
}
