package cc.gukeer.datahub.service.Impl;


import cc.gukeer.datahub.modeView.SyncView;
import cc.gukeer.datahub.persistence.dao.AppMapper;
import cc.gukeer.datahub.persistence.dao.PlatformMapper;
import cc.gukeer.datahub.persistence.dao.RefPlatformAppMapper;
import cc.gukeer.datahub.persistence.entity.App;
import cc.gukeer.datahub.persistence.entity.Platform;
import cc.gukeer.datahub.persistence.entity.RefPlatformApp;
import cc.gukeer.datahub.persistence.entity.RefPlatformAppExample;
import cc.gukeer.datahub.service.SyncService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2017/2/24.
 */
@Service
public class SyncServiceImpl implements SyncService {
    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;
    @Autowired
    PlatformMapper platformMapper;
    @Autowired
    AppMapper appMapper;

    @Override
    public PageInfo<SyncView> getSyncView() {
        RefPlatformAppExample refPlatformAppExample = new RefPlatformAppExample();
        refPlatformAppExample.createCriteria().andOptStatusEqualTo(1);
        List<SyncView> syncViewList = new ArrayList<>();
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatformAppExample);
        for (RefPlatformApp refPlatformApp : refPlatformApps) {
            App app = appMapper.selectByPrimaryKey(refPlatformApp.getAppId());
            Platform platform = platformMapper.selectByPrimaryKey(refPlatformApp.getPlatformId());
            SyncView syncView = new SyncView();
            syncView.setAppName(app.getName());
            syncView.setPlatformName(platform.getName());
            syncView.setPassword(app.getAppSecret());
            syncView.setRefPlatformApp(refPlatformApp);
            syncViewList.add(syncView);
        }
        PageInfo<SyncView> syncViewPageInfo = new PageInfo<>(syncViewList);
        return syncViewPageInfo;
    }
}
