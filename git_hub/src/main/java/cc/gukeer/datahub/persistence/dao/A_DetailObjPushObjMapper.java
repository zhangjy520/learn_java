package cc.gukeer.datahub.persistence.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 马立立 on 2017/5/9.
 */
public interface A_DetailObjPushObjMapper {
    List<Map<String,String>> selectResult();
    List<Map<String,String>> selectQueue();
    List<Map> selectPlatApp();
 }
