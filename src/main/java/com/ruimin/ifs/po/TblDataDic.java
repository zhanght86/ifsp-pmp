/**
 * 
 * Copyright (C), 2016-2016, 上海睿民互联网科技有限公司
 * FileName: AccountTypeService.java
 * Author:   Cheng
 * Date:     2016年7月14日下午4:34:18
 * Description: TODO
 * History: //修改记录
 * <author>      <time>                   <version>    <desc>
 * zhaodk      2016年7月14日下午4:34:18          0.1
 */
package com.ruimin.ifs.po;

import com.ruimin.ifs.framework.core.bean.DataDic;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_data_dic")
public class TblDataDic implements DataDic {
	/* 主键序号 */
	@Id
	private String id;
	/* 数据类型编号 */
	private String dataTypeNo;
	/* 数据编号 */
	private String dataNo;
	/* 数据类型名称 */
	private String dataTypeName;
	/* 数据编号长度 */
	private Integer dataNoLen;
	/* 数据内容 */
	private String dataName;
	/* 是否有上下限标识 */
	private String limitFlag;
	/* 上限 */
	private String highLimit;
	/* 下限 */
	private String lowLimit;
	/* 扩展标识位 */
	private String miscflgs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataTypeNo() {
		return dataTypeNo;
	}

	public void setDataTypeNo(String dataTypeNo) {
		this.dataTypeNo = dataTypeNo;
	}

	public String getDataNo() {
		return dataNo;
	}

	public void setDataNo(String dataNo) {
		this.dataNo = dataNo;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public Integer getDataNoLen() {
		return dataNoLen;
	}

	public void setDataNoLen(Integer dataNoLen) {
		this.dataNoLen = dataNoLen;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getLimitFlag() {
		return limitFlag;
	}

	public void setLimitFlag(String limitFlag) {
		this.limitFlag = limitFlag;
	}

	public String getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getMiscflgs() {
		return miscflgs;
	}

	public void setMiscflgs(String miscflgs) {
		this.miscflgs = miscflgs;
	}

}
