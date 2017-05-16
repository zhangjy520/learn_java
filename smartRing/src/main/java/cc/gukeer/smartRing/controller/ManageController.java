package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.GsonUtil;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.common.utils.RegexpUtil;
import cc.gukeer.common.utils.excel.ExportExcel;
import cc.gukeer.common.utils.excel.ImportExcel;
import cc.gukeer.smartRing.common.RStatusType;
import cc.gukeer.smartRing.modelView.CascadingView;
import cc.gukeer.smartRing.modelView.importExport.ImportBundling;
import cc.gukeer.smartRing.modelView.importExport.ImportStation;
import cc.gukeer.smartRing.modelView.importExport.ImportTempRing;
import cc.gukeer.smartRing.modelView.importExport.ImportUser;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.persistence.entity.extension.ExtensionTeacher;
import cc.gukeer.smartRing.persistence.entity.extension.RingView;
import cc.gukeer.smartRing.persistence.entity.extension.RoleView;
import cc.gukeer.smartRing.persistence.entity.extension.StationView;
import cc.gukeer.smartRing.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/manage")
public class ManageController extends BasicController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    StudentService studentService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;

    /**
     * 手环管理
     *
     * @param request
     * @param model
     * @return
     */
//    @RequestMapping(value = "/ring/index", method = RequestMethod.GET)
    public String ringGet(HttpServletRequest request, Model model) {
        String search = getParamVal(request, "search");
        String lostSearch = getParamVal(request, "lostSearch");
        String _usingSize = getParamVal(request, "usingSize");
        String _usingNum = getParamVal(request, "usingNum");
        String _lostSize = getParamVal(request, "lostSize");
        String _lostNum = getParamVal(request, "lostNum");
        String which = getParamVal(request, "which");

        try {
            search = java.net.URLDecoder.decode(search, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            lostSearch = java.net.URLDecoder.decode(lostSearch, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int usingSize = NumberConvertUtil.convertS2I(_usingSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_usingSize);
        int usingNum = NumberConvertUtil.convertS2I(_usingNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_usingNum);
        int lostSize = NumberConvertUtil.convertS2I(_lostSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_lostSize);
        int lostNum = NumberConvertUtil.convertS2I(_lostNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_lostNum);

        PageHelper.startPage(usingNum, usingSize);
        Page<RingView> usingPage = (Page<RingView>) deviceService.findAllWhoUsedBySchoolId(getLoginUser().getSchoolId(), "%" + search + "%", RStatusType.STATUS_USED, null); //参数（学校，姓名/学号，状态，类型） LEFT JOIN 会查到没有关联学生的手环数据
        PageInfo<RingView> using = new PageInfo<RingView>(usingPage);

        PageHelper.startPage(lostNum, lostSize);
        Page<RingView> lostPage = (Page<RingView>) deviceService.findAllWhoUsedBySchoolId(getLoginUser().getSchoolId(), "%" + lostSearch + "%", RStatusType.STATUS_LOSING, null);
        PageInfo<RingView> lost = new PageInfo<RingView>(lostPage);

        model.addAttribute("which", which);
        model.addAttribute("lostSearch", lostSearch);
        model.addAttribute("search", search);
        model.addAttribute("using", using);
        model.addAttribute("lost", lost);
        return "/manage/ringmanage";
    }

    /**
     * 手环管理
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("ring:manage:index")
    @RequestMapping(value = "/ring/index", method = RequestMethod.GET)
    public String ringGet1(HttpServletRequest request, Model model) {
        String search = getParamVal(request, "search");
        String _pageSize = getParamVal( request, "pageSize");
        String _pageNum = getParamVal( request, "pageNum");
        String _type = getParamVal( request, "type");
        String _status = getParamVal( request, "status");

        try {
            if (("undefined").equals(search)) {
                search = "";
            }
            search = java.net.URLDecoder.decode(search, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);
        Integer type = ("").equals(_type) ? null:NumberConvertUtil.convertS2I( _type);
        Integer status = ("").equals(_status) ? null:NumberConvertUtil.convertS2I( _status);

        PageInfo<RingView> ringViewPage = deviceService.findRingBySchoolId( getLoginUser().getSchoolId(),
                "%" + search + "%", status, type, pageNum, pageSize);//参数（学校，姓名/学号，状态，类型） LEFT JOIN 会查到没有关联学生的手环数据

        model.addAttribute("search", search);
        model.addAttribute( "status", status);
        model.addAttribute( "type", type);
        model.addAttribute("ringViewPage", ringViewPage);
        return "/manage/ringmanage1";
    }

    @ResponseBody
    @RequestMapping( value = "/ring/del/{id}", method = RequestMethod.POST)
    public ResultEntity delRing( @PathVariable String id){
        try {
            DeviceRing ring = new DeviceRing();
            ring.setId( id);
            ring.setDelFlag(1);
            deviceService.saveRing( ring);
            return ResultEntity.newResultEntity("删除成功");
        } catch ( Exception e){
            e.printStackTrace();
            return ResultEntity.newErrEntity("删除失败");
        }

    }

    /**
     * 手环-导入页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/importring", method = RequestMethod.GET)
    public String moBanImport(HttpServletRequest request, Model model) {
        return "/manage/importring";
    }

    /**
     * 手环模板下载
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/download/ring")
    public String studentTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "手环导入模板.xlsx";
            List<Student> list = Lists.newArrayList();
            new ExportExcel("手环数据", ImportTempRing.class, 1, "", 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 手环-导入功能
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/import/ring", method = RequestMethod.POST)
    public ResultEntity classImport(@RequestParam(value = "file") MultipartFile file) {
        try {
            ImportExcel importExcel = new ImportExcel( file, 1, 0);
            List<ImportTempRing> ringList = importExcel.getDataList( ImportTempRing.class,1);
            if (ringList.size() ==0) {
                return ResultEntity.newErrEntity(-2,"模板异常");
            }
            int count  = 0;//总数
            int errorCount = 0;//错误数
            int trueCount = 0;//正确数
            List<ImportTempRing> error = new ArrayList<ImportTempRing>();

            for (ImportTempRing ring:ringList) {
                if ( null == ring.getMac()) {
                    ring.setMsg( "第"+(count+1)+"个：MAC地址为空");
                    error.add(ring);
                    count += 1;
                    errorCount += 1;
                    continue;
                }
                if ( !RegexpUtil.isMacFormat(ring.getMac())){
                    ring.setMsg( "第"+(count+1)+"个数据：MAC地址格式错误");
                    error.add(ring);
                    count += 1;
                    errorCount += 1;
                    continue;
                }
                if ( null != deviceService.findRingByMac( ring.getMac())) {
                    ring.setMsg( "第"+(count+1)+"个数据：已存在该MAC地址");
                    error.add(ring);
                    count += 1;
                    errorCount += 1;
                    continue;
                }
                DeviceRing deviceRing = new DeviceRing();
                deviceRing.setMac( ring.getMac());
                deviceRing.setSchoolId( getLoginUser().getSchoolId());
                deviceRing.setCreateBy( getLoginUser().getId());
                deviceRing.setCreateDate( System.currentTimeMillis());
                deviceService.saveRing( deviceRing);
                trueCount += 1;
                count += 1;
            }
            if (errorCount == 0) {
                return ResultEntity.newResultEntity( "手环数据导入成功");
            }
            return ResultEntity.newResultEntity( -1,String.valueOf(trueCount),error);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newResultEntity("文档格式有误",null);
        }
    }

    /**
     * 下载手环错误列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ring/errorring", method = RequestMethod.POST)
    public String errotemp(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<ImportTempRing> exportFile = new ArrayList<ImportTempRing>();
            for (JsonElement jsonElement : jsonArray) {
                ImportTempRing importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), ImportTempRing.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("导入失败信息", "", ImportTempRing.class).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转作临时手环
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ring/transformtemp/{id}")
    public ResultEntity transformTemp( @PathVariable String id){
        try {
            DeviceRing ring = new DeviceRing();
            ring.setId( id);
            ring.setType( RStatusType.TYPE_TEMP);
            deviceService.saveRing( ring);
            return ResultEntity.newResultEntity("转为临时手环");
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("操作失败");
        }

    }

    /**
     * 手环挂失
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/ring/loss/{id}", method = RequestMethod.POST)
    public ResultEntity ringLoss(@PathVariable String id) {
        try {
            DeviceRing ring = deviceService.findRingById( id);
            ring.setStatus( RStatusType.STATUS_LOSING);
            deviceService.saveRing( ring);
            return ResultEntity.newResultEntity("挂失成功，请绑定临时手环或个人手环");
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("挂失失败");
        }

    }

    /**
     * 1.个人手环解除挂失查看临时手环页面
     * 2.绑定个人手环前查看临时手环页面
     * @param ringId
     * @param model
     * @return
     */
    @RequestMapping( value = "/ring/releaseloss/{id}/{xh}")
    public String ringReportLossjsp(@PathVariable("id") String ringId, @PathVariable String xh, Model model){
        Student student = studentService.selectByXh( getLoginUser().getSchoolId(), xh);
        if ( null != student) {
            GradeClass gradeClass = classService.findClassById( student.getClassId());
            String grade = ConstantUtil.getValueByKeyAndFlag( Integer.valueOf(student.getXd()), "xd")
                    +ConstantUtil.getValueByKeyAndFlag( student.getNj(), "nj")+gradeClass.getName();

            DeviceRingExample example = new DeviceRingExample();
            example.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo( getLoginUser().getSchoolId()).andStudentIdEqualTo( student.getId());
            List<DeviceRing> ringList = deviceService.findRingsByExample( example);
            DeviceRing ring = deviceService.findRingById( ringId);
            model.addAttribute( "ringList", ringList);
            model.addAttribute( "stName", student.getXsxm());
            model.addAttribute( "grade", grade);
            model.addAttribute( "xh", student.getXh());
            model.addAttribute( "ringId", ringId);
            model.addAttribute( "studentId", student.getId());
            model.addAttribute( "type", ring.getType());
            return "/manage/releaselose";
        } else {
            return "/manage/errorring";
        }

    }

    /**
     * 手环解除挂失(个人、临时)
     * 绑定个人手环
     * @param ringId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/ring/release/loss/{id}/{type}")
    public ResultEntity saveRingReportLoss( @PathVariable("id") String ringId, @PathVariable Integer type, HttpServletRequest request){
        try {
            if ( type == RStatusType.TYPE_TEMP){
                //临时手环接触绑定后变为空闲手环
                DeviceRing ring = new DeviceRing();
                ring.setId( ringId);
                ring.setStatus( RStatusType.STATUS_UNUSED);
                ring.setStudentId( "");
                deviceService.saveRing( ring);
                return ResultEntity.newResultEntity("解除成功");
            } else {
                //个人手环解除绑定后状态变为使用中
                //绑定个人手环后类型变为个人，状态变为使用中
                String studentId = request.getParameter("studentId");
                String temp = request.getParameter( "temp");
                String[] tempRings = temp.split(",");
                if ( tempRings.length > 1){
                    for (int i=0;i<tempRings.length;i=i+2){
                        DeviceRing ring = new DeviceRing();
                        ring.setId( tempRings[i]);
                        ring.setStatus( Integer.valueOf(tempRings[i+1]));
                        if ( ring.getStatus()==RStatusType.STATUS_UNUSED){
                            //归还
                            ring.setStudentId( "");
                            deviceService.saveRing( ring);
                        } else {
                            //丢失
                            deviceService.saveRing( ring);
                        }
                    }
                }
                //判断该学生其他手环的状态
                DeviceRingExample example = new DeviceRingExample();
                example.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo( getLoginUser().getSchoolId()).andStudentIdEqualTo( studentId)
                        .andStatusEqualTo( RStatusType.STATUS_USED);
                List<DeviceRing> ringList = deviceService.findRingsByExample( example);
                if ( ringList.size()==0){
                    if ( type == RStatusType.TYPE_PERSONAL) {
                        //个人手环 解除挂失
                        DeviceRing ring = deviceService.findRingById( ringId);
                        ring.setStatus( RStatusType.STATUS_USED);
                        deviceService.saveRing( ring);
                        return ResultEntity.newResultEntity("解除成功");
                    } else {
                        //将挂失的个人手环设置为已丢失
                        DeviceRingExample deviceRingExample = new DeviceRingExample();
                        deviceRingExample.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo( getLoginUser().getSchoolId())
                                .andStudentIdEqualTo( studentId).andTypeEqualTo( RStatusType.TYPE_PERSONAL).andStatusEqualTo( RStatusType.STATUS_LOSING);
                        List< DeviceRing> deviceRingList = deviceService.findRingsByExample( deviceRingExample);
                        if ( deviceRingList.size() == 1){
                            DeviceRing ring1 = new DeviceRing();
                            ring1.setId( deviceRingList.get(0).getId());
                            ring1.setStatus( RStatusType.STATUS_LOST);
                            deviceService.saveRing( ring1);
                        }
                        //绑定个人手环
                        DeviceRing ring = deviceService.findRingById( ringId);
                        ring.setStudentId( studentId);
                        ring.setType( RStatusType.TYPE_PERSONAL);
                        ring.setStatus( RStatusType.STATUS_USED);
                        deviceService.saveRing( ring);

                        return ResultEntity.newResultEntity("绑定成功");
                    }

                } else {
                    return ResultEntity.newErrEntity("该学生有其他手环正在使用中，不能绑定个人手环");
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
            return  ResultEntity.newErrEntity( "操作失败");
        }
    }

    /**
     * 绑定临时手环
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/ring/binding/temp/{id}/{xh}", method = RequestMethod.POST)
    public ResultEntity ringBinding( @PathVariable String id, @PathVariable String xh, HttpServletRequest request) {
        try {
            String type = request.getParameter("type");
            Student student = studentService.selectByXh( getLoginUser().getSchoolId(), xh);
            if ( null != student) {
                if (Integer.valueOf(type) == RStatusType.TYPE_TEMP){
                    DeviceRingExample example = new DeviceRingExample();
                    example.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo( getLoginUser().getSchoolId())
                            .andStudentIdEqualTo( student.getId()).andStatusEqualTo( RStatusType.STATUS_USED);
                    List<DeviceRing> ringList = deviceService.findRingsByExample( example);
                    if ( ringList.size() == 0){
                        //绑定临时手环
                        DeviceRing ring = new DeviceRing();
                        ring.setId( id);
                        ring.setStudentId( student.getId());
                        ring.setStatus( RStatusType.STATUS_USED);
                        deviceService.saveRing( ring);
                        return ResultEntity.newResultEntity("绑定成功");
                    } else {
                        return ResultEntity.newErrEntity("该学生有其他手环正在使用中，不能绑定临时手环");
                    }
                }else {
                    return ResultEntity.newResultEntity();
                }
            } else {
                return ResultEntity.newErrEntity("没有该学生学号");
            }
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("绑定失败");
        }
    }


    /**
     * 手环绑定
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ring/bundling", method = RequestMethod.POST)
    public ResultEntity ringBunling(HttpServletRequest request, Model model) {
        String mac = getParamVal(request, "mac");
        String xh = getParamVal(request, "xh");
        String _type = getParamVal(request, "type");
        int type = NumberConvertUtil.convertS2I(_type);

        // 设备状态[0:空闲, 1使用中, 2:挂失中, 3:已丢失] // 原始：0:使用中;1:挂失中;2:已丢失;3:空闲]
        // 当前如果没有绑定过手环，直接绑定
        // 如果已经佩戴个人手环，再次绑定个人手环，原手环已丢失，绑定成功。如果绑定临时手环，必须先将手置为挂失状态，才能继续绑定
        // 如果已经佩戴临时手环，再次绑定个人手环，原个人手环已丢失，对话框确定原所有临时手环的状态,绑定成功。如果绑定临时手环，原临时手环挂失状态，绑定成功。
        int flag = 0;
        List<DeviceRing> tmpFreeRing = deviceService.findRingByMacBySchoolId(mac, getLoginUser().getSchoolId(), RStatusType.TYPE_TEMP, RStatusType.STATUS_UNUSED);
        List<DeviceRing> lostRing = deviceService.findRingByMacBySchoolId(mac, getLoginUser().getSchoolId(), null, RStatusType.STATUS_LOST);
        if (type == RStatusType.TYPE_PERSONAL) {  //绑定个人手环
            List<DeviceRing> existRing = deviceService.findRingByMacBySchoolId(mac, getLoginUser().getSchoolId(), null, null);
            if (existRing.size() != 0) {
                if (lostRing.size() == 0) {
                    return ResultEntity.newErrEntity(-1, "绑定失败，请先挂失原来的个人手环。"); //存在且未丢失，个人手环不能绑定未丢失的临时手环
                }
            }
        }

        if (type == RStatusType.TYPE_TEMP) {  //绑定临时手环
//            List<DeviceRing> existRing = deviceService.findRingByMacNotLostNotFree(mac, getLoginUser().getSchoolId(), null);  //学校存在该手环，无论个人临时，必须为丢失或者空闲（如果存在的是个人手环，可以转换为临时手环）
            if (tmpFreeRing.size() == 0) { //不是空闲临时手环，且不是挂失的手环
                return ResultEntity.newErrEntity(-1, "绑定失败，该临时手环库存不足。");
            }
        }

        Student student = studentService.selectByXh(getLoginUser().getSchoolId(), xh);
        if (student == null) {
            return ResultEntity.newErrEntity(-1, "未找到学生或学号出现重复");
        }

        List<DeviceRing> usingTempRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_TEMP, RStatusType.STATUS_USED); //正在使用的临时手环-提醒
        List<DeviceRing> usingPersonalRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_PERSONAL, RStatusType.STATUS_USED); //正在使用的个人手环-失败/已挂失
        if (type == RStatusType.TYPE_PERSONAL) {//只有绑定个人手环弹提示框
            List<DeviceRing> lossingTempRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_TEMP, RStatusType.STATUS_LOSING);  //有已挂失的临时手环-提醒处理
            if (lossingTempRing.size() > 0 || usingTempRing.size() > 0) {
                return ResultEntity.newErrEntity(-2, "提示");
            }

            // 正在使用的个人手环 置为已丢失
            if (usingPersonalRing.size() > 0) {
                for (DeviceRing ring : usingPersonalRing) {
                    if (type == RStatusType.TYPE_PERSONAL) {
                        ring.setStatus(RStatusType.STATUS_LOST);
                    }
                    ring.setUpdateBy(getLoginUser().getId());
                    ring.setUpdateDate(System.currentTimeMillis());
                    deviceService.saveRing(ring);
                }
                flag = 1;
            }
            // 已挂失的个人手环-置于已丢失
            List<DeviceRing> lossingPersonalRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_PERSONAL, RStatusType.STATUS_LOSING);
            if (lossingPersonalRing.size() > 0) {
                for (DeviceRing ring : lossingPersonalRing) {
                    ring.setStatus(RStatusType.STATUS_LOST);
                    ring.setUpdateBy(getLoginUser().getId());
                    ring.setUpdateDate(System.currentTimeMillis());
                    deviceService.saveRing(ring);
                }
                flag = 1;
            }
        }

        if (type == RStatusType.TYPE_TEMP) {
            if (usingPersonalRing.size() > 0) {
                return ResultEntity.newResultEntity("绑定失败，该学生正在使用个人手环中", null);
            }
            // 绑定临时手环，将现在的临时手环置为已挂失
            if (usingTempRing.size() > 0) {
                for (DeviceRing ring : usingTempRing) {
                    ring.setStatus(RStatusType.STATUS_LOSING);
                    ring.setUpdateDate(System.currentTimeMillis());
                    ring.setUpdateBy(getLoginUser().getId());
                    deviceService.saveRing(ring);
                    flag = 2;
                }
            }
        }

        //如果这个手环是已丢失的，转换为使用中。可能出现重复的MAC
        if (lostRing.size() != 0) {
            DeviceRing saveRing = lostRing.get(0);
            saveRing.setStudentId(student.getId());
            saveRing.setStatus(RStatusType.STATUS_USED);
            saveRing.setType(type);
            saveRing.setUpdateBy(getLoginUser().getId());
            saveRing.setUpdateDate(System.currentTimeMillis());
            deviceService.saveRing(saveRing);
        } else {
            // 临时手环
            if (type == RStatusType.TYPE_TEMP) { //之前判断了，此处空闲手环必定有
                boolean temp = false;
                for (DeviceRing ring : tmpFreeRing) {
                    if (ring.getMac().equals(mac)) {
                        ring.setStudentId(student.getId());
                        ring.setStatus(RStatusType.STATUS_USED);
                        ring.setType(type);
                        ring.setUpdateBy(getLoginUser().getId());
                        ring.setUpdateDate(System.currentTimeMillis());
                        deviceService.saveRing(ring);
                        temp = true;
                        break;
                    }
                }
                if (temp == false) {
                    return ResultEntity.newErrEntity(-1, "出现错误，请更换手环编号");
                }
            } else {  // 個人手环
                DeviceRing newRing = new DeviceRing();
                newRing.setMac(mac);
                newRing.setStudentId(student.getId());
                newRing.setSchoolId(getLoginUser().getSchoolId());
                newRing.setStatus(RStatusType.STATUS_USED);
                newRing.setType(type);
                newRing.setDelFlag(0);
                newRing.setCreateBy(getLoginUser().getId());
                newRing.setCreateDate(System.currentTimeMillis());
                deviceService.saveRing(newRing);
            }
        }
        if (flag == 1) {
            return ResultEntity.newResultEntity("绑定成功，原手环不能使用", null);
        } else if (flag == 2) {
            return ResultEntity.newResultEntity("绑定成功，已挂失原使用中临时手环", null);
        } else {
            return ResultEntity.newResultEntity("绑定成功", null);
        }

    }

    /**
     * 手环挂失
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ring/report", method = RequestMethod.POST)
    public ResultEntity report(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String _targetStatus = getParamVal(request, "status");
        Integer targetStatus = NumberConvertUtil.convertS2I(_targetStatus);
        int count = deviceService.report(id, targetStatus, getLoginUser().getId());
        if (count != -1 && count != 1) {
            return ResultEntity.newErrEntity(-1, "操作失败");
        }
        if (count == -1) {
            DeviceRing ring = deviceService.findRingById(id);
            Student student = studentService.selectByPrimaryKey(ring.getStudentId());
            return ResultEntity.newErrEntity(-1, student.getXh());
        }
        return ResultEntity.newResultEntity("操作成功", null);
    }

    /**
     * 扫描查询
     * @param request
     * @param mac
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bound/{mac}", method = RequestMethod.GET)
    public ResultEntity beforeBind(HttpServletRequest request, @PathVariable String mac) {

        String schoolId = getParamVal(request, "schoolId");
        String classId = getParamVal(request, "classId");

        schoolId = "2";
        classId = "2";
        if (StringUtils.isBlank(schoolId) || StringUtils.isBlank(classId)) {
            return ResultEntity.newErrEntity("参数非法");
        }

        if (StringUtils.isBlank(mac)) {
            return ResultEntity.newErrEntity("mac 地址非法");
        }

        List<Map<String, String>> list = studentService.selectNoBoundStudentBySchoolIdAndClassId(schoolId, classId);
        if (null == list || list.isEmpty()) {
            return ResultEntity.newErrEntity("所有学生已绑定手环，如需绑定临时手环，请在网站后台操作！");
        }
        Map<String, String> map = list.get(0);

        Map<String, String> rstMap = new HashMap<String, String>();
        rstMap.put("mac", mac);
        rstMap.put("studentId", map.get("student_id"));
        rstMap.put("studentName", map.get("student_name"));
        rstMap.put("grade", map.get("section_name"));
        rstMap.put("nj", map.get("nj"));
        rstMap.put("class", map.get("class_name"));

        return ResultEntity.newResultEntity(rstMap);
    }

    /**
     * 扫描绑定
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bound", method = RequestMethod.POST)
    public ResultEntity doBind(HttpServletRequest request) {

        String studentId = getParamVal(request, "studentId");
        String mac = getParamVal(request, "mac");

        DeviceRing ring = deviceService.findRingByMac(mac);
        if (null != ring) {
            return ResultEntity.newErrEntity("此手环已经使用!");
        }

        Student student = studentService.selectByPrimaryKey(studentId);

        if (null == student) {
            return ResultEntity.newErrEntity("学生ID错误！");
        }

        DeviceRing newRing = new DeviceRing();
        newRing.setMac(mac);
        newRing.setStudentId(studentId);
        newRing.setSchoolId(student.getSchoolId());
        newRing.setStatus(RStatusType.STATUS_USED);
        newRing.setType(RStatusType.TYPE_PERSONAL);
        newRing.setCreateBy("client_bing_opt_id");
        newRing.setCreateDate(System.currentTimeMillis());
        deviceService.saveRing(newRing);

        return ResultEntity.newResultEntity();
    }

    /**
     * 绑定的提示框
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/ring/erromsg")
    public String erromsg(HttpServletRequest request, Model model) {
        String xh = getParamVal(request, "xh");
        Student student = studentService.selectByXh(getLoginUser().getSchoolId(), xh);
        List<DeviceRing> lossingTempRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_TEMP, RStatusType.STATUS_LOSING);  //有已挂失的临时手环-提醒处理
        List<DeviceRing> usingTempRing = deviceService.findRingByStudentId(student.getId(), RStatusType.TYPE_TEMP, RStatusType.STATUS_USED); //正在使用的临时手环-提醒
        List<DeviceRing> finallist = new ArrayList<DeviceRing>();
        if (lossingTempRing.size() > 0) {
            finallist.addAll(lossingTempRing);
        }
        if (usingTempRing.size() > 0) {
            finallist.addAll(usingTempRing);
        }
        model.addAttribute("student", student);
        model.addAttribute("deviceRing", finallist);
        return "/manage/erroMsg";
    }


    /**
     * 批量绑定页面
     *
     * @return
     */
    @RequestMapping(value = "/multibundling")
    public String multiBundling() {
        return "/manage/multibundling";
    }

    /**
     * 批量绑定模板下载
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/bundlingtemplate")
    public String bundlingTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "绑定手环模板.xlsx";
            List<Student> list = Lists.newArrayList();
            new ExportExcel("绑定信息", ImportBundling.class, RStatusType.TYPE_TEMP, "", 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 批量绑定
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/multibundling/save")
    public ResultEntity multiBd(@RequestParam(value = "file") MultipartFile file, RedirectAttributes
            redirectAttributes) {
        try {
            ImportExcel importExcel = new ImportExcel(file, 1, 0);  //从第一行开始
            List<ImportBundling> importBundlings = importExcel.getDataList(ImportBundling.class, 1);
            if (importBundlings.size() == 0) {
                return ResultEntity.newResultEntity("请导入正确文件",null);
            }
            int count = 0;
            String errorMsg = "";
            int errorCount = 0;
            List<ImportBundling> erro = new ArrayList<ImportBundling>();
            for (ImportBundling message : importBundlings) {
                String mac = message.getMac();
                if (mac == null) {
                    message.setMsg("手环编号未填写");
                    erro.add(message);
                    errorCount += 1;
                    continue;
                }
                String xh = message.getXh();
                if (xh == null) {
                    message.setMsg("学号未填写");
                    erro.add(message);
                    errorCount += 1;
                    continue;
                }
                Student student = studentService.selectByXh(getLoginUser().getSchoolId(), xh);
                if (student != null) {
                    //判断学生是否使用手环中
                    List<DeviceRing> usingRings = deviceService.findRingByStudentId(student.getId(), null, RStatusType.STATUS_USED);
                    List<DeviceRing> lossingRings = deviceService.findRingByStudentId(student.getId(), null, RStatusType.STATUS_LOSING);
                    if (usingRings.size() > 0 || lossingRings.size() > 0) {
                        message.setMsg("该学生正在使用手环");
                        erro.add(message);
                        errorCount += 1;
                        continue;
                    }

                    //判断手环是否存在且未丢失
                    List<String> temp = new ArrayList<String>();
                    temp.add(mac);
                    List<DeviceRing> existRings = deviceService.findRingByMacsNotLost(temp, getLoginUser().getSchoolId());
                    if (existRings.size() > 0) {
                        message.setMsg("已存在该手环且未丢失");
                        erro.add(message);
                        errorCount += 1;
                        continue;
                    }

                    //成功
                    DeviceRing newRing = new DeviceRing();
                    newRing.setMac(mac);
                    newRing.setStudentId(student.getId());
                    newRing.setSchoolId(getLoginUser().getSchoolId());
                    newRing.setStatus(RStatusType.STATUS_USED);
                    newRing.setType(RStatusType.TYPE_PERSONAL);
                    newRing.setDelFlag(0);
                    newRing.setCreateBy(getLoginUser().getId());
                    newRing.setCreateDate(System.currentTimeMillis());
                    deviceService.saveRing(newRing);
                    count += 1;
                } else {
                    message.setMsg("未查询到该学生");
                    erro.add(message);
                    errorCount += 1;
                }
            }
            if (errorCount == 0) {
                return ResultEntity.newResultEntity("绑定成功：" + count + "位学生", null);
            }
            return ResultEntity.newResultEntity(-1, String.valueOf(count), erro);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newResultEntity("文档格式有误",null);
        }
    }

    /**
     * 下载绑定错误列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ring/erroexport", method = RequestMethod.POST)
    public String exportFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "绑定失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<ImportBundling> exportFile = new ArrayList<ImportBundling>();
            for (JsonElement jsonElement : jsonArray) {
                ImportBundling importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), ImportBundling.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("绑定信息", "", ImportBundling.class).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 再次绑定确定
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/ring/again", method = RequestMethod.POST)
    public String again(HttpServletRequest request, Model model) {
        String data = getParamVal(request, "data");
        JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
        for (JsonElement each : jsonArray) {
            JsonObject jsonObject = each.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            int targetStatus = jsonObject.get("target").getAsInt();
            deviceService.report(id, targetStatus, getLoginUser().getId());
        }
        String flag = getParamVal(request, "flag");
        if (flag != null && flag != "") {
            return "forward:/manage/ring/report";
        } else {
            return "forward:/manage/ring/bundling";
        }
    }


    /**
     * 确定信息页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/ring/confirm")
    public String confirm(HttpServletRequest request, Model model) {
        String studentId = getParamVal(request, "studentId");
        List<DeviceRing> tempRings = deviceService.findRingByStudentIdNotStatus(studentId, RStatusType.TYPE_TEMP, RStatusType.STATUS_LOST);
        model.addAttribute("tempRings", tempRings);
        return null;
    }

    /**
     * 临时手环
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/tempring/index")
    public String tempring(HttpServletRequest request, Model model) {
        String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);
        PageHelper.startPage(pageNum, pageSize);
        Page<RingView> page = (Page<RingView>) deviceService.findAllWhoUsedBySchoolId(getLoginUser().getSchoolId(), "%%", null, RStatusType.TYPE_TEMP);
        PageInfo<RingView> pageInfo = new PageInfo<RingView>(page);

        List<RingView> freeRing = deviceService.findAllWhoUsedBySchoolId(getLoginUser().getSchoolId(), "%%", RStatusType.STATUS_UNUSED, RStatusType.TYPE_TEMP);
        List<RingView> missingRing = deviceService.findAllWhoUsedBySchoolId(getLoginUser().getSchoolId(), "%%", RStatusType.STATUS_LOSING, RStatusType.TYPE_TEMP);

        model.addAttribute("missingRing", missingRing.size());
        model.addAttribute("freeRing", freeRing.size());
        model.addAttribute("pageInfo", pageInfo);
        return "/manage/tempring";
    }

    /**
     * 新增临时手环
     *
     * @return
     */
    @RequestMapping(value = "/tempring/add")
    public String openAdd() {
        return "/manage/addtemp";
    }

    /**
     * 新增临时手环
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tempring/save")
    public ResultEntity saveTemp(HttpServletRequest request) {
        String mac = getParamVal(request, "mac");
        DeviceRing ring = new DeviceRing();
        List<String> temp = new ArrayList<String>();
        temp.add(mac);
        List<DeviceRing> rings = deviceService.findRingByMacsNotLost(temp, getLoginUser().getSchoolId());
        if (rings.size() > 0) {
            return ResultEntity.newErrEntity("已存在该手环");
        }
        List<DeviceRing> lostRing = deviceService.findRingByMacBySchoolId(mac, getLoginUser().getSchoolId(), null, RStatusType.STATUS_LOST);
        if (lostRing.size() > 0) {
            ring = lostRing.get(0);
            ring.setStudentId(null);
            ring.setUpdateDate(System.currentTimeMillis());
            ring.setUpdateBy(getLoginUser().getId());
            ring.setType(RStatusType.TYPE_TEMP);
            ring.setStatus(RStatusType.STATUS_UNUSED);
        } else {
            ring.setType(RStatusType.TYPE_TEMP);
            ring.setStatus(RStatusType.STATUS_UNUSED);
            ring.setCreateBy(getLoginUser().getId());
            ring.setCreateDate(System.currentTimeMillis());
            ring.setDelFlag(0);
            ring.setMac(mac);
            ring.setSchoolId(getLoginUser().getSchoolId());
        }
        deviceService.saveRing(ring);
        return ResultEntity.newResultEntity("操作成功",null);
    }

    /**
     * 下载用户错误列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ring/errouser", method = RequestMethod.POST)
    public String errouser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<ImportUser> exportFile = new ArrayList<ImportUser>();
            for (JsonElement jsonElement : jsonArray) {
                ImportUser importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), ImportUser.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("绑定信息", "", ImportUser.class).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 角色管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/role/index")
    public String roleManege(HttpServletRequest request, Model model) {
        String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);

        PageInfo<RoleView> roleList = roleService.findRoleViewBySchoolId(getLoginUser().getSchoolId(), pageNum, pageSize);

        model.addAttribute("roleList", roleList);
        return "/manage/rolemanage";
    }

    /**
     * 添加角色
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/role/add")
    public String addRole(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        Role role = new Role();
        if (StringUtils.isNotBlank(id)) {
            role = roleService.findRoleById(id);
        }
        List<Menu> menuList = menuService.findAllMenu();

        List<RoleMenu> roleMenus = roleService.findRoleMenuList(id);

        List<String> menuId = new ArrayList<String>();
        for (RoleMenu roleMenu : roleMenus) {
            Menu menu = roleService.findMenuById(roleMenu.getMenuId());
            menuId.add(menu.getId().toString());
        }

        model.addAttribute("role", role);
        model.addAttribute("menuList", menuList);
        model.addAttribute("menuId", menuId);
        return "/manage/addrole";
    }

    /**
     * 保存角色
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/save")
    public ResultEntity saveRole(HttpServletRequest request, Model model) {
        String _menuIds = getParamVal(request, "menuId");
        String _range = getParamVal(request, "range");
        String roleName = getParamVal(request, "roleName");
        String roleId = getParamVal(request, "roleId");

        int range = NumberConvertUtil.convertS2I(_range);
        String[] _menuId = _menuIds.split(",");

        Role role = new Role();
        role.setPermissionRange(range);
        role.setName(roleName);
        if (StringUtils.isNotBlank(roleId)) {
            role.setId(roleId);
            role.setUpdateBy(getLoginUser().getId());
            role.setUpdateDate(System.currentTimeMillis());
            roleService.updateRole(role);
        } else {
            Role existRoles =  roleService.findRoleByName(getLoginUser().getSchoolId(),roleName);
            if(existRoles != null){
                return ResultEntity.newResultEntity("已存在该角色名称", null);
            }
            role.setSchoolId(getLoginUser().getSchoolId());
            role.setCreateBy(getLoginUser().getId());
            role.setCreateDate(System.currentTimeMillis());
            role.setId( ConstantUtil.getPrimaryKey());
            roleService.insertRole( role);
            roleId = role.getId();
        }
        roleService.deleteRoleMenuByRole(roleId);
        if (_menuId.length > 0) {
            for (int i = 0; i < _menuId.length; i++) {
                roleService.assignRoleMenu(roleId, _menuId[i]);
            }
        }
        return ResultEntity.newResultEntity("保存成功", null);
    }

    /**
     * 删除角色
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/delete")
    public ResultEntity deleteRole(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        Role role = new Role();
        role.setId(id);
        role.setDelFlag(1);
        roleService.updateRole(role);
        return ResultEntity.newResultEntity("删除成功", null);
    }

    /**
     * 用户管理
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/index")
    public String userIndex(HttpServletRequest request, Model model) {
        String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        String search = getParamVal(request, "search");
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);

        PageHelper.startPage(pageNum, pageSize);
        Page<ExtensionTeacher> page = (Page<ExtensionTeacher>) roleService.findTeacherUser(getLoginUser().getSchoolId(), "%" + search + "%");
        PageInfo<ExtensionTeacher> pageInfo = new PageInfo(page);
        model.addAttribute("pageInfo", pageInfo);
        return "/manage/usermanage";
    }

    /**
     * 删除用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public ResultEntity deleteUser(HttpServletRequest request) {
        String userId = getParamVal(request, "userId");
        String roleId = getParamVal(request, "roleId");
        int count = roleService.deleteUserRole(userId, roleId);
        if (count > 0) return ResultEntity.newResultEntity("删除成功", null);
        else return ResultEntity.newErrEntity("删除失败");
    }

    /**
     * 添加用户
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/add")
    public String addUser(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        String teacherNo = getParamVal(request, "teacherNo");
        String teacherId = getParamVal(request, "teacherId");

        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());
        List<RoleView> roleList = roleService.findRoleViewBySchoolId(getLoginUser().getSchoolId());

        if (StringUtils.isNotBlank(roleId)) {
            Role role = roleService.findRoleById(roleId);
            int range = role.getPermissionRange();
            if (range != 0) {
                List<TeacherClass> teacherClass = roleService.findClassByTeacher(teacherId);
                if (teacherClass.size() != 0) {
                    String classId = teacherClass.get(0).getClassId();
                    GradeClass gradeClass = classService.findClassById(classId);
                    if (gradeClass != null) {
                        String nownj = gradeClass.getXd() + "_" + gradeClass.getNj();
                        String nowclass = String.valueOf(classId);
                        model.addAttribute("nownj", nownj);
                        model.addAttribute("nowclass", nowclass);
                    }
                }
            }
        }

        model.addAttribute("roleId", roleId);
        model.addAttribute("teacherNo", teacherNo);
        model.addAttribute("cascadingViews", cascadingViews);
        model.addAttribute("roleList", roleList);
        return "/manage/adduser";
    }

    /**
     * 绑定用户模板下载
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/usertemplate")
    public String userTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "导入用户模板.xlsx";
            List<Student> list = Lists.newArrayList();
            String anno = "数据范围是全校学生，学段就填写全部，年级不用填写，班级不用填写；\n" +
                    "数据范围是全年级学生，学段填写某一个学段，例如初中，年级填写某一个年级，例如一年级，班级不用填写；\n" +
                    "数据范围是全校学生，学段填写某一个学段，例如初中，年级填写某一个年级，例如一年级，班级填写某一个班级，例如1班。";
            new ExportExcel("用户信息", ImportUser.class, 2, anno, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用户导入页面
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/importuser")
    public String importuser(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        return "/manage/importuser";
    }

    /**
     * 用户导入失败页面
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/erroimport", method = RequestMethod.POST)
    public String importUserError(HttpServletRequest request, Model model) {
        JsonArray jsonArray = (JsonArray) new JsonParser().parse(getParamVal(request, "data"));
        List<ImportUser> users = new ArrayList<ImportUser>();
        for (JsonElement element : jsonArray) {
            ImportUser user = GsonUtil.fromJson((JsonObject) element, ImportUser.class);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "/manage/erroimport";
    }

    /**
     * 用户-导入功能
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/import/user", method = RequestMethod.POST)
    public ResultEntity userImport(@RequestParam(value = "file") MultipartFile file, RedirectAttributes
            redirectAttributes) {
        try {
            ImportExcel importExcel = new ImportExcel(file, 2, 0);  //从第一行开始
            List<ImportUser> users = importExcel.getDataList(ImportUser.class, 1);
            if (users.size() == 0) return ResultEntity.newErrEntity(-2, "模板异常");
            List<ImportUser> erro = new ArrayList<ImportUser>();
            String schoolId = getLoginUser().getSchoolId();
            for (ImportUser user : users) {
                if (user.getNo() == null) user.setNo(" ");
                if (user.getRoleName() == null) user.setRoleName(" ");
                if (user.getXd() == null) user.setXd(" ");
                if (user.getNj() == null) user.setNj(" ");
                if (user.getClassId() == null) user.setClassId(" ");
                String no = user.getNo();
                String roleName = user.getRoleName();
                String xdName = user.getXd();
                String njName = user.getNj();
                String className = user.getClassId();
                Teacher teacher = teacherService.findTeacherByNo(schoolId, no);
                if (teacher == null) {
                    user.setMsg("教工号不存在");
                    erro.add(user);
                    continue;   //只显示一条失败的原因
                }
                String teacherId = teacher.getId();

                User adduser = userService.getUserByTypeAndRefId(1, teacherId);
                if (adduser == null) {
                    user.setMsg("系统错误无法添加");
                    erro.add(user);
                    continue;
                }
                String userId = adduser.getId();

                Role role = roleService.findRoleByName(schoolId, roleName);
                if (role == null) {
                    user.setMsg("角色不存在");
                    erro.add(user);
                    continue;
                }
                String roleId = role.getId();

                int range = role.getPermissionRange();//0:全校    1：本年级   2：本班
                //全校
                if (range == 0) {
                    if (!xdName.equals("全部")) {
                        user.setMsg("学段设置有误");
                        erro.add(user);
                        continue;
                    }
                    if (!njName.equals("全部")) {
                        user.setMsg("年级设置有误");
                        erro.add(user);
                        continue;
                    }
                    if (!className.equals("全部")) {
                        user.setMsg("班级设置有误");
                        erro.add(user);
                        continue;
                    }

                    List<String> classIds = new ArrayList<String>();
                    List<GradeClass> gradeClasses = roleService.findClass(getLoginUser().getSchoolId(), "0", 0);
                    if (gradeClasses.size() == 0) {
                        user.setMsg("未设置班级");
                        erro.add(user);
                        continue;
                    }
                    //保存
                    for (GradeClass gradeClass : gradeClasses) {
                        classIds.add(gradeClass.getId());
                    }
                    roleService.saveUserRole(userId, roleId);
                    roleService.saveTeacherClass(teacherId, classIds);
                    continue;
                }

                ClassSection classSection = classService.findClassSectionByName(schoolId, xdName);
                if (classSection == null) {
                    user.setMsg("学段不存在");
                    erro.add(user);
                    continue;
                }
                String xd = classSection.getId();

                int nj = ConstantUtil.getKeyByValueAndFlag(njName, "nj");
                if (nj == 0) {
                    user.setMsg("年级信息有误");
                    erro.add(user);
                    continue;
                }
                int year = classSection.getSectionYear();
                if (nj > year) {
                    user.setMsg("年级超出学段年制");
                    erro.add(user);
                    continue;
                }

                //本年级
                if (range == 1) {
                    if (!className.equals("全部")) {
                        user.setMsg("班级设置有误");
                        erro.add(user);
                        continue;
                    }
                    List<String> classIds = new ArrayList<String>();
                    List<GradeClass> gradeClasses = roleService.findClass(getLoginUser().getSchoolId(), xd, nj);
                    if (gradeClasses.size() == 0) {
                        user.setMsg("未设置班级");
                        erro.add(user);
                        continue;
                    }
                    //保存
                    for (GradeClass gradeClass : gradeClasses) {
                        classIds.add(gradeClass.getId());
                    }
                    roleService.saveUserRole(userId, roleId);
                    roleService.saveTeacherClass(teacherId, classIds);
                    continue;
                }
                //本班
                if (range == 2) {
                    GradeClass gradeClass = classService.findClassByName(schoolId, xd, nj, className);
                    if (gradeClass == null) {
                        user.setMsg("班级不存在");
                        erro.add(user);
                        continue;
                    }
                    String classId = gradeClass.getId();
                    List<String> classIds = new ArrayList<String>();
                    classIds.add(classId);
                    roleService.saveUserRole(userId, roleId);
                    roleService.saveTeacherClass(teacherId, classIds);
                    continue;
                }
            }
            if (erro.size() == 0) {
                return ResultEntity.newResultEntity("导入成功", null);
            } else {
                return ResultEntity.newResultEntity(-1, String.valueOf(users.size() - erro.size()), erro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 保存用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/save")
    public ResultEntity saveUser(HttpServletRequest request) {
        String _no = getParamVal(request, "no");
        String roleId = getParamVal(request, "roleId");
        String xd = getParamVal(request, "xd");
        String _nj = getParamVal(request, "nj");
        String classId = getParamVal(request, "classId");
        String _range = getParamVal(request, "range");

        int nj = NumberConvertUtil.convertS2I(_nj);
        int range = NumberConvertUtil.convertS2I(_range);

        Teacher teacher = teacherService.findTeacherByNo(getLoginUser().getSchoolId(), _no);
        if (teacher == null) return ResultEntity.newErrEntity("未查询到该教师");
        String teacherId = teacher.getId();

        User user = userService.getUserByTypeAndRefId(1, teacherId);
        if (user == null) return ResultEntity.newErrEntity("系统错误无法添加");
        String userId = user.getId();

        List<String> classIds = new ArrayList<String>();
        int count = 0;
        roleService.saveUserRole(userId, roleId);
        if (range == 2) {
            classIds.add(classId);
            count = roleService.saveTeacherClass(teacherId, classIds);
        } else {
            List<GradeClass> gradeClasses = roleService.findClass(getLoginUser().getSchoolId(), xd, nj);
            if (gradeClasses.size() == 0)
                return ResultEntity.newErrEntity("未查询到班级");
            for (GradeClass gradeClass : gradeClasses) {
                classIds.add(gradeClass.getId());
            }
            count = roleService.saveTeacherClass(teacherId, classIds);
        }
        if (count > 0)
            return ResultEntity.newResultEntity("保存成功", null);
        else return ResultEntity.newErrEntity("保存失败");
    }

    /**
     * 基站信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/station/index")
    public String stationIndex(HttpServletRequest request, Model model) {
            String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);

        PageInfo<StationView> pageInfo = deviceService.findStationBySchoolId(getLoginUser().getSchoolId(), null, pageNum, pageSize);

        List<StationView> brokenStation = deviceService.findStationBySchoolId(getLoginUser().getSchoolId(), 1);
        model.addAttribute("brokenStation", brokenStation.size());
        model.addAttribute("pageInfo", pageInfo);
        return "/manage/stationmsg";
    }

    /**
     * 新增基站
     * @return
     */
    @RequestMapping(value = "/station/add")
    public String stationAdd(Model model) {

        List<StationArea> areaList = deviceService.findAreaBySchoolId( getLoginUser().getSchoolId());
        model.addAttribute( "areaList", areaList);

//        List<GradeClass> classList = classService.findClassList( getLoginUser().getSchoolId());
//        model.addAttribute( "classList", classList);
        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());
        model.addAttribute("cascadingViews", cascadingViews);

        return "/manage/addstation";
    }

    /**
     * 修改基站
     * @param stationMac
     * @param model
     * @return
     */
    @RequestMapping(value = "/station/edit/{mac}")
    public String stationEdit(@PathVariable("mac")String stationMac, Model model) {
        DeviceStation station = deviceService.findStationByMac( stationMac);
        List<StationArea> areaList = deviceService.findAreaBySchoolId( getLoginUser().getSchoolId());
        if (StringUtils.isNotEmpty(station.getClassId())) {
            GradeClass gradeClass = classService.findClassById( station.getClassId());
            model.addAttribute( "gradeClass", gradeClass);
        }
//        List<GradeClass> classList = classService.findClassList( getLoginUser().getSchoolId());
//        model.addAttribute( "classList", classList);
        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());
        model.addAttribute("cascadingViews", cascadingViews);
        model.addAttribute( "station", station);
        model.addAttribute( "areaList", areaList);
        return "/manage/addstation";
    }

    /**
     * 保存基站
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/station/save")
    public ResultEntity stationSave( HttpServletRequest request) {
        int count = 0;
        String _id = request.getParameter( "id");
        String _stationMac = request.getParameter("stationMac");
        String _category = request.getParameter("category");
        String classId = request.getParameter("gradeclass");
        String _areaId = request.getParameter("areaId");
        String _remark = request.getParameter("remark");
        String schoolId = getLoginUser().getSchoolId();
        String optId = getLoginUser().getId();

        if( null == _id || ("").equals( _stationMac)) {
            return ResultEntity.newErrEntity("MAC地址不能为空！");
        }

        if ( null == _id || ("").equals(_id) && null != deviceService.findStationByMac(_stationMac)){
            return ResultEntity.newErrEntity("MAC地址已存在！");
        }
        if ( !RegexpUtil.isMacFormat(_stationMac)){
            return ResultEntity.newErrEntity("MAC地址格式错误");

        }

        DeviceStation station = new DeviceStation();
        station.setId( _id);
        station.setStationMac(_stationMac);
        station.setAreaId(_areaId);
        station.setRemarks(_remark);
        station.setSchoolId(schoolId);
        station.setCategory( Integer.valueOf(_category));
        station.setClassId( classId);
        count = deviceService.saveStation(station, optId);
        if (count > 0) {
            return ResultEntity.newResultEntity("保存成功！");
        } else {
            return ResultEntity.newErrEntity("保存失败！");
        }
    }

    /**
     * 删除基站
     * @param request
     * @param mac
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/station/del/{mac}", method = RequestMethod.POST)
    public ResultEntity stationDel(HttpServletRequest request, @PathVariable String mac) {

        int count = deviceService.deleteStationByMac(mac);

        return ResultEntity.newResultEntity(count);
    }

    /**
     * 基站模板下载
     * @param response
     * @return
     */
    @RequestMapping( value = "/stationtemplate")
    public String StationTemplate( HttpServletResponse response) {
        try {
            String fileName = "导入基站模板.xlsx";
            List<DeviceStation> stationList = Lists.newArrayList();
            String anno = "详细位置主要用来描述基站的安装位置；\n" +
                    "所在区域可以填写已存在的区域名称，或者不填，不填的话，该基站不属于任何区域，你可以后期手动分配；";
            new ExportExcel("基站信息", ImportStation.class, 2, anno, 1).setDataList( stationList).write( response, fileName).dispose();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 基站导入页面
     * @return
     */
    @RequestMapping( value = "/importstation")
    public String importStation() {
        return "/manage/importstation";
    }

    /**
     * 基站导入失败页面
     * @param request
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/errorimportstation", method = RequestMethod.POST)
    public String importStationError( HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<ImportStation> exportFile = new ArrayList<ImportStation>();
            for (JsonElement jsonElement : jsonArray) {
                ImportStation importStation = GsonUtil.fromJson(jsonElement.getAsJsonObject(), ImportStation.class);
                exportFile.add(importStation);
            }
            new ExportExcel("失败数据", "", ImportStation.class).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导入基站存入数据库
     * @param file
     * @param redirectAttributes
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/import/station", method = RequestMethod.POST)
    public ResultEntity stationImport(@RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            ImportExcel importExcel = new ImportExcel( file, 2, 0);
            List<ImportStation> stations = importExcel.getDataList( ImportStation.class,1);
            if (stations.size() ==0) {
                return ResultEntity.newErrEntity(-2,"模板异常");
            }
            int count  = 0;//总数
            int errorCount = 0;//错误数
            int trueCount = 0;
            List<ImportStation> error = new ArrayList<ImportStation>();
            //将该学校区域id和区域name存入Map
            //区域name唯一，可作为key,取出id
            List<StationArea> areaList = deviceService.findAreaBySchoolId( getLoginUser().getSchoolId());
            Map<String, String> areaMap = new HashMap<String, String>();
            for ( StationArea area:areaList) {
                areaMap.put(area.getAreaName(), area.getId());
            }
            for ( ImportStation station:stations) {
                if (station.getMac() == null) {
                    station.setMsg("第"+(count+1)+"个数据:"+"基站MAC为空");
                    error.add(station);
                    errorCount += 1;
                    count += 1;
                    continue;
                } else if ( null != deviceService.findStationByMac( station.getMac())){
                    station.setMsg("第"+(count+1)+"个数据:"+"MAC地址已存在");
                    error.add(station);
                    errorCount += 1;
                    count += 1;
                    continue;
                } else if ( !RegexpUtil.isMacFormat(station.getMac())){
                    station.setMsg( "第"+(count+1)+"个数据：MAC地址格式错误");
                    error.add(station);
                    count += 1;
                    errorCount += 1;
                    continue;

                }
                if (station.getXxwz() == null) {
                    station.setSzqy("第"+(count+1)+"个数据:"+"详细位置为空");
                    error.add(station);
                    errorCount += 1;
                    count += 1;
                    continue;
                }

                String mac = station.getMac();
                String szqy = station.getSzqy();
                String xxwz = station.getXxwz();
                DeviceStation deviceStation = new DeviceStation();
                deviceStation.setStationMac(mac);
                deviceStation.setRemarks( xxwz);
                if (szqy == null) {
                    deviceStation.setAreaId("");
                } else {
                    if ( areaMap.containsKey( szqy)) {
                        deviceStation.setAreaId( areaMap.get(szqy));//key调取value值
                    } else {
                        station.setMsg("第"+(count+1)+"个数据"+"所在区域填写错误");
                        error.add( station);
                        errorCount += 1;
                        count += 1;
                        continue;
                    }
                }
                deviceStation.setSchoolId( getLoginUser().getSchoolId());
                String optId = getLoginUser().getId();
                deviceService.saveStation( deviceStation,optId);
                trueCount += 1;
                count += 1;
            }
            if (errorCount == 0) {
                return ResultEntity.newResultEntity("导入成功"+count+"个基站", null);
            }
            return ResultEntity.newResultEntity( -1, String.valueOf(trueCount), error);
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("文档格式有误");
        }
    }



    /**
     * 区域信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/area/index/{type}")
    public String areaIndex(@PathVariable String type, HttpServletRequest request, Model model) {
        String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);

        PageHelper.startPage(pageNum, pageSize);
        Page<StationArea> area = (Page<StationArea>) deviceService.findAreaBySchoolId(getLoginUser().getSchoolId());
        //统计本区域基站数量
        for( StationArea _area:area) {
            DeviceStationExample example = new DeviceStationExample();
            example.createCriteria().andDelFlagEqualTo(0).andAreaIdEqualTo( _area.getId());
            List<DeviceStation> stationList = deviceService.findStationByExample( example);
            _area.setStationNumber( stationList.size());
        }
        PageInfo<StationArea> pageInfo = new PageInfo<StationArea>(area);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute( "type", type);//显示位置：0基础管理：区域管理；1：位置监控：区域信息
        return "/manage/areamsg";
    }

    /**
     * 新增区域
     * @param
     * @return
     */
    @RequestMapping( value = "/area/add")
    public String areaAdd( Model model){
        List<StationView> deviceStations = deviceService.findStationBySchoolId( getLoginUser().getSchoolId(), null);
        model.addAttribute( "deviceStations", deviceStations);
        return "/manage/addarea";
    }

    /**
     * 编辑区域
     * @param id
     * @param model
     * @return
     */
    @RequestMapping( value = "/area/edit/{id}")
    public String areaEdit( @PathVariable String id, Model model){
        StationArea stationArea = deviceService.findAreaById( id);
        List<StationView> deviceStations = deviceService.findStationBySchoolId( getLoginUser().getSchoolId(), null);

        model.addAttribute( "stationArea", stationArea);
        model.addAttribute( "deviceStations", deviceStations);

        return "/manage/addarea";
    }

    /**
     * 保存区域（兼基站保存区域id）
     * @param
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/area/save")
    public ResultEntity areaSave( HttpServletRequest request) {
        try {
            String _areaId = request.getParameter( "areaId");
            String _areaName = request.getParameter( "areaName");
            String _remarks = request.getParameter( "remarks");
            String _stationIds = request.getParameter( "stations");
            String schoolId = getLoginUser().getSchoolId();

            if ( null == _areaId || ("").equals(_areaId)){
                int count = deviceService.findAreaByName( _areaName, schoolId);
                if (count>0){
                    return ResultEntity.newErrEntity("区域名称不可重复！");
                }
            }

            //保存区域
            StationArea area = new StationArea();
            area.setId( _areaId);
            area.setSchoolId( schoolId);
            area.setAreaName( _areaName);
            area.setRemarks( _remarks);
            area = deviceService.saveArea( area);

            if (null != _stationIds && !("").equals(_stationIds)){
                String[] stationIds = _stationIds.split(",");
                //数据库中该区域原有基站,若区域内不再有该基站，则将该基站释放
                if ( null != area.getId() && !("").equals(area.getId())) {
                    DeviceStationExample example = new DeviceStationExample();
                    example.createCriteria().andAreaIdEqualTo(area.getId()).andDelFlagEqualTo( 0);
                    List<DeviceStation> stationList = deviceService.findStationByExample( example);
                    if (stationList != null && stationList.size()>0) {
                        for ( DeviceStation station:stationList) {
                            if (!ArrayUtils.contains( stationIds, station.getId())) {
                                station.setAreaId("");
                                String optId = getLoginUser().getId();
                                deviceService.saveStation( station, optId);
                            }
                        }
                    }
                }
                //保存该区域加入的新基站
                List<String> stationIdList = new ArrayList<String>();
                for ( String stationId:stationIds) {
                    stationIdList.add( stationId);
                }
                DeviceStationExample deviceStationExample = new DeviceStationExample();
                deviceStationExample.createCriteria().andIdIn(stationIdList).andDelFlagEqualTo(0);

                DeviceStation station = new DeviceStation();
                station.setAreaId( area.getId());
                station.setCreateBy( getLoginUser().getId());

                deviceService.updateStation( station, deviceStationExample);
            }

            return ResultEntity.newResultEntity( "保存成功！");
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity( "保存失败！");
        }
    }

    /**
     * 删除区域
     * @param areaId
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/area/del/{id}")
    public ResultEntity areaDel( @PathVariable("id") String areaId) {
        try {
            StationArea area = new StationArea();
            area.setId( areaId);
            area.setDelFlag(1);
            deviceService.saveArea( area);

            //释放该区域基站
            DeviceStation station = new DeviceStation();
            station.setAreaId( "");

            DeviceStationExample example = new DeviceStationExample();
            example.createCriteria().andAreaIdEqualTo( areaId).andDelFlagEqualTo(0);
            deviceService.updateStation( station, example);
            return ResultEntity.newResultEntity( "删除成功！");
        } catch ( Exception e) {
            return ResultEntity.newErrEntity( "删除失败！");
        }
    }
}
