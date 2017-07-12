package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.ClassCardNotifyView;
import cn.gukeer.platform.modelView.ClassCardView;
import cn.gukeer.platform.modelView.SchoolView;
import cn.gukeer.platform.modelView.StudentView;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.dao.ClassRoomMapper;
import cn.gukeer.platform.persistence.dao.GradeClassMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.persistence.entity.extention.GradeClassExtention;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.*;

/**
 * Created by alpha on 17-6-26.
 */
@Controller
@RequestMapping(value = "/classCard")
public class ClassCardContorller extends BasicController {
    @Autowired
    ClassCardService classCardService;

    @Autowired
    ClassService classService;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    A_GradeClassMapper a_gradeClassMapper;

    @Autowired
    ClassCardNotifyService classCardNotifyService;

    @Autowired
    ClassCardNotifyRefService classCardNotifyRefService;


    /**
     * 从云平台进入班牌
     *
     * @param request
     * @param model
     * @return
     */
    /*@RequiresPermissions("class:student:view")*/
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexGet(HttpServletRequest request, Model model) {
        String schoolId = getLoginUser().getSchoolId();
        String appid = getParamVal(request, "appId");
        SchoolView schoolview = new SchoolView();
        String[] judge = {"", "", "", ""};
        schoolview = classService.selectAndMakeTree(schoolId, judge);
        PageInfo<ClassCardView> pageInfo = classCardService.selectClassCardByChoose(schoolId, "", "", 0, 0, 0, 10);

        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        } else model.addAttribute("pageInfo", null);
        model.addAttribute("pageInfo", null);
        model.addAttribute("appId", appid);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("focusNode", "school_" + schoolId);
        model.addAttribute("schoolId", schoolId);

        List searchParam = new ArrayList();
        searchParam.add(schoolId);
        searchParam.add("");
        searchParam.add("");
        searchParam.add(0);
        searchParam.add(0);
        searchParam.add(-1);
        searchParam.add("");
        model.addAttribute("searchParam", searchParam);
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("xueJId", appid);
        return "classCard/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexPost(HttpServletRequest request, Model model, String nodeList) {
        String _cId = getParamVal(request, "classId");
        String _sId = getParamVal(request, "schoolId");
        String _xd = getParamVal(request, "xd");
        String _xq = getParamVal(request, "xq");
        String _nj = getParamVal(request, "nj");
        String nowfocus = getParamVal(request, "focusNode");
        String schoolId = getLoginUser().getSchoolId();

        String xd;
        int xq, nj, status;
        String sid;
        String cid;
        if (_cId != null && _cId != "")
            cid = _cId;
        else cid = "";
        if (_sId != null && _sId != "")
            sid = _sId;
        else sid = "";
        if (_xd != null && _xd != "")
            xd = _xd;
        else xd = "";
        if (_xq != null && _xq != "")
            xq = NumberConvertUtil.convertS2I(_xq);
        else xq = 0;
        if (_nj != null && _nj != "")
            nj = NumberConvertUtil.convertS2I(_nj);
        else nj = 0;
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        SchoolView schoolview = new SchoolView();
        String[] judge = {cid, xd, String.valueOf(xq), String.valueOf(nj)};
        if (nodeList == null || nodeList == "") {                 //可能性为0~
            schoolview = classService.selectAndMakeTree(schoolId, judge);
            model.addAttribute("schoolview", schoolview);
        } else {
            model.addAttribute("nodeList", nodeList);
        }

        /*PageInfo<ClassCard> pageInfo = classCardService.selectClassCardByChoose(sid, cid, xd, nj, xq, pageNum, pageSize);
        */
        PageInfo<ClassCardView> pageInfo = classCardService.selectClassCardByChoose(sid, cid, xd, nj, xq, pageNum, pageSize);

        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        }
        model.addAttribute("classId", cid);
        model.addAttribute("schoolId", sid);
        model.addAttribute("focusNode", nowfocus);
        model.addAttribute("xd", xd);
        model.addAttribute("xq", xq);
        model.addAttribute("nj", nj);

        return "classCard/index";
    }

    /**
     * 编辑班牌信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editClassCard(HttpServletRequest request, Model model) {


        String focusNode = getParamVal(request, "focusNode");       //当前选中的节点

        String schoolId = getLoginUser().getSchoolId();
        List<SchoolType> schoolTypes = teachTaskService.findAllSchoolTypeBySchoolId(schoolId);
        List<Map<String, String>> xds = classCardService.selectXdBySchool(schoolId);
        String _id = getParamVal(request, "id");
        if (!"".equals(_id)) {
            ClassCard classCard = classCardService.selectClassCardById(_id);
            System.out.println(classCard.getClassId());
            model.addAttribute("classCard", classCard);
            //班牌位置
            ClassRoom classRoom = new ClassRoom();
            if (!"".equals(classCard.getTeachClassRoomId()) && classCard.getTeachClassRoomId() != null) {
                classRoom = classRoomMapper.selectByPrimaryKey(classCard.getTeachClassRoomId());
            }
            model.addAttribute("classRoom", classRoom);
            //班牌班级
            GradeClassExtention gradeClassExtention = new GradeClassExtention();
            if (!"".equals(classCard.getClassId()) && classCard.getClassId() != null) {
                gradeClassExtention = a_gradeClassMapper.findByClassId(classCard.getClassId());
            }
            model.addAttribute("gradeClassExtention", gradeClassExtention);

        }
        String option = getParamVal(request, "option");
        model.addAttribute("option", option);
        model.addAttribute("schoolTypes", schoolTypes);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("xds", xds);

        return "classCard/edit";
    }

    /**
     * 级联查询编辑班牌时的班级位置信息
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cascadeClassRoom")
    public ResultEntity cascadeClassRoom(HttpServletRequest request, Model model, String mydata) {
        String schoolId = getLoginUser().getSchoolId();
        Map<String, Object> map = classCardService.selectCascadeClassRoom(mydata, schoolId);
        return ResultEntity.newResultEntity(map);
    }

    /**
     * 级联查询编辑班牌时的班级
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cascadeClass")
    public ResultEntity cascadeClass(HttpServletRequest request, Model model, String mydata) {
        String schoolId = getLoginUser().getSchoolId();
        Map<String, Object> map = classCardService.selectCascadeClass(mydata, schoolId);
        return ResultEntity.newResultEntity(map);
    }

    /**
     * 判断位置是否被占用
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isLocaltationToken")
    public ResultEntity isLocaltationToken(HttpServletRequest request, Model model) {
        String roomNum = getParamVal(request, "roomNum");
        ResultEntity resultEntity = null;
        int resultCount = 0;
        if (!"".equals(roomNum)) {
            resultCount = classCardService.selectClassCardByTeachClassRoomId(roomNum);
        } else {
            return null;
        }
        if (resultCount > 0) {
            resultEntity = ResultEntity.newErrEntity();
        } else {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "isClassToken")
    public ResultEntity isClassToken(HttpServletRequest request, Model model) {
        String classId = getParamVal(request, "classId");
//        int resultCount=classCardService.selectClassCardByClassId(classId);

        ResultEntity resultEntity = null;
        int resultCount = 0;
        if (!"".equals(classId)) {
            resultCount = classCardService.selectClassCardByClassId(classId);
        } else {
            return null;
        }
        if (resultCount > 0) {
            resultEntity = ResultEntity.newErrEntity();
        } else {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    /**
     * 保存班牌
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultEntity saveClassCard(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String macId = getParamVal(request, "macId");
        String equipmentName = getParamVal(request, "equipmentName");
        String teachClassRoomId = getParamVal(request, "teachClassRoomId");
        String classId = getParamVal(request, "classId");
        String classroom = getParamVal(request, "classroom");
        //String classSlogan = getParamVal(request, "classSlogan");
        String schoolId = getParamVal(request, "schoolId");

        ClassCard classCard = new ClassCard();
        classCard.setClassId(id);
        classCard.setMacId(macId);
        classCard.setEquipmentName(equipmentName);
        classCard.setTeachClassRoomId(teachClassRoomId);
        classCard.setClassId(classId);
        classCard.setClassroom(classroom);
        //classCard.setClassSlogan(classSlogan);
        classCard.setSchoolId(schoolId);

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        if ("".equals(id)) {
            classCard.setId(PrimaryKey.get());
            classCard.setDelFlag(0);
            classCard.setCreateBy(user.getId());
            classCard.setCreateDate(System.currentTimeMillis());
            classCardService.insertClassCard(classCard);
        } else {
            classCard.setUpdateBy(user.getId());
            classCard.setUpdateDate(System.currentTimeMillis());
            classCardService.updateClassCard(classCard);
        }
        return ResultEntity.newResultEntity();
    }

    //逻辑删除班牌
    @RequestMapping(value = "multiDelete")
    public ResultEntity multiDelete(HttpServletRequest request, Model model) {
        String classCardIds = getParamVal(request, "classCardIds");
        String[] idsStr = classCardIds.split(",");

        int count = classCardService.deleteClassCard(Arrays.asList(idsStr));
        ResultEntity resultEntity = new ResultEntity();
        if (count > 0) {
            resultEntity = ResultEntity.newResultEntity(ResultEntity.OK_CODE, "删除成功", null);
        } else {
            resultEntity = ResultEntity.newErrEntity();
        }
        return resultEntity;
    }

    /**
     * 通知公告首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/notify/index")
    public String classCardNotifyIndex(HttpServletRequest request, Model model) {

        String title = getParamVal(request, "title");
        String type = getParamVal(request, "type");
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<ClassCardNotifyView> pageInfo = classCardNotifyService.findAllNotify(title, "".equals(type) ? -1 : Integer.parseInt(type), pageNum, pageSize);
        List<ClassCardNotifyView> resultList = classCardNotifyService.transforNotifyView(pageInfo.getList());
        pageInfo.setList(resultList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("title", title);
        model.addAttribute("type", type);

        return "classCard/notifyIndex";
    }

    @RequestMapping(value = "/notify/edit")
    public String notifyEdit(HttpServletRequest request, Model model) {
        String disabled = getParamVal(request, "disabled");
        String id = getParamVal(request, "id");
        if (!"".equals(id)) {
            ClassCardNotify classCardNotify = classCardNotifyService.findById(id);
            model.addAttribute("classCardNotify", classCardNotify);
            List<ClassCardNotifyRef> classCardNotifyRefs = classCardNotifyRefService.findAllByNotifyId(id);
            String checkedIds = "";
            if (classCardNotifyRefs != null && classCardNotifyRefs.size() > 0) {
                for (ClassCardNotifyRef ref : classCardNotifyRefs) {
                    checkedIds += ref.getClassCardId() + ",";
                }
            }
            model.addAttribute("checkedIds", checkedIds);
        }
        model.addAttribute("disabled", disabled);
        return "classCard/notifyedit";
    }

    @ResponseBody
    @RequestMapping(value = "/notify/save")
    public ResultEntity saveClassCardNotify(HttpServletRequest request, Model model) {
        String title = getParamVal(request, "title");
        String type = getParamVal(request, "type");
        String content = getParamVal(request, "content");

        ResultEntity resultEntity = ResultEntity.newErrEntity();
        if ("".equals(title) || "".equals(type) || "".equals(content)) {
            return resultEntity;
        }
        String refId = PrimaryKey.get();
        ClassCardNotify classCardNotify = new ClassCardNotify();
        classCardNotify.setId(refId);
        classCardNotify.setTitle(title);
        classCardNotify.setType(Integer.parseInt(type));
        classCardNotify.setContent(content);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        classCardNotify.setCreateBy(user.getId());
        classCardNotify.setCreateDate(System.currentTimeMillis());

        int count = classCardNotifyService.insertClassCardNotify(classCardNotify);
        String checkedIds = getParamVal(request, "checkedIds");
        boolean flag = classCardNotifyRefService.insertClassCardNotifyRef(checkedIds, refId, user.getSchoolId(), user.getId());
        //String unCheckedIds=getParamVal(request,"unCheckedIds");
        //classCardNotifyRefService.deleteClassCardNotifyRef(unCheckedIds,"");
        //classCardNotifyRefService.insertClassCardNotifyRef()

        if (count != 0 && flag) {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/notify/multiDelete")
    public ResultEntity deleteNotify(HttpServletRequest request, Model model) {
        String notifyId = getParamVal(request, "notifyId");
        ResultEntity resultEntity=ResultEntity.newErrEntity();
        if (!"".equals(notifyId)) {
            int notifyCount = 0;
            int refCount = 0;
            String[] notifyIdArr = notifyId.split(",");
            if (notifyIdArr != null && notifyIdArr.length > 0) {
                notifyCount = classCardNotifyService.deleteClassCardNotify(notifyIdArr);
                refCount = classCardNotifyRefService.deleteRefByNotifyId(notifyIdArr);
                if(notifyCount==notifyIdArr.length){
                    resultEntity=ResultEntity.newResultEntity();
                }
            }
        }
        return resultEntity;
    }

    //发布通知选择设备
    @RequestMapping(value = "/chooseClassCard")
    public String chooseClassCard(HttpServletRequest request, Model model) {
        String checkedIds = getParamVal(request, "checkedIds");
        String disabled = getParamVal(request, "disabled");

        Map<String, Map<String, List<ClassCardView>>> resultMap = classCardService.selectEquipmentForNotify();
        JsonObject returnData = new JsonParser().parse(new Gson().toJson(resultMap)).getAsJsonObject();
        System.out.println(returnData);
        model.addAttribute("returnData", returnData);
        model.addAttribute("checkedIds", checkedIds);
        model.addAttribute("disabled", disabled);
        return "classCard/chooseclasscard";
    }

    /**
     * 班牌模式首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/modeType/index")
    public String classCardModeIndex(HttpServletRequest request, Model model) {
        PageInfo<ClassCardMode> pageInfo = classCardService.selectAllUnDelMode();
        model.addAttribute("pageInfo", pageInfo);
        return null;
    }

    /**
     * 编辑模式
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/modeType/edit")
    public String editClassCardMode(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        ClassCardMode classCardMode = classCardService.selectModeById(id);
        model.addAttribute("classCardMode", classCardMode);
        return null;
    }

    /**
     * 班牌模式保存
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modeType/save")
    public ResultEntity saveClassCardMode(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String title = getParamVal(request, "title");
        String type = getParamVal(request, "type");
        String time_start = getParamVal(request, "time_start");
        String time_end = getParamVal(request, "time_end");
        String content = getParamVal(request, "content");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        ClassCardMode classCardMode = new ClassCardMode();
        classCardMode.setContent(content);
        classCardMode.setId(id);
        classCardMode.setTitle(title);
        classCardMode.setType(null);
        classCardMode.setTimeStart(null);
        classCardMode.setTimeEnd(null);
        classCardMode.setSchoolId(user.getSchoolId());

        if (!"".equals(id)) {
            classCardMode.setUpdateBy(user.getId());
            classCardMode.setUpdateDate(System.currentTimeMillis());
            classCardService.updateMode(classCardMode);
        } else {
            classCardMode.setId(PrimaryKey.get());
            classCardMode.setCreateBy(user.getId());
            classCardMode.setCreateDate(System.currentTimeMillis());
            classCardService.insertMode(classCardMode);
        }

        return ResultEntity.newResultEntity();
    }

    /**
     * 逻辑删除班牌模式
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modeType/delete")
    public ResultEntity deleteMode(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        if (!"".equals(id)) {
            ClassCardMode classCardMode = classCardService.selectModeById(id);
            classCardMode.setDelFlag(1);
            classCardService.updateMode(classCardMode);
        }
        return null;

    }

}
