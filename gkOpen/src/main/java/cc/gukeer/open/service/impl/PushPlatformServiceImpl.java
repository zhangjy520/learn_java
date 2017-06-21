package cc.gukeer.open.service.impl;

import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.persistence.dao.PlatformMapper;
import cc.gukeer.open.persistence.dao.RefPlatformAppMapper;
import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.PlatformExample;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import cc.gukeer.open.persistence.entity.RefPlatformAppExample;
import cc.gukeer.open.service.PushPlatformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LL on 2017/2/20.
 */
@Service
public class PushPlatformServiceImpl implements PushPlatformService {
    @Autowired
    PlatformMapper platformMapper;

    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;

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

    public int save(Platform platform) {

        int succ = 0;
        if (StringUtils.isNotBlank(platform.getId())) {

            succ = platformMapper.updateByPrimaryKeySelective(platform);
            //更新
        } else {
            platform.setDelFlag(0);
            platform.setInitStatus(0);
            //创建保存
            //传输密钥
            String password = RandomStringUtils.random(6, true, true);
            platform.setPassword(password);

            //唯一标识的唯一性
            String random = RandomStringUtils.random(6, true, true);
            PlatformExample platformExample = new PlatformExample();
            platformExample.createCriteria().andIdentityEqualTo(random);
            while (platformMapper.countByExample(platformExample) > 0) {
                String random_only = RandomStringUtils.random(6, true, true);
            }
            platform.setIdentity(random);

            platform.setCreateTime(new Date().getTime());
            platform.setId(PrimaryKey.get());
            succ = platformMapper.insert(platform);
        }

        if (succ > 0) {
            return succ;
        }
        return 0;
    }



    @Override
    public Platform findPlatformById(String id) {
        Platform platform = platformMapper.selectByPrimaryKey(id);
        if (platform != null) {
            return platform;
        }
        return null;
    }


    @Override
    public List<Platform> findPlatformBydelflag() {
        PlatformExample platformExample = new PlatformExample();
        platformExample.createCriteria().andDelFlagEqualTo(0);
        List<Platform> list = platformMapper.selectByExample(platformExample);

        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<RefPlatformApp> findRefPlatformAppByAppId(String appId) {
        RefPlatformAppExample example = new RefPlatformAppExample();
        example.createCriteria().andAppIdEqualTo(appId);
        return refPlatformAppMapper.selectByExample(example);
    }
}
