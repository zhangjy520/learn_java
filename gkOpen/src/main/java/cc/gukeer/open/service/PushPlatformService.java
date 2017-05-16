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

    int insertPlatform(Platform platform);

    int delPlatformById(String id);

    Platform findPlatformById(String id);

    int selectCount(String random);

    int updatePlatform(Platform platform);

    List<Platform> findPlatformBydelflag();

}
