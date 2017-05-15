package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateStudentMapper {
    ChangeStateStudent selectBySyncId(String id);
    int updateBySyncId(ChangeStateStudent changeStateStudent);
    int insertBatch(List<ChangeStateStudent> changeStateStudents);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStateStudent> id, @Param("listSyncId") List<ChangeStateStudent> syncId);
    List<ChangeStateStudent> checkBysyncId();
    List<ChangeStateStudent> selectSyncId();
    int checkById(String id, String source);

}
