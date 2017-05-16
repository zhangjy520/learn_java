package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.smartRing.persistence.dao.*;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.persistence.entity.extension.ExtensionTeacher;
import cc.gukeer.smartRing.persistence.entity.extension.RoleView;
import cc.gukeer.smartRing.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class RoleServiceImpl extends BasicService implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    A_RoleExtensionMapper roleExtensionMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public PageInfo<Role> findAllList(int pageNum, int pageSize) {

        RoleExample example = new RoleExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);

        return pageInfo;
    }

    @Override
    public Role findRoleById(String id) {

        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public int updateRole(Role role) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIdEqualTo(role.getId());
        int count = roleMapper.updateByExampleSelective(role, example);
        return count;
    }

    @Override
    public int insertRole(Role role) {
        int count = roleMapper.insertSelective(role);
        return count;
    }

    @Override
    public List<RoleMenu> findRoleMenuList(String roleId) {

        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);

        List<RoleMenu> roleMenuList = roleMenuMapper.selectByExample(example);

        return roleMenuList;
    }


    public RoleMenu findRoleMenu(String roleId, String menuId) {

        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdEqualTo(menuId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example);

        RoleMenu roleMenu = null;
        if (null != roleMenus && roleMenus.size() > 0) {
            roleMenu = roleMenus.get(0);
        }
        return roleMenu;
    }

    /**
     * 添加角色对于的menu
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public int assignRoleMenu(String roleId, String menuId) {

        RoleMenu roleMenu = findRoleMenu(roleId, menuId);
        if (null != roleMenu) {
            return 0;
        }
        roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        int count = roleMenuMapper.insert(roleMenu);
        return count;
    }

    /**
     * 删除角色对于的menu
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public int deleteRoleMenu(String roleId, String menuId) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdEqualTo(menuId);
        int count = roleMenuMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<Role> findRoleBySchoolId(String schoolId) {
        RoleExample example = new RoleExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<RoleView> findRoleViewBySchoolId(String schoolId) {
        List<RoleView> roleViewList = roleExtensionMapper.findRoleViewBySchoolId(schoolId);
        List<RoleView> viewList = selectRoleMenusToRoleView( roleViewList);
        return  viewList;
    }

    @Override
    public PageInfo<RoleView> findRoleViewBySchoolId(String schoolId, int pageNum, int pageSize) {
        PageHelper.startPage( pageNum, pageSize);
        List<RoleView> roleViewList = roleExtensionMapper.findRoleViewBySchoolId(schoolId);
        List<RoleView> viewList = selectRoleMenusToRoleView( roleViewList);
        PageInfo< RoleView> pageInfo = new PageInfo<RoleView>(viewList);
        return pageInfo;
    }

    //重构
    public List<RoleView> selectRoleMenusToRoleView( List<RoleView> roleViewList) {
        for (RoleView roleview : roleViewList) {
            List<RoleMenu> roleMenus = findRoleMenuList(roleview.getId());
            String menuName = "";
            String menuId = "";
            for (RoleMenu roleMenu : roleMenus) {
                Menu menu = findMenuById(roleMenu.getMenuId());
                if (menuName == "") menuName = menu.getName();
                else menuName = menuName + " 、 " + menu.getName();
                if (menuId == "") menuId = menu.getId().toString();
                else menuId = menuId + "," + menu.getId().toString();
            }
            roleview.setMenuName(menuName);
            roleview.setMenuId(menuId);
        }
        return roleViewList;
    }

    @Override
    public int insertRoleBackId(Role role) {
        return roleExtensionMapper.insertRoleBackId(role);
    }

    @Override
    public List<ExtensionTeacher> findTeacherUser(String schoolId, String search) {
        return roleExtensionMapper.findTeacherUser(schoolId, search);
    }

    @Override
    public int deleteUserRole(String userId, String roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(example);
    }

    @Override
    public int deleteUserRoleByUser(String userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(example);
    }

    @Override
    public int saveUserRole(String userId, String roleId) {
        deleteUserRoleByUser(userId);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRoleMapper.insert(userRole);
    }

    @Override
    public int saveTeacherClass(String teacherId, List<String> classId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId);
        teacherClassMapper.deleteByExample(example);
        int count = 0;
        if (classId.size() > 0) {
            for (String id : classId) {
                TeacherClass teacherClass = new TeacherClass();
                teacherClass.setClassId(id);
                teacherClass.setTeacherId(teacherId);
                int temp = teacherClassMapper.insert(teacherClass);
                count += temp;
            }
        }
        return count;
    }

    @Override
    public List<TeacherClass> findClassByTeacher(String teacherId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId);
        return teacherClassMapper.selectByExample(example);
    }

    @Override
    public List<GradeClass> findClass(String schoolId, String xd, int nj) {
        GradeClassExample example = new GradeClassExample();
        GradeClassExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        if (StringUtils.isNotBlank(xd)) {
            criteria.andXdEqualTo(xd);
        }
        if (nj > 0) {
            criteria.andNjEqualTo(nj);
        }
        return gradeClassMapper.selectByExample(example);
    }

    @Override
    public List<RoleMenu> findRoleMenu() {
        RoleMenuExample example = new RoleMenuExample();
//        example.createCriteria();
        return roleMenuMapper.selectByExample(example);
    }

    @Override
    public Menu findMenuById(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Role findRoleByName(String schoolId, String roleName) {
        RoleExample example = new RoleExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(roleName);
        List<Role> roles = roleMapper.selectByExample(example);
        if(roles.size() == 0){
            return null;
        }else{
            return roles.get(0);
        }
    }

    @Override
    public int deleteRoleMenuByRole(String roleId) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return roleMenuMapper.deleteByExample(example);
    }
}
