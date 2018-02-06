package com.ruimin.ifs.pmp.mktActivity.process.service;
import java.util.Map;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MchtLvInfTmpVO;
import com.ruimin.ifs.util.BaseUtil;

@Service
public class MerchantRatingsService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MerchantRatingsService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(MerchantRatingsService.class);
	}
	/**
	 * 根据页面查询条件(商户号，商户名，当前等级)查询商户评级信息临时表
	 * @param map
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult querySnapForMap(String qmchtId,String qmchtNm,String qmchtLvC, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		// 组装模糊查询
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.merchantRatings.querySnap",
				   RqlParam.map()
	               .set("qmchtId", StringUtils.isBlank(qmchtId) ? "" : "%"+qmchtId+"%")
	               .set("qmchtNm", StringUtils.isBlank(qmchtNm) ? "" : "%"+qmchtNm+"%")
	               .set("qmchtLvC", StringUtils.isBlank(qmchtLvC) ? "" : "%"+qmchtLvC+"%"),
	                page);
	}
	/**
	 * 查看历史评级信息
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryHistoryForMap(Map<String, String> map, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PageResult result = dao.selectList(
				            "com.ruimin.ifs.pmp.mktActivity.rql.merchantRatings.queryHistory", map, page);
		return result;
	}
	/**
	 * 验证审核人身份，成功则直接插入到临时表中，更改状态
	 */
	public void update(String username,String password,String changeLv,String mchtId) throws SnowException{
		 SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		 updateTable(sessionUserInfo,username,changeLv,mchtId);
	}
	public void updateTable(SessionUserInfo sessionUserInfo,String username,String changeLv,String mchtId) throws SnowException{
		 DBDao dao = DBDaos.newInstance();
		 //如果验证成功，更改数据，更新商户评级临时表
		 MchtLvInfTmpVO mchtLvInfTmpVO=new MchtLvInfTmpVO();
	     //设置数据同步状态 0:未同步 1：已同步
	     mchtLvInfTmpVO.setSyncFlag("0");
	     String syncFlag=mchtLvInfTmpVO.getSyncFlag();
	     //设置手工变更标志 0：未变更 1：已变更
	     mchtLvInfTmpVO.setMFlg("1");
	     String mFlg=mchtLvInfTmpVO.getMFlg();
	     //设置更新操作员
	     String operator=sessionUserInfo.getTlrno();
	     //设置更新时间
	     mchtLvInfTmpVO.setUpdateTime(BaseUtil.getCurrentDateTime());
	     String updateTime=mchtLvInfTmpVO.getUpdateTime();
	     //设置审核时间
	     mchtLvInfTmpVO.setAuditTime(BaseUtil.getCurrentDateTime());
	     String auditTime=mchtLvInfTmpVO.getAuditTime();
	     String sql="update TBL_MCHT_LV_INF_TMP set MCHT_LV_C ='"+changeLv +"',"
	        				                                     +"SYNC_FLAG= '"+syncFlag+"',"
	        				                                     +"M_FLG= '"+mFlg+"',"
	        				                                     +"UPDATE_OPR= '"+operator+"',"
	        				                                     +"UPDATE_TIME= '"+updateTime+"',"
	        				                                     +"AUDIT_OPR= '"+username+"',"
	        				                                     +"AUDIT_TIME= '"+auditTime+"'"
	        				 	                                 + " where MCHT_ID = '" +mchtId +"' ";
		dao.executeUpdateSql(sql);
	}
}
