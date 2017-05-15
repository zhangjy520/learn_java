package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.ClassSection;
import cc.gukeer.smartBoard.persistence.entity.ClassSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassSectionMapper {
    int deleteByExample(ClassSectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassSection record);

    int insertSelective(ClassSection record);

    List<ClassSection> selectByExample(ClassSectionExample example);

    ClassSection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassSection record, @Param("example") ClassSectionExample example);

    int updateByExample(@Param("record") ClassSection record, @Param("example") ClassSectionExample example);

    int updateByPrimaryKeySelective(ClassSection record);

    int updateByPrimaryKey(ClassSection record);
}