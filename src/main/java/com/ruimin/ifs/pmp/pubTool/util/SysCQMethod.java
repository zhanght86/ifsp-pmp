/*
 * Copyright (C), 2015-2015, 上海睿民互联网科技有限公司
 * FileName: SysCQMethod.java
 * Author:   GH
 * Date:     2015年6月4日 上午10:50:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <DESC>
 */
package com.ruimin.ifs.pmp.pubTool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.process.service.DataDicEntryService;
import com.ruimin.ifs.po.TblDataDic;

/**
 * 〈字段转义〉<br>
 * 
 * @author GH
 */
public class SysCQMethod {

	/**
	 * 
	 * 功能描述: 字符串转日期格式 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param element
	 * @param value
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public static String toDateStr(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return BaseUtil.convertStringToDateStr(key);
	}

	/**
	 * 
	 * 功能描述: 6位时间字符串转时间格式 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param element
	 * @param value
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public static String toTimeStr(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return BaseUtil.converTimeToTimeStr(key);
	}

	/**
	 * 
	 * 功能描述: 分转元 <br>
	 * 〈功能详细描述〉
	 * 
	 * @param element
	 * @param value
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public static String fenToYuan(FieldBean bean, String key, ServletRequest request) throws SnowException {
		return BaseUtil.transFenToYuan(key);
	}

	// /**
	// * 根据 微信主商户号类型 获取 类型名称
	// *
	// * @param bean
	// * @param key
	// * @param request
	// * @return
	// */
	// public static String getWxMainMchtType(FieldBean bean, String key,
	// ServletRequest request) throws SnowException {
	// if (StringUtils.isNotBlank(key)) {
	// ImpMchtEvalModl modlInfo = EvalModlService.getInstance().query(key);
	// if (modlInfo != null) {
	// return modlInfo.getEvalModlName();
	// }
	// }
	// return key;
	// }

	/**
	 * 商户特殊计费动态查询
	 * 
	 * added by mj 2015-11-24
	 */
	public static String getDataDicName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			String[] keys = key.split("\\|");
			if (keys.length > 1) {
				if ("1347".equals(keys[0])) {
					TblDataDic inf = DataDicEntryService.getInstance().query("1348", keys[1], keys[2]);
					if (inf != null) {
						return inf.getDataName();
					}
				}
			} else {
				TblDataDic inf = DataDicEntryService.getInstance().query(null, null, key);
				if (inf != null) {
					return inf.getDataName();
				}
			}
		}
		return key;
	}

	/**
	 * 时间格式转换，渠道功能里面，渠道清算时间显示时，做格式转换
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 * @throws SnowException
	 * @throws ParseException
	 */
	public static String timeFormat(FieldBean bean, String key, ServletRequest request)
			throws SnowException, ParseException {
		if (StringUtil.isEmpty(key)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Date date = (Date) sdf.parse(key);
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		String chlSetlTm = sdf2.format(date);
		return chlSetlTm;
	}

}
