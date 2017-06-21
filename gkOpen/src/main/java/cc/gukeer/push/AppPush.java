package cc.gukeer.push;

import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.HttpClientUtil;
import cc.gukeer.common.utils.LoggerWrapper;
import cc.gukeer.open.common.*;
import cc.gukeer.open.persistence.dao.*;
import cc.gukeer.open.persistence.entity.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/2/27.
 */
public class AppPush extends LoggerWrapper {
    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;
    @Autowired
    AppMapper appMapper;
    @Autowired
    PlatformMapper platformMapper;
    @Autowired
    OpenUserMapper openUsermapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    PersonalMapper personalMapper;


    public void pushAppToPlatform() {
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andOptStatusEqualTo(RefAppPushOptStatus.FAIL.getStatenum());
        //首先根据操作的状态查询中间表的数据库
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        //遍历得到平台数据推送的地址和应用详情数据数据
        if (refPlatformApps.size() > 0) {
            for (RefPlatformApp refPlatformApp : refPlatformApps) {
                App app = null;
                //通过list拿到平台和应用的id
                String platformId = refPlatformApp.getPlatformId();
                String appId = refPlatformApp.getAppId();
                //通过id拿到平台表和应用表的数据
                app = appMapper.selectByPrimaryKey(appId);
                Platform platform = platformMapper.selectByPrimaryKey(platformId);
                int initStatus = platform.getInitStatus();
                if (initStatus == PlatformInitStatus.INITED.getStatenum()) {
                    String urlApp = platform.getUrlApp();
                    String userId = app.getUserId();
                    String companName = null;
                    String developer = null;
                    OpenUserExample openUserExample = new OpenUserExample();
                    openUserExample.createCriteria().andIdEqualTo(userId);
                    List<OpenUser> openUserList = openUsermapper.selectByExample(openUserExample);
                    OpenUser openUser = openUserList.get(0);
                    Integer userType = openUser.getUserType();
                    if (userType == LoginUserType.COMPANY.getStatenum()) {
                        Company company = companyMapper.selectByPrimaryKey(openUser.getCompanyId());
                        companName = company.getBusinessName();
                        developer = company.getDeveloperName();
                    } else {
                        Personal personal = personalMapper.selectByPrimaryKey(openUser.getPersonalId());
                        companName = personal.getCompanyName();
                        developer = personal.getName();
                    }
                    //加密串的处理
                    String clientId = platform.getIdentity();//平台的clientId
                    String random = RandomStringUtils.random(6, true, true);
                    long currentTime = new Date().getTime();
                    //创建拼接对象
                    StringBuilder paramString = new StringBuilder();
                    paramString.append(clientId).append(currentTime).append(random).append(platform.getPassword());
                    //加密处理
                    String security = MD5Utils.md5(paramString.toString());
                    //封装请求参数
                    Map<String, Object> map = new HashMap<>();
                    JSONObject appJson = (JSONObject) JSON.toJSON(app);
                    Integer appStatus = refPlatformApp.getAppStatus();

                    //这个状态对应的是发送给云平台中应用上下线的状态
                    if (appStatus == RefAppPushStatus.FORBIDDEN.getStatenum()) {
                        app.setCheckStatus(CheckStateType.FORBIDDEN.getStatenum());//在已经上线平台进行下线
                    }

                    if (appStatus == RefAppPushStatus.UPDATE_PUSH.getStatenum()) {
                        app.setCheckStatus(1);//云平台上线
                    } else if (app.getCheckStatus() == CheckStateType.AUDIT_SUCCESS.getStatenum()) {
                        app.setCheckStatus(1);//上线
                    }
                    map.put("app", appJson);
                    map.put("developer", developer);
                    map.put("companName", companName);

                    //传输的是平台的clientId
                    map.put("clientId", clientId);
                    map.put("random", random);
                    map.put("time", currentTime);
                    map.put("security", security);
                    String _reback = HttpClientUtil.postContent(urlApp, security, map);
                    JSONObject json = JSON.parseObject(_reback);
                    Integer code = (Integer) json.get("code");
//                    refPlatformApp.setAppStatus(1);
                    //推送返回数据的处理
                    if (code == 0) {
                        refPlatformApp.setOptStatus(RefAppPushOptStatus.SUCC.getStatenum());
                        refPlatformApp.setDataStatus(0);
                        refPlatformApp.setSyncStatus(0);
                        refPlatformApp.setUpdateTime(new Date().getTime());
                        int update = refPlatformAppMapper.updateByPrimaryKeySelective(refPlatformApp);

                    } else {
                        refPlatformApp.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
//                        refPlatformApp.setAppStatus(1);
                        refPlatformApp.setSyncStatus(0);
                        refPlatformApp.setUpdateTime(new Date().getTime());
                        int update = refPlatformAppMapper.updateByPrimaryKeySelective(refPlatformApp);
                    }
                } else {
                    logger.error("平台信息未初始化，请初始化平台信息");
                }
            }
        }
    }

    /**
     * 产品推送失败的再次推送任务
     */
    public void failToPushAgain() {
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andOptStatusEqualTo(0).andAppStatusEqualTo(1);
        //首先根据操作的状态查询中间表的数据库
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        //遍历得到平台数据得的推送的地址和应用详情数据数据
        if (refPlatformApps.size() > 0) {
            for (RefPlatformApp refPlatformApp : refPlatformApps) {
                App app = null;
                //通过list拿到平台和应用的id
                String platformId = refPlatformApp.getPlatformId();
                String appId = refPlatformApp.getAppId();
                //通过id拿到平台表和应用表的数据
                app = appMapper.selectByPrimaryKey(appId);
                Platform platform = platformMapper.selectByPrimaryKey(platformId);
                int initStatus = platform.getInitStatus();
                if (initStatus == 1) {
                    String urlApp = platform.getUrlApp();
                    String userId = app.getUserId();
                    String companName = null;
                    String developer = null;
                    OpenUserExample openUserExample = new OpenUserExample();
                    openUserExample.createCriteria().andIdEqualTo(userId);
                    List<OpenUser> openUserList = openUsermapper.selectByExample(openUserExample);
                    OpenUser openUser = openUserList.get(0);
                    Integer userType = openUser.getUserType();
                    if (userType == LoginUserType.COMPANY.getStatenum()) {
                        Company company = companyMapper.selectByPrimaryKey(openUser.getCompanyId());
                        companName = company.getBusinessName();
                        developer = company.getDeveloperName();
                    } else {
                        Personal personal = personalMapper.selectByPrimaryKey(openUser.getPersonalId());
                        companName = personal.getCompanyName();
                        developer = personal.getName();
                    }
                    //加密串的处理
                    String clientId = platform.getIdentity();
                    String random = RandomStringUtils.random(6, true, true);
                    long currentTime = new Date().getTime();
                    //创建拼接对象
                    StringBuilder paramString = new StringBuilder();
                    paramString.append(clientId).append(currentTime).append(random).append(platform.getPassword());
                    //加密处理
                    String security = MD5Utils.md5(paramString.toString());
                    //封装请求参数
                    Map<String, Object> map = new HashMap<>();
                    JSONObject appJson = (JSONObject) JSON.toJSON(app);
                    map.put("app", appJson);
                    map.put("developer", developer);
                    map.put("companName", companName);
                    map.put("clientId", clientId);
                    map.put("random", random);
                    map.put("time", currentTime);
                    map.put("security", security);
                    String _reback = HttpClientUtil.postContent(urlApp, security, map);
                    JSONObject json = JSON.parseObject(_reback);
                    Integer code = (Integer) json.get("code");
                    //推送返回数据的处理
                    if (code == 0) {
                        refPlatformApp.setOptStatus(1);
                        refPlatformApp.setAppStatus(1);
                        refPlatformApp.setUpdateTime(new Date().getTime());
                        int update = refPlatformAppMapper.updateByPrimaryKeySelective(refPlatformApp);

                    } else {
                        refPlatformApp.setOptStatus(0);
                        refPlatformApp.setAppStatus(1);
                        refPlatformApp.setUpdateTime(new Date().getTime());
                        int update = refPlatformAppMapper.updateByPrimaryKeySelective(refPlatformApp);
                    }
                } else {
                    logger.error("平台信息未初始化，请初始化平台信息");
                }
            }
        }
    }
}
