package movies.datamodel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="MOVIE")
public class Movie {

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DATE")
    private Date date;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private Set<SeenMovie> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<SeenMovie> getUsers() {
        return users;
    }

    public void setUsers(Set<SeenMovie> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", users=" + users +
                '}';
    }
}