package com.ruimin.ifs.pmp.service.comp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.pmp.pubTool.servlet.UploadXlsServlet;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.service.process.bean.ServiceMchtPollingVo;
import com.ruimin.ifs.pmp.service.process.service.ServiceMchtPollingService;

@ActionResource
@SnowDoc(desc = "商户巡检管理")
public class ServiceMchtPollingAction extends SnowAction {
	static Logger log = SnowLog.getLogger(ServiceMchtPollingAction.class);
	/**
	 * 商户巡检基本信息查询
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryMain(QueryParamBean queryBean) throws SnowException{
		 String qserviceName = queryBean.getParameter("qserviceName");
		 String qmchtName = queryBean.getParameter("qmchtName");
		 String qtermId = queryBean.getParameter("qtermId");
		 String qpollingDate = queryBean.getParameter("qpollingDate");
		return ServiceMchtPollingService.getInstance().queryMain(qserviceName,qmchtName,qtermId,qpollingDate,queryBean.getPage());
	}
	
	/**
	 * 商户巡检基本信息excel批量导入
	 * @param item
	 * @throws IOException
	 * @throws SnowException
	 * @throws SQLException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "excel导入")
	public void processMchtPollinglist(FileItem item) throws IOException,SnowException,SQLException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//yyyyMMdd正则
		String isYearMonDayReg = "^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
		String is6 = "^\\d{6}$";
		String is8 = "^\\d{8}$";
		String is16 = "^\\w{16}$";
		/**验证座机电话 */
		String isPhone = "^(\\d{3,4}-)?\\d{6,8}$";
		/**验证手机电话 */
		String isCellPhone = "^[1]+\\d{10}$";
		//获取数据库中已有的商户巡检信息信息
 		List<Object> list = ServiceMchtPollingService.getInstance().selectAllServiceMchtPolling();
		
		//获取文件中的数据
		String[][] result = UploadXlsServlet.getData(item, 1);
		log.info("========result=============");
		int rowLength = result.length;
		log.info("rowLength:"+rowLength);
		String lastMchtId = "";//商户号
    	String lastPollingDate = "";//巡检日期
    	int i;
    	log.info("即将进入for循环读取数据");
    	for(i = 0 ;i<rowLength;i++){
    		log.info("进入for循环开始读取数据");
    		String fileDataRowNum = String.valueOf(i+2);
    		lastMchtId = result[i][0];
    		lastPollingDate = result[i][10];
			
    		for(int j = i+1;j<rowLength;j++){//循环判断Excel中商户号和商户巡检日期是否重复
    			if(lastMchtId.equals(result[j][0]) && lastPollingDate.equals(result[j][10])){
    				log.info("111Excel中商户号和商户巡检日期重复");
    				SnowExceptionUtil.throwWarnException("WEB_E0931",fileDataRowNum);//商户号和商户巡检日期合起来不能重复,请检查EXCEL相关数据！
    				break;
    			}
    		}
    		for (Object object : list) {//根据商户号和巡检日期查询结果,判断excel中的数据是否和数据库里已有的数据重复
    			ServiceMchtPollingVo listVo = (ServiceMchtPollingVo)object;
				if(lastMchtId.equals(listVo.getMchtId()) && lastPollingDate.equals(listVo.getPollingDate())){
					log.info("222断excel中的数据和数据库里已有的数据重复");
					SnowExceptionUtil.throwWarnException("WEB_E0932",fileDataRowNum);//商户号和巡检日期和已有的商户巡检信息重复,请检查EXCEL相关数据！
				}
			}
    	}

        
        //检查数据
   	 	for ( i=0;i<rowLength;i++){
   	 		//-----判断商户编号是否符合规范--------------
   	 		String fileDataRowNum = String.valueOf(i+2);
   	 		if( !result[i][0].matches(is16)){
   	 			log.info("商户号必须是16位长度,且不能为空");
   	 			SnowExceptionUtil.throwWarnException("WEB_E0933",fileDataRowNum);//商户号必须是16位长度,且不能为空
   	 		}
   	 		if( result[i][1].length() > 64 || result[i][1].length() == 0){
   	 			log.info("商户名长度最大64位,且不能为空");
	 			SnowExceptionUtil.throwWarnException("WEB_E0934",fileDataRowNum);//商户名长度最大64位,且不能为空
	 		}
   	 		if( !result[i][2].matches(is8)){
   	 			log.info("终端编号必须为8位纯数字,且不能为空");
   	 			SnowExceptionUtil.throwWarnException("WEB_E0935",fileDataRowNum);//终端编号必须为8位纯数字,且不能为空
   	 		}
   	 		if( result[i][3].length() > 64 || result[i][3].length() == 0){
   	 			log.info("经营名称长度最大64位,且不能为空");
	 			SnowExceptionUtil.throwWarnException("WEB_E0936",fileDataRowNum);//经营名称长度最大64位,且不能为空
	 		}
	   	 	if( result[i][4].length() > 128 || result[i][4].length() == 0){
	   	 		log.info("经营地址长度最大128位,且不能为空");
	 			SnowExceptionUtil.throwWarnException("WEB_E0937",fileDataRowNum);//经营地址长度最大128位,且不能为空
	 		}
		   	if( result[i][5].length() > 32 || result[i][5].length() == 0){
		   		log.info("设备号长度最大32位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0938",fileDataRowNum);//设备号长度最大32位,且不能为空
		 	}
		   	if( result[i][6].length() > 32 || result[i][6].length() == 0){
		   		log.info("配置码长度最大32位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0939",fileDataRowNum);//配置码长度最大32位,且不能为空
		 	}
		   	if( result[i][7].length() > 32 || result[i][7].length() == 0){
		   		log.info("键盘号长度最大32位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0940",fileDataRowNum);//键盘号长度最大32位,且不能为空
		 	}
		   	if( result[i][8].length() > 32 || result[i][8].length() == 0){
		   		log.info("版本号长度最大32位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0941",fileDataRowNum);//版本号长度最大32位,且不能为空
		 	}
		   	if( result[i][9].length() > 10 || result[i][9].length() == 0){
		   		log.info("客户经理工号长度最大10位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0942",fileDataRowNum);//客户经理工号长度最大10位,且不能为空
		 	}
		   	if( !result[i][10].matches(isYearMonDayReg)){
		   		log.info("巡检日期格式不对,格式:yyyymmdd,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0943",fileDataRowNum);//巡检日期格式不对,格式:yyyymmdd,且不能为空
		 	}
		   	if( result[i][11].length() > 32 || result[i][11].length() == 0){
		   		log.info("联系人长度最大32位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0944",fileDataRowNum);//联系人长度最大32位,且不能为空
		 	}
		   	if( !result[i][12].matches(isPhone) && !result[i][12].matches(isCellPhone)){
		   		log.info("联系电话格式不对,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0945",fileDataRowNum);//联系电话格式不对,且不能为空
		 	}
		   	if( !result[i][13].matches(is6)){
		   		log.info("巡检服务机构号必须为6位纯数字,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0946",fileDataRowNum);//巡检服务机构号必须为6位纯数字,且不能为空
		 	}
			if( result[i][14].length() > 10 || result[i][14].length() == 0){
				log.info("巡检结果长度最大10位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0947",fileDataRowNum);//巡检结果长度最大10位,且不能为空
		 	}
			if( result[i][15].length() > 256 || result[i][15].length() == 0){
				log.info("备注最大长度256位,且不能为空");
		 		SnowExceptionUtil.throwWarnException("WEB_E0948",fileDataRowNum);//备注最大长度256位,且不能为空
		 	}
		   	// 将文件解析的数据SET到数据库中
   	 		ServiceMchtPollingVo vo = new ServiceMchtPollingVo();
   	 		for(i=0; i<rowLength; i++){
   	 			//获取最大序号
   	 			String serviceId = ServiceMchtPollingService.getInstance().getMaxServiceId();
	   	 		if(StringUtil.isEmpty(serviceId)){
	   	 			serviceId = "0";
		   		}
		   		int nextServiceId = Integer.valueOf(serviceId.trim())+1;
		   		serviceId = StringUtil.leftPad(String.valueOf(nextServiceId), 4,"0");
		   		vo.setServiceId(serviceId);//赋值最大序号
		   		vo.setMchtId(result[i][0]);//商户号
		   		vo.setMchtName(result[i][1]);//商户名称
		   		vo.setTermId(result[i][2]);//终端编号
		   		vo.setManageName(result[i][3]);//经营名称
		   		vo.setManageAddr(result[i][4]);//经营地址
		   		vo.setEquipmentId(result[i][5]);//设备号
		   		vo.setConfigurationCode(result[i][6]);//配置码
		   		vo.setKeyboardCode(result[i][7]);//键盘号
		   		vo.setVersionsCode(result[i][8]);//版本号
		   		vo.setMchtAmrNo(result[i][9]);//客户经理工号
		   		vo.setPollingDate(result[i][10]);//巡检日期
		   		vo.setMchtPersonName(result[i][11]);//联系人
		   		vo.setMchtPhone(result[i][12]);//联系电话
		   		vo.setServiceCode(result[i][13]);//巡检服务机构号
		   		vo.setPollingResult(result[i][14]);//巡检结果
		   		vo.setRemark(result[i][15]);//备注
		   		vo.setCrtTlr(sessionUserInfo.getTlrno());//创建人
		   		vo.setCrtDateTime(BaseUtil.getCurrentDateTime());//创建时间
		   		//数据持久化
		   		ServiceMchtPollingService.getInstance().addServiceMchtPolling(vo);
   	 		}
   	 		//记录日志
		   	sessionUserInfo.addBizLog("update.log",
		   				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[商户巡检]--excel导入" });
   	 	}
		
	}

}
