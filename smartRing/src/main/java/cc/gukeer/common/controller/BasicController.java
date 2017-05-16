package cc.gukeer.common.controller;

import cc.gukeer.common.exception.ErrcodeException;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.LoggerWrapper;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.common.MapKeyComparator;
import cc.gukeer.smartRing.common.ProjectConfig;
import cc.gukeer.smartRing.persistence.entity.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by conn on 2016/8/5.
 */
@Controller
public abstract class BasicController extends LoggerWrapper {

    protected int getPageNum(HttpServletRequest request) {

        int _pageNum = 0;
        if (null == request) return _pageNum;

        String pageNum = getParamVal(request, "pageNum");
        if (StringUtils.isEmpty(pageNum)) return _pageNum;

        _pageNum = NumberConvertUtil.convertS2I(pageNum);

        return _pageNum;
    }

    protected int getPageSize(HttpServletRequest request) {

        int _pageSize = ProjectConfig.DEFAULT_PAGE_SIZE;
        if (null == request) return _pageSize;

        String pageSize = getParamVal(request, "pageSize");
        if (StringUtils.isEmpty(pageSize)) return _pageSize;

        _pageSize = NumberConvertUtil.convertS2I(pageSize);
        if (_pageSize == 0) {
            _pageSize = ProjectConfig.DEFAULT_PAGE_SIZE;
        }

        return _pageSize;
    }


    protected String getParamVal(HttpServletRequest request, String key) {
        return getParamVal(request, key, "");
    }

    protected String getParamVal(HttpServletRequest request, String key, String defaultVal) {

        String val = defaultVal;
        if (null == request) return val;

        String value = request.getParameter(key);
        if (StringUtils.isEmpty(value)) return val;

        return value;
    }

    protected String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (null == user || StringUtils.isEmpty(user.getId())) {
            throw new ErrcodeException("登录超时，请重新登录");
        }
        return user;
    }

    protected Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        if (null == subject) {
            throw new ErrcodeException("登录超时，请重新登录");
        }
        return subject;
    }

    protected Map<String, List> getSchoolNj(List<Map> paramList) {
        //使用org_grade_class和 org_class_section翻译
        //List<Map> dataList = sportsService.getSchoolClass(user.getSchoolId(), sportsService.getClassByTeacher(teacherId));
        List<Map> dataList = paramList;
        Map dataItem = new HashMap();
        Map<String, List> resultMap = new HashMap<String, List>();
        if (GukeerStringUtil.isNullOrEmpty(dataList)) {
            return new HashMap<String, List>();
        }
        for (int i = 0; i < dataList.size(); i++) {
            dataItem = dataList.get(i);
            //String tran = ConstantUtil.translateNj(dataItem.get("indexName").toString()).get("njName").toString();
            String tran = dataItem.get("name") + ConstantUtil.getValueByKeyAndFlag(
                    NumberConvertUtil.convertS2I(dataItem.get("nj").toString()), "nj");
            dataItem.put("indexName", tran);
            if (resultMap.containsKey(dataItem.get("indexName"))) {
                resultMap.get(dataItem.get("indexName")).add(dataItem);
            } else {
                List<Map> list = new ArrayList<Map>();
                list.add(dataItem);
                resultMap.put(dataItem.get("indexName").toString(), list);
            }
        }
        //return resultMap;
        return sortMapByKey(resultMap);
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    protected static Map<String, List> sortMapByKey(Map<String, List> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, List> sortMap = new TreeMap<String, List>(
                new MapKeyComparator());
        sortMap.putAll(map);

        return sortMap;
    }

    protected JsonObject getJsonObjectValue(JsonObject jsonObject, String key) {
        JsonObject val = null;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = (JsonObject)jsonObject.get(key);
        }
        return val;
    }

    protected JsonArray getJsonArrayValue(JsonObject jsonObject, String key) {
        JsonArray val = null;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = (JsonArray)jsonObject.get(key);
        }
        return val;
    }

    protected String getJsonStringValue(JsonObject jsonObject, String key) {
        String val = null;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsString();
        }
        return val;
    }

    protected int getJsonIntValue(JsonObject jsonObject, String key) {
        int val = 0;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsInt();
        }
        return val;
    }

    protected long getJsonLongValue(JsonObject jsonObject, String key) {
        long val = 0;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsLong();
        }
        return val;
    }

    protected double getJsonDoubleValue(JsonObject jsonObject, String key) {
        double val = 0;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsDouble();
        }
        return val;
    }

    protected float getJsonFloatValue(JsonObject jsonObject, String key) {
        float val = 0;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsFloat();
        }
        return val;
    }

    protected boolean getJsonBoolValue(JsonObject jsonObject, String key) {
        boolean val = false;
        if (null == jsonObject || StringUtils.isBlank(key)) {
            return val;
        }

        if (jsonObject.has(key)) {
            val = jsonObject.get(key).getAsBoolean();
        }
        return val;
    }
}
