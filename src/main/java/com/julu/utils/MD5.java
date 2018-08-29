package com.julu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author Hai
 *
 */
public final class MD5 {
	
	/**
	 * 将数据加密MD5值(默认编码格式为UTF-8)
	 * @param content 需要MD5的值
	 * @return
	 */
	public final static String encrypt(String content) {
		return encrypt(content, "UTF-8");
	}

	/**
	 * 将数据加密MD5值
	 * @param content 需要MD5的值
	 * @param encoding 内容的编码格式 
	 * @return 返回MD5值
	 */
	public final static String encrypt(String content, String encoding) {
		String md5 = null;
		if (content == null) {
			content = "";
		}
		if (encoding == null || "".equals(encoding)) {
			encoding = "UTF-8";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = content.getBytes(encoding);
			md.update(bytes);
			byte md5Bytes[] = md.digest();
			md5 = HexUtil.bytesToHexString(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5;
	}

	public static String getMD5_32_xx(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
}

	public static void main(String[] args){
		String res=getMD5_32_xx("123456");
		System.out.println(res);
	}

}
