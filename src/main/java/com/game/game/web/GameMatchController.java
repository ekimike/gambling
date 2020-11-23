package com.game.game.web;

import com.game.game.entity.GameMatches;
import com.game.game.entity.GameMatchesDTO;
import com.game.game.entity.GameResult;
import com.game.game.entity.PlayerGame;
import com.game.game.service.GameMatchService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2

@RestController
@RequestMapping("/match")
public class GameMatchController {

    @Autowired private GameMatchService gameMatchService;

    private GameMatches gameMatches = new GameMatches();

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/player/{user}/new_game")
    public EntityModel<PlayerGame> play(@PathVariable String user) {

        log.info("new_game");

        gameMatches.setUser(user);
        gameMatchService.play(gameMatches);

        EntityModel<PlayerGame> gamePlayer = EntityModel.of(gameMatches.getRecordByPlayer().get(user));
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).restartGameForUser(user));
        gamePlayer.add(linkTo.withRel("reset-player-stats"));

        return gamePlayer;
    }

    @CrossOrigin
    @PatchMapping("/player/{user}/restart")
    public EntityModel<GameResult> restartGameForUser(@PathVariable String user) {

        log.info("restart user stats: ", user);

        GameResult gameResult;
        PlayerGame playerGame;

        if( null == gameMatches || null == gameMatches.getRecordByPlayer() ) {
            gameResult = GameResult.builder().message("there is no games yet").build();
        } else if( !gameMatches.getRecordByPlayer().containsKey(user) ) {
            gameResult = GameResult.builder().message("such a user doesn't has played yet").build();
        } else {
            playerGame =  gameMatches.getRecordByPlayer().get(user);
            playerGame.resetPlayerMatch();
            gameResult = GameResult.builder().message("updated").build();
        }

        EntityModel<GameResult> gameResultEntityModel = EntityModel.of(gameResult);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).players());
        gameResultEntityModel.add(linkTo.withRel("all-gamers"));
        return gameResultEntityModel;
    }

    @CrossOrigin
    @GetMapping("/players")
    public HashMap<String, PlayerGame> players() {
        log.info("list all players");

        HashMap<String, PlayerGame> recordByPlayer = gameMatches.getRecordByPlayer();
        return recordByPlayer;
    }

    @CrossOrigin
    @GetMapping("/resume")
    public EntityModel<GameMatchesDTO> gameResume() {
        log.info("resume game");

        GameMatchesDTO gameMatchesDTO = GameMatchesDTO
                .builder()
                .numberRoundsPlayed(gameMatches.getNumberRoundsPlayed())
                .totalDraws(gameMatches.getTotalDraws())
                .winsFirstPlayer(gameMatches.getWinsFirstPlayer())
                .winsSecondPlayer(gameMatches.getWinsSecondPlayer())
                .build();

        EntityModel<GameMatchesDTO> gameResume = EntityModel.of(gameMatchesDTO);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).players());
        gameResume.add(linkTo.withRel("all-players"));

        return gameResume;
    }

    @CrossOrigin
    @GetMapping("/hello")
    public String getHello() {
        return "hello from springboot";
    }
}
