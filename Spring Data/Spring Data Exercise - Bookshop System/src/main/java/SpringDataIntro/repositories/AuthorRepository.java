package SpringDataIntro.repositories;

import SpringDataIntro.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    long count();

    Page<Author> findAll(Pageable pageable);

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate date);

    @Query(value = """
                SELECT a.*\s
                FROM authors a\s
                LEFT JOIN books b ON a.author_id = b.author_id\s
                GROUP BY a.author_id\s
                ORDER BY COUNT(b.book_id) DESC
            """, nativeQuery = true)
    List<Author> findAllOrderByBooksCountDesc();
}
