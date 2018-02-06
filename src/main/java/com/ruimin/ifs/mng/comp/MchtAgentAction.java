/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.comp 
 * FileName: MchtAgentAction.java
 * Author:   Administrator
 * Date:     2017年6月7日 上午9:25:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月7日上午9:25:53                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.mng.comp;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.common.util.AgentUtils;
import com.ruimin.ifs.mng.process.bean.AcctInst;
import com.ruimin.ifs.mng.process.bean.MchtAgent;
import com.ruimin.ifs.mng.process.service.MchtAgentService;
import com.ruimin.ifs.pmp.baseParaMng.process.service.EtcOrgInfoService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 名称：代理商Action〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年6月7日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@ActionResource
@SnowDoc(desc = "代理商管理")
public class MchtAgentAction  {
    /**
     * 查询代理商信息表
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryAgent(QueryParamBean queryBean) throws SnowException {
        String qagentId = queryBean.getParameter("qagentId");// 代理商编号
        String qagentNames = queryBean.getParameter("qagentName");// 代理商名称
        return MchtAgentService.getInstance().queryAgent(qagentId, qagentNames, queryBean.getPage());
        
    }
    /**
     * 代理商信息表新增
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "新增")
    public void addMchtAgent(Map<String, UpdateRequestBean> updateBean) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        MchtAgent mchtAgent=new MchtAgent();
        UpdateRequestBean reqBean = updateBean.get("mchtAgent");
        // 获取数据集
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), mchtAgent);
        }
        AgentUtils agent=new AgentUtils();
        String AgentId=agent.agentId();
        mchtAgent.setAgentId(AgentId);
        /** 补充字段数据 */
        String currentDateTime = BaseUtil.getCurrentDateTime();
        mchtAgent.setCrtTlr(sessionUserInfo.getTlrno());
        mchtAgent.setCrtDateTime(currentDateTime);
        mchtAgent.setLastUpdTlr(sessionUserInfo.getTlrno());
        mchtAgent.setLastUpdDateTime(currentDateTime);
        MchtAgentService.getInstance().addMchtAgent(mchtAgent);
        /** step no 5 : 记录日志 */
        sessionUserInfo.addBizLog("update.log",
                new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                         " 新增代理商信息：代理商编号="+mchtAgent.getAgentId()});
    }
    /**
     * 代理商信息表修改
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "修改")
    public void updMchtAgent(Map<String, UpdateRequestBean> updateBean) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        MchtAgent mchtAgent=new MchtAgent();
        UpdateRequestBean reqBean = updateBean.get("mchtAgent");
        // 获取数据集
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), mchtAgent);
        }
        /** 补充字段数据 */
        String currentDateTime = BaseUtil.getCurrentDateTime();
        mchtAgent.setLastUpdTlr(sessionUserInfo.getTlrno());
        mchtAgent.setLastUpdDateTime(currentDateTime);
        MchtAgentService.getInstance().updMchtAgent(mchtAgent);
        /** step no 5 : 记录日志 */
        sessionUserInfo.addBizLog("update.log",
                new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                         " 修改代理商信息：代理商编号="+mchtAgent.getAgentId()});
    }
    /**
     * 代理商信息表删除
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "删除")
    public void dltMchtAgent(Map<String, UpdateRequestBean> updateBean) throws SnowException {
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        MchtAgent mchtAgent=new MchtAgent();
        UpdateRequestBean reqBean = updateBean.get("mchtAgent");
        // 获取数据集
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), mchtAgent);
        }
        /** 补充字段数据 */
        
        String list=MchtAgentService.getInstance().queryAgentIdMcht(mchtAgent.getAgentId());
        if(list !=null){
            SnowExceptionUtil.throwWarnException("WEB_E0047","该代理机构有商户，不允许删除！");
        }
        MchtAgentService.getInstance().dltMchtAgent(mchtAgent);
        /** step no 4 : 记录日志 */
        sessionUserInfo.addBizLog("update.log",
                new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                         " 删除代理商信息：代理商编号="+mchtAgent.getAgentId()});
    }
    /**
     * 政通内部机构号查询
     * 
     * @return
     * @throws SnowException
     */
       @Action
        @SnowDoc(desc = "政通内部机构号查询")
        public PageResult queryAcctInst(QueryParamBean queryBean) throws SnowException {
            String acctInstName = queryBean.getParameter("acctInstName", "");
            String qacctInstNo = queryBean.getParameter("qacctInstNo", "");
            return MchtAgentService.getInstance().queryAcctInst(qacctInstNo,acctInstName, queryBean.getPage());
           
       }
       /**
         * 开户行导入
         * 
         * @return
         * @throws SnowException
         */
          @Action
          @SnowDoc(desc = "开户行导入")
          public void OrgImport(File file) throws SnowException{
              //开户机构实体类
              AcctInst acctInst=null;             
              try {  
                  // 创建输入流，读取Excel  
                  InputStream is = new FileInputStream(file.getAbsolutePath());  
                  // jxl提供的Workbook类  
                  Workbook wb = Workbook.getWorkbook(is);  
                  // Excel的页签数量  
                  int sheet_size = wb.getNumberOfSheets();  
                  for (int index = 0; index < sheet_size; index++) {  
                      // 每个页签创建一个Sheet对象  
                      Sheet sheet = wb.getSheet(index);  
                      // sheet.getRows()返回该页的总行数  
                      for (int i = 0; i < sheet.getRows(); i++) {
                          String acctInstNo=sheet.getCell(0, i).getContents();
                          String acctInstName=sheet.getCell(1, i).getContents();
                          if(IfspDataVerifyUtil.isEmpty(acctInstNo)||IfspDataVerifyUtil.isEmpty(acctInstName)){
                              SnowExceptionUtil.throwErrorException("第"+(i)+"行开户机构编号或开户机构名称为空，请重新填写！"); 
                          }
                          if(acctInstNo.length()>12){
                              SnowExceptionUtil.throwErrorException("第"+(i)+"行开户机构编号长度不合适，请重新填写！");
                          }
                          if(acctInstName.length()>40){
                              SnowExceptionUtil.throwErrorException("第"+(i)+"行开户机构名称长度不合适，请重新填写！");
                          }
                          acctInst=new AcctInst();
                          acctInst.setAcctInstNo(acctInstNo);;
                          acctInst.setAcctInstName(acctInstName);;
                          MchtAgentService.getInstance().OrgImportAdd(acctInst);
                      }  
                  }  
              } catch (FileNotFoundException e) {  
                  e.printStackTrace();  
              } catch (BiffException e) {  
                  e.printStackTrace();  
              } catch (IOException e) {  
                  e.printStackTrace();  
              }  
          }  
      	/**
      	 * 根据代理商编号查找代理商名
      	 * 
      	 * @param bean
      	 * @param key
      	 *            代理商编号
      	 * @param request
      	 * @return 代理商名称
      	 * @throws SnowException
      	 */
      	@Action
      	@SnowDoc(desc = "根据代理商编号查找代理商名")
      	public static String getAgentName(FieldBean bean, String key, ServletRequest request)
      			throws SnowException {
      		if (StringUtil.isBlank(key)) {
      			return key;
      		}
      		String name = MchtAgentService.getInstance().getAgentNameById(key);
      		if (StringUtil.isBlank(name)) {
      			return key;
      		}
      		return name;
      	}

}
