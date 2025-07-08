package SpringDataIntro;

import SpringDataIntro.models.*;
import SpringDataIntro.repositories.AuthorRepository;
import SpringDataIntro.repositories.BookRepository;
import SpringDataIntro.repositories.CategoryRepository;
import SpringDataIntro.services.AuthorService;
import SpringDataIntro.services.BookService;
import SpringDataIntro.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class Main implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;

    private final CategoryService categoryService;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public Main(AuthorService authorService, BookService bookService, CategoryService categoryService,
                BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        //bookService.getBooksTitlesAfter2000();
        //authorService.findDistinctByBooksReleaseDateBefore();
        //authorService.findDistinctByBooksCount();
        bookService.getGeorgePowellBooks();
    }

    public void callSeeds() throws IOException {
        seedCategories();
        seedAuthors();
        seedBooks();
    }

    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/" + "books.txt"))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();

                    Book book = new Book(title, editionType, price, releaseDate, ageRestriction, author, categories, copies);
                    bookRepository.save(book);
                });
    }

    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/" + "authors.txt"))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    String first_name = data[0];
                    String last_name = data[1];

                    Author author = new Author(first_name, last_name);
                    authorRepository.save(author);
                });
    }

    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of("src/main/resources/" + "categories.txt"))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    String name = data[0].trim();

                    if (!name.isEmpty()) {
                        Category category = new Category(name);
                        categoryRepository.save(category);
                    }
                });
    }
}
