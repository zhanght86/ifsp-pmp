package com.ruimin.ifs.pmp.mktActivity.comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveInfConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMchtInConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMchtInInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveQueryMchtVO;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActInfAuditService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActiveQueryMchtService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;

@SnowDoc(desc = "查询")
@ActionResource
public class ActiveQueryMchtAction extends SnowAction{
	@Action
	@SnowDoc(desc = "查询")
	public PageResult query(QueryParamBean queryBean) throws SnowException {
		String mchtId = queryBean.getParameter("qmchtId");
		String mchtName = queryBean.getParameter("qmchtName");
		String mchtAreaNo = queryBean.getParameter("qmchtAreaNo");
		String mchtMcc = queryBean.getParameter("qmchtMcc");
		String inFlg = queryBean.getParameter("qinFlg");
		String mchtLevl = queryBean.getParameter("qmchtLevl");
		String activeNo = queryBean.getParameter("qActiveNo");
		return ActiveQueryMchtService.getInstance().queryForMap(mchtId,mchtName,mchtAreaNo,
				                                                mchtMcc, inFlg, mchtLevl, activeNo,queryBean.getPage());
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "更新临时表")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/************************* step no 1: 校验审核用户权限 ***********************/

		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		
		/************************ step no 2: 封装商户参与列表信息 *********************/
		String activeNo="";
		//获取数据集
		UpdateRequestBean requestBean = updateMap.get("mchtIn");
		String currentDay = DateUtil.get8Date();
		String currentDateTime = DateUtil.get14Date();
		while (requestBean.hasNext()) {
			ActiveQueryMchtVO tempVO = new ActiveQueryMchtVO();
			DataObjectUtils.map2bean(requestBean.next(), tempVO);
			activeNo = tempVO.getActiveNo();
			//获取参与状态
			String inFlg = tempVO.getInFlg();
			 //如果没状态,默认设为未参与
            if ("".equals(inFlg)||inFlg==null) {
                tempVO.setInFlg("03");
            }
            //查询本次活动的优先级情况
            String actLv = ActiveQueryMchtService.getInstance().selectActLv(activeNo);
			//分支1： 本次被选中
			if(String.valueOf(tempVO.getSelect()).equals("true")){
				
				if(tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_IN)){
					//原纪录参与状态为-01-参与
					continue;
				}else if(tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_QUITE)){
				  //根据商户编号查询活动编号
                    List<String> list = new ArrayList<>();
                    String selectActLv;
                    List<Object> selectActNo = ActiveQueryMchtService.getInstance().selectActNo(tempVO.getMchtId());
                    for (Object object : selectActNo) {
                        selectActLv = ActiveQueryMchtService.getInstance().selectActLv(object.toString());
                        list.add(selectActLv);
                    }
                    if (list.size()!=0) {
                        
                        if (list.size()>=3) {
                            SnowExceptionUtil.throwWarnException("商户"+tempVO.getMchtId()+"已经参加过各个优先级的活动了,无法再参加活动!!");
                        }
                        for (String actL : list) {
                            if (actL.equals(actLv)) {
                                SnowExceptionUtil.throwWarnException("商户"+tempVO.getMchtId()+"已经参加过与本活动优先级相同的活动了,无法参加本活动!!");
                            }
                        }
                        
                    }
				    //原纪录参与状态为-02-退出，更新数据
					ActiveMchtInInfTmpVO inVO = new ActiveMchtInInfTmpVO();
					inVO.setActiveNo(tempVO.getActiveNo());//活动编号
					inVO.setMchtId(tempVO.getMchtId());//商户编号
					inVO.setInFlg(ActiveMchtInConstants.IN_STATE_IN);//参与状态-01-参与
					inVO.setInDate(currentDay);//参与日期
					inVO.setUpdateTime(currentDateTime);//更新时间
					inVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员
					inVO.setSyncFlag(ActiveMchtInConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
					
					ActiveQueryMchtService.getInstance().reIn(inVO);
					
				}else if(tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_NONE)){
				  //根据商户编号查询活动编号
                    List<String> list = new ArrayList<>();
                    String selectActLv;
                    List<Object> selectActNo = ActiveQueryMchtService.getInstance().selectActNo(tempVO.getMchtId());
                    for (Object object : selectActNo) {
                        selectActLv = ActiveQueryMchtService.getInstance().selectActLv(object.toString());
                        list.add(selectActLv);
                    }
                    if (list.size()!=0) {
                        
                        if (list.size()>=3) {
                            SnowExceptionUtil.throwWarnException("商户"+tempVO.getMchtId()+"已经参加过各个优先级的活动了,无法再参加活动!!");
                        }
                        for (String actL : list) {
                            if (actL.equals(actLv)) {
                                SnowExceptionUtil.throwWarnException("商户"+tempVO.getMchtId()+"已经参加过与本活动优先级相同的活动了,无法参加本活动!!");
                            }
                        }
                        
                    }
				    //原纪录参与状态为-03-未参与，新增数据
					ActiveMchtInInfTmpVO inVO = new ActiveMchtInInfTmpVO();
					inVO.setActiveNo(tempVO.getActiveNo());//活动编号
					inVO.setMchtId(tempVO.getMchtId());//商户编号
					inVO.setInFlg(ActiveMchtInConstants.IN_STATE_IN);//参与状态-01-参与
					inVO.setInDate(currentDay);//参与日期
					inVO.setUpdateTime(currentDateTime);//更新时间
					inVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员
					inVO.setSyncFlag(ActiveMchtInConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
					
					ActiveQueryMchtService.getInstance().insert(inVO);
				}
			}else if(String.valueOf(tempVO.getSelect()).equals("false")){
				if(tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_QUITE) || tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_NONE)){
					//原纪录参与状态为-02-退出或03-未参与，继续下一条数据
					continue;
				}else if(tempVO.getInFlg().equals(ActiveMchtInConstants.IN_STATE_IN)){
					//原纪录参与状态为-01-参与，更新数据
					ActiveMchtInInfTmpVO inVO = new ActiveMchtInInfTmpVO();
					inVO.setActiveNo(tempVO.getActiveNo());//活动编号
					inVO.setMchtId(tempVO.getMchtId());//商户编号
					inVO.setInFlg(ActiveMchtInConstants.IN_STATE_QUITE);//参与状态-02-退出
					inVO.setOutDate(currentDay);//退出时间
					inVO.setUpdateTime(currentDateTime);//更新时间
					inVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员
					inVO.setSyncFlag(ActiveMchtInConstants.SYNC_STATE_UNDONE);//数据同步状态-0-已变更未同步
					
					ActiveQueryMchtService.getInstance().quite(inVO);
				}
			}
		}
		/***********************************参与商户审核流程********************************************/
        //根据活动编号查询活动信息,设置参数更新
		String auditId = ContextUtil.getUUID();
		String auditFlag = ActiveInfConstants.AUDIT_FLAG_05;
		ActInfAuditService.getInstance().updActInf(auditId,auditFlag,activeNo,sessionUserInfo.getTlrno());
		//关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActInfAuditService.getInstance().selectTepList(ActiveInfConstants.AUDIT_TYPE_53);
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
        ActInfAuditService.getInstance().addStepInfo(list, auditId);
        /********** 审核：录入审核信息表中 *********/
        ActInfAuditService.getInstance().addAuditInfo2(auditProcId,activeNo,auditId,auditFlag, sessionUserInfo);
		/****************** step no :4 记录操作日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "修改活动商户配置,活动编号=[" + activeNo + "]" });
		
		
		
		
		
		
	}
}
