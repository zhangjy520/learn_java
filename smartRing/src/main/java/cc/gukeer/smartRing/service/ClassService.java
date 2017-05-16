package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.ClassSection;
import cc.gukeer.smartRing.persistence.entity.GradeClass;

import java.util.List;

/**
 * Created by connli on 2017/1/7.
 */
public interface ClassService {
    ClassSection findClassSectionByName(String schoolId, String xdName);
    GradeClass findClassByName(String schoolId, String xd, int nj, String name);
    GradeClass findClassById(String id);
    List<GradeClass> findClassList(String schoolId);
}
