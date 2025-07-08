package SpringDataIntro.repositories;

import com.example.SpringDataIntro.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReleaseDateAfter(LocalDate date);


    @Query(value = """
                SELECT b.*\s
                FROM books b\s
                LEFT JOIN authors a ON a.author_id = b.author_id\s
                WHERE a.first_name LIKE 'George' AND a.last_name LIKE 'Powell'
                GROUP BY b.book_id\s
                ORDER BY b.release_date DESC, b.title ASC
            """, nativeQuery = true)
    List<Book> findAllGeorgePowellBooks();
}
