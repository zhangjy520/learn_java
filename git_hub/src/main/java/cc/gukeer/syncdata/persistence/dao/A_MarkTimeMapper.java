package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.MarkTime;

/**
 * Created by pc-daisike on 2017/2/16.
 */
public interface A_MarkTimeMapper {
    Long selectUpdateDate(String tableName);
    MarkTime selectMarkTime(String tableName);
    int updateById(MarkTime markTime);
}
