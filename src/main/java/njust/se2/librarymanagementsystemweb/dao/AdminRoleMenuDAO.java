package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu, Integer> {
    List<AdminRoleMenu> findAllByRid(int rid);

    List<AdminRoleMenu> findAllByRid(List<Integer> rids);

    void deleteAllByRid(int rid);
}
