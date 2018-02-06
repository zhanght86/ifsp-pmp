<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="定时任务日志查询">
	<snow:dataset id="BopTimedSchedulerManageLog" path="com.ruimin.ifs.mng.dataset.BopTimedSchedulerManageLog" init="true"></snow:dataset>
	<snow:query id="queryId" dataset="BopTimedSchedulerManageLog" fieldstr="excuteTimeStart,excuteTimeEnd,excuteResult"></snow:query>
	<snow:grid dataset="BopTimedSchedulerManageLog" id="gridId" fieldstr="jobno,subProceFunction,excuteTime,excuteResult,excuteOwn,failFlag,sucFlag,exceptionMsg,endExcuteFlag" ></snow:grid>
</snow:page>
