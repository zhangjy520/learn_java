package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseManage;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCourseManageMapper {
    int countByExample(ChangeStateCourseManageExample example);

    int deleteByExample(ChangeStateCourseManageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCourseManage record);

    int insertSelective(ChangeStateCourseManage record);

    List<ChangeStateCourseManage> selectByExample(ChangeStateCourseManageExample example);

    ChangeStateCourseManage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCourseManage record, @Param("example") ChangeStateCourseManageExample example);

    int updateByExample(@Param("record") ChangeStateCourseManage record, @Param("example") ChangeStateCourseManageExample example);

    int updateByPrimaryKeySelective(ChangeStateCourseManage record);

    int updateByPrimaryKey(ChangeStateCourseManage record);
}