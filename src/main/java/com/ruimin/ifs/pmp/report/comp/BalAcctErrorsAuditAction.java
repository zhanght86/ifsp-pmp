/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.report.comp 
 * FileName: BalAcctErrorsAuditAction.java
 * Author:   Administrator
 * Date:     2017年10月9日 下午4:27:40
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年10月9日下午4:27:40                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.report.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mchtMng.comp.AuditCommonAction;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.report.process.bean.BthBalErrorsVO;
import com.ruimin.ifs.pmp.report.process.service.BalAcctErrorsService;

import java.util.HashMap;
import java.util.Map;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月9日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@ActionResource
public class BalAcctErrorsAuditAction {
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "通过")
    public synchronized Map<String, Object> pass(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        Map<String, Object> map = new HashMap<String, Object>();
        UpdateRequestBean reqBean = updateMap.get("balAcctErrors");// 获取数据集
        BthBalErrorsVO errorVo = new BthBalErrorsVO();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), errorVo);
        }
        BalAcctErrorsAction balAcctErrorsAction=new BalAcctErrorsAction();
        // 获取公共方法实体对象
        AuditCommonAction auditCommonAction = new AuditCommonAction();
        // 获取审核意见
        String auditView = reqBean.getParameter("auditView");
        if("02".equals(errorVo.getCorrStat())){
            errorVo.setCorrStat("00");
            balAcctErrorsAction.manualAdjustment(errorVo);
            map.put("flag", "true");
        }else if("04".equals(errorVo.getRefundStat())){
            balAcctErrorsAction.manualReturns(errorVo);
            map.put("flag", "true");
        }else if("05".equals(errorVo.getRefundStat())){
            balAcctErrorsAction.manualQuery(errorVo);
            map.put("flag", "true");
        }
        // 2.更改审核信息表状态，AUDIT_STATE 01-审核通过；
        auditCommonAction.updateAuditInfoState(errorVo.getAuditId());
        return map;
    }
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "拒绝")
    public synchronized void refuse(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        UpdateRequestBean reqBean = updateMap.get("balAcctErrors");// 获取数据集
        BthBalErrorsVO errorVo = new BthBalErrorsVO();
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), errorVo);
        }
        BalAcctErrorsAction balAcctErrorsAction=new BalAcctErrorsAction();
        if("02".equals(errorVo.getCorrStat())){
            errorVo.setCorrStat("01");
            balAcctErrorsAction.manualAdjustment(errorVo);
        }else if("04".equals(errorVo.getRefundStat())){
            errorVo.setRefundStat("06");
            BalAcctErrorsService.getInstance().BthBalErrorsAudit(errorVo);
        }else if("05".equals(errorVo.getRefundStat())){
            errorVo.setRefundStat("02");
            BalAcctErrorsService.getInstance().BthBalErrorsAudit(errorVo);
        }
        // 获取公共方法实体对象
        AuditCommonAction auditCommonAction = new AuditCommonAction();
     // 获取审核意见
        String auditView = reqBean.getParameter("auditView");
        //
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据操作员编号，获取角色编号
        String roleId = auditCommonAction.getRoleIdByTlrno(tlrno).toString();
        // 1.记录此次审核记录，更新到审核记录表中
        auditCommonAction.updateAuditStepInfoRefuse(errorVo.getAuditId(), Integer.parseInt(roleId), tlrno,
                auditView);
        // 2.更改审核信息表状态，AUDIT_STATE 02-审核拒绝；,审核意见
        auditCommonAction.updateAuditInfoRefuse(errorVo.getAuditId());
    }
}
