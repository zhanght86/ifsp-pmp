package com.ruimin.ifs.pmp.report.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.framework.dataobject.FormRequestBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 * 
 * 郑银报表公共方法
 * 
 * @author zhangjc
 *
 */
public class CommonBthUtil {
	/** 加载SnowLog */
	static Logger log = SnowLog.getLogger(CommonBthUtil.class);

	/**
	 * 组装报表&输出Excel
	 * 
	 * @param path
	 *            报表路径
	 * @param title
	 *            标题
	 * @param pageHeader
	 *            查询条件
	 * @param detail
	 *            列表值
	 * @param requestBean
	 *            前端bean
	 * @throws IOException
	 * @throws SnowException
	 */
	public static void genBthOutExcel(String path, String title, Map<String, Object> pageHeader, List<Object> detail,
			FormRequestBean requestBean) throws IOException, SnowException {
		log.info("组装报表&输出Excel【开始】..");
		Long startTime = System.currentTimeMillis();
		/******************************
		 * STEP NO1 组装报表
		 ******************************/
		/** 获取jasper */
		File jasperFile = new File(path);
		log.info("path:"+path);
		/** 组装Detail */
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(detail);
		log.info("JRBeanCollectionDataSource创建成功!");
		/** 组装报表 */
		try {
			title = new String(title.getBytes("gb2312"), "ISO-8859-1");
			JasperPrint jasperPrint = new JasperPrint();// jasper容器
			log.info("jasper容器创建成功!");
			log.info("临时目录："+System.getProperty("java.io.tmpdir"));
			jasperPrint = JasperFillManager.fillReport(jasperFile.getPath(), pageHeader, ds);
			log.info("===========================");
			/******************************
			 * STEP NO2 输出Excel
			 ******************************/
			JRXlsExporter exporter = new JRXlsExporter();
			log.info("exporter创建成功!");
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			log.info("exporter设置参数1");
			try {
				exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
						requestBean.getResponse().getOutputStream());
				log.info("exporter设置参数2");
			} catch (IOException e) {
				log.error("组装Excel失败，原因：" + e.getMessage());
				SnowExceptionUtil.throwErrorException("组装Excel失败，原因：" + e.getMessage());
			}
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			// 设置输出的格式
			requestBean.getResponse().setHeader("Content-Disposition", "attachment;filename=" + title + ".xls");
			requestBean.getResponse().setContentType("application/vnd_ms-excel");
			exporter.exportReport();
		} catch (JRException e) {
			log.error("组装报表失败，原因：" + e.getMessage());
			SnowExceptionUtil.throwErrorException("组装报表失败，原因：" + e.getMessage());
		}
		Long endTime = System.currentTimeMillis();
		log.info("组装报表&输出Excel【完成】，总耗时" + (endTime - startTime) + "ms");
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));
	}
	
}
