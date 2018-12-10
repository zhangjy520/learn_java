package cc.gukeer.smartBoard.persistence.dao;

import java.util.List;
import cc.gukeer.smartBoard.persistence.entity.CourseExample;
import cc.gukeer.smartBoard.persistence.entity.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {

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

	int abvs(CourseExample example);


}