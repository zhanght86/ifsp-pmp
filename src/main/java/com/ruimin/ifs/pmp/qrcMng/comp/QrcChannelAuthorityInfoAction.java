package com.ruimin.ifs.pmp.qrcMng.comp;

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
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.qrcMng.process.bean.QrcChannelAuthorityInfo;
import com.ruimin.ifs.pmp.qrcMng.process.service.QrcChannelAuthorityInfoService;

import java.util.List;
import java.util.Map;

@ActionResource
@SnowDoc(desc = "渠道二维码权限管理")
public class QrcChannelAuthorityInfoAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 二维码渠道权限管理查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "二维码渠道权限查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qChlNo = queryBean.getParameter("qChlNo");
		String qQrcTypeNo = queryBean.getParameter("qQrcTypeNo");
		return QrcChannelAuthorityInfoService.getInstance().queryAll(qChlNo, qQrcTypeNo, queryBean.getPage());
	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理新增<br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "二维码渠道权限新增")
	public void save(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcChannelAuthorityInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcChannelAuthorityInfo bean = new QrcChannelAuthorityInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		/**
		 * 判断重复
		 */
		String chlNo = bean.getChlNo();
		String qrcTypeNo = bean.getQrcTypeNo();
		List<Object> list = QrcChannelAuthorityInfoService.getInstance().quer(chlNo, qrcTypeNo);
		if (list != null && list.size() > 0) {
			SnowExceptionUtil.throwWarnException("不能配置相同的渠道权限，请重新选择！");
		}
		/**
		 * 设置当前新增操作员（获取当前会话中的用户Id）
		 */
		bean.setCrtUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前新增时间
		 */
		bean.setCrtTm(BaseUtil.getCurrentDateTime());
		/**
		 * 设置修改操作员（获取当前会话中的用户Id）
		 */
		bean.setUpdUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前修改时间
		 */
		bean.setUpdTm(BaseUtil.getCurrentDateTime());
		/**
		 * 调用Service进行数据集新增
		 */
		QrcChannelAuthorityInfoService.getInstance().save(bean);

		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道二维码权限管理]--权限新增:权限编号[ChlAuNo]=" + bean.getChlAuNo() });
	}

	@Action
	@SnowDoc(desc = "二维码渠道权限查询")
	public List<ChannelInfo> queryChannelInfo(QueryParamBean queryBean) throws SnowException {
		return QrcChannelAuthorityInfoService.getInstance().queryChannelInfo();
	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理修改<br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "二维码渠道权限修改")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcChannelAuthorityInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcChannelAuthorityInfo bean = new QrcChannelAuthorityInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		/**
		 * 判断重复
		 */
		String chlNo = bean.getChlNo();
		String qrcTypeNo = bean.getQrcTypeNo();
		List<Object> list = QrcChannelAuthorityInfoService.getInstance().quer(chlNo, qrcTypeNo);
		if (list != null && list.size() > 0) {
			SnowExceptionUtil.throwWarnException("QCA_E0001");
		}

		bean.setCrtTm(bean.getCrtTm());
		/**
		 * 设置修改操作员（获取当前会话中的用户Id）
		 */
		bean.setUpdUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前修改时间
		 */
		bean.setUpdTm(BaseUtil.getCurrentDateTime());
		/**
		 * 调用Service进行数据集修改
		 */
		QrcChannelAuthorityInfoService.getInstance().update(bean);
		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道二维码权限管理]--权限修改:权限编号[ChlAuNo]=" + bean.getChlAuNo() });
	}

	/**
	 * 
	 * 功能描述:二维码渠道权限管理删除 <br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcChannelAuthorityInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcChannelAuthorityInfo bean = new QrcChannelAuthorityInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		/**
		 * 调用Service进行数据集删除
		 */
		QrcChannelAuthorityInfoService.getInstance().delete(bean);
		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[渠道二维码权限管理]--权限删除:权限编号[ChlAuNo]=" + bean.getChlAuNo() });
	}

}
