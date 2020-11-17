package com.game.game.service;

import com.game.game.entity.GameMatches;
import com.game.game.entity.PlayerGame;
import com.game.game.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class GameMatchServiceImpl implements GameMatchService {

    @Autowired private GameMatchReport gameMatchReport;

    @Override
    public void play(GameMatches gameMatches) {

        GameChosenOption gameChosenOption = PlayerWithRandomOption.getNewHand();

        gameMatches.setNumberRoundsPlayed();

        switch (gameChosenOption) {
            case ROCK:
                gameMatches.setTotalDraws();
                gameMatches.setResult(Result.DRAWN);
                gameMatchReport.save(gameMatches);
                break;
            case PAPER:
                gameMatches.setWinsFirstPlayer();
                gameMatches.setResult(Result.FIRST_WIN);
                gameMatchReport.save(gameMatches);
                break;
            case SCISSORS:
                gameMatches.setWinsSecondPlayer();
                gameMatches.setResult(Result.SECOND_WIN);
                gameMatchReport.save(gameMatches);
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
