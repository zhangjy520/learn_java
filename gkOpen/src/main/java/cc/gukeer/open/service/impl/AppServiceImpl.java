package cc.gukeer.open.service.impl;

import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.modelView.AppAllInfoView;
import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.persistence.dao.*;
import cc.gukeer.open.persistence.dao.AppMapper;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lx on 2016/11/25.
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppMapper appMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    A_AppExtentionMapper a_appExtentionMapper;
    @Autowired
    PersonalMapper personalMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    AccessoriesMapper accessoriesMapper;

    public int save(App app, String arrsrc, String arrindex) {
        int succ = 0;
        app.setUpdateDate(System.currentTimeMillis());

        if (StringUtils.isEmpty(app.getId())) {
            app.setId(PrimaryKey.get());
            app.setCheckStatus(CheckStateType.AUDITING.getStatenum());//设置为提交状态
            app.setDelFlag(0);
            app.setCreateDate(System.currentTimeMillis());

            //图片排序
            app = image(app, arrsrc, arrindex);

            //产生client_id
            String client_id = RandomStringUtils.random(8, true, true);
            app.setClientSecret(RandomStringUtils.random(6, true, true));

            //clent_id唯一性判断
            AppExample appExample = new AppExample();
            appExample.createCriteria().andClientIdEqualTo(client_id);
            while (appMapper.countByExample(appExample) > 0) {
                client_id = RandomStringUtils.random(8, true, true);
            }
            app.setClientId(client_id);
            //保存应用
            succ = appMapper.insert(app);
        } else {
            //更新应用

            //图片排序
            app = image(app, arrsrc, arrindex);
            app.setCheckStatus(CheckStateType.UPDATE_AUDITING.getStatenum());//设置为提交状态
            succ = appMapper.updateByPrimaryKeySelective(app);
        }

        //保存后状态返回
        if (succ > 0) {
            return succ;
        }
        return 0;
    }

    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        Map newMap = new TreeMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    public PageInfo<App> findAllComming(int pageNum, int pageSize, int status) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andDelFlagEqualTo(0);
        appExample.setOrderByClause("id desc");
        List<App> appList = appMapper.selectByExample(appExample);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<App> pageInfo = new PageInfo<App>(appList);
        return pageInfo;
    }

    @Override
    public App getAppById(String appId) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        List<App> listapp = appMapper.selectByExample(appExample);
        if (listapp == null) {
            return null;
        }
        App app = listapp.get(0);
        return app;
    }

    public PageInfo findAppByDetail(int pageNum, int pageSize, int status, OpenUser openUser) {
        AppExample appExample = new AppExample();
        AppExample.Criteria criteria = appExample.createCriteria();
        if (status != 0) {
            criteria.andCheckStatusEqualTo(status);
        } else {
            criteria.andDelFlagEqualTo(0);
        }
        criteria.andUserIdEqualTo(openUser.getId());
        appExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNum, pageSize);
        List<App> appList = appMapper.selectByExample(appExample);
        PageInfo<App> pageInfo = new PageInfo<App>(appList);
        return pageInfo;
    }

    public PageInfo<AppBaseInfoView> getAppBaseInfoByStatus(Integer status, int pageNum, int pageSize, Integer del) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppBaseInfoView> appBaseInfoViews = a_appExtentionMapper.findAppBaseInfo(status, del);
        PageInfo<AppBaseInfoView> pageInfo = new PageInfo<>(appBaseInfoViews);
        return pageInfo;
    }

    @Override
    public AppAllInfoView getAppAllInfo(String appId) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        List<App> apps = appMapper.selectByExample(appExample);
        if (apps.size() != 0) {
            AppAllInfoView appAllInfoView = new AppAllInfoView();
            String userId = apps.get(0).getUserId();
            OpenUserExample openUserExample = new OpenUserExample();
            openUserExample.createCriteria().andIdEqualTo(userId);
            List<OpenUser> openUsers = openUserMapper.selectByExample(openUserExample);
            Integer userType = openUsers.get(0).getUserType();
            String personalId = openUsers.get(0).getPersonalId();
            String companyId = openUsers.get(0).getCompanyId();
            if (userType == LoginUserType.PERSONAL.getStatenum()) {
                Personal personal = personalMapper.selectByPrimaryKey(personalId);
                appAllInfoView.setPersonal(personal);
                appAllInfoView.setApp(apps.get(0));
                appAllInfoView.setCompany(null);
                return appAllInfoView;
            }
            if (userType == LoginUserType.COMPANY.getStatenum()) {
                Company company = companyMapper.selectByPrimaryKey(companyId);
                appAllInfoView.setCompany(company);
                appAllInfoView.setApp(apps.get(0));
                appAllInfoView.setPersonal(null);
                return appAllInfoView;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int deleteById(String appId) {
        App app = new App();
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        app.setCheckStatus(4);
        app.setDelFlag(1);
        return appMapper.updateByExampleSelective(app, appExample);
    }

    @Override
    public int updateStatus(String appId, int status) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        App app = new App();
        app.setCheckStatus(status);
        return appMapper.updateByExampleSelective(app, appExample);
    }

    public PageInfo<App> finaAppByOpenUser(int pageNum, int pageSize, OpenUser openUser) {
        String openUserId = openUser.getId();
        AppExample appExample = new AppExample();
        appExample.createCriteria().andUserIdEqualTo(openUserId).andDelFlagEqualTo(0);
        appExample.setOrderByClause("create_date desc");
        PageHelper.startPage(pageNum, pageSize);
        List<App> appList = appMapper.selectByExample(appExample);
        PageInfo<App> pageInfo = new PageInfo<App>(appList);
        return pageInfo;
    }

    public PageInfo<AppBaseInfoView> getAppAllInfoByPushStatus(int pageNum, int size, int appPageNum, int pageSize) {
        PageHelper.startPage(appPageNum, pageSize);
        List<AppBaseInfoView> appBaseInfoViews = a_appExtentionMapper.findAppByPushStatus(2);
        PageInfo<AppBaseInfoView> pageInfo = new PageInfo<>(appBaseInfoViews);
        return pageInfo;
    }

    public App getAppAllInfoByPrimarykey(String id) {
        App app = appMapper.selectByPrimaryKey(id);
        if (null != app) {
            return app;
        }
        return null;
    }


    public App findAppByPrimarykeyAndCheckestatus(String id) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(id).andCheckStatusEqualTo(2);
        List<App> app = appMapper.selectByExample(appExample);
        if (null != app) {
            return app.get(0);
        }
        return null;
    }

    public int updatePushStatus(App app) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(app.getId()).andCheckStatusEqualTo(2);

        return 0;
    }

    @Override
    public int selectCount(String client_id) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andClientIdEqualTo(client_id);
        int clientIdCount = appMapper.countByExample(appExample);
        return clientIdCount;
    }

    @Override
    public int disable(String appId) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        App app = new App();
        app.setDelFlag(1);
        appMapper.updateByExampleSelective(app, appExample);
        return 0;
    }

    @Override
    public int enable(String appId) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdEqualTo(appId);
        App app = new App();
        app.setDelFlag(0);
        appMapper.updateByExampleSelective(app, appExample);
        return 0;
    }

    @Override
    public PageInfo<AppBaseInfoView> findAppBaseInfoContainDel(int appPageNum, int pageSize) {
        PageHelper.startPage(appPageNum, pageSize);
        List<AppBaseInfoView> appBaseInfoViews = a_appExtentionMapper.findAppBaseInfoContainDel();
        PageInfo<AppBaseInfoView> pageInfo = new PageInfo<>(appBaseInfoViews);
        return pageInfo;
    }

    @Override
    public void updateAppById(App app) {
        appMapper.updateByPrimaryKeySelective(app);
    }

    public App image(App app, String arrsrc, String arrindex) {
        //分成数组形式
        String[] src = null;
        String[] index = null;
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(arrsrc) &&
                org.apache.commons.lang3.StringUtils.isNotEmpty(arrindex)) {
            src = arrsrc.split(",");
            index = arrindex.split(",");

            Integer[] intIndex = new Integer[index.length];
            for (int i = 0; i < index.length; i++) {
                System.out.println(Integer.parseInt(index[i]));
                intIndex[i] = Integer.parseInt(index[i]);
            }

            //将图片对应的路径和调整后的index放入map
            Map<String, Integer> map = new TreeMap<>();
            for (int j = 0; j < src.length; j++) {
                map.put(src[j], intIndex[j]);
            }
            sortMap(map);//根据map的key排序
            app.setAppScreenshot(map.toString());
        }
        return app;
    }

}
