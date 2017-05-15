package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.dao.CourseMapper;
import cn.gukeer.platform.persistence.entity.Course;
import cn.gukeer.platform.persistence.entity.CourseExample;
import cn.gukeer.platform.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/4/7.
 */
@Service
public class CourseServiceImpl extends BasicService implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public Course findCourseByPrimaryKey(String courseId) {
        if (courseId != "" && courseId != null){
          Course course = courseMapper.selectByPrimaryKey(courseId);
            return course;
        }
        return null;
    }

}
