package SpringDataIntro.services;

import com.example.SpringDataIntro.models.Book;
import com.example.SpringDataIntro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getBooksTitlesAfter2000() {
        LocalDate date = LocalDate.of(2000, 12, 31);
        List<Book> books = bookRepository.findByReleaseDateAfter(date);

        books.forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void getGeorgePowellBooks() {
        List<Book> books = bookRepository.findAllGeorgePowellBooks();

        books.forEach(book -> System.out.println(book.getTitle() + " " + book.getRelease_date() + " " + book.getCopies()));
    }
}
