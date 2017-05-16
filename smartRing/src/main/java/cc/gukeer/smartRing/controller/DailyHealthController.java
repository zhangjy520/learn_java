package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.DateUtils;
import cc.gukeer.common.utils.GsonUtil;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.common.utils.excel.ExportExcel;
import cc.gukeer.smartRing.modelView.*;
import cc.gukeer.smartRing.persistence.entity.HealthyStandard;
import cc.gukeer.smartRing.persistence.entity.extension.MessageView;
import cc.gukeer.smartRing.persistence.entity.extension.StandardView;
import cc.gukeer.smartRing.persistence.entity.extension.Statistics;
import cc.gukeer.smartRing.service.DailyService;
import cc.gukeer.smartRing.service.DeviceService;
import cc.gukeer.smartRing.service.RoleService;
import cc.gukeer.smartRing.service.SportsTestService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pc-daisike on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/dailyhealth")
public class

DailyHealthController extends BasicController {
    @Autowired
    DailyService dailyService;
    @Autowired
    RoleService roleService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    SportsTestService sportsTestService;

    /**
     * 日常健康首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String dailyIndex(Model model) {
        String teacherId = getLoginUser().getRefId();
        Statistics statistics = dailyService.findStatisticsByTeacherId(teacherId, null, null, null, null, null); //全部以分钟为单位
        Integer asleep = statistics.getAvgAsleepTime();
        String _asleep = ConstantUtil.getAsleepTime(asleep);
        model.addAttribute("sportStandard", "标准时间");
        model.addAttribute("sleepStandard", "标准时间");
        model.addAttribute("asleepStandard", "标准时间");
        model.addAttribute("asleep", _asleep);
        model.addAttribute("statistics", statistics);
        return "/daily/index";
    }

    /**
     * 运动统计(视图)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sport/index")
    public String soirtIndex(Model model) {
        //首次打开默认全校，昨天
        String xd = null;
        Integer nj = null;
        String classId = null;
        int pageSize = 10;
        int pageNum = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Integer fromDate = NumberConvertUtil.convertS2I(format.format(cal.getTime()));
        Integer toDate = NumberConvertUtil.convertS2I(format.format(cal.getTime()));
//        List<CascadingView> cascadingViews = deviceService.getCascadingViewByTeacherId(getLoginUser().getSchoolId(),getLoginUser().getRefId());
        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());
        HashMap<String, Object> map = getData(xd, nj, classId, fromDate, toDate);
        PageInfo<MessageView> pageInfo = getTableData(xd, nj, classId, fromDate, toDate, pageSize, pageNum, true);
        try {
            model.addAttribute("cascadingViews", cascadingViews);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("dateList", map.get("dateList"));
            model.addAttribute("sportStandard", map.get("sportStandard"));
            model.addAttribute("sleepStandard", map.get("sleepStandard"));
            model.addAttribute("asleepStandard", map.get("asleepStandard"));
            model.addAttribute("statistics", map.get("statistics"));
            model.addAttribute("diffStatistics", map.get("diffStatistics"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/daily/sport";
    }

    /**
     * 导出运动不足学生时间的
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sport/export")
    public void SportLessExport(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String xd = request.getParameter("xd");
        Integer nj = Integer.parseInt(request.getParameter("nj"));
        String classId = request.getParameter("bj");
        if("0".equals(xd)){
            xd=null;
        }
        if("0".equals(nj)||nj==0){
            nj=null;
        }
        if("0".equals(classId)){
            classId=null;
        }
        Integer fromDate = NumberConvertUtil.convertS2I(request.getParameter("fromDate"));
        Integer toDate = NumberConvertUtil.convertS2I(request.getParameter("toDate"));
        int pageSize = -1;
        int pageNum = 1;
        PageInfo<MessageView> pageInfo = getTableData(xd, nj, classId, fromDate, toDate, pageSize, pageNum, true);
        List<MessageView> scoreList = pageInfo.getList();
        List<StudentSportLessView> astList = new ArrayList<StudentSportLessView>();
        for (MessageView messageView : scoreList) {
            StudentSportLessView view = new StudentSportLessView();
            view.setXsxm(ConstantUtil.formatStr(messageView.getStudentName()));
            view.setSportCalorie(ConstantUtil.formatStr(messageView.getAvgCal()));
            view.setSportTime(ConstantUtil.formatStr(messageView.getAvgTime()));
            view.setTargetSportTime(ConstantUtil.formatStr(messageView.getSportStandard()));
            astList.add(view);
        }

        String fileName = "运动不足学生" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("运动不足学生", "", StudentSportLessView.class).setDataList(astList).write(response, fileName).dispose();

    }

    /**
     * 睡眠时间不足的学生导出
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sleep/export")
    public void sleepLessExport(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String xd = request.getParameter("xd");
        Integer nj = Integer.parseInt(request.getParameter("nj"));
        String classId = request.getParameter("bj");
        if("0".equals(xd)){
            xd=null;
        }
        if("0".equals(nj)||nj==0){
            nj=null;
        }
        if("0".equals(classId)){
            classId=null;
        }
        Integer fromDate = NumberConvertUtil.convertS2I(request.getParameter("fromDate"));
        Integer toDate = NumberConvertUtil.convertS2I(request.getParameter("toDate"));
        int pageSize = -1;
        int pageNum = 1;
        PageInfo<MessageView> pageInfo = getTableData(xd, nj, classId, fromDate, toDate, pageSize, pageNum, false);
        List<MessageView> scoreList = pageInfo.getList();
        List<StudentSleepLessView> astList = new ArrayList<StudentSleepLessView>();
        for (MessageView messageView : scoreList) {
            StudentSleepLessView view = new StudentSleepLessView();
            view.setXsxm(messageView.getStudentName());
            view.setAsleepQuality(messageView.getAvgSleepQuality());
            view.setTargetAsleepTime(ConstantUtil.getAsleepTime(messageView.getAsleepStandard()));//不动
            view.setTargetSleepTime(String.valueOf(messageView.getSleepStandard()));
            view.setAsleepTime(String.valueOf(messageView.getAvgSleepTime()));
            view.setSleepTime(String.valueOf(ConstantUtil.getAsleepTime(messageView.getAvgAsleepTime().intValue())));
            astList.add(view);
        }

        String fileName = "睡眠不足学生统计" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("睡眠不足学生统计", "", StudentSleepLessView.class).setDataList(astList).write(response, fileName).dispose();
    }


    /**
     * 导出运动时间折线图
     * @param request
     * @param response
     * @throws IOException
     */

    @RequestMapping(value = "/sportLine/export")
    public void sportLine(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String xd = request.getParameter("xd");
        Integer nj = Integer.parseInt(request.getParameter("nj"));
        String classId = request.getParameter("bj");
        if("0".equals(xd)){
            xd=null;
        }
        if("0".equals(nj)||nj==0){
            nj=null;
        }
        if("0".equals(classId)){
            classId=null;
        }
        Integer fromDate = NumberConvertUtil.convertS2I(request.getParameter("fromDate"));
        Integer toDate = NumberConvertUtil.convertS2I(request.getParameter("toDate"));
        List<Statistics> dayStatistics = dailyService.findDayStatisticsByTeacherId(getLoginUser().getRefId(), xd, nj, classId, fromDate, toDate);
        List<SportTimeView> astList = new ArrayList<SportTimeView>();
        Map<String, String> dateList = new LinkedHashMap<String, String>();
        if (fromDate != null && toDate != null) {
            dateList = getDatesBetweenTwoDate(fromDate.toString(), toDate.toString());
        }
        if (dateList.size() > 0 && dayStatistics.size() > 0) {
            for (Statistics each : dayStatistics) {
                if (dateList.containsKey(each.getInfoDate().toString())) {
                    dateList.put(each.getInfoDate().toString(), each.getAvgSport().toString());
                }
            }
        }
        Iterator<String> iter = dateList.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            String value = dateList.get(key);
            SportTimeView view = new SportTimeView();
            view.setDays(key);
            view.setTime(value);

            astList.add(view);
        }
        String fileName = "运动时长趋势" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("运动时长趋势", "", SportTimeView.class).setDataList(astList).write(response, fileName).dispose();

    }

    /**
     * 睡眠时间折线图导出数据
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/sleepLine/export")
    public void sleepLine(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String xd = request.getParameter("xd");
        Integer nj = Integer.parseInt(request.getParameter("nj"));
        String classId = request.getParameter("bj");
        if("0".equals(xd)){
            xd=null;
        }
        if("0".equals(nj)||nj==0){
            nj=null;
        }
        if("0".equals(classId)){
            classId=null;
        }
        Integer fromDate = NumberConvertUtil.convertS2I(request.getParameter("fromDate"));
        Integer toDate = NumberConvertUtil.convertS2I(request.getParameter("toDate"));
        Long fdate = Long.valueOf(fromDate);
        List<Statistics> dayStatistics = dailyService.findDayStatisticsByTeacherId(getLoginUser().getRefId(), xd, nj, classId, fromDate, toDate);
//        List<MessageView> scoreList = pageInfo.getList();
        List<SleepTimeView> astList = new ArrayList<SleepTimeView>();
        Map<String, String> sleepList = new LinkedHashMap<String, String>();
        if (fromDate != null && toDate != null) {
            sleepList = getDatesBetweenTwoDate(fromDate.toString(), toDate.toString());
        }
        if (sleepList.size() > 0 && dayStatistics.size() > 0) {
            for (Statistics each : dayStatistics) {
                if (sleepList.containsKey(each.getInfoDate().toString())) {
                    sleepList.put(each.getInfoDate().toString(), each.getAvgSleep().toString());
                }
            }
        }
        Iterator<String> iter = sleepList.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            String value = sleepList.get(key);
            SleepTimeView view = new SleepTimeView();
            view.setDays(key);
            view.setTime(value);

            astList.add(view);
        }

        String fileName = "睡眠时长趋势" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("睡眠时长趋势", "", SleepTimeView.class).setDataList(astList).write(response, fileName).dispose();

    }

    /**
     * 入睡时间折线图导出数据
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/asleepLine/export")
    public void asleepLine(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String xd = request.getParameter("xd");
        Integer nj = Integer.parseInt(request.getParameter("nj"));
        String classId = request.getParameter("bj");
        if("0".equals(xd)){
            xd=null;
        }
        if("0".equals(nj)||nj==0){
            nj=null;
        }
        if("0".equals(classId)){
            classId=null;
        }
        Integer fromDate = NumberConvertUtil.convertS2I(request.getParameter("fromDate"));
        Integer toDate = NumberConvertUtil.convertS2I(request.getParameter("toDate"));
        List<Statistics> dayStatistics = dailyService.findDayStatisticsByTeacherId(getLoginUser().getRefId(), xd, nj, classId, fromDate, toDate);
//        List<MessageView> scoreList = pageInfo.getList();
        List<ASleepView> astList = new ArrayList<ASleepView>();
        Map<String, String> asleepList = new LinkedHashMap<String, String>();
        if (fromDate != null && toDate != null) {
            asleepList = getDatesBetweenTwoDate(fromDate.toString(), toDate.toString());
        }
        if (asleepList.size() > 0 && dayStatistics.size() > 0) {
            for (Statistics each : dayStatistics) {
                if (asleepList.containsKey(each.getInfoDate().toString())) {
                    asleepList.put(each.getInfoDate().toString(), ConstantUtil.getAsleepTime(each.getAvgAsleepTime()));
                }
            }
        }
        Iterator<String> iter = asleepList.keySet().iterator();
        while(iter.hasNext()){
            String key=iter.next();
            String value = asleepList.get(key);
            ASleepView view = new ASleepView();
            view.setDays(key);
            view.setTime(value);

            astList.add(view);
        }

        String fileName = "入睡时间趋势" + DateUtils.getCurrentTime() + ".xlsx";
        new ExportExcel("入睡时间趋势", "", ASleepView.class).setDataList(astList).write(response, fileName).dispose();

    }


    /**
     * 睡眠统计(视图)
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sleep/index")
    public String sleepIndex(HttpServletRequest request, Model model) {

        String xd = null;
        Integer nj = null;
        String classId = null;
        int pageSize = 10;
        int pageNum = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Integer fromDate = NumberConvertUtil.convertS2I(format.format(cal.getTime()));
        Integer toDate = NumberConvertUtil.convertS2I(format.format(cal.getTime()));
        PageInfo<MessageView> pageInfo = getTableData(xd, nj, classId, fromDate, toDate, pageSize, pageNum, false);
        HashMap<String, Object> map = getData(xd, nj, classId, fromDate, toDate);
//        List<CascadingView> cascadingViews = deviceService.getCascadingViewByTeacherId(getLoginUser().getSchoolId(),getLoginUser().getId());
        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());

        try {
            Statistics statistics = (Statistics) map.get("statistics");

            //增
            Map messageMap = (Map) map.get("date");
            model.addAttribute("messageMap",messageMap);

            Integer asleep = statistics.getAvgAsleepTime();
            String _asleep = ConstantUtil.getAsleepTime(asleep);
            model.addAttribute("asleep", _asleep);
            model.addAttribute("statistics", statistics);
            model.addAttribute("cascadingViews", cascadingViews);

            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("dateList", map.get("sleepList"));
            model.addAttribute("asleepList", map.get("asleepList"));
            model.addAttribute("diffStatistics", map.get("diffStatistics"));
            model.addAttribute("sportStandard", map.get("sportStandard"));
            model.addAttribute("sleepStandard", map.get("sleepStandard"));
            model.addAttribute("asleepStandard", map.get("asleepStandard"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/daily/sleep";
    }

    /**
     * 统计数据(Json)
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sport/getdata")
    public String sportJson(HttpServletRequest request) {
        String xd = getParamVal(request, "xd");
        String _nj = getParamVal(request, "nj");
        String classId = getParamVal(request, "classId");
        String _fromDate = getParamVal(request, "fromDate");
        String _toDate = getParamVal(request, "toDate");
        String _pageNum = getParamVal(request, "pageNum");
        String _pageSize = getParamVal(request, "pageSize");
        String _onlyTable = getParamVal(request, "onlyTable");  //true:只获取表格
        String _sportTable = getParamVal(request, "sportTable");    //true:获取运动表格   false:获取睡眠表格

        classId = ZeroIsNull(classId);
        xd = ZeroIsNull(xd);

        boolean onlyTable = Boolean.parseBoolean(_onlyTable);
        boolean sportTable = Boolean.parseBoolean(_sportTable);
        Integer nj = ZeroIsNull(NumberConvertUtil.convertS2I(_nj));
        Integer fromDate = ZeroIsNull(NumberConvertUtil.convertS2I(_fromDate));
        Integer toDate = ZeroIsNull(NumberConvertUtil.convertS2I(_toDate));
        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);
        JsonObject jsonObject = new JsonObject();
        try {
            if (onlyTable == false) {
                HashMap<String, Object> map = getData(xd, nj, classId, fromDate, toDate);
                Statistics statistics = (Statistics) map.get("statistics");
                Integer asleep = statistics.getAvgAsleepTime();
                String _asleep = ConstantUtil.getAsleepTime(asleep);
                jsonObject.addProperty("asleep", _asleep);
                jsonObject.addProperty("sportStandard", map.get("sportStandard").toString());
                jsonObject.addProperty("sleepStandard", map.get("sleepStandard").toString());
                jsonObject.addProperty("asleepStandard", map.get("asleepStandard").toString());
                jsonObject.addProperty("dateList", GsonUtil.toJson(map.get("dateList")));
                jsonObject.addProperty("asleepList", GsonUtil.toJson(map.get("asleepList")));
                jsonObject.addProperty("asleepValueList", GsonUtil.toJson(map.get("asleepValueList")));
                jsonObject.addProperty("sleepList", GsonUtil.toJson(map.get("sleepList")));
                jsonObject.addProperty("statistics", GsonUtil.toJson(map.get("statistics")));
                jsonObject.addProperty("diffStatistics", GsonUtil.toJson(map.get("diffStatistics")));
                //修改睡眠、运动饼图的数据
                jsonObject.addProperty("shuju", GsonUtil.toJson(map.get("date")));
            }

            PageInfo<MessageView> pageInfo = getTableData(xd, nj, classId, fromDate, toDate, pageSize, pageNum, sportTable);
            List<MessageView> messageViews = pageInfo.getList();
            System.out.println("查看："+messageViews);
            jsonObject.addProperty("pageInfo", GsonUtil.toJson(pageInfo.getList()));
            jsonObject.addProperty("total", pageInfo.getTotal());
            jsonObject.addProperty("pages", pageInfo.getPages());
            jsonObject.addProperty("current", pageInfo.getPageNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 日常健康管理
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/manage/index")
    public String manage(Model model) {
        String teacherId = getLoginUser().getRefId();
        List<StandardView> standardViews = dailyService.findStandardByTeahcerId(teacherId);
        model.addAttribute("standardViews", standardViews);
        return "/daily/manage";
    }

    /**
     * 日常管理-保存
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manage/save", method = RequestMethod.POST)
    public ResultEntity saveManage(HttpServletRequest request) {
        String data = getParamVal(request, "data");
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(data).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            int sportStandard = jsonObject.get("sport").getAsInt();
            int sleepStandard = jsonObject.get("sleep").getAsInt();
            int hour = jsonObject.get("hour").getAsInt();
            int min = jsonObject.get("min").getAsInt();
            int asleepStandard = asleepTimeMin(hour,min);
            HealthyStandard healthyStandard = new HealthyStandard();
            healthyStandard.setId(id);
            healthyStandard.setSportStandard(sportStandard);
            healthyStandard.setSleepStandard(sleepStandard);
            healthyStandard.setAsleepStandard(asleepStandard);
            dailyService.saveStandard(healthyStandard);
        }

        return ResultEntity.newResultEntity("保存成功", null);
    }

    /**
     * 图形数据
     *
     * @param xd
     * @param nj
     * @param classId
     * @param fromDate
     * @param toDate
     * @return
     */
    public HashMap<String, Object> getData(String xd, Integer nj, String classId, Integer
            fromDate, Integer toDate) {
        String teacherId = getLoginUser().getRefId();
        HashMap<String, Object> map = new HashMap<String, Object>();
        //统计的数据(玫瑰图，饼图)
        Statistics statistics = dailyService.findStatisticsByTeacherId(teacherId, xd, nj, classId, fromDate, toDate); //全部以分钟为单位
        //------------------------------{修改-----------------------------------------------------------
        //修改尝试
        List<Map<Object,Object>> messagePie = dailyService.messagePie(teacherId, xd, nj, classId, fromDate, toDate);
        double sportCount = 0;
        double sleepCount = 0;
        double asleepCount = 0;
        int count=0;
        if (messagePie.size()<=0){
            count=1;
        }else{
            count = messagePie.size();
            for (Map<Object,Object> mapMessage :messagePie) {
                double sportStandard=Integer.parseInt(mapMessage.get("sportStandard").toString())*1.0;
                String sport = mapMessage.get("sportTime").toString();
                double sportTime=Double.parseDouble(sport);
                double sleepStandard=Integer.parseInt(mapMessage.get("sleepStandard").toString())*1.0;
                String sleep = mapMessage.get("sleep").toString();
                double sleepTime=Double.parseDouble(sleep);
                double asleepStandard=Integer.parseInt(mapMessage.get("asleepStandard").toString())*1.0;
                String asleep = mapMessage.get("asleep").toString();
                double asleepTime=Double.parseDouble(asleep);
                if (sportTime>=sportStandard){
                    sportCount = sportCount + 1;
                }
                if (asleepTime>=asleepStandard){
                    asleepCount = asleepCount + 1;
                }
                if (sleepTime>sleepStandard){
                    sleepCount = sleepCount + 1;
                }
            }
        }

        double sportPercentage = (sportCount/count)*100;
        double sleepPercentage = (sleepCount/count)*100;
        double asleepPercentage = (asleepCount/count)*100;
        Map date = new HashMap();
        date.put("sportPercentage",sportPercentage);
        date.put("sleepPercentage",sleepPercentage);
        date.put("asleepPercentage",asleepPercentage);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String toD = toDate.toString();
        String toF = fromDate.toString();

        long day=0l;
        try {
            day = sdf.parse(toD).getTime() - sdf.parse(toF).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day=day /  86400000;
        //作对比的统计数据
//        int difference = toDate - fromDate + 1;原来的
        int difference =(int)day+1;
        //----------------------------------修改}---------------------------------------------------------
        Statistics diffStatistics = dailyService.findStatisticsByTeacherId(teacherId, xd, nj, classId,
                getDateBefore(fromDate, difference, true), getDateBefore(toDate, difference, true));
        //每天的数据（折线图）
        List<Statistics> dayStatistics = dailyService.findDayStatisticsByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
        Map<String, String> dateList = new LinkedHashMap<String, String>();
        Map<String, String> sleepList = new LinkedHashMap<String, String>();
        Map<String, String> asleepList = new LinkedHashMap<String, String>();
        Map<String, String> asleepValueList = new LinkedHashMap<String, String>();
        if (fromDate != null && toDate != null) {
            dateList = getDatesBetweenTwoDate(fromDate.toString(), toDate.toString());
        }
        sleepList.putAll(dateList);
        asleepList.putAll(dateList);
        asleepValueList.putAll(dateList);
        if (dateList.size() > 0 && dayStatistics.size() > 0) {
            for (Statistics each : dayStatistics) {
                if (dateList.containsKey(each.getInfoDate().toString())) {
                    dateList.put(each.getInfoDate().toString(), each.getAvgSport().toString());
                    sleepList.put(each.getInfoDate().toString(), each.getAvgSleep().toString());
                    asleepList.put(each.getInfoDate().toString(), ConstantUtil.getAsleepTime(each.getAvgAsleepTime()));
                    asleepValueList.put(each.getInfoDate().toString(), each.getAvgAsleepTime().toString());
                }
            }
        }

        //健康标准
        String sportStandard = "标准";
        String sleepStandard = "标准";
        String asleepStandard = "标准";
        if (xd != null && nj != null) {
            HealthyStandard healthyStandard = dailyService.findStandardByClass(getLoginUser().getSchoolId(), xd, nj);
            if (healthyStandard != null) {
                sportStandard = healthyStandard.getSportStandard().toString();
                sleepStandard = healthyStandard.getSleepStandard().toString();
                asleepStandard = ConstantUtil.getAsleepTime(healthyStandard.getAsleepStandard());
            }
        }

        //统计的数据，如果有null，则为0
        changeToZero(statistics);
        changeToZero(diffStatistics);
        diffStatistics = getDifference(statistics, diffStatistics);
        map.put("sportStandard", sportStandard);
        map.put("sleepStandard", sleepStandard);
        map.put("asleepStandard", asleepStandard);
        map.put("dateList", dateList.values());
        map.put("sleepList", sleepList.values());
        map.put("asleepList", asleepList.values());
        map.put("statistics", statistics);
        map.put("diffStatistics", diffStatistics);
        map.put("asleepValueList", asleepValueList.values());
        //增
        map.put("date",date);
        return map;
    }

    /**
     * 表格数据
     *
     * @param xd
     * @param nj
     * @param classId
     * @param fromDate
     * @param toDate
     * @param pageSize
     * @param pageNum
     * @param sport    true:运动统计   false:睡眠统计
     * @return
     */
    //原来的，修改后的写在下面
//    public PageInfo<MessageView> getTableData(String xd, Integer nj, String classId, Integer
//            fromDate, Integer toDate, Integer pageSize, Integer pageNum, boolean sport) {
//        String teacherId = getLoginUser().getRefId();
//        if (sport == true) {
//            if (pageSize==-1){
//
//            }else{
//                PageHelper.startPage(pageNum, pageSize);
//            }
//            Page<MessageView> messageViews = (Page<MessageView>) dailyService.findMessageByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
//            System.out.println(dailyService.findMessageByTeacherId(teacherId, xd, nj, classId, fromDate, toDate));
//            PageInfo<MessageView> pageInfo = new PageInfo<MessageView>(messageViews);
//            return pageInfo;
//        } else {
//            if (pageSize==-1){
//
//            }else{
//                PageHelper.startPage(pageNum, pageSize);
//            }
//            Page<MessageView> messageViews = (Page<MessageView>) dailyService.findMessageSleepByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
//            PageInfo<MessageView> pageInfo = new PageInfo<MessageView>(messageViews);
//            return pageInfo;
//        }
//    }

    public PageInfo<MessageView> getTableData(String xd, Integer nj, String classId, Integer
            fromDate, Integer toDate, Integer pageSize, Integer pageNum, boolean sport) {
        String teacherId = getLoginUser().getRefId();
        if (sport == true) {
            PageInfo<MessageView> pageInfo = dailyService.findMessageByTeacherId(teacherId, xd, nj, classId, pageSize,pageNum,fromDate, toDate);
            return pageInfo;
        } else {
            PageInfo<MessageView> pageInfo = dailyService.findMessageSleepByTeacherId(teacherId, xd, nj, classId,pageSize,pageNum, fromDate, toDate);
            return pageInfo;
        }
    }
    /**
     * 入睡时间(hour,min 转换成 分钟)
     * @param hour
     * @param min
     * @return
     */
    //入睡时间转换读不懂。。。。
    public Integer asleepTimeMin(Integer hour,Integer min) {
        Integer total = 0 ;
        if(hour<8){
            total = hour * 60 + 24 * 60;
        }else{
            total = hour * 60;
        }
        total += min;
        return  total;
    }

    /**
     * 0转换为null
     *
     * @param value
     * @return
     */
    public String ZeroIsNull(String value) {
        if (value == null) return value;
        if (StringUtils.equals(value, "0")) {
            return null;
        } else {
            return value;
        }
    }

    public Integer ZeroIsNull(Integer value) {
        if (value == null) return value;
        if (value.intValue() == 0) {
            return null;
        } else {
            return value;
        }
    }

    /**
     * 获取两个日期期间的值(日期，0)
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, String> getDatesBetweenTwoDate(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Map<String, String> lDate = new LinkedHashMap<String, String>();
        try {
            Date dBegin = sdf.parse(fromDate);
            Date dEnd = sdf.parse(toDate);
            lDate.put(sdf.format(dBegin.getTime()), "0.0");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dBegin);
            boolean bContinue = true;
            while (bContinue) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                if (dEnd.after(cal.getTime())) {
                    lDate.put(sdf.format(cal.getTime()), "0.0");
                } else {
                    break;
                }
            }
            lDate.put(sdf.format(dEnd.getTime()), "0.0");
            return lDate;
        } catch (Exception e) {

        }
        return lDate;
    }

    /**
     * statistics中null转换成0
     *
     * @param statistics
     */
    public void changeToZero(Statistics statistics) {
        try {
            Method[] method = Statistics.class.getDeclaredMethods();
            for (Method m : method) {
                Object val;
                int index = m.getName().indexOf("get");
                if (index != -1) {
                    val = m.invoke(statistics);
                    if (val == null) {
                        for (Method m1 : method) {
                            if (m1.getName().equals("set" + m.getName().substring(index + 3))) {
                                String typeName = m1.getParameterTypes()[0].getName();
                                if (typeName.equals(Integer.class.getName())) {
                                    m1.invoke(statistics, 0);
                                }
                                if (typeName.equals(Double.class.getName())) {
                                    m1.invoke(statistics, 0.0);
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

    /**
     * 获取间隔数日的日期
     *
     * @param d    当前日期yyyyMMdd
     * @param day  间隔day
     * @param flag true:之前 false：之后
     * @return
     */
    public static int getDateBefore(int d, int day, boolean flag) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = format.parse(String.valueOf(d));
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            if (flag == true)
                now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
            else now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
            Date before = now.getTime();
            String b = format.format(before);
            return NumberConvertUtil.convertS2I(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    //这个得到的是判断的判断当前和过去的差值用于判断玫瑰图下方的表格中用到
    /**
     * 所有平均值的差（now - comparison）
     *
     * @param now
     * @param comparison
     * @return
     */
    public Statistics getDifference(Statistics now, Statistics comparison) {
        Statistics statistics = new Statistics();
        statistics.setAvgAsleepTime(now.getAvgAsleepTime() - comparison.getAvgAsleepTime());
        statistics.setAvgSport(now.getAvgSport() - comparison.getAvgSport());
        statistics.setAvgCalories(now.getAvgCalories() - comparison.getAvgCalories());
        statistics.setAvgDistance(now.getAvgDistance() - comparison.getAvgDistance());
        statistics.setAvgDeepSleep(now.getAvgDeepSleep() - comparison.getAvgDistance());
        statistics.setAvgSleep(now.getAvgSleep() - comparison.getAvgSleep());
        statistics.setAvgAsleepTime(now.getAvgAsleepTime() - comparison.getAvgAsleepTime());
        statistics.setAvgWalkDay(now.getAvgWalkDay() - comparison.getAvgWalkDay());
        statistics.setAvgSleepQuality(now.getAvgSleepQuality() - comparison.getAvgSleepQuality());
        return statistics;
    }
}
