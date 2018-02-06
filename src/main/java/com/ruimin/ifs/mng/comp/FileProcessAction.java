package com.ruimin.ifs.mng.comp;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.ruimin.ifs.channel.server.http.bean.HttpAttrBean;
import com.ruimin.ifs.channel.server.http.handler.DefUploadFileProcess;
import com.ruimin.ifs.channel.server.servlet.bean.ServletUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.common.bean.DownLoadFileBean;
import com.ruimin.ifs.core.common.bean.UploadFilebean;
import com.ruimin.ifs.core.common.core.SnowApi;
import com.ruimin.ifs.core.communication.http.HttpRequestMessage;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;
import com.ruimin.ifs.framework.dataobject.FormReturnBean;
import com.ruimin.ifs.util.SnowConstant;

@ActionResource
@SnowDoc(desc = "上传处理")
public class FileProcessAction extends SnowAction {

	@Action
	@SnowDoc(desc = "文件导入处理")
	public FormReturnBean processUploadFile(FormRequestBean requestBean) {
		FormReturnBean retBean = null;
		HttpServletRequest request = requestBean.getRequest();
		String type = requestBean.getParameter("type");
		HttpAttrBean attrbean = HttpAttrBean.getHttpAttrBean();
		attrbean.setCharset("utf-8");
		attrbean.setUploadFileProcess(new DefUploadFileProcess());
		// 对文件进行处理(默认处理上传文件接口，保存至临时文件夹,可重写接口，对文件bytes进行直接处理，不保存）
		HttpRequestMessage reqMsg = null;
		try {
			reqMsg = ServletUtil.httpServerRequestToFsRequest(request, attrbean);

			if (StringUtils.isNotBlank(type)) {
				if (type.equalsIgnoreCase(SnowConstant.FILE_ORG_LST)) {// 机构批量上传
					retBean = SnowApi.getInstance().execAction(BctlAllSelectAction.class, "importOrgLst",
							new Object[] { reqMsg.getFileMap() }, FormReturnBean.class);
				}
				if (type.equalsIgnoreCase(SnowConstant.FILE_CITY_LST)) {// 地区批量上传
					retBean = SnowApi.getInstance().execAction(CityAllSelectAction.class, "importCityLst",
							new Object[] { reqMsg.getFileMap() }, FormReturnBean.class);
				}
				if (type.equalsIgnoreCase(SnowConstant.FILE_HOLIDAY_LST)) {// 节假日批量上传
					retBean = SnowApi.getInstance().execAction(HolidayAction.class, "importHolidayLst",
							new Object[] { reqMsg.getFileMap() }, FormReturnBean.class);
				}

			}
			if (retBean == null) {
				SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0005, new String[] { "无法处理上传文件！" });
			}

		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
			retBean = new FormReturnBean();
			retBean.setSendUrl("/pages/mng/fileUpload.jsp");
			retBean.getReturnAttrMap().put("result", false);
			List<String> tlist = new ArrayList<String>();
			tlist.add(e.getMessage() == null || e.getMessage().length() == 0 ? "执行导入异常！" : e.getMessage());
			retBean.getReturnAttrMap().put("resultMsg", tlist);
		} finally {
			// 删除临时文件
			try {
				if (reqMsg != null && reqMsg.getFileMap().size() > 0) {
					for (Entry<String, UploadFilebean> entry : reqMsg.getFileMap().entrySet()) {
						UploadFilebean uf = entry.getValue();
						if (uf.isFile()) {
							File f = new File(uf.getContentMsg());
							if (f.isFile() && f.exists()) {
								f.delete();
							}
						}
					}
				}
			} catch (Exception e) {
				getLogger().warn("删除临时文件异常:" + e.getMessage());
			}
		}
		return retBean;
	}

	/*
	 * @Action
	 * 
	 * @SnowDoc(desc = "批量导出生成文件的下载") public DownLoadFileBean
	 * processDownLoadFile(FormRequestBean queryBean) throws SnowException {
	 * String rootPath = queryBean.getRequest().getServletContext().getRealPath(
	 * "/WEB-INF/templets");
	 * 
	 * getLogger().debug("root path:"+rootPath);
	 * 
	 * String type = queryBean.getParameter("type"); DownLoadFileBean bean = new
	 * DownLoadFileBean(); if
	 * (type.equalsIgnoreCase(SnowConstant.FILE_PROC_FUSE_LST)) {
	 * bean.setFileName("熔断名单_"+DateFormatUtils.format(new Date(), "yyMMdd"));
	 * bean.setFilePath(this.searchFile(rootPath, "templet_fuselst.xlsx")); } //
	 * if (type.equalsIgnoreCase(SnowConstant.FILE_PROC_LNBL)) { //
	 * bean.setFileName("黑名单_"+DateFormatUtils.format(new Date(), "yyMMdd")); //
	 * bean.setFilePath(this.searchFile(rootPath, "templet_lnbl.xlsx")); //
	 * }else if (type.equalsIgnoreCase(SnowConstant.FILE_PROC_FUSE_PARAM)) { //
	 * bean.setFileName("熔断参数_"+DateFormatUtils.format(new Date(), "yyMMdd"));
	 * // bean.setFilePath(this.searchFile(rootPath, "templet_fuseparam.xlsx"));
	 * // } return bean; }
	 */

	private String searchFile(String rootPath, final String fileName) {
		getLogger().info("download templetes:" + fileName);
		File file = new File(rootPath, fileName);
		if (file.isFile() && file.exists()) {
			return file.getAbsolutePath();
		} else {
			File fd = new File(rootPath);
			File[] fs = fd.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File arg0, String arg1) {
					if (arg1.equals(fileName)) {
						return true;
					}
					return false;
				}
			});
			if (fs != null && fs.length > 0) {
				return fs[0].getAbsolutePath();
			}
		}
		return null;
	}

}
