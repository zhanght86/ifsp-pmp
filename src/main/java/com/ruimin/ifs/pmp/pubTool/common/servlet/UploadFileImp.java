/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: UploadFileImp.java
 * Author:   GH
 * Date:     2015年9月1日 下午2:57:59
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.common.servlet;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 〈方法简述 - 方法描述〉
 * 
 * @author GH
 */
public interface UploadFileImp {

	/**
	 * 校验文件夹是否存在
	 * 
	 * @return boolean
	 */
	public boolean isFileExist(File dir);

	/**
	 * 得到上传文件集合
	 */
	public List getFileList();

	/**
	 * 获取存放目录
	 */
	public String getDiskPath(HttpServletRequest request);

}
