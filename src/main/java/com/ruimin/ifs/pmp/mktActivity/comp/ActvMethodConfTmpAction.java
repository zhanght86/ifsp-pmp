package com.ruimin.ifs.pmp.mktActivity.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveInfConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConfConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActInfAuditService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodConfTmpService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodTmpService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.util.StringUtil;

@SnowDoc(desc = "活动方法配置临时表操作Action")
@ActionResource
public class ActvMethodConfTmpAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String activeNo = queryBean.getParameter("qActiveNo");
		activeNo = StringUtil.isEmpty(activeNo) ? "" : activeNo;
		PageResult result = ActvMethodConfTmpService.getInstance().queryAll(activeNo, queryBean.getPage());
		return result;
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "活动方法配置")
	public void configMethod(Map<String, UpdateRequestBean> updateMap) throws SnowException {

		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();


		/************************ step no 2: 封装活动方法配置信息 *********************/
		//获取分组编号列表
		UpdateRequestBean acctGpBean =updateMap.get("acctGp");
		String fieldSeqNo = acctGpBean.next().get("gpSeq");
		String[] seqNos = fieldSeqNo.split(",");
		
		//获取活动信息
		UpdateRequestBean reqBean = updateMap.get("activeInfo");
		ActiveInfTmpVO activeInfo = new ActiveInfTmpVO();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), activeInfo);
		}
		//获取活动方法配置列表
		UpdateRequestBean methodConfigBean = updateMap.get("methodConfig");
		
		//获取当前活动下最大的方法配置序号
		int currentSeqNo = ActvMethodConfTmpService.getInstance().getMaxConfigSeq(activeInfo.getActiveNo());
		String currentDateTime = DateUtil.get14Date();
		
		List<ActiveMethodConfTmpVO> addList = new ArrayList<ActiveMethodConfTmpVO>();
		List<ActiveMethodConfTmpVO> deleteList = new ArrayList<ActiveMethodConfTmpVO>();
		List<ActiveMethodConfTmpVO> updateList = new ArrayList<ActiveMethodConfTmpVO>();
		//循环获取方法配置列表
		while(methodConfigBean.hasNext()){
			Map<String,String> config = methodConfigBean.next();
			if(methodConfigBean.getRecodeState() == methodConfigBean.NONE){
				//记录未做变更
				continue;
			}else if(methodConfigBean.getRecodeState() == methodConfigBean.INSERT){
				String seqNo = StringUtil.leftPad(String.valueOf(currentSeqNo+1),4,"0");
				//新增活动方法配置
				for(int i=0;i<seqNos.length;i++){
					ActiveMethodConfTmpVO tempVO = new ActiveMethodConfTmpVO();
					tempVO.setConfSeq(seqNo);//方法配置序号
					tempVO.setActiveNo(activeInfo.getActiveNo());//活动编号
					tempVO.setDataState(ActiveMethodConfConstants.DATA_STATE_VALID);//数据有效状态-1-有效
					tempVO.setSyncFlag(ActiveMethodConfConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
					tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
					tempVO.setUpdateTime(currentDateTime);//更新时间-当前系统时间
					tempVO.setMchtLevel(config.get("mchtLevel"));//商户等级
					tempVO.setGpSeq(seqNos[i]);//账户小组序号
					tempVO.setMethodNo(config.get("methodParam"+(i+1)));//方法编号
					
					//添加到新增配置列表
					addList.add(tempVO);
				}
				currentSeqNo++;
			}else if(methodConfigBean.getRecodeState() == methodConfigBean.DELETE){
				//删除活动方法配置
				ActiveMethodConfTmpVO tempVO = new ActiveMethodConfTmpVO();
				tempVO.setConfSeq(config.get("confSeq"));
				tempVO.setActiveNo(activeInfo.getActiveNo());//活动编号
				tempVO.setSyncFlag(ActiveMethodConfConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
				tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
				tempVO.setUpdateTime(currentDateTime);//更新时间-当前系统时间
				tempVO.setDataState(ActiveMethodConfConstants.DATA_STATE_INVALID);//数据状态-0-无效
				
				deleteList.add(tempVO);
			}else if(methodConfigBean.getRecodeState() == methodConfigBean.MODIFY){
				//修改活动方法配置
				for(int i=0;i<seqNos.length;i++){
					ActiveMethodConfTmpVO tempVO = new ActiveMethodConfTmpVO();
					tempVO.setActiveNo(activeInfo.getActiveNo());//活动编号
					tempVO.setConfSeq(config.get("confSeq"));
					tempVO.setSyncFlag(ActiveMethodConfConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
					tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
					tempVO.setUpdateTime(currentDateTime);//更新时间-当前系统时间
					tempVO.setMchtLevel(config.get("mchtLevel"));//商户等级
					tempVO.setGpSeq(seqNos[i]);//账户小组序号
					tempVO.setMethodNo(config.get("methodParam"+(i+1)));//方法编号
					
					//添加到修改配置列表
					updateList.add(tempVO);
				}
			}
		}
		/************************ step no 3: 持久化数据 *********************/
		//批量添加方法配置
		ActvMethodConfTmpService.getInstance().batchAdd(addList);
		//批量修改方法配置
		ActvMethodConfTmpService.getInstance().batchUpdate(updateList);
		//批量删除方法配置
		ActvMethodConfTmpService.getInstance().batchDelete(deleteList);
		
		/***********************************配置审核功能****************************************/
		activeInfo.setAuditId(ContextUtil.getUUID());//设置审核编号
		activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_04);//设置审核标识状态
		//更新活动信息临时表
		ActInfAuditService.getInstance().updActInf(activeInfo.getAuditId(),activeInfo.getAuditFlag(), activeInfo.getActiveNo(),sessionUserInfo.getTlrno());
		//关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActInfAuditService.getInstance().selectTepList(ActiveInfConstants.AUDIT_TYPE_52);
        if (list == null || list.size() == 0) {
            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
        }
        //查询审核业务编号
        String auditProcId = null;
        for (PmpAuditProcStep pmpAuditProcStep : list) {
            auditProcId = pmpAuditProcStep.getAuditProcId();
        }
        if (auditProcId == null) {
            SnowExceptionUtil.throwWarnException("未找到审核流程！");
        }
        /********** 审核：录入审核信息步骤表中 *********/
        ActInfAuditService.getInstance().addStepInfo(list, activeInfo.getAuditId());
        /********** 审核：录入审核信息表中 *********/
        ActInfAuditService.getInstance().addAuditInfo(auditProcId,activeInfo, sessionUserInfo);
		
		
		
		/****************** step no :4 记录操作日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "修改活动方法配置,活动编号=[" + activeInfo.getActiveNo() + "]" });
	}
	/**
	 * 根据方法编号获取方法名称
	 * */
	public static String getMethodName(FieldBean bean, String key, ServletRequest request) throws SnowException {
	       String methodName ="";
	       if(StringUtil.isEmpty(key)){
	    	   return methodName;
	       }
	       ActiveMethodInfTmpVO vo = ActvMethodTmpService.getInstance().getMethodByNo(key);
	       if(vo != null){
	    	   methodName = vo.getMethodNm();
	       }
	       return methodName;
	}
}
