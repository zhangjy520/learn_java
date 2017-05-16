package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.RefClassStudent;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/16.
 */
public interface StudentClassService {
    List<Map>  getAllStudents(String schoolId,String classId);
    int deleteStudent(String classId);
    int insertBatch(List<RefClassStudent> refClassStudentList);

}
