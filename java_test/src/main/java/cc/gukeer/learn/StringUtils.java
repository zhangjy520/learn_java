package cc.gukeer.learn;

import java.util.*;

/**
 * 字符串操作类
 * 
 * @author 作者: 刘颖 
 * @version 创建时间：2018-1-10 下午5:15:34
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	
	private static final int NUM_255 = 255;
	
	/**
	 * 下划线
	 */
	public static final String UNDERLINE = "_";
	
	/**
	 * 分隔符，英文逗号
	 */
	public static final String STRING_SEPARATOR = ",";
	
	private StringUtils() {
		
	}
	
	/**
	 * 删除字符串结尾处的多余标记
	 * @param sb         要删除结尾的字符串
	 * @param flag       要删除的标记
	 */
	public static StringBuilder deleteLastFlag(StringBuilder sb, String flag) {
		if (sb != null && sb.toString().endsWith(flag)) {
			sb.deleteCharAt(sb.length() - flag.length());
		}
		return sb;
	}
	
	private static Map<String, String> HTML_CHAR = new HashMap<String, String>();  
	static {  
		HTML_CHAR.put("&", "&#38;");          
		HTML_CHAR.put("\"", "&#34;");  
		HTML_CHAR.put("<", "&#60;");  
		HTML_CHAR.put(">", "&#62;");   
		HTML_CHAR.put("'", "&#39;");          
	}
	
	/**
	 * 计算字符的字节长度
	 * @param s 字符
	 * @return
	 */
	public static int getStringByteLen(String s)  
    {  
        int length = 0;  
        if (s != null) {
	        for(int i = 0; i < s.length(); i++)
	        {  
	            int ascii = Character.codePointAt(s, i);  
	            if(ascii >= 0 && ascii <= NUM_255)  {
	                length++;  
	            } else {
	                length += 2;
	            }
	        }
        }
        return length;
    } 
	
	/**
	 * 截取指定长度的字符串
	 * @param str 源串
	 * @param subBytes 返回的字符串长度
	 * @return
	 */
	public static String subString(String str, int subBytes) {
		if (str != null) {
			int newLength = subBytes - 3;
		//   用来存储字符串的总字节数
			int bytes = 0; 
			for (int i = 0; i < str.length(); i++) {
				if (bytes == newLength) {
					return str.substring(0, i) + "...";
				}
				char c = str.charAt(i);
				if (c <= NUM_255) {
					//英文字符的字节数看作1
					bytes += 1;
				} else {
					//中文字符的字节数看作2
					bytes += 2;
					if (bytes - newLength == 1) {
						return str.substring(0, i) + "...";
					}
				}
			}
		}
		return str;
	}

	

	/**
	 * 对HTML特殊字符编码
	 * @param str 特殊字符
	 * @return
	 */
	public static StringBuilder toHTMLChar(String str) {  
		if (str == null) {  
			return new StringBuilder();  
		}         
		StringBuilder sb = new StringBuilder(str);  

		char tempChar;  
		String tempStr;  
		for (int i = 0; i < sb.length(); i++) {  
			tempChar = sb.charAt(i);  
			if (HTML_CHAR.containsKey(Character.toString(tempChar))) {  
				tempStr = (String) HTML_CHAR.get(Character  
					.toString(tempChar));  
				sb.replace(i, i + 1, tempStr);  
				i += tempStr.length() - 1;  
			}  
		}  
		return sb;  
	}
	
	/**
	 * 空字符替换为 0长度的字符
	 * @param str 字符
	 * @return
	 */
	public static String toEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		} else {
			return str;
		}
	}
	/**
	 * 空字符替换为 0长度的字符
	 * 注意：这个方法把 "null"字符串也当做空对象处理
	 * @param str 字符
	 * @return
	 */
	public static String toEmptyOrNull(String str) {
		if (str == null || str.isEmpty() || "null".equals(str.toLowerCase())) {
			return "";
		} else {
			return str;
		}
	}
	
	/**
	 * 空字符判断
	 * 注意：这个方法把 "null"字符串也当做空对象处理
	 * @param str 字符
	 * @return
	 */
	public static boolean isEmptyOrNull(String str) {
			return str == null || str.isEmpty() || "null".equals(str.toLowerCase());
	}
	
	/**
	 * 空字符判断
	 * @param str 字符
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	/**
	 *  null替换为 0长度的字符
	 * @param <T> t
	 * @param obj 对象
	 * @return T
	 */
	public static <T> String toEmptyString(T obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public static List<String> stringConvertList(String paramString, String separator) {
		List<String> list = null;
		if(StringUtils.isNotBlank(paramString)) {
			list = new ArrayList<String>();
			if(paramString.contains(separator)) {
				list.addAll(Arrays.asList(paramString.split(separator)));
			} else {
				list.add(paramString);
			}
		}
		return list;
	}

}
