package com.ruimin.ifs.mng.comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

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
import com.ruimin.ifs.framework.core.bean.TreeNode;
import com.ruimin.ifs.framework.dataobject.QueryParamBean;
import com.ruimin.ifs.framework.dataobject.UpdateRequestBean;
import com.ruimin.ifs.framework.dataset.field.FieldBean;
import com.ruimin.ifs.mng.common.constants.OrgManagerConstants;
import com.ruimin.ifs.mng.process.bean.LoginLogVO;
import com.ruimin.ifs.mng.process.service.OrgService;
import com.ruimin.ifs.mng.process.service.TlrLoginLogService;
import com.ruimin.ifs.po.TblBctl;
import com.ruimin.ifs.util.KeyGenerate;
import com.ruimin.ifs.util.SnowConstant;

@SnowDoc(desc = "机构下拉查询")
@ActionResource
public class BctlAllSelectAction extends SnowAction {
	private static final String VIR_BRCODE = "0000";

	/**
	 * 机构下拉查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "机构下拉查询")
	public List<LoginLogVO> queryBctlAllSelect(QueryParamBean queryBean) throws SnowException {
		return TlrLoginLogService.getInstance().queryBctlAllSelect();
	}

	/**
	 * 机构查询
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "机构查询")
	public List<TreeNode> queryAllOrg(QueryParamBean queryBean) throws SnowException {

		boolean bl = Boolean.parseBoolean(queryBean.getParameter("showvir", "false"));
		// String a = queryBean.getParameter("currentBrClass");
		String currentBrClass = queryBean.getParameter("currentBrClass", "");
		List<TreeNode> list = new ArrayList<TreeNode>();
		OrgService service = OrgService.getInstance();
		if (bl) {
			// 创建虚拟节点
			TreeNode vnode = new TreeNode();
			vnode.setIconCls("fa fa-bank");
			vnode.setText("机构树");
			vnode.setId(VIR_BRCODE);
			vnode.setState(TreeNode.EXPAND_STATUS_OPEN);
			list.add(vnode);
		}

		List<Object> orgList = service.listUpOrg(currentBrClass);
		for (Object obj : orgList) {
			TblBctl org = (TblBctl) obj;
			TreeNode node = new TreeNode();
			node.setAttributes(org);
			node.setIconCls("fa fa-bank");
			node.setText(org.getBrname());
			node.setId(org.getBrcode());
			if (StringUtils.isBlank(org.getBlnUpBrcode()) && bl) {
				node.setPid(VIR_BRCODE);
			} else {
				node.setPid(org.getBlnUpBrcode());
			}
			node.setState(TreeNode.EXPAND_STATUS_OPEN);
			list.add(node);
		}
		return list;
	}

	/**
	 * 分页查询机构信息
	 * 
	 * @param queryBean
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "分页查询机构信息")
	public PageResult queryOrgByCode(QueryParamBean queryBean) throws SnowException {
		// 操作员登录session
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();

		List<String> list = OrgService.getInstance().queryBrcode();

		Map<String, String> queryMap = new HashMap<String, String>();
		// 上级机构
		String blnUpBrcode = queryBean.getParameter("blnUpBrcode");
		if (StringUtils.isBlank(blnUpBrcode)) {
			blnUpBrcode = "";
		}
		queryMap.put("blnUpBrcode", blnUpBrcode);
		// 机构号
		queryMap.put("brno", queryBean.getParameter("qbrno", ""));
		// 机构名称
		String brname = queryBean.getParameter("qbrname", "");
		if (StringUtils.isNotBlank(brname)) {
			brname = "%" + brname.trim() + "%";
		}
		queryMap.put("brname", brname);
		// 操作员机构号
		queryMap.put("brCode", sessionUserInfo.getBrCode());
		String brclass = queryBean.getParameter("qbrclass", "");
		queryMap.put("brclass", brclass);
		// 根据操作员机构编码查找其所有下级的机构
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() == 1) {
			sb.append(list.get(0)).append(",");
		} else {
			for (String brCode : list) {
				sb.append("'" + brCode + "',");
			}
		}
		queryMap.put("brcode", sb.toString().substring(0, sb.length() - 1));
		// 查询机构信息
		return OrgService.getInstance().queryListTmp(queryMap, queryBean.getPage());

	}

	/**
	 * 根据brcode获取机构名称method
	 * 
	 * @param bean
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getOrgName(FieldBean bean, String key, ServletRequest request) throws SnowException {
		if (StringUtils.isNotBlank(key)) {
			TblBctl org = OrgService.getInstance().queryOrgById(key);
			if (org != null) {
				return org.getBrname();
			}
		}
		return key;
	}

	/**
	 * 新增机构
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "新增机构")
	public void addOrg(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("orgmanger");
		TblBctl ifsOrg = new TblBctl();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsOrg);
			ifsOrg.setBrcode(KeyGenerate.generateOrgId());
		}
		if (StringUtils.isBlank(ifsOrg.getBlnUpBrcode())) {
			if (!SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())) {
				SnowExceptionUtil.throwWarnException("WEB_E0039", "上级机构不能为空!");
			}
		}
		if (StringUtils.isBlank(ifsOrg.getBrclass())) {
			SnowExceptionUtil.throwWarnException("WEB_E0039", "机构级别不能为空!");
		}
		// 检查机构号是否重复
		int countExist = OrgService.getInstance().queryOrgByBrnoId(ifsOrg.getBrno());
		if (countExist != 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0037", ifsOrg.getBrno());
		}
		if (ifsOrg.getBrclass().equals(SnowConstant.BRCODE_CLASS_HEAD)) {
			// 检查添加是否为总行
			int count = OrgService.getInstance().queryOrgByBrClass();
			if (count > 0) {
				SnowExceptionUtil.throwWarnException("WEB_E0038");
			}
		}
		OrgService.getInstance().addOrg(ifsOrg);

		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		TblBctl objs = OrgService.getInstance().queryOrgById(sessionUserInfo.getBrCode());
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[机构管理]--新增 : 内部机构号[brcode]=" + ifsOrg.getBrcode() });
		List<String> list = new ArrayList<String>();
		if (OrgManagerConstants.BR_CLASS_HEAD.equals(objs.getBrclass())) {
			List<Object> orgs = OrgService.getInstance().listAll();
			for (Object org : orgs) {
				TblBctl bctl = (TblBctl) org;
				list.add(bctl.getBrcode());
			}
		}
		if (OrgManagerConstants.BR_CLASS_BRANCH.equals(objs.getBrclass())) {
			List<Object> orgs = OrgService.getInstance().listOrg(sessionUserInfo.getBrCode());
			for (Object org : orgs) {
				TblBctl bctl = (TblBctl) org;
				list.add(bctl.getBrcode());
			}
			list.add(sessionUserInfo.getBrCode());
		}
		if (OrgManagerConstants.BR_CLASS_SUB_BRANCH.equals(objs.getBrclass())) {
			list.add(sessionUserInfo.getBrCode());
		}
		Map<String, Object> map = sessionUserInfo.getExtenMap();
		map.put("brhs", list);
	}

	/**
	 * 机构修改
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "机构修改")
	public void updateOrg(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		// 获取数据集
		UpdateRequestBean reqBean = updateMap.get("orgmanger");
		String brclass = reqBean.getParameter("brclass");
		TblBctl ifsOrg = new TblBctl();
		while (reqBean.hasNext()) {
			DataObjectUtils.map2bean(reqBean.next(), ifsOrg);
		}
		if (StringUtils.isBlank(ifsOrg.getBrclass())) {
			SnowExceptionUtil.throwWarnException("WEB_E0039", "机构级别不能为空!");
		}
		// 不能修改总行的级别
		/*
		 * if(SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())//原级别为总行
		 * && !SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())){//
		 * 修改后级别非总行
		 * SnowExceptionUtil.throwWarnException("WEB_E0039","不能修改总行的级别"); }
		 */
		if (SnowConstant.BRCODE_CLASS_HEAD.equals(brclass)// 原级别为总行
				&& !SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())) {// 修改后级别非总行
			SnowExceptionUtil.throwWarnException("WEB_E0039", "不能修改总行的级别");
		}

		/*
		 * //检查机构编号是否重复 int br =
		 * OrgService.getInstance().queryOrgByBrnoId(ifsOrg.getBrno()); if
		 * (br>0) {
		 * SnowExceptionUtil.throwWarnException("WEB_E0037",ifsOrg.getBrno()); }
		 */

		if (!SnowConstant.BRCODE_CLASS_HEAD.equals(brclass)// 原级别不是总行
				&& SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())) {// 修改后级别是总行
			// 查询总行是否存在
			int count = OrgService.getInstance().queryOrgByBrClass();
			if (count > 0) {
				SnowExceptionUtil.throwWarnException("WEB_E0039", "总行已存在，不能修改为总行！");
			}
		}
		if (StringUtils.isBlank(ifsOrg.getBlnUpBrcode())) {
			if (!SnowConstant.BRCODE_CLASS_HEAD.equals(ifsOrg.getBrclass())) {
				SnowExceptionUtil.throwWarnException("WEB_E0039", "上级机构不能为空!");
			}
		}
		OrgService.getInstance().updateOrg(ifsOrg);
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[机构管理]--修改 : 内部机构号[brcode]=" + ifsOrg.getBrcode() });
	}

	/**
	 * 设置机构启用/停用
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "设置机构启用/停用")
	public void updateOrgStatus(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("orgmanger");
		TblBctl ifsOrg = new TblBctl();
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		if (reqBean.hasNext()) {
			String status = reqBean.getParameter("setstatus");
			String brcode = reqBean.next().get("brcode");
			OrgService.getInstance().updateOrgStatus(brcode, status);
			// 打印日志
			sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(),
					sessionUserInfo.getBrno(), "[机构管理]--修改状态 : 内部机构号[brcode]=" + ifsOrg.getBrcode() });
		} else {
			SnowExceptionUtil.throwWarnException("WEB_E0039", "无法获取提交机构信息!");
		}
	}

	/**
	 * 全量导入机构
	 * 
	 * @param upFileMap
	 * @return
	 * @throws SnowException
	 */
	/*
	 * @Action(propagation=Propagation.REQUIRED)
	 * 
	 * @SnowDoc(desc="全量导入机构") public FormReturnBean importOrgLst(Map<String,
	 * UploadFilebean> upFileMap) throws SnowException{ boolean isSuccess =
	 * false; SessionUserInfo sessionUserInfo =
	 * SessionUserInfo.getSessionUserInfo(); OrgService service =
	 * OrgService.getInstance(); String filePath =
	 * upFileMap.get("upFile").getContentMsg(); getLogger().info(filePath);
	 * List<TblBctl> list = new ArrayList<TblBctl>(); int success = 0; int fail
	 * = 0; StringBuffer failMsg = new StringBuffer();//记录错误行号 StringBuffer
	 * brNoList=new StringBuffer();//已经放入列表中的机构号 int countHead =
	 * service.queryOrgByBrClass(); //总行数量 int recount = 0;
	 * 
	 * //机构级别Map Map<String,String> brclassMap =
	 * service.getDataDicByTypeNo("105"); //内部机构号 Map<String,String> brCodeMap =
	 * service.getAllBrcode(); //地区编号map Map<String,String> cityMap =
	 * service.getAllCity(); try {
	 * 
	 * TblBctl inf = null;
	 * 
	 * BufferedReader reader = new BufferedReader(new InputStreamReader(new
	 * FileInputStream(filePath), "GBK")); while(reader.ready()){ String tmp =
	 * reader.readLine(); recount++; if (tmp!=null) {
	 * 
	 * String[] data = tmp.split(","); if(data.length!=11){ fail++;
	 * failMsg.append(",").append(recount); continue; } inf = new TblBctl();
	 * //机构号、机构名称、机构级别、账务机构不能为空
	 * if(StringUtils.isBlank(data[0])||StringUtils.isBlank(data[1])||
	 * StringUtils.isBlank(data[2])||StringUtils.isBlank(data[4])){ fail++;
	 * failMsg.append(",").append(recount); continue; } //检查机构号是否重复 int count =
	 * service.queryOrgByBrNo(data[0],"");
	 * if(count>0||brNoList.indexOf(data[0])!=-1){ fail++;
	 * failMsg.append(",").append(recount); continue; } inf.setBrno(data[0]);
	 * inf.setBrname(data[1]); //根据机构级别描述查询机构级别
	 * if(StringUtils.isBlank(brclassMap.get(data[2].trim()))){ fail++;
	 * failMsg.append(",").append(recount); continue; }
	 * inf.setBrclass(brclassMap.get(data[2].trim())); //检查是否为总行（总行只能有一个）
	 * if(inf.getBrclass().equals(SnowConstant.BRCODE_CLASS_HEAD)){
	 * if(countHead>0){ fail++; failMsg.append(",").append(recount); continue; }
	 * countHead++; if(!StringUtils.isBlank(data[3])){//总行的上级机构为空 fail++;
	 * failMsg.append(",").append(recount); continue; } }else{//非总行的上级机构不能为空
	 * if(StringUtils.isBlank(data[3])){//非总行的上级机构不能为空 fail++;
	 * failMsg.append(",").append(recount); continue; }
	 * if(StringUtils.isBlank(brCodeMap.get(data[3].trim()))){ fail++;
	 * failMsg.append(",").append(recount); continue; }
	 * inf.setBlnUpBrcode(brCodeMap.get(data[3].trim())); } //账务机构
	 * if(StringUtils.isBlank(brCodeMap.get(data[4].trim()))){ fail++;
	 * failMsg.append(",").append(recount); continue; }
	 * inf.setBlnManageBrcode(brCodeMap.get(data[4].trim()));
	 * inf.setTeleno(data[5]); inf.setPostno(data[6]); inf.setAddress(data[7]);
	 * inf.setCupBrhId(data[8]); inf.setHostBrhId(data[9]); //所属地区
	 * if(StringUtils.isBlank(cityMap.get(data[10].trim()))){ fail++;
	 * failMsg.append(",").append(recount); continue; }
	 * inf.setAreaCd(cityMap.get(data[10].trim()));
	 * inf.setBrcode(SeqNoGenUtil.genOrgBrCode());
	 * inf.setLastUpdDate(DateUtil.getCurrDate());
	 * inf.setLastUpdTlr(sessionUserInfo.getTlrno()); inf.setStatus("00");
	 * brCodeMap.put(inf.getBrname(), inf.getBrcode());
	 * brNoList.append(data[0]).append(";"); list.add(inf); success++; } }
	 * reader.close();
	 * 
	 * 
	 * } catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } catch (FileNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * if(list!=null&&list.size()>0){ service.addOrgList(list); } List<String>
	 * msgList = new ArrayList<String>();
	 * 
	 * msgList.add("文件导入完成，其中:"); msgList.add("成功写入行数:"+success);
	 * msgList.add("未导入行数:"+fail); if(!StringUtils.isBlank(failMsg.toString())){
	 * msgList.add("其中错误的记录为：["+failMsg.toString().substring(1)+"]"); }
	 * FormReturnBean bean = new FormReturnBean();
	 * bean.setSendUrl("/pages/mng/fileUpload.jsp");
	 * bean.getReturnAttrMap().put("impFile",
	 * upFileMap.get("upFile").getFilename());
	 * bean.getReturnAttrMap().put("result", true);
	 * bean.getReturnAttrMap().put("resultMsg",msgList); //打印日志
	 * sessionUserInfo.addBizLog("update.log",new
	 * String[]{sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
	 * "[机构管理]--全量导入机构管理"}); return bean; }
	 */
	/**
	 * 系统机构管理删除
	 * 
	 * @param updateMap
	 * @throws SnowException
	 */
	/* add by ttt 20151109 */
	@Action(propagation = Propagation.REQUIRED)
	@SnowDoc(desc = "删除")
	public void delOrg(Map<String, UpdateRequestBean> updateMap) throws SnowException {
		UpdateRequestBean reqBean = updateMap.get("orgmanger");
		String brcode = reqBean.getParameter("brcode");

		// 检查是否存在下级机构
		int mcht=OrgService.getInstance().queryOrgMchtOrgCode(brcode);
		int count = OrgService.getInstance().queryOrgByOrgCode(brcode);
		if (mcht > 0) {
            SnowExceptionUtil.throwWarnException("WEB_E0045", "此机构有商户在使用！");
        } else {
            OrgService.getInstance().delOrgByCode(brcode);

        }
		if (count > 0) {
			SnowExceptionUtil.throwWarnException("WEB_E0045", "请先删除该机构的下级机构！");
		} else {
			OrgService.getInstance().delOrgByCode(brcode);

		}
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		// 打印日志
		sessionUserInfo.addBizLog("update.log", new String[] { sessionUserInfo.getTlrno(), sessionUserInfo.getBrno(),
				"[机构管理]--删除 : 内部机构号[brcode]=" + brcode });
	}

	/**
	 * 获取操作员所属机构及下属机构
	 * 
	 * @return
	 * @throws SnowException
	 */
	@Action
	@SnowDoc(desc = "获取操作员所属机构及下属机构")
	public PageResult selOrg(QueryParamBean bean) throws SnowException {
		/** 查询条件 */
		String brname = bean.getParameter("qbrname");// 机构名称

		/** 获取操作员编号 */
		SessionUserInfo sessionUserInfo = SessionUserInfo.getSessionUserInfo();
		String tlrno = sessionUserInfo.getTlrno();

		/** 查询机构信息 */
		return OrgService.getInstance().selOrg(tlrno, brname, bean.getPage());
	}

}
