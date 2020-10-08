package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.WantedList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WantedListDao extends JpaRepository<WantedList, Integer> {
    List<WantedList> findAllByUsername(String username);

    WantedList findByBid(int bid);

    void deleteById(int id);
}
