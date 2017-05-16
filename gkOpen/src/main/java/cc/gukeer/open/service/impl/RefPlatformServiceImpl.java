package cc.gukeer.open.service.impl;

import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.persistence.dao.AppMapper;
import cc.gukeer.open.persistence.dao.PlatformMapper;
import cc.gukeer.open.persistence.dao.RefPlatformAppMapper;
import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import cc.gukeer.open.persistence.entity.RefPlatformAppExample;
import cc.gukeer.open.service.RefPlatformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LL on 2017/2/23.
 */
@Service
public class RefPlatformServiceImpl implements RefPlatformService {
    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;

    @Autowired
    AppMapper appMapper;

    @Autowired
    PlatformMapper platformMapper;


    public void setName(String id, String queuesName) {
        RefPlatformApp refPlatformApp = new RefPlatformApp();
        refPlatformApp.setQueues(queuesName);
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andIdEqualTo(id);
        refPlatformAppMapper.updateByExampleSelective(refPlatformApp, refPlatformAppExample);
    }

    @Override
    public void updateSyncStatus(String id, int status) {
        RefPlatformApp refPlatformApp = new RefPlatformApp();
        refPlatformApp.setSyncStatus(status);
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andIdEqualTo(id);
        refPlatformAppMapper.updateByExampleSelective(refPlatformApp, refPlatformAppExample);
    }

    @Override
    public void updateInitData(String id, int status) {
        RefPlatformApp refPlatformApp = new RefPlatformApp();
        refPlatformApp.setDataStatus(status);
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andIdEqualTo(id);
        refPlatformAppMapper.updateByExampleSelective(refPlatformApp, refPlatformAppExample);
    }


    @Override
    public int insertRefPlatformApp(RefPlatformApp refPlatformApp) {
        int succ = refPlatformAppMapper.insert(refPlatformApp);
        if (succ > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public RefPlatformApp selectById(String appId, String platformId) {
        RefPlatformAppExample refPlatformAppExample  = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andPlatformIdEqualTo(platformId).andAppIdEqualTo(appId);
        List<RefPlatformApp> list = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateRefPlatformByPrimarykey(RefPlatformApp refPlatformApp) {
       int update =  refPlatformAppMapper.updateByPrimaryKeySelective(refPlatformApp);
        if (update>0){
            return 1;
        }
        return 0;
    }

    public PageInfo<RefPlatformApp> findRefPlatformAppByOptAndAppStatus(int appPageNum, int pageSize) {
        RefPlatformAppExample refPlatformAppExample  = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andOptStatusEqualTo(1).andAppStatusEqualTo(1);
        PageHelper.startPage(appPageNum,pageSize);
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        if (refPlatformApps != null && refPlatformApps.size()>0){
            PageInfo<RefPlatformApp> pageInfo = new PageInfo<>(refPlatformApps);
            return pageInfo;
        }
        return null;
    }

    @Override
    public List<RefPlatformApp> findByAppIdAndOpt(String appId) {
        RefPlatformAppExample refPlatformAppExample  = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andOptStatusEqualTo(1).andAppIdEqualTo(appId);
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        if (refPlatformApps.size()>0){
            return refPlatformApps;
        }
        return null;
    }
}

