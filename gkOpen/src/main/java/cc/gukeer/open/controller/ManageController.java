package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.modelView.OpenMessageView;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.service.*;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LL on 2016/12/7.
 */
@Controller
@RequestMapping(value = "/manager")
public class ManageController extends BasicController {

    @Autowired
    MultigraphService multigraphService;

    @Autowired
    OpenUserService openUserService;
    @Autowired
    PersonalService personalService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AccessoriesService accessoriesService;
    @Autowired
    AppService appService;
    @Autowired
    MessageService messageService;

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

    @InitBinder("app")
    public void initBinderApp(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("app.");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        //管理员用户直接跳转后台审核页面
        OpenUser openUser = getLoginUser();
        String userId = openUser.getId();
        OpenUser newOpenUser = openUserService.getOpenUserById(userId);
        request.getSession().setAttribute("openUser", newOpenUser);
        if (openUser.getUserType() == LoginUserType.ADMIN.getStatenum()) {
            String name = request.getServerName();
            int port = request.getServerPort();
            String comtext = request.getContextPath();
            //原来采用的返回方式是直接 return  "/admin/index"
            // 出现的问题就是会在访问时出现路径为+...+/manager/admin/index的错误
            model.addAttribute("adminIndex","adminIndex");
            return "redirect:http://" + name + ":" + port + comtext + "/admin/index";
        }
        //非管理员用户登陆成功
        //首先获取审核的状态
        int user_status = newOpenUser.getStatus();
        int userType = newOpenUser.getUserType();

        if (user_status == CheckStateType.AUDIT_SUCCESS.getStatenum()) {
            //如果用户通过审核 就查寻全部应用
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);
            String _app_status = request.getParameter("status");
            PageInfo<App> appPageInfo = null;
            if (_app_status == null) {
                appPageInfo = appService.finaAppByOpenUser(pageNum, pageSize, openUser);
                model.addAttribute("appPageInfo", appPageInfo);
                model.addAttribute("status2", null);
                return "manager/index";
            } else {
                int app_status = Integer.valueOf(_app_status);
                appPageInfo = appService.findAppByDetail(pageNum, pageSize, app_status, openUser);
                model.addAttribute("appPageInfo", appPageInfo);
                model.addAttribute("status2", app_status);
                return "manager/index";
            }
        } else {
            //用户未通过审核 就查询基本资料
            if (openUser.getUserType() == LoginUserType.COMPANY.getStatenum()) {
                Company company = companyService.findCompanylbyloginUser(openUser);

                Accessories accessories = accessoriesService.findAccessoriesByCompany(company);
                model.addAttribute("company", company);
                model.addAttribute("accessories", accessories);
                model.addAttribute("openUser", openUser);
                model.addAttribute("basic", "basic");
                return "manager/account/company";
            } else {

            Personal personal = personalService.findPersonalbyloginUser(openUser);

                Accessories accessories = accessoriesService.findAccessoriesByPersonal(personal);
                model.addAttribute("accessories", accessories);
                model.addAttribute("openUser", openUser);
                model.addAttribute("personal", personal);
                model.addAttribute("basic", "basic");
                return "manager/account/personal";
            }

        }
    }

    @RequestMapping(value = "/password/page", method = RequestMethod.GET)
    public String updatepasswordPage(HttpServletRequest request, Model model) {
        OpenUser openUser = getLoginUser();
        String passWord = openUser.getPassword();
        model.addAttribute("passWord",passWord);
        model.addAttribute("PWDPage", "PWDPage");
        return "manager/account/updatePWD";
    }

    //跳转创建应用页面
    @RequestMapping(value = "/app/add", method = RequestMethod.GET)
    public String createapplication(HttpServletRequest request, Model model) {
        model.addAttribute("addApp", "addApp");
        return "manager/app/add";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(HttpServletRequest request, Model model) {
        model.addAttribute("upload", "upload");
        return "manager/upload/upload";
    }
    //跳转基本资料页面
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public String basic(HttpServletRequest request, Model model) {
        OpenUser _openUser = getLoginUser();
        String userId = _openUser.getId();
        OpenUser openUser = openUserService.getOpenUserById(userId);
        model.addAttribute("basic", "basic");
        if (openUser.getUserType() == LoginUserType.COMPANY.getStatenum()) {
            Company company = companyService.findCompanylbyloginUser(openUser);
            if (company == null) {
                return "manager/account/company";
            }
            Accessories accessories = accessoriesService.findAccessoriesByCompany(company);
            model.addAttribute("company", company);
            model.addAttribute("accessories", accessories);
            model.addAttribute("openUser", openUser);
            return "manager/account/company";
        } else {
            Personal personal = personalService.findPersonalbyloginUser(openUser);
            if (personal == null) {
                return "manager/account/personal";
            }
            Accessories accessories = accessoriesService.findAccessoriesByPersonal(personal);
            model.addAttribute("accessories", accessories);
            model.addAttribute("openUser", openUser);
            model.addAttribute("personalAddress", personal.getAddress());
            model.addAttribute("personal", personal);
            return "manager/account/personal";

        }
    }

    @ResponseBody
    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public ResultEntity updatePassword(HttpServletRequest request) {
        OpenUser openUser = getLoginUser();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String password_ = AESencryptor.encryptCBCPKCS5Padding(currentPassword);
        if (!password_.equals(openUser.getPassword())) {
            return ResultEntity.newErrEntity("密码错误");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return ResultEntity.newErrEntity("两次输入的密码不一致");
        }
        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmNewPassword)) {
            return ResultEntity.newErrEntity("密码不能为空");
        }
        if (newPassword.equals(confirmNewPassword)) {
            openUser.setPassword(AESencryptor.encryptCBCPKCS5Padding(newPassword));
            Boolean boo = openUserService.updateOpenUser(openUser);
            if (boo) {
                return ResultEntity.newResultEntity("密码修改成功", "/doLogout");
            }
        }
        return ResultEntity.newResultEntity("密码修改失败");
    }

    @RequestMapping(value = "/personindex", method = RequestMethod.GET)
    public String personindex(HttpServletRequest request) {
        return "manager/personindex";
    }

    @RequestMapping(value = "/info/index", method = RequestMethod.GET)
    public String info(HttpServletRequest request, Model model) {
        OpenUser openUser = getLoginUser();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<OpenMessage> messagePageInfo = messageService.findInfoByOpenUser(pageNum, pageSize, openUser);
        List<OpenMessage> list = messagePageInfo.getList();
        List<OpenMessageView> listView = new ArrayList();
        for (OpenMessage openMessage : list) {
            OpenMessageView openMessageView = new OpenMessageView();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (openMessage.getCreateDate() != null) {
                Date date = new Date(openMessage.getCreateDate());
                String res = simpleDateFormat.format(date);
                openMessageView.setDateFormat(res);
            }
            openMessageView.setOpenMessage(openMessage);
            listView.add(openMessageView);
        }
        model.addAttribute("messagePageInfo", messagePageInfo);
        model.addAttribute("messageContent", listView);
        model.addAttribute("openUser", openUser);
        model.addAttribute("all", "all");
        return "manager/info/index";
    }

    @RequestMapping(value = "/info/detail", method = RequestMethod.GET)
    public String infoDetail(HttpServletRequest request, Model model) {
        String id = request.getParameter("messageId");
        OpenMessage openMessage = messageService.findByMessageId(id);
        openMessage.setIsread(1);
        int readed =  messageService.updateByPrimarykey(openMessage);
        model.addAttribute("openMessage", openMessage);
        model.addAttribute("infoDetail", "infoDetail");
        return "manager/info/detail";
    }

    @RequestMapping(value = "/dynamic", method = RequestMethod.GET)
    public String manageCenter4(HttpServletRequest request, Model model) {
        model.addAttribute("dynamic", "dynamic");
        return "manager/info/dynamic";
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String registerall(HttpServletRequest request,
                              Company company,
                              Personal personal,
                              Model model) {
        OpenUser openUser = getLoginUser();
        int loginUserType = openUser.getUserType();
        int result = 0;
        openUser.setStatus(CheckStateType.UPDATE_AUDITING.getStatenum());
        //入库
        openUser.setUpdateDate(System.currentTimeMillis());
        openUserService.updateOpenUserById(openUser, openUser.getId());
        if (loginUserType == LoginUserType.COMPANY.getStatenum()) {
            String companyId = openUser.getCompanyId();
            company.setId(companyId);
            companyService.updateCompanyByPrimaryKeySelective(company);
            Company company1 = companyService.findCompanylbyloginUser(openUser);
            Accessories accessories = accessoriesService.findAccessoriesByCompany(company1);
            model.addAttribute("company", company1);
            model.addAttribute("accessories", accessories);
            return "redirect:/manager/index";
        } else {
            String personalId = openUser.getPersonalId();
            personal.setId(personalId);
            personalService.updatePersonalByPrimaryKeySelective(personal);
            Personal personal1 = personalService.findPersonalbyloginUser(openUser);
            Accessories accessories = accessoriesService.findAccessoriesByPersonal(personal1);
            model.addAttribute("personal", personal1);
            model.addAttribute("accessories", accessories);
            return "redirect:/manager/index";
        }
    }
}
