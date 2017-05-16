package cc.gukeer.common.utils;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录

    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     * @throws UnsupportedEncodingException
     */
    public static JsonObject httpPost(String url, List jsonParam) throws UnsupportedEncodingException{
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param noNeedResponse    不需要返回结果
     * @return
     * @throws UnsupportedEncodingException
     */
    public static JsonObject httpPost(String url, List list, boolean noNeedResponse) throws UnsupportedEncodingException{
        //post请求返回结果
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JsonObject jsonResult = null;
        HttpPost method = new HttpPost(url);


                //解决中文乱码问题
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
                method.setEntity(uefEntity);

            try {
                CloseableHttpResponse  result = httpclient.execute(method);
                HttpEntity entity = result.getEntity();
                System.out.println(entity.toString());
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                url = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /**请求发送成功，并得到响应**/
          /* if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    str = EntityUtils.(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }*/
        return jsonResult;
            //return jsonResult;

   }
}

