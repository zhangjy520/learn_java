package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Course;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by LL on 2017/4/7.
 */
public interface CourseService {
    Course findCourseByPrimaryKey(String courseId);
}
