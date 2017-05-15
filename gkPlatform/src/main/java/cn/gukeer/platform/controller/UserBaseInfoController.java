package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.Student;
import cn.gukeer.platform.persistence.entity.Teacher;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.SchoolService;
import cn.gukeer.platform.service.StudentService;
import cn.gukeer.platform.service.TeacherService;
import cn.gukeer.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by conn on 2016/9/16.
 */
@Controller
@RequestMapping("/user")
public class UserBaseInfoController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @RequestMapping(value = "/editInfo", method = RequestMethod.GET)
    public String editBaseInfo(HttpServletRequest request, Model model) {
        String name = "";
        String account = "";
        String _type = "";
        String phone = "";
        String headUrl = "";

        Integer type = getLoginUser().getUserType();
        String sid = getLoginUser().getSchoolId();
        School school = schoolService.selectSchoolById(sid);
        if (type == 1) {
            String id = getLoginUser().getRefId();
            Teacher teacher = teacherService.findTeacherById(id);
            if (teacher != null) {
                name = teacher.getName();
                account = teacher.getAccount();
                phone = teacher.getPhone();
            }
            _type = "教师";
        } else if (type == 2) {
            String id = getLoginUser().getRefId();
            Student student = studentService.selectStudentById(id);
            if (student != null) {
                name = student.getXsxm();
                account = student.getAccount();
                phone = student.getPhone();
            }
            _type = "学生";
        }else if(type ==3){
            account = getLoginUser().getUsername();
            _type = "家长";
        }
        headUrl = getLoginUser().getPhotoUrl();

        model.addAttribute("headUrl", headUrl);
        model.addAttribute("id", getLoginUser().getRefId());
        model.addAttribute("phone", phone);
        model.addAttribute("name", name);
        model.addAttribute("account", account);
        model.addAttribute("type", _type);
        model.addAttribute("school", school);
        return "users/userInfo";
    }

    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public ResultEntity updateBaseInfo(HttpServletRequest request) {

        try {
            Integer type = getLoginUser().getUserType();

            String id = getParamVal(request, "id");
            String url = getParamVal(request, "headUrl");
            String phone = getParamVal(request, "phone");
            if (type == 1) {
                Teacher teacher = new Teacher();
                teacher.setId(id);
                teacher.setPhone(phone);
                teacherService.save(teacher);
            } else if (type == 2) {
                Student student = new Student();
                student.setId(id);
                student.setPhone(phone);
                studentService.save(student);
            }
            User user = getLoginUser();
            user.setPhotoUrl(url);
            userService.saveUser(user);

        } catch (Exception e) {
            logger.error("msg: {}, cause: {}", e.getMessage(), e.getCause());
        }

        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/editPwd", method = RequestMethod.GET)
    public String editPwd(HttpServletRequest request, Model model) {

        return "users/modifyPwd";
    }

    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public ResultEntity updatePwd(HttpServletRequest request) {

        String oriPwd = getParamVal(request, "oriPwd");
        String newPwd = getParamVal(request, "newPwd");

        String id = getLoginUser().getId();
        String account = getLoginUser().getUsername();
        User user = userService.getByAccountAndPwd(account, AESencryptor.encryptCBCPKCS5Padding(oriPwd));
        if (null != user && user.getId().equals(id)) {
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(newPwd));
            userService.saveUser(user);
        } else {
            return ResultEntity.newErrEntity("原密码错误，请重试！");
        }

        return ResultEntity.newResultEntity();
    }
}
