package com.ruimin.ifs.pmp.txnQuery.comp;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.message.IfspFastJsonUtil;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.pubTool.common.constants.HttpTransConstants;
import com.ruimin.ifs.pmp.pubTool.test.HttpSend;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.pmp.txnQuery.process.service.BatchJobRecordService;


@SnowDoc(desc = "批量任务结果查询")
@ActionResource
public class BatchJobRecordAction extends SnowAction {
	@Action
	@SnowDoc(desc = "批量任务结果查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		//接收查询参数-批量任务Id
		String qjobId = queryBean.getParameter("qjobId");
		//接收查询参数-清算日期
		String qsettleDate = queryBean.getParameter("qsettleDate");		
		//接收查询参数-批量执行状态
		String qjobStat = queryBean.getParameter("qjobStat");
		
		//拼装查询Map
		Map<String,String> paramMap= new HashMap<String,String>();		
		paramMap.put("qjobId", StringUtil.isBlank(qjobId)?"":"%"+qjobId+"%");
		paramMap.put("qsettleDate", StringUtil.isBlank(qsettleDate)?"":qsettleDate);
		paramMap.put("qjobStat", StringUtil.isBlank(qjobStat)?"":qjobStat);
		
		return BatchJobRecordService.getInstance().queryAll(paramMap, queryBean.getPage());
	}
	
	@SuppressWarnings("unchecked")
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "批量任务重跑")
	public void doReRun(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理:[ =======================开始======================== ]");
		if(IfspDataVerifyUtil.isEmptyMap(updateMap)){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 请求数据(updateMap) is null ]");
			return ;
		}
		UpdateRequestBean flowBean = updateMap.get("batchJobReRun");
		
		if(flowBean==null){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 请求数据(flowBean) is null ]");
			return ;
		}
		String jobId = flowBean.getParameter("jobId");
		if(IfspDataVerifyUtil.isBlank(jobId)){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 请求数据(jobId) is null ]");
			return ;
		}
		String settleDate = flowBean.getParameter("settleDate");
		if(IfspDataVerifyUtil.isBlank(settleDate)){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 请求数据(jobId) is null ]");
			return ;
		}
		SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理:[ jobId:" + jobId +";settleDate:" + settleDate+" ]");
			
		//向批量服务发送重跑请求
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("jobId", jobId);
		resultMap.put("settleDate", settleDate);
		resultMap.put("jobDesc", "");
		resultMap.put("tlrno", "");
		String jsonRequest = IfspFastJsonUtil.tojson(resultMap);
		if(IfspDataVerifyUtil.isBlank(jsonRequest)){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 组装批量请求报文(jsonRequest) is null ]");
			return ;
		}
		SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理:[ 组织批量请求报文："+jsonRequest+" ]");
		String url = SysParamUtil.getParam(HttpTransConstants.BATCH_RERUN_JOB_URL);
		if(IfspDataVerifyUtil.isBlank(url)){
			SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 批量请求地址(url) is null ]");
			return ;
		}
		String result = null;
		try {
			result = HttpSend.sendMessage(jsonRequest,url);
			if(IfspDataVerifyUtil.isNotBlank(result)){
				resultMap =IfspFastJsonUtil.jsonToobject(result, HashMap.class);
				if(IfspDataVerifyUtil.isEmptyMap(resultMap)){
					SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理失败:[ 支付批量响应报文转换错误(resultMap) is null ]");
				return ;
				}
				if(!"0000".equals(resultMap.get("respCode")))
					SnowExceptionUtil.throwErrorException("任务" + "["+jobId+"]"+SysParamUtil.getParam((String)resultMap.get("respCode")));
				else
					SnowLog.getGroupLog().info("支付管理平台手动触发支付批量系统跑批业务处理:[ =======================完成======================== ]");
			}else{
				SnowExceptionUtil.throwErrorException("任务" + "["+jobId+"]"+SysParamUtil.getParam((String)resultMap.get("respCode")));
			}
		} catch (Exception e) {
			SnowLog.getGroupLog().error("支付管理平台手动触发支付批量系统跑批业务处理异常:[ "+e.getMessage()+" ]",e);
		}		
	}
	
	
	
}
