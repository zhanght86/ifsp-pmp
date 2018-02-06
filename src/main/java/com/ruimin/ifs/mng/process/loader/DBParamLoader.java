package com.ruimin.ifs.mng.process.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.global.GlobalUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.bean.IParamLoader;
import com.ruimin.ifs.framework.core.bean.SysParam;
import com.ruimin.ifs.po.TblSysParam;
import com.ruimin.ifs.rql.dao.Daos;

public class DBParamLoader implements IParamLoader {

	private Map<String, Map<String, String>> params = null;

	public DBParamLoader() {
		init();
	}

	private void init() {
		this.params = new HashMap<String, Map<String, String>>();
		Daos dao = DBDaos.newInstance();
		List<? extends SysParam> params = new ArrayList<SysParam>();
		try {
			params = dao.selectAll(TblSysParam.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (SysParam param : params) {
			String group = param.group();
			if (group == null) {
				continue;
			}
			Map<String, String> submap = this.params.get(group);
			if (submap == null) {
				submap = new HashMap<String, String>();
				this.params.put(group, submap);
			}
			String key = param.key();
			if (key != null)
				submap.put(key, param.value());
		}
	}

	@Override
	public Map<String, Map<String, String>> get() {
		if (this.params == null || isCluster() || !GlobalUtil.isProductMode()) {
			init();
		}
		return this.params;
	}

	@Override
	public Map<String, String> get(String group) {
		if (isCluster() || !GlobalUtil.isProductMode()) {
			DBDao dao = DBDaos.newInstance();
			List list = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.querySysParamByGroup",
					RqlParam.map().set("group", group));
			Map<String, String> map = new HashMap<String, String>();
			for (Object o : list) {
				SysParam param = (SysParam) o;
				map.put(param.group(), param.key());
			}
			return map;
		} else {
			return this.params.get(group);
		}

	}

	@Override
	public String get(String group, String key) {
		if (group == null || key == null) {
			return null;
		}
		if (isCluster() || !GlobalUtil.isProductMode()) {
			DBDao dao = DBDaos.newInstance();
			List list = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.querySysParamByGroupAndKey",
					RqlParam.map().set("group", group).set("key", key));
			if (!list.isEmpty()) {
				SysParam param = (SysParam) list.get(0);
				return param.value();
			}
			return null;
		} else {
			Map<String, String> params = get(group);
			if (params == null) {
				return null;
			}
			return params.get(key);
		}
	}

	private boolean isCluster() {
		Map<String, String> params = this.params.get("SYS");
		return params != null && "1".equalsIgnoreCase(params.get("CLUSTER_FLAG"));
	}

}
