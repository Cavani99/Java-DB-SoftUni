package SpringDataIntro.services;

import com.example.SpringDataIntro.models.Author;

public interface AuthorService {
    Author getRandomAuthor();

    void findDistinctByBooksReleaseDateBefore();

    void findDistinctByBooksCount();
}
