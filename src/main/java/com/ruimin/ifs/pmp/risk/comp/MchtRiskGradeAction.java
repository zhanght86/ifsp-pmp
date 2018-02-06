package com.ruimin.ifs.pmp.risk.comp;

import java.util.Map;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.risk.process.bean.MchtRiskGradeVo;
import com.ruimin.ifs.pmp.risk.process.service.MchtRiskGradeService;

@SnowDoc(desc = "商户风险等级")
public class MchtRiskGradeAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException{
		 String qMchtNo = queryBean.getParameter("qMchtNo");
		 String qMchtName = queryBean.getParameter("qMchtName");
		 String qRiskLevel = queryBean.getParameter("qRiskLevel");
		 return MchtRiskGradeService.getInstance().queryList(qMchtNo,qMchtName,qRiskLevel,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "手工评级")
	public void handWorkGrade(Map<String, UpdateRequestBean> updateMap)throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("MchtRiskGrade");
		MchtRiskGradeVo mchtRiskGradeVo = new MchtRiskGradeVo();
		if(reqBean.hasNext()){
			DataObjectUtils.map2bean(reqBean.next(),mchtRiskGradeVo);
		}
		//封装方法补充信息
		mchtRiskGradeVo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前用户
		mchtRiskGradeVo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间为当前时间
		//数据持久化
		MchtRiskGradeService.getInstance().handWorkGrade(mchtRiskGradeVo);
	}
}
