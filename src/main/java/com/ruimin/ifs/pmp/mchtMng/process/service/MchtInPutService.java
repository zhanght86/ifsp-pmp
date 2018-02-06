/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.mchtMng.process.service 
 * FileName: MchtInPutService.java
 * Author:   LJY
 * Date:     2017年12月12日 下午5:43:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年12月12日下午5:43:01                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.process.service;

import com.google.gson.Gson;
import com.ruim.ifsp.signature.IfspSdkSignAtureUtil;
import com.ruim.ifsp.utils.message.IfspFastJsonUtil;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;


/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年12月12日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class MchtInPutService extends SnowService{
    
    public String url =SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_IN);
    /**成功返回码*/
    public final static String RESP_CODE_0000 = "0000";
    /**流水重复返回码*/
    public final static String RESP_CODE_1020 = "1020";
    
    Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
    /**
     * 获取实例
     * 
     * @return 服务实例，单例
     * @throws SnowException
     */
    public static MchtInPutService getInstance() throws SnowException {
        return ContextUtil.getSingleService(MchtInPutService.class);
    }
    
    /**
     * 调用dispatcher异步发起商户进件
     * @param mchtId
     * @param inf
     * @throws SnowException
     */
    public void mchtIn(String mchtId,String inf) throws SnowException{
        URL addr = null;
        String requestMsg=null;
            try {
                addr = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.CACHE_SYNCHRONIZATION));
                log.info("商户[" + mchtId + "]"+inf+"审核通过异步发起商户进件【" + addr + "】-请求开始...");
                Map<String, Object> noticeRequest = new HashMap<String, Object>();
                noticeRequest.put("mchtId", mchtId);// 商户ID
                /* 调用调度中心506.506019 */
                Map<String, Object> compExpressMap = new HashMap<String, Object>();
                compExpressMap.put("maxTimes", "1");// 调度中心通知系统最大次数
                compExpressMap.put("interval", "10");// 调度中心通知系统间隔时间
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("toIds", "506019");// 调度中心通知系统的系统id
                map2.put("context", IfspFastJsonUtil.mapTOjson(noticeRequest));// 通知内容
                map2.put("compExpress", IfspFastJsonUtil.mapTOjson(compExpressMap));// 补偿方式
                requestMsg = new Gson().toJson(map2);
                String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
                /** 建立服务器连接 */
                HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(addr, requestMehtod);
                /** 发送报文 */
                HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
                /** 获取服务端响应 */
                String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
                Map<String, String> serRetMap = new HashMap<String, String>();
                Gson gson = new Gson();
                serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
                String respCode = serRetMap.get("respCode");// 响应码 
                // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
                if (respCode.substring((respCode.length() - 4), respCode.length())
                        .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                    log.info("调用dispatcher成功!");
                }else{
                    log.info("调用dispatcher失败!" );
                }
                
                
                
                
                
                
                log.info("商户[" + mchtId + "]"+inf+"审核通过异步发起商户进件【" + addr + "】-调用结束...");
                
                
                
                
            } catch (Exception e) {
                log.info("商户[" + mchtId + "]"+inf+"审核通过异步发起商户进件【" + addr + "】-失败...");
            }
      
    }
    
    /**
     * 调用dispatcher异步发起通知审批结果
     * @param mchtId
     * @throws SnowException
     */
    public void notice(String mchtId) throws SnowException{
        URL addr2 = null;
        String requestMsg=null;
            try {
                addr2 = new URL(SysParamUtil.getParam(MchtChnlRequestConstants.CACHE_SYNCHRONIZATION));
                log.info("商户[" + mchtId + "]审核通过通知审批结果【" + addr2 + "】-请求开始...");
                String reqSeqId = createSeq();
                Map<String, Object> noticeRequest = new HashMap<String, Object>();
                //生成流水
                /** modify by lengjingyu 20180126 优化内管到商户进件模块之间商户审核结果通知接口  jira-1993 */
                noticeRequest.put("mchtId",mchtId );
                noticeRequest.put("reqSeqId",reqSeqId );
                /** modify end */
                /* 调用调度中心506.506006 */
                Map<String, Object> compExpressMap = new HashMap<String, Object>();
                compExpressMap.put("maxTimes", "1");// 调度中心通知系统最大次数
                compExpressMap.put("interval", "10");// 调度中心通知系统间隔时间
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("toIds", "506006");// 调度中心通知系统的系统id
                map2.put("context", IfspFastJsonUtil.mapTOjson(noticeRequest));// 通知内容
                map2.put("compExpress", IfspFastJsonUtil.mapTOjson(compExpressMap));// 补偿方式
                requestMsg = new Gson().toJson(map2);
                String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
                /** 建立服务器连接 */
                HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(addr2, requestMehtod);
                /** 发送报文 */
                HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
                /** 获取服务端响应 */
                String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
                Map<String, String> serRetMap = new HashMap<String, String>();
                Gson gson = new Gson();
                serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
                String respCode = serRetMap.get("respCode");// 响应码 
                // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
                if (respCode.substring((respCode.length() - 4), respCode.length())
                        .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {
                    log.info("调用dispatcher成功!");
                }else{
                    log.info("调用dispatcher失败!" );
                }
                
                log.info("商户[" + mchtId + "]审核通过通知审批结果【" + addr2 + "】-调用结束...");
            } catch (Exception e) {
                log.info("商户[" + mchtId + "]审核通过通知审批结果【" + addr2 + "】-失败...");
            }
    }


    /**
     * 生成流水
     * @return
     */
    public String createSeq() {
        // 流水编号20位,默认为： 5 + 14位当前时间 + 5位随机数
        String currentDateTime = BaseUtil.getCurrentDateTime();
        String random = String.valueOf((int)((Math.random()*9+1)*10000));
        String seq = "5" + currentDateTime + random;
        return seq;
    }


    /**
     * 查询商户下是否有已经签订的合同
     * @param object
     * @throws SnowException 
     */
    public List<Object> getConInf(String mchtId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = "SELECT p.CON_ID,p.CON_STATE FROM pbs_mcht_contr_info_tmp p WHERE p.MCHT_ID = '"+mchtId+"' AND p.CON_STATE = '"+MchtContractConstants.MCHT_CONTR_STAT_NORMAL+"'";
        List<Object> list = dao.executeQuerySql(sql,MchtContractVO.class);
        return list;
        
    }
    
    /**
     * 查询商户状态是否正常
     * @param mchtId
     * @return
     * @throws SnowException
     */
    public List<Object> getMchtInf(String mchtId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = "SELECT MCHT_STAT FROM pbs_mcht_base_info_tmp WHERE MCHT_ID='"+mchtId+"' AND MCHT_STAT='"+MchtMngConstants.MCHT_STAT_NORMAL+"'";
        List<Object> list = dao.executeQuerySql(sql,PbsMchtBaseInfo.class);
        return list;
    }
    
    
    
}
