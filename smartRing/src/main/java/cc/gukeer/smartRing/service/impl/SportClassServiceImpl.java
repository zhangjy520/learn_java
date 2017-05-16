package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.persistence.dao.A_RefClassStudentMapper;
import cc.gukeer.smartRing.persistence.dao.A_SportClassMapper;
import cc.gukeer.smartRing.persistence.dao.RefClassStudentMapper;
import cc.gukeer.smartRing.persistence.dao.SportClassMapper;
import cc.gukeer.smartRing.persistence.entity.RefClassStudentExample;
import cc.gukeer.smartRing.persistence.entity.SportClass;
import cc.gukeer.smartRing.persistence.entity.SportClassExample;
import cc.gukeer.smartRing.service.SportClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/3/6.
 */
@Service
public class SportClassServiceImpl implements SportClassService {
    @Autowired
    SportClassMapper sportClassMapper;
    @Autowired
    A_SportClassMapper a_sportClassMapper;
    @Autowired
    A_RefClassStudentMapper a_refClassStudentMapper;
    @Autowired
    RefClassStudentMapper refClassStudentMapper;
    @Override
    public int insertSportClass(SportClass sportClass){
        return sportClassMapper.insertSelective(sportClass);
    }
//    @Override
//    public List<String> getExtraId(){
//        List<Map<String,String>> mapList = a_sportClassMapper.selectMinId();
//        List<String> stringList = new ArrayList<String>();
//        for(int i = 0;i<mapList.size();i++){
//            stringList.add(mapList.get(i).get("id"));
//        }
//        return stringList;
//    }
    @Override
    public  int deleteBatchId(List<String> id){
        return a_sportClassMapper.deleteBatch(id);
    }

    @Override
    public int deleteClass(String id,String schoolId){
        SportClassExample sportClassExample = new SportClassExample();
        sportClassExample.createCriteria().andSportClassIdEqualTo(id).andSchoolIdEqualTo(schoolId);
        SportClass sportClass = new SportClass();
        sportClass.setDelFlag(1);
        RefClassStudentExample refClassStudentExample = new RefClassStudentExample();
        refClassStudentExample.createCriteria().andSportClassIdEqualTo(id);
        refClassStudentMapper.deleteByExample(refClassStudentExample);
        return sportClassMapper.updateByExampleSelective(sportClass,sportClassExample);
    }
    @Override
    public Map checkSportClassById(String id,String schoolId){
        return a_sportClassMapper.selectByClassId(id,schoolId);
    }

    @Override
    public int delete(String classId,String schoolId){
        SportClassExample sportClassExample = new SportClassExample();
        sportClassExample.createCriteria().andSportClassIdEqualTo(classId).andSchoolIdEqualTo(schoolId);
        return sportClassMapper.deleteByExample(sportClassExample);
    }

    @Override
    public int updateClass(String id,String schoolId,SportClass sportClass){
        SportClassExample sportClassExample = new SportClassExample();
        sportClassExample.createCriteria().andSportClassIdEqualTo(id).andSchoolIdEqualTo(schoolId);
        return sportClassMapper.updateByExampleSelective(sportClass,sportClassExample);
    }
    @Override
    public PageInfo<Map> getClassCount(Map map ){
        int pageNum = NumberConvertUtil.convertS2I(map.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(map.get("pageSize").toString());
        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Map> mapList = a_refClassStudentMapper.getClassCount();
        PageInfo<Map> pageInfo = new PageInfo<Map>(mapList);
        return pageInfo;
    }

    @Override
    public SportClass selectSportClassByName(String name) {
        SportClassExample sportClassExample = new SportClassExample();
        sportClassExample.createCriteria().andSportClassNameEqualTo(name).andDelFlagEqualTo(0);
        List<SportClass> sportClassList = sportClassMapper.selectByExample(sportClassExample);
        SportClass sportClass = new SportClass();
        if(sportClassList.size()>0){
            sportClass = sportClassList.get(0);
        }else{
            sportClass=null;
        }
        return sportClass;
    }

    @Override
    public Map<String,Long> getMaxScort(String schoolId) {

        return a_sportClassMapper.getMaxScort(schoolId);
    }

}
