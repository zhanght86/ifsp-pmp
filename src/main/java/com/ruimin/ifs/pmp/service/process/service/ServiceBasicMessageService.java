package com.ruimin.ifs.pmp.service.process.service;


import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.service.process.bean.ServiceBasicMessageVo;
import com.ruimin.ifs.pmp.service.process.bean.ServiceInnerBrcodeServiceVo;


@Service
public class ServiceBasicMessageService extends SnowService {
	/**
	 * 获取单利的实体类
	 * @return
	 * @throws SnowException
	 */
	public static ServiceBasicMessageService getInstance() throws SnowException{
		return ContextUtil.getSingleService(ServiceBasicMessageService.class);
	}	
	/**
	 * 查询服务机构信息
	 * @param qriskId
	 * @param qriskName
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String qserviceCode, String qserviceName, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return  dao.selectList("com.ruimin.ifs.pmp.service.rql.serviceBasicMessage.queryMain", RqlParam.map()
				.set("qserviceCode",  StringUtils.isBlank(qserviceCode) ? "" : "%"+qserviceCode+"%")
				.set("qserviceName",  StringUtils.isBlank(qserviceName) ? "" : "%"+qserviceName+"%")
				,page);
	}
	/**
	 * 获取最大服务机构号
	 * @return
	 * @throws SnowException
	 */
	public String getMaxCode() throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.service.rql.serviceBasicMessage.getMaxCode");
	}
	/**
	 * 新增服务机构信息
	 * @param vo
	 * @throws SnowException
	 */
	public void saveBasicMessage(ServiceBasicMessageVo vo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.insert(vo);	
	}
	/**
	 * 修改服务机构信息
	 * @param vo
	 * @throws SnowException
	 */
	public void modifyBasicSave(ServiceBasicMessageVo vo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.service.rql.serviceBasicMessage.modifyBasic", vo);
	}
	/**
	 * 根据服务机构号删除单条服务机构信息
	 * @param serviceCode
	 * @throws SnowException
	 */
	public void deleteBasic(String serviceCode) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String sql = "update SERVICE_BASIC_MESSAGE  set stat = '99' where SERVICE_CODE = '"+serviceCode+"'";
		dao.executeUpdateSql(sql);
		
	}
	/**
	 * 取消关联的内部机构重新关联
	 * @param vo
	 * @throws SnowException
	 */
//	public void reIn(ServiceInnerBrcodeServiceVo vo) throws SnowException{
//		DBDao dao = DBDaos.newInstance();
//		dao.executeUpdate("com.ruimin.ifs.pmp.service.rql.serviceInnerBrcodeService.reIn", vo);
//	}
	/**
	 * 未关联过的内部机构新增入内部机构服务机构关联表中
	 * @param vo
	 * @throws SnowException
	 */
	public void insert(ServiceInnerBrcodeServiceVo vo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.insert(vo);
	}
	/**
	 * 修改内部机构和服务机构之间关联状态
	 * @param vo
	 * @throws SnowException
	 */
	public void quite(ServiceInnerBrcodeServiceVo vo) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.service.rql.serviceInnerBrcodeService.quite", vo);
	}
	
}
