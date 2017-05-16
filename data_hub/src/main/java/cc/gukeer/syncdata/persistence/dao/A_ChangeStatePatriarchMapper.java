package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStatePatriarch;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStatePatriarchMapper {
    ChangeStatePatriarch selectBySyncId(String SyncId);
    int insertBatch(List<ChangeStatePatriarch> changeStatePatriarches);
    int updateBySyncId(ChangeStatePatriarch changeStatePatriarch);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
    //int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStatePatriarch> id, @Param("listSyncId") List<ChangeStatePatriarch> syncId);
    List<ChangeStatePatriarch> checkBysyncId();
    List<ChangeStatePatriarch> selectSyncId();
    int checkById(String id, String source);


}
