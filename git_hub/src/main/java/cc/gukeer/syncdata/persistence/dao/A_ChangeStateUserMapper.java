package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateUserMapper {
    ChangeStateUser selectBySyncId(String id);
    int updateBySyncId(ChangeStateUser changeStateUser);
    int insertBatch(List<ChangeStateUser> changeStateUsers);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);
int deleteBatch(@Param("listId") List<ChangeStateUser> id, @Param("listSyncId") List<ChangeStateUser> syncId);
    List<ChangeStateUser> checkBysyncId();
    List<ChangeStateUser> selectSyncId();
    int checkById(String id, String source);
}
