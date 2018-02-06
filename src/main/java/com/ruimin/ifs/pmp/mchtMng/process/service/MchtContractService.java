package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtContractConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractAcctRate;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractPicVO;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractProduct;
import com.ruimin.ifs.pmp.mchtMng.process.bean.MchtContractVO;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditProcStep;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpAuditStepInfo;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@Service
@SnowDoc(desc = "商户合同管理")
public class MchtContractService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtContractService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtContractService.class);
	}

	/**
	 * 商户合同的初始化查询
	 * 
	 * @param string
	 */
	public PageResult queryMain(String qmchtId, String qmchtSimpleName, String qconId, String qpaperConId,
			String qsetlAcctNo, String qconState, String auditId, String currentBrCode, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String sysdate1 = dateFormat.format(cal.getTime()); // 取当前的系统时间
		cal.add(Calendar.MONTH, 1);
		String sysdate2 = dateFormat.format(cal.getTime()); // 取当前的系统时间的后一个月的时间
		// 封装查询参数
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("sysdate1", sysdate1);
		paramMap.put("sysdate2", sysdate2);
		paramMap.put("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%");
		paramMap.put("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%" + qmchtSimpleName + "%");
		paramMap.put("qconId", StringUtils.isBlank(qconId) ? "" : "%" + qconId + "%");
		paramMap.put("qpaperConId", StringUtils.isBlank(qpaperConId) ? "" : "%" + qpaperConId + "%");
		paramMap.put("qsetlAcctNo", StringUtils.isBlank(qsetlAcctNo) ? "" : "%" + qsetlAcctNo + "%");
		paramMap.put("qconState", StringUtils.isBlank(qconState) ? "" : "%" + qconState + "%");
		paramMap.put("auditId", StringUtils.isBlank(auditId) ? "" : auditId);
		paramMap.put("currentBrCode", currentBrCode);
		if (MchtContractConstants.MCHT_CONTR_STAT_DEADED.equals(qconState)) { // 查询合同已经到期的合同记录
			return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMassInfoed", paramMap, page);
		} else if (MchtContractConstants.MCHT_CONTR_STAT_ON.equals(qconState)
				|| MchtContractConstants.MCHT_CONTR_STAT_NORMAL.equals(qconState)) {// 查询合同签订中的合同记录
			return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMassInfo", paramMap, page);
		} else if (MchtContractConstants.MCHT_CONTR_STAT_DEADING.equals(qconState)) {// 查询合同即将到期的合同记录
			return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMassInfoing", paramMap, page);
		} else {// 合同状态为null 时查询的查询记录
			return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMassInfoAll", paramMap, page);

		}
	}

	/*
	 * 交易类型的查询
	 */
	public PageResult queryAcctType(String prodId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryAcctType",
				RqlParam.map().set("prodId", StringUtils.isBlank(prodId) ? "" : prodId.trim()), page);
	}

	/**
	 * 
	 * 功能描述: 根据合同conId查找对应合同的合同状态
	 * 
	 * @param conState
	 * @return String
	 * @throws SnowException
	 */
	public String selectConStateByConId(String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.selectConStateByConId",
				RqlParam.map().set("conId", conId));

	}

	/*
	 * 服务页面动态tab加载初始化
	 */
	public PageResult queryAllRecord(String prodId, String conId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryAllRecord",
				RqlParam.map().set("prodId", StringUtils.isBlank(prodId) ? "" : prodId.trim()).set("conId",
						StringUtils.isBlank(conId) ? "" : conId.trim()),
				page);
	}

   public PageResult queryPagyCategroyCfgl(String qLevelTwoCode,Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryPagyCategroyCfgl",
                RqlParam.map().set("qLevelTwoCode", StringUtils.isBlank(qLevelTwoCode) ? "" : qLevelTwoCode.trim()),page);
    }
	   

   public PageResult queryPagyCategroyCfgl2(String qLevelThreeCode,Page page) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryPagyCategroyCfgl2",
                RqlParam.map().set("qLevelThreeCode", StringUtils.isBlank(qLevelThreeCode) ? "" : qLevelThreeCode.trim()),page);
    }
	public MchtContractVO queryMchtFlag(String mchtflag) {
		DBDao dao = DBDaos.newInstance();
		return (MchtContractVO) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMchtFlag",
				RqlParam.map().set("mchtflag", StringUtils.isBlank(mchtflag) ? "" : mchtflag.trim()));

	}

	public MchtContractProduct queryConfirm(String prodId, String conId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (MchtContractProduct) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryConfirm",
				RqlParam.map().set("prodId", StringUtils.isBlank(prodId) ? "" : prodId.trim()).set("conId",
						StringUtils.isBlank(conId) ? "" : conId.trim()));
	}

	public PageResult queryProdAll(String conId, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryProdAll",
				RqlParam.map().set("conId", StringUtils.isBlank(conId) ? "" : conId), page);
	}

	/**
	 * 根据中止的状态去查找一条记录
	 */
	public MchtContractVO selectOneByConId(String conId) {
		DBDao dao = DBDaos.newInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String sysdate1 = dateFormat.format(cal.getTime()); // 取当前的系统时间
		cal.add(Calendar.MONTH, 1);
		String sysdate2 = dateFormat.format(cal.getTime()); // 取当前的系统时间的后一个月的时间

		return (MchtContractVO) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryOne", RqlParam.map()
				.set("conId", conId).set("sysdate1", Long.valueOf(sysdate1)).set("sysdate2", Long.valueOf(sysdate2)));

	}

	/**
	 * 新增
	 * 
	 * @param mchtVo
	 *            商户合同基本信息实体类
	 * @throws SnowException
	 */
	public void addMcht(MchtContractVO mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(mchtVo);
	}

	/**
	 * 新增【商户合同图片信息】
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
	public void addPic(UpdateRequestBean reqBean, String conId, String crtTlr, String crtDateTime)
			throws SnowException {
		/** 导入实体类 */
		MchtContractPicVO picVo = new MchtContractPicVO();// 商户合同图片表-实体类

		/** 解析图片编号（图片名）信息 */
		List<String> picList = dealPic(reqBean);
		int i = 1;

		/** 组装数据 */
		DBDao dao = DBDaos.newInstance();
		picVo.setConId(conId);// 合同编号
		picVo.setCrtTlr(crtTlr);// 创建操作员
		picVo.setCrtDateTime(crtDateTime);// 创建日期时间
		for (String picId : picList) {
			picVo.setPicSerNum("0" + i);// 图片的序号
			if (picId.length() == 0) {
				picVo.setPicId("0" + i);// 图片编号
			} else {
				picVo.setPicId(picId);// 图片编号
			}
			dao.insert(picVo);
			i++;
		}
	}

	/**
	 * 修改【商户合同图片信息】
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
	public void updPic(UpdateRequestBean reqBean, String conId, String lastUpdTlr, String lastUpdAteTime)
			throws SnowException {
		/** 定义字段 */
		String picId;// 图片编号
		String picType;
		/** 解析图片编号（图片名）信息 */
		List<String> picList = dealPic(reqBean);
		int i = 1;
		/** 组装数据 */
		DBDao dao = DBDaos.newInstance();
		for (String listElement : picList) {
			picId = listElement;// 图片编号（可认为是图片名称，因为带后缀）
			picType = "0" + i;
			dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.updMchtPic",
					RqlParam.map().set("conId", conId).set("picId", picId).set("picType", picType)
							.set("lastUpdTlr", lastUpdTlr).set("lastUpdAteTime", lastUpdAteTime));
			i++;
		}
	}

	/**
	 * 解析合同图片编号（图片名）信息
	 * 
	 * @param reqBean
	 * @return
	 */
	private List<String> dealPic(UpdateRequestBean reqBean) {
		List<String> picList = new ArrayList<String>();
		picList.add(reqBean.getTotalList().get(0).get("picId1"));// 第一张图片
		picList.add(reqBean.getTotalList().get(0).get("picId2"));// 第二张图片
		picList.add(reqBean.getTotalList().get(0).get("picId3"));// 第三张图片
		picList.add(reqBean.getTotalList().get(0).get("picId4"));// 第四张图片
		picList.add(reqBean.getTotalList().get(0).get("picId5"));// 第五张图片
		return picList;
	}

	/**
	 * 生成商户编号[如果没有记录，采用默认编号；反之找出最大编号加一]
	 * 
	 * @param mchtId
	 *            商户编号
	 * @return
	 * @throws SnowException
	 */
	public String genMchtConId() throws SnowException {
		// 商户编号16位,默认为： 8 + 8位日期 + 7位0
		StringBuffer ConId = new StringBuffer();
		String maxId = queryMaxConId();
		if (StringUtil.isEmpty(maxId)) {
			maxId = "D0000001";
			ConId.append(maxId);
		} else {
			String m = "1" + maxId.substring(1);
			maxId = String.valueOf(Long.parseLong(m) + 1).substring(1);
			ConId.append("D").append(maxId);
		}
		return ConId.toString();
	}

	/**
	 * 获取商户号合同编号最大值
	 * 
	 * @return 商户编号[最大值]
	 */
	private String queryMaxConId() {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.queryMaxConId");
	}

	/**
	 * 修改
	 * 
	 * @param mchtVo
	 *            商户基本信息实体类
	 * @throws SnowException
	 */
	public void updMcht(MchtContractVO mchtVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(mchtVo);
	}

	/*
	 * 增加合同下签订产品下的费率
	 */
	public void addMchtContractAcctRate(MchtContractAcctRate mctar) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.addMchtContractAcctRate", mctar);
	}

	/*
	 * 增加合同下签订产品
	 */
	public void addMchtContractProduct(MchtContractProduct mctpt) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.addMchtContractProduct", mctpt);
	}

	/*
	 * 获取合同关联的机构的名字
	 */
	public List<Object> getSetlAcctInstituteName(String prodIds) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.getSetlAcctInstituteName",
				RqlParam.map().set("prodIdArray", prodIdArray));
	}

	/*
	 * 删除合同下已签订的产品
	 */
	public void delTabPro(String conId, String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.delTabPro",
				RqlParam.map().set("conId", conId).set("prodId", prodId.trim()));
	}

	/*
	 * 删除合同下已签订的产品得费率
	 */
	public void delTabRate(String conId, String prodId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.delTabRate",
				RqlParam.map().set("conId", conId).set("prodId", prodId.trim()));
	}

	/*
	 * 根据机构编号去查询审核流程编号
	 */
	public String selectAuditIdByBrno(String brno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtContract.selectAuditIdByBrno",
				RqlParam.map().set("brno", brno));

	}

	/*
	 * 根据操作员的步骤去查询具体的审核步骤
	 */
	public List<PmpAuditProcStep> selectTepList(String tlrno, String auditType) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll(
				"select * from PMP_AUDIT_PROC_STEP a left join PMP_AUDIT_PROC_INFO b on a.audit_proc_id=b.audit_proc_id left join IFS_ORG c on b.br_class=c.brclass left join IFS_STAFF d on c.brcode=d.brcode  where d.TLRNO='"
						+ tlrno + "' and b.audit_proc_type='" + auditType + "'",
				PmpAuditProcStep.class);
	}

	/*
	 * 获取流程步骤的list,初始化插入到信息记录表中
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
			pmpAuditStepInfo.setAuditState(MchtContractConstants.AUDIT_STATE_00);
			pmpAuditStepInfo.setRoleId(pmpAuditProcStep.getAuditRoleId());
			dao.insert(pmpAuditStepInfo);
		}
	}
	public List getProdIdListByConId(String contractId)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.getProdIdListByConId", contractId);
	}
	public List getTmpProdIdListByConId(String contractId)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.getTmpProdIdListByConId", contractId);
	}
	   public String QContractProd(String conId)throws SnowException{
	        DBDao dao = DBDaos.newInstance();
	        return (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.contractAudit.QContractProd", conId);
	    }

    /**
     * 更新合同区划代码
     * @param mchtId
     * @param setlAcctAreaCode
     */
    public void updContField(String mchtId, String setlAcctAreaCode) {
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updConsetlAcctAreaCode",
              RqlParam.map().set("mchtId", mchtId).set("setlAcctAreaCode", setlAcctAreaCode));
        
    }
}
