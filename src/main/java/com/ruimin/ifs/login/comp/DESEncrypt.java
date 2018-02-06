/*
 * Copyright © 2012 YTEC.
 * YUAP项目组
 * All right reserved.
 */
package com.ruimin.ifs.login.comp;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能/模块 ：作业调度
 * 
 * @author chenfz
 * @version 1.0 2012-07-20
 * 
 * 类描述 : DES加解密类
 */
public class DESEncrypt
{
	private final static  Log log = LogFactory.getLog(DESEncrypt.class);
	/**
	 * 加密
	 * @param s 字节数组
	 * @return byte[] 加密后的字节数组
	 */
  public static byte[] encrypt(byte[] s)
  {
    return encrypt(s, 1);
  }

  /**
	 * 解密
	 * @param s 加密后的字节数组
	 * @return byte[] 字节数组
	 */
  public static byte[] decrypt(byte[] s)
  {
    return encrypt(s, 2);
  }

  /**
	 * 加密
	 * @param s 字符串
	 * @return byte[] 加密后的字符串
	 */
  public static String encrypt(String s)
  {
    StringBuffer sb = new StringBuffer(1024);
    byte[] b = encrypt(s.getBytes(), 1);
    if ((b == null) || (b.length < 1)) return null;
    Random r = new Random(s.length());

    for (int i = 0; i < b.length; ++i) {
      char c = (char)(r.nextInt(10) + 71);
      sb.append(c);
      if (b[i] < 0) {
        c = (char)(r.nextInt(10) + 81);
        b[i] = (byte)(-b[i]);
        sb.append(c);
      }
      sb.append(Integer.toString(b[i], 16).toUpperCase());
    }
    sb.deleteCharAt(0);
    return sb.toString();
  }

  /**
	 * 解密
	 * @param s 加密后的字符串
	 * @return byte[] 字符串
	 */
  public static String decrypt(String s)
  {
    if (s.length() < 1) { return null;
    }

    String[] sByte = s.split("[G-Pg-p]");
    byte[] b = new byte[sByte.length];

    for (int i = 0; i < b.length; ++i) {
      char c = sByte[i].charAt(0);
      if (((c >= 'Q') && (c <= 'Z')) || ((c >= 'q') && (c <= 'z')))
        b[i] = (byte)(-Byte.parseByte(sByte[i].substring(1), 16));
      else
        b[i] = Byte.parseByte(sByte[i], 16);
    }

    byte[] ch = encrypt(b, 2);
    if ((ch == null) || (ch.length < 1)) return null;

    return new String(ch);
  }

  /**
   * 加密
   * @param s 字节数组
   * @param mode 加密模式
   * @return 加密后的字节数组
   */
  private static byte[] encrypt(byte[] s, int mode)
  {
    byte[] ciphertext;
    byte[] salt = { -57, 115, 33, -116, 126, -56, -18, -103 };
    try
    {
      SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
      DESKeySpec desKeySpec = new DESKeySpec(salt);
      SecretKey desKey = keyFac.generateSecret(desKeySpec);

      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

      cipher.init(mode, desKey);

      ciphertext = cipher.doFinal(s);
    } catch (Exception e) {
    	log.error(e);
    	ciphertext = null;
    }
    return ciphertext;
  }
}