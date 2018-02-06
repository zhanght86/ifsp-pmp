package com.ruimin.ifs.pmp.qrcMng.process.service;

import com.ruimin.ifs.core.annotation.Service;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.rql.PageResult;
import com.ruimin.ifs.core.iface.service.SnowService;
import com.ruimin.ifs.core.util.ContextUtil;
import com.ruimin.ifs.dao.DBDao;
import com.ruimin.ifs.dao.DBDaos;
import com.ruimin.ifs.dao.param.RqlParam;
import com.ruimin.ifs.framework.process.query.Page;
import com.ruimin.ifs.gov.util.StringUtils;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.qrcMng.common.constants.QrcTypeInfoConstants;
import com.ruimin.ifs.pmp.qrcMng.process.bean.QrcTypeInfo;
import com.ruimin.ifs.util.SYSConstants.STATUS_Constants;

import java.util.List;

@Service
@SnowDoc(desc = "二维码类型查询service")
public class QrcTypeInfoService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static QrcTypeInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(QrcTypeInfoService.class);
	}

	/**
	 * 
	 * 功能描述: 二维码类型查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qrcTypeNo
	 * @param qrcTypeName
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public PageResult queryAll(String qrcTypeNo, String qrcTypeName, String qrcTypeSat, Page page)
			throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.qrcMng.rql.qrcTypeInfo.queryList",
				RqlParam.map().set("qrcTypeNo", StringUtils.isBlank(qrcTypeNo) ? "" : qrcTypeNo)
						.set("qrcTypeName", StringUtils.isBlank(qrcTypeName) ? "" : "%" + qrcTypeName + "%")
						.set("qrcTypeSat", StringUtils.isBlank(qrcTypeSat) ? "" : qrcTypeSat),
				page);
	}

	/**
	 * 
	 * 功能描述: 二维码类型查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qrcTypeNo
	 * @param qrcTypeName
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public List<QrcTypeInfo> queryChannelType() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select QRC_TYPE_NO,QRC_TYPE_NAME from QRC_TYPE_INFO where QRC_TYPE_SAT='"
				+ QrcTypeInfoConstants.QRC_TYPE_STATE_00 + "'", QrcTypeInfo.class);
	}

	/**
	 * 
	 * 功能描述: 二维码类型查询<br>
	 * 〈功能详细描述〉
	 *
	 * @param qrcTypeNo
	 * @param qrcTypeName
	 * @param page
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public QrcTypeInfo getQrcQrcTypeInfo(String qrcTypeNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(QrcTypeInfo.class, qrcTypeNo);
	}

	/**
	 * 根据主键获取二维码渠道参数
	 * 
	 * @param brCode
	 * @return
	 * @throws SnowException
	 */
	public QrcTypeInfo getQrcTypeName(String brCode) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.select(QrcTypeInfo.class, brCode);
	}

	/**
	 * 
	 * 功能描述:维码类型修改 <br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public int update(QrcTypeInfo bean) throws SnowException {
		synchronized (this) {
			DBDao dao = DBDaos.newInstance();
			QrcTypeInfo info = dao.update(bean);
			if (info != null) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * 
	 * 功能描述: 维码类型设置停用/启用<br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void delete(QrcTypeInfo bean) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		dao.update(bean);
	}

	/**
	 * 
	 * 功能描述: 维码类型保存<br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public int save(QrcTypeInfo bean) throws SnowException {
		synchronized (this) {
			bean.setQrcTypeNo(getQrcTypeNo());
			bean.setQrcTypeSat(STATUS_Constants.STATUS_00);
			DBDao dao = DBDaos.newInstance();
			QrcTypeInfo info = dao.insert(bean);
			if (info != null) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * 
	 * 功能描述:获取当前维码类型最大编号ID<br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public String queryMaxQrcTypeNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.qrcMng.rql.qrcTypeInfo.queryMaxQrcTypeNo");
	}

	/**
	 * 
	 * 功能描述:维码类型编号ID <br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private String getQrcTypeNo() throws SnowException {
		String chnlNo = "QR01";
		String idTmp = queryMaxQrcTypeNo();
		if (idTmp != null) {
			String tmp = idTmp.substring(2);
			if (tmp.equals(QrcTypeInfoConstants.QRC_TYPE_STATE_99)) {
				SnowExceptionUtil.throwWarnException(QrcTypeInfoConstants.QRC_TYPE_STATE_99, "二维码类型编号已达最大值，请联系管理员！");
			}
			int id = Integer.parseInt(tmp) + 1;
			chnlNo = "QR" + BaseUtil.fillString(String.valueOf(id), '0', 2, false);
		}
		return chnlNo;
	}
}
