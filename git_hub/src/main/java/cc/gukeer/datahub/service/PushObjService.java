package cc.gukeer.datahub.service;

import cc.gukeer.datahub.modeView.QueueObjView;
import cc.gukeer.datahub.persistence.entity.PushObj;
import cc.gukeer.syncdata.persistence.entity.DetailObj;
import cc.gukeer.syncdata.persistence.entity.DetailObjColumn;

import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2017/4/17.
 */
public interface PushObjService {
    //获取所有push_obj
    List<PushObj> getAllPushObj();

    //删除对象绑定
    void deleteQueObj(String platAppId);

    //推送对象绑定
    int bindPushObj(String queueId, String[] ObjIds);

    List<QueueObjView> getQueueObj();

    //删除push_obj
    int deletePushObj(String id);

    //修改，新增push_obj
    int savePushObj(PushObj pushObj);

    //根据主键查询push_obj
    PushObj selectPushObjByKey(String id);

    List<Map<String, String>> getTableName();

    List<Map<String, String>> selectFiled(String name);

    void objectDelete(String id);

    String detailObjSave(DetailObj detailObj);

    void detailObjInsert(DetailObjColumn detailObjColumn);

    List<DetailObjColumn> getDetailObjColumn(String id);

    void deleteDetailObjColumn(String id);

    DetailObj selectDetailObjByName(String name);

    DetailObj getDetailObjById(String id);

    List<Map<String, String>> selectResult();

    List<Map<String, String>> selectQueue();

}
