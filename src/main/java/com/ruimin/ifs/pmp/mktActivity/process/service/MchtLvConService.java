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
import com.ruimin.ifs.pmp.mktActivity.process.bean.MchtLvConVO;

import java.math.BigDecimal;



/**
 * 
 * 〈商户评级配置〉<br> 
 *  
 * @author zhang_junchi
 */
@Service
public class MchtLvConService extends SnowService {
	
	
		
		/**
		 * 
		 * @return 服务实例，单例
		 * @throws SnowException
		 */
		public static MchtLvConService getInstance() throws SnowException {
			// 根据class获取服务实例
			return ContextUtil.getSingleService(MchtLvConService.class);
		}

		
		/**
		 * 
		 * @return 返回分页结构集合
		 * @throws SnowException
		 */
		public PageResult queryMchtLvConinfo(String qmchtLv,Page page) throws SnowException {
			
			DBDao dao = DBDaos.newInstance();

			return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.mchtLvCon.queryMchtLvCon",RqlParam.map()
					.set("qmchtLv", StringUtils.isBlank(qmchtLv) ? "" : "%"+qmchtLv+"%"), page);
			
		}
		
		
	
		
		/**
		 * 
		 * 新增
		 * @throws SnowException
		 */
		public void addSupply(MchtLvConVO orig) throws SnowException {
			DBDao dao = DBDaos.newInstance();
			
			
			
			dao.insert(orig);
		}
	
		
		
		
		/**
		 * 
		 * @return 指定等级下的最大序列号
		 * @throws SnowException
		 */
		public String queryMaxSeq(String qmchtLv) throws SnowException {
			
			DBDao dao = DBDaos.newInstance();

			return (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.mchtLvCon.queryMaxSeq",RqlParam.map()
					.set("qmchtLv", StringUtils.isBlank(qmchtLv) ? "" : qmchtLv));
		}
		
		
		
		/**
		 * 
		 * 修改
		 * @throws SnowException
		 */
		public void updateSupply(MchtLvConVO vo) throws SnowException {
			DBDao dao = DBDaos.newInstance();
			
			String lvDesc = vo.getLvDesc();
			String mchtLv =vo.getMchtLv();
			String dayDepositMix = vo.getDayDepositMix();
			String busiAmtMix  = vo.getBusiAmtMix();
			BigDecimal busiCntMix  = vo.getBusiCntMix();
			String updateTime =vo.getUpdateTime();
			String updateOpr = vo.getUpdateOpr();
			
			
			String mchtLvSeq =vo.getMchtLvSeq();
			
			 String sql="update tbl_mcht_lv_con set LV_DESC ='"+lvDesc +"',"   
					 +"MCHT_LV = '"+mchtLv +"',"
					 +"BUSI_AMT_MIX = '"+busiAmtMix +"',"
					 +"BUSI_CNT_MIX = "+busiCntMix +","
                     +"DAY_DEPOSIT_MIX= '"+dayDepositMix+"',"
                     +"UPDATE_TIME= '"+updateTime+"',"
                     +"UPDATE_OPR= '"+updateOpr+"'"
+ " where MCHT_LV_SEQ = '" +mchtLvSeq +"'   ";
dao.executeUpdateSql(sql);
	
		}
		
		/**
		 * 
		 * 删除(记录状态置00)
		 * @throws SnowException
		 */
		public void deleteSupply(MchtLvConVO vo) throws SnowException {
			DBDao dao = DBDaos.newInstance();
			String updateTime =vo.getUpdateTime();
			String updateOpr = vo.getUpdateOpr();
			
			String mchtLvSeq =vo.getMchtLvSeq();
			String sql="update tbl_mcht_lv_con set RD_STAT ='00',"
					 +"UPDATE_TIME= '"+updateTime+"',"
                     +"UPDATE_OPR= '"+updateOpr+"'"
+ " where MCHT_LV_SEQ = '" +mchtLvSeq +"'";
dao.executeUpdateSql(sql);
		}
}

