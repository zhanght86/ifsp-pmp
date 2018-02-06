package com.ruimin.ifs.pmp.mktActivity.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;

@SnowDoc(desc = "活动方法临时信息Service")
@Service
public class ActvMethodTmpService extends SnowService {
	public static ActvMethodTmpService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ActvMethodTmpService.class);
	}

	/**
	 * 分页查询活动方法列表
	 * 
	 * @param methodNo
	 *            方法编号
	 * @param methodName
	 *            方法名称
	 * @param methodType
	 *            方法类型
	 * @param page
	 *            分页对象
	 */
	public PageResult queryList(String methodNo, String methodName, String methodType, String auditId,Page page) throws SnowException {
		try {
			DBDao dao = DBDaos.newInstance();
			return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.queryList",
					RqlParam.map().set("methodNo", StringUtils.isEmpty(methodNo) ? "" : methodNo)
							.set("methodNm", StringUtils.isEmpty(methodName) ? "" : "%" + methodName + "%")
							.set("methodTp", StringUtils.isEmpty(methodType) ? "" : methodType)
							.set("auditId",StringUtils.isEmpty(auditId) ?"":auditId),
					page);
		} catch (Exception e) {
			SnowLog.getLogger(ActvMethodTmpService.class).error("查询活动方法列表时Exception异常：", e);
			SnowExceptionUtil.throwErrorException("9999");
		}
		return null;
	}

	/**
	 * 根据方法编号删除方法信息
	 * 
	 * @param methodNo
	 *            方法编号
	 * @param tlrNo
	 *            更新操作员编号
	 * @param auditUser
	 *            审核员编号
	 */
	public int deleteMethodByNo(ActiveMethodInfTmpVO tmpVO, String tlrNo) throws SnowException {
		try {
			ActiveMethodInfTmpVO param = new ActiveMethodInfTmpVO();
			param.setMethodNo(tmpVO.getMethodNo());// 方法编号-更新条件

			param.setUpdateOpr(tlrNo);// 更新操作员-更新内容
			param.setUpdateTime(DateUtil.get14Date());// 更新时间-更新内容
			param.setAuditTime(param.getUpdateTime());// 审核时间-更新内容
			param.setAuditOpr(tlrNo);// 审核员-更新内容
			param.setMethodStat(ActiveMethodConstants.MTHD_STAT_DELETE);// 方法状态-删除-更新内容
			param.setSyncFlag(ActiveMethodConstants.SYNC_STATE_UNDONE);// 同步状态-未同步-更新内容
			param.setAuditFlag(tmpVO.getAuditFlag());//更新审核标志
			param.setAuditId(tmpVO.getAuditId());//更新新审核编号
			DBDao dao = DBDaos.newInstance();
			return dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.deleteByMethodNo", param);
		} catch (Exception e) {
			SnowLog.getLogger(ActvMethodTmpService.class).error("删除活动方法列表时Exception异常：", e);
			SnowExceptionUtil.throwErrorException("9999");
		}
		return 0;
	}

	/**
	 * 查询获取数据库最大的方法编号
	 */
	public String getMaxSeq() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.getMaxSeq");
	}

	/**
	 * 新增活动方法
	 */
	public void insertMethod(ActiveMethodInfTmpVO vo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.insertMethod", vo);
	}

	/**
	 * 修改活动方法
	 */
	public void updateMethod(ActiveMethodInfTmpVO vo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.updateMethod", vo);
	}

	/**
	 * 根据活动类型查询方法列表
	 * 
	 * @param methodNo
	 *            方法编号
	 * @param methodName
	 *            方法名称
	 * @param methodType
	 *            方法类型
	 * @param page
	 *            分页对象
	 */
	public PageResult queryListByActiveType(String activeType, String methodNo, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.queryListByActiveType",
				RqlParam.map().set("activeType", activeType == null ? "" : activeType).set("methodNo", methodNo == null?"":methodNo), page);
	}
	/**
	 * 根据方法编号查询方法信息
	 * 
	 * @param methodNo
	 *            方法编号
	 */
	public ActiveMethodInfTmpVO getMethodByNo(String methodNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (ActiveMethodInfTmpVO)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodTmp.getMethodByNo",methodNo);
	}
}
