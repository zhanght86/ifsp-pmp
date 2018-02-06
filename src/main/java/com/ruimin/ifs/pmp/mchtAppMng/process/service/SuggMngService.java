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
@SnowDoc(desc = "商户管理")
public class SuggMngService extends SnowService{	
	
	/**
	 * 获取实例
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static SuggMngService getInstance() throws SnowException{
		return ContextUtil.getSingleService(SuggMngService.class);
	}
	
	/**
	 * 主页面展示
	 * @param quserName
	 * @param qmchtArtifId
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String tlrno,String brcode,String roleId,String qcrtDate, String qrate, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		PageResult result = null;
		boolean flag = MchtMngService.getInstance().isAmr(roleId);
		//角色是客户经理，只可看见自己商户
		if(flag == true){
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.suggMng.queryMassInfo", RqlParam.map()
					.set("tlrno", tlrno).set("brcode", brcode)
					.set("qcrtDate",  StringUtils.isBlank(qcrtDate) ? "" : "%"+qcrtDate+"%")
					.set("qrate", StringUtils.isBlank(qrate) ? "" : qrate),
					page);
		}else{
			result = dao.selectList("com.ruimin.ifs.pmp.mchtAppMng.rql.suggMng.queryMassInfo2", RqlParam.map()
					.set("tlrno", tlrno).set("brcode", brcode)
					.set("qcrtDate",  StringUtils.isBlank(qcrtDate) ? "" : "%"+qcrtDate+"%")
					.set("qrate", StringUtils.isBlank(qrate) ? "" : qrate),
					page);
		}
		return result;
	}

	/**
	 * 更新状态
	 * @param suggId
	 * @throws SnowException 
	 */
    public void upd(String suggId) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.mchtAppMng.rql.suggMng.update",suggId);
    }

}
