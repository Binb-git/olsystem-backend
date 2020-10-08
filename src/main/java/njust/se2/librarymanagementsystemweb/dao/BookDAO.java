package njust.se2.librarymanagementsystemweb.dao;

import njust.se2.librarymanagementsystemweb.pojo.Book;
import njust.se2.librarymanagementsystemweb.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
    List<Book> findAllByCategory(Category category);

    List<Book> findAllByBooknameLikeOrAuthorLike(String keyword1, String keyword2);

    List<Book> findAllById(int id);

    Book findBookById(int id);
}
