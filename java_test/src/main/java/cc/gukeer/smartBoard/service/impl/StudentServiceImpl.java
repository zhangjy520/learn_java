package cc.gukeer.smartBoard.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.smartBoard.persistence.dao.A_StudentExtensionMapper;
import cc.gukeer.smartBoard.persistence.dao.StudentMapper;
import cc.gukeer.smartBoard.persistence.entity.Student;
import cc.gukeer.smartBoard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BasicService implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    A_StudentExtensionMapper extensionMapper;

    @Override
    public Student selectStudentByMac(String mac) {
        return extensionMapper.selectStudentByMac(mac);
    }

    @Override
    public Student selectByPrimaryKey(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }
}
