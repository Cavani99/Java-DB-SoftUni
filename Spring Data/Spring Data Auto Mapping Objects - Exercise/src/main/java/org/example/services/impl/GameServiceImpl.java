package org.example.services.impl;

import org.example.repositories.GameRepo;
import org.example.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepo gameRepo;

    @Autowired
    public GameServiceImpl(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

}
