package com.ruimin.ifs.pmp.qrcMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 渠道二维码权限管理 名称：<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月31日 <br>
 * 作者：Chenggx <br>
 * 说明：<br>
 */
@Table("QRC_CHANNEL_AUTHORITY_INFO")
public class QrcChannelAuthorityInfo {

	@Id
	private String chlAuNo; // 权限编号
	private String chlNo; // 渠道编号
	private String qrcTypeNo; // 二维码类型编号
	private String crtUsr; // 创建柜员
	private String crtTm; // 创建日期时间
	private String updUsr; // 最近更新柜员
	private String updTm; // 最近更新日期时间

	public String getChlAuNo() {
		return chlAuNo;
	}

	public void setChlAuNo(String chlAuNo) {
		this.chlAuNo = chlAuNo;
	}

	public String getChlNo() {
		return chlNo;
	}

	public void setChlNo(String chlNo) {
		this.chlNo = chlNo;
	}

	public String getQrcTypeNo() {
		return qrcTypeNo;
	}

	public void setQrcTypeNo(String qrcTypeNo) {
		this.qrcTypeNo = qrcTypeNo;
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

}
