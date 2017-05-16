package cc.gukeer.attendance.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.HttpRequestUtil;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/attendancelogin")
public class AttendanceLoginController extends BasicController{
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public ResultEntity attendanceUserLogin(HttpServletRequest request) throws IOException {
        String userName = getParamVal(request,"username");
        String password = getParamVal(request,"password");
        String info=null;
        //获取授权码code
        Map param1 =new HashMap();
        param1.put("response_type","code");
        param1.put("scope","baseInfo");
        param1.put("client_id","K6s8Bsfp");
        param1.put("source","app");
        String data= HttpRequestUtil.get("http://localhost:8089/gkopen/authorize",null,param1);
        String checkData=  new JsonParser().parse(data).getAsJsonObject().get("data").toString();
        String check = new JsonParser().parse(checkData).getAsJsonObject().get("login").toString();
        if("false".equals(check)){
            param1.put("username",userName);
            param1.put("password",password);
            String accessCode=HttpRequestUtil.get("http://localhost:8089/gkopen/authorize",null,param1);
            //将字符串转化为json获取data内容
            String _accessCode=  new JsonParser().parse(accessCode).getAsJsonObject().get("data").toString();
            try{
                //在data内容获取access_code
                String access_code=  new JsonParser().parse(_accessCode).getAsJsonObject().get("access_code").toString();
                //通过access_code获取acess_token和refresh_token
                //去除双引号，返回的值为“dsasdasdass12312431”
                String _access_code = access_code.substring(1,access_code.length()-1);
                Map param2 = new HashMap();
                param2.put("client_id","K6s8Bsfp");
                param2.put("client_secret","U6Em0PTNJzV7Dctg");
                param2.put("grant_type","authorization_code");
                param2.put("code",_access_code);
                String accessToken=HttpRequestUtil.post("http://localhost:8089/gkopen/accessToken",null,null,param2);
                String _accessToken=  new JsonParser().parse(accessToken).getAsJsonObject().get("data").toString();
                String _access_token=  new JsonParser().parse(_accessToken).getAsJsonObject().get("access_token").toString();
                String access_token = _access_token.substring(1,access_code.length()-1);
                Map param3 = new HashMap();
                param3.put("access_token",access_token);
                info=HttpRequestUtil.get("http://localhost:8089/gkopen/userInfo",null,param3);
            }catch (Exception e){
                return ResultEntity.newResultEntity("用户名或密码错误");
            }

        }else if("true".equals(check)){
                return ResultEntity.newResultEntity("用户已经登陆");
        }
        return ResultEntity.newResultEntity(info);
    }
}
