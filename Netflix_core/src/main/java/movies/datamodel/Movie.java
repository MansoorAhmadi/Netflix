package movies.datamodel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DATE")
    private String date;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private Set<SeenMovie> users;

    public Movie(String title, String date, Set<SeenMovie> users) {
        this.title = title;
        this.date = date;
        this.users = users;
    }

    public Movie() {
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
