package com.game.game.service;

import com.game.game.entity.PlayerGame;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameMatchReportImpl implements GameMatchReport{

    private final int INT_ONE = 1;

    @Override
    public void save(HashMap<String, PlayerGame> xyz, String user, int matchResult, int drawResult) {

        PlayerGame existingPlayer = xyz.get(user);

        if( null == existingPlayer ) {

            existingPlayer = new PlayerGame.Builder(user)
                    .roundsPlayed(INT_ONE)
                    .wonMatches(matchResult)
                    .draws(drawResult)
                    .build();
            xyz.put(user, existingPlayer);
        } else {
            existingPlayer
                    .updateRounds()
                    .updateWonMatches(matchResult)
                    .updateDrawMatches(drawResult);
        }



        System.out.println(existingPlayer);
    }
}
