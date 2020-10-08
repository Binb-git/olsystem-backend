package njust.se2.librarymanagementsystemweb.service;

import njust.se2.librarymanagementsystemweb.dao.AdminRoleDAO;
import njust.se2.librarymanagementsystemweb.pojo.AdminMenu;
import njust.se2.librarymanagementsystemweb.pojo.AdminPermission;
import njust.se2.librarymanagementsystemweb.pojo.AdminRole;
import njust.se2.librarymanagementsystemweb.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminMenuService adminMenuService;

    /**
     * 列出权限和菜单列表
     * @return 职位列表
     */
    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }


    /**
     * 添加或更新职位信息
     * @param adminRole 角色对象
     */
    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    /**
     * 根据用户名列出对应的角色
     * @param username 用户名
     * @return 职位列表
     */
    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDAO.findAllById(rids);
    }

    /**
     * 更新角色状态
     * @param role 角色对象
     * @return 保存角色信息
     */
    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.getEnabled());
        return adminRoleDAO.save(roleInDB);
    }

    /**
     * 编辑角色信息
     * @param role 角色对象
     */
    public void editRole(@RequestBody AdminRole role) {
        adminRoleDAO.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}
