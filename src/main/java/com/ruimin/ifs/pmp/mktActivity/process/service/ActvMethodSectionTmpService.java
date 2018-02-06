package com.ruimin.ifs.pmp.mktActivity.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodConstants;
import com.ruimin.ifs.pmp.mktActivity.common.constants.ActiveMethodSectionConstants;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodInfTmpVO;
import com.ruimin.ifs.pmp.mktActivity.process.bean.MethodSectionInfTmpVO;
import com.ruimin.ifs.rql.RqlMap;
@SnowDoc(desc = "活动方法分段临时信息Service")
@Service
public class ActvMethodSectionTmpService extends SnowService {
	public static ActvMethodSectionTmpService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ActvMethodSectionTmpService.class);
	}
	/**
	 * 分页查询活动方法分段列表
	 * 
	 * @param  methodNo 方法编号
	 * @param  methodTp 方法类型
	 * @param  page 分页对象
	 * */
	public PageResult queryList(String methodNo, String methodTp,Page page) throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodSectionTmp.queryList", RqlParam.map().set("methodNo", methodNo).set("methodTp", methodTp), page);
	}
	/**
	 * 批量添加活动方法分段列表
	 * */
	public void batchAddSection(List<MethodSectionInfTmpVO> listSection)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		if(null == listSection || listSection.size() == 0){
			return;
		}
		dao.insert(listSection);
	}
	/**
	 * 根据活动方法编号删除方法下所有的分段信息
	 * @param methodNo 方法编号
	 * @param trlNo  操作员编号
	 * */
	public int deleteSectionByMethodNo(ActiveMethodInfTmpVO tmpVO,String tlrNo){
		MethodSectionInfTmpVO param =  new MethodSectionInfTmpVO();
		param.setMethodNo(tmpVO.getMethodNo());//方法编号-更新条件
		param.setDataState(ActiveMethodSectionConstants.DATA_STATE_INVALID);//数据状态-无效-更新内容
		param.setUpdateOpr(tlrNo);//操作员-更新内容
		param.setUpdateTime(DateUtil.get14Date());//更新时间-更新内容
		param.setSyncFlag(ActiveMethodConstants.SYNC_STATE_UNDONE);//同步标志-未同步-更新内容
		DBDao dao = DBDaos.newInstance();
		return dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodSectionTmp.deleteSectionByMethodNo", param);
	}
	/**
	 * 根据活动方法编号获取分段信息最大序号
	 * @param methodNo 活动方法编号
	 * */
	public String getMaxSeq(String methodNo)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		return (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodSectionTmp.getMaxSeq",methodNo);
	}
	/**
	 * 批量删除分段信息
	 * */
	public void batchDeleteSection(List<MethodSectionInfTmpVO> sectionList)throws SnowException{
		if(null == sectionList || sectionList.size() == 0){
			return;
		}
		DBDao dao = DBDaos.newInstance();
		for(MethodSectionInfTmpVO param : sectionList){
			dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodSectionTmp.deleteSection", param);
		}
	}
	/**
	 * 批量更新分段信息
	 * */
	public void batchUpdateSection(List<MethodSectionInfTmpVO> sectionList)throws SnowException{
		if(null == sectionList || sectionList.size() == 0){
			return;
		}
		DBDao dao = DBDaos.newInstance();
		for(MethodSectionInfTmpVO param : sectionList){
			dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodSectionTmp.updateSection", param);
		}
	}
}
