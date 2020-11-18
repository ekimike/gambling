package com.game.game.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameDetail {

    private String firstPlayerSelection;
    private String secondPlayerSelection;
    private String matchResult;
}
