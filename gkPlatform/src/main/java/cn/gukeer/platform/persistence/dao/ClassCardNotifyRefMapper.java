package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.ClassCardNotifyRef;
import cn.gukeer.platform.persistence.entity.ClassCardNotifyRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardNotifyRefMapper {
    int deleteByExample(ClassCardNotifyRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardNotifyRef record);

    int insertSelective(ClassCardNotifyRef record);

    List<ClassCardNotifyRef> selectByExample(ClassCardNotifyRefExample example);

    ClassCardNotifyRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardNotifyRef record, @Param("example") ClassCardNotifyRefExample example);

    int updateByExample(@Param("record") ClassCardNotifyRef record, @Param("example") ClassCardNotifyRefExample example);

    int updateByPrimaryKeySelective(ClassCardNotifyRef record);

    int updateByPrimaryKey(ClassCardNotifyRef record);
}