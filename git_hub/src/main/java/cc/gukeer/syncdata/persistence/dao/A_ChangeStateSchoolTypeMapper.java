package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSchoolType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateSchoolTypeMapper {
    ChangeStateSchoolType selectBySyncId(String id);
    int updateBySyncId(ChangeStateSchoolType changeStateSchoolType);
    int insertBatch(List<ChangeStateSchoolType> changeStateSchoolTypes);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);

    int deleteBatch(@Param("listId") List<ChangeStateSchoolType> id, @Param("listSyncId") List<ChangeStateSchoolType> syncId);
    List<ChangeStateSchoolType> checkBysyncId();
    List<ChangeStateSchoolType> selectSyncId();
    int checkById(String id, String source);
}
