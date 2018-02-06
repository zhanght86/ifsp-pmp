package com.ruimin.ifs.pmp.mktActivity.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MchtLvConVO;
import com.ruimin.ifs.pmp.mktActivity.process.service.MchtLvConService;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.StringUtil;

import java.util.Map;




@SnowDoc(desc = "商户评级配置")
@ActionResource
public class MchtLvConAction extends SnowAction {

    @Action
    @SnowDoc(desc = "显示商户评级配置情况")
    public PageResult queryMchtLvCon(QueryParamBean queryBean) throws SnowException {
         
    	String qmchtLv = queryBean.getParameter("qmchtLv"); 
        return MchtLvConService.getInstance().queryMchtLvConinfo(qmchtLv,queryBean.getPage());
    }
    
    @SnowDoc(desc = "新增")
	public void saveOrAdd(Map<String, UpdateRequestBean> updateMap) throws SnowException {
    	//获取数据集
    	UpdateRequestBean reqBean = updateMap.get("MchtLvCon");
    	UpdateRequestBean reqBeanAccountInfo = updateMap.get("accountInfo");
		MchtLvConVO MchtLvCon = new MchtLvConVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), MchtLvCon);
		}
		
		//查询最大序列号
		String maxSeq = MchtLvConService.getInstance().queryMaxSeq(MchtLvCon.getMchtLv());
		String nextSeq;
		if(maxSeq !=null){
			int num = Integer.valueOf(maxSeq);
			nextSeq = StringUtil.leftPad(String.valueOf(num+1), 2,"0");
		}else{
			nextSeq = "01";
		}
		MchtLvCon.setMchtLvSeq(nextSeq);
		
		
		//设置记录状态为生效("00"无效,"01"有效)
		MchtLvCon.setRdStat("01");
		//金额写入数据库，元转分
		MchtLvCon.setBusiAmtMix(BaseUtil.transYuanToFen(MchtLvCon.getBusiAmtMix()));
		MchtLvCon.setDayDepositMix(BaseUtil.transYuanToFen(MchtLvCon.getDayDepositMix()));  
		//更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		MchtLvCon.setUpdateOpr(sessionUserInfo.getTlrno());
		MchtLvCon.setUpdateTime(BaseUtil.getCurrentDateTime());
		//调用添加方法
		MchtLvConService.getInstance().addSupply(MchtLvCon);
		//打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
									      sessionUserInfo.getBrno(), "评级配置新增:序列号=" + MchtLvCon.getMchtLvSeq() });
	}
   
    
	@SnowDoc(desc = "修改")
	public void saveOrUpdate(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		//获取数据集
		UpdateRequestBean reqBean = updateMap.get("MchtLvCon");
		UpdateRequestBean reqBeanAccountInfo = updateMap.get("accountInfo");
		MchtLvConVO MchtLvCon = new MchtLvConVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), MchtLvCon);
		}
		
		//金额写入数据库，元转分
		MchtLvCon.setBusiAmtMix(BaseUtil.transYuanToFen(MchtLvCon.getBusiAmtMix()));
		MchtLvCon.setDayDepositMix(BaseUtil.transYuanToFen(MchtLvCon.getDayDepositMix()));  
		//更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		MchtLvCon.setUpdateOpr(sessionUserInfo.getTlrno());
		MchtLvCon.setUpdateTime(BaseUtil.getCurrentDateTime());
		//调用修改方法
		MchtLvConService.getInstance().updateSupply(MchtLvCon);		
		//打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
											      sessionUserInfo.getBrno(), "评级配置修改:序列号=" + MchtLvCon.getMchtLvSeq() });
	}
	
	
	@SnowDoc(desc = "删除")//并不真正删除记录，仅把rd_stat状态置为无效
	public void delete(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("MchtLvCon");
		UpdateRequestBean reqBeanAccountInfo = updateMap.get("accountInfo");
		MchtLvConVO MchtLvCon = new MchtLvConVO();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), MchtLvCon);
		}
		
		//更新相关操作信息
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		MchtLvCon.setUpdateOpr(sessionUserInfo.getTlrno());
		MchtLvCon.setUpdateTime(BaseUtil.getCurrentDateTime());
		//调用删除方法
		MchtLvConService.getInstance().deleteSupply(MchtLvCon);	
		//打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
											      sessionUserInfo.getBrno(), "评级配置删除:序列号=" + MchtLvCon.getMchtLvSeq() });
			
	}
    
}

