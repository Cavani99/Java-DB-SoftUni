package SpringDataIntro.services;

import SpringDataIntro.models.Author;
import SpringDataIntro.repositories.AuthorRepository;
import SpringDataIntro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long entries = authorRepository.count();
        int index = (int) (Math.random() * entries);
        Page<Author> authorPage = authorRepository.findAll(PageRequest.of(index, 1));
        Author author = null;
        if (authorPage.hasContent()) {
            author = authorPage.getContent().get(0);
        }
        return author;
    }

    @Override
    public void findDistinctByBooksReleaseDateBefore() {
        LocalDate beforeDate = LocalDate.of(1990, 1, 1);
        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(beforeDate);

        authors.forEach(author ->
                System.out.println(author.getFirst_name() + " " + author.getLast_name())
        );
    }

    @Override
    public void findDistinctByBooksCount() {
        List<Author> authors = authorRepository.findAllOrderByBooksCountDesc();

        authors.forEach(author -> System.out.println(author.getFirst_name() + " " + author.getLast_name() + " " + author.getBooks().size())
        );
    }
}
