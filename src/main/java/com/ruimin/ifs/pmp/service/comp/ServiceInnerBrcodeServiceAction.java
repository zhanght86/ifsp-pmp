package com.ruimin.ifs.pmp.service.comp;

import java.util.ArrayList;
import java.util.List;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.service.process.bean.ServiceInnerBrcodeMapping;
import com.ruimin.ifs.pmp.service.process.service.ServiceInnerBrcodeServiceService;
@ActionResource
@SnowDoc(desc = "内部机构服务机构关联表")
public class ServiceInnerBrcodeServiceAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询：根据服务机构号查询该服务机构号下面的商户编号和商户名称")
	public List<ServiceInnerBrcodeMapping> queryMain(QueryParamBean queryBean) throws SnowException{
		String serviceCode = queryBean.getParameter("serviceCode");// 服务机构号
		List<ServiceInnerBrcodeMapping> result = new ArrayList<>();
		List<Object> list = ServiceInnerBrcodeServiceService.getInstance().queryMain(serviceCode);
		for (Object object : list) {
			ServiceInnerBrcodeMapping vo = (ServiceInnerBrcodeMapping)object;
			result.add(vo);
		}
		return result;
	}
	
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryInnerBrcode(QueryParamBean queryBean) throws SnowException {
		String qbrcode = queryBean.getParameter("qbrcode");//内部机构号
		String qbrname = queryBean.getParameter("qbrname");//内部机构名称
		String qconnectState = queryBean.getParameter("qconnectState");//关联状态
		String serviceCode = queryBean.getParameter("qserviceCode");//服务机构号
		PageResult result = ServiceInnerBrcodeServiceService.getInstance().queryForMap(qbrcode,qbrname,qconnectState,serviceCode,queryBean.getPage());
		int count = result.getTotalCount();
		System.out.println(count);
		return result;
	}

	

	
	
}
