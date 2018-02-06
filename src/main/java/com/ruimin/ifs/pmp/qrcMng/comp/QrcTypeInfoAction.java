package com.ruimin.ifs.pmp.qrcMng.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.qrcMng.common.constants.QrcTypeInfoConstants;
import com.ruimin.ifs.pmp.qrcMng.process.bean.QrcTypeInfo;
import com.ruimin.ifs.pmp.qrcMng.process.service.QrcTypeInfoService;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

@ActionResource
@SnowDoc(desc = "二维码类型管理")
public class QrcTypeInfoAction extends SnowAction {
	/**
	 * 
	 * 功能描述: 二维码类型查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "二维码类型查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		String qrcTypeNo = queryBean.getParameter("QrcTypeNo");
		String qrcTypeName = queryBean.getParameter("QrcTypeName");
		String qrcTypeSat = queryBean.getParameter("QrcTypeSat");
		// queryBean.getDataBusMap();
		return QrcTypeInfoService.getInstance().queryAll(qrcTypeNo, qrcTypeName, qrcTypeSat, queryBean.getPage());
	}

	/**
	 * 
	 * 功能描述: 二维码类型查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action
	@SnowDoc(desc = "二维码类型查询")
	public List<QrcTypeInfo> queryChannelType(QueryParamBean queryBean) throws SnowException {
		return QrcTypeInfoService.getInstance().queryChannelType();
	}

	/**
	 * 根据二维码的编号获取获取二维码类型名称
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getQrcTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			QrcTypeInfo beanInfo = QrcTypeInfoService.getInstance().getQrcTypeName(key);
			if (beanInfo != null) {
				return beanInfo.getQrcTypeName();
			}
		}
		return key;
	}

	/**
	 * 
	 * 功能描述: 二维码类型新增<br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "二维码类型新增")
	public void save(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcTypeInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcTypeInfo bean = new QrcTypeInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		/**
		 * 设置当前新增操作员（获取当前会话中的用户Id）
		 */
		bean.setCrtUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前新增时间
		 */
		bean.setCrtTm(BaseUtil.convertDateStrToNumStr(BaseUtil.getCurrentDateTime()));
		/**
		 * 设置修改操作员（获取当前会话中的用户Id）
		 */
		bean.setUpdUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前修改时间
		 */
		bean.setUpdTm(BaseUtil.convertDateStrToNumStr(BaseUtil.getCurrentDateTime()));
		/**
		 * 调用Service进行数据集新增
		 */
		QrcTypeInfoService.getInstance().save(bean);

		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[二维码类型管理]--二维码类型新增:类型编号[QrcTypeNo]=" + bean.getQrcTypeNo() });
	}

	/**
	 * 
	 * 功能描述: 二维码类型修改<br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "二维码类型修改")
	public void update(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcTypeInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcTypeInfo bean = new QrcTypeInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		bean.setCrtTm(BaseUtil.convertDateStrToNumStr(bean.getCrtTm()));
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
		QrcTypeInfoService.getInstance().update(bean);
		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[二维码类型管理]--二维码类型修改:类型编号[QrcTypeNo]=" + bean.getQrcTypeNo() });
	}

	/**
	 * 
	 * 功能描述:二维码类型停用/启用 <br>
	 * 〈功能详细描述〉
	 *
	 * @param updateMap
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("DataQrcTypeInfo");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		QrcTypeInfo bean = new QrcTypeInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), bean);
		}
		bean.setCrtTm(BaseUtil.convertDateStrToNumStr(bean.getCrtTm()));
		/**
		 * 设置修改操作员（获取当前会话中的用户Id）
		 */
		bean.setUpdUsr(sessionUserInfo.getTlrno());
		/**
		 * 设置当前修改时间
		 */
		bean.setUpdTm(BaseUtil.convertDateStrToNumStr(BaseUtil.getCurrentDateTime()));
		/**
		 * 调用Service进行数据集修改
		 */
		if (QrcTypeInfoConstants.QRC_TYPE_STATE_00.equals(bean.getQrcTypeSat())) {
			bean.setQrcTypeSat(QrcTypeInfoConstants.QRC_TYPE_STATE_99);
		} else {
			bean.setQrcTypeSat(QrcTypeInfoConstants.QRC_TYPE_STATE_00);
		}
		QrcTypeInfoService.getInstance().delete(bean);
		// 日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrCode(),
				"[二维码类型管理]--二维码类型停用:类型编号[QrcTypeNo]=" + bean.getQrcTypeNo() });
	}

}
