package com.ruimin.ifs.mng.process.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.dataobject.PageQueryResult;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.mng.process.bean.TlrRoleVO;
import com.ruimin.ifs.mng.process.bean.UserAuthority;

@Service
public class UserAuthorityDownService extends SnowService {
	public static UserAuthorityDownService getInstance() throws SnowException {
		return ContextUtil.getSingleService(UserAuthorityDownService.class);
	}
	
	public String qTlrRole(String tlrno,String funcid){
	    DBDao dao = DBDaos.newInstance();
	    return (String) dao.selectOne("com.ruimin.ifs.mng.rql.sysmng.qTlrRole", RqlParam.map().set("tlrno", tlrno).set("funcid", funcid));
	}

	// 用户权限查询
	public PageResult queryList(String tlrno, String tlrName, int pageIndex, int pageCount) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		List<Object> list = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryTlrRole",
				RqlParam.map().set("tlrno", tlrno == null ? "" : "%" + tlrno + "%").set("tlrName",
						tlrName == null ? "" : "%" + tlrName + "%"));
		int size = list.size();
		List<Object> returnList = new ArrayList<Object>();
		if (size > 0) {
			String tno = "";
			String rname = "";
			for (int i = 0; i < size; i++) {
				TlrRoleVO tlrRoleVO = (TlrRoleVO) list.get(i);
				if (tno.equals(tlrRoleVO.getTlrno())) {
					rname += "," + tlrRoleVO.getRoleName() + ",";
					rname = rname.replace(",,", ",");
					if (rname.endsWith(",")) {
						tlrRoleVO.setRoleName(rname.substring(0, rname.length() - 1));
					} else {
						tlrRoleVO.setRoleName(rname);
					}
				} else {
					tno = tlrRoleVO.getTlrno();
					rname = tlrRoleVO.getRoleName();
				}
			}
			String rtlrno = null;
			String rtlrName = null;
			String rroleName = null;
			try {
				for (int i = 0; i < size + 1; i++) {
					TlrRoleVO tlrRoleVO = (TlrRoleVO) list.get(i);
					if (StringUtils.isBlank(rtlrno)) {
						rtlrno = tlrRoleVO.getTlrno();
						rtlrName = tlrRoleVO.getTlrName();
						rroleName = tlrRoleVO.getRoleName();
					} else {
						if (!rtlrno.equals(tlrRoleVO.getTlrno())) {
							TlrRoleVO returnVo = new TlrRoleVO(rtlrno, rtlrName, rroleName);
							returnList.add(returnVo);
							rtlrno = tlrRoleVO.getTlrno();
							rtlrName = tlrRoleVO.getTlrName();
							rroleName = tlrRoleVO.getRoleName();
						} else {
							rtlrno = tlrRoleVO.getTlrno();
							rtlrName = tlrRoleVO.getTlrName();
							rroleName = tlrRoleVO.getRoleName();
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				TlrRoleVO returnVo = (TlrRoleVO) list.get(list.size() - 1);
				returnList.add(returnVo);
			}
		}

		PageQueryResult result = new PageQueryResult();
		if (returnList.size() < pageCount) {
			result.setQueryResult(returnList.subList(pageIndex - 1, returnList.size()));
		} else {
			result.setQueryResult(returnList.subList(pageIndex - 1, pageCount));
		}
		result.setTotalCount(returnList.size());

		return result;
	}

	/**
	 * jxl 导出excel
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] param = request.getParameter("param").split(";");
		DBDao dao = DBDaos.newInstance();
		String sql = "";
		List<Object> result = null;
		WritableWorkbook wwb = null;
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		try {
			wwb = Workbook.createWorkbook(ba);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int s = 0; s < param.length; s++) {
			result = new LinkedList<Object>();
			sql = "select tlrno,funcname from ifs_menu_inf b,ifs_staff d "
					+ " where b.funcid in ( select funcid from ifs_res_inf a where a.role_id in "
					+ " (select role_id from ifs_staff_role_rel where tlrno = '" + param[s] + "' "
					+ " ) ) order by b.lastdirectory, b.showseq ";
			result = dao.selectList("com.ruimin.ifs.mng.rql.sysmng.queryUserRoles",
					RqlParam.map().set("tlrno", param[s]));
			if (wwb != null) {
				WritableSheet ws = wwb.createSheet(param[s], s + 1);
				try {
					ws.addCell(new Label(0, 0, "用户ID"));
					ws.addCell(new Label(1, 0, "用户名称"));
					ws.addCell(new Label(2, 0, "所属权限"));
					for (int i = 0; i < result.size(); i++) {
						UserAuthority ua = (UserAuthority) result.get(i);
						ws.addCell(new Label(0, i + 1, ua.getTlrno()));
						ws.addCell(new Label(1, i + 1, ua.getTlrName()));
						ws.addCell(new Label(2, i + 1, ua.getFuncname()));
					}
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
			}
		}
		try {
			wwb.write();
			wwb.close();
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=userAuthority.xls");
			ServletOutputStream out = response.getOutputStream();
			ba.writeTo(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

}
