/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.baseParaMng.process.bean 
 * FileName: BankBin.java
 * Author:   chenqilei
 * Date:     2016年7月20日 上午9:00:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月20日上午9:00:38                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.baseParaMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月20日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@Table("PBS_BANK_BIN_INFO")
public class BankBin {
	@Id
	private String seqNo; // 序号
	private String issueOrgId; // 发卡机构编号
	private String issueName; // 发卡行名称
	private String cardTypeLen; // 卡类型长度
	private String cardType; // 卡类型
	private String cardNoLen; // 卡号长度
	private String cardBinNo; // 卡BIN号
	private String cupFlag; // 是否银联品牌卡
	private String track2CardNoLen; // 二磁卡号长度
	private String track2CardNoOffset; // 二磁卡号偏移量
	private String track2CardBinOffset; // 二磁中卡BIN偏移量
	private String track3CardNoLen; // 三磁卡号长度
	private String track3CardNoOffset; // 三磁卡号偏移量
	private String track3CardBinOffset; // 三磁卡BIN偏移量
	private String cardBinLen; // 卡BIN长度
	private String cardBinStart; // 卡BIN起始号
	private String cardBinEnd; // 卡BIN结束号
	private String dataSource; // 数据来源
	private String dataState; // 数据有效状态
	private String crtTlr; // 创建柜员
	private String crtDateTime; // 创建日期时间
	private String lastUpdTlr; // 最近更新柜员
	private String lastUpdDateTime; // 最近更新日期时间
	private String internalAcctType; // 内部账户类型编号

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getIssueOrgId() {
		return issueOrgId;
	}

	public void setIssueOrgId(String issueOrgId) {
		this.issueOrgId = issueOrgId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getCardTypeLen() {
		return cardTypeLen;
	}

	public void setCardTypeLen(String cardTypeLen) {
		this.cardTypeLen = cardTypeLen;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNoLen() {
		return cardNoLen;
	}

	public void setCardNoLen(String cardNoLen) {
		this.cardNoLen = cardNoLen;
	}

	public String getCardBinNo() {
		return cardBinNo;
	}

	public void setCardBinNo(String cardBinNo) {
		this.cardBinNo = cardBinNo;
	}

	public String getCupFlag() {
		return cupFlag;
	}

	public void setCupFlag(String cupFlag) {
		this.cupFlag = cupFlag;
	}

	public String getTrack2CardNoLen() {
		return track2CardNoLen;
	}

	public void setTrack2CardNoLen(String track2CardNoLen) {
		this.track2CardNoLen = track2CardNoLen;
	}

	public String getTrack2CardNoOffset() {
		return track2CardNoOffset;
	}

	public void setTrack2CardNoOffset(String track2CardNoOffset) {
		this.track2CardNoOffset = track2CardNoOffset;
	}

	public String getTrack2CardBinOffset() {
		return track2CardBinOffset;
	}

	public void setTrack2CardBinOffset(String track2CardBinOffset) {
		this.track2CardBinOffset = track2CardBinOffset;
	}

	public String getTrack3CardNoLen() {
		return track3CardNoLen;
	}

	public void setTrack3CardNoLen(String track3CardNoLen) {
		this.track3CardNoLen = track3CardNoLen;
	}

	public String getTrack3CardNoOffset() {
		return track3CardNoOffset;
	}

	public void setTrack3CardNoOffset(String track3CardNoOffset) {
		this.track3CardNoOffset = track3CardNoOffset;
	}

	public String getTrack3CardBinOffset() {
		return track3CardBinOffset;
	}

	public void setTrack3CardBinOffset(String track3CardBinOffset) {
		this.track3CardBinOffset = track3CardBinOffset;
	}

	public String getCardBinLen() {
		return cardBinLen;
	}

	public void setCardBinLen(String cardBinLen) {
		this.cardBinLen = cardBinLen;
	}

	public String getCardBinStart() {
		return cardBinStart;
	}

	public void setCardBinStart(String cardBinStart) {
		this.cardBinStart = cardBinStart;
	}

	public String getCardBinEnd() {
		return cardBinEnd;
	}

	public void setCardBinEnd(String cardBinEnd) {
		this.cardBinEnd = cardBinEnd;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
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

	public String getInternalAcctType() {
		return internalAcctType;
	}

	public void setInternalAcctType(String internalAcctType) {
		this.internalAcctType = internalAcctType;
	}

}
