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
    public HashMap<String, PlayerGame> play(@PathVariable String user) {

        gameMatchService.playTest(playerRecord, user);
        return playerRecord;
    }

    @DeleteMapping("/delete_game")
    public void delete() {

    }

    @PutMapping("/{user}/restart")
    public String restartGameForUser(@PathVariable String user) {

        if(playerRecord.containsKey(user)) {
            PlayerGame playerGame = playerRecord.get(user);
            playerGame.resetPlayerMatch();
            System.out.println(playerGame);
            return "Actualizado";
        } else {
            return "usuario no existe";
        }
    }

    @GetMapping
    public HashMap<String, PlayerGame> gameReport() {
        return playerRecord;
    }
}
