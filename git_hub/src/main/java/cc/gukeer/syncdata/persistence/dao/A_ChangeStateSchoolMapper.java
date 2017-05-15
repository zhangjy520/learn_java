package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSchool;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateSchoolMapper {
    ChangeStateSchool selectBySyncId(String id);
    int updateBySyncId(ChangeStateSchool changeStateSchool);
    int insertBatch(List<ChangeStateSchool> changeStateSchools);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStateSchool> id, @Param("listSyncId") List<ChangeStateSchool> syncId);
    List<ChangeStateSchool> checkBysyncId();
    List<ChangeStateSchool> selectSyncId();
    int checkById(String id, String source);
}
