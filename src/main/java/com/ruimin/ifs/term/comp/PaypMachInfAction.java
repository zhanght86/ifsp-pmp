/*
 * Copyright (C), 2015-2016, 上海睿民互联网科技有限公司
 * Package com.ruimin.ifs.term.comp 
 * FileName: PaypMachInfAction.java
 * Author:   wangyl
 * Date:     2016年8月10日 上午10:57:46
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wangyl           2016年8月10日上午10:57:46                     1.0                  
 *===============================================================================================
 */
package com.ruimin.ifs.term.comp;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.pmp.pubTool.servlet.UploadXlsServlet;
import com.ruimin.ifs.term.common.constants.TermConstants;
import com.ruimin.ifs.term.process.bean.PaypMachInf;
import com.ruimin.ifs.term.process.bean.PaypTermInf_temp;
import com.ruimin.ifs.term.process.service.CommonAuditService;
import com.ruimin.ifs.term.process.service.PaypMachInfoService;
import com.ruimin.ifs.term.process.service.PaypTermInfoService;
import com.ruimin.ifs.util.BaseUtil;
import com.ruimin.ifs.util.KeyGenerate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

/**
 * 名称：设备库存表<br>
 * 功能：设备库存信息维护<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月10日 <br>
 * 作者：wangyl <br>
 * 说明：<br>
 */
@SnowDoc(desc = "终端信息")
@ActionResource
public class PaypMachInfAction extends SnowAction {

	@Action
	@SnowDoc(desc = "查询所有终端设备库存信息")
	public PageResult queryAllPaypMachInfo(QueryParamBean queryBean) throws SnowException {
		String paramCMD = queryBean.getParameter("paramCMD");// 产权归属
		if ("NoBing".equals(paramCMD)) {// 查询没有被绑定的终端信息
			return queryNoBingMachIdList(queryBean);//终端信息管理点击绑定按钮时弹出的未绑定窗口
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 商户号
		String machId = queryBean.getParameter("qmachId", ""); // 设备库存编号
		String machType = queryBean.getParameter("qmachType", "");// 设备类型
		String machStat = queryBean.getParameter("qmachStat", "");// 设备状态
		String batchNo = queryBean.getParameter("qbatchNo", "");// 批次号
		String machInst = queryBean.getParameter("qmachInst", "");// 所属机构
		String propertyType = queryBean.getParameter("qpropertyType", "");// 产权归属
		if (machId.length() != 0) {
			machId = "K" + machId.substring(1);
		}
		queryMap.put("machId", machId);
		queryMap.put("machType", machType);
		queryMap.put("machStat", machStat);
		queryMap.put("batchNo", batchNo);
		queryMap.put("machInst", machInst);
		queryMap.put("propertyType", propertyType);
		Page page = queryBean.getPage();
		return PaypMachInfoService.getInstance().queryListPaypMachInf(queryMap, page);
	}

	@Action
	@SnowDoc(desc = "增加设备库存信息")
	public void addMachInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 操作员登录session
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		UpdateRequestBean reqBean = updateMap.get("paypMachInf");
		String machNumber = reqBean.getParameter("machNumber1"); // 设备数量
		PaypMachInf paypMachInf = new PaypMachInf();
		if (StringUtils.isBlank(machNumber)) {
			machNumber = "1";
		}
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypMachInf);
		}
		int machNumberInt = Integer.parseInt(machNumber);
		String batchNo = KeyGenerate.batchNo();
		// 批次号 留下**********
		List<PaypMachInf> paypMachInfList = new ArrayList<PaypMachInf>();
		String currentDateTime = BaseUtil.getCurrentDateTime();
		for (int i = 0; i < machNumberInt; i++) {
			PaypMachInf pmi = new PaypMachInf();
			pmi.setMachId(KeyGenerate.machInvenId());// 终端库存编号16位生成
			pmi.setBatchNo(batchNo);
			pmi.setCrtTlr(sessionUserInfo.getTlrno());
			pmi.setCrtDateTime(currentDateTime);
			pmi.setMachStat(TermConstants.MACH_STAT_0);
			pmi.setCompanyName(paypMachInf.getCompanyName());
			pmi.setMachInst(paypMachInf.getMachInst());
			pmi.setMachType(paypMachInf.getMachType());
			pmi.setPropertyInst(paypMachInf.getPropertyInst());
			pmi.setPropertyType(paypMachInf.getPropertyType());
			//新增字段设置值
			pmi.setMachNo(paypMachInf.getMachNo());
			pmi.setConfNo(paypMachInf.getConfNo());
			pmi.setMachVersion(paypMachInf.getMachVersion());
			
			paypMachInfList.add(pmi);
		}
		PaypMachInfoService.getInstance().batchInsertPaypMachInf(paypMachInfList);
	}

	@Action
	@SnowDoc(desc = "设备 作废操作")
	public void voidedMachInfo(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("paypMachInf");
		PaypMachInf paypMachInf = new PaypMachInf();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypMachInf);
		}
		PaypMachInfoService.getInstance().updatePaypMachInf(paypMachInf);
	}

	@Action
	@SnowDoc(desc = "查询没有被绑定的设备号 根据创建日期排序")
	public PageResult queryNoBingMachIdList(QueryParamBean queryBean) throws SnowException {
		Page page = queryBean.getPage();
		return PaypMachInfoService.getInstance().queryNoBingMachIdList(page);
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "绑定设备操作 ")
	public void updateMachMachStat(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrNo = sessionUserInfo.getTlrno(); // 操作员
		String currentDateTime = BaseUtil.getCurrentDateTime(); // 最后操作时间

		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		paypTermInf.setLastUpdTlr(tlrNo);
		paypTermInf.setLastUpdDateTime(currentDateTime);
		String machId = reqBean.getParameter("machId"); // payp_mach_inf 设备库存编号
		// 未绑定-->绑定，要将绑定的设备编号取出头字母，然后替换成Y,表示绑定待审核
		String machIdTemp = machId.replaceFirst("K", "Y");//2017-03-23修改
		paypTermInf.setMachId(machIdTemp);
		PaypMachInfoService.getInstance().updateMachMachStat(TermConstants.MACH_STAT_1, machId);// 更新设备库存信息
																								// 状态设置为已出库//
																								// 0-已入库,1-已出库,2-作废
		CommonAuditService.getInstance().addAuditEntry(paypTermInf);// 调用方法加入到审核中
		PaypTermInfoService.getInstance().updatePaypTermInf(paypTermInf);
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "解除绑定设备操作 ")
	public void updateUnMachMachStat(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrNo = sessionUserInfo.getTlrno(); // 操作员
		String currentDateTime = BaseUtil.getCurrentDateTime(); // 最后操作时间
		UpdateRequestBean reqBean = updateMap.get("paypTermInf");
		PaypTermInf_temp paypTermInf = new PaypTermInf_temp();
		if (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), paypTermInf);
		}
		String machId = reqBean.getParameter("machId"); // payp_mach_inf 设备库存编号
		paypTermInf.setLastUpdTlr(tlrNo);
		paypTermInf.setLastUpdDateTime(currentDateTime);
		// 绑定-->未绑定，要将绑定的设备编号取出头字母，然后替换成N,表示解绑待审核
		String machIdTemp = machId.replaceFirst("K", "N");
		paypTermInf.setMachId(machIdTemp);
		CommonAuditService.getInstance().addAuditEntry(paypTermInf);// 调用方法加入到审核中
		PaypTermInfoService.getInstance().updatePaypTermInf(paypTermInf);
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "批量导入 ")
	public void processOpenBathAdd(FileItem item) throws FileNotFoundException, IOException, SnowException {
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 获取解析文件的数据
		String[][] result = UploadXlsServlet.getData(item, 1);
		int rowLength = result.length;
		String lastBrcode = "";
		int i;
		// 循环判断主键是否重复	
		 for ( i = 0; i < rowLength; i++) { 
			 for ( int j = i+1; j < rowLength;j++){
				 lastBrcode = result[i][0]; 
				 if (lastBrcode.equals(result[j][0])){
					 SnowExceptionUtil.throwErrorException("主键【设备库存编号】不可重复，请检查EXCEL相关数据！");
					 break;
					 }
				 }
			 }
			// 清空数据前检查 2 5 6 8
			for (i = 0; i < rowLength; i++) {
				String fileDataRowNum = String.valueOf(i+2);
				String machId="K"+result[i][0];
				String queryMachId = PaypMachInfoService.getInstance().selectMachId(machId);
//				System.out.println(queryMachId);
				
				if (result[i][0].length() > 15 || result[i][0].length() == 0) {
					//设备库存编号不能为空，并且设备库存编号最大为15位！
					SnowExceptionUtil.throwWarnException("WEB_E0950",fileDataRowNum);
				}
				//判断设备编号是否已存在
				if(queryMachId!=null){
					SnowExceptionUtil.throwWarnException("WEB_E0962",fileDataRowNum);
				}
				if (result[i][1].length() > 1 || result[i][1].length() == 0) {
					//设备类型不能为空，并且设备类型最大为1位！
					SnowExceptionUtil.throwWarnException("WEB_E0951",fileDataRowNum);
				}
				if (result[i][2].length() > 10 || result[i][2].length() == 0) {
					//设备型号不能为空，并且设备类型最大为10位！
					SnowExceptionUtil.throwWarnException("WEB_E0952",fileDataRowNum);
				}
				if (result[i][3].length() > 20 || result[i][3].length() == 0) {
					//设备配置号不能为空，并且设备配置号最大为20位！
					SnowExceptionUtil.throwWarnException("WEB_E0953",fileDataRowNum);
				}
				if (result[i][4].length() > 32 || result[i][4].length() == 0) {
					//终端设备版本不能为空，并且终端设备版本最大为32位！
					SnowExceptionUtil.throwWarnException("WEB_E0954",fileDataRowNum);
				}
					
				if (result[i][5].length() == 0 || result[i][5].length() > 1) {
					//设备状态不能为空，并且设备状态最大为1位
					SnowExceptionUtil.throwWarnException("WEB_E0955",fileDataRowNum);
				}
				if (result[i][6].length() == 0 || result[i][6].length() > 12) {
					//批次号不能为空，并且批次号最大为12位！
					SnowExceptionUtil.throwWarnException("WEB_E0956",fileDataRowNum);
				}
				if (result[i][7].length() == 0 || result[i][7].length() > 32) {
					//所属机构不能为空，并且所属机构最大为32位！
					SnowExceptionUtil.throwWarnException("WEB_E0957",fileDataRowNum);
				}
				if (result[i][8].length() == 0 || result[i][8].length() > 15) {
					//归属产权不能为空，并且归属产权最大为15位！
					SnowExceptionUtil.throwWarnException("WEB_E0958",fileDataRowNum);
				}
				if (result[i][9].length() == 0 || result[i][9].length() > 32) {
					//产权机构不能为空，并且产权机构最大为32位！
					SnowExceptionUtil.throwWarnException("WEB_E0959",fileDataRowNum);
				}
				if (result[i][10].length() == 0 || result[i][10].length() > 40) {
					//厂商名称不能为空，并且开户名称最大为40位！
					SnowExceptionUtil.throwWarnException("WEB_E0960",fileDataRowNum);
				}
				if (result[i][11].length() > 0) {
					//不符合批量入库导入格式！
					SnowExceptionUtil.throwWarnException("WEB_E0961",fileDataRowNum);
				}
			}
			DBDao dao = DBDaos.newInstance();
			PaypMachInf mach = new PaypMachInf();
			String head = "K";
				// 将解析到的文件set到数据库中
				for (i = 0; i < rowLength; i++) {
					mach.setMachId(head+result[i][0]);// 15
					mach.setMachType(result[i][1]);// 1
					//新增字段设置值
					mach.setMachNo(result[i][2]);// 10
					mach.setConfNo(result[i][3]);// 20
					mach.setMachVersion(result[i][4]);// 32
					
					mach.setMachStat(result[i][5]);// 1
					mach.setBatchNo(result[i][6]);// 12
					mach.setMachInst(result[i][7]);// 32
					mach.setPropertyType(result[i][8]);// 15
					mach.setPropertyInst(result[i][9]);// 64
					mach.setCompanyName(result[i][10]);// 40
					mach.setCrtTlr(sessionUserInfo.getTlrno());
					mach.setCrtDateTime(BaseUtil.getCurrentDateTime());
					mach.setLastUpdTlr(sessionUserInfo.getTlrno());
					mach.setLastUpdDateTime(BaseUtil.getCurrentDateTime());
					try {
						PaypMachInf insert = dao.insert(mach);
						sessionUserInfo.addBizLog(insert.toString());
					} catch (Exception e) {
						sessionUserInfo.addBizLog("添加失败："+e.getMessage());
					}
				}
				// 打印日志
				sessionUserInfo.addBizLog("update.log",
		   				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[终端设备管理]--excel导入" });
	}
}
