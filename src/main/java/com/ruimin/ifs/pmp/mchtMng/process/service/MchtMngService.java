package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.mng.process.bean.MchtAgent;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaesInfoRealMapping;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.SequencePerDay;
import com.ruimin.ifs.pmp.mchtMng.process.bean.TxnWhiteListVO;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditInfo;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.po.TblBctl;

@Service
@SnowDoc(desc = "商户管理")
public class MchtMngService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtMngService.class);
	}

	/**
	 * 查询【商户信息】
	 * 
	 * @param qmchtId
	 *            商户号【模糊查询】
	 * @param qmchtSimpleName
	 *            商户简称【模糊查询】
	 * @param qmchtType
	 *            商户类型
	 * @param qmchtStat
	 *            商户状态
	 * @param qmchtOrgId
	 *            所属机构
	 * @param qmchtName
	 *            商户全名
	 * @param brCode
	 * @param page
	 * @return
	 * @throws SnowException
	 */
    public PageResult queryMain(String qmchtId, String qmchtSimpleName, String qmchtMngSel, String qmchtType, String qmchtStat,
            String qmchtOrgId, String agentId, String brCode,String auditId, Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMassInfo",
                RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
                        .set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? ""
                        : "%" + qmchtSimpleName + "%")
                        .set("qmchtMngSel", StringUtils.isBlank(qmchtMngSel) ? ""
                                : "%" + qmchtMngSel + "%")
                        .set("qmchtType", StringUtils.isBlank(qmchtType) ? "" : qmchtType)
                        .set("qmchtStat", StringUtils.isBlank(qmchtStat) ? "" : qmchtStat)
                        .set("qmchtOrgId", StringUtils.isBlank(qmchtOrgId) ? "" : qmchtOrgId)
                        .set("agentId", StringUtils.isBlank(agentId) ? "" : agentId)
                        .set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
                        .set("currentBrCode", brCode),
                page);
    }

    public PageResult queryMainInit(String qmchtId, String qmchtSimpleName, String qmchtMngSel, String qmchtType, String qmchtStat,
            String qmchtOrgId, String brCode,String chlMchtNo,String auditId, Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMassInfoInit",
                RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
                        .set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? ""
                        : "%" + qmchtSimpleName + "%")
                        .set("qmchtMngSel", StringUtils.isBlank(qmchtMngSel) ? ""
                                : "%" + qmchtMngSel + "%")
                        .set("qmchtType", StringUtils.isBlank(qmchtType) ? "" : qmchtType)
                        .set("qmchtStat", StringUtils.isBlank(qmchtStat) ? "" : qmchtStat)
                        .set("qmchtOrgId", StringUtils.isBlank(qmchtOrgId) ? "" : qmchtOrgId)
                        .set("qChlMchtNo", StringUtils.isBlank(chlMchtNo) ? "" : "%" + chlMchtNo + "%")
                        .set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
                        .set("currentBrCode", brCode),
                page);
    }
	/**
	 * 查询【商户信息】
	 * 
	 * @param qmchtId
	 *            商户号【模糊查询】
	 * @param qmchtSimpleName
	 *            商户简称【模糊查询】
	 * @param qmchtType
	 *            商户类型
	 * @param qmchtStat
	 *            商户状态
	 * @param qmchtOrgId
	 *            所属机构
	 * @param qmchtName
	 *            商户全名
	 * @param brCode
	 * @param page
	 * @return
	 * @throws SnowException
	 *             add by wuhd 20161020
	 */
	public PageResult queryMain2(String qmchtId, String qmchtSimpleName, String qmchtType, String qmchtStat,
			String qmchtOrgId, String qmchtName, String auditId, String brCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMassInfo2",
				RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
						.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? ""
								: "%" + qmchtSimpleName + "%")
						.set("qmchtType", StringUtils.isBlank(qmchtType) ? "" : qmchtType)
						.set("qmchtStat", StringUtils.isBlank(qmchtStat) ? "" : qmchtStat)
						.set("qmchtOrgId", StringUtils.isBlank(qmchtOrgId) ? "" : qmchtOrgId)
						.set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
						.set("qmchtName", StringUtils.isBlank(qmchtName) ? ""
								: "%" + qmchtName + "%")
						.set("currentBrCode", brCode),
				page);
	}
	
	public PageResult queryMain3(String mchtId,String brCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
//		System.out.println("编号"+subMchtId);
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMassInfo3",
				RqlParam.map().set("mchtId",mchtId)
						.set("currentBrCode", brCode),
				         page);

	}
	
	public PageResult queryMain4(String qmchtId, String qmchtSimpleName, String qmchtType, String qmchtStat,
			String qmchtOrgId, String qmchtName, String auditId, String brCode, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMassInfo4",
				RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
						.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? ""
								: "%" + qmchtSimpleName + "%")
						.set("qmchtType", StringUtils.isBlank(qmchtType) ? "" : qmchtType)
						.set("qmchtStat", StringUtils.isBlank(qmchtStat) ? "" : qmchtStat)
						.set("qmchtOrgId", StringUtils.isBlank(qmchtOrgId) ? "" : qmchtOrgId)
						.set("auditId", StringUtils.isBlank(auditId) ? "" : auditId)
						.set("qmchtName", StringUtils.isBlank(qmchtName) ? ""
								: "%" + qmchtName + "%")
						.set("currentBrCode", brCode),
				page);
	}

	/**
	 * 新增【商户信息】
	 * 
	 * @param mchtVo
	 *            商户信息实体类
	 * @throws SnowException
	 */
	public void addMcht(PbsMchtBaseInfo mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtVo);
	}
	
	/**
	 * 新增【商户图片信息】
	 * 
	 * @param reqBean
	 * @param mchtId
	 *            商户编号
	 * @param crtTlr
	 *            创建操作员
	 * @param crtDateTime
	 *            创建日期时间
	 * @throws SnowException
	 */
	public void addPic(UpdateRequestBean reqBean, String mchtId, String crtTlr, String crtDateTime)
			throws SnowException {
		/** 导入实体类 */

		/** 解析图片编号（图片名）信息 */
		List<String> picList = dealPic(reqBean);

		/** 组装数据 */
		DBDao dao = DBDaos.newInstance();
		String picIdLength;
		PbsMchtPicInfo picVo;
		String mchtPicType;
		for (String picId : picList) {
		    picIdLength=null;
		    picVo= new PbsMchtPicInfo();// 商户图片表-实体类
	        picVo.setMchtId(mchtId);// 商户编号
	        picVo.setCrtTlr(crtTlr);// 创建操作员
	        picVo.setCrtDateTime(crtDateTime);// 创建日期时间
	        mchtPicType=(String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtPicNull",RqlParam.map().set("mchtId", mchtId).set("mchtPicType", picId.substring(0, 2)));
            if (!StringUtils.isBlank(mchtPicType)) {
                continue;
            }
            if(picId.length()>2){
    	        picIdLength=picId.substring(2, picId.length());
            }
			picVo.setMchtPicId(picIdLength);// 图片码【截取图片编号2位后的所有字符】
			picVo.setMchtPicType(picId.substring(0, 2));// 图片类型【截取图片编号前2位】
			picVo.setPicSeqNo(genPicSeq());// 图片序号
			dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.insertPbsMchtPicInfo",picVo);
		}
	}

	/**
	 * 修改【商户信息】
	 * 
	 * @param mchtVo
	 *            商户信息实体类
	 * @throws SnowException
	 */
	public void updMcht(PbsMchtBaseInfo mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(mchtVo);
	}

	/**
	 * 修改【商户图片信息】
	 * 
	 * @param reqBean
	 * @param mchtId
	 *            商户编号
	 * @param lastUpdTlr
	 *            更新操作员
	 * @param lastUpdAteTime
	 *            更新日期时间
	 * @throws SnowException
	 */
	public void updPic(UpdateRequestBean reqBean, String mchtId, String lastUpdTlr, String lastUpdAteTime)
			throws SnowException {
		/** 定义字段 */
		String picId=null;// 图片编号
		String picType;// 图片类型

		/** 解析图片编号（图片名）信息 */
		List<String> picList = dealPic(reqBean);

		/** 组装数据 */
		DBDao dao = DBDaos.newInstance();
		for (String listElement : picList) {
	          if(listElement.length()>2){
	              picId = listElement.substring(2, listElement.length());// 图片码【截取图片编号2位后的所有字符】
	            }
			if (StringUtils.isBlank(picId)) {
				continue;
			}
			
			picType = listElement.substring(0, 2);// 图片类型【截取图片编号前2位】
			dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updMchtPic",
					RqlParam.map().set("mchtId", mchtId).set("picId", picId).set("picType", picType)
							.set("lastUpdTlr", lastUpdTlr).set("lastUpdAteTime", lastUpdAteTime));
		}
	}
	
	
	   public void  queryMchtPic(UpdateRequestBean reqBean, String mchtId, String lastUpdTlr, String lastUpdAteTime)
	            throws SnowException {
	        /** 定义字段 */
// 图片编号
	        String picType;// 图片类型

	        /** 解析图片编号（图片名）信息 */
	        List<String> picList = dealPic(reqBean);

	        /** 组装数据 */
	        DBDao dao = DBDaos.newInstance();
	         String picId;
	        for (String listElement : picList) {
	            picId=null;
	            if(listElement.length()>2){
	                  picId = listElement.substring(2, listElement.length());// 图片码【截取图片编号2位后的所有字符】
	            }
	            if (StringUtils.isBlank(picId)) {
	                continue;
	            }
	            picType = listElement.substring(0, 2);// 图片类型【截取图片编号前2位】
	            
                dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updMchtPic",
                        RqlParam.map().set("mchtId", mchtId).set("picId", picId).set("picType", picType)
                        .set("lastUpdTlr", lastUpdTlr).set("lastUpdAteTime", lastUpdAteTime));	                

	        }

	    }
	   
	   
	   /**
     * 删除【商户图片信息】
     * 
     * @param mchtId
     *            商户编号
     */
    public void delPicOne(String mchtId) {
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.delPicOne", mchtId);
    }

	/**
	 * 删除【商户图片信息】
	 * 
	 * @param mchtId
	 *            商户编号
	 */
	public void delPic(String mchtId,String picId) {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.delMchtPic", RqlParam.map().set("mchtId", mchtId).set("picType", picId));
	}

	/**
	 * 查询指定商户编号下，图片信息是否存在【商户图片信息】
	 * 
	 * @param mchtId
	 *            商户编号
	 * @return true/false
	 */
	public boolean queryPicExist(String mchtId) {
		DBDao dao = DBDaos.newInstance();
		if (dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryPicExist", mchtId).equals("40")) {
			return true;
		} else {
			return false;
		}
	}

	public String mchtPicName(String picId){
	    switch (picId) {
            case "00":
                return "商户logo";
            case "01":
                return "营业执照";
            case "02":
                return "法人证件正面";
            case "03":
                return "组织机构";
            case "04":
                return "ICP许可";     
            case "05":
                return "税务许可"; 
            case "06":
                return "商户门店"; 
            case "07":
                return "法人证件反面"; 
            case "08":
                return "银行卡正面照(收款人银行卡照片)"; 
            case "09":
                return "店铺门头照(含有招牌)"; 
            case "10":
                return "店铺全景照(店内环境)"; 
            case "11":
                return "收银台照"; 
            case "12":
                return "商品照"; 
            case "13":
                return "开户许可证"; 
            case "14":
                return "法人手持身份证"; 
            case "18":
                return "收款人证件正面"; 
            case "19":
                return "收款人证件反面"; 
            case "21":
                return "租赁合同(或产权证明书)"; 
            case "22":
                return "合同照片"; 
            case "23":
                return "授权证明(委托收款通知书)"; 
            case "24":
                return "收款人手持身份证照"; 
            case "26":
                return "租赁合同承租人同名身份证正面照"; 
            case "27":
                return "租赁合同承租人同名身份证反面照"; 
            case "28":
                return "租赁合同承租人同名手持身份证照"; 
            case "29":
                return "租赁合同承租人同名银行卡照片"; 
        }
	    return picId;
	}
	/**
	 * 解析图片编号（图片名）信息
	 * 
	 * @param reqBean
	 * @return
	 */
	public List<String> dealPic(UpdateRequestBean reqBean) {
		List<String> picList = new ArrayList<String>();
		picList.add(reqBean.getTotalList().get(0).get("picId0"));// 图片名【商户logo】
		picList.add(reqBean.getTotalList().get(0).get("picId1"));// 图片名【营业执照】
		picList.add(reqBean.getTotalList().get(0).get("picId2"));// 图片名【法人证件正面】
		picList.add(reqBean.getTotalList().get(0).get("picId3"));// 图片名【组织机构】
		picList.add(reqBean.getTotalList().get(0).get("picId4"));// 图片名【ICP许可】
		picList.add(reqBean.getTotalList().get(0).get("picId5"));// 图片名【税务许可】
		picList.add(reqBean.getTotalList().get(0).get("picId6"));// 图片名【商户门店】
		picList.add(reqBean.getTotalList().get(0).get("picId7"));// 图片名【法人证件反面】
		picList.add(reqBean.getTotalList().get(0).get("picId8"));// 图片名【银行卡正面照(收款人银行卡照片)】
		picList.add(reqBean.getTotalList().get(0).get("picId9"));// 图片名【店铺门头照(含有招牌)】
		picList.add(reqBean.getTotalList().get(0).get("picId10"));// 图片名【店铺全景照(店内环境)】
		picList.add(reqBean.getTotalList().get(0).get("picId11"));// 图片名【收银台照】
		picList.add(reqBean.getTotalList().get(0).get("picId12"));// 图片名【商品照】
		picList.add(reqBean.getTotalList().get(0).get("picId13"));// 图片名【开户许可证】
		picList.add(reqBean.getTotalList().get(0).get("picId14"));// 图片名【法人手持身份证】
		//picList.add(reqBean.getTotalList().get(0).get("picId15"));// 图片名【法定代表人证件正面】
		//picList.add(reqBean.getTotalList().get(0).get("picId16"));// 图片名【法定代表人证件反面】
		//picList.add(reqBean.getTotalList().get(0).get("picId17"));// 图片名【法定代表人手持身份证照】
		picList.add(reqBean.getTotalList().get(0).get("picId18"));// 图片名【收款人证件正面】
		picList.add(reqBean.getTotalList().get(0).get("picId19"));// 图片名【收款人证件反面】
		//picList.add(reqBean.getTotalList().get(0).get("picId20"));// 图片名【商户合影照】
		picList.add(reqBean.getTotalList().get(0).get("picId21"));// 图片名【租赁合同(或产权证明书)】
		picList.add(reqBean.getTotalList().get(0).get("picId22"));// 图片名【合同照片】
		picList.add(reqBean.getTotalList().get(0).get("picId23"));// 图片名【授权证明(委托收款通知书)】
		picList.add(reqBean.getTotalList().get(0).get("picId24"));// 图片名【收款人手持身份证照】
		//picList.add(reqBean.getTotalList().get(0).get("picId25"));// 图片名【经营产品照片】
		picList.add(reqBean.getTotalList().get(0).get("picId26"));// 图片名【租赁合同承租人同名身份证正面照】
		picList.add(reqBean.getTotalList().get(0).get("picId27"));// 图片名【租赁合同承租人同名身份证反面照】
		picList.add(reqBean.getTotalList().get(0).get("picId28"));// 图片名【租赁合同承租人同名手持身份证照】
		picList.add(reqBean.getTotalList().get(0).get("picId29"));// 图片名【租赁合同承租人同名银行卡照片】
		picList.add(reqBean.getTotalList().get(0).get("picId30"));// 图片名【补充资料1】
		picList.add(reqBean.getTotalList().get(0).get("picId31"));// 图片名【补充资料2】
		picList.add(reqBean.getTotalList().get(0).get("picId32"));// 图片名【补充资料3】
		picList.add(reqBean.getTotalList().get(0).get("picId33"));// 图片名【补充资料4】
		picList.add(reqBean.getTotalList().get(0).get("picId34"));// 图片名【补充资料5】
		picList.add(reqBean.getTotalList().get(0).get("picId35"));// 图片名【补充资料6】
		picList.add(reqBean.getTotalList().get(0).get("picId36"));// 图片名【补充资料7】
		picList.add(reqBean.getTotalList().get(0).get("picId37"));// 图片名【补充资料8】
		picList.add(reqBean.getTotalList().get(0).get("picId38"));// 图片名【补充资料9】
		picList.add(reqBean.getTotalList().get(0).get("picId39"));// 图片名【补充资料10】
		return picList;
	}

	/**
	 * 生成商户编号[如果当日没有记录，采用默认编号；反之找出最大编号加一]
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String genMchtId() throws SnowException {
		// 商户编号15位,默认为： 9 + 8位当前日期 + 000001
		// 每天都会从默认值开始计算
		String currentDate = BaseUtil.getCurrentDate();
		String mchtId = MchtMngConstants.GEN_MCHTID_FRONT + currentDate + MchtMngConstants.GEN_MCHTID_BEHIND;
		String maxId = queryMaxMchtId(currentDate);
		if (!StringUtils.isBlank(maxId)) {
			mchtId = String.valueOf(Long.parseLong(maxId) + 1);
		}
		return mchtId;
	}

	/**
	 * 获取商户编号最大值
	 * 
	 * @return 商户编号[最大值]
	 */
	private String queryMaxMchtId(String currentDate) {
		DBDao dao = DBDaos.newInstance();
		currentDate = "%" + currentDate + "%";
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMaxMchtId", 
		        RqlParam.map().set("currentDate", currentDate));
	}

	   /**
     * 获取商户编号最大值
     * 
     * @return 商户编号[最大值]
     */
    public String queryMchtLicnNo(String mchtLicnNo,String mchtId) {
        DBDao dao = DBDaos.newInstance();
        return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtLicnNo", 
                RqlParam.map().set("mchtLicnNo", StringUtils.isBlank(mchtLicnNo) ? "" : mchtLicnNo).set("mchtId", StringUtils.isBlank(mchtId) ? "" : mchtId));
    }
    
	/**
	 * 生成图片序号[如果没有记录，采用默认编号；反之找出最大编号加一]
	 * 
	 * @return
	 * @throws SnowException
	 */
	private String genPicSeq() throws SnowException {
		// 图片序号6位,默认为： 100000
		String mchtId = MchtMngConstants.DEFAULT_MCHT_PIC_ID;
		String maxId = queryMaxPicSeq();
		if (!StringUtils.isBlank(maxId)) {
			mchtId = String.valueOf(Long.parseLong(maxId) + 1);
		}
		return mchtId;
	}

	/**
	 * 获取图片序号最大值
	 * 
	 * @return 图片序号[最大值]
	 */
	private String queryMaxPicSeq() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMaxPicSeq");
	}

	/**
	 * 冻结/解冻【商户信息】
	 * 
	 * @param trlno
	 *            更新操作员
	 * @param lastUpdDateTime
	 *            更新日期时间
	 * @param mchtStat
	 *            商户状态
	 * @param mchtId
	 *            商户编号
	 * @throws SnowException
	 */
	public void frzMcht(String auditId, String syncState, String trlno, String lastUpdDateTime, PbsMchtBaseInfo mchtVo,
			String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (!StringUtils.isBlank(mchtVo.getMchtStat())) {
			dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.frzMchtOrNot",
					RqlParam.map().set("auditId", auditId).set("syncState", syncState).set("trlno", trlno)
							.set("lastUpdDateTime", lastUpdDateTime).set("mchtStat", mchtVo.getMchtStat())
							.set("mchtId", mchtId));
		} else {
			SnowExceptionUtil.throwErrorException("商户状态错误,操作失败!");
		}
	}

	/**
	 * 注销【商户信息】
	 * 
	 * @param trlno
	 *            更新操作员
	 * @param lastUpdDateTime
	 *            更新日期时间
	 * @param mchtStat
	 *            商户状态
	 * @param mchtId
	 *            商户编号
	 * @throws SnowException
	 */
	public void offMcht(String auditId, String syncState, String trlno, String lastUpdDateTime, String mchtStat,
			String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (!StringUtils.isBlank(mchtStat)) {
			dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.offMcht",
					RqlParam.map().set("auditId", auditId).set("syncState", syncState).set("trlno", trlno)
							.set("mchtStat", mchtStat).set("lastUpdDateTime", lastUpdDateTime).set("mchtId", mchtId));
		} else {
			SnowExceptionUtil.throwErrorException("商户状态错误,操作失败!");
		}
	}

	/**
	 * 过滤资质信息【当商户类型限制不输入资质信息时】
	 * 
	 * @param mchtVo
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtBaseInfo removeQlf(PbsMchtBaseInfo mchtVo) throws SnowException {
		mchtVo.setMchtLicnNo("");
		mchtVo.setMchtLicnType("");
		mchtVo.setMchtLicnExpDate("");
		mchtVo.setMchtMngScope("");
		mchtVo.setMchtRegAmt("");
		mchtVo.setMchtTrcnNo("");
		mchtVo.setMchtArtifName("");
		mchtVo.setMchtArtifPhone("");
		mchtVo.setMchtArtifType("");
		mchtVo.setMchtArtifId("");
		mchtVo.setRecvDeposit("");
		mchtVo.setPaidDeposit("");
		return mchtVo;
	}

	/**
	 * 新增审核流水(公共方法，根据商户状态，对应审核信息表不同的审核业务类型)
	 * 
	 * @param
	 * @throws SnowException
	 */
	public void addAuditInfo(PbsMchtBaseInfo mchtVo, SessionUserInfo sessionUserInfo) throws SnowException {
		// 根据操作员机构编号，找到对应的机构级别，根据机构级别找到审核流程编号
		String brno = sessionUserInfo.getBrno();
		String auditProcId = selectAuditIdByBrno(brno);
		if (auditProcId == null) {
			SnowExceptionUtil.throwWarnException("未找到审核流程！");
		}
		// 审核信息实体对象
		PmpAuditInfo PmpAuditInfo = new PmpAuditInfo();
		PmpAuditInfo.setAuditId(mchtVo.getAuditId());// 审核信息编号
		// 获取商户状态，根据商户状态设置不同的审核流水业务类型
		String MchtStat = mchtVo.getMchtStat();
		// 新增待审核
		if (MchtMngConstants.MCHT_STAT_03.equals(MchtStat)) {
			PmpAuditInfo.setAuditType(MchtMngConstants.AUDIT_TYPE_00);// 审核业务类型,00-商户信息登记；
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")信息新增");// 审核信息描述
		}
		// 修改待审核
		if (MchtMngConstants.MCHT_STAT_04.equals(MchtStat)) {
			PmpAuditInfo.setAuditType(MchtMngConstants.AUDIT_TYPE_01);// 审核业务类型,00-商户信息变更；
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")信息修改");// 审核信息描述
		}
		// 冻结待审核
		if (MchtMngConstants.MCHT_STAT_05.equals(MchtStat)) {
			PmpAuditInfo.setAuditType(MchtMngConstants.AUDIT_TYPE_02);// 审核业务类型,00-商户信息冻结；
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")信息冻结");// 审核信息描述
		}
		// 恢复待审核
		if (MchtMngConstants.MCHT_STAT_06.equals(MchtStat)) {
			PmpAuditInfo.setAuditType(MchtMngConstants.AUDIT_TYPE_03);// 审核业务类型,00-商户信息解冻；
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")信息恢复");// 审核信息描述
		}
		// 注销待审核
		if (MchtMngConstants.MCHT_STAT_07.equals(MchtStat)) {
			PmpAuditInfo.setAuditType(MchtMngConstants.AUDIT_TYPE_04);// 审核业务类型,00-商户信息注销；
			PmpAuditInfo.setAuditDesc("商户(" + mchtVo.getMchtId() + ")信息注销");// 审核信息描述
		}
		PmpAuditInfo.setAuditProcId(auditProcId);// 审核流程编号
		PmpAuditInfo.setApplyDateTime(BaseUtil.getCurrentDateTime());// 申请日期时间
																		// 14位
		PmpAuditInfo.setApplyTlrNo(sessionUserInfo.getTlrno());// 申请柜员编号
		PmpAuditInfo.setApplyBrNo(brno);// 申请机构编号
		PmpAuditInfo.setAuditUrl("mchtInfoAudit.jsp");// 审核信息路径
		PmpAuditInfo.setAuditState(MchtMngConstants.AUDIT_STATE_00);// 审核状体00-未审核；01-审核通过；02-审核拒绝；
		PmpAuditInfo.setCrtDateTime(BaseUtil.getCurrentDateTime());// 创建日期时间 14位
		PmpAuditInfo.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 最后更新日期时间
																		// 14位

		DBDao dao = DBDaos.newInstance();
		dao.insert(PmpAuditInfo);
	}

	/**
	 * 根据机构编号，查询审核流程编号
	 * 
	 * @param
	 * @throws SnowException
	 */
	public String selectAuditIdByBrno(String brno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.selectAuditIdByBrno",
				RqlParam.map().set("brno", brno));
	}

	/**
	 * 查询符合条件的审核步骤
	 * 
	 * @param tlrno
	 * @return
	 * @throws SnowException
	 */
	public List<PmpAuditProcStep> selectTepList(String tlrno, String auditType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> objList = dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.selectTepList",
				RqlParam.map().set("tlrno", tlrno).set("auditType", auditType));
		List<PmpAuditProcStep> beanList = new ArrayList<>();
		for (Object procStep : objList) {
			beanList.add((PmpAuditProcStep) procStep);
		}
		return beanList;
	}

	/**
	 * 插入到审核步骤表中(插入到审核信息记录表-----Lym)
	 * 
	 * @param list
	 * @throws SnowException
	 */
	public void addStepInfo(List<PmpAuditProcStep> list, String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 临时表实体内容，对应到正式表实体对象中，最近操作时间，操作人
		for (PmpAuditProcStep pmpAuditProcStep : list) {
			// 获取记录表对象
			PmpAuditStepInfo pmpAuditStepInfo = new PmpAuditStepInfo();
			pmpAuditStepInfo.setSeqId(ContextUtil.getUUID());
			pmpAuditStepInfo.setAuditId(auditId);
			pmpAuditStepInfo.setStepNo(pmpAuditProcStep.getStepNo());
			pmpAuditStepInfo.setAuditState(MchtMngConstants.AUDIT_STATE_00);
			pmpAuditStepInfo.setRoleId(pmpAuditProcStep.getAuditRoleId());
			dao.insert(pmpAuditStepInfo);
		}
	}

	/*
	 * 商户名称和商户ID的关联查询
	 */
	public PageResult queryMainMcht(String qmchtId, String qmchtSimpleName, String qmchtType, String brcode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult age= dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtName",
				RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
						.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? ""
								: "%" + qmchtSimpleName + "%")
						.set("qmchtType", StringUtils.isBlank(qmchtType) ? "" : qmchtType).set("currentBrCode", brcode),
				page);
		return	age;
				
	}
	
	   /*
     * 商户名称图片信息
     */
    public List<Object> queryMchtPic(String mchtId)
            throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return  dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtAreaNoPic", mchtId);
                
    }
	//已经添加合同的商户不显示
	public PageResult queryMainMcht1(String qmchtId, String qmchtSimpleName, String brcode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult age= dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtName1",
				RqlParam.map().set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%")
						.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%" + qmchtSimpleName + "%")
						.set("currentBrCode", brcode),
				page);
		  return  age;
	}
    
	/**
	 * 查询【地区码】
	 * 
	 * @param qmchtId
	 *            商户编号
	 * @return
	 * @throws SnowException
	 */
	public  PbsMchtBaseInfoReal queryMchtAreaNo(String qmchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
//		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtAreaNo", qmchtId);
		//根据商户号查询商户全名和商户地区码
		return (PbsMchtBaseInfoReal) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtAreaNo", qmchtId);
	}

	public String queryTel(String tlrno) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return  (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryTel", tlrno);
	}
	
	//----2017,03,17新增--商户门户先关-----
	/**
	 * 根据商户号查询商户基本信息表中的手机号
	 * @param mchtPhone
	 * @return
	 * @throws SnowException
	 */
	public String getoldPhoneByMchtId(String mchtId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.getoldPhoneByMchtId", mchtId);
	}

	//----2017--3-20新增
	public  PbsMchtBaesInfoRealMapping queryMchtAreaNoMapp(String qmchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		//根据商户号查询商户全名和商户地区码
		return (PbsMchtBaesInfoRealMapping) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryMchtAreaNoMapp", qmchtId);
	}

	public PbsMchtBaseInfo queryByMchtId(String mchtId) {
		DBDao dao = DBDaos.newInstance();
    	return (PbsMchtBaseInfo)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.queryByMchtId", mchtId);
	}
	/**
	 * 判断角色是否客户经理
	 * @param roleId
	 * @return
	 */
	public boolean isAmr(String roleId) {
		//获取全部角色为客户经理
	    String roleIdAmr = SysParamUtil.getParam(MchtMngConstants.ROLE_ID_AMR);
		String [] roleIdArr= roleIdAmr.split(",");
		boolean flag = false;
		for (int i = 0; i < roleIdArr.length; i++) {
			String roleIdNo = roleIdArr[i];
			if(roleId.equals(roleIdNo)){//为客户经理
				flag = true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 该方法查询所有的商户编号，返回商户编号列表
	 * */
	public List<String> getAllMchtId(){
		DBDao dao = DBDaos.newInstance();
		return (List) dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.getAllMchtId");
	}
	/**
     * 批量插入商户信息
     * */
	public void batchInsert(List<PbsMchtBaseInfo> mchtVo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.insert(mchtVo);
    }
	   /**
     * 批量导入商户时验证代理商
     * */
    public String getAgentId(String agentId) throws SnowException {
        DBDao dao = DBDaos.newInstance();        
        return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.getAgentId",agentId);
        }
        /**
      * 批量导入商户时验证所属机构
      * */
     public List<Object> getMchtTlrno(String Tlrno) throws SnowException {
         DBDao dao = DBDaos.newInstance();        
         return  dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.getMchtTlrno",Tlrno);     
         }

        /**
         *更新商户风险等级
         * @param mchtId
         * @param riskLevel
         */
        public void updMchtField(String mchtId, String riskLevel) {
            DBDao dao = DBDaos.newInstance(); 
            dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updMchtRiskLevel",
                    RqlParam.map().set("mchtId", mchtId).set("riskLevel", riskLevel));
            
        }

        /**
         * modify by lengjingyu 20180131 生成商户编号:商户号的生成不在以商户基本信息表为准,而是新增了一个商户号累加表,每次获取累加 jira-1977
         * 根据name查询完整信息
         * @param name
         * @return
         */
        public SequencePerDay getSequencePerDay(String name) {
            DBDao dao = DBDaos.newInstance(); 
            return (SequencePerDay)dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.selectSequencePerDay", name);
        }

        /**
         * modify by lengjingyu 20180131 生成商户编号:商户号的生成不在以商户基本信息表为准,而是新增了一个商户号累加表,每次获取累加 jira-1977
         * 更新SequencePerDay信息
         * @param sequencePerDay
         * @throws SnowException 
         */
        public void updateSequencePerDay(SequencePerDay sequencePerDay) throws SnowException {
            DBDao dao = DBDaos.newInstance(); 
            dao.update(sequencePerDay);
        }
     
     
     

}
