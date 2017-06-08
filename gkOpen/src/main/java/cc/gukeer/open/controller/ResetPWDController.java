package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.MailUtils;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.OpenUserService;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by LL on 2017/1/19.
 */
@Controller
@RequestMapping(value = "/pwd")
public class ResetPWDController extends BasicController {
    @Autowired
    OpenUserService openUserService;

    @RequestMapping(value = "/index", method =RequestMethod.GET )
    public String index(HttpServletRequest request){
        return "findPWD/index";
    }

    @RequestMapping(value = "/mail/send")
    public String seekPWD(HttpServletRequest request, Model model) {

        try {
            //获取当前的用户
            String email = request.getParameter("username");
            String url = getMailUrl(email);
            //通过用户名去邮箱查询当前用户
            OpenUser openUser = openUserService.queryUserByUserNameEmail(email);
            if (null != openUser) {
                MailUtils mailUtils = new MailUtils();
                //生成密钥和时间
                String secretKey = UUID.randomUUID().toString(); // 密钥
                Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
                long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
                //首先更新数据库 记录发送邮件的时间和密钥
                openUser.setRandom(secretKey);
                openUser.setCurrentTimeSendemail(date);
                String openUserId = openUser.getId();
                openUserService.updateOpenUserById(openUser,openUserId);
                String mailContent = getMailContent(request,openUser,date,secretKey);
                mailUtils.sendMail(openUser,request,mailContent,"【教育云开放平台】找回密码");
                model.addAttribute("username",email);
                model.addAttribute("url",url);
                return "findPWD/loginEmail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping(value = "check/username")
    public ResultEntity checkUsername(HttpServletRequest request){
        String username = request.getParameter("email");
        if (openUserService.queryUserByUserName(username)){
            return ResultEntity.newResultEntity();
        }else {
            return ResultEntity.newErrEntity("该邮箱尚未注册");
        }
    }
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkResetLink(HttpServletRequest request, Model model) {
        //获取邮件的参数
        String userName = request.getParameter("userName");
        //数据库查询是否存在该用户
        OpenUser openUser = openUserService.queryUserByUserNameEmail(userName);
        String sid = request.getParameter("sid");
        System.out.println("sid>>>" + sid);
        if (sid.equals("")  || userName.equals("")) {
            request.setAttribute("mesg", "链接不完整,请重新生成");
            return "error";
        }
        if (null != openUser) {
            Long outDate =  openUser.getCurrentTimeSendemail();
            System.out.println("outDate>>>"+outDate);
            if(new Date().getTime() - outDate >= 1800000){ //表示已经过期
                request.setAttribute("mesg", "链接已经过期,请重新申请找回密码.");
                return "error";
            }
            String key = openUser.getUsername()+"$"+outDate/1000*1000+"$"+openUser.getRandom();//数字签名
            System.out.println("key link》》"+key);
            String digitalSignature = MD5Utils.md5(key);// 数字签名
            System.out.println("digitalSignature>>>>"+digitalSignature);
            if(!digitalSignature.equals(sid)) {
                request.setAttribute("mesg", "链接不正确,是否已经过期了?重新申请吧.");
                System.out.println("标示不正确");
                return "error";
            }else {
                //链接验证通过 转到修改密码页面
                model.addAttribute("username", userName);
                return "findPWD/updatePWD";
            }
        }else {
            request.setAttribute("mesg", "链接错误,无法找到匹配用户,请重新申请找回密码.");
            System.out.println("用户不存在");
            return "error";
        }
    }
    @ResponseBody
    @RequestMapping(value = "update")
    public ResultEntity updatePWD(HttpServletRequest request){
        String password = request.getParameter("password");
        String username = request.getParameter("userEmail");
//        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        logger.info("LoginUsername: {}, password: {}", username, password);
        if (StringUtil.isEmpty(password)) {
            return ResultEntity.newErrEntity("请填写完整信息");
        }
//        Boolean boo = password.matches(regex);
        if (password.length()<6){
            return ResultEntity.newErrEntity("密码长度不能小于6");
        }
        OpenUser openUser = openUserService.queryUserByUserNameEmail(username);
        openUser.setPassword( AESencryptor.encryptCBCPKCS5Padding(password));
        if (openUserService.updateOpenUser(openUser)){
            return ResultEntity.newResultEntity();
        }else {
            return ResultEntity.newErrEntity();
        }
    }

    public String getMailContent(HttpServletRequest request,OpenUser openUser,Long date,String secretKey){
        StringBuffer sb = new StringBuffer("点击下面的账号修改密码，30分钟内有效，否则重新获取，链接只能使用一次！</br>");
        String basePath = getBasepath(request);
        //生成数字签名
        String key =openUser.getUsername() + "$" + date + "$" + secretKey;
        System.out.println(" key>>>"+key);
        String digitalSignature = MD5Utils.md5(key);
        String resetPassHref = "pwd/check?sid="
                + digitalSignature +"&userName="+openUser.getUsername();
        String url = basePath + resetPassHref;
        sb.append("<a href=").append(url).append(">")
                .append(url)
                .append("</a>");
        return sb.toString();
    }
}
