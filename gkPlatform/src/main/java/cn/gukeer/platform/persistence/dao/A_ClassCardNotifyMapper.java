package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.ClassCardNotifyView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alpha on 17-7-6.
 */
public interface A_ClassCardNotifyMapper {
    //通知公告首页
    List<ClassCardNotifyView> findAllNotifyView(@Param("title")String title,@Param("type")int type);
}
