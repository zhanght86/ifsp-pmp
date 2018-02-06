/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.po 
 * FileName: PaypTermInf.java
 * Author:   wangyl
 * Date:     2016年8月5日 上午9:32:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月5日上午9:32:42                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月5日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
@Table("PAYP_TERM_KEY")
public class PaypTermKey {
	private String mchtId;// 商户id
	// 终端号
	private String termId;
	@Id
	private String keyIndex;// 秘钥索引

	public String getMchtId() {
		return mchtId;
	}

	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getTermZmkName() {
		return termZmkName;
	}

	public void setTermZmkName(String termZmkName) {
		this.termZmkName = termZmkName;
	}

	public String getTermZmk() {
		return termZmk;
	}

	public void setTermZmk(String termZmk) {
		this.termZmk = termZmk;
	}

	public String getTermZmkChk() {
		return termZmkChk;
	}

	public void setTermZmkChk(String termZmkChk) {
		this.termZmkChk = termZmkChk;
	}

	public String getTermZakName() {
		return termZakName;
	}

	public void setTermZakName(String termZakName) {
		this.termZakName = termZakName;
	}

	public String getTermZpkName() {
		return termZpkName;
	}

	public void setTermZpkName(String termZpkName) {
		this.termZpkName = termZpkName;
	}

	public String getTermZrkName() {
		return termZrkName;
	}

	public void setTermZrkName(String termZrkName) {
		this.termZrkName = termZrkName;
	}

	public String getKeySt() {
		return keySt;
	}

	public void setKeySt(String keySt) {
		this.keySt = keySt;
	}

	public String getTmkSt() {
		return tmkSt;
	}

	public void setTmkSt(String tmkSt) {
		this.tmkSt = tmkSt;
	}

	public String getCrtDateTime() {
		return crtDateTime;
	}

	public void setCrtDateTime(String crtDateTime) {
		this.crtDateTime = crtDateTime;
	}

	public String getCrtOpr() {
		return crtOpr;
	}

	public void setCrtOpr(String crtOpr) {
		this.crtOpr = crtOpr;
	}

	public String getUpdDateTime() {
		return updDateTime;
	}

	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}

	public String getUpdOpr() {
		return updOpr;
	}

	public void setUpdOpr(String updOpr) {
		this.updOpr = updOpr;
	}

	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}

	private String termZmkName;// 终端主秘钥名称
	private String termZmk;// 终端主秘钥秘钥
	private String termZmkChk;// 主秘钥校验值
	private String termZakName;// MACKEY名称
	private String termZpkName;// PINKEY名称
	private String termZrkName;// TERKKEY名称
	private String keySt;// 秘钥申请状态
	private String tmkSt;// 有效无效
	private String crtDateTime;// 创建时间
	private String crtOpr;// 创建操作员
	private String updDateTime;// 更新日期时间
	private String updOpr;// 更新操作员
	private String keyFlag;// 00国际秘钥 01国密秘钥

}
