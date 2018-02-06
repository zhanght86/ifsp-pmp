package com.ruimin.ifs.pmp.platMng.process.service;

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
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.platMng.common.constants.NoticeTitleConstants;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpSysNoticeInfoVO;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;

@Service
public class NoticeTitleService extends SnowService {
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static NoticeTitleService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(NoticeTitleService.class);
	}

	/**
	 * 查询系统公告
	 * 
	 * @param qcrtDateTime
	 *            发布公告事件
	 * @param qnoticeTitle
	 *            公告标题
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryNoticeInfoByState(String qcrtDateTime, String qnoticeTitle, String qnoticeState, Page page)
			throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.platMng.rql.PMP_SYS_NOTICE_INFO.queryNoticeInfoByState",
				RqlParam.map().set("qcrtDateTime", StringUtils.isBlank(qcrtDateTime) ? "" : "%" + qcrtDateTime + "%")
						.set("qnoticeState", StringUtils.isBlank(qnoticeState) ? "" : "%" + qnoticeState + "%")
						.set("qnoticeTitle", StringUtils.isBlank(qnoticeTitle) ? "" : "%" + qnoticeTitle + "%"),
				page);
	}

	public PageResult queryNoticeInfo(String noticeNo, String qcrtDateTime, String qnoticeTitle, String qnoticeState,
			Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.platMng.rql.PMP_SYS_NOTICE_INFO.queryNoticeInfo",
				RqlParam.map().set("noticeNo", StringUtils.isBlank(noticeNo) ? "" : "%" + noticeNo + "%")
						.set("qcrtDateTime", StringUtils.isBlank(qcrtDateTime) ? "" : "%" + qcrtDateTime + "%")
						.set("qnoticeState", StringUtils.isBlank(qnoticeState) ? "" : "%" + qnoticeState + "%")
						.set("qnoticeTitle", StringUtils.isBlank(qnoticeTitle) ? "" : "%" + qnoticeTitle + "%"),
				page);
	}

	/**
	 * 启用停用操作
	 */

	public void updateNoticeInfoState(PmpSysNoticeInfoVO pmpSysNoticeInfoVO) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();
		// 公告编号
		String noticeNo = pmpSysNoticeInfoVO.getNoticeNo();

		// 公告状态 00有效 99无效
		String noticeState = pmpSysNoticeInfoVO.getNoticeState();
		if (NoticeTitleConstants.NOTICE_STATE_00.equals(noticeState)) {
			pmpSysNoticeInfoVO.setNoticeState(NoticeTitleConstants.NOTICE_STATE_99);
		}
		if (NoticeTitleConstants.NOTICE_STATE_99.equals(noticeState)) {
			pmpSysNoticeInfoVO.setNoticeState(NoticeTitleConstants.NOTICE_STATE_00);
		}
		// 最近更新柜员
		String lastUpdTlr = sessionUserInfo.getTlrno();
		// 最近更新时间取系统时间
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.pmp.platMng.rql.PMP_SYS_NOTICE_INFO.updateMethod2",
				RqlParam.map().set("noticeState", pmpSysNoticeInfoVO.getNoticeState()).set("lastUpdTlr", lastUpdTlr)
						.set("lastUpdDateTime", lastUpdDateTime).set("noticeNo", noticeNo));
	}

	/**
	 * 公告修改操作
	 * 
	 * @param pmpSysNoticeInfoVO
	 *            公告实体对象
	 * @param noticeTitle
	 *            公告标题
	 * @param noticePicId
	 *            图片号
	 * @param noticeContent 
	 * 				公告内容
	 * @throws SnowException
	 */
	public void updateNoticeInfo(PmpSysNoticeInfoVO pmpSysNoticeInfoVO, String noticeTitle, String noticePicId, String noticeContent)
			throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();
		// 公告编号
		String noticeNo = pmpSysNoticeInfoVO.getNoticeNo();
		// 最近更新柜员
		String lastUpdTlr = sessionUserInfo.getTlrno();
		// 最近更新时间取系统时间
		String lastUpdDateTime = BaseUtil.getCurrentDateTime();
		dao.executeUpdate("com.ruimin.ifs.pmp.platMng.rql.PMP_SYS_NOTICE_INFO.updateMethod1",
				RqlParam.map().set("noticeTitle", noticeTitle).set("noticePicId", noticePicId)
						.set("noticeContent", noticeContent)
						.set("lastUpdTlr", lastUpdTlr).set("lastUpdDateTime", lastUpdDateTime)
						.set("noticeNo", noticeNo));
	}

	/**
	 * 新增公告
	 * 
	 * @param pmpSysNoticeInfoVO
	 *            公告实体对象
	 * @throws SnowException
	 */
	public void addNoticeInfo(PmpSysNoticeInfoVO pmpSysNoticeInfoVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(pmpSysNoticeInfoVO);
	}

	/**
	 * 查询当前记录的系统公告最大编号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String queryMaxNoticeNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.platMng.rql.PMP_SYS_NOTICE_INFO.queryMaxNoticeNo");
	}

}
