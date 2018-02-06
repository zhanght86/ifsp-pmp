package com.ruimin.ifs.pmp.report.process.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.constant.IfspConstants;
import com.ruim.ifsp.utils.id.IfspId;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.pmp.report.process.bean.BthAcctNoParam;
import com.ruimin.ifs.pmp.report.process.bean.BthBalErrorsVO;
import com.ruimin.ifs.pmp.report.process.bean.PagyAlipayTxnInfo;
import com.ruimin.ifs.pmp.report.process.bean.PagyEciticTxnBillInfo;
import com.ruimin.ifs.pmp.report.process.bean.PagyEciticTxnInfo;
import com.ruimin.ifs.pmp.report.process.bean.PagyMixpayTxnInfo;
import com.ruimin.ifs.pmp.report.process.bean.PagySwiftTxnBillInfo;
import com.ruimin.ifs.pmp.report.process.bean.PagySwiftpassTxnInfo;
import com.ruimin.ifs.util.DateUtils;

@Service
@SnowDoc(desc = "对账差错信息service")
public class BalAcctErrorsService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static BalAcctErrorsService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BalAcctErrorsService.class);
	}
	private static String Date;

	/**
	 * 
	 * 功能描述: 对账结果信息分页查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qStlmDateStart
	 * @param qStlmDateEnd
	 * @param qMchtInfo
	 * @param qMchtOrg
	 * @param qTxnType
	 * @param qErrStat
	 * @param qPagyNo
	 * @param qThirdSsn
	 * @param qChlTxnSsn
	 * @param qCorrStat
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult pageQuery(String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qTxnType, String qErrStat, String qPagyNo, String qThirdSsn, String qChlTxnSsn, String qCorrStat,String qtpamOutTransactionId,
			String auditId,String qRefundStat,Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
	    SessionUserInfo session= SessionUtil.getSessionUserInfo();
	    int i=(int) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getRoleIdByTlrno",RqlParam.map().set("tlrno", session.getTlrno()));
	    if(i==41||i==11){
	        return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctErrors.queryListRole",
	                RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
	                        .set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
	                        .set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
	                                : "%" + qMchtInfo + "%")
	                        .set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType).set("qMchtOrg", qMchtOrg)
	                        .set("qErrStat", StringUtils.isBlank(qErrStat) ? "" : qErrStat)
	                        .set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
	                        .set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
	                                : "%" + qThirdSsn + "%")
	                        .set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? ""
	                                : "%" + qChlTxnSsn + "%")
	                        .set("qCorrStat", StringUtils.isBlank(qCorrStat) ? "" : qCorrStat)
	                        .set("qtpamOutTransactionId", StringUtils.isBlank(qtpamOutTransactionId) ? "" : qtpamOutTransactionId)
	                        .set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
	                        .set("qRefundStat", StringUtils.isBlank(qRefundStat) ? "" : qRefundStat),
	                page);
	    }else{
	        return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctErrors.queryList",
	                RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
	                        .set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
	                        .set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
	                                : "%" + qMchtInfo + "%")
	                        .set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType).set("qMchtOrg", qMchtOrg)
	                        .set("qErrStat", StringUtils.isBlank(qErrStat) ? "" : qErrStat)
	                        .set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
	                        .set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
	                                : "%" + qThirdSsn + "%")
	                        .set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? ""
	                                : "%" + qChlTxnSsn + "%")
	                        .set("qCorrStat", StringUtils.isBlank(qCorrStat) ? "" : qCorrStat)
	                        .set("qtpamOutTransactionId", StringUtils.isBlank(qtpamOutTransactionId) ? "" : qtpamOutTransactionId)
	                        .set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
	                        .set("qRefundStat", StringUtils.isBlank(qRefundStat) ? "" : qRefundStat),
	                page);  
	    }
	}
	/**手动调账*/
	public void manualAdjustment(BthBalErrorsVO errorVo)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.manualAdjustment", errorVo);
	}
	   /**手动调账审核*/
    public void manualAdjustmentAudit(BthBalErrorsVO errorVo)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.manualAdjustmentAudit", errorVo);
    }
	   /**退款审核*/
    public void BthBalErrorsAudit(BthBalErrorsVO errorVo)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrorsAudit", errorVo);
    }
	public List queryForExport(String qStlmDateStart, String qStlmDateEnd, String qMchtInfo, String qMchtOrg,
			String qTxnType, String qErrStat, String qPagyNo, String qThirdSsn, String qChlTxnSsn, String qCorrStat,String qtpamOutTransactionId,String auditId,SessionUserInfo sessionUserInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		 int i=(int) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.auditCommon.getRoleIdByTlrno",RqlParam.map().set("tlrno", sessionUserInfo.getTlrno()));
	    if(i==41||i==11){
	        return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctErrors.queryList2",
	                RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
	                        .set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
	                        .set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
	                                : "%" + qMchtInfo + "%")
	                        .set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType).set("qMchtOrg", qMchtOrg)
	                        .set("qErrStat", StringUtils.isBlank(qErrStat) ? "" : qErrStat)
	                        .set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
	                        .set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
	                                : "%" + qThirdSsn + "%")
	                        .set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? ""
	                                : "%" + qChlTxnSsn + "%")
	                        .set("qCorrStat", StringUtils.isBlank(qCorrStat) ? "" : qCorrStat)
	                        .set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
	                        .set("qtpamOutTransactionId", StringUtils.isBlank(qtpamOutTransactionId) ? "" : qtpamOutTransactionId));
	    }else{
		return dao.selectList("com.ruimin.ifs.pmp.report.rql.balAcctErrors.queryList1",
				RqlParam.map().set("qStlmDateStart", StringUtils.isBlank(qStlmDateStart) ? "" : qStlmDateStart)
						.set("qStlmDateEnd", StringUtils.isBlank(qStlmDateEnd) ? "" : qStlmDateEnd)
						.set("qMchtInfo", StringUtils.isBlank(qMchtInfo) ? ""
								: "%" + qMchtInfo + "%")
						.set("qTxnType", StringUtils.isBlank(qTxnType) ? "" : qTxnType).set("qMchtOrg", qMchtOrg)
						.set("qErrStat", StringUtils.isBlank(qErrStat) ? "" : qErrStat)
						.set("qPagyNo", StringUtils.isBlank(qPagyNo) ? "" : qPagyNo)
						.set("qThirdSsn", StringUtils.isBlank(qThirdSsn) ? ""
								: "%" + qThirdSsn + "%")
						.set("qChlTxnSsn", StringUtils.isBlank(qChlTxnSsn) ? ""
								: "%" + qChlTxnSsn + "%")
						.set("qCorrStat", StringUtils.isBlank(qCorrStat) ? "" : qCorrStat)
						.set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
						.set("qtpamOutTransactionId", StringUtils.isBlank(qtpamOutTransactionId) ? "" : qtpamOutTransactionId));
        
      }
	}
	/**
     * 差错退款调用通道
     * 
     * @param 
     *            
     * @param 
     *            
     * @return
     * @throws Exception
     * @throws SnowException
     */
    @Action
    public void returnsRequest(BthBalErrorsVO errorVo) throws Exception, SnowException {
        /** 加载SnowLog */
        Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);

        /******************************************** 计时开始 ********************************************/
        Long startTime = System.currentTimeMillis();
        URL url = null;
        String requestMsg=null;
        try {
            log.info("差错退款请求【" + url + "】-请求开始...");
            log.info("差错订单号：" + errorVo.getThirdSsn());
            if("311".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.ERRORS_REFUND_311));                
                requestMsg=returnsErrors(errorVo.getThirdSsn());               
            }else if("304".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.ERRORS_REFUND_304));
                requestMsg=returnsErrorsAli(errorVo);
            }else if("312".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.ERRORS_REFUND_312));
                requestMsg=returnsErrorsPA(errorVo);
            /** modify by fengwei 20180116 中信差错退款,退款地址，组装报文   jira-1962 */
            }else if("313".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.ERRORS_REFUND_313));                
                requestMsg=returnsErrorsEcitic(errorVo.getThirdSsn());
            /** modify end jira-1962 */ 
            }else{
                SnowExceptionUtil.throwErrorException("此通道不支持差错退款");
            }
            String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
            /** 建立服务器连接 */
            HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
            
            /** 发送报文 */
            HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
            
            // -----------------------------------------STEP NO3
            // 获取响应-----------------------------------------//
            /** 获取服务端响应 */
            String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
            Map<String, String> serRetMap = new HashMap<String, String>();
            Gson gson = new Gson();
            serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
            String respCode = serRetMap.get("respCode");// 响应码 
            // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
            if (respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                DBDao dao = DBDaos.newInstance();
                errorVo.setRefundStat("00");
                dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);
                String QueryrespCode= Query(errorVo);
                if (QueryrespCode.substring((QueryrespCode.length() - 4), QueryrespCode.length())
                        .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                    log.info("调用记账完成" + errorVo.getThirdSsn());
                }else if("settlementMark".equals(QueryrespCode)){
                    log.info("不参与记账" + errorVo.getThirdSsn());
                }else{
                    log.info("调用记账错误" + errorVo.getThirdSsn());
                    /** modify by fengwei 20180116 差错退款,错误修改  jira-1962 */
                    SnowExceptionUtil.throwErrorException("调用记账错误");
                    /** modify end jira-1962 */ 
                }
//                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
                log.info("差错退款成功" + errorVo.getThirdSsn());
            }else if(respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS_0002)||
                    respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS_0005)||
                    respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS_0008)||
                    respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS_1020)){
                DBDao dao = DBDaos.newInstance();
                errorVo.setRefundStat("02");
                dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);
                log.info("差错退款调用受理成功请尽快发起查询" + errorVo.getThirdSsn());
//                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
            }else{
                DBDao dao = DBDaos.newInstance();
                errorVo.setRefundStat("01");
                dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);
                log.info("差错退款调用失败" + errorVo.getThirdSsn());
                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
            }

            /******************************************** 计时结束 ********************************************/
            log.info("差错退款请求【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

        } catch (SnowException se) {
            SnowExceptionUtil.throwErrorException(se.getMessage());
            log.error("差错退款请求【" + url + "差错订单号：" + errorVo.getThirdSsn() + "】，原因：" + se.getMessage());
            log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
        }
    }
    /**
     * 差错退款查询调用通道
     * 
     * @param 
     *            
     * @param 
     *            
     * @return
     * @throws Exception
     * @throws SnowException
     */
    @Action
    public void QueryRequest(BthBalErrorsVO errorVo) throws Exception, SnowException {
        
        /** 加载SnowLog */
        Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);

        /******************************************** 计时开始 ********************************************/
        Long startTime = System.currentTimeMillis();
        URL url = null;
        String requestMsg=null;
        try {
            log.info("差错退款请求【" + url + "】-请求开始...");
            log.info("差错订单号：" + errorVo.getThirdSsn());
            if("311".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.Query_REFUND_311));                               
                /** 组装报文 */
                requestMsg=QueryErrors(errorVo);                
            }else if("312".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.Query_REFUND_312));                               
                /** 组装报文 */
                requestMsg=QueryErrorsPA(errorVo); 
            /** modify by fengwei 20180116 获取中信差错退款查询地址,组装报文  jira-1962 */
            }else if("313".equals(errorVo.getPagyNo())){
                url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.Query_REFUND_313));                               
                /** 组装报文 */
                requestMsg=QueryErrorsEcitic(errorVo); 
            }
            /** modify end jira-1962 */ 
            String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
            /** 建立服务器连接 */
            HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
            
            /** 发送报文 */
            HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
            
            // -----------------------------------------STEP NO3
            // 获取响应-----------------------------------------//
            /** 获取服务端响应 */
            String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
            Map<String, String> serRetMap = new HashMap<String, String>();
            Gson gson = new Gson();
            serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
            String respCode = serRetMap.get("respCode");// 响应码 
            if (respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                DBDao dao = DBDaos.newInstance();
                String origRespCode = serRetMap.get("origRespCode");// 响应码 
                if("0000".equals(origRespCode)){
                    errorVo.setRefundStat("00");
                    dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);
//                    SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
                    log.info("差错退款成功" + errorVo.getThirdSsn());
                   String QueryrespCode= Query(errorVo);
                   if (QueryrespCode.substring((QueryrespCode.length() - 4), QueryrespCode.length())
                           .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                       log.info("调用记账完成" + errorVo.getThirdSsn());
                   }else if("00009997".equals(QueryrespCode)){
                       log.info("不参与记账" + errorVo.getThirdSsn());
                   }else{
                       log.info("调用记账错误" + errorVo.getThirdSsn());
                       /** modify by fengwei 20180116 差错退款,错误修改  jira-1962 */
                       SnowExceptionUtil.throwErrorException("调用记账错误");
                       /** modify end jira-1962 */ 
                   }
                }else if("9999".equals(origRespCode)){
                    errorVo.setRefundStat("01");
                    dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);
                    
                }else if("0002".equals(origRespCode)){
                    errorVo.setRefundStat("02");
                    dao.executeUpdate("com.ruimin.ifs.pmp.report.rql.balAcctErrors.BthBalErrors", errorVo);                    
                }
               
            }else{
                log.info("差错退款调用失败" + errorVo.getThirdSsn());
                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
            }
           

            /******************************************** 计时结束 ********************************************/
            log.info("差错退款查询请求【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

        } catch (SnowException se) {
            SnowExceptionUtil.throwErrorException(se.getMessage());
            log.error("差错退款查询请求【" + url + "差错订单号：" + errorVo.getThirdSsn() + "】，原因：" + se.getMessage());
            log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
        }
    }
    @Action
    public String returnsErrors(String ThirdSsn) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagySwiftTxnBillInfo PagySwiftTxnBillInfo=(com.ruimin.ifs.pmp.report.process.bean.PagySwiftTxnBillInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.returnsErrors", ThirdSsn);
        String Time=PagySwiftTxnBillInfo.getTxnTime();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date=(Date) sdf2.parse(Time);
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Time=format.format(date);
        String amttemp=PagySwiftTxnBillInfo.getAmtSumTrans();
        int i=(int) (Double.valueOf(amttemp)*100);
        String amt=String.valueOf(i);
        resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
        resultMap.put("txnCode", "311007");// 交易类型代码，最大8位
        resultMap.put("pagySysId", "401");//请求方系统代码
        resultMap.put("pagyType", "01");//通道接入类型
        resultMap.put("pagyAcqNo", PagySwiftTxnBillInfo.getMainMchtNo());//通道机构接入编号// 主商户号
        resultMap.put("pagyMerId", PagySwiftTxnBillInfo.getSubMchtNo());//商户编号//子商户号
        resultMap.put("txnSsn", "401"+ContextUtil.getUUID());// 通道核心流水号
        resultMap.put("txnTime", Time);// 通道核心时间
        resultMap.put("chlNo", "C0001");//渠道号
        resultMap.put("chlMerId","1111");//渠道商户号
        resultMap.put("chlTxnSsn", "1111");//渠道流水号
        resultMap.put("chlTxnTime", Time);//渠道流水时间
        resultMap.put("origRespTxnSsn", PagySwiftTxnBillInfo.getMerOrderId());//原交易流水号
        resultMap.put("origRespTxnTime", Time);//时间格式转换,原交易时间
        resultMap.put("TxnAmt",amt);//支付交易金额
        resultMap.put("TxnCcyType", "156");//支付币种
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    /** modify by fengwei 20180116 差错退款,中信退款组装报文  jira-1962 */
    @Action
    public String returnsErrorsEcitic(String ThirdSsn) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagyEciticTxnBillInfo pagyEciticTxnBillInfo=(com.ruimin.ifs.pmp.report.process.bean.PagyEciticTxnBillInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.returnsErrorsEcitic", ThirdSsn);
        String Time=pagyEciticTxnBillInfo.getTxnTime();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date=(Date) sdf2.parse(Time);
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Time=format.format(date);
        String amttemp=pagyEciticTxnBillInfo.getAmtSumTrans();
        int i=(int) (Double.valueOf(amttemp)*100);
        String amt=String.valueOf(i);
        resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
        resultMap.put("txnCode", "313007");// 交易类型代码，最大8位
        resultMap.put("pagySysId", "401");//请求方系统代码
        resultMap.put("pagyType", "01");//通道接入类型
        resultMap.put("pagyAcqNo", pagyEciticTxnBillInfo.getMainMchtNo());//通道机构接入编号// 主商户号
        resultMap.put("pagyMerId", pagyEciticTxnBillInfo.getSubMchtNo());//商户编号//子商户号
        resultMap.put("txnSsn", "401"+ContextUtil.getUUID());// 通道核心流水号
        resultMap.put("txnTime", Time);// 通道核心时间
        resultMap.put("chlNo", "C0001");//渠道号
        resultMap.put("chlMerId","1111");//渠道商户号
        resultMap.put("chlTxnSsn", "1111");//渠道流水号
        resultMap.put("chlTxnTime", Time);//渠道流水时间
        resultMap.put("origRespTxnSsn", pagyEciticTxnBillInfo.getMerOrderId());//原交易流水号
        resultMap.put("origRespTxnTime", Time);//时间格式转换,原交易时间
        resultMap.put("TxnAmt",amt);//支付交易金额
        resultMap.put("TxnCcyType", "156");//支付币种
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    /** modify end jira-1962 */ 
    @Action
    public String returnsErrorsPA(BthBalErrorsVO errorVo) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagyMixpayTxnInfo PagyMixpayTxnInfo = null;
        PagyMixpayTxnInfo=(com.ruimin.ifs.pmp.report.process.bean.PagyMixpayTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.returnsErrorsPA", errorVo.getThirdSsn());
        String mchtNo=(String) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryPagySubMcht", PagyMixpayTxnInfo.getChlMchtId());   
        String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位        
        resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
        resultMap.put("txnCode", "312102");// 交易类型代码，最大8位
        resultMap.put("pagySysId", "401");//请求方系统代码        
        resultMap.put("pagyType", "01");//通道接入类型     
        resultMap.put("pagyAcqNo", PagyMixpayTxnInfo.getPagyAcqNo());//通道机构接入编号// 主商户号
        resultMap.put("pagyMerId", mchtNo);//商户编号//子商户号
        resultMap.put("chlNo", PagyMixpayTxnInfo.getChlNo());//渠道号
        resultMap.put("chlMerId",PagyMixpayTxnInfo.getChlMchtId());//渠道商户号
        resultMap.put("txnAmt",PagyMixpayTxnInfo.getPayTxnAmt());//支付交易金额
        resultMap.put("txnCcyType", "156");//支付币种
        resultMap.put("txnSsn", "401"+IfspId.getUUID(29));// 通道核心流水号
        resultMap.put("txnTime", currentTime);// 通道核心时间                
        resultMap.put("chlTxnSsn","820"+IfspId.getUUID(29));//渠道流水号
        resultMap.put("chlTxnTime", currentTime);//渠道流水时间
        resultMap.put("origRespTxnSsn", PagyMixpayTxnInfo.getPagySeqId());//原交易流水号
        resultMap.put("origRespTxnTime", PagyMixpayTxnInfo.getPagySeqTm());//时间格式转换,原交易时间
        resultMap.put("openId", PagyMixpayTxnInfo.getTpamOpenId());//
        resultMap.put("openKey", PagyMixpayTxnInfo.getTpamOpenKey());//                
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    @Action
    public String returnsErrorsAli(BthBalErrorsVO errorVo) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagyAlipayTxnInfo PagyAlipayTxnInfo = null;
            PagyAlipayTxnInfo=(com.ruimin.ifs.pmp.report.process.bean.PagyAlipayTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.returnsErrorsAlipay", errorVo.getThirdSsn());
            resultMap.put("pagyType", "03");//通道接入类型           
            resultMap.put("txnCode", "304006");// 交易类型代码，最大8位
            resultMap.put("pagyAcqNo", PagyAlipayTxnInfo.getTpamMchtId());//通道机构接入编号// 主商户号
            resultMap.put("pagyMerId", PagyAlipayTxnInfo.getTpamSecMchtNo());//商户编号//子商户号
            resultMap.put("chlNo", PagyAlipayTxnInfo.getChlNo());//渠道号
            resultMap.put("chlMerId",PagyAlipayTxnInfo.getChlMchtId());//渠道商户号
            resultMap.put("chlTxnSsn","820"+IfspId.getUUID(29));//渠道流水号
            resultMap.put("chlTxnTime", PagyAlipayTxnInfo.getChlTxnTime());//渠道流水时间
            resultMap.put("origRespTxnSsn", PagyAlipayTxnInfo.getPagySeqId());//原交易流水号
            resultMap.put("origRespTxnTime", PagyAlipayTxnInfo.getPagySeqTm());//时间格式转换,原交易时间
            resultMap.put("txnAmt",PagyAlipayTxnInfo.getPayTxnAmt());//支付交易金额
        String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
        resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
        resultMap.put("pagySysId", "401");//请求方系统代码
        resultMap.put("txnSsn", "401"+IfspId.getUUID(29));// 通道核心流水号
        resultMap.put("txnTime", currentTime);// 通道核心时间
        resultMap.put("txnCcyType", "156");//支付币种
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    @Action
    public String QueryErrorsPA(BthBalErrorsVO errorVo) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagyMixpayTxnInfo PagyMixpayTxnInfo= (com.ruimin.ifs.pmp.report.process.bean.PagyMixpayTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryErrorsPA", errorVo.getThirdSsn());
        String mchtNo=(String) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryPagySubMcht", PagyMixpayTxnInfo.getChlMchtId());   
        Date=PagyMixpayTxnInfo.getPagySeqTm().substring(0, 8);
            resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
            resultMap.put("txnCode", "312203");// 交易类型代码，最大8位
            resultMap.put("pagySysId", "401");//请求方系统代码
            resultMap.put("pagyType", "01");//通道接入类型
            resultMap.put("pagyAcqNo", PagyMixpayTxnInfo.getPagyAcqNo());//通道机构接入编号// 主商户号
            resultMap.put("pagyMerId", mchtNo);//商户编号//子商户号
            resultMap.put("origRespTxnSsn", PagyMixpayTxnInfo.getPagySeqId());//原交易流水号
            resultMap.put("origRespTxnTime", PagyMixpayTxnInfo.getPagySeqTm());//时间格式转换,原交易时间
            resultMap.put("openId", PagyMixpayTxnInfo.getTpamOpenId());//
            resultMap.put("openKey", PagyMixpayTxnInfo.getTpamOpenKey());//
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    @Action
    public String QueryErrors(BthBalErrorsVO errorVo) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
            PagySwiftpassTxnInfo PagySwiftpassTxnInfo= (com.ruimin.ifs.pmp.report.process.bean.PagySwiftpassTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryErrorsSwiftpay", errorVo.getThirdSsn());
            Date=PagySwiftpassTxnInfo.getPagySeqTm().substring(0, 8);
            resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
            resultMap.put("txnCode", PagySwiftpassTxnInfo.getPagyTxnType());// 交易类型代码，最大8位
            resultMap.put("pagySysId", PagySwiftpassTxnInfo.getPagySeqId());//请求方系统代码
            resultMap.put("pagyType", PagySwiftpassTxnInfo.getPagyType());//通道接入类型
            resultMap.put("pagyAcqNo", PagySwiftpassTxnInfo.getTpamMchtId());//通道机构接入编号// 主商户号
            resultMap.put("pagyMerId", PagySwiftpassTxnInfo.getTpamOrgId());//商户编号//子商户号
            resultMap.put("txnSsn", PagySwiftpassTxnInfo.getPayTxnSsn());// 通道核心流水号
            resultMap.put("txnTime", PagySwiftpassTxnInfo.getPayTxnTime());// 通道核心时间
            resultMap.put("chlNo", PagySwiftpassTxnInfo.getChlNo());//渠道号
            resultMap.put("chlMerId",PagySwiftpassTxnInfo.getChlMchtId());//渠道商户号
            resultMap.put("chlTxnSsn",PagySwiftpassTxnInfo.getChlTxnSsn());//渠道流水号
            resultMap.put("chlTxnTime", PagySwiftpassTxnInfo.getChlTxnTime());//渠道流水时间
            resultMap.put("origRespTxnSsn", PagySwiftpassTxnInfo.getPagySeqId());//原交易流水号
            resultMap.put("origRespTxnTime", PagySwiftpassTxnInfo.getPagySeqTm());//时间格式转换,原交易时间
            resultMap.put("txnAmt",PagySwiftpassTxnInfo.getPayTxnAmt());//支付交易金额
            resultMap.put("txnCcyType", "156");//支付币种        
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    /** modify by fengwei 20180116 差错退款,中信退款查询组装报文  jira-1962 */
    @Action
    public String QueryErrorsEcitic(BthBalErrorsVO errorVo) throws Exception, SnowException {
        Map<String, String> resultMap = new HashMap<String, String>();
        DBDao dao = DBDaos.newInstance();
        PagyEciticTxnInfo pagyEciticTxnInfo= (com.ruimin.ifs.pmp.report.process.bean.PagyEciticTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryErrorsEcitic", errorVo.getThirdSsn());
            Date=pagyEciticTxnInfo.getPagySeqTm().substring(0, 8);
            resultMap.put("encoding", IfspConstants.UTF_8_ENCODING);
            resultMap.put("txnCode", pagyEciticTxnInfo.getPagyTxnType());// 交易类型代码，最大8位
            resultMap.put("pagySysId", pagyEciticTxnInfo.getPagySeqId());//请求方系统代码
            resultMap.put("pagyType", pagyEciticTxnInfo.getPagyType());//通道接入类型
            resultMap.put("pagyAcqNo", pagyEciticTxnInfo.getTpamMchtId());//通道机构接入编号// 主商户号
            resultMap.put("pagyMerId", pagyEciticTxnInfo.getTpamOrgId());//商户编号//子商户号
            resultMap.put("txnSsn", pagyEciticTxnInfo.getPayTxnSsn());// 通道核心流水号
            resultMap.put("txnTime", pagyEciticTxnInfo.getPayTxnTime());// 通道核心时间
            resultMap.put("chlNo", pagyEciticTxnInfo.getChlNo());//渠道号
            resultMap.put("chlMerId",pagyEciticTxnInfo.getChlMchtId());//渠道商户号
            resultMap.put("chlTxnSsn",pagyEciticTxnInfo.getChlTxnSsn());//渠道流水号
            resultMap.put("chlTxnTime", pagyEciticTxnInfo.getChlTxnTime());//渠道流水时间
            resultMap.put("origRespTxnSsn", pagyEciticTxnInfo.getPagySeqId());//原交易流水号
            resultMap.put("origRespTxnTime", pagyEciticTxnInfo.getPagySeqTm());//时间格式转换,原交易时间
            resultMap.put("txnAmt",pagyEciticTxnInfo.getPayTxnAmt());//支付交易金额
            resultMap.put("txnCcyType", "156");//支付币种        
        String retMsg = new Gson().toJson(resultMap);
        return retMsg;
        
    }
    /** modify end jira-1962 */ 
    @Action
    public String Query(BthBalErrorsVO errorVo) throws Exception, SnowException {
        /** 加载SnowLog */
        Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);

        /******************************************** 计时开始 ********************************************/
        Long startTime = System.currentTimeMillis();
        URL url = null;
        String requestMsg=null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String respCode = null;
        String deAcctNo = null;
        String crAcctNo = null;
        DBDao dao = DBDaos.newInstance();
        String settlementMark=null;
        try {
            if("311".equals(errorVo.getPagyNo())){
                PagySwiftpassTxnInfo PagySwiftpassTxnInfo= (com.ruimin.ifs.pmp.report.process.bean.PagySwiftpassTxnInfo) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QueryErrorsSwiftpay", errorVo.getThirdSsn());
                settlementMark=(String) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.QuerySettlementMark",PagySwiftpassTxnInfo.getTpamMchtId());
            /** modify by fengwei 20180116 差错退款,中信退款记账  jira-1962 */
            }else if("304".equals(errorVo.getPagyNo())||"312".equals(errorVo.getPagyNo())||"313".equals(errorVo.getPagyNo())){
            /** modify end jira-1962 */ 
                settlementMark="00"; 
            }
            if("00".equals(settlementMark)){
            if("311".equals(errorVo.getPagyNo())){
                resultMap.put("absText",DateUtils.getCurDateyyyyMMdd() +"_二维码支付_威富通_退款");
                BthAcctNoParam bthAcctNoParam_de=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo","311")
                        .set("ACCT_NO_FLAG",  "JSYWCK"));
                BthAcctNoParam bthAcctNoParam_cr=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo", "100")
                        .set("ACCT_NO_FLAG", "ZSDFK"));
                crAcctNo= bthAcctNoParam_cr.getAcctNo();
                deAcctNo = bthAcctNoParam_de.getAcctNo();
            }else if("304".equals(errorVo.getPagyNo())){
                BthAcctNoParam bthAcctNoParam_de=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo","304")
                        .set("ACCT_NO_FLAG",  "JSYWCK"));
                BthAcctNoParam bthAcctNoParam_cr=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo", "100")
                        .set("ACCT_NO_FLAG", "ZSDFK"));
                crAcctNo= bthAcctNoParam_cr.getAcctNo();
                deAcctNo = bthAcctNoParam_de.getAcctNo();
                resultMap.put("absText", DateUtils.getCurDateyyyyMMdd()+"_二维码支付_支付宝直连_退款");
            }
            else if("312".equals(errorVo.getPagyNo())){
                BthAcctNoParam bthAcctNoParam_de=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo","312")
                        .set("ACCT_NO_FLAG",  "JSYWCK"));
                BthAcctNoParam bthAcctNoParam_cr=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo", "100")
                        .set("ACCT_NO_FLAG", "ZSDFK"));
                crAcctNo= bthAcctNoParam_cr.getAcctNo();
                deAcctNo = bthAcctNoParam_de.getAcctNo();
                resultMap.put("absText", DateUtils.getCurDateyyyyMMdd()+"_二维码支付_平安银行通道_退款");
            /** modify by fengwei 20180116 差错退款,中信退款记账账户  jira-1962 */
            } else if("313".equals(errorVo.getPagyNo())){
                BthAcctNoParam bthAcctNoParam_de=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo","313")
                        .set("ACCT_NO_FLAG",  "JSYWCK"));
                BthAcctNoParam bthAcctNoParam_cr=  (BthAcctNoParam) dao.selectOne("com.ruimin.ifs.pmp.report.rql.balAcctErrors.selectByPrimaryKey",
                        RqlParam.map().set("paygNo", "100")
                        .set("ACCT_NO_FLAG", "ZSDFK"));
                crAcctNo= bthAcctNoParam_cr.getAcctNo();
                deAcctNo = bthAcctNoParam_de.getAcctNo();
                resultMap.put("absText", DateUtils.getCurDateyyyyMMdd()+"_二维码支付_中信通道_退款");
            }
            /** modify end jira-1962 */ 
        log.info("差错退款请求【" + url + "】-请求开始...");
        url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.CHATINACCT));                
        /** 组装报文 */
        String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
        /** 建立服务器连接 */
        HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
        List<Object> list = new ArrayList<>();
        String amt=errorVo.getTxnAmt();
        int i= (int) (Double.parseDouble(amt)*100);
        amt=String.valueOf(i);
        HashMap<String, String> map_de = new HashMap<>();
        map_de.put("amt", amt);
        map_de.put("acctNo", deAcctNo);
        map_de.put("drCrFlg", "D");
        list.add(map_de);

        HashMap<String, String> map_cr = new HashMap<>();
        map_cr.put("amt", amt);
        map_cr.put("acctNo", crAcctNo);
        map_cr.put("drCrFlg", "C");
        list.add(map_cr);

        // 系统调用日期‰
        resultMap.put("acctDate", DateUtils.getCurDateyyyyMMdd());
        resultMap.put("drTtlCnt", 1);
        resultMap.put("crTtlCnt", 1);
        resultMap.put("drTtlAmt", amt);
        resultMap.put("crTtlAmt", amt);
        resultMap.put("absCd", "QRS");
        resultMap.put("acctEntry", list);
        requestMsg= new Gson().toJson(resultMap);
        /** 发送报文 */
        HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
        
        // -----------------------------------------STEP NO3
        // 获取响应-----------------------------------------//
        /** 获取服务端响应 */
        String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
        Map<String, String> serRetMap = new HashMap<String, String>();
        Gson gson = new Gson();
        serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
        respCode=serRetMap.get("respCode");// 响应码 
            }else{
                respCode="00009997";  
            }
        } catch (SnowException se) {
            SnowExceptionUtil.throwErrorException(se.getMessage());
            log.error("差错退款查询请求【" + url + "差错订单号：】，原因：" + se.getMessage());
            log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
        }
        return respCode;
   
    }
    /**
     * 新增审核流水(公共方法，根据商户状态，对应审核信息表不同的审核业务类型)
     * 
     * @param
     * @throws SnowException
     */
    public void addAuditInfo(String auditProcId,String AuditId,String AuditType,String AuditDesc,String Url ,SessionUserInfo sessionUserInfo) throws SnowException {
     // 根据操作员机构编号，找到对应的机构级别，根据机构级别找到审核流程编号
        String brno = sessionUserInfo.getBrno();
        PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
        PmpAuditInfo.setAuditId(AuditId);// 审核信息编号
        PmpAuditInfo.setAuditType(AuditType);// 审核业务类型,00-商户信息登记；
        PmpAuditInfo.setAuditDesc(AuditDesc);// 审核信息描述
        PmpAuditInfo.setAuditProcId(auditProcId);// 审核流程编号
        PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
                                                                        // 14位
        PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
        PmpAuditInfo.setApplyBrNo(brno);// 申请机构编号
        PmpAuditInfo.setAuditUrl(Url);// 审核信息路径
        PmpAuditInfo.setAuditState(MchtMngConstants.AUDIT_STATE_00);// 审核状体00-未审核；01-审核通过；02-审核拒绝；
        PmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
        PmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
                                                                        // 14位

        DBDao dao = DBDaos.newInstance();
        dao.insert(PmpAuditInfo);
    }
}
