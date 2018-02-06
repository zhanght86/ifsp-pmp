package com.ruimin.ifs.pmp.brokerageAppMng.comp;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.ruim.ifsp.utils.datetime.DateUtil;
import com.ruim.ifsp.utils.id.IfspId;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.baseParaMng.process.service.BankBinService;
import com.ruimin.ifs.pmp.brokerageAppMng.common.constant.BrokerageAppConstants;
import com.ruimin.ifs.pmp.brokerageAppMng.process.bean.BbsAppInfoVO;
import com.ruimin.ifs.pmp.brokerageAppMng.process.service.BrokerageAppMngService;
import com.ruimin.ifs.pmp.pubTool.util.Base64Coder;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@ActionResource
public class BrokerageAppMngAction extends SnowAction {
	/**
	 * 查询券商app列表
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询记录")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		// 获取appid
		String qAppId = queryBean.getParameter("qAppId");
		// 获取app名称
		String qAppName = queryBean.getParameter("qAppName");
		// 获取券商名称
		String qTraderName = queryBean.getParameter("qTraderName");
		// 获取状态
		String qStat = queryBean.getParameter("qStat");
		// 获取机构号
		String qOrgId = queryBean.getParameter("qOrgId");

		return BrokerageAppMngService.getInstance().pageQuery(qAppId, qAppName, qTraderName, qOrgId, qStat,
				queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增记录")
	public void add(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		BbsAppInfoVO appInfo = new BbsAppInfoVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("appMng");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), appInfo);
		}
		/** step no 2 : 补充字段数据 */
		String str=BrokerageAppMngService.getInstance().queryAppId(appInfo.getAppid());
		if(appInfo.getAppid().equals(str)){
	          SnowExceptionUtil.throwWarnException("券商id重复！");
		}
		String currentDateTime = BaseUtil.getCurrentDateTime();
		appInfo.setCrtTlr(sessionUserInfo.getTlrno());
		appInfo.setCrtDateTime(currentDateTime);
		appInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		appInfo.setLastUpdDateTime(currentDateTime);
		appInfo.setStat(BrokerageAppConstants.APP_STAT_ENABLE);
		appInfo.setFreeAmt(BaseUtil.transYuanToFen(appInfo.getFreeAmt()));
		/** step no 3 : 记录app信息 */
		String Md5Key;
        try {
            Md5Key = Base64Coder.encoded(appInfo.getMd5Key());
            appInfo.setMd5Key(Md5Key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		BrokerageAppMngService.getInstance().add(appInfo);
		/** step no 4 : 记录日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"新增 券商app：appID=" + appInfo.getAppid() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改记录")
	public void update(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		BbsAppInfoVO appInfo = new BbsAppInfoVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("appMng");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), appInfo);
		}
		/** step no 2 : 补充字段数据 */
		String currentDateTime = BaseUtil.getCurrentDateTime();
		appInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		appInfo.setLastUpdDateTime(currentDateTime);
		appInfo.setFreeAmt(BaseUtil.transYuanToFen(appInfo.getFreeAmt()));
		/** step no 3 : 记录app信息 */
		BrokerageAppMngService.getInstance().update(appInfo);
		/** step no 4 : 记录日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"修改 券商app：appID=" + appInfo.getAppid() });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用/停用记录")
	public void disableOrEnable(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		BbsAppInfoVO appInfo = new BbsAppInfoVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("appMng");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), appInfo);
		}
		/** step no 2 : 补充字段数据 */
		String currentDateTime = BaseUtil.getCurrentDateTime();
		appInfo.setLastUpdTlr(sessionUserInfo.getTlrno());
		appInfo.setLastUpdDateTime(currentDateTime);
		/** step no 3 : 记录app信息 */
		String newStatus = "";
		if(appInfo.getStat().equals(BrokerageAppConstants.APP_STAT_ENABLE)){
			newStatus = BrokerageAppConstants.APP_STAT_DISABLE;
		}else if(appInfo.getStat().equals(BrokerageAppConstants.APP_STAT_DISABLE)){
			newStatus = BrokerageAppConstants.APP_STAT_ENABLE;
		}
		BrokerageAppMngService.getInstance().changeStatus(appInfo.getAppid(), sessionUserInfo.getTlrno(), newStatus);
		/** step no 4 : 记录日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				(newStatus.equals(BrokerageAppConstants.APP_STAT_ENABLE)?"启用":"停用")+" 券商app：appID=" + appInfo.getAppid() });
	}

}
