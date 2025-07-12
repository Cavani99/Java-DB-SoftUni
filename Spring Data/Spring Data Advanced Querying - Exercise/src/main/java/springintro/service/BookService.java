package springintro.service;


import org.springframework.data.repository.query.Param;
import springintro.model.entity.AgeRestriction;
import springintro.model.entity.Book;
import springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    void findByAgeRestriction(AgeRestriction ageRestriction);
    void findByCopiesLessThan(EditionType editionType, int copies);

    void findByPriceLessThanAndPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    void findByReleaseDateYearNot(int year);
    void findByReleaseDateBefore(LocalDate date);

    void findByTitleContainingIgnoreCase(String name);
    void findByAuthorLastName(String end);
    void findBooksWithHigherCount(int length);
    void findBooksByTitle(String title);
}
