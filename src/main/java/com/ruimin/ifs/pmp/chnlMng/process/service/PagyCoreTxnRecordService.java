/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.paydep.chnlMng.process.service 
 * FileName: ChannelInfoService.java
 * Author:   zrx
 * Date:     2016年7月21日 上午10:51:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   zrx           2016年7月21日上午10:51:06                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.pmp.chnlMng.process.service;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.chnlMng.common.constants.ChannelInfoConstants;

/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月21日 <br>
 * 作者：zrx <br>
 * 说明：<br>
 */
@SnowDoc(desc = "通道核心交易记录Service")
@Service
public class PagyCoreTxnRecordService extends SnowService {
	public static PagyCoreTxnRecordService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PagyCoreTxnRecordService.class);
	}

	/**
	 * 分页查询通道核心记录列表
	 */
	public PageResult queryList(Map<String, String> paramMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		//20170418fjf 修改   需求:只有总行才显示 接入渠道为本行接入(C0002)的交易记录,因为本行接入的不需要经过支付业务系统,
		//所以本行接入的商户号不存在支付业务的商户基本信息表中,无法根据机构来判断是否显示该笔交易
		String currentBrCode = paramMap.get("currentBrCode");
		if(ChannelInfoConstants.HEAD_OFFICE.equals(currentBrCode)){//总行
			return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyCoreTxnRecord.headQueryList", paramMap, page);
		}
		return dao.selectList("com.ruimin.ifs.pmp.chnlMng.rql.pagyCoreTxnRecord.queryList", paramMap, page);

	}
}
