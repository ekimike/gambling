package com.game.game.web;

import com.game.game.entity.GameMatches;
import com.game.game.entity.PlayerGame;
import com.game.game.service.GameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match/player")
public class GameMatchController {

    @Autowired private GameMatchService gameMatchService;

    private GameMatches gameMatches = new GameMatches();

    @GetMapping("/{user}/new_game")
    public PlayerGame play(@PathVariable String user) {
        gameMatches.setUser(user);
        gameMatchService.play(gameMatches);
        return gameMatches.getRecordByPlayer().get(user);
    }

    @PutMapping("/{user}/restart")
    public String restartGameForUser(@PathVariable String user) {

        if( null == gameMatches || null == gameMatches.getRecordByPlayer() ) {
            return "there is no games yet";
        } else if( !gameMatches.getRecordByPlayer().containsKey(user) ) {
            return "such a user doesn't has played yet";
        }

        PlayerGame playerGame =  gameMatches.getRecordByPlayer().get(user);
        playerGame.resetPlayerMatch();
        System.out.println(playerGame);
        return "updated";
    }

    @GetMapping
    public GameMatches gameReport() {
        return gameMatches;
    }
}
