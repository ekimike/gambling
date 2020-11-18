package com.game.game.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class PlayerGame {

    @JsonProperty("user")
    private  String userName;
    @JsonProperty("rounds played")
    private  int roundsPlayed;
    @JsonProperty("won")
    private  int wonMatches;
    @JsonProperty("draw")
    private  int draws;

    private List<GameDetail> gameDetail;

    public static class Builder {
        private final String userName;
        private int roundsPlayed, wonMatches, draws;
        private List<GameDetail> gameDetail = new ArrayList<>();

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

        public Builder gameDetail(GameDetail val) {
            gameDetail.add(val);
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
        gameDetail = builder.gameDetail;
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

    public PlayerGame updateGameDetails(GameDetail gameMatch) {
        gameDetail.add(gameMatch);
        return this;
    }

    public PlayerGame resetPlayerMatch() {
        roundsPlayed = 0;
        wonMatches = 0;
        draws = 0;
        gameDetail.clear();
        return this;
    }

}
