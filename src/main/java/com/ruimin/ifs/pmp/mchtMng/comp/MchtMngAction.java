package com.ruimin.ifs.pmp.mchtMng.comp;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.datetime.IfspDateTime;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.chnlMng.comp.MchtChnlRequestAction;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfoTmp;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaesInfoRealMapping;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.SequencePerDay;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtAssistMngService;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.pubTool.servlet.UploadXlsServlet;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.CommonUtil;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.util.CommonConstants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;

@ActionResource
@SnowDoc(desc = "商户管理")
public class MchtMngAction {
	
	private static SessionUserInfo sessionUserInfo = null;
	
	   /**
     * 查询
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryMainAdd(QueryParamBean queryBean) throws SnowException {
        PageQueryResult retResult = new PageQueryResult();
        return retResult;
    }
    /**
     * 查询
     * 
     * @param queryBean
     * @return
     * @throws SnowException
     */
    @Action
    @SnowDoc(desc = "查询")
    public PageResult queryMainInit(QueryParamBean queryBean) throws SnowException {
        /** 查询条件 */
        String qmchtId = queryBean.getParameter("qmchtId");// 商户编号
        String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户编号
        String qmchtMngSel = queryBean.getParameter("qmchtMngSel");// 上级商户
        String qmchtType = queryBean.getParameter("qmchtType");// 商户种类
        String qmchtStat = queryBean.getParameter("qmchtStat");// 商户状态
        String qmchtOrgId = queryBean.getParameter("qmchtOrgId");// 所属机构编号
        /**渠道商户号**/
        String qChlMchtNo=queryBean.getParameter("qChlMchtNo");
        
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String auditId = queryBean.getParameter("auditId");// 审核信息编号
        /** 返回查询结果 */
         PageResult result = MchtMngService.getInstance().queryMainInit(qmchtId,qmchtSimpleName, qmchtMngSel, qmchtType, qmchtStat, qmchtOrgId,
                 sessionUserInfo.getBrCode(),qChlMchtNo,auditId, queryBean.getPage());
         return result;
    }
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
		String qmchtId = queryBean.getParameter("qmchtId");// 商户编号
	    String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户编号
		String qmchtMngSel = queryBean.getParameter("qmchtMngSel");// 上级商户
		String qmchtType = queryBean.getParameter("qmchtType");// 商户种类
		String qmchtStat = queryBean.getParameter("qmchtStat");// 商户状态
		String qmchtOrgId = queryBean.getParameter("qmchtOrgId");// 所属机构编号
		String agentId = queryBean.getParameter("agentId");// 代理商
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        String auditId = queryBean.getParameter("auditId");// 审核信息编号
		/** 返回查询结果 */
		 PageResult result = MchtMngService.getInstance().queryMain(qmchtId,qmchtSimpleName, qmchtMngSel, qmchtType, qmchtStat, qmchtOrgId,
				 agentId, sessionUserInfo.getBrCode(),auditId, queryBean.getPage());
		 return result;
	}

	/**
	 * 仅用于终端信息新增，点击商户编号输入框进行查询 add by wuhd
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain2(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmchtId = queryBean.getParameter("qmchtId");// 商户编号
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户简称
		String qmchtType = queryBean.getParameter("qmchtType");// 商户种类
		String qmchtStat = queryBean.getParameter("qmchtStat");// 商户状态
		String qmchtOrgId = queryBean.getParameter("qmchtOrgId");// 所属机构编号
		String qmchtName = queryBean.getParameter("qmchtName");// 商户全名
		String auditId = queryBean.getParameter("auditId");// 审核信息编号
		 sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 返回查询结果 */
		return MchtMngService.getInstance().queryMain2(qmchtId, qmchtSimpleName, qmchtType, qmchtStat, qmchtOrgId,
				qmchtName, auditId, sessionUserInfo.getBrCode(), queryBean.getPage());
	}
	
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain3(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String mchtId = queryBean.getParameter("mchtId", "");
		Page page = queryBean.getPage();
		 sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 返回查询结果 */
		return MchtMngService.getInstance().queryMain3(mchtId,sessionUserInfo.getBrCode(),page);
	}
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain4(QueryParamBean queryBean) throws SnowException {
		/** 查询条件 */
		String qmchtId = queryBean.getParameter("qqmchtId");// 商户编号
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户简称
		String qmchtType = queryBean.getParameter("qmchtType");// 商户种类
		String qmchtStat = queryBean.getParameter("qmchtStat");// 商户状态
		String qmchtOrgId = queryBean.getParameter("qmchtOrgId");// 所属机构编号
		String qmchtName = queryBean.getParameter("qmchtName");// 商户全名
		String auditId = queryBean.getParameter("auditId");// 审核信息编号
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		/** 返回查询结果 */
		return MchtMngService.getInstance().queryMain4(qmchtId, qmchtSimpleName, qmchtType, qmchtStat, qmchtOrgId,
				qmchtName, auditId, sessionUserInfo.getBrCode(), queryBean.getPage());
	}
	@Action
	@SnowDoc(desc = "查询客户经理电话")
	public static String queryTel(String tlrno) throws SnowException {
		String tel = MchtMngService.getInstance().queryTel(tlrno);
		if(tel!=null){
			return tel;
		}
		return "";
	}

	/**
	 * 新增
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */

	@SnowDoc(desc = "新增")
	public void addMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtInfoAdd");
        //---------------2017.12.14 调用辅表修改action-----------
      UpdateRequestBean reqBeanAssis = updateMap.get("PbsMchtAssistInfoTmp");
      PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();
      /** 自动生成商户号 */
      /**modify by lengjingyu 20180131 生成商户编号:商户号的生成不在以商户基本信息表为准,而是新增了一个商户号累加表,每次获取累加 jira-1977*/
      String mchtId = genMchtId();
      /** modify end jira-1977*/
      PbsMchtBaseInfo mchtVo = new PbsMchtBaseInfo();// 商户信息表-实体类

		/** 导入实体类 */
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
	          Map<String, String> map = reqBeanAssis.next();
	          DataObjectUtils.map2bean(map, assistInfoTmp);
	          assistInfoTmp.setMchtId(mchtId);
		}
	      mchtVo.setMchtId(mchtId);
	     String  mchtLicnNo=MchtMngService.getInstance().queryMchtLicnNo(mchtVo.getMchtLicnNo(),mchtId);
	     if((mchtVo.getMchtLicnNo()).equals(mchtLicnNo)){
	         SnowExceptionUtil.throwWarnException("营业执照号码重复"+mchtLicnNo);        
	     }
		/*****************************
		 * 2017/03/17新增,在新增商户基本信息时判段商户的联系手机号码是否存在用户基本信息表中,且是店员在使用,而不是店长在使用
		 ****************************************/
//		if(!phoneIsShopAssistant(mchtVo.getMchtPhone())){
//			SnowExceptionUtil.throwWarnException("联系手机号码无效,存在店员使用该号码,无法作为店长登陆门户的账号,请修改联系手机号码");
//		}

		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/

      
		/** 状态变更 */
		mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_03);// 新增时状态改为：添加待审核

		/** 金额类型字段，元转分 */
		mchtVo.setMchtRegAmt(CommonUtil.transYuanToFen(mchtVo.getMchtRegAmt()));// 注册资金
		mchtVo.setRecvDeposit(CommonUtil.transYuanToFen(mchtVo.getRecvDeposit()));// 应收保证金
		mchtVo.setPaidDeposit(CommonUtil.transYuanToFen(mchtVo.getPaidDeposit()));// 实收保证金

		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		String currentDate = StringUtils.substring(currentTime, 0, 8);// 当前日期，8位
		mchtVo.setRegDate(currentDate);// 注册日期
		mchtVo.setCrtTlr(sessionUserInfo.getTlrno());// 创建操作员
		mchtVo.setCrtDateTime(currentTime);// 创建日期时间

		/** 过滤数据-> 修改为不过滤数据170228 */
//		boolean mchtTypeFlag = mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_GOV)
//				|| mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_IST)
//				|| mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_FIC);
//		if (mchtTypeFlag) {
			// 插入信息到临时表
//			MchtMngService.getInstance().removeQlf(mchtVo);
//		}

		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
//		if (!mchtTypeFlag) {
			MchtMngService.getInstance().addPic(reqBean, mchtId, mchtVo.getCrtTlr(), mchtVo.getCrtDateTime());
//		}
		String pic=mchtPic(mchtVo,assistInfoTmp);
		String picName;
		if(!("0000".equals(pic))){
		    if("0001".equals(pic)){
	           MchtMngService.getInstance().delPicOne(mchtVo.getMchtId());
	           SnowExceptionUtil.throwWarnException("商户为个体工商和个人商户结算方式不能为对公!");   
		    }else{
		        picName=MchtMngService.getInstance().mchtPicName(pic);
		        MchtMngService.getInstance().delPicOne(mchtVo.getMchtId());
		        SnowExceptionUtil.throwWarnException("图片信息不完整！"+picName);		        
		    }
		}
		// 插入基本信息临时表
		mchtVo.setAuditId(ContextUtil.getUUID());// 设置审核信息编号 32位
		mchtVo.setSyncState(MchtMngConstants.SYNC_STATE_00);// 设置同步状态为：00 已同步
		addMchtPic(mchtVo,assistInfoTmp,sessionUserInfo);

		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户信息管理]--新增:商户编号[mchtId]=" + mchtVo.getMchtId() });
	}
	
	@Action(propagation = Propagation.REQUIRED)
	public void addMchtPic(PbsMchtBaseInfo mchtVo,PbsMchtAssistInfoTmp assistInfoTmp,SessionUserInfo sessionUserInfo)throws SnowException{
        MchtAssistMngService.getInstance().addAssistInfoTmp(assistInfoTmp);
        MchtMngService.getInstance().addMcht(mchtVo);
        /********** 审核：录入审核信息表中 *********/
        // 查询该申请人对应的所有步骤，插入到审核记录表中
        // 获取操作员编号
        String tlrno = sessionUserInfo.getTlrno();
        // 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
        List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno, MchtMngConstants.AUDIT_TYPE_00);
        // 循环插入到审核记录表中
        if (list == null || list.size() == 0) {
            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
        }
        MchtMngService.getInstance().addStepInfo(list, mchtVo.getAuditId());
        /********** 审核：录入审核信息表中 *********/
        MchtMngService.getInstance().addAuditInfo(mchtVo, sessionUserInfo); 
	}
	   /**
     * 发送短信
     * 
     * @param updateMap
     * @throws SnowException
     */
    @Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "发送短信")
    public void noteMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
        /** 加载日志 */
        SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
        /** 获取数据集 */
        UpdateRequestBean reqBean = updateMap.get("mchtInfo");
        
        /** 导入实体类 */
        PbsMchtBaseInfo mchtVo = new PbsMchtBaseInfo();// 商户信息表-实体类
        while (reqBean.hasNext()) {
            DataObjectUtils.map2bean(reqBean.next(), mchtVo);
        }
        Long startTime = System.currentTimeMillis();
        URL url = null;
        String requestMsg=null;
        /** 加载SnowLog */
        Logger log = SnowLog.getLogger(MchtChnlRequestAction.class);
        try {
            url=new URL(SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_ID_NOTE));
            log.info("发送短信【" + url + "】-请求开始...");
            log.info("发送短信：" + mchtVo.getMchtId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mchtId", mchtVo.getMchtId());//
            map.put("phoneNo", mchtVo.getMchtPhone());//
            map.put("extraData", "QRS002");//
            map.put("msgType", "1");//
            requestMsg = new Gson().toJson(map);            
            String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);// 请求方式
            /** 建立服务器连接 */
            HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
            
            /** 发送报文 */
            HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
            
            // -----------------------------------------STEP NO3
            // 获取响应-----------------------------------------//
            /** 获取服务端响应 */
            String resMsg  = HttpTransUtil.getInstance().recvResponse(urlConnection);
            Map<String, String> serRetMap = new HashMap<String, String>();
            Gson gson = new Gson();
            serRetMap = gson.fromJson(resMsg, serRetMap.getClass());
            String respCode = serRetMap.get("respCode");// 响应码 
            // 截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
            if (respCode.substring((respCode.length() - 4), respCode.length())
                    .equals(MchtChnlRequestConstants.CHL_APPLY_SUCCESS)) {   
                log.info("发送短信成功" + mchtVo.getMchtId());
            }else{
                log.info("发送短信成功" + mchtVo.getMchtId());
                SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
            }

            /******************************************** 计时结束 ********************************************/
            log.info("发送短信成功【" + url + "】-请求完成，耗时：" + (System.currentTimeMillis() - startTime) + " ms ");

        } catch (Exception se) {
            SnowExceptionUtil.throwErrorException(se.getMessage());
            log.error("发送短信步失败【" + url + "商户号：" + mchtVo.getMchtId() + "】，原因：" + se.getMessage());
            log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
        }
        
        /** 打印日志 */
        sessionUserInfo.addBizLog( "[商户信息管理]--发送短信:商户编号[mchtId]=" + mchtVo.getMchtId(), new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno()
                ,"noteMcht.log"});   
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
		
	    //---------------2017.12.14 调用辅表修改action-----------
		//---------------------------------------------------
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtInfo");
        UpdateRequestBean reqBeanAss = updateMap.get("PbsMchtAssistInfoTmp");

	      PbsMchtAssistInfoTmp assistInfoTmp=new PbsMchtAssistInfoTmp();

		/** 导入实体类 */
		PbsMchtBaseInfo mchtVo = new PbsMchtBaseInfo();
		while (reqBean.hasNext()) {
	          Map<String, String> map = reqBeanAss.next();
	            DataObjectUtils.map2bean(map, assistInfoTmp);
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
		}
	       String  mchtLicnNo=MchtMngService.getInstance().queryMchtLicnNo(mchtVo.getMchtLicnNo(),mchtVo.getMchtId());
	         if((mchtVo.getMchtLicnNo()).equals(mchtLicnNo)){
	             SnowExceptionUtil.throwWarnException("营业执照号码重复"+mchtLicnNo);        
	         }
		/*****************************
		 * 2017/03/17新增,在修改商户基本信息时判段商户的联系手机号码是否存在用户基本信息表中,且是店员在使用,而不是店长在使用
		 ****************************************/
//		if(!phoneIsShopAssistant(mchtVo.getMchtPhone())){
//			SnowExceptionUtil.throwWarnException("联系手机号码无效,存在店员使用该号码,无法作为店长登陆门户的账号,请修改联系手机号码");
//		}

		/*****************************
		 * STEP NO2 封装数据
		 ****************************************/
		/** 加载日志 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		
		/** 放置商户类型 */
		mchtVo.setMchtType(reqBean.getTotalList().get(0).get("mchtTypeUpd"));

		/** 金额类型字段，元转分 */
		mchtVo.setMchtRegAmt(CommonUtil.transYuanToFen(mchtVo.getMchtRegAmt()));// 注册资金
		mchtVo.setRecvDeposit(CommonUtil.transYuanToFen(mchtVo.getRecvDeposit()));// 应收保证金
		mchtVo.setPaidDeposit(CommonUtil.transYuanToFen(mchtVo.getPaidDeposit()));// 实收保证金

		/** 更新基本信息 */
		String currentTime = BaseUtil.getCurrentDateTime();// 当前时间，14位
		String lastUpdTlr = sessionUserInfo.getTlrno();
		String lastUpdAteTime = currentTime;
		mchtVo.setLastUpdTlr(lastUpdTlr);// 更新操作员
		mchtVo.setLastUpdDateTime(lastUpdAteTime);// 更新日期时间

		/** 过滤数据 -> 修改为不过滤数据170228*/
//		boolean mchtTypeFlag = mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_GOV)
//				|| mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_IST)
//				|| mchtVo.getMchtType().equals(MchtMngConstants.MCHT_TYPE_FIC);
//		if (mchtTypeFlag) {
//			MchtMngService.getInstance().removeQlf(mchtVo);
//		}

		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		/** 如果商户类型为政府机构、事业单位、虚拟商户，删除该商户编号下的图片信息；反之更新 */
//		boolean mchtTypeFlag=false;
//		if (mchtTypeFlag) {
//			MchtMngService.getInstance().delPic(mchtVo.getMchtId());
//		} else {

		//if (!(MchtMngService.getInstance().queryPicExist(mchtVo.getMchtId()))) {        
		    MchtMngService.getInstance().addPic(reqBean, mchtVo.getMchtId(), sessionUserInfo.getTlrno(),
                    currentTime);
		    MchtMngService.getInstance().queryMchtPic(reqBean, mchtVo.getMchtId(), lastUpdTlr, lastUpdAteTime);

        //}
		
		        
//		        if (MchtMngService.getInstance().queryPicExist(mchtVo.getMchtId())) {
//	                MchtMngService.getInstance().updPic(reqBean, mchtVo.getMchtId(), lastUpdTlr, lastUpdAteTime);
//	            } 
//		}
		// 插入基本信息临时表
		// *****************修改时，新加一种状态，当新增被拒绝时，此时点击修改，按新增待审核流程走************//
		String currentDate = StringUtils.substring(currentTime, 0, 8);// 当前日期，8位
		String mchtStat = mchtVo.getMchtStat();
		if (MchtMngConstants.MCHT_STAT_08.equals(mchtStat)) {
			/** 状态变更 */
			mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_03);// 新增时状态改为：添加待审核
		} else if(MchtMngConstants.MCHT_STAT_97.equals(mchtStat)){
		      mchtVo.setRegDate(currentDate);// 注册日期
		        mchtVo.setCrtTlr(sessionUserInfo.getTlrno());// 创建操作员
		        mchtVo.setCrtDateTime(currentTime);// 创建日期时间
			mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_03);// 补录时状态改为：添加待审核
		}else{
	          mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_04);// 修改时状态改为：修改待审核
		}
		mchtVo.setAuditId(ContextUtil.getUUID());// 设置审核信息编号 32位
		mchtVo.setSyncState(MchtMngConstants.SYNC_STATE_00);// 设置同步状态为：00 已同步

		
		//把商户号传给assistInfoTmp
		assistInfoTmp.setMchtId(mchtVo.getMchtId());
		updMchtPic(updateMap,mchtVo,assistInfoTmp,sessionUserInfo);
		/** 打印日志 */
		if(mchtVo.getMchtStat().equals(MchtMngConstants.MCHT_STAT_03)){
		    sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
	                "[商户信息管理]--新增:商户编号[mchtId]=" + mchtVo.getMchtId() });
		}else{
		    sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
		            "[商户信息管理]--修改:商户编号[mchtId]=" + mchtVo.getMchtId() });		    
		}
	}


	public void updMchtPic(Map<String, UpdateRequestBean> updateMap,PbsMchtBaseInfo mchtVo,PbsMchtAssistInfoTmp assistInfoTmp,SessionUserInfo sessionUserInfo)throws SnowException{

	    MchtAssistMngService.getInstance().updAssistInfoTmp(assistInfoTmp);
	        MchtMngService.getInstance().updMcht(mchtVo);
	        /********** 审核：录入审核信息表中 *********/
	        // 查询该申请人对应的所有步骤，插入到审核记录表中
	        // 获取操作员编号
	        String tlrno = sessionUserInfo.getTlrno();
	        // 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
	        List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno,
	                mchtVo.getMchtStat().equals(MchtMngConstants.MCHT_STAT_03) ? MchtMngConstants.AUDIT_TYPE_00
	                        : MchtMngConstants.AUDIT_TYPE_01);
	        // 循环插入到审核记录表中
	        if (list == null || list.size() == 0) {
	            SnowExceptionUtil.throwWarnException("未找到审核步骤！");
	        }
	        MchtMngService.getInstance().addStepInfo(list, mchtVo.getAuditId());
	        /********** 审核：录入审核信息表中 *********/
	        MchtMngService.getInstance().addAuditInfo(mchtVo, sessionUserInfo);
	}
	/**
	 * 冻结/解冻
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "冻结/解冻")
	public void frzMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtInfo");

		/** 导入实体类 */
		PbsMchtBaseInfo mchtVo = new PbsMchtBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
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
		String mchtStat = mchtVo.getMchtStat();// 商户状态
		String mchtId = mchtVo.getMchtId();// 商户编号
		if (MchtMngConstants.MCHT_STAT_NORMAL.equals(mchtStat)) {
			mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_05);
		}
		if (MchtMngConstants.MCHT_STAT_NET.equals(mchtStat)) {
			mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_05);
		}
		if (MchtMngConstants.MCHT_STAT_FRZ.equals(mchtStat)) {
			mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_06);
		}
		// 审核新加字段
		mchtVo.setAuditId(ContextUtil.getUUID());// 设置审核信息编号 32位
		mchtVo.setSyncState(MchtMngConstants.SYNC_STATE_00);// 设置同步状态为：00 已同步
		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/

		MchtMngService.getInstance().frzMcht(mchtVo.getAuditId(), mchtVo.getSyncState(), trlno, lastUpdDateTime, mchtVo,
				mchtId);
		/********** 审核：录入审核信息表中 *********/
		// 查询该申请人对应的所有步骤，插入到审核记录表中
		// 获取操作员编号
		String tlrno = sessionUserInfo.getTlrno();
		// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
		List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno,
				mchtVo.getMchtStat().equals(MchtMngConstants.MCHT_STAT_05) ? MchtMngConstants.AUDIT_TYPE_02
						: MchtMngConstants.AUDIT_TYPE_03);
		// 循环插入到审核记录表中
		if (list == null || list.size() == 0) {
			SnowExceptionUtil.throwWarnException("未找到审核步骤！");
		}
		MchtMngService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		/********** 审核：录入审核信息表中 *********/
		MchtMngService.getInstance().addAuditInfo(mchtVo, sessionUserInfo);

		/** 打印日志 */
		if (mchtStat.equals(MchtMngConstants.MCHT_STAT_FRZ)) {
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户信息管理]--解冻:商户编号[mchtId]=" + mchtVo.getMchtId() });
		} else {
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[商户信息管理]--冻结:商户编号[mchtId]=" + mchtVo.getMchtId() });
		}
	}

	/**
	 * 注销
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "注销")
	public void offMcht(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/*****************************
		 * STEP NO1 解析数据
		 ****************************************/
		/** 获取数据集 */
		UpdateRequestBean reqBean = updateMap.get("mchtInfo");

		/** 导入实体类 */
		PbsMchtBaseInfo mchtVo = new PbsMchtBaseInfo();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), mchtVo);
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
		String mchtId = mchtVo.getMchtId();// 商户编号
		
		/*****************************
		 * STEP NO3 数据入库
		 ****************************************/
		mchtVo.setMchtStat(MchtMngConstants.MCHT_STAT_07);
		mchtVo.setAuditId(ContextUtil.getUUID());// 设置审核信息编号 32位
		mchtVo.setSyncState(MchtMngConstants.SYNC_STATE_00);// 设置同步状态为：00 已同步
		// 注销时状态改为：注销待审核
		MchtMngService.getInstance().offMcht(mchtVo.getAuditId(), mchtVo.getSyncState(), trlno, lastUpdDateTime,
				mchtVo.getMchtStat(), mchtId);
		/********** 审核：录入审核信息表中 *********/
		// 查询该申请人对应的所有步骤，插入到审核记录表中
		// 获取操作员编号
		String tlrno = sessionUserInfo.getTlrno();
		// 根据操作员编号，查询机构级别，查询审核流程编号，查询对应的审核步骤
		List<PmpAuditProcStep> list = MchtMngService.getInstance().selectTepList(tlrno, MchtMngConstants.AUDIT_TYPE_04);
		// 循环插入到审核记录表中
		if (list == null || list.size() == 0) {
			SnowExceptionUtil.throwWarnException("未找到审核步骤！");
		}
		MchtMngService.getInstance().addStepInfo(list, mchtVo.getAuditId());
		/********** 审核：录入审核信息表中 *********/
		MchtMngService.getInstance().addAuditInfo(mchtVo, sessionUserInfo);
		/** 打印日志 */
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[商户信息管理]--注销:商户编号[mchtId]=" + mchtVo.getMchtId() });
	}

	/**
	 * 页面查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "商户合同的引用商户名页面查询")
	public PageResult queryMainMcht(QueryParamBean queryBean) throws SnowException {
		String type = queryBean.getParameter("type");
		/** 查询条件 */
		String qmchtId = queryBean.getParameter("qmchtId");// 商户编号
		String qmchtSimpleName = queryBean.getParameter("qmchtSimpleName");// 商户简称
		String qmchtType = queryBean.getParameter("qmchtType");// 商户种类
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		Log.info("type:"+type);
		Log.info("qmchtId:"+qmchtId);
		Log.info("qmchtSimpleName:"+qmchtSimpleName);
		Log.info("qmchtType:"+qmchtType);
		/** 返回查询结果 */
		if(type!=null){
			return MchtMngService.getInstance().queryMainMcht1(qmchtId, qmchtSimpleName,
					sessionUserInfo.getBrCode(), queryBean.getPage());
		}		
		return MchtMngService.getInstance().queryMainMcht(qmchtId, qmchtSimpleName, qmchtType,
				sessionUserInfo.getBrCode(), queryBean.getPage());
		
	}

	/**
	 * 查询【地区码】
	 * 
	 * @param qmchtId
	 *            商户编号
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询【地区码】以及商户简称和商户全名")
	public Map<String, Object> queryMchtAreaNo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		/** 获取前台参数 */
		String mchtAreaNo="";
		String mchtName="";
		String mchtNameAbbr="";
		String mchtContact="";
		String mchtContactPhone="";
		String mchtSerPhone ="";
		String mchtContactEmail="";
		String ctName = "";
		
		//根据商户号 查询商户全名和商户地区码 返回给 jsp 页面 ，jsp页面使用param 获取map中的值
		String qmchtId = updateMap.get("pagyMchtMng").getParameter("payMchtNo");
		//String mchtAreaNo = MchtMngService.getInstance().queryMchtAreaNo(qmchtId);
		//PbsMchtBaseInfoReal mchtInfoReal = MchtMngService.getInstance().queryMchtAreaNo(qmchtId);
		PbsMchtBaesInfoRealMapping mchtInfoReal = MchtMngService.getInstance().queryMchtAreaNoMapp(qmchtId);
		if(mchtInfoReal!=null){
			mchtAreaNo = mchtInfoReal.getMchtAreaNo();
			mchtName = mchtInfoReal.getMchtName();
			mchtNameAbbr=mchtInfoReal.getMchtSimpleName();
			mchtContactPhone=mchtInfoReal.getMchtPhone();
			mchtSerPhone=mchtContactPhone;
			mchtContactEmail=mchtInfoReal.getMchtEmail();
			mchtContact=mchtInfoReal.getMchtPersonName();
			ctName = mchtInfoReal.getCtName();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchtAreaNo", mchtAreaNo);
		map.put("mchtName", mchtName);
		map.put("mchtNameAbbr",mchtNameAbbr);
		map.put("mchtContact", mchtContact);
		map.put("mchtContactPhone", mchtContactPhone);
		map.put("mchtSerPhone", mchtSerPhone);
		map.put("mchtContactEmail", mchtContactEmail);
		map.put("ctName", ctName);
		return map;
	}
	
	//-----------2017/03/17新增------------------------
	/**
	 * 判断该手机号码时候被店员使用
	 * @param phone
	 * @return
	 * @throws SnowException
	 */
//	public boolean phoneIsShopAssistant(String phone) throws SnowException{
//		List<Object> list = UserMngService.getInstance().queryMssByPhoneNo(phone);
//		if(list.size() > 0){
//			for (Object object : list) {
//				MssUserBaseInfVO vo =(MssUserBaseInfVO) object;
//				if("1".equals(vo.getUserType())){//存在店员在使用这个号码
//					return false;
//				}
//			}
//		}
//		return true;
//	}
	@Action(propagation = Propagation.REQUIRED)
    @SnowDoc(desc = "批量导入")
    public void batchImport(FileItem item) throws SnowException,IOException {
	    SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
	    String currentDateTime = BaseUtil.getCurrentDateTime();
	    /** step no 1 : 转换页面传入参数 */
        //获取文件中的数据
        String[][] result = UploadXlsServlet.getData(item, 1);
        int rowCount = result.length;

        if(rowCount == 0){
            SnowExceptionUtil.throwErrorException("文件内容为空，无法导入！");
        }
        /** step no 2 : 获取全量商户信息和白名单列表 */
      //获取全量的商户号列表
        List<String> mchtIdList = MchtMngService.getInstance().getAllMchtId();
       //
        List<Object> list=MchtMngService.getInstance().getMchtTlrno(sessionUserInfo.getTlrno());
        //
        /** step no 3 : 校验数据有效性 */
        PbsMchtBaseInfo mchtVo;        
        String mchtId;//商户编号
        String mchtSimpleName;//商户简称
        String mchtName;//商户全名
        String mchtType;//商户种类
        String mchtStat;//商户状态
        String mchtMngNo;//上级商户
        String mchtPersonName;//联系人
        String mchtPhone;//手机号
        String telephone;//固定电话
        String mchtEmail;//邮箱
        String mchtContAddr;//联系地址
        String mchtOrgId;//所属机构
        String mchtAmrNo;//业务员号
        String mchtAmrName;//业务员姓名
        String riskLevel;//商户风险等级
        String isExistAgent;//是否存在代理商
        String agentId;//代理商
        String mchtLicnNo = null;//营业执照号码
        String mchtLicnType = null;//执照类型
        String mchtLicnExpDate= null;//执照有效期
        String mchtMngScope= null;//经营范围
        String mchtRegAmt= null;//注册资金
        String currencyType= null;//注册资金币种
        String organizationCode= null;//组织机构代码
        String mchtRegAddr= null;//注册地址
        String mchtTrcnNo= null;//税务登记证号码
        String mchtArtifName= null;//法人姓名
        String mchtArtifType= null;//法人证件类型
        String mchtArtifId= null;//法人证件号码
        String mchtArtifPhone= null;//法人电话
        String recvDeposit= null;//应收保证金
        String paidDeposit= null;//实收保证金
        String mchtLicnSdate = null;//营业执照生效日期
        String artifSdate = null;//法人证件生效日期
        String artifEdate = null;//法人证件失效日期
        //验证正则 
        /**验证固定手机电话 */
        String isPhone = "^(\\d{3,4}-)?\\d{6,8}(-\\d{1,4})?$";
        String isEmail= "^[a-zA-Z0-9]([a-zA-Z0-9\\.]*)@([a-zA-Z0-9]*)\\.([a-zA-Z]{2,3})$";
        /**验证手机电话 */
        String isCellPhone="^1[3|4|5|8][0-9]\\d{8}$";
        String string="^(d{6})(18|19|20)?(d{2})([01]d)([0123]d)(d{3})(d|X|x)?$";
        /**验证金额*/
        String isAmt12="^([1-9][\\d]{0,9}|[1-9][\\d]{0,2}(\\,[\\d]{3})*|0)(.[\\d]{1,2})?$";//最大长度12位，最多包含两位小数，支持带','格式，需和isAmtLength12匹配使用
        String isAmtLength12="^([\\d]{0,10})(\\.[\\d]{1,2})?$";//去除','，最大长度12位，最多包含两位小数
        String isAmt20="^([1-9][\\d]{0,17}|[1-9][\\d]{0,2}(\\,[\\d]{3})*|0)(\\.[\\d]{1,2})?$";//最大长度20位，最多包含两位小数，支持带','格式，需和isAmtLength20匹配使用
        String isAmtLength20="^([\\d]{0,18})(\\.[\\d]{1,2})?$";//去除','，最大长度20位，最多包含两位小数
        Pattern pattern = null;
        int s;//列数
        //验证商户类型为政府机构或个体商户时，资质信息是否有数据
        List<PbsMchtBaseInfo> lstmchtVoList = new ArrayList<PbsMchtBaseInfo>();
        boolean b=true;
        for(int i = 0; i< rowCount;i++){
            /**
             * 封装bean对象
             * */
            mchtVo=new PbsMchtBaseInfo();
            mchtId = MchtMngService.getInstance().genMchtId();
            long l = Long.parseLong(mchtId)+i;
            mchtId=String.valueOf(l);
            mchtVo.setMchtId(mchtId);
            s=result[i].length;
            if(s<14){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行数据有误请查看后在重新上传");

            }
            mchtSimpleName = result[i][0].replaceAll(" ", "");
            if("".equals(mchtSimpleName)||mchtSimpleName==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户简称为空限制");
            }
            if(mchtSimpleName.length()>20){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户简称长度超过");
            }
            mchtVo.setMchtSimpleName(mchtSimpleName);
            mchtName = result[i][1].replaceAll(" ", "");
            if("".equals(mchtName)||mchtName==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户名称为空");
            }
            if(mchtName.length()>20){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户名称长度超过限制");
            }
            mchtVo.setMchtName(mchtName);
            mchtType = result[i][2].replaceAll(" ", "");
            if("".equals(mchtType)||mchtType==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户种类为空");
            }
            if("01".equals(mchtType) || "02".equals(mchtType) || "03".equals(mchtType)|| "04".equals(mchtType)){                
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户种类错误");
            }
            mchtVo.setMchtType(mchtType);
            mchtStat = result[i][3].replaceAll(" ", "");
            if("".equals(mchtStat)||mchtStat==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户状态为空");
            }
            if("97".equals(mchtStat)&&mchtStat.length()==2){
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户状态只能为97");                
            }
            mchtVo.setMchtStat(mchtStat);
            mchtMngNo = result[i][4].replaceAll(" ", "");
            //验证上级商户是否存在
            boolean bool=false;
            if("".equals(mchtMngNo)){
                bool=true;
                }else{
                    for(int j=0;j<mchtIdList.size();j++){
                        if(mchtMngNo.equals(mchtIdList.get(j))){
                           bool=true;
                        }                
                    }
                }
            if(bool){                
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行上级商户没有");  
            }
            mchtVo.setMchtMngNo(mchtMngNo);
            mchtPersonName = result[i][5].replaceAll(" ", "");
            if("".equals(mchtPersonName)||mchtPersonName==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人为空");
            }
            if(mchtPersonName.length()>10){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人姓名长度超过限制");
            }
            mchtVo.setMchtPersonName(mchtPersonName);
            mchtPhone = result[i][6].replaceAll(" ", "");
            if(!"".equals(mchtPhone)&&mchtPhone!=null){
                //SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人电话为空");
                if(mchtPhone.length()>11){
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人电话长度不正确");
                }
              //正则验证测试中
                if(!pattern.compile(isCellPhone).matcher(mchtPhone).matches()){
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人电话格式不正确");
                }
            }
            
            mchtVo.setMchtPhone(mchtPhone);
            //----------------------------新增固话与邮箱验证(非资质信息)----------------------------------//
            telephone = result[i][29].replaceAll(" ", "");  
            if(!"".equals(telephone)&&telephone!=null){
                //SnowExceptionUtil.throwErrorException("第"+(i+1)+"行固定电话为空");
                //正则验证测试中
                if(!pattern.compile(isPhone).matcher(telephone).matches()){
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行固定电话格式错误");
                }
            }
           
            mchtVo.setTelephone(telephone);
            
            mchtEmail = result[i][30].replaceAll(" ", ""); 
            if (!"".equals(mchtEmail)&&mchtEmail!=null) {
                //正则验证测试中
                if(!pattern.compile(isEmail).matcher(mchtEmail).matches()){
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行邮箱格式错误");
                }
            }
           
            mchtVo.setMchtEmail(mchtEmail);
            //-------------------------------------------------------------------------//
            
            
            
            mchtContAddr = result[i][7].replaceAll(" ", "");
            if(mchtContAddr.length()>40){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行联系人地址长度超过限制");
            }
            mchtVo.setMchtContAddr(mchtContAddr);
            mchtOrgId = result[i][8].replaceAll(" ", "");
            if("".equals(mchtOrgId)||mchtOrgId==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行所属机构为空");                
            }
            bool=false;
                    for(int j=0;j<list.size();j++){
                        TblBctl tb=(TblBctl) list.get(j);
                        if(mchtOrgId.equals(tb.getBrno())){
                            mchtOrgId=tb.getBrcode();
                           bool=true;
                        }                
                    }
            if(bool){                
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行所属机构不是操作员所属机构或下属机构");  
            }
//            if("".equals(mchtOrgId)||mchtOrgId==null){
//                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行所属机构不是操作员所属机构或下属机构");
//            }
//            if(mchtOrg.length()>20){
//                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行所属机构长度超过限制");
//            }
            mchtVo.setMchtOrgId(mchtOrgId);
            mchtAmrNo = result[i][9].replaceAll(" ", "");
            if(mchtAmrNo.length()>20){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行客户经理工号");
            }
            mchtVo.setMchtAmrNo(mchtAmrNo);
            mchtAmrName = result[i][10].replaceAll(" ", "");
            if(mchtAmrName.length()>10){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行客户经理姓名");
            }
            mchtVo.setMchtAmrName(mchtAmrName);
            riskLevel = result[i][11].replaceAll(" ", "");
            if("".equals(riskLevel)||riskLevel==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户风险等级为空");
            }
            if("01".equals(riskLevel)||"02".equals(riskLevel)||"03".equals(riskLevel)||"04".equals(riskLevel)||"05".equals(riskLevel)){
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户风险等级错误");                
            }
            mchtVo.setRiskLevel(riskLevel);
            isExistAgent = result[i][12].replaceAll(" ", "");
            if("".equals(isExistAgent)||isExistAgent==null){
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行是否存在代理商为空");
            }
            if("00".equals(isExistAgent)||"99".equals(isExistAgent)){
            }else{
                SnowExceptionUtil.throwErrorException("第"+(i+1)+"行是否存在代理商错误");                
            }
            mchtVo.setIsExistAgent(isExistAgent);
            agentId = result[i][13].replaceAll(" ", "");
            if("00".equals(isExistAgent)){
                String str=MchtMngService.getInstance().getAgentId(agentId);
                if(agentId.equals(str)){
                }else{
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行代理商不存在");                
                }                
            }else{
                    if("".equals(agentId)||agentId==null){
                    }else{
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行不存在代理商，请不要加入代理商");                                      
                }
            }
            mchtVo.setAgentId(agentId);
            //判断商户类型
            if("01".equals(mchtType) || "04".equals(mchtType)){ 
                if(s>14){
                    mchtLicnNo = result[i][14].replaceAll(" ", "");    //第15列营业执照号码               
                    if(mchtLicnNo.length()>64){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行营业执照号码长度超过限制");
                    }
                }
                mchtVo.setMchtLicnNo(mchtLicnNo);
                if(s>15){
                    mchtLicnType = result[i][15].replaceAll(" ", "");                    
                    if("01".equals(mchtLicnType)||"02".equals(mchtLicnType)||"".equals(mchtLicnType)||mchtLicnType==null){
                    }else{
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行执照类型类型不符");                
                    }
                }
                mchtVo.setMchtLicnType(mchtLicnType);
                
                if(s>16){
                    mchtLicnExpDate = result[i][16].replaceAll(" ", "");
                    if(mchtLicnExpDate.length()>8){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行执照有效期长度应为8位");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   
                        try {
                            Date parse1 = sdf.parse(mchtLicnExpDate);
                            String parse2 = sdf.format(parse1);
              
                           if(!parse2.equals(mchtLicnExpDate)){
                                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行执照有效期日期输入有误");
                           }
                        } catch (ParseException e) {
                            SnowExceptionUtil.throwErrorException("第"+(i+1)+"行执照有效期格式有误");
                        }
                }
                mchtVo.setMchtLicnExpDate(mchtLicnExpDate);
                
                
                if(s>17){
                    mchtMngScope = result[i][17].replaceAll(" ", "");                    
                    if(mchtMngScope.length()>40){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"经营范围长度超过限制");
                    }
                }
                mchtVo.setMchtMngScope(mchtMngScope);
                if(s>18){
                    mchtRegAmt = result[i][18].replaceAll(" ", "");                    
                    if("".equals(mchtRegAmt)||mchtRegAmt==null){
                        if(!pattern.compile(isAmt20).matcher(mchtRegAmt).matches()){                   
                            if(!pattern.compile(isAmtLength20).matcher(mchtRegAmt).matches()){
                                SnowExceptionUtil.throwErrorException("第"+(i+1)+"【注册资金】格式错误（最大长度20位，最多包含两位小数,支持整数部分每3位逗号分隔）");
                            }
                        }
                    }
                    if(mchtRegAmt.length()>20){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"注册资金长度超过限制");
                    }
                }
                mchtVo.setMchtRegAmt(mchtRegAmt);
                if(s>19){
                    currencyType = result[i][19].replaceAll(" ", "");                    
                    if("156".equals(currencyType)||"".equals(currencyType)||currencyType==null){
                    }else{
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"注册资金币种类型错误");                
                    }
                }
                mchtVo.setCurrencyType(currencyType);
                if(s>20){
                    organizationCode = result[i][20].replaceAll(" ", "");                    
                    if(organizationCode.length()>32){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"组织机构代码长度超过限制");
                    }
                }
                mchtVo.setOrganizationCode(organizationCode);
                if(s>21){
                    mchtRegAddr = result[i][21].replaceAll(" ", "");                    
                    if(mchtRegAddr.length()>40){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"注册地址长度超过限制");
                    }
                }
                mchtVo.setMchtRegAddr(mchtRegAddr);
                if(s>22){
                    mchtTrcnNo = result[i][22].replaceAll(" ", "");                    
                    if(mchtTrcnNo.length()>64){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"税务登记号码长度超过限制");
                    }
                }
                mchtVo.setMchtTrcnNo(mchtTrcnNo);
                if(s>23){
                    mchtArtifName = result[i][23].replaceAll(" ", "");                    
                    if(mchtArtifName.length()>10){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"法人姓名长度超过限制");
                    }
                }
                mchtVo.setMchtArtifName(mchtArtifName);
                if(s>24){
                    mchtArtifType = result[i][24].replaceAll(" ", "");                    
                    if("00".equals(mchtArtifType)||"01".equals(mchtArtifType)||"02".equals(mchtArtifType)||"03".equals(mchtArtifType)||"".equals(mchtArtifType)||mchtArtifType==null){
                    }else{
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"法人证件类型错误");                    
                    }
                }
                mchtVo.setMchtArtifType(mchtArtifType);
                if(s>25){
                    mchtArtifId = result[i][25].replaceAll(" ", "");                    
                    if(mchtArtifId.length()>32){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"法人证件号码长度超过限制");
                    }
                }
                mchtVo.setMchtArtifId(mchtArtifId);
                if(s>26){
                    mchtArtifPhone = result[i][26].replaceAll(" ", "");                    
                    if(pattern.compile(isCellPhone).matcher(mchtArtifPhone).matches()||"".equals(mchtArtifPhone)||mchtArtifPhone==null){
                    }else{
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人电话格式不正确");                    
                    }
                    if(mchtArtifPhone.length()>32){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"法人电话长度超过限制");
                    }
                }
                mchtVo.setMchtArtifPhone(mchtArtifPhone);
                if(s>27){
                    recvDeposit = result[i][27].replaceAll(" ", "");                    
                    if("".equals(recvDeposit)||recvDeposit==null){
                        if(!pattern.compile(isAmt12).matcher(recvDeposit).matches()){                   
                            if(!pattern.compile(isAmtLength12).matcher(recvDeposit).matches()){
                                SnowExceptionUtil.throwErrorException("第"+(i+1)+"【应收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
                            }
                        }
                    }
                    if(recvDeposit.length()>12){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"应收保证金长度超过限制");
                    }
                }
                mchtVo.setRecvDeposit(recvDeposit);
                if(s>28){
                    paidDeposit = result[i][28].replaceAll(" ", "");                    
                    if("".equals(paidDeposit)||paidDeposit==null){
                        if(!pattern.compile(isAmt12).matcher(paidDeposit).matches()){                   
                            if(!pattern.compile(isAmtLength12).matcher(paidDeposit).matches()){
                                SnowExceptionUtil.throwErrorException("第"+(i+1)+"【实收保证金】格式错误（最大长度12位，最多包含两位小数,支持整数部分每3位逗号分隔）");
                            }
                        }
                    }
                    if(paidDeposit.length()>12){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"实收保证金长度超过限制");
                    }
                }
                mchtVo.setPaidDeposit(paidDeposit);
                
      //------------------------------------------------------------------------------------------------------         
                if (s>31) {//大于31列,判断营业执照生效日期
                    mchtLicnSdate = result[i][31].replaceAll(" ", "");
                    if(mchtLicnSdate.length()>8){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行营业执照生效日期长度应为8位");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   
                        try {
                            Date parse1 = sdf.parse(mchtLicnSdate);
                            String parse2 = sdf.format(parse1);
              
                           if(!parse2.equals(mchtLicnSdate)){
                                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行营业执照生效日期输入有误");
                           }
                        } catch (ParseException e) {
                            SnowExceptionUtil.throwErrorException("第"+(i+1)+"行营业执照生效日期格式有误");
                        }
                }
                mchtVo.setMchtLicnSdate(mchtLicnSdate);
          
      //------------------------------------------------------------------------------------------------------         
             
                if (s>32) {//大于32列,判断法人证件生效日期
                    artifSdate = result[i][32].replaceAll(" ", "");
                    if(artifSdate.length()>8){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件生效日期长度应为8位");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   
                        try {
                            Date parse1 = sdf.parse(artifSdate);
                            String parse2 = sdf.format(parse1);
              
                           if(!parse2.equals(artifSdate)){
                                 SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件生效日期输入有误");
                           }
                           
                        } catch (ParseException e) {
                            SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件生效日期格式有误");
                        }
                        
              
                }
                mchtVo.setArtifSdate(artifSdate);
     
       //------------------------------------------------------------------------------------------------------         
                
                if (s>33) {//判断第34列法人证件失效日期
                    artifEdate = result[i][33].replaceAll(" ", "");
                    if(artifEdate.length()>8){
                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件失效日期长度应为8位");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                   
                        try {
                            Date parse1 = sdf.parse(artifEdate);
                            String parse2 = sdf.format(parse1);
              
                           if(!parse2.equals(artifEdate)){
                                        SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件失效日期输入有误");
                           }
                         //验证  生效日期是否在失效日期之前
                           Date parse3 = sdf.parse(artifSdate);
                           if (parse3.after(parse1)) {//如果生效日期在失效日期之后
                               SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件生效日期不能在失效日期之后!");
                        }
                        } catch (ParseException e) {
                            SnowExceptionUtil.throwErrorException("第"+(i+1)+"行法人证件失效日期格式有误");
                        }
                }
                mchtVo.setArtifEdate(artifEdate); 
                /** 金额类型字段，元转分 */
                if(mchtVo.getMchtRegAmt()!=null){
                    mchtVo.setMchtRegAmt(CommonUtil.transYuanToFen(mchtVo.getMchtRegAmt()));// 注册资金                    
                }
                if(mchtVo.getRecvDeposit()!=null){
                    mchtVo.setRecvDeposit(CommonUtil.transYuanToFen(mchtVo.getRecvDeposit()));// 应收保证金                    
                }
                if(mchtVo.getPaidDeposit()!=null){
                    mchtVo.setPaidDeposit(CommonUtil.transYuanToFen(mchtVo.getPaidDeposit()));// 实收保证金                    
                }
            }else{
//                if(s>17){
//                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户种类为政府机构或个体商户，不应该有资质信息。请确认数据正确");
//                }
                mchtLicnNo = result[i][14].replaceAll(" ", "");
                mchtLicnType = result[i][15].replaceAll(" ", "");
                mchtLicnExpDate = result[i][16].replaceAll(" ", "");
                mchtMngScope = result[i][17].replaceAll(" ", "");
                mchtRegAmt = result[i][18].replaceAll(" ", "");
                currencyType = result[i][19].replaceAll(" ", "");
                organizationCode = result[i][20].replaceAll(" ", "");
                mchtRegAddr = result[i][21].replaceAll(" ", "");
                mchtTrcnNo = result[i][22].replaceAll(" ", "");
                mchtArtifName = result[i][23].replaceAll(" ", "");
                mchtArtifType = result[i][24].replaceAll(" ", "");
                mchtArtifId = result[i][25].replaceAll(" ", "");
                mchtArtifPhone = result[i][26].replaceAll(" ", "");
                recvDeposit = result[i][27].replaceAll(" ", "");
                paidDeposit = result[i][28].replaceAll(" ", "");
                mchtLicnSdate = result[i][31].replaceAll(" ", "");
                artifSdate = result[i][32].replaceAll(" ", "");
                artifEdate = result[i][33].replaceAll(" ", "");
              if(!("".equals(mchtArtifPhone)||mchtArtifPhone==null)){
                  b=false;
              }               
               if(!("".equals(mchtArtifId)||mchtArtifId==null)){
                   b=false;
               }
               if(!(("".equals(mchtArtifType)||mchtArtifType==null))){
                   b=false;
               }
               if(!("".equals(mchtArtifName)||mchtArtifName==null)){
                   b=false;
               }
               if(!("".equals(mchtTrcnNo)||mchtTrcnNo==null)){
                   b=false;
               }
               if(!("".equals(mchtRegAddr)||mchtRegAddr==null)){
                   b=false;
               }
               if(!("".equals(organizationCode)||organizationCode==null)){
                   b=false;
               }
                if(!("".equals(currencyType)||currencyType==null)){
                   b=false;
               }
               if(!("".equals(mchtRegAmt)||mchtRegAmt==null)){
                   b=false;
               }
               if(!("".equals(mchtMngScope)||mchtMngScope==null)){
                   b=false;
               }
               if(!("".equals(mchtLicnExpDate)||mchtLicnExpDate==null)){
                   b=false;
               }
               if(!("".equals(mchtLicnType)||mchtLicnType==null)){
                   b=false;
               }
               if(!("".equals(mchtLicnNo)||mchtLicnNo==null)){
                   b=false;
               }
               if(!("".equals(paidDeposit)||paidDeposit==null)){
                   b=false;
               }
                if(!("".equals(recvDeposit)||recvDeposit==null)){
                    b=false;
                }
                if(!("".equals(mchtLicnSdate)||mchtLicnSdate==null)){
                    b=false;
                }
                if(!("".equals(artifSdate)||artifSdate==null)){
                    b=false;
                }
                if(!("".equals(artifEdate)||artifEdate==null)){
                    b=false;
                }
                if(!b){
                    SnowExceptionUtil.throwErrorException("第"+(i+1)+"行商户种类为政府机构或个体商户，不应该有资质信息。请确认数据正确");
                    }
            }
            mchtVo.setCrtTlr(sessionUserInfo.getTlrno());
            mchtVo.setCrtDateTime(currentDateTime);
            mchtVo.setLastUpdTlr(sessionUserInfo.getTlrno());
            mchtVo.setLastUpdDateTime(currentDateTime);
            /**
             * 存入
             * */
           String dateString=BaseUtil.getCurrentDate();
            mchtVo.setRegDate(dateString);
           lstmchtVoList.add(mchtVo);
        }
        MchtMngService.getInstance().batchInsert(lstmchtVoList);
        /** step no 3 : 记录信息 */
        /** step no 4 : 记录日志 */
        sessionUserInfo.addBizLog("update.log",
                new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
                         " 批量导入商户信息：共"+rowCount+"行" });
	}
	public String mchtPic(PbsMchtBaseInfo mchtVo,PbsMchtAssistInfoTmp assistInfo) throws SnowException {
        List<Object> pbsMchtPicInfoTmp= MchtMngService.getInstance().queryMchtPic(mchtVo.getMchtId());
        if(pbsMchtPicInfoTmp.size()!=0){
            for(Object pic:pbsMchtPicInfoTmp){
                if("01".equals(mchtVo.getMchtType())){
                    if("1".equals(assistInfo.getAccountType())){
                            if("01,02,07,09,13".indexOf(pic.toString())!=-1){
                                return pic.toString();
                            }                                                           
                    }else{
                        if((mchtVo.getMchtArtifName()).equals(assistInfo.getSetlAcctName())){
                            /** modify by lengjingyu 20180127 企业商户结算到法定代表人对私账户，不再需要委托收款通知书  jira-1947 */
                            if("01,02,07,08,09".indexOf(pic.toString())!=-1){
                            /** modify  end*/
                                return pic.toString();
                            } 
                        }else{
                            if("01,02,07,08,09,18,19,23".indexOf(pic.toString())!=-1){
                                return pic.toString();
                            }  
                        }
                    }
                }
                if("04".equals(mchtVo.getMchtType())){
                    if("2".equals(assistInfo.getAccountType())){
                        if((mchtVo.getMchtArtifName()).equals(assistInfo.getSetlAcctName())){
                            /** modify by lengjingyu 20180127 法人代表手持身份证改为选填  jira-1947 */
                            if("01,02,07,08,09".indexOf(pic.toString())!=-1){
                            /** modify end*/
                                return pic.toString();
                            } 
                        }else{
                            /** modify by lengjingyu 20180130 法人代表手持身份证改为选填 ,按照协议定义，24_收款人手持身份证照，不应该是必填项 jira-1947,jira-2012 */
                            if("01,02,07,08,09,18,19,23".indexOf(pic.toString())!=-1){
                            /** modify end*/
                                return pic.toString();
                            }  
                        }
                    }else{
                        return "0001"; 
                    }
                
                }               
                if("06".equals(mchtVo.getMchtType())){
                    if("2".equals(assistInfo.getAccountType())){
                        if("09,21,26,27,28,29".indexOf(pic.toString())!=-1){
                            return pic.toString();
                        }
                    }else{
                        return "0001"; 
                    }
                }                
            }
        }
        return "0000";  
    }
	
	
	
	/**
     * modify by lengjingyu 20180201 商户号的生成不在以商户基本信息表为准,而是新增了一个商户号累加表,每次获取累加
     * jira-1977
     * @return
     * @throws SnowException
     */
    public String genMchtId()  {
        // 商户编号15位,默认为： 9 + 8位当前日期 + 000001
        // 每天都会从默认值开始计算
        String currentDate = IfspDateTime.getYYYYMMDD();
        String mchtId = "9" + currentDate;
        int i;
        try {
            i = getSequencePerDay(CommonConstants.getProperty("sequenceName"));
        } catch (Exception e) {
            return null;
        }
        String CurrentValue=String.valueOf(i);
        while (CurrentValue.length()!=6) {
            CurrentValue="0"+CurrentValue;           
        }
        mchtId=mchtId+CurrentValue;
        return mchtId;
    }
    
    /**
     * modify by lengjingyu 20180201  商户号的生成不在以商户基本信息表为准,而是新增了一个商户号累加表,每次获取累加
     * jira-1977
     * @return
     * @throws Exception 
     * @throws SnowException
     */
    @Action(propagation = Propagation.REQUIRED)
    public int getSequencePerDay(String name) throws Exception{
        int i=0;
        String currentDate = IfspDateTime.getYYYYMMDD();
        //得到sequence_per_day信息
        SequencePerDay sequencePerDay = MchtMngService.getInstance().getSequencePerDay(name);
        if(sequencePerDay.getCurrentValue()>999999){
            SnowExceptionUtil.throwWarnException("今天商户新增已达上限！！！");
        }
        if(currentDate.equals(sequencePerDay.getLastDate())){
            i=sequencePerDay.getCurrentValue()+1;
        }
        sequencePerDay.setCurrentValue(i);
        sequencePerDay.setLastDate(currentDate);
        MchtMngService.getInstance().updateSequencePerDay(sequencePerDay);
        return i;
    }
	
}