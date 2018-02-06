package com.ruimin.ifs.pmp.mktActivity.comp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
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
import com.ruimin.ifs.pmp.mktActivity.process.bean.AcctGpConfVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveProdConfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveRandomDiscountInfo;
import com.ruimin.ifs.pmp.mktActivity.process.bean.TblActiveRedbagConfTmp;
import com.ruimin.ifs.pmp.mktActivity.process.service.AcctGpConfService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActInfAuditService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActiveRedBagInfoService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvCycleConfTmpService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvInfTmpService;
import com.ruimin.ifs.pmp.mktActivity.process.service.ActvProdConfTmpService;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfoVO;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.StringUtil;

@SnowDoc(desc = "活动信息临时表操作Action")
@ActionResource
public class ActvInfTmpAction extends SnowAction {
    /** 银行全资 */
    public final static String BUDGETTP_01 = "01";
    
    
	@Action
	@SnowDoc(desc = "分页查询活动列表")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		/********** step no: 1 获取查询参数 **********/
		String activeNo = queryBean.getParameter("qActiveNo");
		String activeNm = queryBean.getParameter("qActiveNm");
		String activeType = queryBean.getParameter("qActiveType");
		String activeStat = queryBean.getParameter("qActiveStat");
		String prodId = queryBean.getParameter("qProdId");
		String gpTp = queryBean.getParameter("qCardGpNo");
		String auditId = queryBean.getParameter("auditId");

		activeNo = StringUtil.isEmpty(activeNo) ? "" : activeNo;
		activeNm = StringUtil.isEmpty(activeNm) ? "" : activeNm;
		activeType = StringUtil.isEmpty(activeType) ? "" : activeType;
		activeStat = StringUtil.isEmpty(activeStat) ? "" : activeStat;
		prodId = StringUtil.isEmpty(prodId) ? "" : prodId;
		gpTp = StringUtil.isEmpty(gpTp) ? "" : gpTp;
		/********** step no: 1 查询并返回数据列表 **********/
		return ActvInfTmpService.getInstance().queryAll(activeNo, activeNm, activeType, activeStat, prodId, gpTp,
		        auditId,queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增活动")
	public void addActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {


		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();


		/************************ step no 2: 封装活动基本信息 *********************/
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("activeInfo");
		ActiveInfTmpVO activeInfo = new ActiveInfTmpVO();
		if (reqBean.hasNext()) {
		    Map<String, String> map = reqBean.next();
		    DataObjectUtils.map2bean(map, activeInfo);
		    String acctCntMax = map.get("acctCntMax");
		    if (IfspDataVerifyUtil.isBlank(acctCntMax)) {
		        activeInfo.setAcctCntMax(null);
            }
		}
		// 封装方法补充信息
		activeInfo.setUpdateOpr(sessionUserInfo.getTlrno());// 当前操作用户
		activeInfo.setUpdateTime(DateUtil.get14Date());// 当前操作时间
		activeInfo.setAuditTime(activeInfo.getUpdateTime());// 审核时间为当前时间
		activeInfo.setActiveStat(ActiveInfConstants.ACTIVE_STATE_READY_DONE);// 活动状态-10-准备完成
		activeInfo.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);// 同步状态0-已变更未同步
		activeInfo.setAuditId(ContextUtil.getUUID());//设置审核编号
		activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_01);//设置审核标识状态为新增待审核
		//activeInfo.setAuditOpr(auditUserName);// 审核用户
		//信息转换
		activeInfo.setActiveBudget(StringUtil.isEmpty(activeInfo.getActiveBudget()) ? ""
				: BaseUtil.transYuanToFen(activeInfo.getActiveBudget()));// 当活动预算不为空，转换元至分
		activeInfo.setPlatBudget(StringUtil.isEmpty(activeInfo.getPlatBudget()) ? ""
				: BaseUtil.transYuanToFen(activeInfo.getPlatBudget()));// 当平台预算不为空，转换元至分
		activeInfo.setSDate(activeInfo.getSDate().substring(0,10));// 截取到小时
		activeInfo.setEDate(StringUtil.isEmpty(activeInfo.getEDate()) ? "" : activeInfo.getEDate().substring(0,10));//结束日期不为空时，截取到小时
		// 生成活动编号
		String activeNo = ActvInfTmpService.getInstance().genActiveNoByType(activeInfo.getActiveType());
		activeInfo.setActiveNo(activeNo);//活动编号
		
		/************************ step no 3: 封装活动支付产品列表 *********************/
		String prdIds = reqBean.getTotalList().get(0).get("prodId");
		String[] prdArray = prdIds.split(",");
		List<ActiveProdConfTmpVO> prdList = new ArrayList<ActiveProdConfTmpVO>();
		for(String prdId : prdArray){
			ActiveProdConfTmpVO tempVO = new ActiveProdConfTmpVO();
			tempVO.setActiveNo(activeNo);//活动编号
			tempVO.setDataState(ActiveInfConstants.DATA_STATE_VALID);//数据有效状态-1-有效
			tempVO.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
			tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
			tempVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);//同步状态-0-已更新未同步
			tempVO.setPrdId(prdId);//产品编号
			prdList.add(tempVO);
		}
		
		/************************ step no 4: 封装活动周期列表 *********************/
		List<ActiveCycleConfTmpVO> cycleList = new ArrayList<ActiveCycleConfTmpVO>();
		if(activeInfo.getCycleFlg().equals(ActiveInfConstants.ACTIVE_CYCLE_FLAG_YES)){
			//如果活动周期类型为1-是
			UpdateRequestBean reqCycleBean = updateMap.get("cycleInfo");
			int seqNo = 1;
			
			while (reqCycleBean.hasNext()) {
				ActiveCycleConfTmpVO tempCycle =  new ActiveCycleConfTmpVO();
				DataObjectUtils.map2bean(reqCycleBean.next(), tempCycle);
				// 封装周期补充信息
				tempCycle.setActiveNo(activeNo);//活动编号
				tempCycle.setDateSeq(StringUtil.leftPad(String.valueOf(seqNo), 2, "0"));//周期序号
				tempCycle.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
				tempCycle.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
				tempCycle.setDataState(ActiveInfConstants.DATA_STATE_VALID);//数据有效状态-1-有效
				tempCycle.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);//数据有效状态-0-已变更未同步
				cycleList.add(tempCycle);
				seqNo++;
			}
		}
		/************************ step no 5: 封装红包配置信息列表 *********************/
		
		List<TblActiveRedbagConfTmp> redList = new ArrayList<TblActiveRedbagConfTmp>();
		List<ActiveRandomDiscountInfo> ardList = new ArrayList<ActiveRandomDiscountInfo>();
		if ((ActiveInfConstants.ACTIVE_TYPE_21).equals(activeInfo.getActiveType())) {
            //如果是红包立减-21
		    UpdateRequestBean redBagConfBean = updateMap.get("redBagConf");
		    int seqNo = 1;
		    while (redBagConfBean.hasNext()) {
                TblActiveRedbagConfTmp tempRed = new TblActiveRedbagConfTmp();
                DataObjectUtils.map2bean(redBagConfBean.next(),tempRed);
                // 封装补充红包配置信息
                tempRed.setActiveNo(activeNo);
                // 当红包金额不为空，转换元至分
                tempRed.setRedbagAmt(StringUtil.isEmpty(tempRed.getRedbagAmt()) ? ""
                        : BaseUtil.transYuanToFen(tempRed.getRedbagAmt()));
                String redbagAmt = tempRed.getRedbagAmt();
                String redbagCount = tempRed.getRedbagCount();
                BigDecimal b1 = new BigDecimal(redbagAmt);
                BigDecimal b2 = new BigDecimal(redbagCount);
                BigDecimal multiply = b1.multiply(b2);
                // 总金额
                tempRed.setRedbagConsume(multiply.toString());
                tempRed.setUpdateTime(DateUtil.get14Date());
                tempRed.setRedbagSeqNo(StringUtil.leftPad(String.valueOf(seqNo), 2, "0"));
                tempRed.setDataState(ActiveInfConstants.DATA_STATE_VALID);
                tempRed.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);
                redList.add(tempRed);
                //组装请求报文参数
                ActiveRandomDiscountInfo aRandInf = new ActiveRandomDiscountInfo();
                aRandInf.setId(tempRed.getRedbagSeqNo());
                aRandInf.setAmt(tempRed.getRedbagAmt());
                aRandInf.setNum(tempRed.getRedbagCount());
                ardList.add(aRandInf);
                seqNo++;
            }
        }
		
		
		/************************ 调用接口 *********************/
		//如果活动类型为首单立减,并且活动预算为银行全资时调用初始化活动预算金额接口
        if((ActiveInfConstants.ACTIVE_TYPE_11).equals(activeInfo.getActiveType())&&BUDGETTP_01.equals(activeInfo.getActiveBudget())){
            String flag = ActvInfTmpService.getInstance().initAmtTotal(activeNo,activeInfo.getActiveBudget());
            if (!"Y".equals(flag)) {
                SnowExceptionUtil.throwWarnException("活动预算初始化失败!!!");
            }
        }
        //如果活动类型为红包立减则调用接口初始化随机立减金额
        if ((ActiveInfConstants.ACTIVE_TYPE_21).equals(activeInfo.getActiveType())) {
            String flag2 = ActvInfTmpService.getInstance().initActiveRandomDiscountFunds(activeNo,ardList);
            if (!"Y".equals(flag2)) {
                SnowExceptionUtil.throwWarnException("随机立减金额初始化失败!!!");
            }
        }
        
		/************************ step no 5: 持久化数据 *********************/
		//插入活动信息
		ActvInfTmpService.getInstance().addActive(activeInfo);
		
		//批量插入支付产品信息
		ActvProdConfTmpService.getInstance().batchAddProdConf(prdList);
		
		//批量插入活动周期信息
		ActvCycleConfTmpService.getInstance().batchAddCycle(cycleList);
		
		//批量插入活动红包配置信息
		ActiveRedBagInfoService.getInstance().batchAddRedBag(redList);
		
		/****************************** 新增审核功能 ********************************/
		 List<PmpAuditProcStep> list = ActInfAuditService.getInstance().selectTepList(ActiveInfConstants.AUDIT_TYPE_50);
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
		
		
		/****************** step no :6 记录操作日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "新增活动,编号=[" + activeNo + "]" });
	}

	@Action
	@SnowDoc(desc = "根据方法编号查询活动列表")
	public PageResult queryByMethod(QueryParamBean queryBean) throws SnowException {
		String methodNo = queryBean.getParameter("methodNo");
		return ActvInfTmpService.getInstance().queryByMethod(methodNo, queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改活动")
	public void updateActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {


		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();


		/************************ step no 2: 封装活动基本信息 *********************/
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("activeInfo");
		ActiveInfTmpVO activeInfo = new ActiveInfTmpVO();
		if (reqBean.hasNext()) {
			 Map<String, String> map = reqBean.next();
	            DataObjectUtils.map2bean(map, activeInfo);
	            String acctCntMax = map.get("acctCntMax");
	            if (IfspDataVerifyUtil.isBlank(acctCntMax)) {
	                activeInfo.setAcctCntMax(null);
	            }
			
		}
		// 封装方法补充信息
		activeInfo.setUpdateOpr(sessionUserInfo.getTlrno());// 当前操作用户
		activeInfo.setUpdateTime(DateUtil.get14Date());// 当前操作时间
		activeInfo.setAuditTime(activeInfo.getUpdateTime());// 审核时间为当前时间
		activeInfo.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);// 同步状态0-已变更未同步
		activeInfo.setAuditId(ContextUtil.getUUID());
		String auditFlag = activeInfo.getAuditFlag();
		if (ActiveInfConstants.AUDIT_FLAG_02.equals(auditFlag)) {
		    activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_01);
        }else {
            activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_03);
            
        }
		//activeInfo.setAuditOpr(auditUserName);// 审核用户
		//信息转换
		activeInfo.setActiveBudget(StringUtil.isEmpty(activeInfo.getActiveBudget()) ? ""
				: BaseUtil.transYuanToFen(activeInfo.getActiveBudget()));// 当活动预算不为空，转换元至分
		activeInfo.setPlatBudget(StringUtil.isEmpty(activeInfo.getPlatBudget()) ? ""
				: BaseUtil.transYuanToFen(activeInfo.getPlatBudget()));// 当平台预算不为空，转换元至分
		activeInfo.setSDate(activeInfo.getSDate().substring(0,10));// 截取到小时
        activeInfo.setEDate(StringUtil.isEmpty(activeInfo.getEDate()) ? "" : activeInfo.getEDate().substring(0,10));//结束日期不为空时，截取到小时
		
		/************************ step no 3: 封装活动支付产品列表 *********************/
		String prdIds = reqBean.getTotalList().get(0).get("prodId");
		String[] prdArray = prdIds.split(",");
		List<ActiveProdConfTmpVO> prdList = new ArrayList<ActiveProdConfTmpVO>();
		for(String prdId : prdArray){
			ActiveProdConfTmpVO tempVO = new ActiveProdConfTmpVO();
			tempVO.setActiveNo(activeInfo.getActiveNo());//活动编号
			tempVO.setDataState(ActiveInfConstants.DATA_STATE_VALID);//数据有效状态-1-有效
			tempVO.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
			tempVO.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
			tempVO.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);//同步状态-0-已更新未同步
			tempVO.setPrdId(prdId);//产品编号
			prdList.add(tempVO);
		}
		
		/************************ step no 4: 封装活动周期列表 *********************/
		List<ActiveCycleConfTmpVO> addCycleList = new ArrayList<ActiveCycleConfTmpVO>();
		List<ActiveCycleConfTmpVO> updCycleList = new ArrayList<ActiveCycleConfTmpVO>();
		List<ActiveCycleConfTmpVO> dltCycleList = new ArrayList<ActiveCycleConfTmpVO>();
		if(activeInfo.getCycleFlg().equals(ActiveInfConstants.ACTIVE_CYCLE_FLAG_YES)){
			//查询获取当前活动下最大的产品配置序号
			String seqStr = ActvCycleConfTmpService.getInstance().getMaSeqByActiveNo(activeInfo.getActiveNo());
			if(StringUtil.isEmpty(seqStr)){
				seqStr="0";
			}
			int seqNo = Integer.valueOf(seqStr);
			//如果活动周期类型为1-是
			UpdateRequestBean reqCycleBean = updateMap.get("cycleInfo");
			
			while (reqCycleBean.hasNext()) {
				ActiveCycleConfTmpVO tempCycle =  new ActiveCycleConfTmpVO();
				DataObjectUtils.map2bean(reqCycleBean.next(), tempCycle);
				if(reqCycleBean.getRecodeState() == reqCycleBean.NONE){
					continue;
				}
				// 封装周期补充信息
				tempCycle.setActiveNo(activeInfo.getActiveNo());//活动编号
				tempCycle.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
				tempCycle.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
				tempCycle.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);//数据有效状态-0-已变更未同步
				
				if(reqCycleBean.getRecodeState() == reqCycleBean.DELETE){
					tempCycle.setDataState(ActiveInfConstants.DATA_STATE_INVALID);//数据有效状态-0-无效
					dltCycleList.add(tempCycle);
				}else if(reqCycleBean.getRecodeState() == reqCycleBean.INSERT){
					tempCycle.setDateSeq(StringUtil.leftPad(String.valueOf(++seqNo), 2,"0"));//周期序号
					tempCycle.setDataState(ActiveInfConstants.DATA_STATE_VALID);//数据有效状态-1-有效
					addCycleList.add(tempCycle);
				}else if (reqCycleBean.getRecodeState() == reqCycleBean.MODIFY){
					updCycleList.add(tempCycle);
				}
			}
		}else{
			ActiveCycleConfTmpVO tempCycle =  new ActiveCycleConfTmpVO();
			tempCycle.setActiveNo(activeInfo.getActiveNo());//活动编号
			tempCycle.setUpdateOpr(sessionUserInfo.getTlrno());//更新操作员-当前登录用户
			tempCycle.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
			tempCycle.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);//数据有效状态-0-已变更未同步
			tempCycle.setDataState(ActiveInfConstants.DATA_STATE_INVALID);//数据有效状态-0-无效
			//删除周期配置信息
			ActvCycleConfTmpService.getInstance().batchDeleteCycle(tempCycle);
		}
		
		/************************ step no 5: 封装活动红包配置列表 *********************/
		List<TblActiveRedbagConfTmp> addRedList = new ArrayList<TblActiveRedbagConfTmp>();
		List<TblActiveRedbagConfTmp> updRedList = new ArrayList<TblActiveRedbagConfTmp>();
		List<TblActiveRedbagConfTmp> delRedList = new ArrayList<TblActiveRedbagConfTmp>();
		if ((ActiveInfConstants.ACTIVE_TYPE_21).equals(activeInfo.getActiveType())) {
		    //查询当前活动下最大的红包配置序号
		    String seqStr = ActiveRedBagInfoService.getInstance().getMaSeqByActiveNo(activeInfo.getActiveNo());
		    if(StringUtil.isEmpty(seqStr)){
                seqStr="0";
            }
		    int seqNo = Integer.valueOf(seqStr);
		    UpdateRequestBean redBagConfBean = updateMap.get("redBagConf");
		    while (redBagConfBean.hasNext()) {
		        TblActiveRedbagConfTmp tempRed = new TblActiveRedbagConfTmp();
                DataObjectUtils.map2bean(redBagConfBean.next(),tempRed);
                if(redBagConfBean.getRecodeState() == redBagConfBean.NONE){
                    continue;
                }
                // 封装红包配置补充信息
                tempRed.setActiveNo(activeInfo.getActiveNo());
                // 当红包金额不为空，转换元至分
                tempRed.setRedbagAmt(StringUtil.isEmpty(tempRed.getRedbagAmt()) ? ""
                        : BaseUtil.transYuanToFen(tempRed.getRedbagAmt()));
                String redbagAmt = tempRed.getRedbagAmt();
                String redbagCount = tempRed.getRedbagCount();
                BigDecimal b1 = new BigDecimal(redbagAmt);
                BigDecimal b2 = new BigDecimal(redbagCount);
                BigDecimal multiply = b1.multiply(b2);
                // 总金额
                tempRed.setRedbagConsume(multiply.toString());
                tempRed.setUpdateTime(DateUtil.get14Date());
                tempRed.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);
                if(redBagConfBean.getRecodeState() == redBagConfBean.DELETE){
                    tempRed.setDataState(ActiveInfConstants.DATA_STATE_INVALID);
                    delRedList.add(tempRed);
                }else if (redBagConfBean.getRecodeState() == redBagConfBean.INSERT) {
                    tempRed.setDataState(ActiveInfConstants.DATA_STATE_VALID);
                    tempRed.setRedbagSeqNo(StringUtil.leftPad(String.valueOf(++seqNo), 2, "0"));
                    addRedList.add(tempRed);
                }else if (redBagConfBean.getRecodeState() == redBagConfBean.MODIFY) {
                    tempRed.setDataState(ActiveInfConstants.DATA_STATE_VALID);
                    updRedList.add(tempRed);
                }
                
            }
		}
		/*活动类型未开放修改
		 * else {
            TblActiveRedbagConfTmp tempRed = new TblActiveRedbagConfTmp();
            tempRed.setActiveNo(activeInfo.getActiveNo());
            tempRed.setUpdateTime(DateUtil.get14Date());
            tempRed.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);
            tempRed.setDataState(ActiveInfConstants.DATA_STATE_INVALID);
            ActiveRedBagInfoService.getInstance().batchDelRed......
        }*/
		
		/************************ step no 6: 持久化数据 *********************/
		//修改活动信息
		ActvInfTmpService.getInstance().updateActive(activeInfo);
		
		//批量修改支付产品信息
		ActvProdConfTmpService.getInstance().batchUpdateProdConf(activeInfo.getActiveNo(),prdList);
		
		//批量修改活动周期信息
		ActvCycleConfTmpService.getInstance().batchAddCycle(addCycleList);
		ActvCycleConfTmpService.getInstance().batchUpdateCycle(updCycleList);
		ActvCycleConfTmpService.getInstance().batchDeleteCycle(dltCycleList);
		
		//批量修改红包配置信息
		ActiveRedBagInfoService.getInstance().batchAddRedBag(addRedList);
		ActiveRedBagInfoService.getInstance().batchUpdRedBag(updRedList);
		ActiveRedBagInfoService.getInstance().batchDelRedBag(delRedList);
		
		/***********************************修改审核流程********************************************/
		//关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActInfAuditService.getInstance().selectTepList(ActiveInfConstants.AUDIT_TYPE_51);
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
		
		
		/****************** step no :6 记录操作日志 ********************/
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "修改活动,编号=[" + activeInfo.getActiveNo() + "]" });
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "暂停/恢复活动")
	public void stautsActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {


		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();


		/************************ step no 2: 封装活动基本信息 *********************/
		
		UpdateRequestBean reqBean = updateMap.get("activeInfo");
		String activeNo = reqBean.getParameter("activeNo");//活动编号
		String statusFlag = reqBean.getParameter("statuFlag");//状态标志
		
		ActiveInfTmpVO activeInfo = new ActiveInfTmpVO();
		activeInfo.setActiveNo(activeNo);
		activeInfo.setUpdateOpr(sessionUserInfo.getTlrno());// 当前操作用户
		activeInfo.setUpdateTime(DateUtil.get14Date());// 当前操作时间
		activeInfo.setAuditTime(activeInfo.getUpdateTime());// 审核时间为当前时间
		activeInfo.setSyncFlag(ActiveInfConstants.SYNC_STATE_UNDONE);// 同步状态0-已变更未同步
		activeInfo.setAuditId(ContextUtil.getUUID());//设置审核编号
		//activeInfo.setAuditOpr(auditUserName);// 审核用户
		String logMessage;
		String auditType="";
		//当页面传递操作标志为-1-恢复
		if(statusFlag.equals(ActiveInfConstants.STATUS_FLAG_RESUME)){
			activeInfo.setActiveStat(ActiveInfConstants.ACTIVE_STATE_ONGOING);//活动状态-11-进行中
			activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_07);
			auditType = ActiveInfConstants.AUDIT_TYPE_55;
			logMessage="恢复活动,编号["+activeNo+"]";
		}else{
			activeInfo.setActiveStat(ActiveInfConstants.ACTIVE_STATE_PAUSE);//活动状态-12-暂停
			activeInfo.setAuditFlag(ActiveInfConstants.AUDIT_FLAG_06);
			auditType = ActiveInfConstants.AUDIT_TYPE_54;
			logMessage="暂停活动,编号["+activeNo+"]";
		}
		
		/************************ step no 3: 更新活动信息*********************/
		
		ActvInfTmpService.getInstance().statusActive(activeInfo);
		
		/***********************************暂停/恢复审核流程********************************************/
        //关联审核流程基本信息表与审核流程步骤表,根据审核业务类型查询审核步骤信息
        List<PmpAuditProcStep> list = ActInfAuditService.getInstance().selectTepList(auditType);
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
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), logMessage });
	}
	
	
	
	
	
	
	
	 public static String getGpName(FieldBean bean, String key, ServletRequest request) throws SnowException {
	       String gpName ="";
	       if(StringUtil.isEmpty(key)){
	    	   return gpName;
	       }
	       AcctGpConfVO vo = AcctGpConfService.getInstance().getOneGpByNo(key);
	       if(vo != null){
	    	   gpName=vo.getGpTpNm();
	       } 
	       return gpName;
	}
	 public static String getProdName(FieldBean bean, String key, ServletRequest request) throws SnowException {
	       StringBuffer strBuf= new StringBuffer();
	       if(StringUtil.isEmpty(key)){
	    	   return strBuf.toString();
	       }
	       List<Object> prodList = ActvProdConfTmpService.getInstance().getProdListByActiveNo(key);
	       
	       if(prodList == null || prodList.size() ==0){
	    	   return strBuf.toString();
	       } 
	       for(Object prodObj : prodList){
	           PbsProdInfoVO prod =(PbsProdInfoVO) prodObj;
	    	   strBuf.append(prod.getProdName()).append(",");
	       }
	       return strBuf.toString().substring(0,strBuf.toString().length()-1);
	}
}
