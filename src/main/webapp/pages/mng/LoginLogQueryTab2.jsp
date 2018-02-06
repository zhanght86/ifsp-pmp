<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="登录详情">
<snow:dataset id="LoginLogQueryTab2" path="com.ruimin.ifs.mng.dataset.LoginLogQueryTab2" init="true"></snow:dataset>
	<snow:query label="请输入查询条件" id="queryId" dataset="LoginLogQueryTab2" fieldstr="qtlrNo,qbrCode,queryLastaccesstm,queryLastlogouttm"></snow:query>
	<snow:grid dataset="LoginLogQueryTab2" id="gridId" fieldstr="tlrno,brno,lastaccesstm,lastlogouttm,crtTm" exporter="exporterId"></snow:grid>
	<snow:exporter dataset="LoginLogQueryTab2" id="exporterId" type="XLS|CSV" fieldstr="tlrno,brno,lastaccesstm,lastlogouttm,crtTm"></snow:exporter>
</snow:page>
