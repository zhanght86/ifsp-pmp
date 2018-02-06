<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/snowweb" prefix="snow"%>
<snow:page title="批量步骤运行明细">
<snow:dataset id="BatchStepList" path="com.ruimin.ifs.mng.dataset.batch.BatchStepList" init="true" readOnly="true"></snow:dataset>
<snow:grid dataset="BatchStepList" id="steplist" fieldstr="stepdispname,substepname,starttime,endtime,status"></snow:grid>
</snow:page>
