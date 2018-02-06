package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
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
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.pmp.chnlMng.common.constants.ChnlAcsInfoConstants;
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChnlAcsInfoVO;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyMainMchtInfo;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyMchtTxnRel;
import com.ruimin.ifs.pmp.chnlMng.process.bean.PagyPayCertCfg;

@Service
@SnowDoc(desc = "通道接入信息")
public class ChnlAcsInfoService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static ChnlAcsInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ChnlAcsInfoService.class);
	}

	/**
	 * 查询【通道接入信息】
	 * 
	 * @param qpagyNo
	 *            通道编号
	 * @param qmainMchtAcsType
	 *            接入方式
	 * @param qmainMchtNo
	 *            接入编号 【模糊查询】
	 * @param qmainMchtName
	 *            接入名称 【模糊查询】
	 * @param qmainMchtStat
	 *            接入状态
	 * @param qmainMchtNoAC
	 *            接入编号 【精确查询】
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String qpagyNo, String qmainMchtAcsType, String qmainMchtNo, String qmainMchtName,
			String qmainMchtStat, String qmainMchtNoAC, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.queryMassInfo",
				RqlParam.map().set("qpagyNo", StringUtils.isBlank(qpagyNo) ? "" : qpagyNo)
						.set("qmainMchtAcsType", StringUtils.isBlank(qmainMchtAcsType) ? "" : qmainMchtAcsType)
						.set("qmainMchtNo", StringUtils.isBlank(qmainMchtNo) ? ""
								: "%" + qmainMchtNo + "%")
						.set("qmainMchtName", StringUtils.isBlank(qmainMchtName) ? "" : "%" + qmainMchtName + "%")
						.set("qmainMchtStat", StringUtils.isBlank(qmainMchtStat) ? "" : qmainMchtStat)
						.set("qmainMchtNoAC", StringUtils.isBlank(qmainMchtNoAC) ? "" : qmainMchtNoAC),
				page);
	}

	/**
	 * 组装【功能清单】
	 * 
	 * @param funcVos
	 *            参与运算的实体类
	 * @return 树节点list
	 */
	public List<TreeNode> generateTree(List<ChnlAcsInfoVO> funcVos) {
		/** 加载数据容器 */
		List<TreeNode> list = new ArrayList<TreeNode>();
		ChnlAcsInfoVO funcVo = new ChnlAcsInfoVO();// 存放第一轮组装字段【交易信息】
		ChnlAcsInfoVO funcAcctVo = new ChnlAcsInfoVO();// 存放第二轮组装字段【账户类型信息】
		List<String> txnNoArray = new ArrayList<String>();// 存放已经组装过的交易编号【系统不允许出现相同的交易编号】

		/** 加载标识符 */
		Integer currentTurn = 1;// 当前执行第X轮【1-第一轮；2-第二轮】
		Integer count;// 循环计数器
		boolean repeatFlag = false;// 交易编号重复标志,默认false

		/** 组装过程 */
		for (count = 0; count < funcVos.size() * 2; count++) {
			// 组装树节点
			TreeNode node = new TreeNode();
			// 组装操作
			if (currentTurn.equals(1)) {
				/******* 第一轮组装【交易信息】 *******/
				// 获取所需字段
				funcVo = funcVos.get(count / 2);// 从List中取出地X条记录【X等于当前循环次数除2】
				String pagyTxnCode = funcVo.getPagyTxnCode();// 交易编号
				String pagyTxnName = funcVo.getPagyTxnName();// 交易名称
				String acctTypeNo = funcVo.getAcctTypeNo();// 账户类型编号
				String acctTypeName = funcVo.getAcctTypeName();// 账户类型名称
				// 交易编号去重
				if (!txnNoArray.isEmpty()) {
					for (String txnNoElement : txnNoArray) {
						if (txnNoElement.equals(pagyTxnCode)) {
							repeatFlag = true;
							break;
						}
					}
				}
				if (repeatFlag == false) {
					// 组装树节点信息【父节点-无;子节点-交易编号;展示-交易名称】
					node.setAttributes(funcVo);
					node.setIconCls("fa fa-gears");
					node.setText(pagyTxnName);
					node.setId(pagyTxnCode);
					node.setPid("");
					list.add(node);
					// 记录改交易编号
					txnNoArray.add(pagyTxnCode);
				}
				// 还原交易编号重复标志-false
				repeatFlag = false;
				// 组转第二轮所需字段
				funcAcctVo.setPagyTxnCode(pagyTxnCode);// 交易编号
				funcAcctVo.setAcctTypeNo(acctTypeNo);// 账户类型编号
				funcAcctVo.setAcctTypeName(acctTypeName);// 账户类型名称
				// 更新计数器
				currentTurn++;
			} else {
				/******* 第二轮组装【账户类型信息】 *******/
				// 组装树节点信息【父节点-交易编号;子节点-账户类型编号;展示-账户类型名称】
				node.setAttributes(funcAcctVo);
				node.setIconCls("fa fa-gear");
				node.setText(funcAcctVo.getAcctTypeName());
				node.setId(funcAcctVo.getPagyTxnCode() + "_" + funcAcctVo.getAcctTypeNo());
				node.setPid(funcAcctVo.getPagyTxnCode());
				list.add(node);
				// 更新计数器
				currentTurn--;
			}
		}
		return list;
	}

	/**
	 * 新增或修改时，查询【功能清单】,所选通道下的交易+账户类型，根据操作标志判断sql执行
	 * 
	 * @param qpagyNo
	 *            通道编号
	 * @param qoprType
	 *            操作类型
	 * @param mainMchtNo
	 *            接入编号
	 * @return
	 * @throws SnowException
	 */
	public List<ChnlAcsInfoVO> queryFuncTreeWhenAddOrUpd(String qpagyNo, String qoprType, String mainMchtNo)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.queryFuncTreeWhenAddOrUpd",
				RqlParam.map().set("qpagyNo", qpagyNo).set("qoprType", qoprType).set("mainMchtNo", mainMchtNo));
		List<ChnlAcsInfoVO> result = new ArrayList<ChnlAcsInfoVO>();
		for (Object obj : list) {
			result.add((ChnlAcsInfoVO) obj);
		}
		return result;
	}

	/**
	 * 详情时，查询【功能清单】,所选通道下的交易+账户类型
	 * 
	 * @param mainMchtNo
	 *            接入编号
	 * @return
	 * @throws SnowException
	 */
	public List<ChnlAcsInfoVO> queryFuncTreeWhenDetail(String mainMchtNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.queryFuncTreeWhenDetail",
				mainMchtNo);
		List<ChnlAcsInfoVO> result = new ArrayList<ChnlAcsInfoVO>();
		for (Object obj : list) {
			result.add((ChnlAcsInfoVO) obj);
		}
		return result;
	}

	/**
	 * 校验【接入编号是否重复】
	 * 
	 * @param mainMchtNo
	 *            接入编号【主键】
	 * @throws SnowException
	 */
	public void validPkNoRepeat(String mainMchtNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (!dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.validPkNoRepeat", mainMchtNo).isEmpty()) {
			SnowExceptionUtil.throwErrorException("【接入编号】已经存在，请重新输入！");
		}
	}

	/**
	 * 校验【日期输入】
	 * 
	 * @param tempVo
	 *            通道接入信息-实体类
	 * @param currentDate
	 *            当前日期
	 * @throws SnowException
	 */
	public void vaildDateInput(ChnlAcsInfoVO tempVo, String currentDate) throws SnowException {
		Integer date = Integer.valueOf(currentDate);// 当前日期
		if (StringUtils.isNotBlank(tempVo.getCertifiDate())) {
			Integer certifiDate = Integer.valueOf(tempVo.getCertifiDate());// 证书生效日期
			Integer certifiEndDate = Integer.valueOf(tempVo.getCertifiEndDate());// 证书失效日期
			if (!(certifiDate < certifiEndDate)) {
				SnowExceptionUtil.throwErrorException("【证书生效日期】必须早于【证书失效日期】，请重新输入！");
			} else if (!(date < certifiEndDate)) {
				SnowExceptionUtil.throwErrorException("【当前日期】必须早于【证书失效日期】，请重新输入！");
			}
		}
		if (StringUtils.isNotBlank(tempVo.getMd5Date())) {
			Integer md5Date = Integer.valueOf(tempVo.getMd5Date());// 密钥生效日期
			Integer md5EndDate = Integer.valueOf(tempVo.getMd5EndDate());// 密钥失效日期
			if (!(md5Date < md5EndDate)) {
				SnowExceptionUtil.throwErrorException("【证书生效日期】必须早于【证书失效日期】，请重新输入！");
			} else if (!(date < md5EndDate)) {
				SnowExceptionUtil.throwErrorException("【当前日期】必须早于【证书失效日期】，请重新输入！");
			}
		}

	}

	/**
	 * 新增【通道接入基本信息】
	 * 
	 * @param acsVo
	 *            主商户信息表-实体类
	 * @throws SnowException
	 */
	public void addAcs(PagyMainMchtInfo acsVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(acsVo);
	}

	/**
	 * 新增【证书信息】
	 * 
	 * @param certVo
	 *            支付证书信息配置-实体类
	 * @throws SnowException
	 */
	public void addCert(PagyPayCertCfg certVo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(certVo);
	}

	/**
	 * 新增【功能清单树节点】
	 * 
	 * @param funcStr
	 *            勾选节点信息
	 * @param mainMchtNo
	 *            接入编号
	 * @param pagyNo
	 *            通道编号
	 * @throws SnowException
	 */
	public void addFuncTree(String funcStr, String mainMchtNo, String pagyNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PagyMchtTxnRel relVo = new PagyMchtTxnRel();// 通道商户交易关系表-实体类
		String[] funcStrArr = {};
		if (StringUtils.isNotBlank(funcStr)) {
			funcStrArr = funcStr.split(","); // 第一次分解，得到【勾选节点信息】
		} else {
			SnowExceptionUtil.throwErrorException("必须配置功能清单！");
		}
		for (String funcStrElement : funcStrArr) {
			String[] tempStrArr = funcStrElement.split("/");// 第二次分解，得到【父节点】,【子节点】
			String[] txnAndAcct = tempStrArr[1].split("_");// 第三次分解，得到【交易编号】，【账户类型编号】
			if (StringUtils.isBlank(txnAndAcct[0])) {
				SnowExceptionUtil.throwErrorException("配置错误，交易编号为空！");
			}
			if (StringUtils.isBlank(txnAndAcct[1])) {
				SnowExceptionUtil.throwErrorException("配置错误，账户类型编号为空！");
			}
			relVo.setPagyTxnCode(txnAndAcct[0]);// 交易编号
			relVo.setAcctTypeNo(txnAndAcct[1]);// 账户类型编号
			relVo.setMainMchtNo(mainMchtNo);// 接入编号
			relVo.setPagyNo(pagyNo);// 通道编号
			dao.insert(relVo);
		}
	}

	/**
	 * 修改【通道接入基本信息】
	 * 
	 * @param acsVo
	 *            主商户信息表-实体类
	 * @param pkChangeFlag
	 *            主键【接入编号】改动标志
	 * @param mainMchtNoAC
	 *            修改前接入编号-用于修改记录
	 * @throws SnowException
	 */
	public void updAcs(PagyMainMchtInfo acsVo, boolean pkChangeFlag, String mainMchtNoAC) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		if (pkChangeFlag) {
			dao.update(acsVo);
		} else {
			dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.delBase", mainMchtNoAC);
			dao.insert(acsVo);
		}
	}

	/**
	 * 修改【证书信息】 由于使用记录集成技术，所以使用非常规修改方法，先删除后增加 基于action的事务声明，该方法是可靠的
	 * 
	 * @param certVo
	 *            支付证书信息配置-实体类
	 * @param count
	 *            操作次数
	 * @param mainMchtNoAC
	 *            修改前接入编号-用于修改记录
	 * @return 操作次数
	 * @throws SnowException
	 */
	private Integer updCert(PagyPayCertCfg certVo, Integer count, String mainMchtNoAC) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String certifiId = mainMchtNoAC;
		if (count.equals(0)) {
			dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.delCert", certifiId);
		}
		dao.insert(certVo);
		return ++count;
	}

	/**
	 * 修改【功能清单树节点】 由于面向不确定数量的记录，所以使用非常规修改方法，先删除后增加 基于action的事务声明，该方法是可靠的
	 * 
	 * @param funcStr
	 *            勾选节点信息
	 * @param mainMchtNoAC
	 *            修改前接入编号-用于修改记录
	 * @param mainMchtNo
	 *            接入编号
	 * @param pagyNo
	 *            通道编号
	 * @throws SnowException
	 */
	public void updFuncTree(String funcStr, String mainMchtNoAC, String mainMchtNo, String pagyNo)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.delFuncTree", mainMchtNoAC);
		addFuncTree(funcStr, mainMchtNo, pagyNo);
	}

	/**
	 * 根据操作标志智能选择【新增证书】或【修改证书】
	 * 
	 * @param oprFlag
	 *            操作标志
	 * @param certVo
	 *            支付证书信息配置-实体类
	 * @param count
	 *            操作次数
	 * @param mainMchtNoAC
	 *            修改前接入编号-用于修改记录
	 * @return 操作次数
	 * @throws SnowException
	 */
	private Integer addOrUpdCert(String oprFlag, PagyPayCertCfg certVo, Integer count, String mainMchtNoAC)
			throws SnowException {
		if (oprFlag.equals(ChnlAcsInfoConstants.OPR_FLAG_ADD)) {
			addCert(certVo);
		} else if (oprFlag.equals(ChnlAcsInfoConstants.OPR_FLAG_UPD)) {
			count = updCert(certVo, count, mainMchtNoAC);
		} else {
			SnowExceptionUtil.throwErrorException("该操作无法识别！");
		}
		return count;
	}

	/**
	 * 证书信息入库
	 * 
	 * @param tempVo
	 *            通道接入信息-实体类
	 * @param certVo
	 *            支付证书信息配置-实体类
	 * @param oprFlag
	 *            操作标志
	 * @param mainMchtNoAC
	 *            修改前接入编号-用于修改记录
	 * @throws SnowException
	 */
	public void reInCert(ChnlAcsInfoVO tempVo, PagyPayCertCfg certVo, String oprFlag, String mainMchtNoAC)
			throws SnowException {
		String encryptType = tempVo.getEncryptType();// 加密类型【01-RSA；02-MD5】
		String needSign = tempVo.getNeedSign();// 需要加密【00-不需要；01-需要】
		Integer count = 0;// 操作次数,初始为0
		// 无论哪种情况都需组装的字段
		certVo.setCertifiId(tempVo.getMainMchtNo());// 证书编号
		certVo.setCertifiBl(tempVo.getMainMchtNo());// 证书所属
		certVo.setCertifiUseType(tempVo.getPagyNo());// 证书使用类型
		certVo.setCertifiStatus(ChnlAcsInfoConstants.CERT_STAT_ON);// 证书状态

		if (encryptType.equals(ChnlAcsInfoConstants.ENC_TYPE_MD5)) {
			/*********************** MD5 *************************/
			// 标志字段
			certVo.setEncryptType(ChnlAcsInfoConstants.ENC_TYPE_MD5);// 加密类型
			certVo.setEncryptWayType(ChnlAcsInfoConstants.ENC_WAY_TYPE_PASSWORD);// 加密方式
			certVo.setCertifiType(ChnlAcsInfoConstants.CERT_TYPE_MD5);// 证书类型
			// 将md5信息迁移到证书对应字段下【因为数据库共用字段】
			certVo.setCertifiPasswd(tempVo.getMd5Passwd());
			certVo.setCertifiDate(tempVo.getMd5Date());
			certVo.setCertifiEndDate(tempVo.getMd5EndDate());
			// 入库
			count = addOrUpdCert(oprFlag, certVo, count, mainMchtNoAC);
		} else {
			/*********************** RSA *************************/
			// 标志字段
			certVo.setEncryptType(ChnlAcsInfoConstants.ENC_TYPE_RSA);// 加密类型
			certVo.setEncryptWayType(ChnlAcsInfoConstants.ENC_WAY_TYPE_PASSWORD);// 加密方式
			certVo.setCertifiType(ChnlAcsInfoConstants.CERT_TYPE_PRV);// 证书类型
			// 入库
			count = addOrUpdCert(oprFlag, certVo, count, mainMchtNoAC);

			if (needSign.equals(ChnlAcsInfoConstants.SIGN_NEED)) {
				/*********************** 签名 *************************/
				// 标志字段
				certVo.setEncryptType(ChnlAcsInfoConstants.ENC_TYPE_MD5);// 加密类型
				certVo.setEncryptWayType(ChnlAcsInfoConstants.ENC_WAY_TYPE_SIGN);// 加密方式
				certVo.setCertifiType(ChnlAcsInfoConstants.CERT_TYPE_MD5);// 证书类型
				// 将md5信息迁移到证书对应字段下【因为数据库共用字段】
				certVo.setCertifiPasswd(tempVo.getMd5Passwd());
				certVo.setCertifiDate(tempVo.getMd5Date());
				certVo.setCertifiEndDate(tempVo.getMd5EndDate());
				// 删除证书相关信息
				certVo.setCertifiPath("");
				certVo.setCertifiCode("");
				// 入库
				count = addOrUpdCert(oprFlag, certVo, count, mainMchtNoAC);
			}
		}
	}

	/**
	 * 启用/停用【通道接入信息】
	 * 
	 * @param trlno
	 *            更新操作员
	 * @param lastUpdDateTime
	 *            更新日期时间
	 * @param mainMchtStat
	 *            接入状态
	 * @param mainMchtNo
	 *            主商户编号
	 */
	public void stpAcs(String trlno, String lastUpdDateTime, String mainMchtStat, String mainMchtNo) {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.chnlMng.rql.chnlAcsInfo.stpAcs",
				RqlParam.map().set("trlno", trlno).set("lastUpdDateTime", lastUpdDateTime)
						.set("mainMchtStat", mainMchtStat).set("mainMchtNo", mainMchtNo));
	}
}