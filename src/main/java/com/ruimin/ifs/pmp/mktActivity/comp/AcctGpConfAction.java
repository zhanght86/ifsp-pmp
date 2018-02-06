package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mktActivity.process.service.AcctGpConfService;



@SnowDoc(desc = "账户分组")
@ActionResource
public class AcctGpConfAction extends SnowAction {
    @Action
    @SnowDoc(desc = "账户分组编号，名称查询")
    public PageResult queryAcctGpConf(QueryParamBean queryBean) throws SnowException {
    	 String qgpTpNo = queryBean.getParameter("qgpTpNo");
         String qgpTpNm = queryBean.getParameter("qgpTpNm");
        
        return AcctGpConfService.getInstance().queryAcctGpConfinfo(qgpTpNo,qgpTpNm,queryBean.getPage());
      
    } 
    

    @Action
    @SnowDoc(desc = "账户分组全查询")
    public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
    	 String qgpTpNo = queryBean.getParameter("qgpTpNo");
         
    	
        return AcctGpConfService.getInstance().queryAll(qgpTpNo,queryBean.getPage());
      
    } 
    

    @Action
    @SnowDoc(desc = "账户分组活动信息查询")
    public PageResult queryActiveInfTmp(QueryParamBean queryBean) throws SnowException {
    	 String qgpTpNo = queryBean.getParameter("qgpTpNo");
         
    	
        return AcctGpConfService.getInstance().queryActiveInfTmp(qgpTpNo,queryBean.getPage());
      
    } 

    @Action
    @SnowDoc(desc = "账户小组编号和名称查询，用于")
    public PageResult queryGpNoAndName(QueryParamBean queryBean) throws SnowException {
    	String qgpTpNo = queryBean.getParameter("qGpTpNo");
        PageResult pageResult = AcctGpConfService.getInstance().getGpNoAndName(qgpTpNo,queryBean.getPage());
        return pageResult;
    } 
    
   
}



