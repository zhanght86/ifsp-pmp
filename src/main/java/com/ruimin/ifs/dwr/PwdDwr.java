package com.ruimin.ifs.dwr;

import com.ruimin.ifs.core.encrypt.EncryptUtil;
import com.ruimin.ifs.core.encrypt.EncryptUtil.EncryptType;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.service.OperMngEntryService;
import com.ruimin.ifs.po.TblStaff;

public class PwdDwr {
	
	public String reset(String json) throws SnowException{
		String[] map = json.split("_");
		String oprId =  map[0];
		String newPwd =  map[1];
		//String newPwd = CommonFunction.getRandomCharAndNumr(8);
		
		OperMngEntryService operMngEntryService = OperMngEntryService.getInstance();
		TblStaff tblStaff = operMngEntryService.checkId(oprId);
		String passwdenc = tblStaff.getPasswdenc();
		if(StringUtils.isBlank(passwdenc)){
			passwdenc = "MD5";
			tblStaff.setPasswdenc(passwdenc);
		}
		if("MD5".equals(passwdenc)){
			tblStaff.setPassword(EncryptUtil.getEncrypt(EncryptType.MD5).encrypt(newPwd));
		}else{
			tblStaff.setPassword(EncryptUtil.getEncrypt(EncryptType.AES).encrypt(newPwd));
		}
		operMngEntryService.updateOperMngEntry(tblStaff);
		
        return newPwd;
        
    }
//	public static void main(String[] args)throws Exception {
//		String pwd = EncryptUtil.getEncrypt(EncryptType.MD5).encrypt("a111111");
//		System.out.println(pwd);
//	}

}
