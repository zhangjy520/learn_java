package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.A_CourseClassHour;
import cn.gukeer.platform.persistence.entity.GradeClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/10.
 */
public interface A_GradeClassMapper {
    List<GradeClass> findGradeClassBySectionIdAndNj(@Param("list") List<A_CourseClassHour> list);
}
