package com.ruimin.ifs.pmp.report.comp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruim.ifsp.utils.client.http.IfspURLUtil;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.UserAuthorityDownService;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.report.common.constants.BalAcctConstants;
import com.ruimin.ifs.pmp.report.common.constants.ReportConstants;
import com.ruimin.ifs.pmp.report.common.utils.CommonBthUtil;
import com.ruimin.ifs.pmp.report.process.bean.BthBalErrorsVO;
import com.ruimin.ifs.pmp.report.process.service.BalAcctErrorsService;

@ActionResource
@SnowDoc(desc = "对账差错action")
public class BalAcctErrorsAction extends SnowAction {
	private static Logger log = SnowLog.getLogger(BalAcctErrorsAction.class);

	/**
	 * 
	 * 功能描述: 对账结果信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "对账差错信息分页查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qStlmDateStart = queryBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = queryBean.getParameter("qStlmDateEnd");
		String qMchtInfo = queryBean.getParameter("qMchtInfo");
		String qMchtOrg = queryBean.getParameter("qMchtOrg");
		String qTxnType = queryBean.getParameter("qTxnType");
		String qErrStat = queryBean.getParameter("qErrStat");
		String qPagyNo = queryBean.getParameter("qPagyNo");
		String qThirdSsn = queryBean.getParameter("qThirdSsn");
		String qChlTxnSsn = queryBean.getParameter("qChlTxnSsn");
		String qCorrStat = queryBean.getParameter("qCorrStat");
		String qtpamOutTransactionId = queryBean.getParameter("qtpamOutTransactionId");
		String auditId = queryBean.getParameter("auditId");
		String qRefundStat = queryBean.getParameter("qRefundStat");
		// 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
		if (StringUtils.isBlank(qMchtOrg)) {
			qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
		}
		return BalAcctErrorsService.getInstance().pageQuery(qStlmDateStart, qStlmDateEnd, qMchtInfo, qMchtOrg, qTxnType,
				qErrStat, qPagyNo, qThirdSsn, qChlTxnSsn, qCorrStat,qtpamOutTransactionId,auditId,qRefundStat, queryBean.getPage());

	}

	/**
	 * 
	 * 功能描述: 手工调账<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "手工调账")
	public void manualAdjustment(BthBalErrorsVO errorVo) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		errorVo.setLastUpdTlr(sessionUserInfo.getTlrno());
		errorVo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());

		/****************** 更新调账状态 ********************/
		BalAcctErrorsService.getInstance().manualAdjustment(errorVo);

		/****************** 记录日志 ********************/
		String stateMsg = "";
		if (errorVo.getCorrStat().equals(BalAcctConstants.ADJUST_STATUS_DONE)) {
			stateMsg = "已调账";
		} else if (errorVo.getCorrStat().equals(BalAcctConstants.ADJUST_STATUS_UNDO)) {
			stateMsg = "未调账";
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						"差错手工调账:清算日期=" + errorVo.getStlmDate() + ",通道编号=" + errorVo.getPagyNo() + ",通道流水号(txnSsn)="
								+ errorVo.getTxnSsn() + "；修改调账状态为:" + stateMsg });

	}
	 /**
     * 
     * 功能描述: 手工调账审核<br>
     * 〈功能详细描述〉
     *
     * @param queryBean
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "手工调账审核")
    public void manualAdjustmentAudit(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        UpdateRequestBean reqBean = updateMap.get("balAcctErrors");// 获取数据集
        BthBalErrorsVO errorVo = new BthBalErrorsVO();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), errorVo);
        }
        String AuditId= ContextUtil.getUUID();
        //手工调账待审核
        errorVo.setAuditId(AuditId);
        errorVo.setCorrStat("02");
        errorVo.setAuditId(AuditId);
        BalAcctErrorsService.getInstance().manualAdjustmentAudit(errorVo);
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno, "32");
        // 循环插入到审核记录表中
        if (list == null || list.size() == 0) {
            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
        }

        MchtMngService.getInstance().addStepInfo(list, AuditId);
        /********** 审核：录入审核信息表中 *********/
        BalAcctErrorsService.getInstance().addAuditInfo("10003003",AuditId,"32","手工调账审核"+errorVo.getChlTxnSsn(),"balAcctErrorsQueryAudit.jsp",sessionUserInfo);
    }
	@Action
	@SnowDoc(desc = "对账差错交易查询报表下载")
	public void downloadReport(FormRequestBean requestBean) throws UnsupportedEncodingException, SnowException {
	    //判断用户是否有权限下载报表
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno=sessionUserInfo.getTlrno();
        String str=UserAuthorityDownService.getInstance().qTlrRole(tlrno, "1022005");
        if(tlrno.equals(str)){              
        }else{
            SnowExceptionUtil.throwErrorException("此用户没有权限下载");
        }
	    
	    String qStlmDateStart = requestBean.getParameter("qStlmDateStart");
		String qStlmDateEnd = requestBean.getParameter("qStlmDateEnd");
		String qMchtInfo = requestBean.getParameter("qMchtInfo");
		String qMchtOrg = requestBean.getParameter("qMchtOrg");
		String qMchtOrgName = requestBean.getParameter("qMchtOrgName");
		String qTxnType = requestBean.getParameter("qTxnType");
		String qErrStat = requestBean.getParameter("qErrStat");
		String qPagyNo = requestBean.getParameter("qPagyNo");
		String qThirdSsn = requestBean.getParameter("qThirdSsn");
		String qChlTxnSsn = requestBean.getParameter("qChlTxnSsn");
		String qCorrStat = requestBean.getParameter("qCorrStat");
		String qtpamOutTransactionId = requestBean.getParameter("qtpamOutTransactionId");
		String auditId = requestBean.getParameter("auditId");
		//机构号为空，去当前操作员所属机构
		if (StringUtils.isBlank(qMchtOrg)) {
			qMchtOrg = SessionUtil.getSessionUserInfo().getBrCode();
		}else{
			//机构号不为空，转换机构名称
			qMchtOrgName = IfspURLUtil.URLDecoder(qMchtOrgName, "UTF-8");
		}
		//商户信息不为空，转换商户信息
		if (StringUtils.isNotBlank(qMchtInfo)) {
			qMchtInfo = IfspURLUtil.URLDecoder(qMchtInfo, "UTF-8");
		}
		//通道名称不为空，转换通道名称
		if (StringUtils.isNotBlank(qPagyNo)) {
			qPagyNo = IfspURLUtil.URLDecoder(qPagyNo, "UTF-8");
		}
		try {
			/** 报表文件里面需要进行处理的参数 */
			Map<String, Object> reportParam = new HashMap<String, Object>();
			reportParam.put("qStlmDateStart", qStlmDateStart);
			reportParam.put("qStlmDateEnd", qStlmDateEnd);
			reportParam.put("qMchtInfo", qMchtInfo);
			reportParam.put("qMchtOrg", qMchtOrgName);
			
			reportParam.put("qTxnType", qTxnType);

			reportParam.put("qErrStat", qErrStat);
			reportParam.put("qPagyNo", qPagyNo);
			reportParam.put("qThirdSsn", qThirdSsn);
			reportParam.put("qChlTxnSsn", qChlTxnSsn);
			reportParam.put("qCorrStat", qCorrStat);

			/** 查询报表数据 */

			// 如果查询选择的机构为空，则默认查询本机构和本机构下的子级机构商户
			
			List<Object> dataList = BalAcctErrorsService.getInstance().queryForExport(qStlmDateStart, qStlmDateEnd,
					qMchtInfo, qMchtOrg, qTxnType, qErrStat, qPagyNo, qThirdSsn, qChlTxnSsn, qCorrStat,qtpamOutTransactionId,auditId,sessionUserInfo);

			if (IfspDataVerifyUtil.isEmptyList(dataList)) {
				SnowExceptionUtil.throwErrorException("查询数据为空，无法生成报表");
			}

			/** 获取报表路径 **/
			String path = requestBean.getRequest().getSession().getServletContext()
					.getRealPath(ReportConstants.JASPER_PATH_BAL_ACCT_ERRORS);

			/******************************
			 * STEP NO2 组装报表&输出Excel
			 ******************************/
			CommonBthUtil.genBthOutExcel(path, ReportConstants.EXPORT_NAME_BAL_ACCT_ERRORS, reportParam, dataList,
					requestBean);

		} catch (Exception e) {
			log.error("下载对账差错报表异常：", e);
			SnowExceptionUtil.throwErrorException(e.getMessage());
		}

		
	}
	   /**
     * 
     * 功能描述: 退款<br>
     * 〈功能详细描述〉
     *
     * @param queryBean
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "退款")
    public void manualReturns(BthBalErrorsVO errorVo) throws SnowException {
        // 获取当前用户
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        try {
            BalAcctErrorsService.getInstance().returnsRequest(errorVo);
        } catch (Exception e) {
            log.info("差错退款调用失败" + errorVo.getThirdSsn());
            SnowExceptionUtil.throwErrorException(e.getMessage());
        }
    }
    /**
     * 
     * 功能描述: 退款审核<br>
     * 〈功能详细描述〉
     *
     * @param queryBean
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "退款审核")
    public void manualReturnsAudit(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        UpdateRequestBean reqBean = updateMap.get("balAcctErrors");// 获取数据集
        BthBalErrorsVO errorVo = new BthBalErrorsVO();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), errorVo);
        }
        String AuditId= ContextUtil.getUUID();
        //退款待审核
        errorVo.setRefundStat("04");
        errorVo.setAuditId(AuditId);
        BalAcctErrorsService.getInstance().BthBalErrorsAudit(errorVo);
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno, "30");
        // 循环插入到审核记录表中
        if (list == null || list.size() == 0) {
            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
        }

        MchtMngService.getInstance().addStepInfo(list, AuditId);
        /********** 审核：录入审核信息表中 *********/
        BalAcctErrorsService.getInstance().addAuditInfo("10003001",AuditId,"30","差错退款审核"+errorVo.getChlTxnSsn(),"balAcctErrorsQueryAudit.jsp",sessionUserInfo);
    }
    /**
     * 
     * 功能描述: 退款查询<br>
     * 〈功能详细描述〉
     *
     * @param queryBean
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "退款查询")
    public void manualQuery(BthBalErrorsVO errorVo) throws SnowException {
        // 获取当前用户
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        try {
            BalAcctErrorsService.getInstance().QueryRequest(errorVo);
        } catch (Exception e) {
            log.info("差错退款调用失败" + errorVo.getThirdSsn());
            SnowExceptionUtil.throwErrorException(e.getMessage());
        }
    }
    /**
     * 
     * 功能描述: 退款查询审核<br>
     * 〈功能详细描述〉
     *
     * @param queryBean
     * @return
     * @throws SnowException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "退款查询审核")
    public void manualQueryAudit(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        UpdateRequestBean reqBean = updateMap.get("balAcctErrors");// 获取数据集
        BthBalErrorsVO errorVo = new BthBalErrorsVO();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), errorVo);
        }
        String AuditId= ContextUtil.getUUID();
        //退款待审核
        errorVo.setRefundStat("05");
        errorVo.setAuditId(AuditId);
        BalAcctErrorsService.getInstance().BthBalErrorsAudit(errorVo);
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno, "31");
        // 循环插入到审核记录表中
        if (list == null || list.size() == 0) {
            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
        }

        MchtMngService.getInstance().addStepInfo(list, AuditId);
        /********** 审核：录入审核信息表中 *********/
        BalAcctErrorsService.getInstance().addAuditInfo("10003002",AuditId,"31","差错退款审核"+errorVo.getChlTxnSsn(),"balAcctErrorsQueryAudit.jsp",sessionUserInfo);
    }
}
