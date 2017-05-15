package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.platform.persistence.dao.A_AppExtensionMapper;
import cn.gukeer.platform.persistence.dao.AppMapper;
import cn.gukeer.platform.persistence.dao.MyAppMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.AppService;
import cn.gukeer.platform.service.SchoolAppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class AppServiceImpl extends BasicService implements AppService {

    @Autowired
    AppMapper appMapper;

    @Autowired
    A_AppExtensionMapper appExtensionMapper;

    @Autowired
    MyAppMapper myAppMapper;

    @Autowired
    SchoolAppService schoolAppService;

    @Override
    public List<App> findByName(String name) {
        return appExtensionMapper.findByName(name);
    }

    @Override
    public PageInfo<App> findAllList(int pageNum, int pageSize) {

        AppExample example = new AppExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<App> list = appMapper.selectByExample(example);
        PageInfo<App> pageInfo = new PageInfo<App>(list);

        return pageInfo;
    }

    @Override
    public List<App> findNotHaveList(String schoolId) {
        return appExtensionMapper.findNotHaveList(schoolId);
    }

    @Override
    public App findAppById(String id) {

        App app = appMapper.selectByPrimaryKey(id);
        return app;
    }

    @Override
    public int updateApp(App app) {
        AppExample example = new AppExample();
        example.createCriteria().andIdEqualTo(app.getId());
        int count = appMapper.updateByExampleSelective(app, example);
        return count;
    }

    @Override
    public int insertApp(App app) {
        int count = appMapper.insertSelective(app);
        return count;
    }

    @Override
    public int saveApp( App app) {
        int count = 0;
        App findApp = appMapper.selectByPrimaryKey( app.getId());
        if ( null == findApp){
            count = appMapper.insertSelective( app);
        } else {
            count = appMapper.updateByPrimaryKeySelective( app);
        }
        return count;
    }
       
    @Override
    public List<App> findAppBySchool(String schoolId) {
        return appExtensionMapper.findAppBySchool(schoolId);
    }

    @Override
    public List<App> selectAppByUserIdAndUserType(User user){ return appExtensionMapper.selectAppByUserIdAndUserType(user); }

    @Override
    public List<App> selectAppByIsDefault(){
        return appExtensionMapper.selectAppByIsDefault();
    };

    @Override
    public List<App> findOtherSchoolAppList(User user){
        return appExtensionMapper.findOtherSchoolAppList(user);
    }

    @Override
    public List<MyApp> selectAppByUser(User user) {
        MyAppExample example = new MyAppExample();
        example.createCriteria().andUserIdEqualTo(user.getId());
        List<MyApp> myAppList= myAppMapper.selectByExample(example);
        return myAppList;
    }

    @Override
    public List<App> selectAppIdInList(List<String> idList) {
        AppExample appExample=new AppExample();
        appExample.createCriteria().andIdIn(idList).andDelFlagEqualTo(0);
        List<App> myApps=appMapper.selectByExample(appExample);
        return myApps;
    }

    @Override
	public List<App> findAppByName(String name) {
		AppExample example = new AppExample();
        example.createCriteria().andDelFlagEqualTo(0).andNameEqualTo(name);
        List<App> appList = appMapper.selectByExample(example);
		return appList;
	}

	@Override
    public List<App> findAppByNameAndCategoryAndTargetUser(String name, String category, String targetUser, String area, String schoolId) {
        AppExample example = new AppExample();
        AppExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        if(StringUtil.isNotEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if (StringUtil.isNotEmpty(category) && Integer.parseInt(category) > 0) {
            criteria.andCategoryEqualTo(category);
        }
        if (StringUtil.isNotEmpty(targetUser ) && Integer.parseInt(targetUser) > 0) {
            criteria.andTargetUserLike("%"+targetUser+"%");
        }


        if (StringUtil.isNotEmpty(area ) && Integer.parseInt(area) > 0) {
            //获取本校的应用id
            List<String> idList = new ArrayList<String>();
            List<SchoolApp> schoolAppList = schoolAppService.findAllListBySchoolId( schoolId);
            List<App> appList = selectAppByIsDefault();
            if (schoolAppList.size()>0){
                for (SchoolApp schoolApp:schoolAppList) {
                    idList.add( schoolApp.getAppId());
                }
            }
            if (appList.size()>0) {
                for (App app:appList) {
                    idList.add( app.getId());
                }
            }
            //查询本校应用
            if (1 == Integer.parseInt(area)){
                criteria.andIdIn(idList);
            } else {
                criteria.andIdNotIn( idList);
            }

        }
        List<App> appList = appMapper.selectByExample(example);
        return appList;
    }

	@Override
    public List<App> findAppByCriteria() {
        AppExample appExample=new AppExample();
        appExample.createCriteria().andDelFlagEqualTo(0);
        List<App> appList=appMapper.selectByExample(appExample);
        return  appList;
    }

    @Override
    public List<App> findAppByIdDefaultByTargetUser(String targetUser,Integer userPermission) {
        AppExample example = new AppExample();
        AppExample.Criteria criteria= example.createCriteria();
        criteria.andDelFlagEqualTo(0).andAppPermissionEqualTo(userPermission);//1 区级应用，2 校级应用

        AppExample.Criteria criteria1 = example.createCriteria();
        criteria1.andAppPermissionEqualTo(3).andDelFlagEqualTo(0);

        example.or(criteria1);

        example.setOrderByClause("id");
        List<App> appList = appMapper.selectByExample(example);
        if(appList == null || appList.size() == 0)
            return null;
        List<App> finalList = new ArrayList<>();
        for(App app : appList){
            String tgUser = app.getTargetUser();
            if(tgUser == null || tgUser == "")      //当应用没有目标用户时，不获取
                continue;
            if(Arrays.asList(tgUser.split(",")).contains(targetUser))
                finalList.add(app);
        }
        return finalList;
    }

}
