package cc.gukeer.smartBoard.service;

import cc.gukeer.smartBoard.persistence.entity.Teacher;
import com.github.pagehelper.PageInfo;

/**
 * Created by conn on 2016/8/8.
 */
public interface TeacherService {
    PageInfo<Teacher> findAllList(int pageNum, int pageSize);

    Teacher findTeacherById(int id);

    int updateTeacher(Teacher teacher);

    int insertTeacher(Teacher teacher);
}
