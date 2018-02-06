package com.ruimin.ifs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.w3c.dom.Document;

import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.xml.XMLDocumentLoader;

public class FSToolUtil {
	private static final Logger logger = SnowLog.getLogger(FSToolUtil.class);

	public static Properties readProperties(String propRef) {
		String path = propRef.replace(".", "/") + ".properties";
		Properties properties = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream in = loader.getResourceAsStream(path);
		try {
			if (in != null) {
				properties = new Properties();
				properties.load(in);
			} else {
				logger.error("read properties [" + path + "] not exist!");
				throw new RuntimeException("read properties [" + path + "] not exist!");
			}
		} catch (Exception e) {
			e = e;
			logger.error("read properties [" + path + "] exception!", e);
			throw new RuntimeException("read properties [" + path + "] exception!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}

	public static Document readXml(String xmlRef) {
		Document document = null;
		String path = xmlRef.replace(".", "/") + ".xml";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream in = loader.getResourceAsStream(path);
		try {
			if (in != null) {
				XMLDocumentLoader xmlloader = new XMLDocumentLoader();
				document = xmlloader.loadXMLDocument(in);
			} else {
				logger.error("read xml [" + path + "] not exist!");
				throw new RuntimeException("read xml [" + path + "] not exist!");
			}
		} catch (Exception e) {
			e = e;
			logger.error("read xml [" + path + "] exception!", e);
			throw new RuntimeException("read xml [" + path + "] exception!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return document;
	}

	public static String getProperty(String key) {
		// TODO Auto-generated method stub
		Properties ps = readProperties("genconf/resources/sysParam");
		String value = ps.getProperty(key);
		return value;
	}

//	public static void main(String[] args) {
//		String ip = getProperty("USER00001Ip");
//		System.out.println(ip);
//	}
}