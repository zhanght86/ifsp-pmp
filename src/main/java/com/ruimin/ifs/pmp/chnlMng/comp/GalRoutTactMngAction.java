/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.chnlMng.comp 
 * FileName: GalRoutTactMngAction.java
 * Author:   chenqilei
 * Date:     2016年7月27日 下午2:42:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   chenqilei           2016年7月27日下午2:42:15                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.comp;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.chnlMng.common.constants.PagyRoutMngConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.BasicTest;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalBasicInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.GalRoutTactMng;
import com.ruimin.ifs.pmp.chnlMng.process.bean.TactAndBasic;
import com.ruimin.ifs.pmp.chnlMng.process.service.GalRoutTactMngService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月27日 <br>
 * 作者：chenqilei <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道路由策略管理Action")
@ActionResource
public class GalRoutTactMngAction extends SnowAction {
	/**
	 * 路由策略管理表中查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "到路由策略管理表中查询数据")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException {
		// 获取页面传入的参数
		String ttsCode = queryBean.getParameter("qttsCode"); // 路由策略编号
		String ttsResp = queryBean.getParameter("qttsResp"); // 路由策略名称
		String ttsType = queryBean.getParameter("qttsType"); // 路由策略类型
		String ttsStat = queryBean.getParameter("qttsStat"); // 路由策略状态
		return GalRoutTactMngService.getInstance().queryAll(ttsCode, ttsResp, ttsType, ttsStat, queryBean.getPage());
	}

	/**
	 * 查询表格页面的数据
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "修改时查询表格页面的数据")
	public PageResult queryName(QueryParamBean queryBean) throws SnowException {
		String ttsCode = queryBean.getParameter("ttsCode");
		return GalRoutTactMngService.getInstance().queryName(ttsCode, queryBean.getPage());
	}

	/**
	 * 新增时查询通道的名称 并让优先级按照从1 到 2 .....排列
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增时查询通道的名称")
	public PageResult queryGalBasicInfo(QueryParamBean queryBean) throws SnowException {
		String ttsCode = queryBean.getParameter("ttsCode");

		return GalRoutTactMngService.getInstance().queryGalBasicInfo(ttsCode, queryBean.getPage());
	}

	/**
	 * 路由策略新增
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "路由策略新增")
	public void addRoutMng(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取策略管理数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutTactMng");
		// 获取通道信息数据集
		UpdateRequestBean reqBean1 = updateBean.get("galBasicInfo");
		GalRoutTactMng rout = new GalRoutTactMng();
		// 查询最大序列号
		String maxSeq = GalRoutTactMngService.getInstance().queryMaxSeq();
		String nextSeq;
		if (maxSeq != null) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num + 1), 2, "0");
		} else {
			nextSeq = "1000";
		}
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rout);
		}
		// 用来接收数据的实体类
		BasicTest bs = new BasicTest();
		// 创建策略关系表的实体类
		TactAndBasic tact = new TactAndBasic();
		// 获取新增页面表格中的数据
		List<Map<String, String>> list = reqBean1.getTotalList();
		for (Map<String, String> map : list) {
			bs = DataObjectUtils.map2bean(map, BasicTest.class);
			String pagyNo = bs.getPagyNo();
			String ttsCode = nextSeq;
			// 到支付通道策略关系表查询每个策略下有没有重复的通道
			int num = GalRoutTactMngService.getInstance().selectPagyNo(pagyNo, ttsCode);
			if (num > 0) {
				SnowExceptionUtil.throwErrorException("该条数据已经设置过优先级,不可重复设置");
			}
			String priorityVul = bs.getPriorityVul();
			// 到支付通道策略关系表查询同一个策略下通道的优先级有没有重复的
			int result = GalRoutTactMngService.getInstance().selectPriorityVul(priorityVul, ttsCode);
			if (result > 0) {
				SnowExceptionUtil.throwErrorException("优先级已存在");
			}
			tact.setPagyNo(pagyNo);
			tact.setPriorityVul(priorityVul);
			tact.setTtsCode(nextSeq);
			GalRoutTactMngService.getInstance().addData(tact);
		}

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		rout.setTtsCode(nextSeq);
		rout.setCrtTlr(sessionUserInfo.getTlrno());
		rout.setCrtDateTime(DateUtil.getCurrDate());
		GalRoutTactMngService.getInstance().addRoutMng(rout);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[路由策略管理]--新增 : 策略编号[ttsCode]=" + rout.getTtsCode() });
	}

	/**
	 * 路由策略修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "路由策略修改")
	public void updateRoutMng(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutTactMng");
		GalRoutTactMng rout = new GalRoutTactMng();
		// 创建接受表格数据的实体类
		BasicTest bt = new BasicTest();
		TactAndBasic basic = new TactAndBasic();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rout);
		}
		// 获取修改表格也页面的数据集
		UpdateRequestBean reqBean1 = updateBean.get("galBasicInfo");
		List<Map<String, String>> list = reqBean1.getTotalList();
		for (Map<String, String> map : list) {
			bt = DataObjectUtils.map2bean(map, BasicTest.class);
			String recordState = bt.getRecordState();
			String priorityVul = bt.getPriorityVul();
			String ttsCode = rout.getTtsCode();
			String pagyNo = bt.getPagyNo();
			basic.setPriorityVul(priorityVul);
			basic.setPagyNo(pagyNo);
			basic.setTtsCode(rout.getTtsCode());

			// 如果修改表格页面传入的状态是modify 就进行修改操作
			if (("modify".equals(recordState))) {
				// 到支付通道策略关系表查询优先级有没有重复的
				int result = GalRoutTactMngService.getInstance().selectPriorityVul(priorityVul, ttsCode);
				if (result > 0) {
					SnowExceptionUtil.throwErrorException("优先级已存在");
				}
				// 调用修改的Service
				GalRoutTactMngService.getInstance().updateByTtsCodeInfo(basic);
			} else if (("insert".equals(recordState))) {
				// 如果修改表格页面传入的状态是insert 就进行新增操作
				// 到支付通道策略关系表查询优先级有没有重复的
				int result = GalRoutTactMngService.getInstance().selectPriorityVul(priorityVul, ttsCode);
				if (result > 0) {
					SnowExceptionUtil.throwErrorException("优先级已存在");
				}
				// 到支付通道策略关系表查询该条数据有没有重复设置优先级
				int num = GalRoutTactMngService.getInstance().selectPagyNo(pagyNo, ttsCode);
				if (num > 0) {
					SnowExceptionUtil.throwErrorException("该条数据已经设置过优先级,不可重复设置");
				}
				// 调用插入的Service
				GalRoutTactMngService.getInstance().addData(basic);
			} else if (("delete".equals(recordState))) {
				// 如果修改表格页面传入的状态是delete 就进行删除操作

				// 调用修改的Service
				GalRoutTactMngService.getInstance().deleteData(basic);
			} else if ("none".equals(recordState)) {
				continue;
			}

		}
		// 修改路由策略表中的数据
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		rout.setCrtTlr(sessionUserInfo.getTlrno());
		rout.setCrtDateTime(DateUtil.getCurrDate());
		rout.setLastUpdTlr(sessionUserInfo.getTlrno());
		rout.setLastUpdDateTime(DateUtil.getCurrDate());
		// 调用修改路由策略数据的Service
		GalRoutTactMngService.getInstance().updateRoutMng(rout);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[路由策略管理]--修改 : 策略编号[ttsCode]=" + rout.getTtsCode() });
	}

	/**
	 * 路由策略状态的修改
	 * 
	 * @param updateBean
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "路由策略状态修改")
	public void updateRoutMngStatus(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("galRoutTactMng");
		String ttsStat = reqBean.getParameter("ttsStat");
		GalRoutTactMng rout = new GalRoutTactMng();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), rout);
		}
		// 更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		rout.setLastUpdTlr(sessionUserInfo.getTlrno());
		rout.setLastUpdDateTime(DateUtil.getCurrDate());
		rout.setTtsStat(ttsStat);
		// 调用修改状态的方法
		GalRoutTactMngService.getInstance().updateTtsStat(rout);
		String msg = "";
		if (PagyRoutMngConstants.TTS_STAT_ON.equals(ttsStat)) {
			msg = "启用路由策略,id=" + rout.getTtsCode();
		}
		if (PagyRoutMngConstants.TTS_STAT_NO_START.equals(ttsStat)) {
			msg = "未启用路由策略,id=" + rout.getTtsCode();
		}
		if (PagyRoutMngConstants.TTS_STAT_BLOCK_UP.equals(ttsStat)) {
			msg = "停用路由策略,id=" + rout.getTtsCode();
		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[路由策略管理]--修改状态 : 策略编号[ttsCode]=" + rout.getTtsCode() });
	}

	/**
	 * x修改表格页面显示的通道名字
	 */
	public static String getPagyNoName(FieldBean bean, String key, ServletRequest request) throws SnowException {

		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		List<Object> prodList = GalRoutTactMngService.getInstance().getPagyNoName(key);

		if (prodList == null || prodList.size() == 0) {
			return strBuf.toString();
		}
		for (Object prodObj : prodList) {
			GalBasicInfo prod = (GalBasicInfo) prodObj;
			strBuf.append(prod.getPagyName()).append(",");
		}
		return strBuf.toString().substring(0, strBuf.toString().length() - 1);
	}

	/**
	 * 根据策略编号去查询策略名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	public static String queryByTtsCode(FieldBean bean, String key, ServletRequest request) throws SnowException {
		StringBuffer strBuf = new StringBuffer();

		if (StringUtil.isEmpty(key)) {
			return strBuf.toString();
		}
		String prodList = GalRoutTactMngService.getInstance().queryByTtsCode(key);

		if (prodList == null || "".equals(prodList)) {
			return strBuf.toString();
		}
		strBuf.append(prodList);

		return strBuf.toString();

	}

	/**
	 * 查询支付通道策略表名称
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@SnowDoc(desc = "查询支付通道策略表名称")
	public PageResult queryTtsResp(QueryParamBean queryBean) throws SnowException {
		return GalRoutTactMngService.getInstance().queryTtsResp(queryBean.getPage());
	}
}
