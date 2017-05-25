/**
 */
package cn.gukeer.divideStudent.zsfb.web;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.utils.CnToPyUtils;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ExportExcelForDivide;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.common.web.BaseController;
import cn.gukeer.divideStudent.sys.utils.UserUtils;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsAnalysis;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsStudent;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTask;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTaskConstant;
import cn.gukeer.divideStudent.zsfb.service.ZsStudentService;
import cn.gukeer.divideStudent.zsfb.service.ZsTaskService;
import cn.gukeer.divideStudent.zsfb.util.DividePro;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * 分班Controller
 *
 * @author CC
 */
@Controller
@RequestMapping(value = "/zsfb/zsStudent")
public class ZsStudentController extends BaseController {

    @Autowired
    private ZsStudentService zsStudentService;
    @Autowired
    private ZsTaskService zsTaskService;

    @ModelAttribute
    public ZsStudent get(@RequestParam(required = false) String id) {
        ZsStudent entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = zsStudentService.get(id);
        }
        if (entity == null) {
            entity = new ZsStudent();
        }
        return entity;
    }

    /**
     * 学生列表页面
     *
     * @param zsStudent
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:list")
    @RequestMapping(value = {"list", ""})
    public String list(ZsStudent zsStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
        String taskId = request.getParameter("taskId");
        if (StringUtils.isNoneEmpty(taskId)) {
            zsStudent.setTaskId(taskId);
            Page<ZsStudent> page = zsStudentService.findPage(new Page<ZsStudent>(request, response), zsStudent);
            model.addAttribute("page", page);
            model.addAttribute("zsTask", zsTaskService.get(taskId));
        }
        return "zsfb/preview";
    }

    /**
     * 评估结果页面
     *
     * @param zsStudent
     * @param model
     * @param taskId
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:view")
    @RequestMapping(value = "analysis")
    public String analysis(ZsStudent zsStudent, Model model, String taskId) {
        if (StringUtils.isNoneEmpty(taskId)) {
            zsStudent.setTaskId(taskId);
            zsStudent.setSfdkxx(ZsTaskConstant.YES);
            List<ZsAnalysis> analysis = zsStudentService.AnalysisResult(zsStudent);
            List<ZsStudent> schoolList = zsStudentService.findSchoolList(zsStudent);
            Map schoolCount = Maps.newHashMap();
            for (int i = 1; i <= zsStudentService.queryClassCount(taskId); i++) {
                Map bjList = Maps.newHashMap();
                for (ZsStudent obj1 : schoolList) {
                    obj1.setBj(String.valueOf(i));
                    List<ZsAnalysis> analysisList = zsStudentService.findSchoolCount(obj1);
                    for (ZsAnalysis obj2 : analysisList) {
                        bjList.put(obj2.getYxx(), obj2.getYxxCount());
                    }
                }
                schoolCount.put(i, bjList);
            }
            model.addAttribute("analysis", analysis);
            model.addAttribute("schoolList", schoolList);
            model.addAttribute("schoolCount", schoolCount);
        }
        return "zsfb/analysis";
    }

    /**
     * 导出excel文件
     *
     * @param zsStudent
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:export")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(ZsStudent zsStudent, HttpServletRequest request, HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        try {
            Map dataMap = Maps.newHashMap();
            String fileName = "学生" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            String taskId = request.getParameter("taskId");
            Integer classNum = null;
            if (!StringUtils.isEmpty(taskId)) {
                zsStudent.setTaskId(taskId);
                classNum = zsStudentService.queryClassCount(taskId);
                if (null != classNum && classNum > 0) {
                    for (int i = 1; i <= classNum; i++) {
                        zsStudent.setBj(String.valueOf(i));
                        List<ZsStudent> dataList = zsStudentService.findAllList(zsStudent);
                        dataMap.put(i, dataList);
                    }
                }
            }
            if (UserUtils.getUser().getSchool().getXxlx().equals("1")) {
                new ExportExcelForDivide("分班结果", ZsStudent.class, 1, String.valueOf(classNum), 1).setDataList(dataMap)
                        .write(response, fileName).dispose();
            } else {
                new ExportExcelForDivide("分班结果", ZsStudent.class, 1, String.valueOf(classNum), 2).setDataList(dataMap)
                        .write(response, fileName).dispose();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出学生记录失败");
        }
        return "redirect:" + "/zsfb/zsStudent/?repage";
    }

    /**
     * 导入Excel数据
     *
     * @param file
     * @param redirectAttributes
     * @param taskId
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:import")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(ZsStudent zsStudent, MultipartFile file, RedirectAttributes redirectAttributes,
                             String taskId) {
        try {
            // 导入之前删除分班任务下存在的学生
            zsStudent.setTaskId(taskId);
            zsStudentService.deleteInfoByTask(zsStudent);
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 2, 0);
            List<ZsStudent> list = null;
            if (StringUtils.equals(UserUtils.getUser().getSchool().getXxlx(), "1")) {
                list = ei.getDataList(ZsStudent.class, 1);
            } else {
                list = ei.getDataList(ZsStudent.class, 2);
            }
            for (ZsStudent object : list) {
                try {
                  if (!object.getXsxb().equals("1") && !object.getXsxb().equals("2")) continue;
                    object.setTaskId(taskId);
                    if (UserUtils.getUser().getSchool().getXxlx().equals("1")) {
                        object.setZf(DividePro.A);
                    }
                    object.setXmpy(CnToPyUtils.getPingYin(object.getXsxm()));
                    zsStudentService.save(object);
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureNum++;
                    logger.error("ConstraintViolationException:", ex);
                } catch (Exception ex) {
                    failureNum++;
                    logger.error("Exception:", ex);
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条学生记录。");
            }
            redirectAttributes.addAttribute("id", taskId);
            redirectAttributes.addAttribute("importFlag", "true");
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条学生记录" + failureMsg);
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导入学生失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + "/zsfb/zsTask/first?repage";

    }

    /**
     * 下载学生数据模板
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:export")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = UserUtils.getUser().getSchool().getXxmc() + "-学生数据导入模板.xlsx";
            List<ZsStudent> list = Lists.newArrayList();
            if (UserUtils.getUser().getSchool().getXxlx().equals("1")) {
                new ExportExcel("学生数据", ZsStudent.class, 2, 1).setDataList(list).write(response, fileName).dispose();
            } else {
                new ExportExcel("学生数据", ZsStudent.class, 2, 2).setDataList(list).write(response, fileName).dispose();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + "/zsfb/zsTask/first?repage";
    }

    /**
     * 分班开始
     *
     * @param zsStudent
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions(value = {"zsfb:zsStudent:view", "zsfb:zsStudent:add",
            "zsfb:zsStudent:edit"}, logical = Logical.OR)
    @RequestMapping(value = "devide")
    public String devide(ZsStudent zsStudent, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            // 获取任务ID,分班个数,分班条件
            String taskId = request.getParameter("taskId");
            String bjCount = request.getParameter("bjCount");
            String divideCondition = request.getParameter("devideCondition");
            ZsTask zsTask = zsTaskService.get(taskId);
            // 如果任务ID不为空,分班个数不为空
            if (!StringUtils.isEmpty(taskId) && !StringUtils.isEmpty(bjCount)) {
                zsStudent.setTaskId(taskId);
                // 分班条件不为空
                if (!StringUtils.isEmpty(divideCondition)) {
                    if (zsTask != null) {
                        // 根据条件分班
                        String[] conditions = divideCondition.split(",");
                        List<String> condition = Lists.newArrayList(conditions);

                        ZsStudent queryObj = new ZsStudent();
                        queryObj.setTaskId(taskId);
                        List<ZsStudent> allStudent = zsStudentService.findAllList(queryObj);

                        // 权重 人数
                        Map<String, Integer> weightMap = new HashMap<String, Integer>();
                        Map<String, Integer> studentNumMap = new HashMap<String, Integer>();
                        initStudentMap(weightMap, bjCount);
                        initStudentMap(studentNumMap, bjCount);

                        // 特殊人群map
                        Map<String, List<ZsStudent>> allSpecialStudent = fetchAllSpecialStudent(allStudent, condition,
                                weightMap, studentNumMap, bjCount);
                        // 普通人群map
                        Map<String, List<ZsStudent>> toDivideMap = new HashMap<String, List<ZsStudent>>();
                        if (condition.contains("school")) { // 初中[选择学校]

                            for (ZsStudent student : allStudent) {
                                if ("0".equals(student.getSfdkxx())) {
                                    if (!toDivideMap.containsKey("sfdkxx")) {
                                        List<ZsStudent> sfdkxx = new ArrayList<ZsStudent>();
                                        toDivideMap.put("sfdkxx", sfdkxx);
                                    }
                                    List<ZsStudent> sfdkxxList = toDivideMap.get("sfdkxx");
                                    sfdkxxList.add(student);
                                } else {
                                    String schoolName = student.getYxx();
                                    if (!toDivideMap.containsKey(schoolName)) {
                                        List<ZsStudent> yxxList = new ArrayList<ZsStudent>();
                                        toDivideMap.put(schoolName, yxxList);
                                    }
                                    List<ZsStudent> yxxList = toDivideMap.get(schoolName);
                                    yxxList.add(student);
                                }
                            }


                        } else if (condition.contains("jtdz")) { // 小学[选择家庭地址]

                            for (ZsStudent student : allStudent) {

                                String address = null;
                                if (!StringUtils.isEmpty(student.getXq())) {
                                    address = student.getXh();
                                } else {
                                    address = student.getJtdz();
                                }
                                if (!toDivideMap.containsKey(address)) {
                                    List<ZsStudent> addressList = new ArrayList<ZsStudent>();
                                    toDivideMap.put(address, addressList);
                                }
                                List<ZsStudent> addressList = toDivideMap.get(address);
                                addressList.add(student);
                            }
                        } else {
                            toDivideMap.put("one", allStudent);

                        } // 数据分组完成

                        Map<String, List<ZsStudent>> temp = new HashMap<String, List<ZsStudent>>();
                        for (Map.Entry<String, List<ZsStudent>> entry : allSpecialStudent.entrySet()) {
                            String key = entry.getKey();
                            List<ZsStudent> studentList = entry.getValue();
                            for (ZsStudent student : studentList) {
                                if ("1".equals(student.getXsxb())) {
                                    addStudentToMapList(temp, student, key, "m");
                                } else if ("2".equals(student.getXsxb())) {
                                    addStudentToMapList(temp, student, key, "f");
                                }
                            }
                        }

                        int maxClass = Integer.parseInt(bjCount);
                        //defaultKey + sex + DividePro.A
                        Set<String> keySet = allSpecialStudent.keySet();

                        List<ZsStudent> specialA = new ArrayList<ZsStudent>();
                        List<ZsStudent> specialB = new ArrayList<ZsStudent>();
                        List<ZsStudent> specialC = new ArrayList<ZsStudent>();
                        for (String key : keySet) {
                            List<ZsStudent> m = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "m", DividePro.A);
                            List<ZsStudent> f = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "f", DividePro.A);
                            if (null != m && m.size() > 0) {
                                specialA.addAll(m);
                            }
                            if (null != f && f.size() > 0) {
                                specialA.addAll(f);
                            }
                        }
                        for (String key : keySet) {
                            List<ZsStudent> m = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "m", DividePro.B);
                            List<ZsStudent> f = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "f", DividePro.B);
                            if (null != m && m.size() > 0) {
                                specialB.addAll(m);
                            }
                            if (null != f && f.size() > 0) {
                                specialB.addAll(f);
                            }
                        }
                        for (String key : keySet) {
                            List<ZsStudent> m = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "m", DividePro.C);
                            List<ZsStudent> f = divideSpecaialDataGroup(temp, weightMap, studentNumMap, taskId, key, maxClass, "f", DividePro.C);
                            if (null != m && m.size() > 0) {
                                specialC.addAll(m);
                            }
                            if (null != f && f.size() > 0) {
                                specialC.addAll(f);
                            }
                        }// 特殊分完

                        List<ZsStudent> sfdkxxList = toDivideMap.get("sfdkxx");
                        if (null != sfdkxxList && sfdkxxList.size() > 0) {
                            toDivideMap.remove("sfdkxx");
                            List<ZsStudent> list = sfdkxxList;
                            if (condition.contains("sex") && condition.contains("score")) {
                                // 男A 男B 男C 女A 女B 女C
                                List<ZsStudent> maleA = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleA = new ArrayList<ZsStudent>();
                                List<ZsStudent> maleB = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleB = new ArrayList<ZsStudent>();
                                List<ZsStudent> maleC = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleC = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("1".equals(student.getXsxb()) && "A".equalsIgnoreCase(student.getZf())) {
                                        maleA.add(student);
                                    }
                                    if ("1".equals(student.getXsxb()) && "B".equalsIgnoreCase(student.getZf())) {
                                        maleB.add(student);
                                    }
                                    if ("1".equals(student.getXsxb()) && "C".equalsIgnoreCase(student.getZf())) {
                                        maleC.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "A".equalsIgnoreCase(student.getZf())) {
                                        femaleA.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "B".equalsIgnoreCase(student.getZf())) {
                                        femaleB.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "C".equalsIgnoreCase(student.getZf())) {
                                        femaleC.add(student);
                                    }
                                }

                                toDivide(maleA, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(femaleA, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(maleB, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(femaleB, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(maleC, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(femaleC, weightMap, studentNumMap, taskId, maxClass);

                            } else if (condition.contains("sex") && !condition.contains("score")) {
                                // 男 女
                                List<ZsStudent> male = new ArrayList<ZsStudent>();
                                List<ZsStudent> female = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("1".equals(student.getXsxb())) {
                                        male.add(student);
                                    } else {
                                        female.add(student);
                                    }
                                }
                                toDivide(male, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(female, weightMap, studentNumMap, taskId, maxClass);
                            } else if (condition.contains("score") && !condition.contains("sex")) {
                                // 男 女
                                List<ZsStudent> a = new ArrayList<ZsStudent>();
                                List<ZsStudent> b = new ArrayList<ZsStudent>();
                                List<ZsStudent> c = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("A".equalsIgnoreCase(student.getZf())) {
                                        a.add(student);
                                    } else if ("B".equalsIgnoreCase(student.getZf())) {
                                        b.add(student);
                                    } else {
                                        c.add(student);
                                    }
                                }
                                toDivide(a, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(b, weightMap, studentNumMap, taskId, maxClass);
                                toDivide(c, weightMap, studentNumMap, taskId, maxClass);
                            } else {
                                toDivide(list, weightMap, studentNumMap, taskId, maxClass);
                            }
                        }

                        // normal ==============================================================
                        List<ZsStudent> aStudentList = new ArrayList<ZsStudent>();
                        List<ZsStudent> bStudentList = new ArrayList<ZsStudent>();
                        List<ZsStudent> cStudentList = new ArrayList<ZsStudent>();

                        List<ZsStudent> maleStudentList = new ArrayList<ZsStudent>();
                        List<ZsStudent> feMaleStudentList = new ArrayList<ZsStudent>();

                        List<ZsStudent> divideStudentList = new ArrayList<ZsStudent>();
                        for (Map.Entry<String, List<ZsStudent>> entry : toDivideMap.entrySet()) {
                            String key = entry.getKey();
                            List<ZsStudent> list = entry.getValue();
                            if (condition.contains("sex") && condition.contains("score")) {
                                // 男A 男B 男C 女A 女B 女C
                                List<ZsStudent> maleA = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleA = new ArrayList<ZsStudent>();
                                List<ZsStudent> maleB = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleB = new ArrayList<ZsStudent>();
                                List<ZsStudent> maleC = new ArrayList<ZsStudent>();
                                List<ZsStudent> femaleC = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("1".equals(student.getXsxb()) && "A".equalsIgnoreCase(student.getZf())) {
                                        maleA.add(student);
                                    }
                                    if ("1".equals(student.getXsxb()) && "B".equalsIgnoreCase(student.getZf())) {
                                        maleB.add(student);
                                    }
                                    if ("1".equals(student.getXsxb()) && "C".equalsIgnoreCase(student.getZf())) {
                                        maleC.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "A".equalsIgnoreCase(student.getZf())) {
                                        femaleA.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "B".equalsIgnoreCase(student.getZf())) {
                                        femaleB.add(student);
                                    }
                                    if ("2".equals(student.getXsxb()) && "C".equalsIgnoreCase(student.getZf())) {
                                        femaleC.add(student);
                                    }
                                }

//                                toDivide(maleA, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(femaleA, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(maleB, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(femaleB, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(maleC, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(femaleC, weightMap, studentNumMap, taskId, maxClass);
                                if (null != maleA && maleA.size() > 0) {
                                    aStudentList.addAll(maleA);
                                }
                                if (null != femaleA && femaleA.size() > 0) {
                                    aStudentList.addAll(femaleA);
                                }
                                if (null != maleB && maleB.size() > 0) {
                                    bStudentList.addAll(maleB);
                                }
                                if (null != femaleB && femaleB.size() > 0) {
                                    bStudentList.addAll(femaleB);
                                }
                                if (null != maleC && maleC.size() > 0) {
                                    cStudentList.addAll(maleC);
                                }
                                if (null != femaleC && femaleC.size() > 0) {
                                    cStudentList.addAll(femaleC);
                                }
                            } else if (condition.contains("sex") && !condition.contains("score")) {
                                // 男 女
                                List<ZsStudent> male = new ArrayList<ZsStudent>();
                                List<ZsStudent> female = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("1".equals(student.getXsxb())) {
                                        male.add(student);
                                    } else {
                                        female.add(student);
                                    }
                                }
//                                toDivide(male, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(female, weightMap, studentNumMap, taskId, maxClass);
                                maleStudentList.addAll(male);
                                maleStudentList.addAll(female);
                            } else if (condition.contains("score") && !condition.contains("sex")) {
                                // 男 女
                                List<ZsStudent> a = new ArrayList<ZsStudent>();
                                List<ZsStudent> b = new ArrayList<ZsStudent>();
                                List<ZsStudent> c = new ArrayList<ZsStudent>();
                                for (int num = 0; num < list.size(); num++) {
                                    ZsStudent student = list.get(num);
                                    if ("A".equalsIgnoreCase(student.getZf())) {
                                        a.add(student);
                                    } else if ("B".equalsIgnoreCase(student.getZf())) {
                                        b.add(student);
                                    } else {
                                        c.add(student);
                                    }
                                }
//                                toDivide(a, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(b, weightMap, studentNumMap, taskId, maxClass);
//                                toDivide(c, |weightMap, studentNumMap, taskId, maxClass);
                                if (null != a && a.size() > 0) {
                                    divideStudentList.addAll(a);
                                }
                                if (null != b && b.size() > 0) {
                                    divideStudentList.addAll(b);
                                }
                                if (null != c && c.size() > 0) {
                                    divideStudentList.addAll(c);
                                }
                            } else {
//                                toDivide(list, weightMap, studentNumMap, taskId, maxClass);
                                if (null != list && list.size() > 0) {
                                    divideStudentList.addAll(list);
                                }
                            }
                        }
                        toDivide(specialA, weightMap, studentNumMap, taskId, maxClass, 1);
                        toDivide(specialB, weightMap, studentNumMap, taskId, maxClass, 1);
                        toDivide(specialC, weightMap, studentNumMap, taskId, maxClass, 1);
                        if (condition.contains("sex") && condition.contains("score")) {
                            toDivide(aStudentList, weightMap, studentNumMap, taskId, maxClass);
                            toDivide(bStudentList, weightMap, studentNumMap, taskId, maxClass);
                            toDivide(cStudentList, weightMap, studentNumMap, taskId, maxClass);
                        } else if (condition.contains("sex") && !condition.contains("score")) {
                            toDivide(maleStudentList, weightMap, studentNumMap, taskId, maxClass);
                            toDivide(feMaleStudentList, weightMap, studentNumMap, taskId, maxClass);
                        } else {
                            toDivide(divideStudentList, weightMap, studentNumMap, taskId, maxClass);
                        }
                        doOptimizeResult(bjCount, taskId);

                        // 任务分班成功标识
                        zsTask.setFbcg(ZsTaskConstant.YES);
                        zsTaskService.update(zsTask);
                    } else {
                        addMessage(redirectAttributes, "任务信息出错,请刷新后重试");
                        return "redirect:" + "/zsfb/zsTask/second?repage";
                    }
                }
            }
            redirectAttributes.addAttribute("bjCount", bjCount);
            redirectAttributes.addAttribute("taskId", taskId);
            return "redirect:" + "/zsfb/zsStudent/devideResult?repage";
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "分班失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + "/zsfb/zsTask/second?repage";
    }

    private List<ZsStudent> divideSpecaialDataGroup(Map<String, List<ZsStudent>> dataMap,
                                                    Map<String, Integer> weightMap,
                                                    Map<String, Integer> studentNumMap,
                                                    String taskId, String key, int maxClass,
                                                    String sexFlag, String groupIdentify) {
        List<ZsStudent> list = dataMap.get(key + sexFlag + groupIdentify);
//        if (null != list && list.size() > 0) {
//            toDivide(list, weightMap, studentNumMap, taskId, maxClass, 1);
//        }
        return list;
    }

    private void toDivide(List<ZsStudent> dataList, Map<String, Integer> weightMap, Map<String, Integer> studentNumMap, String taskId, int maxClass) {
        toDivide(dataList, weightMap, studentNumMap, taskId, maxClass, 0);
    }

    private void toDivide(List<ZsStudent> dataList, Map<String, Integer> weightMap,
                          Map<String, Integer> studentNumMap, String taskId, int maxClass, int flag) {

        if (null == dataList || dataList.size() == 0) return;
        for (ZsStudent student : dataList) {
            String classKey = getClassNumber(weightMap, studentNumMap, student, taskId, maxClass, flag);
            student.setBj(classKey);
            zsStudentService.update(student);

             // 变化权重属性
            Integer val = weightMap.get(classKey);
            if (null == val) val = 0;
            if ("1".equals(student.getXsxb())) {
                weightMap.put(classKey, ++val);
            } else {
                weightMap.put(classKey, --val);
            }
            int numVal = studentNumMap.get(classKey);
            studentNumMap.put(classKey, ++numVal);
        }
    }

    // 找到
    private String getClassNumber(Map<String, Integer> weightMap,
                                  Map<String, Integer> studentNumMap,
                                  ZsStudent student, String taskId,
                                  int maxClass, int flag) {
        String finalClass = null;
        String sex = student.getXsxb();
        List<String> classList = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : weightMap.entrySet()) {
            String key = entry.getKey();
            Integer count = entry.getValue();
            if ("1".equals(sex)) { // 男 ++
                if (count <= 0) { // 需要男的
                    classList.add(key);
                }
            } else if ("2".equals(sex)) { // 女 --
                if (count >= 0) { // 需要女的
                    classList.add(key);
                }
            }
        }

        if (classList.size() == 0) {
            finalClass = "1";
            int defVal = studentNumMap.get(finalClass);
            for (Map.Entry<String, Integer> entry : studentNumMap.entrySet()) {
                String key = entry.getKey();
                if (finalClass.equals(key)) continue;
                int val = entry.getValue();
                if (defVal > val) {
                    defVal = val;
                    finalClass = key;
                    if (defVal == 0) {
                        break;
                    }
                }
            }
            classList.add(finalClass);
        }

        if (1 == flag) {
            // 判断当前学生的特殊属性是不是和数据库的特殊属性已经冲突
            // 冲突的属性就是判断当前平均值[不是所有平均] 和 当前班级的值
            ZsStudent queryObj = new ZsStudent();
            queryObj.setTaskId(taskId);

            List<ZsStudent> speacialList = null;
            if ("1".equals(student.getSfjs())) { // 是否军属
                queryObj.setSfjs(student.getSfjs());
            } else if ("1".equals(student.getSfjszn())) { // 教师子女
                queryObj.setSfjszn(student.getSfjszn());
            } else if ("1".equals(student.getSfsbjd())) { // 随便就读
                queryObj.setSfsbjd(student.getSfsbjd());
            } else if ("1".equals(student.getSfsqzn())) { // 随迁子女
                queryObj.setSfsqzn(student.getSfsqzn());
            } else if ("1".equals(student.getSfcm())) { // 重名
                queryObj.setSfcm(student.getSfcm());
            } else if ("1".equals(student.getSfwjzn())) { // 外籍子女
                queryObj.setSfwjzn(student.getSfwjzn());
            } else if ("0".equals(student.getSfdkxx())) { // 非对口学校
                queryObj.setSfdkxx(student.getSfdkxx());
            }
            speacialList = zsStudentService.findSpacialStudent(queryObj);
            if (null == speacialList || speacialList.size() == 0) { // 无特殊学生
                String classKey = classList.get(0);
                finalClass = classKey;
                int defVal = studentNumMap.get(classKey);
                for (String key : classList) {
                    if (key.equals(classKey)) continue;
                    int v = studentNumMap.get(key);
                    if (v == 0) {
                        finalClass = key;
                        break;
                    } else if (defVal > v) {
                        defVal = v;
                        finalClass = key;
                    }
                }
            } else {  // 有特殊学生
                Map<String, Integer> spacialClass = new HashMap<String, Integer>();
                for (ZsStudent stu : speacialList) {
                    if (StringUtils.isEmpty(stu.getBj())) continue;
                    Integer count = spacialClass.get(stu.getBj());
                    if (null == count) {
                        count = 0;
                    }
                    spacialClass.put(stu.getBj(), ++count);
                }

                Map<String, Integer> allSpacialCalss = new HashMap<String, Integer>();
                initStudentMap(allSpacialCalss, String.valueOf(maxClass));
                for (Map.Entry<String, Integer> entry : allSpacialCalss.entrySet()) {
                    String key = entry.getKey();
                    Integer val = spacialClass.get(key);
                    if (null != val && val > 0) {
                        allSpacialCalss.put(key, val);
                    }
                }

                int index = 0;
                int minVal = 0;
                // 找到对应属性人数最少的
                for (Map.Entry<String, Integer> classEntry : allSpacialCalss.entrySet()) {
                    String classKey = classEntry.getKey();
                    Integer studentVal = classEntry.getValue();
                    if (null == studentVal || studentVal == 0) { // 当前班级为0个，直接返回
                        finalClass = classKey;
                        break;
                    } else if (index == 0) { // 第一次循环
                        finalClass = classKey;
                        minVal = studentVal;
                        if (minVal == 0) {
                            break;
                        }
                        index++;
                    } else if (minVal > studentVal) {
                        minVal = studentVal;
                        finalClass = classKey;
                        if (minVal == 0) {
                            break;
                        }
                    }
                }
            }

        } else { // 普通学生
            if (UserUtils.getUser().getSchool().getXxlx().equals("1")) {
                finalClass = classList.get(0);
                int minVal = studentNumMap.get(finalClass);
                for (String eachClassKey : classList) {
                    if (finalClass.equals(eachClassKey)) continue;
                    int stuVal = studentNumMap.get(eachClassKey);
                    if (minVal > stuVal) {
                        minVal = stuVal;
                        finalClass = eachClassKey;
                        if (minVal == 0) {
                            break;
                        }
                    }
                }
            } else {
                // 人数少于其他两个，有限放在最少人数那里，否则，优先放在需要填补成绩的那个班里
                ZsStudent queryObj = new ZsStudent();
                queryObj.setTaskId(taskId);
                List<ZsStudent> allStudent = zsStudentService.findAlreadyDivideClass(queryObj);

                // 平均人数
                int avgStudentNum = 0;
                int sum = 0;
                for (Map.Entry<String, Integer> entry : studentNumMap.entrySet()) {
                    Integer count = entry.getValue();
                    sum += count;
                }
                avgStudentNum = (sum + 1) / maxClass;

                boolean studentNumFirst = false;
                int min = 0;
                int max = 0;

                String minClassKey = "1";
                int num = 0;
                for (Map.Entry<String, Integer> entry : studentNumMap.entrySet()) {
                    String key = entry.getKey();
                    Integer count = entry.getValue();
                    if (num == 0) {
                        min = count;
                        max = count;
                        minClassKey = key;
                        num++;
                    } else {
                        if (count > max) {
                            max = count;
                        }
                        if (count < min) {
                            min = count;
                            minClassKey = key;
                        }
                    }
                }
                if (max - min >= 2) {
                    studentNumFirst = true;
                    finalClass = minClassKey;
                }
                if (!studentNumFirst) { // 当前学生应该放在那个班级

                    Map<String, Map<String, Integer>> studentZFMap = new HashMap<String, Map<String, Integer>>();
                    for (ZsStudent zsStudent : allStudent) {
                        Map<String, Integer> map = studentZFMap.get(zsStudent.getBj());
                        if (null == map) {
                            map = new HashMap<String, Integer>();
                            studentZFMap.put(zsStudent.getBj(), map);
                        }
                        Integer count = map.get(zsStudent.getZf());
                        if (null != count && count > 0) {
                            map.put(zsStudent.getZf(), ++count);
                        } else {
                            map.put(zsStudent.getZf(), 1);
                        }
                    }

                    // 因为一开始可能有些班级没有abc成绩
                    // 所以new一个map，从有的里面取，如果取不到，则新建map并且初始化，直接put到abcCountMap中
                    Map<String, Map<String, Integer>> abcCountMap = new HashMap<String, Map<String, Integer>>();
                    for (int index = 1; index <= maxClass; index++) {
                        // 每一个班级的信息
                        Map<String, Integer> cm = studentZFMap.get(index);
                        if (null == cm) {
                            cm = new HashMap<String, Integer>();
                            cm.put(DividePro.A, 0);
                            cm.put(DividePro.B, 0);
                            cm.put(DividePro.C, 0);
                        }
                        abcCountMap.put(String.valueOf(index), cm);
                    }

                    // 找到最需需要 ZF 的班级
                    if (DividePro.A.equals(student.getZf())) {
                        // 1. 循环所有班级，取当前值
                        // 1. 找到 ZF 最少的班级，返回
                        finalClass = finalClassKey(abcCountMap, DividePro.A);
                    } else if (DividePro.B.equals(student.getZf())) {
                        finalClass = finalClassKey(abcCountMap, DividePro.B);
                    } else if (DividePro.C.equals(student.getZf())) {
                        finalClass = finalClassKey(abcCountMap, DividePro.C);
                    }
                }
            }
        }

        return finalClass;
    }

    private String finalClassKey(Map<String, Map<String, Integer>> abcCountMap,
                                 String propertyKey) {

        String finalClass = "1";
        if (null == abcCountMap) return finalClass;

        int minZF = 0;
        int num = 0;
        for (Map.Entry<String, Map<String, Integer>> entry : abcCountMap.entrySet()) {
            String classKey = entry.getKey();
            Map<String, Integer> classProperty = entry.getValue();
            Integer v = classProperty.get(propertyKey);
            if (null == v) {
                v = 0;
            }
            if (num == 0) {
                minZF = v;
                finalClass = classKey;
                num++;
            } else {
                if (v == 0) {
                    finalClass = classKey;
                    break;
                } else if (minZF > v) {
                    minZF = v;
                    finalClass = classKey;
                }
            }

        }
        return finalClass;
    }

    private void addStudentToMapList(Map<String, List<ZsStudent>> map, ZsStudent student, String defaultKey, String sex) {
        String key = defaultKey + sex;
        if (DividePro.A.equals(student.getZf())) {
            List<ZsStudent> list = map.get(key + DividePro.A);
            if (null == list) {
                list = new ArrayList<ZsStudent>();
                map.put(key + DividePro.A, list);
            }
            list.add(student);
        } else if (DividePro.B.equals(student.getZf())) {
            List<ZsStudent> list = map.get(key + DividePro.B);
            if (null == list) {
                list = new ArrayList<ZsStudent>();
                map.put(key + DividePro.B, list);
            }
            list.add(student);
        } else if (DividePro.C.equals(student.getZf())) {
            List<ZsStudent> list = map.get(key + DividePro.C);
            if (null == list) {
                list = new ArrayList<ZsStudent>();
                map.put(key + DividePro.C, list);
            }
            list.add(student);
        }
    }

    private void initStudentMap(Map<String, Integer> map, String bjCount) {
        int num = Integer.parseInt(bjCount);
        for (int i = 1; i <= num; i++) {
            map.put(String.valueOf(i), 0);
        }
    }

    private Map<String, List<ZsStudent>> fetchAllSpecialStudent(List<ZsStudent> allList, List<String> condition,
                                                                Map<String, Integer> weightMap,
                                                                Map<String, Integer> studentNumMap,
                                                                String bjCount) {
        List<ZsStudent> twinsList = null;
        List<ZsStudent> sqznList = null;
        List<ZsStudent> deformityList = null;
        List<ZsStudent> cmList = null;
        List<ZsStudent> armysList = null;
        List<ZsStudent> tchildList = null;
        List<ZsStudent> wjchildList = null;

        for (int j = 0; j < allList.size(); j++) {
            ZsStudent student = allList.get(j);
            // 双胞胎
            if (condition.contains("twins")) {
                List<ZsStudent> l = findStudent(student.getSfsbt(), student, twinsList);
                if (null == twinsList) {
                    twinsList = l;
                }
            }
            // 随迁子女
            if (condition.contains("sqzn") && !"1".equals(student.getSfsbt())) {
                List<ZsStudent> l = findStudent(student.getSfsqzn(), student, sqznList);
                if (null == sqznList && null != l && l.size() > 0) {
                    sortGender(l);
                    sqznList = l;
                }
            }
            // 随班就读
            if (condition.contains("deformity") && !"1".equals(student.getSfsbt()) && !"1".equals(student.getSfsqzn())) {
                List<ZsStudent> l = findStudent(student.getSfsbjd(), student, deformityList);
                if (null == deformityList && null != l && l.size() > 0) {
                    sortGender(l);
                    deformityList = l;
                }
            }
            // 重名 xmpy
            if (condition.contains("cm") && !"1".equals(student.getSfsbt()) &&
                    !"1".equals(student.getSfsqzn()) && !"1".equals(student.getSfsbjd())) {
                List<ZsStudent> l = findStudent(student.getSfcm(), student, cmList);
                if (null == cmList && null != l && l.size() > 0) {
                    sortGender(l);
                    cmList = l;
                }
            }
            // 军人子女
            if (condition.contains("armys") && !"1".equals(student.getSfsbt()) &&
                    !"1".equals(student.getSfsqzn()) && !"1".equals(student.getSfsbjd()) &&
                    !"1".equals(student.getSfcm())) {
                List<ZsStudent> l = findStudent(student.getSfjs(), student, armysList);
                if (null == armysList && null != l && l.size() > 0) {
                    sortGender(l);
                    armysList = l;
                }
            }
            // 教师子女
            if (condition.contains("tchild") && !"1".equals(student.getSfsbt()) &&
                    !"1".equals(student.getSfsqzn()) && !"1".equals(student.getSfsbjd()) &&
                    !"1".equals(student.getSfcm()) && !"1".equals(student.getSfjs())) {
                List<ZsStudent> l = findStudent(student.getSfjszn(), student, tchildList);
                if (null == tchildList && null != l && l.size() > 0) {
                    sortGender(l);
                    tchildList = l;
                }
            }
            // 外籍子女
            if (condition.contains("wjchild") && !"1".equals(student.getSfsbt()) &&
                    !"1".equals(student.getSfsqzn()) && !"1".equals(student.getSfsbjd()) &&
                    !"1".equals(student.getSfcm()) && !"1".equals(student.getSfjs()) && !"1".equals(student.getSfjszn())) {
                List<ZsStudent> l = findStudent(student.getSfwjzn(), student, wjchildList);
                if (null == wjchildList && null != l && l.size() > 0) {
                    sortGender(l);
                    wjchildList = l;
                }
            }
        } // 特殊的找完

        if (null != twinsList && !twinsList.isEmpty()) {
            allList.removeAll(twinsList);
        }
        if (null != sqznList && !sqznList.isEmpty()) {
            allList.removeAll(sqznList);
        }
        if (null != deformityList && !deformityList.isEmpty()) {
            allList.removeAll(deformityList);
        }
        if (null != cmList && !cmList.isEmpty()) {
            allList.removeAll(cmList);
        }
        if (null != armysList && !armysList.isEmpty()) {
            allList.removeAll(armysList);
        }
        if (null != tchildList && !tchildList.isEmpty()) {
            allList.removeAll(tchildList);
        }
        if (null != wjchildList && !wjchildList.isEmpty()) {
            allList.removeAll(wjchildList);
        }

        int classNum = 1;
        // 双胞胎分班
        if (null != twinsList) {

            for (ZsStudent s : twinsList) {
                String bj = s.getBj();
                if (!org.springframework.util.StringUtils.isEmpty(bj)) continue;

                String twinsNum = s.getSbtxh();
                List<ZsStudent> sameList = new ArrayList<ZsStudent>();

                for (ZsStudent s1 : twinsList) {
                    if (s.getId().equals(s1.getId())) continue;

                    String twinsNum1 = s1.getSbtxh();
                    if (twinsNum.equals(twinsNum1) && org.springframework.util.StringUtils.isEmpty(s1.getBj())) {
                        sameList.add(s1);
                    }
                }

                String keyClass = String.valueOf(classNum);
                s.setBj(keyClass);
                zsStudentService.update(s);


                if (sameList.size() > 0) {
                    for (ZsStudent same : sameList) {
                        same.setBj(String.valueOf(classNum));
                        zsStudentService.update(same);

                        Integer val = weightMap.get(String.valueOf(classNum));
                        if ("1".equals(s.getXsxb())) {
                            weightMap.put(String.valueOf(classNum), ++val);
                        } else {
                            weightMap.put(String.valueOf(classNum), --val);
                        }
                    }
                }

                Integer val = weightMap.get(String.valueOf(classNum));
                if ("1".equals(s.getXsxb())) {
                    weightMap.put(String.valueOf(classNum), ++val);
                } else {
                    weightMap.put(String.valueOf(classNum), --val);
                }

                int plus = 0;
                if (null != sameList && sameList.size() > 0) {
                    plus = sameList.size();
                }
                studentNumMap.put(String.valueOf(classNum), plus + 1);

                classNum = addClass(classNum, Integer.parseInt(bjCount));
            }
        }

        Map<String, List<ZsStudent>> rstMap = new HashMap<String, List<ZsStudent>>();
        if (null != sqznList) {
            rstMap.put(DividePro.SQZN, sqznList);
        }
        if (null != deformityList) {
            rstMap.put(DividePro.SBJD, deformityList);
        }
        if (null != cmList) {
            rstMap.put(DividePro.CM, cmList);
        }
        if (null != armysList) {
            rstMap.put(DividePro.JS, armysList);
        }
        if (null != tchildList) {
            rstMap.put(DividePro.JSZN, tchildList);
        }
        if (null != wjchildList) {
            rstMap.put(DividePro.WJZN, wjchildList);
        }

        return rstMap;
    }

    private void sortGender(List<ZsStudent> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                ZsStudent temp = null;
                if (list.get(i).getXsxb().compareTo(list.get(j).getXsxb()) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    private int addClass(int classNum, int maxClass) {

        if (maxClass > 1) {
            classNum++;
            if (classNum > maxClass && maxClass > 1) {
                classNum = 1;
            }
        }
        return classNum;
    }

    private List<ZsStudent> findStudent(String condition, ZsStudent student, List<ZsStudent> toList) {
        if ("1".equals(condition)) {
            if (null == toList) {
                toList = new ArrayList<ZsStudent>();
            }
            toList.add(student);
        }
        return toList;
    }

    /**
     * 查看分班结果
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:list")
    @RequestMapping(value = "devideResult")
    public String devideResult(ZsStudent zsStudent, HttpServletResponse response, HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Model model) {
        try {
            String taskId = request.getParameter("taskId");
            String bjCount = request.getParameter("bjCount");
            String currentBj = request.getParameter("currentBj");
            if (StringUtils.isEmpty(bjCount)) {// 根据任务id查询分班数量
                bjCount = String.valueOf(zsStudentService.queryClassCount(taskId));
            }
            if (StringUtils.isEmpty(currentBj)) {
                zsStudent.setBj("1");
                model.addAttribute("currentBj", "1");
            } else {
                zsStudent.setBj(currentBj);
                model.addAttribute("currentBj", currentBj);
            }
            zsStudent.setTaskId(taskId);
            zsStudent.setXsxb(ZsTaskConstant.MALE);
            Page<ZsStudent> male = zsStudentService.findPage(new Page<ZsStudent>(request, response), zsStudent);
            zsStudent.setXsxb(ZsTaskConstant.FEMALE);
            Page<ZsStudent> female = zsStudentService.findPage(new Page<ZsStudent>(request, response), zsStudent);
            model.addAttribute("bjCount", bjCount);
            model.addAttribute("zsTask", zsTaskService.get(taskId));
            model.addAttribute("male", male);
            model.addAttribute("female", female);
            ZsStudent all = new ZsStudent();
            all.setBj(zsStudent.getBj());
            all.setTaskId(taskId);
            List<ZsStudent> stuList = zsStudentService.findAllList(all);
            model.addAttribute("stuList", stuList);
        } catch (Exception e) {
            logger.error("查询分班结果失败:", e);
            addMessage(redirectAttributes, "查询分班结果失败:" + e.getMessage());
        }
        return "zsfb/third";
    }

    /**
     * 打印分班结果
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("zsfb:zsStudent:list")
    @RequestMapping(value = "printResult")
    public String printResult(ZsStudent zsStudent, HttpServletResponse response, HttpServletRequest request,
                              RedirectAttributes redirectAttributes, Model model) {
        try {
            String taskId = request.getParameter("taskId");
            String currentBj = request.getParameter("currentBj");
            if (StringUtils.isEmpty(currentBj)) {
                zsStudent.setBj("1");
                model.addAttribute("currentBj", "1");
            } else {
                zsStudent.setBj(currentBj);
                model.addAttribute("currentBj", currentBj);
            }
            model.addAttribute("zsTask", zsTaskService.get(taskId));
            zsStudent.setXsxb(ZsTaskConstant.MALE);
            List<ZsStudent> male = zsStudentService.findAllList(zsStudent);
            zsStudent.setXsxb(ZsTaskConstant.FEMALE);
            List<ZsStudent> female = zsStudentService.findAllList(zsStudent);
            model.addAttribute("zsTask", zsTaskService.get(taskId));
            model.addAttribute("male", male);
            model.addAttribute("female", female);
            model.addAttribute("currentBj", currentBj);
            ZsStudent all = new ZsStudent();
            all.setBj(zsStudent.getBj());
            all.setTaskId(taskId);
            List<ZsStudent> stuList = zsStudentService.findAllList(all);
            model.addAttribute("stuList", stuList);
        } catch (Exception e) {
            logger.error("查询分班结果失败:", e);
            addMessage(redirectAttributes, "查询分班结果失败:" + e.getMessage());
        }
        return "zsfb/print";
    }

    /**
     * 对分班结果做调整
     *
     * @param bjCount
     * @param taskId
     */
    private void doOptimizeResult(String bjCount, String taskId) {
        try {
            Integer classNum = Integer.parseInt(bjCount);
            ZsStudent queryObj = new ZsStudent();
            queryObj.setXsxb(ZsTaskConstant.MALE);
            queryObj.setTaskId(taskId);
            List<ZsStudent> male = zsStudentService.findAllList(queryObj);
            queryObj.setXsxb(ZsTaskConstant.FEMALE);
            List<ZsStudent> female = zsStudentService.findAllList(queryObj);
            //男女平均数
            int avgMale = male.size() / classNum;
            if (male.size() % classNum != 0) avgMale += 1;
            int avgFemale = female.size() / classNum;
            if (female.size() % classNum != 0) avgFemale += 1;

            Map<String, Map<String, Integer>> sex = new HashMap<String, Map<String, Integer>>();
            for (ZsStudent student : male) {
                String classStr = student.getBj();
                Map<String, Integer> map = sex.get(classStr);
                if (null == map) {
                    map = new HashMap<String, Integer>();
                    sex.put(classStr, map);
                }
                Integer count = map.get("male");
                if (null != count && count > 0) {
                    ++count;
                } else {
                    count = 1;
                }
                map.put("male", count);
            }

            for (ZsStudent student : female) {
                String classStr = student.getBj();
                Map<String, Integer> map = sex.get(classStr);
                if (null == map) {
                    map = new HashMap<String, Integer>();
                    sex.put(classStr, map);
                }
                Integer count = map.get("female");
                if (null != count && count > 0) {
                    ++count;
                } else {
                    count = 1;
                }
                map.put("female", count);
            }
            //循环每个班级map
            for (Map.Entry<String, Map<String, Integer>> entry : sex.entrySet()) {

                //当前班级
                String cls = entry.getKey();
                //当前班级属性值
                Map<String, Integer> map = entry.getValue();

                Integer _maleNum = map.get("male");
                int maleNum = 0;
                if (null != _maleNum) {
                    maleNum = _maleNum;
                }

                if (avgMale == maleNum || avgMale - maleNum == 1) {
                    continue;
                }
                if (avgMale - maleNum > 1) { // 男生少于平均数

                    for (Map.Entry<String, Map<String, Integer>> _entry : sex.entrySet()) {
                        String _cls = _entry.getKey();
                        if (_cls.equals(cls)) continue;
                        Map<String, Integer> _target = _entry.getValue();
                        //目标班级男生要多
                        Integer _maleN = _target.get("male");
                        int maleN = 0;
                        if (null != _maleN) {
                            maleN = _maleN;
                        }
                        //少于或者等于平均数的都跳过
                        if (avgMale - maleN >= 0) {
                            continue;
                        }

                        //女生人数要少
                        Integer _femaleN = _target.get("female");
                        int femaleN = 0;
                        if (null != _femaleN) {
                            femaleN = _femaleN;
                        }
                        //等于平均数或多于平均数的跳过
                        if (femaleN - avgFemale >= 0) {
                            continue;
                        }

                        int number = avgMale - maleNum - 1;
                        boolean ifContinue = true;
                        for (int i = 0; i < number && ifContinue; i++) {
                            ZsStudent changeObj = new ZsStudent();
                            changeObj.setBj(cls);
                            changeObj.setXsxb(ZsTaskConstant.FEMALE);
                            changeObj.setTaskId(taskId);
                            List<ZsStudent> changeList = zsStudentService.findNormalStudent(changeObj);

                            for (int j = 0; j < changeList.size(); j++) {
                                ZsStudent fromObj = changeList.get(j);

                                ZsStudent toObj = new ZsStudent();
                                toObj.setBj(_cls);
                                toObj.setTaskId(taskId);
                                toObj.setZf(fromObj.getZf());
                                toObj.setXsxb(ZsTaskConstant.MALE);

                                List<ZsStudent> targetList = zsStudentService.findNormalStudent(toObj);

                                if (null != targetList && targetList.size() > 0) {
                                    fromObj.setBj(_cls);
                                    targetList.get(0).setBj(cls);

                                    Map<String, Integer> f = sex.get(cls);
                                    int tempMale = f.get("male");
                                    int tempFemale = f.get("female");
                                    f.put("male", ++tempMale);
                                    f.put("female", --tempFemale);
                                    Map<String, Integer> t = sex.get(_cls);
                                    tempMale = t.get("male");
                                    tempFemale = t.get("female");
                                    t.put("male", --tempMale);
                                    t.put("female", ++tempFemale);
                                    zsStudentService.update(fromObj);
                                    zsStudentService.update(targetList.get(0));
                                    if (avgMale - tempMale == 0 || avgMale - tempMale == 1) {
                                        ifContinue = false;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else if (avgMale - maleNum < 0) { // 男生多了

                    for (Map.Entry<String, Map<String, Integer>> _entry : sex.entrySet()) {
                        String _cls = _entry.getKey();
                        if (_cls.equals(cls)) continue;
                        Map<String, Integer> _target = _entry.getValue();
                        //目标班级男生要少
                        Integer _maleN = _target.get("male");
                        int maleN = 0;
                        if (null != _maleN) {
                            maleN = _maleN;
                        }
                        //男生多于或等于平均值的跳过
                        if (avgMale - maleN <= 0) {
                            continue;
                        }
                        //目标班级女生要多
                        Integer _femaleN = _target.get("female");
                        int femaleN = 0;
                        if (null != _femaleN) {
                            femaleN = _femaleN;
                        }
                        //女生少于或等于平均值的跳过
                        if (femaleN - avgFemale <= 0) {
                            continue;
                        }

                        int number = maleNum - avgMale - 1;
                        boolean ifContinue = true;
                        for (int i = 0; i < number && ifContinue; i++) {
                            ZsStudent changeObj = new ZsStudent();
                            changeObj.setBj(cls);
                            changeObj.setXsxb(ZsTaskConstant.MALE);
                            changeObj.setTaskId(taskId);
                            List<ZsStudent> changeList = zsStudentService.findNormalStudent(changeObj);

                            for (int j = 0; j < changeList.size(); j++) {
                                ZsStudent fromObj = changeList.get(j);

                                ZsStudent toObj = new ZsStudent();
                                toObj.setBj(_cls);
                                toObj.setTaskId(taskId);
                                toObj.setZf(fromObj.getZf());
                                toObj.setXsxb(ZsTaskConstant.FEMALE);

                                List<ZsStudent> targetList = zsStudentService.findNormalStudent(toObj);

                                if (null != targetList && targetList.size() > 0) {
                                    fromObj.setBj(_cls);
                                    targetList.get(0).setBj(cls);

                                    Map<String, Integer> f = sex.get(cls);
                                    int tempMale = f.get("male");
                                    int tempFemale = f.get("female");
                                    f.put("male", --tempMale);
                                    f.put("female", ++tempFemale);
                                    Map<String, Integer> t = sex.get(_cls);
                                    tempMale = t.get("male");
                                    tempFemale = t.get("female");
                                    t.put("male", ++tempMale);
                                    t.put("female", --tempFemale);
                                    zsStudentService.update(fromObj);
                                    zsStudentService.update(targetList.get(0));
                                    if (avgMale - tempMale == 0 || avgMale - tempMale == 1) {
                                        ifContinue = false;
                                    }
                                    break;
                                }
                            }
                        }
                    }

                }
            }

            Map<String, Map<String, Integer>> finalMap = new HashMap<String, Map<String, Integer>>();
            for (Map.Entry<String, Map<String, Integer>> entry : sex.entrySet()) {
                String cls = entry.getKey();
                Map<String, Integer> map = entry.getValue();
                Integer _maleNum = map.get("male");
                int maleNum = 0;
                if (null != _maleNum) {
                    maleNum = _maleNum;
                }

                if (avgMale - maleNum <= 1) { // 符合条件
                    continue;
                }

                finalMap.put(cls, map);
            }

            // 如果男的少了,找到当前班级的女生用于交换
            for (Map.Entry<String, Map<String, Integer>> entry : finalMap.entrySet()) {
                String cls = entry.getKey();
                ZsStudent changeObj = new ZsStudent();
                changeObj.setBj(cls);
                changeObj.setXsxb(ZsTaskConstant.FEMALE);
                changeObj.setTaskId(taskId);
                List<ZsStudent> changeList = zsStudentService.findNormalStudent(changeObj);
                if (null != changeList && changeList.size() > 0) {

                    ZsStudent fromObj = changeList.get(0);
                    boolean ifContinue = true;

                    // 找到其他班级男生为平局值的
                    for (Map.Entry<String, Map<String, Integer>> _entry : sex.entrySet()) {
                        if (ifContinue) {
                            String _cls = _entry.getKey();
                            if (_cls.equals(cls)) {
                                continue;
                            }

                            Map<String, Integer> _map = _entry.getValue();
                            Integer _maleNum = _map.get("male");
                            int maleN = 0;
                            if (null != _maleNum) {
                                maleN = _maleNum;
                            }

                            if (maleN == avgMale) {
                                ZsStudent toObj = new ZsStudent();
                                toObj.setBj(_cls);
                                toObj.setTaskId(taskId);
                                toObj.setZf(fromObj.getZf());
                                toObj.setXsxb(ZsTaskConstant.MALE);

                                List<ZsStudent> targetList = zsStudentService.findNormalStudent(toObj);

                                if (null != targetList && targetList.size() > 0) {
                                    fromObj.setBj(_cls);
                                    targetList.get(0).setBj(cls);

                                    Map<String, Integer> f = sex.get(cls);
                                    int tempMale = f.get("male");
                                    int tempFemale = f.get("female");
                                    f.put("male", ++tempMale);
                                    f.put("female", --tempFemale);
                                    int orgMale = tempMale;

                                    Map<String, Integer> t = sex.get(_cls);
                                    tempMale = t.get("male");
                                    tempFemale = t.get("female");
                                    t.put("male", --tempMale);
                                    t.put("female", ++tempFemale);

                                    zsStudentService.update(fromObj);
                                    zsStudentService.update(targetList.get(0));
                                    changeList.remove(fromObj);

                                    if (avgMale - orgMale <= 1) {
                                        ifContinue = false;
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

