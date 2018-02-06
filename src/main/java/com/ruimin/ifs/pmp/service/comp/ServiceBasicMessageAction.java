package com.ruimin.ifs.pmp.service.comp;

import java.util.List;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.service.constants.ServiceConstants;
import com.ruimin.ifs.pmp.service.process.bean.ServiceBasicMessageVo;
import com.ruimin.ifs.pmp.service.process.bean.ServiceInnerBrcodeMapping;
import com.ruimin.ifs.pmp.service.process.bean.ServiceInnerBrcodeServiceVo;
import com.ruimin.ifs.pmp.service.process.service.ServiceBasicMessageService;
import com.ruimin.ifs.pmp.service.process.service.ServiceInnerBrcodeServiceService;
@ActionResource
@SnowDoc(desc = "服务机构信息管理")
public class ServiceBasicMessageAction extends SnowAction {
	/**
	 * 查询服务机构基本信息
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询：服务机构信息表")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException{
		String qserviceCode = queryBean.getParameter("qserviceCode");
		String qserviceName = queryBean.getParameter("qserviceName");
		return ServiceBasicMessageService.getInstance().queryMain(qserviceCode, qserviceName, queryBean.getPage());
	}
	/**
	 * 新增服务机构基本信息
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addActive(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的黑名单基本信息
		UpdateRequestBean reqBean = updateMap.get("serviceBasicMessage");
		ServiceBasicMessageVo vo = new ServiceBasicMessageVo();
		if(reqBean.hasNext()){
			DataObjectUtils.map2bean(reqBean.next(), vo);
		}
		//获取最大服务机构号
		String serviceCode = ServiceBasicMessageService.getInstance().getMaxCode();
		if(StringUtil.isEmpty(serviceCode)){
			serviceCode = "0";
		}
		int nextServiceCode = Integer.valueOf(serviceCode.trim())+1;
		serviceCode = StringUtil.leftPad(String.valueOf(nextServiceCode),6,"0");
		vo.setServiceCode(serviceCode);
		//补充信息
		vo.setCrtTlr(sessionUserInfo.getTlrno());//创建人为当前操作员
		vo.setCrtDateTime(DateUtil.get14Date());//创建时间为当前时间
		vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新时间为当前操作员
		vo.setLastUpdDateTime(vo.getCrtDateTime());//最后更新时间为当前时间
		vo.setStat("00");//有效
		//数据持久化
		ServiceBasicMessageService.getInstance().saveBasicMessage(vo);
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[服务机构管理]--新增:机构号=[" + serviceCode + "]" });
	}
	/**
	 * 修改服务机构基本信息
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void modActive(Map<String, UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的数据集
		UpdateRequestBean reqBean = updateMap.get("serviceBasicMessage");
		ServiceBasicMessageVo vo = new ServiceBasicMessageVo();
		if(reqBean.hasNext()){
			DataObjectUtils.map2bean(reqBean.next(), vo);
		}
		//补充信息
		vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前操作用户
		vo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间为当前时间
		//数据持久化
		ServiceBasicMessageService.getInstance().modifyBasicSave(vo);
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[服务机构管理]--修改:机构号=[" + vo.getServiceCode() + "]" });
	}
	
	/**
	 * 删除服务机构信息
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delActive(Map<String, UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的数据集
		UpdateRequestBean reqBean = updateMap.get("serviceBasicMessage");
		String serviceCode = reqBean.getParameter("serviceCode");
		//根据服务机构号，查询该服务机构下面是够配置了内部机构
		List<Object> list = ServiceInnerBrcodeServiceService.getInstance().queryServiceInnerByCode(serviceCode);
		if(list.size() > 0){//有值，说明服务机构下面配置了行内机构，无法删除
			SnowExceptionUtil.throwWarnException("WEB_E0930");
		}
		//数据持久化
		ServiceBasicMessageService.getInstance().deleteBasic(serviceCode);
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[服务机构管理]--删除:机构号=[" + serviceCode + "]" });
	}
	
	/**
	 * 给服务机构下关联行内机构
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "关联")
	public void connecActive(Map<String, UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取数据集
		UpdateRequestBean reqBean = updateMap.get("serviceInnerBrcode");
		String serviceCode = "";
		while(reqBean.hasNext()){
			ServiceInnerBrcodeMapping mpVo = new ServiceInnerBrcodeMapping();
			DataObjectUtils.map2bean(reqBean.next(), mpVo);
			serviceCode = mpVo.getCode();
			if(String.valueOf(mpVo.getSelect()).equals("true")){//是选中状态
				if(mpVo.getInFlag().equals(ServiceConstants.IN_STATE_IN)){
					//之前就是选中状态，无改变
					continue;
				}else if(mpVo.getInFlag().equals(ServiceConstants.IN_STATE_OUT)){
					//之前就是退出关联状态，只需要修改关联状态，改成00即可
					ServiceInnerBrcodeServiceVo vo = new ServiceInnerBrcodeServiceVo();
					vo.setServiceCode(serviceCode);//服务机构号
					vo.setBrcode(mpVo.getBrcode());//内部机构号
					vo.setInFlag(ServiceConstants.IN_STATE_IN);//关联状态
					vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人
					vo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间
					ServiceBasicMessageService.getInstance().quite(vo);
				}else if(mpVo.getInFlag().equals(ServiceConstants.IN_STATE_NONE)){
					//之前从未关联过，新增数据
					ServiceInnerBrcodeServiceVo vo = new ServiceInnerBrcodeServiceVo();
					vo.setServiceCode(serviceCode);//服务机构号
					vo.setBrcode(mpVo.getBrcode());//内部机构号
					vo.setInFlag(ServiceConstants.IN_STATE_IN);//关联状态
					vo.setCrtTlr(sessionUserInfo.getTlrno());//创建人
					vo.setCrtDateTime(DateUtil.get14Date());//创建时间
					vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人
					vo.setLastUpdDateTime(vo.getCrtDateTime());//最后更新时间
					ServiceBasicMessageService.getInstance().insert(vo);
				}
			}else if(String.valueOf(mpVo.getSelect()).equals("false")){//未选中状态
				if(mpVo.getInFlag().equals(ServiceConstants.IN_STATE_NONE) || mpVo.getInFlag().equals(ServiceConstants.IN_STATE_OUT)){
					//之前是未关联状态，或者是取消关联状态
					continue;
				}else if(mpVo.getInFlag().equals(ServiceConstants.IN_STATE_IN)){
					//之前是关联状态,修改成取消关联状态
					ServiceInnerBrcodeServiceVo vo = new ServiceInnerBrcodeServiceVo();
					vo.setServiceCode(serviceCode);//服务机构号
					vo.setBrcode(mpVo.getBrcode());//内部机构号
					vo.setInFlag(ServiceConstants.IN_STATE_OUT);//关联状态
					vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人
					vo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间
					ServiceBasicMessageService.getInstance().quite(vo);
				}
			}
		}
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[服务机构管理]--关联:机构号=[" + serviceCode + "]" });
	}
	
}
