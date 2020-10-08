package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
    AdminMenu findById(int id);

    List<AdminMenu> findAllByParentId(int parentId);
}

