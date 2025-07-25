package springintro.service;



import springintro.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    void findByFirstNameEndingWith(String end);

    void finBookCopiesForAuthor();
}
