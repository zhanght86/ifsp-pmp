package com.ruimin.ifs.pmp.mchtAppMng.comp;

import java.util.Map;

import com.ruim.ifsp.utils.beans.IfspBeanUtils;
import com.ruim.ifsp.utils.datetime.IfspDateTime;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.service.RoleMngEntryService;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.EventBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.EventVo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.EventMngService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.UserMngService;

@ActionResource
@SnowDoc(desc = "用户管理")
public class EventMngAction {
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
		String qeventTitle = queryBean.getParameter("qeventTitle");
		String qphoneNo = queryBean.getParameter("qphoneNo");
		String qmchtId = queryBean.getParameter("qmchtId");
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");
		String qeventStat = queryBean.getParameter("qeventStat");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();
		String brcode = sessionUserInfo.getBrCode();
		String roleId = RoleMngEntryService.getInstance().queryRoleByTlrno(tlrno);
		/**返回查询结果*/
		result = EventMngService.getInstance().queryMain(tlrno,brcode,roleId,qcrtDate,qeventTitle,qphoneNo,qmchtId,qmchtSimpleName,qeventStat, queryBean.getPage());
		SnowLog.getServerLog().info("事件信息查询: [ 记录总数："+result.getTotalCount()+" ]");
		return result;
	}
		
	@Action
	@SnowDoc(desc = "事件处理")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException{
		UpdateRequestBean reqBean = updateMap.get("eventMng");
		EventVo vo = new EventVo();
		EventBaseInfo eventVo = new EventBaseInfo();//商户信息实体类
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), vo);
		}
		IfspBeanUtils.copyProperties(vo, eventVo);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		eventVo.setUpdTlr(sessionUserInfo.getTlrno());//创建操作员
		eventVo.setUpdDate(IfspDateTime.getYYYYMMDD());
		eventVo.setEventStat(vo.getEventStat1());
		UserMngService.getInstance().update(eventVo);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"处理申报事件：事件编号=" +eventVo.getEventId() });
	}
}
