package njust.se2.librarymanagementsystemweb.service;

import njust.se2.librarymanagementsystemweb.dao.AdminUserRoleDAO;
import njust.se2.librarymanagementsystemweb.pojo.AdminRole;
import njust.se2.librarymanagementsystemweb.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    /**
     * 根据 uid 列出所有用户角色
     * @param uid 用户 id
     * @return 用户角色列表
     */
    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

    /**
     * 保存身份更改, 通过这个方法，把 user表的 id写入了 admin_user_role表的 uid中，
     * 把 role信息写入到了rid中
     * @param uid   user 表中的id
     * @param roles user 表中的身份，实则表中没有，所以加了transient注解，表明不进入数据库
     */
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        adminUserRoleDAO.saveAll(urs);
    }
}
