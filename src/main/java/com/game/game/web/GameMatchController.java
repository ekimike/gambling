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

        int userOneGameResult = gameMatchService.play(user);
        saveUserMatch(user, userOneGameResult);
        counter++;
        return "hola: " + counter;
    }

    @DeleteMapping("/delete_game")
    public void delete() {

    }

    private void saveUserMatch(String user, int countMatchesWon) {
        if ( playerRecord.containsKey(user) ) {
            int userMatches = playerRecord.get(user).getRoundsPlayed() + 1;
            playerRecord.get(user).setRoundsPlayed(userMatches);
        } else {
            PlayerGame playerGame = PlayerGame
                    .builder()
                    .userName(user)
                    .roundsPlayed(1)
                    .build();
            playerRecord.put(user, playerGame);
        }
    }
}
