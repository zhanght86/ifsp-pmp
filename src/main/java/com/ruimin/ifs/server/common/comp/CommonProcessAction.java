package com.ruimin.ifs.server.common.comp;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.ruimin.ifs.core.action.base.ActionCacheAccess;
import com.ruimin.ifs.core.action.base.ActionReflectFactory;
import com.ruimin.ifs.core.annotation.Action;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.common.bean.SystemDataObject;
import com.ruimin.ifs.core.common.core.SnowApi;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.util.constant.SnowEnum.Propagation;
import com.ruimin.ifs.core.util.constant.SnowErrorCode;
import com.ruimin.ifs.server.common.bean.MerSignResp;
import com.ruimin.ifs.server.common.process.ProcConfUtil;

@SnowDoc(desc = "通用处理组件")
@ActionResource
public class CommonProcessAction extends SnowAction {

	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "处理前执行")
	public String preProcess(String requetMsg, SystemDataObject systemData) throws SnowException {
		getLogger().info("[" + systemData.getSerialNumber() + "] pre process request msg:" + requetMsg);
		String actionId = ProcConfUtil.getActionId(systemData.getInsideCode());
		if (StringUtils.isNotBlank(actionId)) {
			return actionId;
		} else {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0005,
					new String[] { "根据渠道号交易码[" + systemData.getInsideCode() + "]不能获取处理组件!" });
		}
		return null;
	}

	@Action
	@SnowDoc(desc = "执行业务处理组件")
	public String execAction(String actionId, String requestMsg, SystemDataObject systemData) throws SnowException {
		ActionCacheAccess actionCacheAccess = ActionReflectFactory.getInstance().initMethodAccess(actionId);
		String[] clsMethod = actionId.split(":");
		Method execMd = null;
		Method[] mds = actionCacheAccess.getActionClsInstance().getClass().getDeclaredMethods();
		for (int i = 0; i < mds.length; i++) {
			Method md = mds[i];
			if (md.getName().equals(clsMethod[1])) {
				execMd = md;
				break;
			}
		}
		Class<?> paramCls = null;
		int len = 1;
		if (execMd != null) {
			Class<?>[] paramTypes = execMd.getParameterTypes();
			if (paramTypes != null && paramTypes.length > 0) {
				paramCls = paramTypes[0];
				len = paramTypes.length;
			}
		}
		Object[] paramValues = new Object[len];
		if (paramCls != null) {
			if (paramCls == String.class) {
				paramValues[0] = requestMsg;
			} else {
				if (StringUtils.isNotBlank(requestMsg)) {
					Gson gson = new Gson();
					Object obj = gson.fromJson(requestMsg, paramCls);
					paramValues[0] = obj;
				} else {
					paramValues[0] = new Object();
				}
			}
			if (len > 1) {
				paramValues[1] = systemData;
			}
		} else {
			SnowExceptionUtil.throwErrorException(SnowErrorCode.MESSAGE_SYS_0005,
					new String[] { "组件[" + actionId + "]输入参数不符合要求!" });
		}
		getLogger().info("[" + systemData.getSerialNumber() + "] process action" + actionId);
		Object retValue = null;
		try {
			retValue = SnowApi.getInstance().execAction(actionId, paramValues, Object.class);
		} catch (SnowException e) {
			MerSignResp resp = new MerSignResp();
			resp.setRespCode(e.getErrorCode());
			resp.setRespMsg(e.getErrorParams()[0]);
			retValue = resp;
		}

		if (retValue != null) {
			if (retValue instanceof String) {
				return (String) retValue;
			} else {
				Gson gson = new Gson();
				return gson.toJson(retValue);
			}
		}
		return "";
	}

	@Action
	@SnowDoc(desc = "处理后执行")
	public String postProcess(SystemDataObject systemData, String returnMsg) throws SnowException {
		getLogger().info("[" + systemData.getSerialNumber() + "] post process return msg:" + returnMsg);
		return returnMsg;
	}

	@Action
	@SnowDoc(desc = "系统异常执行")
	public void exceptionProcess(SystemDataObject systemData) throws SnowException {
		getLogger().info("[" + systemData.getSerialNumber() + "] 执行异常:" + systemData.getSnowException().getMessage());
		throw systemData.getSnowException();
	}

}
