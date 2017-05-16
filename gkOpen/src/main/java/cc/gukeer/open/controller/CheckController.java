
package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.common.dwr.MessagePush;
import cc.gukeer.open.modelView.AppAllInfoView;
import cc.gukeer.open.modelView.CompanyAllInfoView;
import cc.gukeer.open.modelView.PersonalAllInfoView;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/check")
public class CheckController extends BasicController {
    @Autowired
    OpenUserService openUserService;
    @Autowired
    AppService appService;
    @Autowired
    PersonalService personalService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AccessoriesService accessoriesService;
    @Autowired
    MultigraphService multigraphService;
    @Autowired
    PushPlatformService pushPlatformService;
    @Autowired
    MessageService messageService;
    @Autowired
    RefPlatformService refPlatformService;

    @RequestMapping(value = "/details/{viewflag}", method = RequestMethod.GET)
    public String check(HttpServletRequest request, Model model, @PathVariable String viewflag) {
        String id = request.getParameter("id");
        String mark = request.getParameter("mark");
        String type = request.getParameter("type");
        model.addAttribute("mark", mark);
        OpenUser openUser = openUserService.getOpenUserById(id);
        OpenMessage openMessage = messageService.findByRefId(id, NumberConvertUtil.convertS2I(type));
        if (viewflag.equals("user")) {
            if (openUser.getUserType() == LoginUserType.COMPANY.getStatenum()) {
                CompanyAllInfoView companyAllInfoView = new CompanyAllInfoView();
                Company company = companyService.findCompanylbyloginUser(openUser);
                Accessories accessories = accessoriesService.findAccessoriesByCompany(company);
                companyAllInfoView.setAccessories(accessories);
                companyAllInfoView.setCompany(company);
                model.addAttribute("companyInfo", companyAllInfoView);
                model.addAttribute("username", openUser.getUsername());
                if (openMessage != null){
                    model.addAttribute("message", openMessage.getText());
                }
                model.addAttribute("userId", openUser.getId());
                model.addAttribute("adminIndex", "adminIndex");
                return "check/companydetail";
            } else {
                PersonalAllInfoView personalAllInfoView = new PersonalAllInfoView();
                Personal personal = personalService.findPersonalbyloginUser(openUser);
                Accessories accessories = accessoriesService.findAccessoriesByPersonal(personal);
                personalAllInfoView.setAccessories(accessories);
                personalAllInfoView.setPersonal(personal);
                model.addAttribute("personalInfo", personalAllInfoView);
                model.addAttribute("username", openUser.getUsername());
                model.addAttribute("userId", openUser.getId());
                if (openMessage != null){
                    model.addAttribute("message", openMessage.getText());
                }
                model.addAttribute("adminIndex", "adminIndex");
                return "check/personaldetail";
            }
        } else {
            AppAllInfoView appAllInfoView = appService.getAppAllInfo(id);
            String screenshot = appAllInfoView.getApp().getAppScreenshot();
            List<String> screenshotList = multigraphService.makeImgPath(screenshot);
            model.addAttribute("appInfo", appAllInfoView);
            model.addAttribute("screenshotList", screenshotList);
            if (openMessage != null){
                model.addAttribute("message", openMessage.getText());
            }
            return "check/appdetail";
        }
    }

    //审核用户
    @ResponseBody
    @RequestMapping(value = "/user/{isPass}")
    public ResultEntity checkPersonal(@PathVariable String isPass, HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String text = request.getParameter("text");
        try {
            if (isPass.equals("Pass")) {
                openUserService.updateCheckState(CheckStateType.AUDIT_SUCCESS.getStatenum(), userId);
                messageService.deleteMessage(userId);
                OpenMessage openMessage = new OpenMessage();
                openMessage.setIsread(0);
                openMessage.setStatus(CheckStateType.AUDIT_SUCCESS.getStatenum());
                //0为用户
                openMessage.setMessageType(0);
                openUserService.addMessage(userId, openMessage);
            } else {
                messageService.deleteMessage(userId);
                openUserService.updateCheckState(CheckStateType.AUDIT_FAIL.getStatenum(), userId);
                OpenMessage openMessage = new OpenMessage();
                openMessage.setText(text);
                openMessage.setIsread(0);
                openMessage.setStatus(CheckStateType.AUDIT_FAIL.getStatenum());
                //0为用户
                openMessage.setMessageType(0);
                openUserService.addMessage(userId, openMessage);
            }
            return ResultEntity.newResultEntity("操作成功");
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultEntity.newResultEntity("操作失败");
        }
    }

    //审核应用
    @ResponseBody
    @RequestMapping(value = "/app/{isPass}")
    public ResultEntity checkApp(@PathVariable String isPass, HttpServletRequest request) {
        String appId = request.getParameter("appId");
        try {
            App app = appService.getAppById(appId);
            String userId = app.getUserId();
            if (isPass.equals("Pass")) {
                appService.updateStatus(appId, CheckStateType.AUDIT_SUCCESS.getStatenum());
                OpenMessage openMessage = new OpenMessage();
                openMessage.setIsread(0);
                openMessage.setAppId(appId);
                openMessage.setAppName(app.getName());
                openMessage.setStatus(CheckStateType.AUDIT_SUCCESS.getStatenum());
                //1为应用
                openMessage.setMessageType(1);
                //审核成功之后就把app的数据和平台的数据加入到中间表
                //首先获取所有平台信息 此时的平台信息并不知道有没有初始化信息 只是中间表插入一条数据
                List<Platform> platforms = pushPlatformService.findPlatformBydelflag();
                if (platforms != null && platforms.size()>0){
                    for (Platform platform:platforms) {
                        String platformId = platform.getId();
                        RefPlatformApp  refPlatformApp = refPlatformService.selectById(platformId,appId);
                        if (refPlatformApp !=null) {
                            refPlatformApp.setUpdateTime(new Date().getTime());
                            refPlatformApp.setOptStatus(0);
                            refPlatformApp.setAppStatus(2);
                            refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
                        }
                    }
                }
                openUserService.addMessage(userId, openMessage);
                MessagePush messagePush = new MessagePush();
                try {
                    messagePush.sendToId(app.getName(),app.getUserId(),1);
                }catch (Exception e){
                    return ResultEntity.newResultEntity("操作成功");
                }
                return ResultEntity.newResultEntity("操作成功");
            } else {
                String text = request.getParameter("text");
                OpenMessage openMessage = new OpenMessage();
                openMessage.setText(text);
                openMessage.setAppId(appId);
                openMessage.setIsread(0);
                openMessage.setAppName(app.getName());
                openMessage.setStatus(CheckStateType.AUDIT_FAIL.getStatenum());
                //1为应用
                openMessage.setMessageType(1);
                appService.updateStatus(appId, CheckStateType.AUDIT_FAIL.getStatenum());
                openUserService.addMessage(userId, openMessage);
                MessagePush messagePush = new MessagePush();
                try {
                    messagePush.sendToId(app.getName(),app.getUserId(),0);
                }catch (Exception e){
                    return ResultEntity.newResultEntity("操作成功");
                }
                return ResultEntity.newResultEntity("操作成功");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultEntity.newResultEntity("操作失败");
        }
    }
}
