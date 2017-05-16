package cc.gukeer.attendance.service.impl;

import cc.gukeer.attendance.persistence.dao.RefCoursesStudentMapper;
import cc.gukeer.attendance.persistence.entity.RefCoursesStudent;
import cc.gukeer.attendance.persistence.entity.RefCoursesStudentExample;
import cc.gukeer.attendance.service.RefCoursesStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc-daisike on 2017/5/15.
 */
@Service
public class RefCoursesStudentServiceImpl implements RefCoursesStudentService{
    @Autowired
    RefCoursesStudentMapper refCoursesStudentMapper;
    @Override
    public void save(RefCoursesStudent refCoursesStudent) {
        refCoursesStudentMapper.insert(refCoursesStudent);
    }

    @Override
    public void update(RefCoursesStudent refCoursesStudent) {
        RefCoursesStudentExample refCoursesStudentExample = new RefCoursesStudentExample();
        refCoursesStudentExample.createCriteria().andCourseIdsEqualTo(refCoursesStudent.getCourseIds()).andStudentIdEqualTo(refCoursesStudent.getStudentId());
        refCoursesStudentMapper.updateByExample(refCoursesStudent,refCoursesStudentExample);
    }
}
