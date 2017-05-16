package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface TeacherService {
    PageInfo<Teacher> findAllList(int pageNum, int pageSize);

    Teacher findTeacherById(String id);

    int updateTeacher(Teacher teacher);

    int insertTeacher(Teacher teacher);

    Teacher findTeacherByNo(String schoolId, String no);

    List<Teacher> checkTeacherByName(String teacherName);

    List<Teacher> checkTeacherByNo(String teacherNo);

    List<Teacher> getAllTeacher(String schoolId);
}
