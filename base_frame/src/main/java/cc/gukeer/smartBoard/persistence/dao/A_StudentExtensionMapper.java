package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.Student;

/**
 * Created by conn on 16-10-11.
 */
public interface A_StudentExtensionMapper {

    Student selectStudentByMac(String mac);
    
}
