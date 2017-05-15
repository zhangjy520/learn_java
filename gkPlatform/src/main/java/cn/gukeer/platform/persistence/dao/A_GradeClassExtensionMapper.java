package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.GradeClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/23.
 */
public interface A_GradeClassExtensionMapper { 
    int changeDelFlag(String id);
    int batchInsertGradeClass(List<GradeClass> list);
    List<Map> getSchoolClass(@Param("schoolId") String schoolId);

    List<GradeClass> getSchoolClassBySchoolId(String schoolId);
}
