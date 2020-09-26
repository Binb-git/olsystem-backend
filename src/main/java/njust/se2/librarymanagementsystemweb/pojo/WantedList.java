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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
