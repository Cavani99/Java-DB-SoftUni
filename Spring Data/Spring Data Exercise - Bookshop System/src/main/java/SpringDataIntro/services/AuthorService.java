package SpringDataIntro.services;

import SpringDataIntro.models.Author;

public interface AuthorService {
    Author getRandomAuthor();

    void findDistinctByBooksReleaseDateBefore();

    void findDistinctByBooksCount();
}
