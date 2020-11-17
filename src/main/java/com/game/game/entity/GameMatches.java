package com.game.game.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class GameMatches {

    @Setter
    private String user;
    private int numberRoundsPlayed;
    private int totalDraws;
    private int winsFirstPlayer;
    private int winsSecondPlayer;
    @Setter
    private HashMap<String, PlayerGame> recordByPlayer;

    @Setter
    private Result result;

    public void setNumberRoundsPlayed() {
        numberRoundsPlayed += 1;
    }

    public void setTotalDraws() {
        totalDraws += 1;
    }

    public void setWinsFirstPlayer() {
        winsFirstPlayer += 1;
    }

    public void setWinsSecondPlayer() {
        winsSecondPlayer += 1;
    }
}
