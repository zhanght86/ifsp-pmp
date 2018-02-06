package com.ruimin.ifs.pmp.txnQuery.process.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;

/**
 * 名称：〈一句话功能简述〉<br> 
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2018年2月5日 <br>
 * 作者：LJY <br>
 * 说明：modify by lengjingyu 20180205 交易查询界面变更需求 jira-1865 <br>
 */

@SnowDoc(desc = "支付业务交易查询Service")
@Service
public class PbsOrderInfoService extends SnowService {
	private Logger log = SnowLog.getLogger(PbsOrderInfoService.class);

	public static PbsOrderInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PbsOrderInfoService.class);
	}

	/**
	 * 根据查询条件 分页查询 支付业务交易数据列表
	 */
	public PageResult queryAll(Map<String, Object> queryParamMap, Page page) {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryListPbs", queryParamMap, page);
	}
	
	/**
	 * 根据流水号查询记录
	 * @param queryParamMap
	 * @param page
	 * @return
	 */
	public PageResult queryByTpamOutId(Map<String, Object> queryParamMap, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryByTpamOutId", queryParamMap, page);
    }
	
	/**
	 * 根据流水号查询记录(关联视图)
	 * @param queryParamMap
	 * @param page
	 * @return
	 */
	public PageResult selectByTpamOutId(Map<String, Object> queryParamMap, Page page) {
	    DBDao dao = DBDaos.newInstance();
	    return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.tpamView", queryParamMap, page);
	}
	
	/**
	 * 根据查询条件 分页查询 支付业务交易数据列表(不走通道)
	 */
	public PageResult queryDetil(String txnSeqId, Page page) {
	    DBDao dao = DBDaos.newInstance();
	    return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryDetil", txnSeqId, page);
	}
	
	/**
	 * 根据通道流水分页查询 支付业务交易数据列表
	 * @param queryParamMap
	 * @param page
	 * @return
	 */
	public PageResult queryAllByPagyTxnSsn(Map<String, Object> queryParamMap, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectByPagySeqId", queryParamMap, page);
    }
	
    public List<Object> doutAgent(String qTxnDateStart, String qTxnDateEnd, String qMchtId,String qTxnState,String qTpamOutId , String qMchtOrderId, String qTxnSeqId,String qPayProduct,String qPagySeqId,String currentBrCode)  throws SnowException{
        /** 查询操作 **/
        DBDao dao = DBDaos.newInstance();
        List<Object> detail = dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.downLoad3",
                RqlParam.map()
                .set("qTxnDateStart", qTxnDateStart)
                .set("qTxnDateEnd",  qTxnDateEnd)
                .set("qMchtId", StringUtils.isBlank(qMchtId) ? "": "%" + qMchtId + "%")
                .set("qTxnState", StringUtils.isBlank(qTxnState) ? "" : qTxnState)
                .set("qTpamOutId", StringUtils.isBlank(qTpamOutId) ? "" : qTpamOutId)
                .set("qMchtOrderId", StringUtils.isBlank(qMchtOrderId) ? "" : qMchtOrderId)
                .set("qTxnSeqId", StringUtils.isBlank(qTxnSeqId) ? "" : qTxnSeqId)
                .set("qPayProduct", StringUtils.isBlank(qPayProduct) ? "" : qPayProduct )
                .set("qPagySeqId", StringUtils.isBlank(qPagySeqId) ? "" : qPagySeqId )
                .set("currentBrCode", StringUtils.isBlank(currentBrCode) ? "" : currentBrCode));
        if (detail.size() == 0) {
            SnowExceptionUtil.throwErrorException("数据记录不存在，无法生成报表");
        }
        return detail;
    }
    
    
    public String selectAccessType(String qAccessType) {
        DBDao dao = DBDaos.newInstance();
        return (String) dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectAccessType",qAccessType);
    }
    public String selectTxnState(String typeNo,String qTxnState) {
        DBDao dao = DBDaos.newInstance();
        return (String) dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTxnState", RqlParam.map().set("typeNo", typeNo).set("qTxnState", qTxnState));
    }
    public String selectTxnType(String TxnType) {
        DBDao dao = DBDaos.newInstance();
        return (String) dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTxnType",TxnType);
    }

    /**
     * 根据支付凭证号查询平台流水号
     * @param qtpamOutId
     * @return 
     */
    public List<Object> queryTxnSeqId(String qtpamOutId) {
        DBDao dao = DBDaos.newInstance();
        qtpamOutId = qtpamOutId+"%";
        return  dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutId",qtpamOutId);
    }

    /**
     * 根据平台流水号关联通道核心表查询通道号
     * @param txnSeqId
     */
    public String getPagyNo(String txnSeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectPagyNo",txnSeqId);
        
    }

    /**
     * 关联支付宝直连
     * @param txnSeqId
     * @param page
     * @return
     */
    public PageResult queryAliDetil(String txnSeqId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryAliDetil", txnSeqId, page);
    }
    
    /**
     * 关联平安通道
     * @param txnSeqId
     * @param page
     * @return
     */
    public PageResult queryMixDetil(String txnSeqId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryMixpayDetil", txnSeqId, page);
    }
    
    /**
     * 关联威富通通道
     * @param txnSeqId
     * @param page
     * @return
     */
    public PageResult querySwfDetil(String txnSeqId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.querySwiftDetil", txnSeqId, page);
    }
    
    /**
     * 关联中信通道
     * @param txnSeqId
     * @param page
     * @return
     */
    public PageResult queryEciDetil(String txnSeqId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryEciDetil", txnSeqId, page);
    }
    
    /**
     * 关联联网通汇
     * @param txnSeqId
     * @param page
     * @return
     */
    public PageResult queryNetDetil(String txnSeqId, Page page) {
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.queryNetDetil", txnSeqId, page);
    }

    
    /**
     * 根据通道流水号查询通道号
     * @param txnSeqId
     */
    public String getPagyNo2(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectPagyNo2",qPagySeqId);
        
    }
    
    
    /**
     * 中信支付凭证号
     * @param qPagySeqId
     */
    public String getTpamOutIdEci(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutIdEci",qPagySeqId);
    }
    /**
     * 平安支付凭证号
     * @param qPagySeqId
     */
    public String getTpamOutIdMix(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutIdMix",qPagySeqId);
    }
    /**
     * 威富通支付凭证号
     * @param qPagySeqId
     */
    public String getTpamOutIdSwf(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutIdSwf",qPagySeqId);
    }
    /**
     * 联网通汇支付凭证号
     * @param qPagySeqId
     */
    public String getTpamOutIdNet(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutIdNet",qPagySeqId);
    }
    /**
     * 支付宝支付凭证号
     * @param qPagySeqId
     */
    public String getTpamOutIdAli(String qPagySeqId) {
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.txnQuery.rql.pbsOrderInfo.selectTpamOutIdAli",qPagySeqId);
    }
}
