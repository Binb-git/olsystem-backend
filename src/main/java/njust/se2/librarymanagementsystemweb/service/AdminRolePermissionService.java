package njust.se2.librarymanagementsystemweb.service;

import njust.se2.librarymanagementsystemweb.dao.AdminRolePermissionDAO;
import njust.se2.librarymanagementsystemweb.pojo.AdminPermission;
import njust.se2.librarymanagementsystemweb.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    /**
     * 通过 rid 查询所有的角色权限
     *
     * @param rid admin_role表中的id
     * @return 所有符合条件的条目
     */
    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }

    /**
     * 更改权限信息
     * @param rid 职位 id
     * @param perms 权限对象列表
     */
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }
}
