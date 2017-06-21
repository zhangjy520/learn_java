package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LL on 2017/2/20.
 */
public interface PushPlatformService {
    PageInfo<Platform> findAllPushPlatform(int pageNum,int pageSize);

    List<Platform> findPlatformByInitStatus();

    int save(Platform platform);

    Platform findPlatformById(String id);

    List<Platform> findPlatformBydelflag();

    List<RefPlatformApp> findRefPlatformAppByAppId(String appId);
}
