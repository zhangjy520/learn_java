package cc.gukeer.open.service.impl;


import cc.gukeer.common.utils.HttpRequestUtil;
import cc.gukeer.common.utils.PropertiesUtil;
import cc.gukeer.open.service.ShortMessageService;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lx on 2016/11/28.
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {


   /* @Override
    public String sendMessageByGet(String mobile, String msg, boolean needstatus, String extno) throws Exception {
        String res = batchSendByGet(url,account,pswd,mobile,msg,needstatus,extno);
        return res;
    }

    @Override
    public String sendMessageByPost(String mobile, String msg, boolean needstatus, String extno) throws Exception {
        String res = batchSendByPost(url,account,pswd,mobile,msg,needstatus,extno);
        return res;
    }*/


    /*private static String batchSendByGet(String url, String account, String pswd, String mobile, String msg,
                                      boolean needstatus, String extno) throws Exception {
        try {
            Map queries = new HashMap();
            queries.put("account", account);
            queries.put("pswd", pswd);
            queries.put("mobile", mobile);
            queries.put("msg", msg);
            queries.put("needstatus", needstatus);
            queries.put("extno", extno);
            String res = HttpRequestUtil.get(url, null, queries);
            return res;
        }catch (Exception e){
            return "";
        }
    }

    private static String batchSendByPost(String url, String account, String pswd, String mobile, String msg,
                                        boolean needstatus, String extno) throws Exception {
        try {
            Map params = new HashMap();
            params.put("account", account);
            params.put("pswd", pswd);
            params.put("mobile", mobile);
            params.put("msg", msg);
            params.put("needstatus", needstatus);
            params.put("extno", extno);
            String res = HttpRequestUtil.post(url, null, null, params);
            return res;
        }catch (Exception e){
            return "";
        }
    }
*/

    public   String batchSend(String url, String account, String pswd, String mobile, String msg,
                            boolean needstatus, String extno) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("un", account),
                    new NameValuePair("pw", pswd),
                    new NameValuePair("phone", mobile),
                    new NameValuePair("rd", "0"),
                    new NameValuePair("msg", msg),
                    new NameValuePair("ex", extno),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }
}
