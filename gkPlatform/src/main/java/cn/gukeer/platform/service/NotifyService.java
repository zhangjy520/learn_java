package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Notify;
import cn.gukeer.platform.persistence.entity.NotifyColumn;
import cn.gukeer.platform.persistence.entity.NotifyRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 */
public interface NotifyService {

    PageInfo<Notify> findAllList(int pageNum, int pageSize);

    Notify findNotifyById(String id);

    int saveNotify(Notify notify);

    List<Map<Object, Object>> findNotifyView(Map<Object,Object> param);

    List<Notify> getNotifyByCriteria(Notify notify, String beginTime, String endTime);

    List<NotifyColumn> findAllColumn(String schoolId);

    NotifyColumn findNotifyColumnById(String id);

    int saveNotifyColumn(NotifyColumn notifyColumn);

    int deleteNotifyByColumnId(Notify notify, String columnId);

    int saveNotifyRecord(NotifyRecord notifyRecord);

    int addNotifyRecord(NotifyRecord notifyRecord);

    int deleteNotifyRecord(NotifyRecord record);

    List< Map<Object,Object> > getRecordList(String id);

    int addNotifyBackId(Notify notify);
    //未读通知公告数目
    int selectRemindNotifyCount(String refId);

}
