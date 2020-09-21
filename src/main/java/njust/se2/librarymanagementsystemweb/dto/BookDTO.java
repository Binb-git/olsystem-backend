package njust.se2.librarymanagementsystemweb.dto;

import lombok.Data;
import lombok.ToString;
import njust.se2.librarymanagementsystemweb.dto.base.OutputConverter;
import njust.se2.librarymanagementsystemweb.pojo.Book;

@Data
@ToString
public class BookDTO implements OutputConverter<BookDTO, Book> {

    private int id;

    private String bookname;

    private String press;

    private String author;

    private String date;

    private String abs;

    private String cover;

    private int cid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
