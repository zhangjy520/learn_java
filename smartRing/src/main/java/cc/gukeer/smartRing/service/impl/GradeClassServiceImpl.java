package cc.gukeer.smartRing.service.impl;

import cc.gukeer.smartRing.persistence.dao.A_GradeClassMapper;
import cc.gukeer.smartRing.persistence.dao.GradeClassMapper;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.service.GradeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cc.gukeer.common.utils.ConstantUtil.getValueByKeyAndFlag;

/**
 * Created by pc-daisike on 2017/3/6.
 */
@Service
public class GradeClassServiceImpl implements GradeClassService {
    @Autowired
    A_GradeClassMapper a_gradeClassMapper;
    @Autowired
    GradeClassMapper gradeClassMapper;



    @Override
    public List<Map> getGradeClassName(User user){
        String schoolId = user.getSchoolId();
        List<Map> maps = a_gradeClassMapper.checkXdAndNjAndName(schoolId);
        List<Map> gradeClassList = new ArrayList<Map>();
        for (int i =0;i<maps.size();i++) {
            Map<String,Object> mapstudent = new HashMap();
            Integer nj =(Integer) maps.get(i).get("nj");
            String getStringNJ = getValueByKeyAndFlag(nj,"nj");
            //maps.put("nj",getStringNJ);
            mapstudent.put("nj",getStringNJ);
            mapstudent.put("njInt",nj);


            mapstudent.put("xd",maps.get(i).get("xd"));

            mapstudent.put("name",maps.get(i).get("name"));

            gradeClassList.add(mapstudent);
        }
        return gradeClassList;

    }

    @Override
    public List<Map> getAllClass(User user){
//        String schoolId = user.getSchoolId();
//        GradeClassExample gradeClassExample = new GradeClassExample();
//        gradeClassExample.createCriteria().andSchoolIdEqualTo(user.getSchoolId());
//        gradeClassMapper.selectByExample(gradeClassExample);
        return null;
    }

}
