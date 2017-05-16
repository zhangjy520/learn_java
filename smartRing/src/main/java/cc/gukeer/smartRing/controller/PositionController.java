package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.utils.GsonUtil;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.modelView.CascadingView;
import cc.gukeer.smartRing.persistence.entity.extension.LogDetailView;
import cc.gukeer.smartRing.service.DailyService;
import cc.gukeer.smartRing.service.DeviceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/position")
public class PositionController extends BasicController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    DailyService dailyService;

    /**
     * 实时监控
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/realtime/index")
    public String realTimeIndex(HttpServletRequest request, Model model) {
        String _pageNum = getParamVal(request, "pageNum");
        String _pageSize = getParamVal(request, "pageSize");
        String name = getParamVal(request,"name");

        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -3);
        Long lastUpdate = calendar.getTimeInMillis();    //当前时间向前推几分钟

        List<HashMap<String, String>> stuNum = dailyService.CountStuNumByStu(lastUpdate);
        PageHelper.startPage(pageNum, pageSize);
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Page<HashMap<String, String>> stuMsgPage = (Page<HashMap<String, String>>) dailyService.StuNowPosition(lastUpdate,"%"+name+"%");
        PageInfo stuMsgInfo = new PageInfo(stuMsgPage);
        model.addAttribute("stuNum", stuNum);
        model.addAttribute("stuMsgInfo", stuMsgInfo);
        model.addAttribute("stuName",name);
        return "/position/index";
    }

    /**
     * 位置统计
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/statistics/index")
    public String statistics(HttpServletRequest request, Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        String _today = simpleDateFormat.format(date) + "000000";
        String _yesterday = simpleDateFormat.format(yesterday) + "000000";
        Long fromDate = Long.valueOf(_yesterday);
        Long toDate = Long.valueOf(_today);
        List<HashMap<String, Object>> avgTime = dailyService.avgTimeInArea(null, null, null, fromDate, toDate);
//        List<CascadingView> cascadingViews = deviceService.getCascadingViewByTeacherId(getLoginUser().getSchoolId(),getLoginUser().getId());
        List<CascadingView> cascadingViews = deviceService.getCascadingView(getLoginUser().getSchoolId());
        //转换成小时
        getAvgTime(avgTime);
        model.addAttribute("cascadingViews", cascadingViews);
        model.addAttribute("avgTime", avgTime);
        return "/position/statistics";
    }

    @ResponseBody
    @RequestMapping(value = "/statistics/getdata")
    public String statisticsData(HttpServletRequest request, Model model) {
        String xd = getParamVal(request, "xd");
        String _nj = getParamVal(request, "nj");
        String classId = getParamVal(request, "classId");
        String _fromDate = getParamVal(request, "fromDate");
        String _toDate = getParamVal(request, "toDate");

        classId = ZeroIsNull(classId);
        xd = ZeroIsNull(xd);

        Integer nj = ZeroIsNull(NumberConvertUtil.convertS2I(_nj));
        Long fromDate = 0l;
        Long toDate = 0l;
        if (_fromDate != "" || _toDate != "") {
            fromDate = Long.valueOf(_fromDate);
            toDate = Long.valueOf(_toDate);
        }
        List<HashMap<String, Object>> avgTime = dailyService.avgTimeInArea(xd, nj, classId, fromDate, toDate);
        getAvgTime(avgTime);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("avgTime", GsonUtil.toJson(avgTime));
        return jsonObject.toString();
    }

    /**
     * 个人详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/realtime/personalDetail")
    public String personalDetail(HttpServletRequest request, Model model) {
        String _pageSize = getParamVal(request, "pageSize");
        String _pageNum = getParamVal(request, "pageNum");
        String studentId = getParamVal(request, "studentId");
        String _areaName = getParamVal(request,"areaName");
        String name = getParamVal(request, "name");
        String xh = getParamVal(request, "xh");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");
            _areaName = java.net.URLDecoder.decode(_areaName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int pageSize = NumberConvertUtil.convertS2I(_pageSize) == 0 ? 10 : NumberConvertUtil.convertS2I(_pageSize);
        int pageNum = NumberConvertUtil.convertS2I(_pageNum) == 0 ? 1 : NumberConvertUtil.convertS2I(_pageNum);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = sdf1.format(new Date());
        Long fromDate = Long.valueOf(now);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String today = sdf2.format(new Date()) + "000000";
        Long toDate = Long.valueOf(today);
        fromDate = 20161217000000L;     //当前时间的凌点
        toDate = 20161217230000L;       //当前时间
        List<LogDetailView> detailViews = dailyService.personalDetail(studentId, fromDate, toDate);
        List<LogDetailView> finalMsg = new ArrayList<LogDetailView>();
        if (detailViews.size() != 0) {
            for (int i = 0; i < detailViews.size(); i++) {
                LogDetailView detailView = detailViews.get(i);
                Long startTime = detailView.getLastUpdate();
                Long cycle = detailView.getCycle();
                Long endTime = getEndTime(startTime, cycle);
                int j = detail(detailViews, i);
                if (i != j) {
                    endTime = getEndTime(detailViews.get(i).getLastUpdate(), detailViews.get(i).getCycle());
                    i = j;
                }
                LogDetailView temp = new LogDetailView();
                temp.setAreaName(detailView.getAreaName());
                temp.setLastUpdate(startTime);
                temp.setLeavingTime(endTime);
                finalMsg.add(temp);
            }
        }

        int total = finalMsg.size();
        if (total != 0) {
            try {
                List<LogDetailView> page = new ArrayList<LogDetailView>();
                Double _pages = Math.ceil((double) total / pageSize);
                int pages = _pages.intValue();
                int pageStart = (pageNum - 1) * pageSize + 1;
                int pageEnd = pageNum * pageSize;
                pageStart = pageStart == 0 ? 1 : pageStart;
                pageEnd = pageEnd > total ? total : pageEnd;
                page = finalMsg.subList(pageStart, pageEnd);
                model.addAttribute("page", page);
                model.addAttribute("pages", pages);
                model.addAttribute("pageNum", pageNum);
                model.addAttribute("pageSize", pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("areaName",_areaName);
        model.addAttribute("xh", xh);
        model.addAttribute("total", total);
        model.addAttribute("studentId", studentId);
        return "/position/personal";
    }

    /**
     * 返回离开时的detailView位置
     *
     * @param detailViews
     * @param i           当前位置
     * @return
     */
    public int detail(List<LogDetailView> detailViews, int i) {
        if (detailViews.size() == 0) return i;
        if (i == detailViews.size() - 1) return i;
        LogDetailView detailView = detailViews.get(i);
        Long startTime = detailView.getLastUpdate();
        Long cycle = detailView.getCycle();
        Long endTime = getEndTime(startTime, cycle);
        if (endTime + 500 > detailViews.get(i + 1).getLastUpdate() && endTime - 500 < detailViews.get(i + 1).getLastUpdate()) {  //浮动5秒
            detail(detailViews, i + 1);
        }
        return i;
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

    /**
     * 0转换为null
     *
     * @param value
     * @return
     */
    public Integer ZeroIsNull(Integer value) {
        if (value == null) return value;
        if (value.intValue() == 0) {
            return null;
        } else {
            return value;
        }
    }

    /**
     * 计算结束时间(根据时间戳转换)
     *
     * @param startTime
     * @param cycle
     * @return
     */
    public Long getEndTime(Long startTime, Long cycle) {
        long end = 0l;
        try {
            if (startTime == null || cycle == null) {
                return end;
            } else {
                String str = String.valueOf(startTime);
                long cyc = cycle * 1000;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                long millionSeconds = sdf.parse(str).getTime();//毫秒
                long sfds = millionSeconds + cyc;
                return Long.valueOf(sdf.format(sfds));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return end;
    }

    public void getAvgTime(List<HashMap<String,Object>> avgTime){
        if (avgTime.size() > 0) {
            try {
                Double total = 0.0;
                for (HashMap<String, Object> map : avgTime) {
                    if (!map.containsKey("avgTime")) {
                        BigDecimal zero = new BigDecimal(0);
                        map.put("avgTime", zero);
                    }
                    BigDecimal time = (BigDecimal) map.get("avgTime");
                    NumberFormat nt = NumberFormat.getNumberInstance();
                    nt.setMaximumFractionDigits(1);
                    String str = nt.format(time.doubleValue() / 60 / 60);
                    map.put("avgTime", str);
                    total += Double.valueOf(str);
                }
                for (HashMap<String, Object> map : avgTime) {
                    if (total != 0.0) {
                        NumberFormat nt = NumberFormat.getPercentInstance();
                        nt.setMaximumFractionDigits(1);
                        Double time = Double.valueOf((String) map.get("avgTime"));
                        String str = nt.format(time / total);
                        map.put("percent", str);
                    } else {
                        map.put("percent", "0%");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
