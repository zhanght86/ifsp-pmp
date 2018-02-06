/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.pmp.sysConf.process.service 
 * FileName: PmpBankInfoServiec.java
 * Author:   ZLJ
 * Date:     2016年7月11日 下午1:49:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   ZLJ           2016年7月11日下午1:49:49                     1.0                  
 *===============================================================================================
 */

package com.ruimin.ifs.pmp.sysConf.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.Page;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.pmp.sysConf.process.bean.PassInfoVO;
import com.ruimin.ifs.pmp.sysConf.process.bean.PayBankBaseInfo;
import com.ruimin.ifs.pmp.sysConf.process.bean.PmpBankLimitInfoVO;
import com.ruimin.ifs.pmp.sysConf.process.bean.PmpRelBankPassBankVO;

import java.util.Map;

//com.ruimin.ifs.pmp.sysConf.process 
//com.ruimin.ifs.paydep.sysConf.process
/**
 * 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年7月11日 <br>
 * 作者：ZLJ <br>
 * 说明：银行信息 service 类<br>
 */
@Service
public class PmpBankInfoService extends SnowService {

	/**
	 * 获取服务类单例
	 * 
	 * @return 银行信息实例
	 * @throws SnowException
	 */
	public static PmpBankInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(PmpBankInfoService.class);
	}

	/**
	 * 添加银行 信息
	 * 
	 * @param bankInfo
	 *            银行信息
	 * @throws SnowException
	 */
	public void save(PayBankBaseInfo bankInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		// 将银行信息持久化到数据库中
		dao.insert(bankInfo);
	}

	/**
	 * 修改银行信息
	 * 
	 * @param bankInfo
	 *            银行信息
	 * @throws SnowException
	 */
	public void update(PayBankBaseInfo newBankInfo) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.pmpBankInfo.updPmpBankInfo", newBankInfo);
	}

	/**
	 * 添加银行限额信息
	 * 
	 * @param bankLimitInfo
	 * @throws SnowException
	 */
	public void save(PmpBankLimitInfoVO bankLimitInfo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(bankLimitInfo);
	}

	/**
	 * 获取最大的银行编号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String getMaxBankNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		Object maxBankNo = dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.pmpBankInfo.findMaxBankNo");
		return maxBankNo == null ? "" : maxBankNo.toString();
	}

	/**
	 * 获取最大的银行限额编号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public String getMaxBankLimitNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		Object maxBankLimitNo = dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.pmpBankLimitInfo.findMaxBankLimitNo");
		return maxBankLimitNo == null ? "" : maxBankLimitNo.toString();
	}

	/**
	 * 查询银行信息
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param page
	 *            分页信息
	 * @return
	 * @throws SnowException
	 */
	public PageResult listBank(Map<String, String> queryMap, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.pmpBankInfo.queryBank", queryMap, page);
	}

	/**
	 * 按银行ID查询银行信息
	 * 
	 * @param id
	 * @return
	 * @throws SnowException
	 */
	public PayBankBaseInfo getBankInfo(String id) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(PayBankBaseInfo.class, id);
	}

	/**
	 * 按银行编号删除限额信息
	 * 
	 * @param bankNo
	 * @throws SnowException
	 */
	public void deleteBankLimitByBankNo(String bankNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.pmpBankLimitInfo.deleteBankLimitByBankNo",
				RqlParam.map().set("bankNo", bankNo));
	}

	/**
	 * 按银行编号查询限额列表
	 * 
	 * @param bankNo
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult listBankLimit(String bankNo, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.pmpBankLimitInfo.findBankLimitByBankNo",
				RqlParam.map().set("bankNo", bankNo), page);
	}

	/**
	 * 按银行编号修改银行有效状态
	 * 
	 * @param map
	 *            bankNo --银行编号 dataState -- 数据有效状态
	 * @throws SnowException
	 */
	public void updateStateByBankNo(Map<String, String> map) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.pmpBankInfo.updateState", map);
	}

	/**
	 * 按产品银行编号查询通道配置信息
	 * 
	 * @param bankNo
	 *            产品银行编号
	 * @param page
	 * @return
	 */
	public PageResult listPmpRelBankPassBank(String bankNo, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.pmpRelBankPassBank.findByBankNo",
				RqlParam.map().set("bankNo", bankNo), page);
	}

	/**
	 * 获取最大的记录编号
	 * 
	 * @returns
	 * @throws SnowException
	 */
	public String getMaxRelNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		Object maxRelNo = dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.pmpRelBankPassBank.findMaxRelNo");
		return maxRelNo == null ? "" : maxRelNo.toString();
	}

	/**
	 * 判断显示顺序是否重复
	 * 
	 * @param showSer
	 * @return
	 * @throws SnowException
	 */
	public int judgeShowSerExit(String showSer, String bankNo) throws SnowException {

		DBDao dao = DBDaos.newInstance();
		return (Integer) dao.selectOne("com.ruimin.ifs.pmp.sysConf.rql.pmpBankInfo.judgeShowSerExit",
				RqlParam.map().set("showSer", showSer).set("bankNo", bankNo));
	}

	/**
	 * 添加通道配置信息
	 * 
	 * @param relBankPassBank
	 * @throws SnowException
	 */
	public void save(PmpRelBankPassBankVO relBankPassBank) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.insert(relBankPassBank);
	}

	/**
	 * 按产品银行编号 删除通道配置信息
	 * 
	 * @param bankNo
	 * @throws SnowException
	 */
	public void deletePassBankByBankNo(String bankNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.executeUpdate("com.ruimin.ifs.pmp.sysConf.rql.pmpRelBankPassBank.deleteByBankNo",
				RqlParam.map().set("bankNo", bankNo));
	}

	/**
	 * 按通道名称查询通道信息
	 * 
	 * @param passName通道名称
	 * @param page
	 *            分页信息
	 * @return
	 * @throws SnowException
	 */
	public PageResult listPassInfo(String passName, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.pmpRelBankPassBank.findPassInfoByPassName",
				RqlParam.map().set("passName", passName), page);
	}

	/**
	 * 按通道编号查询通道银行信息
	 * 
	 * @param map
	 * @param page
	 * @return
	 * @throws SnowException
	 */
	public PageResult listPassBank(Map<String, String> map, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.sysConf.rql.pmpRelBankPassBank.findPassBank", map, page);
	}

	/**
	 * 按通道编号 获取通道信息
	 * 
	 * @return
	 * @throws SnowException
	 */
	public PassInfoVO getPassNameByNo(String passNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(PassInfoVO.class, passNo);
	}

}
