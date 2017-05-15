package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.TeacherClass;
import cn.gukeer.platform.persistence.entity.TeachCycle;
import cn.gukeer.platform.persistence.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/4/5.
 */
public interface A_TeachCycleExtentionMapper {

    List<TeacherClass> findTeacherByCycleIdAndSchoolId(Map param);
}
