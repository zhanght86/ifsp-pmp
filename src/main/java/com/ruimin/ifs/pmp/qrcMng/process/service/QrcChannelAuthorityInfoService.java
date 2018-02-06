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
import com.ruimin.ifs.pmp.chnlMng.process.bean.ChannelInfo;
import com.ruimin.ifs.pmp.pubTool.util.BaseUtil;
import com.ruimin.ifs.pmp.qrcMng.common.constants.QrcTypeInfoConstants;
import com.ruimin.ifs.pmp.qrcMng.process.bean.QrcChannelAuthorityInfo;

import java.util.List;

@Service
@SnowDoc(desc = "渠道二维码权限service")
public class QrcChannelAuthorityInfoService extends SnowService {
	/**
	 * 获取实例
	 * 
	 * @return 服务实例，单例
	 * @throws SnowException
	 */
	public static QrcChannelAuthorityInfoService getInstance() throws SnowException {
		return ContextUtil.getSingleService(QrcChannelAuthorityInfoService.class);
	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理查询<br>
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
	public PageResult queryAll(String qChlNo, String qQrcTypeNo, Page page) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.qrcMng.rql.qrcChannelAuthorityInfo.queryList",
				RqlParam.map().set("qChlNo", StringUtils.isBlank(qChlNo) ? "" : qChlNo).set("qQrcTypeNo",
						StringUtils.isBlank(qQrcTypeNo) ? "" : qQrcTypeNo),
				page);
	}

	/**
	 * 
	 * 功能描述: 渠道查询<br>
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
	public List<ChannelInfo> queryChannelInfo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.queryAll("select CHL_ID,CHL_NAME from  PAGY_CHANNEL_INFO where CHL_STAT='00'", ChannelInfo.class);

	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理查询<br>
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
	public List<Object> quer(String chlNo, String qrcTypeNo) throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return dao.selectList("com.ruimin.ifs.pmp.qrcMng.rql.qrcChannelAuthorityInfo.queryList",
				RqlParam.map().set("qChlNo", StringUtils.isBlank(chlNo) ? "" : chlNo).set("qQrcTypeNo",
						StringUtils.isBlank(qrcTypeNo) ? "" : qrcTypeNo));
	}

	/**
	 * 
	 * 功能描述:二维码渠道权限管理修改 <br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public int update(QrcChannelAuthorityInfo bean) throws SnowException {
		synchronized (this) {
			DBDao dao = DBDaos.newInstance();
			QrcChannelAuthorityInfo info = dao.update(bean);
			if (info != null) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理删除<br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public boolean delete(QrcChannelAuthorityInfo bean) throws SnowException {
		synchronized (this) {
			DBDao dao = DBDaos.newInstance();
			return dao.delete(bean);
		}
	}

	/**
	 * 
	 * 功能描述: 二维码渠道权限管理保存<br>
	 * 〈功能详细描述〉
	 *
	 * @param bean
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public int save(QrcChannelAuthorityInfo bean) throws SnowException {
		synchronized (this) {
			DBDao dao = DBDaos.newInstance();
			bean.setChlAuNo(getQrcTypeNo());
			QrcChannelAuthorityInfo info = dao.insert(bean);
			if (info != null) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * 
	 * 功能描述:获取当前二维码渠道参数最大编号ID<br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public String queryMaxQrcTypeNo() throws SnowException {
		DBDao dao = DBDaos.newInstance();
		return (String) dao.selectOne("com.ruimin.ifs.pmp.qrcMng.rql.qrcChannelAuthorityInfo.queryMaxQrcTypeNo");
	}

	/**
	 * 
	 * 功能描述:二维码渠道参数编号ID <br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 * @throws SnowException
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private String getQrcTypeNo() throws SnowException {
		String chnlNo = QrcTypeInfoConstants.QRC_CHNL_NO_MIN;
		String idTmp = queryMaxQrcTypeNo();
		if (idTmp != null) {
			String tmp = idTmp.substring(8);
			if (tmp.equals(QrcTypeInfoConstants.QRC_CHNL_NO_MAX)) {
				SnowExceptionUtil.throwWarnException(QrcTypeInfoConstants.QRC_CHNL_NO_MAX, "二维码渠道参数编号已达最大值，请联系管理员！");
			}
			int id = Integer.parseInt(tmp) + 1;
			chnlNo = "AU" + BaseUtil.fillString(String.valueOf(id), '0', 8, false);
		}
		return chnlNo;
	}

}
