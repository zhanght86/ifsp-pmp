/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: ImpTermBind.java
 * Author:   xw
 * Date:     2015年8月26日 下午7:28:47
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
@Table("IMP_TERM_BIND")
public class ImpTermBind implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String termNo;
	private String imei;

	/**
	 * @return the termNo
	 */
	public String getTermNo() {
		return termNo;
	}

	/**
	 * @param termNo
	 *            the termNo to set
	 */
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @param termNo
	 * @param imei
	 */
	public ImpTermBind(String termNo, String imei) {
		super();
		this.termNo = termNo;
		this.imei = imei;
	}

	/**
	 * 
	 */
	public ImpTermBind() {
		super();
	}

}
