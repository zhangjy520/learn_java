package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.ClassCardNotifyRef;

import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
public interface ClassCardNotifyRefService {
    boolean insertClassCardNotifyRef(String checkedIds,String notifyId,String schoolId,String createBy);

    int deleteClassCardNotifyRef(String unCheckedIds,String notifyId);

    List<ClassCardNotifyRef> findAllByNotifyId(String notifyId );

    int deleteRefByNotifyId(String notifyId[]);

}
