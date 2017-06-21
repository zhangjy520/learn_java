package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.A_CourseClassHour;
import cn.gukeer.platform.persistence.entity.GradeClass;
import cn.gukeer.platform.persistence.entity.extention.GradeClassExtention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/10.
 */
public interface A_GradeClassMapper {
    List<GradeClass> findGradeClassBySectionIdAndNj(@Param("list") List<A_CourseClassHour> list);

    List<GradeClassExtention> findAllGradeClassBySchoolId(@Param("schoolId") String schoolId);
}
