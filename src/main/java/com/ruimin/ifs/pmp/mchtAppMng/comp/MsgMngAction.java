package com.ruimin.ifs.pmp.mchtAppMng.comp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruim.ifsp.utils.beans.IfspBeanUtils;
import com.ruim.ifsp.utils.datetime.IfspDateTime;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MsgBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MsgVo;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.MsgMngService;
import com.ruimin.ifs.pmp.mchtAppMng.process.service.UserMngService;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@ActionResource
@SnowDoc(desc = "消息管理")
public class MsgMngAction {
	/**
	 * 查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException{
		PageResult result = null;
		/**查询条件*/
		String qmsgDate = queryBean.getParameter("qmsgDate");
		String qmsgTitle = queryBean.getParameter("qmsgTitle");
		String qmsgChn = queryBean.getParameter("qmsgChn");
		String qsendWay = queryBean.getParameter("qsendWay");
		/**返回查询结果*/
		result = MsgMngService.getInstance().queryMain(qmsgDate,qmsgTitle,qmsgChn,qsendWay, queryBean.getPage());
		SnowLog.getServerLog().info("消息信息查询: [ 记录总数："+result.getTotalCount()+" ]");
		return result;
	}
		
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "消息新增")
	public void add(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
    	//消息信息
		MsgBaseInfo msgBaseInfo = new MsgBaseInfo();
    	//获取消息数据集
    	UpdateRequestBean reqBean = updateMap.get("msgMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), msgBaseInfo);
    	}

    	SnowLog.getLogger(MsgMngService.class).info("=============== 设置消息信息 =============");
    	String msgExpiryTime = msgBaseInfo.getMsgExpiry();
    	SnowLog.getLogger(MsgMngService.class).info("=============== 有效期: ============="+msgExpiryTime);
    	if(msgExpiryTime.length()!=14 || !"2".equals(msgExpiryTime.substring(0, 1))){
    		Long time = Long.valueOf(msgExpiryTime);
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
			msgExpiryTime = format.format(time);
    	}
    	SnowLog.getLogger(MsgMngService.class).info("=============== 有效期: ============="+msgExpiryTime);
    	long msgExpiry = Long.valueOf(msgExpiryTime);  
    	long sysTime = Long.valueOf(IfspDateTime.getYYYYMMDDHHMMSS());
    	if(msgExpiry<sysTime){
    		SnowExceptionUtil.throwErrorException("有效期必须在当前时间之后");
    	}
    	//查询当前记录的最大系统消息编号
    	String maxSeq = MsgMngService.getInstance().queryMaxId();
    	String nextSeq;
    	if(!StringUtil.isBlank(maxSeq)){
    	    long num = Long.valueOf(maxSeq);
    	    nextSeq = String.valueOf(num+1);
    	}else{
    	    nextSeq = "10000001";
    	}
    	msgBaseInfo.setMsgId(nextSeq);//设置消息编号
    	msgBaseInfo.setMsgDate(IfspDateTime.getYYYYMMDD());
    	msgBaseInfo.setMsgTime("");
    	//实体类对象插入到数据库中
    	MsgMngService.getInstance().add(msgBaseInfo);
    	
    	sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
    	        "[系统消息]--新增:消息编号[noticeNo]=" + msgBaseInfo.getMsgId() });
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "消息推送")
	public void send(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
    	MsgVo msgVo = new MsgVo();
    	//获取消息数据集
    	UpdateRequestBean reqBean = updateMap.get("msgMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), msgVo);
    	}
    	
    	Map map = MsgMngService.getInstance().buildMsg(msgVo);
    	SnowLog.getLogger(MsgMngService.class).info("=============== 消息信息: ============="+map);
    	msgVo.setMsgDate(IfspDateTime.getYYYYMMDD());
    	msgVo.setMsgTime(IfspDateTime.getHHMMSS());
    	MsgMngService.getInstance().send(map);
    	List list = UserMngService.getInstance().queryInfoByDeviceType(msgVo.getDeviceType());
    	MsgBaseInfo msgBaseInfo = new MsgBaseInfo();
    	IfspBeanUtils.copyProperties(msgVo, msgBaseInfo);
    	MsgMngService.getInstance().addMsgUserBaseInfo(list,msgBaseInfo);
    	
    	sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
    	        "[系统消息]--推送:消息编号[noticeNo]=" + msgVo.getMsgId() });
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "消息推送查询")
	public ArrayList sendQuery(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		MsgVo msgVo = new MsgVo();
    	//获取消息数据集
    	UpdateRequestBean reqBean = updateMap.get("msgMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), msgVo);
    	}
    	ArrayList list = MsgMngService.getInstance().sendQuery(msgVo);
    	
    	sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
    	        "[系统消息]--发送查询:消息编号[noticeNo]=" + msgVo.getMsgId() });
    	return list;
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "消息删除")
	public void delete(Map<String, UpdateRequestBean> updateMap) throws Exception {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
    	//消息信息
		MsgBaseInfo msgBaseInfo = new MsgBaseInfo();
    	//获取消息数据集
    	UpdateRequestBean reqBean = updateMap.get("msgMng");
    	while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), msgBaseInfo);
    	}

    	SnowLog.getLogger(MsgMngService.class).info("=============== 设置消息信息 =============");
    	
    	MsgMngService.getInstance().delete(msgBaseInfo);
    	sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
    	        "[系统消息]--新增:消息编号[noticeNo]=" + msgBaseInfo.getMsgId() });
	}
}
