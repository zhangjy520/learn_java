package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateGradeMapper {
    int insertBatch(List<ChangeStateGrade> changeStateGrades);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
    int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStateGrade> id, @Param("listSyncId") List<ChangeStateGrade> syncId);
    List<ChangeStateGrade> checkBysyncId();
    List<ChangeStateGrade> selectSyncId();
    int checkById(String id, String source);
}
