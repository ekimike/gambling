package com.game.game.entity;


import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@ToString
public class PlayerGame {

    private  String userName;
    private  int roundsPlayed;
    private  int wonMatches;
    private  int draws;

    public static class Builder {
        private final String userName;
        private int roundsPlayed, wonMatches, draws;

        public Builder(String userName) {
            this.userName = userName;
        }

        public Builder roundsPlayed(int val) {
            roundsPlayed = val;
            return this;
        }

        public Builder wonMatches(int val) {
            wonMatches += val;
            return this;
        }

        public Builder draws(int val) {
            draws = val;
            return this;
        }

        public PlayerGame build() {
            return new PlayerGame(this);
        }
    }

    private PlayerGame(Builder builder) {
        userName = builder.userName;
        roundsPlayed = builder.roundsPlayed;
        wonMatches = builder.wonMatches;
        draws = builder.draws;
    }

    public PlayerGame updateRounds() {
        roundsPlayed += 1;
        return this;
    }

    public PlayerGame updateWonMatches(int matchResult) {
        wonMatches += matchResult;
        return this;
    }

    public PlayerGame updateDrawMatches(int drawResult) {
        draws += drawResult;
        return this;
    }

    public PlayerGame resetPlayerMatch() {
        roundsPlayed = 0;
        wonMatches = 0;
        draws = 0;
        return this;
    }

}
