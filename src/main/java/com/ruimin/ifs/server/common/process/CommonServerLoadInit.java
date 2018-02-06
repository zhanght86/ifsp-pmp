package com.ruimin.ifs.server.common.process;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.server.IServerLoadInit;
import com.ruimin.ifs.core.iface.xml.ProcDocument;
import com.ruimin.ifs.core.server.bean.ServerBean;
import com.ruimin.ifs.core.util.xml.XMLUtil;

/**
 * 进行处理组件路由信息初始化
 * 
 * @author pengning
 * @date 2015-6-24 下午4:49:26
 * @Description
 */
public class CommonServerLoadInit implements IServerLoadInit {

	private static final String PROC_CONF = "/com/ruimin/ifs/server/common/resource/route/httpchannel_route.xml";

	@Override
	public void serverStart(ServerBean arg0) throws SnowException {
		Map<String, String> procMap = XMLUtil.parseXmlConf(PROC_CONF, new ProcDocument<Map<String, String>>() {

			@Override
			public Map<String, String> processXmlDoucment(Document doc) throws SnowException {
				Map<String, String> retMap = new HashMap<String, String>();

				Node procNode = XMLUtil.findElementNode(doc, "routes");
				if (XMLUtil.isElementNode(procNode)) {
					NodeList list = procNode.getChildNodes();
					for (int i = 0; i < list.getLength(); i++) {
						Node pNode = list.item(i);
						if (XMLUtil.isElementNode(pNode)) {
							String channel = XMLUtil.getNodeAttr(pNode, "channel");
							String transactionCode = XMLUtil.getNodeAttr(pNode, "transactionCode");
							String actionId = XMLUtil.getNodeAttr(pNode, "actionId");
							if (StringUtils.isNotBlank(actionId)) {
								retMap.put(channel + "_" + transactionCode, actionId);
							}
						}
					}
				}
				return retMap;
			}
		});

		ProcConfUtil.setProcMap(procMap);
	}

	@Override
	public void serverStop(ServerBean arg0) throws SnowException {
		if (ProcConfUtil.getProcMap() != null) {
			ProcConfUtil.setProcMap(null);
		}

	}

}
