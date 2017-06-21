package cn.gukeer.platform.persistence.dao;

import java.util.List;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.AppExample;
import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.User;

/**
 * Created by chen on 2016/9/12.
 */
public interface A_AppExtensionMapper {
    List<App> selectAppByUserIdAndUserType(User record);

    List<App> selectAppByIsDefault();

    List<App> findOtherSchoolAppList(User record);

    int deleteMyApp(MyApp myApp);

    List<App> findAppBySchool(String schoolId);

    List<App> findNotHaveList(String schoolId);
    
    List<App> findByName(String name);
}
