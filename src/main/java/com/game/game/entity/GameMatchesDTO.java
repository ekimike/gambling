package com.game.game.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameMatchesDTO {

    private int numberRoundsPlayed;
    private int totalDraws;
    private int winsFirstPlayer;
    private int winsSecondPlayer;
}
