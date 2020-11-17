package com.game.game.service;

import com.game.game.entity.PlayerGame;

import java.util.HashMap;

public interface GameMatchReport {

    void save(HashMap<String, PlayerGame> xyz, String user, int matchResult);
}
