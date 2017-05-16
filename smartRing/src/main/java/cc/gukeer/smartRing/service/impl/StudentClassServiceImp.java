package cc.gukeer.smartRing.service.impl;

import cc.gukeer.smartRing.persistence.dao.A_RefClassStudentMapper;
import cc.gukeer.smartRing.persistence.dao.RefClassStudentMapper;
import cc.gukeer.smartRing.persistence.entity.RefClassStudent;
import cc.gukeer.smartRing.persistence.entity.RefClassStudentExample;
import cc.gukeer.smartRing.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/16.
 */
@Service
public class StudentClassServiceImp implements StudentClassService {
    @Autowired
    RefClassStudentMapper refClassStudentMapper;
    @Autowired
    A_RefClassStudentMapper a_refClassStudentMapper;
    @Override
    public List<Map> getAllStudents(String schoolId,String classId){
        return  a_refClassStudentMapper.getAllstudent(schoolId,classId);
    }
    @Override
    public int deleteStudent(String classId){
        RefClassStudentExample refClassStudentExample = new RefClassStudentExample();
        refClassStudentExample.createCriteria().andSportClassIdEqualTo(classId);
        return refClassStudentMapper.deleteByExample(refClassStudentExample);
    }
    @Override
    public int insertBatch(List<RefClassStudent> refClassStudentList){
        return a_refClassStudentMapper.addStudentBatch(refClassStudentList);
    }

}
