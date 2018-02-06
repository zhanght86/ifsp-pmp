package com.ruimin.ifs.util;

import org.apache.commons.lang.StringUtils;

import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.framework.process.generator.SeqNoGenerator;

/**
 * 序列号生成器
 * 
 * @author pengning
 * @date 2015-7-2 上午9:21:06
 * @Description
 */
public class SeqNoGenUtil {
	/** 机构编号ValueNo ***/
	public static final int VALUE_NO_ORG = 10;
	/** 角色岗位ValueNo ***/
	public static final int VALUE_NO_ROLE_ID = 11;
	/** 批量设置 ****/
	public static final int VALUE_NO_BHPROCSTEP_ID = 12;

	/** 风控规则 **/
	public static final int VALUE_NO_CONTROLRULE = 13;

	public static final int VALUE_NO_BADINF_ID = 14;

	public static final int VALUE_NO_MCHTEVNT_ID = 15;

	public static final int VALUE_NO_TERMRUFUSE_ID = 16;

	public static String leftPadZero(int value, int length) {
		return StringUtils.leftPad(String.valueOf(value), length, '0');
	}

	/**
	 * 获取机构编号
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static String genOrgBrCode() throws SnowException {
		int brcodeNo = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_ORG);
		return leftPadZero(brcodeNo, 4);
	}

	/**
	 * 获取商户事件ID
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static String genMchtEventCode() throws SnowException {
		int brcodeNo = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_MCHTEVNT_ID);
		return leftPadZero(brcodeNo, 6);
	}

	/**
	 * 角色主键
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static int genRoleId() throws SnowException {
		return SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_ROLE_ID);

	}

	/**
	 * 终端拒绝表主键
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static String genId() throws SnowException {
		int id = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_TERMRUFUSE_ID);
		return leftPadZero(id, 7);
	}

	/**
	 * 获取批量ID
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static int genBhProcStepId() throws SnowException {
		return SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_BHPROCSTEP_ID);
	}

	/**
	 * 获取风控id
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static int genControlRuleId() throws SnowException {
		return SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_CONTROLRULE);
	}

	/**
	 * 特征标识id
	 * 
	 * @return
	 * @throws SnowException
	 */
	public static String genControlFeatureId() throws SnowException {
		int brcodeNo = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_CONTROLRULE);
		return leftPadZero(brcodeNo, 8);
	}

	public static String genCtrlRuleId() throws SnowException {
		int brcodeNo = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_CONTROLRULE);
		return leftPadZero(brcodeNo, 4);
	}

	public static String genBadInfId() throws SnowException {
		int brcodeNo = SeqNoGenerator.getInstance().getSeqNo(VALUE_NO_BADINF_ID);
		return leftPadZero(brcodeNo, 9);
	}
}
