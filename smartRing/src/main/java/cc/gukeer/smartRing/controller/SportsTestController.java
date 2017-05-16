package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.*;
import cc.gukeer.common.utils.excel.ExportExcel;
import cc.gukeer.common.utils.excel.ImportExcel;
import cc.gukeer.smartRing.common.MapKeyComparator;
import cc.gukeer.smartRing.modelView.A_ScoreMapRuleView;
import cc.gukeer.smartRing.modelView.A_StuScoreView;
import cc.gukeer.smartRing.modelView.ScoreMapRuleView;
import cc.gukeer.smartRing.modelView.StuScoreView;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.service.*;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import static cc.gukeer.common.utils.ConstantUtil.getKeyByValueAndFlag;
import static cc.gukeer.common.utils.ConstantUtil.getValueByKeyAndFlag;

@Controller
@RequestMapping(value = "/sport")
public class SportsTestController extends BasicController {

    @Autowired
    SportsTestService sportsService;
    @Autowired
    GradeClassService gradeClassService;
    @Autowired
    SportClassService sportClassService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentClassService studentClassService;

    /*
    * 体育测试》成绩管理
    * param:request,model
    * method:get
    * */
    @RequestMapping(value = "/scoremange/index", method = RequestMethod.GET)
    public String ruleIndex(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        User user = getLoginUser();
        String teacherId = user.getRefId();

        String whichTap = getParamVal(request, "whichTap");
        if (StringUtils.isEmpty(whichTap)) {
            whichTap = "1";//默认选择第一个tap

        }

        List<SportItem> sportItemList = sportsService.getAllItem(user);
        List<Integer> testCxList = sportsService.getAllTestCount(user);
        List<String> njList = sportsService.getAllNj(user);

        //成绩修改/查看  ----------------------------------------------------------------------------------tap1
        String itemName = URLDecoder.decode(getParamVal(request, "itemName"), "UTF-8");
        String testCount = getParamVal(request, "testCount");
        String stuNameOrNo = URLDecoder.decode(getParamVal(request, "stuNameOrNo"), "UTF-8");
        int pageSize = NumberConvertUtil.convertS2I(getParamVal(request, "pageSize"));
        int pageNum = NumberConvertUtil.convertS2I(getParamVal(request, "pageNum"));
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id


        Map njBjMap = getNjBj(nj);
        Object njId1 = njBjMap.get("njId");
        Object xdId1 = njBjMap.get("xdId");
        Object njList1 = njBjMap.get("njList");
        Object bjList1 = njBjMap.get("bjList");
        nj = njBjMap.get("nj").toString();

        if (StringUtils.isEmpty(itemName) && sportItemList.size() > 0) {
            itemName = sportItemList.get(0).getItemName();
        }
        if (StringUtils.isEmpty(testCount) && testCxList.size() > 0) {
            testCount = testCxList.get(0).toString();
        }


        Map params = new HashMap();
        params.put("itemName", "%" + itemName + "%");
        params.put("testCount", testCount);

        if ("全部".equals(nj.trim())) {
            bjList1 = null;
        } else {
            params.put("xdId", xdId1);
            params.put("njId", njId1);
            params.put("oneClass", bj);
        }
        if (!StringUtils.isEmpty(stuNameOrNo)) {
            params.put("stuNameOrNo", "%" + stuNameOrNo + "%");
        }
        params.put("schoolId", user.getSchoolId());
        params.put("classId", sportsService.getClassByTeacher(teacherId));//根据当前身份查询班级id集合
        params.put("pageSize", pageSize);
        params.put("pageNum", pageNum);
        params.put("scoreType", 0);//查询体育测试的成绩列表

        PageInfo<Map> pageInfo = sportsService.findScoreList(params);
        List<Map> scoreList = pageInfo.getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("bjList1", bjList1);
        model.addAttribute("njList1", njList1);
        model.addAttribute("xdId1", xdId1);
        model.addAttribute("njId1", njId1);
        //保存搜索参数
        model.addAttribute("itemName", itemName);
        model.addAttribute("stuNameOrNo", stuNameOrNo);
        model.addAttribute("testCount", testCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("chooseNj", nj);
        model.addAttribute("chooseBj", bj);


        //成绩映射规则  --------------------------------------------------------------------tap2
        String mapItemName = getParamVal(request, "mapItemName");
        String gradeName = getParamVal(request, "gradeName");
        String gender = getParamVal(request, "gender");
        int pageSize2 = NumberConvertUtil.convertS2I(getParamVal(request, "pageSize2"));
        int pageNum2 = NumberConvertUtil.convertS2I(getParamVal(request, "pageNum2"));
        String schoolId = user.getSchoolId();
        mapItemName = URLDecoder.decode(mapItemName, "UTF-8");//解决非post访问的中文乱码问题。
        gradeName = URLDecoder.decode(gradeName, "UTF-8");//解决非post访问的中文乱码问题。

        if (StringUtils.isEmpty(mapItemName) && sportItemList.size() > 0) {
            mapItemName = sportItemList.get(0).getItemName();
        }
        if (StringUtils.isEmpty(gender)) {
            gender = "1";
        }
        if (StringUtils.isEmpty(gradeName) && njList.size() > 0) {
            gradeName = njList.get(0);
        }


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("itemName", mapItemName);
        paramMap.put("gradeName", gradeName);
        paramMap.put("gender", gender);
        paramMap.put("pageSize", pageSize2);
        paramMap.put("pageNum", pageNum2);
        paramMap.put("schoolId", schoolId);

        PageInfo pageInfo2 = sportsService.findScoreMapRuleByCriteria(paramMap);
        List<ScoreMapRule> scoreMapRuleList = pageInfo2.getList();

        scoreMapRuleList = translate(scoreMapRuleList);

        model.addAttribute("pageInfo2", pageInfo2);
        model.addAttribute("scoreMapRuleList", scoreMapRuleList);

        //保存搜索参数
        model.addAttribute("gradeName", gradeName);
        model.addAttribute("gender", gender);
        model.addAttribute("mapItemName", mapItemName);
        model.addAttribute("pageSize2", pageSize2);

        //所有测试项目
        model.addAttribute("sportItemList", sportItemList);
        //所有测试次序
        model.addAttribute("testCxList", testCxList);
        //所有年级
        model.addAttribute("njList", njList);
        //tapIndex
        model.addAttribute("whichTap", whichTap);


        //查看测试项目
        int pageSize3 = NumberConvertUtil.convertS2I(getParamVal(request, "pageSize3"));
        int pageNum3 = NumberConvertUtil.convertS2I(getParamVal(request, "pageNum3"));
        Map map = new HashMap();
        map.put("pageSize", pageSize3);
        map.put("pageNum", pageNum3);
        model.addAttribute("pageSize3", pageSize3);
        model.addAttribute("pageNum3", pageNum3);
        PageInfo pageInfo3 = sportsService.findSportItem(map);
        List<SportItem> sportItems = pageInfo3.getList();
        model.addAttribute(sportItems);
        model.addAttribute("sportItems", sportItems);
        model.addAttribute("pageInfo3", pageInfo3);
        return "sport/scoreManage";
    }

    //    体育测试，成绩修改查看，新增成绩页面
    @RequestMapping(value = "/score/add/index", method = RequestMethod.GET)
    public String scoreAddIndex(Model model) {
        User user = getLoginUser();
        List<SportItem> sportItemList = sportsService.getAllItem(user);

        model.addAttribute("sportItemList", sportItemList);
        return "sport/popPage/scoreAdd";
    }

    //    体育测试，成绩修改查看，修改成绩页面
    @RequestMapping(value = "/score/edit/index", method = RequestMethod.GET)
    public String scoreEditIndex(HttpServletRequest request, Model model) {
        String testSeq = getParamVal(request, "testSeq");
        Map scoreDetail = sportsService.getScoreByPrimary(testSeq);

        model.addAttribute("scoreDetail", scoreDetail);
        return "sport/popPage/scoreEdit";
    }

    //    体育测试，成绩修改查看，导入成绩页面
    @RequestMapping(value = "/score/import/index", method = RequestMethod.GET)
    public String scoreImportIndex() {
        return "sport/popPage/scoreImport";
    }

    /**
     * 添加/修改 体育测试项目
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/item/add", method = RequestMethod.POST)
    public ResponseEntity itemAdd(HttpServletRequest request) {
        String item_name = getParamVal(request, "itemName");
        String item_unit = getParamVal(request, "itemUnit");
        String item_id = getParamVal(request, "itemId");

        Map res = new HashMap();
        if (this.sportsService.itemExist(item_name)) {
            res.put("code", "0");
            res.put("msg", "项目已经存在");
        } else {
            SportItem item = new SportItem();
            item.setItemId(item_id);
            item.setItemName(item_name);
            item.setItemUnit(item_unit);
            this.sportsService.saveSportItem(item, getLoginUser());
            res.put("code", "0");
            res.put("msg", "添加完成");
        }

        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 删除 学生成绩
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/delete", method = RequestMethod.POST)
    public void stuScoreDelete(HttpServletRequest request) {
        String testSeq = getParamVal(request, "testSeq");
        StuScoreRef stuScoreRef = new StuScoreRef();
        stuScoreRef.setDelFlag(1);
        stuScoreRef.setTestSeq(testSeq);

        sportsService.saveStuScore(stuScoreRef);
    }

    /**
     * 添加/修改 学生成绩
     * param: request
     * method:POST
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/stuScore/add", method = RequestMethod.POST)
    public ResponseEntity stuScoreAdd(HttpServletRequest request) {
        Map<String, String> sportItemMap = getItemMap();
        User user = getLoginUser();

        String testSeq = getParamVal(request, "testSeq");
        int stuNo = NumberConvertUtil.convertS2I(getParamVal(request, "stuNo"));
        int testCount = NumberConvertUtil.convertS2I(getParamVal(request, "testCount"));
        String itemName = getParamVal(request, "itemName");
        String testDate = getParamVal(request, "testDate");
        String testMark = getParamVal(request, "testMark");
        String itemUnit = getParamVal(request, "itemUnit");
        Map returnMap = new HashMap();

        if (!RegexpUtil.scoreFormat(itemUnit, testMark)) {
            returnMap.put("msg", "成绩格式不正确");
            returnMap.put("code", "1");
            return new ResponseEntity(returnMap, HttpStatus.OK);
        }

        testMark = ConstantUtil.translateSecondToMi(testMark);//分秒格式的数据会转换成秒，其他格式不变
        //testMark = ConstantUtil.translateUnit(itemUnit,testMark);//分秒格式的数据会转换成秒，其他格式不变
        StuScoreRef stuScoreRef = new StuScoreRef();
        if (testCount != 0) {
            stuScoreRef.setTestId(testCount);
            stuScoreRef.setStudentNum(stuNo);
            stuScoreRef.setItemName(itemName);
            stuScoreRef.setItemId(sportItemMap.get(itemName));
        } else {
            if (StringUtils.isEmpty(testSeq)) {
                returnMap.put("msg", "测试次序格式有误");
                returnMap.put("code", "1");
                return new ResponseEntity(returnMap, HttpStatus.OK);
            }
        }
        stuScoreRef.setStudentMark(testMark);
        stuScoreRef.setCreateBy(user.getId());
        stuScoreRef.setCreateDate(testDate);
        stuScoreRef.setSchoolId(user.getSchoolId());

        if (StringUtils.isEmpty(testSeq)) {
            //成绩记录主键为空，执行新增流程（新增需要更改此学生此项目之前的成绩过期）
            testSeq = ConstantUtil.getPrimaryKey();
            stuScoreRef.setTestSeq(testSeq);
            String validate = addScoreValidate(stuScoreRef, itemUnit, 0, request);
            if (!StringUtils.isEmpty(validate)) {
                returnMap.put("msg", validate);
                returnMap.put("code", "1");
                return new ResponseEntity(returnMap, HttpStatus.OK);
            }
            sportsService.updateRefScore(stuScoreRef, getLoginUser());//此学生此项目之前成绩过期,
            sportsService.addStuScore(stuScoreRef);
        } else {
            stuScoreRef.setTestSeq(testSeq);
            String validate = addScoreValidate(stuScoreRef, itemUnit, 1, request);
            if (!StringUtils.isEmpty(validate)) {
                returnMap.put("msg", validate);
                returnMap.put("code", "1");
                return new ResponseEntity(returnMap, HttpStatus.OK);
            }
            sportsService.saveStuScore(stuScoreRef);//保存学生成绩
        }

        List<String> primList = new ArrayList<String>();
        primList.add(testSeq);
        updateStuScore(primList);//计算学生分数，等级

        returnMap.put("msg", "操作成功");
        returnMap.put("code", "0");
        return new ResponseEntity(returnMap, HttpStatus.OK);
    }

    /**
     * 下载 学生体育测试成绩 导入模板
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/download")
    public void downloadStuScore(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "体育测试成绩模板.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.测试编号必须为正整数\n" +
                    "          2.测试项目必须存在  \n" +
                    "          3.测试时间：YYYY-MM-dd,例如：2016-09-01  \n" +
                    "          4.学生学号必须存在  \n" +
                    "          5.测试成绩：非负数字，若为二级单位 例：2′10″";
            new ExportExcel("学生体育测试成绩", StuScoreView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 学生体育测试成绩导入
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/import", method = RequestMethod.POST)
    public ResponseEntity scoreImport(@RequestParam(value = "file") MultipartFile file) {
        int all = 0;
        int successCount = 0;
        long begin = System.currentTimeMillis();

        User user = getLoginUser();
        Map<String, String> sportItemMap = getItemMap();
        List<Map> validateList = new ArrayList<Map>();
        List<Map> reasonList = new ArrayList<Map>();
        try {
            ImportExcel importExcel = new ImportExcel(file, 2, 0);
            List<StuScoreView> list = importExcel.getDataList(StuScoreView.class, 1);
            List<String> primList = new ArrayList<String>();//保存插入成绩的主键列表，留作计算成绩的参数。
            all = list.size();

            for (StuScoreView stuScoreView : list) {
                Map map = new HashMap();
                map.put("mark", stuScoreView.getStu_mark());
                map.put("stuScoreView", stuScoreView);
                validateList.add(map);
            }
            Map res = batchValidate(validateList);
            reasonList = (List<Map>) res.get("reasonList");
            List<StuScoreView> regularList = (List<StuScoreView>) res.get("regular");

            List<StuScoreRef> scoreBatch = new ArrayList<StuScoreRef>();
            //插入正确格式的列表
            for (StuScoreView regular : regularList) {
                StuScoreRef stuScoreRef = new StuScoreRef();
                stuScoreRef.setTestId(regular.getTest_id());
                stuScoreRef.setStudentNum(regular.getStu_no());
                stuScoreRef.setItemName(regular.getItem_name());
                stuScoreRef.setItemId(sportItemMap.get(regular.getItem_name()));
                stuScoreRef.setStudentMark(ConstantUtil.translateSecondToMi(regular.getStu_mark()));
                stuScoreRef.setCreateBy(user.getId());
                stuScoreRef.setCreateDate(regular.getCreate_date());
                stuScoreRef.setSchoolId(user.getSchoolId());

                String prim = ConstantUtil.getPrimaryKey();
                stuScoreRef.setTestSeq(prim);
                primList.add(prim);
                sportsService.updateRefScore(stuScoreRef, getLoginUser());//更新学生成绩表
                sportsService.addStuScore(stuScoreRef);//保存学生成绩
            }
            successCount = regularList.size();
            updateStuScore(primList);//计算学生分数，成绩

        } catch (Exception e) {
            e.printStackTrace();
        }
        int code = 0;
        if (all == successCount) {
            code = 1;
        }
        long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("success", successCount);
        res.put("fail", all - successCount);
        res.put("failList", reasonList);
        res.put("code", code);
        res.put("timeUse", (end - begin) / 1000);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 学生体育测试成绩导出
     * param: request
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/export", method = RequestMethod.GET)
    public void stuScoreExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemName = getParamVal(request, "itemName");
        String testCount = getParamVal(request, "testCount");
        String stuNameOrNo = getParamVal(request, "stuNameOrNo");
        String nj = getParamVal(request, "nj");
        String bj = getParamVal(request, "bj");

        itemName = URLDecoder.decode(itemName, "UTF-8");
        stuNameOrNo = URLDecoder.decode(stuNameOrNo, "UTF-8");
        nj = URLDecoder.decode(nj, "UTF-8");

        User user = getLoginUser();
        String teacherId = user.getRefId();

        Map njBjMap = getNjBj(nj);
        Object njId1 = njBjMap.get("njId");
        Object xdId1 = njBjMap.get("xdId");
        nj = njBjMap.get("nj").toString();

        Map params = new HashMap();
        params.put("itemName", "%" + itemName + "%");
        params.put("testCount", testCount);

        if (!StringUtils.isEmpty(stuNameOrNo)) {
            params.put("stuNameOrNo", "%" + stuNameOrNo + "%");
        }

        params.put("njId", njId1);
        params.put("xdId", xdId1);
        params.put("oneClass", bj);
        params.put("schoolId", user.getSchoolId());
        params.put("classId", sportsService.getClassByTeacher(teacherId));//根据当前身份查询班级id集合
        params.put("pageSize", -1);
        params.put("pageNum", 1);
        params.put("scoreType", 0);//查询体育测试的成绩列表

        PageInfo<Map> pageInfo = sportsService.findScoreList(params);
        List<Map> scoreList = pageInfo.getList();
        List<A_StuScoreView> astList = new ArrayList<A_StuScoreView>();
        for (Map map : scoreList) {
            A_StuScoreView view = new A_StuScoreView();
            view.setItemName(ConstantUtil.formatStr(map.get("itemName")));
            view.setTestCount(ConstantUtil.formatStr(map.get("testCount")));
            view.setTestDate(ConstantUtil.formatStr(map.get("testDate")));
            view.setXsxm(ConstantUtil.formatStr(map.get("xsxm")));
            view.setStuNum(ConstantUtil.formatStr(map.get("stuNum")));

            //单位转换   1′2″ <=====>  62
            if (map.get("itemUnit").toString().contains("秒")) {
                view.setMark(ConstantUtil.unitTranslate(ConstantUtil.formatStr(map.get("mark"))));
            } else {
                view.setMark(ConstantUtil.formatStr(map.get("mark")));
            }

            view.setItemUnit(ConstantUtil.formatStr(map.get("itemUnit")));
            view.setStuScore(ConstantUtil.formatStr(map.get("stuScore")));
            view.setStuLevel(ConstantUtil.formatStr(map.get("stuLevel")));

            astList.add(view);
        }
        String fileName = "体育测试成绩" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("测试成绩", "", A_StuScoreView.class).setDataList(astList).write(response, fileName).dispose();
    }

    @RequestMapping(value = "/score/error/export", method = RequestMethod.POST)
    public String errorScore(HttpServletRequest request, HttpServletResponse response) {
        try {
            String anno = "注释：红色字段为必填项\n" +
                    "          1.测试编号必须为正整数\n" +
                    "          2.测试项目必须存在  \n" +
                    "          3.测试时间：YYYY-MM-dd,例如：2016-09-01  \n" +
                    "          4.学生学号必须存在  \n" +
                    "          5.测试成绩：非负数字，若为二级单位 例：2′10″";

            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<StuScoreView> exportFile = new ArrayList<StuScoreView>();
            for (JsonElement jsonElement : jsonArray) {
                StuScoreView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), StuScoreView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("学生成绩", StuScoreView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    体育测试，成绩修改查看，新增映射规则页面
    @RequestMapping(value = "/rule/add/index", method = RequestMethod.GET)
    public String mapRuleAdd(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String itemName = URLDecoder.decode(getParamVal(request, "itemName"), "UTF-8");
        String gradeName = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");
        String gender = getParamVal(request, "gender");

        Map xdAndNj = sportsService.getXdAndNj(gradeName, getLoginUser());
        model.addAttribute("itemName", itemName);
        model.addAttribute("gradeName", gradeName);
        model.addAttribute("gender", gender);
        model.addAttribute("xdAndNj", xdAndNj);
        return "sport/popPage/mapRuleAdd";
    }

    //    体育测试，成绩修改查看，导入映射规则页面
    @RequestMapping(value = "/rule/import/index", method = RequestMethod.GET)
    public String mapRuleImport(HttpServletRequest request, Model model) {
        return "sport/popPage/mapRuleImport";
    }

    //    体育测试，成绩修改查看，修改成绩映射规则页面
    @RequestMapping(value = "/rule/edit/index", method = RequestMethod.GET)
    public String mapRuleEdit(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String ruleId = getParamVal(request, "ruleId");
        String itemUnit = URLDecoder.decode(getParamVal(request, "itemUnit"), "UTF-8");
        ScoreMapRule mapRule = sportsService.getRuleByPrimary(ruleId);

        model.addAttribute("mapRule", mapRule);
        model.addAttribute("itemUnit", itemUnit);
        return "sport/popPage/mapRuleEdit";
    }

    /**
     * 删除 分数成绩映射规则
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/rule/delete", method = RequestMethod.POST)
    public void ruleDelete(HttpServletRequest request) {
        String rule_id = getParamVal(request, "ruleId");
        ScoreMapRule rule = new ScoreMapRule();
        rule.setDelFlag(1);
        rule.setRuleId(rule_id);
        sportsService.saveScoreMapRule(rule, getLoginUser());
    }

    /**
     * 批量删除成绩映射规则
     */
    @ResponseBody
    @RequestMapping(value = "/rule/deleteBatch", method = RequestMethod.POST)
    public void ruleDeleteBatch(HttpServletRequest request) {
        String rule_id = getParamVal(request, "ruleId");
        String[] idArray = rule_id.split(",");
        for (int i = 1; i < idArray.length; i++) {
            ScoreMapRule rule = new ScoreMapRule();
            rule.setDelFlag(1);
            rule.setRuleId(idArray[i]);
            sportsService.saveScoreMapRule(rule, getLoginUser());
        }

    }
    /**
     * 批量删除学生成绩
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/deleteBatch", method = RequestMethod.POST)
    public void scoreDeleteBatch(HttpServletRequest request) {
        String scoreId = getParamVal(request, "scoreId");
        String[] idArray = scoreId.split(",");
        for (int i = 1; i < idArray.length; i++) {
            StuScoreRef stu = new StuScoreRef();
            stu.setDelFlag(1);
            stu.setTestSeq(idArray[i]);
            sportsService.saveStuScore(stu);
        }

    }
    /**
     * 添加/修改 分数成绩映射规则
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/rule/add", method = RequestMethod.POST)
    public void ruleAdd(HttpServletRequest request) {
        String item_name = getParamVal(request, "itemName");//项目名称
        String grade = getParamVal(request, "grade");//年级
        String gender = getParamVal(request, "gender");//性别
        String mark = getParamVal(request, "mark");//成绩（2.1 秒  100次）
        String score = getParamVal(request, "score");//分数
        String level = getParamVal(request, "level");//1：优秀 2：良好 3：一般 4：差
        String rule_id = getParamVal(request, "ruleId");//规则
        String xd = getParamVal(request, "xd");//学段
        String nj = getParamVal(request, "nj");//年级

        mark = ConstantUtil.translateSecondToMi(mark);//分秒格式的数据会转换成秒，其他格式不变

        Map<String, String> sportItemMap = getItemMap();

        ScoreMapRule rule = new ScoreMapRule();

        if (!StringUtils.isEmpty(item_name)) {
            rule.setItemId(sportItemMap.get(item_name));
            rule.setItemName(item_name);
            rule.setGradeName(grade);
            rule.setGender(gender);
            rule.setXd(xd);
            rule.setNj(NumberConvertUtil.convertS2I(nj));
        }

        rule.setMark(mark);
        rule.setScore(score);
        rule.setLevel(level);
        rule.setRuleId(rule_id);

        sportsService.saveScoreMapRule(rule, getLoginUser());
    }

    /**
     * 下载 成绩映射规则 导入模板
     */
    @ResponseBody
    @RequestMapping(value = "/scoreMapRule/download")
    public void downloadScoreMap(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "成绩映射规则模板.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.项目名必须存在\n" +
                    "          2.学段：小学，初中，高中  \n" +
                    "          3.年级：一年级，二年级......  \n" +
                    "          4.性别：男/女  \n" +
                    "          5.成绩：非负数字，若为二级单位 例：2′10″  \n" +
                    "          6.分数：正整数  \n" +
                    "          3.等级：优秀，良好，及格，不及格";
            new ExportExcel("映射规则", ScoreMapRuleView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 成绩映射规则导入
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/scoreMapRule/import", method = RequestMethod.POST)
    public ResponseEntity scoreMapRuleImport(@RequestParam(value = "file") MultipartFile file) {
        int all = 0;
        int successCount = 0;

        User user = getLoginUser();
        Map<String, String> sportItemMap = getItemMap();
        List<Map> validateList = new ArrayList<Map>();
        List<Map> reasonList = new ArrayList<Map>();

        List<ClassSection> sectionList = sportsService.getSections(user.getSchoolId());
        Map sectionMap = new HashMap();
        for (ClassSection section : sectionList) {
            sectionMap.put(section.getName(), section.getId());
        }
        try {
            ImportExcel importExcel = new ImportExcel(file, 2, 0);
            List<ScoreMapRuleView> list = importExcel.getDataList(ScoreMapRuleView.class, 1);
            all = list.size();

            Map res = batchRuleValidate(list, sectionMap);
            reasonList = (List<Map>) res.get("reasonList");
            List<ScoreMapRuleView> regularList = (List<ScoreMapRuleView>) res.get("regular");

            List<ScoreMapRule> batchRule = new ArrayList<ScoreMapRule>();

            for (ScoreMapRuleView scoreMapRuleView : regularList) {
                ScoreMapRule rule = new ScoreMapRule();
                rule.setRuleId(ConstantUtil.getPrimaryKey());
                rule.setItemId(sportItemMap.get(scoreMapRuleView.getItem_name()));
                rule.setItemName(scoreMapRuleView.getItem_name());
                rule.setSchoolId(user.getSchoolId());
                //rule.setXd(sportsService.getSection(scoreMapRuleView.getXd(), user).getId());
                rule.setXd(sectionMap.get(scoreMapRuleView.getXd()).toString());
                rule.setNj(getKeyByValueAndFlag(scoreMapRuleView.getNj(), "nj"));
                rule.setGradeName(scoreMapRuleView.getXd() + scoreMapRuleView.getNj());
                rule.setGender(getKeyByValueAndFlag(scoreMapRuleView.getGender(), "xb").toString());
                rule.setMark(ConstantUtil.translateSecondToMi(scoreMapRuleView.getMark()));
                rule.setScore(scoreMapRuleView.getScore());
                rule.setLevel(scoreMapRuleView.getLevel());
                rule.setCreateBy(user.getId());
                rule.setCreateTime(DateUtils.getCurrentTime());
                rule.setRuleType(0);
            /*    String validate = ruleValidate(rule);
                if (!StringUtils.isEmpty(validate)) {
                    Map failReason = new HashMap();
                    failReason.put("data", scoreMapRuleView);
                    failReason.put("reason", validate);
                    reasonList.add(failReason);
                    continue;
                }*/
                batchRule.add(rule);
            }
            sportsService.batchMapRuleSave(batchRule);
            successCount = batchRule.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int code = 0;
        if (all == successCount) {
            code = 1;
        }
        Map res = new HashMap();
        res.put("success", successCount);
        res.put("fail", all - successCount);
        res.put("failList", reasonList);
        res.put("code", code);

        return new ResponseEntity(res, HttpStatus.OK);
    }


    /**
     * 成绩映射规则导出
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/scoreMapRule/export", method = RequestMethod.GET)
    public void scoreMapRuleExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemName = getParamVal(request, "itemName");
        String gradeName = getParamVal(request, "gradeName");
        String gender = getParamVal(request, "gender");

        itemName = URLDecoder.decode(itemName, "UTF-8");
        gradeName = URLDecoder.decode(gradeName, "UTF-8");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("itemName", itemName);
        paramMap.put("gradeName", gradeName);
        paramMap.put("gender", gender);
        paramMap.put("pageSize", -1);
        paramMap.put("pageNum", 1);
        paramMap.put("schoolId", getLoginUser().getSchoolId());

        PageInfo pageInfo = sportsService.findScoreMapRuleByCriteria(paramMap);
        List<ScoreMapRule> scoreMapRuleList = pageInfo.getList();
        scoreMapRuleList = translate(scoreMapRuleList);

        List<A_ScoreMapRuleView> aScore = new ArrayList<A_ScoreMapRuleView>();
        for (ScoreMapRule mapRule : scoreMapRuleList) {
            A_ScoreMapRuleView as = new A_ScoreMapRuleView();
            as.setItemName(mapRule.getItemName());
            as.setGender(getValueByKeyAndFlag(NumberConvertUtil.convertS2I(mapRule.getGender()), "xb"));
            as.setGradeName(mapRule.getGradeName());
            as.setItemUnit(mapRule.getRemark());
            as.setMark(mapRule.getMark());
            as.setScore(mapRule.getScore());
            as.setLevel(mapRule.getLevel());

            aScore.add(as);
        }
        String fileName = "成绩映射关系" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("映射关系", "", A_ScoreMapRuleView.class).setDataList(aScore).write(response, fileName).dispose();
    }

    @RequestMapping(value = "/scoreMapRule/error/export", method = RequestMethod.POST)
    public String errorRule(HttpServletRequest request, HttpServletResponse response) {
        try {
            String anno = "注释：红色字段为必填项\n" +
                    "          1.项目名必须存在\n" +
                    "          2.学段：小学，初中，高中  \n" +
                    "          3.年级：一年级，二年级......  \n" +
                    "          4.性别：男/女  \n" +
                    "          5.成绩：非负数字，若为二级单位 例：2′10″  \n" +
                    "          6.分数：正整数  \n" +
                    "          3.等级：优秀，良好，及格，不及格";
            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<ScoreMapRuleView> exportFile = new ArrayList<ScoreMapRuleView>();
            for (JsonElement jsonElement : jsonArray) {
                ScoreMapRuleView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), ScoreMapRuleView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("映射关系", ScoreMapRuleView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
      * 体育测试》教学建议
      * param:request,model
      * method:get
      * */
    @RequestMapping(value = "/teach/index", method = RequestMethod.GET)
    public String teachAdvice(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String itemId = getParamVal(request, "itemId");
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id
        Integer gender = NumberConvertUtil.convertS2I(getParamVal(request, "gender"));

        User user = getLoginUser();
        Map section = getSchoolNj(sportsService.getSchoolClass(user.getSchoolId(), sportsService.getClassByTeacher(user.getRefId())));
        Set njSet = section.keySet();
        List njList = new ArrayList();
        njList.addAll(njSet);
        if (itemId.equals("-1")) {
            itemId = null;
        }

        if (StringUtils.isEmpty(nj) && njList.size() > 0) {
            nj = njList.get(0).toString();
        }

        List<Map> bjList = new ArrayList<Map>();
        if (nj.equals("全部")) {
            bjList = null;//年级选择全部，班级导航不显示
        } else {
            bjList = (List<Map>) section.get(nj);
        }

        if (bj == 0) {
            bj = -1;
        }
        if (gender == 0) {
            gender = -1;
        }

        String xdId = null;
        int njId = 0;

        if (!nj.equals("全部")) {
            //通过年级，获取xd，nj，全部年级只查scoolId
            //List<Map> classList = sportsService.getClassDetail(ConstantUtil.translate(nj), user.getSchoolId());
            if (!GukeerStringUtil.isNullOrEmpty(bjList)) {
                Map mapRes = bjList.get(0);
                xdId = mapRes.get("xd").toString();
                njId = NumberConvertUtil.convertS2I(mapRes.get("nj").toString());
            }
        }

        Map scoreParam = new HashMap();
        scoreParam.put("schoolId", user.getSchoolId());
        scoreParam.put("gender", gender);
        scoreParam.put("classId", bj);
        scoreParam.put("xdId", xdId);
        scoreParam.put("njId", njId);
        scoreParam.put("scoreType", 0);//查询体育测试的最新平均成绩

        List<Map> scoreRes = sportsService.getAvg(scoreParam);
        //将未参与测试的班级项目成绩设置为0
        List<Map> itemList = sportsService.getItems(0, xdId, njId, user.getSchoolId());//体育测试的项目

        List<Map> scoreResView = new ArrayList<Map>();
        scoreResView.addAll(scoreRes);

        Map resM = new HashMap();
        for (Map param : scoreRes) {
            resM.put(param.get("itemName"), param.get("itemUnit"));
        }

        if (itemList.size() != scoreRes.size()) {
            //结果和当前年级的项目数量不匹配，则补全未测试的项目成绩为0
            for (int i = 0; i < itemList.size(); i++) {
                String itemName = itemList.get(i).get("itemName").toString();
                Object val = resM.get(itemName);
                if (StringUtils.isEmpty(val)) {
                    //查询的结果中未包含的项目
                    Map zeroMap = new HashMap();
                    zeroMap.put("mark", "0");
                    zeroMap.put("score", "0");
                    zeroMap.put("itemName", itemName);
                    zeroMap.put("gender", "0");
                    zeroMap.put("xd", "0");
                    zeroMap.put("xdName", "0");
                    zeroMap.put("nj", "0");
                    zeroMap.put("itemUnit", itemList.get(i).get("itemUnit"));
                    scoreResView.add(zeroMap);
                }
            }
        }

        //成绩不足的学生列表
        Map failP = new HashMap();
        failP.put("schoolId", user.getSchoolId());
        failP.put("gender", gender);
        failP.put("classId", bj);
        failP.put("xdId", xdId);
        failP.put("njId", njId);
        failP.put("stuLevel", "不及格");
        failP.put("itemId", itemId);

        List<Map> failStuList = sportsService.getFailStuList(failP);

        model.addAttribute("njList", njList);//年级
        model.addAttribute("bjList", bjList);//班级
        model.addAttribute("scoreRes", scoreResView);//柱状图和表格
        model.addAttribute("itemList", itemList);//项目列表
        model.addAttribute("failStuList", failStuList);//项目列表
        //保存搜索参数
        model.addAttribute("njChoose", nj);//年级
        model.addAttribute("bjChoose", bj);//班级
        model.addAttribute("genderChoose", gender);//性别
        model.addAttribute("itemIdChoose", itemId);//性别
        return "sport/teachAdvice";
    }


    /*
      * 体育测试》成绩统计
      * param:request,model
      * method:get
      * */
    @RequestMapping(value = "/score/index", method = RequestMethod.GET)
    public String score(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id
        String itemId = getParamVal(request, "itemId");
        Integer gender = NumberConvertUtil.convertS2I(getParamVal(request, "gender"));

        User user = getLoginUser();
        Map section = getSchoolNj(sportsService.getSchoolClass(user.getSchoolId(),
                sportsService.getClassByTeacher(user.getRefId())));
        Set njSet = section.keySet();
        List njList = new ArrayList();
        njList.addAll(njSet);

        if (StringUtils.isEmpty(nj) && njList.size() > 0) {
            nj = njList.get(0).toString();
        }

        List<Map> bjList = new ArrayList<Map>();
        bjList = (List<Map>) section.get(nj);

        int xdId = 0;
        int njId = 0;
        if (!GukeerStringUtil.isNullOrEmpty(bjList)) {
            xdId = NumberConvertUtil.convertS2I(bjList.get(0).get("xd").toString());
            njId = NumberConvertUtil.convertS2I(bjList.get(0).get("nj").toString());
        }
        //List<Map> itemList = sportsService.getItems(xdId, njId, user.getSchoolId());
        List<SportItem> itemList = sportsService.getAllItem(user);

        if (StringUtils.isEmpty(itemId) && itemList.size() > 0) {
            itemId = itemList.get(0).getItemId();
        }

        //成绩变化趋势查询
        Map paramMap = new HashMap();
        paramMap.put("schoolId", user.getSchoolId());
        paramMap.put("xdId", xdId);
        paramMap.put("njId", njId);
        paramMap.put("itemId", itemId);
        paramMap.put("gender", gender);
        paramMap.put("classId", bj);
        List<Map> lines = sportsService.getScoreLine(paramMap);

        if (lines.size() > 1) {
            Map personAvg = lines.get(lines.size() - 1);
            Map latestAvg = lines.get(lines.size() - 2);
            String par1 = personAvg.get("mark").toString();
            String par2 = latestAvg.get("mark").toString();

            Double delete = NumberConvertUtil.sub(par1, par2);

            model.addAttribute("personAvg", personAvg);//人均成绩
            model.addAttribute("delete", delete);//与上次相比
        } else if (lines.size() == 1) {
            Map personAvg = lines.get(0);
            model.addAttribute("personAvg", personAvg);//人均成绩
            model.addAttribute("delete", 0);//与上次相比
        }

        model.addAttribute("njList", njList);//年级列表
        model.addAttribute("bjList", bjList);//班级列表
        model.addAttribute("itemList", itemList);//项目列表
        model.addAttribute("lines", lines);//
        //保存搜索参数
        model.addAttribute("njChoose", nj);//年级
        model.addAttribute("bjChoose", bj);//班级
        model.addAttribute("genderChoose", gender);//性别
        model.addAttribute("itemIdChoose", itemId);//项目id
        return "sport/scoreCount";
    }

    public List<ScoreMapRule> translate(List<ScoreMapRule> scoreMapRuleList) {
        List<SportItem> sportItemList = sportsService.getAllItem(getLoginUser());
        //翻译，使用备用字段存储项目单位
        for (int i = 0; i < scoreMapRuleList.size(); i++) {
            for (int j = 0; j < sportItemList.size(); j++) {
                if (scoreMapRuleList.get(i).getItemId().equals(sportItemList.get(j).getItemId())) {
                    scoreMapRuleList.get(i).setRemark(sportItemList.get(j).getItemUnit());
                }
            }
        }
        return scoreMapRuleList;
    }

    //根据项目名字获得项目，时间复杂度：1
    public Map<String, String> getItemMap() {
        // List<SportItem> sportItemList = sportsService.getAllTzItem();
        List<SportItem> sportItemList = sportsService.getAllItem(getLoginUser());

        Map<String, String> sportItemMap = new HashMap<String, String>();
        for (SportItem item : sportItemList) {
            sportItemMap.put(item.getItemName(), item.getItemId());
        }
        return sportItemMap;
    }

    //导入，新增学生成绩后调用，应用映射规则来计算分数和等级
    public void updateStuScore(List<String> primList) {

        User user = getLoginUser();
        //导入成绩之后，通过成绩映射规则计算学生的分数，等级！
        List<Map> scoreAndStuList = sportsService.getBatchInfo(primList);
        for (Map map : scoreAndStuList) {
            if (!StringUtils.isEmpty(map.get("stuScore")) && !map.get("prim").toString().equals(primList.get(0))) {
                continue;//若学生分数已经计算过，则不再计算分数；当更新指定学生成绩，需要重新计算
            }
            map.put("ruleType", 0);//0选择体育测试的规则
            Map ruleRes = sportsService.chooseRuleByInfo(map);//通过人员信息，选择映射规则
            StuScoreRef stuScoreRef = new StuScoreRef();
            stuScoreRef.setTestSeq(map.get("prim").toString());

            if (!GukeerStringUtil.isNullOrEmpty(ruleRes)) {
                stuScoreRef.setStudentLevel(ruleRes.get("level").toString());
                stuScoreRef.setStudentScore(ruleRes.get("score").toString());
            }
            stuScoreRef.setUpdateBy(user.getId());
            stuScoreRef.setUpdateDate(DateUtils.getCurrentTime());

            sportsService.setScoreAndLevel(stuScoreRef);//根据成绩，学生信息，匹配映射规则，计算分数，等级
        }

    }

    public Map getNjBj(String nj) {
        User user = getLoginUser();

        Map section = getSchoolNj(sportsService.getSchoolClass(user.getSchoolId(), sportsService.getClassByTeacher(user.getRefId())));

        Set njSet = section.keySet();
        List njList = new ArrayList();
        njList.addAll(njSet);

        if (StringUtils.isEmpty(nj) && njList.size() > 0) {
            nj = njList.get(0).toString();
        }

        List<Map> bjList = new ArrayList<Map>();
        bjList = (List<Map>) section.get(nj);

        int xdId = 0;
        int njId = 0;
        if (!StringUtils.isEmpty(bjList) && bjList.size() > 0) {
            xdId = NumberConvertUtil.convertS2I(bjList.get(0).get("xd").toString());
            njId = NumberConvertUtil.convertS2I(bjList.get(0).get("nj").toString());
        }
        Map resMap = new HashMap();
        resMap.put("nj", nj);
        resMap.put("bjList", bjList);
        resMap.put("njList", njList);
        resMap.put("xdId", xdId);
        resMap.put("njId", njId);
        return resMap;
    }

    public Map batchValidate(List<Map> param) {
        List itemNameList = sportsService.itemNameList(0);
        List stuNoList = sportsService.stuNoList(getLoginUser().getSchoolId());
        List<Map> reasonList = new ArrayList<Map>();
        List<StuScoreView> regularList = new ArrayList<StuScoreView>();
        for (Map map : param) {
            StuScoreView score = (StuScoreView) map.get("stuScoreView");
            String mark = (String) map.get("mark");
            Map failReason = new HashMap();
            if (StringUtils.isEmpty(score.getTest_id()) || !RegexpUtil.isNumPlus(score.getTest_id().toString())) {
                failReason.put("reason", "测试编号格式错误");
            }
            if (StringUtils.isEmpty(score.getStu_no()) || !stuNoList.contains(score.getStu_no().toString())) {
                failReason.put("reason", "学号不存在");
            }
            if (!itemNameList.contains(score.getItem_name())) {
                failReason.put("reason", "项目不存在");
            }
            if (!RegexpUtil.isDateForMat(score.getCreate_date())) {
                failReason.put("reason", "测试时间格式错误");
            }
            if (!RegexpUtil.scoreFormat(score.getItem_name(), mark)) {
                failReason.put("reason", "测试成绩错误");
            }
            if (GukeerStringUtil.isNullOrEmpty(failReason.get("reason"))) {
                //若验证格式正确，添加到合法参数集合
                regularList.add(score);
            } else {
                failReason.put("data", score);
                reasonList.add(failReason);
            }
        }
        Map res = new HashMap();
        res.put("reasonList", reasonList);//List<Map>
        res.put("regular", regularList);//剔除错误数据后的合法参数列表
        return res;
    }

    public Map batchRuleValidate(List<ScoreMapRuleView> ruleList, Map sectionMap) {
        User user = getLoginUser();
        List itemNameList = sportsService.itemNameList(0);
        List<GradeClass> njXdList = sportsService.getAllClass(user.getSchoolId());

        List<Map> reasonList = new ArrayList<Map>();
        List<ScoreMapRuleView> regularList = new ArrayList<ScoreMapRuleView>();
        for (ScoreMapRuleView rule : ruleList) {
            Object xd = sectionMap.get(rule.getXd());
            Map failReason = new HashMap();
            if (!itemNameList.contains(rule.getItem_name())) {
                failReason.put("reason", "项目不存在");
            }
            if (GukeerStringUtil.isNullOrEmpty(xd)) {
                failReason.put("reason", "学段不存在");
            }
            boolean njExits = false;
            for (GradeClass gradeClass : njXdList) {
                Integer nj = getKeyByValueAndFlag(rule.getNj(), "nj");
                if (gradeClass.getXd().equals(xd) && gradeClass.getNj().equals(nj)) {
                    njExits = true;//存在这个年级
                }
            }
            if (!njExits) {
                failReason.put("reason", "年级不存在");
            }
            if ("0".equals(rule.getGender())) {
                failReason.put("reason", "性别格式错误");
            }
            if (!RegexpUtil.isFloatPlus(rule.getMark())) {
                failReason.put("reason", "成绩格式错误");
            }
            if (!RegexpUtil.isNumPlus(rule.getScore())) {
                failReason.put("reason", "分数格式错误");
            }
            if (!RegexpUtil.isLevel(rule.getLevel())) {
                failReason.put("reason", "等级格式不正确");
            }

            if (GukeerStringUtil.isNullOrEmpty(failReason.get("reason"))) {
                //若验证格式正确，添加到合法参数集合
                regularList.add(rule);
            } else {
                failReason.put("data", rule);
                reasonList.add(failReason);
            }
        }
        Map res = new HashMap();
        res.put("reasonList", reasonList);//List<Map>
        res.put("regular", regularList);//剔除错误数据后的合法参数列表
        return res;
    }

    public String ruleValidate(ScoreMapRule rule) {
        if (!sportsService.itemExist(rule.getItemName())) {
            return "项目不存在";
        }
        if (!sportsService.xdExist(rule.getXd())) {
            return "学段不存在";
        }
        if (!sportsService.njExist(rule.getXd(), rule.getNj())) {
            return "年级不存在";
        }
        if (rule.getGender().equals("0")) {
            return "性别格式错误";
        }
        if (!RegexpUtil.isFloatPlus(rule.getMark())) {
            return "成绩格式错误";
        }
        if (!RegexpUtil.isNumPlus(rule.getScore())) {
            return "分数格式错误";
        }
        if (!RegexpUtil.isLevel(rule.getLevel())) {
            return "等级格式不正确";
        }
        return null;
    }

    public String addScoreValidate(StuScoreRef score, String itemUnit, Integer type, HttpServletRequest request) {
        if (type == 0 && !sportsService.stuNumExist(score.getStudentNum().toString())) {
            return "学号不存在";
        }
        if (type == 0 && sportsService.scoreExist(score)) {
            return "该次测试成绩已经存在";
        }
        if (!RegexpUtil.isDateForMat(score.getCreateDate())) {
            return "日期格式不正确";
        }
        return null;
    }

    public String scoreValidate(StuScoreRef score) {

        if (StringUtils.isEmpty(score.getTestId()) || !RegexpUtil.isNumPlus(score.getTestId().toString())) {
            return "测试编号格式错误";
        }
        if (!sportsService.itemExist(score.getItemName())) {
            return "测试项目不存在";
        }
        if (!RegexpUtil.isDateForMat(score.getCreateDate())) {
            return "测试时间格式错误";
        }
        if (!sportsService.stuNumExist(score.getStudentNum().toString())) {
            return "学号不存在";
        }
        if (!RegexpUtil.isFloatPlus(score.getStudentMark())) {
            return "测试成绩错误";
        }
        return null;
    }


    //-------------------------------------------------------------------------------------------
    //修改测试项目内容
    @ResponseBody
    @RequestMapping(value = "/item/update/date", method = RequestMethod.POST)
    public ResponseEntity itemUpdate(HttpServletRequest request) {
        String item_id = getParamVal(request, "itemId");
        String item_name = getParamVal(request, "itemName");
        String item_unit = getParamVal(request, "itemUnit");
        SportItem sportItem = sportsService.getItemById(item_id, getLoginUser());
        Map res = new HashMap();
        sportItem.setItemName(item_name);
        sportItem.setItemUnit(item_unit);
        sportsService.saveSportItem(sportItem, getLoginUser());
        res.put("code", "0");
        res.put("msg", "修改完成");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //弹出界面修改测试项目页面
    @RequestMapping(value = "/item/update", method = RequestMethod.GET)
    public String itemUpdateView(HttpServletRequest request, Model model) {
        String sportItemName = getParamVal(request, "sportItemName");
        try {
            sportItemName = java.net.URLDecoder.decode(sportItemName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = getLoginUser();
        //List<SportItem> sportItemList = sportsService.getAllItem(user);
        SportItem sportItem = sportsService.getItemByName(sportItemName, user);
        model.addAttribute("sportItem", sportItem);
        return "sport/popPage/sportItemEdit";
    }

    //弹出添加界面
    @RequestMapping(value = "/Item/addItem")
    public String addItem() {
        return "sport/popPage/sportItemAdd";
    }

    //选中checkbox批量删除体育项目Id
    @ResponseBody
    @RequestMapping(value = "/Item/deleteBatch", method = RequestMethod.GET)
    public void sportItemDeleteBatch(HttpServletRequest request) {
        String ids = request.getParameter("itemId");
        if (ids != null) {
            String[] idArray = ids.split(",");
            for (int i = 1; i < idArray.length; i++) {
                sportsService.delItemByPrimaryId(idArray[i]);
            }
        }
    }

    /**
     * 删除 体育测试项目
     */
    @ResponseBody
    @RequestMapping(value = "/Item/delete", method = RequestMethod.POST)
    public void sportItemDelete(HttpServletRequest request) {
        //String[] item_id = request.getParameterValues("itemId");
        String item_id = getParamVal(request, "itemId");
        SportItem sportItem = new SportItem();
        sportItem.setDelFlag(1);
        sportItem.setItemId(item_id);
        sportsService.saveSportItem(sportItem, getLoginUser());
    }


    //显示当前班级
    @RequiresPermissions("sportClass:manage:index")
    @RequestMapping(value = "/class/index")
    public String checkClass(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Map map = new HashMap();
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        map.put("schoolId", schoolId);
        //分页
        PageInfo pageInfo = sportsService.checkSportClassByname(map);
        List<Map> mapList = pageInfo.getList();
        List<Map> sportClassList = new LinkedList<Map>();
        PageInfo classCountPageInfo=sportClassService.getClassCount(map);
        List<Map> classCount = classCountPageInfo.getList();
        for (int i = 0; i < mapList.size(); i++) {
            Map entityMap = new HashMap();
            entityMap = mapList.get(i);
            String nj = (String) mapList.get(i).get("multiNj");
            String xd = (String) mapList.get(i).get("multiSection");
            String[] idArray = nj.split(",");
            String[] idArray2 = xd.split(",");
            String getStringNJ = "";
            String getStringXD = "";
            for (int j = 0; j < idArray.length; j++) {
                String StringNJ = getValueByKeyAndFlag(Integer.parseInt(idArray[j]), "nj");
                String StringXD = idArray2[j];
                getStringNJ = getStringNJ + "," + StringXD + StringNJ;
            }
            getStringNJ = getStringNJ.substring(1, getStringNJ.length());
            String[] idArray3 = getStringNJ.split(",");
            for (int j = 0; j < idArray3.length; j++) {
                for (int k = 0; k < idArray3.length - j - 1; k++) {
                    if (new MapKeyComparator().compare(String.valueOf(idArray3[k]), String.valueOf(idArray3[k + 1])) > 0) {
                        String temp;
                        temp = idArray3[k];
                        idArray3[k] = idArray3[k + 1];
                        idArray3[k + 1] = temp;
                    }
                }
            }
            for (int j = 0; j < idArray3.length; j++) {
                String XD = idArray3[j];
                getStringXD = getStringXD + "," + XD;
            }
            getStringXD = getStringXD.substring(1, getStringNJ.length() + 1);
            entityMap.put("njxd", getStringXD);
            entityMap.put("classCount",classCount.get(i).get("classCount"));
            sportClassList.add(entityMap);
        }
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("sportClassList", sportClassList);
        model.addAttribute("pageInfo", pageInfo);

        return "sport/sportClass";
    }


    //进入班级界面
    @RequestMapping(value = "/view/into")
    public String addSportClass(HttpServletRequest request, Model model) {
        String sportClassId = getParamVal(request, "sportClassId");
        model.addAttribute("sportClassId", sportClassId);
        User user = getLoginUser();
        //修改年级
        if (!StringUtils.isEmpty(sportClassId)) {
            Map sportClassById = sportClassService.checkSportClassById(sportClassId, user.getSchoolId());
            String nj = (String) sportClassById.get("multiNj");
            String xd = (String) sportClassById.get("multiSection");
            String xdId = (String) sportClassById.get("multiSectionId");
            //分别为xd，nj，xdId
            String[] idArray = nj.split(",");
            String[] idArray4 = xdId.split(",");
            String[] idArray2 = xd.split(",");

            String getStringNJ = "";
            String getStringXD = "";
            //拼出年纪 例如：初中一年级
            for (int j = 0; j < idArray.length; j++) {
                String StringNJ = getValueByKeyAndFlag(Integer.parseInt(idArray[j]), "nj");
                String StringXD = idArray2[j];
                getStringNJ = getStringNJ + "," + StringXD + StringNJ;
            }
            List<String> gradeId = new ArrayList<String>();
            //为了解决，，，我也不知道为啥我加了个0，0反正肯定有用
            gradeId.add("0,0");
            for (int j = 0; j < idArray.length; j++) {
                String getXDNJ = "";
                String StringNJ = idArray[j];
                String StringXD = idArray4[j];
                getXDNJ = StringXD + "," + StringNJ;
                gradeId.add(getXDNJ);
            }
            model.addAttribute("gradeId", new Gson().toJson(gradeId));

            getStringNJ = getStringNJ.substring(1, getStringNJ.length());
            String[] idArray3 = getStringNJ.split(",");
            //前台拼出初中一年级，初中二年级，顺便排序
            for (int j = 0; j < idArray3.length; j++) {
                for (int k = 0; k < idArray3.length - j - 1; k++) {
                    if (new MapKeyComparator().compare(String.valueOf(idArray3[k]), String.valueOf(idArray3[k + 1])) > 0) {
                        String temp;
                        temp = idArray3[k];
                        idArray3[k] = idArray3[k + 1];
                        idArray3[k + 1] = temp;
                    }
                }
            }
            for (int j = 0; j < idArray3.length; j++) {
                String XD = idArray3[j];
                getStringXD = getStringXD + "," + XD;
            }
            getStringXD = getStringXD.substring(1, getStringNJ.length() + 1);
            sportClassById.put("njxd", getStringXD);
            model.addAttribute("sportClassById", sportClassById);
        } else {
            List<String> gradeId = new ArrayList<String>();
            gradeId.add("0,0");
            model.addAttribute("gradeId", new Gson().toJson(gradeId));
        }


        List<Map> classStudentList = studentService.getClassStudent(sportClassId);
        model.addAttribute("classStudent", new Gson().toJson(classStudentList));


        List<SportItem> sportItemList = sportsService.getAllItem(user);
        model.addAttribute("sportItemList", sportItemList);
        List<Map> mapList = gradeClassService.getGradeClassName(user);
        model.addAttribute("mapList", mapList);
        List<Teacher> teacherList = teacherService.getAllTeacher(user.getSchoolId());
        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        return "sport/sportClassAdd";
    }

    //弹出页面层
    @RequestMapping(value = "/view/intostudent")
    public String addSportClasstoStudent(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String sportClassId = getParamVal(request, "sportClassId");
        model.addAttribute("sportClassId", sportClassId);
        //获得弹出层选中的学生学号及名字并封装成map
        String sendId = getParamVal(request, "sendId");
        model.addAttribute("sendId", sendId);
        String studentName = getParamVal(request, "studentName");
        studentName = java.net.URLDecoder.decode(studentName, "utf-8");
        List<Map> classNameAndId = new ArrayList<Map>();
        String[] arr1 = studentName.split(",");
        String[] arr2 = sendId.split(",");

        for (int i = 1; i < arr1.length; i++) {
            Map map = new HashMap();
            map.put("xh", arr2[i]);
            map.put("name", arr1[i]);
            classNameAndId.add(map);
        }
        model.addAttribute("classNameAndId", new Gson().toJson(classNameAndId));
        //获得已选课程的学生学号
        String studentXHLsit = null;
        User user = getLoginUser();
        if (sportClassId != null) {
            List<Map> student = studentClassService.getAllStudents(user.getSchoolId(), sportClassId);
            model.addAttribute("studentClass", student);
            for (int i = 0; i < student.size(); i++) {
                String studentXH = (String) student.get(i).get("xh");
                studentXHLsit = studentXHLsit + "," + studentXH;
            }
            model.addAttribute("studentXHLsit", studentXHLsit);
        }
        //获得性别，姓名，班级，年级，做查询
        String gender = getParamVal(request, "gender");
        String name = URLDecoder.decode(getParamVal(request, "name"), "UTF-8");
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));
//        if (StringUtils.isEmpty(gender)) {
//            gender = "1";
//        }
        Map section = getSchoolNj(sportsService.getSchoolClass(user.getSchoolId(), sportsService.getClassByTeacher(user.getRefId())));
        Set njSet = section.keySet();
        List njList = new ArrayList();
        njList.addAll(njSet);
//        if (StringUtils.isEmpty(nj) && njList.size() > 0) {
//            nj = njList.get(0).toString();
//        }
        if (StringUtils.isEmpty(nj) ) {
            nj = "全部";
        }
        List<Map> bjList = new ArrayList<Map>();
//        if (nj.equals("全部")) {
//            bjList = null;//年级选择全部，班级导航不显示
//        } else {
//            bjList = (List<Map>) section.get(nj);
//        }
        bjList = (List<Map>) section.get(nj);
        List<Map> allList = new ArrayList();
        List<Map> onlyNj = new ArrayList<Map>();
        for (Object key : njSet) {
            List<Map> value = (List<Map>) section.get(key);
            onlyNj.add(value.get(0));
            allList.addAll(value);
        }
        String xdId = null;
        int njId = 0;
        if(nj=="全部"||"全部".equals(nj)){
            xdId=null;
            njId=0;
        }else {
            Map mapRes = bjList.get(0);
            xdId = mapRes.get("xd").toString();
            njId = NumberConvertUtil.convertS2I(mapRes.get("nj").toString());
        }
//        if (!GukeerStringUtil.isNullOrEmpty(bjList)) {
//            Map mapRes = bjList.get(0);
//            xdId = mapRes.get("xd").toString();
//            njId = NumberConvertUtil.convertS2I(mapRes.get("nj").toString());
//        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xsxb", gender);
        map.put("xsxm", "%" + name + "%");
        map.put("schoolId", user.getSchoolId());
        map.put("classId", bj);
        map.put("nj", njId);
        map.put("xd", xdId);
        List<Map> studentList = studentService.selectStudentByAttribute(map);
        model.addAttribute("studentList", studentList);
        model.addAttribute("njList", njList);
        model.addAttribute("bjList", bjList);
        model.addAttribute("allList", allList);
        model.addAttribute("onlyNj", onlyNj);
        return "sport/popPage/sportClassToStudent";
    }

    //局部更新学生信息
    @ResponseBody
    @RequestMapping(value = "/student/check", method = RequestMethod.POST)
    public String addStudentView(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        User user = getLoginUser();
        String gender = getParamVal(request, "gender");
        String sportClassId = getParamVal(request, "sportClassId");
        String name = getParamVal(request, "name");
        String nj = getParamVal(request, "nj");
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));
        if (StringUtils.isEmpty(gender)) {
//            gender = "1";
        }
        //得到所有班级学生
        Map section = getSchoolNj(sportsService.getSchoolClass(user.getSchoolId(), sportsService.getClassByTeacher(user.getRefId())));
        Set njSet = section.keySet();
        List njList = new ArrayList();
        njList.addAll(njSet);

        List maps = new ArrayList();
        for (int i = 0; i < njList.size(); i++) {
            List<Map> mapList = new ArrayList<Map>();
            mapList = (List<Map>) section.get(njList.get(i));
            maps.add(mapList);
        }
//        if (StringUtils.isEmpty(nj) && njList.size() > 0) {
//            nj = njList.get(0).toString();
//        }
        String xdId = null;
        int njId = 0;
        if(nj=="全部"||"全部".equals(nj)){
            xdId=null;
            njId=0;
        }else {
            List<Map> bjList = new ArrayList<Map>();
            bjList = (List<Map>) section.get(nj);

//        if (!GukeerStringUtil.isNullOrEmpty(bjList)) {
            Map mapRes = bjList.get(0);
            xdId = mapRes.get("xd").toString();
            njId = NumberConvertUtil.convertS2I(mapRes.get("nj").toString());
//        }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xsxb", gender);
        map.put("xsxm", "%" + name + "%");
        map.put("schoolId", user.getSchoolId());
        map.put("classId", bj);
        map.put("nj", njId);
        map.put("xd", xdId);
        //根据属性查询学生
        List<Map> studentList = studentService.selectStudentByAttribute(map);
        List<Map> getStudent = studentClassService.getAllStudents(user.getSchoolId(), sportClassId);
        //转换初中1
        for (int i = 0; i < studentList.size(); i++) {
            String gradeName = null;
            gradeName = (String) studentList.get(i).get("indexName");
            String xd = gradeName.substring(0, gradeName.length() - 1);
            String njzh = gradeName.substring(2, gradeName.length());
            njzh = getValueByKeyAndFlag(Integer.parseInt(njzh), "nj");
            gradeName = xd + njzh;
            studentList.get(i).put("indexName", gradeName);
        }
        List<Object> studentId = new ArrayList<Object>();
        for (Map choose : getStudent) {
            studentId.add(choose.get("xh"));
        }
        Map res = new HashMap();
        res.put("studentList", studentList);
        res.put("chooseList", studentId);
        res.put("getStudent", getStudent);
        return new Gson().toJson(res);
    }




    //添加班级及添加学生到班级
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/class/add", method = RequestMethod.POST)
    public String addStudentClass(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String classId = getParamVal(request, "classId");

        String itemId = getParamVal(request, "item");
        String teacher = getParamVal(request, "teacherName");
        String sportClassName = getParamVal(request, "sportClassName");
        String gradeClassId = getParamVal(request, "grade");
        String studentId = request.getParameter("studentId");
        String[] teacherArray = teacher.split("-");
        //如果老师传过来的是学号和姓名则按学号只有姓名则按姓名
        SportClass sportClassByName = sportClassService.selectSportClassByName(sportClassName);
        //返回的fasle和下面的false2是为了弹出layer条件
        if(sportClassByName!=null){
            if (!(classId).equals(sportClassByName.getSportClassId())) {
                return "false";
            }
        }
        List<Teacher> teacherList = new ArrayList<Teacher>();
        //如果是长度大于一传过来的数据为（name-no）否则为（name）
        if (teacherArray.length < 1) {
            teacherList = teacherService.checkTeacherByNo(teacherArray[1]);
        } else {
            teacherList = teacherService.checkTeacherByName(teacherArray[0]);
        }

        String teacherId = null;
        if(teacherList.size()>0) {
            teacherId = teacherList.get(0).getId();
        }else{
            return "false2";
        }

        if (classId != null || classId != "") {
            sportClassService.delete(classId, user.getSchoolId());
            studentClassService.deleteStudent(classId);
        }


        Map<String,Long> sortMap = sportClassService.getMaxScort(user.getSchoolId());
        Long sort = null;
        if(sortMap!=null){
            sort = sortMap.get("sort");
        }

        Long count = 0L;
        if(!"".equals(sort)&&sort!=null){
            count = sort;
        }
        count= count +1;
        SportClass sportClass = new SportClass();
        String sportClassId = ConstantUtil.getPrimaryKey();
        sportClass.setSportClassId(sportClassId);
        sportClass.setSort(count);
        if (gradeClassId != null) {
            //得到传过来的数据并转化为数组
            //分割年级字符串，并分成数组
            String[] idArray = gradeClassId.split(";");

            String[] idArray2 = new String[idArray.length - 1];
            int a = 0;
            //去除第一位的0（传过来的的数据为[0；xx;xx]）
            for (int i = 1; i < idArray.length; i++) {
                idArray2[a] = idArray[i];
                a++;
            }
            for (int i = 0; i < idArray2.length; i++) {
                String[] idArry3 = idArray2[i].split(",");
                sportClass.setId(ConstantUtil.getPrimaryKey());
                sportClass.setSportClassName(sportClassName);
                sportClass.setSportItemId(itemId);
                sportClass.setTecherId(teacherId);
                sportClass.setSchoolId(user.getSchoolId());
                sportClass.setXd(idArry3[0]);
                Integer nj = Integer.parseInt(idArry3[1]);
                sportClass.setNj(nj);
                sportClass.setDelFlag(0);
                System.out.println(sportClass);
                sportClassService.insertSportClass(sportClass);
            }
        }
//-----------------------------------------------------------------------
        //添加学生至班级
        List<RefClassStudent> refClassStudentList = new ArrayList<RefClassStudent>();
        if (studentId != null) {
            //分割传过来的数据（0，XXX,XXX）
            String[] idArray = studentId.split(",");
            for (int i = 1; i < idArray.length; i++) {
                RefClassStudent refClassStudent = new RefClassStudent();
                refClassStudent.setStudentId(idArray[i]);
                refClassStudent.setSportClassId(sportClassId);
                refClassStudent.setSort(count);
                refClassStudentList.add(refClassStudent);
            }
        }
        studentClassService.insertBatch(refClassStudentList);
        return sportClassId;
    }


    //更改学生至班级
    @ResponseBody
    @RequestMapping(value = "/student/update")
    public void addStudent(HttpServletRequest request) {
        String studentId = request.getParameter("studentId");
        String classId = request.getParameter("classId");
        if (classId != null && classId != "") {
            studentClassService.deleteStudent(classId);
        }
        List<RefClassStudent> refClassStudentList = new ArrayList<RefClassStudent>();
        if (studentId != null) {
            String[] idArray = studentId.split(",");
            for (int i = 1; i < idArray.length; i++) {
                RefClassStudent refClassStudent = new RefClassStudent();
                refClassStudent.setStudentId(idArray[i]);
                refClassStudent.setSportClassId(classId);
                refClassStudentList.add(refClassStudent);
            }
        }
        studentClassService.insertBatch(refClassStudentList);
    }

    //删除班级
    @ResponseBody
    @RequestMapping(value = "/class/delete")
    public void deleteClass(HttpServletRequest request) {
        String sportClassId = getParamVal(request, "sportClassId");
        String[] idArray = sportClassId.split(",");
        User user = getLoginUser();
        if (idArray.length == 1) {
            sportClassService.deleteClass(sportClassId, user.getSchoolId());
        } else {
            for (int i = 1; i < idArray.length; i++) {
                sportClassService.deleteClass(idArray[i], user.getSchoolId());
            }
        }
    }

//
//    //单个删除学生
//    @ResponseBody
//    @RequestMapping(value = "/student/delete")
//    public void deleteStudent(HttpServletRequest request) {
//        String sportClassId = getParamVal(request, "sportClassId");
//        String studentXh = getParamVal(request, "studentXh");
//        //sportClassService.deleteClass(sportClassId,user.getSchoolId());
//        studentService.deleteStudent(sportClassId, studentXh);
//    }
//
//    //全部清空删除学生（为了解决左面复选框读取数据库从而翻页后依旧选择问题）
//    @ResponseBody
//    @RequestMapping(value = "/student/deletebatch")
//    public void deleteStudentBatch(HttpServletRequest request) {
//        String sportClassId = getParamVal(request, "sportClassId");
//        String studentXh = getParamVal(request, "studentXh");
//        //sportClassService.deleteClass(sportClassId,user.getSchoolId());
//        studentXh = studentXh.substring(0, studentXh.length());
//        String[] idArray = studentXh.split(",");
//        for (int i = 0; i < idArray.length; i++) {
//            if (idArray[i] != null) {
//                studentService.deleteStudent(sportClassId, idArray[i]);
//            }
//        }
//    }
}
