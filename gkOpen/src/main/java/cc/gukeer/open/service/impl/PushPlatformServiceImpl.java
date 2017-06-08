package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.PlatformMapper;
import cc.gukeer.open.persistence.dao.RefPlatformAppMapper;
import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.PlatformExample;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import cc.gukeer.open.persistence.entity.RefPlatformAppExample;
import cc.gukeer.open.service.PushPlatformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LL on 2017/2/20.
 */
@Service
public class PushPlatformServiceImpl implements PushPlatformService {
    @Autowired
    PlatformMapper platformMapper;

    @Autowired
    RefPlatformAppMapper  refPlatformAppMapper;

    public PageInfo<Platform> findAllPushPlatform(int pageNum, int pageSize) {
        PlatformExample platformExample = new PlatformExample();
        platformExample.createCriteria().andDelFlagEqualTo(0);
        platformExample.setOrderByClause("create_time");
        PageHelper.startPage(pageNum, pageSize);
        List<Platform> list = platformMapper.selectByExample(platformExample);
        if (null != list) {
            PageInfo<Platform> pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
        return null;
    }

    @Override
    public List<Platform> findPlatformByInitStatus() {
        PlatformExample platformExample = new PlatformExample();
        platformExample.createCriteria().andInitStatusEqualTo(1);
        List<Platform> platFormList = platformMapper.selectByExample(platformExample);
        if (null != platFormList) {
            return platFormList;
        }
        return null;
    }

    public int insertPlatform(Platform platform) {
        int succ = platformMapper.insertSelective(platform);
        if (succ > 0) {
            return 1;
        }
        return 0;
    }
    public int delPlatformById(String id) {
        Platform platform = platformMapper.selectByPrimaryKey(id);
        if (platform != null) {
            platform.setDelFlag(1);
            int succ = platformMapper.updateByPrimaryKeySelective(platform);
            if (succ > 0) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Platform findPlatformById(String id) {
        Platform platform = platformMapper.selectByPrimaryKey(id);
        if (platform != null){
            return  platform;
        }
        return null;
    }

    public int selectCount(String random) {
        PlatformExample platformExample = new PlatformExample();
        platformExample.createCriteria().andIdentityEqualTo(random);
        int  count = platformMapper.countByExample(platformExample);
        return count;
    }

    @Override
    public int updatePlatform(Platform platform) {
       int succ =  platformMapper.updateByPrimaryKeySelective(platform);
        if (succ>0){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Platform> findPlatformBydelflag() {
        PlatformExample platformExample = new PlatformExample();
        platformExample.createCriteria().andDelFlagEqualTo(0);
        List<Platform> list = platformMapper.selectByExample(platformExample);

        if (list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<RefPlatformApp> findRefPlatformAppByAppId(String appId) {
        RefPlatformAppExample example= new RefPlatformAppExample();
        example.createCriteria().andAppIdEqualTo(appId);
        return refPlatformAppMapper.selectByExample(example);
    }
}
