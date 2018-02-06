/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: BaseUploadServlet.java
 * Author:   GH
 * Date:     2015年9月1日 下午2:50:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.common.servlet;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.ruimin.ifs.core.log.SnowLog;

/**
 * 〈一句话功能简述〉<br>
 * 
 * @author GH
 */
public class BaseUploadServlet extends HttpServlet implements UploadFileImp {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5322172669681581498L;

	/** 日志 */
	protected Logger log = SnowLog.getServerLog();

	/** 1MB */
	public final static int ONE_MB = 1024 * 1024;

	/** 默认: 单个文件最大大小 */
	public final int default_singleFileMaxSize = 2;
	/** 默认: 单次上传文件最大大小 */
	public final int default_onceFileMaxSize = 50;
	/** 默认: 单次上传文件最大数 */
	public final int default_onceFileMaxAmount = 10;
	/** 默认: 单次上传文件最大数 */
	public final String[] default_legalFileSuffixes = { "jpg", "jpeg", "gif", "png", "bmp" };

	/** 单个文件最大大小 */
	public int singleFileMaxSize;
	/** 单次上传文件最大大小 */
	public int onceFileMaxSize;
	/** 单次上传文件最大数 */
	public int onceFileMaxAmount;
	/** 允许上传的文件格式 */
	public String[] legalFileSuffixes;

	/** 文件临时存放目录 */
	protected File tmpDir;
	/** 文件目标存放目录 */
	File tarDir;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruimin.ifs.pmp.common.servlet.UploadFileImp#isExist(java.io.File)
	 */
	@Override
	public boolean isFileExist(File dir) {
		if (dir == null) {
			throw new NullPointerException("this dir parameter is null.");
		}
		if (dir.exists()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruimin.ifs.pmp.common.servlet.UploadFileImp#getFileList()
	 */
	@Override
	public List getFileList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDiskPath(HttpServletRequest request) {
		return request.getParameter("DISK_PATH");
	}

}
