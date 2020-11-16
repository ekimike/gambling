package com.game.game.service;

import com.game.game.entity.PlayerGame;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameMatchServiceImpl implements GameMatchService {

    GameChosenOption gameChosenOption;

    private final int INT_ONE = 1, INT_ZERO = 0;

    @Override
    public int play(String user) {

        gameChosenOption = PlayerWithRandomOption.getNewHand();

        switch (gameChosenOption) {
            case ROCK:
                System.out.println("DRAW");
                return INT_ZERO;
            case PAPER:
                System.out.println("PLAYER ONE WINS");
                return INT_ONE;
            case SCISSORS:
                System.out.println("PLAYER TWO WINS");
                return INT_ZERO;
            default:
                System.out.println("SOMETHING WENT WRONG");
                return INT_ZERO;

        }
    }
}

class PlayerWithRandomOption extends PlayerGame {

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
