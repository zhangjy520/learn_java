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
import cc.gukeer.syncdata.persistence.dao.SyncBaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    SyncBaseMapper syncBaseMapper;

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

    @Override
    public PageInfo<Map<String, Object>> getInitDatas(String tableName, String source, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> _datas = syncBaseMapper.getInitDatas(tableName, source);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(_datas);
        if (pageNum > pageInfo.getPages())
            return new PageInfo<Map<String, Object>>(new ArrayList<Map<String, Object>>());//在mybatis-config配置pageHelper reaonable
        return pageInfo;
    }
}
