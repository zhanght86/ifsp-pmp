package com.ruimin.ifs.pmp.mktActivity.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.net.aso.a;

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
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodSectionConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MethodSectionInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActMethodAuditService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodSectionTmpService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvMethodTmpService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.StringUtil;

@SnowDoc(desc = "活动方法管理临时表操作Action")
@ActionResource
public class ActvMethodTmpAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询活动方法列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		/********** step no: 1 获取查询参数 **********/
		// 获取查询条件-方法编号
		String qMethodNo = queryBean.getParameter("qMethodNo");
		// 获取查询条件-方法名称
		String qMethodName = queryBean.getParameter("qMethodName");
		// 获取查询条件-方法类型
		String qMethodType = queryBean.getParameter("qMethodType");
		String auditId = queryBean.getParameter("auditId");// 审核信息编号

		/************* step no: 2 分页查询活动方法列表 **************/
		return ActvMethodTmpService.getInstance().queryList(qMethodNo, qMethodName, qMethodType,auditId, queryBean.getPage());

	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增活动方法")
	public void addMethod(Map<String, UpdateRequestBean> updateMap) throws SnowException {


		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		// 校验审核用户权限

		/*************************
		 * step no 2: 封装活动方法基本信息
		 ***********************/
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("methodInfo");
		ActiveMethodInfTmpVO methodInfo = new ActiveMethodInfTmpVO();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), methodInfo);
		}
		//设置审核编号
		String auditId = ContextUtil.getUUID();
		// 封装方法补充信息
		methodInfo.setUpdateOpr(sessionUserInfo.getTlrno());// 当前操作用户
		methodInfo.setUpdateTime(DateUtil.get14Date());// 当前操作时间
		methodInfo.setAuditTime(methodInfo.getUpdateTime());// 审核时间为当前时间
		methodInfo.setMethodStat(ActiveMethodConstants.MTHD_STAT_NORMAL);// 活动状态-00-正常
		methodInfo.setSyncFlag(ActiveMethodConstants.SYNC_STATE_UNDONE);// 同步状态0-已变更未同步
		//methodInfo.setAuditOpr(auditUserName);// 审核用户
		methodInfo.setAuditFlag(ActiveMethodConstants.AUDIT_FLAG_01);//设置审核状态
		methodInfo.setAuditId(auditId);//设置审核编号

		// 判断类型，如果为21-红包立减, 则需要转换金额
		if ( methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_21)) {
			methodInfo.setParam1Data(BaseUtil.transYuanToFen(methodInfo.getParam1Data()));
		}
		// 查询获取方法编号
		String methodNo = ActvMethodTmpService.getInstance().getMaxSeq();// 查询获取数据库最大的方法编号
		if (StringUtil.isEmpty(methodNo)) {
			methodNo = "0";
		}
		int nextSeqNo = Integer.valueOf(methodNo.trim()) + 1;
		methodNo = StringUtil.leftPad(String.valueOf(nextSeqNo), 4, "0");

		methodInfo.setMethodNo(methodNo);// 赋值最大方法编号

		// 插入活动方法信息
		ActvMethodTmpService.getInstance().insertMethod(methodInfo);
		/*************************
		 * step no 3: 封装活动方法分段信息
		 ***********************/
		// 当方法类型不为21-红包立减时，获取分段信息
		if (!methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_21)) {
			// 获取页面传递的分段信息
			List<MethodSectionInfTmpVO> sectionList = new ArrayList<MethodSectionInfTmpVO>();
			UpdateRequestBean reqSectionBean = updateMap.get("methodSectionInfo");
			int seqNo = 1;
			while (reqSectionBean.hasNext()) {
				MethodSectionInfTmpVO tempVO = new MethodSectionInfTmpVO();
				DataObjectUtils.map2bean(reqSectionBean.next(), tempVO);
				// 封装分段补充信息
				tempVO.setSectionSeq(StringUtil.leftPad(String.valueOf(seqNo), 2, "0"));
				tempVO.setMethodNo(methodNo);// 方法编号
				tempVO.setUpdateOpr(sessionUserInfo.getTlrno());
				tempVO.setUpdateTime(DateUtil.get14Date());
				tempVO.setDataState(ActiveMethodSectionConstants.DATA_STATE_VALID);// 数据有效状态-1-有效
				tempVO.setSyncFlag(ActiveMethodSectionConstants.SYNC_STATE_UNDONE);// 数据同步状态-0-已变更未同步
				tempVO.setSectionLeft(BaseUtil.transYuanToFen(tempVO.getSectionLeft()));
				tempVO.setSectionRight(StringUtil.isEmpty(tempVO.getSectionRight())?"":BaseUtil.transYuanToFen(tempVO.getSectionRight()));
				// 如果方法类型不为32-折后减后凑整，去掉分段信息参数2
				if (!methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_DISC_INT)) {
					tempVO.setSectionParam2(null);
				}
				// 如果方法类型为11-手续费减免，则分段参数1转换为分
				if (methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_DISC)) {
					tempVO.setSectionParam1(BaseUtil.transYuanToFen(tempVO.getSectionParam1()));
				}
				// 如果方法类型为32：折后减后凑整，则分段参数2转换为分
				if (methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_DISC_INT)) {
					tempVO.setSectionParam2(BaseUtil.transYuanToFen(tempVO.getSectionParam2()));
				}
				sectionList.add(tempVO);
				seqNo++;
			}
			// 批量插入方法分段列表
			ActvMethodSectionTmpService.getInstance().batchAddSection(sectionList);
		}
		//-----------------------------------------新增审核功能----------------------------------------------//
        //关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActMethodAuditService.getInstance().selectTepList(ActiveMethodConstants.AUDIT_TYPE_40);
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
        ActMethodAuditService.getInstance().addStepInfo(list, methodInfo.getAuditId());
        /********** 审核：录入审核信息表中 *********/
        ActMethodAuditService.getInstance().addAuditInfo(auditProcId,methodInfo, sessionUserInfo);
        
		// 记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "新增活动方法,编号=[" + methodNo + "]" });
	}

	@SnowDoc(desc = "删除活动方法")
	@Action(propagation = Propagation.REQUIRED)
	public void deleteMethod(Map<String, UpdateRequestBean> updateMap) throws SnowException {

		/****************** step no :1 校验审核用户信息 ********************/
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/****************** step no :2 获取页面传递参数 ********************/
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("methodInfo");
		//String methodNo = reqBean.getParameter("methodNo");
		ActiveMethodInfTmpVO methodInfo = new ActiveMethodInfTmpVO();
        if (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), methodInfo);
        }
        methodInfo.setAuditFlag(ActiveMethodConstants.AUDIT_FLAG_04);
        methodInfo.setAuditId(ContextUtil.getUUID());
		/****************** step no :3 删除方法信息 ********************/
        //把临时表审核标识状态改为删除待审核,并加入审核编号
        ActMethodAuditService.getInstance().updAuditFlagDel(methodInfo.getMethodNo(),methodInfo.getAuditId(),sessionUserInfo.getTlrno());
		/*// 删除方法下所有的分段信息
		ActvMethodSectionTmpService.getInstance().deleteSectionByMethodNo(methodInfo, sessionUserInfo.getTlrno());

		// 删除活动信息
		ActvMethodTmpService.getInstance().deleteMethodByNo(methodInfo, sessionUserInfo.getTlrno());*/
		
		//-----------------------------------------删除审核功能----------------------------------------------//
        //关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActMethodAuditService.getInstance().selectTepList(ActiveMethodConstants.AUDIT_TYPE_42);
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
        ActMethodAuditService.getInstance().addStepInfo(list, methodInfo.getAuditId());
        /********** 审核：录入审核信息表中 *********/
        ActMethodAuditService.getInstance().addAuditInfo(auditProcId,methodInfo, sessionUserInfo);

		/****************** step no :4 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "删除活动方法,编号=[" + methodInfo.getMethodNo() + "]" });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改活动方法")
	public void updateMethod(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/****************** step no :1 校验审核用户 ********************/
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		
		/****************** step no :2 获取页面传递方法基本信息 ********************/
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("methodInfo");
		ActiveMethodInfTmpVO methodInfo = new ActiveMethodInfTmpVO();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), methodInfo);
		}
		// 封装方法补充信息
		methodInfo.setUpdateOpr(sessionUserInfo.getTlrno());// 当前操作用户
		methodInfo.setUpdateTime(DateUtil.get14Date());// 当前操作时间
		methodInfo.setAuditTime(methodInfo.getUpdateTime());// 审核时间为当前时间
		methodInfo.setMethodStat(ActiveMethodConstants.MTHD_STAT_NORMAL);// 活动状态-00-正常
		methodInfo.setSyncFlag(ActiveMethodConstants.SYNC_STATE_UNDONE);// 同步状态0-已变更未同步
		//methodInfo.setAuditOpr(auditUserName);// 审核用户
		methodInfo.setAuditId(ContextUtil.getUUID());//设置审核编号
		String auditFlag = methodInfo.getAuditFlag();
		if (ActiveMethodConstants.AUDIT_FLAG_02.equals(auditFlag)) {
		    methodInfo.setAuditFlag(ActiveMethodConstants.AUDIT_FLAG_01);//设置审核标志
        }else {
            methodInfo.setAuditFlag(ActiveMethodConstants.AUDIT_FLAG_03);//设置审核标志
        }
		// 判断类型，如果为21-红包立减, 则需要转换金额
        if ( methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_21)) {
            methodInfo.setParam1Data(BaseUtil.transYuanToFen(methodInfo.getParam1Data()));
        }
		//更新活动方法基本信息
		ActvMethodTmpService.getInstance().updateMethod(methodInfo);
		
		/****************** step no :3 获取页面传递方法分段信息 ********************/
		// 当方法类型不为21-红包立减时，获取分段信息
		if (!methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_21)) {
			// 获取页面传递的分段信息
			List<MethodSectionInfTmpVO> sectionAddList = new ArrayList<MethodSectionInfTmpVO>();
			List<MethodSectionInfTmpVO> sectionUpdList = new ArrayList<MethodSectionInfTmpVO>();
			List<MethodSectionInfTmpVO> sectionDltList = new ArrayList<MethodSectionInfTmpVO>();
			UpdateRequestBean reqSectionBean = updateMap.get("methodSectionInfo");
			
			//查询当前方法下分段信息最大序号
			String seqStr = ActvMethodSectionTmpService.getInstance().getMaxSeq(methodInfo.getMethodNo());
			if(StringUtil.isEmpty(seqStr)){
				seqStr="0";
			}
			int seqNo =Integer.valueOf(seqStr);
			while (reqSectionBean.hasNext()) {
				MethodSectionInfTmpVO tempVO = new MethodSectionInfTmpVO();
				DataObjectUtils.map2bean(reqSectionBean.next(), tempVO);
				//当本条分段记录未改动时，跳过本条
				if(reqSectionBean.getRecodeState() == reqSectionBean.NONE){
					continue;
				}
				//当本条分段记录状态为删除时
				if(reqSectionBean.getRecodeState() == reqSectionBean.DELETE){
					tempVO.setDataState(ActiveMethodSectionConstants.DATA_STATE_INVALID);//数据状态-0-无效
					sectionDltList.add(tempVO);
				}else if(reqSectionBean.getRecodeState() == reqSectionBean.INSERT){
					//如果本条分段记录状态为新增时
					tempVO.setSectionSeq(StringUtil.leftPad(String.valueOf(++seqNo),2,"0"));//分段序号
					tempVO.setDataState(ActiveMethodSectionConstants.DATA_STATE_VALID);//数据状态-1-有效
					tempVO.setMethodNo(methodInfo.getMethodNo());//活动方法编号
					sectionAddList.add(tempVO);
				}else if(reqSectionBean.getRecodeState() == reqSectionBean.MODIFY){
					sectionUpdList.add(tempVO);
				}
				
				tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前用户
				tempVO.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
				tempVO.setSyncFlag(ActiveMethodSectionConstants.SYNC_STATE_UNDONE);// 数据同步状态-0-已变更未同步
				tempVO.setSectionLeft(BaseUtil.transYuanToFen(tempVO.getSectionLeft()));//左闭-元转换分
				tempVO.setSectionRight(StringUtil.isEmpty(tempVO.getSectionRight())?"":BaseUtil.transYuanToFen(tempVO.getSectionRight()));//右开-分转换元
				// 如果方法类型不为32-折后减后凑整，去掉分段信息参数2
				if (!methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_DISC_INT)) {
					tempVO.setSectionParam2(null);
				}
				// 如果方法类型为11-手续费减免，则分段参数1转换为分
				if (methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_FEE_DISC)) {
					tempVO.setSectionParam1(BaseUtil.transYuanToFen(tempVO.getSectionParam1()));
				}
				// 如果方法类型为32：折后减后凑整，则分段参数2转换为分
				if (methodInfo.getMethodTp().equals(ActiveMethodConstants.MTHD_TYPE_DISC_INT)) {
					tempVO.setSectionParam2(BaseUtil.transYuanToFen(tempVO.getSectionParam2()));
				}
			}
			//批量新增分段信息
			ActvMethodSectionTmpService.getInstance().batchAddSection(sectionAddList);
			//批量更新分段信息
			ActvMethodSectionTmpService.getInstance().batchUpdateSection(sectionUpdList);
			//批量删除分段信息
			ActvMethodSectionTmpService.getInstance().batchDeleteSection(sectionDltList);
		}
		
		//-----------------------------------------修改审核功能----------------------------------------------//
        //关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActMethodAuditService.getInstance().selectTepList(ActiveMethodConstants.AUDIT_TYPE_41);
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
        ActMethodAuditService.getInstance().addStepInfo(list, methodInfo.getAuditId());
        /********** 审核：录入审核信息表中 *********/
        ActMethodAuditService.getInstance().addAuditInfo(auditProcId,methodInfo, sessionUserInfo);
		
		
		
		/****************** step no :4 记录操作日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "新增活动方法,编号=[" + methodInfo.getMethodNo() + "]" });
		
	}
	@Action
	@SnowDoc(desc = "查询活动方法列表,用于活动管理-方法配置功能")
	public PageResult queryByActiveType(QueryParamBean queryBean) throws SnowException {
		/********** step no: 1 获取查询参数 **********/
		String activeType = queryBean.getParameter("qActiveType");
		
		String methodNo = queryBean.getParameter("qMethodNo");
		/************* step no: 2 分页查询活动方法列表 **************/
		return ActvMethodTmpService.getInstance().queryListByActiveType(activeType,methodNo, queryBean.getPage());

	}
}
