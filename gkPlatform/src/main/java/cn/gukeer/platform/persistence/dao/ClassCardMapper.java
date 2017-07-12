package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.ClassCardView;
import cn.gukeer.platform.persistence.entity.ClassCard;
import cn.gukeer.platform.persistence.entity.ClassCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardMapper {
    int deleteByExample(ClassCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCard record);

    int insertSelective(ClassCard record);

    List<ClassCard> selectByExample(ClassCardExample example);

    ClassCard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCard record, @Param("example") ClassCardExample example);

    int updateByExample(@Param("record") ClassCard record, @Param("example") ClassCardExample example);

    int updateByPrimaryKeySelective(ClassCard record);

    int updateByPrimaryKey(ClassCard record);

  /*  List<ClassCardView> selectByClassIdAndNj(@Param("classId") String classId, @Param("schoolId") String schoolId, @Param("xd") String xd,
                                             @Param("nj") int nj, @Param("xq") int xq);*/

}