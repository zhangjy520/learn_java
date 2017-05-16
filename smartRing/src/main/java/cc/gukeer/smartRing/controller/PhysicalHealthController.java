package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.*;
import cc.gukeer.common.utils.excel.ExportExcel;
import cc.gukeer.common.utils.excel.ImportExcel;
import cc.gukeer.smartRing.modelView.A_StuScoreForPhysicalView;
import cc.gukeer.smartRing.modelView.StuScoreForPhysicalView;
import cc.gukeer.smartRing.persistence.entity.HealthyStandard;
import cc.gukeer.smartRing.persistence.entity.SportItem;
import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.service.DailyService;
import cc.gukeer.smartRing.service.PhysicalHealthService;
import cc.gukeer.smartRing.service.SportsTestService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.*;

/**
 * Created by conn on 2016/8/8.
 * teacher
 */
@Controller
@RequestMapping(value = "/physical")
public class PhysicalHealthController extends BasicController {

    @Autowired
    PhysicalHealthService physicalHealthService;

    @Autowired
    SportsTestService sportsTestService;

    @Autowired
    DailyService dailyService;


    /*
   * 体质健康》成绩管理
   * param:request,model
   * method:get
   * */
    @RequestMapping(value = "/score/index", method = RequestMethod.GET)
    public String ruleIndex(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String itemId = getParamVal(request, "itemId");
        String time = URLDecoder.decode(getParamVal(request, "time"), "UTF-8");
        String gender = getParamVal(request, "gender");
        String stuNameOrNum = URLDecoder.decode(getParamVal(request, "stuNameOrNum"), "UTF-8");
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id

        User user = getLoginUser();

        Map njBjMap = getNjBj(nj);
        Object njId = njBjMap.get("njId");
        Object xdId = njBjMap.get("xdId");
        Object njList = njBjMap.get("njList");
        Object bjList = njBjMap.get("bjList");

        List<SportItem> itemList = physicalHealthService.getAllItems();
        List<String> timeList = physicalHealthService.getTimeList(user.getSchoolId().toString());

        //处理默认选中参数
        if (StringUtils.isEmpty(itemId)) {
            if (itemList.size() > 0) {
                itemId = itemList.get(0).getItemId();
            }
        }
        if (StringUtils.isEmpty(time)) {
            if (timeList.size() > 0) {
                time = timeList.get(0);
            }
        }
        if (StringUtils.isEmpty(gender)) {
            gender = "0";
        }

        Map paramMap = new HashMap();
        paramMap.put("itemId", itemId);
        paramMap.put("gender", gender);
        paramMap.put("testTime", time);

        if (!StringUtils.isEmpty(stuNameOrNum)) {
            paramMap.put("stuNameOrNo", "%" + stuNameOrNum + "%");
        }

        paramMap.put("schoolId", user.getSchoolId());

        if ("全部".equals(nj.trim()) || StringUtils.isEmpty(nj)) {
            bjList = null;//选择全部年级，不显示班级
        } else {
            paramMap.put("xdId", xdId);
            paramMap.put("njId", njId);
            paramMap.put("oneClass", bj);
        }

        paramMap.put("classId", sportsTestService.getClassByTeacher(user.getRefId()));//根据当前身份查询班级id集合
        paramMap.put("pageSize", pageSize);
        paramMap.put("pageNum", pageNum);
        paramMap.put("scoreType", 1);//查询体质健康的成绩列表

        PageInfo<Map> pageInfo = sportsTestService.findScoreList(paramMap);
        List<Map> scoreList = pageInfo.getList();
        //传递数据对象
        model.addAttribute("itemList", itemList);
        model.addAttribute("timeList", timeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("bjList", bjList);
        model.addAttribute("njList", njList);
        model.addAttribute("bj", bj);
        model.addAttribute("xdId", xdId);
        model.addAttribute("njId", njId);
        //保存搜索参数
        model.addAttribute("chooseItem", itemId);
        model.addAttribute("chooseTime", time);
        model.addAttribute("chooseGender", gender);
        model.addAttribute("chooseName", stuNameOrNum);
        model.addAttribute("choosePageSize", pageSize);
        model.addAttribute("chooseNj", nj);
        model.addAttribute("chooseBj", bj);

        return "physical/physicalScoreManage";
    }

    //    体育测试，成绩修改查看，新增成绩页面
    @RequestMapping(value = "/score/add/index", method = RequestMethod.GET)
    public String scoreAddIndex(Model model) {
        List<SportItem> itemList = physicalHealthService.getAllItems();

        model.addAttribute("sportItemList", itemList);
        return "physical/popPage/physicalScoreAdd";
    }

    @ResponseBody
    @RequestMapping(value = "/score/add", method = RequestMethod.POST)
    public ResponseEntity scoreAdd(HttpServletRequest request) {
        List<SportItem> itemList = physicalHealthService.getAllItems();
        Map<String, String> sportItemMap = getItemMap(itemList, 2);
        User user = getLoginUser();

        String testSeq = getParamVal(request, "testSeq");
        int stuNo = NumberConvertUtil.convertS2I(getParamVal(request, "stuNo"));
        String itemId = getParamVal(request, "itemId");
        String testTime = getParamVal(request, "testTime");
        String testMark = getParamVal(request, "testMark");
        String itemUnit = getParamVal(request, "itemUnit");
        testMark = ConstantUtil.translateSecondToMi(testMark);//分秒格式的数据会转换成秒，其他格式不变

        Map returnMap = new HashMap();

        StuScoreRef stuScoreRef = new StuScoreRef();
        stuScoreRef.setStudentMark(testMark);
        stuScoreRef.setTestDate(testTime);
        stuScoreRef.setSchoolId(user.getSchoolId());

        List<String> primList = new ArrayList<String>();
        if (StringUtils.isEmpty(testSeq)) {
            //新增
            stuScoreRef.setStudentNum(stuNo);
            stuScoreRef.setItemName(sportItemMap.get(itemId));
            stuScoreRef.setItemId(itemId);
            stuScoreRef.setScoreType(1);

            String prim = ConstantUtil.getPrimaryKey();
            stuScoreRef.setTestSeq(prim);
            stuScoreRef.setCreateBy(user.getId());
            stuScoreRef.setCreateDate(ConstantUtil.getCurrentTime());

            String validate = addScoreValidate(stuScoreRef, itemUnit, 0, request);
            if (!StringUtils.isEmpty(validate)) {
                returnMap.put("msg", validate);
                returnMap.put("code", "1");
                return new ResponseEntity(returnMap, HttpStatus.OK);
            }

            sportsTestService.updateRefScore(stuScoreRef, user);//将此学生此项目之前的成绩设置为过期
            physicalHealthService.insertStuScore(stuScoreRef);

            primList.add(prim);
            updateStuScore(primList);//计算此学生的成绩
        } else {
            stuScoreRef.setTestSeq(testSeq);
            stuScoreRef.setUpdateBy(user.getId());
            stuScoreRef.setUpdateDate(ConstantUtil.getCurrentTime());

            String validate = addScoreValidate(stuScoreRef, itemUnit, 1, request);
            if (!StringUtils.isEmpty(validate)) {
                returnMap.put("msg", validate);
                returnMap.put("code", "1");
                return new ResponseEntity(returnMap, HttpStatus.OK);
            }

            physicalHealthService.updateStuScore(stuScoreRef);
            primList.add(testSeq);
            updateStuScore(primList);//计算修改成绩后的分数
        }
        returnMap.put("msg", "操作成功");
        returnMap.put("code", "0");
        return new ResponseEntity(returnMap, HttpStatus.OK);
    }


    @RequestMapping(value = "score/edit/index", method = RequestMethod.GET)
    public String scoreEditPage(HttpServletRequest request, Model model) {
        String testSeq = getParamVal(request, "testSeq");
        Map scoreDetail = sportsTestService.getScoreByPrimary(testSeq);

        model.addAttribute("scoreDetail", scoreDetail);
        return "physical/popPage/physicalScoreEdit";
    }


    @ResponseBody
    @RequestMapping(value = "score/delete", method = RequestMethod.POST)
    public void scoreDelete(HttpServletRequest request) {
        String testSeq = getParamVal(request, "testSeq");
        StuScoreRef stuScoreRef = new StuScoreRef();
        stuScoreRef.setDelFlag(1);
        stuScoreRef.setUpdateBy(getLoginUser().getId());
        stuScoreRef.setUpdateDate(ConstantUtil.getCurrentTime());
        stuScoreRef.setTestSeq(testSeq);

        physicalHealthService.updateStuScore(stuScoreRef);
    }

    /**
     * 下载导入模板：学生体质健康测试成绩模板
     */
    @ResponseBody
    @RequestMapping(value = "/scoreMb/download")
    public void downloadStuScore(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "体质健康测试成绩模板.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.学生学号必须存在 \n" +
                    "          2.项目必须存在 \n" +
                    "          3.测试时间：YYYY年春/夏/秋/冬,例如：2016年春 \n" +
                    "          5.测试成绩：非负数字，若为二级单位 例：2′10″";
            new ExportExcel("学生体质健康成绩", StuScoreForPhysicalView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  体质健康，导入成绩页面
    @RequestMapping(value = "/score/import/index", method = RequestMethod.GET)
    public String scoreImport(HttpServletRequest request, Model model) {
        return "physical/popPage/physicalScoreImport";
    }


 /*   @ResponseBody
    @RequestMapping(value = "/score/import", method = RequestMethod.POST)
    public ResponseEntity physicalScoreImport(@RequestParam(value = "file") MultipartFile file) {
        int all = 0;
        int successCount = 0;
        long begin = System.currentTimeMillis();

        List<Map> reasonList = new ArrayList<Map>();

        User user = getLoginUser();
        List<SportItem> itemList = physicalHealthService.getAllItems();
        Map<String, String> sportItemMap = getItemMap(itemList, 1);

        List<Map> validateList = new ArrayList<Map>();
        try {
            ImportExcel importExcel = new ImportExcel(file, 2, 0);
            List<StuScoreForPhysicalView> list = importExcel.getDataList(StuScoreForPhysicalView.class, 1);
            List<String> primList = new ArrayList<String>();//保存插入成绩的主键列表，留作计算成绩的参数。
            all = list.size();
            for (StuScoreForPhysicalView stuScoreForPhysicalView : list) {
                StuScoreRef stuScoreRef = new StuScoreRef();
                stuScoreRef.setStudentNum(stuScoreForPhysicalView.getStuNo());
                stuScoreRef.setItemName(stuScoreForPhysicalView.getItemName());
                stuScoreRef.setItemId(sportItemMap.get(stuScoreForPhysicalView.getItemName()));
                stuScoreRef.setStudentMark(ConstantUtil.translateSecondToMi(stuScoreForPhysicalView.getStuMark()));
                stuScoreRef.setCreateBy(user.getId());
                stuScoreRef.setCreateDate(ConstantUtil.getCurrentTime());
                stuScoreRef.setSchoolId(user.getSchoolId());
                stuScoreRef.setScoreType(1);//体质健康成绩类型为1，体育测试成绩默认为0
                stuScoreRef.setTestDate(stuScoreForPhysicalView.getTestTime());

                Map map = new HashMap();
                map.put("mark",stuScoreForPhysicalView.getStuMark());
                map.put("stuScoreForPhysicalView",stuScoreForPhysicalView);
                validateList.add(map);

                String prim = ConstantUtil.getPrimaryKey();
                stuScoreRef.setTestSeq(prim);
                primList.add(prim);

                sportsTestService.updateRefScore(stuScoreRef, getLoginUser());//更新学生成绩表
                int flag = sportsTestService.addStuScore(stuScoreRef);//保存学生成绩
                successCount += flag;
            }
            updateStuScore(primList);//计算学生分数，成绩

        } catch (Exception e) {
            e.printStackTrace();
        }
        int code = 0;
        if (all == successCount) {
            code = 1;
        }
        long end = System.currentTimeMillis();

        reasonList = batchValidate(validateList);
        Map res = new HashMap();
        res.put("success", successCount);
        res.put("fail", all - successCount);
        res.put("failList", reasonList);
        res.put("code", code);
        res.put("timeUse", (end - begin) / 1000);
        return new ResponseEntity(res, HttpStatus.OK);
    }
*/

    /**
     * 体质健康测试成绩导入
     * param: request
     * method:POST
     */
    @ResponseBody
    @RequestMapping(value = "/score/import", method = RequestMethod.POST)
    public ResponseEntity physicalScoreImport(@RequestParam(value = "file") MultipartFile file) {
        int all = 0;
        int successCount = 0;
        long begin = System.currentTimeMillis();

        User user = getLoginUser();
        List<SportItem> itemList = physicalHealthService.getAllItems();
        Map<String, String> sportItemMap = getItemMap(itemList, 1);
        List<Map> validateList = new ArrayList<Map>();
        List<Map> reasonList = new ArrayList<Map>();
        try {
            ImportExcel importExcel = new ImportExcel(file, 2, 0);
            List<StuScoreForPhysicalView> list = importExcel.getDataList(StuScoreForPhysicalView.class, 1);
            List<String> primList = new ArrayList<String>();//保存插入成绩的主键列表，留作计算成绩的参数。
            all = list.size();//总数据条数

            for (StuScoreForPhysicalView stuScoreForPhysicalView : list) {
                Map map = new HashMap();
                map.put("mark", stuScoreForPhysicalView.getStuMark());
                map.put("stuScoreForPhysicalView", stuScoreForPhysicalView);
                validateList.add(map);
            }
            Map res = batchValidate(validateList);//将数据集通过vatchValidate批量验证，返回正确的数据集合+错误数据的原因
            reasonList = (List<Map>) res.get("reasonList");//错误格式的列表
            List<StuScoreForPhysicalView> regularList = (List<StuScoreForPhysicalView>) res.get("regular");//正确格式的列表

            List<StuScoreRef> scoreBatch = new ArrayList<StuScoreRef>();
            //插入正确格式的列表
            for (StuScoreForPhysicalView regular : regularList) {
                StuScoreRef stuScoreRef = new StuScoreRef();
                stuScoreRef.setStudentNum(regular.getStuNo());
                stuScoreRef.setItemName(regular.getItemName());
                stuScoreRef.setItemId(sportItemMap.get(regular.getItemName()));
                stuScoreRef.setStudentMark(ConstantUtil.translateSecondToMi(regular.getStuMark()));
                stuScoreRef.setCreateBy(user.getId());
                stuScoreRef.setCreateDate(ConstantUtil.getCurrentTime());
                stuScoreRef.setSchoolId(user.getSchoolId());
                stuScoreRef.setScoreType(1);//体质健康成绩类型为1，体育测试成绩默认为0
                stuScoreRef.setTestDate(regular.getTestTime());

                String prim = ConstantUtil.getPrimaryKey();
                stuScoreRef.setTestSeq(prim);
                primList.add(prim);

                scoreBatch.add(stuScoreRef);
            }
            successCount = regularList.size();
            batchSaveStuScore(scoreBatch, primList);
            //先插入成绩，再更新分数,批量更新耗时较长！
          /*sportsTestService.batchSave(scoreBatch,getLoginUser());
            successCount = regularList.size();
            updateStuScore(primList);*/

            //直接插入更新过的分数，速度更快


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

    @RequestMapping(value = "/physicalScore/error/export", method = RequestMethod.POST)
    public String errorScore(HttpServletRequest request, HttpServletResponse response) {
        try {
            String anno = "注释：红色字段为必填项\n" +
                    "          1.学生学号必须存在 \n" +
                    "          2.项目必须存在 \n" +
                    "          3.测试时间：YYYY年春/夏/秋/冬,例如：2016年春 \n" +
                    "          5.测试成绩：非负数字，若为二级单位 例：2′10″";

            String fileName = "导入失败信息.xlsx";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<StuScoreForPhysicalView> exportFile = new ArrayList<StuScoreForPhysicalView>();
            for (JsonElement jsonElement : jsonArray) {
                StuScoreForPhysicalView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), StuScoreForPhysicalView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("学生成绩", StuScoreForPhysicalView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 学生体育测试成绩导出
     * param: request
     */
    @ResponseBody
    @RequestMapping(value = "/stuScore/export", method = RequestMethod.GET)
    public void stuScoreExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemId = getParamVal(request, "itemId");
        String time = getParamVal(request, "time");
        String gender = getParamVal(request, "gender");
        String stuNameOrNum = getParamVal(request, "stuNameOrNum");
        String nj = getParamVal(request, "nj");
        String bj = getParamVal(request, "bj");

        stuNameOrNum = URLDecoder.decode(stuNameOrNum, "UTF-8");
        time = URLDecoder.decode(time, "UTF-8");
        nj = URLDecoder.decode(nj, "UTF-8");

        User user = getLoginUser();

        Map njBjMap = getNjBj(nj);
        Object njId = njBjMap.get("njId");
        Object xdId = njBjMap.get("xdId");
        nj = njBjMap.get("nj").toString();

        Map paramMap = new HashMap();
        paramMap.put("njId", njId);
        paramMap.put("xdId", xdId);
        paramMap.put("oneClass", bj);
        paramMap.put("itemId", itemId);
        paramMap.put("gender", gender);
        paramMap.put("testTime", time);

        if (!StringUtils.isEmpty(stuNameOrNum)) {
            paramMap.put("stuNameOrNo", "%" + stuNameOrNum + "%");
        }

        paramMap.put("schoolId", user.getSchoolId());
        paramMap.put("classId", sportsTestService.getClassByTeacher(user.getRefId()));//根据当前身份查询班级id集合
        paramMap.put("pageSize", -1);
        paramMap.put("pageNum", 1);
        paramMap.put("scoreType", 1);//查询体质健康的成绩列表

        PageInfo<Map> pageInfo = sportsTestService.findScoreList(paramMap);
        List<Map> scoreList = pageInfo.getList();
        List<A_StuScoreForPhysicalView> astList = new ArrayList<A_StuScoreForPhysicalView>();
        for (Map map : scoreList) {
            A_StuScoreForPhysicalView view = new A_StuScoreForPhysicalView();
            view.setItemName(ConstantUtil.formatStr(map.get("itemName")));
            view.setXsxm(ConstantUtil.formatStr(map.get("xsxm")));
            view.setStuNum(ConstantUtil.formatStr(map.get("stuNum")));
            view.setItemName(ConstantUtil.formatStr(map.get("itemName")));
            view.setTestTime(ConstantUtil.formatStr(map.get("testTime")));
            view.setMark(ConstantUtil.formatStr(map.get("mark")));
            view.setItemUnit(ConstantUtil.formatStr(map.get("itemUnit")));
            view.setScore(ConstantUtil.formatStr(map.get("stuScore")));
            view.setLevel(ConstantUtil.formatStr(map.get("stuLevel")));

            astList.add(view);
        }

        String fileName = "体质健康测试成绩" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("体质健康测试成绩", "", A_StuScoreForPhysicalView.class).setDataList(astList).write(response, fileName).dispose();
    }

    /*
     * 体质健康》体质分析
     * param:request,model
     * method:get
     * */
    @RequestMapping(value = "/analysis/index", method = RequestMethod.GET)
    public String AnalysisIndex(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一6
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id
        String itemId = getParamVal(request, "itemId");
        Integer gender = NumberConvertUtil.convertS2I(getParamVal(request, "gender"));
        User user = getLoginUser();

        Map njBjMap = getNjBj(nj);
        Object njId = njBjMap.get("njId");
        Object xdId = njBjMap.get("xdId");
        Object njList = njBjMap.get("njList");
        Object bjList = njBjMap.get("bjList");
        nj = njBjMap.get("nj").toString();
        //List<Map> itemList = sportsService.getItems(xdId, njId, user.getSchoolId());
        List<SportItem> itemList = physicalHealthService.getAllItems();
        if (StringUtils.isEmpty(itemId) && itemList.size() > 0) {
            itemId = itemList.get(0).getItemId();
        }
        //查询：体质健康，各项目的成绩
        Map scoreParam = new HashMap();
        scoreParam.put("schoolId", user.getSchoolId());
        scoreParam.put("gender", gender);
        scoreParam.put("classId", bj);
        scoreParam.put("xdId", xdId);
        scoreParam.put("njId", njId);
        scoreParam.put("scoreType", 1);//查询体质健康的最新平均成绩

        List<Map> scoreRes = sportsTestService.getAvg(scoreParam);

        model.addAttribute("njList", njList);//年级列表
        model.addAttribute("bjList", bjList);//班级列表
        model.addAttribute("itemList", itemList);//项目列表
        model.addAttribute("scoreRes", scoreRes);//项目列表

        //保存搜索参数
        model.addAttribute("xdId", xdId);//学段id
        model.addAttribute("njId", njId);//年级id
        model.addAttribute("njChoose", nj);//年级
        model.addAttribute("bjChoose", bj);//班级
        model.addAttribute("genderChoose", gender);//性别
        model.addAttribute("itemIdChoose", itemId);//性别
        return "physical/physicalAnalysis";
    }

    //体质分析，成绩变化趋势
    @ResponseBody
    @RequestMapping(value = "/analysis/score/line", method = RequestMethod.POST)
    public ResponseEntity scoreLine(HttpServletRequest request, Model model) {
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id
        String xdId = getParamVal(request, "xdId");//学段
        String njId = getParamVal(request, "njId");//年级
        Integer gender = NumberConvertUtil.convertS2I(getParamVal(request, "gender"));//性别
        String itemId = getParamVal(request, "itemId");//项目id

        Map param = new HashMap();
        param.put("schoolId", getLoginUser().getSchoolId());
        //  param.put("schoolId", 2);
        param.put("xdId", xdId);
        param.put("njId", njId);
        param.put("itemId", itemId);
        param.put("gender", gender);
        param.put("classId", bj);

        List<Map> resLine = physicalHealthService.scoreChangeLine(param);
        return new ResponseEntity(resLine, HttpStatus.OK);
    }

    /*
    * 体质健康》学生体质档案
    * param:request,model
    * method:get
    * */
    @RequestMapping(value = "/archives/index", method = RequestMethod.GET)
    public String archives(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String stuNameOrNo = URLDecoder.decode(getParamVal(request, "stuNameOrNo"), "UTF-8");//学号/姓名
        String nj = URLDecoder.decode(getParamVal(request, "nj"), "UTF-8");//例：初一
        Integer bj = NumberConvertUtil.convertS2I(getParamVal(request, "bj"));//班级id
        String itemId = getParamVal(request, "itemId");
        String dayPageSize = getParamVal(request, "dayPageSize");
        String dayPageNum = getParamVal(request, "dayPageNum");
        String pePageSize = getParamVal(request, "pePageSize");
        String pePageNum = getParamVal(request, "pePageNum");

        Map njBjMap = getNjBj(nj);
        Object njId = njBjMap.get("njId");
        Object xdId = njBjMap.get("xdId");
        Object njList = njBjMap.get("njList");
        Object bjList = njBjMap.get("bjList");
        nj = njBjMap.get("nj").toString();

        List<SportItem> itemList = sportsTestService.getAllItem(getLoginUser());
        User user = getLoginUser();

        List<String> classIdList = sportsTestService.getClassByTeacher(user.getRefId());
        //体育测试表格
        Map paramMap = new HashMap();
        paramMap.put("xdId", xdId);
        paramMap.put("njId", njId);
        if (bj != 0) {
            paramMap.put("oneClass", bj);
        }
        paramMap.put("itemId", itemId);
        paramMap.put("expire", "0");//查询最新的测试成绩
        if (!StringUtils.isEmpty(stuNameOrNo)) {
            paramMap.put("stuNameOrNo", "%" + stuNameOrNo + "%");
        }
        paramMap.put("schoolId", user.getSchoolId());
        paramMap.put("classId", classIdList);//根据当前身份查询班级id集合
        paramMap.put("pageSize", pePageSize);
        paramMap.put("pageNum", pePageNum);
        paramMap.put("scoreType", 0);//查询体育测试的成绩列表

        PageInfo<Map> pePageInfo = sportsTestService.findScoreList(paramMap);
        List<Map> resList = pePageInfo.getList();

        List<Map> resListView = new ArrayList<Map>();
        for (Map map : resList) {
            Object njName = map.get("xdName") + ConstantUtil.getValueByKeyAndFlag(
                    NumberConvertUtil.convertS2I(map.get("nj").toString()), "nj");//   ConstantUtil.translateNj(map.get("njName").toString()).get("njName");
            map.put("njName", njName);
            resListView.add(map);
        }

        //日常健康表格
        Map dailyParam = new HashMap();
        dailyParam.put("pageSize", dayPageSize);
        dailyParam.put("pageNum", dayPageNum);
        dailyParam.put("classId", classIdList);
        dailyParam.put("schoolId", user.getSchoolId());
        if (!StringUtils.isEmpty(stuNameOrNo)) {
            dailyParam.put("stuNameOrNo", "%" + stuNameOrNo + "%");
        }
        dailyParam.put("xdId", xdId);
        dailyParam.put("njId", njId);
        if (bj != 0) {
            dailyParam.put("oneClass", bj);
        }
        PageInfo dailyPageInfo = physicalHealthService.getDailyHealthy(dailyParam);
        List<Map> dailyList = dailyPageInfo.getList();

        HealthyStandard standard = dailyService.findStandardByClass(user.getSchoolId(), xdId.toString(),
                NumberConvertUtil.convertS2I(njId.toString()));

        List<Map> dailyListView = new ArrayList<Map>();
        if (!GukeerStringUtil.isNullOrEmpty(standard)) {
            for (Map map : dailyList) {
                Object sportTimeS = map.get("sportTime");
                Object asleepTimeS = map.get("asleepTime");
                Object sleepLongS = map.get("sleepLong");
                if (GukeerStringUtil.isNullOrEmpty(sportTimeS) || GukeerStringUtil.isNullOrEmpty(asleepTimeS) ||
                        GukeerStringUtil.isNullOrEmpty(sleepLongS)) {
                    continue;
                }

                Double sportTime = NumberConvertUtil.convertS2D(map.get("sportTime").toString());
                Double asleepTime = NumberConvertUtil.convertS2D(map.get("asleepTime").toString());
                Double sleepLong = NumberConvertUtil.convertS2D(map.get("sleepLong").toString());

                if (sportTime > standard.getSportStandard()) {
                    map.put("sportStandard", "合格");
                } else {
                    map.put("sportStandard", "不合格");
                }
                if (asleepTime > standard.getAsleepStandard()) {
                    map.put("asleepStandard", "不合格");
                } else {
                    map.put("asleepStandard", "合格");
                }
                if (sleepLong > standard.getSleepStandard()) {
                    map.put("sleepLongStandard", "合格");
                } else {
                    map.put("sleepLongStandard", "不合格");
                }
                map.put("indexName", map.get("sectionName") + ConstantUtil.getValueByKeyAndFlag(
                        NumberConvertUtil.convertS2I(map.get("nj").toString()), "nj"));
                dailyListView.add(map);
            }
        }

        //保存搜索参数
        model.addAttribute("njChoose", nj);
        model.addAttribute("bjChoose", bj);
        model.addAttribute("peChoose", pePageSize);
        model.addAttribute("stuNameOrNo", stuNameOrNo);
        model.addAttribute("njId", njId);
        model.addAttribute("xdId", xdId);
        model.addAttribute("itemChoose", itemId);

        //搜索参数集合
        model.addAttribute("njList", njList);
        model.addAttribute("bjList", bjList);
        model.addAttribute("itemList", itemList);

        //查询结果
        model.addAttribute("peList", resListView);
        model.addAttribute("pePageInfo", pePageInfo);
        model.addAttribute("dailyPageInfo", dailyPageInfo);
        model.addAttribute("dailyList", dailyList);
        model.addAttribute("dailyListView", dailyListView);
        return "physical/physicalArchives";
    }

    @RequestMapping(value = "/archives/detail/{id}", method = RequestMethod.GET)
    public String stuDetail(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        String tap = getParamVal(request, "tap");
        if (StringUtils.isEmpty(tap)) {
            tap = "tap1";
        }
        User user = getLoginUser();
        List<SportItem> itemList = sportsTestService.getAllItem(user);
        List<SportItem> physicalItemList = sportsTestService.getAllTzItem();
        Map stuView = physicalHealthService.getStuInfo(id, user.getSchoolId());//个人信息
        model.addAttribute("stuNum", id);
        model.addAttribute("stuView", stuView);
        model.addAttribute("whichTap", tap);
        model.addAttribute("itemList", itemList);
        model.addAttribute("physicalItemList", physicalItemList);
        return "physical/physicalArchivesDetail";
    }

    //基础信息接口
    @ResponseBody
    @RequestMapping(value = "/archives/person/{id}", method = RequestMethod.GET)
    public ResponseEntity getPersonInfo(@PathVariable("id") String id, HttpServletRequest request) {
        User user = getLoginUser();
        Map stuView = physicalHealthService.getStuInfo(id, user.getSchoolId());//个人信息

        return new ResponseEntity(stuView, HttpStatus.OK);
    }

    //体育测试接口+体质健康接口
    @ResponseBody
    @RequestMapping(value = "/archives/sport/{id}", method = RequestMethod.POST)
    public ResponseEntity getSportTest(@PathVariable("id") String id, HttpServletRequest request) {
        String itemId = getParamVal(request, "itemId");
        int scoreType = NumberConvertUtil.convertS2I(getParamVal(request, "scoreType"));
        User user = new User();//getLoginUser();
        user.setSchoolId("2");

        List<Map> latestScore = physicalHealthService.getStuAllLatestScore(id, scoreType, user.getSchoolId());
        List<Map> itemScoreLog = physicalHealthService.getStuItemScore(id, itemId, scoreType, user.getSchoolId());

        Map resMap = new HashMap();
        resMap.put("latestScore", latestScore);
        resMap.put("itemScoreLog", itemScoreLog);

        return new ResponseEntity(resMap, HttpStatus.OK);
    }

    //日常健康接口
    @ResponseBody
    @RequestMapping(value = "/archives/daily/{id}", method = RequestMethod.POST)
    public ResponseEntity getDailyHealth(@PathVariable("id") String id, HttpServletRequest request) throws ParseException {
        String beginDate = getParamVal(request, "beginDate");
        String endDate = getParamVal(request, "endDate");
        User user = getLoginUser();
        List<Map> allData = physicalHealthService.getStuDailyHealthy(id, user.getSchoolId(), beginDate, endDate, 0);//0(每天的数据)
        List<Map> avgData = physicalHealthService.getStuDailyHealthy(id, user.getSchoolId(), beginDate, endDate, 1);//1（这些天的平均成绩）

        Map avg = new HashMap();
        if (avgData.size() > 0) {
            avg = avgData.get(0);
            if (!GukeerStringUtil.isNullOrEmpty(avg)) {
                avg.put("asleepTime", ConstantUtil.asleepTime(avg.get("asleepTime").toString()));
            }
        }

        Map<String, Map> allDataMap = new HashMap<String, Map>();
        for (Map map : allData) {
            map.put("asleepTimeFormat", ConstantUtil.asleepTime(map.get("asleepTime").toString()));
            allDataMap.put(map.get("info_date").toString(), map);
        }
        //无数据的日期，补0！！！！！！！
        //补0后的集合为allDataAddViewAddNull;
        List<Object> allDataAddViewAddNull = new ArrayList<Object>();
        List<String> days = ConstantUtil.betweenDays(beginDate, endDate);
        for (int i = 0; i < days.size(); i++) {
            String index = days.get(i);
            Object goal = allDataMap.get(index);
            if (!GukeerStringUtil.isNullOrEmpty(goal)) {
                allDataAddViewAddNull.add(goal);
            } else {
                Map map = new HashMap();
                map.put("info_date", i);
                map.put("sportTime", "0");
                map.put("asleepTime", "0");
                map.put("asleepTimeFormat", "00:00");
                map.put("sleepLong", "0");
                allDataAddViewAddNull.add(map);
            }
        }

        Map resMap = new HashMap();
        resMap.put("avgData", avg);
        //  resMap.put("allData", allDataView);//不补0的结果
        resMap.put("allData", allDataAddViewAddNull);//补0的结果

        return new ResponseEntity(resMap, HttpStatus.OK);

    }

    //根据项目名字获得项目，时间复杂度：1
    public Map<String, String> getItemMap(List<SportItem> sportItemList, int type) {

        Map<String, String> sportItemMap = new HashMap<String, String>();
        if (type == 1) {
            //itemName:itemID
            for (SportItem item : sportItemList) {
                sportItemMap.put(item.getItemName(), item.getItemId());
            }
        } else {
            //itemId:itemName
            for (SportItem item : sportItemList) {
                sportItemMap.put(item.getItemId(), item.getItemName());
            }
        }

        return sportItemMap;
    }

    //导入，新增学生成绩后调用，应用映射规则来计算分数和等级，传入成绩的主键，根据主键查询得到成绩信息，并根据学生成绩信息应用映射规则
    public void updateStuScore(List<String> primList) {

        User user = getLoginUser();
        //导入成绩之后，通过成绩映射规则计算学生的分数，等级！
        List<Map> scoreAndStuList = sportsTestService.getBatchInfo(primList);
        List<StuScoreRef> batchScore = calcScore(scoreAndStuList, primList);
        sportsTestService.batchUpdateScore(batchScore);

    }

    //批量计算导入的学生的成绩
    public int batchSaveStuScore(List<StuScoreRef> list, List<String> primList) {
        List<Map> scoreAndStuList = new ArrayList<Map>();
        List<Map> res = sportsTestService.batchStuInfo(list);

        Map stuInfoMap = new HashMap();
        for (Map stuInfo: res) {
            stuInfoMap.put(stuInfo.get("xh"),stuInfo);
        }

        for (StuScoreRef stuScore : list) {
            Map stuInfo = (Map) stuInfoMap.get(stuScore.getStudentNum().toString());
            if (!GukeerStringUtil.isNullOrEmpty(stuInfo)) {
                    Map map = new HashMap();
                    map.put("itemId", stuScore.getItemId());
                    map.put("xd", stuInfo.get("xd"));
                    map.put("nj", stuInfo.get("nj"));
                    map.put("stuScore", stuScore.getStudentScore());
                    map.put("prim", stuScore.getTestSeq());
                    map.put("mark", stuScore.getStudentMark());
                    map.put("gender", stuInfo.get("gender"));
                    scoreAndStuList.add(map);
            }
        }
        List<StuScoreRef> completeScoreList = calcScore(scoreAndStuList, primList);
        List<StuScoreRef> resScoreList = new ArrayList<StuScoreRef>();

        Map completeMap = new HashMap();
        for (StuScoreRef map : completeScoreList) {
            Map score = new HashMap();
            score.put("score", map.getStudentScore());
            score.put("level", map.getStudentLevel());
            completeMap.put(map.getTestSeq(), score);
        }

        for (StuScoreRef stu : list) {
            Map scoreObj = (Map) completeMap.get(stu.getTestSeq());
            if (!GukeerStringUtil.isNullOrEmpty(scoreObj)) {
                stu.setStudentScore(scoreObj.get("score").toString());
                stu.setStudentLevel(scoreObj.get("level").toString());
                resScoreList.add(stu);
            }

        }
        sportsTestService.batchSave(resScoreList, getLoginUser());
        return resScoreList.size();
    }

    //学生信息list，分数主键list，计算成绩并返回计算完成的成绩列表。
    public List<StuScoreRef> calcScore(List<Map> param, List<String> primList) {

        List<StuScoreRef> batchScore = new ArrayList<StuScoreRef>();
        for (Map map : param) {
            if (!StringUtils.isEmpty(map.get("stuScore")) && !map.get("prim").toString().equals(primList.get(0))) {
                continue;//若学生分数已经计算过，则不再计算分数；当更新指定学生成绩，需要重新计算
            }
            map.put("ruleType", 1);//1选择体质健康的规则
            map.put("schoolId", null);//体质健康的规则不针对学校
            Map ruleRes = sportsTestService.chooseRuleByInfo(map);//通过人员信息，选择映射规则
            StuScoreRef stuScoreRef = new StuScoreRef();
            stuScoreRef.setTestSeq(map.get("prim").toString());

            if (!GukeerStringUtil.isNullOrEmpty(ruleRes)) {
                stuScoreRef.setStudentLevel(ruleRes.get("level").toString());
                stuScoreRef.setStudentScore(ruleRes.get("score").toString());
                batchScore.add(stuScoreRef);
            }
        }
        return batchScore;
    }

    public Map getNjBj(String nj) {
        User user = getLoginUser();

        Map section = getSchoolNj(sportsTestService.getSchoolClass(user.getSchoolId(), sportsTestService.getClassByTeacher(user.getRefId())));
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
        Map resMap = new HashMap();
        resMap.put("bjList", bjList);
        resMap.put("njList", njList);
        resMap.put("xdId", xdId);
        resMap.put("njId", njId);
        resMap.put("nj", nj);
        return resMap;
    }

    public String addScoreValidate(StuScoreRef score, String itemUnit, Integer type, HttpServletRequest request) {
        if (type == 0 && !sportsTestService.stuNumExist(score.getStudentNum().toString())) {
            return "学号不存在";
        }
        if (!RegexpUtil.isPeDate(score.getTestDate())) {
            return "时间格式不正确";
        }
        if (!RegexpUtil.scoreFormat(itemUnit, getParamVal(request, "testMark"))) {
            return "成绩格式不正确";
        }
        return null;
    }

    public Map batchValidate(List<Map> param) {
        List itemNameList = sportsTestService.itemNameList(1);
        List stuNoList = sportsTestService.stuNoList(getLoginUser().getSchoolId());
        List<Map> reasonList = new ArrayList<Map>();
        List<StuScoreForPhysicalView> regularList = new ArrayList<StuScoreForPhysicalView>();
        for (Map map : param) {
            StuScoreForPhysicalView score = (StuScoreForPhysicalView) map.get("stuScoreForPhysicalView");
            String mark = (String) map.get("mark");
            Map failReason = new HashMap();
            if (StringUtils.isEmpty(score.getStuNo()) || !stuNoList.contains(score.getStuNo().toString())) {
                failReason.put("reason", "学号不存在");
            }
            if (!itemNameList.contains(score.getItemName())) {
                failReason.put("reason", "项目不存在");
            }
            if (!RegexpUtil.scoreFormat(score.getItemName(), mark)) {
                failReason.put("reason", "测试成绩错误");
            }
            if (!RegexpUtil.isPeDate(score.getTestTime())) {
                failReason.put("reason", "测试时间格式错误");
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

    public String scoreValidate(StuScoreRef score, String mark) {
        if (StringUtils.isEmpty(score.getStudentNum()) || !sportsTestService.stuNumExist(score.getStudentNum().toString())) {
            return "学号不存在";
        }
        if (!sportsTestService.itemExist(score.getItemName())) {
            return "项目不存在";
        }
        if (!RegexpUtil.scoreFormat(score.getItemName(), mark)) {
            return "测试成绩错误";
        }
        if (!RegexpUtil.isPeDate(score.getTestDate())) {
            return "测试时间格式错误";
        }
        return null;
    }

}
