package dao;

import entity.CourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import entity.Course;

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