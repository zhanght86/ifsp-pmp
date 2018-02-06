package com.ruimin.ifs.pmp.mchtMng.process.service;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;


import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mchtMng.comp.QrcMngAction;
import com.ruimin.ifs.pmp.mchtMng.process.bean.QrcBaseInfo;
import com.ruimin.ifs.util.StringUtil;

@Service
@SnowDoc(desc = "商户二维码")
public class QrcMngService extends SnowService {
	
	Logger log = SnowLog.getLogger(QrcMngAction.class);
	
	/**
	 * 获取实体类
	 * @return
	 * @throws SnowException
	 */
	public static QrcMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(QrcMngService.class);
	}

	
	/**
	 * 解析二维码接口响应报文，并将解析的数据存入商户二维码基本信息实体类中，然互将商户二维码基本信息存入数据库
	 * @param baseInfo
	 * @param map
	 * @throws Exception
	 */
	public void updBaseInfo(QrcBaseInfo baseInfo,Map map) throws Exception{
		DBDao dao = DBDaos.newInstance();
		List list = (List) map.get("txnTokenCodeList");
	       for (int i = 0; i < list.size(); i++) {
	           Map listMap = (Map) list.get(i);
	           String qrCode = (String) listMap.get("txnTokenCode");
	           String respTxnSsn = (String) listMap.get("respTxnSsn");
	           String respTxnTime = (String) listMap.get("respTxnTime");
	           String qrcTimeEnd = (String) listMap.get("qrcTmEd");
	           //log.info("支付前置地址+二维码信息："+SysParamUtil.getParam(MchtChnlRequestConstants.PAY_PER_URL)+qrCode);
	           baseInfo.setQrCode(qrCode);
	           baseInfo.setRespTxnSsn(respTxnSsn);
	           baseInfo.setRespTxnTime(respTxnTime);
	           baseInfo.setExpiryDateTime(qrcTimeEnd);
//	           dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.addBaseInfo",
//	        		   baseInfo);
	           dao.insert(baseInfo);
	        }
	}
	
	/**
	 * 生成二维码编号
	 * @return
	 * @throws Exception
	 */
	public String getCodeId() throws Exception{
		StringBuffer codeId = new StringBuffer();
		DBDao dao = DBDaos.newInstance();
		String currentMaxId = (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getMaxId");
		if (StringUtil.isEmpty(currentMaxId)) {
			currentMaxId = "00000";
		}
		int nextId = Integer.parseInt(currentMaxId)+1;
		codeId.append(StringUtil.leftPad(String.valueOf(nextId),5,"0"));
		return codeId.toString();
	}


	/**
	 * 主页面查询二维码基本信息
	 * @param mchtId
	 * @param mchtSimpleName
	 * @param mchtAmrName
	 * @param qrcCodeId
	 * @param mchtUseStat 
	 * @param qrcStat 
	 * @param qrcType
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryList(String mchtId, String mchtSimpleName,String mchtAmrNo,  String mchtAmrName, String qrcCodeId,
			String qrcStat, String mchtUseStat,String currentBrCode,String qrcType,Page page)
			throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.queryList",
				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%" + mchtId + "%")
						.set("mchtSimpleName", StringUtils.isBlank(mchtSimpleName) ? "" : "%" + mchtSimpleName + "%")
						.set("mchtAmrNo", StringUtils.isBlank(mchtAmrNo) ? "" : "%" + mchtAmrNo + "%")
						.set("mchtAmrName", StringUtils.isBlank(mchtAmrName) ? "" : "%" + mchtAmrName + "%")
						.set("qrcCodeId", StringUtils.isBlank(qrcCodeId) ? "" : "%" + qrcCodeId + "%")
						.set("qrcStat", StringUtils.isBlank(qrcStat) ? "" :  qrcStat )
						.set("mchtUseStat", StringUtils.isBlank(mchtUseStat) ? "" :  mchtUseStat )
						.set("currentBrCode", currentBrCode).set("qrcType", qrcType == null ? "": qrcType),
				page);
	}
	
	
	/**
	 * 根据商户号将商户二维码基本信息表中的二维码状态改为无效
	 * @param mchtId
	 * @throws SnowException
	 */
	public void modifyMchtQrcStateByMchtId(String mchtId,String qrcType) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.modifyMchtQrcStateByMchtId",
				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : mchtId).set("qrcType", qrcType) );
	}
	
	   /**
     * 根据商户号将商户二维码基本信息表中的二维码状态改为无效
     * @param mchtId
     * @throws SnowException
     */
    public void modifyMchtId(String mchtId) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.modifyMchtId",
                RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" : mchtId));
    }
	/**
	 * 根据商户合同编号查询合同关联产品正式表中该商户签约的支付产品中的所有不重复的交易类型
	 * @param codId
	 * @return
	 * @throws SnowException
	 */
	public String getTxnTypeCodeByConId(String conId)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String txnTypeCode = (String)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getTxnTypeCodeByConId",
				RqlParam.map().set("conId", StringUtils.isBlank(conId) ? "" :  conId ));
		if (StringUtil.isEmpty(txnTypeCode)) {
			txnTypeCode = "";
		}
		return txnTypeCode;
	}
	
	/**
	 * 根据商户号，查询该商户合同正式表中的商户合同编号
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public String getCodIdByMchtId(String mchtId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return  (String)dao.
				selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getCodIdByMchtId",
						RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" :  mchtId ));
	}

	/**
	 * 根据商户合同编号查询临时表中该商户即将签约的支付产品中的所有不重复的交易类型
	 * @param codId
	 * @return
	 * @throws SnowException
	 */
	public String getTxnTypeCodeByConIdFromTmp(String conId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String txnTypeCode = (String)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getTxnTypeCodeByConIdFromTmp",
				RqlParam.map().set("conId", StringUtils.isBlank(conId) ? "" :  conId ));
		if (StringUtil.isEmpty(txnTypeCode)) {
			txnTypeCode = "";
		}
		return txnTypeCode;
	}
	
	/**
	 * 根据商户号，查询该商户合同信息临时表中的商户合同编号
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public String getCodIdByMchtIdFromTmp(String mchtId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return  (String)dao.
				selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getCodIdByMchtIdFromTmp",
						RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" :  mchtId ));
	}
	/**
	 * 根据商户查询商户的简称
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public String getMchtSimpleNameByMchtId(String mchtId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return  (String)dao.
				selectOne("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.getMchtSimpleNameByMchtId",
						RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" :  mchtId ));
	}
	
//	/**
//	 * 根据商户号查询商户二维码基本信息表
//	 * @param mchtId
//	 * @return
//	 * @throws SnowException
//	 */
//	public List<Object> selectMchtQrcBaseInfo(String mchtId) throws SnowException{
//		DBDao dao = DBDaos.newInstance();
//		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.selectMchtQrcBaseInfo",
//				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" :  mchtId ));
//	}
	
	
	/**
	 * 根据商户号查询商户二维码基本信息表
	 * @param mchtId
	 * @param qrcType
	 * @return
	 * @throws SnowException
	 */
	public List<Object> selectMchtQrcBaseInfo(String mchtId,String qrcType) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.selectMchtQrcBaseInfo",
				RqlParam.map().set("mchtId", StringUtils.isBlank(mchtId) ? "" :  mchtId ).set("qrcType", qrcType));
	}

	/**
	 * 启用停用商户二维码信息
	 * @param baseInfo
	 * @throws SnowException
	 */
	public void statusChange(QrcBaseInfo baseInfo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.qrcMng.statusChange",baseInfo );
		
	}

}
