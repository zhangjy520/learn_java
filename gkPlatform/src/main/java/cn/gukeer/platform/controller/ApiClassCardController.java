package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.platform.modelView.weather.HeWeather;
import cn.gukeer.platform.service.SchoolService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by alpha on 17-7-7.
*/

@Controller
@RequestMapping(value = "/api/classcard")
public class ApiClassCardController extends BasicController {

    public static final String PASSWORD = "vqATIY";
    @Autowired
    SchoolService schoolService;

    @ResponseBody
    @RequestMapping(value = "/test")
    public ResultEntity tt(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String checksum = getParamVal(request, "security");
        String classCardId = getParamVal(request, "classCardId");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(classCardId).append(time).append(random).append(PASSWORD);
        //String token = MD5Utils.md5(paramString.toString());
         /*MessageDigest md5=MessageDigest.getInstance("MD5");
        　　　　　　BASE64Encoder base64en = new BASE64Encoder();
         　　　　　　//加密后的字符串
        　　　　　　String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
*/

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }
        System.out.println(token);
        return new ResultEntity().newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, "token:" + token);
    }

    @ResponseBody
    @RequestMapping(value = "/heweather")
    public ResultEntity weatherApi(HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String schoolId = getParamVal(request, "schoolId");
        String checksum = getParamVal(request, "security");
        String classCardId = getParamVal(request, "classCardId");
        String time = getParamVal(request, "time");
        String random = getParamVal(request, "random");

        StringBuffer paramString = new StringBuffer();
        paramString.append(classCardId).append(time).append(random).append(PASSWORD);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String token = Base64Utils.encodeToString(md5.digest(paramString.toString().getBytes("utf-8")));
        if (!token.equals(checksum)) {
            return ResultEntity.newErrEntity("传入数据有误");
        }

        Map<String, String> returnMap = new HashMap<>();
        if ("".equals(schoolId)) {
            return ResultEntity.newErrEntity("请传入学校ID");
        } else {
            String weatherStr = schoolService.getWeather(schoolId);
            Gson gson = new Gson();
            HeWeather heWeather = gson.fromJson(weatherStr,
                    new TypeToken<HeWeather>() {
                    }.getType());

            if (heWeather != null) {
                returnMap.put("txt_d", heWeather.getDaily_forecast().get(0).getCond().getTxt_d());
                returnMap.put("code_d", heWeather.getDaily_forecast().get(0).getCond().getCode_d());
                returnMap.put("max", heWeather.getDaily_forecast().get(0).getTmp().getMax());
                returnMap.put("min", heWeather.getDaily_forecast().get(0).getTmp().getMin());
                returnMap.put("hum", heWeather.getDaily_forecast().get(0).getHum());
            }
        }
        return new ResultEntity().newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, returnMap);
    }

}
