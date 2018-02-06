/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: QrsRoutMngAction.java
 * Author:   LJY
 * Date:     2017年9月25日 下午4:53:31
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年9月25日下午4:53:31                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.bean.QrsRouteInfo;
import com.ruimin.ifs.pmp.chnlMng.process.service.QrsRoutMngService;
import com.ruimin.ifs.util.StringUtil;

import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年9月25日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
public class QrsRoutMngAction {
    
    @Action
    @SnowDoc(desc = "二维码路由的查询功能")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
        String routeId = queryBean.getParameter("qrouteId");
        String mchtId = queryBean.getParameter("qqmchtId");
        return QrsRoutMngService.getInstance().queryAll(routeId,mchtId, queryBean.getPage());
        
    }
    
    @Action
    @SnowDoc(desc = "二维码路由的新增/修改")
    public void addOrUpdQrsRout(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        /** 获取数据集 */
        UpdateRequestBean reqBean = updateMap.get("qrsRoutMng");
        
        /** 导入实体类 */
        QrsRouteInfo qInfo = new QrsRouteInfo();// 二维码路由信息表-实体类
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), qInfo);
        }
        
        //得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        
        QrsRoutMngService.getInstance().addOrUpdQrsRout(qInfo,tlrno);
        
        String msg = "[二维码路由管理 ]--新增/修改二维码路由信息 :操作员编号[Tlrno]=" +tlrno;
        sessionUserInfo.addBizLog("update.log",
                new String[] { tlrno, sessionUserInfo.getBrno(), msg });
    }
    
    @Action
    @SnowDoc(desc = "二维码路由的删除")
    public void deleteQrs(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        UpdateRequestBean reqBean = updateMap.get("qrsRoutMng");//根据数据集Id从前台得到数据
        String foo = reqBean.getParameter("foo");//从前台得到路由编号
        //得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        
        QrsRoutMngService.getInstance().delQrsRout(foo);
        String msg = "[二维码路由管理 ]--删除二维码路由 :操作员编号[Tlrno]=" +tlrno;
        sessionUserInfo.addBizLog("update.log",
                new String[] { tlrno, sessionUserInfo.getBrno(), msg });
    }
    
    @Action
    @SnowDoc(desc = "二维码路由刷新缓存")
    public void refreshQrs() throws SnowException{
        QrsRoutMngService.getInstance().refresh();
        //得到操作员编号
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String tlrno = sessionUserInfo.getTlrno();
        String msg = "[二维码路由管理 ]--刷新缓存 :操作员编号[Tlrno]=" +tlrno;
        sessionUserInfo.addBizLog("update.log",
                new String[] { tlrno, sessionUserInfo.getBrno(), msg });
        
    }
    
    public static String getPagyName(FieldBean bean, String key, ServletRequest request) throws SnowException{
        String pagyName ="";
        if (StringUtil.isEmpty(key)) {
            return pagyName;
        }
        String pagyNameById = QrsRoutMngService.getInstance().getPagyNameById(key);
        if (pagyNameById != null) {
            pagyName = pagyNameById;
        }
        return pagyName;
    }
    
    
    
    
}