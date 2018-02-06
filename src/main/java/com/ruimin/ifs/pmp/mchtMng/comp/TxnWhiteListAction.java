package com.ruimin.ifs.pmp.mchtMng.comp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ruim.ifsp.utils.id.IfspId;
import com.ruim.ifsp.utils.verify.IfspDataVerifyUtil;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.mchtMng.process.bean.PbsMchtBaseInfo;
import com.ruimin.ifs.pmp.mchtMng.process.bean.TxnWhiteListVO;
import com.ruimin.ifs.pmp.mchtMng.process.service.MchtMngService;
import com.ruimin.ifs.pmp.mchtMng.process.service.TxnWhiteListService;
import com.ruimin.ifs.pmp.pubTool.servlet.UploadXlsServlet;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.txnQuery.process.service.txnCountService;

@ActionResource
@SnowDoc(desc = "特殊商户交易白名单action")
public class TxnWhiteListAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询")
	public PageResult pageQuery(QueryParamBean queryBean) throws SnowException {
		String qMchtId = queryBean.getParameter("qMchtId");//商户号
		String qMchtSimpleName = queryBean.getParameter("qMchtSimpleName");//商户简称
		String qFiltrateValue = queryBean.getParameter("qFiltrateValue");//值
		return TxnWhiteListService.getInstance().pageQuery(qMchtId,qMchtSimpleName,qFiltrateValue,queryBean.getPage());
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void add(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TxnWhiteListVO txnWhiteList = new TxnWhiteListVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("whiteList");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), txnWhiteList);
		}
		/**step no 2: 校验商户号是否存在*/
		PbsMchtBaseInfo mchtInfo = MchtMngService.getInstance().queryByMchtId(txnWhiteList.getMchtId());
		if(mchtInfo == null){
			SnowExceptionUtil.throwErrorException("找不到该商户号对应的商户信息，请检查！");
		}
		/** step no 3 : 补充字段数据 */
		String qMchtId = txnWhiteList.getMchtId();
        String qFiltrateValue = txnWhiteList.getFiltrateValue();
        System.out.println(qMchtId+"xyz");
        System.out.println(qFiltrateValue+"xyz");
		String str=TxnWhiteListService.getInstance().pageQuery1(qMchtId,qFiltrateValue);
		System.out.println(str);
		if(str==null){
		}else{
            SnowExceptionUtil.throwErrorException("该商户号对应的同样的值已有，请检查！");
		}
		String cfgId = IfspId.getObjectId();
		txnWhiteList.setCfgId(cfgId);
		String currentDateTime = BaseUtil.getCurrentDateTime();
		txnWhiteList.setCrtTlr(sessionUserInfo.getTlrno());
		txnWhiteList.setCrtDateTime(currentDateTime);
		txnWhiteList.setLastUpdTlr(sessionUserInfo.getTlrno());
		txnWhiteList.setLastUpdDateTime(currentDateTime);
		/** step no 4 : 记录信息 */
		TxnWhiteListService.getInstance().add(txnWhiteList);
		/** step no 5 : 记录日志 */
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						 " 新增商户交易白名单：商户号="+txnWhiteList.getMchtId()+",值="+txnWhiteList.getFiltrateValue()});
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void update(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TxnWhiteListVO txnWhiteList = new TxnWhiteListVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("whiteList");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), txnWhiteList);
		}
		/**step no 2: 校验商户号是否存在*/
		PbsMchtBaseInfo mchtInfo = MchtMngService.getInstance().queryByMchtId(txnWhiteList.getMchtId());
		if(mchtInfo == null){
			SnowExceptionUtil.throwErrorException("找不到该商户号对应的商户信息，请检查！");
		}
		/** step no 3 : 补充字段数据 */
		String currentDateTime = BaseUtil.getCurrentDateTime();
		txnWhiteList.setLastUpdTlr(sessionUserInfo.getTlrno());
		txnWhiteList.setLastUpdDateTime(currentDateTime);
		/** step no 4 : 记录信息 */
		TxnWhiteListService.getInstance().update(txnWhiteList);
		/** step no 5 : 记录日志 */
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						 " 修改商户交易白名单：商户号="+txnWhiteList.getMchtId()+",值="+txnWhiteList.getFiltrateValue()});
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delete(Map<String, UpdateRequestBean> updateBean) throws SnowException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TxnWhiteListVO txnWhiteList = new TxnWhiteListVO();
		// 获取数据集
		UpdateRequestBean reqBean = updateBean.get("whiteList");
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), txnWhiteList);
		}
		/** step no 2 : 补充字段数据 */
		/** step no 3 : 记录信息 */
		TxnWhiteListService.getInstance().delete(txnWhiteList);
		/** step no 4 : 记录日志 */
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						 " 删除商户交易白名单：商户号="+txnWhiteList.getMchtId()+",值="+txnWhiteList.getFiltrateValue()});
	}

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "批量导入")
	public void batchImport(FileItem item) throws SnowException,IOException {
		/** step no 1 : 转换页面传入参数 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String currentDateTime = BaseUtil.getCurrentDateTime();
		//获取文件中的数据
		String[][] result = UploadXlsServlet.getData(item, 1);
		int rowCount = result.length;
		if(rowCount == 0){
			SnowExceptionUtil.throwErrorException("文件内容为空，无法导入！");
		}
		/** step no 2 : 获取全量商户信息和白名单列表 */
		//获取全量的商户号列表
		List<String> mchtIdList = MchtMngService.getInstance().getAllMchtId();
		if(IfspDataVerifyUtil.isEmptyList(mchtIdList)){
			SnowExceptionUtil.throwErrorException("找不到该商户号对应的商户信息，请检查！");
		}
		//获取全量的白名单列表
		List<String> whiteList = TxnWhiteListService.getInstance().getAllKeysForImport();
		boolean needCheckFlag = true;
		if(IfspDataVerifyUtil.isEmptyList(whiteList)){
			needCheckFlag = false;
		}
		
		/** step no 3 : 校验数据有效性 */
		List<TxnWhiteListVO> lstTxnWhiteList = new ArrayList<TxnWhiteListVO>();
		TxnWhiteListVO whiteListVo;
		String mchtId;
		String filtrateValue;
		for(int i = 0; i< rowCount;i++){
			/**
			 * 校验商户编号
			 * */
			mchtId = result[i][0].replaceAll(" ", "");//获取该行第一列，即商户编号
			if(IfspDataVerifyUtil.isEmpty(mchtId)){
				SnowExceptionUtil.throwErrorException("第"+(i+2)+"行商户号为空，请重新填写！");
			}
			if(!mchtIdList.contains(mchtId)){
				SnowExceptionUtil.throwErrorException("找不到该商户号对应的商户信息，请检查！");
			}
			/**
			 * 校验过滤值
			 * */
			filtrateValue = result[i][1].replaceAll(" ", "");//获取该行第二列，即过滤值
			if(IfspDataVerifyUtil.isEmpty(filtrateValue)){
				SnowExceptionUtil.throwErrorException("第"+(i+2)+"行白名单值为空，请重新填写！");
			}
			if(filtrateValue.length()>32){
				SnowExceptionUtil.throwErrorException("第"+(i+2)+"行白名单值格式有误，请重新填写！");
			}
			if(whiteList.contains(mchtId+"_"+filtrateValue)){
				SnowExceptionUtil.throwErrorException("第"+(i+2)+"行信息已存在，不能重复添加！");
			}
			/**
			 * 封装bean对象
			 * */
			whiteListVo = new TxnWhiteListVO();
			whiteListVo.setCfgId(IfspId.getObjectId());
			whiteListVo.setMchtId(mchtId);
			whiteListVo.setFiltrateValue(filtrateValue);
			whiteListVo.setCrtTlr(sessionUserInfo.getTlrno());
			whiteListVo.setCrtDateTime(currentDateTime);
			whiteListVo.setLastUpdTlr(sessionUserInfo.getTlrno());
			whiteListVo.setLastUpdDateTime(currentDateTime);
			/**
			 * 存入list
			 * */
			lstTxnWhiteList.add(whiteListVo);
			
		}
		/** step no 3 : 记录信息 */
		TxnWhiteListService.getInstance().batchInsert(lstTxnWhiteList);
		/** step no 4 : 记录日志 */
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
						 " 批量导入商户交易白名单：共"+rowCount+"行" });
	}

}
