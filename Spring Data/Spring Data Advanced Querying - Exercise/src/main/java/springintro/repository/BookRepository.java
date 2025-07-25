package springintro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springintro.model.entity.AgeRestriction;
import springintro.model.entity.Book;
import springintro.model.entity.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    @Query("SELECT b FROM Book b WHERE YEAR(b.releaseDate) <> :year")
    List<Book> findByReleaseYearNot(@Param("year") int year);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContainingIgnoreCase(String name);

    List<Book> findByAuthorLastNameStartingWithIgnoreCase(String end);

    @Query("SELECT count(b) FROM Book b WHERE length(title) > :length")
    int findBooksWithHigherCount(@Param("length") int length);

    @Query("SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            "FROM Book b " +
            "WHERE title = :title")
    List<Object[]> findBooksByTitle(@Param("title") String title);
}
