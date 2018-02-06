package com.ruimin.ifs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.framework.core.bean.Function;

public class FunctionUtil {
	/**
	 * 对function map按照层级关系和显示顺序排序后返回列表
	 */
	public static List<Function> sortFunctions(Map<String, Function> functions) {
		List<Function> firstLevelFunctions = new ArrayList<Function>();
		List<Function> directoryFunctions = new ArrayList<Function>();
		for (Function func : functions.values()) {
			func.children().clear();
			if ("1".equals(func.getFuncType())) {
				continue;
			}
			if (!functions.containsKey(func.getLastdirectory())) {
				firstLevelFunctions.add(func);
			}
		}
		Collections.sort(firstLevelFunctions, new Comparator<Function>() {
			public int compare(Function f1, Function f2) {
				return f1.getShowseq() - f2.getShowseq();
			}
		});

		for (Function func : functions.values()) {
			if ("1".equals(func.getFuncType())) {
				continue;
			}
			String pid = func.getLastdirectory();
			if (functions.containsKey(pid)) {
				functions.get(pid).addChild(func);
				if (!firstLevelFunctions.contains(functions.get(pid))) {
					directoryFunctions.add(functions.get(pid));
				}
			}

		}
		for (Function func : firstLevelFunctions) {
			Collections.sort(functions.get(func.getFuncid()).children(), new Comparator<Function>() {
				public int compare(Function f1, Function f2) {
					return f1.getShowseq() - f2.getShowseq();
				}
			});
		}
		for (Function func : directoryFunctions) {
			Collections.sort(functions.get(func.getFuncid()).children(), new Comparator<Function>() {
				public int compare(Function f1, Function f2) {
					return f1.getShowseq() - f2.getShowseq();
				}
			});
		}
		return firstLevelFunctions;
	}

}
