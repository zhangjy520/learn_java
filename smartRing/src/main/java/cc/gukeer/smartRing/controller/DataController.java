package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.persistence.entity.DeviceRing;
import cc.gukeer.smartRing.persistence.entity.DeviceStation;
import cc.gukeer.smartRing.persistence.entity.RingMessage;
import cc.gukeer.smartRing.persistence.entity.ScanLog;
import cc.gukeer.smartRing.service.DataService;
import cc.gukeer.smartRing.service.DeviceService;
import cc.gukeer.smartRing.service.SchoolService;
import cc.gukeer.smartRing.service.StudentService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 与数据中心交互controller
 *
 * Created by conn on 2016/8/29.
 */
@Controller
@RequestMapping(value = "/data")
public class DataController extends BasicController {

    @Autowired
    DataService dataService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    StudentService studentService;

    @Autowired
    SchoolService schoolService;

    /**
     * 接收基础数据
     *
     * @param request
     * @param model
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/post/basic/{schoolId}", method = RequestMethod.POST)
    public ResultEntity recvBasicData(HttpServletRequest request, Model model, @PathVariable String schoolId) {

        int count = schoolService.countSchoolsById(schoolId);
        if (count == 0) {
            return ResultEntity.newErrEntity("not found school by id.");
        }

        String data = getParamVal(request, "data");
        JsonElement element = new JsonParser().parse(data);
        if (null == element || !element.isJsonArray()) {
            return ResultEntity.newErrEntity("data format error.");
        }
        JsonArray array = element.getAsJsonArray();
        for (JsonElement item : array) {
            JsonObject jsonObject = item.getAsJsonObject();

            RingMessage message = new RingMessage();
            //getJsonStringValue通过key值得到value
            message.setStudentId(getJsonStringValue(jsonObject, "studentId"));
            message.setStudentName(getJsonStringValue(jsonObject, "studentName"));
            message.setStationMac(getJsonStringValue(jsonObject, "stationMac"));
            message.setRingMac(getJsonStringValue(jsonObject, "ringMac"));

            // calculate
            setSleepTime(message, getJsonStringValue(jsonObject, "sleepHour"));
            setSportTime(message, getJsonStringValue(jsonObject, "sportHour"));
            setInfoDate(message, getJsonStringValue(jsonObject, "infoDate"));

            message.setBalance(getJsonStringValue(jsonObject, "balance"));
            message.setLastUpdate(getJsonLongValue(jsonObject, "lastUpdate"));
            message.setLevel(getJsonIntValue(jsonObject, "level"));
            message.setVersion(getJsonStringValue(jsonObject, "version"));
            message.setCaloriesDay(getJsonIntValue(jsonObject, "caloriesDay"));
            message.setDistanceDay(getJsonIntValue(jsonObject, "distanceDay"));
            message.setJogDay(getJsonIntValue(jsonObject, "jogDay"));
            message.setRunDay(getJsonIntValue(jsonObject, "runDay"));
            message.setSleepQuality(0f);
            message.setAsleepTime(0l);
            message.setStepDay(getJsonIntValue(jsonObject, "stepDay"));
            message.setWalkDay(getJsonIntValue(jsonObject, "walkDay"));
            message.setDelFlag(0);

            int num = deviceService.saveRingMessage(message);
            logger.info("==save {} ring message success", num);
        }

        return ResultEntity.newResultEntity();
    }

    /**
     * 接收日志信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/post/scanlog/{schoolId}", method = RequestMethod.POST)
    public ResultEntity recvScanLog(HttpServletRequest request, @PathVariable String schoolId) {

        int count = schoolService.countSchoolsById(schoolId);
        if (count == 0) {
            return ResultEntity.newErrEntity("not found school by id.");
        }

        String data = getParamVal(request, "data");
        JsonElement element = new JsonParser().parse(data);
        if (null == element || !element.isJsonArray()) {
            return ResultEntity.newErrEntity("data format error.");
        }

        JsonArray array = element.getAsJsonArray();
        for (JsonElement item : array) {
            JsonObject jsonObject = item.getAsJsonObject();

            ScanLog scanLog = new ScanLog();
            scanLog.setId(ConstantUtil.getPrimaryKey());
            scanLog.setMac(getJsonStringValue(jsonObject, "mac"));
            scanLog.setRingSignal(getJsonIntValue(jsonObject, "signal"));
            scanLog.setStationMac(getJsonStringValue(jsonObject, "stationMac"));
            scanLog.setType(getJsonIntValue(jsonObject, "type"));
            scanLog.setStudentId(getJsonStringValue(jsonObject, "studentId"));
            scanLog.setStudentName(getJsonStringValue(jsonObject, "studentName"));

            int num = deviceService.saveScanLog(scanLog);

            logger.info("save {} log success, type is: {}", num, scanLog.getType());
        }

        return ResultEntity.newResultEntity();
    }

    /**
     * 实时心率数据 [暂时为空]
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/post/heartbeat/{schoolId}", method = RequestMethod.POST)
    public ResultEntity recvHeartbeat(HttpServletRequest request, Model model) {

        String heartbeat = request.getParameter("heartbeat");

        logger.info("receive data is: {}", heartbeat);

        return ResultEntity.newResultEntity();
    }

    /**
     * 数据中心获取手环数据
     *
     * @param request
     * @param schoolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/rings/{schoolId}", method = RequestMethod.GET)
    public ResultEntity ringList(HttpServletRequest request, @PathVariable String schoolId) {

        String timeStamp = request.getParameter("timeStamp");

        int pageSize = NumberConvertUtil.convertS2I(request.getParameter("pageSize"));
        pageSize = (pageSize == 0 ? 100 : pageSize);

        PageInfo ringsPage = deviceService.ringList(schoolId, pageSize,timeStamp);

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        String timeStampRes = null;

        List<DeviceRing> ringsList = new ArrayList<DeviceRing>();
        if (!GukeerStringUtil.isNullOrEmpty(ringsPage)) {
            ringsList = ringsPage.getList();
            timeStampRes = ringsList.get(0).getLastUpdate().toString();
            for (DeviceRing ring : ringsList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("schoolId", ring.getSchoolId());
                map.put("studentId", ring.getStudentId());
                map.put("studentName", ring.getStudentName());
                map.put("stationName", ring.getStationName());
                map.put("stationMac",ring.getStationMac());
                map.put("ringType", ring.getType());
                map.put("ringStatus", ring.getStatus());
                map.put("ringMac", ring.getMac());
                map.put("lastUpdate", ring.getLastUpdate());
                resList.add(map);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("timestamp", timeStampRes);
        result.put("schoolId", schoolId);
        result.put("list", resList);
        return ResultEntity.newResultEntity(result);
    }

    /**
     * 数据中心获取基站数据
     *
     * @param request
     * @param schoolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/stations/{schoolId}", method = RequestMethod.GET)
    public ResultEntity stationList(HttpServletRequest request, @PathVariable String schoolId) {
        String timeStamp = request.getParameter("timeStamp");
        int pageSize = NumberConvertUtil.convertS2I(request.getParameter("pageSize"));
        pageSize = (pageSize == 0 ? 100 : pageSize);
        PageInfo ringsPage = deviceService.stationList(schoolId, pageSize,timeStamp);
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        String timeStampRes = null;
        List<DeviceRing> ringsList = new ArrayList<DeviceRing>();
        if (!GukeerStringUtil.isNullOrEmpty(ringsPage)) {
            ringsList = ringsPage.getList();
            timeStampRes = ringsList.get(0).getLastUpdate().toString();
            for (DeviceRing ring : ringsList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("schoolId", ring.getSchoolId());
                map.put("studentId", ring.getStudentId());
                map.put("studentName", ring.getStudentName());
                map.put("stationName", ring.getStationName());
                map.put("stationMac",ring.getStationMac());
                map.put("ringType", ring.getType());
                map.put("ringStatus", ring.getStatus());
                map.put("ringMac", ring.getMac());
                map.put("lastUpdate", ring.getLastUpdate());
                resList.add(map);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("timestamp", timeStampRes);
        result.put("schoolId", schoolId);
        result.put("list", resList);
        return ResultEntity.newResultEntity(result);
    }

    /**
     * old
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public ResultEntity receive(HttpServletRequest request, Model model) {

        String data = request.getParameter("data");

        logger.info("receive data is: {}", data);
        data = "{\"name\":\"rk3368\",\"timStamp\":1485080986200,\"datalist\":[{\"detail\":[{\"date\":1485080986196,\"id\":0,\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 2 0 0 0 0 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \"},{\"caloriesDay\":\"0\",\"date\":1484994586196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 1 0 0 0 1 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"},{\"caloriesDay\":\"0\",\"date\":1484908186196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 0 0 0 0 2 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"}],\"balance\":\"0.0\",\"time\":1485080986196,\"mac\":\"C7:67:9A:30:9B:C1\",\"version\":\"1.6.0\",\"signal\":\"0\",\"level\":\"70\"},{\"detail\":[{\"caloriesDay\":\"0\",\"date\":1485080986196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 2 0 0 0 0 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"},{\"caloriesDay\":\"0\",\"date\":1484994586196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 1 0 0 0 1 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"},{\"caloriesDay\":\"0\",\"date\":1484908186196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 0 0 0 0 2 0 0 1 3 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"}],\"balance\":\"0.0\",\"time\":1485080986196,\"mac\":\"F7:77:CB:27:10:FD\",\"version\":\"1.6.4\",\"signal\":\"0\",\"level\":\"65\"},{\"detail\":[{\"date\":1485080986196,\"id\":0,\"sleepQuarter\":\"3 3 3 1 0 3 3 3 3 3 2 0 0 1 0 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 2 0 0 0 0 0 0 0 0 \",\"sportHour\":\"0 27 0 0 0 0 0 0 0 28 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \"},{\"caloriesDay\":\"2\",\"date\":1484994586196,\"distanceDay\":\"27\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 1 0 0 0 1 0 0 0 0 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"55\",\"walkDay\":\"55\"},{\"caloriesDay\":\"0\",\"date\":1484908186196,\"distanceDay\":\"0\",\"id\":0,\"jogDay\":\"0\",\"runDay\":\"0\",\"sleepQuarter\":\"3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 0 0 1 3 3 2 0 1 0 0 0 1 0 1 1 0 0 0 0 2 0 0 0 0 \",\"sportHour\":\"0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \",\"stepDay\":\"0\",\"walkDay\":\"0\"}],\"balance\":\"0.0\",\"time\":1485080986196,\"mac\":\"F7:E9:CA:80:A1:66\",\"version\":\"1.1.0\",\"signal\":\"0\",\"level\":\"91\"}],\"mac\":\"22:22:4B:23:C2:64\"}";

        JsonObject dataJson = (JsonObject) new JsonParser().parse(data);
        String stationMac = getJsonStringValue(dataJson, "mac");
        String stationName = getJsonStringValue(dataJson, "name");
        JsonArray dataList = dataJson.getAsJsonArray("datalist");

        for (JsonElement ring : dataList) {
            JsonObject jsonObject = ring.getAsJsonObject();
            String ringMac = getJsonStringValue(jsonObject, "mac");
            String balance = getJsonStringValue(jsonObject, "balance");
            String level = getJsonStringValue(jsonObject, "level");
            String version = getJsonStringValue(jsonObject, "version");
            String lastUpdate = getJsonStringValue(jsonObject, "time");
            JsonArray dateList = jsonObject.getAsJsonArray("detail");
            for (int i = 0; i < dateList.size(); i++) {
                JsonObject dateData = (JsonObject) dateList.get(i);
                RingMessage ringMessage = new RingMessage();
                String caloriesDay = getJsonStringValue(dateData, "caloriesDay");
                String infoDate = getJsonStringValue(dateData, "date");
                String distanceDay = getJsonStringValue(dateData, "distanceDay");
                String jogDay = getJsonStringValue(dateData, "jogDay");
                String runDay = getJsonStringValue(dateData, "runDay");
                String sleepQuarter = getJsonStringValue(dateData, "sleepQuarter");
                String sportHour = getJsonStringValue(dateData, "sportHour");
                String stepDay = getJsonStringValue(dateData, "stepDay");
                String walkDay = getJsonStringValue(dateData, "walkDay");

                setSleepTime(ringMessage, sleepQuarter);
                setSportTime(ringMessage, sportHour);
                setInfoDate(ringMessage, infoDate);

                ringMessage.setBalance(balance);
                ringMessage.setRingMac(ringMac);
                ringMessage.setLastUpdate(lastUpdate != null && lastUpdate != ""
                        ? Long.valueOf(lastUpdate) : 0l);
                ringMessage.setLevel(NumberConvertUtil.convertS2I(level));
                ringMessage.setVersion(version);
                ringMessage.setCaloriesDay(NumberConvertUtil.convertS2I(caloriesDay));
                ringMessage.setDistanceDay(NumberConvertUtil.convertS2I(distanceDay));
                ringMessage.setJogDay(NumberConvertUtil.convertS2I(jogDay));
                ringMessage.setRunDay(NumberConvertUtil.convertS2I(runDay));
                ringMessage.setSleepQuality(0f);
                ringMessage.setAsleepTime(0l);
                ringMessage.setStepDay(NumberConvertUtil.convertS2I(stepDay));
                ringMessage.setWalkDay(NumberConvertUtil.convertS2I(walkDay));
                ringMessage.setDelFlag(0);

                int count = deviceService.saveRingMessage(ringMessage);
                saveScanLog(null, ringMac, stationMac, stationName, Long.valueOf(infoDate), 0, 0);
            }
        }

        return ResultEntity.newResultEntity();
    }

    /**
     * old
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receive/location", method = RequestMethod.POST)
    public ResultEntity receivePosition(HttpServletRequest request, Model model) {

        String location = request.getParameter("location");

        logger.info("receive location is: {}", location);

//        location = "{\"timStamp\":1484896004436,\"rings\":[{\"signal\":\"-36\",\"mac\":\"DA:9B:3B:7B:22:94\"},{\"signal\":\"-44\",\"mac\":\"C7:67:9A:30:9B:C1\"},{\"signal\":\"-93\",\"mac\":\"EF:42:4C:54:2B:9A\"},{\"signal\":\"-52\",\"mac\":\"C3:AD:13:07:85:53\"},{\"signal\":\"-93\",\"mac\":\"F7:E9:CA:80:A1:66\"},{\"signal\":\"-42\",\"mac\":\"F7:77:CB:27:10:FD\"}],\"mac\":\"84:BE:52:AD:53:00\",\"name\":\"T1-A21w\"}";

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(location);

        String timeStamp = getJsonStringValue(jsonObject, "timStamp");
        String locationName = getJsonStringValue(jsonObject, "name");
        String stationMac = getJsonStringValue(jsonObject, "mac");
        JsonArray rings = jsonObject.getAsJsonArray("rings");
        int count = 0;
        for (JsonElement _ring : rings) {
            JsonObject ring = (JsonObject) _ring;
            String signal = getJsonStringValue(ring, "signal");
            String ringMac = getJsonStringValue(ring, "mac");

            count += saveScanLog(null, ringMac, stationMac, locationName,
                    Long.valueOf(timeStamp), 1, Integer.valueOf(signal));
        }

        return ResultEntity.newResultEntity();
    }

    private int saveScanLog(String id, String ringMac, String stationMac, String locationName,
                            long lastUpdateTime, int type, int signal) {

        ScanLog log = new ScanLog();
        if (StringUtils.isNotBlank(id)) {
            log.setId(id);
        }
        log.setMac(ringMac);
        log.setStationMac(stationMac);
        log.setStationName(locationName);
        log.setLastUpdate(lastUpdateTime);
        log.setType(type);
        log.setRingSignal(signal);

        int count = deviceService.saveScanLog(log);
        return count;
    }

    /**
     * old
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/receive/heartbeat", method = RequestMethod.POST)
    public ResultEntity receiveHeartbeat(HttpServletRequest request, Model model) {

        String heartbeat = request.getParameter("heartbeat");

        logger.info("receive data is: {}", heartbeat);

        return ResultEntity.newResultEntity();
    }

    /**
     * 基站 获取 设置
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stations/{mac}/setting", method = RequestMethod.GET)
    public ResultEntity stationSetting(HttpServletRequest request, @PathVariable String mac) {
        if (StringUtils.isBlank(mac)) {
            return ResultEntity.newResultEntity("MAC有误");
        }
        DeviceStation deviceStation = deviceService.findStationByMac(mac);
        if (deviceStation == null) {
            return ResultEntity.newErrEntity("未查询到相关基站");
        }
        Map<String, Integer> msg = new LinkedHashMap<String, Integer>();
        msg.put("type", deviceStation.getType());
        msg.put("base", deviceStation.getStepFrequency());
        msg.put("position", deviceStation.getPositionCycle());
        return ResultEntity.newResultEntity(msg);
    }

    /**
     * 数据中心 获取 当天 体育课手环列表 [正在上体育课的学生] 按照时间
     * {
     *     code:0,
     *     msg:'ok',
     *     data:[
     *          {
     *              start:1111111111, // 数据中心精确到 分钟
     *              end:1111111111,
     *              rings:mac1,mac2,mac3
     *          },
     *          {
     *              start:222222222222,
     *              end:222222222222,
     *              rings:mac4,mac5,mac6
     *          },
     *          {
     *              start:3333333333,
     *              end:3333333333,
     *              rings:mac7,mac8,mac9
     *          }
     *     ]
     * }
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stations/rings", method = RequestMethod.GET)
    public ResultEntity sportStationRingList(HttpServletRequest request) {

        List<String> ringMac = new ArrayList<String>();
        List<DeviceRing> rings = deviceService.findRingByStationMac(null);    //  只包含使用中的手环
        if (rings.size() == 0) {
            return ResultEntity.newErrEntity("未查询到手环");
        } else {
            for (DeviceRing ring : rings) {
                ringMac.add(ring.getMac());
            }
        }
        return ResultEntity.newResultEntity(ringMac);
    }


    /**
     * 设置睡眠相关时间
     *
     * @param ringMessage
     * @param sleepQuarter
     */
    public void setSleepTime(RingMessage ringMessage, String sleepQuarter) {
        int deepSleep = 0;
        int shallowSleep = 0;
        int consciousSleep = 0;
        int awakeTime = 0;
        if (sleepQuarter.indexOf(" ") >= 0) {
            String[] sleepArray = sleepQuarter.split(" ");
            if (sleepArray.length > 0) {
                int deepTimes = 0;
                int shallowTimes = 0;
                int consciousTimes = 0;
                int awakeTimes = 0;
                for (String temp : sleepArray) {
                    if (temp.equals("0")) deepTimes += 1;
                    if (temp.equals("1")) shallowTimes += 1;
                    if (temp.equals("2")) consciousTimes += 1;
                    if (temp.equals("3")) awakeTimes += 1;
                }
                int totalTimes = deepTimes + shallowTimes + consciousTimes + awakeTimes;
                if (totalTimes != 0) {
                    deepSleep = deepTimes / totalTimes * 24 * 60;
                    shallowSleep = shallowTimes / totalTimes * 24 * 60;
                    consciousSleep = consciousTimes / totalTimes * 24 * 60;
                    awakeTime = awakeTimes / totalTimes * 24 * 60;
                }
            }
        }
        ringMessage.setDeepSleep(deepSleep);
        ringMessage.setShallowSleep(shallowSleep);
        ringMessage.setConsciousSleep(consciousSleep);
        ringMessage.setAwakeTime(awakeTime);
    }

    /**
     * 设置运动时间
     *
     * @param ringMessage
     * @param sportHour
     */
    public void setSportTime(RingMessage ringMessage, String sportHour) {
        int sportTime = 0;
        if (sportHour.indexOf(" ") >= 0) {
            String[] sportArray = sportHour.split(" ");
            if (sportArray.length > 0) {
                for (String temp : sportArray) {
                    sportTime += NumberConvertUtil.convertS2I(temp);
                }
            }
        }
        ringMessage.setSportTime(sportTime);
    }

    /**
     * 获取数据的日期
     *
     * @param ringMessage
     * @param infoDate
     */
    public void setInfoDate(RingMessage ringMessage, String infoDate) {
        if (infoDate == null) {
            ringMessage.setInfoDate(0L);
            return;
        } else {
            ringMessage.setInfoDate(Long.valueOf(infoDate));
        }
    }
}
