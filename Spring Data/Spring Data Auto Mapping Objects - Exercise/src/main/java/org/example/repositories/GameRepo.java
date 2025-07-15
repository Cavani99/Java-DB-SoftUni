package org.example.repositories;

import org.example.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {

    Optional<Game> findByTitle(String title);
}
