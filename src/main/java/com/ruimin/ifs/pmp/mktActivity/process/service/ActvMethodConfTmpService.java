package com.ruimin.ifs.pmp.mktActivity.process.service;

import java.util.List;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveMethodConfTmpVO;
import com.ruimin.ifs.util.StringUtil;

@SnowDoc(desc = "活动方法配置临时信息Service")
@Service
public class ActvMethodConfTmpService extends SnowService {
	public static ActvMethodConfTmpService getInstance() throws SnowException {
		return ContextUtil.getSingleService(ActvMethodConfTmpService.class);
	}
	/**
	 * 根据页面传递参数查询活动列表
	 */
	public PageResult queryAll(String activeNo,Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodConfTmp.queryAll",
				RqlParam.map().set("activeNo", activeNo),page);
	}
	/**
	 * 根据活动编号获取当前活动下最大的方法配置序号
	 * */
	public int  getMaxConfigSeq(String activeNo)throws SnowException{
		DBDao dao = DBDaos.newInstance();
		String configSeq = (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodConfTmp.getMaxConfigSeq",activeNo);
		if(StringUtil.isEmpty(configSeq)){
			return 0;
		}
		return Integer.valueOf(configSeq);
	}
	/**
	 * 批量新增活动方法配置
	 * */
	public void  batchAdd(List<ActiveMethodConfTmpVO> list)throws SnowException{
		if(null == list || list.size() == 0){
			return;
		}
		DBDao dao = DBDaos.newInstance();
		dao.insert(list);
	}
	/**
	 * 批量更新活动方法配置
	 * */
	public void  batchUpdate(List<ActiveMethodConfTmpVO> list)throws SnowException{
		if(null == list || list.size() == 0){
			return;
		}
		DBDao dao = DBDaos.newInstance();
		dao.executeBatchUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodConfTmp.updateConfig", list);
	}
	/**
	 * 批量删除活动方法配置
	 * */
	public void  batchDelete(List<ActiveMethodConfTmpVO> list)throws SnowException{
		if(null == list || list.size() == 0){
			return;
		}
		DBDao dao = DBDaos.newInstance();
		dao.executeBatchUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvMethodConfTmp.deleteConfig", list);
	}
}
