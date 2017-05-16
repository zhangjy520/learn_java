package cc.gukeer.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 接受默认值，处理异常
 *
 * Created by conn on 2016/8/8.
 */
public class NumberConvertUtil {

    public static int convertS2I(String val) {
        return convertS2I(val, 0);
    }

    public static int convertS2I(String val, int def) {
        int rst = def;
        try {
            rst = Integer.parseInt(val);
        } catch (Exception e) {

        }
        return rst;
    }

    public static int getVal(Integer val) {
        if (null == val) return 0;
        return val;
    }

    public static float getVal(Float val) {
        if (null == val) return 0f;
        return val;
    }

    public static double getVal(Double val) {
        if (null == val) return 0;
        return val;
    }

    public static long getVal(Long val) {
        if (null == val) return 0l;
        return val;
    }

    public static String getNumFromStr(String str){
        if (str == "" || str ==null) {
            return "0";
        }else {
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            String res = m.replaceAll("").trim();
            if (res.equals("")) {
                return "0";
            }else {
                return  m.replaceAll("").trim();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getNumFromStr("aaaa12"));
    }

}
