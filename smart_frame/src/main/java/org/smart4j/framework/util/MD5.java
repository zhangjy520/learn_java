package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class MD5 {
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}



	public static String getTimeStr( int inc){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String timestr =  year + "/" + month + "/" + date + " " +(hour + inc);
		return timestr;
	}

	public static boolean auth(String key) {

		if(
				"betaaitest".equals(key) ||
						getMD5Str("betaai@#$%" + getTimeStr(0) ).equals(key) ||
						getMD5Str("betaai@#$%" + getTimeStr(1) ).equals(key) ||
						getMD5Str("betaai@#$%" + getTimeStr(-1) ).equals(key)
				){
			return true;
		}else{
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println(getTimeStr(0));
		System.out.println("http://localhost:8888/login/auth/12311@qq.com/" + MD5.getMD5Str("betaai@#$%" + getTimeStr(0)));
	}

}
