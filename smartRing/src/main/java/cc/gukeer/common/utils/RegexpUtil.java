package cc.gukeer.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by conn on 2016/9/9.
 */
public class RegexpUtil {


    public static boolean scoreFormat(String itemUnit, String score) {
        if ((itemUnit.contains("分")&& !itemUnit.contains("分钟"))||itemUnit.contains("1000")||itemUnit.contains("800")) {
            int indexS = score.indexOf("′");
            int indexSd = score.indexOf("'");
            if (indexS != indexSd ) {
                return true;
            }else {
                return false;
            }
        } else {
            /*String reg = "^\\d+(\\.\\d+)?$";
            return Pattern.compile(reg).matcher(score).find();*/
            return isFloat(score);
        }
    }
    //判断浮点数
    public static boolean isFloat(String param){
        if(StringUtils.isEmpty(param)){
            return false;
        }
        String reg = "^(-?\\d+)(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(param).find();
    }

    //判断 "^\\d+(\\.\\d+)?$"　　//非负浮点数（正浮点数   +   0）
    public static boolean isFloatPlus(String param) {
        if(StringUtils.isEmpty(param)){
            return false;
        }
        String reg = "^\\d+(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(param).find();
    }

    //判断 "^\\d+$"　　//非负整数（正整数   +   0）
    public static boolean isNumPlus(String param) {
        if(StringUtils.isEmpty(param)){
            return false;
        }
        String reg = "^\\d+$";
        return Pattern.compile(reg).matcher(param).find();
    }

    public static boolean isLevel(String level) {
        if(StringUtils.isEmpty(level)){
            return false;
        }
        String[] arry1 = {"优秀", "良好", "及格", "不及格"};
        int index1 = Arrays.asList(arry1).indexOf(level);
        if (index1 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断 2012年春，2012年秋。。。。
    public static boolean isPeDate(String param) {
        if(StringUtils.isEmpty(param)){
            return false;
        }
        String reg = "^\\d{4}年.{1}$";
        return Pattern.compile(reg).matcher(param).find();
    }

    public static boolean isDateForMat(String dateStr) {
        if(StringUtils.isEmpty(dateStr)){
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 设置日期转化成功标识
        boolean dateflag = true;
        // 这里要捕获一下异常信息
        try {
            Date date = format.parse(dateStr);
        } catch (ParseException e) {
            dateflag = false;
        } finally {
            //	成功：true ;失败:false
            return dateflag;
        }
    }

    public static boolean isMacFormat( String mac) {
        if(StringUtils.isEmpty(mac)){
            return false;
        }
        String patternMac="^[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}$";

        return Pattern.compile(patternMac).matcher(mac).find();
    }

    public static void main(String[] args) {
        String mac = "23:34:3e:5f:33:3d";
        System.out.println( isMacFormat(mac));

    }


}
