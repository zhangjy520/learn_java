/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2017年12月12日 16:51:10
 * <p>2016-3-21
 * Company: gukeer
 * <p>
 * Author: lxsoft
 * <p>
 * Version: 1.0
 * <p>
 */
package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.Course;
import cc.gukeer.smartBoard.persistence.entity.CourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}