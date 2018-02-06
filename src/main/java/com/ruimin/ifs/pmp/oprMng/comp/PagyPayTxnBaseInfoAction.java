package com.ruimin.ifs.pmp.oprMng.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.oprMng.process.bean.PagyPayTxnBaseInfo;
import com.ruimin.ifs.pmp.oprMng.process.bean.PagyPayTxnRel;
import com.ruimin.ifs.pmp.oprMng.process.service.PagyPayTxnBaseInfoService;
import com.ruimin.ifs.util.BaseUtil;

public class PagyPayTxnBaseInfoAction {
	/**
	 * 接入支付交易基础信息全查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取查询条件
		String qpayTxnCode = queryBean.getParameter("qpayTxnCode");
		String qpayTxnResp = queryBean.getParameter("qpayTxnResp");
		String qpayTxnTypeId = queryBean.getParameter("qpayTxnTypeId");
		String qpayTxnStat = queryBean.getParameter("qpayTxnStat");
		return PagyPayTxnBaseInfoService.getInstance().queryAll(qpayTxnCode, qpayTxnResp, qpayTxnTypeId, qpayTxnStat,
				queryBean.getPage());
	}

	/**
	 * 查询支付与通道交易关系表中所有数据
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryPagyPayTxnRel(QueryParamBean queryBean) throws SnowException {
		String payTxnCode = queryBean.getParameter("payTxnCode");
		return PagyPayTxnBaseInfoService.getInstance().queryPagyPayTxnRel(payTxnCode, queryBean.getPage());
	}

	/**
	 * 接入支付交易基础信息表新增
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "交易新增")
	public void addType(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyPayTxnBaseInfo");
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 创建实体类对象
		PagyPayTxnBaseInfo base = new PagyPayTxnBaseInfo();
		// 遍历页面数据,存到实体类中
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), base);
			// 获取交易类型编号
			String payTxnTypeId = reqBean.getParameter("payTxnTypeId");
			// 获取通道交易编号
			String txnNo = reqBean.getParameter("txnNo");
			// 设置支付交易编号
			String payTxnCode = payTxnTypeId + txnNo;

			// 查询该交易编号是否存在 存在就提示错误
			int count = PagyPayTxnBaseInfoService.getInstance().queryPayTxnCode(payTxnCode);
			if (count > 0) {
				SnowExceptionUtil.throwErrorException("通道交易编号已存在不允许重复");
			}
			String crtTlr = sessionUserInfo.getTlrno();
			String crtDateTime = BaseUtil.getCurrentDateTime();
			base.setPayTxnCode(payTxnCode);
			base.setCrtTlr(crtTlr);
			base.setCrtDateTime(crtDateTime);
			// 调用新增的方法
			PagyPayTxnBaseInfoService.getInstance().addType(base);
			// 打印日志
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[接入支付交易基础信息]--新增 : 交易编号[payTxnCode]=" + payTxnCode });
		}

	}

	public void cofigType(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyPayTxnBaseInfo");
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String payTxnCode = reqBean.getParameter("payTxnCode");
		String payTxnResp = reqBean.getParameter("payTxnResp");
		String crtTlr = sessionUserInfo.getTlrno();
		String crtDateTime = BaseUtil.getCurrentDateTime();
		String lastUpdTlr = sessionUserInfo.getTlrno();
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		// 调用修改的方法
		PagyPayTxnBaseInfoService.getInstance().updateType(payTxnCode, payTxnResp, crtTlr, crtDateTime, lastUpdTlr,
				lastUpdDateTime);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[接入支付交易基础信息]--修改 : 交易编号[payTxnCode]=" + payTxnCode });
	}

	/**
	 * 接入支付交易与通道交易和通道的配置
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "接入支付交易与通道交易和通道的配置")
	public void updateType(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyPayTxnBaseInfo");
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		String payTxnCode = reqBean.getParameter("payTxnCode");
		String payTxnResp = reqBean.getParameter("payTxnResp");
		String crtTlr = sessionUserInfo.getTlrno();
		String crtDateTime = BaseUtil.getCurrentDateTime();
		String lastUpdTlr = sessionUserInfo.getTlrno();
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		// 调用修改的方法
		PagyPayTxnBaseInfoService.getInstance().updateType(payTxnCode, payTxnResp, crtTlr, crtDateTime, lastUpdTlr,
				lastUpdDateTime);
		// 获取支付与通道交易关系表的数据集
		UpdateRequestBean reqBean1 = updateBean.get("pagyPayTxnRel");
		// 根据支付交易编号删除关系表中的数据
		PagyPayTxnBaseInfoService.getInstance().deleteData(payTxnCode);
		// 创建支付与通道交易关系表对象
		PagyPayTxnRel txnRel = new PagyPayTxnRel();
		while (reqBean1.hasNext()) {
			DataObjectUtils.map2bean(reqBean1.next(), txnRel);
			String pagyTxnCode = txnRel.getPagyTxnCode();
			String str = pagyTxnCode;
			String pagyNo = str.substring(0, 3);
			// 对支付与通道交易关系表进行增,删,改
			// 当本条数据为删除时
			if (reqBean1.getRecodeState() == reqBean1.DELETE) {
				continue;
			}
			// 在新增表格的数据到关系表中
			txnRel.setPagyTxnCode(pagyTxnCode);
			txnRel.setPayTxnCode(payTxnCode);
			txnRel.setPagyNo(pagyNo);
			// 根据通道编号 查询通道交易编号+支付交易编号查重复
			int num = PagyPayTxnBaseInfoService.getInstance().query(pagyTxnCode, payTxnCode);
			if (num > 0) {
				SnowExceptionUtil.throwErrorException("同一个通道编号下通道交易编号+支付交易编号不允许重复");
			}
			// 像支付与通道交易关系表中插入数据
			PagyPayTxnBaseInfoService.getInstance().addPagyPayTxnRel(txnRel);
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[接入支付交易基础信息]--配置 : 交易编号[payTxnCode]=" + payTxnCode });
	}

	/**
	 * 接入支付交易基础信息表状态的修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "交易状态的修改")
	public void updateStatus(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("pagyPayTxnBaseInfo");
		// 获取交易编号
		String payTxnCode = reqBean.getParameter("payTxnCode");
		// 获取页面传入的状态
		String payTxnStat = reqBean.getParameter("payTxnStat");
		// 创建操作员
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String crtTlr = sessionUserInfo.getTlrno();
		String crtDateTime = BaseUtil.getCurrentDateTime();
		// 调用修改状态的方法
		PagyPayTxnBaseInfoService.getInstance().updateStatus(payTxnCode, payTxnStat, crtTlr, crtDateTime);
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[接入支付交易基础信息]--状态修改 : 交易编号[payTxnCode]=" + payTxnCode });
	}
}
