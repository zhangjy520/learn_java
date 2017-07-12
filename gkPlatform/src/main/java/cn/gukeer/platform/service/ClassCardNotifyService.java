package cn.gukeer.platform.service;

import cn.gukeer.platform.modelView.ClassCardNotifyView;
import cn.gukeer.platform.persistence.entity.ClassCardNotify;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
public interface ClassCardNotifyService {
    int insertClassCardNotify(ClassCardNotify classCardNotify);

    PageInfo<ClassCardNotifyView> findAllNotify(String title, int type, int pageNum, int pageSize);

    public List<ClassCardNotifyView> transforNotifyView(List<ClassCardNotifyView> classCardNotifyViews);

    ClassCardNotify  findById(String id);

    int deleteClassCardNotify(String id[]);


}
