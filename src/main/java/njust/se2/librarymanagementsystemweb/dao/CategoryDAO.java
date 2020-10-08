package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
