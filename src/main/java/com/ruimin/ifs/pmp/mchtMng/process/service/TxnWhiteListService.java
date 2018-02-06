package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.util.List;

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
import com.ruimin.ifs.pmp.mchtMng.process.bean.TxnWhiteListVO;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;

@Service
@SnowDoc(desc = "特殊商户交易白名单service")
public class TxnWhiteListService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static TxnWhiteListService getInstance() throws SnowException {
		return ContextUtil.getSingleService(TxnWhiteListService.class);
	}

	/**
	 * @param qMchtId
	 *            商户号 模糊匹配
	 * @Param qMchtSimpleName 商户简称 模糊匹配
	 * @param qFiltrateValue
	 *            值 模糊匹配
	 * @param page
	 *            分页对象
	 */
	public PageResult pageQuery(String qMchtId, String qMchtSimpleName, String qFiltrateValue, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.txnWhiteList.queryList",
				RqlParam.map().set("qMchtId", StringUtil.isEmpty(qMchtId) ? "" : "%" + qMchtId + "%")
						.set("qMchtSimpleName", StringUtil.isEmpty(qMchtSimpleName) ? "" : "%" + qMchtSimpleName + "%")
						.set("qFiltrateValue", StringUtil.isEmpty(qFiltrateValue) ? "" : "%" + qFiltrateValue + "%"),
				page);
	}

	public String pageQuery1(String qMchtId, String qFiltrateValue)
            throws SnowException {
        DBDao dao = DBDaos.newInstance();
        return  (String) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.txnWhiteList.queryList1",
                RqlParam.map().set("qMchtId",qMchtId)
                .set("qFiltrateValue", qFiltrateValue));
    }
	public void add(TxnWhiteListVO txnWhiteList) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(txnWhiteList);
	}

	public void update(TxnWhiteListVO txnWhiteList) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(txnWhiteList);
	}

	public void delete(TxnWhiteListVO txnWhiteList) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.delete(txnWhiteList);
	}

	public void batchInsert(List<TxnWhiteListVO> txnWhiteList) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(txnWhiteList);
	}
	/**
	 * 该方法用于批量导入校验，返回全量的商户号+"_"+值拼接的字符串列表
	 * */
	public List<String> getAllKeysForImport(){
		DBDao dao = DBDaos.newInstance();
		return (List)dao.selectList("com.ruimin.ifs.pmp.mchtMng.rql.txnWhiteList.getAllKeysForImport");
	}
}
