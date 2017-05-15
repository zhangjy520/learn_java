package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateTitle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/17.
 */
public interface A_ChangeStateTitleMapper {
    int insertBatch(List<ChangeStateTitle> changeStateTitles);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);
//    int deleteBatch(List<Object> ObjectList);
    int deleteBatch(@Param("listId") List<ChangeStateTitle> id, @Param("listSyncId") List<ChangeStateTitle> syncId);
    List<ChangeStateTitle> checkBysyncId();
    List<ChangeStateTitle> selectSyncId();
    int checkById(String id, String source);
}
