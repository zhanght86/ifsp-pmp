package com.ruimin.ifs.pmp.mchtAppMng.process.service;
import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.VerBaseInfo;

@Service
public class VerMngService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static VerMngService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(VerMngService.class);
	}
	/**
	 * 首页查询
	 * @param qdeviceType
	 * @param qappVer
	 * @param qupdFlag
	 * @param page
	 * @return
	 */
	public PageResult queryMain(String qdeviceType, String qappVer, String qupdFlag, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.verMng.queryMain",RqlParam.map()
	                         .set("qdeviceType", StringUtils.isBlank(qdeviceType) ? "" : qdeviceType)
	                         .set("qappVer", StringUtils.isBlank(qappVer) ? "" : "%"+qappVer+"%")
	                         .set("qupdFlag", StringUtils.isBlank(qupdFlag) ? "" : qupdFlag),
	                         page);
	}
	
	/**
	 * 新增
	 * @param verBaseInfo
	 * @throws SnowException
	 */
	public void add(VerBaseInfo verBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(verBaseInfo);
	}
	
	/**
	 * 判断是否存在
	 * @param verBaseInfo
	 * @param string 
	 * @return
	 */
	public static boolean ifExit(VerBaseInfo verBaseInfo) {
		DBDao dao = DBDaos.newInstance();
		String deviceType = verBaseInfo.getDeviceType();
    	String appVer = verBaseInfo.getAppVer();
    	List list = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.verMng.queryExit",RqlParam.map()
				.set("deviceType",deviceType).set("appVer", appVer) );
    	if(list.size() == 0){
    		return false;//可以提交
    	}else{
    		return true;
    	}
	}
	
}
