package cc.gukeer.datahub.service.Impl;


import cc.gukeer.datahub.persistence.dao.AppMapper;
import cc.gukeer.datahub.persistence.dao.PlatformMapper;
import cc.gukeer.datahub.persistence.dao.RefPlatformAppMapper;
import cc.gukeer.datahub.persistence.entity.RefPlatformApp;
import cc.gukeer.datahub.persistence.entity.RefPlatformAppExample;
import cc.gukeer.datahub.service.RefPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

