package com.ruimin.ifs.pmp.risk.comp;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.util.DataObjectUtils;
import com.ruimin.ifs.core.util.DateUtil;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.framework.core.SessionUserInfo;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.pmp.pubTool.servlet.UploadXlsServlet;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.pubTool.util.StringUtil;
import com.ruimin.ifs.pmp.risk.process.bean.UserBlackListVo;
import com.ruimin.ifs.pmp.risk.common.constants.UserBlackListConstants;
import com.ruimin.ifs.pmp.risk.process.service.UserBlackListService;

@SnowDoc(desc = "商户黑名单")
@ActionResource
public class UserBlackListAction extends SnowAction {
	@Action
	@SnowDoc(desc = "查询")
	public PageResult queryAll(QueryParamBean queryBean) throws SnowException{
		 String qBlacklistType = queryBean.getParameter("qBlacklistType");//弃用
		 String qBlacklistStatus = queryBean.getParameter("qBlacklistStatus");
		 String qBlacklistValue = queryBean.getParameter("qBlacklistValue");
		return UserBlackListService.getInstance().queryList(qBlacklistType,qBlacklistStatus,qBlacklistValue,queryBean.getPage());
	}
	
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增")
	public void addActive(Map<String,UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的黑名单基本信息
		UpdateRequestBean reqBean = updateMap.get("UserBlackList");
		UserBlackListVo userBlackListVo = new UserBlackListVo();
		if(reqBean.hasNext()){
			DataObjectUtils.map2bean(reqBean.next(), userBlackListVo);
		}
		//新增时判断黑名单是否存在，防止重复新增
		String blacklistType = userBlackListVo.getBlacklistType();
		String blacklistValue = userBlackListVo.getBlacklistValue();
		//根据类型和值查询结果
		UserBlackListVo vo =  UserBlackListService.getInstance().selectBlackListByTypeAndValue(blacklistType,blacklistValue);
		if(vo != null){
			//SnowExceptionUtil.throwErrorException("存在相同的黑名单信息，无法新增已存在的黑名单");
			SnowExceptionUtil.throwWarnException("WEB_E0905");
		}
		//封装黑名单补充信息
		userBlackListVo.setBlacklistValue(userBlackListVo.getBlacklistValue().replaceAll(" ", ""));
		userBlackListVo.setBlacklistStatus(UserBlackListConstants.BLACKLIST_STAT_NORMAL);
		userBlackListVo.setCrtTlr(sessionUserInfo.getTlrno());//创建人为当前用户
		userBlackListVo.setCrtDateTime(DateUtil.get14Date());//创建时间为当前时间
		userBlackListVo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前用户
		userBlackListVo.setLastUpdDateTime(userBlackListVo.getCrtDateTime());//最后更新时间为当前时间
		userBlackListVo.setStartDate(userBlackListVo.getStartDate().replace("-", ""));// 开始日期去掉-
		userBlackListVo.setEndDate(StringUtil.isEmpty(userBlackListVo.getEndDate()) ? "-" : userBlackListVo.getEndDate().replaceAll("-", ""));
		//获取最大黑名单ID
		String blackListId =UserBlackListService.getInstance().getMaxId();
		if(StringUtil.isEmpty(blackListId)){
			blackListId = "0";
		}
		int nextBlackListId = Integer.valueOf(blackListId.trim())+1;
		blackListId = StringUtil.leftPad(String.valueOf(nextBlackListId), 4,"0");
		userBlackListVo.setBlacklistId(blackListId);//赋值最大黑名单编号
		//数据持久化
		UserBlackListService.getInstance().save(userBlackListVo);
		//记录日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[用户黑名单]--新增:编号=[" + blackListId + "]" });
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "修改")
	public void modificationActive(Map<String,UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("UserBlackList");
		UserBlackListVo userBlackListVo = new UserBlackListVo();
		if(reqBean.hasNext()){
			DataObjectUtils.map2bean(reqBean.next(), userBlackListVo);
		}
		
		//修改时判断黑名单是否存在，防止重复
		String blacklistId = userBlackListVo.getBlacklistId();
		UserBlackListVo vo1 = UserBlackListService.getInstance().selectBlackListById(blacklistId);
		String blacklistType1 = vo1.getBlacklistType();//查询获取的类型
		String blacklistValue1 = vo1.getBlacklistValue().replaceAll(" ", "");//查询获取的值
		String blacklistType = userBlackListVo.getBlacklistType();//页面传递过来的类型
		String blacklistValue = userBlackListVo.getBlacklistValue().replaceAll(" ", "");//页面传递过来的值
		if(blacklistType1 != null && blacklistValue1 != null){
			//根据当前黑名单Id查询出来的结果和页面的结果一样，证明没有修改黑名单类型和黑名单值
			if(blacklistType1.equals(blacklistType) && blacklistValue1.equals(blacklistValue)){
				
			}else{//修改了值，那么判断修改的值是否存在，存在则提示错误
				//根据类型和值查询结果
				UserBlackListVo vo2 =  UserBlackListService.getInstance().selectBlackListByTypeAndValue(blacklistType,blacklistValue);
				if(vo2 != null){
					//SnowExceptionUtil.throwErrorException("存在相同的黑名单信息，无法修改成已存在的黑名信息");
					SnowExceptionUtil.throwWarnException("WEB_E0906");
				}
			}
		}
		
		//封装方法补充信息
		userBlackListVo.setStartDate(userBlackListVo.getStartDate().replace("-", ""));// 开始日期去掉-
		if(!"-".equals(userBlackListVo.getEndDate())){
			userBlackListVo.setEndDate(StringUtil.isEmpty(userBlackListVo.getEndDate()) ? "-" : userBlackListVo.getEndDate().replaceAll("-", ""));
		}else{
			userBlackListVo.setEndDate("-");
		}
		userBlackListVo.setBlacklistValue(userBlackListVo.getBlacklistValue().replaceAll(" ", ""));
		userBlackListVo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前用户
		userBlackListVo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间为当前时间
		//数据持久化
		UserBlackListService.getInstance().modify(userBlackListVo);
		//记录日志
		sessionUserInfo.addBizLog("update.log",
						new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[用户黑名单]--修改:编号=[" + userBlackListVo.getBlacklistId() + "]" });
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "启用停用")
	public void stautsActive(Map<String,UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("UserBlackList");
		String blacklistId = reqBean.getParameter("blacklistId");
		String blacklistStatus = reqBean.getParameter("blacklistStatus");
		blacklistStatus = "00".equals(blacklistStatus) ? "99" : "00";
		UserBlackListVo userBlackListVo = new UserBlackListVo();
		userBlackListVo.setBlacklistId(blacklistId);
		userBlackListVo.setBlacklistStatus(blacklistStatus);
		userBlackListVo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人为当前用户
		userBlackListVo.setLastUpdDateTime(DateUtil.get14Date());//最后更新时间为当前时间
		//数据持久化
		UserBlackListService.getInstance().statusChange(userBlackListVo);
		//记日志
		sessionUserInfo.addBizLog("update.log",
				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[用户黑名单]--启用/停用:编号=[" + blacklistId + "]" });
		
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void deleteActive(Map<String,UpdateRequestBean> updateMap) throws SnowException{
		//获取当前用户
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		//获取页面传递的信息
		UpdateRequestBean reqBean = updateMap.get("UserBlackList");
		String blacklistId = reqBean.getParameter("blacklistId");
		//数据持久化
		UserBlackListService.getInstance().delete(blacklistId);
		//记录日志
		sessionUserInfo.addBizLog("update.log",
								new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[用户黑名单]--删除:编号=[" + blacklistId + "]" });
		
	}
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "excel导入")
	public  void processUserBlacklist(FileItem item) throws IOException,SnowException,SQLException{
				//获取当前用户
				SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
				//yyyyMMdd正则
				String isYearMonDayReg = "^((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
				String isCardNo= "^\\d{15,19}$";//15-19位卡号
		 		String isCardBin= "^\\d{2,10}$";//2-10为卡BIN
		 		String isNumAndDot = "^[0-9]{0}([0-9]|[.])+$";//数字和点
		 		//获取数据库中已有的黑名单信息
		 		List<Object> list = UserBlackListService.getInstance().selectAllBlackList();
		 		
				//获取文件中的数据
				String[][] result = UploadXlsServlet.getData(item, 1);
				int rowLength = result.length;
				String lastType = "";
		    	String lastValue = "";
		    	int i;
		    	for(i = 0 ;i<rowLength;i++){
		    		String fileDataRowNum = String.valueOf(i+2);
		    		lastType = result[i][0];
	    			lastValue = result[i][1].replaceAll(" ", "");
	    			
		    		for(int j = i+1;j<rowLength;j++){//循环判断Excel中黑名单类型加上黑名单值是否重复
		    			if(lastType.equals(result[j][0]) && lastValue.equals(result[j][1].replaceAll(" ", ""))){
		    				//SnowExceptionUtil.throwErrorException("黑名单类型加上黑名单值合起来不能重复，请检查EXCEL相关数据！");
		    				SnowExceptionUtil.throwWarnException("WEB_E0907",fileDataRowNum);
		    				break;
		    			}
		    		}
		    		for (Object object : list) {//根据类型和值查询结果,判断excel中的数据是否和数据库里已有的数据重复
						UserBlackListVo listVo = (UserBlackListVo)object;
						if(lastType.equals(listVo.getBlacklistType()) && lastValue.equals(listVo.getBlacklistValue().replaceAll(" ", ""))){
							SnowExceptionUtil.throwWarnException("WEB_E0918",fileDataRowNum);
						}
					}
		    	}
		    	//获取当前系统时间
		    	 SimpleDateFormat   dateFormat = new SimpleDateFormat("yyyyMMdd");
		         Calendar cal = Calendar.getInstance();
		         String sysdate = dateFormat.format(cal.getTime()); //取当前的系统时间
		    	//检查数据
		   	 	for ( i=0;i<rowLength;i++){
		   	 		//-----判断黑名单种类是否符合规范--------------
		   	 		String fileDataRowNum = String.valueOf(i+2);
//		   	 		if( !"01".equals(result[i][0]) && !"02".equals(result[i][0]) && !"03".equals(result[i][0]) ){
//		   	 			//SnowExceptionUtil.throwErrorException("黑名单种类不对，黑名单种类只能是:01,02,03。且不能为空。");
//		   	 			SnowExceptionUtil.throwWarnException("WEB_E0908",fileDataRowNum);
//		   	 		}
		   	 	if( !"01".equals(result[i][0])){
	   	 			//SnowExceptionUtil.throwErrorException("黑名单种类不对，黑名单种类只能是:01,且不能为空。");
	   	 			SnowExceptionUtil.throwWarnException("WEB_E0908",fileDataRowNum);
	   	 		}
		   	 		//-----判断黑名单值是否符合规范--------------
		   	 		if("01".equals(result[i][0])){//卡号黑名单
		   	 			if(!result[i][1].replaceAll(" ", "").matches(isCardNo)){
		   	 				//SnowExceptionUtil.throwErrorException("当黑名单种类为01时,值必须是16位纯数字");
		   	 				SnowExceptionUtil.throwWarnException("WEB_E0909",fileDataRowNum);
		   	 			}
		   	 		}
//		   	 		else if("02".equals(result[i][0])){//卡BIN黑名单
//		   	 			if(!result[i][1].replaceAll(" ", "").matches(isCardBin)){
//		   	 				//SnowExceptionUtil.throwErrorException("当黑名单种类为02时,值必须是6位纯数字");
//		   	 				SnowExceptionUtil.throwWarnException("WEB_E0910",fileDataRowNum);
//		   	 			}
//		   	 		}else if("03".equals(result[i][0])){//IP黑名单
//		   	 			if(!result[i][1].replaceAll(" ", "").matches(isNumAndDot)){
//		   	 				//SnowExceptionUtil.throwErrorException("当黑名单种类为03时,值必须是纯数字和点");
//		   	 				SnowExceptionUtil.throwWarnException("WEB_E0911",fileDataRowNum);
//		   	 			}
//		   	 		}
		   	 		//-----判断黑名单状态是否符合规范--------------
		   	 		if(!"00".equals(result[i][2]) && !"99".equals(result[i][2])){
		   	 			//SnowExceptionUtil.throwErrorException("黑名单状态不对，黑名单状态只能是:00,99。且不能为空。");
		   	 			SnowExceptionUtil.throwWarnException("WEB_E0912",fileDataRowNum);
		   	 		}
		   	 		//-----判断黑名单开始日期，结束日期是否符合规范--------------
		   	 		String startTime = result[i][3];
		   	 		String endTime = result[i][4];
		   	 		if(startTime == null){
		   	 			//SnowExceptionUtil.throwErrorException("开始日期不能为空");
		   	 			SnowExceptionUtil.throwWarnException("WEB_E0913",fileDataRowNum);
		   	 		}else{
		   	 			if(!startTime.matches(isYearMonDayReg)){
		   	 				//SnowExceptionUtil.throwErrorException("开始日期格式不对，格式:yyyymmdd");
		   	 				SnowExceptionUtil.throwWarnException("WEB_E0914",fileDataRowNum);
		   	 			}
		   	 		}
		   	 		
		   	 		if(!"".equals(endTime)){
		   	 			if(!endTime.matches(isYearMonDayReg)){
		   	 				//SnowExceptionUtil.throwErrorException("结束日期格式不对，格式:yyyymmdd");
		   	 				SnowExceptionUtil.throwWarnException("WEB_E0915",fileDataRowNum);
		   	 			}
		   	 			int startTimeInt = Integer.parseInt(startTime);
		   	 			int endTimeInt = Integer.parseInt(endTime);
		   	 			if(startTimeInt>endTimeInt){
		   	 				//SnowExceptionUtil.throwErrorException("开始日期不能大于结束日期");
		   	 				SnowExceptionUtil.throwWarnException("WEB_E0916",fileDataRowNum);
		   	 			}
		   	 			if(Integer.parseInt(endTime)<=Integer.parseInt(sysdate)){
		   	 				//SnowExceptionUtil.throwErrorException("结束日期必须大于当天");
		   	 				SnowExceptionUtil.throwWarnException("WEB_E0917",fileDataRowNum);
		   	 			}
		   	 		}
		   	 	}
		   	 DBDao dao = DBDaos.newInstance();
		   	 // 将文件解析的数据SET到数据库中
		   	 UserBlackListVo  vo = new UserBlackListVo();
		   	 for(i=0; i<rowLength; i++){
		   		 //获取最大黑名单ID
		   		 String blackListId =UserBlackListService.getInstance().getMaxId();
		   		 if(StringUtil.isEmpty(blackListId)){
		   			 blackListId = "0";
		   		 }
		   		 int nextBlackListId = Integer.valueOf(blackListId.trim())+1;
		   		 blackListId = StringUtil.leftPad(String.valueOf(nextBlackListId), 4,"0");
		   		 vo.setBlacklistId(blackListId);//赋值最大黑名单编号
		   		 vo.setBlacklistType(result[i][0]);//类型
		   		 vo.setBlacklistValue(result[i][1].replaceAll(" ", ""));//值
		   		 vo.setBlacklistStatus(result[i][2]);//状态
		   		 vo.setStartDate(result[i][3]);//开始日期
		   		 vo.setEndDate(result[i][4]);//结束日期
		   		 vo.setCrtTlr(sessionUserInfo.getTlrno());//创建人
		   		 vo.setCrtDateTime(BaseUtil.getCurrentDateTime());//创建时间
		   		 vo.setLastUpdTlr(sessionUserInfo.getTlrno());//最后更新人
		   		 vo.setLastUpdDateTime(vo.getCrtDateTime());//最后更新时间
		   		vo.setEndDate(StringUtil.isEmpty(vo.getEndDate()) ? "-" : vo.getEndDate());
		   		 dao.insert(vo);
		   	 }
		   //记录日志
		   	sessionUserInfo.addBizLog("update.log",
		   				new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(), "[用户黑名单]--excel导入" });
			
	}
	
}
