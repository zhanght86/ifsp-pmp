/*
 * Created on 2010-08-02
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ruimin.ifs.mng.process.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.pool.ObjectPool;
import org.slf4j.Logger;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.communication.http.HttpConstances;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.constant.SnowConstants;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.core.WebConstants;
import com.ruimin.ifs.framework.core.thread.SnowWebThreadPoolFactory;
import com.ruimin.ifs.framework.dataset.DataSetBean;
import com.ruimin.ifs.framework.dataset.DataSetFactroy;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.framework.process.FieldValueProcess;
import com.ruimin.ifs.framework.process.exp.ExportPool;
import com.ruimin.ifs.framework.process.exp.IQueryExport;
import com.ruimin.ifs.framework.process.exp.IQueryExportService;
import com.ruimin.ifs.framework.process.exp.QueryExportForm;
import com.ruimin.ifs.framework.process.exp.bean.ExportBean;
import com.ruimin.ifs.framework.process.exp.csv.CSVExport;
import com.ruimin.ifs.framework.process.exp.xls.BatchExpTask;
import com.ruimin.ifs.framework.process.query.FieldData;
import com.ruimin.ifs.framework.process.query.QueryProcess;
import com.ruimin.ifs.framework.process.query.Result;
import com.ruimin.ifs.framework.process.query.RowData;
import com.ruimin.ifs.framework.session.SessionUtil;
import com.ruimin.ifs.framework.utils.DataDicUtil;
import com.ruimin.ifs.framework.utils.SysParamUtil;
import com.ruimin.ifs.po.TblExpTaskInfo;
import com.ruimin.ifs.rql.dao.Daos;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Service
public class QryExpService extends SnowService implements IQueryExportService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = SnowLog.getLogger(QryExpService.class);

	/**
	 * 删除任务信息
	 * 
	 * @param tskInf
	 * @throws SnowException
	 */
	public void delTsk(ExportBean tskInf) throws SnowException {
		Daos dao = DBDaos.newInstance();
		dao.delete(tskInf);
	}

	public void updTskInf(ExportBean tskInf) throws SnowException {
		Daos dao = DBDaos.newInstance();
		dao.update(tskInf);
	}

	public void expTskProcess(int procSize, String ownerName) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> tasks = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryExpTaskInfoForReadyExport");
		if (!tasks.isEmpty()) {
			logger.debug("######batch export task fetch " + tasks.size() + " to handle######");
			for (Object o : tasks) {
				ExportBean bean = (ExportBean) o;
				bean.setTskOwner(ownerName);
			}
			dao.executeBatchUpdate("com.ruimin.ifs.mng.rql.sysmng.updateExpTaskInfoForReadyExport", tasks);

			for (Object o : tasks) {
				ExportBean tskInf = (ExportBean) o;
				SnowWebThreadPoolFactory.getInstance().addTask(new BatchExpTask(tskInf));
			}
		}
		// // 获取准备执行的任务
		// List<Object> tskList =
		// dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryExpTaskInfoForReadyExportByOwner",
		// RqlParam.map().set("tskOwner", ownerName));
		// // 需放置到线程池中执行
		// for (Object o : tskList) {
		// ExportBean tskInf = (ExportBean) o;
		// SnowWebThreadPoolFactory.getInstance().addTask(new
		// BatchExpTask(tskInf));
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruimin.ifs.mng.service.AS#saveExpTskInf(java.util.Map,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public ExportBean saveExpTskInf(Map<String, String> params, String flowid, String flowctxid, String flowretid)
			throws SnowException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveExpTskInf( ) - start"); //$NON-NLS-1$
		}
		/** 进行参数检查 **/
		if (StringUtils.isBlank(flowid)) {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0044);
		}
		// 获取DAO处理对象，负责增删查改操作
		DBDao dao = DBDaos.newInstance();
		// 获取字段
		TblExpTaskInfo expTsk = new TblExpTaskInfo();
		// 执行类
		expTsk.setTskRunClass("01");
		// 任务结束时间
		expTsk.setTskEndTms(null);
		// 文件名
		expTsk.setExpFileNm((String) params.get(QueryExportForm.P_FILE_NAME));
		// 文件大小
		expTsk.setExpFileSize(0L);
		// 任务名称 CQID
		expTsk.setTskNm(params.get(WebConstants.DATASET_PATH));
		// 任务描述
		expTsk.setTskDesc(params.get(QueryExportForm.P_EXP_DESC));
		// 请求参数
		Iterator iter = params.keySet().iterator();
		StringBuffer paramStr = new StringBuffer();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object val = params.get(key);
			if (val instanceof String) {
				paramStr.append(key);
				paramStr.append(":");
				paramStr.append((String) val);
				paramStr.append(";");
			} else {
				System.out.println("can not process type =" + val.getClass().getName());
			}
		}
		// 压缩-编码
		String param = compressString(paramStr.toString());
		String[] paramList = splitStrWithLenLimit(param, 2000);
		if (paramList.length > 2)
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0045);
		if (paramList.length == 0)
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0046);

		expTsk.setTskParam1(paramList[0]);
		if (paramList.length == 2)
			expTsk.setTskParam2(paramList[1]);
		// System.out.println("paramStr="+paramStr.toString());

		// 记录数
		expTsk.setExpRcdNum(0L);
		// 发起人
		expTsk.setTskStartOp(params.get("tlrno"));
		// 开始时间
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String startTime = dataFormat.format(new Date());
		expTsk.setTskStartTms(startTime);
		// 状态
		expTsk.setTskStat(SnowConstants.BATCH_EXPORT_STATUS_NONE);
		// 主键ID
		dataFormat = new SimpleDateFormat("HHmmss");
		expTsk.setTskId(UUID.randomUUID().toString());
		// 将批量任务信息存入数据库
		dao.insert(expTsk);
		return expTsk;
	}

	/**
	 * 写文件头信息
	 *
	 * @throws SnowException
	 * @throws IOException
	 */
	public void wirtHead(String[] expClsId, Map<String, FieldBean> fieldsMap, IQueryExport export, String encoding,
			Map<String, String> userParams) throws SnowException, IOException {
		// List<String[]>
		List<String[]> colsStr = new ArrayList<String[]>();
		// 定义文件头信息
		String[] headDesc = new String[expClsId.length];
		for (int i = 0; i < expClsId.length; i++) {
			if (fieldsMap.containsKey(expClsId[i])) {
				FieldBean field = fieldsMap.get(expClsId[i]);
				String desc = field.getAnyValue("desc");
				// try {
				// headDesc[i] = new String(desc.getBytes(), encoding);
				// } catch (UnsupportedEncodingException e) {
				headDesc[i] = desc;
				// }
			} else {
				// 如果描述未找到，默认是字段名
				headDesc[i] = expClsId[i];
			}
		}
		if (userParams.containsKey("PageQryExp_title")) {
			colsStr.add(new String[] { "TITLE", String.valueOf(Math.max(headDesc.length - 1, 0)),
					(String) userParams.get("PageQryExp_title") });
		}
		colsStr.add(headDesc);
		// 将头信息写入文件
		export.writeHead(colsStr);
	}

	/**
	 * 写文件尾信息,未实现
	 * 
	 * @throws IOException
	 */
	public void wirtTail(String[] tailStr, LinkedHashMap<?, ?> fieldsMap, IQueryExport export, String encoding)
			throws SnowException, IOException {

	}

	/**
	 * 写入文件内容
	 *
	 * @throws SnowException
	 * @throws IOException
	 */
	public void wirtDetail(QueryExportForm form, DataSetBean commonQueryBean, IQueryExport export,
			Map<String, String> userParams) throws SnowException, IOException {
		// 需要导出的字段
		String[] fileIds = form.getColumnSort().split(",");
		// 开始页
		// int startPage = Integer.parseInt(form.getStartPage());
		int startPage = StringUtils.isBlank(form.getStartPage()) ? 1 : Integer.parseInt(form.getStartPage());
		// 结束页
		int endPage = Integer.parseInt(form.getEndPage());
		// 用户自定义的参数
		Map<String, String> otherParams = null;
		if (userParams != null && (!userParams.isEmpty())) {
			otherParams = userParams;
		} else {
			otherParams = new HashMap<String, String>();
		}

		// //////////////////////////////////////////////////// zhaozhiguo begin
		boolean hasMore = true;// 是否还有数据
		int everyPage = Integer.parseInt(form.getEveryPage());// XML中定义的pageSize
		long maxsize = everyPage * (endPage - startPage + 1L);// 最大可能下载记录数
		long sumsize = 0L;
		// 已导出笔数与导出的总笔数
		otherParams.put("exp_cur_num", "0");
		otherParams.put("exp_sum_num", "0");
		if (form.isExpAll()) {// 批量下载
			logger.info("=========批量导出========");
			String perfetch = otherParams.get("perfetch");
			otherParams.put(SnowConstants.PAGE_EVERYPAGE, perfetch);
			logger.debug("=========每页读取条数======" + perfetch);

		} else {
			logger.info("=========联机导出========");
			int maxpage = Integer.parseInt(form.getMaxpage());// 联机最大支持页
			endPage = Math.min(startPage + maxpage - 1, endPage);
			logger.debug("=========最大支持页======" + maxpage);
			logger.debug("=========实际导出页======" + endPage);

		}
		// ////////////////////////////////////////////////////zhaozhiguo end

		for (int currentPage = startPage; (endPage < 0 || currentPage <= endPage) && hasMore; currentPage++) {
			// 每次调用，设置不同的页码
			otherParams.put(SnowConstants.PAGE_NEXTPAGE, String.valueOf(currentPage));

			Result result = QueryProcess.getInstance().processCallForExporter(otherParams, form.getRequest(),
					form.getResponse());
			List<RowData> data = result.getData();
			// 如果取得的当前页数数据无空或小于分页大小,则说明已无数据
			if (data.isEmpty() || data.size() < everyPage) {
				hasMore = false;
			}
			List qryData = new ArrayList();
			for (int i = 0; i < data.size(); i++) {
				if (endPage > 0 && ++sumsize > maxsize) {
					hasMore = false;
					break;
				}
				// 获取行数据
				RowData row = data.get(i);
				String[] rowData = new String[fileIds.length];
				for (int j = 0; j < fileIds.length; j++) {
					FieldData field = row.getField(fileIds[j]);
					if (field == null) {
						rowData[j] = " ";
					} else {
						rowData[j] = field.toString();// new
														// String(field.toString().getBytes(),
														// form.getFileEnCode());
						FieldBean fBean = commonQueryBean.getField(fileIds[j]);
						String translated = fBean.getAnyValue("datasource");
						String paramMethod = fBean.getAnyValue(WebConstants.FIELD_METHOD);
						// XML配置中的method已在BaseCallGetterProcess.processSync中做完，如果translated不为空，则需要转换
						if (translated != null && translated.trim().length() > 0) {
							// 现只对LIST和DATA_DIC进行处理
							List args = new ArrayList();
							if (translated.toUpperCase().startsWith("LIST")) {
								// 例如：LIST:0,男;1,女
								String[] trans = translated.split(":");
								if (trans[1] != null) {
									String[] ops = trans[1].split(";");
									for (int x = 0; x < ops.length; x++) {
										if (ops[x] != null && (!"".equals(ops[x].trim()))) {
											String[] vals = ops[x].split(",");
											if (vals.length == 2) {
												if (vals[0].trim().equalsIgnoreCase(rowData[j])) {
													rowData[j] = vals[1].trim();
												}
											}
										}
									}
								}
							} else if (translated.toUpperCase().startsWith("DSCQ")) {
								// 先不处理
							} else {
								// 目前DATA_DIC写死的
								if (translated.toUpperCase().startsWith("DDIC")) {
									String[] trans = translated.split(":");
									String translatedValue = DataDicUtil.getInstance().getDataValue(trans[1],
											rowData[j]);
									if (translatedValue != null) {
										rowData[j] = translatedValue;
									}
								}

							}
						}

						// 对method 方法起作用
						if (paramMethod != null && paramMethod.trim().length() != 0
								&& !paramMethod.equalsIgnoreCase(WebConstants.ELEMENT_METHOD_NONE)) {
							rowData[j] = FieldValueProcess.process(form.getRequest(), fBean, rowData[j]);
						}
					}
					if (export instanceof CSVExport) {
						FieldBean fBean = commonQueryBean.getField(fileIds[j]);
						String dataType = fBean.getAnyValue("datatype");
						if (StringUtils.isEmpty(dataType) || StringUtils.equalsIgnoreCase(dataType, "string")) {
							if (StringUtils.isNotBlank(rowData[j]) && NumberUtils.isNumber(rowData[j])
									&& !Character.isLetter(rowData[j].charAt(rowData[j].length() - 1))) {
								BigDecimal bd = NumberUtils.createBigDecimal(rowData[j]).abs();
								if (bd.scale() > 6 || bd.precision() > 11) {
									rowData[j] = "=\"" + rowData[j] + "\"";
								} else {
									// ignore;
								}
							} else {
								// ignore;
							}
						} else {
							// ignore
						}
					} else {
						// ignore
					}
				}
				// 向结果中添加转换后的行数据
				qryData.add(rowData);
			}
			// 将数据写入输出流
			export.writeDetails(qryData);
			String num = String.valueOf(Long.valueOf((String) otherParams.get("exp_cur_num")) + qryData.size());
			otherParams.put("exp_cur_num", num);
			otherParams.put("exp_sum_num", num);
		}
	}

	public void genExportBatch(ExportBean tskInf) {
		// 文件输出流
		FileOutputStream out=null;
		try {
			tskInf.setTskStat(SnowConstants.BATCH_EXPORT_STATUS_RUNNING);// 先设置为正在执行
			this.updTskInf(tskInf);

			MockHttpServletRequest request = new MockHttpServletRequest();
			MockHttpServletResponse response = new MockHttpServletResponse();

			QueryExportForm expForm = new QueryExportForm(request, response);
			// 恢复参数，将String分解，转换成键值对
			Map<String, String> params = new HashMap<String, String>();
			// 拼接串
			String paramStr = tskInf.getTskParam1() + (tskInf.getTskParam2() == null ? "" : tskInf.getTskParam2());

			// 解压缩-解码
			paramStr = decompressString(paramStr);

			String[] para = paramStr.split(";");
			for (int i = 0; i < para.length; i++) {
				if (para[i] != null && (para[i].indexOf(':') != -1)) {
					String[] p = para[i].split(":");
					if (p.length == 2) {
						params.put(p[0].trim().trim(), p[1].trim().trim());
					} else if (p.length == 1) {
						params.put(p[0].trim().trim(), "");
					}
				}

			}

			// 构造虚拟的GlobalInfo
			SessionUserInfo userinfo = SessionUserInfo.newInstance();
			userinfo.setTlrno(params.get("global_userid"));
			userinfo.setBrCode(params.get("global_brcode"));
			userinfo.setBrno(params.get("global_brhid"));
			userinfo.setBrClass(params.get("global_brhclass"));
			HttpSession session = request.getSession();
			userinfo.setSessionId(session.getId());
			SessionUtil.setSessionUserInfo(session, userinfo);

			// CQID
			String cqId = params.get(WebConstants.DATASET_NAME);
			expForm.setDsName(cqId.trim());
			expForm.setDsPath(params.get(WebConstants.DATASET_PATH));
			// 文件类型
			String fileType = (String) params.get(cqId + "_" + QueryExportForm.P_EXP_TYPE);
			if (fileType == null) {// 参数应该是expType
				fileType = (String) params.get(QueryExportForm.P_EXP_TYPE);
			}
			if (fileType == null) {
				fileType = "CSV";
			} else {
				fileType = fileType.toUpperCase();
			}
			// 下载文件是否需要压缩，默认不需要压缩
			expForm.setZipFlag(false);
			String zip = (String) params.get(cqId + "_" + QueryExportForm.P_ZIP_FLAG);
			if (zip != null && (!"".equals(zip))) {
				if ("1".equals(zip.trim())) {
					expForm.setZipFlag(true);
				}
			}
			// 导出字段的顺序
			String els = (String) params.get(cqId + "_" + QueryExportForm.P_COL_SORT_NAME);
			expForm.setColumnSort(els);
			// 批量下载
			expForm.setExpAll(true);
			expForm.setExportType(fileType);
			// 开始页码
			String startPage = (String) params.get(cqId + "_" + QueryExportForm.P_START_PAGE);
			expForm.setStartPage(startPage);
			// 结束页码
			String endPage = (String) params.get(cqId + "_" + QueryExportForm.P_END_PAGE);
			expForm.setEndPage(endPage);

			// zhaozhiguo每页大小
			String everyPage = (String) params.get(SnowConstants.PAGE_EVERYPAGE);
			expForm.setEveryPage(everyPage);

			// 文件名，包含完整的路径
			String fileName = tskInf.getExpFileNm();

			File file = new File(fileName);
			String folderPath = file.getParent();
			File dirFile = new File(folderPath);
			if (!dirFile.isDirectory() || !dirFile.exists()) {
				try {
					FileUtils.forceMkdir(dirFile);
				} catch (IOException ioex) {
					throw new RuntimeException("create unmatch info diractory : [" + folderPath + "] error", ioex);
				}
			}

			
			out = new FileOutputStream(fileName);
			logger.info("######batch export filepath: " + fileName);
			expForm.setFileName(fileName);

			// 标志导出是后台执行,不记录系统日志TBL_CS_BIZ_LOG
			params.put("DO_CS_BIZ_LOG", "false");// FIXME 在查询的通用处理中需要判断该标识
			// 查询结果导出处理
			genExport(expForm, out, params);
			// 更改批量任务的状态为已完成 0:初始状态 1:准备执行 2:正在执行 3:任务完成 4执行失败
			tskInf.setTskStat(SnowConstants.BATCH_EXPORT_STATUS_SUCCESS);

			tskInf.setExpFileSize(file.length());
			tskInf.setExpRcdNum(Long.valueOf(params.get("exp_cur_num")));
			tskInf.setExpRcdSumNum(Long.valueOf(params.get("exp_sum_num")));
		} catch (Exception e) {
			logger.error("e", e);
			tskInf.setTskStat(SnowConstants.BATCH_EXPORT_STATUS_FAILED);
		} finally {
			// 结束时间
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String endTime = dataFormat.format(new Date());
			tskInf.setTskEndTms(endTime);
			try {
				if(out != null){
					out.close();
				}
				this.updTskInf(tskInf);
			} catch (SnowException e) {
				e.printStackTrace();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
	}

	public void genExport(QueryExportForm form, OutputStream outStream, Map<String, String> userParams)
			throws SnowException {
		// 对象缓冲池
		ObjectPool pool = null;
		IQueryExport export = null;
		// 导出的字段信息
		String[] sortFields = null;
		// 文件输出流
		OutputStream out = outStream;
		try {
			HttpServletResponse response = form.getResponse();
			HttpServletRequest request = form.getRequest();
			// CommonQueryBean
			DataSetBean commonQueryBean = DataSetFactroy.getInstance().getDataSetBean(form.getDsPath());
			// 设置输出编码
			String encoding = SysParamUtil.getInstance().getString("EXP.ENCODING", "utf-8");
			form.setFileEnCode(encoding);
			// 取得配置文件中的字段信息
			Map<String, FieldBean> fieldsMap = commonQueryBean.getFieldMap();
			// 判断生成类型文件、编码
			String type = getAnyValueDefault(form.getExportType(), "csv").toLowerCase();

			// 设置联机最大支持页数
			int expmaxrcd = commonQueryBean.getExpmaxrcd();
			form.setMaxpage(expmaxrcd + "");

			// 输出流，默认取response中的输出流
			if (out == null) {
				out = response.getOutputStream();
			}
			// 下载文件是否需要压缩
			if (form.isZipFlag()) {
				// 压缩文件response头部信息
				response.setContentType("application/zip; " + "charset=" + form.getFileEnCode().toUpperCase());
				// 支持Https
				response.setHeader("Pragma", "public");
				String disFileName = getAnyValueDefault(form.getFileName(), "qryExpZip");
				String userAgent = request.getHeader(HttpConstances.HTTP_User_Agent);

				String retFileName = disFileName;
				if (StringUtils.isNotBlank(userAgent) && userAgent.toLowerCase().indexOf("firefox") > 0) {
					retFileName = new String(disFileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
				} else {
					retFileName = URLEncoder.encode(disFileName, "UTF-8");
					if (retFileName.length() > 150) {// 解决IE 6.0 bug
						retFileName = new String(disFileName.getBytes("GB2312"), "ISO8859-1");
					}
				}

				// disFileName = new String(disFileName.getBytes("UTF-8"),
				// "8859_1");
				response.setHeader("Content-disposition", "attachment;filename=" + retFileName);
				out = new ZipOutputStream(out);
				String fileName = getAnyValueDefault(form.getFileName(), "downloadFile");
				// Zip后的文件名中文乱码,写死压缩的文件名
				fileName = "downloadFile";
				((ZipOutputStream) out).putNextEntry(new ZipEntry(fileName + "." + type));
			} else {
				if ("csv".equalsIgnoreCase(type) && "utf-8".equalsIgnoreCase(System.getProperty("file.encoding"))) {// csv
																													// utf-8乱码解决：加BOM头
					out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
				}

				// 非压缩文件response头部信息
				response.setContentType("application/" + type + "; " + "charset=" + form.getFileEnCode().toUpperCase());
				// 支持Https
				response.setHeader("Pragma", "public");
				String disFileName = getAnyValueDefault(form.getFileName(), "qryExpZip");

				String userAgent = request.getHeader(HttpConstances.HTTP_User_Agent);

				String retFileName = disFileName;
				if (StringUtils.isNotBlank(userAgent) && userAgent.toLowerCase().indexOf("firefox") > 0) {
					retFileName = new String(disFileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
				} else {
					retFileName = URLEncoder.encode(disFileName, "UTF-8");
					if (retFileName.length() > 150) {// 解决IE 6.0 bug
						retFileName = new String(disFileName.getBytes("GB2312"), "ISO8859-1");
					}
				}

				// disFileName = new String(disFileName.getBytes("UTF-8"),
				// "8859_1");
				response.setHeader("Content-disposition", "attachment;filename=" + retFileName);
			}
			/* 设置IE特殊的扩展头 保证可以直接打开 */
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			// 根据文件下载类型获取连接池
			pool = ExportPool.getExportPool(type.toUpperCase());
			// 获取导出对象
			export = (IQueryExport) (pool.borrowObject());
			// 设置输出流
			export.setOutPutStream(out);

			// 输出字段的排序和选择的限制
			String columnSort = form.getColumnSort();
			// 没有字段，默认展现值和Fields 一样
			if (columnSort == null || columnSort.length() < 1) {
				columnSort = commonQueryBean.toFieldString();
				form.setColumnSort(columnSort);
			}
			sortFields = columnSort.split(",");
			// ------写文件头信息
			wirtHead(sortFields, fieldsMap, export, form.getFileEnCode(), userParams);
			// 写文件体
			wirtDetail(form, commonQueryBean, export, userParams);
			// TODO 写文件尾， 未实现
			wirtTail(null, null, null, form.getFileEnCode());
			// 手动关闭 关闭输出流- 清理对象
			export.clear();
		} catch (Exception e) {
			throw SnowExceptionUtil.wrapException(e);
		} finally {
			try {
			       if (out != null) {  
		               try {  
		                   out.close(); // 关闭流  
		               } catch (IOException e) {  
		                   e.printStackTrace();
		               }  
		           }  
				if (null != export) {
					// 返回使用的对象到连接池中
					pool.returnObject(export);
				}
			} catch (Exception e) {
				// ignored
			    e.printStackTrace();
			}

		}
	}

	/**
	 * 字符串处理
	 *
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String getAnyValueDefault(String value, String defaultValue) {

		if (value != null && !"".equals(value)) {
			return value;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 拆分字符串为几个子串，每个字串的字节长不能超过maxLen
	 * 
	 * @param inputStr
	 *            出入值
	 * @param maxLen
	 *            每个拆分后的值最大长度(字节长)
	 * @return
	 */
	public String[] splitStrWithLenLimit(String inputStr, int maxLen) {
		int byteLen = maxLen / 2;//
		if (byteLen == 0)
			byteLen = 1;//

		int inputLen = inputStr.length();
		int dealNum = inputLen % byteLen == 0 ? inputLen / byteLen : inputLen / byteLen + 1;

		String[] resultArray = new String[dealNum];// return array
		for (int index = 0; index < dealNum - 1; index++) {
			resultArray[index] = inputStr.substring(byteLen * index, byteLen * (index + 1));
		}

		resultArray[dealNum - 1] = inputStr.substring(byteLen * (dealNum - 1), inputStr.length());// last
																									// one

		return resultArray;
	}

	private String compressString(String input) {
		ByteArrayOutputStream baos = null;
		GZIPOutputStream gzip = null;
		try {
			baos = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(baos);

			gzip.write(input.getBytes());
			gzip.finish();
			byte[] bytes = baos.toByteArray();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(bytes);
		} catch (IOException e) {
			return input;
		} finally {
			try {
				if (gzip != null)
					gzip.close();
				if (baos != null)
					baos.close();
			} catch (IOException e) {
			}
		}
	}

	private String decompressString(String input) {
		BASE64Decoder decoder = new BASE64Decoder();
		ByteArrayInputStream bais = null;
		GZIPInputStream gzip = null;
		ByteArrayOutputStream baos = null;
		try {
			bais = new ByteArrayInputStream(decoder.decodeBuffer(input));
			gzip = new GZIPInputStream(bais);
			baos = new ByteArrayOutputStream();

			byte[] buf = new byte[1024];
			int num = -1;
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			byte[] bytes = baos.toByteArray();
			return new String(bytes);
		} catch (IOException e) {
			return input;
		} finally {
			try {
				if (gzip != null)
					gzip.close();
				if (baos != null)
					baos.close();
			} catch (IOException e) {
			}
		}
	}

}
