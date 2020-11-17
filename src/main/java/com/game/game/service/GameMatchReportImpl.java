package com.game.game.service;

import com.game.game.entity.GameMatches;
import com.game.game.entity.PlayerGame;
import com.game.game.entity.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameMatchReportImpl implements GameMatchReport{

    private final int INT_ONE = 1;

    @Override
    public void save(GameMatches gameMatches) {
        String user = gameMatches.getUser();
        int actualUserMatchResult = gameMatches.getResult() == Result.FIRST_WIN ? 1 : 0;
        int actualUserMatchResultDraw = gameMatches.getResult() == Result.DRAWN ? 1 : 0;

        PlayerGame existingPlayer;
        HashMap<String, PlayerGame> gameRecord;

        if ( null == gameMatches.getRecordByPlayer() ) {
            gameRecord = new HashMap<>();
            gameMatches.setRecordByPlayer(gameRecord);
        }

        if (  null == gameMatches.getRecordByPlayer().get(user)) {

            existingPlayer = new PlayerGame.Builder(user)
                    .roundsPlayed(INT_ONE)
                    .wonMatches(actualUserMatchResult)
                    .draws(actualUserMatchResultDraw)
                    .build();

            gameMatches.getRecordByPlayer().put(user, existingPlayer);
        } else {
            existingPlayer = gameMatches.getRecordByPlayer().get(user);
            existingPlayer
                    .updateRounds()
                    .updateWonMatches(actualUserMatchResult)
                    .updateDrawMatches(actualUserMatchResultDraw);
        }
    }
}
