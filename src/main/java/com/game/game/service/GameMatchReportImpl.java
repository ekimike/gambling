package com.game.game.service;

import com.game.game.entity.PlayerGame;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameMatchReportImpl implements GameMatchReport{

    @Override
    public void save(HashMap<String, PlayerGame> xyz, String user, int matchResult) {

        PlayerGame existingPlayer = xyz.get(user);

        if( null == existingPlayer ) {
            existingPlayer = PlayerGame
                    .builder()
                    .userName(user)
                    .roundsPlayed(1)
                    .wonMatches(matchResult)
                    .build();

            xyz.put(user, existingPlayer);
        } else {
            existingPlayer.updateRounds().updateWonMatches(matchResult);
        }



        System.out.println(existingPlayer);
    }
}
