package cc.gukeer.smartBoard.service;

import cc.gukeer.smartBoard.persistence.entity.Student;

public interface StudentService {
    Student selectStudentByMac(String mac);

    Student selectByPrimaryKey(Integer studentId);
}
