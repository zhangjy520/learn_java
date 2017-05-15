package cc.gukeer.datahub.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface A_PushObjMapper {

    List<Map<String, String>> getTableName();

    List<Map<String, String>> selectFiled(@Param("name") String name, @Param("schema") String schema);

}