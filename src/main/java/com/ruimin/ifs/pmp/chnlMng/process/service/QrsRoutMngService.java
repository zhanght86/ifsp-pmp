/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.process.service 
 * FileName: QrsRoutMngService.java
 * Author:   LJY
 * Date:     2017年9月26日 上午9:47:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年9月26日上午9:47:14                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import com.google.gson.Gson;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.QrsRouteInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.HttpClientUtils;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年9月26日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class QrsRoutMngService {
    public String url =SysParamUtil.getParam(MchtChnlRequestConstants.Qrs_Rout_Mng_REQUEST_SEVR_URL);
    public boolean base64Flag = false;
    /**成功返回码*/
    public static final String RESP_SUCCESS = "2010000";
    /**没有对应路由配置*/
    public static final String RESP_NOCONF= "2010400";
    
    public static QrsRoutMngService getInstance() throws SnowException{
        
        return ContextUtil.getSingleService(QrsRoutMngService.class);
    }
    
    /**
     * 根据路由编号查询二维码路由信息
     * @param routeId
     * @param page
     * @return
     * @throws SnowException
     */
    public PageResult queryAll(String routeId,String mchtId,Page page) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.qrsRoutInfo.queryByRoutId",
                RqlParam.map().set("routeId", StringUtils.isBlank(routeId) ? "" :"%"+ routeId+"%")
                .set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%"+mchtId+"%"),
                page);
    }
    
    /**
     * 调用接口:新增/修改
     * @param qInfo
     * @param tlrNo
     * @throws SnowException 
     */
    @SuppressWarnings("unchecked")
    public void addOrUpdQrsRout(QrsRouteInfo qInfo,String tlrNo) throws SnowException{
       
        Map<String , String> params = new HashMap<>();
        String routeId ;
        if (qInfo.getRouteId()==null||qInfo.getRouteId().length()<=0) {
            routeId = getRoutId();
        }else {
            routeId = qInfo.getRouteId();
        }
        String arrMchtId = qInfo.getMchtId();
        String arrProId = qInfo.getProdId();
        String arrAcct = qInfo.getAcctType();
        String mchtId = "";
        String proId = "";
        String acctType = "";
        //如果包含*,则只传*,否则将,转成|
        if (arrMchtId.contains("*")) {
            mchtId = "*";
        }else {
            mchtId = arrMchtId.replaceAll(",", "|");
        }
        if (arrProId.contains("*")) {
            proId = "*";
        }else {
            proId = arrProId.replaceAll(",", "|");
        }
        if (arrAcct.contains("*")) {
            acctType = "*";
        }else {
            acctType = arrAcct.replaceAll(",", "|");
        }
        params.put("routeId", routeId);
        params.put("mchtId", mchtId);
        params.put("prodId", proId);
        params.put("acctType", acctType);
        params.put("pagyNo", qInfo.getPagyNo());
        params.put("priority",qInfo.getPriority());
        params.put("status", qInfo.getStatus());
        params.put("routeDesc", qInfo.getRouteDesc());
        params.put("tlrNo", tlrNo);
        String send = HttpClientUtils.send(params, "201.route.addorupdate", url, base64Flag);
        params = new Gson().fromJson(send, params.getClass());
        String respC = params.get("respCode");
        String respM = params.get("respMsg");
        if (!RESP_SUCCESS.equals(respC)) {
            SnowExceptionUtil.throwWarnException(respM);
        }
    }
    
    /**
     * 获取路由编号最大值
     * @param currentDate
     * @return
     */
    private String queryMaxRoutId(String currentDate) {
        DBDao dao = DBDaos.newInstance();
        currentDate = "%" + currentDate + "%";
        return (String) dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.qrsRoutInfo.queryMaxRoutId", currentDate);
    }
    
    /**
     * 生成路由编号[如果当日没有记录，采用默认编号；反之找出最大编号加一]
     * @return
     * @throws SnowException
     */
    public String getRoutId() throws SnowException {
        // 路由编号15位,默认为： 6 + 8位当前日期 + 000001
        // 每天都会从默认值开始计算
        String currentDate = BaseUtil.getCurrentDate();
        String routId = "6" + currentDate + "000001";
        String maxId = queryMaxRoutId(currentDate);
        if (!StringUtils.isBlank(maxId)) {
            routId = String.valueOf(Long.parseLong(maxId) + 1);
        }
        return routId;
    }
    
    
    /**
     *  调用接口:删除
     * @param routeId
     * @throws SnowException 
     */
    @SuppressWarnings("unchecked")
    public void delQrsRout(String routeId) throws SnowException{
        Map<String , String> params = new HashMap<>();
        params.put("routeId", routeId);
        String send = HttpClientUtils.send(params, "201.route.del", url, base64Flag);
        params = new Gson().fromJson(send, params.getClass());
        String respC = params.get("respCode");
        String respM = params.get("respMsg");
        /*if (RESP_NOCONF.equals(respC)) {
            SnowExceptionUtil.throwWarnException(respM);
        }*/
        if (!RESP_SUCCESS.equals(respC)) {
            if (RESP_NOCONF.equals(respC)) {
                
            }else {
                SnowExceptionUtil.throwWarnException(respM);
            }
        }
    }
    
    /**
     * 刷新缓存
     * @throws SnowException 
     */
    @SuppressWarnings("unchecked")
    public void refresh() throws SnowException{
        Map<String , String> params = new HashMap<String , String>();
        String send = HttpClientUtils.sendNoParam("201.route.refresh", url);
        params = new Gson().fromJson(send, params.getClass());
        String respC = params.get("respCode");
        String respM = params.get("respMsg");
        if (!RESP_SUCCESS.equals(respC)) {
            SnowExceptionUtil.throwWarnException(respM);
        }
        
    }
    
    /**
     * 根据通道编号查询名称
     * @param pagyNo
     * @return
     */
    public String getPagyNameById(String pagyNo){
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.chnlMng.rql.utils.queryPagyById",
                RqlParam.map().set("pagyNo", StringUtil.isBlank(pagyNo)?"":pagyNo)
                );
        
    }
    
    
}
