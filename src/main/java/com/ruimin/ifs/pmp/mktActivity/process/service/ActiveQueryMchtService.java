package com.ruimin.ifs.pmp.mktActivity.process.service;

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
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMchtInInfTmpVO;

import java.util.List;

@Service
public class ActiveQueryMchtService extends SnowService{
	/**
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static ActiveQueryMchtService getInstance() throws SnowException {
		// 根据class获取服务实例
		return ContextUtil.getSingleService(ActiveQueryMchtService.class);
	}
	public PageResult queryForMap(String mchtId,String mchtName,String mchtAreaNo,
			                      String mchtMcc,String inFlg,String mchtLevl,String activeNo, Page page) throws SnowException {
		// 获取一个DAO对象
		DBDao dao = DBDaos.newInstance();
		// 组装模糊查询
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.activeQueryMcht.queryMchtInfo",
				   RqlParam.map()
	               .set("mchtId", StringUtils.isBlank(mchtId) ? "" : "%"+mchtId+"%")
	               .set("mchtName", StringUtils.isBlank(mchtName) ? "" : "%"+mchtName+"%")
	               .set("mchtAreaNo", StringUtils.isBlank(mchtAreaNo) ? "" : "%"+mchtAreaNo+"%")
	               .set("mchtMcc", StringUtils.isBlank(mchtMcc) ? "" : "%"+mchtMcc+"%")
	               .set("inFlg", StringUtils.isBlank(inFlg) ? "" : "%"+inFlg+"%")
	               .set("mchtLevl", StringUtils.isBlank(mchtLevl) ? "" : "%"+mchtLevl+"%").set("activeNo", activeNo),
	                page);
	}
	/**
	 * 插入互动商户参与信息临时表
	 * @param tempVO
	 * @throws SnowException
	 */
	public void insert(ActiveMchtInInfTmpVO tempVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(tempVO);
	}

	
	/**
	 * 
	 * 已退出商户重新参与
	 * @throws SnowException
	 */
	public void reIn(ActiveMchtInInfTmpVO tempVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.activeQueryMcht.reIn", tempVO);
	}
	/**
	 * 
	 * 退出商户
	 * @throws SnowException
	 */
	public void quite(ActiveMchtInInfTmpVO tempVO) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.activeQueryMcht.quite", tempVO);
	}
	
	/**
     * 根据商户编号查询商户参加的活动编号
     * @param mchtId
     * @throws SnowException 
     */
    /*public List<Object> selectActNo(String mchtId){
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.activeQueryMcht.selectActNo", 
                RqlParam.map().set("mchtId", mchtId));
    }*/
    
    
    public List<Object> selectActNo(String mchtId) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.executeQuerySql("SELECT t.ACTIVE_NO from tbl_active_mcht_in_inf t LEFT JOIN tbl_active_inf_tmp a "
                + "on t.ACTIVE_NO = a.ACTIVE_NO  WHERE t.MCHT_ID ='"+mchtId+"' and t.IN_FLG = '01' AND a.ACTIVE_STAT in ('10','11')",
                String.class);
    }
    
    /**
     * 根据活动编号查询活动优先级
     * @param activeNo
     * @throws SnowException 
     */
    public String selectActLv(String activeNo){
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.activeQueryMcht.selectActLv", RqlParam.map().set("activeNo",activeNo));
    }
}
