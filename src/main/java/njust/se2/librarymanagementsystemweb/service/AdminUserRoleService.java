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

    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

//    @Modifying

    /**
     * 保存身份更改, 通过这个方法，可以看到把user表的id写入了admin_user_role表的uid中，把role信息写入到了rid中
     * @param uid user 表中的id
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
