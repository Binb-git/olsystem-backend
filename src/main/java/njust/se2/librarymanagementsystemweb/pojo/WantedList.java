package njust.se2.librarymanagementsystemweb.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user_book")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class WantedList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int bid;
    private String username;
    private String adress;
    private String status;
    private String price;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    private String bookname;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    private String phone;
    private String cid;

    public int getId() {
        return id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setId(int id) {
        this.id= id;
    }

    public WantedList(int id, int bid, String username) {
        this.id = id;
        this.bid = bid;
        this.username = username;
    }

    public WantedList(int bid, String username) {
        this.bid = bid;
        this.username = username;
    }

    public WantedList() {
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
