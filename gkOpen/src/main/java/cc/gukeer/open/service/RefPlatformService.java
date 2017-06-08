package cc.gukeer.open.service;

import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LL on 2017/2/23.
 */
public interface RefPlatformService {

    void setName(String id,String queuesName);

    void updateSyncStatus(String id ,int status);

    void updateInitData(String id,int status);

    int insertRefPlatformApp(RefPlatformApp refPlatformApp);

    RefPlatformApp selectById(String appId, String platformId);

    int updateRefPlatformByPrimarykey(RefPlatformApp refPlatformApp);

    List<RefPlatformApp> findRefplatformByAppId(String appId);

    RefPlatformApp findRefPlatformByAppIdAndPlatformId(String appId, String platformId);
}
