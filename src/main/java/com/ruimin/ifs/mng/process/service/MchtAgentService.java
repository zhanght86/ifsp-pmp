/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.mng.process.service 
 * FileName: MchtAgentService.java
 * Author:   Administrator
 * Date:     2017年6月7日 上午9:30:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   Administrator           2017年6月7日上午9:30:51                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.mng.process.bean.AcctInst;
import com.ruimin.ifs.mng.process.bean.MchtAgent;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 名称：代理商管理<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年6月7日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
@Service
@SnowDoc(desc = "代理商管理")
public class MchtAgentService extends SnowService {

    /**
     * 获取实例
     * 
     * @return 服务实例，单例
     * @throws SnowException
     */
    public static MchtAgentService getInstance() throws SnowException {
        return ContextUtil.getSingleService(MchtAgentService.class);
    }
    /**
     * 根据代理商名称和代理商编号进行查询代理商信息
     * */
    public PageResult queryAgent(String agentId,String agentNames, Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryAgent",
                RqlParam.map().set("agentId",StringUtils.isBlank(agentId) ? "" : "%"+agentId+"%")
                        .set("agentNames", StringUtils.isBlank(agentNames) ? "" : "%"+agentNames+"%"),
                         page);

    }
    public void addMchtAgent(MchtAgent mchtAgent) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.insert(mchtAgent);
    }

    public void updMchtAgent(MchtAgent mchtAgent) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.update(mchtAgent);
    }

    public void dltMchtAgent(MchtAgent mchtAgent) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.delete(mchtAgent);
    }
    /**
     * 查询政通内部机构号
     * 
     * @param acctInstName
     *            开户机构名称
     * @param page
     *            分页查询
     * @return
     */
    public PageResult queryAcctInst(String acctInstNo, String acctInstName, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryAcctInst",
                RqlParam.map().set("acctInstName", acctInstName)
                .set("agentId", acctInstNo),
                page);
        
    }
    /**
     * 查询代理商是否有商户
     * 
     * @param acctInstName
     *            开户机构名称
     * @param page
     *            分页查询
     * @return
     */
    public String queryAgentIdMcht(String agentId) {
        DBDao dao = DBDaos.newInstance();
        String m=  (String) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.queryAgentIdMcht",agentId);      
        return m;
    }
    /**
     * 开户机构批量导入
     * 
     */
    public void OrgImportAdd(AcctInst acctInst) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.insert(acctInst);
    }
    public String agentId(){
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.agentId");
    }
    
    public String getAgentNameById(String agentId)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return (String) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.getAgentNameById",agentId);
   }
   
}
