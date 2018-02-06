<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="任务提醒">
	<snow:dataset id="taskWarn" path="com.ruimin.ifs.pmp.err.dataset.taskWarn" init="true" submitMode="current"></snow:dataset>
    <snow:grid dataset="taskWarn" id="gridId" fieldstr="txnName,txnNum" paginationbar=""></snow:grid>
</snow:page>
