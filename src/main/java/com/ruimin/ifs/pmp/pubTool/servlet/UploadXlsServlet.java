package com.ruimin.ifs.pmp.pubTool.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;

import com.ruimin.ifs.core.common.core.SnowApi;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.mng.common.constants.OrgManagerConstants;
import com.ruimin.ifs.mng.process.service.OrgService;
import com.ruimin.ifs.pmp.baseParaMng.process.bean.OpenAcctOrgan;
import com.ruimin.ifs.pmp.mchtMng.comp.MchtMngAction;
import com.ruimin.ifs.pmp.mchtMng.comp.TxnWhiteListAction;
import com.ruimin.ifs.pmp.pubTool.common.servlet.BaseUploadServlet;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.comp.UserBlackListAction;
import com.ruimin.ifs.pmp.service.comp.ServiceMchtPollingAction;
import com.ruimin.ifs.po.IfsCtCd;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.term.comp.PaypMachInfAction;
import com.ruimin.ifs.util.SnowConstant;

//解析页面传过来的excel表格插入到数据库中
public class UploadXlsServlet extends BaseUploadServlet {
	
	/** servlet配置参数 */

	public static final String PARM_NAME_SINGLE_FILE_MAX_SIZE = "singleFileMaxSize";
	public static final String PARM_NAME_ONE_BATCH_MAX_SIZE = "oneBatchMaxSize";
	private long singleFileMaxSize;
	private long oneBatchMaxSize;
	
	
	/** 加载SnowLog */
	static Logger log = SnowLog.getLogger(UploadXlsServlet.class);

	
	public void init() throws ServletException {
		String temp = getInitParameter(PARM_NAME_SINGLE_FILE_MAX_SIZE);
		if (!StringUtil.isBlank(temp)) {
			singleFileMaxSize = Integer.valueOf(temp);
		}else{
			singleFileMaxSize = default_singleFileMaxSize;
		}
		log.debug("UploadImageServlet初始化参数【singleFileMaxSize】=" + singleFileMaxSize+"MB");
		temp = getInitParameter(PARM_NAME_ONE_BATCH_MAX_SIZE);
		if (!StringUtil.isBlank(temp)) {
			oneBatchMaxSize = Integer.valueOf(temp);
		}else{
			oneBatchMaxSize = default_onceFileMaxSize;
		}
		log.debug("UploadImageServlet初始化参数【oneBatchMaxSize】=" + singleFileMaxSize+"MB");
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doHandler(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	    // 设置编码格式为UTF-8
		request.setCharacterEncoding("UTF-8");
		// 初始化Common Upload
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List fileList = new ArrayList();
		FileItem item = null;
		try {
			// 获取功能类型
			String type = (String) request.getParameter("type");
			if (StringUtils.isBlank(type)) {
				SnowExceptionUtil.throwErrorException("无法获取功能类型！");
			}
			// 从update中得到文件列表
			fileList = upload.parseRequest(request);
			if (fileList != null) {
				item = (FileItem) fileList.get(0);
				
				long size = item.getSize();
				
				if(size>singleFileMaxSize*ONE_MB){
					SnowExceptionUtil.throwErrorException("上传文件过大（最大限制"+singleFileMaxSize+"MB）");
				}
				
				// 文件导入
				selectProcess(item, type);
			}

		}catch(SnowException e){
			log.error("捕捉到异常SnowException",e);
//			            e.printStackTrace();
			response.setHeader("Content-type", "text/html;charset=UTF-8");
						OutputStream os = response.getOutputStream(); // 提示信息输出流
						String message = e.getMessage();
						String errorHTML = "<b>上传失败,失败原因：" + message + "</b><br>";
						errorHTML += "<input type='button' value='重新导入' onclick='history.go(-1)'>&nbsp;&nbsp;";
						os.write(errorHTML.getBytes("UTF-8"));
						os.close();
						return;
		} catch (Exception e) {
			log.error("捕捉到异常Exception",e);
			// e.printStackTrace();
			OutputStream os = response.getOutputStream(); // 提示信息输出流
			String errorHTML = "<b>上传失败,失败原因：系统异常</b><br>";
			errorHTML += "<input type='button' value='重新导入' onclick='history.go(-1)'>&nbsp;&nbsp;";
			os.write(errorHTML.getBytes("UTF-8"));
			os.close();
			return;
		}
		log.info("===============导入成功");
		System.out.println("===============导入成功");
		request.setAttribute("message", "导入成功！");
		request.setAttribute("fileId", "xxxxxxxxxxxxx");
		request.setAttribute("result", "true");
		request.getRequestDispatcher("/pages/payPmp/pubTool/importXls.jsp").forward(request, response);
	}

	public void selectProcess(FileItem item, String type) throws SnowException, IOException, SQLException {
		if (type.equals(SnowConstant.FILE_ORG_LST)) {
			processOrg(item);
		}else if (type.equals(SnowConstant.FILE_CITY_LST)) {
			processCity(item);
		}else if (type.equals(SnowConstant.FILE_OPENORG_LST)) {
			processOpenOrg(item);
		}else if (type.equals(SnowConstant.FILE_BATHADD_LST)) {//终端设备管理批量导入
			new PaypMachInfAction().processOpenBathAdd(item);
		}else if(type.equals(SnowConstant.FILE_BLACKLIST_LST)){//风控用户黑名单导入
        	new UserBlackListAction().processUserBlacklist(item);
        }else if(type.equals(SnowConstant.FILE_POLLING_LIST)){//服务机构管理商户巡检基本信息导入
			new ServiceMchtPollingAction().processMchtPollinglist(item);
		}else if(type.equals(SnowConstant.FILE_TXN_WHITE_LST)){//特殊商户交易白名单导入
			new TxnWhiteListAction().batchImport(item);
		}else if(type.equals(SnowConstant.PBS_MCHT_BASE_INFO_TMP)){//商户批量导入
//		    new MchtMngAction().batchImport(item);
		    Object[] it=new Object[1];
		    it[0]=item;
          SnowApi.getInstance().execAction(MchtMngAction.class, "batchImport", it);
        }
	}

	public void processOpenOrg(FileItem item) throws IOException, SnowException, SQLException {
		// 获取解析文件的数据
		String[][] result = getData(item, 1);
		int rowLength = result.length;
		String lastBrcode = "";
		int i;
		DBDao dao = DBDaos.newInstance();
		// 循环判断主键是否重复
		for (i = 0; i < rowLength; i++) {
			for (int j = i + 1; j < rowLength; j++) {
				lastBrcode = result[i][0];
				if (lastBrcode.equals(result[j][0])) {
					SnowExceptionUtil.throwErrorException("主键【开户机构号】不可重复，请检查EXCEL相关数据！");
					break;
				}
			}
		}
		// 清空数据前检查
		for (i = 0; i < rowLength; i++) {
			if (result[i][0].length() > 12 || result[i][0].length() == 0) {
				SnowExceptionUtil.throwErrorException("开户机构编号不能为空，并且开户机构最大为12位！");
			}
			if (result[i][1].length() > 42 || result[i][1].length() == 0) {
				SnowExceptionUtil.throwErrorException("开户名称不能为空，并且开户名称最大为42位！");
			}
			if (result[i][2].length() > 0) {
				SnowExceptionUtil.throwErrorException("不符合开户机构导入格式");
			}
		}
		// 清空数据库
		dao.executeUpdate("com.ruimin.ifs.pmp.baseParaMng.rql.openAcctOrgan.deleteAll", "");
		// 检测EXCEL中是否存在主键重复

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 将解析到的文件set到数据库中
		for (i = 0; i < rowLength; i++) {
			OpenAcctOrgan oao = new OpenAcctOrgan();
			oao.setAcctInstNo(result[i][0]);
			oao.setAcctInstName(result[i][1]);
			oao.setDataState("00");
			oao.setCrtTlr(sessionUserInfo.getTlrno());
			oao.setCrtDateTime(BaseUtil.getCurrentDateTime());
			oao.setLastUpdTlr(sessionUserInfo.getTlrno());
			oao.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
			dao.insert(oao);

		}
		// 打印日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[开户机构]--全量导入开户机构:acctInstNo=" });
	}

	public void processOrg(FileItem item) throws IOException, SnowException, SQLException {
		// 获得解析文件的数据
		String[][] result = getData(item, 1);
		int rowLength = result.length;
		String lastBrcode = "";
		TblBctl vo = null;
		DBDao dao = DBDaos.newInstance();
		String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
		String reg = "^(0{1}[0-9]{2,3}\\-)?([2-9]{1}[0-9]{6,7})";
		String regx = "[1-9]{1}[0-9]{0,19}";
		// 检测EXCEL中是否存在主键重复
		for (int i = 0; i < rowLength; i++) {
			for (int j = i + 1; j < rowLength; j++) {
				lastBrcode = result[i][0];
				if (lastBrcode.equals(result[j][0])) {
					SnowExceptionUtil.throwErrorException("主键【内部机构号】不可重复，请检查EXCEL相关数据！");
					break;
				}
			}
		}
		for (int i = 0; i < rowLength; i++) {
			if (result[i][0].length() != 4) {
				SnowExceptionUtil.throwErrorException("机构号必须为4位！");
			}
			if (!result[i][1].matches(regx)) {
				SnowExceptionUtil.throwErrorException("机构编号必须为20位以内！");
			}
			if (result[i][2].length() > 20) {
				SnowExceptionUtil.throwErrorException("机构名称最大为20位！");
			}
			if (!(OrgManagerConstants.BR_CLASS_HEAD.equals(result[i][3])
					|| OrgManagerConstants.BR_CLASS_BRANCH.equals(result[i][3])
					|| OrgManagerConstants.BR_CLASS_SUB_BRANCH.equals(result[i][3]))) {
				SnowExceptionUtil.throwErrorException("机构级别只能为1或2或3！");
			}
			if (result[i][4].length() > 4) {
				SnowExceptionUtil.throwErrorException("上级机构的最大长度为4！");
			}
			if (result[i][5].length() > 4) {
				SnowExceptionUtil.throwErrorException("所属地区最大长度为4！");
			}
			if (result[i][6].length() > 6) {
				SnowExceptionUtil.throwErrorException("联系人名称最大长度为6！");
			}
			if (!(result[i][7].matches(reg) || result[i][7].matches(regExp)) && result[i][7] != ""
					&& result[i][7] != null) {
				SnowExceptionUtil.throwErrorException("联系电话的格式不对！");
			}
			if (result[i][8].length() != 6) {
				SnowExceptionUtil.throwErrorException("邮政编码长度为6！");
			}
			if (result[i][9].length() > 20) {
				SnowExceptionUtil.throwErrorException("机构地址的最大长度为20！");
			}
			if (result[i][10].length() > 11) {
				SnowExceptionUtil.throwErrorException("银联机构编号的最大长度为11！");
			}
		}
		// 清空数据库
		dao.executeUpdate("com.ruimin.ifs.mng.rql.othmng.deleteAll", "");

		// 将文件解析的数据SET到数据库中
		for (int i = 0; i < rowLength; i++) {

			vo = new TblBctl();
			vo.setBrcode(result[i][0]);
			int br = OrgService.getInstance().queryOrgByBrnoId(result[i][1]);
			if (br > 0) {
				SnowExceptionUtil.throwErrorException("机构编号不能重复！");
			} else {
				vo.setBrno(result[i][1]);
			}
			vo.setBrname(result[i][2]);

			vo.setBrclass(result[i][3]);
			vo.setBlnUpBrcode(result[i][4]);
			vo.setAreaCd(result[i][5]);
			vo.setLinkman(result[i][6]);
			vo.setTeleno(result[i][7]);
			vo.setPostno(result[i][8]);
			vo.setAddress(result[i][9]);
			vo.setCupBrhId(result[i][10]);
			vo.setStatus("00");
			dao.insert(vo);

		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 打印日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[机构管理]--全量导入机构管理:brcode=" });
	}

	/**
	 * 地区信息导入
	 * 
	 * @param item
	 * @throws IOException
	 * @throws SnowException
	 * @throws SQLException
	 */
	public void processCity(FileItem item) throws IOException, SnowException, SQLException {
		// 获得解析文件的数据
		String[][] result = getData(item, 1);
		int rowLength = result.length;
		String cityCode = "";
		int i;
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		DBDao dao = DBDaos.newInstance();

		// 检测EXCEL中是否存在主键重复
		for (i = 0; i < rowLength; i++) {
			for (int j = i + 1; j < rowLength; j++) {
				cityCode = result[i][0];
				if (cityCode.equals(result[j][0])) {
					SnowExceptionUtil.throwErrorException("主键【地区编号】不可重复，请检查EXCEL相关数据！");
					break;
				}
			}
		}

		// 全量导入前对所有数据检查
		for (i = 0; i < rowLength; i++) {
			if (result[i][0].length() > 4) {
				SnowExceptionUtil.throwErrorException("地区编码不能超过4位！");
			}

			if (result[i][1].length() > 20) {
				SnowExceptionUtil.throwErrorException("地区名称不能超过20位！");
			}

			if (result[i][2].length() > 1) {
				SnowExceptionUtil.throwErrorException("地区标识不能超过1位！");
			}

			if (result[i][3].length() > 4) {
				SnowExceptionUtil.throwErrorException("上级地区编码不能超过4位！");
			}

			if (result[i][4].length() > 4) {
				SnowExceptionUtil.throwErrorException("内部地区编码不能超过4位！");
			}
		}

		// 全量导入前清空数据库中全部数据
		dao.executeUpdate("com.ruimin.ifs.mng.rql.othmng.deleteAllCity", "");

		// 将文件解析的数据SET到数据库中
		for (i = 0; i < rowLength; i++) {
			IfsCtCd vo = new IfsCtCd();
			vo.setCtCode(result[i][0]);
			vo.setCtName(result[i][1]);
			vo.setCtFlg(result[i][2]);
			vo.setUpCtCode(result[i][3]);
			vo.setTlCtCode(result[i][4]);
			dao.insert(vo);
		}
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[地区管理]--地区信息全量导入" });
	}

	
	/**
	 * 解析Excel文件
	 * @throws SnowException 
	 */
	@SuppressWarnings("deprecation")
	public static String[][] getData(FileItem item, int ignoreRows) throws FileNotFoundException, IOException, SnowException {
	    InputStream in =null;
	    try {
			log.info("进入excel文件解析方法");
			List<String[]> result = new ArrayList<String[]>();
			int rowSize = 0;
			 in = item.getInputStream();
			// 打开HSSFWorkbook
			POIFSFileSystem fs = new POIFSFileSystem(in);
			log.info("POIFSFileSystem");
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			log.info("HSSFWorkbook");
			HSSFCell cell = null;
			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				HSSFSheet st = wb.getSheetAt(sheetIndex);
				log.info("HSSFSheet");
				// 第一行为标题，不取
				for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
					HSSFRow row = st.getRow(rowIndex);
					if (row == null) {
						continue;
					}
					int tempRowSize = row.getLastCellNum() + 1;
					if (tempRowSize > rowSize) {
						rowSize = tempRowSize;
					}
					String[] values = new String[rowSize];
					Arrays.fill(values, "");
					boolean hasValue = false;
					for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
						String value = "";
						cell = row.getCell(columnIndex);
						if (cell != null) {
							// 注意：一定要设成这个，否则可能会出现乱码
							// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								log.info("进入CELL_TYPE_STRING");
								log.info("value:"+value);
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								log.info("进入CELL_TYPE_NUMERIC");
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										value = new SimpleDateFormat("yyyy-MM-dd").format(date);
									} else {
										value = "";
									}
								} else {
									value = new DecimalFormat("0").format(cell.getNumericCellValue());
								}
								log.info("value:"+value);
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								log.info("CELL_TYPE_FORMULA");
								// 导入时如果为公式生成的数据则无值
								if (!cell.getStringCellValue().equals("")) {
									value = cell.getStringCellValue();
								} else {
									value = cell.getNumericCellValue() + "";
								}
								log.info("value:"+value);
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								log.info("CELL_TYPE_BLANK");
								log.info("value:"+value);
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								log.info("CELL_TYPE_ERROR");
								value = "";
								log.info("value:"+value);
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								value = (cell.getBooleanCellValue() == true ? "Y" : "N");
								log.info("CELL_TYPE_BOOLEAN,value:"+value);
								break;
							default:
								value = "";
							}
						}
						if (columnIndex == 0 && value.trim().equals("")) {
							break;
						}
						values[columnIndex] = rightTrim(value);
						hasValue = true;
					}

					if (hasValue) {
						result.add(values);
					}
				}
			}
			in.close();
			String[][] returnArray = new String[result.size()][rowSize];
			log.info("returnArray");
			for (int i = 0; i < returnArray.length; i++) {
				returnArray[i] = (String[]) result.get(i);
			}
			log.info("return returnArray");
			return returnArray;
		} catch (Exception e) {
			SnowExceptionUtil.throwErrorException("错误原因："+e);
		/** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */	
		}finally {
            if(in!=null ){
                in.close();
            }
        }
	    /** modify end */
		return null;
		
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}


}
