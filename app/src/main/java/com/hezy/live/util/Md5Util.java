package com.hezy.live.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * 
 * @version 1.0
 * @author
 * 
 */
public class Md5Util {
	public static String byteToHEX(byte ib) {
		// NameValuePair a[]=new NameValuePair[1];

		char[] DigitNormal = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

		'a', 'b', 'c', 'd', 'e', 'f' };

		/*
		 * char[] DigitCap = { '0','1','2','3','4','5','6','7','8','9',
		 * 'A','B','C','D','E','F' };
		 */

		char[] ob = new char[2];

		ob[0] = DigitNormal[(ib >>> 4) & 0X0F];

		// ob[0] = DigitCap[(ib >>> 4) & 0X0F];

		ob[1] = DigitNormal[ib & 0X0F];

		// ob[1] = DigitCap[ib & 0X0F];

		return new String(ob);

	}

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 * @return
	 */
	public static String generateMD5String(String plainText) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;
			StringBuffer sb = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// log.error...
			System.out.println("generate MD5 String error! " + e.getMessage());
			// throw e;
		}
		return null;
	}

	public static byte[] MD5(String source) {
		// String s = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(source.getBytes("utf-8"));
			byte tmp[] = md.digest(); 
			return tmp;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(source.getBytes());
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String getKeyedDigest(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));

			String result = "";
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}

			return result;

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
