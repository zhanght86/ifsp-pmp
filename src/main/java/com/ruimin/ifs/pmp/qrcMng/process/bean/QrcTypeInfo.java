package com.ruimin.ifs.pmp.qrcMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 二维码类型信息管理 名称：<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月31日 <br>
 * 作者：Chenggx <br>
 * 说明：<br>
 */
@Table("QRC_TYPE_INFO")
public class QrcTypeInfo {

	@Id
	private String qrcTypeNo; // 二维码类型编号
	private String qrcTypeName; // 二维码类型名称
	private String unitsType; // 有效期单位类型
	private String unitsValues; // 有效期单位值
	private String qrcAmount; // 生成数量
	private String qrcTypeSat; // 二维码状态 00：启用；99：停用
	private String crtUsr; // 创建柜员
	private String crtTm; // 创建日期时间
	private String updUsr; // 最近更新柜员
	private String updTm; // 最近更新日期时间

	public String getQrcTypeNo() {
		return qrcTypeNo;
	}

	public void setQrcTypeNo(String qrcTypeNo) {
		this.qrcTypeNo = qrcTypeNo;
	}

	public String getQrcTypeName() {
		return qrcTypeName;
	}

	public void setQrcTypeName(String qrcTypeName) {
		this.qrcTypeName = qrcTypeName;
	}

	public String getUnitsType() {
		return unitsType;
	}

	public void setUnitsType(String unitsType) {
		this.unitsType = unitsType;
	}

	public String getUnitsValues() {
		return unitsValues;
	}

	public void setUnitsValues(String unitsValues) {
		this.unitsValues = unitsValues;
	}

	public String getQrcTypeSat() {
		return qrcTypeSat;
	}

	public void setQrcTypeSat(String qrcTypeSat) {
		this.qrcTypeSat = qrcTypeSat;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getCrtTm() {
		return crtTm;
	}

	public void setCrtTm(String crtTm) {
		this.crtTm = crtTm;
	}

	public String getUpdUsr() {
		return updUsr;
	}

	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

	public String getUpdTm() {
		return updTm;
	}

	public void setUpdTm(String updTm) {
		this.updTm = updTm;
	}

	public String getQrcAmount() {
		return qrcAmount;
	}

	public void setQrcAmount(String qrcAmount) {
		this.qrcAmount = qrcAmount;
	}

}
