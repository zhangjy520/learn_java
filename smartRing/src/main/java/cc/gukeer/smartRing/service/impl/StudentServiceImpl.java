package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.persistence.dao.*;
import cc.gukeer.smartRing.persistence.entity.RefClassStudentExample;
import cc.gukeer.smartRing.persistence.entity.Student;
import cc.gukeer.smartRing.persistence.entity.StudentExample;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends BasicService implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    A_StudentExtensionMapper extensionMapper;

    @Autowired
    A_StudentMapper a_studentMapper;

    @Autowired
    RefClassStudentMapper refClassStudentMapper;
    @Autowired
    A_RefClassStudentMapper a_refClassStudentMapper;

    @Override
    public Student selectStudentByMac(String mac) {
        return extensionMapper.selectStudentByMac(mac);
    }

    @Override
    public Student selectByPrimaryKey(String studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public Student selectByXh(String schoolId,String xh) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andXhEqualTo(xh).andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<Student> students = studentMapper.selectByExample(studentExample);
        if(students.size() != 1 ){
            return null;
        }else return students.get(0);
    }

    @Override
    public List<Map<String, String>> selectNoBoundStudentBySchoolIdAndClassId(String schoolId, String classId) {

        List<Map<String, String>> list = null;
        if (StringUtils.isBlank(schoolId) || StringUtils.isBlank(classId)) {
            return list;
        }
        list = extensionMapper.selectNoBoundRingStudentBySchoolIdAndClassId(schoolId, classId);

        return list;
    }
    @Override
    public List<Student> getAllStudent(User user){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSchoolIdEqualTo(user.getSchoolId());
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        return studentList;
    }

    @Override
    public PageInfo checkAllStudent(Map map){
        int pageNum = NumberConvertUtil.convertS2I(map.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(map.get("pageSize").toString());
        String schoolId = (String) map.get("schoolId");
        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Map> mapList = a_studentMapper.selectStudent(schoolId);

        PageInfo<Map> pageInfo = new PageInfo<Map>(mapList);
        return pageInfo;
    }



    @Override
    public List<Map> selectStudentByAttribute(Map map){

        return a_studentMapper.selectStudentByCondition(map);
    }

    @Override
    public List<Map> selectAllStudent(User user){
        return a_studentMapper.getAllStudent(user.getSchoolId());
    }

    @Override
    public int deleteStudent(String classId,String studentxh){
        RefClassStudentExample refClassStudentExample = new RefClassStudentExample();
        refClassStudentExample.createCriteria().andSportClassIdEqualTo(classId).andStudentIdEqualTo(studentxh);
        return refClassStudentMapper.deleteByExample(refClassStudentExample);
    }

    @Override
    public List<Map> getClassStudent(String classId){
        return a_studentMapper.getClassStudent(classId);
    }
}
