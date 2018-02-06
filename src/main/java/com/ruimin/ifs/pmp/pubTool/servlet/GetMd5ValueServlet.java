package com.ruimin.ifs.pmp.pubTool.servlet;

import com.ruim.ifsp.signature.IfspSdkSignAtureUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统随机生成MD5值 名称：〈一句话功能简述〉<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 日期：2016年8月5日 <br>
 * 作者：Administrator <br>
 * 说明：<br>
 */
public class GetMd5ValueServlet extends HttpServlet {
	private static final long serialVersionUID = -7825355637448948879L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 系统随机生成MD5值
		/*String md5Value = IfspSdkSignAtureUtil.getMd5Key().getMd5Key();*/
		// ------2017.12.13 商户进件修改签名包------
		String md5Value = IfspSdkSignAtureUtil.getMd5Key().getCertId();

		// 返回信息给上一个JSP
		System.out.println(md5Value);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(md5Value);
		out.close();
	}
}
