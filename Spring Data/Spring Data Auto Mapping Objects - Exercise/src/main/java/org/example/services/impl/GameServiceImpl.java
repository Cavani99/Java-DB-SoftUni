package org.example.services.impl;

import org.example.entities.Game;
import org.example.repositories.GameRepo;
import org.example.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepo gameRepo;

    @Autowired
    public GameServiceImpl(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    @Override
    public void AddGame(String title, BigDecimal price, double size, String trailer, String thumbnail, String description, LocalDate releaseDate) {

        if (!validateTitle(title)) {
            return;
        }

        if (!validatePrice(price)) {
            return;
        }

        if (!validateSize(size)) {
            return;
        }

        if (!validateTrailer(trailer)) {
            return;
        }

        if (!validateThumbnail(thumbnail)) {
            return;
        }

        if (!validateDescription(description)) {
            return;
        }

        Game game = new Game();
        game.setTitle(title);
        game.setPrice(price);
        game.setSize(size);

        Pattern pattern = Pattern.compile("(https://www.youtube.com/watch\\?v=)([\\s\\S]+)");
        Matcher matcher = pattern.matcher(trailer);
        if (!matcher.matches()) {
            System.out.println("Trailer must a YouTube video");

            return;
        }
        String videoId = matcher.group(2);

        game.setTrailer(videoId);
        game.setThumbnail(thumbnail);
        game.setDescription(description);
        game.setReleaseDate(releaseDate);

        gameRepo.save(game);
        System.out.println("Added " + title);
    }

    @Override
    public void EditGame(long id, List<String> values) {
        Optional<Game> game = gameRepo.findById(id);

        if (game.isEmpty()) {
            System.out.println("Game does not exist");

            return;
        }

        Game editGame = game.get();
        for (String field : values) {
            String[] value = field.split("=");

            switch (value[0]) {
                case "title":
                    if (validateTitle(value[1])) {
                        editGame.setTitle(value[1]);
                    } else {
                        return;
                    }
                    break;
                case "price":
                    BigDecimal toDecimal = BigDecimal.valueOf(Double.parseDouble(value[1]));

                    if (validatePrice(toDecimal)) {
                        editGame.setPrice(toDecimal);
                    } else {
                        return;
                    }
                    break;
                case "size":
                    double toInt = Double.parseDouble(value[1]);

                    if (validateSize(toInt)) {
                        editGame.setSize(toInt);
                    } else {
                        return;
                    }
                    break;
                case "trailer":
                    if (validateTrailer(value[1])) {
                        Pattern pattern = Pattern.compile("(https://www.youtube.com/watch\\?v=)([\\s\\S]+)");
                        Matcher matcher = pattern.matcher(value[1]);
                        if (!matcher.matches()) {
                            System.out.println("Trailer must a YouTube video");

                            return;
                        }
                        editGame.setTrailer(value[1]);
                    } else {
                        return;
                    }
                    break;
                case "thumbnail":
                    if (validateThumbnail(value[1])) {
                        editGame.setThumbnail(value[1]);
                    } else {
                        return;
                    }
                    break;
                case "description":
                    if (validateDescription(value[1])) {
                        editGame.setDescription(value[1]);
                    } else {
                        return;
                    }
                    break;
                case "release_date":
                    editGame.setReleaseDate(LocalDate.parse(value[1]));
                    break;
            }
        }

        gameRepo.save(editGame);
        System.out.println("Edited " + editGame.getTitle());
    }

    @Override
    public void DeleteGame(long id) {
        Optional<Game> game = gameRepo.findById(id);

        if (game.isEmpty()) {
            System.out.println("Game does not exist");

            return;
        }

        gameRepo.delete(game.get());
        System.out.println("Deleted " + game.get().getTitle());
    }

    private boolean validateTitle(String title) {
        if (!title.matches("^([A-Z]).+")) {
            System.out.println("Title must begin with an Uppercase letter");

            return false;
        }

        if (title.length() < 3 || title.length() > 100) {
            System.out.println("Title must be between 3 and 100 symbols");

            return false;
        }

        Optional<Game> findByTitle = gameRepo.findByTitle(title);

        if (findByTitle.isPresent()) {
            System.out.println("Game already exists");

            return false;
        }

        return true;
    }

    private boolean validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(0)) < 0) {
            System.out.println("Price must be a positive number");

            return false;
        }

        return true;
    }

    private boolean validateSize(double size) {
        if (size < 0) {
            System.out.println("Size must be a positive number");

            return false;
        }

        return true;
    }

    private boolean validateTrailer(String trailer) {
        if (!trailer.matches("(https://www.youtube.com/watch\\?v=)([\\s\\S]+)")) {
            System.out.println("Trailer must a YouTube video");

            return false;
        }

        return true;
    }

    private boolean validateThumbnail(String thumbnail) {
        if (!thumbnail.matches("^(https://).+") && !thumbnail.matches("^(http://).+")) {
            System.out.println("Thumbnail must be an url video");

            return false;
        }

        return true;
    }

    private boolean validateDescription(String description) {
        if (description.length() < 20) {
            System.out.println("Description must be at least 20 symbols");

            return false;
        }

        return true;
    }
}
