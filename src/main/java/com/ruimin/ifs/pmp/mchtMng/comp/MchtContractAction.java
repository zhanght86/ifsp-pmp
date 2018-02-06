package com.ruimin.ifs.pmp.mchtMng.comp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractAcctRate;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractProduct;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtTxnLimitInfoTmp;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtContractService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtSetlTypeService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtTxnLimitService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@ActionResource
@SnowDoc(desc = "商户合同管理")
public class MchtContractAction {
	/**
	 * 页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "商户合同的页面信息查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmchtId = queryBean.getParameter("qmchtId");// 商户编号
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户简称
		String qconId = queryBean.getParameter("qconId"); // 平台合同编号
		String qpaperConId = queryBean.getParameter("qpaperConId");// 商户合同ID
		String qsetlAcctNo = queryBean.getParameter("qsetlAcctNo");
		String qconState = queryBean.getParameter("qconState");
		String auditId = queryBean.getParameter("auditId");
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 查询主页面字段 */
		return MchtContractService.getInstance().queryMain(qmchtId, qmchtSimpleName, qconId, qpaperConId, qsetlAcctNo,
				qconState, auditId, sessionUserInfo.getBrCode(), queryBean.getPage());
	}

	   /**
     * 页面查询
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "页面查询")
    public PageResult queryPagyCategroyCfgl(QueryParamBean queryBean) throws SnowException {
        String qLevelTwoCode = queryBean.getParameter("qLevelTwoCode");


        return MchtContractService.getInstance().queryPagyCategroyCfgl(qLevelTwoCode,queryBean.getPage());
       
    }
    
        /**
      * 页面查询
      * 
      * @param queryBean
      * @return
      * @throws SnowException
      */
     @Action
     @SnowDoc(desc = "页面查询")
     public PageResult queryPagyCategroyCfgl2(QueryParamBean queryBean) throws SnowException {
         String qLevelThreeCode = queryBean.getParameter("qLevelThreeCode"); 
         return MchtContractService.getInstance().queryPagyCategroyCfgl2(qLevelThreeCode,queryBean.getPage());
        
     }
 
	/**
	 * 页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "页面查询")
	public PageResult queryAcctType(QueryParamBean queryBean) throws SnowException {
		String prodId = queryBean.getParameter("prodId");
		String conId = queryBean.getParameter("conId");
		String flag = queryBean.getParameter("flag");
		if (!"yes".equals(flag)) {// 根据标志，加载出合同下已经添加的账户和费率记录
			/** 查询主页面字段 */
			return MchtContractService.getInstance().queryAllRecord(prodId, conId, queryBean.getPage());
		} else {// 根据标志来，仅仅下拉出该产品下的账户类型和费率
			return MchtContractService.getInstance().queryAcctType(prodId, queryBean.getPage());
		}

	}

	/**
	 * 新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");
		String mchtId1 = reqBean.getParameter("mchtId1");
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		//String limitOne = reqBean.getParameter("limitOne");
		//String limitDay = reqBean.getParameter("limitDay");
		/* 判断该商户是否已经添加过 */
		MchtContractVO mchtflag = MchtContractService.getInstance().queryMchtFlag(mchtId1);
		if (mchtflag != null) {
			SnowExceptionUtil.throwErrorException("该商户已经签订合同，请重新选择商户！");
		}
		/** 导入实体类 */
		MchtContractVO mchtVo = new MchtContractVO();
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		/** 导入商户限额的实体类 **/
		//MchtTxnLimitInfoTmp mchtTxnLitTmp = new MchtTxnLimitInfoTmp();

		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
		}
		mchtVo.setAuditId(ContextUtil.getUUID());
		mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		/** 自动生成合同号 */
		mchtVo.setConId(MchtContractService.getInstance().genMchtConId());
		mchtVo.setMchtId(mchtId1);
		if (MchtContractConstants.CON_TERM_01.equals(mchtVo.getConTerm())) {
			mchtVo.setEndDate(genEndDay(mchtVo.getStartDate(), 1));
		} else {
			mchtVo.setEndDate(genEndDay(mchtVo.getStartDate(), 2));
		}
		/** 状态变更 */
		mchtVo.setConState(MchtContractConstants.MCHT_CONTR_STAT_ON);
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		mchtVo.setCrtTlr(sessionUserInfo.getTlrno());// 创建操作员
		mchtVo.setCrtDateTime(currentTime);// 创建日期时间

		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
//		if (limitOne != null || limitDay != null) {
//			mchtTxnLitTmp.setMchtId(mchtId1);
//			mchtTxnLitTmp.setLimitOne(limitOne);
//			mchtTxnLitTmp.setLimitDay(limitDay);
//			mchtTxnLitTmp.setDataState(MchtContractConstants.DATA_STATE_00); // 交易限额的数据状态设置为启用
//			mchtTxnLitTmp.setCrtTlr(sessionUserInfo.getTlrno());
//			mchtTxnLitTmp.setCrtDateTime(currentTime);
//			MchtTxnLimitService.getInstance().addMchtTxnLitTmp(mchtTxnLitTmp);
//		}

		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		MchtContractService.getInstance().addPic(reqBean, mchtVo.getConId(), mchtVo.getCrtTlr(),
				mchtVo.getCrtDateTime());
		MchtContractService.getInstance().addMcht(mchtVo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户合同管理]--信息新增:合同编号[conId]=" + mchtVo.getConId() });
	}

	/**
	 * 产品配置
	 * 
	 * @param requestMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "产品配置")
	public void addMchtService(Map<String, UpdateRequestBean> requestMap) throws SnowException {
		UpdateRequestBean requestBean = requestMap.get("conSerPro");// 获取支付产品的信息
		UpdateRequestBean passBankReqBean = requestMap.get("conSerAcc");// 获取账户费率的信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();// 获取当前的操作员
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		MchtContractProduct mctpt = new MchtContractProduct();
		MchtContractAcctRate mctar = new MchtContractAcctRate();
		String prodId = requestBean.getParameter("prodId");
		String conId = requestBean.getParameter("conId");
		String AllowModify = passBankReqBean.getParameter("AllowModify");
		while (requestBean.hasNext()) {
			DataObjectUtils.map2bean(requestBean.next(), mctpt);
		}
		mctpt.setCrtTlr(sessionUserInfo.getTlrno());// 操作时间和操作员
		mctpt.setCrtDateTime(currentTime);
		mctpt.setProdId(prodId);
		mctpt.setConId(conId);
		mctpt.setDataState(MchtContractConstants.DATA_STATE_00);
		// 判断是否为重复添加数据
		List<Map<String, String>> passBankList = passBankReqBean.getTotalList();// 账户费率配置信息
		MchtContractProduct mctptest = MchtContractService.getInstance().queryConfirm(prodId, conId);
		if ("true".equals(AllowModify)) {// 产品配置: 新增修改标志:true为新增 false为修改
			if (mctptest != null) {
				SnowExceptionUtil.throwErrorException("该支付产品已添加，请选择其它支付产品！");
			} else {
				for (Map<String, String> map : passBankList) {
					mctar = DataObjectUtils.map2bean(map, MchtContractAcctRate.class);
					mctar.setConId(conId);// 记录合同编号
					mctar.setProdId(prodId); // 记录产品编号
					mctar.setDataState(MchtContractConstants.DATA_STATE_00);// 数据有效状态
					mctar.setCrtTlr(sessionUserInfo.getTlrno());
					mctar.setCrtDateTime(currentTime);
					MchtContractService.getInstance().addMchtContractAcctRate(mctar);
					/** 打印日志 */
					sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
							sessionUserInfo.getBrno(), "[商户合同管理]--服务:合同编号[conId]=" + conId });
				}
			}
			MchtContractService.getInstance().addMchtContractProduct(mctpt);

		} else {
			MchtContractService.getInstance().delTabPro(conId, prodId);
			MchtContractService.getInstance().delTabRate(conId, prodId);
			for (Map<String, String> map : passBankList) {
				mctar = DataObjectUtils.map2bean(map, MchtContractAcctRate.class);
				mctar.setConId(conId);// 记录合同编号
				mctar.setProdId(prodId); // 记录产品编号
				mctar.setDataState(MchtContractConstants.DATA_STATE_00);// 数据有效状态
				mctar.setCrtTlr(sessionUserInfo.getTlrno());
				mctar.setCrtDateTime(currentTime);
				MchtContractService.getInstance().addMchtContractAcctRate(mctar);
			}
			MchtContractService.getInstance().addMchtContractProduct(mctpt);
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同管理]--服务产品配置修改:合同编号[conId]=" + conId });
		}
	}

	/**
	 * 修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void updMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		//String limitOne = reqBean.getParameter("limitOne");
		//String limitDay = reqBean.getParameter("limitDay");

		/** 导入实体类 */
		MchtContractVO mchtVo = new MchtContractVO();

		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		/** 导入商户限额的实体类 **/
		//MchtTxnLimitInfoTmp mchtTxnLitTmp = new MchtTxnLimitInfoTmp();

		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
		}
		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		String conState = mchtVo.getConState();
		if (MchtContractConstants.MCHT_CONTR_STAT_ON.equals(mchtVo.getConTerm())) {
			mchtVo.setEndDate(genEndDay(mchtVo.getStartDate(), 1));
		} else {
			mchtVo.setEndDate(genEndDay(mchtVo.getStartDate(), 2));
		}
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		mchtVo.setLastUpdTlr(sessionUserInfo.getTlrno());// 更新操作员
		mchtVo.setLastUpdDateTime(currentTime);// 更新日期时间
		String mchtId = mchtVo.getMchtId(); // 获取商户号
		//----2017年04-01修改---隐藏单笔交易限额和日交易限额
		// 先删除原来临时表中的数据，再添加
		//MchtTxnLimitService.getInstance().delMchtTxnLitTmpByMchtId(mchtId);
//		if (limitOne != null || limitDay != null) {
//			mchtTxnLitTmp.setMchtId(mchtId);
//			mchtTxnLitTmp.setLimitOne(limitOne);
//			mchtTxnLitTmp.setLimitDay(limitDay);
//			mchtTxnLitTmp.setDataState(MchtContractConstants.DATA_STATE_00); // 交易限额的数据状态设置为启用
//			mchtTxnLitTmp.setCrtTlr(sessionUserInfo.getTlrno());
//			mchtTxnLitTmp.setCrtDateTime(currentTime);
//			/* 交易限额的实体类封装入库 */
//			MchtTxnLimitService.getInstance().addMchtTxnLitTmp(mchtTxnLitTmp);
//		}

		String conId = mchtVo.getConId();
		String conStateOld = null;
		// 如果是到期的合同状态，要判断清楚，原来的状态是什么状态，然后判断走什么流程
		if (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)) {
			conStateOld = MchtContractService.getInstance().selectConStateByConId(conId);
		}
		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		// 当合同状态为00-执行中，02-即将到期，走06-修改待审核状态和流程
		if (MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(conState)
				|| (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
						&& MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(conStateOld))) {
			mchtVo.setAuditId(ContextUtil.getUUID());
			mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_06);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_11);
			// 循环插入到审核记录表中
			if (list == null || list.size() == 0) {
				SnowExceptionUtil.throwWarnException("未找到审核步骤！");
			}

			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		} else if (MchtContractConstants.MCHT_STAT_05.equals(conState)
				|| (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
						&& MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conStateOld))) {
			mchtVo.setAuditId(ContextUtil.getUUID());
			mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_04);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_10);
			// 循环插入到审核记录表中
			if (list == null || list.size() == 0) {
				SnowExceptionUtil.throwWarnException("未找到审核步骤！");
			}

			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		}
		MchtContractService.getInstance().updPic(reqBean, mchtVo.getConId(), sessionUserInfo.getTlrno(), currentTime);
		MchtContractService.getInstance().updMcht(mchtVo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户合同管理]--信息修改:合同编号[conId]=" + mchtVo.getConId() });
	}

	/**
	 * 中止/恢复
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "中止/恢复")
	public void frzMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");

		/** 导入实体类 */
		MchtContractVO mchtVo = new MchtContractVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
		}
	      String conState = mchtVo.getConState();
		PbsMchtBaseInfo pbsMchtBaseInfo=MchtMngService.getInstance().queryByMchtId(mchtVo.getMchtId());
		if("01".equals(pbsMchtBaseInfo.getMchtStat())||"02".equals(pbsMchtBaseInfo.getMchtStat())){
		    if (conState.equals(MchtContractConstants.MCHT_CONTR_STAT_OFF)){
		        SnowExceptionUtil.throwWarnException("商户状态为注销或冻结，商户合同不可恢复或终止！");		        
		    }
		}
		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		String conId = mchtVo.getConId();
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		mchtVo.setLastUpdTlr(sessionUserInfo.getTlrno());// 更新操作员
		mchtVo.setLastUpdDateTime(currentTime);// 更新日期时间

		if (conState.equals(MchtContractConstants.MCHT_CONTR_STAT_NORMAL)
				|| conState.equals(MchtContractConstants.MCHT_CONTR_STAT_DEADING)) {
			mchtVo.setAuditId(ContextUtil.getUUID());
			mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_08);
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_12);
			// 循环插入到审核记录表中
			if (list == null || list.size() == 0) {
				SnowExceptionUtil.throwWarnException("未找到审核步骤！");
			}

			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		} else if (conState.equals(MchtContractConstants.MCHT_CONTR_STAT_OFF)) {
			mchtVo.setAuditId(ContextUtil.getUUID());
			mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_10);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_13);
			// 循环插入到审核记录表中
			if (list == null || list.size() == 0) {
				SnowExceptionUtil.throwWarnException("未找到审核步骤！");
			}

			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
			/** 打印日志 */
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户合同管理]--中止:合同编号[conId]=" + conId });
		}
		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		MchtContractService.getInstance().updMcht(mchtVo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户合同管理]--修改:合同编号[conId]=" + mchtVo.getConId() });
	}

	// 根据修改的合同起始日期和合同期限，重新确定合同的终止日期
	public String genEndDay(String startDay, int conTerm) {
		String endDay = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date dt = new Date();
		try {
			dt = dateFormat.parse(startDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(dt);
		cal.add(Calendar.YEAR, conTerm);
		cal.add(Calendar.DATE, -1);
		endDay = dateFormat.format(cal.getTime());
		return endDay;
	}

	@Action
	@SnowDoc(desc = "根据合同号查询产品信息查询")
	public PageResult queryProdAll(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qconId = queryBean.getParameter("conId"); // 平台合同编号
		/** 查询主页面字段 */
		return MchtContractService.getInstance().queryProdAll(qconId, queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "根据开户机构编号查找开户机构名")
	public static String getSetlAcctInstituteName(FieldBean bean, String key, ServletRequest request)
			throws SnowException {
		if (StringUtil.isBlank(key)) {
			return key;
		}
		List<Object> SetlAcctInstituteNameList = MchtContractService.getInstance().getSetlAcctInstituteName(key);
		if (null == SetlAcctInstituteNameList || SetlAcctInstituteNameList.size() == 0) {
			return key;
		}
		String SetlAcctInstituteName = SetlAcctInstituteNameList.toString().replace(" ", "").replace("[", "")
				.replace("]", "");
		return SetlAcctInstituteName;
	}

	@SnowDoc(desc = "删除合同配置信息")
	@Action(propagation = Propagation.REQUIRED)
	public void delTab(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取页面传递的方法基本信息
		UpdateRequestBean reqBean = updateMap.get("ConToPro");
		String conId = reqBean.getParameter("conId");
		String prodId = reqBean.getParameter("prodId");
		MchtContractService.getInstance().delTabPro(conId, prodId);
		MchtContractService.getInstance().delTabRate(conId, prodId);
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				" [商户合同管理]--服务删除合同配置:合同编号[conId]=" + conId });
	}

	/**
	 * 服务配置提交
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "服务配置提交")
	public void serviceSubmit(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtContractInfo");
		/** 导入实体类 */
		MchtContractVO mchtVo = new MchtContractVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
		}

		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		mchtVo.setLastUpdTlr(sessionUserInfo.getTlrno());// 更新操作员
		mchtVo.setLastUpdDateTime(currentTime);// 更新日期时间
		String conState = mchtVo.getConState();
		String conId = mchtVo.getConId();
		String conStateOld = null;
		if (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)) {
			conStateOld = MchtContractService.getInstance().selectConStateByConId(conId);
		}

		mchtVo.setAuditId(ContextUtil.getUUID());
		mchtVo.setSyncState(MchtContractConstants.SYNC_STATE_00);
		if (MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(conState)
				|| (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
						&& MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(conStateOld))) {
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_06);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_11);
			// 循环插入到审核记录表中
			if (list == null || list.size() == 0) {
				SnowExceptionUtil.throwWarnException("未找到审核步骤！");
			}

			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		} else if (MchtContractConstants.MCHT_CONTR_STAT_ON.equals(conState)
				|| MchtContractConstants.MCHT_STAT_05.equals(conState)
				|| (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
						&& MchtContractConstants.MCHT_CONTR_STAT_ON.equals(conStateOld))
				|| (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
						&& MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conStateOld))) {
			addPmpAduitInfo(mchtVo, sessionUserInfo);
			mchtVo.setConState(MchtContractConstants.MCHT_STAT_04);
			// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
			List<PmpAuditProcStep> list = MchtContractService.getInstance().selectTepList(sessionUserInfo.getTlrno(),
					MchtContractConstants.AUDIT_TYPE_10);
			// 循环插入到审核记录表中
			MchtContractService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		}
		MchtContractService.getInstance().updMcht(mchtVo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户合同管理]--服务合同配置提交:合同编号[conId]=" + mchtVo.getConId() });
	}

	/*
	 * 更新审核信息表
	 */
	public void addPmpAduitInfo(MchtContractVO mchtVo, SessionUserInfo sessionUserInfo) throws SnowException {
		String brno = sessionUserInfo.getBrno();
		String auditProcId = MchtContractService.getInstance().selectAuditIdByBrno(brno);
		if (auditProcId == null) {
			SnowExceptionUtil.throwErrorException("未找到审核流程！");
		}
		PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
		PmpAuditInfo.setAuditId(mchtVo.getAuditId());
		PmpAuditInfo.setAuditProcId(auditProcId);
		String conState = mchtVo.getConState();
		if (MchtContractConstants.MCHT_CONTR_STAT_ON.equals(conState)
				|| MchtContractConstants.MCHT_STAT_05.equals(conState)) {
			PmpAuditInfo.setAuditType(MchtContractConstants.AUDIT_TYPE_10);
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")合同登记");// 审核信息描述
			PmpAuditInfo.setAuditUrl("mchtContractAudit.jsp");
		} else if (MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(conState)
				|| MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(conState)
				|| MchtContractConstants.MCHT_STAT_06.equals(conState)) {
			PmpAuditInfo.setAuditType(MchtContractConstants.AUDIT_TYPE_11);
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")合同变更");// 审核信息描述
			PmpAuditInfo.setAuditUrl("mchtContractAudit.jsp");
		} else if (MchtContractConstants.MCHT_STAT_08.equals(conState)) {
			PmpAuditInfo.setAuditType(MchtContractConstants.AUDIT_TYPE_12);
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")合同中止");// 审核信息描述
			PmpAuditInfo.setAuditUrl("mchtContractAudit.jsp");
		} else if (MchtContractConstants.MCHT_CONTR_STAT_OFF.equals(conState)) {
			PmpAuditInfo.setAuditType(MchtContractConstants.AUDIT_TYPE_13);
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")合同恢复");// 审核信息描述
			PmpAuditInfo.setAuditUrl("mchtContractAudit.jsp");
		}
		PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
																		// 14位
		PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
		PmpAuditInfo.setApplyBrNo(brno);// 申请机构编号
		PmpAuditInfo.setAuditState(MchtContractConstants.AUDIT_STATE_00);
		PmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
		PmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
																		// 14位
		DBDao dao = DBDaos.newInstance();
		dao.insert(PmpAuditInfo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				" [商户合同审核管理]--更新审核信息表信息:合同编号[conId]=" + mchtVo.getConId() });
	}
	
	
    /**
     * 根据结算编号查询结算方式
     * @param bean
     * @param key
     * @param request
     * @return
     * @throws SnowException
     */
    public static String getSetlTypeName(FieldBean bean, String key, ServletRequest request) throws SnowException {
        String setlTypeName ="";
        if(StringUtil.isEmpty(key)){
            return setlTypeName;
        }
        String nameById = MchtSetlTypeService.getInstance().getNameById(key);
        if(IfspDataVerifyUtil.isNotBlank(nameById)){
            setlTypeName=nameById;
        } 
        return setlTypeName;
 } 
    
	
}
