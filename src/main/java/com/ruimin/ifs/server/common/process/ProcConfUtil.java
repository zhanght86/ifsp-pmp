package com.ruimin.ifs.server.common.process;

import java.util.Map;

public class ProcConfUtil {
	private static Map<String, String> procMap = null;

	public static void setProcMap(Map<String, String> map) {
		procMap = map;
	}

	public static Map<String, String> getProcMap() {
		return procMap;
	}

	public static String getActionId(String channel, String tranCode) {
		String key = channel + "_" + tranCode;
		if (procMap != null && procMap.containsKey(key)) {
			return procMap.get(key);
		}
		return null;
	}

	public static String getActionId(String insideCode) {
		if (procMap != null && procMap.containsKey(insideCode)) {
			return procMap.get(insideCode);
		}
		return null;
	}
}
