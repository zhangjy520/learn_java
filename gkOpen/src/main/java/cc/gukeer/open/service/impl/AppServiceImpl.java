package cc.gukeer.open.service.impl;

import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.modelView.AppAllInfoView;
import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.persistence.dao.*;
import cc.gukeer.open.persistence.dao.AppMapper;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int save(App app) {
        int succ = appMapper.insert(app);
        if (succ > 0) {
            return 1;
        }
        return 0;
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
        }else{
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

    public int updateAppById(App app) {
        //注意调用此方法时如果有字段为空就会更新失败  此时的做法便是将空字段设置为null 再调用此方法
        int updateSucc = appMapper.updateByPrimaryKeySelective(app);
        System.out.println(updateSucc);
        if (updateSucc > 0) {
            return 1;
        } else {
            return 0;
        }
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

}
