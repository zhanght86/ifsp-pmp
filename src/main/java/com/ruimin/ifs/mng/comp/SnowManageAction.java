package com.ruimin.ifs.mng.comp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.util.file.DirectoryUtil;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;

@ActionResource
public class SnowManageAction {

	private static List<Object[]> icons = null;

	@Action
	@SnowDoc(desc = "查询所有图标")
	public List<Object[]> listIcons(QueryParamBean queryBean) throws SnowException {
		String iconName = queryBean.getParameter("qName");
		String iconCssFile = queryBean.getParameter("iconCssFile");
		String cache = queryBean.getParameter("cache");
		if ((icons == null || "false".equalsIgnoreCase(cache)) && StringUtils.isNotBlank(iconCssFile)) {
			String rootpath = DirectoryUtil.getProjectRootPath();
			int index = rootpath.indexOf("/WEB-INF/");
			String path = rootpath.substring(0, index);
			try {
				String css = FileUtils.readFileToString(new File(path + iconCssFile));
				icons = new ArrayList<Object[]>();
				Matcher m = Pattern.compile("(fa-(\\w+)):before").matcher(css);
				int i = 0;
				while (m.find()) {
					icons.add(new Object[] { ++i, m.group(2), "fa " + m.group(1) });
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Object[]> filteredList = null;
		if (StringUtils.isBlank(iconName)) {
			filteredList = icons;
		} else {
			filteredList = new ArrayList<Object[]>();
			for (Object[] obj : icons) {
				String name = (String) obj[1];
				if (name.indexOf(iconName) > -1) {
					filteredList.add(obj);
				}
			}
		}

		return filteredList;
	}

}
