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
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.mktActivity.process.bean.ActiveCycleConfTmpVO;
@SnowDoc(desc = "活动周期配置临时信息Service")
@Service
public class ActvCycleConfTmpService extends SnowService {
    public static ActvCycleConfTmpService getInstance() throws SnowException {
        return ContextUtil.getSingleService(ActvCycleConfTmpService.class);
    }
    public PageResult queryAll(String methodNo,Page page)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return dao.selectList("com.ruimin.ifs.pmp.mktActivity.rql.actvCycleConfTmp.queryList",methodNo, page);
    }
    /**
     * 批量添加活动周期列表
     * */
    public void batchAddCycle(List<ActiveCycleConfTmpVO> listCycle)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        if(null == listCycle || listCycle.size() == 0){
            return;
        }
        dao.insert(listCycle);
    }
    /**
     * 批量修改活动周期列表
     * */
    public void batchUpdateCycle(List<ActiveCycleConfTmpVO> listCycle)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        if(null == listCycle || listCycle.size() == 0){
            return;
        }
        for(ActiveCycleConfTmpVO param : listCycle){
            dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvCycleConfTmp.updateCycle", param);
        }
    }
    /**
     * 批量删除活动周期列表
     * */
    public void batchDeleteCycle(List<ActiveCycleConfTmpVO> listCycle)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        if(null == listCycle || listCycle.size() == 0){
            return;
        }
        for(ActiveCycleConfTmpVO param : listCycle){
            dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvCycleConfTmp.deleteCycle", param);
        }
    }
    /**
     * 批量删除活动周期列表
     * */
    public void batchDeleteCycle(ActiveCycleConfTmpVO param)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        dao.executeUpdate("com.ruimin.ifs.pmp.mktActivity.rql.actvCycleConfTmp.deleteCycleByActiveNo", param);
    }
    /**
     * 根据活动编号获取最大序号
     * */
    public String getMaSeqByActiveNo(String activeNo)throws SnowException{
        DBDao dao = DBDaos.newInstance();
        return (String)dao.selectOne("com.ruimin.ifs.pmp.mktActivity.rql.actvCycleConfTmp.getMaxSeq",activeNo);
    }
}
