package com.game.game.entity;

public class GameDetailFacade {

    public static GameDetail getGameDetail(GameMatches gameMatch) {

        Result result = gameMatch.getResult();
        GameDetail gameDetail = GameDetail.builder()
                .firstPlayerSelection(getFirstPlayerSelection(result))
                .secondPlayerSelection("ROCK")
                .matchResult(getFirstPlayerResult(result))
                .build();

        return gameDetail;
    }

    private static String getFirstPlayerSelection(Result firstPlayerSelection) {

        String result;

        switch (firstPlayerSelection) {
            case DRAWN: result =  "ROCK"; break;
            case FIRST_WIN: result =  "PAPER"; break;
            case SECOND_WIN: result =  "SCISSOR"; break;
            default:
                throw new IllegalStateException("Unexpected value: " + firstPlayerSelection);
        };

        return result;
    }

    private static String getFirstPlayerResult(Result  firstPlayerSelection) {
        String result;

        switch (firstPlayerSelection) {
            case DRAWN: result = "DRAW"; break;
            case FIRST_WIN: result = "WON"; break;
            case SECOND_WIN: result = "LOST"; break;
            default:
                throw new IllegalStateException("Unexpected value: " + firstPlayerSelection);
        }

        return result;
    }
}
