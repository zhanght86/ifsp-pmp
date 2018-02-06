package com.ruimin.ifs.pmp.risk.process.service;

import java.util.List;

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
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.process.bean.RiskBaseCfg;
import com.ruimin.ifs.pmp.risk.process.bean.RiskProdMatchVo;
import com.ruimin.ifs.pmp.risk.process.bean.RiskValueOperateVo;

@Service
@SnowDoc(desc = "风控管理")
public class RiskMngService extends SnowService{
	/**
	 * 获取实例
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static RiskMngService getInstance() throws SnowException{
		return ContextUtil.getSingleService(RiskMngService.class);
	}	
	
	/**
	 * 查询：风控管理数据列表
	 * @param qriskId 风险编号【模糊查询】
	 * @param qriskName 风险名称【模糊查询】
	 * @param qprodId 产品编号
	 * @param qriskStauts 风险状态
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult queryMain(String qriskId, String qriskName, String qprodId, String qriskStauts, Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.risk.rql.riskMng.queryMain", RqlParam.map()
										.set("qriskId",  StringUtils.isBlank(qriskId) ? "" : "%"+qriskId+"%")
										.set("qriskName",  StringUtils.isBlank(qriskName) ? "" : "%"+qriskName+"%")
										.set("qprodId",  StringUtils.isBlank(qprodId) ? "" :"%"+qprodId+"%")
										.set("qriskStauts",  StringUtils.isBlank(qriskStauts) ? "" : qriskStauts),page);
	}

	/**
	 * 生成风控编号
	 * @return
	 * @throws SnowException
	 */
	public String genRiskId() throws SnowException{
		StringBuffer riskId = new StringBuffer();
		DBDao dao = DBDaos.newInstance();
		String currentMaxId = (String) dao.selectOne("com.ruimin.ifs.pmp.risk.rql.riskMng.getMaxId","");
		if (StringUtil.isEmpty(currentMaxId)) {
			currentMaxId = "RM100000";
		}
		int nextId = Integer.parseInt(currentMaxId.substring(3).trim())+1;
		riskId.append("RM1").append(StringUtil.leftPad(String.valueOf(nextId),5,"0"));
		return riskId.toString();
	}

	/**
	 * 新增风控规则基本配置表信息
	 * @param riskBaseCfg
	 * @throws SnowException
	 */
	public void saveRiskBase(RiskBaseCfg riskBaseCfg) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskMng.addRiskBase",riskBaseCfg);
		
	}

	/**
	 * 新增风控规则产品映射表信息
	 * @param riskProdMatchVoList
	 * @throws SnowException
	 */
	public void saveRrodMatch(List<RiskProdMatchVo> riskProdMatchVoList) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(riskProdMatchVoList.size() == 0 || null == riskProdMatchVoList){
			return ;
		}
		dao.insert(riskProdMatchVoList);
	}
	/**
	 * 新增风控规则阈值操作表信息
	 * @param riskValueOperateVoList
	 * @throws SnowException
	 */
	public void saveRiskValueOperate(List<RiskValueOperateVo> riskValueOperateVoList) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(riskValueOperateVoList.size()==0 || null == riskValueOperateVoList){
			return ;
		}
		dao.insert(riskValueOperateVoList);
		
	}
	/**
	 * 修改风控规则基本配置表信息
	 * @param riskBaseCfg
	 * @throws SnowException
	 */
	public void modifyRiskBase(RiskBaseCfg riskBaseCfg) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskMng.modifyRiskBase", riskBaseCfg);
		
	}
	
	/**
	 * 根据风控编号删除风控阀值操作信息
	 * @param riskId
	 * @throws SnowException
	 */
	public void deleteRiskValueOperate(String riskId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from RISK_VALUE_OPERATE where RISK_ID = '"+riskId+"'";
		dao.executeUpdateSql(sql);
		
	}
	/**
	 * 根据风控编号删除风控产品
	 * @param riskId
	 * @throws SnowException
	 */
	public void deleteRrodMatch(String riskId) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String sql = "delete from RISK_PROD_MATCH where RISK_ID = '"+riskId+"'";
		dao.executeUpdateSql(sql);
		
	}
	/**
	 * 启用停用风控信息
	 * @param riskBaseCfg
	 * @throws SnowException
	 */
	public void statusChange(RiskBaseCfg riskBaseCfg) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.risk.rql.riskMng.statusChange",riskBaseCfg );
	}
}
