package com.ruimin.ifs.pmp.mchtMng.process.bean;

import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;
/**
 * @deprecated:渠道商户基本信息表
 * 创建日期:2018-01-10 13:46:58
 */
@Table("pbs_chl_mcht_info")
public class PbsChlMchtInfo{

   /**
      *渠道商户号(与pbs_mcht_enter_log_info中的CHL_SYS_NO)对应 
      *数据类型：varchar(32) 
      *是否必填:true 
   **/
   @Id
   private String chlMchtNo;
   /**
      *渠道商户名 
      *数据类型：varchar(64) 
      *是否必填:false 
   **/
   private String chlMchtName;
   /**
      *00-启用，99-停用,默认00 
      *数据类型：char(2) 
      *是否必填:true 
   **/
   private String chlMchtStat;
   /**
      *创建柜员 
      *数据类型：varchar(20) 
      *是否必填:true 
   **/
   private String crtTlr;
   /**
      *创建日期时间 
      *数据类型：char(14) 
      *是否必填:true 
   **/
   private String crtDateTime;
   /**
      *最近更新柜员 
      *数据类型：varchar(20) 
      *是否必填:false 
   **/
   private String lastUpdTlr;
   /**
      *最近更新日期时间 
      *数据类型：varchar(20) 
      *是否必填:false 
   **/
   private String lastUpdDateTime;

  //get和set方法
   /**
    *渠道商户号(与pbs_mcht_enter_log_info中的CHL_SYS_NO)对应
    *@return String
    */
   public String getChlMchtNo(){
      return chlMchtNo;
   }

   /**
    *渠道商户号(与pbs_mcht_enter_log_info中的CHL_SYS_NO)对应
    *@param chlMchtNo
    */
   public void setChlMchtNo(String chlMchtNo){
      this.chlMchtNo = chlMchtNo;
   }

   /**
    *渠道商户名
    *@return String
    */
   public String getChlMchtName(){
      return chlMchtName;
   }

   /**
    *渠道商户名
    *@param chlMchtName
    */
   public void setChlMchtName(String chlMchtName){
      this.chlMchtName = chlMchtName;
   }

   /**
    *00-启用，99-停用,默认00
    *@return String
    */
   public String getChlMchtStat(){
      return chlMchtStat;
   }

   /**
    *00-启用，99-停用,默认00
    *@param chlMchtStat
    */
   public void setChlMchtStat(String chlMchtStat){
      this.chlMchtStat = chlMchtStat;
   }

   /**
    *创建柜员
    *@return String
    */
   public String getCrtTlr(){
      return crtTlr;
   }

   /**
    *创建柜员
    *@param crtTlr
    */
   public void setCrtTlr(String crtTlr){
      this.crtTlr = crtTlr;
   }

   /**
    *创建日期时间
    *@return String
    */
   public String getCrtDateTime(){
      return crtDateTime;
   }

   /**
    *创建日期时间
    *@param crtDateTime
    */
   public void setCrtDateTime(String crtDateTime){
      this.crtDateTime = crtDateTime;
   }

   /**
    *最近更新柜员
    *@return String
    */
   public String getLastUpdTlr(){
      return lastUpdTlr;
   }

   /**
    *最近更新柜员
    *@param lastUpdTlr
    */
   public void setLastUpdTlr(String lastUpdTlr){
      this.lastUpdTlr = lastUpdTlr;
   }

   /**
    *最近更新日期时间
    *@return String
    */
   public String getLastUpdDateTime(){
      return lastUpdDateTime;
   }

   /**
    *最近更新日期时间
    *@param lastUpdDateTime
    */
   public void setLastUpdDateTime(String lastUpdDateTime){
      this.lastUpdDateTime = lastUpdDateTime;
   }

}