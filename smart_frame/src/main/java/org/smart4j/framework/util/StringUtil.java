package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;  
  
import org.apache.commons.lang3.math.NumberUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * Created by jack on 2015/12/26. 
 * �ַ��������� 
 */
public final class StringUtil {

    /**
     * �ַ����ָ��
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    /**
     * �ж��ַ����Ƿ�Ϊ��
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }
    /**
     * �ж��ַ����Ƿ�Ϊ��
     */

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }
    /**
     * ���ַ���Ϊ�գ���ȡĬ��ֵ
     */
    public static String defaultEmpty(String str,String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

    /**
     * �滻�̶���ʽ��
     */
    public static String replaceAll(String str, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        if (matcher.find()) {
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * �ж��Ƿ�Ϊ����
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }

    /**
     * �ж��Ƿ�Ϊʮ��������
     */
    public static boolean isDigits(String str) {
        return NumberUtils.isDigits(str);
    }

    /**
     * ���շ����滻Ϊ�»��߷��
     */
    public static String camelhumpT0Unerline(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; matcher.find() ; i++) {
            sb.replace(matcher.start() + i, matcher.end() + i, "_" + matcher.group().toLowerCase());
        }
        if ('_' == sb.charAt(0)) {
            sb.deleteCharAt(0);
        }
        return sb.toString();

    }

    /**
     * �»����滻Ϊ�շ�
     * @param str
     * @return
     */
    public static String unerlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        return builder.toString();
    }

    /**
     * �ָ�̶����ַ���
     * @return
     */
    public static String[] splitString(String str,String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
    /**
     * ���ַ�������ĸ��д
     */
    public static String firstToUpper(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    /**
     * ������ĸСд
     */
    public static String firstToLower(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
    /**
     * תΪ��˹��������ʽ���磺FooBar��
     */

    public static String toPascalStyle(String str ,String seperator) {
        return StringUtil.firstToUpper(toCamelhumpStyle(str, seperator));
    }
    /**
     * תΪ�շ����ʽ���磺fooBar��
     */
    public static String toCamelhumpStyle(String str,String seperator) {
        return StringUtil.unerlineToCamelhump(toUnderlineStyle(str, seperator));
    }


    /**
     * תΪ�»���������ʽ���磺foo_bar��
     */
    public static String toUnderlineStyle(String str,String seperator) {
        str = str.trim().toLowerCase();
        if (str.contains(seperator)) {
            str = str.replace(seperator , "_");
        }
        return str;
    }

    /**
     * תΪ��ʵ������ʽ
     */

    public static String toDisplayStyle(String str, String seperator) {
        String displayName = "";
        str = str.trim().toLowerCase();
        if (str.contains(seperator)) {
            String[] words = StringUtil.splitString(str, seperator);
            for (String word : words) {
                displayName += StringUtil.firstToUpper(word)+"";
            }
        }else {
            displayName = StringUtil.firstToUpper(str);
        }
        return displayName;
    }
} 