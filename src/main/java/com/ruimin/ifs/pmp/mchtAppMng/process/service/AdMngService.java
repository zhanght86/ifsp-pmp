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
import com.ruimin.ifs.pmp.mchtAppMng.process.bean.AdBaseInfo;

@Service
public class AdMngService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static AdMngService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(AdMngService.class);
	}
	/**
	 * 查询系统公告
	 * @param page 
	 * @param qupdFlag 
	 * @param qappVer 
	 * @param qdeviceType 
	 * @param qcrtDateTime 发布公告事件
	 * @param qnoticeTitle 公告标题
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	
	public PageResult queryMain(String qadPst, String qadInfo, String qadStat, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.adMng.queryMain",RqlParam.map()
	                         .set("qadPst", StringUtils.isBlank(qadPst) ? "" : qadPst)
	                         .set("qadInfo", StringUtils.isBlank(qadInfo) ? "" : "%"+qadInfo+"%")
	                         .set("qadStat", StringUtils.isBlank(qadStat) ? "" : qadStat),
	                         page);
	}
	
	/**
	 * @param pmpSysNoticeInfoVO 公告实体对象
	 * 公告修改操作
	 * @param noticeTitle  公告标题
	 * @param noticePicId  图片号
	 * @throws SnowException
	 */
	public void update(AdBaseInfo adBaseInfo) throws SnowException{
		 DBDao dao = DBDaos.newInstance();
		 dao.update(adBaseInfo);
	}
	/**
	 * 新增公告
	 * @param pmpSysNoticeInfoVO 公告实体对象
	 * @throws SnowException
	 */
	public void add(AdBaseInfo adBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(adBaseInfo);
	}
	/**
	 * 查询当前记录的系统公告最大编号
	 * @return
	 * @throws SnowException
	 */
	public String queryMaxId() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.mchtAppMng.rql.adMng.queryMaxId");
	}
	
	
	public static boolean ifExit(AdBaseInfo adBaseInfo) {
		DBDao dao = DBDaos.newInstance();
		String adPst = adBaseInfo.getAdPst();
    	String picPst = adBaseInfo.getPicPst();
    	List list = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.adMng.queryExit",RqlParam.map()
				.set("adPst",adPst).set("picPst", picPst) );
    	if(list.size() == 0){
    		return true;
    	}else{
    		if(list.size() == 1){
    			AdBaseInfo info	= (AdBaseInfo) list.get(0);
    			if(info.getAdId().equals(adBaseInfo.getAdId())){
    				return true;
    			}
    		}
    		return false;
    	}
	}
	public void delete(AdBaseInfo adBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(adBaseInfo);
	}
	
}
