package com.ruimin.ifs.pmp.mktActivity.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
/**
 * @deprecated:
 * 创建日期:2017-11-28 16:52:44
 */
@Table("tbl_active_redbag_conf")
public class TblActiveRedbagConf{

   @Id
   private String activeNo;
   @Id
   private String redbagSeqNo;
   private String redbagAmt;
   private String redbagCount;
   private String redbagConsume;
   private String misc1;
   private String misc2;
   private String misc3;
   private String misc4;
/**
 * @return the activeNo
 */
public String getActiveNo() {
    return activeNo;
}
/**
 * @param activeNo the activeNo to set
 */
public void setActiveNo(String activeNo) {
    this.activeNo = activeNo;
}
/**
 * @return the redbagSeqNo
 */
public String getRedbagSeqNo() {
    return redbagSeqNo;
}
/**
 * @param redbagSeqNo the redbagSeqNo to set
 */
public void setRedbagSeqNo(String redbagSeqNo) {
    this.redbagSeqNo = redbagSeqNo;
}
/**
 * @return the redbagAmt
 */
public String getRedbagAmt() {
    return redbagAmt;
}
/**
 * @param redbagAmt the redbagAmt to set
 */
public void setRedbagAmt(String redbagAmt) {
    this.redbagAmt = redbagAmt;
}
/**
 * @return the redbagCount
 */
public String getRedbagCount() {
    return redbagCount;
}
/**
 * @param redbagCount the redbagCount to set
 */
public void setRedbagCount(String redbagCount) {
    this.redbagCount = redbagCount;
}
/**
 * @return the redbagConsume
 */
public String getRedbagConsume() {
    return redbagConsume;
}
/**
 * @param redbagConsume the redbagConsume to set
 */
public void setRedbagConsume(String redbagConsume) {
    this.redbagConsume = redbagConsume;
}
/**
 * @return the misc1
 */
public String getMisc1() {
    return misc1;
}
/**
 * @param misc1 the misc1 to set
 */
public void setMisc1(String misc1) {
    this.misc1 = misc1;
}
/**
 * @return the misc2
 */
public String getMisc2() {
    return misc2;
}
/**
 * @param misc2 the misc2 to set
 */
public void setMisc2(String misc2) {
    this.misc2 = misc2;
}
/**
 * @return the misc3
 */
public String getMisc3() {
    return misc3;
}
/**
 * @param misc3 the misc3 to set
 */
public void setMisc3(String misc3) {
    this.misc3 = misc3;
}
/**
 * @return the misc4
 */
public String getMisc4() {
    return misc4;
}
/**
 * @param misc4 the misc4 to set
 */
public void setMisc4(String misc4) {
    this.misc4 = misc4;
}
   
   
}