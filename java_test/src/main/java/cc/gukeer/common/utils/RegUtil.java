package cc.gukeer.common.utils;
  
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  
  
public class RegUtil {
    /** 
     * 正则表达式匹配两个指定字符串中间的内容 
     * @param soap 
     * @return 
     */  
    public static List<String> getSubUtil(String soap,String rgex){  
        List<String> list = new ArrayList<String>();  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while (m.find()) {  
            int i = 1;  
            list.add(m.group(i));  
            i++;  
        }
        return list;  
    }  
      
    /** 
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样 
     * @param soap 
     * @param rgex 
     * @return 
     */  
    public static String getSubUtilSimple(String soap,String rgex){  
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(soap);  
        while(m.find()){
            return m.group(1);
        }  
        return "";  
    }

    public static String replaceSpace(String company){
        if (!company.contains("(") && !company.contains(")"))
            return company;

        String bracket = company.substring(company.indexOf("("),company.indexOf(")")+1);
        company = company.replace(bracket, "");
        if (company.contains("(")||company.contains(")"))
            company = replaceSpace(company);
        return company;
    }
      
    /** 
     * 测试 
     * @param args 
     */  
    public static void main(String[] args) {  
        String str = "我的我的(das)哈哈(dass)哈";
        System.out.println(replaceSpace(str));
        String rgex = "<(.*?)>";
        System.out.println(getSubUtil(str,rgex));  
        System.out.println(getSubUtilSimple(str, rgex));  
    }    
}  