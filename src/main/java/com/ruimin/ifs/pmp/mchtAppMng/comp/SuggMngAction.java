package com.ruimin.ifs.pmp.mchtAppMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.mng.process.service.RoleMngEntryService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.SuggMngService;

@ActionResource
@SnowDoc(desc = "意见建议管理")
public class SuggMngAction {
	/**
	 * 查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException{
		PageResult result = null;
		/**查询条件*/
		String qcrtDate = queryBean.getParameter("qcrtDate");
		String qrate = queryBean.getParameter("qrate");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();
		String brcode = sessionUserInfo.getBrCode();
		String roleId = RoleMngEntryService.getInstance().queryRoleByTlrno(tlrno);
		/**返回查询结果*/
		result = SuggMngService.getInstance().queryMain(tlrno,brcode,roleId,qcrtDate,qrate, queryBean.getPage());
		SnowLog.getServerLog().info("意见建议信息查询: [ 记录总数："+result.getTotalCount()+" ]");
		return result;
	}
	
	@Action
    @SnowDoc(desc = "意见建议更新状态")
    public String updStat(QueryParamBean queryBean) throws SnowException{
        /**查询条件*/
        String suggId = queryBean.getParameter("suggId");
        
        SuggMngService.getInstance().upd(suggId);
        return "OK";
    }
	
//	@Action(propagation = Propagation.REQUIRED)
//    @SnowDoc(desc = "意见建议更新状态")
//    public void update(FormRequestBean bean) throws Exception {
//        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
//        String suggId = bean.getParameter("suggId");
//        /***************************** STEP NO1 解析数据 ****************************************/  
//        /**导入实体类*/
//        //实体类对象插入到数据库中
//        SuggMngService.getInstance().upd(suggId);
//        sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
//                    "[系统消息]--修改:意见建议编号[suggId]=" + suggId });            
//	}
}
