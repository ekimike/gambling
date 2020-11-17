package com.game.game.service;

import com.game.game.entity.PlayerGame;

import java.util.HashMap;

public interface GameMatchService {

    int play(String user);

    void playTest(HashMap<String, PlayerGame> playerRecord, String user);
}
