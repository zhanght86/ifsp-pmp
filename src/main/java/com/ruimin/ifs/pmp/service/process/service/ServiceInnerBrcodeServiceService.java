package com.ruimin.ifs.pmp.service.process.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
@Service
public class ServiceInnerBrcodeServiceService extends SnowService {
	public static ServiceInnerBrcodeServiceService getInstance() throws SnowException{
		return ContextUtil.getSingleService(ServiceInnerBrcodeServiceService.class);
	}	
	
	/**
	 * 查询
	 * @param serviceCode
	 * @return
	 * @throws SnowException
	 */
	public List<Object> queryMain(String serviceCode) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.service.rql.serviceInnerBrcodeService.queryMain", RqlParam.map()
							.set("serviceCode",  StringUtils.isBlank(serviceCode) ? "" : serviceCode));
	}
	/**
	 * 关联页面的查询
	 * @param qbrcode
	 * @param qbrname
	 * @param qconnectState
	 * @param serviceCode
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryForMap(String qbrcode, String qbrname, String qconnectState, String serviceCode,Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.service.rql.serviceInnerBrcodeService.queryInnerBrcode",
				RqlParam.map().set("qbrcode", StringUtils.isBlank(qbrcode) ? "" : "%"+qbrcode+"%")
				.set("qbrname",StringUtils.isBlank(qbrname) ? "" : "%"+qbrname+"%")
				.set("qconnectState", StringUtils.isBlank(qconnectState) ? "" : "%"+qconnectState+"%")
				.set("serviceCode", serviceCode),page);
	}
	/**
	 * 根据服务机构号，查询下面关联的所有内部机构号
	 * @param serviceCode
	 * @return
	 * @throws SnowException
	 */
	public List<Object> queryServiceInnerByCode(String serviceCode) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.service.rql.serviceInnerBrcodeService.queryServiceInnerByCode", RqlParam.map()
				.set("serviceCode",  StringUtils.isBlank(serviceCode) ? "" : serviceCode));
	}

}
