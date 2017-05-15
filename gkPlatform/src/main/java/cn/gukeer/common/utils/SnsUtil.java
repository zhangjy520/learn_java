package cn.gukeer.common.utils;

import cn.gukeer.common.utils.syncdata.MD5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SnsUtil {
//    public final static String SNS_URL = "http://115.28.175.51";
    public final static String SNS_URL = "http://118.190.66.188";
    //public final static String SNS_URL = "http://192.168.199.213";
    public final static String MOOC_URL ="http://121.42.48.126:8082";

    public final static String KEY = "sns!@#$%";
    public final static String MOOC_KEY = "CD93nWSNVSFk86b";
    public final static String COMMON_KEY = "CD93nWSNVSFk86b";

    /*
    *url:接口地址
     * type:返回参数对象格式
      * count:获取返回的条数
      * interfaceType:接口类型（0：返回数组的接口  1：返回json的接口）
    * */
    public static List<Object> getSns(String url, Class type, Integer count, Integer interfaceType) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            if (interfaceType == 0) {
                JSONArray json = JSONArray.fromObject(HttpRequestUtil.get(SNS_URL + url, null, null));
                resultList = JSONArray.toList(json, type);
            } else {
                JSONObject object = JSONObject.fromObject(HttpRequestUtil.get(SNS_URL + url, null, null));
                Object obj = JSONObject.toBean(object,type);
                resultList.add(obj);
                return resultList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() > count && resultList.size() != 0) {
            return resultList.subList(0, count);
        } else {
            return resultList;
        }
    }

    public static String getMoocLoginParams(String puid){
        Long ptime =System.currentTimeMillis();
        String time = DateUtils.millsToyyyyMMdd(ptime);
        String mac = MD5Util.go(time+ptime+puid+MOOC_KEY);
        //String withParam = "&puid="+puid+"&ptime="+ptime+"&pkey="+mac;
        String withParam = "puid="+puid+"&ptime="+ptime+"&pkey="+mac;
        return withParam;
    }

    public static String getThirdPartyParams(String platId,String appId,String userId,String timeStamp){
        String sign = MD5Util.go(platId + appId + userId + timeStamp + COMMON_KEY);
        String withParam = "platform_id="+platId+"&app_id="+appId+
                "&user_id="+userId+"&timeStamp="+timeStamp+"&sign="+sign;
        return withParam;
    }

    public static void main(String[] args) {
       // Object aa = getSns("/testsns/index.php/Api/Index/fans/zhang/535813d1ae517c114ca58c8852456bae", FansView.class, 0, 1);
       // System.out.println(DateUtils.millsToyyyyMMdd(1479800351225l));
        // System.out.println(getSns("index.php/Api/Index/weibo_all"));//微博
        // System.out.println(getSns("index.php/Api/Index/forum_all"));//全部帖子

    }

}
