package com.ruimin.ifs.mng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.framework.utils.ValueUtil;
import com.ruimin.ifs.mng.common.constants.StaffConstants;

@Service
public class BizLogService extends SnowService {

	public static BizLogService getInstance() throws SnowException {
		return ContextUtil.getSingleService(BizLogService.class);
	}

	/**
	 * 
	 * 功能描述: 查询功能
	 * 
	 * @param oprcode
	 *            操作号
	 * @param tlrno
	 *            操作名称
	 * @param startDate
	 *            操作开始时间
	 * @param endDate
	 *            操作结束时间
	 * @param brno
	 *            操作员编号
	 * @param page
	 *            页码数
	 * @return 返回类型 List
	 * @throws SnowException
	 */
	public PageResult queryList(String oprcode, String tlrno, String startDate, String endDate, String brno, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		SessionUserInfo currentUser = SessionUtil.getSessionUserInfo();
		// 当前登陆的操作员如果是系统管理员，则可以查找所有操作员的日志记录
		if (currentUser.getTlrno().equals(StaffConstants.ADMIN_TLRNO)) {
			return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryBizLog",
					RqlParam.map().set("brno", ValueUtil.getRqlParamValue(brno, ""))
							.set("oprcode", ValueUtil.getRqlParamValue(oprcode, "", "%" + oprcode + "%"))
							.set("tlrno", ValueUtil.getRqlParamValue(tlrno, "", tlrno))
							.set("startDate", ValueUtil.getRqlParamValue(startDate, ""))
							.set("endDate", ValueUtil.getRqlParamValue(endDate, "")),
					page);
		} else {
			// 当前登陆的操作员如果是非系统管理员，则只能查找当前操作员的日志记录
			return dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryBizLog1",
					RqlParam.map().set("brno", sessionUserInfo.getTlrno())
							.set("oprcode", ValueUtil.getRqlParamValue(oprcode, "", "%" + oprcode + "%"))
							.set("tlrno", ValueUtil.getRqlParamValue(tlrno, "", tlrno))
							.set("startDate", ValueUtil.getRqlParamValue(startDate, ""))
							.set("endDate", ValueUtil.getRqlParamValue(endDate, "")),
					page);
		}

	}

}
