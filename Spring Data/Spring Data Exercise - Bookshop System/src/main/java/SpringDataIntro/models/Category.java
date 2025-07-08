package SpringDataIntro.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBook() {
        return books;
    }

    public void setBook(Set<Book> book) {
        this.books = book;
    }

    public Category() {
    }

    public Category(String name) {
        this.setName(name);
    }
}
