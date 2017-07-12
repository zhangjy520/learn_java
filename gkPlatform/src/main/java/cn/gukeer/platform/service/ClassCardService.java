package cn.gukeer.platform.service;

import cn.gukeer.platform.modelView.ClassCardView;
import cn.gukeer.platform.persistence.entity.ClassCard;
import cn.gukeer.platform.persistence.entity.ClassCardMode;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 17-6-26.
 */
public interface ClassCardService {
    //----------------班牌------------------
    int insertClassCard(ClassCard classCard);

    int updateClassCard(ClassCard classCard);

    ClassCard selectClassCardById(String id);

    PageInfo<ClassCardView> selectClassCardByChoose(String sid, String cid, String xd, int nj, int xq, int pageNum, int pageSize);

    Map<String, Object> selectCascadeClassRoom(String condition, String schoolId);

    List<Map<String, String>> selectXdBySchool(String schoolId);

    Map<String, Object> selectCascadeClass(String condition, String schoolId);

    int deleteClassCard(List<String> ids);

    int selectClassCardByTeachClassRoomId(String teachClassRoomId);

    int selectClassCardByClassId(String ClassId);


    //---------------------班牌模式-------------------
    PageInfo<ClassCardMode> selectAllUnDelMode();

    ClassCardMode selectModeById(String id);

    int updateMode(ClassCardMode classCardMode);

    int insertMode(ClassCardMode classCardMode);

    //逻辑删除当前时间在班牌模式结束时间之后的记录
    List<ClassCardMode> delOutDateMode(List<ClassCardMode> classCardModes);

    //----------------------通知公告------------------
    //选择设备
    Map<String, Map<String, List<ClassCardView>>> selectEquipmentForNotify();
}
