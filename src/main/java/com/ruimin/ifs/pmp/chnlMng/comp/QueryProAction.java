/*
 * Copyright (C), 2015-2017, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: QueryProAction.java
 * Author:   LJY
 * Date:     2017年10月24日 下午3:42:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   LJY           2017年10月24日下午3:42:42                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.process.service.QueryProService;
import com.ruimin.ifs.pmp.payProdMng.process.bean.PbsProdInfoVO;
import com.ruimin.ifs.util.StringUtil;

import java.util.List;

import javax.servlet.ServletRequest;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2017年10月24日 <br>
 * 作者：LJY <br>
 * 说明：<br>
 */
@SnowDoc(desc = "产品管理")
@ActionResource
public class QueryProAction extends SnowAction{
        @Action
        @SnowDoc(desc = "产品信息查询")
        public PageResult queryPbsProdInfo(QueryParamBean queryBean) throws SnowException {
            String qprodId = queryBean.getParameter("qprodId");
            String qprodName = queryBean.getParameter("qprodName");
            String qprodState = queryBean.getParameter("qprodState");
            return QueryProService.getInstance().queryPbsProdInfo(qprodId, qprodName, qprodState, queryBean.getPage());

    }
        
        public static String getProdName(FieldBean bean, String key, ServletRequest request) throws SnowException {
            StringBuffer strBuf= new StringBuffer();
            if(StringUtil.isEmpty(key)){
                return strBuf.toString();
            }
            List<Object> prodNameByIds = QueryProService.getInstance().getProdNameByIds(key);
            if (null == prodNameByIds||prodNameByIds.size() == 0) {
                return strBuf.toString();
            }
            for (Object object : prodNameByIds) {
                PbsProdInfoVO prod =(PbsProdInfoVO) object;
                strBuf.append(prod.getProdName()).append("|");
            }
            return strBuf.toString().substring(0,strBuf.toString().length()-1);
     }  
        
}
