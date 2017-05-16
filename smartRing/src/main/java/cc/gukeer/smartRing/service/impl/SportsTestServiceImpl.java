package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.DateUtils;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.persistence.dao.*;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.service.SportsTestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjy on 2016/12/9.
 */
@Service
public class SportsTestServiceImpl extends BasicService implements SportsTestService {
    @Autowired
    SportClassMapper sportClassMapper;
    @Autowired
    SportItemMapper sportItemMapper;

    @Autowired
    ScoreMapRuleMapper scoreMapRuleMapper;

    @Autowired
    StuScoreRefMapper stuScoreRefMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;

    @Autowired
    A_SportTestMapper a_sportTestMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_SportClassMapper a_sportClassMapper;



    @Override
    public ScoreMapRule getRuleByPrimary(String ruleId) {
        return scoreMapRuleMapper.selectByPrimaryKey(ruleId);
    }

    @Override
    public Map getXdAndNj(String gradeName, User user) {
        ScoreMapRuleExample example = new ScoreMapRuleExample();
        example.createCriteria().andGradeNameEqualTo(gradeName).andSchoolIdEqualTo(user.getSchoolId()).andDelFlagEqualTo(0).andRuleTypeEqualTo(0);

        List<ScoreMapRule> res = scoreMapRuleMapper.selectByExample(example);
        Map resMap = new HashMap();
        if (res.size() > 0) {
            resMap.put("nj", res.get(0).getNj());
            resMap.put("xd", res.get(0).getXd());
        }
        return resMap;
    }

    @Transactional
    @Override
    public List<String> getClassByTeacher(String teacherId) {

        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId);
        List<TeacherClass> teacherClassKeyList = teacherClassMapper.selectByExample(example);
        List<String> intList = new ArrayList<String>();
        for (TeacherClass keys : teacherClassKeyList) {
            intList.add(keys.getClassId());
        }
        return intList;
    }

    @Override
    public Map getScoreByPrimary(String testSeq) {
        return a_sportTestMapper.getScoreByPrimary(testSeq);
    }

    @Override
    public PageInfo<Map> findScoreList(Map paramMap) {
        int pageNum = NumberConvertUtil.convertS2I(paramMap.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(paramMap.get("pageSize").toString());

        pageSize = (pageSize == 0 ? 10 : pageSize);

        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<Map> mapList = a_sportTestMapper.getScoreByCriteria(paramMap);
        PageInfo<Map> pageInfo = new PageInfo<Map>(mapList);
        return pageInfo;
    }


    @Override
    public PageInfo<SportItem> findSportItem(Map<String,Object> map){
        int pageNum = NumberConvertUtil.convertS2I(map.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(map.get("pageSize").toString());
        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        SportItemExample example = new SportItemExample();
        example.setOrderByClause("item_name");
        example.createCriteria().andDelFlagEqualTo(0).andItemTypeEqualTo(0);
        List<SportItem> sportItemList = sportItemMapper.selectByExample(example);
        for (SportItem sport : sportItemList) {
            System.out.println(sport);
        }
        PageInfo<SportItem> pageInfo = new PageInfo<SportItem>(sportItemList);
        return pageInfo;
    }
    @Override
    public PageInfo<ScoreMapRule> findScoreMapRuleByCriteria(Map<String, Object> paramMap) {
        String itemName = (String) paramMap.get("itemName");
        String gradeName = (String) paramMap.get("gradeName");
        String gender = (String) paramMap.get("gender");
        String schoolId = (String) paramMap.get("schoolId");
        int pageNum = NumberConvertUtil.convertS2I(paramMap.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(paramMap.get("pageSize").toString());

        pageSize = (pageSize == 0 ? 10 : pageSize);

        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        ScoreMapRuleExample example = new ScoreMapRuleExample();
        example.setOrderByClause("mark DESC ");
        ScoreMapRuleExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andRuleTypeEqualTo(0);

        if (!StringUtils.isEmpty(itemName)) {
            criteria.andItemNameLike("%" + itemName + "%");
        }
        if (!StringUtils.isEmpty(gradeName)) {
            criteria.andGradeNameLike("%" + gradeName + "%");
        }
        if (!StringUtils.isEmpty(gender)) {
            criteria.andGenderEqualTo(gender);
        }

        List<ScoreMapRule> scoreMapRuleList = scoreMapRuleMapper.selectByExample(example);
        PageInfo<ScoreMapRule> pageInfo = new PageInfo<ScoreMapRule>(scoreMapRuleList);

        return pageInfo;
    }

    @Override
    public int saveSportItem(SportItem item, User user) {
        int flag = 0;
        System.out.println("Item:"+item.getItemId());
        item.setSchoolId(user.getSchoolId());
        if (StringUtils.isEmpty(item.getItemId())) {
            //新增
            item.setItemId(null);
            item.setCreateBy(user.getId());
            item.setCreateTime(System.currentTimeMillis());
            item.setItemId(ConstantUtil.getPrimaryKey());
            flag = sportItemMapper.insertSelective(item);
        } else {
            //修改
            item.setUpdateBy(user.getId());
            item.setUpdateTime(System.currentTimeMillis());
            flag = sportItemMapper.updateByPrimaryKeySelective(item);
        }
        return flag;
    }

    @Override
    public int saveScoreMapRule(ScoreMapRule mapRule, User user) {
        int flag = 0;
        mapRule.setSchoolId(user.getSchoolId());
        if (StringUtils.isEmpty(mapRule.getRuleId())) {
            //新增
            mapRule.setCreateTime(DateUtils.getCurrentTime());
            mapRule.setCreateBy(user.getId());
            mapRule.setRuleId(ConstantUtil.getPrimaryKey());
            flag = scoreMapRuleMapper.insertSelective(mapRule);
        } else {
            //修改
            mapRule.setUpdateBy(user.getId());
            mapRule.setUpdateTime(DateUtils.getCurrentTime());
            flag = scoreMapRuleMapper.updateByPrimaryKeySelective(mapRule);
        }
        return flag;
    }

    @Override
    public int saveStuScore(StuScoreRef stuScoreRef) {
        int flag = 0;
        if (StringUtils.isEmpty(stuScoreRef.getTestSeq())) {
            stuScoreRef.setTestSeq(ConstantUtil.getPrimaryKey());
            flag = stuScoreRefMapper.insertSelective(stuScoreRef);
        } else {
            flag = stuScoreRefMapper.updateByPrimaryKeySelective(stuScoreRef);
        }
        return flag;
    }

    @Override
    public int addStuScore(StuScoreRef stuScoreRef) {
        int flag = 0;
        flag = stuScoreRefMapper.insertSelective(stuScoreRef);
        return flag;
    }
    @Override
    public SportItem getItemById(String itemId,User user){
        SportItemExample sportItemExample = new SportItemExample();
        sportItemExample.createCriteria().andItemIdEqualTo(itemId);
        List<SportItem> sportItems=sportItemMapper.selectByExample(sportItemExample);
        return sportItems.get(0);
    }

    @Override
    public SportItem getItemByName(String itemName, User user) {
        SportItemExample example = new SportItemExample();
        example.createCriteria().andItemNameEqualTo(itemName).andDelFlagEqualTo(0).andSchoolIdEqualTo(user.getSchoolId());

        List<SportItem> itemList = sportItemMapper.selectByExample(example);

        if (itemList.size() > 0) {
            return itemList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SportItem> getAllItem(User user) {
        SportItemExample example = new SportItemExample();
        example.setOrderByClause("item_name");
        example.createCriteria().andDelFlagEqualTo(0).andItemTypeEqualTo(0).andSchoolIdEqualTo(user.getSchoolId());
        List<SportItem> sportItemList = sportItemMapper.selectByExample(example);
        return sportItemList;
    }

    @Override
    public List<SportItem> getAllTzItem() {
        SportItemExample example = new SportItemExample();
        example.setOrderByClause("item_name ");
        example.createCriteria().andDelFlagEqualTo(0).andItemTypeEqualTo(1);
        List<SportItem> sportItemList = sportItemMapper.selectByExample(example);
        return sportItemList;
    }

    @Override
    public List<Integer> getAllTestCount(User user) {
        List<Integer> a = a_sportTestMapper.getAllTestCount(user.getSchoolId());
        return a;
    }

    @Override
    public List<String> getAllNj(User user) {
        List<String> nj = a_sportTestMapper.getAllNj(user.getSchoolId());

        return nj;
    }

    @Override
    public List<ClassSection> getSections(String schoolId) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);

        return classSectionMapper.selectByExample(example);
    }

    @Override
    public List<Map> getSchoolClass(String schoolId, List<String> param) {
        List<Map> res = a_sportTestMapper.getSchoolClass(schoolId, param);
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    @Override
    public int updateRefScore(StuScoreRef stuScoreRef, User user) {
        StuScoreRefExample example = new StuScoreRefExample();
        example.createCriteria().andStudentNumEqualTo(stuScoreRef.getStudentNum())
                .andSchoolIdEqualTo(user.getSchoolId()).andItemIdEqualTo(stuScoreRef.getItemId());

        StuScoreRef ref = new StuScoreRef();
        ref.setExpire(1);//导入新的测试成绩，之前的成绩设置为过期
        ref.setUpdateDate(DateUtils.getCurrentTime());
        ref.setUpdateBy(user.getId());
        int flag = stuScoreRefMapper.updateByExampleSelective(ref, example);
        return flag;
    }

    @Override
    public int setScoreAndLevel(StuScoreRef stuScoreRef) {

        int flag = stuScoreRefMapper.updateByPrimaryKeySelective(stuScoreRef);
        return flag;
    }

    @Override
    public List<Map> getClassDetail(String shortName, String schoolId) {
        List<Map> res = a_sportTestMapper.getClassDetail(shortName, schoolId);

        return res;
    }

    @Override
    public ClassSection getSection(String xdName, User user) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(user.getSchoolId())
                .andNameEqualTo(xdName);
        List<ClassSection> classSectionList = classSectionMapper.selectByExample(example);
        ClassSection classSection = new ClassSection();
        if (classSectionList.size() > 0) {
            classSection = classSectionList.get(0);
        }
        return classSection;
    }

    @Override
    public List<Map> getAvg(Map param) {
        List<Map> res = a_sportTestMapper.getAvg(param);
        return res;
    }

    @Override
    public List<Map> getFailStuList(Map param) {
        List<Map> res = a_sportTestMapper.getFailStuList(param);
        return res;
    }

    @Override
    public List<Map> getScoreLine(Map param) {
        List<Map> res = a_sportTestMapper.getScoreLine(param);
        return res;
    }

    @Override
    public List<Map> getItems(Integer scoreType, String xdId, Integer njId, String schoolId) {
        return a_sportTestMapper.getItems(scoreType, xdId, njId, schoolId);
    }


    @Override
    public Map<Object, Object> chooseRuleByInfo(Map<Object, Object> paramMap) {

        List<Map<Object, Object>> res = a_sportTestMapper.chooseRuleByInfo(paramMap);
        Map result = new HashMap();
        if (res.size() > 0) {
            result = res.get(0);
        }
        return result;
    }

    @Override
    public Map getGlobalDaily(Map param) {
        Map res = a_sportTestMapper.getGlobalDaily(param);
        if (GukeerStringUtil.isNullOrEmpty(res)) {
            return null;
        } else {
            return a_sportTestMapper.getGlobalDaily(param);
        }
    }

    @Override
    public PageInfo<Map> getGlobalSport(Map param) {
        int pageNum = NumberConvertUtil.convertS2I(param.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(param.get("pageSize").toString());

        PageHelper.startPage(pageNum, pageSize);
        List<Map> res = a_sportTestMapper.getGlobalSport(param);
        PageInfo<Map> pageInfo = new PageInfo<Map>(res);
        return pageInfo;
    }

    @Override
    public List<Map> getTeacherIdentify(String schoolId, String teacherId) {
        return a_sportTestMapper.getTeacherIdentify(schoolId, teacherId);
    }

    @Override
    public boolean scoreExist(StuScoreRef ref) {
        StuScoreRefExample example = new StuScoreRefExample();
        example.createCriteria().andDelFlagEqualTo(0).andStudentNumEqualTo(ref.getStudentNum())
                .andItemNameEqualTo(ref.getItemName()).andTestIdEqualTo(ref.getTestId());
        List<StuScoreRef> res = stuScoreRefMapper.selectByExample(example);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean stuNumExist(String stuNum) {
        StudentExample example = new StudentExample();
        example.createCriteria().andDelFlagEqualTo(0).andXhEqualTo(stuNum);
        List<Student> res = studentMapper.selectByExample(example);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean itemExist(String itemName) {
        SportItemExample sportItemExample = new SportItemExample();
        sportItemExample.createCriteria().andDelFlagEqualTo(0).andItemNameEqualTo(itemName);
        List<SportItem> res = sportItemMapper.selectByExample(sportItemExample);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean xdExist(String xd) {
        ClassSection res = classSectionMapper.selectByPrimaryKey(xd);
        if (!GukeerStringUtil.isNullOrEmpty(res)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean njExist(String xd, Integer nj) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo(0).andNjEqualTo(nj).andXdEqualTo(xd);
        List<GradeClass> res = gradeClassMapper.selectByExample(example);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<GradeClass> getAllClass(String schoolId) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);

        List<GradeClass> res = gradeClassMapper.selectByExample(example);
        return res;
    }

    @Override
    public List<String> itemNameList(Integer itemType) {
        SportItemExample example = new SportItemExample();
        example.createCriteria().andDelFlagEqualTo(0).andItemTypeEqualTo(itemType);
        List<SportItem> itemList = sportItemMapper.selectByExample(example);
        List<String> itemNameList = new ArrayList<String>();
        for (SportItem item : itemList) {
            itemNameList.add(item.getItemName());
        }
        return itemNameList;
    }

    @Override
    public List<String> stuNoList(String schoolId) {
        StudentExample example = new StudentExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<Student> studentList = studentMapper.selectByExample(example);
        List<String> stuNoList = new ArrayList<String>();
        for (Student student : studentList) {
            stuNoList.add(student.getXh());
        }
        return stuNoList;
    }

    @Override
    public int batchSave(List<StuScoreRef> scoreList, User user) {
        List<List> devList = ConstantUtil.createList(scoreList, 1000);
        for (int i = 0; i < devList.size(); i++) {
            a_sportTestMapper.batchInsertScore(devList.get(i));
        }
        return scoreList.size();
    }

    @Override
    public List<Map> getBatchInfo(List<String> prims) {
        return a_sportTestMapper.getBatchInfo(prims);
    }

    @Override
    public int batchUpdateScore(List<StuScoreRef> stuScoreRef) {
        List<List> devList = ConstantUtil.createList(stuScoreRef,1000);
        for (int i = 0; i < devList.size(); i++) {
            a_sportTestMapper.batchUpdateScore(devList.get(i));
        }
        return stuScoreRef.size();
    }

    @Override
    public List<Map> batchStuInfo(List<StuScoreRef> stuScoreRefs) {
        return a_sportTestMapper.batchStuInfo(stuScoreRefs);
    }

    @Override
    public int batchMapRuleSave(List<ScoreMapRule> ruleList) {
        List<List> devList = ConstantUtil.createList(ruleList,1000);
        for (int i = 0; i < devList.size(); i++) {
            a_sportTestMapper.batchMapRuleSave(devList.get(i));
        }
        return ruleList.size();
    }



    @Override
    public int delItemByPrimaryId(String itemId){
        SportItemExample sportItemExample = new SportItemExample();
        sportItemExample.createCriteria().andItemIdEqualTo(itemId);
//        SportItem sportItem = sportItemMapper.selectByPrimaryKey(itemId);
        SportItem sportItem = new SportItem();
        sportItem.setItemId(itemId);
        sportItem.setDelFlag(1);

        return sportItemMapper.updateByPrimaryKeySelective(sportItem);
    }

    @Override
    public PageInfo<Map> checkSportClassByname(Map map){
        String schoolId = (String) map.get("schoolId");
        int pageNum = NumberConvertUtil.convertS2I(map.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(map.get("pageSize").toString());

        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Map> mapList = a_sportClassMapper.selectByClassName(schoolId);
        //List viewList = formatList(mapList);

        PageInfo<Map> pageInfo = new PageInfo<Map>(mapList);
        return pageInfo;
    }

//    @Override
//    public List<Map> addSportCheck(String schoolId){
//        List<Map> mapList = new ArrayList<Map>();
//        List<Map> maps = a_sportClassMapper.selectAddClassName(schoolId);
//        for(int i =0;i<maps.size();i++){
//            Map entityMap = new HashMap();
//            entityMap = maps.get(i);
//            Integer nj =(Integer) maps.get(i).get("nj");
//            String getStringNJ = getValueByKeyAndFlag(nj,"nj");
//            entityMap.put("nj",getStringNJ);
//            mapList.add(entityMap);
//        }
//        return mapList;
//    }
}
