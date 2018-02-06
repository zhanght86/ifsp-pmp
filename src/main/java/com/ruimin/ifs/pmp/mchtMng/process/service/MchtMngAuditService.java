package com.ruimin.ifs.pmp.mchtMng.process.service;

import java.util.List;

import org.slf4j.Logger;

import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtMngConstants;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaesInfoRealMapping;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfoReal;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtContractInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtPicInfoReal;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.ReflectionUtil;

@Service
@SnowDoc(desc = "商户管理")
public class MchtMngAuditService extends SnowService {
    
    Logger log = SnowLog.getLogger(MchtMngAuditService.class);
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static MchtMngAuditService getInstance() throws SnowException {
		return ContextUtil.getSingleService(MchtMngAuditService.class);
	}

	/**
	 * 商户临时表数据中，商户状态MCHT_STAT，00 正常；同步状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateMchtInfoTmp(String auditId, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最近更新时间
		String time = BaseUtil.getCurrentDateTime();
		String sql = "update PBS_MCHT_BASE_INFO_TMP set MCHT_STAT ='" + MchtMngConstants.MCHT_STAT_NORMAL
				+ "',SYNC_STATE='" + MchtMngConstants.SYNC_STATE_01 + "',LAST_UPD_DATE_TIME='" + time
				+ "',LAST_UPD_TLR='" + tlrno + "' where AUDIT_ID='" + auditId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 根据商户号查询，临时表图片信息 pbsMchtPicInfo
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public List<PbsMchtPicInfo> selectPbsMchtPicInfo(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select * from PBS_MCHT_PIC_INFO_TMP where MCHT_ID='" + mchtId + "'", PbsMchtPicInfo.class);
	}
	
	   /**
     * 修改通道显示的商户全名
     * 
     * @throws SnowException
     */
    public void updPagySubpagyName(PbsMchtBaseInfo pbsMchtBaseInfo) throws SnowException {
        DBDao dao = DBDaos.newInstance();
        String sql = " UPDATE PAGY_SUB_MCHT_INFO set PAY_MCHT_NAME='"+pbsMchtBaseInfo.getMchtName()+ "'where PAY_MCHT_NO='"+pbsMchtBaseInfo.getMchtId()+ "'";
        dao.executeUpdateSql(sql);
    }
	
	/**
	 * 把审核通过信息插入到正式表中，同时把同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @throws SnowException
	 */
	public void insertMchtBaseInfo(PbsMchtBaseInfo pbsMchtBaseInfo, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		//2017-04-01新增  解决bug  偶发性出现 商户临时信息表中数据已经审核完  但操作员没有去刷新页面
		PbsMchtBaesInfoRealMapping pbir = MchtMngService.getInstance().queryMchtAreaNoMapp(pbsMchtBaseInfo.getMchtId());
		if(pbir != null){//正式表中已经存在审核完毕的该商户信息
			// 抛出异常
			SnowExceptionUtil.throwWarnException("本次审核记录已经审核完毕,请刷新页面或重新执行查询!");
		}
		// 获取正式表实体对象
		PbsMchtBaseInfoReal PbsMchtBaseInfoReal = new PbsMchtBaseInfoReal();
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		// 调用工具类，把临时表对象内容copy到正式表中
		PbsMchtBaseInfo pbsMchtBaseInfoByMchtId = (PbsMchtBaseInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtBaseInfoByMchtIdAudit",
				RqlParam.map().set("mchtId", pbsMchtBaseInfo.getMchtId()));
		ReflectionUtil.copyProperties(pbsMchtBaseInfoByMchtId, PbsMchtBaseInfoReal);
		// 最近更新操作员，最近更新时间，把正式表状态改为00：已同步；
		PbsMchtBaseInfoReal.setMchtStat(MchtMngConstants.MCHT_STAT_NORMAL);
		PbsMchtBaseInfoReal.setLastUpdTlr(tlrno);
		PbsMchtBaseInfoReal.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		PbsMchtBaseInfoReal.setSyncState(MchtMngConstants.SYNC_STATE_00);// 已同步
		// 插入正式表中
		dao.insert(PbsMchtBaseInfoReal);
	}

	/**
	 * 临时表状态改为已同步
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateSyncState(String auditId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = "update PBS_MCHT_BASE_INFO_TMP set SYNC_STATE='" + MchtMngConstants.SYNC_STATE_00
				+ "' where AUDIT_ID='" + auditId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 把图片信息表数据插入到正式表中
	 * 
	 * @param pbsMchtPicInfo
	 * @throws SnowException
	 */
	public void insertMchtPicInfo(List<PbsMchtPicInfo> list, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 临时表实体内容，对应到正式表实体对象中，最近操作时间，操作人
		for (PbsMchtPicInfo pbsMchtPicInfo : list) {
			// 获取正式表图片实体对象
			PbsMchtPicInfoReal pbsMchtPicInfoReal = new PbsMchtPicInfoReal();
			ReflectionUtil.copyProperties(pbsMchtPicInfo, pbsMchtPicInfoReal);
			// 最近更新操作员，最近更新时间，把正式表状态改为
			pbsMchtPicInfoReal.setLastUpdTlr(tlrno);
			pbsMchtPicInfoReal.setLastUpdAteTime(BaseUtil.getCurrentDateTime());
			// 插入图片信息正式表中
			//dao.insert(pbsMchtPicInfoReal);
			 //查询正式表是否有记录
	        Object selectOne = dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.selPicReal", 
	                    RqlParam.map().set("mchtId", pbsMchtPicInfoReal.getMchtId())
	                    .set("mchtPicType",pbsMchtPicInfoReal.getMchtPicType())
	                    );
	            
	            if (IfspDataVerifyUtil.isNotBlank(selectOne)) {
	                log.info("图片更新:type["+pbsMchtPicInfoReal.getMchtPicType()+"];id:["+pbsMchtPicInfoReal.getMchtPicId()+"]");
	                dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updPicReal", RqlParam.map()
	                        .set("picSeqNo",pbsMchtPicInfoReal.getPicSeqNo())
	                        .set("mchtPicId",pbsMchtPicInfoReal.getMchtPicId())
	                        .set("mchtPicPath",pbsMchtPicInfoReal.getMchtPicPath())
	                        .set("crtTlr",pbsMchtPicInfoReal.getCrtTlr())
	                        .set("crtDateTime",pbsMchtPicInfoReal.getCrtDateTime())
	                        .set("lastUpdAteTime",pbsMchtPicInfoReal.getLastUpdAteTime())
	                        .set("mchtId",pbsMchtPicInfoReal.getMchtId())
	                        .set("mchtPicType",pbsMchtPicInfoReal.getMchtPicType()));
	            }else {
	                log.info("图片新增:type["+pbsMchtPicInfoReal.getMchtPicType()+"];id:["+pbsMchtPicInfoReal.getMchtPicId()+"]");
	                dao.insert(pbsMchtPicInfoReal);
	            };
			
		}
	}

	/**
	 * 修改【商户信息】，审核通过时
	 * 
	 * @param mchtVo
	 *            商户信息实体类
	 * @throws SnowException
	 */
	public void updMcht(PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtBaseInfoReal PbsMchtBaseInfoReal, String tlrno)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsMchtBaseInfo pbsMchtBaseInfoByMchtId = (PbsMchtBaseInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtBaseInfoByMchtIdAudit",
				RqlParam.map().set("mchtId", pbsMchtBaseInfo.getMchtId()));
		// 修改审核通过，把临时表修改的那条数据copy到对应的正式表中
		ReflectionUtil.copyProperties(pbsMchtBaseInfoByMchtId, PbsMchtBaseInfoReal);
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		PbsMchtBaseInfoReal.setMchtStat(MchtMngConstants.MCHT_STAT_NORMAL);
		PbsMchtBaseInfoReal.setLastUpdTlr(tlrno);
		PbsMchtBaseInfoReal.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		PbsMchtBaseInfoReal.setSyncState(MchtMngConstants.SYNC_STATE_00);// 已同步
		dao.update(PbsMchtBaseInfoReal);
	}

	/**
	 * 根据商户号，删除对应的图片信息
	 * 
	 * @param pbsMchtPicInfo
	 * @throws SnowException
	 */
	public void deleteMchtPicInfo(PbsMchtBaseInfo pbsMchtBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String mchtId = pbsMchtBaseInfo.getMchtId();
		String sql = "delete from PBS_MCHT_PIC_INFO where MCHT_ID='" + mchtId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 根据商户号，查询正式表中是否有图片信息
	 * 
	 * @param pbsMchtBaseInfo
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtPicInfoReal selectMchtPicInfoByMchtId(PbsMchtBaseInfo pbsMchtBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String mchtId = pbsMchtBaseInfo.getMchtId();
		return (PbsMchtPicInfoReal) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtPicInfoByMchtId",
				RqlParam.map().set("mchtId", mchtId));
	}

	/**
	 * 修改图片信息，审核通过时，此时五条记录对应修改
	 * 
	 * @param mchtVo
	 *            商户信息实体类
	 * @throws SnowException
	 */
	public void updPic(List<PbsMchtPicInfo> list, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取正式表实体对象
		PbsMchtPicInfoReal pbsMchtPicInfoReal = new PbsMchtPicInfoReal();
		for (PbsMchtPicInfo pbsMchtPicInfo : list) {
			ReflectionUtil.copyProperties(pbsMchtPicInfo, pbsMchtPicInfoReal);
			// 最近更新操作员，最近更新时间，把正式表状态改为
			pbsMchtPicInfoReal.setLastUpdTlr(tlrno);
			pbsMchtPicInfoReal.setLastUpdAteTime(BaseUtil.getCurrentDateTime());
			//查询正式表是否有记录
		Object selectOne = dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.selPicReal", 
			        RqlParam.map().set("mchtId", pbsMchtPicInfoReal.getMchtId())
			        .set("mchtPicType",pbsMchtPicInfoReal.getMchtPicType())
			        );
			
			if (IfspDataVerifyUtil.isNotBlank(selectOne)) {
			    log.info("图片更新:type["+pbsMchtPicInfoReal.getMchtPicType()+"];id:["+pbsMchtPicInfoReal.getMchtPicId()+"]");
			    dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.mchtMng.updPicReal", RqlParam.map()
                        .set("picSeqNo",pbsMchtPicInfoReal.getPicSeqNo())
                        .set("mchtPicId",pbsMchtPicInfoReal.getMchtPicId())
                        .set("mchtPicPath",pbsMchtPicInfoReal.getMchtPicPath())
                        .set("crtTlr",pbsMchtPicInfoReal.getCrtTlr())
                        .set("crtDateTime",pbsMchtPicInfoReal.getCrtDateTime())
                        .set("lastUpdAteTime",pbsMchtPicInfoReal.getLastUpdAteTime())
                        .set("mchtId",pbsMchtPicInfoReal.getMchtId())
                        .set("mchtPicType",pbsMchtPicInfoReal.getMchtPicType()));
            }else {
                log.info("图片新增:type["+pbsMchtPicInfoReal.getMchtPicType()+"];id:["+pbsMchtPicInfoReal.getMchtPicId()+"]");
                dao.insert(pbsMchtPicInfoReal);
            };
			
		}
	}

	/**
	 * 商户临时表数据中，商户状态，SYNC_STATE 01：已变更未同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateMchtInfoTmpByFrz(PbsMchtBaseInfo pbsMchtBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		String sql = null;
		String mchtStat = pbsMchtBaseInfo.getMchtStat();
		String syncState = pbsMchtBaseInfo.getSyncState();
		String auditId = pbsMchtBaseInfo.getAuditId();
		sql = "update PBS_MCHT_BASE_INFO_TMP set MCHT_STAT ='" + mchtStat + "',SYNC_STATE='" + syncState
				+ "' where AUDIT_ID='" + auditId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 商户临时表数据中，商户状态MCHT_STAT，08 新增被拒绝；同步状态，SYNC_STATE 00：已同步；
	 * 
	 * @param auditId
	 * @throws SnowException
	 */
	public void updateMchtInfoTmpRefuse(String auditId, String tlrno) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取最近更新时间
		String time = BaseUtil.getCurrentDateTime();
		String sql = "update PBS_MCHT_BASE_INFO_TMP set MCHT_STAT ='" + MchtMngConstants.MCHT_STAT_08 + "',SYNC_STATE='"
				+ MchtMngConstants.SYNC_STATE_00 + "',LAST_UPD_TLR='" + tlrno + "',LAST_UPD_DATE_TIME='" + time
				+ "' where AUDIT_ID='" + auditId + "'";
		dao.executeUpdateSql(sql);
	}

	/**
	 * 根据商户号查询，正式表中商户基本信息
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public PbsMchtBaseInfoReal selectMchtInfoTmpReal(String mchtId) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (PbsMchtBaseInfoReal) dao.selectOne("com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtInfoTmpReal",
				RqlParam.map().set("mchtId", mchtId));
	}

	/**
	 * 回滚数据方法，修改，冻结，恢复，注销，审核被拒绝时
	 * 
	 * @param mchtId
	 * @return
	 * @throws SnowException
	 */
	public void backMchtInfo(PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtBaseInfoReal pbsMchtBaseInfoReal, String tlrno,String auditId)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsMchtBaseInfo pbsMchtBaseInfoByMchtId = (PbsMchtBaseInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtBaseInfoByMchtIdAudit",
				RqlParam.map().set("mchtId", pbsMchtBaseInfo.getMchtId()));
		// 把正式表数据copy到临时表中
		ReflectionUtil.copyProperties(pbsMchtBaseInfoReal, pbsMchtBaseInfoByMchtId);
		// 临时表实体内容，对应到正式表实体对象中，临时表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		pbsMchtBaseInfoByMchtId.setLastUpdTlr(tlrno);
		pbsMchtBaseInfoByMchtId.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		pbsMchtBaseInfoByMchtId.setSyncState(MchtMngConstants.SYNC_STATE_00);// 已同步
		//-------------------2017-12-20  为支持商户进件查询此处审核编号不回滚--------------------------
		pbsMchtBaseInfoByMchtId.setAuditId(auditId);
		// 正式表实体内容，对应到临时表实体对象中
		dao.update(pbsMchtBaseInfoByMchtId);
	}

	/**
	 * 根据商户号，查询出来正式表中，图片的信息
	 * 
	 * @param pbsMchtBaseInfo
	 * @return
	 * @throws SnowException
	 */
	public List<PbsMchtPicInfoReal> selectMchtPicInfoReal(PbsMchtBaseInfo pbsMchtBaseInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 获取商户号
		String mchtId = pbsMchtBaseInfo.getMchtId();
		return dao.queryAll("select * from PBS_MCHT_PIC_INFO where MCHT_ID='" + mchtId + "'", PbsMchtPicInfoReal.class);
	}

	/**
	 * 正式表数据回滚到临时表中
	 * 
	 * @param list
	 * @param listPic
	 * @throws SnowException
	 */
	public void backMchtPicInfo(List<PbsMchtPicInfo> list, List<PbsMchtPicInfoReal> listPic, String tlrno)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 当临时表图片信息为空，修改被拒绝时，两种情况：1.正式表不为空，则插入到临时表中。2.正式表为空，此时不做操作
		if (list == null || list.size() == 0) {
			if (listPic != null && listPic.size() != 0) {
				for (PbsMchtPicInfoReal pbsMchtPicInfoReal : listPic) {
					PbsMchtPicInfo pbsMchtPicInfo = new PbsMchtPicInfo();
					ReflectionUtil.copyProperties(pbsMchtPicInfoReal, pbsMchtPicInfo);
					// 最近更新操作员，最近更新时间
					pbsMchtPicInfo.setLastUpdTlr(tlrno);
					pbsMchtPicInfo.setLastUpdAteTime(BaseUtil.getCurrentDateTime());
					// 正式表不为空，则插入到临时表中。
					dao.insert(pbsMchtPicInfo);
				}
			}
		}
		// 当临时表数据不为空，修改被拒绝时，两种情况：1.正式表为空，则删除临时表中图片信息。2.正式表不为空则回滚数据到临时表中
		if (list != null && list.size() != 0) {
			// 获取商户号
			PbsMchtPicInfo pbsMchtPicInfo = list.get(0);
			String mchtId = pbsMchtPicInfo.getMchtId();
			if (listPic == null || listPic.size() == 0) {
				String sql = "delete from PBS_MCHT_PIC_INFO_TMP where MCHT_ID='" + mchtId + "'";
				dao.executeUpdateSql(sql);
			}
			if (listPic != null && listPic.size() != 0) {
				for (int i = 0; i < listPic.size(); i++) {
					// 正式表数据copy到临时表中
					ReflectionUtil.copyProperties(listPic.get(i), list.get(i));
					// 最近更新操作员，最近更新时间
					list.get(i).setLastUpdTlr(tlrno);
					list.get(i).setLastUpdAteTime(BaseUtil.getCurrentDateTime());
					dao.update(list.get(i));
				}
			}
		}

	}

	/**
	 * 冻结/解冻方法,注销，审核通过时
	 * 
	 * @param mchtVo
	 *            商户信息实体类
	 * @throws SnowException
	 */
	public void updMchtFrz(PbsMchtBaseInfo pbsMchtBaseInfo, PbsMchtBaseInfoReal PbsMchtBaseInfoReal, String tlrno)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		PbsMchtBaseInfo pbsMchtBaseInfoByMchtId = (PbsMchtBaseInfo) dao.selectOne(
				"com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.selectMchtBaseInfoByMchtIdAudit",
				RqlParam.map().set("mchtId", pbsMchtBaseInfo.getMchtId()));
		// 审核通过，把临时表修改的那条数据copy到对应的正式表中
		ReflectionUtil.copyProperties(pbsMchtBaseInfoByMchtId, PbsMchtBaseInfoReal);
		// 临时表实体内容，对应到正式表实体对象中，正式表状态改为SYNC_STATE 00：已同步；最近操作时间，操作人
		PbsMchtBaseInfoReal.setLastUpdTlr(tlrno);
		PbsMchtBaseInfoReal.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
		PbsMchtBaseInfoReal.setSyncState(MchtMngConstants.SYNC_STATE_00);// 已同步
		dao.update(PbsMchtBaseInfoReal);
	}

    /**
     * @param mchtId
     * @param accountType
     * @param setlAcctName
     */
    public void syncContractAndPbsAssit(String mchtId, String accountType, String setlAcctName) throws SnowException{
        DBDao dao = DBDaos.newInstance();
        //查询商户下有没有配置过合同
        String sql = "SELECT * FROM pbs_mcht_contract_info WHERE MCHT_ID = '"+mchtId+"'";
        List<Object> list = dao.executeQuerySql(sql, PbsMchtContractInfo.class);
        //有则更新正式表与临时表信息
        if (IfspDataVerifyUtil.isNotEmptyList(list)) {
            log.info("开始更新商户("+mchtId+")合同临时表....更新字段名为accountType,setlAcctName.....");
            dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.updContrTmp", 
                 RqlParam.map().set("mchtId", mchtId).set("accountType", accountType).set("setlAcctName", setlAcctName));
            log.info("更新结束.....");
            log.info("开始更新商户("+mchtId+")合同正式表....更新字段名为accountType,setlAcctName.....");
            dao.executeUpdate("com.ruimin.ifs.pmp.mchtMng.rql.checkMchtMng.updContr", 
                 RqlParam.map().set("mchtId", mchtId).set("accountType", accountType).set("setlAcctName", setlAcctName));
            log.info("更新结束.....");
        }
        
    }
}
