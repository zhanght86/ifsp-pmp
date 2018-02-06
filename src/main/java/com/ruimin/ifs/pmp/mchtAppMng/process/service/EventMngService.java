package com.ruimin.ifs.pmp.mchtAppMng.process.service;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;

@Service
@SnowDoc(desc = "事件管理")
public class EventMngService extends SnowService{	
	
	/**
	 * 获取实例
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static EventMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(EventMngService.class);
	}
	
	public PageResult queryMain(String tlrno,String brcode,String roleId,String qcrtDate, String qeventTitle, String qphoneNo, String qmchtId, String qmchtSimpleName, String qeventStat,Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		PageResult result = null;
		boolean flag = MchtMngService.getInstance().isAmr(roleId);
		//角色是客户经理，只可看见自己商户
		if(flag == true){
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.eventMng.queryMassInfo", RqlParam.map()
					.set("tlrno", tlrno).set("brcode",brcode)
					.set("qcrtDate",  StringUtils.isBlank(qcrtDate) ? "" : "%"+qcrtDate+"%")
					.set("qeventTitle", StringUtils.isBlank(qeventTitle) ? "" : "%"+qeventTitle+"%")
					.set("qphoneNo",  StringUtils.isBlank(qphoneNo) ? "" : "%"+qphoneNo+"%")
					.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%")
					.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%"+qmchtSimpleName+"%")
					.set("qeventStat", StringUtils.isBlank(qeventStat) ? "" : qeventStat),
					page);			
			return result;
		}else{			
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.eventMng.queryMassInfo2", RqlParam.map()
					.set("tlrno", tlrno).set("brcode",brcode)
					.set("qcrtDate",  StringUtils.isBlank(qcrtDate) ? "" : "%"+qcrtDate+"%")
					.set("qeventTitle", StringUtils.isBlank(qeventTitle) ? "" : "%"+qeventTitle+"%")
					.set("qphoneNo",  StringUtils.isBlank(qphoneNo) ? "" : "%"+qphoneNo+"%")
					.set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%")
					.set("qmchtSimpleName", StringUtils.isBlank(qmchtSimpleName) ? "" : "%"+qmchtSimpleName+"%")
					.set("qeventStat", StringUtils.isBlank(qeventStat) ? "" : qeventStat),
					page);			
			return result;
		}
	}

	public void update(String userId, Page page) {
		DBDao dao = DBDaos.newInstance();		
		dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.userMng.queryMassInfo1", RqlParam.map()
				.set("userId",  StringUtils.isBlank(userId) ? "" : userId),
				page);			
	}

}
