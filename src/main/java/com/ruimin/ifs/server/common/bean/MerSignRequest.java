package com.ruimin.ifs.server.common.bean;

import com.ruimin.ifs.server.common.annotation.Stand;
import com.ruimin.ifs.server.common.annotation.Stand.AllowEmpty;
import com.ruimin.ifs.server.common.annotation.VerifyInterface;
import java.util.List;

/**
 * 商户签约请求报文
 */
@VerifyInterface
public class MerSignRequest extends BaseRequest {

	@Stand(maxLen = 16, empty = AllowEmpty.NULL)
	private String mchtId;
	@Stand(maxLen = 60)
	private String mchtName;
	@Stand(maxLen = 40)
	private String mchtCnAbbr;
	@Stand(maxLen = 20)
	private String mchtArtifName;
	@Stand(scope = { "00", "01", "02", "03" })
	private String mchtArtifType;
	@Stand(maxLen = 30)
	private String mchtArtifId;
	@Stand(maxLen = 20)
	private String mchtArtifPhone;
	@Stand(maxLen = 20)
	private String mchtPersonName;
	@Stand(maxLen = 40)
	private String mchtPhone;

	@Stand(maxLen = 20, empty = AllowEmpty.NULL)
	private String mchtTrcnNo;
	@Stand(maxLen = 10, empty = AllowEmpty.NULL)
	private List<String> livePicNo;
	@Stand(maxLen = 256, empty = AllowEmpty.NULL)
	private String livePicPath;
	@Stand(maxLen = 80)
	private String mchtAddr;
	@Stand(maxLen = 40, empty = AllowEmpty.NULL)
	private String logoPicNo;
	@Stand(maxLen = 256, empty = AllowEmpty.NULL)
	private String logoPicPath;
	@Stand(maxLen = 40)
	private String mchtLicnNo;
	@Stand(maxLen = 256)
	private String mchtLicnPicPath;
	@Stand(maxLen = 20)
	private String mchtLongitude;
	@Stand(maxLen = 20)
	private String mchtLatitude;
	@Stand(maxLen = 20, empty = AllowEmpty.NULL)
	private String mchtAmrNo;
	@Stand(maxLen = 20, empty = AllowEmpty.NULL)
	private String mchtAmrName;
	@Stand(maxLen = 32, empty = AllowEmpty.NULL)
	private String mchtAmrPhone;
	@Stand(maxLen = 10)
	private List<String> mchtArtifPicNo;
	@Stand(maxLen = 256)
	private String mchtArtifPicPath;

	/**
	 * @return the mchtId
	 */
	public String getMchtId() {
		return mchtId;
	}

	/**
	 * @param mchtId
	 *            the mchtId to set
	 */
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}

	/**
	 * @return the mchtName
	 */
	public String getMchtName() {
		return mchtName;
	}

	/**
	 * @param mchtName
	 *            the mchtName to set
	 */
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	/**
	 * @return the mchtCnAbbr
	 */
	public String getMchtCnAbbr() {
		return mchtCnAbbr;
	}

	/**
	 * @param mchtCnAbbr
	 *            the mchtCnAbbr to set
	 */
	public void setMchtCnAbbr(String mchtCnAbbr) {
		this.mchtCnAbbr = mchtCnAbbr;
	}

	/**
	 * @return the mchtArtifName
	 */
	public String getMchtArtifName() {
		return mchtArtifName;
	}

	/**
	 * @param mchtArtifName
	 *            the mchtArtifName to set
	 */
	public void setMchtArtifName(String mchtArtifName) {
		this.mchtArtifName = mchtArtifName;
	}

	/**
	 * @return the mchtArtifType
	 */
	public String getMchtArtifType() {
		return mchtArtifType;
	}

	/**
	 * @param mchtArtifType
	 *            the mchtArtifType to set
	 */
	public void setMchtArtifType(String mchtArtifType) {
		this.mchtArtifType = mchtArtifType;
	}

	/**
	 * @return the mchtArtifId
	 */
	public String getMchtArtifId() {
		return mchtArtifId;
	}

	/**
	 * @param mchtArtifId
	 *            the mchtArtifId to set
	 */
	public void setMchtArtifId(String mchtArtifId) {
		this.mchtArtifId = mchtArtifId;
	}

	/**
	 * @return the mchtArtifPhone
	 */
	public String getMchtArtifPhone() {
		return mchtArtifPhone;
	}

	/**
	 * @param mchtArtifPhone
	 *            the mchtArtifPhone to set
	 */
	public void setMchtArtifPhone(String mchtArtifPhone) {
		this.mchtArtifPhone = mchtArtifPhone;
	}

	/**
	 * @return the mchtPersonName
	 */
	public String getMchtPersonName() {
		return mchtPersonName;
	}

	/**
	 * @param mchtPersonName
	 *            the mchtPersonName to set
	 */
	public void setMchtPersonName(String mchtPersonName) {
		this.mchtPersonName = mchtPersonName;
	}

	/**
	 * @return the mchtPhone
	 */
	public String getMchtPhone() {
		return mchtPhone;
	}

	/**
	 * @param mchtPhone
	 *            the mchtPhone to set
	 */
	public void setMchtPhone(String mchtPhone) {
		this.mchtPhone = mchtPhone;
	}

	/**
	 * @return the mchtTrcnNo
	 */
	public String getMchtTrcnNo() {
		return mchtTrcnNo;
	}

	/**
	 * @param mchtTrcnNo
	 *            the mchtTrcnNo to set
	 */
	public void setMchtTrcnNo(String mchtTrcnNo) {
		this.mchtTrcnNo = mchtTrcnNo;
	}

	/**
	 * @return the livePicNo
	 */
	public List<String> getLivePicNo() {
		return livePicNo;
	}

	/**
	 * @param livePicNo
	 *            the livePicNo to set
	 */
	public void setLivePicNo(List<String> livePicNo) {
		this.livePicNo = livePicNo;
	}

	/**
	 * @return the livePicPath
	 */
	public String getLivePicPath() {
		return livePicPath;
	}

	/**
	 * @param livePicPath
	 *            the livePicPath to set
	 */
	public void setLivePicPath(String livePicPath) {
		this.livePicPath = livePicPath;
	}

	/**
	 * @return the mchtAddr
	 */
	public String getMchtAddr() {
		return mchtAddr;
	}

	/**
	 * @param mchtAddr
	 *            the mchtAddr to set
	 */
	public void setMchtAddr(String mchtAddr) {
		this.mchtAddr = mchtAddr;
	}

	/**
	 * @return the logoPicNo
	 */
	public String getLogoPicNo() {
		return logoPicNo;
	}

	/**
	 * @param logoPicNo
	 *            the logoPicNo to set
	 */
	public void setLogoPicNo(String logoPicNo) {
		this.logoPicNo = logoPicNo;
	}

	/**
	 * @return the logoPicPath
	 */
	public String getLogoPicPath() {
		return logoPicPath;
	}

	/**
	 * @param logoPicPath
	 *            the logoPicPath to set
	 */
	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	/**
	 * @return the mchtLicnNo
	 */
	public String getMchtLicnNo() {
		return mchtLicnNo;
	}

	/**
	 * @param mchtLicnNo
	 *            the mchtLicnNo to set
	 */
	public void setMchtLicnNo(String mchtLicnNo) {
		this.mchtLicnNo = mchtLicnNo;
	}

	/**
	 * @return the mchtLicnPicPath
	 */
	public String getMchtLicnPicPath() {
		return mchtLicnPicPath;
	}

	/**
	 * @param mchtLicnPicPath
	 *            the mchtLicnPicPath to set
	 */
	public void setMchtLicnPicPath(String mchtLicnPicPath) {
		this.mchtLicnPicPath = mchtLicnPicPath;
	}

	/**
	 * @return the mchtLongitude
	 */
	public String getMchtLongitude() {
		return mchtLongitude;
	}

	/**
	 * @param mchtLongitude
	 *            the mchtLongitude to set
	 */
	public void setMchtLongitude(String mchtLongitude) {
		this.mchtLongitude = mchtLongitude;
	}

	/**
	 * @return the mchtLatitude
	 */
	public String getMchtLatitude() {
		return mchtLatitude;
	}

	/**
	 * @param mchtLatitude
	 *            the mchtLatitude to set
	 */
	public void setMchtLatitude(String mchtLatitude) {
		this.mchtLatitude = mchtLatitude;
	}

	/**
	 * @return the mchtAmrNo
	 */
	public String getMchtAmrNo() {
		return mchtAmrNo;
	}

	/**
	 * @param mchtAmrNo
	 *            the mchtAmrNo to set
	 */
	public void setMchtAmrNo(String mchtAmrNo) {
		this.mchtAmrNo = mchtAmrNo;
	}

	/**
	 * @return the mchtAmrName
	 */
	public String getMchtAmrName() {
		return mchtAmrName;
	}

	/**
	 * @param mchtAmrName
	 *            the mchtAmrName to set
	 */
	public void setMchtAmrName(String mchtAmrName) {
		this.mchtAmrName = mchtAmrName;
	}

	/**
	 * @return the mchtAmrPhone
	 */
	public String getMchtAmrPhone() {
		return mchtAmrPhone;
	}

	/**
	 * @param mchtAmrPhone
	 *            the mchtAmrPhone to set
	 */
	public void setMchtAmrPhone(String mchtAmrPhone) {
		this.mchtAmrPhone = mchtAmrPhone;
	}

	/**
	 * @return the mchtArtifPicNo
	 */
	public List<String> getMchtArtifPicNo() {
		return mchtArtifPicNo;
	}

	/**
	 * @param mchtArtifPicNo
	 *            the mchtArtifPicNo to set
	 */
	public void setMchtArtifPicNo(List<String> mchtArtifPicNo) {
		this.mchtArtifPicNo = mchtArtifPicNo;
	}

	/**
	 * @return the mchtArtifPicPath
	 */
	public String getMchtArtifPicPath() {
		return mchtArtifPicPath;
	}

	/**
	 * @param mchtArtifPicPath
	 *            the mchtArtifPicPath to set
	 */
	public void setMchtArtifPicPath(String mchtArtifPicPath) {
		this.mchtArtifPicPath = mchtArtifPicPath;
	}

}
