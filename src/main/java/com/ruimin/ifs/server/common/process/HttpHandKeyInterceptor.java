package com.ruimin.ifs.server.common.process;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.ruimin.ifs.core.communication.http.HttpRequestMessage;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.channel.IChannelMsgKeyIntercept;
import com.ruimin.ifs.core.iface.action.channel.ICommunReceivedMessage;
import com.ruimin.ifs.core.iface.action.channel.impl.CommunReceivedHttpReq;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;

/**
 * 从http请求头截取渠道号交易码
 * 
 * @author pengning
 * @date 2015-6-11 下午2:58:18
 * @Description
 */
public class HttpHandKeyInterceptor implements IChannelMsgKeyIntercept {

	@Override
	public String channelKeyIntercept(ICommunReceivedMessage receivedMsg, String param) throws SnowException {
		String channel = null;
		if (receivedMsg instanceof CommunReceivedHttpReq) {
			CommunReceivedHttpReq httpreq = (CommunReceivedHttpReq) receivedMsg;
			HttpRequestMessage httpReqMsg = httpreq.getValue();
			channel = httpReqMsg.getHanderValue(param);
			if (StringUtils.isBlank(channel)) {// 渠道号为空,从报文获取
				Gson gson = new Gson();
				Map jsonMap = gson.fromJson(httpReqMsg.getContent(), Map.class);
				if (jsonMap.containsKey(param)) {
					channel = (String) jsonMap.get(param);
				}
			}
		}
		if (StringUtils.isBlank(channel)) {
			SnowLog.getServerLog().warn("channel is null!");
		}
		return channel;
	}

	@Override
	public String transactionCodeKeyIntercept(ICommunReceivedMessage receivedMsg, String param) throws SnowException {
		String tranCode = null;
		if (receivedMsg instanceof CommunReceivedHttpReq) {
			CommunReceivedHttpReq httpreq = (CommunReceivedHttpReq) receivedMsg;
			HttpRequestMessage httpReqMsg = httpreq.getValue();
			tranCode = httpReqMsg.getHanderValue(param);
			if (StringUtils.isBlank(tranCode)) {// 渠道号为空,从报文获取
				Gson gson = new Gson();
				Map jsonMap = gson.fromJson(httpReqMsg.getContent(), Map.class);
				if (jsonMap.containsKey(param)) {
					tranCode = (String) jsonMap.get(param);
				}
			}
		}
		if (StringUtils.isBlank(tranCode)) {
			SnowLog.getServerLog().warn("tranCode is null!");
		}
		return tranCode;
	}

}
