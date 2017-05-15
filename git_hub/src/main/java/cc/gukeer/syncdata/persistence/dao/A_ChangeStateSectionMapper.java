package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface  A_ChangeStateSectionMapper {
    ChangeStateSection selectBySyncId(String id);
    int updateBySyncId(ChangeStateSection changeStateSection);
    int insertBatch(List<ChangeStateSection> changeStateSections);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
    //int deleteBatch(List<Object> ObjectList);
    int checkById(String id, String source);



    //List<Map<String,Object>> selectSyncId();
    int deleteBatch(@Param("listId") List<ChangeStateSection> id, @Param("listSyncId") List<ChangeStateSection> syncId);
    List<ChangeStateSection> checkBysyncId();
    List<ChangeStateSection> selectSyncId();
}
