package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.GradeClass;
import cn.gukeer.platform.persistence.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/4/28.
 */
public interface A_RefTeacherClassMapper {
    List<TeacherClass> findMasterByClassIdListAndType(@Param("list") List<String> list,@Param("cycleId") String cycleId);
}
