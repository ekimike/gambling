package com.game.game.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class PlayerGame {

    private String userName;

    @Setter
    private int roundsPlayed;

    @Setter
    private int wonMatches;

    public PlayerGame updateRounds() {
        roundsPlayed += 1;
        return this;
    }

    public PlayerGame updateWonMatches(int matchResult) {
        wonMatches += matchResult;
        return this;
    }

    public PlayerGame resetPlayerMatch() {
        roundsPlayed = 0;
        wonMatches = 0;
        return this;
    }

}
