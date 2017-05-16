package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.ClassSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by admin on 2017/2/22.
 */
public interface A_ClassExtensionMapper {
    List<ClassSection> getSessionByGradeClassAndSchoolId( @Param("schoolId") String schoolId);
}
