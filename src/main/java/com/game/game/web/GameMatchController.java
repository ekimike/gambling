package com.game.game.web;

import com.game.game.entity.PlayerGame;
import com.game.game.service.GameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;

@RestController
@RequestMapping("/match/player")
public class GameMatchController {

    @Autowired private GameMatchService gameMatchService;

    private int counter;
    private HashMap<String, PlayerGame> playerRecord = new HashMap<>();

    @GetMapping("/{user}/new_game")
    public String play(@PathVariable String user) {

//        playerRecord.put(user, null);
        gameMatchService.playTest(playerRecord, user);
//        int matchResult = gameMatchService.play(user);
//        saveUserMatch(user, matchResult);
        return gameMatchService.toString();
    }

    @DeleteMapping("/delete_game")
    public void delete() {

    }

    private void saveUserMatch(String user, int countMatchesWon) {
        if ( playerRecord.containsKey(user) ) {
            int userRoundsPlayed = getRoundsPlayedAndAddOne(user, playerRecord);
            playerRecord.get(user).
                    setRoundsPlayed(userRoundsPlayed);

        } else {
            PlayerGame playerGame = PlayerGame
                    .builder()
                    .userName(user)
                    .roundsPlayed(1)
                    .build();
            playerRecord.put(user, playerGame);
        }
    }

    private int getRoundsPlayedAndAddOne(final String user, final HashMap<String, PlayerGame> playerRecord) {
        return playerRecord.get(user).getRoundsPlayed() + 1;
    }

    private int getRoundsWonAndAddOne(final String user, final HashMap<String, PlayerGame> playerRecord) {
        return playerRecord.get(user).getWonMatches() + 1;
    }
}
