package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.ClassCardView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alpha on 17-7-3.
 */
public interface A_ClassCardMapper {
    //班牌首页根据树搜索班牌
    List<ClassCardView> selectByClassIdAndNj(@Param("classId") String classId, @Param("schoolId") String schoolId, @Param("xd") String xd,
                                             @Param("nj") int nj, @Param("xq") int xq);

    //通知公告，选择班牌设备
    List<ClassCardView> selectAllClassCardView();
}



