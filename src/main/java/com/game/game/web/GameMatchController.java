package com.game.game.web;

import com.game.game.entity.GameMatches;
import com.game.game.entity.GameMatchesDTO;
import com.game.game.entity.GameResult;
import com.game.game.entity.PlayerGame;
import com.game.game.service.GameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/match")
public class GameMatchController {

    @Autowired private GameMatchService gameMatchService;

    private GameMatches gameMatches = new GameMatches();

    @GetMapping("/player/{user}/new_game")
    public EntityModel<PlayerGame> play(@PathVariable String user) {

        gameMatches.setUser(user);
        gameMatchService.play(gameMatches);

        EntityModel<PlayerGame> gamePlayer = EntityModel.of(gameMatches.getRecordByPlayer().get(user));
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).restartGameForUser(user));
        gamePlayer.add(linkTo.withRel("reset-player-stats"));

        return gamePlayer;
    }

    @PutMapping("/player/{user}/restart")
    public EntityModel<GameResult> restartGameForUser(@PathVariable String user) {

        GameResult gameResult;

        if( null == gameMatches || null == gameMatches.getRecordByPlayer() ) {
            gameResult = GameResult.builder().message("there is no games yet").build();
        } else if( !gameMatches.getRecordByPlayer().containsKey(user) ) {
            gameResult = GameResult.builder().message("such a user doesn't has played yet").build();
        } else {
            gameResult = GameResult.builder().message("updated").build();
        }

        PlayerGame playerGame =  gameMatches.getRecordByPlayer().get(user);
        playerGame.resetPlayerMatch();

        EntityModel<GameResult> gameResultEntityModel = EntityModel.of(gameResult);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).players());
        gameResultEntityModel.add(linkTo.withRel("all-gamers"));
        return gameResultEntityModel;
    }

    @GetMapping("/players")
    public HashMap<String, PlayerGame> players() {
        HashMap<String, PlayerGame> recordByPlayer = gameMatches.getRecordByPlayer();
        return recordByPlayer;
    }

    @GetMapping("/resume")
    public EntityModel<GameMatchesDTO> gameResume() {

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
}
