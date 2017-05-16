package cc.gukeer.common.utils;

import java.math.BigDecimal;

/**
 * 接受默认值，处理异常
 * <p>
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

    public static Long convertS2L(String val, long def) {
        long rst = def;
        try {
            rst = Long.parseLong(val);
        } catch (Exception e) {

        }
        return rst;

    }

    public static Long convertS2L(String val) {
        return convertS2L(val, 0);
    }

    public static Double convertS2D(String val, Double def) {
        Double rst = def;
        try {
            rst = Double.valueOf(val);
        } catch (Exception e) {

        }
        return rst;
    }

    public static Double convertS2D(String val) {
        return convertS2D(val, 0.0);
    }

    public static Double sub(String val1, String val2) {
        // 进行减法运算
        BigDecimal b1 = new BigDecimal(val1);
        BigDecimal b2 = new BigDecimal(val2);
        return b1.subtract(b2).doubleValue();
    }

    public static Double add(String d1, String d2) {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    public static Double mul(String d1, String d2) {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    public static Double div(String d1, String d2, int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double round(String d, int len) {     // 进行四舍五入操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        String aa = "8.65";
        String bb = "8.85";
        System.out.println(sub(aa, bb));
    }
}
