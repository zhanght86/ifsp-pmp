package com.ruimin.ifs.pmp.platMng.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.platMng.common.constants.NoticeTitleConstants;
import com.ruimin.ifs.pmp.platMng.process.bean.PmpSysNoticeInfoVO;
import com.ruimin.ifs.pmp.platMng.process.service.NoticeTitleService;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@SnowDoc(desc = "系统公告")
@ActionResource
public class NoticeTitleAction extends SnowAction {
	@Action
	@SnowDoc(desc = "系统公告启用查询")
	public PageResult queryNoticeInfoByState(QueryParamBean queryBean) throws SnowException {
		String qcrtDateTime = queryBean.getParameter("qcrtDateTime");
		String qnoticeTitle = queryBean.getParameter("qnoticeTitle");
		String qnoticeState = queryBean.getParameter("qnoticeState");
		return NoticeTitleService.getInstance().queryNoticeInfoByState(qcrtDateTime, qnoticeTitle, qnoticeState,
				queryBean.getPage());
	}

	@Action
	@SnowDoc(desc = "系统公告全部查询")
	public PageResult queryNoticeInfo(QueryParamBean queryBean) throws SnowException {
		String qcrtDateTime = queryBean.getParameter("qcrtDateTime");
		String qnoticeTitle = queryBean.getParameter("qnoticeTitle");
		String qnoticeState = queryBean.getParameter("qnoticeState");
		String noticeNo = queryBean.getParameter("noticeNo");
		return NoticeTitleService.getInstance().queryNoticeInfo(noticeNo, qcrtDateTime, qnoticeTitle, qnoticeState,
				queryBean.getPage());
	}

	@SnowDoc(desc = "停用启用系统公告")
	@Action(propagation = Propagation.REQUIRED)
	public void updateNoticeInfoState(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("systemNoticeInfo");// 获取数据集
		PmpSysNoticeInfoVO pmpSysNoticeInfoVO = new PmpSysNoticeInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pmpSysNoticeInfoVO);
		}
		String noticeState = pmpSysNoticeInfoVO.getNoticeState();
		String msg = "";
		if (NoticeTitleConstants.NOTICE_STATE_00.equals(noticeState)) {// 停用
			msg = "停用";
		}
		if (NoticeTitleConstants.NOTICE_STATE_99.equals(noticeState)) {// 启用
			msg = "启用";
		}
		NoticeTitleService.getInstance().updateNoticeInfoState(pmpSysNoticeInfoVO);

		/****************** 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[系统公告]--" + msg + ":公告编号[noticeNo]=" + pmpSysNoticeInfoVO.getNoticeNo() + "" });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改系统公告")
	public void updateNoticeInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("systemNoticeInfo");// 获取数据集
		PmpSysNoticeInfoVO pmpSysNoticeInfoVO = new PmpSysNoticeInfoVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), pmpSysNoticeInfoVO);
		}
		// 获取公告标题
		String noticeTitle = reqBean.getParameter("noticeTitle");
		// 获取图片信息
		String noticePicId = reqBean.getParameter("noticePicId");
		//获取公告内容
		String noticeContent = reqBean.getParameter("noticeContent");
		//如果没有修改图片
		if (noticePicId == null || "".equals(noticePicId)) {
			noticePicId = pmpSysNoticeInfoVO.getNoticePicId();
		}
		// 修改公告信息
		NoticeTitleService.getInstance().updateNoticeInfo(pmpSysNoticeInfoVO, noticeTitle, noticePicId,noticeContent);
		/****************** 记录日志 ********************/
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[系统公告]--修改:公告编号[noticeNo]=" + pmpSysNoticeInfoVO.getNoticeNo() + "]" });
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "公告新增")
	public synchronized void save(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		SnowLog.getLogger(NoticeTitleAction.class).info("=============== 初始化公告信息 =============");
		// 公告信息
		PmpSysNoticeInfoVO pmpSysNoticeInfoVO = new PmpSysNoticeInfoVO();
		// 获取公告数据集
		UpdateRequestBean reqBean = updateMap.get("systemNoticeInfo");
		// 获取公告标题
		String noticeTitle = reqBean.getParameter("noticeTitle");
		// 获取图片信息
		String noticePicId = reqBean.getParameter("noticePicId");
		//获取公告内容
		String noticeContent = reqBean.getParameter("noticeContent");
		SnowLog.getLogger(NoticeTitleAction.class).info("=============== 设置新增公告信息 =============");

		// 查询当前记录的最大系统公告编号
		String maxSeq = NoticeTitleService.getInstance().queryMaxNoticeNo();
		String nextSeq;
		if (!StringUtil.isBlank(maxSeq)) {
			int num = Integer.valueOf(maxSeq);
			nextSeq = String.valueOf(num + 1);
		} else {
			nextSeq = NoticeTitleConstants.NOTICE_N0_MIN;
		}
		pmpSysNoticeInfoVO.setNoticeNo(nextSeq);// 设置公告编号
		pmpSysNoticeInfoVO.setNoticeTitle(noticeTitle);// 设置公告标题
		pmpSysNoticeInfoVO.setNoticePicId(noticePicId);// 设置图片ID
		pmpSysNoticeInfoVO.setNoticeContent(noticeContent);//公告内容
		pmpSysNoticeInfoVO.setNoticeState(NoticeTitleConstants.NOTICE_STATE_00);// 设置公告状态
																				// 00启用
																				// 99停用
		pmpSysNoticeInfoVO.setCrtTlr(sessionUserInfo.getTlrno());// 设置创建柜员
		pmpSysNoticeInfoVO.setCrtDateTime(BaseUtil.getCurrentDateTime());// 设置创建日期时间
		pmpSysNoticeInfoVO.setLastUpdTlr(sessionUserInfo.getTlrno());// 设置最新更新操作员
		pmpSysNoticeInfoVO.setLastUpdDateTime(BaseUtil.getCurrentDateTime());// 设置最近更新时间
		// 实体类对象插入到数据库中
		NoticeTitleService.getInstance().addNoticeInfo(pmpSysNoticeInfoVO);
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[系统公告]--新增:公告编号[noticeNo]=" + pmpSysNoticeInfoVO.getNoticeNo() });

	}
}
