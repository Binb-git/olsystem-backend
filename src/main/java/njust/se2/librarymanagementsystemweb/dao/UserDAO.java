package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);

    User getByUsernameAndPassword(String username, String password);

}

