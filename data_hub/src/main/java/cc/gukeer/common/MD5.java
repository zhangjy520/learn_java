package cc.gukeer.common;

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
		// 鑾峰彇鏈湴鏃堕棿
		Calendar c = Calendar.getInstance();//鍙互瀵规瘡涓椂闂村煙鍗曠嫭淇敼
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		// 鍏佽涓�畾鐨勬椂闂磋宸�
		String timestr =  year + "/" + month + "/" + date + " " +(hour + inc);
		return timestr;
	}
	
	public static boolean auth(String key) {
		
		if( 
				"betaaitest".equals(key) || // TODO 娴嬭瘯鐢ㄦ柟渚胯緭鍏ュ湴鍧�洿鎺ヨ闂紝姝ｅ紡骞冲彴搴旀敞鎺�
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
		
		// 鐢ㄦ潵鍋氱櫥闄嗛獙璇佺殑涓�
		System.out.println("http://114.215.29.39:10090/login/auth/619311433@qq.com/" + MD5.getMD5Str("betaai@#$%" + getTimeStr(0)));
	}
	
}
