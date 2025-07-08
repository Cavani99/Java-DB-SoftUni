package SpringDataIntro.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction age_restriction;

    @Column(name = "copies")
    private int copies;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType edition_type;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "title")
    private String title;


    @ManyToMany
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AgeRestriction getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(AgeRestriction age_restriction) {
        this.age_restriction = age_restriction;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEdition_type() {
        return edition_type;
    }

    public void setEdition_type(EditionType edition_type) {
        this.edition_type = edition_type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getRelease_date() {
        return releaseDate;
    }

    public void setRelease_date(LocalDate release_date) {
        this.releaseDate = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {

    }

    public Book(String title, EditionType editionType, BigDecimal price,
                LocalDate release_date, AgeRestriction age_restriction,
                Author author, Set<Category> categories, int copies) {
        this.setTitle(title);
        this.setEdition_type(editionType);
        this.setPrice(price);
        this.setRelease_date(release_date);
        this.setAge_restriction(age_restriction);
        this.setAuthor(author);
        this.setCategories(categories);
        this.setCopies(copies);
    }

}
