package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/17.
 */
public interface A_ChangeStateDepartmentMapper {
    int insertBatch(List<ChangeStateDepartment> changeStateDepartments);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
   // int deleteBatch(List<Object> ObjectList);
   int deleteBatch(@Param("listId") List<ChangeStateDepartment> id, @Param("listSyncId") List<ChangeStateDepartment> syncId);
    List<ChangeStateDepartment> checkBysyncId();
    List<ChangeStateDepartment> selectSyncId();
    int checkById(String id, String source);
}
