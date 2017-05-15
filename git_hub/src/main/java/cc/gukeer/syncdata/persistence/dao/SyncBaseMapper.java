package cc.gukeer.syncdata.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2017/2/7.
 */
public interface SyncBaseMapper {
    List<String> getTableNames(@Param("schema") String schema, @Param("tableNamePrefix") String tableNamePrefix);
    List<Map<String,Object>> getDatas(String tableName, @Param("ids") List<String> ids, String source);
    List<Map<String,Object>> getInitDatas(String tableName, String source);
    int identityDeletion(String tableName, String mark,
                         @Param("ids") List<String> ids, String notLikeMark);
    List<Map<String,Object>> getRefData(String tableName,
                                         String source,
                                         String sync_del_flag);
    List<String> getColumns(String detailObjId);
}
