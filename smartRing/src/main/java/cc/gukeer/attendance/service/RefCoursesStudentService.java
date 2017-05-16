package cc.gukeer.attendance.service;

import cc.gukeer.attendance.persistence.entity.RefCoursesStudent;

/**
 * Created by pc-daisike on 2017/5/15.
 */
public interface RefCoursesStudentService {
    //插入数据
    public void save(RefCoursesStudent refCoursesStudent);
    //更改学生数据
    public void update(RefCoursesStudent refCoursesStudent);
}
