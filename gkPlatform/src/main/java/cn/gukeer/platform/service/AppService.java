package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface AppService {

    List<App> findByName(String name);

    PageInfo<App> findAllList(int pageNum, int pageSize);

    List<App> findNotHaveList(String schoolId);

    App findAppById(String id);

    int updateApp(App app);

    int insertApp(App app);

    int saveApp( App app);

    List<App> findAppByNameAndCategoryAndTargetUser(String name, String category, String targetUser, String area, String schoolId);

    List<App> findAppBySchool(String schoolId);

    List<App> selectAppByUserIdAndUserType(User user);

    List<App> selectAppByIsDefault();

    List<App> findOtherSchoolAppList(User user);

    List<MyApp> selectAppByUser(User user);

    List<App> selectAppIdInList(List<String> idList);

    List<App> findAppByCriteria();

    List<App> findAppByIdDefaultByTargetUser(String targetUser,Integer userPermission);

}
