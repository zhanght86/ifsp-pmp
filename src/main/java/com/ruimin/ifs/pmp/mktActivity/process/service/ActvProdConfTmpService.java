package com.ruimin.ifs.pmp.mktActivity.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveProdConfTmpVO;
@SnowDoc(desc = "活动产品配置临时信息Service")
@Service
public class ActvProdConfTmpService extends SnowService {
	public static ActvProdConfTmpService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ActvProdConfTmpService.class);
	}
	/**
	 * 批量添加活动产品配置列表
	 * */
	public void batchAddProdConf(List<ActiveProdConfTmpVO> listProdConf)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(null == listProdConf || listProdConf.size() == 0){
			return;
		}
		dao.insert(listProdConf);
	}
	/**
	 * 根据产品编号查询产品列表
	 * */
	public List<Object> getProdListByActiveNo(String prodIds)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String[] prodIdArray = prodIds.split(",");
		
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvProdConfTmp.queryProdListByIds", RqlParam.map().set("prodIdArray", prodIdArray));
	}
	/**
	 * 批量更新活动产品配置列表
	 * */
	public void batchUpdateProdConf(String activeNo,List<ActiveProdConfTmpVO> prdList)throws SnowException{
//		//转换产品ID为List，用以判断
//		List<String> prodIdList = new ArrayList<String>();
//		for(String prodId : prodIds){
//			prodIdList.add(prodId);
//		}
//		//查询该活动下所有的产品配置记录
//		DBDao dao = DBDaos.newInstance();
//		List<Object> existList = dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.ActvProdConfTmp.queryProdListWithoutDataState", activeNo);
//		
//		 addList = new ArrayList<ActiveProdConfTmpVO>();
//		List<ActiveProdConfTmpVO> updateList = new ArrayList<ActiveProdConfTmpVO>();
//		for(Object obj :existList){
//			ActiveProdConfTmpVO prod = (ActiveProdConfTmpVO) obj;
//			if(prodIdList.contains(prod.getPrdId())){
//				if(prod.getDataState().equals(ActiveProdConfConstant.DATA_STATE_INVALID)){
//					prod.setDataState(ActiveProdConfConstant.DATA_STATE_VALID);//数据有效状态-1-有效
//					prod.setUpdateOpr(tlrNo);//更新操作员-当前登录用户
//					prod.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
//					prod.setSyncFlag(ActiveProdConfConstant.SYNC_STATE_UNDONE);//同步标志-0-已变更未同步
//					//添加到更新list中
//					updateList.add(prod);
//				}
//			}else{
//				if(prod.getDataState().equals(ActiveProdConfConstant.DATA_STATE_VALID)){
//					prod.setDataState(ActiveProdConfConstant.DATA_STATE_INVALID);//数据有效状态-0-无效
//					prod.setUpdateOpr(tlrNo);//更新操作员-当前登录用户
//					prod.setUpdateTime(DateUtil.get14Date());//更新时间-当前系统时间
//					prod.setSyncFlag(ActiveProdConfConstant.SYNC_STATE_UNDONE);//同步标志-0-已变更未同步
//					//添加到更新list中
//					updateList.add(prod);
//				}
//			}
//		}
//		
		//批量删除活动下产品配置信息
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvProdConfTmp.deleteByActiveNo", activeNo);
		//批量添加活动产品配置
		batchAddProdConf(prdList);
	}
}
