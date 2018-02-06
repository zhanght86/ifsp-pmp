package com.ruimin.ifs.util;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.slf4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.ruimin.ifs.core.annotation.ActionResource;
import com.ruimin.ifs.core.annotation.SnowDoc;
import com.ruimin.ifs.core.exception.SnowException;
import com.ruimin.ifs.core.exception.SnowExceptionUtil;
import com.ruimin.ifs.core.iface.action.SnowAction;
import com.ruimin.ifs.core.log.SnowLog;
import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.AuditConstants;
import com.ruimin.ifs.pmp.mchtMng.common.constants.MchtQrcCodeConstants;
import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
/**
 * 二维码操作工具类
 * @author shrm_tyzf010
 *
 */
@ActionResource
@SnowDoc(desc = "")
public class QrCodeUtil extends SnowAction{
	
	static Logger log = SnowLog.getLogger(QrCodeUtil.class);
	//支付前置请求路径
	static String tokenUrl = SysParamUtil.getParam(MchtChnlRequestConstants.PAY_PER_URL);
	static String QRC_BG_IMG_PATH = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_BACKGROUND_PATH);
	/**
	 * 生成商户一码付二维码背景图文件
	 * @param qrcCode 二维码内容
	 * @param saveFileName 临时文件存放路径+文件名
	 * @param picType  文件类型，即文件后缀
	 * @param mchtId 商户编号
	 * @param mchtSimpleName 商户名称
	 * @param bgImageName  背景图文件名称 
	 * */
	public void genMchtOneCodeImage(String qrCode,String saveFileName,String picType,String mchtId,String mchtSimpleName,String bgImageName)throws SnowException{
	    BufferedImage bimg = null;
	    BufferedImage image =null;
	    FileInputStream fis =null;
	    try {	
			log.info("======开始生成商户二维码图片");
			log.info("商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
			
			log.info("===set  before java.awt.headless=" + System.getProperty("java.awt.headless"));
			System.setProperty("java.awt.headless", "true");
			log.info("===set  after java.awt.headless=" + System.getProperty("java.awt.headless"));
			
			log.info("===开始获取二维码背景图");
			log.info("文件路径为："+QRC_BG_IMG_PATH+bgImageName);
			 fis =new FileInputStream(QRC_BG_IMG_PATH+bgImageName);
			 bimg = ImageIO.read(fis);
			log.info("===完成获取二维码背景图");
			
			int bw = bimg.getWidth();
			int bh = bimg.getHeight();
			log.info("bimg宽：" + bw + ",bimg长：" + bh);
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix bitMatrix = null;
			//设置生生的二维码图片大小为300x300
			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE, 370, 370,
			hints);
			log.info("生成BitMatrix成功");
			int iw = bitMatrix.getWidth();
			int ih = bitMatrix.getHeight();	
			 image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);;
			log.info("生成image成功");
			for (int x = 0; x < iw; x++) {
				for (int y = 0; y < ih; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) == true ? new Color(0, 143, 212).getRGB() : Color.white.getRGB());
				}
			}
			log.info("image.setRGB完成");
			log.info("===开始生成存放临时文件");
			log.info("文件全路径："+saveFileName);
			File file = new File(saveFileName);
			if (!file.exists()) {
				//new File(tmpPath).mkdirs();
				file.createNewFile();
				file.setReadable(true, false);
				file.setWritable(true, false);
			}
			log.info("===完成生成存放临时文件");
			if(bimg != null){
				log.info("bimg========="+bimg);
				Graphics2D g = (Graphics2D) bimg.getGraphics();
				Graphics2D g2 = (Graphics2D)bimg.getGraphics();
				log.info("g2"+g2);
				g.setColor(Color.WHITE);
				log.info("g.setColor");
				g2.setColor(Color.WHITE);
				log.info("g2.setColor");
				log.info("二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
				Font font = new Font(Font.DIALOG, Font.BOLD, 43);
				Font font2 = new Font(Font.DIALOG, Font.BOLD, 16);//目前第二个参数不可为Font.PLAIN，否则某些中文字符显示异常，第三个参数不可超过31，否则导致显示不同行
				g.setFont(font); // 设置字体
				g2.setPaint(new Color(188,189,192));
				/**证通版本屏蔽写入商户简称 20170616*/
//				if (StringUtil.isNotBlank(mchtSimpleName)) {
//					FontMetrics fm2 = new JLabel().getFontMetrics(font2);
//					int a = fm2.stringWidth(mchtSimpleName);
//					//商户简称的字体x轴居中,y轴往下50
//					g2.drawString(mchtSimpleName,bw / 2 - a / 2, 50);
//				} 
				Font name = new Font("微软雅黑", 0, 48);
				g2.setFont(name); 
				FontMetrics fmName = new JLabel().getFontMetrics(name);
	            int a = fmName.stringWidth(mchtSimpleName);				
				//二维码图片的位置x轴居中,y轴往下60
//				g.drawImage(image, (bw - iw) / 2 , 368, null);
//				g2.drawString(mchtSimpleName,  bw / 2 - a / 2, 908); 
                g.drawImage(image, (bw - iw) / 2 , 400, null);
                g2.drawString(mchtSimpleName,  bw / 2 - a / 2, 980);
				g2.dispose();
				g.dispose();
				log.info("二维码图片高:"+bimg.getHeight()+",二维码图片宽:"+bimg.getWidth());
				ImageIO.write(bimg, picType, file);
			}
			log.info("======完成生成商户二维码图片");
		} catch (Exception e) {
			log.info("错误原因:"+e);
			SnowExceptionUtil.throwWarnException("错误原因:"+e);
			SnowExceptionUtil.throwErrorException(e.getMessage());
	 /** modify by fengwei 20180116 代码完善,正确的关闭资源  没有jira */
		}finally {
            if(bimg!=null){
                bimg=null;
            }
            if(image!=null){
                image=null;
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
	    /** modify end */
	}
	
	
//	/**
//	 * 二维码图片生成(生成的二维码带白色边框版本,如果需要去除白色边框可以使用下面注释掉的方法)
//	 * @param qrCode 二维码token
//	 * @param tmpPath 二维码保存路径
//	 * @param picType 二维码图片文件类型（小写）
//	 * @param mchtId 商户号
//	 * @param mchtSimpleName 商户简称
//	 * @param qrcBackGroundImageName 
//	 * @throws Exception
//	 */
//	public  void build(String qrCode, String tmpPath, String picType, String mchtId, String mchtSimpleName, String qrcBackGroundImageName)
//			throws Exception {
//		try {			
//			log.info("Set before===========java.awt.headless" + System.getProperty("java.awt.headless"));
//			System.setProperty("java.awt.headless", "true");
//			log.info("Set End===========java.awt.headless" + System.getProperty("java.awt.headless"));
//			
//			log.info("商户二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//			log.info("tmpPath====:"+tmpPath);
//			String path = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_BACKGROUND_PATH);
//			path = path+qrcBackGroundImageName;
//			log.info("获取二维码背景图片路径成功，[文件路径]="+path);
//			FileInputStream fis =new FileInputStream(path);
//			log.info("生成fileInputStream流成功");
//			BufferedImage bimg = ImageIO.read(fis);
//			log.info("生成BufferedImage成功");
//			int bw = bimg.getWidth();
//			int bh = bimg.getHeight();
//			log.info("bimg宽：" + bw + ",bimg长：" + bh);
//			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//			hints.put(EncodeHintType.MARGIN, 1);
//			BitMatrix bitMatrix = null;
//			//设置生生的二维码图片大小为背景图片的10分之8的大小
////			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  bw * 8 / 10, bw * 8 / 10,
////					hints);
//			//设置生生的二维码图片大小为300x300
//			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  300, 300,
//			hints);
//			
//			
//			log.info("生成BitMatrix成功");
//			int iw = bitMatrix.getWidth();
//			int ih = bitMatrix.getHeight();	
//			BufferedImage image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);;
//			log.info("生成image成功");
//			for (int x = 0; x < iw; x++) {
//				for (int y = 0; y < ih; y++) {
//					image.setRGB(x, y, bitMatrix.get(x, y) == true ? Color.black.getRGB() : Color.white.getRGB());
//				}
//			}
//			log.info("image.setRGB完成");
//			File file = new File(tmpPath + mchtId + "." + PNG);
//				if (!file.exists()) {
//					new File(tmpPath).mkdirs();
//					file.createNewFile();
//					file.setReadable(true, false);
//					file.setWritable(true, false);
//				}
//			log.info("file:"+file);
//			if(bimg != null){
//				log.info("bimg========="+bimg);
//				Graphics2D g = (Graphics2D) bimg.getGraphics();
//				Graphics2D g2 = (Graphics2D)bimg.getGraphics();
//				log.info("g2"+g2);
//				g.setColor(Color.WHITE);
//				log.info("g.setColor");
//				g2.setColor(Color.WHITE);
//				log.info("g2.setColor");
//				log.info("二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//				Font font = new Font(Font.DIALOG, Font.BOLD, 43);
//				Font font2 = new Font(Font.DIALOG, Font.BOLD, 25);//目前第二个参数不可为Font.PLAIN，否则某些中文字符显示异常，第三个参数不可超过31，否则导致显示不同行
//				g.setFont(font); // 设置字体
//				g2.setFont(font2); // 设置字体
//				/**证通版本屏蔽写入商户号 20170616*/
////				if (StringUtil.isNotBlank(mchtSimpleName)) {
////					FontMetrics fm2 = new JLabel().getFontMetrics(font2);
////					int a = fm2.stringWidth(mchtSimpleName);
////					//商户简称的字体x轴居中,y轴往下50
////					g2.drawString(mchtSimpleName,bw / 2 - a / 2, 50);
////				} 
//				//二维码图片的位置x轴居中,y轴往下60
//				g.drawImage(image, (bw - iw) / 2 , 80, null);
//				g2.dispose();
//				g.dispose();
//				log.info("二维码图片高:"+bimg.getHeight()+",二维码图片宽:"+bimg.getWidth());
//				ImageIO.write(bimg, picType, file);
//				ImageIO.write
//			}
//		} catch (Exception e) {
//			log.info("错误原因:"+e);
//			SnowExceptionUtil.throwWarnException("错误原因:"+e);
//			SnowExceptionUtil.throwErrorException(e.getMessage());
//		}
//		
//	}	

	/**
	 * 二维码图片生成(生成的二维码图片不带白色边框版本)
	 * @param qrCode 二维码token
	 * @param tmpPath 二维码保存路径
	 * @param picType 二维码图片生成类型的
	 * @param mchtId 商户号
	 * @param mchtSimpleName 商户简称
	 * @param qrcBackGroundImageName 
	 * @throws Exception
	 */
//	public  void build(String qrCode, String tmpPath, String picType, String mchtId, String mchtSimpleName, String qrcBackGroundImageName)
//			throws Exception {
//		try {			
//			log.info("Set before===========java.awt.headless" + System.getProperty("java.awt.headless"));
//			System.setProperty("java.awt.headless", "true");
//			log.info("Set End===========java.awt.headless" + System.getProperty("java.awt.headless"));
//			
//			log.info("商户二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//			log.info("tmpPath====:"+tmpPath);
//			String path = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_BACKGROUND_PATH);
//			if("qrcBackground1.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground1.png";
//			}
//			if("qrcBackground2.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground2.png";
//			}
//			if("qrcBackground3.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground3.png";
//			}
//			if("qrcBackground4.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground4.png";
//			}
//			if("qrcBackground5.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground5.png";
//			}
//			if("qrcBackground6.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground6.png";
//			}
//			if("qrcBackground7.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground7.png";
//			}
//			log.info("获取二维码背景图片路径成功");
//			FileInputStream fis =new FileInputStream(path);
//			log.info("生成fileInputStream流成功");
//			BufferedImage bimg = ImageIO.read(fis);
//			log.info("生成BufferedImage成功");
//			int bw = bimg.getWidth();
//			int bh = bimg.getHeight();
//			log.info("bimg宽：" + bw + ",bimg长：" + bh);
//			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//			hints.put(EncodeHintType.MARGIN, 1);
//			BitMatrix bitMatrix = null;
//			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  bw * 8 / 10, bw * 8 / 10,
//					hints);
//			log.info("生成BitMatrix成功");
//			int iw = bitMatrix.getWidth();
//			int ih = bitMatrix.getHeight();
//			int[] rec = bitMatrix.getEnclosingRectangle();
//			int resWidth = rec[2] + 1;
//			int resHeight = rec[3] + 1;
//			BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);//根据白色边框重新生成BitMatrix
//			log.info("重新生成BitMatrix成功");
//			resMatrix.clear();
//			for (int i = 0; i < resWidth; i++) {//循环，将二维码图案绘制到新的resMatrix中
//				for (int j = 0; j < resHeight; j++) {
//					if (bitMatrix.get(i + rec[0], j + rec[1])) {
//						resMatrix.set(i, j);
//					}
//				}
//			}
//			log.info("循环将二维码图片绘制到新的resMatrix中成功");
//			int width = resMatrix.getWidth();
//			int height = resMatrix.getHeight();
//			BufferedImage image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);;
//			log.info("生成image成功");
//			for (int x = 0; x < width; x++) {
//				for (int y = 0; y < height; y++) {
//					image.setRGB(x, y, resMatrix.get(x, y) == true ? Color.black.getRGB() : Color.white.getRGB());
//				}
//			}
//			log.info("image.setRGB完成");
//			File file = new File(tmpPath + mchtId + "." + PNG);
//				if (!file.exists()) {
//					new File(tmpPath).mkdirs();
//					file.createNewFile();
//					file.setReadable(true, false);
//					file.setWritable(true, false);
//				}
//			log.info("file:"+file);
//			if(bimg != null){
//				log.info("bimg========="+bimg);
//				Graphics2D g = (Graphics2D) bimg.getGraphics();
//				Graphics2D g2 = (Graphics2D)bimg.getGraphics();
//				log.info("g2"+g2);
//				g.setColor(Color.WHITE);
//				log.info("g.setColor");
//				g2.setColor(Color.WHITE);
//				log.info("g2.setColor");
//				log.info("二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//				Font font = new Font(Font.DIALOG, Font.BOLD, 43);
//				Font font2 = new Font(Font.DIALOG, Font.BOLD, 25);//目前第二个参数不可为Font.PLAIN，否则某些中文字符显示异常，第三个参数不可超过31，否则导致显示不同行
//				g.setFont(font); // 设置字体
//				g2.setFont(font2); // 设置字体
//				if (StringUtil.isNotBlank(mchtSimpleName)) {
//					FontMetrics fm2 = new JLabel().getFontMetrics(font2);
//					int a = fm2.stringWidth(mchtSimpleName);
//					//System.err.println(a);
//					g2.drawString(mchtSimpleName,bw / 2 - a / 2, 35);
//				} 
//				g.drawImage(image, (bw - iw) / 2 +10 , 60, null);
//				g2.dispose();
//				g.dispose();
//				log.info("二维码图片高:"+bimg.getHeight()+",二维码图片宽:"+bimg.getWidth());
//				ImageIO.write(bimg, picType, file);
//			}
//		} catch (Exception e) {
//			log.info("错误原因:"+e);
//			SnowExceptionUtil.throwWarnException("错误原因:"+e);
//			SnowExceptionUtil.throwErrorException(e.getMessage());
//		}
//		
//	}
	public static boolean deleteDir(File dir) throws Exception{
		if(dir.isDirectory()){
			String[] children = dir.list();
			//递归删除目录中的子目录
			for(int i=0;i<children.length;i++){
				boolean success = deleteDir(new File(dir,children[i]));
				if(!success){
					return false;
				}
			}
		}
		//目录此时为空，可以删除
		return dir.delete();
	}
	public static void main(String[] args) {
		try {
		    
			String mchtId= "123";
			String mchtSimpleName ="王麻子铁匠铺";
			//二维码图片本地存放临时路径
			 String QRC_TMP_PATH = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_TMP_SAVE_PATH);
			//二维码图片类型
			String QRC_IMG_TYPE = SysParamUtil.getParam(MchtQrcCodeConstants.QRC_IMG_FILE_TYPE);
			String qrCode = "123";
			String saveFileName =QRC_TMP_PATH+mchtId+mchtSimpleName+"."+QRC_IMG_TYPE;
			System.out.println(saveFileName);
			String picType = QRC_IMG_TYPE;
		
			String bgImageName = SysParamUtil.getParam(AuditConstants.QRC_ONECODE_BG_IMG_NAME);;
			//new QrCodeUtil().build("11", SysParamUtil.getParam(MchtChnlRequestConstants.QRC_TMP_SAVE_PATH)+File.separator, "png", "123", "测试", "qrcBackground1.png");
			new QrCodeUtil().genMchtOneCodeImage(qrCode, saveFileName, picType, mchtId, mchtSimpleName, bgImageName);
			System.out.println("生产二维码图片成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}
}
