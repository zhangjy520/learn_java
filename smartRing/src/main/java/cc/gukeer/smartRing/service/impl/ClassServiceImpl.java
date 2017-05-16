package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.smartRing.persistence.dao.ClassSectionMapper;
import cc.gukeer.smartRing.persistence.dao.GradeClassMapper;
import cc.gukeer.smartRing.persistence.entity.ClassSection;
import cc.gukeer.smartRing.persistence.entity.ClassSectionExample;
import cc.gukeer.smartRing.persistence.entity.GradeClass;
import cc.gukeer.smartRing.persistence.entity.GradeClassExample;
import cc.gukeer.smartRing.service.ClassService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassSectionMapper classSectionMapper;
    @Autowired
    GradeClassMapper gradeClassMapper;

    @Override
    public ClassSection findClassSectionByName(String schoolId, String xdName) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(xdName);
        List<ClassSection> classSectionList = classSectionMapper.selectByExample(example);
        if(classSectionList.size() == 0){
            return null;
        }else{
            return classSectionList.get(0);
        }
    }

    @Override
    public GradeClass findClassByName(String schoolId, String xd, int nj, String name) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo(0).andXdEqualTo(xd).andNjEqualTo(nj).andNameEqualTo(name);
        List<GradeClass> gradeClasses = gradeClassMapper.selectByExample(example);
        if(gradeClasses.size() == 0){
            return null;
        }else{
            return gradeClasses.get(0);
        }
    }

    @Override
    public GradeClass findClassById(String id) {
        return gradeClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GradeClass> findClassList( String schoolId) {
//        List<GradeClass> list = new ArrayList<GradeClass>();

        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo( schoolId);
        List<GradeClass> gradeClassList = gradeClassMapper.selectByExample( example);
        List<String> xd = new ArrayList<String>();
        for (GradeClass gradeClass:gradeClassList) {
            if ( !xd.contains( gradeClass.getXd())) {
                xd.add(gradeClass.getXd());
            }
        }
        Collections.sort(xd);
        for ( GradeClass gradeClass:gradeClassList){
            gradeClass.setRemarks(ConstantUtil.getValueByKeyAndFlag(Integer.valueOf(gradeClass.getXd()),"xd")+
                    ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(),"nj")+
                    gradeClass.getName());
            gradeClass.setName(gradeClass.getXd()+","+gradeClass.getNj()+","+gradeClass.getName());

        }
        return gradeClassList;
    }
}
