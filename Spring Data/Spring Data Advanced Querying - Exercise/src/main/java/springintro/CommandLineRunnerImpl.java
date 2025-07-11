package springintro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springintro.model.entity.AgeRestriction;
import springintro.model.entity.Book;
import springintro.model.entity.EditionType;
import springintro.service.AuthorService;
import springintro.service.BookService;
import springintro.service.CategoryService;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
       // pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        /*
        String input = "miNor";
        AgeRestriction ageRestriction = parseAgeRestriction(input);
        bookService.findByAgeRestriction(ageRestriction);*/
        bookService.findByCopiesLessThan(EditionType.GOLD, 5000);
    }

    private AgeRestriction parseAgeRestriction(String input) {
        return AgeRestriction.valueOf(input.toUpperCase());
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
