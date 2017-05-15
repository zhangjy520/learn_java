package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateTeacherMapper {
    ChangeStateTeacher selectBySyncId(String id);
    int updateBySyncId(ChangeStateTeacher changeStateTeacher);
    int insertBatch(List<ChangeStateTeacher> changeStateTeachers);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStateTeacher> id, @Param("listSyncId") List<ChangeStateTeacher> syncId);
    List<ChangeStateTeacher> checkBysyncId();
    List<ChangeStateTeacher> selectSyncId();
    int checkById(String id, String source);
}
