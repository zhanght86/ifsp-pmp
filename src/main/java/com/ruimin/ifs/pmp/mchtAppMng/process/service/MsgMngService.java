package com.ruimin.ifs.pmp.mchtAppMng.process.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.ruim.ifsp.utils.message.IfspFastJsonUtil;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtAppMng.common.constant.MessageConstants;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MchtUserVO;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MsgBaseInfo;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MsgVo;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.MssUserMsgRlt;
import com.ruimin.ifs.pmp.pubTool.util.HttpTransUtil;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
import com.ruimin.ifs.util.StringUtil;

@Service
@SnowDoc(desc = "消息管理")
public class MsgMngService extends SnowService{	
	private Logger log = SnowLog.getLogger(MsgMngService.class);
	/**
	 * 获取实例
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MsgMngService getInstance() throws SnowException{
		return ContextUtil.getSingleService(MsgMngService.class);
	}
	

	/**
	 * 
	 * @param qcrtDate
	 * @param qmsgTitle
	 * @param qmsgType
	 * @param qsendWay
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String qmsgDate, String qmsgTitle, String qmsgChn, String qsendWay, com.ruimin.ifs.framework.process.query.Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		PageResult result = null;
		
		result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.msgMng.queryMain", RqlParam.map()
				.set("qmsgDate",  StringUtils.isBlank(qmsgDate) ? "" : "%"+qmsgDate+"%")
				.set("qmsgTitle", StringUtils.isBlank(qmsgTitle) ? "" : "%"+qmsgTitle+"%")
				.set("qmsgChn", StringUtils.isBlank(qmsgChn) ? "" : qmsgChn)
				.set("qsendWay", StringUtils.isBlank(qsendWay) ? "" : qsendWay),
				page);			
		return result;
	}

	public void add(MsgBaseInfo msgBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(msgBaseInfo);
	}

	public String queryMaxId() {
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.msgMng.queryMaxId");
	}


	public void delete(MsgBaseInfo msgBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(msgBaseInfo);
	}

	/**
	 * 消息推送
	 * @param paramMap
	 * @throws Exception
	 */
	public void send(Map paramMap) throws Exception {
		Gson gson = new Gson();
		
		/********************************************计时开始********************************************/
		Long startTime = System.currentTimeMillis();
		try {
			log.info("消息推送请求接口-请求开始...paramMap:"+paramMap); 
				
			/**组装报文*/
			String requestMsg = gson.toJson(paramMap);
			//-----------------------------------------STEP NO2 传输阶段-----------------------------------------//
			/**配置传输基本信息*/
			URL url = null;//资源地址
			url = new URL(SysParamUtil.getParam(MessageConstants.MSG_SEND_SEVR_URL));				
			
			String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);//请求方式
			
			/**建立服务器连接*/
			HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
			
			/**发送报文*/
			HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
			
			//-----------------------------------------STEP NO3 获取响应-----------------------------------------//
			/**获取服务端响应*/
			String retMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
			Map<String,String> serRetMap=new HashMap<String, String>();
			serRetMap = gson.fromJson(retMsg, Map.class);
			String respCode = serRetMap.get("respCode");//响应码
			String respMsg = serRetMap.get("respMsg");
			//截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
			if(!"0000".equals(respCode)){
				SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
			}
			/********************************************计时结束********************************************/
		
		} catch (SnowException se) {
			log.error("消息推送【"  + "】-请求失败：原因：",se);
			log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
			SnowExceptionUtil.throwErrorException("消息推送【"  + "】-请求失败：原因："+se);
		}
	}

	/**
	 * 消息发送查询
	 * @param msgBaseInfo
	 * @throws Exception 
	 */
	public ArrayList sendQuery(MsgVo msgVo) throws Exception {
		Map paramMap = new HashMap();
		paramMap.put("chlNo", "401");
		paramMap.put("msgId", msgVo.getMsgId());
		paramMap.put("deviceType", msgVo.getDeviceType());
		
		/********************************************计时开始********************************************/
		Long startTime = System.currentTimeMillis();
		try {
			log.info("消息推送查询请求接口-请求开始...paramMap:"+paramMap); 
				
			/**组装报文*/
			Gson gson = new Gson();
			String requestMsg = gson.toJson(paramMap);
			//-----------------------------------------STEP NO2 传输阶段-----------------------------------------//
			/**配置传输基本信息*/
			URL url = null;//资源地址
			url = new URL(SysParamUtil.getParam(MessageConstants.MSG_QUERY_SEVR_URL));				
			
			String requestMehtod = SysParamUtil.getParam(MchtChnlRequestConstants.MCHT_CHL_REQUEST_METHOD);//请求方式
			
			/**建立服务器连接*/
			HttpURLConnection urlConnection = HttpTransUtil.getInstance().buildConnect(url, requestMehtod);
			
			/**发送报文*/
			HttpTransUtil.getInstance().sendMsg(urlConnection, requestMsg);
			
			//-----------------------------------------STEP NO3 获取响应-----------------------------------------//
			/**获取服务端响应*/
			String retMsg = HttpTransUtil.getInstance().recvResponse(urlConnection);
			Map<String,String> serRetMap=new HashMap<String, String>();
			serRetMap = gson.fromJson(retMsg, Map.class);
			String respCode = serRetMap.get("respCode");//响应码
			String respMsg = serRetMap.get("respMsg");
			String msgInfoJson = (String) serRetMap.get("respMsgInfoList");
			ArrayList list = new ArrayList();
			list = gson.fromJson(msgInfoJson, ArrayList.class);
			//截取响应码后四位，判断请求是否成功，如果失败，返回失败原因
			if(!"0000".equals(respCode)){
				SnowExceptionUtil.throwErrorException(serRetMap.get("respMsg"));
			}
			return list;
			/********************************************计时结束********************************************/
		
		} catch (SnowException se) {
			SnowExceptionUtil.throwErrorException("消息推送查询【"  + "】-请求失败：原因：",se);
			log.error("消息推送查询【"  + "】-请求失败：原因：",se);
			log.info("耗时：" + (System.currentTimeMillis() - startTime) + " ms ");
		}
		return null;
	}

	/**
	 * 组装报文
	 * @param msgVo
	 * @return
	 * @throws SnowException
	 */
	public Map buildMsg(MsgVo msgVo) throws SnowException {
		Map paramMap = new HashMap();
		Map mapOut = new HashMap();
		if("1".equals(msgVo.getDeviceType())){
		    paramMap.put("deviceType", "IOS");
		}else{ 
		    paramMap.put("deviceType", "ANDROID");        		    
		}
		paramMap.put("ntfType", "ALLS"); //单设备推送
		mapOut.put("msgIntro", msgVo.getMsgTitle());//标题
		mapOut.put("description", msgVo.getMsgIntro());//简介
        paramMap.put("ntfText", mapOut);// 消息内容，json格式
//		paramMap.put("chlNo", "411");
//		paramMap.put("msgType", "1");
////		paramMap.put("messageType", msgVo.getMessageType());
//		paramMap.put("msgModelType", msgVo.getMsgModelType());
//		String deviceType = "";
//		//所有设备
//		if("01".equals(msgVo.getMsgModelType())){
//			deviceType = msgVo.getDeviceType();
//			if(StringUtil.isNotBlank(msgVo.getSendTm())){
//				paramMap.put("sendTime", msgVo.getSendTm());				
//			}
//		}
//		//指定设备ey
//		if("02".equals(msgVo.getMsgModelType())){
//			String userId = msgVo.getUserId().trim();
//			MchtUserVO mchtUserVO = new MchtUserVO();
//			try{
//				mchtUserVO = UserMngService.getInstance().queryInfoById(userId);				
//			}catch(Exception e){
//				SnowExceptionUtil.throwErrorException("用户信息获取异常",e);
//			}
//			paramMap.put("userId", userId);
//			deviceType = mchtUserVO.getDeviceType();
//			paramMap.put("channelId", mchtUserVO.getChannelId());
//		}
//		paramMap.put("deviceType", deviceType);
//		if(StringUtil.isNotBlank(msgVo.getMsgExpires())){
//			paramMap.put("msgExpires", msgVo.getMsgExpires());			
//		}
//		String message = "";
//		Gson gson = new Gson();
//		if("1".equals(deviceType)){//IOS
//			Map mapOut = new HashMap();
//			Map mapIn = new HashMap();
//			mapIn.put("alert", msgVo.getMsgTitle());
//			mapIn.put("sound", "");
//			mapIn.put("badge", "0");
////			String mapInJson = gson.toJson(mapIn);
//			mapOut.put("aps", mapIn);
//			mapOut.put("key1", "");
//			mapOut.put("key2", "");
//			message = gson.toJson(mapOut);
//		}else if("2".equals(deviceType)){//安卓
//			Map map = new HashMap();
//			map.put("title", msgVo.getMsgTitle());
//			map.put("description", msgVo.getMsgDesc());
//			map.put("notification_builder_id", "0");
//			map.put("notification_basic_style", "7");
//			map.put("openType", "0");
//			map.put("url", "http://developer.baidu.com");
//			map.put("pkg_content", "551115");
////			Map mapContent = new HashMap();
////			mapContent.put("key", "");
////			map.put("custom_content", gson.toJson(mapContent));
//			message = gson.toJson(map);
//		}
//		paramMap.put("message", message);
		return paramMap;
	}


	public void addMsgUserBaseInfo(List list, MsgBaseInfo msgBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(msgBaseInfo);
		for (int i = 0; i < list.size(); i++) {
			MssUserMsgRlt mssUserMsgRlt = new MssUserMsgRlt();
			MchtUserVO mchtUserVO = (MchtUserVO) list.get(i);
			Map map = new HashMap();
			map.put("msgId", msgBaseInfo.getMsgId());
			map.put("userId", mchtUserVO.getUserId());
			List list2 = queryUserMsgRltByKey(map);
			log.info("list2："+list2.size());
			if(list2.size()==0){
				mssUserMsgRlt.setMsgId(msgBaseInfo.getMsgId());
				mssUserMsgRlt.setUserId(mchtUserVO.getUserId());
				mssUserMsgRlt.setUserLev(mchtUserVO.getUserLevel());
				mssUserMsgRlt.setMsgStat("00");
				dao.insert(mssUserMsgRlt);				
			}
		}
	}


	private List queryUserMsgRltByKey(Map map) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.msgMng.queryUserMsgRltByKey", map);
	}
}
