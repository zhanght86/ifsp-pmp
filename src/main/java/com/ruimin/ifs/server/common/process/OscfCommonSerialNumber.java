package com.ruimin.ifs.server.common.process;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.ruimin.ifs.core.common.bean.SystemDataObject;
import com.ruimin.ifs.core.communication.http.HttpRequestMessage;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.channel.IChannelSerialNumber;
import com.ruimin.ifs.core.iface.action.channel.impl.CommunReceivedHttpReq;
import com.ruimin.ifs.system.channel.serialnum.UUIDChannelSerialNumber;

public class OscfCommonSerialNumber implements IChannelSerialNumber {

	@Override
	public String generateSerialNumber(SystemDataObject systemData) {
		String serNum = null;
		CommunReceivedHttpReq recMsg = (CommunReceivedHttpReq) systemData.getReceivedMsg();
		HttpRequestMessage reqMsg = recMsg.getValue();
		serNum = reqMsg.getHanderValue("_tn");
		if (StringUtils.isBlank(serNum)) {
			Gson gson = new Gson();
			Map jsonMap;
			try {
				jsonMap = gson.fromJson(reqMsg.getContent(), Map.class);
				if (jsonMap.containsKey("_tn")) {
					serNum = (String) jsonMap.get("_tn");
				}
			} catch (SnowException e) {
				serNum = new UUIDChannelSerialNumber().generateSerialNumber(systemData);
			}
			if (StringUtils.isBlank(serNum)) {
				serNum = new UUIDChannelSerialNumber().generateSerialNumber(systemData);
			}
		}
		return serNum;

	}

}
