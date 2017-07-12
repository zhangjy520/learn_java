package cn.gukeer.platform.service.impl;

import cn.gukeer.platform.modelView.ClassCardView;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.ClassCardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 17-6-26.
 */
@Service
public class ClassCardServiceImpl implements ClassCardService {
    @Autowired
    ClassCardModeMapper classCardModeMapper;

    @Autowired
    ClassCardMapper classCardMapper;

    @Autowired
    A_ClassRoomMapper a_classRoomMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_ClassCardMapper a_classCardMapper;

    @Override
    public int insertClassCard(ClassCard classCard) {
        int count = classCardMapper.insert(classCard);
        return count;
    }

    @Override
    public int updateClassCard(ClassCard classCard) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andIdEqualTo(classCard.getId());
        int count = classCardMapper.updateByExampleSelective(classCard, example);
        return count;
    }

    @Override
    public ClassCard selectClassCardById(String id) {
        ClassCard classCard = classCardMapper.selectByPrimaryKey(id);
        return classCard;
    }

    @Override
    public PageInfo<ClassCardView> selectClassCardByChoose(String sid, String cid, String xd, int nj, int xq, int pageNum, int pageSize) {

        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardView> views = a_classCardMapper.selectByClassIdAndNj(cid, sid, xd, nj, xq);
        PageInfo<ClassCardView> pageInfo = new PageInfo<ClassCardView>(views);

        return pageInfo;
    }

    @Override
    public Map<String, Object> selectCascadeClassRoom(String condition, String schoolId) {
        JsonObject params = new JsonParser().parse(condition).getAsJsonObject();
        String flag = params.get("flag").getAsString();
        String xqId = params.get("xqId").getAsString();
        String teachBuilding = params.get("teachBuilding").getAsString();
        String floor = params.get("floor").getAsString();


        List<ClassRoom> classRooms = a_classRoomMapper.findCascadeClassRoom(flag, xqId, teachBuilding, floor, schoolId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("classRomms", classRooms);
        resultMap.put("flag", flag);

        return resultMap;
    }

    @Override
    public List<Map<String, String>> selectXdBySchool(String schoolId) {
        List<GradeClass> gradeClasses = gradeClassMapper.selectXdBySchoolId(schoolId);
        List<Map<String, String>> list = new ArrayList<>();
        for (GradeClass gc : gradeClasses) {
            Map<String, String> map = new HashMap<>();
            map.put("xd", gc.getName());
            map.put("xdId", gc.getXd());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> selectCascadeClass(String condition, String schoolId) {
        JsonObject params = new JsonParser().parse(condition).getAsJsonObject();
        String flag = params.get("flag").getAsString();
        String xdId = params.get("xdId").getAsString();
        String nj = params.get("nj").getAsString();
        List<GradeClass> gradeClasses = gradeClassMapper.selectClassCascade(flag, xdId, nj, schoolId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("gradeClasses", gradeClasses);
        resultMap.put("flag", flag);
        return resultMap;
    }

    @Override
    public int deleteClassCard(List<String> ids) {
        /*ClassCardExample example=new ClassCardExample();
        example.createCriteria().andIdIn(ids);
        int count =classCardMapper.deleteByExample(example);*/

        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andIdIn(ids);
        int count = 0;
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        for (ClassCard cc : classCards) {
            cc.setDelFlag(1);
            count += classCardMapper.updateByPrimaryKey(cc);
        }
        if (count != ids.size()) {
            count = -1;
        }
        return count;
    }

    @Override
    public int selectClassCardByTeachClassRoomId(String teachClassRoomId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andTeachClassRoomIdEqualTo(teachClassRoomId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        int count = 0;
        if (classCards != null && classCards.size() > 0) {
            count = classCards.size();
        }
        return count;
    }

    @Override
    public int selectClassCardByClassId(String classId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andClassIdEqualTo(classId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        int count = 0;
        if (classCards != null && classCards.size() > 0) {
            count = classCards.size();
        }
        return count;
    }

    @Override
    public PageInfo<ClassCardMode> selectAllUnDelMode() {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andDelFlagEqualTo(0);

        List<ClassCardMode> modes = classCardModeMapper.selectByExample(example);
        List<ClassCardMode> resultList = delOutDateMode(modes);

        PageInfo<ClassCardMode> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public ClassCardMode selectModeById(String id) {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andIdEqualTo(id);
        ClassCardMode classCardMode = classCardModeMapper.selectByPrimaryKey(id);
        return classCardMode;
    }

    @Override
    public int updateMode(ClassCardMode classCardMode) {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andIdEqualTo(classCardMode.getId());
        int count = classCardModeMapper.updateByExampleSelective(classCardMode, example);
        return count;
    }

    @Override
    public int insertMode(ClassCardMode classCardMode) {
        int count = classCardModeMapper.insert(classCardMode);
        return count;
    }

    @Override
    public List<ClassCardMode> delOutDateMode(List<ClassCardMode> classCardModes) {
        List<ClassCardMode> resultList = new ArrayList<>();
        if (classCardModes != null && classCardModes.size() > 0) {
            for (ClassCardMode ccm : classCardModes) {
                if (ccm.getTimeEnd() < System.currentTimeMillis()) {
                    ccm.setDelFlag(1);
                } else {
                    resultList.add(ccm);
                }
            }
        }
        return resultList;
    }

    @Override
    public Map<String, Map<String, List<ClassCardView>>> selectEquipmentForNotify() {
        List<ClassCardView> classCardViews = a_classCardMapper.selectAllClassCardView();
        Map<String, Map<String, List<ClassCardView>>> resultMap = new HashMap<>();
        for (ClassCardView ccv : classCardViews) {
            //学段
            if (resultMap.get(ccv.getXd()) == null) {
                Map<String, List<ClassCardView>> njMap=new HashMap<>();
                List<ClassCardView> list = new ArrayList<>();
                njMap.put(ccv.getNj(),list);
                resultMap.put(ccv.getXd(),njMap);
            }
            //年级
            Map<String, List<ClassCardView>> stringListMap = resultMap.get(ccv.getXd());
            if (stringListMap.get(ccv.getNj()) != null) {
                List<ClassCardView> views = stringListMap.get(ccv.getNj());
                views.add(ccv);
                stringListMap.put(ccv.getNj(),views);
            } else {
                List<ClassCardView> list = new ArrayList<>();
                list.add(ccv);
                stringListMap.put(ccv.getNj(), list);
            }
        }
        return resultMap;
    }
}
