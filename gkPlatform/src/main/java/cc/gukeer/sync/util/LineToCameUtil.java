package cc.gukeer.sync.util;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lx on 2017/2/13.
 */
public class LineToCameUtil {
    public static final char UNDERLINE='_';
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        test a = new test();
        String b = "{\"password\":\"OE43szSeXws9orNa6ooYsQ\0006=\",\"versions\":0,\"schoolId\":\"bb\",\"syncDate\":1487002423000,\"name\":\"范昕桐\",\"refId\":\"2154\",\"userType\":2,\"id\":2,\"event\":\"MODIFY\",\"syncDelFlag\":\"0\",\"syncId\":\"1888\",\"username\":\"fanxintong\" }";
        Gson gson = new Gson();
       test aaaa =gson.fromJson(b,test.class);
        aaaa.getId();

    }
}
