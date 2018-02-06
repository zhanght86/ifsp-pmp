package com.ruimin.ifs.pmp.chnlMng.comp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.utils.JSONUtil;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.chnlMng.common.constants.ChnlAcsInfoConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChnlAcsInfoVO;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyMainMchtInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyPayCertCfg;
import com.ruimin.ifs.pmp.chnlMng.process.service.ChnlAcsInfoService;
import com.ruimin.ifs.pmp.pubTool.util.Base64Coder;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.CommonUtil;

@ActionResource
@SnowDoc(desc = "通道接入信息")
public class ChnlAcsInfoAction {
	/**
	 * 查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qpagyNo = queryBean.getParameter("qpagyNo");// 通道编号
		String qmainMchtAcsType = queryBean.getParameter("qmainMchtAcsType");// 接入方式
		String qmainMchtNo = queryBean.getParameter("qmainMchtNo");// 接入编号
		String qmainMchtName = queryBean.getParameter("qmainMchtName");// 接入名称
		String qmainMchtStat = queryBean.getParameter("qmainMchtStat");// 接入状态
		String qmainMchtNoAC = queryBean.getParameter("qmainMchtNoAC");// 接入编号【精确】

		/** 返回查询结果 */
		return ChnlAcsInfoService.getInstance().queryMain(qpagyNo, qmainMchtAcsType, qmainMchtNo, qmainMchtName,
				qmainMchtStat, qmainMchtNoAC, queryBean.getPage());
	}

	/**
	 * 查询【功能清单】,所选通道下的交易+账户类型
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询【功能清单】")
	public List<TreeNode> queryFuncTree(QueryParamBean queryBean) throws SnowException {
		/** 查询字段 */
		String qoprType = queryBean.getParameter("qoprType", "");// 操作类型
		String qpagyNo = queryBean.getParameter("qpagyNo", "");// 通道编号
		String mainMchtNo = queryBean.getParameter("mainMchtNo", "");// 接入编号

		/** 数据容器 */
		List<ChnlAcsInfoVO> funcVos = new ArrayList<ChnlAcsInfoVO>();// 存放未配置的节点信息

		/** 组装树 */
		if (StringUtils.isNotBlank(qpagyNo) && StringUtils.isNotBlank(qoprType)) {
			// 根据操作类型选择查询分支
			if (qoprType.equals(ChnlAcsInfoConstants.OPR_FLAG_ADD)) {
				funcVos = ChnlAcsInfoService.getInstance().queryFuncTreeWhenAddOrUpd(qpagyNo, qoprType, mainMchtNo);
			} else if (qoprType.equals(ChnlAcsInfoConstants.OPR_FLAG_UPD)) {
				funcVos = ChnlAcsInfoService.getInstance().queryFuncTreeWhenAddOrUpd(qpagyNo, qoprType, mainMchtNo);
			} else if (qoprType.equals(ChnlAcsInfoConstants.OPR_FLAG_DETAIL)) {
				funcVos = ChnlAcsInfoService.getInstance().queryFuncTreeWhenDetail(mainMchtNo);
			} else {
				SnowExceptionUtil.throwErrorException("未知操作，已被拦截！");
			}
		}
		return ChnlAcsInfoService.getInstance().generateTree(funcVos);
	}

	/**
	 * 新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws UnsupportedEncodingException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addAcs(Map<String, UpdateRequestBean> updateMap) throws SnowException, UnsupportedEncodingException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 初始化操作标志 */
		String oprFlag = ChnlAcsInfoConstants.OPR_FLAG_ADD;

		/** 获取当前时间 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		String currentDate = currentTime.substring(0, 8);// 当前日期，8位

		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mainInfo");

		/** 加载实体类容器 */
		ChnlAcsInfoVO tempVo = new ChnlAcsInfoVO();// 通道接入信息-实体类
		PagyMainMchtInfo mainVo = new PagyMainMchtInfo();// 支付通道主商户信息表-实体类
		PagyPayCertCfg certVo = new PagyPayCertCfg();// 支付证书信息配置-实体类

		/** 导入实体类 */
		while (reqBean.hasNext()) {
			Map<String, String> beanElement = reqBean.next();
			DataObjectUtils.map2bean(beanElement, mainVo);
			DataObjectUtils.map2bean(beanElement, certVo);
			DataObjectUtils.map2bean(beanElement, tempVo);
		}

		/*****************************
		 * STEP NO2 逻辑判断
		 ****************************************/
		/** 校验【接入编号是否重复】 */
		ChnlAcsInfoService.getInstance().validPkNoRepeat(tempVo.getMainMchtNo());

		/** 校验【日期输入】 */
		ChnlAcsInfoService.getInstance().vaildDateInput(tempVo, currentDate);

		/*****************************
		 * STEP NO3 封装数据
		 ****************************************/
		/** 清算时间，去除冒号 */
		String mainMchtSetlTm = CommonUtil.filtStr(tempVo.getMainMchtSetlTm(), ":");
		mainVo.setMainMchtSetlTm(mainMchtSetlTm);

		/** 密码进行BASE64加密 */
		// 初始化
		String certifiPasswd = tempVo.getCertifiPasswd();// 证书密码
		String md5Passwd = tempVo.getMd5Passwd();// 密钥值
		// 加密并装入实体类
		if (!StringUtils.isBlank(md5Passwd)) {
			md5Passwd = Base64Coder.encoded(md5Passwd);
			tempVo.setMd5Passwd(md5Passwd);
		}
		certifiPasswd = Base64Coder.encoded(certifiPasswd);
		certVo.setCertifiPasswd(certifiPasswd);

		/** 获取节点信息 */
		String funcStr = reqBean.getParameter("funcStr");// 节点信息【父节点/节点】

		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		mainVo.setCrtTlr(sessionUserInfo.getTlrno());// 创建操作员
		mainVo.setCrtDateTime(currentTime);// 创建日期时间
		certVo.setRecCrtOpr(sessionUserInfo.getTlrno());// 创建操作员
		certVo.setRecCrtTs(currentTime);// 创建日期时间

		/*****************************
		 * STEP NO4 数据入库
		 ****************************************/
		ChnlAcsInfoService.getInstance().addAcs(mainVo);// 新增【主商户信息】
		ChnlAcsInfoService.getInstance().reInCert(tempVo, certVo, oprFlag, "");// 新增【证书信息】
		ChnlAcsInfoService.getInstance().addFuncTree(funcStr, tempVo.getMainMchtNo(), tempVo.getPagyNo());// 新增【功能清单树节点】

		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道接入信息]--新增：接入编号[mainMchtNo]=" + mainVo.getMainMchtNo() });
	}

	/**
	 * 修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 * @throws UnsupportedEncodingException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void updAcs(Map<String, UpdateRequestBean> updateMap) throws SnowException, UnsupportedEncodingException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 初始化操作标志 */
		String oprFlag = ChnlAcsInfoConstants.OPR_FLAG_UPD;

		/** 获取当前时间 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		String currentDate = currentTime.substring(0, 8);// 当前日期，8位

		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mainInfo");

		/** 加载实体类容器 */
		ChnlAcsInfoVO tempVo = new ChnlAcsInfoVO();// 通道接入信息-实体类
		PagyMainMchtInfo mainVo = new PagyMainMchtInfo();// 支付通道主商户信息表-实体类
		PagyPayCertCfg certVo = new PagyPayCertCfg();// 支付证书信息配置-实体类

		/** 导入实体类 */
		while (reqBean.hasNext()) {
			Map<String, String> beanElement = reqBean.next();
			DataObjectUtils.map2bean(beanElement, mainVo);
			DataObjectUtils.map2bean(beanElement, certVo);
			DataObjectUtils.map2bean(beanElement, tempVo);
		}

		/*****************************
		 * STEP NO2 逻辑判断
		 ****************************************/
		/** 判断主键【接入编号】是否改动 */
		String mainMchtNoAC = tempVo.getMainMchtNoAC();// 修改前接入编号-用于修改记录
		boolean pkChangeFlag = mainMchtNoAC.equals(tempVo.getMainMchtNo());

		/** 校验【接入编号是否重复】 */
		if (!pkChangeFlag) {
			ChnlAcsInfoService.getInstance().validPkNoRepeat(tempVo.getMainMchtNo());
		}

		/** 校验【日期输入】 */
		ChnlAcsInfoService.getInstance().vaildDateInput(tempVo, currentDate);

		/*****************************
		 * STEP NO3 封装数据
		 ****************************************/
		/** 清算时间，去除冒号 */
		String mainMchtSetlTm = CommonUtil.filtStr(tempVo.getMainMchtSetlTm(), ":");
		mainVo.setMainMchtSetlTm(mainMchtSetlTm);

		/** 密码进行BASE64加密 */
		// 初始化
		String certifiPasswd = tempVo.getCertifiPasswd();// 证书密码
		String md5Passwd = tempVo.getMd5Passwd();// 密钥值
		// 加密并装入实体类
		if (!StringUtils.isBlank(md5Passwd)) {
			md5Passwd = Base64Coder.encoded(md5Passwd);
			tempVo.setMd5Passwd(md5Passwd);
		}
		certifiPasswd = Base64Coder.encoded(certifiPasswd);
		certVo.setCertifiPasswd(certifiPasswd);

		/** 获取节点信息 */
		String funcStr = reqBean.getParameter("funcStr");// 节点信息【父节点/节点】

		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		mainVo.setLastUpdTlr(sessionUserInfo.getTlrno());// 更新操作员
		mainVo.setLastUpdDateTime(currentTime);// 更新日期时间
		certVo.setRecUpdOpr(sessionUserInfo.getTlrno());// 更新操作员
		certVo.setRecUpdTs(currentTime);// 更新日期时间

		/*****************************
		 * STEP NO4 数据入库
		 ****************************************/
		ChnlAcsInfoService.getInstance().updAcs(mainVo, pkChangeFlag, mainMchtNoAC);// 修改【主商户信息】
		ChnlAcsInfoService.getInstance().reInCert(tempVo, certVo, oprFlag, mainMchtNoAC);// 修改【证书信息】
		ChnlAcsInfoService.getInstance().updFuncTree(funcStr, mainMchtNoAC, tempVo.getMainMchtNo(), tempVo.getPagyNo());// 修改【功能清单树节点】

		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道接入信息]--修改：接入编号[mainMchtNo]=" + mainVo.getMainMchtNo() });
	}

	/**
	 * 修改时，查询已配置的功能节点
	 * 
	 * @param updateMap
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "修改时，查询已配置的功能节点")
	public Map<String, Object> queryUsingNode(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取前台参数 */
		String mainMchtNo = updateMap.get("mainInfo").getParameter("mainMchtNo");

		/** 查询并封装结果 */
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(mainMchtNo)) {
			List<ChnlAcsInfoVO> UsingNodes = ChnlAcsInfoService.getInstance().queryFuncTreeWhenDetail(mainMchtNo);
			List<String> checkNodes = new ArrayList<String>();
			for (ChnlAcsInfoVO voElements : UsingNodes) {
				checkNodes.add(voElements.getPagyTxnCode());
				checkNodes.add(voElements.getPagyTxnCode() + "_" + voElements.getAcctTypeNo());
			}
			map.put("checkNode", JSONUtil.toJSON(checkNodes).replace("\"", "").replace("[", "").replace("]", ""));
		}
		return map;
	}

	/**
	 * 启用/停用
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用/停用")
	public void stpAcs(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mainInfo");

		/** 导入实体类 */
		PagyMainMchtInfo acsVo = new PagyMainMchtInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), acsVo);
		}

		/*****************************
		 * STEP NO2 获取操作参数
		 ****************************************/
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		String trlno = sessionUserInfo.getTlrno();// 更新操作员
		String lastUpdDateTime = currentTime;// 更新日期时间
		String mainMchtStat = acsVo.getMainMchtStat();// 接入状态
		String mainMchtNo = acsVo.getMainMchtNo();// 主商户编号

		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		ChnlAcsInfoService.getInstance().stpAcs(trlno, lastUpdDateTime, mainMchtStat, mainMchtNo);

		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[通道接入信息]--启用/停用：接入编号[mainMchtNo]=" + mainMchtNo });
	}
}