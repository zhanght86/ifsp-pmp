/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpMachInvenBind.java
 * Author:   xw
 * Date:     2015年8月26日 下午2:28:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.ruimin.ifs.po;

import java.io.Serializable;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author xw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Table("IMP_MACH_INVEN_BIND")
public class ImpMachInvenBind implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String termId;
	private String mchtId;
	private String padIdId;
	private String crtTlr;
	private String crtDateTime;
	private String lastUpdTlr;
	private String lastUpdDateTime;

	/**
	 * @return the termId
	 */
	public String getTermId() {
		return termId;
	}

	/**
	 * @param termId
	 *            the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}

	/**
	 * @return the mchtId
	 */
	public String getMchtId() {
		return mchtId;
	}

	/**
	 * @param mchtId
	 *            the mchtId to set
	 */
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	/**
	 * @return the padIdId
	 */
	public String getPadIdId() {
		return padIdId;
	}

	/**
	 * @param padIdId
	 *            the padIdId to set
	 */
	public void setPadIdId(String padIdId) {
		this.padIdId = padIdId;
	}

	/**
	 * @return the crtTlr
	 */
	public String getCrtTlr() {
		return crtTlr;
	}

	/**
	 * @param crtTlr
	 *            the crtTlr to set
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
	 * @param crtDateTime
	 *            the crtDateTime to set
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
	 * @param lastUpdTlr
	 *            the lastUpdTlr to set
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
	 * @param lastUpdDateTime
	 *            the lastUpdDateTime to set
	 */
	public void setLastUpdDateTime(String lastUpdDateTime) {
		this.lastUpdDateTime = lastUpdDateTime;
	}

	/**
	 * @param termId
	 * @param mchtId
	 * @param padIdId
	 * @param crtTlr
	 * @param crtDateTime
	 * @param lastUpdTlr
	 * @param lastUpdDateTime
	 */
	public ImpMachInvenBind(String termId, String mchtId, String padIdId, String crtTlr, String crtDateTime,
			String lastUpdTlr, String lastUpdDateTime) {
		super();
		this.termId = termId;
		this.mchtId = mchtId;
		this.padIdId = padIdId;
		this.crtTlr = crtTlr;
		this.crtDateTime = crtDateTime;
		this.lastUpdTlr = lastUpdTlr;
		this.lastUpdDateTime = lastUpdDateTime;
	}

	/**
	 * 
	 */
	public ImpMachInvenBind() {
		super();
	}

}
