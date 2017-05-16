package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.RefClassStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/9.
 */
public interface A_RefClassStudentMapper {
    int addStudentBatch(List<RefClassStudent> refClassStudentList);
    List<Map> getAllstudent(@Param("schoolId") String schoolId, @Param("sportClassId") String classId);
    //得到各个班级的人数
    List<Map> getClassCount();
}
