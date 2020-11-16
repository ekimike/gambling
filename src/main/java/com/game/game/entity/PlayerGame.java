package com.game.game.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PlayerGame {

    private String userName;

    @Setter
    private int roundsPlayed;

    @Setter
    private int wonMatches;

}
