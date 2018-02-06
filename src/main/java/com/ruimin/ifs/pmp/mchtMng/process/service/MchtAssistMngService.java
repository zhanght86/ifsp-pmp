package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.realm.ldap.DefaultLdapContextFactory;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtAssistInfoTmp;

@Service
@SnowDoc(desc = "商户辅表管理")
public class MchtAssistMngService {

	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtAssistMngService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtAssistMngService.class);
	}
	
	/**
	 * 商户合同的初始化查询
	 * @param qmchtId 
	 * 
	 * @param string
	 */
	public PageResult queryMain(String qmchtId, Page page)throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.mchtAssistMng.queryList",
				RqlParam.map().set("qmchtId",  StringUtils.isBlank(qmchtId) ? "" : "%" + qmchtId + "%"),page);
	}

	public void addAssistInfoTmp(PbsMchtAssistInfoTmp assistInfoTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(assistInfoTmp);
	}

	public void addAssistInfo(PbsMchtAssistInfo assistInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(assistInfo);
	}

	public void updAssistInfoTmp(PbsMchtAssistInfoTmp assistInfoTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		//2017-12-21  解决之前没有辅表信息商户的修改
		Object one = dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtAssistMng.queryByKeyTmp", RqlParam.map().set("mchtId", assistInfoTmp.getMchtId()));
		if (IfspDataVerifyUtil.isNotBlank(one)) {
		    dao.update(assistInfoTmp);
        }else {
            dao.insert(assistInfoTmp);
        }
	}

	public void updAssistInfo(PbsMchtAssistInfo assistInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		//2017-12-21  解决之前没有辅表信息商户的修改
        Object one = dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtAssistMng.queryByKey", RqlParam.map().set("mchtId", assistInfo.getMchtId()));
        if (IfspDataVerifyUtil.isNotBlank(one)) {
            dao.update(assistInfo);
        }else {
            dao.insert(assistInfo);
        }
	}

	/**
	 * 查询辅表信息(通过主键)
	 * @param mchtId
	 * @return 
	 */
	public PbsMchtAssistInfo selectByMchtId(String mchtId) {
		DBDao dao = DBDaos.newInstance();
		PbsMchtAssistInfo assistInfo = (PbsMchtAssistInfo) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtAssistMng.queryByKey", RqlParam.map().set("mchtId", mchtId));
		return assistInfo;
	}


	public void deleteAssistTmp(PbsMchtAssistInfoTmp assistInfoTmp) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(assistInfoTmp);
	}
	
}
