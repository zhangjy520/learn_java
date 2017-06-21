package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.SchoolApp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface SchoolAppService {

    int insertSchoolApp(SchoolApp schoolApp);

    int deleteSchoolApp(SchoolApp schoolApp);

    List<SchoolApp> findAllListBySchoolId(String schoolId);

}
