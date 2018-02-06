/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: PagyCategoryNameAction.java
 * Author:   zqy
 * Date:     2016年8月02日 上午10:39:09
 * Description: 类目名词     
 * History: 
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zqy           2016年7月27日上午10:39:09                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.mchtMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.PagyCategoryNameService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtSetlTypeService;

/**
 * 名称：类目名词
 * 功能：类目名词
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：zqy <br>
 * 说明：<br>
 */
@SnowDoc(desc="清算方法查询Action")
@ActionResource
public class MchtSetlTypeAction extends SnowAction {
    @Action
    @SnowDoc(desc="根据商户级别查询可选择的清算方式")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException{
        //获取查询参数
    	String qmchtId=queryBean.getParameter("qmchtId");
    	SnowLog.getGroupLog().info("qmchtId:" + qmchtId);
        //分页查询
        return MchtSetlTypeService.getInstance().queryList(qmchtId,queryBean.getPage());
        
    }    
}
