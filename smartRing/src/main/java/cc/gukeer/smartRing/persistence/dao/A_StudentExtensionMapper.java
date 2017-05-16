package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 16-10-11.
 */
public interface A_StudentExtensionMapper {

    Student selectStudentByMac(String mac);

    List<Map<String, String>> selectNoBoundRingStudentBySchoolIdAndClassId(@Param("schoolId") String schoolId,
                                                                           @Param("classId") String classId);
    
}
