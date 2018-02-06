<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="日志查询">
	<snow:dataset id="LoginLogQueryTab1" path="com.ruimin.ifs.mng.dataset.LoginLogQueryTab1" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="LoginLogQueryTab1" fieldstr="qtlrNo,qbrNo"></snow:query>
	<snow:grid dataset="LoginLogQueryTab1" id="gridId" fieldstr="tlrNo[100],brcode[100],lastaccesstm[140],lastlogouttm[140],lastfailedtm[140],ipAdr[140],loginRemark" exporter="exporterId"></snow:grid>
	<snow:exporter dataset="LoginLogQueryTab1" id="exporterId" type="XLS|CSV" fieldstr="tlrNo,brcode,lastaccesstm,lastlogouttm,lastfailedtm,ipAdr,loginRemark"></snow:exporter>
</snow:page>