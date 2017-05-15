package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateRefTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_ChangeStateRefTeacherMapper {
    int insertBatch(List<ChangeStateRefTeacher> changeStateRefTeachers);
    int deleteBatch(List<Object> ObjectList);
    List<Map<String,Object>> selectMinId(@Param("source") String source, @Param("syncDate") Long syncDate);

    int checkById(String Teacherid, String source, String ClassId);
    List<ChangeStateRefTeacher> checkTeacherId();
    List<ChangeStateRefTeacher> checkClassId();
    List<ChangeStateRefTeacher> checkBysyncId();
    int deleteBatch(@Param("listId") List<ChangeStateRefTeacher> id, @Param("listTeacherId") List<ChangeStateRefTeacher> listTeacherId, @Param("listClassId") List<ChangeStateRefTeacher> listClassId);
}
