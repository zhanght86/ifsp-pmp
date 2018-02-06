package com.ruimin.ifs.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ruimin.ifs.framework.core.bean.Function;
import com.ruimin.ifs.rql.annotation.Id;
import com.ruimin.ifs.rql.annotation.Table;

@Table("ifs_menu_inf")
public class TblFunction implements Function, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String funcid;
	private String funcname;
	private String pagepath;
	private Integer location;
	private Integer isdirectory;
	private String lastdirectory;
	private Integer showseq;
	private String funcClass;
	private String funcType;
	private String workflowFlag;
	private String upFuncCode;
	private String funcDesc;
	private String status;
	private String miscflgs;
	private String misc;
	private String iconCls;

	public String getFuncid() {
		return funcid;
	}

	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getIsdirectory() {
		return isdirectory;
	}

	public void setIsdirectory(Integer isdirectory) {
		this.isdirectory = isdirectory;
	}

	public String getLastdirectory() {
		return lastdirectory;
	}

	public void setLastdirectory(String lastdirectory) {
		this.lastdirectory = lastdirectory;
	}

	public Integer getShowseq() {
		return showseq;
	}

	public void setShowseq(Integer showseq) {
		this.showseq = showseq;
	}

	public String getFuncClass() {
		return funcClass;
	}

	public void setFuncClass(String funcClass) {
		this.funcClass = funcClass;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public String getWorkflowFlag() {
		return workflowFlag;
	}

	public void setWorkflowFlag(String workflowFlag) {
		this.workflowFlag = workflowFlag;
	}

	public String getUpFuncCode() {
		return upFuncCode;
	}

	public void setUpFuncCode(String upFuncCode) {
		this.upFuncCode = upFuncCode;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMiscflgs() {
		return miscflgs;
	}

	public void setMiscflgs(String miscflgs) {
		this.miscflgs = miscflgs;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	List<Function> children = new ArrayList<Function>();

	@Override
	public void addChild(Function sub) {
		children.add(sub);
	}

	@Override
	public List<Function> children() {
		return children;
	}
	//
	// public int compareTo(Function newFunc) {
	// if (newFunc == null) {
	// return 1;
	// }
	// if (this.getShowseq() == null) {
	// return -1;
	// } else {
	// return this.getShowseq().compareTo(newFunc.getShowseq());
	// }
	// }
}
