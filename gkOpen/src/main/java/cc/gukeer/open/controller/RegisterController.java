
package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.MailUtils;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.common.ProjectConfig;
import cc.gukeer.open.common.RegisterStatusType;
import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.persistence.entity.Personal;
import cc.gukeer.open.service.AccessoriesService;
import cc.gukeer.open.service.CompanyService;
import cc.gukeer.open.service.OpenUserService;
import cc.gukeer.open.service.PersonalService;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BasicController {
    @Autowired
    OpenUserService openUserService;
    @Autowired
    PersonalService personalService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AccessoriesService accessoriesService;

    @InitBinder("company")
    public void initBinderCompany(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("company.");
    }

    @InitBinder("personal")
    public void initBinderPersonal(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("personal.");
    }

    @InitBinder("OpenUser")
    public void initBinderLoginUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("OpenUser.");
    }

    @InitBinder("accessories")
    public void initBinderAccessories(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("accessories.");
    }

    //跳转注册页面
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String reg(Model model) {
        model.addAttribute("registerindex", "registerindex");
        return "register/index";
    }

    //跳转激活邮箱页面
    @RequestMapping(value = "/emailcheck")
    public String emailChecking(HttpServletRequest request, Model model) {
        String loginUserId = request.getParameter("id");
        OpenUser OpenUser = openUserService.getOpenUserById(loginUserId);
        String username = OpenUser.getUsername();
        String url = getMailUrl(username);
        model.addAttribute("username", username);
        model.addAttribute("url", url);
        return "register/emailcheck";
    }

    //跳转个人详情填写页面
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String personalCompleteData(HttpServletRequest request, Model model) {
        String loginUserId = request.getParameter("id");
        OpenUser OpenUser = openUserService.getOpenUserById(loginUserId);
        String username = OpenUser.getUsername();
        model.addAttribute("username", username);
        model.addAttribute("loginUserId", loginUserId);
        if (OpenUser.getUserType() == LoginUserType.PERSONAL.getStatenum()) {
            return "register/personal";
        } else {
            return "register/company";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/mail/resend", method = RequestMethod.POST)
    public ResultEntity personalCompleteData(HttpServletRequest request) {

        String openUserId = request.getParameter("id");
        String username = request.getParameter("username");
        OpenUser openUser = null;
        if (StringUtil.isNotEmpty(openUserId)) {
            openUser = openUserService.getOpenUserById(openUserId);
        } else {
            openUser = openUserService.queryUserByUserNameEmail(username);
        }
        MailUtils mailUtils = new MailUtils();
        try {
            String mailContent = getMailContent(request, openUser);
            mailUtils.sendMail(openUser, request, mailContent, "重新发送的激活邮件");
            return ResultEntity.newResultEntity("邮件已经重新发送", "/register/emailcheck?id=" + openUser.getId() + "&username=" + openUser.getUsername());
        } catch (MessagingException e) {
            e.printStackTrace();
            //request.setAttribute("",);
            return ResultEntity.newErrEntity("error");
        }
    }

    //注册登录信息
    @ResponseBody
    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public ResultEntity register(HttpServletRequest request, OpenUser openUser, HttpServletResponse response) throws MessagingException {
        int roleflag = openUser.getUserType();
        String username = openUser.getUsername();
        String password = openUser.getPassword();
//        String regex = "[A-Za-z0-9]{6}";
        logger.info("LoginUsername: {}, password: {}", username, password);
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return ResultEntity.newErrEntity("请填写完整信息");
        }
//        Boolean boo = password.matches(regex);
        if (password.length() < 6) {
            return ResultEntity.newErrEntity("请填写六位以上的密码");
        }
        //查询数据库用户不存在
        Boolean loginUserFromDataBase = openUserService.queryUserByUserName(openUser.getUsername());
        MailUtils mailUtils = new MailUtils();
        if (!loginUserFromDataBase) {
            //随机数，用于发送邮件时的密钥的拼接
            String random = RandomStringUtils.random(5);
            //时间戳，密钥拼接
            long timeStamp = System.currentTimeMillis();

            openUser.setId(PrimaryKey.get());
            openUser.setCurrentTimeSendemail(timeStamp);
            openUser.setRandom(random);

            //审核状态【1提交等待审核，2审核成功，3审核失败，4删除，5修改i信息等待审核】
            openUser.setStatus(0);
            //激活状态  【0未激活, 1激活, 2冻结】
            openUser.setRegisterStatus(0);
            openUser.setCreateDate(new Date().getTime());
            openUser.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));
            String content = getMailContent(request, openUser);
            mailUtils.sendMail(openUser, request, content, "激活邮件");
            //OpenUser.setCreateDate(new Date().getTime());
            openUserService.saveOpenUser(openUser);
            String url = "/register/emailcheck?id=" + openUser.getId() + "&username=" + username;
            return ResultEntity.newResultEntity(0, "正在发送验证邮件到您的邮箱，请稍后", url);
        } else {
            //查询数据库用户存在
            OpenUser loginUserFromDB = openUserService.queryUserByUserNameEmail(openUser.getUsername());
            Integer registerStatus = loginUserFromDB.getRegisterStatus();
            if (registerStatus == RegisterStatusType.UNACTIVATE.getStatenum()) {
                Long createDate = loginUserFromDB.getCreateDate();
                Date diffCurrentTime = new Date();
                Long currentTime = diffCurrentTime.getTime();
                Long diff = currentTime - createDate;
                Long hours = diff / (1000 * 60 * 60);
                if (hours > 24) {
                    try {
                        String content = getMailContent(request, openUser);
                        mailUtils.sendMail(openUser, request, content, "激活邮件");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return ResultEntity.newResultEntity("邮箱已经注册，请前往邮箱激活账号", "/register/emailcheck?id=" + loginUserFromDB.getId());
                } else {
                    return ResultEntity.newResultEntity("邮箱已经注册，请前往邮箱激活账号", "/register/emailcheck?id=" + loginUserFromDB.getId());
                }
            } else if (registerStatus == RegisterStatusType.ACTIVATE.getStatenum()) {
                OpenUser _loginUser = openUserService.getByAccountAndPwd(username, AESencryptor.encryptCBCPKCS5Padding(password));
                if (_loginUser != null) {
                    return ResultEntity.newResultEntity("请完善个人资料", "/register/detail?id=" + loginUserFromDB.getId());
                } else {
                    return ResultEntity.newResultEntity("邮箱已经注册，请前往邮箱激活账号", "/register/emailcheck?id=" + loginUserFromDB.getId());
                }
            }
            return ResultEntity.newResultEntity(1, "该邮箱已注册完成,请前往首页登陆", "/");
        }
    }

    //用户点击邮箱中的链接 激活账号
    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activate(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultEntity resultEntity = null;
        //获取get请求的所有参数名并遍历得到的参数名的值
        String email = request.getParameter("email");
        String checksum = request.getParameter("checksum");
        OpenUser openUser = openUserService.queryUserByUserNameEmail(email);
        if (null == openUser) {
            model.addAttribute("error", "当前邮箱未通过注册");
            return "error";
        }
        int registerStatus = openUser.getRegisterStatus();
        if (registerStatus == RegisterStatusType.ACTIVATE.getStatenum()) {
            try {
                response.getWriter().write("该邮箱已经激活，请前往网站首页登陆");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long currentTimeSendEmail = openUser.getCurrentTimeSendemail();
        String random = openUser.getRandom();
        String md5_code = MD5Utils.md5(ProjectConfig.SECRET_STR + email + random + currentTimeSendEmail);
        try {
            if (md5_code.equals(checksum)) {
                long nowCurrentTime = System.currentTimeMillis();
                long diff = nowCurrentTime - currentTimeSendEmail;//这样得到的差值是微秒级别
                long hours = diff / (1000 * 60 * 60);
                if (hours > 24) {
                    MailUtils mailUtils = new MailUtils();
                    openUser.setCreateDate(nowCurrentTime);
                    String content = getMailContent(request, openUser);
                    mailUtils.sendMail(openUser, request, content, "教育云开放平台激活邮件");
                    model.addAttribute("error", "注册时间已经超过24小时，请前往邮箱查收激活邮件重新激活账号");
                    return "error";
                } else {
                    openUser.setRegisterStatus(RegisterStatusType.ACTIVATE.getStatenum());
                    Boolean boo = openUserService.updateOpenUser(openUser);
                    if (boo) {
                        String name = request.getServerName();
                        int port = request.getServerPort();
                        String comtext = request.getContextPath();
                        return "redirect:http://" + name + ":" + port + comtext + "/register/detail?id=" + openUser.getId();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "认证失败");
            return "error";
        }
        model.addAttribute("error", "认证失败");
        return "error";
    }

    //注册时验证码的判断
    @ResponseBody
    @RequestMapping(value = "/judgeCode", method = RequestMethod.POST)
    public ResultEntity judge(HttpServletRequest request) {
        String _random = request.getParameter("code");
        if (StringUtils.isEmpty(_random)) {
            return ResultEntity.newErrEntity("验证码不能为空");
        }
        String usernameEmail = request.getParameter("username");
        OpenUser OpenUser = openUserService.queryUserByUserNameEmail(usernameEmail);
        String random = OpenUser.getRandom();
        if (!random.equals(_random)) {
            return ResultEntity.newErrEntity("验证码错误");
        }
        return ResultEntity.newResultEntity();
    }

    //注册时详细信息的填写
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String registerall(HttpServletRequest request,
                              Company company,
                              Personal personal,
                              Accessories accessories,
                              Model model) {
        String loginUserId = request.getParameter("loginUserId");
        OpenUser openUser = openUserService.getOpenUserById(loginUserId);
        String cmbProvince = request.getParameter("cmbProvince");
        String cmbCity = request.getParameter("cmbCity");
        String cmbArea = request.getParameter("cmbArea");
        String cmbAddress = request.getParameter("cmbAddress");
        String address = cmbProvince + cmbCity + cmbArea + cmbAddress;
        //保存图片信息到图片表
        try {
            accessories.setId(PrimaryKey.get());
            accessoriesService.save(accessories);
            if (openUser.getUserType() == LoginUserType.PERSONAL.getStatenum()) {
                //保存个人详细信息
                personal.setAddress(address);
                personal.setAccessoriesId(accessories.getId());
                personal.setId(PrimaryKey.get());
                personalService.save(personal);
                openUser.setPersonalId(personal.getId());
                openUser.setRegisterStatus(RegisterStatusType.WRITE.getStatenum());
                openUser.setStatus(CheckStateType.AUDITING.getStatenum());
                openUser.setDelFlag(0);
                openUser.setCreateDate(System.currentTimeMillis());
                openUser.setUpdateDate(System.currentTimeMillis());
                openUserService.updateOpenUser(openUser);
                request.getSession().setAttribute("status", openUser.getStatus());
                return "redirect:/registerLogin?username=" + openUser.getUsername() + "&&password=" + openUser.getPassword();
            } else {
                //保存公司详细信息
                company.setAddress(address);
                company.setAccessoriesId(accessories.getId());
                company.setId(PrimaryKey.get());
                companyService.save(company);
                openUser.setCompanyId(company.getId());
                openUser.setRegisterStatus(RegisterStatusType.WRITE.getStatenum());
                openUser.setStatus(CheckStateType.AUDITING.getStatenum());
                openUser.setDelFlag(0);
                openUser.setUpdateDate(System.currentTimeMillis());
                openUserService.updateOpenUser(openUser);
                request.getSession().setAttribute("status", openUser.getStatus());
                return "redirect:/registerLogin?username=" + openUser.getUsername() + "&&password=" + openUser.getPassword();
            }
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
            return "error";
        }
    }

    public String getMailContent(HttpServletRequest request, OpenUser openUser) {
        String md5_code = MD5Utils.md5(ProjectConfig.SECRET_STR + openUser.getUsername() + openUser.getRandom() + openUser.getCurrentTimeSendemail());
        String basepath = getBasepath(request);
        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        String _url = basepath + "register/activate?email=" + openUser.getUsername() + "&checksum=" + md5_code;
        sb.append("<a href=").append(_url).append(">")
                .append(_url)
                .append("</a>");
        return sb.toString();
    }
}
