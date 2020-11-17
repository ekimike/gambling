package com.game.game.service;

import com.game.game.entity.PlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class GameMatchServiceImpl implements GameMatchService {

    @Autowired private GameMatchReport gameMatchReport;

    private final int INT_ONE = 1, INT_ZERO = 0, INT_ONE_DRAW = 1;

    @Override
    public int play(String user) {

        GameChosenOption gameChosenOption = PlayerWithRandomOption.getNewHand();

        switch (gameChosenOption) {
            case ROCK:
                return INT_ZERO;
            case PAPER:
                return INT_ONE;
            case SCISSORS:
                return INT_ZERO;
            default:
                return INT_ZERO;

        }
    }

    @Override
    public void playTest(HashMap<String, PlayerGame> playerRecord, String user) {
        GameChosenOption gameChosenOption = PlayerWithRandomOption.getNewHand();

        switch (gameChosenOption) {
            case ROCK:
                gameMatchReport.save(playerRecord, user, INT_ZERO, INT_ONE_DRAW);
                break;
            case PAPER:
                gameMatchReport.save(playerRecord, user, INT_ONE, INT_ZERO);
                break;
            case SCISSORS:
                gameMatchReport.save(playerRecord, user, INT_ZERO, INT_ZERO);
                break;
        }

    }


}

final class PlayerWithRandomOption extends PlayerGame {

    static GameChosenOption getNewHand() {
        return GameChosenOption.getRandomValue();
    }
}

enum GameChosenOption {
    ROCK,
    PAPER,
    SCISSORS;

    public static GameChosenOption getRandomValue() {
        Random randomValue = new Random();
        return values()[randomValue.nextInt(values().length)];
    }
}
