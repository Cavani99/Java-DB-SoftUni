package SpringDataIntro.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Author() {
    }

    public Author(String first_name, String last_name) {
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
    }

}
