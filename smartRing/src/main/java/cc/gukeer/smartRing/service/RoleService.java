package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.persistence.entity.extension.ExtensionTeacher;
import cc.gukeer.smartRing.persistence.entity.extension.RoleView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface RoleService {

    PageInfo<Role> findAllList(int pageNum, int pageSize);

    Role findRoleById(String id);

    int updateRole(Role role);

    int insertRole(Role role);

    /**
     * roleId 查询roleMenu
     * @param roleId
     * @return
     */
    List<RoleMenu> findRoleMenuList(String roleId);

    /**
     * 添加角色对于的menu
     * @param roleId
     * @param menuId
     * @return
     */
    int assignRoleMenu(String roleId, String menuId);

    /**
     * 删除角色对于的menu
     * @param roleId
     * @param menuId
     * @return
     */
    int deleteRoleMenu(String roleId, String menuId);

    List<Role> findRoleBySchoolId(String schoolId);

    List<RoleView> findRoleViewBySchoolId(String schoolId);

    PageInfo<RoleView> findRoleViewBySchoolId(String schoolId, int pageNum, int pageSize);

    int insertRoleBackId(Role role);

    List<ExtensionTeacher> findTeacherUser(String schoolId,String search);

    int deleteUserRole(String userId, String roleId);

    int deleteUserRoleByUser(String userId);

    int saveUserRole(String userId, String roleId);

    int saveTeacherClass(String teacherId, List<String> classId);

    List<TeacherClass> findClassByTeacher(String teacherId);

    List<GradeClass> findClass(String schoolId, String xd, int nj);

    List<RoleMenu> findRoleMenu();

    Menu findMenuById(String id);

    Role findRoleByName(String schoolId,String roleName);

    int deleteRoleMenuByRole(String roleId);
}
