package org.example.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface GameService {
    void AddGame(String title, BigDecimal price, double size, String trailer, String thumbnail, String description, LocalDate releaseDate);

    void EditGame(long id, List<String> values);

    void DeleteGame(long id);

    void AllGames();

    void DetailGame(String title);

    void OwnedGames();
}
