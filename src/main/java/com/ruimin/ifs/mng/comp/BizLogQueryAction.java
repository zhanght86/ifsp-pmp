package com.ruimin.ifs.mng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.mng.common.constants.StaffConstants;
import com.ruimin.ifs.mng.process.service.BizLogService;

@SnowDoc(desc = "系统日志查询")
@ActionResource
public class BizLogQueryAction extends SnowAction {
    /**
     * 
     * 功能描述: 查询功能
     * 
     * @param queryBean
     * @return 返回类型 PageResult
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
        String tlrno = queryBean.getParameter("tlrno");
        String oprcode1 = queryBean.getParameter("oprcode1");
        String startDate = queryBean.getParameter("startDate");
        String endDate = queryBean.getParameter("endDate");
        
        
        return BizLogService.getInstance().queryList(oprcode1, tlrno, startDate, endDate,
                SessionUserInfo.getSessionUserInfoNoException().getBrCode(), queryBean.getPage());
    }

}
